import java.util.concurrent.Semaphore;

// Puente 2.0: conserva tu simulacion de caravana.
// El primero simula el recorrido; los seguidores ya admitidos esperan fin para salir en orden.
public class Puente2_0 {
    private static final int NORTE = 0;
    private static final int SUR = 1;

    // CAMBIADO: la llave es justa y se conserva hasta adquirir fin.
    private final Semaphore semLLave = new Semaphore(1, true);
    private final Semaphore fin = new Semaphore(1, true);
    private final Semaphore esperaNorte = new Semaphore(0, true);
    private final Semaphore esperaSur = new Semaphore(0, true);
    private final Semaphore mutex = new Semaphore(1, true);

    private int cantEsperaNorte = 0;
    private int cantEsperaSur = 0;
    private int cruzando = 0;
    private int direccion = -1;
    private final long tiempoCruceMilis;

    public Puente2_0() {
        this(1000);
    }

    // AGREGADO: permite acelerar las pruebas sin cambiar el protocolo.
    public Puente2_0(long tiempoCruceMilis) {
        if (tiempoCruceMilis < 0) {
            throw new IllegalArgumentException("El tiempo no puede ser negativo");
        }
        this.tiempoCruceMilis = tiempoCruceMilis;
    }

    public void entrarNorte() throws InterruptedException {
        entrar(NORTE);
    }

    public void entrarSur() throws InterruptedException {
        entrar(SUR);
    }

    // AGREGADO: norte y sur comparten el mismo protocolo para evitar diferencias accidentales.
    private void entrar(int lado) throws InterruptedException {
        semLLave.acquire();
        boolean primero = false;
        boolean esperar;

        try {
            mutex.acquireUninterruptibly();
            try {
                esperar = direccion != -1 && direccion != lado;

                if (!esperar) {
                    // CAMBIADO: ser primero se decide con el estado protegido, no con tryAcquire.
                    primero = cruzando == 0;
                    direccion = lado;
                    cruzando++;
                    anunciarEntrada(lado);
                } else if (lado == NORTE) {
                    cantEsperaNorte++;
                } else {
                    cantEsperaSur++;
                }
            } finally {
                mutex.release();
            }

            if (esperar) {
                // CAMBIADO: tampoco se devuelve la llave al esperar el cambio de direccion.
                // Nadie puede adelantarse al despertado ni tomar una habilitacion suya.
                espera(lado).acquireUninterruptibly();

                mutex.acquireUninterruptibly();
                try {
                    if (lado == NORTE) cantEsperaNorte--;
                    else cantEsperaSur--;

                    primero = cruzando == 0;
                    direccion = lado;
                    cruzando++;
                    anunciarEntrada(lado);
                } finally {
                    mutex.release();
                }
            }

            // QUITADO: tryAcquire, que permite adelantamientos incluso con fairness=true.
            // El siguiente no recibe semLLave hasta que este auto tiene su lugar en fin.
            // Despues del registro las esperas no se cancelan, evitando turnos abandonados.
            fin.acquireUninterruptibly();
        } finally {
            semLLave.release();
        }

        // La llave se devuelve ANTES del recorrido: puede admitirse un seguidor mientras avanzo.
        if (primero) {
            try {
                Thread.sleep(tiempoCruceMilis);
            } catch (InterruptedException e) {
                // AGREGADO: si se interrumpe al primero, devuelve el lugar y fin antes de salir.
                salir(lado);
                throw e;
            }
        }

        // QUITADO: despertar al mismo sentido desde entrar.
        // Su siguiente auto avanza por semLLave; solo hay un solicitante esperando otro sentido.
    }

    public void salirNorte() {
        salir(NORTE);
    }

    public void salirSur() {
        salir(SUR);
    }

    private void salir(int lado) {
        // QUITADO: pedir semLLave al salir. Una entrada puede tenerla mientras espera fin;
        // si la salida tambien la pidiera, no podria devolver fin y se produciria deadlock.
        mutex.acquireUninterruptibly();
        try {
            if (cruzando <= 0 || direccion != lado) {
                throw new IllegalStateException("Salida sin una entrada del mismo sentido");
            }

            // CORREGIDO: se usa cruzando en todo el archivo; no cantCruzando ni una llave distinta.
            cruzando--;
            System.out.println("SALE " + nombre(lado) + ": " + Thread.currentThread().getName());

            if (cruzando == 0) {
                int opuesto = 1 - lado;
                int esperando = opuesto == NORTE ? cantEsperaNorte : cantEsperaSur;

                if (esperando > 0) {
                    direccion = opuesto;
                    espera(opuesto).release();
                } else {
                    // AGREGADO: una llegada posterior puede elegir sentido si el puente quedo vacio.
                    direccion = -1;
                }
            }

            // Fin se devuelve despues de registrar la salida; el siguiente no puede terminar antes.
            fin.release();
        } finally {
            mutex.release();
        }
    }

    private Semaphore espera(int lado) {
        return lado == NORTE ? esperaNorte : esperaSur;
    }

    private void anunciarEntrada(int lado) {
        System.out.println("ENTRA " + nombre(lado) + ": " + Thread.currentThread().getName());
    }

    private String nombre(int lado) {
        return lado == NORTE ? "NORTE" : "SUR";
    }

    // AGREGADO: ejemplo ejecutable sin modificar tu puente.java ni necesitar otras clases.
    public static void main(String[] args) throws InterruptedException {
        int cantidad = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        long tiempo = args.length > 1 ? Long.parseLong(args[1]) : 1000;
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad negativa");

        Puente2_0 puente = new Puente2_0(tiempo);
        Thread[] autos = new Thread[cantidad];

        for (int i = 0; i < cantidad; i++) {
            final int lado = (i / 3) % 2;
            autos[i] = new Thread(() -> {
                try {
                    if (lado == NORTE) {
                        puente.entrarNorte();
                        puente.salirNorte();
                    } else {
                        puente.entrarSur();
                        puente.salirSur();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Auto-" + i);
        }

        for (Thread auto : autos) auto.start();
        for (Thread auto : autos) auto.join();
    }
}

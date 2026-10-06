package tp4Punto10Joaco;

import java.util.concurrent.Semaphore;

public class Peluqueria {
    private final int cantBarberos;
    private final int cantSillasEsp;
    private int cantCortandose;
    private int cantSentados;
    private int cantEspSilla;
    private final Semaphore Mutex;
    private final Semaphore Silla;
    private final Semaphore barbLibre;
    private final Semaphore[] MutexBarbero;
    private final Semaphore[] semCortar;
    private final Semaphore[] semTerminar;

    public Peluqueria(int cantBarberos, int cantSillasEsp) {
        if (cantBarberos < 1 || cantSillasEsp < 1) {
            throw new IllegalArgumentException();
        }
        this.cantBarberos = cantBarberos;
        this.cantSillasEsp = cantSillasEsp;
        this.Mutex = new Semaphore(1);
        this.Silla = new Semaphore(0);
        this.barbLibre = new Semaphore(0);
        this.MutexBarbero = new Semaphore[cantBarberos];
        this.semCortar = new Semaphore[cantBarberos];
        this.semTerminar = new Semaphore[cantBarberos];
    }

    public void llenarArray() {
        for (int i = 0; i < cantBarberos; i++) {
            MutexBarbero[i] = new Semaphore(1);
            semCortar[i] = new Semaphore(0);
            semTerminar[i] = new Semaphore(0);
        }
    }

    public void cortarse() throws InterruptedException {
        Mutex.acquire();
        if (cantCortandose + 1 > cantBarberos) {
            if (cantSentados + 1 > cantSillasEsp) {
                cantEspSilla++;
                Mutex.release();
                Silla.acquire();
                Mutex.release();
            } else {
                cantSentados++;
                Mutex.release();
            }
            barbLibre.acquire();
            liberarSilla();
        } else {
            cantCortandose++;
            Mutex.release();
        }

        int i = 0;
        boolean exito = false;
        Mutex.acquire();
        while (i < cantBarberos && !exito) {
            if (MutexBarbero[i].tryAcquire()) {
                exito = true;
            } else {
                i++;
            }
        }
        if (!exito) {
            Mutex.release();
            throw new IllegalStateException();
        }
        Mutex.release();

        semCortar[i].release();
        semTerminar[i].acquire();

        Mutex.acquire();
        MutexBarbero[i].release();
        if (cantSentados > 0) {
            barbLibre.release();
        } else {
            cantCortandose--;
            Mutex.release();
        }
    }

    private void liberarSilla() {
        if (cantEspSilla > 0) {
            cantEspSilla--;
            Silla.release();
        } else {
            cantSentados--;
            Mutex.release();
        }
    }

    public void cortarPelo(int i) throws InterruptedException {
        semCortar[i].acquire();
        System.out.println("Barbero " + i + " comienza un corte");
        Thread.sleep(5000);
        System.out.println("Barbero " + i + " termina un corte");
        semTerminar[i].release();
    }
}

package tp3Punto3;

public class Hamster implements Runnable {
    String nombre;
    Actividad rueda;
    Actividad hamaca;
    Actividad plato;

    public Hamster(String unNombre, Actividad unaRueda, Actividad unaHamaca, Actividad unPlato) {
        this.nombre = unNombre;
        this.rueda = unaRueda;
        this.hamaca = unaHamaca;
        this.plato = unPlato;
    }

    public void run() {
        if (rueda.estaLibre()) {
            this.rueda.realizarActividad();
            this.rueda.liberar();
        }
        if (hamaca.estaLibre()) {
            this.hamaca.realizarActividad();
            this.hamaca.liberar();
        }
        if (plato.estaLibre()) {
            this.plato.realizarActividad();
            this.plato.liberar();
        }
    }
}

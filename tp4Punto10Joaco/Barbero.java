package tp4Punto10Joaco;

public class Barbero implements Runnable {
    private final int indice;
    private final Peluqueria peluqueria;

    public Barbero(int indice, Peluqueria peluqueria) {
        this.indice = indice;
        this.peluqueria = peluqueria;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                peluqueria.cortarPelo(indice);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

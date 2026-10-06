package tp4Punto10Joaco;

public class Cliente implements Runnable {
    private final Peluqueria peluqueria;

    public Cliente(Peluqueria peluqueria) {
        this.peluqueria = peluqueria;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " llega");
            peluqueria.cortarse();
            System.out.println(Thread.currentThread().getName() + " se retira atendido");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

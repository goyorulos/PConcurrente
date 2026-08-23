package Punto5;
public class UsoHilos {
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando.");
        HiloContador nuevoHilo = new HiloContador("#1");
        nuevoHilo.start();

        for (int i = 0; i < 50; i++) {
            System.out.print(" .");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido");
        }
        System.out.println("Hilo principal finalizado");
    }
}
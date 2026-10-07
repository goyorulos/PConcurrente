package tp4punto10;

public class Main {
    public static void main(String[] args) {
        int sillasDeEspera = 3;
        Peluqueria peluqueria = new Peluqueria(sillasDeEspera);

        // Crear e iniciar el hilo del barbero
        Thread barberoThread = new Thread(new Barbero(peluqueria), "Barbero-1");
        barberoThread.start();

        // Simular la llegada de 10 clientes con intervalos de tiempo aleatorios
        for (int i = 1; i <= 10; i++) {
            Thread clienteThread = new Thread(new Customer(peluqueria), "Cliente" + i);
            clienteThread.start();

            try {
                // Genera diferimiento aleatorio de llegada (entre 0 y 2.5 segundos)
                Thread.sleep((long) (Math.random() * 2500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
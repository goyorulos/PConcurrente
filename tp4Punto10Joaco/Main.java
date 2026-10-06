package tp4Punto10Joaco;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int cantBarberos = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        int cantSillas = args.length > 1 ? Integer.parseInt(args[1]) : 5;
        int cantClientes = args.length > 2 ? Integer.parseInt(args[2]) : 15;

        Peluqueria peluqueria = new Peluqueria(cantBarberos, cantSillas);
        peluqueria.llenarArray();

        Thread[] barberos = new Thread[cantBarberos];
        for (int i = 0; i < cantBarberos; i++) {
            barberos[i] = new Thread(new Barbero(i, peluqueria), "Barbero " + i);
            barberos[i].start();
        }

        Thread[] clientes = new Thread[cantClientes];
        for (int i = 0; i < cantClientes; i++) {
            clientes[i] = new Thread(new Cliente(peluqueria), "Cliente " + i);
            clientes[i].start();
        }

        for (Thread cliente : clientes) {
            cliente.join();
        }
        for (Thread barbero : barberos) {
            barbero.interrupt();
        }
        for (Thread barbero : barberos) {
            barbero.join();
        }
    }
}

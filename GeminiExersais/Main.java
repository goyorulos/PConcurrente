public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Creamos UNA SOLA cuenta compartida
        CuentaBancaria cuentaCompartida = new CuentaBancaria();

        // Creamos el Hilo 1 (Tú)
        Thread tu = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    cuentaCompartida.depositar();
                }
            }
        });

        // Creamos el Hilo 2 (Tu hermano)
        Thread hermano = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    cuentaCompartida.depositar();
                }
            }
        });

        // Ambos empiezan a depositar al mismo tiempo
        tu.start();
        hermano.start();

        // Esto hace que el main espere a que ambos terminen antes de imprimir el resultado
        tu.join();
        hermano.join();

        // Imprimimos cuánto dinero quedó al final
        System.out.println("Saldo final: $" + cuentaCompartida.saldo);
    }
}
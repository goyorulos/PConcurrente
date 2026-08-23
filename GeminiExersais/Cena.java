public class Cena {
    public static void main(String[] args) {
        
        // Estos son nuestros dos recursos compartidos (solo hay uno de cada uno)
        final Object cuchillo = new Object();
        final Object tenedor = new Object();

        // Hilo 1: Tú
        Thread tu = new Thread(new Runnable() {
            @Override
            public void run() {
                // Paso 1: Agarras el cuchillo y le pones candado
                synchronized (cuchillo) {
                    System.out.println("Tú: Tengo el cuchillo en la mano izquierda.");
                    
                    // Pausa de 100ms (como si tardaras un instante en alcanzar el otro cubierto)
                    try { Thread.sleep(100); } catch (Exception e) {}
                    
                    System.out.println("Tú: Intentando agarrar el tenedor...");
                    
                    // Paso 2: Intentas agarrar el tenedor
                    synchronized (tenedor) {
                        System.out.println("Tú: ¡Tengo ambos, estoy comiendo!");
                    }
                }
            }
        });

        // Hilo 2: Tu amigo
        Thread amigo = new Thread(new Runnable() {
            @Override
            public void run() {
                // Paso 1: Tu amigo agarra primero el tenedor y le pone candado
                synchronized (cuchillo) {
                    System.out.println("Amigo: Tengo el tenedor en la mano derecha.");
                    
                    // Pausa de 100ms
                    try { Thread.sleep(100); } catch (Exception e) {}
                    
                    System.out.println("Amigo: Intentando agarrar el cuchillo...");
                    
                    // Paso 2: Intenta agarrar el cuchillo
                    synchronized (tenedor) {
                        System.out.println("Amigo: ¡Tengo ambos, estoy comiendo!");
                    }
                }
            }
        });

        // ¡A comer!
        tu.start();
        amigo.start();
    }
}
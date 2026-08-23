package Punto2;
public class Hilo extends Thread {
    private Thread este;
    public Hilo(Thread unHilo){
        este = unHilo;
    }

    public void run() {
        ir();
        try {
            este.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
    public void ir() {
        hacerMas();
    }   
    public void hacerMas() {
        System.out.println("En la pila");
    }
}
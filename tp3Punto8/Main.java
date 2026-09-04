package tp3Punto8;

public class Main {
    public static void main(String[] args) {
        Controlador control = new Controlador();
        Hilo h1 = new Hilo("A", 1, control);
        Hilo h2 = new Hilo("BB", 2, control);
        Hilo h3 = new Hilo("CCC", 3, control);
        Thread hilo1 = new Thread(h1);
        Thread hilo2 = new Thread(h2);
        Thread hilo3 = new Thread(h3);
        hilo1.start();
        hilo2.start();
        hilo3.start();

    }
}

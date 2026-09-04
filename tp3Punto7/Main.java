package tp3Punto7;

public class Main {
    public static void main(String[] args) {
        Surtidor surtidor = new Surtidor(50);
        Auto auto1 = new Auto("abrath", 0, 20, 5, surtidor);
        Auto auto2 = new Auto("fiat", 0, 5, 0, surtidor);
        Thread hilo1 = new Thread(auto1);
        Thread hilo2 = new Thread(auto2);
        hilo1.start();
        hilo2.start();
    }
}

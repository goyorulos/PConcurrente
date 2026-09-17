package tp4Punto3;

public class Main {
    public static void main(String[] args) {
        Barrera barrera = new Barrera();
        HiloP1 p1 = new HiloP1(1, barrera);
        HiloP1 p2 = new HiloP1(2, barrera);
        HiloP1 p3 = new HiloP1(3, barrera);
        Thread h1 = new Thread(p1,"1");
        Thread h2 = new Thread(p2,"2");
        Thread h3 = new Thread(p3,"3");
        h1.start();
        h2.start();
        h3.start();

    }
}

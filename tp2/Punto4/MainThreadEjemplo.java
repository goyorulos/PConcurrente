package Punto4;

public class MainThreadEjemplo {
    public static void main(String[]args){
        ThreadEjemplo o1 = new ThreadEjemplo("Maria Jose");
        ThreadEjemplo o2 = new ThreadEjemplo("paco pedro");

        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);

        t1.start();
        t2.start();
    }
}

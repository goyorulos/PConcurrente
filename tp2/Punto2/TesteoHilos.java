package Punto2;
public class TesteoHilos {
    public static void main(String[] args) {
        Thread hiloMain = Thread.currentThread();
        Thread miHilo = new Hilo(hiloMain);
    
        miHilo.start();

        try{
            miHilo.join();
        } catch(InterruptedException e){
            System.out.println("error");
        }

        System.out.println("En el main");
    }
}
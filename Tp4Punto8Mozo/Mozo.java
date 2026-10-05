package Tp4Punto8Mozo;

public class Mozo implements Runnable{
    private Comedor confiteria;
    public Mozo(Comedor unComedor){
        this.confiteria = unComedor;
    }
    public void run(){
        this.confiteria.abrirComedor();
        while(true){
            this.confiteria.inventarPollo();
            this.confiteria.tomarOrden();
            try {
                System.out.println(Thread.currentThread().getName() + "Esta preparando la comida");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.confiteria.servircomida();
        }
    }
}

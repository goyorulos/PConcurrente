package Tp4Punto8Mozo;

public class Empleado implements Runnable{
    private Comedor confiteria;
    public Empleado(Comedor unComedor){
        this.confiteria = unComedor;
    }

    public void run(){
        this.confiteria.EntrarComedor();
        this.confiteria.comer();
        try {
            Thread.sleep(100);//Empleado comiendo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.confiteria.levantarseEirse();
    }
}

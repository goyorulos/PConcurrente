package tp4punto6;

import java.util.concurrent.Semaphore;

public class taxi {
    private Semaphore inicio;
    private Semaphore fin;

    public taxi (){
        this.inicio = new Semaphore(0);
        this.fin = new Semaphore(0);
    }

    public void pedirViaje(){
        this.inicio.release();
        try {
            this.fin.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void iniciarViaje(){
        try {
            this.inicio.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.fin.release();
    }
}

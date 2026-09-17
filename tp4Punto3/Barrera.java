package tp4Punto3;

import java.util.concurrent.Semaphore;

public class Barrera {
    private Semaphore barrera1;
    private Semaphore barrera2;
    private Semaphore barrera3;

    public Barrera(){
        barrera1 = new Semaphore(1);
        barrera2 = new Semaphore(0);
        barrera3 = new Semaphore(0);
    }

    public void obtenerBarrera(int id){
        if(id == 1){
            try {
                barrera1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            barrera3.release();
        }else if(id == 3){
            try {
                barrera3.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            barrera2.release();
        }else if(id == 2){
            try {
                barrera2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            barrera1.release();
        }
    }
}

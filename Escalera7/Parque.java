package Escalera7;

import java.util.concurrent.Semaphore;

public class Parque {
    private Semaphore toboganSem = new Semaphore(0, true);
    private Semaphore toboganesLibres = new Semaphore(2,true);
    private Semaphore semPrimero = new Semaphore(1,true);
    private Semaphore esperando = new Semaphore(0,true);
    private Semaphore escaleraSem;

    public Parque(int x){
        escaleraSem = new Semaphore(x, true);
    }

    //==================Visitante====================

    public void subirEscalera() throws InterruptedException{
        this.escaleraSem.acquire();
    }

    public void esperarTobogan() throws InterruptedException{
        //Espero a estar primero
        this.semPrimero.acquire();
        //Despierto al wn del encargado
        this.esperando.release();
        //el webiado me deja subir
        this.toboganSem.acquire();
        this.escaleraSem.release();
        //dejo de ser el primero
        this.semPrimero.release();
    }

    public void bajarTobogan() throws InterruptedException{
        toboganesLibres.release();
    }

//================Encargado======================

    public void dejarPasar() throws InterruptedException{
        this.esperando.acquire();
        toboganesLibres.acquire();
        toboganSem.release();
    }

}

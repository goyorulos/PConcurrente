package tp4punto10;

import java.util.concurrent.Semaphore;

public class Peluqueria {
    private Semaphore semCliente;
    private Semaphore semPeluquero;
    private Semaphore semFinCorte;
    private Semaphore mutex;
    private int sillasTotales;
    private int sillasOcupadas;

    public Peluqueria(int sillas){
        this.semCliente = new Semaphore(0);
        this.semPeluquero = new Semaphore(0);
        this.semFinCorte = new Semaphore(0);
        this.mutex = new Semaphore(1);
        this.sillasTotales = sillas;
        this.sillasOcupadas = 0;
    }

    //================Cliente==================//

    public boolean intentoSentarmeEspera() throws InterruptedException{
        boolean exito = false;
        this.mutex.acquire();
        if(this.sillasOcupadas<this.sillasTotales){
            System.out.println(Thread.currentThread().getName() + ": al no haber sillas ocupadas me siento");
            exito = true;
            this.sillasOcupadas++;
            if(this.sillasOcupadas==1){
                this.semCliente.release();
            }
            this.mutex.release();
            this.semPeluquero.acquire();
        }else{
            this.mutex.release();
        }
        return exito;
        
    }

    public void esperarCortarse() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " me estan haciendo la chapa y pintura");
        this.semFinCorte.acquire();
    }

    //==================Peluquero==================//

    public void esperarCliente() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " me duelmo");
        this.semCliente.acquire();
    }

    public void atenderSiguiente() throws InterruptedException{
        this.mutex.acquire();
        this.sillasOcupadas--;
        this.semPeluquero.release();
        this.mutex.release();
    }

    public boolean terminarCorte() throws InterruptedException{
        this.semFinCorte.release();
        this.mutex.acquire();
        boolean hayEspera = (this.sillasOcupadas>0);
        this.mutex.release();
        return hayEspera;
    }


}

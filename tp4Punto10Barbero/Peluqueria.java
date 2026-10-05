package tp4Punto10Barbero;

import java.util.concurrent.Semaphore;

public class Peluqueria {
    private Semaphore sillaCorte;
    private Semaphore cortarPelo;
    private Semaphore corteTerminado;
    private Semaphore espera;
    private int sillasDisponibles;
    private int esperandoEspera;
    private Semaphore mutex;

    public Peluqueria(int sillas){
        this.cortarPelo = new Semaphore(0);
        this.sillaCorte = new Semaphore(1);
        this.corteTerminado = new Semaphore(0);
        this.espera = new Semaphore(0);
        this.sillasDisponibles = sillas;
        this.mutex = new Semaphore(1);
        this.esperandoEspera = 0;
    }

    public void cortar(){ //Peluquero
        try {
            this.cortarPelo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean entrarPeluqueria(){
        return this.sillaCorte.tryAcquire();
    }

    public void cortarsePelo(){ //cliente
        this.cortarPelo.release();
        try {
            this.corteTerminado.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void terminarCorte(){
        this.corteTerminado.release();
    }

    public void levantarse(){
        try {
            this.mutex.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sentarseEspera(){
        try {
            this.mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(this.sillasDisponibles-1 >= 0){
            this.sillasDisponibles --;
            this.mutex.release();
            this.cortarsePelo();
        }else{
            this.esperandoEspera ++;
            this.mutex.release();
            try {
                this.espera.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package tp4Punto10Barbero;

import java.util.concurrent.Semaphore;

public class Peluqueria {
    private Semaphore cortarPelo;
    private Semaphore [] peluqueros;
    private Semaphore cortarse;
    private Semaphore mutex;
    private Semaphore mutexPeluquero;
    private Semaphore espera;
    private int sillasTotales;
    private int sillasUsadas;
    
    public Peluqueria(int cantPeluqueros, int sillas){
        this.cortarPelo = new Semaphore(0);
        this.peluqueros = new Semaphore[cantPeluqueros];
        this.declararPeluqueros();
        this.cortarse = new Semaphore(0);
        this.sillasTotales = sillas;
        this.mutex = new Semaphore(1);
        this.espera = new Semaphore(0);
        this.mutexPeluquero = new Semaphore(1);

    }

    public boolean hayEspera(){
        return (sillasUsadas>0);
    }

    private void declararPeluqueros(){
        for (int i = 0; i<peluqueros.length;i++){
            peluqueros[i] = new Semaphore(1);
        }
    }


    //==================================================//

    public void esperarCliente(){
        try {
            this.cortarPelo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean entrarPeluqueria(){
        boolean entro = false;
        for(int i = 0; i<peluqueros.length;i++){
            if(peluqueros[i].tryAcquire()){
                this.cortarPelo.release();
                entro = true;
            }
        }
        return entro;
    }

    public void esperarCorte(){
        try {
            this.cortarse.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cortarPelo(){
        this.cortarse.release();
    }

    public boolean intentarSentarseEspera(){
        boolean exito = false;
        try {
            this.mutex.acquire();
            if(this.sillasUsadas<this.sillasTotales){
                this.sillasUsadas++;   
                exito = true;
            }
            this.mutex.release();
            this.cortarse.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public void levantarse(){
        try {
            this.mutex.acquire();
            if(this.sillasUsadas>=1){
                this.espera.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}

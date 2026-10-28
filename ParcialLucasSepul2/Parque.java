package ParcialLucasSepul2;

import java.util.concurrent.Semaphore;

public class Parque {
    private Semaphore semEscuela;
    private Semaphore semResidente;
    private Semaphore semRegular;
    private Semaphore semSalida;
    private Semaphore mutex;
    private int filaEscuela;
    private int filaResidente;
    private int limiteMax;
    private int actual;
    private int residentes;
    private int escuelas;

    public Parque(){
        this.semEscuela = new Semaphore(0);
        this.semResidente = new Semaphore(0);
        this.semRegular = new Semaphore(0);
        this.semSalida = new Semaphore(0);
        this.mutex = new Semaphore(0);
        this.filaEscuela =0;
        this.filaResidente = 0;
        this.residentes = 0;
        this.escuelas = 0;
    }

    public void entrarEscuela(int cantidad)throws InterruptedException{
        this.semEscuela.acquire();
        this.mutex.acquire();
        this.actual += cantidad;
        this.mutex.release();
    }

    //===============gestor=================

    public void dejarEntrar()throws InterruptedException{
        this.mutex.acquire();
        if(this.escuelas>0){
            if(this.)
        }else if(this.residentes>0){

        }else{

        }
    }

}

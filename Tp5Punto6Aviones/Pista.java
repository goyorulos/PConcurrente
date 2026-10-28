package Tp5Punto6Aviones;

import java.util.concurrent.Semaphore;

public class Pista {
    private int avionesAire;
    private int avionesTierra;
    private int cantAterrizados;
    private Semaphore solicitarAterrizaje = new Semaphore(0);
    private Semaphore solicitarDespegue = new Semaphore(0);
    private Semaphore despegue = new Semaphore(0);
    private Semaphore aterrizaje = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);



    //------------------aviones Volando=============

    public void pedirAterrizar() throws InterruptedException{
        mutex.acquire();
        avionesAire ++;
        mutex.release();
        solicitarAterrizaje.release();
        aterrizaje.acquire();
    }

    public void aterrizar() throws InterruptedException{
        mutex.acquire();
        avionesAire--; //se que esta mal porque se deberia restar apenas obtiene el permiso de aterrizaje 
                       //pero me parecio correcto asi en el codigo del hilo hago un sleep entre los metodos
        mutex.release();
        System.out.println("aterrizo");
    }

    //============aviones Tierra====================

    public void pedirDespegar() throws InterruptedException{
        mutex.acquire();
        avionesTierra++;
        mutex.release();
        solicitarDespegue.release();
        despegue.acquire();
    }

    public void despegar() throws InterruptedException{
        mutex.acquire();
        avionesTierra--; //se que esta mal porque se deberia restar apenas obtiene el permiso de aterrizaje 
                       //pero me parecio correcto asi en el codigo del hilo hago un sleep entre los metodos
        mutex.release();
        System.out.println("Despego");
    }


    //================Torre Control================
    public void xd() throws InterruptedException{
        mutex.acquire();
        if(cantAterrizados >= 10 && avionesTierra >0){
            cantAterrizados = 0;
            mutex.release();
            solicitarDespegue.acquire();
            despegue.release();
        }else if(avionesAire>0){
            cantAterrizados++;
            mutex.release();
            solicitarAterrizaje.acquire();
            aterrizaje.release();
        }else if(avionesTierra >0){
            mutex.release();
            solicitarDespegue.acquire();
            despegue.release();
        }
    }

}

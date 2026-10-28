package tp5punto5TRen;

import java.util.concurrent.Semaphore;

public class Tren {
    private int cantidadMax;
    private int cantActual = 0;
    private Semaphore entradas = new Semaphore(0);
    private Semaphore iniciar = new Semaphore(0);
    private Semaphore salida = new Semaphore(0);
    private Semaphore comprar= new Semaphore(0);
    private Semaphore vender = new Semaphore(0);
    private Semaphore vuelta= new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public Tren(int limite){
        cantidadMax = limite;
    }

    

    public void venderBoleto() throws InterruptedException{
        vender.acquire();
        comprar.release();
    }

    //======================controlTren===========================
    public void habilitarEntrada() throws InterruptedException{
        entradas.release(cantidadMax);
        iniciar.acquire(); 

    }

    public void terminarViaje() throws InterruptedException{
        salida.release();
        vuelta.acquire();
    }

    //=====================Pasajero=========================

    public void comprarBoleto() throws InterruptedException{
        vender.release();
        comprar.acquire();
    }

    public void entrarTren() throws InterruptedException{
        entradas.acquire();
        mutex.acquire();
        cantActual++;
        if(cantActual==cantidadMax){
            iniciar.release();
        }
        mutex.release();
        salida.acquire();
    }

    public void bajar() throws InterruptedException{
        mutex.acquire();
        if(cantActual==1){
            vuelta.release();
        }else{
            salida.release();
        }
        cantActual--;
        mutex.release();
    }
}

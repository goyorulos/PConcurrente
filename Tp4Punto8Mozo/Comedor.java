package Tp4Punto8Mozo;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore inicio;
    private Semaphore comer;
    private Semaphore ordenar;
    private Semaphore comedor;
    private Semaphore termino;
    
    public Comedor(){
        this.inicio = new Semaphore(0);
        this.comer = new Semaphore(0);
        this.ordenar = new Semaphore(0);
        this.comedor = new Semaphore(0);
        this.termino = new Semaphore(0);
    }

    public void abrirComedor(){
        this.comedor.release();
    }
    public void inventarPollo(){
        System.out.println(Thread.currentThread().getName() + " Esta inventando pollo");
        try {
            this.inicio.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void EntrarComedor(){
        System.out.println(Thread.currentThread().getName() + " quiere entrar al comedor, Pide comedor.aquire");
        try {
            this.comedor.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.inicio.release();
        System.out.println(Thread.currentThread().getName() + " Entro al comedor");
        try {
            this.ordenar.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tomarOrden(){
        System.out.println(Thread.currentThread().getName() + "Esta tomadno la orden");
        this.ordenar.release();
    }

    public void comer(){
        System.out.println(Thread.currentThread().getName() + " Quiere comer");
        try {
            this.comer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Esta Comindo");
    }
    public void servircomida(){
        System.out.println(Thread.currentThread().getName() + " Sirvio el plato");
        this.comer.release();
    }

    public void levantarseEirse(){
        System.out.println(Thread.currentThread().getName() + " Se levanto y dejo la silla");
        this.termino.release();
        this.comedor.release();
    }
}

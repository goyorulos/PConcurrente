package parcialLucasSepul;

import java.util.concurrent.Semaphore;

public class Caja {
    private Semaphore semSacar;
    private Semaphore semDepositar;
    private Semaphore mutex;
    private int cantMax;
    private int cantActual;
    private int cantAutosActuales;
    private int cantAutosMax;

    public Caja(int cantidadMaxima){
        this.semSacar = new Semaphore(0);
        this.semDepositar = new Semaphore(0);
        this.mutex = new Semaphore(1);
        this.cantMax = cantidadMaxima;
        this.cantActual = 0;
        this.cantAutosActuales =0;
        this.cantAutosMax = 5;
    }

    //Equipo1/2/3

    public void depositar() throws InterruptedException{
        this.mutex.acquire();
        while(this.cantActual== cantMax){
            this.mutex.release();
            this.semDepositar.acquire();
            this.mutex.acquire();
        }
        this.cantActual++;
        this.semSacar.release();
        this.mutex.release();

    }

    //Equipo4
    public void sacar(int cantidad)throws InterruptedException{
        this.mutex.acquire();
        while(this.cantActual<cantidad){
            this.mutex.release();
            this.semSacar.acquire();
            this.mutex.acquire();
        }
        this.cantActual-=cantidad;
        this.semDepositar.release();
        this.mutex.release();
    }

    public boolean hacerAutos()throws InterruptedException{
        boolean exito = false;
        this.mutex.acquire();
        this.cantAutosActuales++;
        if(this.cantAutosActuales<this.cantAutosMax){
            this.mutex.release();
        }else{
            exito = true;
            this.mutex.release();
        }
        return exito;
    }

    public void reiniciarCajaAutos() throws InterruptedException{
        this.mutex.acquire();
        this.cantAutosActuales =0;
        this.mutex.release();
    }

}

package ParcialFlorGranchelli;

import java.util.concurrent.Semaphore;

;public class Colectivo {
    private int cantMax;
    private int pasajeros;
    private int esperando;
    private Semaphore entrada;
    private Semaphore salida;
    private Semaphore cerrar;
    private Semaphore iniciarViaje;
    private Semaphore mutex;

    public Colectivo(int cantidad){
        this.cantMax = cantidad;
        this.pasajeros = 0;
        this.esperando = 0;
        this.entrada = new Semaphore(0);
        this.salida = new Semaphore(0);
        this.mutex = new Semaphore(1);
        this.cerrar = new Semaphore(0);
        this.iniciarViaje = new Semaphore(0);
    }

    public void habilitarEntrada(){
        this.entrada.release();
    }

    public void entrar(){
        try {
			this.mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        this.esperando ++;
        this.mutex.release();
        try {
			this.entrada.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        try {
			this.mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        this.pasajeros ++;
        if(pasajeros < cantMax){
            this.entrada.release();
        }else if (this.pasajeros == this.cantMax || this.esperando == 0){
            this.iniciarViaje.release();
        }
        this.mutex.release();
    }

    public void salirPasajeros(){
        try {
			this.salida.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        try {
			this.mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        this.pasajeros --;
        if(this.pasajeros == 0){
            this.cerrar.release();
        }
        this.salida.release();
        this.mutex.release();
    }

    public void habilitarSalida(){
        this.salida.release();
        try {
			this.cerrar.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public void iniciarViaje(){
        try {
			this.iniciarViaje.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}

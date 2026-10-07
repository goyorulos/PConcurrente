package ParcialFlorLucasVersion;

import java.util.concurrent.Semaphore;

;public class Colectivo {
    private int cantMax;
    private int pasajeros;
    private int esperando;
    private Semaphore parada;
    private Semaphore entrada;
    private Semaphore salida;
    private Semaphore cerrar;
    private Semaphore salir;
    private Semaphore mutex;
    private Semaphore entro;

    public Colectivo(int cantidad){
        this.cantMax = cantidad;
        this.pasajeros = 0;
        this.esperando = 0;
        this.parada = new Semaphore(0);
        this.entrada = new Semaphore(0);
        this.salida = new Semaphore(0);
        this.mutex = new Semaphore(1);
        this.cerrar = new Semaphore(0);
        this.salir = new Semaphore(0);
        this.entro = new Semaphore(0);
    }

    public void llegarParada() throws InterruptedException{
        this.mutex.acquire();
        this.esperando ++;
      
        if(esperando == 1){
            this.parada.release();
        }
          this.mutex.release();
    }

    public boolean habilitarEntrada(){
        boolean exito = false;
        try {
			this.parada.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        while(!exito){
                this.entrada.release();
            try {
                this.entro.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(pasajeros < cantMax && this.esperando > 0){
                this.mutex.release();
            }else if (this.pasajeros == this.cantMax || this.esperando == 0){
                        exito = true;
                        if (esperando > 0) {
                                parada.release();
                            }
                        this.mutex.release();
                        this.salir.release();
            }
        }
        
       
        return exito;
    }

    public void entrar(){       
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
        this.esperando --;
        this.entro.release();
        
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
        }else{
            
        this.salida.release();
        }
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
			this.salir.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}

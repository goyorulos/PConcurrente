package tp4punto3perrosGatos;

import java.util.concurrent.Semaphore;

public class GestorComedero {
    private Semaphore comederos;
    private Semaphore trabaGato;
    private Semaphore trabaPerro;
    private Semaphore llave;
    private Semaphore mutex;
    private int limite;
    private int cantAdentro;
    private int cantComidos;
    private int filaGato;
    private int filaPerro;
    private int tipo;


    public GestorComedero(int unLimite){
        this.comederos = new Semaphore(unLimite);
        this.trabaGato = new Semaphore(1);
        this.trabaPerro = new Semaphore(1);
        this.llave = new Semaphore(1);
        this.filaGato = 0;
        this.filaPerro = 0;
        this.limite = unLimite;
        this.cantAdentro = 0;
        this.tipo = 0;
    }

    public void agarrarLaveGato() throws InterruptedException{
        this.llave.acquire();
        switch (tipo) {
            case 0:
                this.trabaPerro.acquire();
                mutex.acquire();
                this.tipo =1;
                this.mutex.release();
                break;
            default:
                break;
        }
        this.llave.release();
    }

    public void entrarGato(){
        this.trabaGato.acquire();

    }

    
}


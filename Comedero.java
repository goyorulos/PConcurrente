
import java.util.concurrent.Semaphore;

public class Comedero {

    private Semaphore mutex = new Semaphore(1);
    private Semaphore trabaPerro = new Semaphore(0);
    private Semaphore trabaGato = new Semaphore(0);
    private Semaphore comedero;
    private int cantGatos = 0;
    private int cantPerros = 0;
    private int cantComiendo = 0;
    private int limite;
    private int tipo;
    private int gatosEsperando = 0;
    private int perrosEsperando = 0;


    public Comedero(int comederos, int limite){
        comedero = new Semaphore(comederos);
        this.limite = limite;
        this.tipo = 0;
    }

    public void entrarGato() throws InterruptedException{
        
        mutex.acquire();
        if(tipo == 0){
            tipo = 1;
            
        }
        while (tipo == 2 || (limite <= cantGatos && perrosEsperando > 0)){
            gatosEsperando++;
            mutex.release();
            trabaGato.acquire();
            mutex.acquire();
            gatosEsperando--;
        }
        if(gatosEsperando > 0 && (limite > cantGatos+1 || perrosEsperando == 0)){
            trabaGato.release();
        }
        cantGatos++;
        cantComiendo++;
        mutex.release();
        comedero.acquire();
    }

    public void salirGato() throws InterruptedException{

        mutex.acquire();
        if(cantComiendo == 1){
            if(perrosEsperando > 0){
                tipo = 2;
                cantGatos = 0;
                trabaPerro.release();
            }else if(gatosEsperando == 0){
                tipo = 0;
                cantGatos = 0;
            }

        }
        cantComiendo--;
        mutex.release();
        comedero.release();

    }

    public void entrarPerro() throws InterruptedException{
        
        mutex.acquire();
        if(tipo == 0){
            tipo = 2;
            
        }
        while (tipo == 1 || (limite <= cantPerros && gatosEsperando > 0)){
            perrosEsperando++;
            mutex.release();
            trabaPerro.acquire();
            mutex.acquire();
            perrosEsperando--;
        }
        if(perrosEsperando > 0 && (limite > cantPerros+1 || gatosEsperando == 0)){
            trabaPerro.release();
        }
        cantPerros++;
        cantComiendo++;
        mutex.release();
        comedero.acquire();
    }

    public void salirPerro() throws InterruptedException{

        mutex.acquire();
        if(cantComiendo == 1){
            if(gatosEsperando > 0){
                tipo = 1;
                cantPerros = 0;
                trabaGato.release();
            }else if(perrosEsperando == 0){
                tipo = 0;
                cantPerros = 0;
            }

        }
        cantComiendo--;
        mutex.release();
        comedero.release();

    }

}

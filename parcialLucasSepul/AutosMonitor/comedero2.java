
import java.util.concurrent.Semaphore;
public class comedero2 {
    private Semaphore llave = new Semaphore(1);
    private Semaphore colaPerros = new Semaphore(0);
    private Semaphore colaGatos = new Semaphore(0);
    private Semaphore comedero = new Semaphore(3);
    private int cantPerrosConsecutivos = 0;
    private int cantGatosConsecutivos = 0;
    private int cantPerrosEsperando = 0;
    private int cantGatosEsperando = 0;
    private int cantComiendo = 0;
    private int esperaCOmer = 0;
    private int limite = 5;
    private boolean cambio = false;
    private int tipoComiendo = -1; // 0 = perro, 1 = gato
    private Semaphore mutex = new Semaphore(1);

    private void entrarGato()throws  InterruptedException{
        this.llave.acquire();
        this.mutex.acquire();
        if((this.tipoComiendo == -1 || this.tipoComiendo == 1) && (cantGatosConsecutivos < limite || cantPerrosEsperando == 0 )){
            this.tipoComiendo = 1;
            this.cantGatosConsecutivos ++;
            this.cantPerrosConsecutivos = 0;
            this.esperaCOmer++;
            this.llave.release();
        }else{
            if(cantGatosConsecutivos >= limite && cantPerrosEsperando > 0 ){
                this.cambio = true;
            }
                this.cantGatosEsperando++;
                this.mutex.release();
                this.llave.release();
                this.colaGatos.acquire();
                this.mutex.acquire();
                if(((this.cantGatosConsecutivos) < limite || cantPerrosEsperando == 0) && cantGatosEsperando >  0){
                    cantGatosConsecutivos ++;
                    cantGatosEsperando --;
                    this.esperaCOmer++;
                    this.colaGatos.release();
            }
        }
        
        this.mutex.release();
        this.comedero.acquire();
        this.mutex.acquire();
        this.esperaCOmer--;
        this.cantComiendo ++;
        this.mutex.release();
    }
    private void salirGato() throws InterruptedException{
        this.mutex.acquire();
        if(cantComiendo == 1 && esperaCOmer == 0){
            if(cantPerrosEsperando > 0){
                if(cantGatosConsecutivos >= limite || cantGatosEsperando == 0){
                    cantGatosConsecutivos = 0;
                    cantPerrosConsecutivos ++;
                    cantPerrosEsperando --;
                    this.cambio = false;
                    this.esperaCOmer++;
                    this.colaPerros.release();
                    this.tipoComiendo = 0;
                }
            }else{
                if(cantGatosEsperando == 0){
                    this.tipoComiendo = -1;
                }
            }
        }
        this.cantComiendo--;
        this.comedero.release();
        this.mutex.release();
    }
}

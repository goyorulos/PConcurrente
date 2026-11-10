
import java.util.concurrent.Semaphore;
public class babuinos {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore cuerda = new Semaphore(5);
    private Semaphore esperaNorte = new Semaphore(0);
    private Semaphore esperaSur = new Semaphore(0);
    private int ladoHabilitado = -1; // 0 Norte 1 Sur.
    private int cantCruzando = 0;
    private int cantNorteCruzaron = 0;
    private int consecutivosNorte = 0;
    private int consecutivosSur = 0;
    private int cantSurCruzaron = 0;
    private int CantesperaNorte = 0;
    private int CantesperaSur = 0;
    private int maxPorLado = 10;
    private int babuinosNorte;
    private int babuinosSur;
    public babuinos(int cantNorte, int cantSur){
        this.babuinosNorte = cantNorte;
        this.babuinosSur= cantSur;
    }

    private void cruzarNorte() throws InterruptedException{
        mutex.acquire();
        if(ladoHabilitado == -1 || ladoHabilitado== 0){
            if(!((cantCruzando + cantNorteCruzaron) + 1 == babuinosNorte) || babuinosSur == 0 || (cantSurCruzaron > 0)){
                    ladoHabilitado = 0;
                    consecutivosSur = 0;
                    cantCruzando ++;
                    consecutivosNorte ++;
            }else{
                CantesperaNorte++;
                mutex.release();
                esperaNorte.acquire();
                mutex.acquire();
                if((!((cantCruzando + cantNorteCruzaron) + 1 >= babuinosNorte) || babuinosSur == 0|| cantSurCruzaron > 0) && CantesperaNorte > 0 ){
                    cantCruzando ++;
                    consecutivosNorte ++;
                    CantesperaNorte--;
                    esperaNorte.release();
                }
            }

        }else{
                CantesperaNorte++;
                mutex.release();
                esperaNorte.acquire();
                mutex.acquire();
                if((!((cantCruzando + cantNorteCruzaron) + 1 >= babuinosNorte) || babuinosSur == 0|| cantSurCruzaron > 0) && CantesperaNorte > 0 ){
                    cantCruzando ++;
                    consecutivosNorte ++;
                    CantesperaNorte--;
                    esperaNorte.release();
                }

        }
        mutex.release();
        cuerda.acquire();
    }

    private void salirNorte() throws InterruptedException{
        mutex.acquire();
        if(cantCruzando == 1){
            if(CantesperaSur > 0){
                if(((cantCruzando + cantNorteCruzaron) + 1 >= babuinosNorte) || CantesperaNorte == 0){
                    consecutivosNorte = 0;
                    ladoHabilitado = 1;
                    cantCruzando ++;
                    consecutivosSur ++;
                    CantesperaSur--;
                    esperaSur.release();
                }
            }else if(CantesperaNorte == 0   || cantNorteCruzaron + cantCruzando + 1 == babuinosNorte
    && babuinosSur > 0
    && cantSurCruzaron == 0){
                ladoHabilitado = -1;
            }
        }
        cantNorteCruzaron++;
        cantCruzando--;
        cuerda.release();
        mutex.release();
    }


}

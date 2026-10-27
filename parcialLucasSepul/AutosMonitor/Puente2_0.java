
import java.util.concurrent.Semaphore;
public class Puente2_0 {

    private Semaphore semLLaveN = new Semaphore(1,true);
    private Semaphore semLLaveS = new Semaphore(1,true);
    private Semaphore fin = new Semaphore(1,true);
    private Semaphore esperaNorte = new Semaphore(0,true);
    private Semaphore esperaSur = new Semaphore(0,true);
    private int cantEsperaNorte = 0;
    private int cantEsperaSur = 0;
    private int cruzando = 0;
    private int direccion = -1; // 0 = norte, 1 = sur
    private Semaphore mutex = new Semaphore(1);


    public void entrarNorte() throws  InterruptedException{
        this.semLLaveN.acquire();
        this.mutex.acquire();
        boolean primero = (cruzando == 0);
        if( (this.direccion == -1 || this.direccion == 0) && this.cantEsperaNorte == 0){
            this.direccion = 0;
            this.cruzando++;
            this.mutex.release();
            
    }else{
        this.cantEsperaNorte++;
        this.mutex.release();
        this.esperaNorte.acquire();
        primero = true;
    }
    System.out.println("Cruzando desde el norte" + "auto " + Thread.currentThread().getName());
    
    this.fin.acquire();
    this.semLLaveN.release();
    if(primero){
        Thread.sleep(1000);
    }

  this.mutex.acquire();
     if(this.cantEsperaNorte > 0){
            this.cantEsperaNorte --;
            this.cruzando++;
            this.esperaNorte.release();
        }
        this.mutex.release();
}

public void salirNorte() throws InterruptedException{
    this.mutex.acquire();
    this.cruzando--;
    System.out.println("Saliendo desde el norte" + "auto " + Thread.currentThread().getName());
    if(this.cruzando == 0){
        if(this.cantEsperaSur > 0){
            this.direccion = 1;
            this.cantEsperaSur--;
            this.cruzando++;
            this.esperaSur.release();
            this.direccion = -1;
          
        }
    }
            this.fin.release();
            this.mutex.release();

    }

    public void entrarSur() throws  InterruptedException{
        this.semLLaveS.acquire();
        this.mutex.acquire();
        boolean primero = (cruzando == 0);
        if( (this.direccion == -1 || this.direccion == 1) && this.cantEsperaSur == 0){
            this.direccion = 1;
            this.cruzando++;
            this.mutex.release();
            
    }else{
        this.cantEsperaSur++;
        this.mutex.release();
        this.esperaSur.acquire();
        primero = true;
    }
    System.out.println("Cruzando desde el sur" + "auto " + Thread.currentThread().getName());
    
    this.fin.acquire();
    this.semLLaveS.release();
    if(primero){
        Thread.sleep(1000);
    }

  this.mutex.acquire();
     if(this.cantEsperaSur > 0){
            this.cantEsperaSur --;
            this.cruzando++;
            this.esperaSur.release();
        }
        this.mutex.release();
}

public void salirSur() throws InterruptedException{
    this.mutex.acquire();
    this.cruzando--;
    System.out.println("Saliendo desde el sur" + "auto " + Thread.currentThread().getName());
    if(this.cruzando == 0){
        if(this.cantEsperaNorte > 0){
            this.direccion = 0;
            this.cantEsperaNorte--;
            this.cruzando++;
            this.esperaNorte.release(); 
        }else if(this.cantEsperaSur==0){
            this.direccion = -1;
          
        }
    }
            this.fin.release();
            this.mutex.release();

    }

}

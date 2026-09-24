package tp4punto4;

import java.util.concurrent.Semaphore;

public class GestorImpresion {
    private Semaphore [] array;
    private Semaphore libresA;
    private Semaphore libresB;
    private Semaphore mutexLibreA;
    private Semaphore mutexLibreB;
    private int impresorasLibresA;
    private int impresorasLibresB;
    private int hilosEsperandoA;
    private int hilosEsperandoB;

    public GestorImpresion(int cant){
        this.array = new Semaphore[cant];
        this.hilosEsperandoA = 0;
        this.hilosEsperandoB = 0;
        this.impresorasLibresA = cant/2;
        this.impresorasLibresB = cant/2;
        this.mutexLibreA = new Semaphore(1);
        this.mutexLibreB = new Semaphore(1);
        this.libresA = new Semaphore(0);
        this.libresB = new Semaphore(0);
    }

    public void crearImpresoras(){
        for (int i = 0; i<array.length;i++){
            array[i] = new Semaphore(1);
        }
    }

    public boolean imprimir(String documento, int tipo){
        boolean impreso = false;
        int salto = 2;
        int eleccion = tipo;
        if(tipo == 0){
            adquirirA();
        }else if(tipo ==1){
            adquirirB();
        }else{
            if(hilosEsperandoA<=hilosEsperandoB){
                adquirirA();
                eleccion = 0;
            }else{
                adquirirB();
                eleccion = 1;
            }
        }
        int i = eleccion;
        
        while(i<array.length && !impreso){
            if(array[i].tryAcquire()){
                impreso = true;
                /*if(eleccion >= 0){
                    adquirirMutex(eleccion);
                }else{
                    adquirirMutex(tipo);
                }*/
                            
                System.out.println(documento);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                array[i].release();
                if(eleccion >= 0){
                    adquirirMutex(eleccion);
                }else{
                    adquirirMutex(tipo);
                }
                if(eleccion == 0 || tipo == 0){
                    this.impresorasLibresA ++;
                    if(hilosEsperandoA >0){
                        this.libresA.release();
                    }else{
                        this.mutexLibreA.release();
                    }
                }else if( eleccion == 1 || tipo == 1){
                    this.impresorasLibresB ++;
                    if(hilosEsperandoB>0){
                        this.libresB.release();
                    }else{
                        this.mutexLibreB.release();
                    }
                }
                

            }
            i += salto;
        }
        
        return impreso;
    }

    

    private void adquirirMutex(int tipo){
        if(tipo == 0){
            try {
                this.mutexLibreA.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
           try {
                this.mutexLibreB.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }
    }

    private void adquirirA(){
        adquirirMutex(0);
        if(this.impresorasLibresA ==0){
            
            this.hilosEsperandoA ++;
            this.mutexLibreA.release();
            try {
                System.out.println(Thread.currentThread().getName() + "esperando");
                this.libresA.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.hilosEsperandoA--;
        }
        this.impresorasLibresA --;
        this.mutexLibreA.release();
    }
    private void adquirirB(){
        adquirirMutex(1);
        if(this.impresorasLibresB ==0){
            
            this.hilosEsperandoB ++;
            this.mutexLibreB.release();
            try {
                System.out.println(Thread.currentThread().getName() + "esperando");
                this.libresB.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.hilosEsperandoB--;
        }
        this.impresorasLibresB --;
        this.mutexLibreB.release();
    }

}
package tp4punto4;

import java.util.concurrent.Semaphore;

public class GestorImpresion {
    private Semaphore [] array;
    private Semaphore libres;
    private Semaphore mutexLibre;
    private int impresorasLibres;
    private int hilosEsperando;
    public GestorImpresion(int cant){
        this.array = new Semaphore[cant];
        this.impresorasLibres = cant;
        this.mutexLibre = new Semaphore(1);
        this.impresorasLibres = 0;
    }

    public void crearImpresoras(){
        for (int i = 0; i<array.length;i++){
            array[i] = new Semaphore(1);
        }
    }

    public boolean imprimir(String documento){
        boolean impreso = false;
        int i =0;
        while(i<array.length && !impreso){
            if(array[i].tryAcquire()){
                impreso = true;
                try {
                    this.mutexLibre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.impresorasLibres --;
                this.mutexLibre.release();
                System.out.println(documento);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                array[i].release();
                try {
                    this.mutexLibre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(this.impresorasLibres == 0 && hilosEsperando>0){
                    libres.release();
                }
                this.mutexLibre.release();
            }
            i++;
        }
        if(!impreso){
            try {
                this.mutexLibre.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.hilosEsperando ++;
            this.mutexLibre.release();
            try {
                this.libres.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return impreso;
    }
}

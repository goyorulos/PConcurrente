package tp3punto5;

public class Hilo implements Runnable{
    int inicio;
    int fin;
    Arreglo arreglo;
    public Hilo(int unInicio, int unFinal, Arreglo unArreglo){
        this.inicio = unInicio;
        this.fin = unFinal;
        this.arreglo = unArreglo;
    }
    public void run(){
        System.out.println("corriendo" + Thread.currentThread());
        int suma1=0;
        for(int i = this.inicio;i<fin;i++){
            suma1 += this.arreglo.getElemPos(i);
        }
        arreglo.sumarResultado(suma1);
    }
}

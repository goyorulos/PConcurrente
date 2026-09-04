package tp3punto5;
import java.util.Random;
public class Arreglo {
    int [] arreglo;
    int largo;
    int resultado;
    public Arreglo(int[] unArreglo){
        this.arreglo = unArreglo;
        this.resultado = 0;
    }

    public synchronized void sumarResultado(int valor){
        resultado += valor;
    }
    public int getResultado() {
        return resultado;
    }
    public int getElemPos(int pos){
        return this.arreglo[pos];
    }
    public void llenar(){
        largo = this.arreglo.length;
        Random random = new Random();
        for(int i = 0; i<largo;i++){
            /*
            int min = 1;
            int max = 10;
            this.arreglo[i] = random.nextInt(max - min + 1) + min;
            */
            this.arreglo[i]=1;
        }
    }
}

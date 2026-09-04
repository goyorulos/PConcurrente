package tp3punto5;

public class Main {
    public static void main(String[] args) {
        int n = 50009,k = 5, inicio, fin;
        Thread []hilos = new Thread[k];
        Arreglo arreglo = new Arreglo(new int[n]);
        arreglo.llenar();
        int bloque = n/k;
        for (int i = 0; i<k;i++){
            inicio = i*bloque;
            fin = (i == k - 1) ? n : inicio + bloque;
            Hilo hilo=new Hilo(inicio, fin, arreglo);
            hilos[i] = new Thread(hilo);
        }
        for(int g =0; g<k;g++){
            hilos[g].start();
        }
        for(int s = 0; s<k;s++){
            try {
                hilos[s].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(arreglo.getResultado());
    }
        
}

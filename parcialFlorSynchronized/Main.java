package parcialFlorSynchronized;

public class Main {
    public static void main(String[] args) {
        Thread [] hilos = new Thread[10];
        Pizarra pizarron = new Pizarra();
        for(int i = 0; i< hilos.length; i++){
            Hilo hilo = new Hilo(pizarron, "soy gay papi");
            hilos[i] = new Thread(hilo, "goyo"+i);
        }
        for(int g =0; g<hilos.length;g++){
            hilos[g].start();
        }
    }
}

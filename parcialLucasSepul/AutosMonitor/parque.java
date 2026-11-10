import java.util.concurrent.Semaphore;
public class parque {
    private Semaphore escalera;
    private Semaphore tirarse;
    private Semaphore toboganes;
    private Semaphore visitante;
    private Boolean libre1;
    private Boolean libre2;
    private Semaphore mutex;
    private int toboganLibreParaMi;


    public parque(int cantEscalera){
        escalera = new Semaphore(cantEscalera);
        tirarse = new Semaphore(0);
        toboganes = new Semaphore(2);
        visitante = new Semaphore(0);
        mutex = new Semaphore(1);
        libre1 = true;
        libre2 = true;
        toboganLibreParaMi = 0;
    }

    public int subirTobogan()throws  InterruptedException{
        escalera.acquire();
        Thread.sleep(1000);
        visitante.release();
        tirarse.acquire();
        int tob = toboganLibreParaMi; // internamente el hilo asigna lo que devuelve esta funcion a una variable de clase de ese hilo para luego usarlo en salir
        mutex.release();
        escalera.release();
        return tob;// deja un espacio una ves se esta por tirar porque antes esta esperando tirarse y no deja la escalera.
    }
    // se tira
    public void salirTobogan(int tobogan){
        mutex.acquire();
        if(tobogan == 1){
            libre1 = true;
        }else{
            libre2 = true;
        }
        toboganes.release();
        mutex.release();

    }
    public void habilitarTirada()throws  InterruptedException{ //Esto va dentro de un while True en el hilo que decide quien se tira. 
        visitante.acquire();
        toboganes.acquire();// alguno de los dos se libero;
        mutex.acquire();
        if(libre1){
            System.out.println("Puede tirarse del tobogan 1");
            libre1 = false;
            toboganLibreParaMi = 1;
        }else if(libre2){
            System.out.println("Puede tirarse del tobogan 2");
            libre2 = false;
            toboganLibreParaMi = 2;
        }

        tirarse.release();
    }


}
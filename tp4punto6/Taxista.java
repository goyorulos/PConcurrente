package tp4punto6;

public class Taxista implements Runnable{
    private taxi taxi;
    public Taxista(taxi tatsi){
        this.taxi = tatsi;
    }
    public void run(){
        while (true){
            this.taxi.iniciarViaje();
        }
    }
}

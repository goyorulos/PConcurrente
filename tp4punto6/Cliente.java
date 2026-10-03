package tp4punto6;

public class Cliente implements Runnable{
    private taxi taxi;
    public Cliente(taxi tatsi){
        this.taxi = tatsi;
    }
    public void run(){
        while (true){
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.taxi.pedirViaje();
        }
    }
}

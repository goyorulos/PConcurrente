package tp4Punto3;

public class HiloP1 implements Runnable{
    private int id;
    private Barrera barrera;

    public HiloP1(int name, Barrera barr){
        this.id = name;
        this.barrera = barr;
    }

    public void run(){
        while(true){
            barrera.obtenerBarrera(id);
        }
    }

}

package tp3Punto7;

public class Playero implements Runnable{
    private Surtidor surti;
    private String nombre;
    public Playero(Surtidor unSurtidor, String name){
        this.surti = unSurtidor;
        this.nombre = name;
    }

    public void run(){
        while(true){
            if(surti.hayNafta()){
                
            }
        }
    }


}

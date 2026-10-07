package ParcialFlorLucasVersion;

public class Colectivero implements Runnable{
    private Colectivo cole;
    private int cantVueltas;
    public Colectivero(Colectivo colectivo, int mas){
        this.cole = colectivo;
        this.cantVueltas = mas;
    }

    public void run(){
        for(int i = 0; i< cantVueltas; i++){
            boolean Partir = false;
            Partir =  cole.habilitarEntrada();
            
          
            cole.iniciarViaje();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cole.habilitarSalida();
        }
    }
}

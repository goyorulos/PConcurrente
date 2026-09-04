package tp3Punto8;

public class Hilo implements Runnable{
    private String cadena;
    private int id;
    private Controlador control;

    public Hilo (String unaLetra, int unId, Controlador contr){
        this.cadena = unaLetra;
        this.id = unId;
        this.control = contr;
    }


    public void run(){
        while (true){
            if(!control.ponerLetra(id, cadena)){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

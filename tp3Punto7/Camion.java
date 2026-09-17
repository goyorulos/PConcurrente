package tp3Punto7;

public class Camion implements Runnable{
    private String nombre;
    private Surtidor playero;
    private Controlador controlador;
    public Camion (String name, Surtidor unPlayero, Controlador contr){
        this.nombre = name;
        this.playero = unPlayero;
        this.controlador = contr;
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controlador.llamarCamion();
            playero.cargarSurtidor();
            controlador.cambiarAuto();
        }
    }
}

package tp3Punto7;

public class Controlador {
    private int turno;
    public Controlador (){
        this.turno = 0;
    }

    public synchronized boolean consultarTurno(int id){
        return (this.turno == id);
    }

    public void llamarCamion(){
        this.turno = 1;
    }

    public void cambiarAuto(){
        this.turno = 0;
    }
}

package tp3Punto8;

public class Controlador {
    private int turno;
    public Controlador(){
        this.turno = 1;
    }

    public synchronized boolean ponerLetra(int id, String cadena){
        boolean exito = false;
        
        if(id == turno){
            System.out.print(cadena);
            exito = true;
            turno = (turno %3) +1;         
        }
        return exito;
    }
}

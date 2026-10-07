package tp3Punto3;


public class Actividad {
    private String nombre;
    private boolean libre;
    public Actividad(String unNombre){
        this.nombre = unNombre;
        this.libre = true;
    }

    public synchronized void liberar(){
        libre = true;
    }

    public synchronized boolean estaLibre(){
        boolean estaLibre = libre;
        if(this.libre){
            this.libre = false;
        }
        return estaLibre;
    }
    public void realizarActividad(){
        
        try {
            Thread.sleep(5000);
            System.out.println(nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}

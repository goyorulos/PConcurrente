package tp3Punto7;
import java.util.Random;
public class Auto implements Runnable {
    private String nombre;
    private int km_recorridos;
    private int combustible;
    private int nivelReseva;
    private Surtidor unSurtidor;
    public Auto(String unNombre, int km,int unCombustible, int reserva, Surtidor elSurtidor){
        this.nombre = unNombre;
        this.km_recorridos = km;
        this.combustible = unCombustible;
        this.nivelReseva = reserva;
        this.unSurtidor = elSurtidor;
    }

    public boolean darVuelta(){
        boolean exito = true;
        Random random = new Random();
        int min = 1;
        int max = 10;
        int numero = random.nextInt(max - min + 1) + min;
        this.combustible -=numero;
        if(this.combustible <= this.nivelReseva){
            this.km_recorridos += numero;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exito = false;
        }
        return exito;
    }

    public void run(){
        for(int i = 0; i<=10; i++){
            if(!darVuelta()){
                if(unSurtidor.cargarAuto()){
                    this.combustible += 10;
                    System.out.println(this.nombre + " Cargo");
                }
            }
        }
    }
}

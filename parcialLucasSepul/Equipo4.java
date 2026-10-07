package parcialLucasSepul;

public class Equipo4 implements Runnable{
    private Caja cajaRuedas;
    private Caja cajaPuertas;
    private Caja cajaCarroceria;
    private Caja cajaAutos;

    public Equipo4(Caja ruedas, Caja puertas,Caja carroceria, Caja autos){
        this.cajaRuedas = ruedas;
        this.cajaPuertas = puertas;
        this.cajaCarroceria = carroceria;
        this.cajaAutos = autos;
    }

    public void run(){
        try {
            while(true){
                do{
                    this.cajaRuedas.sacar(4);
                    this.cajaPuertas.sacar(2);
                    this.cajaCarroceria.sacar(1);
                }while(!this.cajaAutos.hacerAutos());
                System.out.println("Se han entregado 5 autos");
                this.cajaAutos.reiniciarCajaAutos();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

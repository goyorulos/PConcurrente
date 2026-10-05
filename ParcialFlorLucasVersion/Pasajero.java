package ParcialFlorLucasVersion;

public class Pasajero implements Runnable{
    private Colectivo cole;
    public Pasajero(Colectivo colectivo ){
        this.cole = colectivo;
    }

    public void run(){

        cole.entrar();
        cole.salirPasajeros();
    }

}

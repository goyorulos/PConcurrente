package ParcialFlorLucasVersion;

public class Pasajero implements Runnable{
    private Colectivo cole;
    public Pasajero(Colectivo colectivo ){
        this.cole = colectivo;
    }

    public void run(){
        try {
			cole.llegarParada();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cole.entrar();
        cole.salirPasajeros();
    }

}

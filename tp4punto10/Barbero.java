package tp4punto10;

public class Barbero implements Runnable{
    private Peluqueria pelu;
    public Barbero(Peluqueria laPelu){
        this.pelu = laPelu;
    }

    public void run(){
        try {
            while(true){
                this.pelu.esperarCliente();
                boolean hayEspera = true;
                while(hayEspera){
                    this.pelu.atenderSiguiente();
                    Thread.sleep(5000);
                    hayEspera = this.pelu.terminarCorte();
                }
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

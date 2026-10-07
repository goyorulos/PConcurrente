package tp4Punto10Barbero;

public class Peluquero implements Runnable{
    private Peluqueria pelu;
    public Peluquero(Peluqueria laPelu){
        this.pelu = laPelu;
    }
    public void run(){
        while(true){
            if(this.pelu.hayEspera()){
                
            }else{
                this.pelu.esperarCliente();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    this.pelu.cortarPelo();
            }
        }
    }
}

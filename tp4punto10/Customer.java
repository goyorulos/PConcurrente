package tp4punto10;

public class Customer implements Runnable{
    private Peluqueria pelu;
    public Customer(Peluqueria laPelu){
        this.pelu = laPelu;
    }

    public void run(){
        try {
            if(this.pelu.intentoSentarmeEspera()){
                this.pelu.esperarCortarse();
                System.out.println(Thread.currentThread().getName() + " Se levanto y se fue");
            }else{
                System.out.println("la pelu estaba llena "+ Thread.currentThread().getName() + "seFU");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

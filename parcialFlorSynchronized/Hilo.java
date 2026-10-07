package parcialFlorSynchronized;

public class Hilo implements Runnable{
    private Pizarra pizarron;
    private String texto;
    public Hilo (Pizarra unaPizarra, String teto){
        this.pizarron = unaPizarra;
        this.texto = teto;
    }
    public void run(){
        boolean avisado=false;
        while(!pizarron.preguntarYusar()){
            if(!avisado){
                System.out.println(Thread.currentThread().getName() + "esta esperando");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " escribio: "+ texto);
        pizarron.liberar();
    }
}

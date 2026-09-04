public class Cliente extends Thread{
    private Recurso miRecurso;
    public Cliente (Recurso miRecurso){
        this.miRecurso = miRecurso;
    }
    public void run(){
        System.out.println("Soy "+ Thread.currentThread().getName());
        this.miRecurso.uso();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println("error");
        }
    }
}
package parcialFlorSynchronized;

public class Pizarra {
    private boolean ocupado;
    private String contenido;

    public Pizarra(){
        this.ocupado = false;
        this.contenido = "";
    }

    public synchronized boolean preguntarYusar(){
        boolean exito = false;
        if(!this.ocupado){
            this.ocupado = true;
            exito = true;
        }
        return exito;
    }

    public synchronized void liberar(){
        this.ocupado = false;
        this.contenido = "";
    }

    public void escribirPizarra(String texto){
        this.contenido = texto;
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " escribió: " + contenido);
    }

}

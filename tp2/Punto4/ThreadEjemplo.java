package Punto4;

public class ThreadEjemplo implements Runnable{
    private String name;
    public ThreadEjemplo (String nombre){
        this.name = nombre;
    }
    public void run(){
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + this.name);
        System.out.println("Termina thread " + this.name);
    }
}

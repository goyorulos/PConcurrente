package Punto5;

public class HiloContador extends Thread {
    public HiloContador(String str){
        super(str);
    }
    
    public void run() {
        System.out.println("Comenzando " + getName());
        try {
            for (int contar = 0; contar < 10; contar++) {
                Thread.sleep(400);
                System.out.println("En " + getName()
                        + ", el recuento " + contar);
            }
        } catch (InterruptedException exc) {
            System.out.println(getName() + " Interrumpido");
        }
        System.out.println("Terminando " + getName());
    }
}



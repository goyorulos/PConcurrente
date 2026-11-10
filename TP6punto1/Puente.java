package TP6punto1;

import java.util.Queue;

public class Puente {
    private Queue filaN;
    private Queue filaS;
    private Queue salirN;
    private Queue salirS;
    private int turno = -1;
    private int cruzando = 0;
    private int cantMax = 10;
    
    public synchronized void entrarNorte(int id) throws InterruptedException{
        filaN.add(id);
        while(turno == 1 || !filaN.element().equals(id)|| (cruzando>=cantMax && !filaS.isEmpty())){
            this.wait();
        }
        turno =0;
        filaN.remove();
        salirN.add(id);
        cruzando++;
        notifyAll();
        System.out.println("cruzando puente");
    }

    public synchronized void salirNorte(int id) throws InterruptedException{
        while(!salirN.element().equals(id)){
            this.wait();
        }
        salirN.remove();
        if(salirN.isEmpty() && !filaS.isEmpty()){
            turno = 1;
            cruzando = 0;
        }else if(salirN.isEmpty()){
            turno = -1;
            cruzando=0;
        }
        notifyAll();
    }

}

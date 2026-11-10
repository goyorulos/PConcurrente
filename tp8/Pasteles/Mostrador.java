package tp8.Pasteles;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

public class Mostrador {
    private Lock mostrador = new ReentrantLock();
    private Condition esperaRobot = mostrador.newCondition();
    private ArrayList <Integer> pasteles = new ArrayList<>();


    public int tomarPastel() throws InterruptedException{
        mostrador.lock();
        while(pasteles.isEmpty()){
            esperaRobot.await();
        }
        int peso = pasteles.get(0);
        pasteles.remove(0);
        mostrador.unlock();
        return peso;
    }


    public void ponerPastel(int n){
        mostrador.lock();
        pasteles.add(n);
        esperaRobot.signal();
        mostrador.unlock();
    }
}

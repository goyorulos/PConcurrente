package tp8.Pasteles;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja {
    Lock caja = new ReentrantLock();
    Condition esperaCaja = caja.newCondition();
    Condition esperaBrazo = caja.newCondition();
    int max;
    int pesoActual= 0;
    boolean lleno = false;

    public Caja(int n){
        max = n;
    }

    public void soltarPastel(int peso) throws InterruptedException{
        caja.lock();
        while(pesoActual+peso>max){
            lleno = true;
            esperaBrazo.signal();
            esperaCaja.await();
        }
        pesoActual += peso;
        caja.unlock();
    }

    public void retirarCaja() throws InterruptedException{
        caja.lock();
        while(!lleno){
            esperaBrazo.await();
        }
        pesoActual = 0;
        esperaCaja.signalAll();
        caja.unlock();
    }
}

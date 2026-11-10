package tp8.Pasteles.Canciones;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
    private Lock rock = new ReentrantLock();
    private Lock folk = new ReentrantLock();
    private Lock blues = new ReentrantLock();
    private Lock country = new ReentrantLock();
    private Lock album = new ReentrantLock();
    Condition esperaRock = rock.newCondition();
    Condition esperaFolk = folk.newCondition();
    Condition esperaBlues = blues.newCondition();
    Condition esperaCountry = country.newCondition();
    Condition albumEspera = album.newCondition();
    Condition managerEspera = album.newCondition();
    boolean lleno = false;
    int max;
    int cantActual;
    int cantRock = 0;
    int cantFolk = 0;
    int cantBlues = 0;
    int cantCountry = 0;  
    int duracionRock = 2;
    int duracionFolk = 3;
    int duracionBlues = 1;
    int duracionCountry = 230; 
    public void generarCancion(int tipo){
        if(tipo == 0){
            rock.lock();

        }else if(tipo == 1){
            folk.lock();

        }else if(tipo ==2){
            blues.lock();

        } else{
            country.lock();
            
        }
    }
}

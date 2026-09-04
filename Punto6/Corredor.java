package Punto6;

import java.util.Random;

public class Corredor extends Thread {
    private String nombre;
    private int distancia;
    public Corredor (String unNombre){
        this.nombre = unNombre;
        this.distancia = 0;
    }
    public void run(){
        int numeroAleatorio;
        Random randomNumero = new Random();
        do{
            numeroAleatorio = randomNumero.nextInt(1,10);
            System.out.println("soy "+ this.nombre + " hice "+ numeroAleatorio + "Pasos");
            distancia += numeroAleatorio;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(distancia<100);
        System.out.println("soy"+ this.nombre + " hice "+ distancia);

    }
}

package Punto6;

public class Main {
    public static void main(String []args){
        Corredor [] competidores = new Corredor[10];
        System.out.println("empezo el hilo main");
        for(int i = 0; i<competidores.length;i++){
            competidores[i] = new Corredor("#"+i);
            competidores[i].start();
        }
        for(int i = 0; i<competidores.length();i++){}
    }
}

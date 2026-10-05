package Tp4Punto8Mozo;

public class Main {
    public static void main(String[] args) {
        Thread [] empleados = new Thread[3];
        Comedor confiteria = new Comedor();
        Mozo julio = new Mozo(confiteria);
        Thread mozo = new Thread(julio, "julio");
        for(int i = 0; i<empleados.length; i++){
            Empleado empleado = new Empleado(confiteria);
            empleados[i] = new Thread(empleado, "goyo"+i);
        }
        mozo.start();
        for(int j = 0; j<empleados.length; j++){
            empleados[j].start();
        }
    }
}

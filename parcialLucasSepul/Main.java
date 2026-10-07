package parcialLucasSepul;

public class Main {
    public static void main(String[] args) {
        // 1. Se instancian las cajas con sus capacidades máximas
        Caja cajaRuedas = new Caja(10);
        Caja cajaPuertas = new Caja(6);
        Caja cajaCarrocerias = new Caja(3);
        // 2. Se instancia el contador de autos
        Caja contadorAutos = new Caja(0);

        // 3. Se crean los objetos Runnable de producción (Equipos 1, 2 y 3)
        HiloProductos equipo1 = new HiloProductos(cajaRuedas, "Rueda");
        HiloProductos equipo2 = new HiloProductos(cajaPuertas, "Puerta");
        HiloProductos equipo3 = new HiloProductos(cajaCarrocerias, "Carrocería");

        // 4. Se crea el objeto Runnable de ensamblaje (Equipo 4)
        Equipo4 equipo4 = new Equipo4(cajaRuedas, cajaPuertas, cajaCarrocerias, contadorAutos);

        // 5. Se crean e inician los hilos asignándoles un nombre
        Thread t1 = new Thread(equipo1, "Equipo 1 (Ruedas)");
        Thread t2 = new Thread(equipo2, "Equipo 2 (Puertas)");
        Thread t3 = new Thread(equipo3, "Equipo 3 (Carrocerías)");
        Thread t4 = new Thread(equipo4, "Equipo 4 (Ensamblador)");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
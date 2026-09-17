package tp3Punto7;

public class Surtidor {
    private int combustibleMax;
    private int combustibleGastado;
    private Controlador controlador;

    public Surtidor(int uncombustible, Controlador contr) {
        this.combustibleMax = uncombustible;
        this.combustibleGastado = 0;
        this.controlador = contr;
    }

    public synchronized boolean cargarAuto() {
        boolean exito = false;
        if (combustibleGastado + 10 <= combustibleMax) {
            exito = true;
            this.combustibleGastado += 10;
            System.out.println(combustibleGastado);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no hay nafta");
        }
        return exito;
    }

    public synchronized void cargarSurtidor(){
        this.combustibleGastado = 0;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean hayNafta(){
        return (combustibleGastado >= combustibleMax);
    }

    public boolean hayCamion(){
        boolean exito = false;
        if(controlador.consultarTurno(1)){
            exito = true;
        }
        return exito;
    }

}

package tp3Punto7;

public class Surtidor {
    private int combustibleMax;
    private int combustibleGastado;

    public Surtidor(int uncombustible) {
        this.combustibleMax = uncombustible;
        this.combustibleGastado = 0;
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

}

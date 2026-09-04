package tp3Punto1;

public class Main {
    public static void main(String[] args) {
        CuentaBanco cb = new CuentaBanco(50);
        VerificarCuenta vcL = new VerificarCuenta(cb);
        VerificarCuenta vcJ = new VerificarCuenta(cb);
        Thread lucas = new Thread(vcL, "Lucas");
        Thread jere = new Thread(vcJ, "Jere");
        lucas.start();
        jere.start();
    }
}

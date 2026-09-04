package tp3Punto1;

public class CuentaBanco {
    private int balance;

    public CuentaBanco(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void retiroBancario(int retiro) {
        this.balance -= retiro;
    }
}
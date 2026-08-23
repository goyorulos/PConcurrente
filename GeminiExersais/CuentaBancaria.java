// 1. Creamos el objeto que ambos hilos van a compartir
class CuentaBancaria {
    int saldo = 0;

    public synchronized void depositar() {
        // Aumentamos el saldo en 1
        saldo = saldo + 1;
    }
}

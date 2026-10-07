package parcialLucasSepul;

public class HiloProductos implements Runnable{
    private Caja caja;
    private String producto;
    public HiloProductos(Caja unaCaja, String unProducto){
        this.caja = unaCaja;
        this.producto = unProducto;
    }

    public void run(){
        try{
            while(true){
                Thread.sleep(500);
                this.caja.depositar();
                System.out.println(Thread.currentThread().getName() + " produjo 1 unidad de: "+ this.producto);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

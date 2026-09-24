package tp4punto4;

public class Cliente implements Runnable{
    private String documento;
    private GestorImpresion gestor;
    private int tipo;

    public Cliente(String docum, GestorImpresion gest, int t){
        this.documento = docum;
        this.gestor = gest;
        this.tipo = t;
    }

    public void run(){
        gestor.imprimir(documento, tipo);
    }
}
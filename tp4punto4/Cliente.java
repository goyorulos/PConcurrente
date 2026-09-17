package tp4punto4;

public class Cliente implements Runnable{
    private String documento;
    private GestorImpresion gestor;

    public Cliente(String docum, GestorImpresion gest){
        this.documento = docum;
        this.gestor = gest;
    }

    public void run(){
        gestor.imprimir(documento);
    }
}

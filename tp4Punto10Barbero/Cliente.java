package tp4Punto10Barbero;

public class Cliente implements Runnable{
    private Peluqueria pelu;

    public Cliente(Peluqueria laPelu){
        this.pelu = laPelu;
    }

    public void run(){
        if()
        this.pelu.entrarPeluqueria();
        this.pelu.levantarse();
    }

}

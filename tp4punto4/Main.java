package tp4punto4;

public class Main {
        public static void main(String[] args) {
            GestorImpresion gestor = new GestorImpresion(5);
            Cliente c1 = new Cliente("cliente1", gestor);
            Cliente c2 = new Cliente("cliente2", gestor);
            Cliente c3 = new Cliente("cliente3", gestor);
            Cliente c4 = new Cliente("cliente4", gestor);
            Cliente c5 = new Cliente("cliente5", gestor);
            Cliente c6 = new Cliente("cliente6", gestor);
            Cliente c7 = new Cliente("cliente7", gestor);

            Thread h1 = new Thread(c1);
            Thread h2 = new Thread(c2);
            Thread h3 = new Thread(c3);
            Thread h4 = new Thread(c4);
            Thread h5 = new Thread(c5);
            Thread h6 = new Thread(c6);
            Thread h7 = new Thread(c7);

            gestor.crearImpresoras();

            h1.start();
            h2.start();
            h3.start();
            h4.start();
            h5.start();
            h6.start();
            h7.start();

        }
}

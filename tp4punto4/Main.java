package tp4punto4;

public class Main {
        public static void main(String[] args) {
            GestorImpresion gestor = new GestorImpresion(4);
            Cliente c1 = new Cliente("cliente1A", gestor, 0);
            Cliente c2 = new Cliente("cliente2A", gestor, 0);
            Cliente c3 = new Cliente("cliente3A", gestor,0);
            Cliente c4 = new Cliente("cliente4B", gestor, 1);
            Cliente c5 = new Cliente("cliente5B", gestor,1);
            Cliente c6 = new Cliente("cliente6B", gestor,1);
            Cliente c7 = new Cliente("cliente7B", gestor,1);
            Cliente c8 = new Cliente("cliente8X", gestor, 2);
            Cliente c9 = new Cliente("cliente9X", gestor,2);
            Cliente c10 = new Cliente("cliente10X", gestor,2);


            Thread h1 = new Thread(c1, "Clie1A");
            Thread h2 = new Thread(c2, "Clie2A");
            Thread h3 = new Thread(c3, "Clie3A");
            Thread h4 = new Thread(c4, "Clie4B");
            Thread h5 = new Thread(c5, "Clie5B");
            Thread h6 = new Thread(c6, "Clie6B");
            Thread h7 = new Thread(c7, "Clie7B");
            Thread h8 = new Thread(c8, "Clie8X");
            Thread h9 = new Thread(c9, "Clie9X");
            Thread h10 = new Thread(c10, "Clie10X");

            gestor.crearImpresoras();

            h1.start();
            h2.start();
            h3.start();
            h4.start();
            h5.start();
            h6.start();
            h7.start();
            h8.start();
            h9.start();
            h10.start();

        }
}
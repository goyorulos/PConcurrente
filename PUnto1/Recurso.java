public class Recurso {
 public Recurso() {}
   public void uso() {
       Thread t = Thread.currentThread();
       System.out.println("en Recurso: Soy " + t.getName());
   }
 }
public class App {
    public static void main(String[] args) throws Exception {
        Pila pila = new Pila();

        pila.push(1);
        pila.push(2);
        pila.push(3);
        pila.push(4);
        pila.push(5);
        pila.push(6);
        pila.push(7);

        pila.imprimir();
        pila.dibujarGraphviz("normal");
        pila.invertir();
        pila.imprimir();
        pila.dibujarGraphviz("invertido");



    }
}

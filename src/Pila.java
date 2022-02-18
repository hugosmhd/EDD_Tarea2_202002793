import java.io.FileWriter;
import java.io.PrintWriter;

public class Pila {
    Nodo cima;
    int tamanio;

    public Pila() {
        this.cima = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return this.cima==null;
    }

    public void push(Object data) {
        Nodo nuevo = new Nodo(data);
        if (estaVacia()) {
            this.cima = nuevo;
        } else {
            nuevo.siguiente = this.cima;
            this.cima = nuevo;
        }
        this.tamanio += 1;       
    }

    public void imprimir() {
        Nodo actual = this.cima;
        while (actual != null) {
            System.out.print(actual.info + ", ");     
            actual = actual.siguiente;
        }
        System.out.println();
    }



    public void invertir() {
        Nodo actual = this.cima;
        int aux = 0;
        while (actual != null && aux < ((tamanio-1)/2)) {
            int contador = 0;
            Nodo nodoAux = this.cima;
            while (nodoAux != null && contador < tamanio - aux) {
                contador += 1;
                if(contador < tamanio - aux){
                    nodoAux = nodoAux.siguiente;
                } 
            }
            Object dataActual = actual.getInfo();
            actual.setInfo(nodoAux.getInfo());
            nodoAux.setInfo(dataActual);
            aux += 1;            
            actual = actual.siguiente;
        }
    }

    public String codigoGraphviz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G { \n");
        dot.append("node[shape=box, color=red];\n");
        
        String nombresNodos = "";
        String conexiones = "";
        Nodo actual= this.cima;
        while( actual!= null){
            nombresNodos += "nodo" + actual.hashCode() + "[label=\"" +  actual.toString() + "\"]" + "\n";
            if (actual.siguiente != null)            
                conexiones += String.format("nodo%d -> nodo%d;\n", actual.hashCode(), actual.siguiente.hashCode());
            actual=actual.siguiente;
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("rankdir=LR;\n");
        dot.append("} \n");    
        
        return dot.toString();
    }

    private void escribirArchivo(String ruta, String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(pw != null) {
                pw.close();
            }            
        }
    }
    
    public void dibujarGraphviz(String nombre) {
        try {
            escribirArchivo(nombre + ".dot", codigoGraphviz());
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot","-Tsvg","-o", nombre + ".svg", nombre + ".dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

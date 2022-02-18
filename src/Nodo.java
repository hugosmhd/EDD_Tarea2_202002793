public class Nodo {
    Object info;
    Nodo siguiente;

    public Nodo(Object info){
        this.info=info;
        this.siguiente=null;
    }    
    
    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    public String toString(){
        return String.valueOf(this.info);
    }    
}

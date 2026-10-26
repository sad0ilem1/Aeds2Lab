public class Celula<T> {
    private final T item;
    private Celula<T> proximo;

    public Celula() { this(null); }          // usada para a sentinela
    public Celula(T item) {
        this.item = item;
        this.proximo = null;
    }
    public T getItem() { return item; }
    public Celula<T> getProximo() { return proximo; }
    public void setProximo(Celula<T> proximo) { this.proximo = proximo; }
}

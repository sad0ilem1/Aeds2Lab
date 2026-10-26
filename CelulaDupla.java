public class CelulaDupla<T> {
    private final T item;
    private CelulaDupla<T> anterior;
    private CelulaDupla<T> proximo;

    public CelulaDupla() { this(null); }
    public CelulaDupla(T item) {
        this.item = item;
        this.anterior = null;
        this.proximo = null;
    }
    public T getItem() { return item; }
    public CelulaDupla<T> getAnterior() { return anterior; }
    public void setAnterior(CelulaDupla<T> anterior) { this.anterior = anterior; }
    public CelulaDupla<T> getProximo() { return proximo; }
    public void setProximo(CelulaDupla<T> proximo) { this.proximo = proximo; }
}

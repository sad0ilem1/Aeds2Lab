public class ListaDupla<E> {
    private CelulaDupla<E> primeiro;
    private CelulaDupla<E> ultimo;
    private int tamanho;

    public ListaDupla() {
        CelulaDupla<E> sentinela = new CelulaDupla<>();
        primeiro = ultimo = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public void inserirFinal(E item) {
        CelulaDupla<E> nova = new CelulaDupla<>(item);
        nova.setAnterior(ultimo);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    // O(1): não precisa percorrer, graças ao "anterior"
    public E removerFinal() {
        if (vazia()) throw new IllegalStateException("Lista vazia!");
        CelulaDupla<E> removida = ultimo;
        ultimo = ultimo.getAnterior();
        ultimo.setProximo(null);
        removida.setAnterior(null);
        tamanho--;
        return removida.getItem();
    }

    public void inserir(E item, int posicao) {
        if (posicao < 0 || posicao > tamanho)
            throw new IndexOutOfBoundsException("Posição inválida!");
        CelulaDupla<E> anterior = primeiro;
        for (int i = 0; i < posicao; i++)
            anterior = anterior.getProximo();
        CelulaDupla<E> nova = new CelulaDupla<>(item);
        CelulaDupla<E> proxima = anterior.getProximo();
        nova.setAnterior(anterior);       // 4 ligações
        nova.setProximo(proxima);
        anterior.setProximo(nova);
        if (proxima != null)
            proxima.setAnterior(nova);
        else
            ultimo = nova;                // inseriu no fim
        tamanho++;
    }

    public E remover(int posicao) {
        if (vazia()) throw new IllegalStateException("Lista vazia!");
        if (posicao < 0 || posicao >= tamanho)
            throw new IndexOutOfBoundsException("Posição inválida!");
        CelulaDupla<E> removida = primeiro.getProximo();
        for (int i = 0; i < posicao; i++)
            removida = removida.getProximo();
        CelulaDupla<E> anterior = removida.getAnterior();
        CelulaDupla<E> proxima = removida.getProximo();
        anterior.setProximo(proxima);
        if (proxima != null)
            proxima.setAnterior(anterior);
        else
            ultimo = anterior;            // removeu o último
        removida.setProximo(null);
        removida.setAnterior(null);
        tamanho--;
        return removida.getItem();
    }

    public void imprimir() {
        CelulaDupla<E> aux = primeiro.getProximo();
        while (aux != null) {
            System.out.print(aux.getItem() + " ");
            aux = aux.getProximo();
        }
        System.out.println();
    }

    // de trás para frente, usando "anterior"
    public void imprimirInverso() {
        CelulaDupla<E> aux = ultimo;
        while (aux != primeiro) {
            System.out.print(aux.getItem() + " ");
            aux = aux.getAnterior();
        }
        System.out.println();
    }

    public boolean contem(E item) {
        CelulaDupla<E> aux = primeiro.getProximo();
        while (aux != null) {
            if (aux.getItem().equals(item)) return true;
            aux = aux.getProximo();
        }
        return false;
    }
}

public class Lista<E extends Comparable<E>> {
    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        primeiro = ultimo = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public void inserir(E novo, int posicao) {
        if (posicao < 0 || posicao > tamanho)
            throw new IndexOutOfBoundsException("Posição inválida!");
        Celula<E> anterior = primeiro;
        for (int i = 0; i < posicao; i++)        // para na célula ANTES da posição
            anterior = anterior.getProximo();
        Celula<E> novaCelula = new Celula<>(novo);
        Celula<E> proximaCelula = anterior.getProximo();
        anterior.setProximo(novaCelula);
        novaCelula.setProximo(proximaCelula);
        if (posicao == tamanho)                  // inseriu no fim
            ultimo = novaCelula;
        tamanho++;
    }

    public void inserirInicio(E novo) { inserir(novo, 0); }
    public void inserirFinal(E novo)  { inserir(novo, tamanho); }

    public E remover(int posicao) {
        if (vazia()) throw new IllegalStateException("Lista vazia!");
        if (posicao < 0 || posicao >= tamanho)
            throw new IndexOutOfBoundsException("Posição inválida!");
        Celula<E> anterior = primeiro;
        for (int i = 0; i < posicao; i++)
            anterior = anterior.getProximo();
        Celula<E> celulaRemovida = anterior.getProximo();
        Celula<E> proximaCelula = celulaRemovida.getProximo();
        anterior.setProximo(proximaCelula);
        celulaRemovida.setProximo(null);
        if (celulaRemovida == ultimo)            // removeu o último
            ultimo = anterior;
        tamanho--;
        return celulaRemovida.getItem();
    }

    public void imprimir() {
        Celula<E> aux = primeiro.getProximo();
        while (aux != null) {
            System.out.print(aux.getItem() + " ");
            aux = aux.getProximo();
        }
        System.out.println();
    }

    // retorna a posição do item, ou -1 se não achar
    public int localizar(E item) {
        Celula<E> aux = primeiro.getProximo();
        int pos = 0;
        while (aux != null) {
            if (aux.getItem().equals(item)) return pos;
            aux = aux.getProximo();
            pos++;
        }
        return -1;
    }

    // recursivo (exercício 4)
    public boolean contem(E item) {
        return contem(item, primeiro.getProximo());
    }
    public boolean contem(E item, Celula<E> aux) {
        if (aux == null) return false;
        if (aux.getItem().equals(item)) return true;
        return contem(item, aux.getProximo());
    }

    // recursivo (exercício 3)
    public int contarQtos(E item) {
        return contarQtos(item, primeiro.getProximo());
    }
    public int contarQtos(E item, Celula<E> aux) {
        if (aux == null) return 0;
        int achou = aux.getItem().equals(item) ? 1 : 0;
        return achou + contarQtos(item, aux.getProximo());
    }

    // exercício 11: remove todas as ocorrências
    public boolean removerTodos(E item) {
        Celula<E> anterior = primeiro;
        if (this.vazia()) throw new IllegalStateException("Lista vazia!");
        boolean removeu = false;
        for ( ; anterior.getProximo() != null; ) {
            Celula<E> atual = anterior.getProximo();
            if (atual.getItem().equals(item)) {
                anterior.setProximo(atual.getProximo()); // pula a célula
                atual.setProximo(null);
                if (atual == ultimo) ultimo = anterior;
                tamanho--;
                removeu = true;                           // NÃO avança anterior
            } else {
                anterior = anterior.getProximo();
            }
        }
        return removeu;
    }

    // exercício 10: insere já na posição certa
    public void inserirEmOrdem(E item) {
        Celula<E> anterior = primeiro;
        while (anterior.getProximo() != null
               && anterior.getProximo().getItem().compareTo(item) < 0) {
            anterior = anterior.getProximo();
        }
        Celula<E> nova = new Celula<>(item);
        nova.setProximo(anterior.getProximo());
        anterior.setProximo(nova);
        if (nova.getProximo() == null) ultimo = nova;
        tamanho++;
    }

    // ordena a lista inteira religando as células
    public void ordenar() {
        Celula<E> resto = primeiro.getProximo();
        primeiro.setProximo(null);
        ultimo = primeiro;
        while (resto != null) {
            Celula<E> atual = resto;
            resto = resto.getProximo();
            Celula<E> anterior = primeiro;
            while (anterior.getProximo() != null
                   && anterior.getProximo().getItem().compareTo(atual.getItem()) <= 0) {
                anterior = anterior.getProximo();
            }
            atual.setProximo(anterior.getProximo());
            anterior.setProximo(atual);
            if (atual.getProximo() == null) ultimo = atual;
        }
    }

    // exercício 7: inverte religando as células
    public void inverter() {
        Celula<E> anterior = null;
        Celula<E> atual = primeiro.getProximo();
        ultimo = (atual == null) ? primeiro : atual;  // o 1º vira o último
        while (atual != null) {
            Celula<E> proxima = atual.getProximo();   // guarda o resto
            atual.setProximo(anterior);               // vira a seta
            anterior = atual;
            atual = proxima;
        }
        primeiro.setProximo(anterior);
    }

    // exercício 1: lista ordenada a partir de duas filas ordenadas
    public Lista(Fila<E> fila1, Fila<E> fila2) {
        this();
        while (!fila1.vazia() && !fila2.vazia()) {
            if (fila1.consultarPrimeiro().compareTo(fila2.consultarPrimeiro()) <= 0)
                inserirFinal(fila1.desenfileirar());
            else
                inserirFinal(fila2.desenfileirar());
        }
        while (!fila1.vazia()) inserirFinal(fila1.desenfileirar());
        while (!fila2.vazia()) inserirFinal(fila2.desenfileirar());
    }
}

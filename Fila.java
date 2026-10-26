public class Fila<E extends Comparable<E>> {
    private Celula<E> frente;
    private Celula<E> tras;

    public Fila() {
        Celula<E> sentinela = new Celula<>();
        frente = tras = sentinela;
    }

    public boolean vazia() {
        return frente == tras;
    }

    public void enfileirar(E item) {
        Celula<E> nova = new Celula<>(item);
        tras.setProximo(nova);
        tras = nova;
    }

    public E desenfileirar() {
        if (vazia()) throw new IllegalStateException("Fila vazia!");
        Celula<E> primeira = frente.getProximo();
        frente.setProximo(primeira.getProximo());
        if (primeira == tras)      // era o único item
            tras = frente;
        primeira.setProximo(null);
        return primeira.getItem();
    }

    public E consultarPrimeiro() {
        if (vazia()) throw new IllegalStateException("Fila vazia!");
        return frente.getProximo().getItem();
    }

    // código do quadro
    public void inserirNoInicio(E item) {
        Celula<E> nova = new Celula<>(item);
        Celula<E> aux = frente.getProximo();
        frente.setProximo(nova);
        nova.setProximo(aux);
        if (frente == tras)
            tras = nova;
    }

    // código do quadro: remove o último usando uma pilha
    public E removerFim() {
        Pilha<E> pilha = new Pilha<>();
        while (!vazia())
            pilha.empilhar(desenfileirar());
        E temp = pilha.desempilhar();       // o último da fila
        while (!pilha.vazia())
            inserirNoInicio(pilha.desempilhar());
        return temp;
    }

    public void imprimir() {
        Celula<E> aux = frente.getProximo();
        while (aux != null) {
            System.out.print(aux.getItem() + " ");
            aux = aux.getProximo();
        }
        System.out.println();
    }

    // recursivo (assinatura da lista de exercícios)
    public int obterNumeroItens() {
        return obterNumeroItens(frente.getProximo());
    }
    public int obterNumeroItens(Celula<E> aux) {
        if (aux == null) return 0;
        return 1 + obterNumeroItens(aux.getProximo());
    }

    public boolean contem(E item) {
        return contem(item, frente.getProximo());
    }
    public boolean contem(E item, Celula<E> aux) {
        if (aux == null) return false;
        if (aux.getItem().equals(item)) return true;
        return contem(item, aux.getProximo());
    }

    // quantos itens estão à frente de item (exercício 10)
    public int quantosAfrente(E item) {
        return quantosAfrente(frente.getProximo(), item);
    }
    public int quantosAfrente(Celula<E> atual, E item) {
        if (atual == null) throw new IllegalArgumentException("Item não está na fila!");
        if (atual.getItem().equals(item)) return 0;
        return 1 + quantosAfrente(atual.getProximo(), item);
    }

    // ordena religando as células (inserção ordenada)
    public void ordenar() {
        Celula<E> resto = frente.getProximo(); // corrente desordenada
        frente.setProximo(null);               // fila fica vazia
        tras = frente;
        while (resto != null) {
            Celula<E> atual = resto;
            resto = resto.getProximo();
            Celula<E> anterior = frente;       // acha onde encaixar
            while (anterior.getProximo() != null
                   && anterior.getProximo().getItem().compareTo(atual.getItem()) <= 0) {
                anterior = anterior.getProximo();
            }
            atual.setProximo(anterior.getProximo());
            anterior.setProximo(atual);
            if (atual.getProximo() == null) tras = atual;
        }
    }

    // inverte usando uma pilha
    public void inverter() {
        Pilha<E> pilha = new Pilha<>();
        while (!vazia()) pilha.empilhar(desenfileirar());
        while (!pilha.vazia()) enfileirar(pilha.desempilhar());
    }
}

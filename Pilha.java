public class Pilha<E extends Comparable<E>> {
    private Celula<E> topo;
    private Celula<E> fundo;

    public Pilha() {
        Celula<E> sentinela = new Celula<>();
        fundo = topo = sentinela;
    }

    public boolean vazia() {
        return fundo == topo;
    }

    public void empilhar(E item) {
        Celula<E> nova = new Celula<>(item);
        nova.setProximo(topo);   // 1º: nova aponta para o topo atual
        topo = nova;             // 2º: nova vira o topo
    }

    public E desempilhar() {
        if (vazia()) throw new IllegalStateException("Pilha vazia!");
        Celula<E> removida = topo;
        topo = topo.getProximo();
        removida.setProximo(null);
        return removida.getItem();
    }

    public E consultarTopo() {
        if (vazia()) throw new IllegalStateException("Pilha vazia!");
        return topo.getItem();
    }

    // imprime na ordem em que seriam desempilhados (topo -> fundo)
    public void imprimir() {
        Celula<E> aux = topo;
        while (aux != fundo) {
            System.out.print(aux.getItem() + " ");
            aux = aux.getProximo();
        }
        System.out.println();
    }

    public int obterNumeroItens() {
        int cont = 0;
        Celula<E> aux = topo;
        while (aux != fundo) {
            cont++;
            aux = aux.getProximo();
        }
        return cont;
    }

    // procurar (iterativo)
    public boolean contem(E item) {
        Celula<E> aux = topo;
        while (aux != fundo) {
            if (aux.getItem().equals(item)) return true;
            aux = aux.getProximo();
        }
        return false;
    }

    // procurar (recursivo) + auxiliar
    public boolean contemRecursivo(E item) {
        return contem(item, topo);
    }
    public boolean contem(E item, Celula<E> aux) {
        if (aux == fundo) return false;                  // caso base: acabou
        if (aux.getItem().equals(item)) return true;     // achou
        return contem(item, aux.getProximo());           // procura no resto
    }

    // imprime na ordem de inserção (fundo -> topo), recursivo
    public void imprimirOrdemInsercao() {
        imprimirOrdemInsercao(topo);
        System.out.println();
    }
    private void imprimirOrdemInsercao(Celula<E> aux) {
        if (aux != fundo) {
            imprimirOrdemInsercao(aux.getProximo()); // desce primeiro
            System.out.print(aux.getItem() + " ");   // imprime na volta
        }
    }

    // ordena: menor no topo. Usa só empilhar/desempilhar + pilha auxiliar
    public void ordenar() {
        Pilha<E> aux = new Pilha<>();
        while (!this.vazia()) {
            E atual = this.desempilhar();
            // devolve para this tudo que for maior que atual
            while (!aux.vazia() && aux.consultarTopo().compareTo(atual) > 0) {
                this.empilhar(aux.desempilhar());
            }
            aux.empilhar(atual);
        }
        // aux tem o maior no topo; voltando, this fica com o menor no topo
        while (!aux.vazia()) {
            this.empilhar(aux.desempilhar());
        }
    }

    // inverte usando uma fila
    public void inverter() {
        Fila<E> fila = new Fila<>();
        while (!vazia()) fila.enfileirar(desempilhar());
        while (!fila.vazia()) empilhar(fila.desenfileirar());
    }
}

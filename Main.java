public class Main {
    public static void main(String[] a) {
        Pilha<String> p = new Pilha<>();
        for (String s : new String[]{"maria","ana","joao","carlos","bia"}) p.empilhar(s);
        System.out.print("pilha: "); p.imprimir();
        System.out.print("ordem insercao: "); p.imprimirOrdemInsercao();
        System.out.println("n=" + p.obterNumeroItens() + " contem joao=" + p.contem("joao") + " rec ze=" + p.contemRecursivo("ze"));
        p.ordenar(); System.out.print("ordenada (topo=menor): "); p.imprimir();
        p.inverter(); System.out.print("invertida: "); p.imprimir();
        System.out.println("desempilha: " + p.desempilhar() + " topo: " + p.consultarTopo());

        Fila<Integer> f = new Fila<>();
        for (int x : new int[]{30, 10, 50, 20, 40}) f.enfileirar(x);
        System.out.print("fila: "); f.imprimir();
        f.inserirNoInicio(99); System.out.print("inserirNoInicio 99: "); f.imprimir();
        System.out.println("removerFim: " + f.removerFim()); f.imprimir();
        System.out.println("n=" + f.obterNumeroItens() + " contem 20=" + f.contem(20) + " quantosAfrente 50=" + f.quantosAfrente(50));
        f.ordenar(); System.out.print("ordenada: "); f.imprimir();
        f.enfileirar(5); System.out.print("enfileira 5 (tras ok?): "); f.imprimir();
        f.inverter(); System.out.print("invertida: "); f.imprimir();
        Fila<Integer> g = new Fila<>(); g.inserirNoInicio(1); g.enfileirar(2); g.imprimir();
        System.out.println(g.desenfileirar()+" "+g.desenfileirar()+" vazia="+g.vazia()); g.enfileirar(3); g.imprimir();

        Lista<String> l = new Lista<>();
        for (String s : new String[]{"d","b","a","b","c","b"}) l.inserirFinal(s);
        System.out.print("lista: "); l.imprimir();
        System.out.println("localizar c=" + l.localizar("c") + " contarQtos b=" + l.contarQtos("b") + " contem z=" + l.contem("z"));
        System.out.println("removerTodos b=" + l.removerTodos("b")); l.imprimir();
        l.inserirFinal("b"); System.out.println("removerTodos b (no fim)=" + l.removerTodos("b")); l.inserirFinal("z"); l.imprimir();
        l.ordenar(); System.out.print("ordenada: "); l.imprimir();
        l.inserirEmOrdem("bb"); l.inserirEmOrdem("zz"); l.inserirEmOrdem("0"); l.imprimir();
        l.inverter(); System.out.print("invertida: "); l.imprimir();
        l.inserirFinal("FIM"); l.imprimir();
        System.out.println("remover(1)=" + l.remover(1)); l.imprimir();

        Fila<Integer> f1 = new Fila<>(), f2 = new Fila<>();
        for (int x : new int[]{1,4,9}) f1.enfileirar(x);
        for (int x : new int[]{2,3,10,11}) f2.enfileirar(x);
        Lista<Integer> m = new Lista<>(f1, f2); System.out.print("intercalada: "); m.imprimir();

        ListaDupla<String> d = new ListaDupla<>();
        d.inserirFinal("A"); d.inserirFinal("C"); d.inserir("B", 1); d.inserir("Z", 3); d.inserir("0", 0);
        d.imprimir(); d.imprimirInverso();
        System.out.println("removerFinal=" + d.removerFinal() + " remover(0)=" + d.remover(0) + " remover(1)=" + d.remover(1));
        d.imprimir(); d.imprimirInverso(); d.inserirFinal("D"); d.imprimirInverso();
    }
}

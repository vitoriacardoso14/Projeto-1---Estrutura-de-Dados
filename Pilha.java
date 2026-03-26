public class Pilha<T> {  // <T> significa que a pilha pode armazenar qualquer tipo

    private Object[] dados;
    private int topo;
    private int capacidade;

    public Pilha(int capacidade) {
        this.capacidade = capacidade;
        this.dados = new Object[capacidade];
        this.topo = -1;
    }

    public boolean isFull() {
        return topo == capacidade - 1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public void push(T elemento) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
            return;
        }

        dados[++topo] = elemento;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Pilha vazia!");
            return null;
        }

        T elemento = (T) dados[topo];
        dados[topo] = null;
        topo--;

        return elemento;
    }

    public T peek() {
        if (isEmpty()) return null;
        return (T) dados[topo];
    }

    public int size() {
        return topo + 1;
    }

    public T get(int index) {
        if (index < 0 || index > topo) return null;
        return (T) dados[index];
    }
}
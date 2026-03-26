////Código de pilha responsável pelos chamados com nível >= 4

public class Pilha<T> {
    private int topo;
    private int capacidade;
    private T[] elementos;

    @SuppressWarnings("unchecked")
    public Pilha(int capacidade) {
        this.capacidade = capacidade;
        this.topo = -1; // Indica que a pilha está vazia
        this.elementos = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == capacidade - 1;
    }

    public void push(T elemento) {
        if (!isFull()) {
            elementos[++topo] = elemento;
        } else {
            System.out.println("Erro: Pilha de emergência lotada!");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            return elementos[topo--];
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return elementos[topo];
        }
        return null;
    }
}
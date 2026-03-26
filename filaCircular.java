//Código de fila responsável pelos chamados com nível < 4

public class FilaCircular<T> {
    private int primeiro;
    private int ultimo;
    private int total;
    private int capacidade;
    private T[] elementos;

    @SuppressWarnings("unchecked")
    public FilaCircular(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = (T[]) new Object[capacidade];
        this.primeiro = 0;
        this.ultimo = 0;
        this.total = 0;
    }

    public boolean isEmpty() {
        return total == 0;
    }

    public boolean isFull() {
        return total == capacidade;
    }

    public void enqueue(T elemento) {
        if (!isFull()) {
            elementos[ultimo] = elemento;
            ultimo = (ultimo + 1) % capacidade; // Lógica circular
            total++;
        } else {
            System.out.println("Erro: Fila comum lotada!");
        }
    }

    public T dequeue() {
        if (!isEmpty()) {
            T elemento = elementos[primeiro];
            primeiro = (primeiro + 1) % capacidade; // Lógica circular
            total--;
            return elemento;
        }
        return null;
    }

    public int getTotal() {
        return total;
    }
}
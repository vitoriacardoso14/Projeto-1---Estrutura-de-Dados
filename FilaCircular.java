public class FilaCircular<T> { // <T> significa que a fila pode armazenar qualquer tipo

    private Object[] dados;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircular(int capacidade) {
        this.capacidade = capacidade;
        this.dados = new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean isFull() {
        return tamanho == capacidade;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public void enqueue(T elemento) {
        if (isFull()) {
            System.out.println("Fila cheia!");
            return;
        }

        dados[fim] = elemento;
        fim = (fim + 1) % capacidade;
        tamanho++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Fila vazia!");
            return null;
        }

        T elemento = (T) dados[inicio];
        dados[inicio] = null;
        inicio = (inicio + 1) % capacidade;
        tamanho--;

        return elemento;
    }

    public T peek() {
        if (isEmpty()) return null;
        return (T) dados[inicio];
    }

    public int size() {
        return tamanho;
    }

    public T get(int index) {
        if (index < 0 || index >= tamanho) return null;
        int posicao = (inicio + index) % capacidade;
        return (T) dados[posicao];
    }
}
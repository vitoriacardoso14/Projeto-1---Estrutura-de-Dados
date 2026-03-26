import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner ent = new Scanner(System.in);
    static ArrayList<Chamado> atendimentosAtivos = new ArrayList<>();
    static ArrayList<Chamado> historico = new ArrayList<>();

    // Simulações simples (caso você ainda não tenha implementado essas classes)
    static Pilha<Chamado> pilhaEmergencia = new Pilha<>(30);
    static FilaCircular<Chamado> filaComum = new FilaCircular<>(30);

    public static void main(String[] args) {

        while (true) {
            exibirMenu();
            String opcao = ent.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarChamado();
                    break;

                case "2":
                    realizarAtendimento();
                    break;

                case "3":
                    concluirAtendimento();
                    break;

                case "9":
                    mostrarRankingBairros()
                    break;

                case "11":
                    System.out.println("Encerrando...");
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // 1
    public static void cadastrarChamado() {

        System.out.print("ID: ");
        int id = Integer.parseInt(ent.nextLine());

        System.out.print("Bairro: ");
        String bairro = ent.nextLine();

        System.out.print("Descrição: ");
        String descricao = ent.nextLine();

        System.out.print("Nível de urgência (1-5): ");
        char nivel = ent.nextLine().charAt(0);

        Chamado novo = new Chamado(id, bairro, descricao, nivel, Chamado.Status.ABERTO);

        int urgencia = Character.getNumericValue(nivel);

        if (urgencia >= 4) {
            pilhaEmergencia.push(novo);
        } else {
            filaComum.enqueue(novo);
        }

        historico.add(novo);

        System.out.println("Chamado cadastrado!");
    }

    // 2
    public static void realizarAtendimento() {

        Chamado chamado = null;

        if (!pilhaEmergencia.isEmpty()) {
            chamado = pilhaEmergencia.pop();
        } else if (!filaComum.isEmpty()) {
            chamado = filaComum.dequeue();
        } else {
            System.out.println("Nenhum chamado disponível.");
            return;
        }

        chamado.setStatus(Chamado.Status.EM_ATENDIMENTO);
        atendimentosAtivos.add(chamado);

        System.out.println("Atendendo chamado ID: " + chamado.getId());
    }

    // 3
    public static void concluirAtendimento() {

        if (atendimentosAtivos.isEmpty()) {
            System.out.println("Nenhum atendimento em andamento.");
            return;
        }

        System.out.println("Atendimentos ativos:");
        for (Chamado c : atendimentosAtivos) {
            System.out.println("ID: " + c.getId());
        }

        System.out.print("Digite o ID: ");
        int id = Integer.parseInt(ent.nextLine());

        Chamado remover = null;

        for (Chamado c : atendimentosAtivos) {
            if (c.getId() == id) {
                remover = c;
                break;
            }
        }

        if (remover != null) {
            atendimentosAtivos.remove(remover);

            for (Chamado c : historico) {
                if (c.getId() == id) {
                    c.setStatus(Chamado.Status.FINALIZADO);
                    break;
                }
            }

            System.out.println("Atendimento concluído!");
        } else {
            System.out.println("Chamado não encontrado.");
        }
    }

    //4
    public static void mostrarRankingBairros() {
    if (historico.isEmpty()) {
        System.out.println("Histórico vazio. Sem dados para o ranking.");
        return;
    }

    ArrayList<BairroContagem> ranking = new ArrayList<>();

    // 1. Contagem de chamados por bairro baseada no Histórico [cite: 86]
    for (Chamado c : historico) {
        boolean encontrado = false;
        for (BairroContagem bc : ranking) {
            if (bc.nome.equalsIgnoreCase(c.getBairro())) {
                bc.total++;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            ranking.add(new BairroContagem(c.getBairro(), 1));
        }
    }

    // 2. Ordenação Manual (Bubble Sort) - Ordem Decrescente 
    int n = ranking.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (ranking.get(j).total < ranking.get(j + 1).total) {
                // Troca os elementos
                BairroContagem temp = ranking.get(j);
                ranking.set(j, ranking.get(j + 1));
                ranking.set(j + 1, temp);
            }
        }
    }

    // 3. Exibição do Ranking 
    System.out.println("\n--- RANKING DE BAIRROS (MAIS PROBLEMÁTICOS) ---");
    for (int i = 0; i < ranking.size(); i++) {
        BairroContagem bc = ranking.get(i);
        System.out.println((i + 1) + "º " + bc.nome + ": " + bc.total + " chamados");
    }

    // menu
    public static void exibirMenu() {
        System.out.println("\n--- Sistema de Emergência ---");
        System.out.println("1. Cadastrar chamado");
        System.out.println("2. Realizar atendimento");
        System.out.println("3. Concluir atendimento");
        System.out.println("11. Sair");
        System.out.print("Opção: ");
    }
}
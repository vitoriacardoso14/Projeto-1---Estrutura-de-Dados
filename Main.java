import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner ent = new Scanner(System.in);
    static ArrayList<Chamado> atendimentosAtivos = new ArrayList<>();
    static ArrayList<Chamado> historico = new ArrayList<>();
    static Pilha<Chamado> pilhaEmergencia = new Pilha<>(30); // pilha para chamados urgentes (LIFO)
    static FilaCircular<Chamado> filaComum = new FilaCircular<>(30); // fila para chamados comuns (FIFO)

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

                case "4":
                    mostrarChamadosAbertos();
                    break;

                case "5":
                    mostrarAtendimentosAtivos();
                    break;

                case "6":
                    mostrarHistoricoCompleto();
                    break;

                case "7":
                    mostrarEstatisticaNiveis();
                    break;

                case "8":
                    mostrarChamadosPorNivel();
                    break;

                case "9":
                    mostrarRankingBairros();
                    break;

                case "10":
                    simularCadastro();
                    break;

                case "11":
                    System.out.print("Deseja realmente sair? (S/N): ");
                    String resp = ent.nextLine().trim().toUpperCase();

                    if (resp.equals("S")) {
                        System.out.println("Sistema finalizado.");
                        return;
                    } else if (resp.equals("N")) {
                        System.out.println("Voltando para o menu...");
                        break;
                    }

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // 1
    public static void cadastrarChamado() {
        System.out.println("\n========= CADASTRAR CHAMADO =========");
        
        System.out.print("ID: ");
        int id;

        try { // valida id
            id = Integer.parseInt(ent.nextLine());
        } catch (Exception e) {
            System.out.println("ID inválido!");
            return;
        }
        
        // verifica se o id ja existe
        
        for (Chamado c : historico) {
            if (c.getId() == id) {
                System.out.println("ID já existe!");
                return;
            }
        }

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
        System.out.println("\n========= REALIZAR ATENDIMENTO =========");
        
        Chamado chamado = null;

        if (!pilhaEmergencia.isEmpty()) {
            chamado = pilhaEmergencia.pop(); // remove da pilha
        } else if (!filaComum.isEmpty()) {
            chamado = filaComum.dequeue(); // remove da fila
        } else {
            System.out.println("Nenhum chamado disponível.");
            return;
        }

        chamado.setStatus(Chamado.Status.EM_ATENDIMENTO);
        atendimentosAtivos.add(chamado);

        System.out.println("Atendendo: " + chamado);
    }

    // 3
    public static void concluirAtendimento() {
        System.out.println("\n========= CONCLUIR =========");
        
        if (atendimentosAtivos.isEmpty()) {
            System.out.println("Nenhum atendimento em andamento.");
            return;
        }

        for (Chamado c : atendimentosAtivos) {
            System.out.println(c);
        }

        System.out.print("Digite o ID: ");
        int id = Integer.parseInt(ent.nextLine());

        Chamado remover = null;

        for (Chamado c : atendimentosAtivos) {
            if (c.getId() == id) {
                remover = c;
                break; // para quando encontra
            }
        }

        if (remover != null) {
            atendimentosAtivos.remove(remover);

            for (Chamado c : historico) {
                if (c.getId() == id) {
                    c.setStatus(Chamado.Status.FINALIZADO);
                }
            }

            System.out.println("Atendimento finalizado!");
        } else {
            System.out.println("ID não encontrado.");
        }
    }

    // 4
    public static void mostrarChamadosAbertos() {
        System.out.println("\n========= CHAMADOS ABERTOS =========");

        if (pilhaEmergencia.isEmpty()) {
            System.out.println("Vazia");
        } else {
            for (int i = pilhaEmergencia.size() - 1; i >= 0; i--) {
                System.out.println(pilhaEmergencia.get(i));
            }
        }

        System.out.println("\nFILA COMUM");

        if (filaComum.isEmpty()) {
            System.out.println("Vazia");
        } else {
            for (int i = 0; i < filaComum.size(); i++) {
                System.out.println(filaComum.get(i));
            }
        }
    }

    // 5
    public static void mostrarAtendimentosAtivos() {
        System.out.println("\n========= ATENDIMENTOS ATIVOS =========");
        
        if (atendimentosAtivos.isEmpty()) {
            System.out.println("Nenhum atendimento.");
            return;
        }

        for (Chamado c : atendimentosAtivos) {
            System.out.println(c);
        }
    }

    // 6
    public static void mostrarHistoricoCompleto() {
        System.out.println("\n========= HISTORICO COMPLETO =========");
        
        if (historico.isEmpty()) {
            System.out.println("Sem histórico.");
            return;
        }

        for (Chamado c : historico) {
            System.out.println(c);
        }
    }

    // 7
    public static void mostrarEstatisticaNiveis() {
        System.out.println("\n========= ESTATÍSTICA NÍVEIS =========");
        
        int[] contagem = new int[6];

        for (Chamado c : historico) {
            int nivel = Character.getNumericValue(c.getNivelUrgencia());
            contagem[nivel]++;
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("Nível " + i + ": " + contagem[i]);
        }
    }

    // 8
    public static void mostrarChamadosPorNivel() {
        System.out.println("\n========= CHAMADOS POR NÍVEL =========");
        
        System.out.print("Nível: ");
        char nivel = ent.nextLine().charAt(0);

        int total = 0;

        for (Chamado c : historico) {
            if (c.getNivelUrgencia() == nivel) {
                System.out.println(c);
                total++;
            }
        }

        System.out.println("Total: " + total);
    }

    // 9
    public static void mostrarRankingBairros() {
        System.out.println("\n========= RANKING BAIRROS =========");
        
        ArrayList<BairroContagem> ranking = new ArrayList<>();

        for (Chamado c : historico) {
            boolean achou = false;

            for (BairroContagem b : ranking) {
                if (b.nome.equalsIgnoreCase(c.getBairro())) {
                    b.total++;
                    achou = true;
                    break;
                }
            }

            if (!achou) {
                ranking.add(new BairroContagem(c.getBairro(), 1));
            }
        }

        // ordenação selection
        for (int i = 0; i < ranking.size() - 1; i++) {
            int max = i;

            for (int j = i + 1; j < ranking.size(); j++) {
                if (ranking.get(j).total > ranking.get(max).total) {
                    max = j;
                }
            }

            BairroContagem temp = ranking.get(i);
            ranking.set(i, ranking.get(max));
            ranking.set(max, temp);
        }

        for (int i = 0; i < ranking.size(); i++) {
            BairroContagem b = ranking.get(i);
            System.out.println((i + 1) + "º " + b.nome + " - " + b.total);
        }
    }

    // 10
    public static void simularCadastro() {
        System.out.println("\n========= SIMULAR CADASTRO =========");
        
        Object[][] dados = {
            {101, "Tatuapé", "Comum", '2'},
            {102, "Pinheiros", "Emergência", '5'},
            {103, "Bela Vista", "Comum", '3'},
            {104, "Tucuruvi", "Emergência", '4'}
        };

        for (Object[] d : dados) {
            int id = (int) d[0];
            String bairro = (String) d[1];
            String descricao = (String) d[2];
            char nivel = (char) d[3];

            Chamado novo = new Chamado(id, bairro, descricao, nivel, Chamado.Status.ABERTO);

            if (Character.getNumericValue(nivel) >= 4) {
                pilhaEmergencia.push(novo);
            } else {
                filaComum.enqueue(novo);
            }

            historico.add(novo);
        }

        System.out.println("Simulação concluída!");
    }

    public static void exibirMenu() {

        System.out.println("\nSistema de atendimento de Emergência Urbana");
        System.out.println("\n1. Cadastrar chamado");
        System.out.println("2. Realizar atendimento chamado");
        System.out.println("3. Concluir atendimento");
        System.out.println("4. Mostrar chamados abertos");
        System.out.println("5. Mostrar lista ativa de atendimentos");
        System.out.println("6. Mostrar histórico completo dos chamados");
        System.out.println("7. Mostrar estatística dos níveis de emergência");
        System.out.println("8. Mostrar os chamados com nível de emergência");
        System.out.println("9. Mostrar Ranking de bairros");
        System.out.println("10. Simular Cadastro de Chamado");
        System.out.println("11. Sair");
        System.out.print("\nOpção: ");
    }
}
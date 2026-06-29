package sistema_notificacoes;

import java.util.Scanner;

public class TestaNotificacoes {

    public static void main(String[] args) {
        System.out.println("***** TESTES AUTOMÁTICOS DO ENUNCIADO *****");
        testarEmailValido();
        testarSmsValido();
        testarMensagemVazia();
        testarDestinatarioVazio();
        testarTipoInexistente();
        
        System.out.println("\n*******************************************");
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;

        System.out.println("*** SISTEMA INTERATIVO DE NOTIFICAÇÕES ***");

        while (opcao != 3) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Disparar Notificação (E-mail ou SMS)");
            System.out.println("2 - Rodar Novamente os Testes de Validação");
            System.out.println("3 - Sair");
            System.out.print("Digite a opção desejada: ");
            
            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    dispararNotificacaoInterativa(teclado);
                    break;
                case 2:
                    System.out.println("\n*** Reexecutando Validações Fixas ***");
                    testarMensagemVazia();
                    testarDestinatarioVazio();
                    testarTipoInexistente();
                    System.out.println("\nPressione ENTER para continuar...");
                    teclado.nextLine();
                    break;
                case 3:
                    System.out.println("\nSaindo... Todas as tarefas concluídas com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        teclado.close();
    }
    public static void criarNotificacao(String tipo, String destinatario, String mensagem) {
        if (tipo == null || (!tipo.equalsIgnoreCase("Email") && !tipo.equalsIgnoreCase("SMS"))) {
            throw new IllegalArgumentException("Tipo de notificação inexistente! Use apenas 'Email' ou 'SMS'.");
        }

        Notificacao notificacao;
        if (tipo.equalsIgnoreCase("Email")) {
            notificacao = new NotificacaoEmail(destinatario);
        } else {
            notificacao = new NotificacaoSMS(destinatario);
        }
        
        notificacao.enviar(mensagem);
    }
    private static void dispararNotificacaoInterativa(Scanner teclado) {
        System.out.println("\n*** Disparador de Mensagem ***");
        System.out.print("Digite o Tipo (Email / SMS): ");
        String tipo = teclado.nextLine();
        System.out.print("Digite o Destinatário: ");
        String dest = teclado.nextLine();
        System.out.print("Digite a Mensagem: ");
        String msg = teclado.nextLine();

        try {
            criarNotificacao(tipo, dest, msg);
            System.out.println("\nNotificação enviada perfeitamente!");
        } catch (IllegalArgumentException error) {
            System.out.println("\n[ERRO DE VALIDAÇÃO] Falha no envio.");
            System.out.println("Motivo: " + error.getMessage());
        }

        System.out.println("\nPressione ENTER para voltar ao menu...");
        teclado.nextLine();
    }

    public static void testarEmailValido() {
        System.out.println("\n*** Teste: Email Válido ***");
        try {
            criarNotificacao("Email", "ana@email.com", "Bem-vindo ao sistema");
        } catch (Exception error) {
            System.out.println("Erro inesperado: " + error.getMessage());
        }
    }

    public static void testarSmsValido() {
        System.out.println("\n*** Teste: SMS Válido ***");
        try {
            criarNotificacao("SMS", "11988887777", "Seu código é 1234");
        } catch (Exception error) {
            System.out.println("Erro inesperado: " + error.getMessage());
        }
    }

    public static void testarMensagemVazia() {
        System.out.println("\n*** Teste: Mensagem Vazia ***");
        try {
            criarNotificacao("Email", "ana@email.com", "");
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }

    public static void testarDestinatarioVazio() {
        System.out.println("\n*** Teste: Destinatário Vazio ***");
        try {
            criarNotificacao("SMS", "  ", "Olá");
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }

    public static void testarTipoInexistente() {
        System.out.println("\n*** Teste: Tipo Inexistente ***");
        try {
            criarNotificacao("WhatsApp", "11988887777", "Oi");
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }
}
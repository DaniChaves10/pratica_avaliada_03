package sistema_usuarios;

import java.util.ArrayList;
import java.util.Scanner;

public class TestaUsuario {
	
	private static ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();

    public static void main(String[] args) {
    	
    	System.out.println("***** TESTES AUTOMÁTICOS DO ENUNCIADO *****");
        testarUsuarioValido();
        testarAdministradorValido();
        testarNomeInvalido();
        testarEmailInvalido();
        
        System.out.println("\n*********************************************");
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;

        System.out.println("*** SISTEMA DE GERENCIAMENTO DE USUÁRIOS ***");

        while (opcao != 4) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Criar Usuário Comum");
            System.out.println("2 - Criar Administrador");
            System.out.println("3 - Listar Todos os Usuários");
            System.out.println("4 - Sair");
            System.out.print("Digite a opção desejada: ");
            
            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números (1, 2, 3 ou 4).");
                continue;
            }

            switch (opcao) {
                case 1:
                    criarUsuarioInterativo(teclado);
                    break;
                case 2:
                    criarAdministradorInterativo(teclado);
                    break;
                case 3:
                    listarUsuarios(teclado);
                    break;
                case 4:
                    System.out.println("\nSaindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha 1 a 4.");
            }
        }

        teclado.close();
    }

    private static void criarUsuarioInterativo(Scanner teclado) {
        System.out.println("\n*** Cadastro de Usuário Comum ***");
        
        System.out.print("Digite o Nome: ");
        String nome = teclado.nextLine();
        
        System.out.print("Digite o Email: ");
        String email = teclado.nextLine();

        try {
            Usuario novoUsuario = new Usuario(nome, email);
            listaDeUsuarios.add(novoUsuario);
            System.out.println("\nUsuário criado com sucesso!");
            novoUsuario.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("\n[ERRO DE VALIDAÇÃO] Não foi possível criar o usuário.");
            System.out.println("Motivo: " + error.getMessage());
        }
        System.out.println("\nPressione ENTER para continuar...");
        teclado.nextLine();
    }

    private static void criarAdministradorInterativo(Scanner teclado) {
        System.out.println("\n*** Cadastro de Administrador ***");
        
        System.out.print("Digite o Nome: ");
        String nome = teclado.nextLine();
        
        System.out.print("Digite o Email: ");
        String email = teclado.nextLine();
        
        System.out.print("Digite o Nível de Acesso: ");
        String nivel = teclado.nextLine();

        try {
            Administrador novoAdmin = new Administrador(nome, email, nivel);
            listaDeUsuarios.add(novoAdmin);
            System.out.println("\nAdministrador criado com sucesso!");
            novoAdmin.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("\n[ERRO DE VALIDAÇÃO] Não foi possível criar o administrador.");
            System.out.println("Motivo: " + error.getMessage());
        }
    }
    
    private static void listarUsuarios(Scanner teclado) {
        System.out.println("\n*** LISTA DE USUÁRIOS CADASTRADOS ***");
        
        if (listaDeUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário foi cadastrado de forma interativa ainda.");
        } else {
            for (Usuario u : listaDeUsuarios) {
                u.visualizar();
                System.out.println("***************************************");
            }
        }
        System.out.println("\nPressione ENTER para voltar ao menu...");
        teclado.nextLine();
    }
    
    public static void testarUsuarioValido() {
        try {
            System.out.println("\n*** Teste: Criando Usuário Válido ***");
            Usuario u1 = new Usuario("Daniel Chaves", "daniel@email.com");
            u1.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("Erro inesperado: " + error.getMessage());
        }
    }

    public static void testarAdministradorValido() {
        try {
            System.out.println("\n*** Teste: Criando Administrador Válido ***");
            Administrador adm = new Administrador("Hellen Larissa", "hellen@email.com", "Admin_Total");
            adm.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("Erro inesperado: " + error.getMessage());
        }
    }

    public static void testarNomeInvalido() {
        try {
            System.out.println("\n*** Teste: Forçando Erro de Nome Vazio ***");
            Usuario u2 = new Usuario(" ", "teste@email.com");
            u2.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }

    public static void testarEmailInvalido() {
        try {
            System.out.println("\n*** Teste: Forçando Erro de Email Incorreto ***");
            Usuario u3 = new Usuario("Carlos Santos", "email_sem_arroba.com");
            u3.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }
}
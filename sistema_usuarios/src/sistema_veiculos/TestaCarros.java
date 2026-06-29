package sistema_veiculos;

import java.util.ArrayList;
import java.util.Scanner;

public class TestaCarros {
	
	private static ArrayList<CarroEletrico> listaDeCarros = new ArrayList<>();

    public static void main(String[] args) {
    	
    	System.out.println("***** TESTES AUTOMÁTICOS DO ENUNCIADO *****");
        testarFluxoSucesso();
        testarAceleracaoInvalida();
        testarBateriaInsuficiente();
        testarBateriaNegativa();
        testarBateriaAcimaLimite();
        
        System.out.println("\n***********************************************");
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;
        
        System.out.println("*** SISTEMA DE GERENCIAMENTO DE VEÍCULOS ELETRICOS ***");

        while (opcao != 4) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar Carro Elétrico");
            System.out.println("2 - Acelerar um Carro");
            System.out.println("3 - Listar Todos os Carros");
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
                    cadastrarCarroInterativo(teclado);
                    break;
                case 2:
                    acelerarCarroInterativo(teclado);
                    break;
                case 3:
                    listarCarros(teclado);
                    break;
                case 4:
                    System.out.println("\nSaindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha de 1 a 4.");
            }
        }

        teclado.close();
    }
    private static void cadastrarCarroInterativo(Scanner teclado) {
        System.out.println("\n*** Cadastro de Carro Elétrico ***");
        
        System.out.print("Digite o Modelo do Carro: ");
        String modelo = teclado.nextLine();
        
        System.out.print("Digite a Velocidade Inicial (km/h): ");
        int velocidade;
        try {
            velocidade = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Velocidade inválida! Deve ser um número.");
            return;
        }
        
        System.out.print("Digite o Nível da Bateria (%): ");
        int bateria;
        try {
            bateria = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nível de bateria inválido! Deve ser um número.");
            return;
        }

        try {
            CarroEletrico novoCarro = new CarroEletrico(modelo, velocidade, bateria);
            listaDeCarros.add(novoCarro);
            System.out.println("\nCarro cadastrado com sucesso!");
            novoCarro.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("\n[ERRO DE VALIDAÇÃO] Não foi possível cadastrar o veículo.");
            System.out.println("Motivo: " + error.getMessage());
        }
        
        System.out.println("\nPressione ENTER para continuar...");
        teclado.nextLine();
    }
    private static void acelerarCarroInterativo(Scanner teclado) {
        System.out.println("\n*** Acelerar Veículo ***");
        
        if (listaDeCarros.isEmpty()) {
            System.out.println("Nenhum carro cadastrado para acelerar ainda.");
            System.out.println("\nPressione ENTER para voltar...");
            teclado.nextLine();
            return;
        }

        System.out.println("Selecione o carro que deseja acelerar:");
        for (int i = 0; i < listaDeCarros.size(); i++) {
            System.out.println(i + " - " + listaDeCarros.get(i).getModelo() + " (Velocidade atual: " + listaDeCarros.get(i).getVelocidade() + " km/h)");
        }
        System.out.print("Digite o número do carro: ");
        
        int indice;
        try {
            indice = Integer.parseInt(teclado.nextLine());
            if (indice < 0 || indice >= listaDeCarros.size()) {
                System.out.println("Carro inválido!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
            return;
        }

        System.out.print("Digite o quanto deseja acelerar (km/h): ");
        int aceleracao;
        try {
            aceleracao = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor de aceleração inválido!");
            return;
        }

        try {
            CarroEletrico carroSelecionado = listaDeCarros.get(indice);
            carroSelecionado.acelerar(aceleracao);
            System.out.println("\n[SUCESSO] O carro acelerou!");
            carroSelecionado.visualizar();
        } catch (IllegalArgumentException error) {
            System.out.println("\n[ERRO DE VALIDAÇÃO] Não foi possível acelerar.");
            System.out.println("Motivo: " + error.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        teclado.nextLine();
    }
    private static void listarCarros(Scanner teclado) {
        System.out.println("\n*** LISTA DE VEÍCULOS CADASTRADOS ***");
        
        if (listaDeCarros.isEmpty()) {
            System.out.println("Nenhum carro foi cadastrado de forma interativa ainda.");
        } else {
            for (CarroEletrico c : listaDeCarros) {
                c.visualizar();
                System.out.println("*********************************");
            }
        }
        
        System.out.println("\nPressione ENTER para voltar ao menu...");
        teclado.nextLine();
    }
    
    public static void criarCarroEletrico(String modelo, int velocidade, int bateria, int aceleracao) {
        CarroEletrico carro = new CarroEletrico(modelo, velocidade, bateria);
        carro.acelerar(aceleracao);
        carro.visualizar();
    }

    public static void testarFluxoSucesso() {
        System.out.println("\n--- Teste 1: Cenário de Sucesso (Ajustado para Restar 5%) ---");
        try {
            CarroEletrico tesla = new CarroEletrico("Tesla Model 3", 0, 65);
            System.out.println("=== Estado Inicial ===");
            tesla.visualizar();
            System.out.println("\n=== Após Acelerar 30 km/h ===");
            tesla.acelerar(30);
            tesla.visualizar();
        } catch (Exception error) {
            System.out.println("Erro inesperado: " + error.getMessage());
        }
    }

    public static void testarAceleracaoInvalida() {
        System.out.println("\n--- Teste 2: Aceleração com Valor Inválido (-5 km/h) ---");
        try {
            criarCarroEletrico("BYD Seal", 0, 80, -5);
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }

    public static void testarBateriaInsuficiente() {
        System.out.println("\n--- Teste 3: Bateria Insuficiente (Acelerar muito com pouca carga) ---");
        try {
            criarCarroEletrico("Tesla Model 3", 0, 20, 40);
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }

    public static void testarBateriaNegativa() {
        System.out.println("\n--- Teste 4: Nível de Bateria Inicial Negativo (-10%) ---");
        try {
            new CarroEletrico("Renault Kwid E-Tech", 0, -10);
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }

    public static void testarBateriaAcimaLimite() {
        System.out.println("\n--- Teste 5: Nível de Bateria Inicial Acima de 100% (150%) ---");
        try {
            new CarroEletrico("Audi e-tron", 0, 150);
        } catch (IllegalArgumentException error) {
            System.out.println("Sucesso no teste de erro -> Pegou a exceção: " + error.getMessage());
        }
    }
}
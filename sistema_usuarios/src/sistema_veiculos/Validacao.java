package sistema_veiculos;

public class Validacao {
	
	public static void validarAceleracao(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("A aceleração deve ser um número inteiro positivo maior que zero!");
        }
    }
    public static void validarNivelBateria(int nivel) {
        if (nivel < 0 || nivel > 100) {
            throw new IllegalArgumentException("Nível de bateria inválido! Deve estar entre 0 e 100%.");
        }
    }
    public static void validarBateriaInsuficiente(int consumo, int bateriaAtual) {
        if (consumo > bateriaAtual) {
            throw new IllegalArgumentException("Bateria insuficiente para realizar esta aceleração!");
        }
    }
}
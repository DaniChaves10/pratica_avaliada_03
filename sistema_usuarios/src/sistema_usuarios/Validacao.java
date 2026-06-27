package sistema_usuarios;

public class Validacao {
	
	public static void validarNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome Inválido!");
	}
	}
	
	public static void validarEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email Inválido!");
	}
	}

}

package sistema_usuarios;

import java.util.regex.Pattern;

public class Validacao {
	
	private static final String EMAIL_GEFEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final Pattern pattern = Pattern.compile(EMAIL_GEFEX);
	
	public static void validarNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome Inválido! O nome não pode ser nulo ou vazio.");
	}
	}
	
	public static void validarEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email Inválido! O campo não pode ser vazio.");
	}
		if(!pattern.matcher(email).matches()) {
			throw new IllegalArgumentException("Email inválido! Formato inconrreto (exemplo: exemplo@exemplo.com).");
		}
	}

}

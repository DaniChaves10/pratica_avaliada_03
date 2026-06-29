package sistema_notificacoes;

import java.util.regex.Pattern;

public class Validacao {

    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String REGEX_CELULAR = "^[1-9]{2}9[0-9]{8}$";

    public static void validarMensagem(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode ser nula ou vazia!");
        }
    }

    public static void validarDestinatario(String destinatario) {
        if (destinatario == null || destinatario.trim().isEmpty()) {
            throw new IllegalArgumentException("O destinatário não pode ser nulo ou vazio!");
        }
        String limpo = destinatario.trim();

        boolean ehEmail = Pattern.compile(REGEX_EMAIL).matcher(limpo).matches();
        boolean ehCelular = Pattern.compile(REGEX_CELULAR).matcher(limpo).matches();

        if (!ehEmail && !ehCelular) {
            throw new IllegalArgumentException("Destinatário inválido! Deve ser um e-mail válido ou telefone celular (DDD + 9 dígitos).");
        }
    }
}
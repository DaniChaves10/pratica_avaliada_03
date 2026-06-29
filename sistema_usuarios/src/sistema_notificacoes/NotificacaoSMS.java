package sistema_notificacoes;

public class NotificacaoSMS extends Notificacao {

    public NotificacaoSMS(String destinatario) {
        super(destinatario);
    }

    @Override
    public void enviar(String mensagem) {
        Validacao.validarMensagem(mensagem);
        System.out.println("Enviando SMS para " + formatarTelefone(getDestinatario()) + " - Mensagem: " + mensagem);
    }
    private String formatarTelefone(String telefone) {
        String t = telefone.trim();
        return "(" + t.substring(0, 2) + ") " + t.substring(2, 7) + "-" + t.substring(7);
    }
}
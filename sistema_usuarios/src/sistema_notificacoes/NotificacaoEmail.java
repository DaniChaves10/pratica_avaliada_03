package sistema_notificacoes;

public class NotificacaoEmail extends Notificacao {

    public NotificacaoEmail(String destinatario) {
        super(destinatario);
    }

    @Override
    public void enviar(String mensagem) {
        Validacao.validarMensagem(mensagem);
        System.out.println("Enviando Email para " + getDestinatario() + " - Mensagem: " + mensagem);
    }
}
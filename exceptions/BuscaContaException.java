package exceptions;

public class BuscaContaException extends Exception {
    private final String mensagem;

    public BuscaContaException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMessage() {
        return mensagem;
    }
}

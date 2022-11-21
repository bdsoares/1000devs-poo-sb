package models;

public class ClientePf extends Cliente {
    private final String cpf;
    private final String nome;
    private final short idade;

    public ClientePf(String conta, String agencia, String telefone, double saldo, double limite, String cpf, String nome, short idade) {
        super(conta, agencia, telefone, saldo, limite);
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public void exibirConta() {
        System.out.println(super.getConta());
        System.out.println(super.getAgencia());
        System.out.println(super.getTelefone());
        System.out.println(super.getSaldo());
        System.out.println(super.getLimite());
        System.out.println(this.getCpf());
        System.out.println(this.getNome());
        System.out.println(this.getIdade());
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public short getIdade() {
        return idade;
    }
}

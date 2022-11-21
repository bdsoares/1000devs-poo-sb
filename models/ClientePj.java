package models;

public class ClientePj extends Cliente {
    private final String cnpj;
    private final String[] socios;
    private final String razaoSocial;
    private final String nomeFantasia;

    public ClientePj(String conta, String agencia, String telefone, double saldo, double limite, String cnpj, String[] socios, String razaoSocial, String nomeFantasia) {
        super(conta, agencia, telefone, saldo, limite);
        this.cnpj = cnpj;
        this.socios = socios;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public void exibirConta() {
        System.out.println(super.getConta());
        System.out.println(super.getAgencia());
        System.out.println(super.getTelefone());
        System.out.println(super.getSaldo());
        System.out.println(super.getLimite());
        System.out.println(this.getCnpj());
        for (String socio : this.getSocios())
            System.out.println(socio);
        System.out.println(this.getRazaoSocial());
        System.out.println(this.getNomeFantasia());
    }

    public String getCnpj() {
        return cnpj;
    }

    public String[] getSocios() {
        return socios;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }
}

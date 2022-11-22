package models;

public class ClientePj extends Cliente {
    private final String cnpj;
    private final String[] socios;
    private final String razaoSocial;
    private final String nomeFantasia;

    public ClientePj(String conta, String agencia, String telefone, double saldo, double limite, boolean contaAtiva, String cnpj, String[] socios, String razaoSocial, String nomeFantasia) {
        super(conta, agencia, telefone, saldo, limite, contaAtiva);
        this.cnpj = cnpj;
        this.socios = socios;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public void exibirConta() {
        super.exibirConta();
        System.out.println("CNPJ: " + this.getCnpj());
        System.out.println("Socios:");
        for (int i = 0; i < getSocios().length; i++)
            System.out.printf("\tSocio %d: %s\n", i+1, getSocios()[i]);
        System.out.println("Razão Social: " + this.getRazaoSocial());
        System.out.println("Nome Fantasia: " + this.getNomeFantasia());
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

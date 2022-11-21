package models;

public abstract class Cliente implements Conta {
    private final String conta;
    private final String agencia;
    private final String telefone;
    private double saldo;
    private double limite;

    public Cliente(String conta, String agencia, String telefone, double saldo, double limite) {
        this.conta = conta;
        this.agencia = agencia;
        this.telefone = telefone;
        this.saldo = saldo;
        this.limite = limite;
    }

    @Override
    public void exibirConta() {
        System.out.println(getConta());
        System.out.println(getAgencia());
        System.out.println(getTelefone());
        System.out.println(getSaldo());
        System.out.println(getLimite());
    }

    public String getConta() {
        return conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}

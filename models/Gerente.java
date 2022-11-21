package models;

import exceptions.BuscaContaException;

import java.util.Scanner;

public class Gerente {
    private final int MAX_CLIENTES = 50;
    private final Cliente[] clientesPf = new ClientePf[MAX_CLIENTES];
    private final Cliente[] clientesPj = new ClientePj[MAX_CLIENTES];

    public void cadastraCliente(Scanner in, int tipoCliente) {
        System.out.print("Número da conta: ");
        String conta = in.nextLine();
        System.out.print("Agência: ");
        String agencia = in.nextLine();
        System.out.print("Telefone: ");
        String telefone = in.nextLine();
        System.out.print("Saldo: R$");
        double saldo = Double.parseDouble(in.nextLine());
        System.out.print("Limite: R$");
        double limite = Double.parseDouble(in.nextLine());

        if (tipoCliente == 1) {
            System.out.print("CPF: ");
            String cpf = in.nextLine();
            System.out.print("Nome: ");
            String nome = in.nextLine();
            System.out.print("Idade: ");
            short idade = Short.parseShort(in.nextLine());

            ClientePf novoCliente = new ClientePf(conta, agencia, telefone, saldo, limite, cpf, nome, idade);

            for (int i = 0; i < MAX_CLIENTES; i++)
                if (this.clientesPf[i] == null) {
                    this.clientesPf[i] = novoCliente;
                    System.out.printf("Cliente (PF) %s cadastrado com sucesso!\n", nome);
                    break;
                }
        } else {
            System.out.print("CNPJ: ");
            String cnpj = in.nextLine();
            System.out.println("Insira o nome de três sócios: ");
            String[] socios = {
                    in.nextLine(),
                    in.nextLine(),
                    in.nextLine()
            };
            System.out.print("Razão social: ");
            String razaoSocial = in.nextLine();
            System.out.print("Razão fantasia: ");
            String nomeFantasia = in.nextLine();

            ClientePj novoCliente = new ClientePj(conta, agencia, telefone, saldo, limite, cnpj, socios, razaoSocial, nomeFantasia);

            for (int i = 0; i < MAX_CLIENTES; i++)
                if (this.clientesPj[i] == null) {
                    this.clientesPj[i] = novoCliente;
                    System.out.printf("Cliente (PJ) %s cadastrado com sucesso!\n", razaoSocial);
                    break;
                }
        }
    }

    public void removeCliente(String conta, Scanner in) {
        for (int i = 0; i < MAX_CLIENTES; i++) {
            ClientePf clientePf = (ClientePf) clientesPf[i];
            ClientePj clientePj = (ClientePj) clientesPj[i];

            if (clientePf.getConta().equals(conta)) {
                System.out.printf("Tem certeza que deseja remover a conta (PF) de %s? (S/N)", clientePf.getNome());
                String resp = in.next();

                if (resp.equalsIgnoreCase("S")) {
                    clientesPf[i] = null;
                    System.out.println("Operação realizada com sucesso!");
                } else
                    System.out.println("Operação cancelada.");

                break;
            } else if (clientePj.getConta().equals(conta)) {
                System.out.printf("Tem certeza que deseja remover a conta (PJ) de %s? (S/N)", clientePj.getRazaoSocial());
                String resp = in.next();

                if (resp.equalsIgnoreCase("S")) {
                    clientesPj[i] = null;
                    System.out.println("Operação realizada com sucesso!");
                } else
                    System.out.println("Operação cancelada.");

                break;
            }
        }
    }

    public void consultaCliente(String conta) throws BuscaContaException {
        Cliente cliente = buscaConta(conta);
        cliente.exibirConta();
    }

    public void ajustaLimite(String conta, double limite) throws BuscaContaException {
        Cliente cliente = buscaConta(conta);
        cliente.setLimite(limite);
    }

    public void tranfere(String contaOrigem, String contaDestino, double valor) throws BuscaContaException {
        Cliente clienteOrigem = buscaConta(contaOrigem);
        Cliente clienteDestino = buscaConta(contaDestino);

        if (clienteOrigem.getSaldo() > valor) {
            clienteOrigem.setSaldo(clienteOrigem.getSaldo() - valor);
            clienteDestino.setSaldo(clienteDestino.getSaldo() + valor);
        }
        else
            System.out.println("Valor não disponível em saldo na conta de origem.");
    }

    public void adicionaSaldo(String conta, double valor) throws BuscaContaException {
        Cliente cliente = buscaConta(conta);
        cliente.setSaldo(cliente.getSaldo() + valor);
    }

    public void relatorioClientes() {
        for (int i = 0; i < MAX_CLIENTES; i++) {
            ClientePf clientePf = (ClientePf) clientesPf[i];
            ClientePj clientePj = (ClientePj) clientesPj[i];

            if (clientePf != null)
                System.out.println(clientePf.getNome());
            if (clientePj != null)
                System.out.println(clientePj.getRazaoSocial());
        }
    }

    private Cliente buscaConta(String conta) throws BuscaContaException {
        for (int i = 0; i < MAX_CLIENTES; i++) {
            ClientePf clientePf = (ClientePf) clientesPf[i];
            ClientePj clientePj = (ClientePj) clientesPj[i];

            if (clientePf != null && clientePf.getConta().equals(conta))
                return clientePf;
            else if (clientePj != null && clientePj.getConta().equals(conta))
                return clientePj;
        }

        throw new BuscaContaException("Conta não encontrada!");
    }
}

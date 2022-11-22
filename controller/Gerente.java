package controller;

import exceptions.BuscaContaException;
import models.Cliente;
import models.ClientePf;
import models.ClientePj;

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

            ClientePf novoCliente = new ClientePf(conta, agencia, telefone, saldo, limite, true, cpf, nome, idade);

            for (int i = 0; i < MAX_CLIENTES; i++)
                if (this.clientesPf[i] == null) {
                    this.clientesPf[i] = novoCliente;
                    System.out.printf("Cliente (PF) %s cadastrado(a) com sucesso!\n", nome);
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

            ClientePj novoCliente = new ClientePj(conta, agencia, telefone, saldo, limite, true, cnpj, socios, razaoSocial, nomeFantasia);

            for (int i = 0; i < MAX_CLIENTES; i++)
                if (this.clientesPj[i] == null) {
                    this.clientesPj[i] = novoCliente;
                    System.out.printf("Cliente (PJ) %s cadastrado(a) com sucesso!\n", razaoSocial);
                    break;
                }
        }
    }

    public void removeCliente(Scanner in) throws BuscaContaException {
        Cliente conta = buscaConta(in);

        for (int i = 0; i < MAX_CLIENTES; i++) {
            ClientePf clientePf = (ClientePf) clientesPf[i];
            ClientePj clientePj = (ClientePj) clientesPj[i];

            if (clientePf != null && clientePf.getConta().equals(conta.getConta()) && conta.isContaAtiva()) {
                System.out.printf("Tem certeza que deseja remover a conta (PF) de %s (S/N)? ", clientePf.getNome());
                String resp = in.nextLine();

                if (resp.equalsIgnoreCase("S")) {
                    clientesPf[i].setContaAtiva(false);
                    System.out.println("Operação realizada com sucesso!");
                } else
                    System.out.println("Operação cancelada.");

                return;
            } else if (clientePj != null && clientePj.getConta().equals(conta.getConta()) && conta.isContaAtiva()) {
                System.out.printf("Tem certeza que deseja remover a conta (PJ) de %s (S/N)? ", clientePj.getRazaoSocial());
                String resp = in.nextLine();

                if (resp.equalsIgnoreCase("S")) {
                    clientesPj[i].setContaAtiva(false);
                    System.out.println("Operação realizada com sucesso!");
                } else
                    System.out.println("Operação cancelada.");

                return;
            }
        }

        System.out.println("Conta nao encontrada!");
    }

    public void consultaCliente(Scanner in) throws BuscaContaException {
        Cliente cliente = buscaConta(in);
        System.out.printf("# ===== Consulta Conta%s ===== #\n", cliente.isContaAtiva() ? "" : " Removida");
        cliente.exibirConta();
        System.out.printf("# ==========================%s #\n", cliente.isContaAtiva() ? "" : "=========");
    }

    public void ajustaLimite(Scanner in) throws BuscaContaException {
        Cliente cliente = buscaConta(in);
        if (!checkContaAtiva(cliente)) return;

        System.out.print("Informe o novo limite: R$");
        double limite = Double.parseDouble(in.nextLine());

        cliente.setLimite(limite);
        System.out.printf("Limite da conta %s alterado para R$%.2f\n", cliente.getConta(), cliente.getLimite());
    }

    public void tranfere(Scanner in) throws BuscaContaException {
        System.out.print("[Origem] ");
        Cliente clienteOrigem = buscaConta(in);
        if (!checkContaAtiva(clienteOrigem)) return;
        System.out.print("[Destino] ");
        Cliente clienteDestino = buscaConta(in);
        if (!checkContaAtiva(clienteDestino)) return;
        System.out.print("Informe a quantia a ser transferida: R$");
        double valor = Double.parseDouble(in.nextLine());

        if (clienteOrigem.getSaldo() >= valor) {
            clienteOrigem.setSaldo(clienteOrigem.getSaldo() - valor);
            clienteDestino.setSaldo(clienteDestino.getSaldo() + valor);
            System.out.printf("Valor de R$%.2f transferido com sucesso!\n", valor);
        }
        else
            System.out.println("Valor não disponível em saldo na conta de origem.");
    }

    public void adicionaSaldo(Scanner in) throws BuscaContaException {
        Cliente cliente = buscaConta(in);
        if (!checkContaAtiva(cliente)) return;
        System.out.print("Informe o valor: R$");
        double valor = Double.parseDouble(in.nextLine());

        cliente.setSaldo(cliente.getSaldo() + valor);
        System.out.printf("Valor de R$%.2f adicionado à conta %s!\n", valor, cliente.getConta());
    }

    public void relatorioClientes(boolean ativos) {
        System.out.printf("# ===== Relatório de Clientes%s ===== #\n", ativos ? "" : " Removidos");
        for (int i = 0; i < MAX_CLIENTES; i++) {
            ClientePf clientePf = (ClientePf) clientesPf[i];
            ClientePj clientePj = (ClientePj) clientesPj[i];

            if (clientePf != null && ativos == clientePf.isContaAtiva()) {
                System.out.println("(PF) " + clientePf.getNome());
                System.out.printf("\tConta nº: %s\n", clientePf.getConta());
            }

            if (clientePj != null && ativos == clientePj.isContaAtiva()) {
                System.out.println("(PJ) " + clientePj.getRazaoSocial());
                System.out.printf("\tConta nº: %s\n", clientePj.getConta());
            }
        }
        System.out.printf("# =================================%s #\n", ativos ? "" : "==========");
    }

    public void restauraCliente(Scanner in) throws BuscaContaException {
        Cliente cliente = buscaConta(in);

        if (!cliente.isContaAtiva()) {
            cliente.setContaAtiva(true);
            System.out.printf("Conta %s restaurada com sucesso!\n", cliente.getConta());
        }
        else
            System.out.println("Essa conta já está ativa!");
    }

    private Cliente buscaConta(Scanner in) throws BuscaContaException {
        System.out.print("Informe o número da conta: ");
        String conta = in.nextLine();

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

    private boolean checkContaAtiva(Cliente cliente) {
        if (!cliente.isContaAtiva()) {
            System.out.println("A conta informada foi removida!");
            System.out.println("Acesse o menu de contas removidas para mais detalhes.");
            return false;
        } else
            return true;
    }
}

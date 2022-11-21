import models.Gerente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Gerente gerente = new Gerente();

        int opcao = 0;
        do {
            try {
                mostraMenu();
                opcao = Integer.parseInt(in.nextLine());

                switch (opcao) {
                    case 1 -> {
                        mostraTipoContas();
                        opcao = Integer.parseInt(in.nextLine());

                        if (opcao == 1 || opcao == 2)
                            gerente.cadastraCliente(in, opcao);
                    }
                    case 2 -> gerente.removeCliente(solicitaConta(in), in);
                    case 3 -> gerente.consultaCliente(solicitaConta(in));
                    case 4 -> {
                        String conta = solicitaConta(in);
                        System.out.print("Informe o novo limite: R$");
                        double limite = Double.parseDouble(in.nextLine());

                        gerente.ajustaLimite(conta, limite);
                    }
                    case 5 -> {
                        System.out.print("[ Origem ] ");
                        String contaOrigem = solicitaConta(in);
                        System.out.print("[ Destino ] ");
                        String contaDestino = solicitaConta(in);
                        System.out.print("Informe a quantia a ser transferida: R$");
                        double valor = Double.parseDouble(in.nextLine());

                        gerente.tranfere(contaOrigem, contaDestino, valor);
                    }
                    case 6 -> {
                        String conta = solicitaConta(in);
                        System.out.print("Informe o valor: R$");
                        double valor = Double.parseDouble(in.nextLine());

                        gerente.adicionaSaldo(conta, valor);
                    }
                    case 7 -> gerente.relatorioClientes();
                    case 8 -> System.out.println("Obrigado por utilizar o sistema, até logo!");
                    default -> System.out.println("Opção inválida, por favor tente novamente!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (opcao != 8);
    }

    public static void mostraMenu() {
        System.out.println("# ===== Sistema Bancário ===== #");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Remover cliente");
        System.out.println("3. Consultar cliente");
        System.out.println("4. Ajustar limite");
        System.out.println("5. Realizar transferência");
        System.out.println("6. Adicionar saldo");
        System.out.println("7. Relatório de clientes");
        System.out.println("8. Sair");
        System.out.println("# ============================ #");
        System.out.print("Insira uma opção: ");
    }

    public static void mostraTipoContas() {
        System.out.println("# ===== Sistema Bancário ===== #");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.println("3. Cancelar");
        System.out.println("# ============================ #");
        System.out.print("Insira uma opção: ");
    }

    public static String solicitaConta(Scanner in) {
        System.out.print("Informe o número da conta: ");
        return in.nextLine();
    }
}
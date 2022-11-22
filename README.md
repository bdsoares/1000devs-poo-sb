# Sistema Bancário

A proposta do projeto é modelar e escrever um software, usando todos os conceitos já trabalhados de orientação a objetos abordados no programa 1000 devs 2022 (encapsulamento, herança, composição, classes abstratas e interfaces).
## Sobre

Modele um sistema bancário, através do qual o gerente vai ser capaz de gerenciar os seus clientes.

Nesse sistema os clientes podem ser tanto de pessoa física quanto de pessoa jurídica (PF e PJ).


Os clientes, independente do tipo, tem um número de conta, agência, telefone, saldo e limite de cheque especial.

Os clientes PJ tem no seu cadastro o cnpj, nomes dos sócios(até 3), razão social e nome fantasia.

Os clientes PF tem no seu cadastro o cpf, nome e idade.

O gerente é capaz de cadastrar novos clientes (ler todas as informações do teclado);

Remover clientes de sua carteira pelo número de conta (ler o número do teclado);

Consultar cliente pelo número da conta (ler o número do teclado);

Aumentar e diminuir o limite do cheque especial do cliente (ler todas as informações do teclado);

Fazer transferências entre seus clientes (somente se o cliente que transfere tiver saldo) (ler todas as informações do teclado);

Adicionar saldo a um cliente (somente valores positivos) (ler todas as informações do teclado);

Imprimir um relatório com todos os seus clientes (em tela);


## Requisitos

- Todas essas operações devem estar contidas num menu em seu programa principal e devem permitir a entrada de dados pelo teclado.
- Nao é permitido o uso de Collections (arraylist, set, map), utilize um (ou mais) vetor puro de 50 posições;
- Em caso de falha em alguma operação o sistema deverá imprimir um indicativo em tela dessa falha
- Desenvolver uma funcionalidade extra para o gerente (use sua criatividade).
- O gerente pode ser abstraído por funções no programa principal ou para uma classe, pense na abordagem mais adequada para sua solução, só deverá existir um gerente.

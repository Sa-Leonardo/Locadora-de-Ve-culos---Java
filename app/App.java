package app;

import facade.BancoFacade;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.cliente.Cliente;
import model.carro.Carro;
import model.locacao.Locacao;
import facade.BancoException;
import repository.RepositoryException;

class App {

  private static BancoFacade facade;
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    try {
      System.out.println("Carregando sistema...");
      facade = BancoFacade.getInstance();

      CriaDadosDeTeste();

      int opcao;
      do {
        limpaTela();
        System.out.println("MENU PRINCIPAL");
        System.out.println("==== =========");
        System.out.println();
        System.out.println("<1> Cadastro Clientes");
        System.out.println("<2> Cadastro Carros");
        System.out.println("<3> Locação");
        System.out.println("<0> Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");

        try {
          opcao = Integer.valueOf(scanner.nextLine());
        } catch (Exception e) {
          opcao = 0;
        }

        switch (opcao) {
          case 0:
            limpaTela();
            break;
          case 1:
            cadastroClientes();
            break;
          case 2:
            cadastroCarros();
            break;
          case 3:
            locacao();
            break;
        }
      } while (opcao != 0);

      facade.exit();
    } catch (BancoException e) {
      System.err.println("Erro ao carregar dados");
      e.printStackTrace();
    }
    System.out.println("Programa terminado");
  }

  private static void limpaTela() {
    for (int i = 0; i < 25; i++) {
      System.out.println();
    }
  }

  private static void cadastroClientes() {
    int opcao;
    do {
      limpaTela();
      System.out.println("CADASTRO CLIENTES");
      System.out.println("======== ========");
      System.out.println();
      System.out.println("<1> Incluir Cliente");
      System.out.println("<2> Alterar Cliente");
      System.out.println("<3> Excluir Cliente");
      System.out.println("<4> Listar Clientes");
      System.out.println("<0> Menu Principal");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(scanner.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          incluirCliente();
          break;
        case 2:
          alterarCliente();
          break;
        case 3:
          excluirCliente();
          break;
        case 4:
          listarCliente();
          break;
      }
    } while (opcao != 0);
  }

  private static void incluirCliente() {
    limpaTela();
    System.out.println("Cadastro de Cliente");
    System.out.println("===================");
    System.out.print("CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("CNH: ");
    String cnh = scanner.nextLine();
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Endereço: ");
    String endereco = scanner.nextLine();

    System.out.print("Telefone: ");
    String fone = scanner.next();

    Cliente cliente = new Cliente(cpf, cnh, nome, endereco, fone);

    try {
      facade.inserirCliente(cliente);
      System.out.println("Cliente cadastrado!");
    } catch (RepositoryException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
    scanner.nextLine();
  }

  private static void listarCliente() {
    limpaTela();
    List<Cliente> clientes = facade.getAllClientes();
    System.out.printf("CPF            CNH        Nome                 Endereço Telefone\n");
    System.out.printf("============== ==================== ==== ===============\n");
    for (Cliente cliente : clientes) {
      System.out.printf("%14s ", cliente.getCpf());
      System.out.printf("%14s ", cliente.getCnh());
      System.out.printf("%-20s ", cliente.getNome());
      System.out.printf("%s ", cliente.getEndereco());
      System.out.printf("%15s\n", cliente.getTelefone());
    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void excluirCliente() {
    limpaTela();
    System.out.println("Excluir de Cliente");
    System.out.println("==================");
    System.out.print("CPF: ");
    String cpf = scanner.nextLine();

    try {
      Cliente cliente = facade.buscarCliente(cpf);
      System.out.println();
      System.out.println("CNH: " + cliente.getCnh());
      System.out.println("Nome: " + cliente.getNome());
      System.out.println("Endereço: " + cliente.getEndereco());
      System.out.println("Telefone: " + cliente.getTelefone());
      System.out.println();

      System.out.print("Exclui esse cliente? (s/n)?");
      String resposta = scanner.nextLine();

      if (resposta.equalsIgnoreCase("s")) {
        facade.excluirCliente(cliente);
        System.out.println("Cliente excluído!");
      }

    } catch (RepositoryException | BancoException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void alterarCliente() {
    limpaTela();
    System.out.println("Alterar de Cliente");
    System.out.println("==================");
    System.out.print("CPF: ");
    String cpf = scanner.nextLine();

    try {
      Cliente cliente = facade.buscarCliente(cpf);

      System.out.println();
      System.out.println("CNH: " + cliente.getCnh());
      System.out.println("Nome: " + cliente.getNome());
      System.out.print("Nome (<enter> = Não alterar): ");
      String nome = scanner.nextLine();
      if (!nome.equals("")) {
        cliente.setNome(nome);
      }

      System.out.println("Endereco: " + cliente.getEndereco());
      System.out.print("Endereço (<enter> = Não alterar): ");
      String endereco = scanner.nextLine();
      if (!endereco.equals("")) {
        cliente.setEndereco(endereco);
      }

      System.out.println("Telefone: " + cliente.getTelefone());
      System.out.print("Telefone (<enter> = Não alterar): ");
      String fone = scanner.nextLine();
      if (!fone.equals("")) {
        cliente.setTelefone(fone);
      }

      System.out.println();

      facade.alterarCliente(cliente);
      System.out.println("Cliente Alterado!");
      System.out.println();

    } catch (RepositoryException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void cadastroCarros() {
    int opcao;
    do {
      limpaTela();
      System.out.println("CADASTRO CARROS");
      System.out.println("======== ========");
      System.out.println();
      System.out.println("<1> Incluir Carros");
      System.out.println("<2> Alterar Carros");
      System.out.println("<3> Excluir Carros");
      System.out.println("<4> Listar Carros");
      System.out.println("<0> Menu Principal");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(scanner.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          incluirCarros();
          break;
        case 2:
          alterarCarros();
          break;
        case 3:
          excluirCarros();
          break;
        case 4:
          listarCarros();
          break;
      }
    } while (opcao != 0);
  }

  private static void incluirCarros() {
    limpaTela();
    System.out.println("Cadastro de Carros");
    System.out.println("===================");
    System.out.print("Placa: ");
    String placa = scanner.nextLine();
    System.out.print("Cor: ");
    String cor = scanner.nextLine();
    System.out.print("Modelo: ");
    String modelo = scanner.nextLine();
    System.out.print("Ano: ");
    int ano = scanner.nextInt();
    System.out.print("Diária:");
    double diaria = scanner.nextDouble();

    Carro carro = new Carro(placa, cor, modelo, ano, diaria);// passando para classe Carro

    try {
      facade.inserirCarros(carro);
      System.out.println("Carro cadastrado!");
    } catch (RepositoryException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
    scanner.nextLine();
  }

  private static void listarCarros() {
    limpaTela();
    List<Carro> carros = facade.getAllCarros();
    System.out.printf("Placa          Cor            Modelo       Ano      Diária\n");
    System.out.printf("============== ============== ============ ======== ========\n");
    for (Carro carro : carros) {
      System.out.printf("%s ", carro.getPlaca());
      System.out.printf("%s ", carro.getCor());
      System.out.printf("%s ", carro.getModelo());
      System.out.printf("%d\n", carro.getAno());
      System.out.printf("R$%.2f\n", carro.getDiaria());
    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void excluirCarros() {
    limpaTela();
    System.out.println("Excluir de Cliente");
    System.out.println("==================");
    System.out.print("Placa: ");
    String placa = scanner.nextLine();

    try {
      Carro carro = facade.buscarCarros(placa);
      System.out.println();

      System.out.println("Cor: " + carro.getCor());
      System.out.println("Modelo: " + carro.getModelo());
      System.out.println("Ano: " + carro.getAno());
      System.out.println("Diária: " + carro.getDiaria());
      System.out.println();

      System.out.print("Exclui esse carro? (s/n)?");
      String resposta = scanner.nextLine();

      if (resposta.equalsIgnoreCase("s")) {
        facade.excluirCarros(carro);
        System.out.println("Carro excluído!");
      }

    } catch (RepositoryException | BancoException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void alterarCarros() {
    limpaTela();
    System.out.println("Alterar Carro");
    System.out.println("==================");
    System.out.print("Placa: ");
    String placa = scanner.nextLine();

    try {
      Carro carro = facade.buscarCarros(placa);

      System.out.println();
      System.out.println("Cor: " + carro.getCor());
      System.out.print("Cor (<enter> = Não alterar): ");
      String cor = scanner.nextLine();
      if (!cor.equals("")) {
        carro.setCor(cor);
      }
      System.out.println();
      System.out.println("Modelo: " + carro.getModelo());
      System.out.print("Modelo (<enter> = Não alterar): ");
      String modelo = scanner.nextLine();
      if (!modelo.equals("")) {
        carro.setModelo(modelo);
      }

      System.out.println();
      System.out.println("Ano: " + carro.getAno());
      System.out.print("Ano (<enter> = Não alterar): ");
      String input = scanner.nextLine();

      if (!input.isEmpty()) {
        int ano = Integer.parseInt(input);
        carro.setAno(ano);
      }
      System.out.println();
      System.out.println("Diaria: " + carro.getDiaria());
      System.out.print("Diaria (<enter> = Não alterar): ");
      String input2 = scanner.nextLine();
      if (!input2.isEmpty()) {
        double diaria = Double.parseDouble(input2);
        carro.setDiaria(diaria);
      }

      System.out.println();

      facade.alterarCarros(carro);
      System.out.println("Carro Alterado!");
      System.out.println();

    } catch (RepositoryException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void locacao() {

    /*
     * try {
     * limpaTela();
     * System.out.println("LOCAÇÃO");
     * System.out.println("================");
     * System.out.println();
     * System.out.print("ID da Locação: ");
     * String numeroConta = scanner.nextLine();
     * }
     */
    int opcao;
    do {
      limpaTela();
      System.out.println("LOCAÇÃO");
      System.out.println("================");
      System.out.println();
      System.out.println("<1> Alugar Carro");
      System.out.println("<2> Alterar locação");
      System.out.println("<3> Devolução");
      System.out.println("<4> Carros Disponíveis");
      System.out.println("<5> Listar Locação");
      System.out.println("<0> Menu Principal");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(scanner.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          alugarCarro();
          break;
        case 2:
          alterarLocacao();
          break;
        case 3:
          devolucao();
          break;
        case 4:
          carrosDisponiveis();
          break;
        case 5:
          listarLocacao();
          break;
      }
    } while (opcao != 0);
  }

  private static void alugarCarro() {
    limpaTela();
    System.out.println("ALUGAR CARRO");
    System.out.println("===================");
    /*
     * String carroInput = scanner.nextLine();
     * System.out.print("Nome do cliente: ");
     * String clienteInput = scanner.nextLine();
     * System.out.print("Status: ");
     * String status = scanner.nextLine();
     * System.out.print("Data de hoje: ");
     * String dataLoc = scanner.nextLine();
     * System.out.print("Data de devolução:");
     * String dataPrev = scanner.nextLine();
     * 
     * Locacao locacao = new Locacao(carroInput, clienteInput, status, dataLoc,
     * dataPrev);
     */

    try {
      System.out.println("Digite o CPF:");
      String cpf = scanner.nextLine();
      Cliente cliente = facade.buscarCliente(cpf);
      System.out.println("Cliente: " + cliente.getNome());
      System.out.println("Digite a placa:");
      String placa = scanner.nextLine();
      Carro carro = facade.buscarCarros(placa);
      System.out.println("Carro: " + carro.getModelo());
      System.out.print("Data de devolução:");
      String dataPrev = scanner.nextLine();

      //falta inserir a locação
      //tentei assim, a classe da locação é abstrata e não pode ser instanciada diretamente
      //facade.inserirLocacao(new Cliente(inserirCliente(facade.buscarCliente(cpf))), new Carro(inserirCarros(facade.buscarCarros(placa)), dataPrev));
      
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    System.out.println("tecle <enter> para voltar");
    scanner.nextLine();
  }

  private static void alterarLocacao() {
    limpaTela();
  }

  private static void devolucao() {
    limpaTela();
  }

  public static void carrosDisponiveis() {
    limpaTela();
  }

  public static void listarLocacao() {
    limpaTela();
    try {
      System.out.println("Digite o CPF:");
      String cpf = scanner.nextLine();
      Cliente cliente = facade.buscarCliente(cpf);
      System.out.println("Cliente: " + cliente.getNome());
    } catch (Exception e) {

    }

  }

  private static void CriaDadosDeTeste() {
    try {

      facade.inserirCliente(new Cliente("111.111.111-11", "1213141516", "João Batista", "mararu", "99111-1111"));

      facade.inserirCliente(new Cliente("222.222.222-22", "9988776655", "Paula Leite", "jacamin", "99222-2222"));

      facade.inserirCarros(new Carro("ABCD-123", "Vermelho", "Prisma", 2018, 140.00));

      facade.inserirCarros(new Carro("EFGH-456", "Preto", "Palio", 2010, 100.00));

    } catch (RepositoryException e) {
      System.out.println(e.getMessage());
    }
  }
}
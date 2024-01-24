package facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.cliente.Cliente;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteLista;

import model.carro.Carro;
import repository.carro.PlacaJaCadastradaException;
import repository.carro.CarroNaoCadastradoException;
import repository.carro.RepositorioCarro;
import repository.carro.RepositorioCarroLista;

import model.locacao.Locacao;
import repository.locacao.LocacaoJaCadastradaException;
import repository.locacao.LocacaoNaoCadastradaException;
import repository.locacao.RepositorioLocacao;
import repository.locacao.RepositorioLocacaoLista;


public class BancoFacade {

  // Arquivo para salvar os dados
  private final File FILE = new File("banco.dat");

  private RepositorioCliente repositorioCliente;
  private RepositorioCarro repositorioCarro;
  private RepositorioLocacao repositorioLocacao;

  /* Padrão de Projeto Singleton */

  // Atributo estático que vai manter a única
  // instância da classe
  private static BancoFacade instance = null;

  // Contrutor deve ser privado garantindo que não
  // pode ser chamado de fora da classe
  private BancoFacade() throws BancoException {
    if (FILE.exists()) {
      loadData();
    } else {
      repositorioCliente = new RepositorioClienteLista();
      repositorioCarro = new RepositorioCarroLista();
      repositorioLocacao = new RepositorioLocacaoLista();
    }
  }

  // Método getInstance, responsável por fornecer a única
  // instância da classe
  public static BancoFacade getInstance() throws BancoException {

    if (instance == null) {
      instance = new BancoFacade();
    }

    return instance;
  }

  public void inserirCliente(Cliente cliente) throws CPFJaCadastradoException {
    repositorioCliente.inserirCliente(cliente);
  }

  public void alterarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
    repositorioCliente.alterarCliente(cliente);
  }

  public Cliente buscarCliente(String cpf) throws ClienteNaoCadastradoException {
    return repositorioCliente.buscarCliente(cpf);
  }

  public void excluirCliente(Cliente cliente) throws BancoException, ClienteNaoCadastradoException {
    repositorioCliente.deletarCliente(cliente);
  }

  public List<Cliente> getAllClientes() {
    return repositorioCliente.getAll();
  }

  public void inserirCarros(Carro carro) throws PlacaJaCadastradaException {
    repositorioCarro.inserirCarros(carro);
  }

  public void alterarCarros(Carro carro) throws CarroNaoCadastradoException {
    repositorioCarro.alterarCarros(carro);
  }

  public Carro buscarCarros(String placa) throws CarroNaoCadastradoException {
    return repositorioCarro.buscarCarros(placa);
  }

  public void excluirCarros(Carro carro) throws BancoException, CarroNaoCadastradoException {
    repositorioCarro.deletarCarros(carro);
  }

  public List<Carro> getAllCarros() {
    return repositorioCarro.getAll();
  }

  public void inserirLocacao(Locacao locacao) throws LocacaoJaCadastradaException {
    repositorioLocacao.inserirLocacao(locacao);
  }

  public void alterarLocacao(Locacao locacao) throws LocacaoNaoCadastradaException {
    repositorioLocacao.alterarLocacao(locacao);
  }

  public Locacao buscarLocacao(String numero) throws LocacaoNaoCadastradaException {
    return repositorioLocacao.buscarLocacao(numero);
  }

  public void excluirLocacao(Locacao locacao) throws BancoException, LocacaoNaoCadastradaException {
    repositorioLocacao.deletarLocacao(locacao);
  }

  public List<Locacao> getAllLocacoes() {
    return repositorioLocacao.getAll();
  }

  private void loadData() throws BancoException {
    try {
      FileInputStream f = new FileInputStream(FILE);
      ObjectInputStream o = new ObjectInputStream(f);

      repositorioCliente = (RepositorioCliente) o.readObject();
      repositorioCarro = (RepositorioCarro) o.readObject();
      repositorioLocacao = (RepositorioLocacao) o.readObject();

      o.close();
      f.close();
    } catch (Exception e) {
      throw new BancoException("Erro ao carregar dados do arquivo", e);
    }
  }

  public void exit() throws BancoException {
    try {
      FileOutputStream f = new FileOutputStream(FILE);
      ObjectOutputStream o = new ObjectOutputStream(f);

      // Salvar meus dados
      o.writeObject(repositorioCliente);
      o.writeObject(repositorioCarro);
      o.writeObject(repositorioLocacao);

      o.close();
      f.close();
    } catch (IOException e) {
      throw new BancoException("Erro ao gravar dados no arquivo", e);
    }
  }

}
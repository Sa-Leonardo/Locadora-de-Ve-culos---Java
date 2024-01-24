package repository.carro;

import model.carro.Carro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCarroLista implements RepositorioCarro, Serializable {

  List<Carro> carros;

  public RepositorioCarroLista() {
    this.carros = new ArrayList<Carro>();
  }

  @Override
  public void inserirCarros(Carro carro) throws PlacaJaCadastradaException {
    try {
      buscarCarros(carro.getPlaca());
      throw new PlacaJaCadastradaException();
    } catch (CarroNaoCadastradoException ex) {
      carros.add(carro);
    }
  }

  @Override
  public void alterarCarros(Carro carro) throws CarroNaoCadastradoException {
    // Em memória, não há necessidade de atualizar objeto
    buscarCarros(carro.getPlaca());
  }

  @Override
  public void deletarCarros(Carro carro) throws CarroNaoCadastradoException {
    if (!carros.remove(carro)) {
      throw new CarroNaoCadastradoException();
    }
  }

  @Override
  public Carro buscarCarros(String placa) throws CarroNaoCadastradoException {

    for (Carro carro : carros) {
      if (carro.getPlaca().equals(placa)) {
        return carro;
      }
    }
    throw new CarroNaoCadastradoException();

  }

  @Override
  public List<Carro> getAll() {
    return new ArrayList<>(carros);
  }

}

package repository.carro;

import repository.RepositoryException;

public class PlacaJaCadastradaException extends RepositoryException {

  public PlacaJaCadastradaException() {
    super("Placa já cadastrada");
  }
}
package repository.carro;

import model.carro.Carro;
import java.util.List;

public interface RepositorioCarro {

    void inserirCarros(Carro carro) throws PlacaJaCadastradaException;

    void alterarCarros(Carro carro) throws CarroNaoCadastradoException;

    void deletarCarros(Carro carro) throws CarroNaoCadastradoException;

    Carro buscarCarros(String placa) throws CarroNaoCadastradoException;

    List<Carro> getAll();
    
}
package repository.carro;

import repository.RepositoryException;

public class CarroNaoCadastradoException extends RepositoryException {

    public CarroNaoCadastradoException() {
        super("Carro não cadastrado");
    }
    
    
}
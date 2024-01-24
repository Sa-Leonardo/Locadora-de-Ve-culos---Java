package repository.cliente;

import repository.RepositoryException;

public class ClienteNaoCadastradoException extends RepositoryException {

    public ClienteNaoCadastradoException() {
        super("Cliente não cadastrado");
    }
    
    
}

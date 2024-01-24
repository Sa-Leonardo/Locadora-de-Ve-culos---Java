package repository.cliente;

import repository.RepositoryException;

public class CPFJaCadastradoException extends RepositoryException {

    public CPFJaCadastradoException() {
        super("CPF já cadastrado");
    }
    
    
}

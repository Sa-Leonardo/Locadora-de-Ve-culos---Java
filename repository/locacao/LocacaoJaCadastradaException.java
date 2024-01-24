package repository.locacao;

import repository.RepositoryException;

public class LocacaoJaCadastradaException extends RepositoryException {

    public LocacaoJaCadastradaException() {
        super("Locação já cadastrada");
    }
    
    
}
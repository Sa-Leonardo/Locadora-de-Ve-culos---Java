package repository.locacao;

import repository.RepositoryException;

public class LocacaoNaoCadastradaException extends RepositoryException {

    public LocacaoNaoCadastradaException() {
        super("Locação não cadastrada");
    } 
}
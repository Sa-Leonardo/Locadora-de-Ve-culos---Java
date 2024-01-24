package repository.locacao;

import model.locacao.Locacao;
import java.util.List;

public interface RepositorioLocacao {

    Locacao inserirLocacao(Locacao locacao) throws LocacaoJaCadastradaException;

    void alterarLocacao(Locacao locacao) throws LocacaoNaoCadastradaException;

    void deletarLocacao(Locacao locacao) throws LocacaoNaoCadastradaException;

    Locacao buscarLocacao(String numero) throws LocacaoNaoCadastradaException;

    List<Locacao> getAll();
    
}
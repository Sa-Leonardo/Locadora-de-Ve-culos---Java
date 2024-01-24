package repository.locacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.locacao.Locacao;
import pattern.IdGeneratorStrategy;


public class RepositorioLocacaoLista implements RepositorioLocacao, Serializable {
  
    List<Locacao> locacoes;
    IdGeneratorStrategy idGenerator;

  
    public RepositorioLocacaoLista() {
        locacoes = new ArrayList<>();
        idGenerator = new SequencialLocacaoIdGeneratorStrategy();
    }

  @Override
  public Locacao inserirLocacao(Locacao locacao) throws LocacaoJaCadastradaException {
    if(locacao.getNumero() == null){
      locacao.setNumero(idGenerator.nextId());
    }
    
    try {
      while(buscarLocacao(locacao.getNumero()) != null){
        locacao.setNumero(idGenerator.nextId());
      }
    } catch (LocacaoNaoCadastradaException ex) {
      locacoes.add(locacao);
    }
    return locacao;
  }

  @Override
  public void alterarLocacao(Locacao locacao) throws LocacaoNaoCadastradaException {
    // Em memória, não há necessidade de atualizar objeto
  }

  @Override
  public void deletarLocacao(Locacao locacao) throws LocacaoNaoCadastradaException {
    if (! locacoes.remove(locacao)) {
      throw new LocacaoNaoCadastradaException();
    }
  }

  @Override
  public Locacao buscarLocacao(String numero) throws LocacaoNaoCadastradaException {

    for (Locacao locacao : locacoes) {
      if (locacao.getNumero().equals(numero)) {
        return locacao;
      }
    }
    throw new LocacaoNaoCadastradaException();

  }

  @Override
  public List<Locacao> getAll() {
    return new ArrayList<>(locacoes);
  }

public List<Locacao> getAll(String cpf) {
        List<Locacao> lista = new ArrayList<>();
        for (Locacao locacao : locacoes) {
            if (locacao.getCliente().getCpf().equals(cpf)) {
                lista.add(locacao);
            }
        }
        return lista;
  }
}
package repository.locacao;

import java.io.Serializable;

import pattern.IdGeneratorStrategy;

public class SequencialLocacaoIdGeneratorStrategy implements IdGeneratorStrategy, Serializable {

  private int nextNumber;

  public SequencialLocacaoIdGeneratorStrategy() {
    this.nextNumber = 1;
  }
  
  public String nextId() {
    return String.valueOf(nextNumber++);
  }

}
package model.cliente;

import java.io.Serializable;

public class Cliente implements Serializable {

  private String cpf;
  private String cnh;
  private String nome;
  private String endereco;
  private String telefone;

  public Cliente(String cpf, String cnh, String nome, String endereco, String telefone) {
    this.cpf = cpf;
    this.cnh = cnh;
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getCnh() {
    return this.cnh;
  }

  public void setCnh(String cnh) {
    this.cnh = cnh;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return this.endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String toString() {
    return nome;
  }
}
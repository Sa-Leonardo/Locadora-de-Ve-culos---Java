package model.locacao;

import java.io.Serializable;

import model.carro.Carro;
import model.cliente.Cliente;


public abstract class Locacao implements Serializable{
  private String numero;
  private Cliente cliente;
  private Carro carro;
  private String dataPrev;

  public Locacao(Cliente cliente, Carro carro, String dataPrev, String numero) {
    this.numero = numero;
    this.cliente = cliente;
    this.carro = carro;
    this.dataPrev = dataPrev;
  }
  public String getNumero(){
    return numero;
  }
  public void setNumero(String numero){
    this.numero = numero;
  }
  public Carro getCarro() {
    return this.carro;
  }

  public void setCarro(Carro carro) {
    this.carro = carro;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getDataPrev() {
    return this.dataPrev;
  }

  public void setDataPrev(String dataPrev) {
    this.dataPrev = dataPrev;
  }

  public void calcDiaria(double diaria) {

  }

  public void renderJuros(double diaria, String dataPrev, String dataDev) {
    
  }

  public void devolucao() {

  }

  public String toString() {
    return String.format("Cliente: %s\nCarro: %s\nPlaca: %s\n Data de Locação: %s\nData de Devolução: %s",
        cliente.getNome(), carro.getModelo(), carro.getPlaca(), dataPrev);
  }
}
// calculo diaria
// render juros
// status
// data de locacao
// data prevista devolucao
// data devolucao
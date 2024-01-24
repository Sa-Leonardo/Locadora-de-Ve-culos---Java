package model.carro;
import java.io.Serializable;
public class Carro {
  private String placa;
  private String cor;
  private String modelo;
  private int ano;
  private double diaria;
  
  public Carro(String placa, String cor, String modelo, int ano, double diaria) {
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;
        this.ano = ano;
        this.diaria = diaria;
  }

  public String getPlaca() {
    return this.placa;
  }
  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getCor() {
    return this.cor;
  }
  public void setCor(String cor) {
    this.cor = cor;
  }

  public String getModelo() {
    return this.modelo;
  }
  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public int getAno() {
    return this.ano;
  }
  public void setAno(int ano) {
    this.ano = ano;
  }

  public double getDiaria() {
    return this.diaria;
  }
  public void setDiaria(double diaria) {
    this.diaria = diaria;
  }
  
}
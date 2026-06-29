package sistema_veiculos;

public class Carro {
    private String modelo;
    private int velocidade;

    public Carro(String modelo, int velocidade) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo não pode ser vazio!");
        }
        if (velocidade < 0) {
            throw new IllegalArgumentException("A velocidade inicial não pode ser negativa!");
        }
        this.modelo = modelo;
        this.velocidade = velocidade;
    }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getVelocidade() { return velocidade; }
    
    public void setVelocidade(int velocidade) {
        if (velocidade < 0) {
            throw new IllegalArgumentException("A velocidade não pode ser negativa!");
        }
        this.velocidade = velocidade;
    }

    public void acelerar(int valor) {
        Validacao.validarAceleracao(valor);
        this.velocidade += valor;
    }

    public void visualizar() {
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Velocidade atual: " + this.velocidade + " km/h");
    }
}
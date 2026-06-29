package sistema_veiculos;

public class CarroEletrico extends Carro {
    
    public static final int CONSUMO_POR_ACELERACAO = 2;
    private int nivelBateria;

    public CarroEletrico(String modelo, int velocidade, int nivelBateria) {
        super(modelo, velocidade);
        Validacao.validarNivelBateria(nivelBateria);
        this.nivelBateria = nivelBateria;
    }

    public int getNivelBateria() { 
    	return nivelBateria; }

    public void setNivelBateria(int nivelBateria) {
        Validacao.validarNivelBateria(nivelBateria);
        this.nivelBateria = nivelBateria;
    }

    @Override
    public void acelerar(int valor) {
        Validacao.validarAceleracao(valor);
        int consumo = valor * CONSUMO_POR_ACELERACAO;
        Validacao.validarBateriaInsuficiente(consumo, this.nivelBateria);
        this.nivelBateria -= consumo;
        super.acelerar(valor);
    }

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println("Bateria restante: " + this.nivelBateria + "%");
    }
}
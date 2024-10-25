package model;

public class Ponto {
    private int pontoId;
    private int funcionarioId;
    private String dataRegistro;
    private String horaEntrada;
    private String horaSaida;
    private String horasTrabalhadas;

    // Construtores
    public Ponto() {
    }

    public Ponto(int pontoId, int funcionarioId, String dataRegistro, 
                 String horaEntrada, String horaSaida, String horasTrabalhadas) {
        this.pontoId = pontoId;
        this.funcionarioId = funcionarioId;
        this.dataRegistro = dataRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    // Getters e Setters
    public int getPontoId() {
        return pontoId;
    }

    public void setPontoId(int pontoId) {
        this.pontoId = pontoId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(String horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }
}
package model;

import java.sql.Timestamp;

public class Ponto {
    private int pontoId;
    private int funcionarioId;
    private java.sql.Date dataRegistro;
    private Timestamp horaEntrada;
    private Timestamp horaSaida;
    private double horasTrabalhadas;
    private boolean atraso;

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

    public java.sql.Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(java.sql.Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Timestamp horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Timestamp getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Timestamp horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public boolean isAtraso() {
        return atraso;
    }

    public void setAtraso(boolean atraso) {
        this.atraso = atraso;
    }
}
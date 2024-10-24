package controller;

import model.Funcionario;
import model.Ponto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Scanner;

public class MenuInserir {
    public static void menuInserir(Scanner teclado, Connection connection) {
        Funcionario funcionario = new Funcionario();

        System.out.println("Inserir Funcionário");
        System.out.print("Nome: ");
        funcionario.setNome(teclado.next());
        System.out.print("Sexo (M/F): ");
        funcionario.setSexo(teclado.next().charAt(0));
        System.out.print("Idade: ");
        funcionario.setIdade(teclado.nextInt());
        System.out.print("Telefone: ");
        funcionario.setTelefone(teclado.next());
        System.out.print("CPF: ");
        funcionario.setCpf(teclado.next());
        System.out.print("Cargo: ");
        funcionario.setCargo(teclado.next());
        System.out.print("Setor: ");
        funcionario.setSetor(teclado.next());

        String insertFuncionarioSQL = "INSERT INTO Funcionario (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor) "
                + "VALUES (funcionario_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertFuncionarioSQL, new String[]{"funcionario_id"})) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, String.valueOf(funcionario.getSexo()));
            pstmt.setInt(3, funcionario.getIdade());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setString(5, funcionario.getCpf());
            pstmt.setString(6, funcionario.getCargo());
            pstmt.setString(7, funcionario.getSetor());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                funcionario.setFuncionarioId(rs.getInt(1));
            }

            System.out.println("Funcionário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
            return; 
        }

        Ponto ponto = new Ponto();
        System.out.println("Inserir Registro de Ponto");

        System.out.print("Data (YYYY-MM-DD): ");
        ponto.setDataRegistro(java.sql.Date.valueOf(teclado.next()));

        System.out.print("Hora de Entrada (HH:MM:SS): ");
        String horaEntradaInput = teclado.next();
        ponto.setHoraEntrada(Timestamp.valueOf(ponto.getDataRegistro() + " " + horaEntradaInput));

        System.out.print("Hora de Saída (HH:MM:SS): ");
        String horaSaidaInput = teclado.next();
        ponto.setHoraSaida(Timestamp.valueOf(ponto.getDataRegistro() + " " + horaSaidaInput));

        Duration duracao = Duration.between(ponto.getHoraEntrada().toLocalDateTime(), ponto.getHoraSaida().toLocalDateTime());
        ponto.setHorasTrabalhadas(duracao.toHours());

        String insertPontoSQL = "INSERT INTO Ponto (ponto_id, funcionario_id, data_registro, hora_entrada, hora_saida, horas_trabalhadas, atraso) "
                + "VALUES (ponto_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertPontoSQL)) {
            pstmt.setInt(1, funcionario.getFuncionarioId());
            pstmt.setDate(2, ponto.getDataRegistro());
            pstmt.setTimestamp(3, ponto.getHoraEntrada());
            pstmt.setTimestamp(4, ponto.getHoraSaida());
            pstmt.setDouble(5, ponto.getHorasTrabalhadas());
            pstmt.setString(6, "N"); // Supondo que não houve atraso

            pstmt.executeUpdate();
            System.out.println("Registro de Ponto inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir registro de ponto: " + e.getMessage());
        }
    }
}
package controller;

import model.Ponto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPonto {
    public static void menuPonto(Scanner teclado, Connection connection) {
        int opcao;
        do {
            System.out.println("\n=== Submenu de Cadastro de Ponto ===");
            System.out.println("1. Registrar Ponto");
            System.out.println("2. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = teclado.nextInt();
            
            switch (opcao) {
                case 1:
                    registrarPonto(teclado, connection);
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);
    }

    private static void registrarPonto(Scanner teclado, Connection connection) {
        Ponto ponto = new Ponto();

        System.out.print("\nInsira o ID do Colaborador: ");
        int funcionarioId = teclado.nextInt();
        teclado.nextLine();  

        System.out.print("Data (DD/MM/YYYY): ");
        ponto.setDataRegistro(teclado.nextLine());

        System.out.print("Hora de Entrada (HH:MM): ");
        ponto.setHoraEntrada(teclado.nextLine());

        System.out.print("Hora de Saída (HH:MM): ");
        ponto.setHoraSaida(teclado.nextLine());

        String[] horaEntrada = ponto.getHoraEntrada().split(":");
        String[] horaSaida = ponto.getHoraSaida().split(":");

        int horasEntrada = Integer.parseInt(horaEntrada[0]);
        int minutosEntrada = Integer.parseInt(horaEntrada[1]);
        int horasSaida = Integer.parseInt(horaSaida[0]);
        int minutosSaida = Integer.parseInt(horaSaida[1]);

        double totalHoras = (horasSaida - horasEntrada) + 
                            (minutosSaida - minutosEntrada) / 60.0;

        ponto.setHorasTrabalhadas(String.format("%.2f", totalHoras));

        String insertPontoSQL = "INSERT INTO Ponto (ponto_id, funcionario_id, data_registro, " +
                                "hora_entrada, hora_saida, horas_trabalhadas) " +
                                "VALUES (ponto_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertPontoSQL)) {
            pstmt.setInt(1, funcionarioId);
            pstmt.setString(2, ponto.getDataRegistro());
            pstmt.setString(3, ponto.getHoraEntrada());
            pstmt.setString(4, ponto.getHoraSaida());
            pstmt.setString(5, ponto.getHorasTrabalhadas());

            pstmt.executeUpdate();
            System.out.println("Registro de Ponto inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir registro de ponto: " + e.getMessage());
        }
    }
}

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuRelatorio {
    public static void menuRelatorio(Scanner teclado, Connection connection) {
        int opcao;
        
        do {
            System.out.println("\n=== Submenu de Relatório ===");
            System.out.println("1. Exibir relatório");
            System.out.println("2. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcao) {
                case 1:
                    exibirRelatorio(teclado, connection);
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);
    }

    private static void exibirRelatorio(Scanner teclado, Connection connection) {
        System.out.println("\n=== Relatório de Colaboradores e Pontos ===\n");

        String query = "SELECT f.funcionario_id, f.nome, f.telefone, p.data_registro, p.hora_entrada, p.hora_saida, p.horas_trabalhadas " +
                      "FROM Colaborador f LEFT JOIN Ponto p ON f.funcionario_id = p.funcionario_id";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.printf("%-15s %-30s %-20s %-15s %-15s %-15s %-20s%n", 
                            "ID", "Nome", "Telefone", "Data Registro", "Hora Entrada", "Hora Saída", "Horas Trabalhadas");
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int funcionarioId = rs.getInt("funcionario_id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String dataRegistro = rs.getString("data_registro");
                String horaEntrada = rs.getString("hora_entrada");
                String horaSaida = rs.getString("hora_saida");
                double horasTrabalhadas = rs.getDouble("horas_trabalhadas");

                System.out.printf("%-15d %-30s %-20s %-15s %-15s %-15s %-20.2f%n", 
                                funcionarioId, nome, telefone, dataRegistro, horaEntrada, horaSaida, horasTrabalhadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
        
        System.out.println("\nPressione Enter para voltar ao submenu...");
        teclado.nextLine();
    }
}

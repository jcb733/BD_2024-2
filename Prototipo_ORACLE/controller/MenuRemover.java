package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuRemover {
    public static void menuRemover(Scanner teclado, Connection connection) {
        int opcao;
        
        do {
            System.out.println("\n=== Submenu de Remoção ===");
            System.out.println("1. Remover Colaborador");
            System.out.println("2. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcao) {
                case 1:
                    removerFuncionario(teclado, connection);
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);
    }

    private static void removerFuncionario(Scanner teclado, Connection connection) {
        System.out.println("\n=== Remover Colaborador ===");
        System.out.print("Digite o ID do Colaborador: ");
        int id = teclado.nextInt();
        
        String deletePontoSQL = "DELETE FROM Ponto WHERE funcionario_id = ?";
        String deleteFuncionarioSQL = "DELETE FROM Colaborador WHERE funcionario_id = ?";
        
        try {
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement pstmtPonto = connection.prepareStatement(deletePontoSQL)) {
                    pstmtPonto.setInt(1, id);
                    pstmtPonto.executeUpdate();
                }
                
                try (PreparedStatement pstmtFuncionario = connection.prepareStatement(deleteFuncionarioSQL)) {
                    pstmtFuncionario.setInt(1, id);
                    int funcionariosRemovidos = pstmtFuncionario.executeUpdate();
                    
                    if (funcionariosRemovidos > 0) {
                        System.out.println("Colaborador e seus registros de ponto removidos com sucesso!");
                        connection.commit(); 
                    } else {
                        System.out.println("Colaborador não encontrado.");
                        connection.rollback(); 
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao remover registro: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\nPressione Enter para voltar ao submenu...");
        teclado.nextLine();
        teclado.nextLine();
    }
}

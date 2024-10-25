package controller;

import model.Colaborador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuAtualizar {
    public static void menuAtualizar(Scanner teclado, Connection connection) {
        int opcao;
        
        do {
            System.out.println("\n=== Submenu de Atualização ===");
            System.out.println("1. Atualizar dados do Colaborador");
            System.out.println("2. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcao) {
                case 1:
                    atualizarFuncionario(teclado, connection);
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);
    }

    private static void atualizarFuncionario(Scanner teclado, Connection connection) {
        System.out.println("\n=== Atualizar Dados do Colaborador ===");
        System.out.print("Digite o ID do Funcionário para atualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Colaborador funcionario = new Colaborador();
        funcionario.setFuncionarioId(id);

        System.out.print("Novo Nome: ");
        funcionario.setNome(teclado.nextLine());

        System.out.print("Novo Telefone: ");
        funcionario.setTelefone(teclado.nextLine());

        String updateSQL = "UPDATE Colaborador SET nome = ?, telefone = ? WHERE funcionario_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getTelefone());
            pstmt.setInt(3, funcionario.getFuncionarioId());

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Registro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar o registro: " + e.getMessage());
        }

        System.out.println("\nPressione Enter para voltar ao submenu...");
        teclado.nextLine();
    }
}

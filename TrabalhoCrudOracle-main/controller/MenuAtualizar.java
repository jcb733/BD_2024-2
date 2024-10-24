package controller;

import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuAtualizar {
    public static void menuAtualizar(Scanner teclado, Connection connection) {
        System.out.println("Atualizar Registro");

        System.out.print("Digite o ID do Funcionário para atualizar: ");
        int id = teclado.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setFuncionarioId(id);

        System.out.print("Novo Nome: ");
        funcionario.setNome(teclado.next());
        System.out.print("Novo Telefone: ");
        funcionario.setTelefone(teclado.next());

        String updateSQL = "UPDATE Funcionario SET nome = ?, telefone = ? WHERE funcionario_id = ?";

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
    }
}
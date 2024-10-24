package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuRemover {
    public static void menuRemover(Scanner teclado, Connection connection) {
        System.out.println("Remover Registro");
        System.out.print("Digite o ID do Funcionário: ");
        int id = teclado.nextInt();

        String deleteFuncionarioSQL = "DELETE FROM Funcionario WHERE funcionario_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(deleteFuncionarioSQL)) {
            pstmt.setInt(1, id);
            int funcionariosRemovidos = pstmt.executeUpdate();
            
            if (funcionariosRemovidos > 0) {
                System.out.println("Funcionário e seus registros de ponto removidos com sucesso!");
            } else {
                System.out.println("Nenhum funcionário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao remover registro: " + e.getMessage());
        }

        System.out.println("Pressione Enter para voltar ao menu principal...");
        teclado.nextLine(); 
        teclado.nextLine(); 
    }
}

package controller;

import model.Colaborador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuInserir {
    public static void menuInserir(Scanner teclado, Connection connection) {
        int opcao;
        do {
            System.out.println("\n=== Submenu de Inserção ===");
            System.out.println("1. Inserir dados do Colaborador");
            System.out.println("2. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = teclado.nextInt();
            
            switch (opcao) {
                case 1:
                    inserirDadosColaborador(teclado, connection);
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);
    }

    private static void inserirDadosColaborador(Scanner teclado, Connection connection) {
        Colaborador colaborador = new Colaborador();

        System.out.println("\nInserir Funcionário");
        System.out.print("Nome: ");
        colaborador.setNome(teclado.next());
        System.out.print("Sexo (M/F): ");
        colaborador.setSexo(teclado.next().charAt(0));
        System.out.print("Idade: ");
        colaborador.setIdade(teclado.nextInt());
        System.out.print("Telefone: ");
        colaborador.setTelefone(teclado.next());
        System.out.print("CPF: ");
        colaborador.setCpf(teclado.next());
        System.out.print("Cargo: ");
        colaborador.setCargo(teclado.next());
        System.out.print("Setor: ");
        colaborador.setSetor(teclado.next());

        String insertColaboradorSQL = "INSERT INTO Colaborador (funcionario_id, nome, sexo, idade, telefone, cpf, cargo, setor) "
                + "VALUES (funcionario_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertColaboradorSQL, new String[]{"funcionario_id"})) {
            pstmt.setString(1, colaborador.getNome());
            pstmt.setString(2, String.valueOf(colaborador.getSexo()));
            pstmt.setInt(3, colaborador.getIdade());
            pstmt.setString(4, colaborador.getTelefone());
            pstmt.setString(5, colaborador.getCpf());
            pstmt.setString(6, colaborador.getCargo());
            pstmt.setString(7, colaborador.getSetor());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                colaborador.setFuncionarioId(rs.getInt(1));
            }

            System.out.println("Funcionário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }
}

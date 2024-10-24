package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPrincipal {
    public static void menuPrincipal() {
        Scanner teclado = new Scanner(System.in);
        
        Connection connection = null;
        try {
            // Estabelecendo a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:XE", "labdatabase", "labDatabase2022");
            char opcao;
            do {
                System.out.println("Menu Principal\n" +
                    "1. INSERIR REGISTROS\n" +
                    "2. REMOVER REGISTROS\n" +
                    "3. ATUALIZAR REGISTROS\n" +
                    "4. RELATÓRIOS\n" +
                    "5. SAIR"
                );
                opcao = teclado.next().charAt(0);
                switch (opcao) {
                    case '1':
                        MenuInserir.menuInserir(teclado, connection);
                        break;
                    case '2':
                        MenuRemover.menuRemover(teclado, connection);
                        break;
                    case '3':
                        MenuAtualizar.menuAtualizar(teclado, connection);
                        break;
                    case '4':
                        MenuRelatorio.menuRelatorio(teclado, connection);
                        break;
                    case '5':
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente");
                }
            } while (opcao != '5');

            System.out.println("Fim do Programa!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        } finally {
            // Fechando a conexão
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        teclado.close();
    }
}

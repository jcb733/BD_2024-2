package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPrincipal {
    public static void limparTela() {
        try {
            final String os = System.getProperty("os.name");
            
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void mostrarMensagemSucesso(String operacao) {
        limparTela();
        System.out.println("✓ " + operacao + " realizada com sucesso!");
        System.out.println("\nPressione ENTER para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        limparTela();
    }

    public static void menuPrincipal() {
        Scanner teclado = new Scanner(System.in);
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:XE", "labdatabase", "labDatabase2022");
            char opcao;
            do {
                limparTela();
                System.out.println("Menu Principal\n" +
                    "1. CADASTRAR FUNCIONÁRIO\n" +
                    "2. CADASTRAR PONTO\n" +    
                    "3. REMOVER REGISTROS\n" +
                    "4. ATUALIZAR REGISTROS\n" +
                    "5. RELATÓRIOS\n" +
                    "6. SAIR"
                );
                opcao = teclado.next().charAt(0);
                limparTela();
                
                switch (opcao) {
                    case '1':
                        MenuInserir.menuInserir(teclado, connection);
                        mostrarMensagemSucesso("Inserção");
                        break;
                    case '2':
                        MenuPonto.menuPonto(teclado, connection); 
                        mostrarMensagemSucesso("Cadastro de Ponto");
                        break;
                    case '3':
                        MenuRemover.menuRemover(teclado, connection);
                        mostrarMensagemSucesso("Remoção");
                        break;
                    case '4':
                        MenuAtualizar.menuAtualizar(teclado, connection);
                        mostrarMensagemSucesso("Atualização");
                        break;
                    case '5':
                        MenuRelatorio.menuRelatorio(teclado, connection);
                        mostrarMensagemSucesso("Geração de relatório");
                        break;
                    case '6':
                        limparTela();
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            } while (opcao != '6');

            System.out.println("Fim do Programa!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        } finally {
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
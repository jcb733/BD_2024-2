package utils;
import conexion.ConexaoBanco;

public class SplashScreen {
    public static void showSplashScreen() {
        ConexaoBanco conexao = new ConexaoBanco();
        int countFuncionario = conexao.contarRegistros("Funcionario");

        System.out.println("--------------------------------------------");
        System.out.println("#           SISTEMA DE FOLHA                 #");
        System.out.println("#                                            #");
        System.out.println("#   REGISTROS:                               #");
        System.out.println("#   QTD FUNCIONARIOS "+countFuncionario+"    #");
        System.out.println("#                                            #");
        System.out.println("#   DESENVOLVIDO POR:                        #");
        System.out.println("#   JULIO CESAR SEPULCRI GHESSO              #");
        System.out.println("#                                            #");
        System.out.println("#   DISCIPLINA: BANCO DE DADOS               #");
        System.out.println("#   2024/2                                   #");
        System.out.println("#                                            #");
        System.out.println("#   PROFESSOR: HOWARD ROATTI                 #");
        System.out.println("----------------------------------------------");
    }
}

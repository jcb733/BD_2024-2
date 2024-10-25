# Sistema de Controle de Ponto


Este sistema de controle de ponto permite o registro, atualização e geração de relatórios de horários de trabalho dos funcionários. A aplicação inclui um banco de dados Oracle para armazenar os dados de registro de ponto e uma interface em Java para interação.
## Pré-requisitos

- Sistema operacional Linux
- Java 8 ou superior
- Oracle Database

## Configuração do Banco de Dados

Certifique-se de que o Oracle Database esteja configurado e em execução. Precisa-se de baixar o drive JDBC para conexão do java com o oracle (Pasta lib no projeto com os arquvios ojdbc8.jar). Você pode usar SQL Developer para executar os scripts SQL fornecidos para criar as tabelas e sequências necessárias:

DROP SEQUENCE funcionario_seq;
DROP SEQUENCE ponto_seq;
DROP TABLE Ponto;
DROP TABLE Funcionario;

CREATE SEQUENCE funcionario_seq;
CREATE SEQUENCE ponto_seq;

CREATE TABLE Funcionario (
    funcionario_id NUMBER DEFAULT funcionario_seq.nextval PRIMARY KEY,
    nome VARCHAR2(255) NOT NULL,
    sexo CHAR(1) NOT NULL,
    idade NUMBER NOT NULL,
    telefone VARCHAR2(20) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    cargo VARCHAR2(50) NOT NULL,
    setor VARCHAR2(50) NOT NULL
);

CREATE TABLE Ponto (
    ponto_id NUMBER DEFAULT ponto_seq.nextval PRIMARY KEY,
    funcionario_id NUMBER NOT NULL,
    data_registro DATE NOT NULL,
    hora_entrada TIMESTAMP NOT NULL,
    hora_saida TIMESTAMP NOT NULL,
    horas_trabalhadas NUMBER(5, 2) NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(funcionario_id)
);
 
## Executando o Projeto 
Clone este repositório:

https://github.com/jcb733/BD_2024-2    

 Execute o aplicativo: 
 java Main 
 OBS: Se rodar na IDE do vscode, terá que baixar a extensão "pack for java"  para poder executar.

O aplicativo será iniciado e você verá o menu principal. Você pode interagir com o sistema para inserir, remover, atualizar a folha de ponto dos funcionários, além de gerar relatórios.

- Como Usar

Siga as opções no menu principal para interagir com o sistema. Você pode inserir, remover, atualizar registros da folha de ponto dos funcionários, bem como gerar relatórios.

- Video Explicativo de como utilizar o programa: https://www.youtube.com/watch?v=GgQp4mopzXk

  
    



    

DROP SEQUENCE funcionario_seq;
DROP SEQUENCE ponto_seq;
DROP TABLE Ponto;
DROP TABLE Colaborador;

CREATE SEQUENCE funcionario_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE ponto_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE Colaborador (
    funcionario_id NUMBER PRIMARY KEY, 
    nome VARCHAR2(255) NOT NULL,
    sexo CHAR(1) NOT NULL,
    idade NUMBER NOT NULL,
    telefone VARCHAR2(20) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    cargo VARCHAR2(50) NOT NULL,
    setor VARCHAR2(50) NOT NULL
);

CREATE TABLE Ponto (
    ponto_id NUMBER PRIMARY KEY, 
    funcionario_id NUMBER NOT NULL,
    data_registro DATE NOT NULL,
    hora_entrada TIMESTAMP NOT NULL,
    hora_saida TIMESTAMP NOT NULL,
    horas_trabalhadas INTERVAL DAY TO SECOND NOT NULL,
    atraso CHAR(1) DEFAULT 'N',
    FOREIGN KEY (funcionario_id) REFERENCES Colaborador(funcionario_id)
);
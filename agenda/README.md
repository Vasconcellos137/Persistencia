31.10.25

CÓDIGO ou SCRIPT P/ BANCO DE DADOS

create database Agenda;
use Agenda;

create table enderecos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(10),
    cidade VARCHAR(50),
    estado VARCHAR(50)
);

create table contatos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    celular VARCHAR(20),
    email VARCHAR(100),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES enderecos(id)
);

• P/ cada classe persistente é necessário ter um "dao".
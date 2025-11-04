31.10.25

Persistência - Serve/Pode ser usado:
•Arquivos de texto;
•Arquivos binários;
•Banco de dados.

*Conecta o BD ao código no VsCode.

Mapeamento Objeto-Relacional
*Padrão de Projeto (Design Pattems)

•MVC: Model View Controller -> São basicamente separação por pacotes, organizar a arquitetura, a estrutura;
Cada coisa esta num separado -> [Model] <-> [Controller] <-> [View]

*View - Interface de interação.
*Controller - Regras de negócio, validações.
*Model (orm) - Mapeamento do BD.

•Singleton -> Garantir que cada objeto..??

•Factory-> ??

•Data Access Object -> ??

-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•

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

-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•

• P/ cada classe persistente é necessário ter um "dao".

•Padrões de Projeto usandos:
-MVC
-Factory
-Singleton
-dao
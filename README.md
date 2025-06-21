🛒 Projeto Mensal 04 - Sistema de E-commerce
Curso: Análise e Desenvolvimento de Sistemas
Semestre: 3º semestre

.

🔍 Descrição
Este projeto consiste em um sistema de e-commerce completo, focado na venda de tênis, camisetas, roupas e outros produtos. O sistema foi desenvolvido com foco em usabilidade, segurança e controle administrativo, utilizando interface gráfica com Java Swing.

.

🧩 Funcionalidades
👤 Sistema de login e cadastro de usuários (clientes e administradores)

🛍️ Catálogo de produtos com tipo, nome, preço e quantidade

🛒 Carrinho de compras e finalização com diferentes formas de pagamento

📦 Escolha entre entrega ou retirada

🔐 Área Administrativa:

-Cadastro,remoção,listar

-Controle de usuários

.

⚙️ Tecnologias Utilizadas

✅ Java 17 – Backend com Programação Orientada a Objetos

✅ Java Swing – Interface gráfica

✅ PostgreSQL – Banco de dados relacional

✅ Maven – Gerenciador de dependências e build

✅ IntelliJ IDEA – Ambiente de desenvolvimento

✅ IntelliJ IDEA – Ambiente de desenvolvimento (com uso de API para busca de endereço via CEP)

.

🌐 Integração com API de CEP

Para facilitar o cadastro de usuários e tornar o preenchimento de endereços mais rápido e confiável, o sistema integra uma API de CEP.

🧩 Funcionalidades da integração:

Preenchimento automático de rua, bairro, cidade e estado a partir do CEP informado.

Validação de CEPs inexistentes.

Redução de erros manuais no cadastro.

.

🔧 Como funciona:

Durante o cadastro de cliente, o sistema realiza uma requisição HTTP para a API (como ViaCEP).

Os dados retornados são exibidos automaticamente nos campos de endereço.

🛠️ Tecnologias envolvidas na integração:

java.net.HttpURLConnection

Leitura e parse de JSON com org.json ou outra lib de sua escolha.

.

🛠 Requisitos Funcionais
Menu interativo com operações CRUD

Persistência de dados no PostgreSQL

Tratamento de exceções

Execução autônoma com instruções no GitHub

Uso de Programação Orientada a Objetos (POO)

.

▶️ Como Executar o Projeto
Instale o Java 17

Configure o JAVA_HOME no sistema

Configure o IntelliJ IDEA para usar Java 17

Vá em File > Project Structure > Project SDK

Instale o Maven

Configure o caminho do Maven no sistema

Instale o PostgreSQL

Crie o banco com o nome eCommerce

Anote usuário e senha para configurar o Hibernate

Clone o projeto:


bash
Copy
Edit
git clone git@github.com:BielMurbak/Projeto-Mensal-V4.git
Configure o Hibernate
Edite o arquivo src/main/resources/hibernate.cfg.xml com:

xml
Copy
Edit
<property name="connection.username">SEU_USUARIO</property>
<property name="connection.password">SUA_SENHA</property>
<property name="connection.url">jdbc:postgresql://localhost:5432/eCommerce</property>
Execute o projeto

Acesse src/main/java/org/grsstreet

Abra o arquivo Main.java

Clique em Run para iniciar a aplicação com a interface gráfica

.

👨‍💻 Integrantes

Rafael Carlos Scarabelot

Gabriel Murbak Scarabelot

.

<div> <img src="https://img.shields.io/badge/WINDOWS-0078D6?style=for-the-badge&logo=windows&logoColor=white"> <img src="https://img.shields.io/badge/INTELLIJIDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/JAVA-ED8B00?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/POSTGRESQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white"> </div>



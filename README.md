# Tech Boutique – CRUD em JavaFX + PostgreSQL

## Descrição do Projeto

Este projeto implementa um sistema simples de CRUD para uma loja de roupas fictícia chamada **Tech Boutique**.  
A aplicação foi desenvolvida utilizando:

- **Java 17+**
- **JavaFX** para interface gráfica
- **PostgreSQL** (em container Docker)
- **Arquitetura MVC**
- **Maven** como gerenciador de dependências

O objetivo é demonstrar conceitos fundamentais de orientação a objetos, persistência de dados e construção de interfaces gráficas.

---

# Arquitetura do Sistema (MVC)

O projeto segue o padrão **MVC**, organizado da seguinte forma:

### **Model**
- `Produto`
- `ProdutoDAO`
- `ConnectionFactory`

### **Controller**
- `ProdutoController`

### **View**
- `HelloApplication`
- `TelaInicialView`
- `TelaListaProdutosView`
- `TelaCadastroProdutoView`

Cada camada tem responsabilidades bem definidas e independentes.

---

#  Modelo UML – Diagrama de Classes

![Diagrama de classes.jpeg](../Diagrama%20de%20classes.jpeg)
---

# Dicionário de Dados – Tabela `produto`

| Campo      | Tipo / Tamanho     | Nulo? | Descrição |
|------------|---------------------|-------|-----------|
| **id**     | SERIAL / INTEGER    | Não   | Identificador único do produto (chave primária). |
| **nome**   | VARCHAR(100)        | Não   | Nome do produto. |
| **tamanho**| VARCHAR(10)         | Sim   | Tamanho da peça (ex.: P, M, G, GG). |
| **cor**    | VARCHAR(30)         | Sim   | Cor do produto. |
| **preco**  | NUMERIC(10,2)       | Sim   | Preço com duas casas decimais. |
| **estoque**| INTEGER             | Sim   | Quantidade disponível em estoque. |

---

# Script SQL utilizado no PostgreSQL

```sql
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tamanho VARCHAR(10),
    cor VARCHAR(30),
    preco NUMERIC(10,2),
    estoque INT
);



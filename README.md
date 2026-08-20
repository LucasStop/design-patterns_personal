# Atividade

Vamos separar nossas classes em diferentes responsabilidades através do padrão de projetos Adapter.
---

## 🎯 Objetivo da Atividade

1. Crie a interface br.pucpr.table.TableData para representar os dados de uma tabela (TableData)
2. Refatore as classes UsersPrinter e PlanetasPrinter para ter uma única classe Table
3. A largura da coluna será dada pela largura do seu cabeçalho.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

---

## 📝 Notas de Desenvolvimento

Pontos que enfrentei ao longo da refatoração, pra deixar registrado o raciocínio (e não só o resultado):

- **Onde colocar a formatação dos valores** (CPF mascarado, distância em UA, tipo de planeta por extenso etc.): decidi manter fora dos records `User`/`Planet`. Eles são dados de domínio; quem sabe formatar pra tabela é o próprio `TableData` (`UserTableAdapter`/`PlanetTableAdapter`), senão o domínio fica acoplado a uma preocupação de apresentação.
- **`Table` genérica**: a primeira versão tinha número de colunas e largura fixos no código (pensado só pra 3 colunas). Corrigi pra montar o formato dinamicamente a partir de `getHeaders().length`, senão não funcionava nem pros 4 campos de usuário nem pros 5 de planeta ao mesmo tempo.
- **Cálculo da borda**: passei por algumas fórmulas erradas pra largura da borda (valor fixo, depois `headers.length * N` com `N` chutado) antes de calcular certo somando a largura real de cada coluna.
- **Requisito 3 (largura = largura do cabeçalho)**: implementado, mas expôs um problema — `String.format("%-Ns", ...)` só define largura **mínima**. Quando o valor da linha é maior que o cabeçalho (ex. `"1.429.400.000 km"` é maior que `"DIST. SOL (KM)"`), a linha estoura e desalinha a borda, porque não existe truncamento automático.

### Em andamento

Ainda estou ajustando pra tabela não estourar: a ideia é que a largura de cada coluna passe a ser o **tamanho mínimo necessário pra caber o maior valor daquela coluna** (olhando cabeçalho e todas as linhas, não só o cabeçalho isolado), garantindo alinhamento sem cortar nenhum dado. Ainda não finalizei essa parte.
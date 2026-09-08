# Atividade

Crie uma forma de adicionar paginação na tabela.

- Ela deve permitir paginar qualquer uma das opções que já criamos.
- Você não deve alterar a classe da tabela, ou criar um filho dela.
- Deve suportar futuras implementações de `TableData`.

---

## 👥 Grupo

Lucas Stopinski da Silva e Lucas Bruno e Silva

---

## 🎯 Objetivo da Atividade

1. Implementar o padrão **Decorator**: `PagedTableData implements TableData` embrulha qualquer outro `TableData` (enum, reflection, Builder — não importa a origem) e expõe só uma fatia das linhas por vez, sem herdar de `Table` nem alterá-la.
2. `rowCount()` devolve quantas linhas cabem **na página atual** (não o total), tratando a última página com menos linhas que `pageSize`. `colCount()`/`header(int)` repassam pro `TableData` original. `get(row, col)` traduz o índice relativo à página pro índice real antes de repassar.
3. Navegação via `nextPage()`/`previousPage()` (mutam a página atual, guardados) e `totalPages()` (calculado a partir de `tableData.rowCount()` e `pageSize`, sem guardar estado redundante).
4. `DesignPatternsApplication` demonstra a paginação de duas formas: embrulhando um `ColumnTableData` manual, e embrulhando o resultado de um `ColumnsBuilder` — provando que o Decorator funciona em cima de qualquer `TableData`, incluindo o que o Builder produz.
5. Indicador "Página X de Y" centralizado embaixo de cada página impressa, sem expor nenhum método novo em `Table` — mede a largura a partir do `toString()` que `Table` já expõe publicamente.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

---

## 📝 Notas de Desenvolvimento

- **Por que Decorator**: as 3 restrições do enunciado (não alterar `Table`, não herdar dela, funcionar com qualquer `TableData` — inclusive futuro) descartam herança e forçam composição. `PagedTableData` implementa a mesma interface que envolve, e `Table` recebe ela normalmente sem saber que está paginada.
- **`totalPages()` não é um campo, é um método**: a primeira versão guardava um campo `totalPages` calculado no construtor — redundante, já que `tableData.rowCount()` e `pageSize` não mudam depois de criado. Virou método (`Math.ceil((double) tableData.rowCount() / pageSize)`), sem estado extra pra manter sincronizado.
- **Bug real que apareceu no meio do caminho**: o cálculo de páginas totais usava `paged.rowCount()` (linhas da página atual, no máximo `pageSize`) em vez de `tableData.rowCount()` (total real) — sempre dava "página X de 1". Só reaparece se alguém tentar calcular o total fora da própria classe; por isso o cálculo virou responsabilidade do `PagedTableData`.
- **Centralizar o rótulo de página sem tocar em `Table`**: como a largura calculada da tabela é privada, usamos o `toString()` (já público, herdado de `Object` e sobrescrito por `Table`) pra pegar o texto renderizado, medir a largura pela primeira linha, e centralizar o texto por fora — sem adicionar nenhum método a `Table`.
- **Decorator funciona igual em cima do Builder**: `new PagedTableData(columnsBuilder.build(dados), pageSize)` — o `ColumnsBuilder.build()` já devolve um `TableData` puro, então embrulhar ele não exige nenhuma mudança em `ColumnsBuilder`/`TableBuilder`, só composição das peças que já existiam.

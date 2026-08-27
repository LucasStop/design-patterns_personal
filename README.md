# Atividade

Terminar de implementar as classes filhas de `ColumnData` para desenhar a tabela de usuários, usando o padrão Strategy (uma classe por coluna) combinado com o Adapter que já tínhamos.

---

## 👥 Grupo

Lucas Stopinski da Silva e Lucas Bruno e Silva

---

## 🎯 Objetivo da Atividade

1. Criar a interface `br.pucpr.table.ColumnData<T>`, representando uma coluna capaz de dizer seu próprio nome (`getColumnName()`) e extrair/formatar o valor de um objeto `T` (`getValue(T)`).
2. Implementar as classes filhas de `ColumnData` para desenhar a tabela de usuários (`IdColumn`, `UserNameColumn`, `CpfColumn`, `EmailColumn`) e, pela mesma lógica, a de planetas (`PlanetNameColumn`, `DiameterColumn`, `DistSunKmColumn`, `DistSunUaColumn`, `TypeColumn`).
3. Criar `br.pucpr.table.ColumnDataAdapter<T> implements TableData` — Adapter genérico que monta uma linha da tabela a partir de um objeto e da lista de colunas que sabem formatá-lo, substituindo os antigos `UserTableAdapter`/`PlanetTableAdapter`.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

---

## 📝 Notas de Desenvolvimento

- **Por que estender a nossa arquitetura em vez de adotar a do professor**: o material de referência da aula (`upstream/aula-04`) já vem com o `ColumnData`/`ColumnTableData` prontos, mas embutidos numa arquitetura de `TableData` diferente da nossa (por coleção inteira, `Table` com estado, `Theme` em outro pacote). Optamos por aplicar o mesmo conceito — Strategy genérico por coluna — em cima do que já tínhamos (`TableData` por linha, `Table` estático), sem jogar fora o trabalho da aula anterior.
- **Colisão de nomes**: como `User` e `Planet` cada um precisava de uma coluna "Nome", as classes viraram `UserNameColumn` e `PlanetNameColumn` em pacotes diferentes (`br.pucpr.user.columns` e `br.pucpr.planet.columns`) para não colidir.
- **`ColumnDataAdapter` guardando referência mutável (SpotBugs EI2)**: o construtor recebia a lista de colunas e guardava a mesma referência (`this.columns = columns`). Se quem chamou o construtor alterasse essa lista depois, o adapter mudava junto sem querer. Corrigido fazendo cópia defensiva (`new ArrayList<>(columns)`) — o `object` (linha única) não precisa da mesma cópia, só a lista de colunas é compartilhada/mutável entre chamadas.
- **Lista de colunas montada uma vez, fora do loop**: a lista de colunas (`userColumns`/`planetColumns`) é a mesma para todo objeto do mesmo tipo — construída antes do `for` em `DesignPatternsApplication`, não recriada a cada iteração.
- **Chamada de método estático via instância**: `Planet.sunDistanceUa(long)` é estático; a primeira versão do `DistSunUaColumn` chamava via `object.sunDistanceUa(...)`, o que compila mas confunde leitura — corrigido para `Planet.sunDistanceUa(...)`.

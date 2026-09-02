# Atividade

Antes de começar, analise como foi implementado o exemplo de reflexão na classe `Main` do professor e como foi criada/usada a anotação `@Column`.

**Objetivo**: inspecionar a classe do usuário ou planeta e gerar o modelo da tabela automaticamente, usando anotações + reflection. Também consolidamos as colunas escritas à mão em enums (`UserColumns`/`PlanetColumns`), com corpo específico por constante.

---

## 👥 Grupo

Lucas Stopinski da Silva e Lucas Bruno e Silva

---

## 🎯 Objetivo da Atividade

1. Criar a anotação `br.pucpr.table.reflection.Column` (`@Retention(RUNTIME)`, `@Target(METHOD)`, elemento `header()` com valor padrão) e aplicá-la nos componentes dos records `User`/`Planet`.
2. Criar `ReflectedColumn<T> implements ColumnData<T>` — coluna que não formata nada à mão, só guarda um `Method` (o acessor anotado) e devolve `getter.invoke(object)` na hora de ler o valor.
3. Criar `Util.inspect(Class<T>)` — varre os métodos declarados de uma classe, filtra quem tem `@Column`, monta a lista de `ReflectedColumn` automaticamente.
4. Consolidar as colunas manuais em dois enums, `UserColumns` e `PlanetColumns`, cada `implements ColumnData<T>` com corpo (`get`) por constante — substituindo as 9 classes separadas que tínhamos (`IdColumn`, `UserNameColumn`, `CpfColumn`, `EmailColumn`, `PlanetNameColumn`, `DiameterColumn`, `DistSunKmColumn`, `DistSunUaColumn`, `TypeColumn`).
5. `Main` imprime 4 tabelas: usuário e planeta via enum (formatação completa) e usuário e planeta via reflection (valor cru, sem formatação) — pra deixar visível a diferença entre os dois mecanismos.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

---

## 📝 Notas de Desenvolvimento

- **Trocamos de arquitetura de propósito nesta atividade**: até a aula anterior, mantínhamos nossa própria arquitetura (`TableData` por linha, `Table` estático, coluna alargando pro maior valor) em vez da do professor. Nesta atividade, ao montar `ColumnData`/`ColumnTableData`/reflection do zero, acabamos convergindo pra arquitetura dele (`TableData` por coleção inteira — `rowCount/colCount/header/get` —, `Table` com estado e `toString()`, truncamento de valor grande com reticências, `Theme` movido pro pacote `table`). Decisão consciente, não acidente: like ficou claro que reescrever tudo de novo só pra manter a divergência não valia o esforço, dado que o conteúdo da aula (reflection + anotação) já pressupõe esse formato.
- **Reflection só lê valor cru**: `ReflectedColumn.get()` faz só `getter.invoke(object).toString()` — sem máscara de CPF, sem separador de milhar, sem tradução de enum pra português. Por isso mantivemos as tabelas via reflection como uma segunda tabela **de demonstração**, ao lado da tabela via enum (que continua com toda a formatação) — não como substituição.
- **Coluna calculada não dá pra gerar via reflection**: `sunDistanceKm` do planeta não tem `@Column` (a distância em UA precisa de conversão via `Planet.sunDistanceUa(long)`, que recebe parâmetro — `Method.invoke(object)` só chama método sem parâmetro). Na tabela "planetas refletidos", a coluna de UA é adicionada manualmente com `PlanetReflectionColumns.add(PlanetColumns.SUN_DISTANCE_AU)`, igual ao material do professor.
- **SpotBugs `EI2` em `ReflectedColumn`**: o construtor guarda a referência do `Method` recebido. Diferente da cópia defensiva que já tínhamos feito em outras classes (`new ArrayList<>(...)`), não tem como copiar um `Method` — é metadado da JVM, não um dado comum. Suprimimos o alerta em `config/spotbugs/exclude.xml` (mesma solução que o material de referência usa).
- **Generics no `ColumnDataAdapter`**: o construtor aceita `Collection<? extends ColumnData<? super T>>` (extra `? extends` na coleção) — sem isso, o retorno de `Util.inspect` (`List<ColumnData<T>>`) não encaixava no parâmetro esperado, por invariância de generics.

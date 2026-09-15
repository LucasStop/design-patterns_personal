# Atividade

Suponha que você precise redesenhar a tabela (ou talvez outros componentes relacionados) sempre que os dados da tabela mudarem.

Implemente uma solução que permita a esses componentes saberem que a mudança ocorreu.

---

## 👥 Grupo

Lucas Stopinski da Silva

---

## 🎯 Objetivo da Atividade

1. Implementar o padrão **Observer**: `ObservableTableData implements TableData, Observable` embrulha qualquer outro `TableData` (Decorator, igual `PagedTableData`) e mantém uma lista de `Observer`s interessados em saber quando os dados mudam.
2. `Observer` define o contrato de quem reage (`onChange(TableData data)`), recebendo os dados atuais. `Observable` define o contrato de quem é observado (`attach`/`detach`/`notifyChange`).
3. `notifyChange()` percorre os observers registrados e chama `onChange(this)` em cada um, sem que `ObservableTableData` precise saber o que cada observer faz com o aviso.
4. `TableObserver implements Observer` é o exemplo concreto: embrulha uma `Table` e, ao ser avisado, chama `setData` + `print` nela — sem precisar alterar `Table`.
5. `DesignPatternsApplication` demonstra o fluxo completo: cria um `ObservableTableData` sobre os planetas, anexa um `TableObserver`, e dispara `notifyChange()` para simular a mudança — a tabela só é redesenhada quando a notificação acontece.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

---

## 📝 Notas de Desenvolvimento

- **Por que Observer**: o problema pede exatamente o que o padrão resolve — desacoplar quem gera a mudança (os dados) de quem precisa reagir a ela (a tabela e outros componentes futuros), sem que o gerador conheça os detalhes de cada reação.
- **Onde entra o Observer na arquitetura**: como `TableData` não tem mutador, quem detecta a mudança nos dados subjacentes é responsável por chamar `notifyChange()` explicitamente — o padrão cuida de propagar o aviso, não de detectar a mudança sozinho.
- **Observer recebendo o `TableData` novo**: `onChange(TableData data)` passa o próprio `ObservableTableData` (que também é um `TableData`), então quem reage já tem acesso aos dados atualizados sem precisar consultar de volta o objeto original.
- **Decorator + Observer compostos**: `ObservableTableData` segue o mesmo formato do `PagedTableData` — embrulha qualquer `TableData` e repassa `rowCount/colCount/header/get` — mas soma o papel de `Observable` por cima, então dá pra observar dados já paginados ou construídos via `Builder` sem nenhuma mudança nessas classes.
- **`TableObserver` não altera `Table`**: reaproveita `setData`, que já existia, para trocar os dados e redesenhar — mantendo `Table` totalmente alheia ao fato de que está sendo observada.

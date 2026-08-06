# Convenção de commits

Gerar sempre a mensagem de commit no formato Conventional Commits:

```
tipo(escopo): resumo curto, no imperativo, minúsculo, sem ponto final

descrição (opcional — só quando o "porquê" da mudança não for óbvio pelo resumo)
```

## Tipos válidos
- `feat` — nova funcionalidade
- `fix` — correção de bug
- `refactor` — mudança de estrutura sem alterar comportamento
- `chore` — configuração, build, dependências, tooling
- `style` — formatação, sem mudança de lógica
- `test` — testes
- `docs` — documentação (README, etc.)

## Escopo
Nome do pacote/módulo afetado (ex.: `usuario`, `gradle`, `hooks`). Omitir se a mudança for ampla demais para um escopo só.

## Exemplos
- `feat(usuario): adiciona validação de CPF`
- `refactor(usuario): separa formatação de impressão em UserFormatter`
- `chore(gradle): configura checkstyle e spotless`

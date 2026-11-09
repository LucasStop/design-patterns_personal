package br.pucpr.table;

import br.pucpr.table.lambda.SimpleColumn;
import br.pucpr.table.model.ColumnData;
import br.pucpr.table.model.ColumnTableData;
import br.pucpr.table.model.TableData;
import br.pucpr.table.reflection.ColumnInspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ColumnsBuilder<T> {
  private List<ColumnData<T>> columns;

  public ColumnsBuilder() {
    this.columns = new ArrayList<>();
  }

  public final ColumnsBuilder<T> add(Collection<ColumnData<T>> cols) {
    columns.addAll(cols);
    return this;
  }

  @SafeVarargs
  public final ColumnsBuilder<T> add(ColumnData<T>... cols) {
    return add(Arrays.asList(cols));
  }

  public final ColumnsBuilder<T> add(String header, Function<T, String> getter) {
    return add(new SimpleColumn<>(header, getter));
  }

  public final ColumnsBuilder<T> inspect(Class<T> clazz) {
    return add(ColumnInspector.inspect(clazz));
  }

  public TableData build(Collection<T> data) {
    return new ColumnTableData<>(data, columns);
  }

  public TableData build(Collection<T> data, Predicate<? super T> filter) {
    return build(data.stream().filter(filter).toList());
  }
}

package br.pucpr.planet.columns;

import br.pucpr.planet.Planet;
import br.pucpr.table.ColumnData;

public class PlanetNameColumn implements ColumnData<Planet> {
  @Override
  public String getColumnName() {
    return "NOME";
  }

  @Override
  public String getValue(Planet object) {
    return object.name();
  }
}

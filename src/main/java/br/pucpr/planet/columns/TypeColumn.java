package br.pucpr.planet.columns;

import br.pucpr.planet.Planet;
import br.pucpr.planet.PlanetType;
import br.pucpr.table.ColumnData;

public class TypeColumn implements ColumnData<Planet> {
  @Override
  public String getColumnName() {
    return "TIPO";
  }

  @Override
  public String getValue(Planet object) {
    return formatType(object.type());
  }

  public static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}

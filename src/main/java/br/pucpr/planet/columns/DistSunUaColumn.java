package br.pucpr.planet.columns;

import br.pucpr.planet.Planet;
import br.pucpr.table.ColumnData;
import java.util.Locale;

public class DistSunUaColumn implements ColumnData<Planet> {
  @Override
  public String getColumnName() {
    return "DISTÂNCIA DO SOL (UA)";
  }

  @Override
  public String getValue(Planet object) {
    return formatDistanceUa(Planet.sunDistanceUa(object.sunDistanceKm()));
  }

  private String formatDistanceUa(double l) {
    return String.format(Locale.of("pt", "BR"), "%,.2f ua", l);
  }
}

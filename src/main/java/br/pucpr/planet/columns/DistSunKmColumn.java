package br.pucpr.planet.columns;

import br.pucpr.planet.Planet;
import br.pucpr.table.ColumnData;
import java.util.Locale;

public class DistSunKmColumn implements ColumnData<Planet> {
  @Override
  public String getColumnName() {
    return "DISTÂNCIA DO SOL (KM)";
  }

  @Override
  public String getValue(Planet object) {
    return formatDistanceKm(object.sunDistanceKm());
  }

  private String formatDistanceKm(double l) {
    return String.format(Locale.of("pt", "BR"), "%,.0f km", l);
  }
}

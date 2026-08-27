package br.pucpr.planet.columns;

import br.pucpr.planet.Planet;
import br.pucpr.table.ColumnData;
import java.util.Locale;

public class DiameterColumn implements ColumnData<Planet> {
  @Override
  public String getColumnName() {
    return "DIÂMETRO (KM)";
  }

  @Override
  public String getValue(Planet object) {
    return formatDiameter(object.diameterKm());
  }

  private String formatDiameter(double v) {
    return String.format(Locale.of("pt", "BR"), "%,.1f km", v);
  }
}

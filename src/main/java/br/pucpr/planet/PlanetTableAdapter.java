package br.pucpr.planet;

import br.pucpr.table.TableData;
import java.util.Locale;

public class PlanetTableAdapter implements TableData {
  private final Planet planet;

  public PlanetTableAdapter(Planet planet) {
    this.planet = planet;
  }

  @Override
  public String[] getHeaders() {
    return new String[] {"NOME", "DIÂMETRO", "DIST. SOL (KM)", "DIST. SOL (UA)", "TIPO"};
  }

  @Override
  public String[] getRow() {
    return new String[] {
      planet.name(),
      formatDiameter(planet.diameterKm()),
      formatDistanceKm(planet.sunDistanceKm()),
      formatDistanceUa(Planet.sunDistanceUa(planet.sunDistanceKm())),
      formatType(planet.type())
    };
  }

  private String formatDiameter(double v) {
    return String.format(Locale.of("pt", "BR"), "%,.1f km", v);
  }

  private String formatDistanceKm(double l) {
    return String.format(Locale.of("pt", "BR"), "%,.0f km", l);
  }

  private String formatDistanceUa(double l) {
    return String.format(Locale.of("pt", "BR"), "%,.2f ua", l);
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

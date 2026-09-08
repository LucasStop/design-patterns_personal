package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;
import java.util.Locale;

public enum PlanetColumns implements ColumnData<Planet> {
  NAME("%-10s".formatted("Nome")) {
    @Override
    public String get(Planet planet) {
      return planet.name() == null || planet.name().isEmpty() ? "NÃO INFORMADO" : planet.name();
    }
  },
  DIAMETER("%10s".formatted("Diâmetro (km)")) {
    @Override
    public String get(Planet planet) {
      return String.format(LOCALE_PT_BR, "%,10.1f km", planet.diameterKm());
    }
  },
  SUN_DISTANCE_KM("%18s".formatted("Dist. sol (km)")) {
    @Override
    public String get(Planet planet) {
      return String.format(LOCALE_PT_BR, "%,15d km", planet.sunDistanceKm());
    }
  },
  SUN_DISTANCE_AU("%18s".formatted("Dist. sol (ua)")) {
    @Override
    public String get(Planet planet) {
      return String.format(
          LOCALE_PT_BR, "%,15.2f ua", Planet.sunDistanceUa(planet.sunDistanceKm()));
    }
  },
  TYPE("%-10s".formatted("Tipo")) {
    @Override
    public String get(Planet planet) {
      return switch (planet.type()) {
        case ROCK -> "Rochoso";
        case GAS -> "Gasoso";
        case ICE -> "Gelado";
        case DWARF -> "Anão";
      };
    }
  };

  private final String header;
  private static final Locale LOCALE_PT_BR = Locale.of("pt", "BR");

  PlanetColumns(String header) {
    this.header = header;
  }

  @Override
  public String header() {
    return header;
  }
}

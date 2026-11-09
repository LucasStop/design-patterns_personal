package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;
import java.util.Locale;
import java.util.function.Function;

public enum PlanetColumns implements ColumnData<Planet> {
  NAME(
      "%-10s".formatted("Nome"),
      planet -> planet.name() == null || planet.name().isEmpty() ? "NÃO INFORMADO" : planet.name()),
  DIAMETER("%10s".formatted("Diâmetro (km)"), planet -> fmt("%,10.1f km", planet.diameterKm())),
  SUN_DISTANCE_KM(
      "%18s".formatted("Dist. sol (km)"), planet -> fmt("%,15d km", planet.sunDistanceKm())),
  SUN_DISTANCE_AU(
      "%18s".formatted("Dist. sol (ua)"),
      planet -> fmt("%,15.2f ua", Planet.sunDistanceUa(planet.sunDistanceKm()))),
  TYPE(
      "%-10s".formatted("Tipo"),
      planet ->
          switch (planet.type()) {
            case ROCK -> "Rochoso";
            case GAS -> "Gasoso";
            case ICE -> "Gelado";
            case DWARF -> "Anão";
          });

  private static final Locale LOCALE_PT_BR = Locale.of("pt", "BR");

  private final String header;
  private final Function<Planet, String> getter;

  PlanetColumns(String header, Function<Planet, String> getter) {
    this.header = header;
    this.getter = getter;
  }

  @Override
  public String header() {
    return header;
  }

  @Override
  public String get(Planet planet) {
    return getter.apply(planet);
  }

  private static String fmt(String format, Object... args) {
    return String.format(LOCALE_PT_BR, format, args);
  }
}

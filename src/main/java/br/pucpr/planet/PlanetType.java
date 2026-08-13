package br.pucpr.planet;

public enum PlanetType {
  ROCK,
  GAS,
  ICE,
  DWARF;

  // Switch case para os tipos
  public static String getTypeName(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}

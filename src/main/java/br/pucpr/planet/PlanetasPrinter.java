package br.pucpr.planet;

import br.pucpr.user.Theme;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetasPrinter {
  public void print(ArrayList<Planet> planets, Theme theme, boolean alignRight) {
    if (planets == null || planets.isEmpty()) {
      System.out.println("ERRO: Lista de planetas vazia ou nula.");
      return;
    }
    final var borderChar = theme.getBorderChar();

    // Borda superior e cabeçalho
    final var BORDER_WIDTH = 88;
    var sb = new StringBuilder();
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    sb.append(
        String.format(
            "| %-10s | %-15s | %-20s | %-17s | %-10s |%n",
            "NOME", "DIÂMETRO", "DIST. SOL (KM)", "DIST. SOL (UA)", "TIPO"));
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    for (var planet : planets) {
      if (planet == null) {
        continue;
      }
      sb.append(
          String.format(
              "| %-10s | %-15s | %-20s | %-17s | %-10s |%n",
              planet.name(),
              formatDiameter(planet.diameterKm()),
              formatDistanceKm(planet.sunDistanceKm()),
              formatDistanceUa(planet.sunDistanceUa()),
              PlanetType.getTypeName(planet.type())));
    }
    // Borda inferior
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");

    // Espaçamento
    if (alignRight) {
      var lines = sb.toString().split("\n");
      for (var line : lines) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
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
}

package br.pucpr.planet;

public record Planet(
    String name, double diameterKm, long sunDistanceKm, double sunDistanceUa, PlanetType type) {
  // sunDistanceUa calculated from sunDistanceKm
  public Planet(String name, double diameterKm, long sunDistanceKm, PlanetType type) {
    this(name, diameterKm, sunDistanceKm, (sunDistanceKm / 149_597_870.7), type);
  }
}

package br.pucpr;

import static br.pucpr.planet.PlanetType.*;
import static br.pucpr.user.Theme.LIGHT;

import br.pucpr.planet.Planet;
import br.pucpr.planet.columns.*;
import br.pucpr.table.ColumnData;
import br.pucpr.table.ColumnDataAdapter;
import br.pucpr.table.Table;
import br.pucpr.table.TableData;
import br.pucpr.user.User;
import br.pucpr.user.columns.CpfColumn;
import br.pucpr.user.columns.EmailColumn;
import br.pucpr.user.columns.IdColumn;
import br.pucpr.user.columns.UserNameColumn;
import java.util.ArrayList;
import java.util.List;

public class DesignPatternsApplication {
  public static void main(String[] args) {

    System.out.println("IMPRIMINDO USUÁRIOS");
    System.out.println("-------------------");
    final List<TableData> userRows = new ArrayList<>();
    final List<ColumnData<? super User>> userColumns =
        List.of(new IdColumn(), new UserNameColumn(), new EmailColumn(), new CpfColumn());
    for (var user : createUsers()) {
      userRows.add(new ColumnDataAdapter<User>(user, userColumns));
    }
    new Table().print(userRows, LIGHT, true);

    System.out.println();
    System.out.println("IMPRIMINDO PLANETAS");
    System.out.println("-------------------");
    final List<TableData> planetRows = new ArrayList<>();
    final List<ColumnData<? super Planet>> planetColumns =
        List.of(
            new PlanetNameColumn(),
            new DiameterColumn(),
            new DistSunKmColumn(),
            new DistSunUaColumn(),
            new TypeColumn());
    for (var planet : createPlanets()) {
      planetRows.add(new ColumnDataAdapter<Planet>(planet, planetColumns));
    }
    new Table().print(planetRows, LIGHT, true);
  }

  private static ArrayList<User> createUsers() {
    final var usuarios = new ArrayList<User>();
    usuarios.add(
        new User(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
    usuarios.add(new User(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
    usuarios.add(
        new User(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
    usuarios.add(new User(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
    usuarios.add(new User(105L, "Lucas Mendes", "lucas@email.com", "12345"));
    usuarios.add(new User(106L, "", "beatriz@email.com", "55566677788"));
    return usuarios;
  }

  private static ArrayList<Planet> createPlanets() {
    final var planetas = new ArrayList<Planet>();
    planetas.add(new Planet("Mercúrio", 4879, 57_910_000L, ROCK));
    planetas.add(new Planet("Vênus", 12104, 108_200_000L, ROCK));
    planetas.add(new Planet("Terra", 12756, 149_600_000L, ROCK));
    planetas.add(new Planet("Marte", 6792, 227_940_000L, ROCK));
    planetas.add(new Planet("Júpiter", 142984, 778_330_000L, GAS));
    planetas.add(new Planet("Saturno", 120536, 1_429_400_000L, GAS));
    planetas.add(new Planet("Urano", 51118, 2_870_990_000L, ICE));
    planetas.add(new Planet("Netuno", 49528, 4_504_300_000L, ICE));
    planetas.add(new Planet("Plutão", 2376, 5_906_380_000L, DWARF));
    return planetas;
  }
}

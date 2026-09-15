package br.pucpr;

import static br.pucpr.planet.PlanetType.*;

import br.pucpr.planet.Planet;
import br.pucpr.planet.PlanetColumns;
import br.pucpr.table.ColumnsBuilder;
import br.pucpr.table.PagedTableData;
import br.pucpr.table.Table;
import br.pucpr.table.TableBuilder;
import br.pucpr.table.model.ColumnTableData;
import br.pucpr.table.observer.ObservableTableData;
import br.pucpr.table.observer.TableObserver;
import br.pucpr.user.User;
import java.util.ArrayList;

public class DesignPatternsApplication {
  public static void main(String[] args) {

    //        System.out.println("IMPRIMINDO USUÁRIOS");
    //        System.out.println("-------------------");
    //        new Table(new ColumnTableData<User>(users(), UserColumns.values())).print();
    //
    //        System.out.println();
    //        System.out.println("IMPRIMINDO USUÁRIOS COM REFLECTION");
    //        System.out.println("-------------------");
    //        final var userReflectionColumns = ColumnInspector.inspect(User.class);
    //        new Table(new ColumnTableData<User>(users(), userReflectionColumns)).print();
    //
    //        System.out.println();
    //        System.out.println("IMPRIMINDO USUÁRIOS COM PATTERN BUILDER");
    //        System.out.println("-------------------");
    //        new TableBuilder()
    //                .light()
    //                .rightAligned()
    //                .withData(users(), c -> c.add(UserColumns.values()))
    //                .print();

    //        System.out.println();
    //        System.out.println("IMPRIMINDO PLANETAS");
    //        System.out.println("-------------------");
    //        new Table(new ColumnTableData<Planet>(planets(), PlanetColumns.values())).print();
    //
    //        System.out.println();
    //        System.out.println("IMPRIMINDO PLANETAS COM REFLECTION");
    //        System.out.println("-------------------");
    //        final var planetReflectionColumns = ColumnInspector.inspect(Planet.class);
    //        planetReflectionColumns.add(PlanetColumns.SUN_DISTANCE_AU);
    //        new Table(new ColumnTableData<Planet>(planets(), planetReflectionColumns)).print();
    //
    //        System.out.println();
    //        System.out.println("IMPRIMINDO PLANETAS COM PATTERN BUILDER");
    //        System.out.println("-------------------");
    //        new TableBuilder()
    //                .light()
    //                .rightAligned()
    //                .withData(planets(), c -> c.add(PlanetColumns.values()))
    //                //                    .add(
    //                //                        "Distance (au)",
    //                //                        p ->
    //                // "%,11.2f".formatted(Planet.sunDistanceUa(p.sunDistanceKm()))))
    //                .print();

    System.out.println();
    System.out.println("IMPRIMINDO PLANETAS COM PAGINAÇÃO");
    System.out.println("-------------------");
    final var paged =
        new PagedTableData(new ColumnTableData<Planet>(planets(), PlanetColumns.values()), 3);
    final var table = new Table(paged);
    for (int i = 0; i < paged.totalPages(); i++) {
      table.print();
      paged.nextPage();
      System.out.printf("Página %d de %d%n", i + 1, paged.totalPages());
    }

    System.out.println();
    System.out.println("IMPRIMINDO PLANETAS COM PAGINAÇÃO E PATTERN BUILDER");
    System.out.println("-------------------");
    var data = new ColumnsBuilder<Planet>().add(PlanetColumns.values()).build(planets());
    final var pagedBuilder = new PagedTableData(data, 3);
    final var builderTable = new TableBuilder().light().rightAligned().withData(pagedBuilder);
    for (int i = 0; i < pagedBuilder.totalPages(); i++) {
      var rendered = builderTable.toString();
      var width = rendered.lines().findFirst().map(String::length).orElse(0);
      System.out.print(rendered);

      var label = "Página %d de %d".formatted(i + 1, pagedBuilder.totalPages());
      var padding = Math.max(0, (width - label.length()) / 2);
      System.out.println(" ".repeat(padding) + label);
      pagedBuilder.nextPage();
    }

    System.out.println();
    System.out.println("IMPRIMINDO PLANETAS COM NOTIFICAÇÃO DE MUDANÇAS");
    System.out.println("-------------------");
    final var observableData =
        new ObservableTableData(new ColumnTableData<Planet>(planets(), PlanetColumns.values()));
    final var observedTable = new Table(observableData);
    observableData.attach(new TableObserver(observedTable));
    observableData.notifyChange();
  }

  private static ArrayList<User> users() {
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

  private static ArrayList<Planet> planets() {
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

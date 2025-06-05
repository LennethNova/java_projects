import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdenesProduccion {
    // Show orders
    public static void mostrarOrdenes(List< ? extends OrdenProduccion > lista) {
        System.out.println("\nRegistered orders: ");
        for (OrdenProduccion o : lista) {
            o.mostrarResumen();
        }
    }

    // Personalized orders
    public static void procesarPersonalizadas(List < ? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\n Processing personalized orders... ");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada p) {
                p.aplicaarCostoAdicional(costoAdicional);
            }
        }
    }

    // Main
    public static void main(String[]args) {
        List<OrdenMasa> ordenesMasa = Arrays.asList(
                new OrdenMasa("A123", 500),
                new OrdenMasa("A124", 750)
        );

        List<OrdenPersonalizada> ordenesPersonalizadas = Arrays.asList(
                new OrdenPersonalizada("P456", 100, "ClientX"),
                new OrdenPersonalizada("P789", 150, "ClientY")
        );

        List<OrdenPrototipo> ordenesPrototipo = Arrays.asList(
                new OrdenPrototipo("T123", 10, "Design"),
                new OrdenPrototipo("T456", 5, "Tests")
        );

        // Show orders
        mostrarOrdenes(ordenesMasa);
        mostrarOrdenes(ordenesPersonalizadas);
        mostrarOrdenes(ordenesPrototipo);

        // Process personalized
        procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        // Summary
        System.out.println("\nTotal Orders Summary: ");
        System.out.println("\nMass Production: " + ordenesMasa.size());
        System.out.println("\nPersonalized: " + ordenesPersonalizadas.size());
        System.out.println("\nPrototypes: " + ordenesPrototipo.size());

    }
}
import java.util.*;
import java.util.stream.*;

// Order
class Pedido {
    private String cliente;
    private String tipoEntrega;
    private String telefono;

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega(){
        return tipoEntrega;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}

public class Main {
    public static void main(String[]args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Claudio", "delivery", "123456789"),
                new Pedido("Andres", "store", "987654321"),
                new Pedido("Telma", "delivery", null),
                new Pedido("Ana", "store", null),
                new Pedido("Laura", "delivery", "741852963"),
                new Pedido("Abel", "store", "147852369"),
                new Pedido("Cesar", "delivery", null)

        );

        System.out.println("\nProcessing confirmation for delivery...");
        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("delivery"))
                .map(Pedido::getTelefono)
                .flatMap(Optional::stream)
                .map(tel -> "\n Confirmation delivered to phone number: " + tel)
                .forEach(System.out::println);
    }
}
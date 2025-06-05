// Dependencies
import java.util.*;

//abstract
abstract class OrdenProduccion{
    protected String codigo;
    protected int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public abstract void mostrarResumen();
}

// Order mass production
class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\nMass Order - Code: " + codigo + " - Quantity: " + cantidad);
    }
}

// Personalized order
class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    public void aplicaarCostoAdicional(int costo) {
        System.out.println("\nOrder: " + codigo + " adjusted with additional fee of: $" + costo);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\nPersonalized order - Code: " + codigo + " - Quantity: " + cantidad + " - Client: " + cliente);
    }

}

// Prototype order
class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\nPrototype order - Code: " + codigo + " - Quantity: " + cantidad + " - Phase: " + faseDesarrollo);
    }
}



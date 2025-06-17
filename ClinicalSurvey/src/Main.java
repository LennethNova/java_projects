import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

// Survey

class Encuesta {
    private String paciente;
    private String comentario;
    private int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }

}

// Store Location
class Sucursal {
    private String nombre;
    private List<Encuesta> encuestas;

    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
}

public class Main {
    public static void main(String[]args) {
        List<Sucursal> sucursales = Arrays.asList(
                new Sucursal("Downtown", Arrays.asList(
                        new Encuesta("Takeo", "Large waiting time", 2),
                        new Encuesta("Sunakawa", "Excellent response", 5),
                        new Encuesta("Rinko", "Little expensive", 3),
                        new Encuesta("Maomao", "Not enough antidotes", 1),
                        new Encuesta("Jinshi", null, 4),
                        new Encuesta("Chocobo", "WAAAAAARK", 2)
                )),

                new Sucursal("North", Arrays.asList(
                        new Encuesta("Kinta", "Large waiting time", 3),
                        new Encuesta("Yoshitaka", "Meh", 2),
                        new Encuesta("Naoko", "Excellent service", 5),
                        new Encuesta("Yue", null, 1),
                        new Encuesta("Rika", "Did not have the medicine", 4),
                        new Encuesta("Moogle", "Kupo kupo", 4)

                ))
        );

        System.out.println("\nProcessing information from unsatisfied clients...");

        sucursales.stream()
                .flatMap(sucursal -> sucursal.getEncuestas().stream()
                        .filter(encuesta -> encuesta.getCalificacion() <=3)
                        .map(Encuesta::getComentario)
                        .flatMap(Optional::stream)
                        .map(comentario -> "Store Location " + sucursal.getNombre() + " : patient follow-up with commentary: \"" + comentario + "\""))
                .forEach(System.out::println);
    }
}
// Dependencies
import java.util.*;
import java.util.function.Predicate;

// Abstract class
abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public abstract void mostrarDetalle();
}

class Video extends MaterialCurso {
    // Duration mins
    private int duracion;

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("\n Video: " + titulo + " - Author: " + autor + " - Duration: " + duracion + " min." );
    }
}

class Articulo extends MaterialCurso {
    private int palabras;

    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("\nArticle: " + titulo + " - Author: " + autor + " - Words: " + palabras);
    }
}

class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }

    public void marcarRevisado() {
        this.revisado = true;
    }

    public boolean isRevisado() {
        return revisado;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("\nExcercise: " + titulo + " - Author: " + autor + " - Revised: " + revisado);
    }
}

public class MaterialesCursoGenericos {

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("\nClass materials: ");
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("\nVideo Lenght: " + total + " minutes");
    }

    public static void marcarEjerciciosRevisados(List < ? super Ejercicio > lista) {
        System.out.println();
        for (Object obj : lista) {
            if (obj instanceof Ejercicio e) {
                e.marcarRevisado();
                System.out.println("\nExcercise '" + e.titulo + "' mark as checked.");
            }
        }
    }

    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        System.out.println("\nFilter materials per author: ");
        boolean encontrado = false;
        for (MaterialCurso m : lista) {
            if (filtro.test(m)) {
                m.mostrarDetalle();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("\nCouldn't find materials with that author.");
        }
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();

        materiales.add(new Video("Introduction to Java", "Mario", 15));
        materiales.add(new Video("OOP en Java", "Carlos", 20));
        materiales.add(new Articulo("Java's History", "Ana", 1200));
        materiales.add(new Articulo("Data Types", "Luis", 800));
        materiales.add(new Ejercicio("Variables and types", "Luis"));
        materiales.add(new Ejercicio("Conditionals", "Mario"));

        // Show materials
        mostrarMateriales(materiales);

        // Video lenght (filtered)
        List<Video> soloVideos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if (m instanceof Video v) {
                soloVideos.add(v);
            }
        }
        contarDuracionVideos(soloVideos);

        // Mark as checked
        marcarEjerciciosRevisados(materiales);

        // Filter per author
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Add author name to find: ");
        String autorBuscado = scanner.nextLine();

        filtrarPorAutor(materiales, m -> m.getAutor().equalsIgnoreCase(autorBuscado));

        scanner.close();
    }
}



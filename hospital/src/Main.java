package hospital;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        RecursoMedico salaCirugia = new RecursoMedico("Surgery room");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable tarea1 = () -> salaCirugia.usar("Dr. Sánchez");
        Runnable tarea2 = () -> salaCirugia.usar("Dr. Gómez");
        Runnable tarea3 = () -> salaCirugia.usar("Nurse Ruiz");
        Runnable tarea4 = () -> salaCirugia.usar("Dr. Martínez");

        System.out.println("\nSimulation of hospital resource access initiated...");

        executor.submit(tarea1);
        executor.submit(tarea2);
        executor.submit(tarea3);
        executor.submit(tarea4);

        executor.shutdown();
    }
}
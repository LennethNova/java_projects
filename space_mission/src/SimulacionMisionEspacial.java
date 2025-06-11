import jdk.jshell.spi.ExecutionControl;

import java.util.concurrent.*;

// Subsystem: Navigation
class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "\nNavigation: Trajectory Successfully Corrected.";
    }
}

// Subsystem: Vital Support
class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(800);
        return "\nVital Support: Pressure and oxigen levels inside normal parameters.";
    }
}

// Subsystem: Thermal Control
class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1500);
        return "\nThermal Control: Stable temperature (22°C).";
    }
}

// Subsystem: Communications
class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(700);
        return "\nCommunications: Link with land station established.";
    }
}

public class SimulacionMisionEspacial {
    public static void main(String[]args) {
        System.out.println("Space mission simulation initiated...");
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            // send tasks
            Future<String> nav = executor.submit(new SistemaNavegacion());
            Future<String> soporte = executor.submit(new SistemaSoporteVital());
            Future<String> termico = executor.submit(new SistemaControlTermico());
            Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());

            // Results
            System.out.println(comunicaciones.get());
            System.out.println(soporte.get());
            System.out.println(termico.get());
            System.out.println(nav.get());

            System.out.println("\nAll Systems available.");

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Simulation error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
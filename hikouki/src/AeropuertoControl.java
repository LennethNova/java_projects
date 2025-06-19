import java.util.concurrent.*;
import java.util.Random;

public class AeropuertoControl {

    private static final Random random = new Random();

    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean disponible = random.nextDouble() < 0.8;
                System.out.println("Available Runway: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error verifying the runway");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean favorable = random.nextDouble() < 0.85;
                System.out.println("Favorable climate: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error verifying climate");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean despejado = random.nextDouble() < 0.9;
                System.out.println("Clear air traffic: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error verifying air traffic");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean disponible = random.nextDouble() < 0.95;
                System.out.println("Staff available: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error verifying staff on ground");
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("\nVerifying landing conditions...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pista, clima, trafico, personal);

        allChecks.thenRun(() -> {
            try {
                boolean resultado = pista.get() && clima.get() && trafico.get() && personal.get();
                if (resultado) {
                    System.out.println("\nAuthorized landing: all conditions are optimal.");
                } else {
                    System.out.println("\nUnauthorized landing: Not optimal conditions.");
                }
            } catch (Exception e) {
                System.out.println("\n❌ Error combining results: " + e.getMessage());
            }
        }).exceptionally(ex -> {
            System.out.println("\n❌ General failure in landing system: " + ex.getMessage());
            return null;
        });

        // maintain simulation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

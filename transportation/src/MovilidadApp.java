import java.util.concurrent.*;

public class MovilidadApp {

    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Estimating route...");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new  IllegalStateException("Error estimating route.", e);
            }
            return "Downtown -> North";
        });
    }

    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Estimating price...");
            try {
                Thread.sleep(1000 + new java.util.Random().nextInt(1000,3000));
            } catch (InterruptedException e) {
                throw new  IllegalStateException("Error estimating price.", e);
            }
            return 75.50;
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) ->
                "Calculating route: " + ruta + " | Estimated price: $" + tarifa
        ).exceptionally(ex ->
                "❌ Error during processing: " + ex.getMessage()
        ).thenAccept(System.out::println);

        // Until process end
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

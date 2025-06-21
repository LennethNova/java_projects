// Dependencies
package main.java.com.tefi;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class MeridianPrimeSistemaReactivo {
    private static final Random random = new Random();
    private static final AtomicInteger semaforoRojoCount = new AtomicInteger(0);

    public static void main(String[]args) throws InterruptedException {
        Flux<String> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101))
                .filter(congestion -> congestion > 70)
                .doOnNext(congestion -> "Alert: " + congestion + "% congestion on Peak Avenue")
                .onBackpressureBuffer(5);

        Flux<String> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(80))
                .filter(pm -> pm > 50)
                .doOnNext(pm -> "Alert: High Pollution (PM2.5: " + pm + " ug/m3");

        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Low", "Medium", "High"};
                    return prioridades[random.nextInt(prioridades.length)];
                })
                .filter(prioridad -> prioridad.equals("High"))
                .doOnNext(prioridad -> System.out.println("Road Emergency: High priority accident"));

        Flux<Integer> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11))
                .filter(retraso -> retraso > 5)
                .doOnNext(retraso -> System.out.println("Train with critical delay: " + retraso + " minutes"))
                .onBackpressureBuffer(3);

        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Green", "Yellow", "Red"};
                    return estados[random.nextInt(estados.length)];
                })
                .transform(MeridianPrimeControl::controlarSemaforos);

        // combine flux
        Flux.merge(trafico, contaminacion, accidentes, trenes, semaforos)
                .bufferTimeout(5, Duration.ofSeconds(2))
                .filter(lista -> lista.size() >= 3)
                .doOnNext(lista -> System.out.println("\nGlobal alert: Multiple critical events detected on Meridian Prime\n"))
                .subscribe();

        // keep running
        Thread.sleep(25000);
        }

        // 3 lights
        private static Flux<String> controlarSemaforos(Flux<String> flujo) {
            final int[] contadorRojos = {0};
            return flujo
                    .filter(estado -> {
                        if (estado.equals("Rojo")) {
                            contadorRojos[0]++;
                            if (contadorRojos[0] >= 3) {
                                contadorRojos[0] = 0;
                                return true;
                            }
                        } else {
                            contadorRojos[0] = 0;
                        }
                        return false;
                    })
                    .doOnNext(estado -> System.out.println("Red traffic light detected 3 timesin a row on North Crossing"));


    }
}
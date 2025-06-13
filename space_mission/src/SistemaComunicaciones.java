package space_mission;
import java.util.concurrent.Callable;

// Subsystem: Communications
class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(700);
        return "\nCommunications: Link with land station established.";
    }
}
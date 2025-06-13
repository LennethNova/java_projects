package hospital;

import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println("Initiating access to the " + nombre + "...");

        lock.lock();
        try {
            System.out.println(profesional + " has entered to " + nombre);
            Thread.sleep(1000);
            System.out.println(profesional + " is out of " + nombre);
        } catch (InterruptedException e) {
            System.out.println(profesional + " was interrupted while was using " + nombre);
        } finally {
            lock.unlock();
        }
    }

}
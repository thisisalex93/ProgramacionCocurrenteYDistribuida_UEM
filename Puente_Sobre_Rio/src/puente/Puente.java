package puente;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Puente {
    private int norteCruzando;
    private int surCruzando;
    private final Lock lock;
    private final Condition norteCond;
    private final Condition surCond;

    public Puente() {
        norteCruzando = 0;
        surCruzando = 0;
        lock = new ReentrantLock();
        norteCond = lock.newCondition();
        surCond = lock.newCondition();
    }

    public void cruzarNorte() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Coche en direccion norte quiere cruzar el puente");
            while (surCruzando > 0) {
                System.out.println("Hay coches cruzando en direccion sur, esperando...");
                norteCond.await();
            }
            norteCruzando++;
            System.out.println("Coche en direccion norte cruzando el puente...");
        } finally {
            lock.unlock();
        }

        // cruzar el puente en dirección norte

        lock.lock();
        try {
            norteCruzando--;
            System.out.println("Coche en direccion norte ha cruzado el puente");
            if (norteCruzando == 0) {
                surCond.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void cruzarSur() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Coche en direccion sur quiere cruzar el puente");
            while (norteCruzando > 0) {
                System.out.println("Hay coches cruzando en direccion norte, esperando...");
                surCond.await();
            }
            surCruzando++;
            System.out.println("Coche en direccion sur cruzando el puente...");
        } finally {
            lock.unlock();
        }

        // cruzar el puente en dirección sur

        lock.lock();
        try {
            surCruzando--;
            System.out.println("Coche en direccion sur ha cruzado el puente");
            if (surCruzando == 0) {
                norteCond.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
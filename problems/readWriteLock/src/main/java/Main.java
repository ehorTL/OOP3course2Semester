import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        ExecutorService executeService = Executors.newFixedThreadPool(10);
        ReadWriteLock lock = new ReaderWriterPattern();

        for (int i = 0; i < 5; i++) {
            long writingTime = ThreadLocalRandom.current().nextLong(5000);
            executeService.submit(new MyWriter("Writer " + i, lock, writingTime));
        }
        LOGGER.log(Level.INFO, "Writers added...");

        for (int i = 0; i < 5; i++) {
            long readingTime = ThreadLocalRandom.current().nextLong(10);
            executeService.submit(new MyReader("Reader " + i, lock, readingTime));
        }
        LOGGER.log(Level.INFO, "Readers added...");

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error sleeping before adding more readers", e);
            Thread.currentThread().interrupt();
        }

        for (int i = 6; i < 10; i++) {
            long readingTime = ThreadLocalRandom.current().nextLong(10);
            executeService.submit(new MyReader("Reader " + i, lock, readingTime));
        }
        LOGGER.log(Level.INFO,"More readers added...");

        executeService.shutdown();
        try {
            executeService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE,"Error waiting for ExecutorService shutdown", e);
            Thread.currentThread().interrupt();
        }
    }
}

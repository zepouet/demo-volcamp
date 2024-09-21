package org.volcanix.boot;

public class CpuBoost {

    public static void perform() throws InterruptedException {
        // Nombre de threads pour simuler une charge CPU sur plusieurs cœurs
        int numThreads = Runtime.getRuntime().availableProcessors(); // Utilise tous les cœurs disponibles

        // Temps de début
        long startTime = System.currentTimeMillis();

        // Délai cible : 30 secondes
        long targetTime = 30 * 1000; // 30 secondes en millisecondes

        // Créer et démarrer plusieurs threads
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                // Boucle CPU intensive
                while (System.currentTimeMillis() - startTime < targetTime) {
                    double value = Math.random();
                    for (int j = 0; j < 10_000_000; j++) {
                        value = Math.sin(value) * Math.cos(value);
                    }
                }
            });
            threads[i].start();
        }

        // Attendre que tous les threads terminent
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Calcul CPU intensif terminé !");

        System.out.println("Calcul CPU intensif terminé !");
        System.out.println("Calcul CPU intensif terminé !");
        System.out.println("Calcul CPU intensif terminé !");
    }
}
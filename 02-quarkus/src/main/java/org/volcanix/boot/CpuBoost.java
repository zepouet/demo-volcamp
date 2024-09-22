package org.volcanix.boot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CpuBoost {

    public static void perform() throws InterruptedException {
        // Récupérer le nombre de CPU alloués via Kubernetes (cgroups)
        double allocatedCpu = getAllocatedCpu();

        // Calculer une durée de calcul ajustée en fonction du CPU alloué
        // Si plus de CPU est alloué, la durée est réduite
        long targetTime = (long) (30_000 / allocatedCpu); // 30 secondes ajustées selon CPU

        System.out.println("Allocated CPU: " + allocatedCpu);
        System.out.println("Target time (ms): " + targetTime);

        // Créer plusieurs threads pour maximiser l'utilisation du CPU
        int numThreads = (int) Math.ceil(allocatedCpu);
        Thread[] threads = new Thread[numThreads];
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                while (System.currentTimeMillis() - startTime < targetTime) {
                    // Calcul CPU intensif
                    double value = Math.random();
                    for (int j = 0; j < 10_000_000; j++) {
                        value = Math.sin(value) * Math.cos(value);
                    }
                }
            });
            threads[i].start();
        }

        // Attendre que tous les threads se terminent
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Calcul CPU intensif terminé !");
    }

    // Méthode pour récupérer le CPU alloué à partir des cgroups sous Kubernetes
    private static double getAllocatedCpu() {
        String cpuQuotaPath = "/sys/fs/cgroup/cpu/cpu.cfs_quota_us";
        String cpuPeriodPath = "/sys/fs/cgroup/cpu/cpu.cfs_period_us";

        try {
            int quota = Integer.parseInt(Files.readAllLines(Paths.get(cpuQuotaPath)).get(0));
            int period = Integer.parseInt(Files.readAllLines(Paths.get(cpuPeriodPath)).get(0));
            return (double) quota / period;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors de la lecture des cgroups : " + e.getMessage());
            return Runtime.getRuntime().availableProcessors(); // Par défaut, utilise les processeurs visibles
        }
    }
}
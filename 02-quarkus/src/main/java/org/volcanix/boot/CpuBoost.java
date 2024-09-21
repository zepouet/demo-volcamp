package org.volcanix.boot;

public class CpuBoost {

    public static void perform() {
        // Temps de début
        long startTime = System.currentTimeMillis();

        // Délai cible : 30 secondes
        long targetTime = 30 * 1000; // 30 secondes en millisecondes

        // Boucle CPU intensive
        while (System.currentTimeMillis() - startTime < targetTime) {
            // Effectuer des calculs intensifs
            double value = Math.random();
            for (int i = 0; i < 1_000_000; i++) {
                value = Math.sin(value) * Math.cos(value);
            }
        }

        System.out.println("Calcul CPU intensif terminé !");
    }
}
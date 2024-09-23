package org.volcanix.boot;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
        try {
            // Taille des matrices (augmente la taille pour intensifier le calcul)
            int matrixSize = 1000;

            System.out.println("Génération des matrices...");
            double[][] matrixA = CpuBoost.generateRandomMatrix(matrixSize);
            double[][] matrixB = CpuBoost.generateRandomMatrix(matrixSize);

            System.out.println("Multiplication des matrices...");
            long startTime = System.currentTimeMillis();

            // Effectue la multiplication des matrices
            double[][] resultMatrix = CpuBoost.multiplyMatrices(matrixA, matrixB, matrixSize);

            long endTime = System.currentTimeMillis();
            System.out.println("Multiplication terminée en " + (endTime - startTime) + " ms");

            // Affiche une valeur de la matrice résultat pour vérifier le calcul
            System.out.println("Résultat [0][0] : " + resultMatrix[0][0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}
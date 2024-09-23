package org.volcanix.boot;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CpuBoost {


    // Génère une matrice aléatoire de taille n x n
    public static double[][] generateRandomMatrix(int n) {
        Random random = new Random();
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }

        return matrix;
    }

    // Multiplie deux matrices A et B de taille n x n
    public static double[][] multiplyMatrices(double[][] A, double[][] B, int n) {
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

}

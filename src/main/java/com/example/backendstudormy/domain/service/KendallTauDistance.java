package com.example.backendstudormy.domain.service;

import java.util.List;

public class KendallTauDistance {
    public static double calculateKendallTau(List<Integer> rank1, List<Integer> rank2) {
        int n = rank1.size();
        int numConcordant = 0;
        int numDiscordant = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank1Diff = rank1.get(i) - rank1.get(j);
                int rank2Diff = rank2.get(i) - rank2.get(j);
                if (rank1Diff * rank2Diff > 0) {
                    numConcordant++;
                } else if (rank1Diff * rank2Diff < 0) {
                    numDiscordant++;
                }
            }
        }

        int totalPairs = n * (n - 1) / 2;
        return (numConcordant - numDiscordant) / (double) totalPairs;
    }
}

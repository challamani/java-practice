package com.core_java_practice.collections;

public class TowersAndWaterHolding {

    public static void main(String args[]) {

        int[] towers = {5, 3, 7, 4, 6, 7, 8, 2, 1, 5, 6, 7, 3, 2, 3};
        int[] waterHolding = new int[towers.length];
        int max = towers[0];
        int x = 1;
        int secondMax = 0;
        int lastMaxPosition = 0;

        while (x < towers.length) {
            if (max < towers[x]) {
                secondMax = max;
                max = towers[x];
                fillWater(towers, waterHolding, lastMaxPosition, x, secondMax);
                lastMaxPosition = x;
            } else if (x + 1 == towers.length) {
                x = getMaxTowerPosition(towers, lastMaxPosition + 1);
                System.out.println("reverse max :: " + towers[x] + " , pos :: " + x);
                secondMax = towers[x];
                max = secondMax;
                fillWater(towers, waterHolding, lastMaxPosition, x, secondMax);
                lastMaxPosition = x;
            }
            x++;
        }
        for (int water : waterHolding)
            System.out.print(water + ",");
    }

    private static void fillWater(int[] towers, int[] waterHolding, int x, int y, int maxTower) {
        for (int i = x; i < y; i++) {
            waterHolding[i] = maxTower <= towers[i] ? 0 : maxTower - towers[i];
        }
    }

    private static int getMaxTowerPosition(int[] towers, int x) {
        int max = towers[x];
        int maxNumberPosition = x;
        for (int i = x; i < towers.length; i++) {
            if (max < towers[i]) {
                maxNumberPosition = i;
                max = towers[i];
            }
        }
        return maxNumberPosition;
    }
}


package com.company;

import java.util.Random;

public class Main {
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Healer "};
    public static int[] heroesHealth = {270, 280, 250, 200};
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static int bossDamage = 50;
    public static int bossHealth = 700;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;

    public static void healerStats() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random random = new Random();
            int ran = random.nextInt(heroesAttackType.length);

            if (heroesHealth[i] <= 100) {
                heroesHealth[i] = heroesHealth[ran] + ran;
                if (heroesHealth[i]<=0){
                    break;
                }
            }
        }
    }
    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefenceType);
    }

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinish()) {
            round();
        }
    }



    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHits();
        printStatistics();
        healerStats();
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coef = random.nextInt(8) + 2;
                    if (bossHealth < heroesDamage[i] * coef) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND----------------");
        System.out.println("Boss health " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health "
                    + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("----------------");
    }

    public static boolean isGameFinish() {
        if (bossHealth <= 0) {
            System.out.println("HEROES WON!!!");
            return true;
        }
//        if (heroesHealth[0] <=0 && heroesHealth[1] <=0 && heroesHealth[2] <=0){
//            System.out.println("BOSS WON!!!");
//            return true;
//        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("BOSS WON!!!");
        }
        return allHeroesDead;
    }
}


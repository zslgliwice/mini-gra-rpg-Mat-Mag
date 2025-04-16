import java.util.*;

// System.out.println("\u001b[34m       \u001b[0m");

public class TajemniczyLasGame {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("\u001b[34m\tWitaj w Tajemniczym Lesie!\u001b[0m");
        Player player = new Player();

        boolean gameRunning = true;

        while (gameRunning && player.hp > 0){
            System.out.println("-----------------------------------------");
            System.out.println("Twoje statystyki: HP = " + player.hp + " | Złoto = " + player.gold + " | Atak = " + player.attack + "\n");
            System.out.println("Co chcesz zrobić?");
            System.out.println("\t1. Eksplorować dalej");
            System.out.println("\t2. Sprawdzić ekwipunek");
            System.out.println("\t3. Zakończyć grę");

            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    losoweWydarzenie(player);
                    break;
                case "2":
                    player.showStats();
                    break;
                case "3":
                    System.out.println("Kończysz grę :( Do zobaczenia!");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("\t???");
            }
        }

        if (player.hp <= 0) {
            System.out.println("\u001b[31mZginąłeś w lesie... Its Joever.\u001b[0m");
        }
    }



    static void losoweWydarzenie(Player player) {
        int event = random.nextInt(10);

        if (event < 4) {
            Enemy enemy = new Enemy();
            System.out.println("\u001b[31;1m\tSpotkałeś " + enemy.name + "!\u001b[0m");
            walka(player, enemy);

        } else if (event < 7) {
            int goldFound = random.nextInt(12) + 5;
            System.out.println("\u001b[33m\tZnalazłeś " + goldFound + " sztuk złota!\u001b[0m");
            player.gold += goldFound;

        } else if (event < 9) {
            System.out.println("\u001b[32m\tZnalazłeś obóz. Możesz odpocząć (10 zł za 20 HP albo +10 ataku).\u001b[0m");

            if (player.gold >= 10) {
                System.out.println("Czy chcesz odpocząć? (atak, hp, nie)");
                String answer = scanner.nextLine();
                
                if (answer.equalsIgnoreCase("hp")) {
                    player.gold -= 10;
                    player.hp = Math.min(player.hp + 20, 100);
                    System.out.println("Odpocząłeś. HP = " + player.hp);

                } else if (answer.equalsIgnoreCase("atak")) {
                    player.gold -= 10;
                    player.attack += 10;
                    System.out.println("Twój atak został ulepszony. Nowy atak = " + player.attack);
            
                }

            } else {
                System.out.println("Nie masz wystarczająco złota na odpoczynek.");
            }
        } else {
            System.out.println("\u001b[36m\tZnalazłeś wyjście z lasu!\u001b[0m");
            System.out.println("\u001b[36m\t\tGratulacje, udało Ci się przeżyć i wrócić do domu!\u001b[0m");
            
            System.exit(0);
        }
    }

    static void walka(Player player, Enemy enemy) {
        
        while (enemy.hp > 0 && player.hp > 0) {
            System.out.println("\u001b[41;1m\u001b[30m\t" + enemy.llamo + ": HP = " + enemy.hp + "\u001b[0m");

            System.out.println("Co robisz?");
            System.out.println("1. Atakuj");
            System.out.println("2. Uciekaj");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                int minDamage = player.attack / 2;
                int maxDamage = player.attack;
                int playerDamage = random.nextInt((maxDamage - minDamage) + 1) + minDamage;
                enemy.hp -= playerDamage;
                System.out.println("\u001b[31;1mZadałeś " + playerDamage + " obrażeń!\u001b[0m");

                if (enemy.hp > 0) {
                    int minEnemyDmg = enemy.attack / 2;
                    int enemyDamage = random.nextInt((enemy.attack - minEnemyDmg) + 1) + minEnemyDmg;
                    player.hp -= enemyDamage;

                    System.out.println(enemy.llamo + " atakuje! \u001b[31mOtrzymujesz " + enemyDamage + " obrażeń. HP = " + player.hp + "\u001b[0m");
                } else {
                    int loot = random.nextInt(6) + 10;
                    System.out.println("\u001b[34;1mPokonałeś " + enemy.name + "!\u001b[0m");
                    System.out.println("\u001b[33;1mZdobywasz " + loot + " złota.\u001b[0m");                    
                    player.gold += loot;
                }

            } else if (choice.equals("2")) {
                if (random.nextBoolean()) {
                    System.out.println("\u001b[32;1mUdało Ci się uciec!\u001b[0m");
                    return;
                } else {
                    System.out.println("\u001b[35mNie udało się uciec! Tracisz turę.\u001b[0m");
                    int minEnemyDmg = enemy.attack / 2;
                    int enemyDamage = random.nextInt((enemy.attack - minEnemyDmg) + 1) + minEnemyDmg;
                    player.hp -= enemyDamage;
                    System.out.println(enemy.name + " atakuje! Otrzymujesz " + enemyDamage + " obrażeń. HP = " + player.hp);
                }
            } else {
                System.out.println("Nieprawidłowy wybór!");
            }
        }
    }
}

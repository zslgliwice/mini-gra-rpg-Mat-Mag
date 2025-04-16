import java.util.Random;

public class Player {
    int hp = 100;
    int gold = 10;
    int attack = 15;


    void showStats() {
        System.out.println("---\nEkwipunek:");
        System.out.println("HP = " + hp);
        System.out.println("Złoto = " + gold);
        System.out.println("Atak = " + attack);
    }
}

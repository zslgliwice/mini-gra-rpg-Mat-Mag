import java.util.Random;

public class Enemy {
    String name;
    String llamo;
    int hp;
    int attack;

    public Enemy() {
        Random random = new Random();
        String[] names = {"wilka", "goblina", "bandyte"};
        String[] llamos = {"wilk", "goblin", "bandyta"};

        int index = random.nextInt(names.length);

        name = names[index];
        llamo = llamos[index];

        hp = random.nextInt(31) + 20;
        attack = random.nextInt(11) + 5;
    }
}

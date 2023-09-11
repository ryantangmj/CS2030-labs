import java.util.Scanner;

class Main {    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Roster roster = new Roster("AY2020");
        int num = sc.nextInt();

        for (int i = 0; i < num; i++) {
            roster = roster.add(sc.next(), sc.next(), sc.next(), sc.next()); 
            sc.nextLine();
        }

        while (sc.hasNext()) {
            System.out.println(roster.getGrade(sc.next(), sc.next(), sc.next()));
            sc.nextLine();
        }
    }
}

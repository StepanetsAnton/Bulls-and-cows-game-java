package bullscows;
import java.util.*;
public class Main {
    public boolean grader(StringBuilder key,String guess){
        boolean finish = false;
        int bulls=0, cows = 0;
        for(int i=0;i<key.length();i++){
            for(int j=0;j<guess.length();j++){
                if(j==i && key.charAt(i)==guess.charAt(j)){
                    bulls+=1;
                }else if(j!=i && key.charAt(i)==guess.charAt(j)){
                    cows +=1;
                }
            }
        }
        if (bulls == key.length()) {
            System.out.println("Grade: "+bulls+" bull(s).");
            finish = true;
        }else if(bulls != 0){
            if(cows!=0){
                System.out.println("Grade: "+bulls+" bull(s) and "+cows+" cow(s).");
            }else{
                System.out.println("Grade: "+bulls+" bull(s).");
            }
        }else {
            if(cows!=0){
                System.out.println("Grade: "+cows+" cow(s).");
            }else{
                System.out.println("Grade: None.");
            }
        }
        return finish;
    }
    public StringBuilder generate() {
        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        String lengthStr = scanner.nextLine();
        if(lengthStr.contains(" ")){
            System.out.println("error: \""+lengthStr+"\" isn`t a valid number.");
            System.exit(1);
        }else {
            int length = Integer.parseInt(lengthStr);
            System.out.println("Input the number of possible symbols in the code:");
            String symbolsStr = scanner.nextLine();
            int symbols = Integer.parseInt(symbolsStr);
            if(length == 0){
                System.out.println("error: cant be empty.");
                System.exit(1);
            }else if(symbols<length){
                System.out.println("error: it's not possible to generate a code with a length of "+length+" with "+symbols+" unique symbols.");
                System.exit(1);
            }else if(symbols>36){
                System.out.println("error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(1);
            }else {
                Set<Character> set = new HashSet<>();
                StringBuilder stars = new StringBuilder("");

                if (symbols <= 10) {
                    do {
                        char a = (char) (random.nextInt(9) + '0');
                        if (set.contains(a)) {
                            continue;
                        } else {
                            set.add(a);
                            number.append(a);
                            stars.append("*");
                        }
                    } while (length != number.length());
                    char s1 = '0';
                    char s2 = (char) (symbols + 47);
                    System.out.println("The secret is prepared: " + stars + " (" + s1 + "-" + s2 + ")");
                } else {
                    do {
                        char a;
                        int rand = random.nextInt(2);
                        if (rand == 0) {
                            a = (char) (random.nextInt(9) + '0');
                            if (set.contains(a)) {
                                continue;
                            } else {
                                set.add(a);
                                number.append(a);
                                stars.append("*");
                            }
                        } else {
                            int letter = symbols - 11;
                            a = (char) (random.nextInt(letter) + 'a');
                            if (set.contains(a)) {
                                continue;
                            } else {
                                set.add(a);
                                number.append(a);
                                stars.append("*");
                            }
                        }
                    } while (length != number.length());
                    char s3 = (char) ((symbols - 11) + 'a');
                    System.out.println("The secret is prepared:" + stars + " (0-9, a-" + s3 + ")");
                }
            }
        }
        return number;
    }
    public static void main(String[] args) {
        Main game = new Main();
        StringBuilder key = game.generate();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Okay, let's start a game!");
        boolean finish = false;
        int i = 1;
        while (finish == false) {
            System.out.println("Turn " + i + ":");
            String guess = scanner.next();
            finish = game.grader(key, guess);
            i++;
        }
        System.out.println("Congratulations! You guessed the secret code.");

    }
}

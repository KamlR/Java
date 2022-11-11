import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    public static int[] countAnswer(String str) {
        String vowels = "AaEeIiOoUuYy";
        String consonants = "BbCcDdFfGgHhJjLlKkMmNnPpQqRrSsTtVvWwXxZz";
        int count_vowels = 0;
        int count_consonants = 0;
        for (int i = 0; i < str.length(); i++) {
            if (vowels.indexOf(str.charAt(i)) != -1) {
                count_vowels++;
                continue;
            }
            if (consonants.indexOf(str.charAt(i)) != -1) {
                count_consonants++;
            }
        }
        int answer[] = {count_vowels, count_consonants};
        return answer;
    }

    public static void main(String[] args) {
        out.println("Enter your string:");
        Scanner console = new Scanner(in);
        String str = console.nextLine();
        int[] answer = countAnswer(str);
        out.print("Number of vowels: ");
        out.println(answer[0]);
        out.print("Number of consonants: ");
        out.println(answer[1]);
    }
}
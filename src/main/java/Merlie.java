import java.util.Scanner;

public class Merlie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String bot = "Merlie: ";
        String user = "You: ";

        System.out.println("\n" + line);
        System.out.println(bot + "Hello! I'm Merlie the Merlion\n"
                + bot + "What can I do for you?");
        System.out.println(line);

        while(true) {
            System.out.print(user);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(bot + "Hope to see you again soon! Majulah Singapura!");
                System.out.println(line + "\n");
                break;
            } else if (!input.isEmpty()) {
                System.out.println(bot + input);
                System.out.println(line);
            }
        }
        sc.close();
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Merlie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String bot = "Merlie: ";
        String user = "You: ";
        List<String> list = new ArrayList<String>();

        System.out.println("\n" + line);
        System.out.println(bot + "Hello! I'm Merlie the Merlion\n"
                + bot + "What can I do for you?");
        System.out.println(line);

        while(true) {
            System.out.print(user);
            String input = sc.nextLine().trim();

            // Exit
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(bot + "Hope to see you again soon! Majulah Singapura!");
                System.out.println(line + "\n");
                break;

            // List Tasks
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(bot + "Here’s what you’ve got:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("  " + (i+1) + ". " + list.get(i));
                }
                System.out.println(line);

            // Add Task to List
            } else if (!input.isEmpty()) {
                if (list.stream().anyMatch(task -> task.equalsIgnoreCase(input))) {
                    System.out.println(bot + input + " is already in your list!");
                }
                else{
                    list.add(input);
                    System.out.println(bot + "added: " + input);
                }
                System.out.println(line);
            }
        }
        sc.close();
    }
}

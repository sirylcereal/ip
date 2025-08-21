import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Merlie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String bot = "Merlie: ";
        String user = "You: ";
        List<Task> list = new ArrayList<>();

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
                if (list.isEmpty()) {
                    System.out.println(bot + "You have 0 tasks on your list!");
                } else {
                    System.out.println(bot + "Here’s what you’ve got:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + list.get(i));
                    }
                }

            // Mark Task as Done
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < list.size()) {
                        list.get(index).markDone();
                        System.out.println(" Merlie: Congratulations on completing your task:");
                        System.out.println("     " + list.get(index));
                    }
                    else {
                        System.out.println(bot + "Rawrr, enter a valid task number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(bot + "Rawrr, enter the task number instead (mark <task num>)");
                }

            // Unmark Task to be Undone
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < list.size()) {
                        list.get(index).markUndone();
                        System.out.println(" Merlie: Rawrr, I've marked this task as not done (YET):");
                        System.out.println("     " + list.get(index));
                    }
                    else {
                        System.out.println(bot + "Rawrr, enter a valid task number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(bot + "Rawrr, enter the task number instead (unmark <task num>)");
                }

            // Add Task to List
            } else if (!input.isEmpty()) {
                if (list.stream().anyMatch(task -> task.getDescription()
                        .equalsIgnoreCase(input))) {
                    System.out.println(bot + input + " is already in your list!");
                }
                else{
                    Task newTask = new Task(input);
                    list.add(newTask);
                    System.out.println(bot + "added: " + input);
                }
            }
            System.out.println(line);
        }
        sc.close();
    }
}

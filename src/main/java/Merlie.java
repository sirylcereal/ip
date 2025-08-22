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
                        System.out.println(bot + "Rawrr, I've marked this task as not done (YET):");
                        System.out.println("     " + list.get(index));
                    }
                    else {
                        System.out.println(bot + "Rawrr, enter a valid task number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(bot + "Rawrr, enter the task number instead (unmark <task num>)");
                }

            // Delete Task
            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < list.size()) {
                        Task task = list.get(index);
                        list.remove(index);
                        System.out.println(bot + "Okie, I've sprayed water on the task, so it has EXSTINGUISHED...:");
                        System.out.println("     " + task);
                        System.out.println(bot + "Now you have " + list.size() + " tasks in the list.");
                    }
                    else {
                        System.out.println(bot + "Rawrr, enter a valid task number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(bot + "Rawrr, enter the task number instead (delete <task num>)");
                }

            // Add Todo Task to List
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();

                if (list.stream().anyMatch(task -> task.getDescription().equalsIgnoreCase(description)))
                    System.out.println(bot + "Rawrr, " + description + " is already in your list!");
                else {
                    Task newTask = new Todo(description);
                    list.add(newTask);
                    System.out.println(bot + "Got it! I've added this to your task list:\n\t" + newTask);
                    System.out.println(bot + "Now you have " + list.size() + " tasks in the list.");
                }

            // Add Deadline Task to List
            } else if (input.startsWith("deadline ")) {
                String text = input.substring(9).trim();

                if (!text.contains(" /by ")) {
                    System.out.println(bot + "Rawrr, provide a deadline using '/by' in the correct syntax  (deadline <task> /by <deadline>");
                    System.out.println(line);
                    continue;
                }

                String[] taskInfo = text.split(" /by ", 2);
                String description = taskInfo[0].trim();
                String by = taskInfo[1].trim();

                if (description.isEmpty() || by.isEmpty()) {
                    System.out.println(bot + "Rawrr, both description and deadline must be non-empty!");
                    System.out.println(line);
                    continue;
                }

                Task newTask = new Deadline(description, by);
                if (!haveDuplicate(newTask,list)) {
                    list.add(newTask);
                    System.out.println(bot + "Got it! I've added this task:\n\t " + newTask);
                    System.out.println(bot + "Now you have " + list.size() + " tasks in the list.");
                }

            // Add Event Task to List
            } else if (input.startsWith("event ")) {
                String text = input.substring(6).trim();
                String description = "";
                String from = "";
                String to = "";

                try {

                    if (!text.contains(" /from ") || !text.contains(" /to ")) {
                        System.out.println(bot + "Rawrr, provide a date range in the correct syntax (event <task> /from <start> /to <end>");
                        System.out.println(line);
                        continue;
                    }
                    String[] taskInfo1 = text.split(" /from ", 2);
                    description = taskInfo1[0].trim();

                    String[] taskInfo2 = taskInfo1[1].split(" /to ", 2);
                    from = taskInfo2[0].trim();
                    to = taskInfo2[1].trim();
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("Rawrr, create the event task in the correct syntax" +
                            "(event <task> /from <start> /to <end>");
                    System.out.println(line);
                    continue;
                }

            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                System.out.println(bot + "Rawrr, description and date range must be non-empty!");
                System.out.println(line);
                continue;
            }

            Task newTask = new Event(description,from,to);

            if (!haveDuplicate(newTask,list)) {
                list.add(newTask);
                System.out.println(bot + "Got it! I've added this task:\n\t " + newTask);
                System.out.println(bot + "Now you have " + list.size() + " tasks in the list.");
            }

            // Echo User if Not Command
            } else if (!input.isEmpty()) {
                System.out.println(bot + "Echo after you AH!: " + input);
            }
            System.out.println(line);
        }
        sc.close();
    }

    // Helper Method to Check for Duplicates and Handle Respectively
    static boolean haveDuplicate(Task newTask, List<Task> list) {
        String bot = "Merlie: ";
        for (Task task : list) {
            if (task.isDuplicate(newTask)) {
                System.out.println(bot + "Rawrr, " + newTask.getDescription() + " is already in your list!");
                return true;
            } else if (task.updateIfSameDesc(newTask)) {
                System.out.println(bot + "Ooo, updated the task for: " + task);
                return true;
            }
        }
        return false;
    }
}

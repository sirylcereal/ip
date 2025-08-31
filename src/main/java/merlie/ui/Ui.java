package merlie.ui;

import merlie.task.Task;
import merlie.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.print("\n");
        printLine();
        output("Hello! I'm Merlie the Merlion");
        output("What can I do for you?");
        printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public String readInput() {
        System.out.print("You: ");
        return sc.nextLine().trim();
    }

    public void end() {
        output("Hope to see you again soon! Majulah Singapura!");
        printLine();
        System.out.println("\n");
        sc.close();
    }

    public void taskSizeOutput (int size) {
        output("You have " + size + " tasks on your list!");
    }

    public void showList(TaskList list) {
        output("Here’s what you’ve got:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + list.getTask(i));
        }
    }

    public void markDoneOutput(Task task) {
        output("Congratulations on completing your task:");
        System.out.println("     " + task);
    }

    public void markUndoneOutput(Task task) {
        output("Rawrr, I've marked this task as not done (YET):");
        System.out.println("     " + task);
    }

    public void updateOutput(Task task) {
        output("Ooo, updated the task for: " + task);

    }

    public void addOutput(Task task, int size) {
        output("Got it! I've added this to your task list:\n\t" + task);
        output("Now you have " + size + " tasks in the list.");
    }

    public void deleteOutput(Task task, int size) {
        output("Okie, I’ve sprayed water on the task, so it has EXSTINGUISHED…:");
        output("     " + task);
        output("Now you have " + size + " tasks in the list.");
    }

    public void errorOutput(String error) {
        output("Rawrr, " + error);
    }

    public void echo(String message) {
        output("Echo after you AH!: " + message);
    }

    private void output(String message) {
        System.out.println("Merlie: " + message);
    }
}
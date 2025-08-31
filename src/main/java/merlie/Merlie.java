package merlie;

import merlie.ui.Ui;
import merlie.tasklist.TaskList;
import merlie.command.Command;
import merlie.parser.Parser;
import merlie.listfile.ListFile;
import merlie.exception.MerlieException;

import java.util.Scanner;
import java.util.List;

/**
 * Main application class for Merlie the Merlion chatbot.
 * Handles user input and manages tasks.
 */
public class Merlie {
    private final Ui ui;
    private final TaskList taskList;
    private final ListFile listFile;
    private boolean isExit = false;

    public Merlie(String filepath) {
        this.ui = new Ui();
        this.listFile = new ListFile((filepath));
        this.taskList = listFile.load();
    }

    public void run() {
        ui.start();
        while (!this.isExit) {
            String input = "";
            try {
                input = ui.readInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, listFile);
                this.isExit = c.isExit();
            } catch (MerlieException e) {
                if (e.getMessage().isEmpty()) {
                    ui.echo(input);
                } else {
                    ui.errorOutput(e.getMessage());
                }
            }
        }
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command-line arguments (ignored)
     */
    public static void main(String[] args) {
        new Merlie("./data/tasklist.txt").run();
    }
}
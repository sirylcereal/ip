package merlie;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import merlie.command.Command;
import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.parser.Parser;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Main application class for Merlie the Merlion chatbot.
 * Handles the main application loop, input processing, and command execution.
 */
public class Merlie {
    private final Ui ui;
    private final TaskList taskList;
    private final ListFile listFile;
    private boolean isExit = false;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream ps = new PrintStream(baos);


    /**
     * Constructs a Merlie application instance with the specified data file.
     *
     * @param filepath The path of the file used to store tasks.
     */
    public Merlie(String filepath) {
        this.ui = new Ui();
        this.listFile = new ListFile(filepath);
        this.taskList = listFile.load();
    }

    /**
     * Runs the main loop of the application.
     * Reads user input, parses commands, and executes them until exit.
     */
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
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Gets the welcome message for GUI initialization.
     *
     * @return The formatted welcome message as a String.
     */
    public String getStartMessage() {
        return ui.getStartForGui();
    }

    /**
     * Processes a single user input and returns the chatbot's response as a String for the GUI.
     *
     * @param input The raw input string entered by the user.
     * @return The chatbot's response, formatted for GUI display.
     */
    public String getResponse(String input) {
        baos.reset();
        System.setOut(this.ps);

        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, listFile);
            this.isExit = c.isExit();
        } catch (MerlieException e) {
            if (e.getMessage().isEmpty()) {
                ui.echo(input);
            } else {
                ui.errorOutput(e.getMessage());
            }
        } finally {
            ps.flush();
            String response = baos.toString();
            //System.setOut(System.out);
            return this.ui.formatForGui(response);
        }
    }

    /**
     * Checks if the application should exit.
     *
     * @return true if exit command was executed, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Merlie("./data/tasklist.txt").run();
    }
}

package merlie.parser;

import merlie.exception.MerlieException;
import merlie.command.Command;
import merlie.command.ExitCommand;
import merlie.command.ListCommand;
import merlie.command.MarkCommand;
import merlie.command.UnmarkCommand;
import merlie.command.DeleteCommand;
import merlie.command.TodoCommand;
import merlie.command.DeadlineCommand;
import merlie.command.EventCommand;

public class Parser {
    public static Command parse(String input) throws MerlieException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            try {
                int index = Integer.parseInt(arguments) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new MerlieException("please provide a valid task number for mark!");
            }

        case "unmark":
            try {
                int index = Integer.parseInt(arguments) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new MerlieException("please provide a valid task number for unmark!");
            }

        case "delete":
            try {
                int index = Integer.parseInt(arguments) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new MerlieException("please provide a valid task number for delete!");
            }

        case "todo":
            return new TodoCommand(arguments);

        case "deadline": {
            if (!arguments.contains(" /by ")) {
                throw new MerlieException("provide a deadline using '/by' in the correct syntax  (deadline <task> /by <deadline>");
            }
            String[] taskInfo = arguments.split(" /by ", 2);
            String description = taskInfo[0].trim();
            String byString = taskInfo[1].trim();

            if (description.isEmpty() || byString.isEmpty()) {
                throw new MerlieException("both description and deadline must be non-empty!");
            }

            ParsedDate parsed = ParsedDate.parseDate(byString);
            return new DeadlineCommand(description, parsed.getDate(), parsed.getHasTime());
        }


        case "event": {
            if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
                throw new MerlieException("provide a date range in the correct syntax " +
                        "(event <task> /from <start> /to <end>)");
            }

            String description = "";
            String fromString = "";
            String toString = "";

            try {
                String[] taskInfo1 = arguments.split(" /from ", 2);
                description = taskInfo1[0].trim();
                String[] taskInfo2 = taskInfo1[1].split(" /to ", 2);
                fromString = taskInfo2[0].trim();
                toString = taskInfo2[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MerlieException("create the event task in the correct syntax "
                        + "(event <task> /from <start> /to <end>)");
            }

            if (description.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
                throw new MerlieException("description and date range must be non-empty!");
            }

            ParsedDate from = ParsedDate.parseDate(fromString);
            ParsedDate to = ParsedDate.parseDate(toString);

            return new EventCommand(description, from.getDate(), from.getHasTime(),
                    to.getDate(), to.getHasTime());
        }

        default:
            throw new MerlieException("");
        }
    }
}
package merlie.parser;

import merlie.exception.MerlieException;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsedDate {
    public final LocalDateTime dateTime;
    public final boolean hasTime;
    private static final DateTimeFormatter[] DATE_ONLY_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
    };
    private static final DateTimeFormatter[] TIME_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };


    public ParsedDate(LocalDateTime dateTime, boolean hasTime) {
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    public static ParsedDate parseDate(String input) throws MerlieException {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                LocalDateTime dt = LocalDateTime.parse(input, format);
                return new ParsedDate(dt, true);
            } catch (DateTimeParseException e) {
                // ignore and try next format
            }
        }

        for (DateTimeFormatter format : DATE_ONLY_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(input, format);
                return new ParsedDate(localDate.atStartOfDay(), false);  // hasTime = false
            } catch (DateTimeParseException e) {
                // ignore and try next format
            }
        }

        throw new MerlieException("invalid date format. " +
                "Use yyyy-MM-dd (HHmm), d-M-yyyy (HHmm), or d/M/yyyy (HHmm)");
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public boolean getHasTime() {
        return hasTime;
    }

}

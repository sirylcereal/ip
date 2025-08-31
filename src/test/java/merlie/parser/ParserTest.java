package merlie.parser;

import merlie.command.Command;
import merlie.exception.MerlieException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParserTest {

    @Test
    void parse_todoCommand_success() throws MerlieException {
        Command c = Parser.parse("todo Read book");
        assertEquals("TodoCommand", c.getClass().getSimpleName());
    }

    @Test
    void parse_invalidCommand_throwsException() {
        assertThrows(MerlieException.class, () -> Parser.parse("testing"));
    }
}

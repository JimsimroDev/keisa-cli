package uk.jimsimrodev.parser;

import java.util.Arrays;

public class CommandParser {

    public CommandInput parse(String comando) {

        String[] lineas = comando.split("\\s+");

        String[] args = Arrays.copyOfRange(lineas, 1, lineas.length);

        CommandInput input = new CommandInput(lineas[0], args);

        return input;
    }
}

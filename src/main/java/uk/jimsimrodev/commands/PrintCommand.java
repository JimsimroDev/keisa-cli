package uk.jimsimrodev.commands;

import uk.jimsimrodev.logica.ConsoleColors;
import uk.jimsimrodev.logica.SymbolTable;
import uk.jimsimrodev.logica.Variable;
import uk.jimsimrodev.view.TerminalPrinter;

public class PrintCommand implements Command {

    private final TerminalPrinter terminalPrinter;

    public PrintCommand(TerminalPrinter terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    @Override
    public void execute(String[] args, SymbolTable symbolTable) {

        try {
            Variable variable = symbolTable.lookupVariable(args[0]);

            terminalPrinter.animarTexto(ConsoleColors.PURPLE_LAVENDER,
                    "» " + args[0] + ConsoleColors.RESET + ConsoleColors.DEEP_DARK + " =>: " + ConsoleColors.RESET
                            + ConsoleColors.VIVID_RED + variable.value(),
                    4);

        } catch (NullPointerException e) {
            terminalPrinter.animarTexto(ConsoleColors.VIVID_RED,
                    "✕ Error: Una o ambas variables no existen en la tabla de símbolos. " + e.getMessage(), 4);

        }
    }

}

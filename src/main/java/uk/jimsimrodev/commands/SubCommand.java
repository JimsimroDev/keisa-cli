package uk.jimsimrodev.commands;

import uk.jimsimrodev.logica.ConsoleColors;
import uk.jimsimrodev.logica.SymbolTable;
import uk.jimsimrodev.logica.Variable;
import uk.jimsimrodev.view.TerminalPrinter;

public class SubCommand implements Command {

    private final TerminalPrinter terminalPrinter;

    public SubCommand(TerminalPrinter terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    @Override
    public void execute(String[] args, SymbolTable symbolTable) {

        if (args.length != 2) {
            terminalPrinter.animarTexto(ConsoleColors.VIVID_RED,
                    "✕ Error de sintaxis: SUB requiere dos variables cargadas en memoria. (Ej: SUB x y)", 4);
            return;
        }

        try {
            Variable variable = symbolTable.lookupVariable(args[0]);
            Variable variable1 = symbolTable.lookupVariable(args[1]);

            int v1 = (Integer) variable.value();
            int v2 = (Integer) variable1.value();
            int resultado = v1 - v2;

            terminalPrinter.animarTexto(ConsoleColors.PURPLE_LAVENDER,
                    "  » Resultado: " + ConsoleColors.RESET + ConsoleColors.DEEP_DARK + v1 + " - "
                            + v2 + " = " + ConsoleColors.RESET + ConsoleColors.NEON_FUCHSIA + resultado,
                    4);

        } catch (NullPointerException e) {
            terminalPrinter.animarTexto(ConsoleColors.VIVID_RED,
                    "✕ Error: Una o ambas variables no existen en la tabla de símbolos. " + e.getMessage(), 4);

        }
    }

}

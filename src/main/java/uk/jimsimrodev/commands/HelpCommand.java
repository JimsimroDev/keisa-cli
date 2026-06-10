package uk.jimsimrodev.commands;

import uk.jimsimrodev.logica.SymbolTable;
import uk.jimsimrodev.view.TerminalPrinter;

public class HelpCommand implements Command {

    private final TerminalPrinter terminalPrinter;

    public HelpCommand(TerminalPrinter terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    @Override
    public void execute(String[] args, SymbolTable symbolTable) {
        terminalPrinter.mostrarComandos();
    }
}

package uk.jimsimrodev.commands;

import uk.jimsimrodev.logica.SymbolTable;

public interface Command {
    void execute(String[] args, SymbolTable symbolTable);
}

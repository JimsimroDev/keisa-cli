package uk.jimsimrodev.commands;

import uk.jimsimrodev.logica.SymbolTable;

public class ClearCommand implements Command {

    @Override
    public void execute(String[] args, SymbolTable symbolTable) {
         System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

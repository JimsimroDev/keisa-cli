package uk.jimsimrodev.commands;

import java.util.List;

import uk.jimsimrodev.logica.ConsoleColors;
import uk.jimsimrodev.logica.SymbolTable;
import uk.jimsimrodev.logica.Variable;
import uk.jimsimrodev.view.TerminalPrinter;

public class VarsCommand implements Command {

    private final TerminalPrinter terminalPrinter;

    public VarsCommand(TerminalPrinter terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    @Override
    public void execute(String[] args, SymbolTable symbolTable) {

        List<Variable> variables = symbolTable.getAllVariable();

        if (variables.isEmpty()) {
            terminalPrinter.animarTexto(ConsoleColors.VIVID_RED,
                    "✕ No hay variables creadas en la tabla de símbolos aún.", 4);
            return;
        }

        int maxAnchoNombre = 6;
        int maxAnchoValor = 5;

        // 2. Encontrar el tamaño máximo real recorriendo la lista
        for (Variable data : variables) {
            if (data.name().length() > maxAnchoNombre) {
                maxAnchoNombre = data.name().length();
            }

            String valorStr = String.valueOf(data.value());
            if (valorStr.length() > maxAnchoValor) {
                maxAnchoValor = valorStr.length();
            }
        }

        // 3. Crear las plantillas de formato dinámicas
        String formatoCabecera = "| %-" + maxAnchoNombre + "s | %-" + maxAnchoValor + "s |\n";
        String formatoFila = "| %-" + maxAnchoNombre + "s | %-" + maxAnchoValor + "s |\n";

        // Línea separadora superior ajustada al tamaño
        int totalLineas = maxAnchoNombre + maxAnchoValor + 7;

        System.out.print(ConsoleColors.NEON_FUCHSIA);
        System.out.println("_".repeat(totalLineas) + ConsoleColors.RESET);

        // 4. Imprimir Cabecera
        System.out.print(ConsoleColors.NEON_FUCHSIA);
        System.out.printf(formatoCabecera, "Nombre", "Valor");

        System.out.println("_".repeat(totalLineas) + ConsoleColors.RESET);

        // 5. Imprimir cada variable perfectamente alineada
        for (Variable data : variables) {
            String nombre = data.name();
            String valor = String.valueOf(data.value());
            System.out.print(ConsoleColors.PURPLE_LAVENDER);
            // Se usa printf para rellenar los espacios dinámicos
            System.out.printf(formatoFila, nombre, valor);
        }

        System.out.print(ConsoleColors.PURPLE_LAVENDER);
        System.out.println("-".repeat(totalLineas) + ConsoleColors.RESET);
        System.out.println();
    }

}

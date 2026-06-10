package uk.jimsimrodev.commands;

import uk.jimsimrodev.logica.ConsoleColors;
import uk.jimsimrodev.logica.SymbolTable;
import uk.jimsimrodev.logica.Variable;
import uk.jimsimrodev.view.TerminalPrinter;

public class SetCommand implements Command {

    private final TerminalPrinter terminalPrinter;

    public SetCommand(TerminalPrinter terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    @Override
    public void execute(String[] args, SymbolTable symbolTable) {
        // 1. Ahora permitimos asignaciones directas (longitud 2) o expresiones
        // (longitud 4: variable, operando1, operador, operando2)
        if (args.length != 2 && args.length != 4) {
            terminalPrinter.animarTexto(ConsoleColors.VIVID_RED,
                    "X Sintaxis incorrecta. Usa: SET variable valor (Ej: SET x 5) o SET variable expr (Ej: SET z x + y)",
                    4);
            return;
        }

        String nombreVariableDestino = args[0];
        int valorFinal = 0;

        try {
            if (args.length == 2) {
                // CASO A: Asignación directa (Ej: SET x 5 o SET x y)
                valorFinal = evaluarOperando(args[1], symbolTable);
            } else {
                // CASO B: Expresión matemática (Ej: SET resultado x + y)
                int operando1 = evaluarOperando(args[1], symbolTable);
                String operador = args[2];
                int operando2 = evaluarOperando(args[3], symbolTable);

                valorFinal = calcularExpresion(operando1, operador, operando2);
            }

            // 2. Guardar o actualizar en la Tabla de Símbolos
            String type = "int";
            Variable variable = new Variable(type, nombreVariableDestino, valorFinal);

            try {
                symbolTable.defineVariable(variable);
            } catch (Exception e) {
                // Si ya existe la variable, usamos tu método de asignación (asumo que se
                // escribe assignVariable o assigVariable como lo tenías)
                symbolTable.assigVariable(nombreVariableDestino, valorFinal);
            }

            terminalPrinter.animarTexto(ConsoleColors.SOFT_GREEN,
                    "✓ Variable '" + nombreVariableDestino + "' guardada correctamente con valor: " + valorFinal, 4);

        } catch (Exception e) {
            terminalPrinter.animarTexto(ConsoleColors.VIVID_RED, "✕ Error: " + e.getMessage(), 4);
        }
    }

    /**
     * Evalúa un operando individual: si es un número lo parsea, si es texto busca
     * su valor en la memoria.
     */
    private int evaluarOperando(String token, SymbolTable symbolTable) throws Exception {
        try {
            // Intentamos ver si es un número puro (Ej: "5")
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            // Si no es número, asumimos que es una variable (Ej: "x")
            // Ojo: Ajusta aquí el método real que usas en tu SymbolTable para obtener una
            // variable o su valor.
            // Si tu symbolTable retorna el objeto Variable, harías:
            // symbolTable.getVariable(token).getValor();
            Variable var = symbolTable.lookupVariable(token);
            if (var != null) {
                return (Integer) var.value(); // Asegúrate de usar tu getter real (ej: .valor() si es un record)
            } else {
                throw new Exception("La variable '" + token + "' no está definida en la memoria.");
            }
        }
    }

    /**
     * Resuelve la operación aritmética básica detectando el operador.
     */
    private int calcularExpresion(int op1, String operador, int op2) throws Exception {
        return switch (operador) {
            case "+" -> op1 + op2;
            case "-" -> op1 - op2;
            case "*" -> op1 * op2;
            case "/" -> {
                if (op2 == 0)
                    throw new Exception("División por cero no permitida.");
                yield op1 / op2;
            }
            default -> throw new Exception("Operador desconocido '" + operador + "'. Usa +, -, * o /");
        };
    }
}

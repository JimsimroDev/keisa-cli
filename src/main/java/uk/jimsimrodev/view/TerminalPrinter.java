package uk.jimsimrodev.view;

import java.util.List;

import uk.jimsimrodev.logica.ConsoleColors;

public class TerminalPrinter {
    private final static String SEPARADOR = "-------------------------------------------------------";

    public void pintarTexto(ConsoleColors color, String texto) {
        System.out.println(color + texto + ConsoleColors.RESET);
    }

    public void animarBloqueLineas(ConsoleColors color, List<String> lineas, int retrasoLineasMs) {
        // Activamos tu fucsia o el color que elijas al inicio
        System.out.print(color);

        for (String linea : lineas) {
            // Imprime la fila completa de un solo golpe
            System.out.println(linea);
            System.out.flush();

            try {
                // Pausa sutil entre filas (ej. 30ms a 50ms)
                Thread.sleep(retrasoLineasMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Restablecemos el color por defecto de la terminal
        System.out.print(ConsoleColors.RESET);
    }

    public void animarTexto(ConsoleColors color, String texto, int retrasoMs) {
        // Activamos el color al inicio
        System.out.print(color);

        // Recorre el texto carácter por carácter
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            System.out.flush(); // Fuerza a la consola a pintar el carácter de inmediato

            try {
                Thread.sleep(retrasoMs); // Pausa en milisegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Si el hilo se interrumpe
            }
        }

        // Al terminar, restablecemos el color y damos un salto de línea
        System.out.println(ConsoleColors.RESET);
    }

    public void animarTextoSinSalto(ConsoleColors color, String texto, int retrasoMs) {
        // Activamos el color al inicio
        System.out.print(color);

        // Recorremos el texto carácter por carácter
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            System.out.flush(); // Fuerza a la consola a pintar el carácter de inmediato

            try {
                Thread.sleep(retrasoMs); // Pausa en milisegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Si el hilo se interrumpe
            }
        }

        // Al terminar, restablecemos el color y damos un salto de línea
        System.out.print(ConsoleColors.RESET);
    }

    public void mostrarComandos() {
        // Título de la guía
        animarTexto(ConsoleColors.PURPLE_LAVENDER, ":::: GUÍA DE COMANDOS PERMITIDOS ::::", 4);

        String plantilla = String.format("""
                %s SET  %s -> %s Crear/modificar variable. %s  (Ej: SET x 5)
                %s ADD  %s -> %s Sumar dos variables.      %s  (Ej: ADD x y)
                %s SUB  %s -> %s Resta dos variables.      %s  (Ej: SUB x y)
                %s MUL  %s -> %s Resta dos variables.      %s  (Ej: MUL x y)
                %s DIV  %s -> %s Resta dos variables.      %s  (Ej: MUL x y)
                %s HELP %s -> %s Mostrar esta guía de ayuda
                %s EXIT %s -> %s Finalizar el intérprete
                """,
                // Línea 1: SET
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET, ConsoleColors.DEEP_DARK,
                // Línea 2: ADD
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET, ConsoleColors.DEEP_DARK,
                // Línea 3: SUB
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET, ConsoleColors.DEEP_DARK,
                // Línea 4: MUL
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET, ConsoleColors.DEEP_DARK,
                // Línea 4: DIV
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET, ConsoleColors.DEEP_DARK,
                // Línea 3: HELP
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET,
                // Línea 4: EXIT
                ConsoleColors.PURPLE_LAVENDER, ConsoleColors.VIVID_RED, ConsoleColors.RESET);

        // IO.print(plantilla);
        animarTextoSinSalto(ConsoleColors.PURPLE_LAVENDER, plantilla, 1);
        pintarTexto(ConsoleColors.PURPLE_LAVENDER, SEPARADOR);
    }
}

package uk.jimsimrodev.logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import uk.jimsimrodev.commands.AddCommand;
import uk.jimsimrodev.commands.ClearCommand;
import uk.jimsimrodev.commands.Command;
import uk.jimsimrodev.commands.DivCommand;
import uk.jimsimrodev.commands.HelpCommand;
import uk.jimsimrodev.commands.MulCommand;
import uk.jimsimrodev.commands.PrintCommand;
import uk.jimsimrodev.commands.SetCommand;
import uk.jimsimrodev.commands.SubCommand;
import uk.jimsimrodev.commands.VarsCommand;
import uk.jimsimrodev.parser.CommandInput;
import uk.jimsimrodev.parser.CommandParser;
import uk.jimsimrodev.view.BannerLoader;
import uk.jimsimrodev.view.TerminalPrinter;

public class MiniScrip {

    private TerminalPrinter terminalPrinter = new TerminalPrinter();
    private CommandParser commandParser = new CommandParser();
    private final Map<String, Command> commands = registrarComandos();
    private final SymbolTable symbolTable = new SymbolTable();

    private final BannerLoader bannerLoader = new BannerLoader();

    private String config;

    // Método privado de inicialización
    private Map<String, Command> registrarComandos() {
        Map<String, Command> map = new HashMap<>();

        map.put("SET", new SetCommand(terminalPrinter));
        map.put("ADD", new AddCommand(terminalPrinter));
        map.put("SUB", new SubCommand(terminalPrinter));
        map.put("MUL", new MulCommand(terminalPrinter));
        map.put("DIV", new DivCommand(terminalPrinter));
        map.put("PRINT", new PrintCommand(terminalPrinter));
        map.put("VARS", new VarsCommand(terminalPrinter));
        map.put("HELP", new HelpCommand(terminalPrinter));
        map.put("CLEAR", new ClearCommand());

        return map;
    }

    public void iniciar() {

        config = bannerLoader.cargarBanner();

        try (Scanner sc = new Scanner(System.in)) {

            terminalPrinter.animarTexto(ConsoleColors.NEON_FUCHSIA, config, 1);

            String lineaLimpia = "";

            while (true) {

                terminalPrinter.animarTextoSinSalto(ConsoleColors.PURPLE_LAVENDER, "-> ", 4);

                String linea = sc.nextLine();
                lineaLimpia = linea.trim();

                if (lineaLimpia.isEmpty()) {
                    continue;
                }

                if ("EXIT".equals(lineaLimpia.toUpperCase())) {
                    terminalPrinter.animarTexto(ConsoleColors.PURPLE_LAVENDER, "Finalizando programa....", 4);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    terminalPrinter.animarTexto(ConsoleColors.PURPLE_LAVENDER,
                            "Programa Finalizado con exíto",
                            4);
                    break;
                }
                try {
                    CommandInput commandInput = commandParser.parse(lineaLimpia.toUpperCase());
                    despachar(commandInput);

                } catch (Exception e) {
                    terminalPrinter.animarTexto(ConsoleColors.VIVID_RED, "X Error en ejución " + e.getMessage(), 4);
                }
            }
        }

    }

    private void despachar(CommandInput input) {
        Command command = commands.get(input.instruction());
        if (command != null) {
            command.execute(input.argumentos(), symbolTable);
            return;

        }

        terminalPrinter.animarTexto(ConsoleColors.VIVID_RED,
                "X Comando desconocido: " + input.instruction() + ", usa help para ver comando perimitidos", 4);
    }
}

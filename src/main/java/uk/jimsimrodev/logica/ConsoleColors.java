package uk.jimsimrodev.logica;

public enum ConsoleColors {
    RESET("\033[0m"),

    // Colores insignia
    NEON_FUCHSIA("\033[38;5;198m"), // El rosa vibrante
    PURPLE_LAVENDER("\033[38;5;141m"), // morado

    // Colores de estado
    SOFT_GREEN("\033[38;5;84m"), // Para éxitos (✓)
    VIVID_RED("\033[38;5;196m"), // Para errores (✕)
    DEEP_DARK("\033[90m"); // Para texto secundario o bordes

    private final String code;

    ConsoleColors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}

package uk.jimsimrodev.logica;

/**
 * Representa una variable dentro del intérprete.
 * Almacena información sobre su tipo, nombre, valor actual y la línea donde fue
 * definida.
 *
 * @param type  El tipo de dato de la variable.
 * @param name  El nombre identificador de la variable.
 * @param value El valor almacenado en la variable.
 * @param line  La línea de código donde se originó la variable.
 * @author Jimmis J Simanca
 */
public record Variable(
                String type,
                String name,
                Object value) {
}

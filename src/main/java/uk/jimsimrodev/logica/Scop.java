package uk.jimsimrodev.logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa un ámbito (scope) o contexto de ejecución en el intérprete.
 * Gestiona un conjunto de variables locales dentro de este ámbito.
 *
 * @author Jimmis J Simanca
 */
public class Scop {
    private Map<String, Variable> variables = new HashMap<>();

    /**
     * Agrega una nueva variable al ámbito actual.
     *
     * @param v La variable a agregar.
     * @throws IllegalArgumentException Si la variable ya existe en el ámbito
     *                                  actual.
     */
    public void addVarible(Variable v) {

        if (isExistVarialble(v.name())) {
            throw new IllegalArgumentException(String.format(
                    "logica.Variable '%s' ya está definida en el scop", v.name()));
        }
        variables.put(v.name(), v);
    }

    /**
     * Actualiza el valor de una variable existente en este ámbito.
     *
     * @param name El nombre de la variable a actualizar.
     * @param v    El nuevo objeto Variable con el valor actualizado.
     */
    public void updateVariable(String name, Variable v) {
        variables.put(name, v);
    }

    // ✔ verificar si ya existe (IMPORTANTE)
    private boolean isExistVarialble(String name) {

        return variables.containsKey(name);
    }

    /**
     * Busca y retorna una variable por su nombre en el ámbito actual.
     *
     * @param name El nombre de la variable a buscar.
     * @return La variable encontrada o null si no existe en este ámbito.
     */
    public Variable getVariable(String name) {

        return variables.get(name);
    }

    /**
     * Retorna todas las variables definidas en el ámbito actual.
     *
     * @return Una colección con todas las variables de este ámbito.
     */
    public Collection<Variable> getAllVariable() {
        return variables.values();
    }
}

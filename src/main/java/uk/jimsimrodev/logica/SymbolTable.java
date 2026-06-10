package uk.jimsimrodev.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Gestiona la tabla de símbolos del intérprete utilizando una pila de ámbitos
 * (scopes).
 * Permite la gestión de variables locales y globales siguiendo las reglas de
 * visibilidad.
 *
 * @author Jimmis J Simanca
 */
public class SymbolTable {
    private Stack<Scop> pilaScop;

    /**
     * Inicializa una nueva tabla de símbolos con un ámbito global base.
     */
    public SymbolTable() {
        pilaScop = new Stack<>();
        pilaScop.push(new Scop());
    }

    /**
     * Crea y entra en un nuevo ámbito de ejecución.
     */
    public void enterScope() {
        pilaScop.push(new Scop());
    }

    /**
     * Sale del ámbito actual y regresa al ámbito padre.
     *
     * @throws IllegalStateException Si se intenta salir del ámbito global.
     */
    public void exitScope() {
        if (pilaScop.size() <= 0) {
            throw new IllegalStateException("Error semántico: no se puede salir del ámbito global");
        }
        pilaScop.pop();
    }

    /**
     * Define una nueva variable en el ámbito actual (en el tope de la pila).
     *
     * @param v La variable a definir.
     */
    public void defineVariable(Variable v) {
        // me trae el scop del tope
        pilaScop.peek().addVarible(v);

    }

    /**
     * Busca una variable por su nombre en los ámbitos visibles, desde el más
     * interno al global.
     *
     * @param name El nombre de la variable a buscar.
     * @return La variable encontrada.
     * @throws IllegalArgumentException Si la variable no se encuentra en ningún
     *                                  ámbito visible.
     */
    public Variable lookupVariable(String name) {// busca desde el scop más interno hasta el global

        for (int n = pilaScop.size() - 1; n >= 0; n--) {
            Scop scop = pilaScop.get(n);
            Variable variable = scop.getVariable(name);

            if (variable != null) {
                return variable;
            }
        }
        throw new IllegalArgumentException("logica.Variable not found");
    }

    /**
     * Asigna un nuevo valor a una variable existente buscando en los ámbitos
     * visibles.
     *
     * @param name       El nombre de la variable.
     * @param nuevoValor El nuevo valor a asignar.
     * @throws IllegalArgumentException Si la variable no existe en ningún ámbito
     *                                  visible.
     */
    public void assigVariable(String name, Object nuevoValor) {
        int line = 0;
        for (int n = pilaScop.size() - 1; n >= 0; n--) {

            Scop scop = pilaScop.get(n);
            Variable variable = scop.getVariable(name);

            if (variable != null) {
                variable = new Variable(
                        variable.type(),
                        variable.name(),
                        nuevoValor);
                scop.updateVariable(name, variable);

                return;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Error La variable %s no esta definida. Debes crear la varialbe local. Error en la liena %s\n ", name,
                line));
    }

    /**
     * Retorna una lista con todas las variables definidas en todos los ámbitos
     * actuales.
     *
     * @return Una lista de todas las variables visibles y no visibles.
     */
    public List<Variable> getAllVariable() {// busca desde el scop más interno hasta el global
        List<Variable> variables = new ArrayList<>();
        for (int n = pilaScop.size() - 1; n >= 0; n--) {
            Scop scop = pilaScop.get(n);

            variables.addAll(scop.getAllVariable());

        }
        return variables;
    }
}

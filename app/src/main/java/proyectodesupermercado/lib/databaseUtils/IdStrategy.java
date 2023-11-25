package proyectodesupermercado.lib.databaseUtils;

/**
 * With manual, the Id field is going to be taken into account when inserting a new entry.
 * With identity, the Id field is ignore when inserting a new entry.
 */
public enum IdStrategy {
    MANUAL, IDENTITY
}

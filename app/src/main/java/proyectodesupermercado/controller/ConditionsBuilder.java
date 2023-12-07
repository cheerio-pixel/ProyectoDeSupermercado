package proyectodesupermercado.controller;

import java.util.LinkedList;
import java.util.List;

public class ConditionsBuilder {
    private final List<String> conditions = new LinkedList<>();
    private final List<Object> params = new LinkedList<>();
    private final String body;
    private String atLast;

    /**
     * @param body El cuerpo de la expresion principal, sea un SELECT, INSERT y los demas
     */
    public ConditionsBuilder(String body) {
        this.body = body;
    }

    public ConditionsBuilder setAtLast(String atLast) {
        this.atLast = atLast;
        return this;
    }

    public String commitConditions(String delimiter) {
        if (conditions.isEmpty()) {
            return body + ((atLast != null) ? atLast : "");
        } else {
            return body + " WHERE " + String.join(
                    " " + delimiter + " ",
                    conditions
            ) + ((atLast != null) ? atLast : "");
        }
    }

    public List<Object> getParams() {
        return params;
    }

    public ConditionsBuilder addConditionIf(
            boolean conditional,
            String condition,
            Object params
    ) {
        if (conditional) {
            addCondition(condition, params);
        }
        return this;
    }

    public ConditionsBuilder addCondition(
            String condition,
            Object params
    ) {
        conditions.add(condition);
        this.params.add(params);
        return this;
    }

    public ConditionsBuilder addConditionIf(
            boolean conditional,
            String condition,
            Object... params
    ) {
        if (conditional) {
            addCondition(condition, params);
        }
        return this;
    }

    public ConditionsBuilder addConditionOrElse(
            boolean conditional,
            String conditionIf,
            List<Object> paramsIf,
            String conditionElse,
            List<Object> paramsElse
    ) {
        if (conditional) {
            addCondition(conditionIf, paramsElse);
        } else {
            addCondition(conditionElse, paramsElse);
        }
        return this;
    }

    public ConditionsBuilder addCondition(
            String condition,
            Object... params
    ) {
        addCondition(condition, List.of(params));
        return this;
    }

    public ConditionsBuilder addCondition(
            String condition,
            List<Object> params
    ) {
        conditions.add(condition);
        this.params.addAll(params);
        return this;
    }
}

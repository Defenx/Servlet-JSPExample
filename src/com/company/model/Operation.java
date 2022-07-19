package com.company.model;

import com.company.interfaces.Actionable;

public enum Operation {
    SUM((Double::sum)),
    MIN(Double::min),
    MAX(Double::max),
    AVG(Double::sum);

    private final Actionable actionable;

    Operation(Actionable actionable) {
        this.actionable = actionable;
    }

    public double getResult(double[] numbers) {

        double result = 0;

        for (double number : numbers) {
            result = actionable.getResult(result,number);
        }

        if(this == Operation.AVG){
            result /= numbers.length;
        }

        return result;
    }
}

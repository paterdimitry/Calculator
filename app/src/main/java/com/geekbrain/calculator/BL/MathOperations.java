package com.geekbrain.calculator.BL;

import java.io.Serializable;

public class MathOperations implements Serializable {

    private Double firstArg = null;
    private Double secondArg;

    //operationFlag показывает выбранную пользователем операцию.
    //Значения берутся из ENUM Operation
    private Operation operationFlag = Operation.NULL;

    public void setFirstArg(NumberBuilder numberBuilder) {
        if (operationFlag == Operation.NULL) {
            if (numberBuilder.isNotEmpty())
                firstArg = numberBuilder.getNumber();
            else
                firstArg = (double) 0;
            numberBuilder.clear();
        }
    }

    public void setSecondArg(double secondArg) {
        this.secondArg = secondArg;
    }

    public void setOperationFlag(Operation operationFlag) {
        this.operationFlag = operationFlag;
    }

    //Получение результата
    public String getResult() {
        Double result;
        //в зависимости от состояния operationFlag выполняются соответствующие действия
        switch (operationFlag) {
            case INDUCTION:
                result = firstArg + secondArg;
                break;
            case DEDUCTION:
                result = firstArg - secondArg;
                break;
            case MULTIPLY:
                result = firstArg * secondArg;
                break;
            case DIVISION:
                if (secondArg != 0) {
                    result = firstArg / secondArg;
                    break;
                } else {
                    clearAll();
                    return "DIV by ZERO";
                }
            case NULL:
                result = firstArg;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operationFlag);
        }
        firstArg = result;
        return result.toString();
    }

    public void clearAll() {
        firstArg = null;
        secondArg = null;
        operationFlag = Operation.NULL;
    }

    public boolean isOperationNull() {
        return operationFlag == Operation.NULL;
    }

    public boolean isFirstArgNull() {
        return firstArg == null;
    }
}

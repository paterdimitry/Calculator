package com.geekbrain.calculator.BL;

import android.widget.EditText;

import java.io.Serializable;

/* Класс предназначен для составления чисел.*/

public class NumberBuilder implements Serializable {
    double number;
    String stringNumber = "";

    public NumberBuilder() {
        this.number = 0.0;
    }

    public double getNumber() {
        number = Double.parseDouble(stringNumber);
        stringNumber = "";
        return number;
    }

    //Добавление цифры. Исключается "0" в начале числа
    public void appendNumber(String n, EditText textView) {
        if (stringNumber.length() == 0 && n.equals("0"))
            return;
        else
            stringNumber = stringNumber + n;
        textView.setText(stringNumber);
    }

    //Добавление разделителя.
    // Если разделитель уже есть, то второй не ставится
    // В случае пустой строки, сначала добавляется "0."
    public void appendPointer(EditText textView) {
        if (!stringNumber.contains("."))
            if (stringNumber.length() != 0)
                stringNumber = stringNumber + ".";
            else
                stringNumber = "0.";
        textView.setText(stringNumber);
    }

    //Замена знака +/-
    //При нуле или пустой строке знак не меняется
    //Осуществляется добавлением или стиранием первым символом в строке "-"
    public void changeSign(EditText textView) {
        if (stringNumber.length() != 0 && !stringNumber.equals("0"))
            if (stringNumber.contains("-"))
                stringNumber = stringNumber.substring(1);
            else
                stringNumber = "-" + stringNumber;
        else
            return;
        textView.setText(stringNumber);
    }

    //Очистка строки
    public void clear() {
        stringNumber = "";
    }

    //Првоерка строки на пустоту
    public boolean isNotEmpty() {
        return stringNumber.length() != 0;
    }
}

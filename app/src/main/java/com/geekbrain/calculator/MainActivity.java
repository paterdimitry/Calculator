package com.geekbrain.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.geekbrain.calculator.BL.MathOperations;
import com.geekbrain.calculator.BL.NumberBuilder;
import com.geekbrain.calculator.BL.Operation;

public class MainActivity extends AppCompatActivity {

    private EditText textView;
    private NumberBuilder numberBuilder;
    private MathOperations mathOperations;
    private Operation operation;

    private static final String THEME = "Settings";

    private final String mathOperationKey = "mathOperationKey";
    private final String numberBuilderKey = "numberBuilderKey";
    private final String textViewKey = "textViewKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getIntent().getIntExtra(THEME, R.style.MatrixStyle));
        setContentView(R.layout.activity_main);

        initView();
        initViewSettingsButton();
    }

    private void initViewSettingsButton() {
        Button viewSettingsButton = findViewById(R.id.viewSettings);
        viewSettingsButton.setOnClickListener(v ->{
            Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(runSettings);
        });
    }

    //Для адаптации поворота экрана сохраняем состояние до переворота
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(mathOperationKey, mathOperations);
        outState.putSerializable(numberBuilderKey, numberBuilder);
        outState.putString(textViewKey, textView.getText().toString());
    }

    //Восстанавливаем состояние после поворота экрана
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mathOperations = (MathOperations) savedInstanceState.getSerializable(mathOperationKey);
        numberBuilder = (NumberBuilder) savedInstanceState.getSerializable(numberBuilderKey);
        textView.setText(savedInstanceState.getString(textViewKey));
    }

    private void initView() {
        textView = findViewById(R.id.text);
        textView.setText("0");
        operation = Operation.NULL;
        numberBuilder = new NumberBuilder();
        mathOperations = new MathOperations();
        initButtons();
    }

    //Инициализация клавиш
    private void initButtons() {
        //Клавиши с цифрами
        initNumberButton(R.id.button_0, "0");
        initNumberButton(R.id.button_1, "1");
        initNumberButton(R.id.button_2, "2");
        initNumberButton(R.id.button_3, "3");
        initNumberButton(R.id.button_4, "4");
        initNumberButton(R.id.button_5, "5");
        initNumberButton(R.id.button_6, "6");
        initNumberButton(R.id.button_7, "7");
        initNumberButton(R.id.button_8, "8");
        initNumberButton(R.id.button_9, "9");

        //функциональные клавиши
        initPointerButton();
        initClearButton();
        initEqualButton();
        initChangeSignButton();

        //клавиши операций
        initOperationButton(R.id.induction, Operation.INDUCTION);
        initOperationButton(R.id.deduction, Operation.DEDUCTION);
        initOperationButton(R.id.multiply, Operation.MULTIPLY);
        initOperationButton(R.id.division, Operation.DIVISION);
    }

    //клавиша обнуляет состояние калькулятора
    private void initClearButton() {
        Button buttonClear = findViewById(R.id.clear);
        buttonClear.setOnClickListener(v -> {
            textView.setText("0");
            numberBuilder.clear();
            mathOperations.clearAll();
        });
    }

    //Клавиша равенства. осуществляет вычисление введенных данных
    private void initEqualButton() {
        Button buttonEqual = findViewById(R.id.equal);
        buttonEqual.setOnClickListener(v -> {
            if (numberBuilder.isNotEmpty())
                mathOperations.setSecondArg(numberBuilder.getNumber());
            if (!mathOperations.isOperationNull()) {
                textView.setText(mathOperations.getResult());
                mathOperations.setOperationFlag(Operation.NULL);
            }
        });
    }

    //клавиша смены знака
    private void initChangeSignButton() {
        Button buttonSign = findViewById(R.id.sign);
        buttonSign.setOnClickListener(v -> numberBuilder.changeSign(textView));
    }

    //клавиши операций
    //Осуществлена возможность выполнения цепочки вычислений без нажатия клавиши "="
    private void initOperationButton(int p, Operation operation) {
        Button buttonInduction = findViewById(p);
        buttonInduction.setOnClickListener(v -> {
            if (mathOperations.isOperationNull()) {
                if (mathOperations.isFirstArgNull()) {
                    mathOperations.setFirstArg(numberBuilder);
                }
            } else {
                if (numberBuilder.isNotEmpty())
                    mathOperations.setSecondArg(numberBuilder.getNumber());
                textView.setText(mathOperations.getResult());
            }
            mathOperations.setOperationFlag(operation);
        });
    }

    //разделитель
    private void initPointerButton() {
        Button buttonPointer = findViewById(R.id.pointer);
        buttonPointer.setOnClickListener(v -> numberBuilder.appendPointer(textView));
    }

    //клавиша добавления цифры
    private void initNumberButton(int p, String s) {
        Button button = findViewById(p);
        button.setOnClickListener(v -> numberBuilder.appendNumber(s, textView));
    }
}
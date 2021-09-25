package by.bsuir.karinincalc;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    TextView operationField;    // тексππтовое поле для вывода знака операции
    Double operand = null;  // операнд операции
    String[] history  = new String[4];
    String strKey1 = "resultField";
    String strKey2 = "numberField";
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        resultField =(TextView) findViewById(R.id.resultField);
        numberField = (EditText) findViewById(R.id.numberField);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (resultField != null)
        outState.putString("resultField", String.valueOf(resultField));
        outState.putString("numberField", String.valueOf(numberField));
        super.onSaveInstanceState(outState);
    }
    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand = savedInstanceState.getDouble(strKey1);
        resultField.setText(savedInstanceState.getString("resultField"));
        numberField.setText(savedInstanceState.getString("numberField"));
    }
    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view){
        Button button = (Button)view;
        numberField.append(button.getText());
    }
    public void onClear(View view){
        numberField.setText("");
        resultField.setText("");
    }
    public void equalBtn(View view){
        String userExpresion = String.valueOf(numberField.getText());
        Expression e = new Expression(userExpresion);
        System.out.println(e.calculate());
        String result = String.valueOf(e.calculate());
        numberField.setText(result);
        index += 1;
        history[index] = (userExpresion + "=" + result);
        for(int i=1; i<= index; i++){
            resultField.append(history[i] + "\n");
        }
    }
    }




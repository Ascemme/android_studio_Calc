package by.bsuir.calc_rad;

import static java.util.Collections.list;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
//RecyclerView

public class MainActivity extends AppCompatActivity {
    //private static Object String;/////мб не надо

    TextView historyOut; // текстовое поле для вывода результата
    EditText display;   // поле для ввода числа
    String operand = null;  // операнд операции
    int index = 0;
    List <String> list = new ArrayList <>();// по умолчанию 10
    LinearLayout linearLayout;
    ScrollView scroll;
    SharedPreferences sPref;
    String key1 = "key1";
    String key2 ="key2";
    Set<String> lister = new HashSet<String>();
    public String car = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        display = (EditText) findViewById(R.id.inPut);
        display.setShowSoftInputOnFocus(false);
        linearLayout = (LinearLayout) findViewById(R.id.llContainer);
        scroll = (ScrollView) findViewById(R.id.scroll);
        try {
            onLoader();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


//выход из приложения
    @Override
    protected void onDestroy() {
        super.onDestroy();
        onSaver();
    }
    //сохранение при повороте
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("str",display.getText().toString());
        outState.putStringArrayList(key2, (ArrayList<String>) list);
        onSaver();

        super.onSaveInstanceState(outState);
    }
    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand = savedInstanceState.getString("str");
        display.setText("");
        display.setText(operand);
        display.setSelection(operand.length());
        ArrayList<String> list2= savedInstanceState.getStringArrayList(key2);
        System.out.println(list2);
        for (String i : list2){
            if (i != ""){
            updateHistory(i.toString());
        }}
    }
    //сохранение данных в памяти
    private void onSaver(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed =sPref.edit();
        ed.putStringSet(key1, lister);
        ed.putString("str",display.getText().toString());
        ed.commit();
    }//выгруска из памяти при загрузки приложения
    private void onLoader(){
        sPref = getPreferences(MODE_PRIVATE);
        Set<String> sevedText = sPref.getStringSet(key1, new HashSet<String>());
        System.out.print(sevedText);
        display.setText("");
        display.setText(sPref.getString("str",""));
        for(String res : sevedText) {
            updateHistory(res.toString());

        }

    }//вывод текста
    private void updateText(String addText){
        String oldText = display.getText().toString();
        int corsorPos = display.getSelectionStart();
        String leftStr = oldText.substring(0, corsorPos);
        String rightStr = oldText.substring(corsorPos);
        if ("0".equals(display.getText().toString())){
            display.setText(addText);

        }else{
            display.setText(String.format("%s%s%s", leftStr, addText , rightStr));
            display.setSelection(corsorPos + 1);
        }
    }



    // обработка нажатия на числовую кнопку
    public void onNumberClick(View v) {
        Button button = (Button) v;
        updateText(button.getText().toString());

    }



    public void backSpace(View view) {
        int cousor = display.getSelectionStart();
        int textLeg = display.getText().length();
        if (cousor != 0 && textLeg != 0) {
            SpannableStringBuilder selecion = (SpannableStringBuilder) display.getText();
            selecion.replace(cousor - 1, cousor, "");
            display.setText(selecion);
            display.setSelection(cousor -1);
        }}



    public void onClear(View view){
        display.setText("");
    }



        public void sin_btn(View view){
            String str = "sin(rad(";
            updateText(str);

            display.setSelection(display.getText().toString().length());
        }

        public void cos_btn(View view){
            String str = "cos(rad(";
            updateText(str);
            display.setSelection(display.getText().toString().length());
    }
         public void tg_btn(View view){
            String str = "cos(rad(";
            updateText(str);
             display.setSelection(display.getText().toString().length());
    }
        public void arg_btn(View view){
            String str = "arg(rad(";
            updateText(str);
            display.setSelection(display.getText().toString().length());
        }
        public void ln_btn(View view){
            String str = "ln(";
            updateText(str);
            display.setSelection(display.getText().toString().length());
        }
        public void pi_btn(View view){
            String str = "pi";
            updateText(str);
            display.setSelection(display.getText().toString().length());
        }
        public void pers_btn(View view){
            String str = "%%%";

        }

//вывод истории
        public void updateHistory( String args){
        index +=1;
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setTextColor(0xff66ff66); // hex color 0xAARRGGBB
            textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
            textView1.setText(args);
            list.add(args);
            linearLayout.addView(textView1);
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }//вывод новой истории
        public void updateHS( String args) {
        lister.add(args + "=" + display.getText());
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setTextColor(0xff66ff66); // hex color 0xAARRGGBB
            textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
            textView1.setText(args + "=" + display.getText());
            linearLayout.addView(textView1);
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }


//равно
    public void equalBtn (View view){
            String userExpresion = String.valueOf(display.getText());
            Expression e1 = new Expression(userExpresion);
            String result = String.valueOf(e1.calculate());
            if (result.equals("NaN")){
                display.setText("Error");
            }
            else{
                updateHS(result);
                display.setText(result);
                display.setSelection(result.length());
            }
        }

    }
/*

    public void onPar(View view) {
        int openPar = 0;
        int closePar = 0;
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) ;
            {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) ;
            {
                closePar += 1;
            }
        }
        if (openPar == closePar || display.getText().toString().substring(textLen -1 , textLen).equals("(")){
            updateText("(");
        }
       else  if (closePar < openPar && !display.getText().toString().substring(textLen -1 , textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(closePar +1 );
    }

*/

/*
  public  void Str(String args) {    //на лк через массив со скроллингом

        list.add(display.getText() + "=" + args +  "\n");
        System.out.print(list.toString());

       // list.clear();
       }
*/



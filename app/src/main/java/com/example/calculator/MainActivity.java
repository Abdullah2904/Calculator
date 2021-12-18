package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
       display.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(getString(R.string.display).equals(display.getText().toString())){
                   display.setText("");
               }
           }
       });
    }
    private  void updatetext(String stradd){
        String oldstr = display.getText().toString();
        int cursorpos = display.getSelectionStart();
        String leftstr = oldstr.substring(0,cursorpos);
        String rightstr = oldstr.substring(cursorpos);
        display.setText(String.format("%s%s%s",leftstr,stradd,rightstr));
        display.setSelection(cursorpos+1);
    }
    public void zero(View view){
    updatetext("0");
    }
    public void one(View view){
        updatetext("1");

    }
    public void  two(View view ){
      updatetext("2");
    }
    public void three(View view){
         updatetext("3");
    }
    public void four(View view){
        updatetext("4");
    }
    public void five(View view){
     updatetext("5");
    }
    public void six(View view){
      updatetext("6");
    }
    public void seven(View view){
     updatetext("7");
    }
    public void eight(View view){
        updatetext("8");

    }
    public void nine(View view){
        updatetext("9");
    }
    public void add(View view){
       updatetext("+");
    }
    public void sub(View view){
      updatetext("-");
    }
    public void mul(View view){
      updatetext("x");
    }
    public void divide(View view){
       updatetext("/");
    }
    public void dot(View view){
        updatetext(".");
    }
    public void equal(View view){
        String userexpres  = display.getText().toString();
        userexpres = userexpres.replaceAll("%", "/");
        userexpres = userexpres.replaceAll("x","*");

        Expression expression = new Expression(userexpres);
        String result = String.valueOf(expression.calculate());

        display.setText(result);
        display.setSelection(result.length());//end of result
    }
    public void paren(View view){
         int cursorpos = display.getSelectionStart();
         int openpar = 0;
         int closepar = 0;
         int textlen = display.getText().length();
        for (int i=0;i<cursorpos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openpar+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closepar+=1;
            }
        }

        if(openpar==closepar || display.getText().toString().substring(textlen-1,textlen).equals("(")){
            updatetext("(");
            display.setSelection(cursorpos+1);
        }
        else if(closepar < openpar && !display.getText().toString().substring(textlen-1,textlen).equals(")")){
            updatetext(")");
            display.setSelection(cursorpos+1);
        }
    }
    public void exp(View view){
        updatetext("^");

    }
    public void plusminus(View view){

    }
    public void clear(View view){
        display.setText(" ");
    }
    public void backspace(View view){
        int cursorpos = display.getSelectionStart();
        int textlen = display.getText().length();
        if(cursorpos!=0 && textlen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorpos-1,cursorpos," ");
            display.setText(selection);
            display.setSelection(cursorpos-1);
        }


    }


}
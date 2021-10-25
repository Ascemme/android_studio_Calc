package com.ascemme.questkarinf;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ascemme.questkarinf.UI.game;
import com.ascemme.questkarinf.UI.loging;
import com.ascemme.questkarinf.UI.Start;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangerFragment(0);

    }

    public void ChangerFragment(int i){
        Fragment fragment;
        switch (i){
            case 1:
                fragment = new game();
                break;
            case 2:
                fragment = new loging();
                break;
            default:
                fragment = new Start();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_main,fragment);
        ft.commit();

    }



    public void alert(int message, int gameLeft) {
        String messager = "Правльный ответ " + message;
        String gemesLeft= "Пройдено игр " + gameLeft + " из 10";
        AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)
                .setTitle(gemesLeft)
                .setMessage(messager)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        dlg.show();
    }

}

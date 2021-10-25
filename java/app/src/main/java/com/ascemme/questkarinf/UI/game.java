package com.ascemme.questkarinf.UI;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.ascemme.questkarinf.MainActivity;
import com.ascemme.questkarinf.R;
import com.ascemme.questkarinf.model.GameAdapter;
import com.ascemme.questkarinf.model.ListOfValues;
import com.ascemme.questkarinf.model.Logic;
import com.ascemme.questkarinf.model.ModelText;

import java.util.ArrayList;
import java.util.Random;

public class game extends Fragment {



    int answers = 0;
    int gamesLeft = 0;
    ImageView imageView;
    public static ArrayList<ModelText> textList = new ArrayList <ModelText>();
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    int trueAnswer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = getView().findViewById(R.id.flag);
        gamer();
    }

    private void gamer(){
        setupData();
        setupList();
        setupOnclickListener();
    }


    private void setupData()
    {   Logic logic = new Logic(1);
        imageView.setImageResource(logic.getImageValue());
        textList.clear();
        ModelText btnFerst = new ModelText(logic.getFirstBtn(),0);
        textList.add(btnFerst);
        ModelText btnSecond = new ModelText(logic.getSecondBtn(),1);
        textList.add(btnSecond);
        ModelText btnThird = new ModelText(logic.getThirdBtn(),2);
        textList.add(btnThird);
        ModelText btnFourth = new ModelText(logic.getFourthBtn(),3);
        textList.add(btnFourth);
        trueAnswer = logic.getAnswerIndex();
        System.out.println("answer is "+trueAnswer);




    }

    private void setupList(){
        listView = (ListView) getView().findViewById(R.id.ListView);
        GameAdapter adapter = new GameAdapter(getActivity().getApplicationContext(),0, textList);
        listView.setAdapter(adapter);
        ObjectAnimator appearList = ObjectAnimator
                .ofFloat(listView, "alpha", 0, 1)
                .setDuration(2000);
        appearList.start();
        ObjectAnimator appearImage = ObjectAnimator
                .ofFloat(imageView, "alpha", 0, 1)
                .setDuration(1000);
        appearImage.start();



    }

    private void setupOnclickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ModelText selectedShape = (ModelText) (listView.getItemAtPosition(position));
                System.out.println(selectedShape.getName());
                System.out.println(selectedShape.getId());

                System.out.println("real answer is "+ trueAnswer + " but yor answer is " + selectedShape.getId());

                if (trueAnswer == selectedShape.getId()){
                    answers += 1;
                }else
                {
                    answers -= 1;
                    if (answers < 0){
                        answers = 0;
                    }
                }
                gamesLeft += 1;
                ((MainActivity) getActivity()).alert(answers, gamesLeft);
                if (gamesLeft > 10 ){
                    ((MainActivity) getActivity()).ChangerFragment(0);
                }



                gamer();
            }
        });
    }


}

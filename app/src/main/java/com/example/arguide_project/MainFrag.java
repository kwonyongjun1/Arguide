package com.example.arguide_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.net.URL;

public class MainFrag extends Fragment {

    private View view;
    private Activity activity;
    private TextView today;
    private URL url;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main_frag, container,false);

        Document doc = null;
        try{
            doc = (Document) Jsoup.connect("https://github.com/kwonyongjun1/Arguide.git").get();
        }catch (IOException e){
            e.printStackTrace();
        }
        return view;
    }
}
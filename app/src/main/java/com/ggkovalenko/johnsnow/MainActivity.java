package com.ggkovalenko.johnsnow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ggkovalenko.johnsnow.ui.list.CharacterListFragment;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, CharacterListFragment.newInstance(), CharacterListFragment.TAG)
                .commit();
    }

}

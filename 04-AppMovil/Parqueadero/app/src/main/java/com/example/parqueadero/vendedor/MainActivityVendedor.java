package com.example.parqueadero.vendedor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parqueadero.MainActivity;
import com.example.parqueadero.R;
import com.example.parqueadero.administrador.MainActivityAdmin;

public class MainActivityVendedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendedor);
    }

}
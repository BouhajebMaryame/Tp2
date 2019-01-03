package com.example.maryamebouhajeb.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "hello";
    public RadioButton radio1 ;
    public CheckBox check1 ;
    public EditText montant;
    public TextView result;
    public Button btn;
    public RadioGroup group ;
    public Button   menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Hello");
        btn = (Button)findViewById(R.id.convertir);
        montant = (EditText)findViewById(R.id.editText);
        check1 = (CheckBox)findViewById(R.id.checkBox);
        group = (RadioGroup)findViewById(R.id.group);
        result = (TextView)findViewById(R.id.textView3);
        menu = (Button)findViewById(R.id.menu);
        btn.setOnClickListener(convertirListener);
        montant.addTextChangedListener(textWatcher);
        check1.setOnClickListener(checkedListener);
        registerForContextMenu(menu);
    }

    private TextWatcher textWatcher;

    {
        textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                result.setText("");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

        private View.OnClickListener razListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montant.getText().clear();
                result.setText("");
            }
        };

    private View.OnClickListener checkedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // On remet le texte par défaut si c'était le texte de la test fonction qui était écrit
            if(!((CheckBox)v).isChecked() && result.getText().equals("test checkbox"))
                result.setText("");
        }
    };
    private float dirhamToEuro(float valeurDirham){

        return (float) (valeurDirham * 9.30);
    }
    private float euroToDirham(float valeurEuro) {
        return (float) (valeurEuro * 1/9.30);
    }
    private View.OnClickListener convertirListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!check1.isChecked()) {
                String m = montant.getText().toString();
                float mValue = Float.valueOf(m);

                if(group.getCheckedRadioButtonId() == R.id.dirham)
                    mValue= dirhamToEuro( mValue);
                else mValue= euroToDirham( mValue);;
                result.setText("Le montant apres la conversion: " + String.valueOf(mValue));
            } else
                result.setText("test check");
        }


    };


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_1:
                Toast.makeText(this, "item 1",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_2:
                Toast.makeText(this,"item 2",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_3:
                Toast.makeText(this,"item 3",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }

    }

    }




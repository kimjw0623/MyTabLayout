package com.sauce.mytablayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListViewAdd extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Button add1 = (Button) findViewById(R.id.add);

        final EditText edittext=(EditText)findViewById(R.id.name2);
        final EditText edittext1=(EditText)findViewById(R.id.num2);
        //final TextView textView=(TextView)findViewById(R.id.name2);
        //final TextView textView1=(TextView)findViewById(R.id.num2);

        add1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent data = new Intent();
                data.putExtra("name",edittext.getText());
                data.putExtra("phone",edittext1.getText());
                setResult(0,data);
                finish();
            }
        });

    }
}

package com.example.a09_pozycjonowanie_elementow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText num = (EditText) findViewById(R.id.editText);
        Button ch = (Button) findViewById(R.id.buttonRead);
        TelephonyManager operator = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Button save = (Button) findViewById(R.id.buttonSave);

        final String myID = ""; //When Reading The File Back, I Need To Store It In This String For Later Use

        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Get Text From EditText "ID" And Save It To Internal Memory
            }
        });

        ch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Read From The Saved File Here And Append It To String "myID"


                String hash = Uri.encode("#");
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:*141*" + /*Use The String With Data Retrieved Here*/ num.getText()
                        + hash));
                startActivity(intent);
            }
        });
    }
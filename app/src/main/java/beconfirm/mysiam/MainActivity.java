package beconfirm.mysiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //explicits
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial View
        InitialView();

        //Textview Controlled
        textviewControlled();

        //Button Controlled
        buttonControlled();


    } //method Main

    private void buttonControlled() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Value From Edit Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString();

                //Check Space
                if (userString.length()==0 || passwordString.length() ==0) {

                } else {
                }

            }
        });
    }

    private void textviewControlled() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent to newRegisterActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });
    }

    private void InitialView(){

        userEditText = (EditText) findViewById(R.id.editUser);
        passwordEditText = (EditText) findViewById(R.id.editPass);
        textView = (TextView) findViewById(R.id.txtRegister);
        button = (Button) findViewById(R.id.btnLogin);




    }

} //main Class

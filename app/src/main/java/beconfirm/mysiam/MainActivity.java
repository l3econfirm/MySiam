package beconfirm.mysiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //explicits
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;
    private Myalert myalert;


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
                if (userString.length() == 0 || passwordString.length() == 0) {
                    //Have space

                    myalert.myDialog(getResources().getString(R.string.titleHaveSpace),
                            getResources().getString(R.string.messageHaveSpace));
                } else {
                    //No Space
                    CheckUSerAndPass()

                }

            }
        });
    }

    private void CheckUSerAndPass() {

        try {

            String urlPHP ="http://androidthai.in.th/siam/getAllDataMaster.php";
            GetAllData getAllData = new GetAllData(MainActivity.this);
            getAllData.execute(urlPHP);
            String strJSON = getAllData.get();
            Log.d("SiamV1", "JSON ===" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            boolean b = true;
            String[] strings = new String[]{"id", "Name",
                    "User", "Password"};
            String[] loginStrings1 = new String[strings.length];


            for (int i=0; i<jsonArray.length(); i++) {


                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if(userString.equals(jsonObject.getString("User"))) {


                    b = false;
                    for (int i1=0; i1<strings.length; i1++) {
                        loginStrings1[i1] = jsonObject.getString(strings[i1]);
                        Log.d("SiamV1", "loginString[" + i1 + "] ==> " + loginStrings1[i1]);
                    }

                }

            }   //for
            if (b) {
                myAlert.myDialog(getResources().getString(R.string.titleUserFalse),
                        getResources().getString(R.string.messageUerFalse));
            }else if (passwordString.equals(loginStrings1[3])) {
                Toast.makeText(MainActivity.this, "Welcome " + loginStrings1[1],
                        Toast.LENGTH_SHORT).show();
            } else {
                myAlert.myDialog(getResources().getString(R.string.titlePasswordFalse),
                        getResources().getString(R.string.messagePasswordFalse));
            }





        } catch (Exception e) {
                e.printStackTrace();
        }

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

        myalert = new Myalert(MainActivity.this);

        userEditText = (EditText) findViewById(R.id.editUser);
        passwordEditText = (EditText) findViewById(R.id.editPass);
        textView = (TextView) findViewById(R.id.txtRegister);
        button = (Button) findViewById(R.id.btnLogin);




    }

} //main Class

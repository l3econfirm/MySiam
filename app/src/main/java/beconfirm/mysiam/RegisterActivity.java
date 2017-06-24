package beconfirm.mysiam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {


    //Explicit
    private ImageView imageView;
    private EditText nameEditText, userEditText, passwordEditText;
    private Button button;

    private String nameString, userString, passwordString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initial View
        initialView();

        //Back Controller
        backController();

        //newRegister Controller
        newRegisterController();

    } //Main Method

    private void newRegisterController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get value From edit text
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //check space
                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
                    //have space
                    Myalert myalert = new Myalert(RegisterActivity.this);
                    myalert.myDialog("Have space", "Please Fill");
                } else {
                    //no space
                    uploadValuetoServer();
                }

            }


        });
    }

    private void uploadValuetoServer() {

        try {
            PostDataToServer postDataToServer = new PostDataToServer(RegisterActivity.this);
            postDataToServer.execute(nameString,userString,passwordString,
                    "http://androidthai.in.th/siam/addDataMaster.php");
            if (Boolean.parseBoolean(postDataToServer.get())) {
                finish();
            } else {
                Myalert myalert = new Myalert(RegisterActivity.this);
                myalert.myDialog("cannot Upload", "Please Try Again");
            }


        }catch (Exception e){
            Log.d("siamV1", "e upload ===" + e.toString());

        }
    }

    private void backController() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initialView() {
        imageView = (ImageView) findViewById(R.id.btnblack);
        nameEditText= (EditText) findViewById(R.id.edtname);
        userEditText= (EditText) findViewById(R.id.edtuser);
        passwordEditText= (EditText) findViewById(R.id.edtpass);
        button= (Button) findViewById(R.id.btnNewRegister);
    }


} //์Main Classs

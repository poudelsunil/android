package bluetooth.majorproject.differentsstorageoptions;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    EditText etRawName,etRawPassword,etSavedName,etSavedPassword;
    Button btnSaveSP,btnLoadSP,btnSaveIS,btnLoadIS,btnSaveChe,btnLoadChe,btnSaveES,btnLoadES;

    String nameToSave="",passwordToSave="",nameToShow="",passwordToShow="";

    public final static String DEFAULT = "Not Saved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        etRawName=(EditText)findViewById(R.id.etRawName);
        etRawPassword=(EditText)findViewById(R.id.etRawPassword);
        etSavedName=(EditText)findViewById(R.id.etSavedName);
        etSavedPassword=(EditText)findViewById(R.id.etSavedPassword);


        btnSaveSP=(Button)findViewById(R.id.btnSaveSP);
        btnLoadSP=(Button)findViewById(R.id.btnLoadSP);

        btnSaveIS=(Button)findViewById(R.id.btnSaveIS);
        btnLoadIS=(Button)findViewById(R.id.btnLoadIS);

        btnSaveChe=(Button)findViewById(R.id.btnSaveChe);
        btnLoadChe=(Button)findViewById(R.id.btnLoadChe);

        btnSaveES=(Button)findViewById(R.id.btnSaveES);
        btnLoadES=(Button)findViewById(R.id.btnLoadES);
    }
    public void getDataFromET(){
        String tmp=null;
        nameToSave=(tmp=etRawName.getText().toString())==null?tmp:DEFAULT;
        tmp=null;
        passwordToSave=(tmp=etRawPassword.getText().toString())==null?tmp:DEFAULT;
    }
    public void display(){
        etSavedName.setText(nameToShow);
        etSavedPassword.setText(passwordToShow);
    }


    public void SP(View view){
        SharedPreferences sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);
        if(view.getId()==R.id.btnSaveSP){
            getDataFromET();

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("name",nameToSave);
            editor.putString("password",passwordToSave);
            editor.commit();

            Toast.makeText(this,"Data Saved To SharedPreference !!",Toast.LENGTH_LONG).show();
        }else if(view.getId()==R.id.btnLoadSP){
            nameToShow=sharedPreferences.getString("name",DEFAULT);
            passwordToShow=sharedPreferences.getString("password",DEFAULT);
            display();
            Toast.makeText(this,"Data Loaded From SharedPreference !!",Toast.LENGTH_LONG).show();
        }
    }

    public void IS(View view){
        if(view.getId()==R.id.btnSaveIS){

            getDataFromET();
            FileOutputStream fileOutputStream= null;
            try {
                fileOutputStream = openFileOutput("MyFile.txt",MODE_PRIVATE);
                fileOutputStream.write(nameToSave.getBytes());
                fileOutputStream.write("*".getBytes());
                fileOutputStream.write(passwordToSave.getBytes());

                Toast.makeText(this,"Data Saved To Internal Storage",Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }else{
            StringBuffer buffer=new StringBuffer();
            FileInputStream fileInputStream= null;
            try {
                fileInputStream = openFileInput("MyFile.txt");
                int read=-1;
                while ((read=fileInputStream.read())!=-1){
                    buffer.append((char)read);
                }

                nameToShow=buffer.substring(0,buffer.indexOf("*"));
                passwordToShow=buffer.substring(buffer.indexOf("*")+1);

                display();
                Toast.makeText(this,"Data Loaded From Internal Storage",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void Che(View view){
        if(view.getId()==R.id.btnSaveChe){

        }else{

        }
    }

    public void ES(View view){
        if(view.getId()==R.id.btnSaveES){

        }else{

        }
    }

}

package lockdown.org.databasedemoapp2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{
    EditText etName, etLoginId, etPswd, etNo;
    Button btnRegister, btnCancelRegistration, btnBack;
    SQLiteDatabase db;
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_register);
        db = openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        etName = findViewById(R.id.etName);
        etLoginId = findViewById(R.id.etLoginId);
        etPswd = findViewById(R.id.etPswd);
        etNo = findViewById(R.id.etNo);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancelRegistration = findViewById(R.id.btnCancelRegistration);
        btnBack = findViewById(R.id.btnBack);
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String RegisterName = etName.getText().toString();
                String RegisterLoginId = etLoginId.getText().toString();
                String RegisterPassword = etPswd.getText().toString();
                String RegisterMobileNo = etNo.getText().toString();
                if (RegisterName.length() == 0)
                {
                    Toast.makeText(RegisterActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if (RegisterLoginId.length() == 0)
                {
                    Toast.makeText(RegisterActivity.this, "Enter Your Login Id", Toast.LENGTH_SHORT).show();
                }
                else if (RegisterPassword.length() == 0)
                {
                    Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (RegisterMobileNo.length() == 0)
                {
                    Toast.makeText(RegisterActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try
                    {
                        db.execSQL("insert into students(name,loginid,password,mobileno) values('" + RegisterName + "','" + RegisterLoginId + "','" + RegisterPassword + "','" + RegisterMobileNo + "')");
                        Toast.makeText(RegisterActivity.this, "Record inserted", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(RegisterActivity.this, "Error : " + e, Toast.LENGTH_SHORT).show();
                    }
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
        btnCancelRegistration.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                etName.setText("");
                etLoginId.setText("");
                etPswd.setText("");
                etNo.setText("");
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
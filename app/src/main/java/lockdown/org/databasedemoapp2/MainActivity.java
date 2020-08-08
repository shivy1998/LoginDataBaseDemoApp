package lockdown.org.databasedemoapp2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase db;
    EditText etId, etPassword;
    TextView tvRegistration;
    Button btnSignIn, btnCancel;
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists students(name varchar(30),loginid varchar(20),password varchar(10),mobileno varchar(20))");
        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnCancel = findViewById(R.id.btnCancel);
        tvRegistration = findViewById(R.id.tvRegistration);
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String loginid = etId.getText().toString();
                String password = etPassword.getText().toString();
                Cursor cur = db.rawQuery("select * from students where loginid='"+loginid+"' and password='"+password+"'",null);
                if (cur.getCount() > 0)
                {
                    Intent i = new Intent(MainActivity.this,HomeActivity.class);
                    i.putExtra("loginid",loginid);
                    startActivity(i);
                }
                else
                    {
                        Toast.makeText(MainActivity.this,"Invalid User",Toast.LENGTH_SHORT).show();
                    }
                cur.close();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                etId.setText("");
                etPassword.setText("");
            }
        });
        tvRegistration.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
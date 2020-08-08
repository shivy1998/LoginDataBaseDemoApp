package lockdown.org.databasedemoapp2;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
{
    TextView tvName, tvLoginId, tvMobileNo;
    SQLiteDatabase db;
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_home);
        tvName = findViewById(R.id.tvName);
        tvLoginId = findViewById(R.id.tvLoginId);
        tvMobileNo = findViewById(R.id.tvMobileNo);
        db = openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        Bundle ob = getIntent().getExtras();
        String loginid = ob.getString("loginid");
        tvLoginId.setText("Login Id : "+loginid);
        Cursor cur = db.rawQuery("select * from students where loginid='"+loginid+"'",null);
        cur.moveToFirst();
        String name = cur.getString(0);
        String mobileno = cur.getString(3);
        tvName.setText("Name : "+name);
        tvMobileNo.setText("Contact Details : "+mobileno);
    }
}
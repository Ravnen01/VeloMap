package lpiem.lecomte.com.velomap;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NoConnectionActitivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection_actitivy);
        getSupportActionBar().hide();

    }

    public void ressayez(View v){
        Intent i=new Intent(getApplicationContext(),TestJson.class);
        startActivity(i);
        finish();
    }
}

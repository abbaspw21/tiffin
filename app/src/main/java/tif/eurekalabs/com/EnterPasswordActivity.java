package tif.eurekalabs.com;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class EnterPasswordActivity extends AppCompatActivity {

    Toolbar toolbar;

    AppCompatButton btnSubmit;

    AppCompatEditText etPassword;
    AppCompatEditText etCnfrmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        btnSubmit=(AppCompatButton) findViewById(R.id.btn_verify);

        etPassword=(AppCompatEditText) findViewById(R.id.et_password);
        etCnfrmPassword=(AppCompatEditText) findViewById(R.id.et_cnfrm_pswd);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back_white));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EnterPasswordActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

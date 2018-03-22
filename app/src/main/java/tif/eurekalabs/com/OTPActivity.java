package tif.eurekalabs.com;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    TextView tvNumber;
    TextView tvResend;

    AppCompatButton btnSubmit;

    PinEntryEditText etOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tvNumber=(TextView) findViewById(R.id.tv_number);
        tvResend=(TextView) findViewById(R.id.tv_resend);

        btnSubmit=(AppCompatButton) findViewById(R.id.btn_verify);

        etOtp=(PinEntryEditText) findViewById(R.id.et_otp);

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


        tvResend.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_resend:
                break;
            case R.id.btn_verify:
                Intent i=new Intent(OTPActivity.this,MainActivity.class);
                startActivity(i);
                break;
        }
    }
}

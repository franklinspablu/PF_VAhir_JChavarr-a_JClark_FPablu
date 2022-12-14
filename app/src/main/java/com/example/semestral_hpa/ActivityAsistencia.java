package com.example.semestral_hpa;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.semestral_hpa.Helpers.EstudiantesResponse;

public class ActivityAsistencia extends AppCompatActivity{

    NfcAdapter nfcAdapter;
    PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null){
            Toast.makeText(this,"NO tiene NFC io!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onNewIntent(Intent intent) {
        getTagInfo(intent);
        super.onNewIntent(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        String techList = tag.getTechList()[2];
        if (techList.equals(Ndef.class.getName())){
            Ndef mNdef = Ndef.get(tag);
            if (mNdef!= null) {
                NdefMessage mNdefMessage = mNdef.getCachedNdefMessage();
                NdefRecord[] records = mNdefMessage.getRecords();
                if(records != null){
                    EstudiantesResponse estudiante = new EstudiantesResponse(
                            new String(mNdefMessage.getRecords()[0].getPayload()),
                            "",
                            new String(mNdefMessage.getRecords()[1].getPayload()),
                            "12:00 md"
                    );

                    //new Archivos(getApplicationContext()).CrearArchivo("Asistencia20221LS142.txt",estudiante.toString());
                }


                String cedula = new String(mNdefMessage.getRecords()[0].getPayload());

                Toast.makeText(getApplicationContext(),"Lectura => "+cedula,Toast.LENGTH_LONG).show();
            }
        }
    }

}

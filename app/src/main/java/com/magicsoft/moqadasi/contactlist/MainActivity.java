package com.magicsoft.moqadasi.contactlist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.magicsoft.moqadasi.contactlist.adp.CustomArrayAdapterContacts;
import com.magicsoft.moqadasi.contactlist.models.Contact;
import com.magicsoft.moqadasi.contactlist.utils.GetContactsDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public TextView outputText;
    ListView mActMain_lstContacts;
    List<Contact> lstAllContacts = new ArrayList<>();
    int TAG_INTENT_CONTACT_PERMISSION = 555;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputText = (TextView) findViewById(R.id.textView1);
        mActMain_lstContacts = (ListView) findViewById(R.id.actMain_lstContacts);
        checkAccess();
    }

    private void checkAccess() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, TAG_INTENT_CONTACT_PERMISSION);
        } else
            FillList();
    }

    public void FillList() {
        lstAllContacts = GetContactsDetail.getAllContacts(this);
        CustomArrayAdapterContacts adp = new CustomArrayAdapterContacts(this, R.layout.contacts_list_item, lstAllContacts);
        mActMain_lstContacts.setAdapter(adp);
        mActMain_lstContacts.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent goToDetail = new Intent(this, ShowDetail.class);
        goToDetail.putExtra("contact_id", lstAllContacts.get(position).id);
        goToDetail.putExtra("contact_name", lstAllContacts.get(position).name);
        goToDetail.putExtra("contact_pos", position);
        startActivity(goToDetail);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 555: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fileList();
                } else {
                    Toast.makeText(this, "متاسفانه برنامه بدون دسترسی به مخاطبین نمیتواند اجرا شود", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }


}

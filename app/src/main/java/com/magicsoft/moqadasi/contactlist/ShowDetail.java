package com.magicsoft.moqadasi.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicsoft.moqadasi.contactlist.models.Contact;
import com.magicsoft.moqadasi.contactlist.utils.GetContactsDetail;

public class ShowDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Intent rec_intent = getIntent();

        Integer position = rec_intent.getIntExtra("contact_pos", 0);
        Contact contactToShow = new Contact(rec_intent.getStringExtra("contact_id"), rec_intent.getStringExtra("contact_name"),rec_intent.getStringExtra("contact_number"));

        ImageView mShowDetail_imgPro = (ImageView) findViewById(R.id.showDetail_imgPro);
        TextView mShowDetail_txtName = (TextView) findViewById(R.id.showDetail_txtName);
        TextView mShowDetail_txtMainNumber = (TextView) findViewById(R.id.showDetail_txtMainNumber);
        TextView mShowDetail_txtDetail = (TextView) findViewById(R.id.showDetail_txtDetail);

        if ((position % 2) == 0)
            mShowDetail_imgPro.setBackground(getResources().getDrawable(R.mipmap.man));
        else
            mShowDetail_imgPro.setBackground(getResources().getDrawable(R.mipmap.woman));
        mShowDetail_imgPro.setImageURI(GetContactsDetail.getContactUri(getContentResolver(), contactToShow.id));

        mShowDetail_txtMainNumber.setText(contactToShow.main_number);
        mShowDetail_txtName.setText(contactToShow.name);
        mShowDetail_txtDetail.setText("شماره تلفن ها:\n");
        mShowDetail_txtDetail.append(GetContactsDetail.getContactNumbers(getContentResolver(), contactToShow.id));
        mShowDetail_txtDetail.append("\nایمیل ها:\n");
        mShowDetail_txtDetail.append(GetContactsDetail.getContactEmails(getContentResolver(), contactToShow.id));
    }
}

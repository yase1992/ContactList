package com.magicsoft.moqadasi.contactlist.models;

import android.net.Uri;

public class Contact {
    public String id;
    public String name;
    public String main_number;
    public Uri pro_uri;

    public Contact() {
    }

    public Contact(String id, String name, String main_number) {
        this.id = id;
        this.name = name;
        this.main_number = main_number;
    }
}

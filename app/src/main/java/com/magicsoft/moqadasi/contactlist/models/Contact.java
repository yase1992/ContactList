package com.magicsoft.moqadasi.contactlist.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contacts")
public class Contact {
    @DatabaseField(generatedId = true, index = true)
    public int id;
    @DatabaseField()
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Contact() {
    }

    public Contact(String name) {
        Name = name;
    }
}

package com.rs.rewardstyle.model;

import android.provider.ContactsContract;

import java.util.List;

public class Root{

    private List<Ltk> ltks;
    private List<ContactsContract.Profile> profiles;
    private Meta meta;
    private List<Product> products;
    private List<MediaObject> media_objects;

    public List<Ltk> getLtks() {
        return ltks;
    }
}

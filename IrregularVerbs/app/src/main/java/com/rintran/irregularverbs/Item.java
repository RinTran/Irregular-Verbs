package com.rintran.irregularverbs;



public class Item {
    private String name;
    private int linkImage;

    public Item(String n, int l){
        this.name = n;
        this.linkImage = l;
    }

    public String getName(){
        return name;
    }
    public int getLinkImage(){
        return linkImage;
    }
}

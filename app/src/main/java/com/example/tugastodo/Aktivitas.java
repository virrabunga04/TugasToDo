package com.example.tugastodo;

public class Aktivitas {
    private String id;
    private String what;
    private String time;

    public Aktivitas(String id, String what, String time){
        this.id = id;
        this.what = what;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getWhat() {
        return what;
    }

    public String getTime() {
        return time;
    }
}

package model;

import java.io.Serializable;

public class User implements Serializable {

    //フィールド
    private String id;
    private String name;
    private String pass;

    public User(){}
    public User(String id, String name, String pass){
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    //アクセッサ
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }

    public void setId(String id) {
    	this.id = id;
    }

    public void setName(String name) {
    	this.name = name;
    }
    public void setPass(String pass) {
    	this.pass = pass;
    }
}
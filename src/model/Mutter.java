package model;

import java.io.Serializable;

public class Mutter implements Serializable {
  private int id; // id
  private String userName;
  private String text;
  private String userNumber;


  public Mutter() {
  }

  public Mutter(String userName, String text) {
    this.userName = userName;
    this.text = text;
  }

  public Mutter(int id, String userName, String text) {
    this.id = id;
    this.userName = userName;
    this.text = text;
  }

  public Mutter(int id, String userName, String text,String userNumber) {
	    this.id = id;
	    this.userName = userName;
	    this.text = text;
	    this.userNumber = userNumber;
	  }



  public int getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public String getText() {
    return text;
  }

  public String getUserNumber() {
	  return userNumber;
  }

public void setId(int id) {
	this.id = id;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public void setText(String text) {
	this.text = text;
}

public void setUserNumber(String userNumber) {
	this.userNumber = userNumber;
}

}
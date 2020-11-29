package com.academy.recursion;

import java.util.List;

public class Friend {
    private int id;
    private String name;
    private String surname;
    List<Friend> friendList;

    public Friend() {
    }

    public Friend(int id, String name, String surname, List<Friend> friendList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.friendList = friendList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}

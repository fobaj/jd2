package com.academy.recursion;

import java.util.ArrayList;
import java.util.List;

public class OutputFriends {
    public static void main(String[] args) {

        // Creating friends.

        Friend friend1 = createFriend(1, "AMELIA", "HARRIS");
        Friend friend2 = createFriend(2, "ANNA", "ANDERSON");
        Friend friend3 = createFriend(3, "EMILIA", "HENDERSON");
        Friend friend4 = createFriend(4, "EVA", "HILL");
        Friend friend5 = createFriend(5, "GRACE", "HOWARD");
        Friend friend6 = createFriend(6, "JESSICA", "BROWN");
        Friend friend7 = createFriend(7, "JULIA", "JACKSON");
        Friend friend8 = createFriend(8, "MARIA", "ROBERTS");
        Friend friend9 = createFriend(9, "SARAH", "ROBINSON");
        Friend friend10 = createFriend(10, "VICTORIA", "JOHNSON");
        Friend friend11 = createFriend(11, "ADAM", "ROGERS");
        Friend friend12 = createFriend(12, "DAVID", "COLEMAN");
        Friend friend13 = createFriend(13, "FREDDIE", "SMITH");
        Friend friend14 = createFriend(14, "HARRY", "LOPEZ");
        Friend friend15 = createFriend(15, "JACK", "TAYLOR");
        Friend friend16 = createFriend(16, "LUCAS", "MORGAN");
        Friend friend17 = createFriend(17, "MICHAEL", "WATSON");
        Friend friend18 = createFriend(18, "OLIVER", "WILLIAMS");
        Friend friend19 = createFriend(19, "ROBERT", "GREEN");
        Friend friend20 = createFriend(20, "THOMAS", "PARKER");


        // Creating lists of friends.

        List<Friend> friendList1 = createListOfFriends(friend1, friend2, friend3);
        List <Friend> friendList2 = createListOfFriends(friend2, friend4, friend5, friend6);
        List<Friend> friendList3 = createListOfFriends(friend3, friend7, friend8);
        List<Friend> friendList4 = createListOfFriends(friend4, friend9, friend10);
        List<Friend> friendList5 = createListOfFriends(friend5, friend11);
        List<Friend> friendList6 = createListOfFriends(friend6, friend12);
        List<Friend> friendList7 = createListOfFriends(friend7, friend13, friend14);
        List<Friend> friendList8 = createListOfFriends(friend8, friend15, friend16);
        List<Friend> friendList12 = createListOfFriends(friend12, friend17, friend18, friend19, friend20);


        // Output a list of friends and friends of friends.

        recursion(friend2);

    }

    // Method for creating friends.

    public static Friend createFriend (int id, String name, String surname) {
        Friend friend = new Friend();
        friend.setId(id);
        friend.setName(name);
        friend.setSurname(surname);
        return friend;
    }

    // Methods for creating a friend list.

    public static List<Friend> createListOfFriends (Friend friend, Friend friend1) {
        List<Friend> friendList = new ArrayList<>();
        friendList.add(friend1);
        friend.setFriendList(friendList);
        return friendList;
    }

    public static List<Friend> createListOfFriends (Friend friend, Friend friend1, Friend friend2) {
        List<Friend> friendList = new ArrayList<>();
        friendList.add(friend1);
        friendList.add(friend2);
        friend.setFriendList(friendList);
        return friendList;
    }

    public static List<Friend> createListOfFriends (Friend friend, Friend friend1, Friend friend2, Friend friend3) {
        List<Friend> friendList = new ArrayList<>();
        friendList.add(friend1);
        friendList.add(friend2);
        friendList.add(friend3);
        friend.setFriendList(friendList);
        return friendList;
    }

    public static List<Friend> createListOfFriends (Friend friend, Friend friend1, Friend friend2, Friend friend3, Friend friend4) {
        List<Friend> friendList = new ArrayList<>();
        friendList.add(friend1);
        friendList.add(friend2);
        friendList.add(friend3);
        friendList.add(friend4);
        friend.setFriendList(friendList);
        return friendList;
    }

    // Recursive method.

    public static void recursion (Friend friend) {
        List<Friend> friendList = friend.getFriendList();
        if (friendList != null) {
            for (Friend x : friendList) {
                System.out.println(x);
                if (x.getFriendList() != null) {
                    recursion(x);
                }
            }
        }
    }
}

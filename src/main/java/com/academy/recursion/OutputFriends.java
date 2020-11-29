package com.academy.recursion;

import java.util.ArrayList;
import java.util.List;

public class OutputFriends {
    public static void main(String[] args) {

        // Creating friends.

        Friend friend1 = new Friend();
        friend1.setId(1);
        friend1.setName("AMELIA");
        friend1.setSurname("HARRIS");

        Friend friend2 = new Friend();
        friend2.setId(2);
        friend2.setName("ANNA");
        friend2.setSurname("ANDERSON");

        Friend friend3 = new Friend();
        friend3.setId(3);
        friend3.setName("EMILIA");
        friend3.setSurname("HENDERSON");

        Friend friend4 = new Friend();
        friend4.setId(4);
        friend4.setName("EVA");
        friend4.setSurname("HILL");

        Friend friend5 = new Friend();
        friend5.setId(5);
        friend5.setName("GRACE");
        friend5.setSurname("HOWARD");

        Friend friend6 = new Friend();
        friend6.setId(6);
        friend6.setName("JESSICA");
        friend6.setSurname("BROWN");

        Friend friend7 = new Friend();
        friend7.setId(7);
        friend7.setName("JULIA");
        friend7.setSurname("JACKSON");

        Friend friend8 = new Friend();
        friend8.setId(8);
        friend8.setName("MARIA");
        friend8.setSurname("ROBERTS");

        Friend friend9 = new Friend();
        friend9.setId(9);
        friend9.setName("SARAH");
        friend9.setSurname("ROBINSON");

        Friend friend10 = new Friend();
        friend10.setId(10);
        friend10.setName("VICTORIA");
        friend10.setSurname("JOHNSON");

        Friend friend11 = new Friend();
        friend11.setId(11);
        friend11.setName("ADAM");
        friend11.setSurname("ROGERS");

        Friend friend12 = new Friend();
        friend12.setId(12);
        friend12.setName("DAVID");
        friend12.setSurname("COLEMAN");

        Friend friend13 = new Friend();
        friend13.setId(13);
        friend13.setName("FREDDIE");
        friend13.setSurname("SMITH");

        Friend friend14 = new Friend();
        friend14.setId(14);
        friend14.setName("HARRY");
        friend14.setSurname("LOPEZ");

        Friend friend15 = new Friend();
        friend15.setId(15);
        friend15.setName("JACK");
        friend15.setSurname("TAYLOR");

        Friend friend16 = new Friend();
        friend16.setId(16);
        friend16.setName("LUCAS");
        friend16.setSurname("MORGAN");

        Friend friend17 = new Friend();
        friend17.setId(17);
        friend17.setName("MICHAEL");
        friend17.setSurname("WATSON");

        Friend friend18 = new Friend();
        friend18.setId(18);
        friend18.setName("OLIVER");
        friend18.setSurname("WILLIAMS");

        Friend friend19 = new Friend();
        friend19.setId(19);
        friend19.setName("ROBERT");
        friend19.setSurname("GREEN");

        Friend friend20 = new Friend();
        friend20.setId(20);
        friend20.setName("THOMAS");
        friend20.setSurname("PARKER");


        // Creating lists of friends.

        List<Friend> friendList1 = new ArrayList<>();
        friendList1.add(friend2);
        friendList1.add(friend3);
        friend1.setFriendList(friendList1);

        List<Friend> friendList2 = new ArrayList<>();
        friendList2.add(friend4);
        friendList2.add(friend5);
        friendList2.add(friend6);
        friend2.setFriendList(friendList2);

        List<Friend> friendList3 = new ArrayList<>();
        friendList3.add(friend7);
        friendList3.add(friend8);
        friend3.setFriendList(friendList3);

        List<Friend> friendList4 = new ArrayList<>();
        friendList4.add(friend9);
        friendList4.add(friend10);
        friend4.setFriendList(friendList4);

        List<Friend> friendList5 = new ArrayList<>();
        friendList5.add(friend11);
        friend5.setFriendList(friendList5);

        List<Friend> friendList6 = new ArrayList<>();
        friendList6.add(friend12);
        friend6.setFriendList(friendList6);

        List<Friend> friendList7 = new ArrayList<>();
        friendList7.add(friend13);
        friendList7.add(friend14);
        friend7.setFriendList(friendList7);

        List<Friend> friendList8 = new ArrayList<>();
        friendList8.add(friend15);
        friendList8.add(friend16);
        friend8.setFriendList(friendList8);

        List<Friend> friendList12 = new ArrayList<>();
        friendList12.add(friend17);
        friendList12.add(friend18);
        friendList12.add(friend19);
        friendList12.add(friend20);
        friend12.setFriendList(friendList12);


        // Output a list of friends and friends of friends.

        recursion(friend2);

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

package com.bookee.guidetest;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        HashMap<User,User> map = new HashMap<>();
        for(int i = 1;i < 30;i++){
            User user = new User();
            map.put(user,user);
            System.out.println("i="+i);
        }
    }

}
class User{
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
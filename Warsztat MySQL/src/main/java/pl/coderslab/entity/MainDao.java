package pl.coderslab.entity;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;
import java.util.SortedMap;

public class MainDao {

    public static void main(String[] args) {

        UserDao UserDao = new UserDao();
//        UserDao.register();

        UserDao.dispalyData();


        }



//        System.out.println(UserDao.read(4));

//        User zmianadanych = UserDao.read(9);
//        zmianadanych.setUserName("Tomasz Tomal Tomaszewski");
//        UserDao.update(zmianadanych);


//        System.out.println(UserDao.read(1));

//        UserDao.delete(8);
//
////        System.out.println(ArrayUtils.toString(UserDao.findAll()));
//
//        User[] all = UserDao.findAll();
//
//        for (User u : all) {
//
//            System.out.println(u);
//        }
    }

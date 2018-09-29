package com.test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test03 {

    public static void main(String[] args) {

        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间：" + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

    }
}

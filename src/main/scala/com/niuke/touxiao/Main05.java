package com.niuke.touxiao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
class Activity{
    public int start;
    public int end;
    public Activity(int start, int end){
        this.start = start;
        this.end = end;
    }
}

public class Main05 {
    public static int activity_selector(int[] start, int[] end, int n, int index){
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (i != index) {
                activities.add(new Activity(start[i], end[i]));
            }
        }
        activities.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                if (o1.end < o2.end){
                    return -1;
                }else if(o1.end == o2.end && o1.start <= o2.start){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        int size = activities.size();
        int activities_num = 1;
        if (size == 0){
            activities_num = 0;
            return activities_num;
        }else if (size == 1){
            return activities_num;
        }else {
            for (int i = 1; i < size; i++) {
                if (i == index) {
                    continue;
                }
                if (activities.get(i - 1).end <= activities.get(i).start) {
                    activities_num++;
                }
            }
            return activities_num;
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[]start = new int[n];
        int[]end = new int[n];
        for (int i = 0; i < n; i++){
            start[i] = in.nextInt();
            end[i] = in.nextInt();
        }

        int count = 0;
        int activities_num = activity_selector(start, end, n,-1);

        if (activities_num == n){
            System.out.println(n);
            for (int i = 1; i <= n; i++){
                System.out.print(i + " ");
            }
            System.out.println();
        }else{
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < n; i++){
                if (n - 1 == activity_selector(start, end, n, i)){
                    count++;
                    result.add(i + 1);
                }
            }
            System.out.println(count);
            for (int i = 0; i < result.size(); i++){
                System.out.print(result.get(i)+ " ");
            }
            System.out.println();
        }

    }
}

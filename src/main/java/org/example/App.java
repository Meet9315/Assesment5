package org.example;
import java.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int stu;
        Scanner sc= new Scanner(System.in);
        stu=sc.nextInt();
        String grade="";
        if(stu>=90 && stu<=100){
            grade="S";
        }
        else if(stu>=80 && stu<90){
            grade="A";
        }
        else if(stu>=70 && stu<80){
            grade="B";
        }
        else if(stu>=60 && stu<70){
            grade="C";
        }
        else{
            grade="E";
        }
        System.out.println("Grade = "+grade);
    }
}

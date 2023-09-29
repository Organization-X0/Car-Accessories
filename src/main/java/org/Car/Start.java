package org.Car;

import java.util.Scanner;

public class Start {
    public Start(){
    }
    public static int menu(){
        Scanner scanner=new Scanner(System.in);

        System.out.println(Cli.purpleBoldText("Program"));
        System.out.println(Cli.blueBoldText("1.Login"));
        System.out.println(Cli.blueBoldText("2.Sign UP"));
        int option=scanner.nextInt();
        if(option==1) {
            return 1;
        }else if(option==2){
            return 2;
        }else{
            return 0;
        }
    }
}

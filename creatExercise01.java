import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//生成带括号的题目
public class creatExercise01 {
    //产生题目和答案并放入对应文件中
    public creatExercise01(int n) {
        Random r = new Random();
        MyJFrame myJFrame = new MyJFrame();
        //清空Exercises.txt和Answers.txt文件夹中的内容
        try{
            new FileWriter("Exercises.txt").write("");
            new FileWriter("Answers.txt").write("");
        }catch(IOException e){
            e.printStackTrace();
        }

        //用来统计产生过的总题目数量,这个可以不要,就是用来统计一下确保确实可以产生不重复题目
        int count = 0;

        //定义一个数组存放未重复的题目
        String[] arr = new String[myJFrame.getN() + 1];
        for (int i = 1; i <myJFrame.getN() ; ) {
            int random = r.nextInt(3);//产生一个随机数用来决定生成几个运算符的算式
            //调用类creatArithmetic01中生成算式的方法
            creatArithmetic01 ca = new creatArithmetic01(n);
            if (random == 0) {//random为0产生一个运算符的
                if (!judgment(arr, ca.process1)) {//判断是否重复,如果不重复,写入对应文件
                    writeExercises(i + ".  " + ca.process1);
                    writeAnswers(i + ".  " + ca.answer1);
                    System.out.println("请计算:" + ca.process1);
                    System.out.println("答案为:" + ca.answer1);
                    arr[i] = ca.process1;//将没有重复的算式放入数组当中
                    i++;//此时已经生成一个式子,所以i+1
                }
            }else {//产生两个运算符的
                if (!judgment(arr, ca.process2)) {//判断是否重复,如果不重复,写入对应文件
                    writeExercises(i + ".  " + ca.process2);
                    writeAnswers(i + ".  " + ca.answer2);
                    System.out.println("请计算:" + ca.process2);
                    System.out.println("答案为:" + ca.answer2);
                    arr[i] = ca.process2;//将没有重复的算式放入数组当中
                    i++;//此时已经生成一个式子,所以i+1
                }
            }
            //没进行一次循环,count+1,用于统计总共产生过多少道题目
            count++;
        }
        System.out.println("生成过" + count +"道题目");
        System.out.println("生成题目时重复了"+ (count - myJFrame.getN()) + "道题目");
    }

    //定义一个带参方法用来判断数组是否包含该元素,返回true或false
    public static boolean judgment(String[] arr,String containValue) {
        for (int i = 0; i < arr.length; i++) {
            if (Arrays.asList(arr).contains(containValue)) {
                return true;
            }
        }
        return false;
    }

    //定义一个带参方法将生成的题目放入文件Exercises.txt
    public void writeExercises(String exercises){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Exercises.txt", true));
            bufferedWriter.write(exercises + System.lineSeparator());
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //定义一个带参方法将生成的答案放入文件Answers.txt
    public void writeAnswers(String answers){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("Answers.txt", true));
            bufferedWriter.write(answers + System.lineSeparator());
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

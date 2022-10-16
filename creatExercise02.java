import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//生成不带括号的题目
public class creatExercise02 {                 //这个类所有解释直接参照creatExercise01
    //产生题目和答案并放入对应文件中
    public creatExercise02(int n) {
        MyJFrame myJFrame = new MyJFrame();
        //清空Exercises.txt和Answers.txt文件夹中的内容
        try{
            new FileWriter("Exercises.txt").write("");
            new FileWriter("Answers.txt").write("");
        }catch(IOException e){
            e.printStackTrace();
        }
        Random r = new Random();
        //用来统计产生过的总题目数量
        int count = 0;
        //用一个数组存放未重复的题目
        String[] arr = new String[myJFrame.getN() + 1];
        //随机生成哪种运算
        for (int i = 1; i < myJFrame.getN() + 1; ) {
            int random = r.nextInt(3);
            //random为0产生一个运算符的,random不为0产生两个运算符的
            creatArithmetic02 ca = new creatArithmetic02(n);
            if (random == 0) {
                //判断是否重复
                if (!judgment(arr, ca.process1)) {
                    writeExercises(i + ".  " + ca.process1);
                    writeAnswers(i + ".  " + ca.answer1);
                    System.out.println("请计算:" + ca.process1);
                    System.out.println("答案为:" + ca.answer1);
                    arr[i] = ca.process1;
                    i++;
                }
            }else {
                if (!judgment(arr, ca.process2)) {
                    writeExercises(i + ".  " + ca.process2);
                    writeAnswers(i + ".  " + ca.answer2);
                    System.out.println("请计算:" + ca.process2);
                    System.out.println("答案为:" + ca.answer2);
                    arr[i] = ca.process2;
                    i++;
                }
            }
            //可统计除去重复总共产生过多少道题
            count++;
        }
        System.out.println("生成过" + count +"道题目");
        System.out.println("生成题目时重复了"+ (count - myJFrame.getN()) + "道题目");
    }

    //判断数组是否包含该元素
    public static boolean judgment(String[] arr,String containValue) {
        for (int i = 0; i < arr.length; i++) {
            if (Arrays.asList(arr).contains(containValue)) {
                return true;
            }
        }
        return false;
    }

    //将生成的题目放入文件Exercises.txt
    public void writeExercises(String exercises){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Exercises.txt", true));
            bufferedWriter.write(exercises + System.lineSeparator());
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //将生成的答案放入文件Answers.txt
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

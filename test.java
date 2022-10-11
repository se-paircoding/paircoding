import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("题目中的数值范围在()以内");
        //设置参数n控制取值范围
        int n = sc.nextInt();
        //通过输入yes或者no来决定题目是否可以带有括号
        while (true){
            System.out.println("题目是否需要带括号(yes/no)");
            String choice = sc.next();
            if (choice.equals("yes")) {
                //带括号的题目,参考类 creatArithmetic01
                new creatExercise01(n);
                break;
            } else if (choice.equals("no")) {
                //不带括号的题目,参考类 creatArithmetic02
                new creatExercise02(n);
                break;
            }else {
                //输入其它内容无效,重新输入
                System.out.println("输入无效,请重新输入");
            }
        }
    }
}

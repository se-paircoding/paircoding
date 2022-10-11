import java.util.Random;

//生成不带括号的题目
public class creatArithmetic02 {
    //这里的所有属性参照类creatArithmetic01
    String process;
    String process1;
    String process2;
    String[] operationSymbol1 = new String[1];
    String[] operationSymbol2 = new String[1];
    int number1;
    int number2;
    int number3;
    String answer1;
    String answer2;
    Random r = new Random();

    public creatArithmetic02(int n) {
        oneArithmeticResult(n);
        twoArithmeticResult(n);
    }

    //生成两个运算符
    private void twoArithmeticResult(int n) {
        oneArithmeticResult(n);
        while (true) {
            if(operationSymbol1[0].equals("÷")) {//如果第一个运算符为÷,同类creatArithmetic01中的解释
                process2 = process1;
                answer2 = simplestFraction(number1,number2);
                break;
            }
            number3 = r.nextInt(n - 2) + 2;//生成第三个随机数字
            int choice2 = r.nextInt(2);//定义choice2用于确定随机的运算符
            if (choice2 == 0) {//第二个运算符为＋
                operationSymbol2[0] = "＋";
                if (operationSymbol1[0].equals("＋") || operationSymbol1[0].equals("－")) {//如果第一个运算符不是÷,按顺序计算
                    if (Integer.parseInt(answer1) + number3 < n) {
                        answer2 = String.valueOf(Integer.parseInt(answer1) + number3);
                        process2 = process + operationSymbol2[0] + number3 + "=";
                        break;
                    }
                }
            } else if (choice2 == 1) {//第二个运算符为－
                operationSymbol2[0] = "－";
               if (operationSymbol1[0].equals("＋") || operationSymbol1[0].equals("－")) {
                    if (Integer.parseInt(answer1) >= number3) {
                        answer2 = String.valueOf(Integer.parseInt(answer1) - number3);
                        process2 = process + operationSymbol2[0] + number3 + "=";
                        break;
                    }
                }
            }              //这里说明一下为什么没有考虑第二个运算符为÷
        }                  //如果第二个运算符为÷,那么第一个运算符就不能是＋或者－了,否则不满足结果为真分数
    }                      //如果第一个运算符也为÷,那么就和之前解释的两个都为÷的情况一样,就舍去掉了

    //生成一个运算符
    //这里参照类creatArithmetic01中的生成一个运算符
    private void oneArithmeticResult(int n) {
        while (true) {
            int randomNumber1 = r.nextInt(n - 2) + 2;
            int randomNumber2 = r.nextInt(n - 2) + 2;
            int choice1 = r.nextInt(3);
            if (choice1 == 0) {
                operationSymbol1[0] = "＋";
                if (randomNumber1 + randomNumber2 < n) {
                    answer1 = String.valueOf(randomNumber1 + randomNumber2);
                    number1 = randomNumber1;
                    number2 = randomNumber2;
                    break;
                }
            } else if (choice1 == 1) {
                operationSymbol1[0] = "－";
                if (randomNumber1 > randomNumber2) {
                    answer1 = String.valueOf(randomNumber1 - randomNumber2);
                    number1 = randomNumber1;
                    number2 = randomNumber2;
                    break;
                }
            } else if (choice1 == 2) {
                if (randomNumber1 < randomNumber2) {
                    operationSymbol1[0] = "÷";
                    answer1 = simplestFraction(randomNumber1, randomNumber2);
                    number1 = randomNumber1;
                    number2 = randomNumber2;
                    break;
                }
            }
        }
        process = number1 + operationSymbol1[0] + number2;
        process1 = number1 + operationSymbol1[0] + number2 + "=";
    }

    //定义一个方法返回一个分数的最简分数
    //参数为分子和分母
    private String simplestFraction(int molecule, int denominator) {
        //先求最大公约数
        int greatestCommonDivisor = 1;
        for (int i = molecule; i >= 1; i--) {
            if (molecule % i == 0 && denominator % i == 0) {
                greatestCommonDivisor = i;
                break;
            }
        }
        //再返回最简分数
        return molecule / greatestCommonDivisor + "/" + denominator / greatestCommonDivisor;
    }
}

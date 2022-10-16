import java.util.Random;

//生成带括号的题目
public class creatArithmetic01 {
    String process;//表示含有一个运算符且不含=的式子,如"1 ＋ 1"这种形式
    String process1;//表示含有一个运算符且含=的式子,如"1 ＋ 1 ="这种形式
    String process2;//表示含有两个个运算符且含=的式子,如"1 ＋ 1 ＋ 1 ="这种形式
    String[] operationSymbol1 = new String[1];//定义长度为1的数组用来存放含一个运算符式子中的运算符,如存放运算符"＋"
    String[] operationSymbol2 = new String[1];//定义长度为1的数组用来存放含两个运算符式子中的第二个运算符
    int number1;//定义运算式的第一个数字
    int number2;//定义运算式的第二个数字
    int number3;//定义运算式的第三个数字
    String answer1;//定义只含有一个运算符的式子的答案
    String answer2;//定义含有两个运算符的式子的答案
    Random r = new Random();
    //定义一个含参方法(这里的参数n就是在类test里面的控制取值范围的n),用来生成对应的题目内容
    public creatArithmetic01(int n) {
        oneArithmeticResult(n);////生成一个运算符的式子
        twoArithmeticResult(n);////生成两个运算符的式子
    }

    //生成两个运算符(先看生成一个运算符的方法,在下面)
    private void twoArithmeticResult(int n) { //这里的n也是控制取值范围的n
        //调用生成一个运算符式子方法,后续在此基础上扩展成两个运算符(因为刚开始没有考虑到带括号的情况,把弄复杂了)
        oneArithmeticResult(n);
        //这里用一个while循环用来生成符合条件的题目
        while (true) {
            number3 = r.nextInt(n - 2) + 2; //随机生成第三个数字
            int choice2 = r.nextInt(3);//同样定义一个choice2用来决定随机生成哪个运算符表示第二个运算符
            if (choice2 == 0) {
                operationSymbol2[0] = "＋";//第二个运算符为＋
                //判断第一个运算符是什么
                if(operationSymbol1[0].equals("÷")){
                    //如果第一个运算符是÷,我们直接给第二个数字和第三个数字的运算打上括号进行计算
                    //这里这样处理是因为如果第一个运算符是÷,那后面再＋或者－一个数结果一定不为真分数,所以就对后面两个数字先计算
                    if((number2 + number3) > number1 &&
                            (number2 + number3 )< n){//后面两个数字需要满足计算结果大于第一个数字并且小于取值范围才满足条件
                        answer2 = simplestFraction(number1 ,(number2 + number3));//这里是调用方法直接生成最简分数表示答案
                        //生成含有两个运算符的式子
                        process2 = number1 + operationSymbol1[0] + "(" + number2 + operationSymbol2[0] + number3 + ")" + "=";
                    }
                }else if (operationSymbol1[0].equals("＋") || operationSymbol1[0].equals("－")) {//当第一个运算符不是÷时,进行正常的顺序计算
                    if (Integer.parseInt(answer1) + number3 < n) {//结果需要满足小于取值范围
                        answer2 = String.valueOf(Integer.parseInt(answer1) + number3);
                        process2 = process + operationSymbol2[0] + number3 + "=";
                        break;
                    }
                }
            } else if (choice2 == 1) {
                operationSymbol2[0] = "－";//第二个运算符为－
                if(operationSymbol1[0].equals("÷")){//如果第一个运算符为÷
                    //此时如果先计算÷再计算－结果不为真分数,所以我们把后两个数字先计算
                    if(number2 - number3 > number1){//后两个数字的差需要满足大于第一个数字
                        answer2 = simplestFraction(number1 ,(number2 - number3));
                        process2 = number1 + operationSymbol1[0] + "(" + number2 + operationSymbol2[0] + number3 + ")" + "=";
                        break;
                    }
                }else if (operationSymbol1[0].equals("＋") || operationSymbol1[0].equals("－")) {//如果第一个数字不为÷,则按顺序进行计算
                    if (Integer.parseInt(answer1) >= number3) {//因为此时第二个运算符是－,所以前两个数字的运算结果需要大于第三个数字才满足
                        answer2 = String.valueOf(Integer.parseInt(answer1) - number3);
                        process2 = process + operationSymbol2[0] + number3 + "=";
                        break;
                    }
                }
            } else if (choice2 == 2) {
                operationSymbol2[0] = "÷";//第二个运算符为÷
                if(operationSymbol1[0].equals("÷")){
                    //如果此时第一个运算符也为÷,就直接变为只含有一个÷的式子,去掉第三个运算符
                    //这样处理的原因是如果第二个数字过大,要满足计算的范围不超过n,就会导致只有当第三个数字是1的时候才满足结果分母不超过n,并且这样的还有1的计算没有意义
                    //我尝试过进一步处理,但是需要改变number2的随机取值范围和number3的随机取值范围,有点麻烦,就直接不处理了嘿嘿
                    process2 = process1;
                    answer2 = simplestFraction(number1,number2);
                    break;
                }else if(operationSymbol1[0].equals("＋") || operationSymbol1[0].equals("－")){
                    //如果此时第一个运算符不为÷,则直接给前两个数字的运算加上括号,因为不加括号按顺序计算会导致结果不是真分数
                    if (Integer.parseInt(answer1) < number3 && Integer.parseInt(answer1) > 0) {//需满足前两个数字的计算结果小于第三个数字并且大于0
                        answer2 = simplestFraction(Integer.parseInt(answer1),number3);
                        process2 = "(" + process + ")" + operationSymbol2[0] + number3 + "=";
                        break;
                    }
                }
            }
        }
    }

    //生成一个运算符
    private void oneArithmeticResult(int n) { //这里的n也是控制取值范围的n
        //这里用一个while循环用来生成符合条件的题目
        while (true) {
            // 因为数字1在计算中太过简单,且太容易满足生成式子所需条件,就把舍去
            number1 = r.nextInt(n - 2) + 2;//随机产生第一个数字
            number2 = r.nextInt(n -2) + 2;//随机产生第二个数字
            int choice1 = r.nextInt(3);//随机生成一个长度为4的choice1用来决定运算符是什么
            if (choice1 == 0) {//当choice1=0的时候运算符为＋
                //当两个数字的和小于取值范围的时候才能生成"＋"的算式
                if (number1 + number2 < n) {
                    operationSymbol1[0] = "＋";
                    answer1 = String.valueOf(number1 + number2);//因为answer1是字符串类型,所以需要转换
                    break;
                }
            } else if (choice1 == 1) {//当choice1=1的时候运算符为－
                //当第一个数字大于第二个数字的时候才能生成"－"的算式
                if (number1 > number2) {
                    operationSymbol1[0] = "－";
                    answer1 = String.valueOf(number1 - number2);
                    break;
                }
            } else if (choice1 == 2) {//当choice>=2的时候运算符为÷,
                if (number1 < number2) {//因为要求结果为真分数,第一个数字要小于第二个数字
                    operationSymbol1[0] = "÷";
                    answer1 = simplestFraction(number1, number2);//这里调用下面定义的方法直接生成最简分数
                    break;
                }
            }
        }
        //单独把生成不含"="的process是为了方便在生成含有两个运算符号的方法中直接调用
        process = number1 + operationSymbol1[0] + number2;
        //生成只含有一个运算符的式子
        process1 = number1 + operationSymbol1[0] + number2 + "=";
    }

    //定义一个方法返回一个分数的最简分数
    //参数为分子和分母
    private String simplestFraction(int molecule, int denominator) {
        //先求最大公约数
        int greatestCommonDivisor = 1;//先定义最大公约数
        for (int i = molecule; i >= 1; i--) {
            if (molecule % i == 0 && denominator % i == 0) {
                greatestCommonDivisor = i;//从后往前遍历,当满足被整除时,即为最大公约数
                break;
            }
        }
        //再返回最简分数
        //分子和分母同时除以最大公约数
        return molecule / greatestCommonDivisor + "/" + denominator / greatestCommonDivisor;
    }
}

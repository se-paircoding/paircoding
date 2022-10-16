import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJFrame extends JFrame implements ActionListener {
    //定义初始题目数量
    private int n = 0;
    //定义是否带括号
    private String choice;
    //定义初始取值范围
    private int range = 100;
    //设置可编辑文本框
    JTextField textField1 = new JTextField(15);
    JTextField textField2 = new JTextField(15);
    JTextField textField3 = new JTextField(15);
    //设置确定按钮
    JButton b = new JButton("确定");

    public  MyJFrame() {
        //设置界面的宽高
        this.setSize(400,150);
        //设置界面的标题
        this.setTitle("四则运算生成器");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面关闭
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置,只有取消了,才会按照XY轴的形式添加组件
        this.setLayout(null);
        //设置容器
        Container c =this.getContentPane();
        c.setLayout(new FlowLayout());
        //设置文本框输入
        JTextField [] t = {
                new JTextField("请输入题目数量:",16),textField1,
                new JTextField("题目是否带括号(yes/no):",16),textField2,
                new JTextField("题目中的数值在()以内",16),textField3
        };
        t[0].setEditable(false);
        t[2].setEditable(false);
        t[4].setEditable(false);
        for (int i = 0; i < 6; i++) {
            c.add(t[i]);
        }

        c.add(b);
        //设置界面可见
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        JButton obj = (JButton) e.getSource();
        if (obj.equals(b)) {
            //获取文本信息
            n = Integer.parseInt(textField1.getText());
            choice = textField2.getText();
            range = Integer.parseInt(textField3.getText());
            //添加提示弹框
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个管理文字的容器对象
            JLabel jLabel = new JLabel();
            //设置位置和宽高
            jLabel.setBounds(150,75,100,50);
            //把文字添加到弹框当中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(200,100);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //通过输入yes或者no来决定题目是否可以带有括号
            while (true){
                if (choice.equals("yes")) {
                    //带括号的题目,参考类 creatArithmetic01
                    new creatExercise01(range);
                    //更改弹框内容
                    jLabel.setText("题目和答案已生成,请在对应的文件夹中查看");
                    break;
                } else if (choice.equals("no")) {
                    //不带括号的题目,参考类 creatArithmetic02
                    new creatExercise02(range);
                    //更改弹框内容
                    jLabel.setText("题目和答案已生成,请在对应的文件夹中查看");
                    break;
                }else {
                    //更改弹框内容
                    jLabel.setText("输入题目是否带括号类型无效,请重新输入");
                    //输入其它内容无效,重新输入
                    System.out.println("输入无效,请重新输入");
                }
                //让弹框显示出来
                jDialog.setVisible(true);
            }
        }
    }


    public int getN() {
        return n;
    }

    public String getChoice() {
        return choice;
    }

    public int getRange() {
        return range;
    }



}

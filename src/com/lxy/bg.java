package com.lxy;

import java.awt.*;

import static java.awt.Color.*;

public class bg {
    //关卡数
    static int level = 1;
    //目标得分
    int goal=level*5;
    //总分
    static int count=0;
    //药水数量
    static int waterNum = 3;
    //药水状态(true正在使用
    static boolean waterFlag = false;
    //开始时间
    long startTime;
    //结束时间
    long endTime;
    //药水价格
    int price = (int) (Math.random()*10);
    //是否进入商店
    boolean shop=false;

    //载入图片
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
    Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");
    //将图片载入到程序当中，用到image方法

    void paintself(Graphics g){
        g.drawImage(bg1,0,0,null);
        g.drawImage(bg,0,200,null);

        switch (GameWin.state){
            case 0:
                drawWord(g, black,80,"准备开始",200,400);
                break;
            case 1:
                g.drawImage(peo,310,50,null);
                drawWord(g, black,30,"积分："+count,30,150);

                //药水组件
                g.drawImage(water,450,40,null);
                drawWord(g, black,30,"*"+waterNum,510,70);

                //关卡数
                drawWord(g, black,20,"第"+level+"关",30,60);
                //目标积分
                drawWord(g, black,30,"目标积分："+goal,30,110);

                //时间组件
                endTime = System.currentTimeMillis();
                long tim = 20-(endTime-startTime)/1000;
                drawWord(g, black,30,"时间"+(tim>0?tim:0),520,150);
                break;
            case 2:
                g.drawImage(water,300,400,null);
                drawWord(g, black,30,"药水价格"+price,300,500);
                drawWord(g, black,30,"是否购买？",300,550);
                if(shop){
                    count=count-price;
                    waterNum++;
                    shop=false;
                    GameWin.state=1;
                    startTime=System.currentTimeMillis();
                }
                break;
            case 3:
                drawWord(g, cyan,80,"失败",250,350);
                drawWord(g, cyan,80,"积分"+count,200,450);
                break;
            case 4:
                drawWord(g, red,80,"成功",250,350);
                drawWord(g, red,80,"积分"+count,200,450);
                break;
            default:
        }



    }

    //t倒计时完成 f正在倒计时
    boolean gameTime(){
        long tim=(endTime-startTime)/1000;
        if(tim>20){
            return true;
        }else{
            return false;
        }
    }

    //重置元素
    void reGame(){
        //关卡数
        level = 1;
        //目标得分
        goal=level*5;
        //总分
        count=0;
        //药水数量
        waterNum = 3;
        //药水状态(true正在使用
        waterFlag = false;
    }


    //绘制字符串
    public static void drawWord(Graphics g,Color color,int size,String str,int x,int y){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}

package com.lxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    // 0未开始 1运行中 2商店 3失败 4胜利
    static int state;//设置状态

    List<Object> objectList = new ArrayList<>();//创建集合存储金块、石块
    bg bg = new bg();
    Line line = new Line(this);
//    Gold gold = new Gold();
    Image offScreenImage;//新建一个画布

    //在类中不能直接用for循环：无参构造/代码块。（产生多个不同金块石块）
    {
        //是否可以放置
        boolean isPlace = true;
        for (int i = 0; i < 11; i++) {
            double random = Math.random();
            Gold gold;//添加成员变量，存放当前生成的金块
            if(random<0.3){
//                objectList.add(new GoldMini());
                gold = new GoldMini();
            }else if(random<0.7){
//                objectList.add(new Gold());
                gold = new Gold();
            }else{
//                objectList.add(new GoldPlus());
                gold = new GoldPlus();
            }

            for (Object obj:objectList){//增强for循环
                if (gold.getRec().intersects(obj.getRec())){
                    //发生重叠，不能放置
                    isPlace = false;
                }
            }
            if(isPlace){objectList.add(gold);}
            else{isPlace=true;i--;}

        }

        for (int i = 0; i < 3; i++) {
            Rock rock = new Rock();
            objectList.add(new Rock());
            for (Object obj:objectList){
                if (rock.getRec().intersects(obj.getRec())){
                    isPlace = false;
                }
            }
            if(isPlace){objectList.add(rock);}
            else{isPlace=true;i--;}
        }
    }
  void launch() throws InterruptedException {
        this.setVisible(true);
        this.setSize(768,1024);
        this.setLocationRelativeTo(null);
        this.setTitle("黄金矿工");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (state){
                    case 0:
                        if(e.getButton()==3){
                            state=1;
                            bg.startTime = System.currentTimeMillis();
                        }//鼠标右键进入游戏
                        break;
                    case 1:
                        if(e.getButton()==1&&line.state==0){//1为左键3为右键
                            line.state=1;
                        }
                        if(e.getButton()==3&&line.state==3&&bg.waterNum>0){
                            bg.waterFlag=true;
                            bg.waterNum--;
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1) {
                            bg.shop=true;
                        }
                        if(e.getButton()==3){
                            state=1;
                            bg.startTime=System.currentTimeMillis();
                        }
                        break;
                    case 3:
                    case 4:
                        if(e.getButton()==1){
                            state=0;
                            bg.reGame();
                            line.reGame();
                        }
                        break;
                    default:
                }


            }
        });

        while(true){//死循环，不停绘制
            repaint();
            nextLevel();
            Thread.sleep(10);//间隔十毫秒刷新一次，一秒刷新一百次
        }
    }

    //下一关
    public void nextLevel() throws InterruptedException {
        if(bg.gameTime() && state==1){
            if(bg.count>=bg.goal){
                if(bg.level==5){
                    state=4;
                }else{
                    state=2;
                    bg.level++;
                }


            }else{
                state=3;
            }
            dispose();//释放之前的窗体
            GameWin gameWin1 = new GameWin();//重新遍历一个组件
            gameWin1.launch();//绘制一个新的窗口
        }

    }
    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(768,1000);//画布大小
        Graphics gImage = offScreenImage.getGraphics();//为画布添加笔

        bg.paintself(gImage);//使用画布上的笔

        //        gold.paintself(gImage);

        if(state==1){
            //画金块
            for (Object obj:objectList){
                obj.paintself(gImage);
            }

            //画线

            try {
                line.paintSelf(gImage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        g.drawImage(offScreenImage,0,0,null);


    }
    //绘制方法：paint函数

    public static void main(String[] args) throws InterruptedException {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}

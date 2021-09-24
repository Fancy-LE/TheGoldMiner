package com.lxy;

import java.awt.*;

import static java.lang.Thread.*;

public class Line {
    //初始坐标
    int x=380;
    int y=180;
    //结束坐标
    int endx=500;
    int endy=500;
    //长度和角度
    double length = 100;

    double MIN_length = 100;
    double MAX_length = 750;
    double n = 0;
    //往返
    int dir = 1;
    //状态:0 摇摆；1 抓取；2 收回；3 抓取返回
    int state;
    //钩爪图片
    Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook.png");

    //接收主窗口中的元素
    GameWin frame;
    Line(GameWin frame){this.frame=frame;}//有参构造

    //碰撞检测
    void logic(){
        for (Object obj:this.frame.objectList){
            if(endx>obj.x && endx<obj.x+obj.width && endy>obj.y && endy<obj.y+obj.height){
                state=3;
                obj.flag=true;
            }
        }

    }

    //绘制
    void lines(Graphics g){
        endx = (int) (x + length*Math.cos(n*Math.PI));
        endy = (int) (y + length*Math.sin(n*Math.PI));
        g.setColor(Color.red);
        g.drawLine(x-1,y,endx-1,endy);
        g.drawLine(x,y,endx,endy);
        g.drawLine(x+1,y,endx+1,endy);
        g.drawImage(hook,endx-36,endy-2,null);
    }

    public void paintSelf(Graphics g) throws InterruptedException {
        logic();

        switch (state){
            case 0:
                if(n<0.1){
                    dir = 1;
                }else if(n>0.9){
                    dir = -1;
                }
                n = n + 0.005*dir;
                lines(g);
                break;
            case 1:
                if(length<MAX_length){
                    length=length+10;
                    lines(g);
                }else{
                    state=2;
                }
                break;
            case 2:
                if(length>MIN_length){
                    length=length-10;
                    lines(g);
                }else{
                    state=0;
                }
                break;
            case 3:
                int m=1;
                if(length>MIN_length){
                    length=length-10;
                    lines(g);
                    for (Object obj:this.frame.objectList) {
                        if(obj.flag){
                            m=obj.m;
                            obj.x = endx - obj.getWidth()/2;
                            obj.y = endy;
                            if(length<=MIN_length){
                                obj.x=-150;
                                obj.y=-150;
                                obj.flag=false;
                                bg.waterFlag=false;
                                //加分
                                bg.count=obj.count+bg.count;
                                state=0;
                            }
                            if (bg.waterFlag==true){
                                if(obj.type==1){
                                    m=1;
                                }
                                if(obj.type==2){
                                    obj.x=-150;
                                    obj.y=-150;
                                    obj.flag=false;
                                    bg.waterFlag=false;
                                    state=2;
                                }
                            }
                        }

                    }
                }
                Thread.sleep(m);
                break;
        }


    }

    void reGame(){
        n=0;
        length=100;

    }
}

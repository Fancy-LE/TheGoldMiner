package com.lxy;

import java.awt.*;

public class Gold extends Object {
    Gold(){//无参构造
//        this.x=300;
//        this.y=500;
        this.x=(int)(Math.random()*700);//0~700
        this.y=(int)(Math.random()*524+300);//300~850
        this.width=52;
        this.height=52;
        this.m=50;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold1.gif");
        this.count=4;
        this.type=1;
        //金块初始值
    }
}
class GoldMini extends Gold {
    GoldMini(){
        this.width=36;
        this.height=36;
        this.m=15;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold0.gif");
        this.count=2;
    }
}
class GoldPlus extends Gold {
    GoldPlus(){
        this.x=(int)(Math.random()*600);
        this.width=105;
        this.height=105;
        this.m=60;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/gold2.gif");
        this.count=8;
    }
}

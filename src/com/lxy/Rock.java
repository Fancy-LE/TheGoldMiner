package com.lxy;

import java.awt.*;

public class Rock extends Object {
    Rock(){//无参构造
        this.x=(int)(Math.random()*700);//0~700
        this.y=(int)(Math.random()*524+300);//300~850
        this.width=71;
        this.height=71;
        this.m=80;
        this.img = Toolkit.getDefaultToolkit().getImage("imgs/rock1.png");
        this.count=1;
        this.type=2;
        //金块初始值
    }
}

package com.lxy;

import java.awt.*;

public class Object {
    //坐标
    int x;
    int y;
    //宽高
    int width;
    int height;
    //图片
    Image img;
    //标记是否可以移动
    boolean flag = false;
    //质量
    int m;
    //积分
    int count;
    //类型1为金块，2为石块
    int type;

    void paintself(Graphics g){
        g.drawImage(img,x,y,null);
    }

    public int getWidth() {
        return width;
    }
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
    //获取矩形,Rectangle类已经封装获取矩形的方法
}

package com.mkt.snakeproject;

import java.awt.*;

/**
 * 节点类
 * 用于 食物、蛇的身体
 * 蛇：一组节点
 * 食物：一个随机生成的节点
 */
public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //画节点
    public void drawNode(Graphics g,int i){
        //通过i来判断当前节点是否为头节点
        if (i == 0) {
            g.fillOval(this.x,this.y,19,19);
        }else{
            g.fillRect(this.x,this.y,19,19);
        }
    }
}

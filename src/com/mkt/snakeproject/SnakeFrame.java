package com.mkt.snakeproject;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeMap;

/**
 * 游戏面板窗口
 * 蛇的绘制
 * 食物的绘制
 */
public class SnakeFrame extends Frame {

    SnakeControl snakeControl = new SnakeControl();

    public SnakeFrame() {
        /**
         * 设置游戏窗体的样式
         */
        this.setLocation(200, 100);
        this.setSize(1000, 1000);
        this.setBackground(Color.cyan);
        this.setTitle("SnakeProject");
        this.setResizable(false);
        //注册窗口监听
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //注册键盘监听
        //获取方向
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //控制蛇的方向
                //上：1，下：3，右：2，左：4
                int dir=-1;
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        dir=1;
                        break;
                    case KeyEvent.VK_DOWN:
                        dir=3;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dir=2;
                        break;
                    case KeyEvent.VK_LEFT:
                        dir=4;
                        break;
                }
                //判断方向更改的合理性
                if(snakeControl.getDircation()%2!=dir%2&&dir!=-1){
                    snakeControl.setDircation(dir);
                }
            }
        });

        this.setVisible(true);
    }

    //窗口的绘制
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.orange);
        //绘制蛇
        for (int i = 0; i < snakeControl.getSnake().length; i++) {
            snakeControl.getSnake()[i].drawNode(g, i);
        }
        //绘制食物
        snakeControl.getFood().drawNode(g,-1);
        //移动
        snakeControl.isDead();
        if(snakeControl.isEat()){
            snakeControl.addBody();
            snakeControl.newFoodNode();
        }else{
            snakeControl.move();
        }
        if (snakeControl.isIsrunning()==false){
            Font f1 = new Font("Helvetica",Font.PLAIN,150);
            g.setFont(f1);
            g.drawString("Game Over",100,700);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

    public void start() {
        while (snakeControl.isIsrunning()) {
            super.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.repaint();
    }
}

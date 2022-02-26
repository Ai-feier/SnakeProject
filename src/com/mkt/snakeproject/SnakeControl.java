package com.mkt.snakeproject;

public class SnakeControl {
    /**
     * 1、控制蛇的移动，方向
     * 2、控制食物的生成
     * 3、蛇吃食物
     * 4、判断游戏是否结束
     */

    /**
     * 判断游戏正在运行
     * 判断蛇是否死亡
     */
    private boolean isrunning = true;//游戏是否继续
    private int dircation = 2;//上：1，下：3，右：2，左：4
    private boolean iseated = false;

    //初始化蛇
    private Node[] snake = new Node[]{
            new Node(260, 200),
            new Node(240, 200),
            new Node(220, 200)
    };


    /**
     * 移动策略：
     * 删除最后一个节点
     * 加入新的头节点
     * 蛇吃到食物
     */
    public void move() {
        Node[] body = new Node[snake.length];
        body[0] = getNewNode();
        System.arraycopy(snake, 0, body, 1, body.length - 1);
        this.snake = body;
    }

    //新增头节点
    public Node getNewNode() {
        Node newNode = null;
        switch (dircation) {
            case 1:
                newNode = new Node(snake[0].getX(), snake[0].getY() - 20);
                break;
            case 2:
                newNode = new Node(snake[0].getX() + 20, snake[0].getY());
                break;
            case 3:
                newNode = new Node(snake[0].getX(), snake[0].getY() + 20);
                break;
            case 4:
                newNode = new Node(snake[0].getX() - 20, snake[0].getY());
                break;
        }
        return newNode;
    }

    //蛇吃到食物
    public boolean isEat() {
        //移动的下一个节点与食物节点相同
        if (this.getNewNode().getX() == food.getX() && this.getNewNode().getY() == food.getY()) {
            return true;
        } else {
            return false;
        }
    }

    //蛇吃到食物、蛇身体加长
    public void addBody() {
        Node[] body = new Node[snake.length + 1];
        body[0] = food;
        System.arraycopy(snake, 0, body, 1, snake.length);
        this.snake = body;
    }

    /**
     * 食物节点
     * 随机生成
     * 单个节点
     */
    private Node food = new Node(randomNumber(0, (1000 / 20) - 1) * 20, randomNumber(0, (1000 / 20) - 1) * 20);

    //生成随机食物
    public void newFoodNode() {
        food = null;
        Node newFood = new Node(randomNumber(2, (1000 / 20) - 1) * 20, randomNumber(2, (1000 / 20) - 1) * 20);
        this.food = newFood;
    }


    //生成随机数
    public int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * 判断游戏是否结束
     * 撞到自己的身体
     * 撞到边界
     */
    public void isDead() {
        for (int i = 0; i < snake.length; i++) {
            if (this.getNewNode().getX() == snake[i].getX() && this.getNewNode().getY() == snake[i].getY())
                this.isrunning = false;
            else if (this.getNewNode().getX()<20||this.getNewNode().getX()>=1000-20)
                this.isrunning=false;
            else if (this.getNewNode().getY()<=20||this.getNewNode().getY()>=1000-20)
                this.isrunning=false;
            else this.isrunning=true;
        }
    }


    public static void main(String[] args) {
        new SnakeFrame().start();
    }


    public Node[] getSnake() {
        return snake;
    }

    public void setSnake(Node[] snake) {
        this.snake = snake;
    }

    public boolean isIsrunning() {
        return isrunning;
    }

    public void setIsrunning(boolean isrunning) {
        this.isrunning = isrunning;
    }

    public int getDircation() {
        return dircation;
    }

    public void setDircation(int dircation) {
        this.dircation = dircation;
    }

    public Node getFood() {
        return food;
    }

    public void setFood(Node food) {
        this.food = food;
    }
}

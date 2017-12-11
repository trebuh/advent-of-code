package com.advent.day3;

public class Point {
    private int value = 0;
    private int abscissa;
    private int ordinate;

    void setCoordinates(int abs, int ord) {
        this.abscissa = abs;
        this.ordinate = ord;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    int getAbscissa(){
        return this.abscissa;
    }

    int getOrdinate(){
        return this.ordinate;
    }
    int getDistance() {
        return Math.abs(this.abscissa) + Math.abs(this.ordinate);
    }

    public String toString() {
        return "(" + this.abscissa + ';' + this.ordinate + "):" + this.value;

    }

    boolean equals(Point point) {
        return point.getAbscissa() == this.abscissa &&
                point.getOrdinate() == this.ordinate &&
                point.getValue() == this.value;
    }


    boolean isNeighbourOf(Point point) {
        int abs = point.getAbscissa();
        int ord = point.getOrdinate();
        boolean up = abs == this.abscissa && ord == this.ordinate + 1;
        boolean upRight = abs == this.abscissa + 1 && ord == this.ordinate + 1;
        boolean upLeft = abs == this.abscissa - 1 && ord == this.ordinate + 1;
        boolean down = abs == this.abscissa && ord == this.ordinate - 1;
        boolean downLeft = abs == this.abscissa - 1 && ord == this.ordinate -1;
        boolean downRight = abs == this.abscissa + 1 && ord == this.ordinate - 1;
        boolean left = abs == this.abscissa - 1 && ord == this.ordinate;
        boolean right = abs == this.abscissa + 1 && ord == this.ordinate;

        return up || upRight || upLeft || down || downLeft || downRight || left || right;

    }
}

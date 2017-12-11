package com.advent.day3;

import java.util.LinkedList;

public class Vector {

    static final  String LEFT = "L";
    static final String DOWN = "D";
    static final String UP = "U";
    static final String RIGHT = "R";

    private Point startingPoint;
    private String orientation;
    private int length;
    private LinkedList<Point> listOfPointsWithoutFirst = new LinkedList<>();

    Vector(Point startingPoint, String orientation, int length) {
        this.startingPoint = startingPoint;
        this.orientation = orientation;
        this.length = length;
    }

    LinkedList<Point> getListOfPointsWithoutFirst() {
        return this.listOfPointsWithoutFirst;
    }

    void buildVectorPoints() {
        Point currentPoint = this.startingPoint;

        for(int i = 0; i < this.length; i++) {
            Point nextPoint = new Point();
            nextPoint.setValue(currentPoint.getValue() +1);
            switch (this.orientation) {
                case LEFT:
                    nextPoint.setCoordinates(currentPoint.getAbscissa()-1, currentPoint.getOrdinate());
                    break;
                case RIGHT:
                    nextPoint.setCoordinates(currentPoint.getAbscissa()+1, currentPoint.getOrdinate());
                    break;
                case UP:
                    nextPoint.setCoordinates(currentPoint.getAbscissa(), currentPoint.getOrdinate()+1);
                    break;
                case DOWN:
                    nextPoint.setCoordinates(currentPoint.getAbscissa(), currentPoint.getOrdinate()-1);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            currentPoint = nextPoint;
            this.listOfPointsWithoutFirst.add(currentPoint);
        }
    }

    void buildVectorPointsWithNeighbours(LinkedList<Point> spiralPoints) {
        Point currentPoint = this.startingPoint;

        for(int i = 1; i <= this.length; i++) {
            Point nextPoint = new Point();
            switch (this.orientation) {
                case LEFT:
                    nextPoint.setCoordinates(currentPoint.getAbscissa()-1, currentPoint.getOrdinate());
                    break;
                case RIGHT:
                    nextPoint.setCoordinates(currentPoint.getAbscissa()+1, currentPoint.getOrdinate());
                    break;
                case UP:
                    nextPoint.setCoordinates(currentPoint.getAbscissa(), currentPoint.getOrdinate()+1);
                    break;
                case DOWN:
                    nextPoint.setCoordinates(currentPoint.getAbscissa(), currentPoint.getOrdinate()-1);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            int nextValue = 0;
            for (Point spiralPoint: spiralPoints) {
                if(spiralPoint.isNeighbourOf(nextPoint) && !spiralPoint.equals(currentPoint)) {
                    nextValue += spiralPoint.getValue();
                }
            }

            nextValue += currentPoint.getValue();

            nextPoint.setValue(nextValue);
            this.listOfPointsWithoutFirst.add(nextPoint);
            currentPoint = nextPoint;
        }
    }

    Point getLastPoint() {
        return this.listOfPointsWithoutFirst.getLast();
    }

    int getLength() {
        return this.length;
    }

    Point getStartingPoint() {
        return this.startingPoint;
    }

    String getOrientation() {
        return this.orientation;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("START: ").append(this.startingPoint).append(" ORIENTATION: ").append(this.orientation).
                append(" LENGTH: ").append(this.length).append( " LIST: ").append(this.startingPoint);
        for(Point point: this.listOfPointsWithoutFirst) {
            builder.append(point);
            if(!point.equals(this.getLastPoint())) {
                builder.append(' ');
            } else {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
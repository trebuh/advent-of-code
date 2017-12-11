package com.advent.day3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Spiral {

    private static int CLOCKWISE = 1;
    private static int COUNTERCLOCKWISE = -1;

    private Vector initialVector;
    private Map<String,String> movesSequence = new HashMap<>();
    private LinkedList<Point> spiralPoints = new LinkedList<>();

    private Spiral(Point initialPoint, int spiralOrientation, String initialVectorOrientation, int initialVectorLength) {
        this.initialVector = new Vector(initialPoint, initialVectorOrientation, initialVectorLength);
        this.spiralPoints.add(initialPoint);
        this.setMovesSequence(spiralOrientation);
    }

    private void setMovesSequence(int spiralOrientation) {
        if (spiralOrientation == CLOCKWISE) {
            this.movesSequence.put(Vector.RIGHT,Vector.DOWN);
            this.movesSequence.put(Vector.DOWN,Vector.LEFT);
            this.movesSequence.put(Vector.LEFT,Vector.UP);
            this.movesSequence.put(Vector.UP,Vector.RIGHT);
        } else if (spiralOrientation == COUNTERCLOCKWISE) {
            this.movesSequence.put(Vector.UP,Vector.LEFT);
            this.movesSequence.put(Vector.LEFT,Vector.DOWN);
            this.movesSequence.put(Vector.DOWN,Vector.RIGHT);
            this.movesSequence.put(Vector.RIGHT,Vector.UP);
        }
    }

    private void generatePoints(int limit) {
        Vector currentVector = this.initialVector;
        Point resultPoint = null;
        int offset = 0;
        if(limit == 1) {
            resultPoint = this.initialVector.getStartingPoint();
        }
        while (resultPoint == null) {
            int nextLength = currentVector.getLength() + offset%2;
            offset++;
            currentVector.buildVectorPoints();
            for(Point point: currentVector.getListOfPointsWithoutFirst()) {
                this.spiralPoints.add(point);
                if(point.getValue() == limit) {
                    resultPoint = point;
                    break;
                }
            }
            currentVector = new Vector(currentVector.getLastPoint(),
                    this.movesSequence.get(currentVector.getOrientation()), nextLength);
        }
    }

    private void generatePointsSquareValues(int limit) {
        Vector currentVector = this.initialVector;
        Point resultPoint = null;
        int offset = 0;
        if(limit == 1) {
            resultPoint = this.initialVector.getStartingPoint();
        }
        while (resultPoint == null) {
            int nextLength = currentVector.getLength() + offset%2;
            offset++;
            currentVector.buildVectorPointsWithNeighbours(this.spiralPoints);
            for(Point point: currentVector.getListOfPointsWithoutFirst()) {
                this.spiralPoints.add(point);
                if(point.getValue() >= limit) {
                    resultPoint = point;
                    break;
                }
            }
            currentVector = new Vector(currentVector.getLastPoint(),
                    this.movesSequence.get(currentVector.getOrientation()), nextLength);
        }
    }

    private Point getLastPoint() {
        return this.spiralPoints.getLast();
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Point point: this.spiralPoints) {
            builder.append(point);
            if(!point.equals(this.getLastPoint())) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }

    public static void main(String args[]) {

        System.out.println("Enter The Value for your input (number of spiral points) :");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        if(input <= 0) {
            System.out.println("ERROR Please provide a strictly positive number");
            System.exit(1);
        }

        Point startingPoint = new Point();
        startingPoint.setCoordinates(0,0);
        startingPoint.setValue(1);

        Spiral spiral = new Spiral(startingPoint, COUNTERCLOCKWISE, Vector.RIGHT, 1);
        spiral.generatePoints(input);
        System.out.println("FIRST SPIRAL: " +  spiral);
        System.out.println("VALUE: " + spiral.getLastPoint().getValue());
        System.out.println("DISTANCE: " + spiral.getLastPoint().getDistance());

        Spiral secondSpiral = new Spiral(startingPoint, COUNTERCLOCKWISE, Vector.RIGHT, 1);
        secondSpiral.generatePointsSquareValues(input);
        System.out.println("SECOND SPIRAL: " +  secondSpiral);
        System.out.println("LAST POINT SECOND SPIRAL: " + secondSpiral.getLastPoint());
    }
}
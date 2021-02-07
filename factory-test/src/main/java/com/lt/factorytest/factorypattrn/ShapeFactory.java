package com.lt.factorytest.factorypattrn;

/**
 * @author tong.luo
 * @description ShapeFactory
 * @date 2021/2/7 15:16
 */
public class ShapeFactory {

    public static final String CIRCLE = "CIRCLE";
    public static final String SQUARE = "SQUARE";
    public static final String RECTANGLE = "RECTANGLE";

    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case CIRCLE:
                return new Circle();
            case SQUARE:
                return new Square();
            case RECTANGLE:
                return new Rectangle();
            default:
                return null;
        }
    }
}

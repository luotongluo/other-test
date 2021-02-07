package com.lt.factorytest.factorypattrn;

/**
 * @author tong.luo
 * @description FactoryPatternMain
 * @date 2021/2/7 15:21
 */
public class FactoryPatternMain {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape = shapeFactory.getShape(ShapeFactory.CIRCLE);
        shape.draw();
        Shape shape1 = shapeFactory.getShape(ShapeFactory.SQUARE);
        shape1.draw();
        Shape shape2 = shapeFactory.getShape(ShapeFactory.RECTANGLE);
        shape2.draw();
    }
}

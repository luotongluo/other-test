package com.lt.factorytest.abstractfact;

import com.lt.factorytest.factorypattrn.Circle;
import com.lt.factorytest.factorypattrn.Rectangle;
import com.lt.factorytest.factorypattrn.Shape;
import com.lt.factorytest.factorypattrn.Square;

import static com.lt.factorytest.factorypattrn.ShapeFactory.CIRCLE;
import static com.lt.factorytest.factorypattrn.ShapeFactory.RECTANGLE;
import static com.lt.factorytest.factorypattrn.ShapeFactory.SQUARE;

/**
 * @author tong.luo
 * @description ShapeFactory
 * @date 2021/2/7 15:39
 */
public class ShapeFactory extends AbstractFactory {
    /**
     * getShape
     *
     * @param shapeType
     * @return
     */
    @Override
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

    /**
     * getColor
     *
     * @param color
     * @return
     */
    @Override
    public Color getColor(String color) {
        return null;
    }
}

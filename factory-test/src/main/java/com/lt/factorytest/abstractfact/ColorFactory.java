package com.lt.factorytest.abstractfact;

import com.lt.factorytest.factorypattrn.Shape;

/**
 * @author tong.luo
 * @description ColorFactory
 * @date 2021/2/7 15:40
 */
public class ColorFactory extends AbstractFactory {
    /**
     * getShape
     *
     * @param shape
     * @return
     */
    @Override
    public Shape getShape(String shape) {
        return null;
    }

    /**
     * getColor
     *
     * @param color
     * @return
     */
    @Override
    public Color getColor(String color) {
        switch (color) {
            case BLUE:
                return new Blue();
            case GREEN:
                return new Green();
            case RED:
                return new Red();
            default:
                return null;
        }
    }
}

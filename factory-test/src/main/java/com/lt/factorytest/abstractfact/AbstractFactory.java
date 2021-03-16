package com.lt.factorytest.abstractfact;

import com.lt.factorytest.factorypattrn.Shape;

/**
 * @author tong.luo
 * @description AbstractFactory
 * https://www.runoob.com/design-pattern/abstract-factory-pattern.html
 * @date 2021/2/7 15:36
 */
public abstract class AbstractFactory {
    public static final String RED = "RED";
    public static final String BLUE = "BLUE";
    public static final String GREEN = "GREEN";
    public static final String SHAPE = "SHAPE";
    public static final String COLOR = "COLOR";

    /**
     * getShape
     *
     * @param shape
     * @return
     */
    public abstract Shape getShape(String shape);

    /**
     * getColor
     *
     * @param color
     * @return
     */
    public abstract Color getColor(String color);
}

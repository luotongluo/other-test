package com.lt.factorytest.abstractfact;

import com.lt.factorytest.factorypattrn.ShapeFactory;

/**
 * @author tong.luo
 * @description AbstractMain
 * @date 2021/2/7 15:55
 */
public class AbstractMain {
    public static void main(String[] args) {
        AbstractFactory factory = FactoryProducer.getFactory(AbstractFactory.COLOR);
        factory.getColor(AbstractFactory.RED).fill();
        factory.getColor(AbstractFactory.BLUE).fill();
        factory.getColor(AbstractFactory.GREEN).fill();

        AbstractFactory shapeFactory = FactoryProducer.getFactory(AbstractFactory.SHAPE);
        shapeFactory.getShape(ShapeFactory.CIRCLE).draw();
        shapeFactory.getShape(ShapeFactory.RECTANGLE).draw();
        shapeFactory.getShape(ShapeFactory.SQUARE).draw();
    }
}

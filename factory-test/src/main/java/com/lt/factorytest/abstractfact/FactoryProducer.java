package com.lt.factorytest.abstractfact;

/**
 * @author tong.luo
 * @description FactoryProducer
 * @date 2021/2/7 15:37
 */
public class FactoryProducer {
    /**
     * getFactory
     *
     * @return
     */
    public static AbstractFactory getFactory(String choice) {
        if (AbstractFactory.SHAPE.equals(choice)) {
            return new ShapeFactory();
        } else if (AbstractFactory.COLOR.equals(choice)) {
            return new ColorFactory();
        }
        return null;
    }
}

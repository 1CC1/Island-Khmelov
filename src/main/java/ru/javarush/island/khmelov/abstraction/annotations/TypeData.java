package ru.javarush.island.khmelov.abstraction.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TypeData {
    String name();

    String icon();

    double maxWeight();

    int maxCountInCell();

    int flockSize() default 1;

    int maxSpeed();

    double maxFood();

}

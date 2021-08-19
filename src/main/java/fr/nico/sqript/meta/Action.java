package fr.nico.sqript.meta;

import fr.nico.sqript.structures.Side;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * @return The global name of this class.
     */
    String name();

    Feature[] features();

    int priority() default 0;
}
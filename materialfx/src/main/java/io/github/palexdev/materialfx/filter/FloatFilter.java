package io.github.palexdev.materialfx.filter;

import io.github.palexdev.materialfx.filter.base.NumberFilter;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Extension of {@link NumberFilter} for float fields.
 * <p></p>
 * Offers the following default {@link BiPredicateBean}s:
 * <p> - "is": checks for floats equality
 * <p> - "is not": checks for floats inequality
 * <p> - "greater than": checks if a float is greater than another float
 * <p> - "greater or equal to": checks if a float is greater or equal to another float
 * <p> - "lesser than": checks if a float is lesser than another float
 * <p> - "lesser or equal to": checks if a float is lesser or equal to another float
 */
public class FloatFilter<T> extends NumberFilter<T, Float> {

    //================================================================================
    // Constructors
    //================================================================================
    public FloatFilter(String name, Function<T, Float> extractor) {
        this(name, extractor, new FloatStringConverter());
    }

    public FloatFilter(String name, Function<T, Float> extractor, StringConverter<Float> converter) {
        super(name, extractor, converter);
    }

    //================================================================================
    // Overridden Methods
    //================================================================================
    @Override
    protected ObservableList<BiPredicateBean<Float, Float>> defaultPredicates() {
        return Stream.<BiPredicateBean<Float, Float>>of(
                new BiPredicateBean<>("is", Float::equals),
                new BiPredicateBean<>("is not", (aFloat, aFloat2) -> !aFloat.equals(aFloat2)),
                new BiPredicateBean<>("greater than", (aFloat, aFloat2) -> aFloat > aFloat2),
                new BiPredicateBean<>("greater or equal to", (aFloat, aFloat2) -> aFloat >= aFloat2),
                new BiPredicateBean<>("lesser than", (aFloat, aFloat2) -> aFloat < aFloat2),
                new BiPredicateBean<>("lesser or equal to", (aFloat, aFloat2) -> aFloat <= aFloat2)
        ).collect(FXCollectors.toList());
    }

    @SafeVarargs
    @Override
    protected final FloatFilter<T> extend(BiPredicateBean<Float, Float>... predicateBeans) {
        Collections.addAll(super.predicates, predicateBeans);
        return this;
    }
}
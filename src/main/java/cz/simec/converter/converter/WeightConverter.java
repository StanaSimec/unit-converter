package cz.simec.converter.converter;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeightConverter extends Converter {
    private static final Map<String, Double> unitsMap = Map.of(
            "kg", 1000.0,
            "g", 1.0,
            "mg", 0.001,
            "ounce", 28.3495231,
            "pound", 453.59237
    );

    @Override
    public Map<String, Double> getUnitsMap() {
        return unitsMap;
    }
}

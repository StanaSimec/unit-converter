package cz.simec.converter.converter;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TemperatureConverter extends Converter {
    private static final Map<String, Double> unitsMap = Map.of(
            "celsius", 1000.0,
            "fahrenheit", 1.0,
            "kelvin", 1.0
    );

    @Override
    public Map<String, Double> getUnitsMap() {
        return unitsMap;
    }
}

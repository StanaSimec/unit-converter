package cz.simec.converter.converter;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LengthConverter extends Converter {

    private static final Map<String, Double> units = Map.of(
            "km", 1000.0,
            "m", 1.0,
            "cm", 0.01,
            "mm", 0.001,
            "inch", 0.0254,
            "foot", 0.3048
    );

    @Override
    public Map<String, Double> getUnitsMap() {
        return units;
    }
}

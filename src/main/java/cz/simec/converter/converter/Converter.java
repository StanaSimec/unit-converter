package cz.simec.converter.converter;

import java.util.List;
import java.util.Map;

public abstract class Converter {

    public abstract Map<String, Double> getUnitsMap();

    public List<String> getUnitsNames() {
        return getUnitsMap().keySet().stream()
                .toList();
    }

    public double convert(String startUnitName, String endUnitName, double amount) {
        Double startUnitMultiplier = getMultiplier(startUnitName);
        Double endUnitMultiplier = getMultiplier(endUnitName);
        return convert(startUnitMultiplier, endUnitMultiplier, amount);
    }

    private Double getMultiplier(String name) {
        return getUnitsMap().get(name);
    }

    private double convert(Double startUnitMultiplier, Double endUnitMultiplier, double amount) {
        double baseAmount = amount * startUnitMultiplier;
        return baseAmount / endUnitMultiplier;
    }
}

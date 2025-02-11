package cz.simec.converter.controller;

import cz.simec.converter.converter.Converter;
import cz.simec.converter.converter.TemperatureConverter;
import cz.simec.converter.form.UnitForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TemperatureController {

    private final Converter converter;

    @Autowired
    public TemperatureController(TemperatureConverter converter) {
        this.converter = converter;
    }

    @GetMapping("/temp")
    public String form(Model model) {
        model.addAttribute("form", new UnitForm());
        model.addAttribute("options", converter.getUnitsNames());
        return "temp";
    }

    @GetMapping("/temp/convert")
    public String convert(@ModelAttribute UnitForm form, Model model) {
        if (!isFormValid(form)) {
            return "redirect:/temp";
        }
        model.addAttribute("form", form);
        double convertedAmount = converter.convert(
                form.getStartUnitName(),
                form.getEndUnitName(),
                form.getAmount()
        );
        model.addAttribute("endAmount", convertedAmount);
        return "result";
    }

    private boolean isFormValid(UnitForm unitForm) {
        return unitForm.getAmount() > 0.0
                && converter.getUnitsMap().containsKey(unitForm.getStartUnitName())
                && converter.getUnitsMap().containsKey(unitForm.getEndUnitName());
    }
}

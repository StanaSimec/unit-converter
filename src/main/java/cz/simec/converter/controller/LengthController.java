package cz.simec.converter.controller;

import cz.simec.converter.converter.Converter;
import cz.simec.converter.converter.LengthConverter;
import cz.simec.converter.form.UnitForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LengthController {

    private final Converter converter;

    @Autowired
    public LengthController(LengthConverter lengthConverter) {
        this.converter = lengthConverter;
    }

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("form", new UnitForm());
        model.addAttribute("options", converter.getUnitsNames());
        return "length";
    }

    @GetMapping("/convert")
    public String convert(@ModelAttribute UnitForm form, Model model) {
        if (!isFormValid(form)) {
            return "redirect:/";
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

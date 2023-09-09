package apap.tutorial.celsiusConverter.controller;

import apap.tutorial.celsiusConverter.model.CelsiusConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CelsiusConverterController {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private String getCelsiusConverterPage(String celsius, Model model){
        if (celsius.isBlank() || celsius.isEmpty() || !pattern.matcher(celsius).matches()){
            model.addAttribute("message", "Isi nilai celsius yang ingin di-convert dengan benar!");
        } else{
            final CelsiusConverter celsiusConverter = new CelsiusConverter(Double.parseDouble(celsius));
            model.addAttribute("celsiusConverter", celsiusConverter);
        }
        return "celsiusConverterPage.html";
    }

    @GetMapping(value = "/celsius-converter")
    public String celsiusConverterRequestParam(@RequestParam(value = "celsius") String celsius, Model model) {
        return getCelsiusConverterPage(celsius, model);
    }

    @GetMapping(value = "/celsius-converter/{celsius}")
    public String celsiusConverterWithPathVariable(@PathVariable(value = "celsius") String celsius, Model model){
        return getCelsiusConverterPage(celsius, model);
    }
}

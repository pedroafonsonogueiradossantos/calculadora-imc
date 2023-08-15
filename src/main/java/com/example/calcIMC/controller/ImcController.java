package com.example.calcIMC.controller;

import com.example.calcIMC.model.ImcData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ImcController {

    @GetMapping("/")
    public String exibirCalculadoraImc(Model model) {
        model.addAttribute("imcData", new ImcData());
        model.addAttribute("classificacao",null);
        return "calculadora-imc";
    }

    @PostMapping("/calcular-imc")
    public String calcularImc(@ModelAttribute ImcData imcData, Model model) {
        double imc = imcData.getPeso() / (imcData.getAltura() * imcData.getAltura());
        String imcF = String.format("%.0f", imc);
        model.addAttribute("imcResultado", imcF);
        model.addAttribute("classificacao",classificacaoImc(imc));
        return "calculadora-imc";
    }

    public String classificacaoImc(double imc) {
        if (imc < 18.5) {
            return "Abaixo do Peso";
        } else if (imc < 25) {
            return "Peso Normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else if (imc < 35) {
            return "Obesidade Grau I";
        } else if (imc < 40) {
            return "Obesidade Grau II";
        } else {
            return "Obesidade Grau III";
        }
    }

}

package br.com.luciano.controller;

import br.com.luciano.exception.DivisionByZeroException;
import br.com.luciano.exception.UnsupportedMathOperationException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {

        if((!isNumeric(numberOne)) || (!isNumeric(numberTwo))) throw new UnsupportedMathOperationException("Please insert a numeric value");

        return converToDouble(numberOne) + converToDouble(numberTwo);
    }

    @RequestMapping("/subtract/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo){

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please insert a numeric value");

        return converToDouble(numberOne) - converToDouble(numberTwo);
    }

    @RequestMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiply(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo){

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please insert a numeric value");

        return converToDouble(numberOne) * converToDouble(numberTwo);
    }

    @RequestMapping("/divide/{numberOne}/{numberTwo}")
    public Double divide(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo){

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please insert a numeric value");

        return converToDouble(numberOne) / converToDouble(numberTwo);
    }

    @RequestMapping("/square/{number}")
    public Double square(@PathVariable(value="number") String number){

        if(!isNumeric(number)) throw new UnsupportedMathOperationException("Please insert a numeric value");

        return Math.sqrt(converToDouble(number));
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {

        if((!isNumeric(numberOne)) || (!isNumeric(numberTwo))) throw new UnsupportedMathOperationException("Please insert a numeric value");

        return (converToDouble(numberOne) + converToDouble(numberTwo)) / 2;
    }

    private boolean isNumeric(String strNumber) throws UnsupportedMathOperationException{
        strNumber = strNumber = strNumber.replace(",", ".");

        if(NumberUtils.isCreatable(strNumber)){
            return true;
        }
        return false;
    }

    private Double converToDouble(String strNumber){

        strNumber = strNumber = strNumber.replace(",", ".");

        return Double.parseDouble(strNumber);
    }
}

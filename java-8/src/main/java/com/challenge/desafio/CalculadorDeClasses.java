package com.challenge.desafio;
import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object o) {

        BigDecimal totalizador = BigDecimal.ZERO;

        for(Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Somar.class) && field.getType().equals(BigDecimal.class)){

                try {
                    totalizador = totalizador.add((BigDecimal) field.get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return totalizador;
    }

    @Override
    public BigDecimal subtrair(Object o) {

        BigDecimal totalizador = BigDecimal.ZERO;

        for(Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Subtrair.class) && field.getType().equals(BigDecimal.class)){

                try {
                    totalizador = totalizador.add((BigDecimal) field.get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return totalizador;
    }

    @Override
    public BigDecimal totalizar(Object o) {

        BigDecimal soma = somar(o);
        BigDecimal sub = subtrair(o);

        return soma.subtract(sub);
    }
}

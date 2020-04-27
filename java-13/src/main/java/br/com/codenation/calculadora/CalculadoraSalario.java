package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

		if (salarioBase < 1039)
			return Math.round(0.0);

		double inss = calcularInss(salarioBase);
		double irrf = calcularIrrf(salarioBase - inss);
		double salarioLiquido = salarioBase - inss - irrf;

		return Math.round(salarioLiquido);
	}

	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {

		if(salarioBase <= 1500.0){
			return salarioBase*0.08;
		} else if(salarioBase <= 4000.0){
			return salarioBase*0.09;
		} else if(salarioBase > 4000.0){
			return salarioBase*0.11;
		} else {
			return 0.0;
		}
	}

	private double calcularIrrf(double salarioBase) {

		if(salarioBase <= 3000.0){
			return 0.0;
		} else if(salarioBase <= 6000.0){
			return salarioBase*0.075;
		} else if(salarioBase > 6000.0){
			return salarioBase*0.15;
		} else {
			return 0.0;
		}
	}
}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/
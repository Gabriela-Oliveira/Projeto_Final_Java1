package br.com.serratec.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.serratec.calculos.FolhaPagamento;
import br.com.serratec.excecoes.DependenteException;

public class Funcionario extends Pessoa implements FolhaPagamento{
	private double salarioBruto;
	private double descontoInss;
	private double descontoIr;
	private Set <Dependente> dependentes = new HashSet <Dependente> ();
	private double salarioLiquido;
		
	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
	}

	
	
	@Override
	public double calculoInss() {
		 if (salarioBruto <= 1751.81) {
	            descontoInss = salarioBruto * 0.08;
	        } else if (salarioBruto >= 1751.82 && salarioBruto <= 2919.72) {
	            descontoInss = salarioBruto * 0.09;
	        } else if (salarioBruto >= 2919.73 && salarioBruto <= 5837.45) {
	            descontoInss = salarioBruto * 0.11;
	        } else {
	            descontoInss = 5839.456 * 0.11;
	        }
	        return descontoInss;
	    }
	
	@Override
	public double calculaIr() {
		double calculoIr;
		calculoIr = salarioBruto - descontoInss - (dependentes.size() * 189.59);
        if (calculoIr <= 1903.98) {
            descontoIr= 0;
        } else if (calculoIr >= 1903.99 && calculoIr <= 2826.65) {
            descontoIr = calculoIr * 0.075 - 142.80;
        } else if (calculoIr >= 2826.66 && calculoIr <= 3751.05) {
            descontoIr = calculoIr * 0.15 - 354.80;
        } else if (calculoIr >= 3751.06 && calculoIr <= 4664.68) {
            descontoIr = calculoIr * 0.22 - 636.13;
        } else {
            descontoIr = calculoIr * 0.275 - 869.36;
        }
        return descontoIr;
	}
	
	@Override
	public double salarioLiquido() {
		salarioLiquido = salarioBruto - descontoInss - descontoIr;
		return salarioLiquido;
	}

	@Override
	public String toString() {
		return  nome + ";" + cpf + ";" + String.format("%.2f", descontoInss) + ";" + String.format("%.2f", descontoIr) + ";" + 
				String.format("%.2f",salarioLiquido) + "\r";
				
	}

	public Set<Dependente> setDependentes() {
		return dependentes;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
}
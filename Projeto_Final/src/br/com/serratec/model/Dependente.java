package br.com.serratec.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.serratec.excecoes.DependenteException;

public class Dependente extends Pessoa {
	private Parentesco parentesco;


	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return "Dependente [parentesco=" + parentesco + "]";
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void verificarIdade() {
		LocalDate today = LocalDate.now();
		Period idade = Period.between(getDataNascimento(), today);
		if(idade.getYears() < 18) {
			
		} else {
			throw new DependenteException("Idade de dependente inválida!!");
		}
	}
}
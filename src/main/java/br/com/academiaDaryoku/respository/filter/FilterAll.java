package br.com.academiaDaryoku.respository.filter;

import java.time.LocalDate;

public class FilterAll {

	private String nome;
	
	private LocalDate date; 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}

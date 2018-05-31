package br.com.academiaDaryoku.model;

public enum TipoFaixa {
	
	A("Amarelo"), VER("Vermelha"), L("Laranja"), VERD("Verde"), R("Roxa"), M("Marrom"), P("Preto");

	private String tipo;
	
	TipoFaixa(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}

package br.com.academiaDaryoku.model;

public enum TipoEnum {

	ADM("Administrador"), EST("Estudante"), PF("Professor");

	private String tipo;

	TipoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}

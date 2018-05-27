package br.com.academiaDaryoku.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the tb_cidade database table.
 *
 */
@Entity
@Table(name = "tb_cidade")
@NamedQuery(name = "TbCidade.findAll", query = "SELECT t FROM TbCidade t")
public class TbCidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cidade")
	private int idCidade;

	@NotNull(message = "Informe uma cidade!")
	private String cidade;

	@ManyToOne
	@JoinColumn(name = "tb_estado_id_estado")
	private TbEstado tbEstado;

	public TbCidade() {
	}

	public int getIdCidade() {
		return this.idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public TbEstado getTbEstado() {
		return this.tbEstado;
	}

	public void setTbEstado(TbEstado tbEstado) {
		this.tbEstado = tbEstado;
	}

	@Override
	public String toString() {
		return cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCidade;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbCidade other = (TbCidade) obj;
		if (idCidade != other.idCidade)
			return false;
		return true;
	}

}
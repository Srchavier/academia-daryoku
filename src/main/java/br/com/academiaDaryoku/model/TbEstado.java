package br.com.academiaDaryoku.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the tb_estado database table.
 *
 */
@Entity
@Table(name = "tb_estado")
@NamedQuery(name = "TbEstado.findAll", query = "SELECT t FROM TbEstado t")
public class TbEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_estado")
	private int idEstado;

	@NotNull(message = "Informe um estado!")
	private String estado;

	private String sigla;

	@OneToMany(mappedBy = "tbEstado", fetch = FetchType.LAZY)
	private Set<TbCidade> tbCidades;

	public TbEstado() {
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Set<TbCidade> getTbCidades() {
		return tbCidades;
	}

	public void setTbCidades(Set<TbCidade> tbCidades) {
		this.tbCidades = tbCidades;
	}

	public TbCidade addTbCidade(TbCidade tbCidade) {
		getTbCidades().add(tbCidade);
		tbCidade.setTbEstado(this);

		return tbCidade;
	}

	public TbCidade removeTbCidade(TbCidade tbCidade) {
		getTbCidades().remove(tbCidade);
		tbCidade.setTbEstado(null);

		return tbCidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEstado;
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
		TbEstado other = (TbEstado) obj;
		if (idEstado != other.idEstado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return estado + "(" + sigla + ")";
	}

}
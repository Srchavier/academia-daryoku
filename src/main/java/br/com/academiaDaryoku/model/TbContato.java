package br.com.academiaDaryoku.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * The persistent class for the tb_contato database table.
 *
 */
@Entity
@Table(name = "tb_contato")
@NamedQuery(name = "TbContato.findAll", query = "SELECT t FROM TbContato t")
public class TbContato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_contato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContato;

	@NotNull(message = "Informe seu e-mail!")
	@Size(max = 120, min = 10)
	@Email(message = "Email valido")
	private String email;

	@NotNull(message = "Informe seu telefone!")
	private String tele1;

	private String tele2;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_pessoa_id_pessoa")
	private TbPessoa tbPessoa;

	public TbContato() {
	}

	public int getIdContato() {
		return this.idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele1() {
		return this.tele1;
	}

	public void setTele1(String tele1) {
		this.tele1 = tele1;
	}

	public String getTele2() {
		return this.tele2;
	}

	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}

	public TbPessoa getTbPessoa() {
		return this.tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tbPessoa == null) ? 0 : tbPessoa.hashCode());
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
		TbContato other = (TbContato) obj;
		if (tbPessoa == null) {
			if (other.tbPessoa != null)
				return false;
		} else if (!tbPessoa.equals(other.tbPessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbContato [idContato=" + idContato + ", email=" + email + ", tele1=" + tele1 + ", tele2=" + tele2
				+ ", tbPessoa=" + tbPessoa + "]";
	}

}
package br.com.academiaDaryoku.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the tb_endereco database table.
 *
 */
@Entity
@Table(name = "tb_endereco")
@NamedQuery(name = "TbEndereco.findAll", query = "SELECT t FROM TbEndereco t")
public class TbEndereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_endereco")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEndereco;

	@NotEmpty(message = "Informe um cep!")
	private String cep;

	private String complemento;

	@NotEmpty(message = "Informe um endereço!")
	@Size(max =50 , min = 20)
	private String logradouro;

	private int numero;

	// bi-directional many-to-one association to TbCidade
	@NotNull(message = "Cidade não pode ser nulo!")
	@ManyToOne(cascade= {CascadeType.REFRESH})
	@JoinColumn(name = "tb_cidade_id_cidade")
	private TbCidade tbCidade;

	// bi-directional one-to-one association to TbPessoa
	@NotNull(message = "Pessoa não pode ser nulo!")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_pessoa_id_pessoa")
	private TbPessoa tbPessoa;

	public TbEndereco() {
	}

	public int getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TbCidade getTbCidade() {
		return this.tbCidade;
	}

	public void setTbCidade(TbCidade tbCidade) {
		this.tbCidade = tbCidade;
	}

	public TbPessoa getTbPessoa() {
		return this.tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	@Override
	public String toString() {
		return "TbEndereco [idEndereco=" + idEndereco + ", cep=" + cep + ", complemento=" + complemento
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", tbCidade=" + tbCidade + ", tbPessoa="
				+ tbPessoa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEndereco;
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
		TbEndereco other = (TbEndereco) obj;
		if (idEndereco != other.idEndereco)
			return false;
		return true;
	}
}
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

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the tb_usuario database table.
 *
 */
@Entity
@Table(name = "tb_usuario")
@NamedQuery(name = "TbUsuario.findAll", query = "SELECT t FROM TbUsuario t")
public class TbUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@NotNull(message = "Informe um  login!")
	@Size(max = 9, min = 9)
	@Column(name = "mat_login")
	private String matLogin;

	@NotEmpty(message = "Informe uma senha!")
	@Column(name = "mat_senha")
	private String matSenha;

	// bi-directional one-to-one association to TbPessoa
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_pessoa_id_pessoa")
	private TbPessoa tbPessoa;

	public TbUsuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMatLogin() {
		return this.matLogin;
	}

	public void setMatLogin(String matLogin) {
		this.matLogin = matLogin;
	}

	public String getMatSenha() {
		return this.matSenha;
	}

	public void setMatSenha(String matSenha) {
		this.matSenha = matSenha;
	}

	public TbPessoa getTbPessoa() {
		return this.tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	@Override
	public String toString() {
		return "TbUsuario [idUsuario=" + idUsuario + ", matLogin=" + matLogin + ", matSenha=" + matSenha + ", tbPessoa="
				+ tbPessoa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
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
		TbUsuario other = (TbUsuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}

}
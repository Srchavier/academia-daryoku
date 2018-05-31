package br.com.academiaDaryoku.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the tb_pessoa database table.
 *
 */
@Entity
@Table(name = "tb_pessoa")
@NamedQuery(name = "TbPessoa.findAll", query = "SELECT t FROM TbPessoa t")
public class TbPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pessoa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPessoa;

	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@NotNull(message = "Informe data de nascimento!")
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dtNascimento;

	@NotNull(message = "Informe um sexo!")
	@Column(name = "fl_sexo")
	@Enumerated(EnumType.STRING)
	private SexoEnum flSexo;
	
	@Size(max = 50)
	private String foto;

	@Column(name = "nm_pessoa")
	@NotEmpty(message = "Informe um nome")
	@Size(max = 120, min = 15)
	private String nmPessoa;

	@Enumerated(EnumType.STRING)
	private TipoEnum tipo;
	
	@Enumerated(EnumType.STRING)
	private TipoFaixa tipoFaixa;

	@NotNull(message = "Informe uma Turma!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_turma_id_turma")
	private TbTurma tbTurma;

	public TbPessoa() {
	}

	public int getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNmPessoa() {
		return this.nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public TbTurma getTbTurma() {
		return this.tbTurma;
	}

	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}

	public SexoEnum getFlSexo() {
		return flSexo;
	}

	public void setFlSexo(SexoEnum flSexo) {
		this.flSexo = flSexo;
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	public TipoFaixa getTipoFaixa() {
		return tipoFaixa;
	}

	public void setTipoFaixa(TipoFaixa tipoFaixa) {
		this.tipoFaixa = tipoFaixa;
	}

	@Override
	public String toString() {
		return "TbPessoa [idPessoa=" + idPessoa + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro + ", dtNascimento="
				+ dtNascimento + ", flSexo=" + flSexo + ", nmPessoa=" + nmPessoa + ", tipo=" + tipo + ", tbTurma="
				+ tbTurma + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPessoa;
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
		TbPessoa other = (TbPessoa) obj;
		if (idPessoa != other.idPessoa)
			return false;
		return true;
	}

}
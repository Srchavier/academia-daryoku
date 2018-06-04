package br.com.academiaDaryoku.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the tb_turma database table.
 *
 */

@Entity
@Table(name = "tb_turma")
@NamedQuery(name = "TbTurma.findAll", query = "SELECT t FROM TbTurma t")
public class TbTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private int idTurma;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_fim")
	private Date dtFim;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_inicio")
	private Date dtInicio;

	@NotNull
	@Size(max = 45, min = 5)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tb_pessoa_id_pessoa")
	private TbPessoa tbPessoa;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_evento_has_tb_turma", joinColumns = {
			@JoinColumn(name = "tb_turma_id_turma") }, inverseJoinColumns = {
					@JoinColumn(name = "tb_evento_id_Evento") })
	private List<TbEvento> tbEventos;

	// bi-directional one-to-one association to TbDiassemana
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_diasSemana", referencedColumnName = "id_diasSemana")
	private TbDiassemana tbDiassemana;

	@OneToMany(mappedBy = "tbTurma", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TbAula> tbAulas;

	public TbTurma() {
		tbDiassemana = new TbDiassemana();
		tbAulas = new ArrayList<>();
	}

	public int getIdTurma() {
		return this.idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Date getDtInicio() {
		return this.dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TbEvento> getTbEventos() {
		return tbEventos;
	}

	public void setTbEventos(List<TbEvento> tbEventos) {
		this.tbEventos = tbEventos;
	}

	public TbPessoa getTbPessoa() {
		return tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	public TbDiassemana getTbDiassemana() {
		return this.tbDiassemana;
	}

	public void setTbDiassemana(TbDiassemana tbDiassemana) {
		this.tbDiassemana = tbDiassemana;
	}

	public List<TbAula> getTbAulas() {
		return tbAulas;
	}

	public void setTbAulas(List<TbAula> tbAulas) {
		this.tbAulas = tbAulas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTurma;
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
		TbTurma other = (TbTurma) obj;
		if (idTurma != other.idTurma)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

	public TbAula adicionarAula(TbAula aula) {
		aula.setTbTurma(this);
		getTbAulas().add(aula);

		return aula;
	}


}
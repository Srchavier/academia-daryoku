package br.com.academiaDaryoku.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the tb_evento database table.
 *
 */
@Entity
@Table(name = "tb_evento")
@NamedQuery(name = "TbEvento.findAll", query = "SELECT t FROM TbEvento t")
public class TbEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Evento;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFim;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInicil;

	@NotNull
	@Size(max = 800, min = 5)
	private String descricao;

	@NotNull
	@Size(max = 45, min = 5)
	private String nome;

	@NotNull
	@Size(max = 45, min = 5)
	private String subTitulo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_evento_has_tb_turma", joinColumns = {
			@JoinColumn(name = "tb_evento_id_Evento") }, inverseJoinColumns = {
					@JoinColumn(name = "tb_turma_id_turma") })
	private List<TbTurma> tbTurmas;

	public TbEvento() {
	}

	public Long getId_Evento() {
		return this.id_Evento;
	}

	public void setId_Evento(Long id_Evento) {
		this.id_Evento = id_Evento;
	}

	public Date getDateFim() {
		return this.dateFim;
	}

	public void setDateFim(Date dateFim) {
		this.dateFim = dateFim;
	}

	public Date getDateInicil() {
		return this.dateInicil;
	}

	public void setDateInicil(Date dateInicil) {
		this.dateInicil = dateInicil;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSubTitulo() {
		return this.subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public List<TbTurma> getTbTurmas() {
		return tbTurmas;
	}

	public void setTbTurmas(List<TbTurma> tbTurmas) {
		this.tbTurmas = tbTurmas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_Evento ^ (id_Evento >>> 32));
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
		TbEvento other = (TbEvento) obj;
		if (id_Evento != other.id_Evento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbEvento [id_Evento=" + id_Evento + ", dateFim=" + dateFim + ", dateInicil=" + dateInicil
				+ ", descricao=" + descricao + ", nome=" + nome + ", subTitulo=" + subTitulo + ", tbTurmas=" + tbTurmas
				+ "]";
	}

}
package br.com.academiaDaryoku.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * The persistent class for the tb_aula database table.
 * 
 */
@Entity
@Table(name = "tb_aula")
@NamedQuery(name = "TbAula.findAll", query = "SELECT t FROM TbAula t")
public class TbAula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_aula")
	private Date dtAula;

	@Column(name = "hr_fim")
	private Time hrFim;

	@Column(name = "hr_inicio")
	private Time hrInicio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aula")
	private int idAula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
	private TbTurma tbTurma;

	public TbAula() {

	}

	public TbAula(LocalDate dataAux, LocalTime hrInicio, LocalTime hrFim) {
		dtAula = Date.from(dataAux.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.hrInicio = Time.valueOf(hrInicio);
		this.hrFim = Time.valueOf(hrFim);
	}

	public Date getDtAula() {
		return this.dtAula;
	}

	public void setDtAula(Date dtAula) {
		this.dtAula = dtAula;
	}

	public Time getHrFim() {
		return this.hrFim;
	}

	public void setHrFim(Time hrFim) {
		this.hrFim = hrFim;
	}

	public Time getHrInicio() {
		return this.hrInicio;
	}

	public void setHrInicio(Time hrInicio) {
		this.hrInicio = hrInicio;
	}

	public int getIdAula() {
		return this.idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public TbTurma getTbTurma() {
		return this.tbTurma;
	}

	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}

	@Override
	public String toString() {
		return "TbAula [dtAula=" + dtAula + ", hrFim=" + hrFim + ", hrInicio=" + hrInicio + ", idAula=" + idAula
				+ ", tbTurma=" + tbTurma + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAula;
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
		TbAula other = (TbAula) obj;
		if (idAula != other.idAula)
			return false;
		return true;
	}

	public static LocalTime gvxg(Date d) {
		Instant instant = Instant.ofEpochMilli(d.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
	}

}
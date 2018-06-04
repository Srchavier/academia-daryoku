package br.com.academiaDaryoku.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tb_diassemana database table.
 * 
 */

@Entity
@Table(name = "tb_diassemana")
@NamedQuery(name = "TbDiassemana.findAll", query = "SELECT t FROM TbDiassemana t")
public class TbDiassemana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_diasSemana;

	@Embedded
	private DiasDaSemana diasDaSemana;

	public TbDiassemana() {
		diasDaSemana = new DiasDaSemana();
	}

	public TbDiassemana(TbTurma tbTurma2) {
		this.id_diasSemana = tbTurma2.getTbDiassemana().id_diasSemana;
		this.diasDaSemana = tbTurma2.getTbDiassemana().getDiasDaSemana();
	}

	public DiasDaSemana getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(DiasDaSemana diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public Integer getId_diasSemana() {
		return id_diasSemana;
	}

	public void setId_diasSemana(Integer id_diasSemana) {
		this.id_diasSemana = id_diasSemana;
	}

}
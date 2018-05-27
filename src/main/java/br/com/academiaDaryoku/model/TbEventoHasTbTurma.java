package br.com.academiaDaryoku.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_evento_has_tb_turma database table.
 * 
 */
@Entity
@Table(name="tb_evento_has_tb_turma")
@NamedQuery(name="TbEventoHasTbTurma.findAll", query="SELECT t FROM TbEventoHasTbTurma t")
public class TbEventoHasTbTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbEventoHasTbTurmaPK id;

	public TbEventoHasTbTurma() {
	}

	public TbEventoHasTbTurmaPK getId() {
		return this.id;
	}

	public void setId(TbEventoHasTbTurmaPK id) {
		this.id = id;
	}

}
package br.com.academiaDaryoku.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_evento_has_tb_turma database table.
 * 
 */
@Embeddable
public class TbEventoHasTbTurmaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int tb_evento_id_Evento;

	@Column(name="tb_turma_id_turma", insertable=false, updatable=false)
	private int tbTurmaIdTurma;

	public TbEventoHasTbTurmaPK() {
	}
	public int getTb_evento_id_Evento() {
		return this.tb_evento_id_Evento;
	}
	public void setTb_evento_id_Evento(int tb_evento_id_Evento) {
		this.tb_evento_id_Evento = tb_evento_id_Evento;
	}
	public int getTbTurmaIdTurma() {
		return this.tbTurmaIdTurma;
	}
	public void setTbTurmaIdTurma(int tbTurmaIdTurma) {
		this.tbTurmaIdTurma = tbTurmaIdTurma;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TbEventoHasTbTurmaPK)) {
			return false;
		}
		TbEventoHasTbTurmaPK castOther = (TbEventoHasTbTurmaPK)other;
		return 
			(this.tb_evento_id_Evento == castOther.tb_evento_id_Evento)
			&& (this.tbTurmaIdTurma == castOther.tbTurmaIdTurma);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tb_evento_id_Evento;
		hash = hash * prime + this.tbTurmaIdTurma;
		
		return hash;
	}
}
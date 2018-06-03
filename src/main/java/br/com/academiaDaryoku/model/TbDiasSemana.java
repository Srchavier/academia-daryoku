package br.com.academiaDaryoku.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_diassemana database table.
 * 
 */
@Entity
@Table(name="tb_diassemana")
@NamedQuery(name="TbDiasSemana.findAll", query="SELECT t FROM TbDiasSemana t")
public class TbDiasSemana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_diasSemana;
	
	private boolean dom;

	private boolean qua;

	private boolean qui;

	private boolean sab;

	private boolean seg;

	private boolean sex;

	private boolean ter;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_diasSemana", referencedColumnName="id_diasSemana")
	private TbTurma tbTurma;
	
	public TbDiasSemana() {
	}
	
	
	public int getId_diasSemana() {
		return id_diasSemana;
	}

	public void setId_diasSemana(int id_diasSemana) {
		this.id_diasSemana = id_diasSemana;
	}


	public boolean isDom() {
		return dom;
	}


	public void setDom(boolean dom) {
		this.dom = dom;
	}


	public boolean isQua() {
		return qua;
	}


	public void setQua(boolean qua) {
		this.qua = qua;
	}


	public boolean isQui() {
		return qui;
	}


	public void setQui(boolean qui) {
		this.qui = qui;
	}


	public boolean isSab() {
		return sab;
	}


	public void setSab(boolean sab) {
		this.sab = sab;
	}


	public boolean isSeg() {
		return seg;
	}


	public void setSeg(boolean seg) {
		this.seg = seg;
	}


	public boolean isSex() {
		return sex;
	}


	public void setSex(boolean sex) {
		this.sex = sex;
	}


	public boolean isTer() {
		return ter;
	}


	public void setTer(boolean ter) {
		this.ter = ter;
	}


	public TbTurma getTbTurma() {
		return tbTurma;
	}


	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}


	@Override
	public String toString() {
		return "TbDiasSemana [dom=" + dom + ", qua=" + qua + ", qui=" + qui + ", sab=" + sab + ", seg=" + seg + ", sex="
				+ sex + ", ter=" + ter + ", tbTurma=" + tbTurma + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_diasSemana;
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
		TbDiasSemana other = (TbDiasSemana) obj;
		if (id_diasSemana != other.id_diasSemana)
			return false;
		return true;
	}

}
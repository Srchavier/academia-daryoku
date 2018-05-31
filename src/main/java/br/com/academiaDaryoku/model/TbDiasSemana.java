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
	
	private byte dom;

	private byte qua;

	private byte qui;

	private byte sab;

	private byte seg;

	private byte sex;

	private byte ter;

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

	public byte getDom() {
		return this.dom;
	}

	public void setDom(byte dom) {
		this.dom = dom;
	}

	public byte getQua() {
		return this.qua;
	}

	public void setQua(byte qua) {
		this.qua = qua;
	}

	public byte getQui() {
		return this.qui;
	}

	public void setQui(byte qui) {
		this.qui = qui;
	}

	public byte getSab() {
		return this.sab;
	}

	public void setSab(byte sab) {
		this.sab = sab;
	}

	public byte getSeg() {
		return this.seg;
	}

	public void setSeg(byte seg) {
		this.seg = seg;
	}

	public byte getSex() {
		return this.sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getTer() {
		return this.ter;
	}

	public void setTer(byte ter) {
		this.ter = ter;
	}

	public TbTurma getTbTurma() {
		return this.tbTurma;
	}

	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}


	@Override
	public String toString() {
		return "TbDiasSemana [id_diasSemana=" + id_diasSemana + ", dom=" + dom + ", qua=" + qua + ", qui=" + qui
				+ ", sab=" + sab + ", seg=" + seg + ", sex=" + sex + ", ter=" + ter + ", tbTurma=" + tbTurma + "]";
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
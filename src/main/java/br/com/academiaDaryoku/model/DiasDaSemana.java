package br.com.academiaDaryoku.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DiasDaSemana implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private boolean dom;

	private boolean qua;

	private boolean qui;

	private boolean sab;

	private boolean seg;

	private boolean sex;

	private boolean ter;

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

}

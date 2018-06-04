package br.com.academiaDaryoku.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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


/**
 * The persistent class for the tb_presenca database table.
 * 
 */
@Entity
@Table(name="tb_presenca")
@NamedQuery(name="TbPresenca.findAll", query="SELECT t FROM TbPresenca t")
public class TbPresenca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_presenca")
	private int idPresenca;

	@Column
	private byte presenca;

	//bi-directional many-to-one association to TbAula
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="id_aula", referencedColumnName="id_aula")
	private TbAula tbAula;

	//bi-directional many-to-one association to TbPessoa
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa", referencedColumnName="id_pessoa")
	private TbPessoa tbPessoa;

	public TbPresenca() {
	}

	public int getIdPresenca() {
		return this.idPresenca;
	}

	public void setIdPresenca(int idPresenca) {
		this.idPresenca = idPresenca;
	}

	

	public TbAula getTbAula() {
		return this.tbAula;
	}

	public void setTbAula(TbAula tbAula) {
		this.tbAula = tbAula;
	}

	public TbPessoa getTbPessoa() {
		return this.tbPessoa;
	}

	public byte getPresenca() {
		return presenca;
	}

	public void setPresenca(byte presenca) {
		this.presenca = presenca;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

}
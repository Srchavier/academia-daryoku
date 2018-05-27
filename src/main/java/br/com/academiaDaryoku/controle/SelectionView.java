package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.service.PessoaService;

@Named(value = "controleDePresenca")
@ViewScoped
public class SelectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	private TbPessoa selectedPessoa;
	private List<TbPessoa> selectedPessoas;

	List<TbPessoa> listaDeAlunos;

	@Inject
	private PessoaService pessoaService;

	@PostConstruct
	public void init() {
		listaDeAlunos = new ArrayList<>();
		selectedPessoas = new ArrayList<>();
		setSelectedPessoa(new TbPessoa());
		buscar();
	}

	private void buscar() {
		listaDeAlunos = pessoaService.buscatodos();
	}

	public List<TbPessoa> getSelectedPessoas() {
		return selectedPessoas;
	}

	public void setSelectedPessoas(List<TbPessoa> selectedPessoas) {
		this.selectedPessoas = selectedPessoas;
	}

	public List<TbPessoa> getListaDeAlunos() {
		return listaDeAlunos;
	}

	public void setListaDeAlunos(List<TbPessoa> listaDeAlunos) {
		this.listaDeAlunos = listaDeAlunos;
	}

	public TbPessoa getSelectedPessoa() {
		return selectedPessoa;
	}

	public void setSelectedPessoa(TbPessoa selectedPessoa) {
		this.selectedPessoa = selectedPessoa;
	}

}
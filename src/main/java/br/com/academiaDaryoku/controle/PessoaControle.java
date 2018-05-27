package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.primefaces.PrimeFaces;

import br.com.academiaDaryoku.converter.ConverterCidade;
import br.com.academiaDaryoku.converter.ConverterEstado;
import br.com.academiaDaryoku.converter.ConverterTurma;
import br.com.academiaDaryoku.model.TbCidade;
import br.com.academiaDaryoku.model.TbContato;
import br.com.academiaDaryoku.model.TbEndereco;
import br.com.academiaDaryoku.model.TbEstado;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.model.TipoEnum;
import br.com.academiaDaryoku.respository.filter.FilterAll;
import br.com.academiaDaryoku.service.CidadeService;
import br.com.academiaDaryoku.service.ContatoService;
import br.com.academiaDaryoku.service.EnderencoService;
import br.com.academiaDaryoku.service.EstadoService;
import br.com.academiaDaryoku.service.LoginService;
import br.com.academiaDaryoku.service.PessoaService;
import br.com.academiaDaryoku.service.TurmaService;
import br.com.academiaDaryoku.ultils.Sha256;
import br.com.academiaDaryoku.ultils.UtilErros;
import br.com.academiaDaryoku.ultils.UtilMensagens;

@Named(value = "pessoaControle")
@ViewScoped
public class PessoaControle implements Serializable {

	private static final long serialVersionUID = 6835397027661347473L;

	private String pesquisar;

	private TbEndereco tbEndereco;

	private TbCidade tbCidade;

	private TbEstado tbEstado;

	private TbTurma tbTurma;

	private TbPessoa tbPessoa;

	private TbContato tbContato;

	private TbUsuario tbUsuario;

	private TbPessoa tbPessoaSelecionada;

	private List<TbPessoa> pessoa;

	private List<TbCidade> listaCidades;

	@Inject
	private PessoaService pessoaService;

	@Inject
	private ContatoService contatoService;

	@Inject
	private EnderencoService enderencoService;

	@Inject
	private LoginService usuarioService;

	@Inject
	private TurmaService turmaService;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private EstadoService estadoService;

	@Inject
	private ConverterTurma converterTurma;

	@Inject
	private ConverterEstado converterEstado;

	@Inject
	private ConverterCidade converterCidade;

	@PostConstruct
	public void inicializar() {
		newModel();
		tbPessoaSelecionada = null;
		buscarTodos();
	}

	public void novo() {
		newModel();
		String gg = gerarMatricula();
		try {
			usuarioService.porMatLogin(gg);
			novo();
		} catch (NoResultException e) {
			tbUsuario.setMatLogin(gg);
		}
		tbPessoaSelecionada = null;
	}

	private void newModel() {
		tbPessoa = new TbPessoa();
		tbContato = new TbContato();
		tbTurma = new TbTurma();
		tbUsuario = new TbUsuario();
		tbEndereco = new TbEndereco();
		tbEstado = new TbEstado();
		tbCidade = new TbCidade();
	}

	public void buscarTodos() {
		pessoa = new ArrayList<>();
		listaCidades = new ArrayList<>();
		pessoa = pessoaService.buscatodos();
	}

	public void pesquisarNome() {
		FilterAll filter = new FilterAll();
		filter.setNome(pesquisar);

		pessoa = new ArrayList<>();
		if (!(pessoa = pessoaService.buscaPorNome(filter)).isEmpty()) {
			UtilMensagens.mensagemInformacao(pessoa.size() + " Regristro encontrad(o)!");
		} else {
			UtilMensagens.mensagemInformacao("Não encontrado!");
		}
		PrimeFaces.current().ajax().update(Arrays.asList("form:msgs", "form:pessoa-table"));
	}

	public TbPessoa getTbPessoaSelecionada() {
		return tbPessoaSelecionada;
	}

	public void setTbPessoaSelecionada(TbPessoa tbPessoaSelecionada) {
		this.tbPessoaSelecionada = tbPessoaSelecionada;
	}

	public void editarPessoa() {
		tbTurma = tbPessoa.getTbTurma();
		tbEndereco = enderencoService.porIdPessoa(tbPessoaSelecionada.getIdPessoa());
		tbContato = contatoService.porIdPessoa(tbPessoaSelecionada.getIdPessoa());
		tbUsuario = usuarioService.porIdPessoa(tbPessoaSelecionada.getIdPessoa());
		tbUsuario.setMatSenha("");
		tbCidade = tbEndereco.getTbCidade();
		tbEstado = tbEndereco.getTbCidade().getTbEstado();
		listaCidades = (cidadeService.findPorIdEstado(tbEndereco.getTbCidade().getTbEstado().getIdEstado()));
	}

	public List<TbCidade> EstadoSelecionadoCidade() {
		int id = tbEstado.getIdEstado();
		try {
			return listaCidades = cidadeService.findPorIdEstado(id);
		} catch (Exception e) {
			UtilErros.getMensagemErro(e);
			return null;
		}
	}

	public void salvar() {
		if (salvarUsuario()) {
			PrimeFaces.current().ajax().addCallbackParam("validacaoMat", true);
			PrimeFaces.current().ajax().update(Arrays.asList("form:msgs", "form:pessoa-table"));
		}
	}

	private synchronized boolean salvarUsuario() {

		if (this.tbPessoa.getTipo().equals(TipoEnum.PF)) {
			if (turmaService.isProfessor(this.tbPessoa)) {
				UtilMensagens.mensagemInformacao("Professor já existe!!!");
				PrimeFaces.current().ajax().addCallbackParam("validacaoMat", false);
				return false;
			} else {
				if (tbPessoa.getDataCadastro() == null) {
					Date data = new Date();
					this.tbPessoa.setDataCadastro(data);
				}
				TbPessoa pessoaSalvar = pessoaService.salvar(this.tbPessoa);
				tbTurma = pessoaSalvar.getTbTurma();
				turmaService.isNullPessoaTurma(pessoaSalvar);
				tbTurma.setTbPessoa(pessoaSalvar);
				turmaService.alterar(tbTurma);
				tbContato.setTbPessoa(pessoaSalvar);
				contatoService.salvar(this.tbContato);
				tbEndereco.setTbCidade(this.tbCidade);
				tbEndereco.setTbPessoa(pessoaSalvar);
				enderencoService.salvar(this.tbEndereco);
				tbUsuario.setMatSenha(Sha256.shaSet(this.tbUsuario.getMatSenha()));
				tbUsuario.setTbPessoa(pessoaSalvar);
				usuarioService.salvar(this.tbUsuario);
				tbUsuario.setMatSenha("");
				UtilMensagens.mensagemInformacao("Professor Salvo ou Alteranda com sucesso!");
				PrimeFaces.current().ajax().update(Arrays.asList("form:pessoa-table"));
				return true;
			}
		}

		if (this.tbPessoa.getTipo().equals(TipoEnum.ADM) || this.tbPessoa.getTipo().equals(TipoEnum.EST)) {

			if (tbPessoaSelecionada != null) {
				TbPessoa pessoaSalvar = pessoaService.alterar(this.tbPessoa);
				turmaService.isNullPessoaTurma(pessoaSalvar);
				tbContato.setTbPessoa(pessoaSalvar);
				contatoService.alterar(this.tbContato);
				tbEndereco.setTbCidade(this.tbCidade);
				tbEndereco.setTbPessoa(pessoaSalvar);
				enderencoService.alterar(this.tbEndereco);
				tbUsuario.setMatSenha(Sha256.shaSet(this.tbUsuario.getMatSenha()));
				tbUsuario.setTbPessoa(pessoaSalvar);
				usuarioService.alterar(this.tbUsuario);
				tbUsuario.setMatSenha("");
				buscarTodos();
				UtilMensagens.mensagemInformacao("Pessoa alterando com sucesso!");
				PrimeFaces.current().ajax().update(Arrays.asList("form:pessoa-table"));
				return true;
			} else {
				try {
					usuarioService.porMatLogin(this.tbUsuario.getMatLogin());
					UtilMensagens.mensagemInformacao("Error ao cadastrar matrícula já existe!!!");
					PrimeFaces.current().ajax().addCallbackParam("validacaoMat", false);
					return false;
				} catch (NoResultException e) {
					Date data = new Date();
					this.tbPessoa.setDataCadastro(data);
					TbPessoa pessoaSalvar = pessoaService.salvar(this.tbPessoa);
					tbContato.setTbPessoa(pessoaSalvar);
					contatoService.salvar(this.tbContato);
					tbEndereco.setTbCidade(this.tbCidade);
					tbEndereco.setTbPessoa(pessoaSalvar);
					enderencoService.salvar(this.tbEndereco);
					tbUsuario.setMatSenha(Sha256.shaSet(this.tbUsuario.getMatSenha()));
					tbUsuario.setTbPessoa(pessoaSalvar);
					usuarioService.salvar(this.tbUsuario);
					tbUsuario.setMatSenha("");
					buscarTodos();
					UtilMensagens.mensagemInformacao("Pessoa salva com sucesso!");
					PrimeFaces.current().ajax().update(Arrays.asList("form:pessoa-table"));
					return true;
				}
			}
		}
		return false;
	}

	public void excluir() {
		if (tbPessoaSelecionada == null) {
			UtilMensagens.mensagemErro("Error");
		}
		try {
			turmaService.isNullPessoaTurma(tbPessoaSelecionada);
			enderencoService.delete(tbPessoaSelecionada.getIdPessoa());
			contatoService.delete(tbPessoaSelecionada.getIdPessoa());
			usuarioService.delete(tbPessoaSelecionada.getIdPessoa());
			pessoaService.delete(tbPessoaSelecionada);
			tbPessoaSelecionada = null;
			buscarTodos();
			UtilMensagens.mensagemInformacao("Pessoa excluída com sucesso!");
		} catch (Exception e) {
			UtilErros.getMensagemErro(e);
			UtilMensagens.mensagemErro("Error ao deleta!");
		}
		PrimeFaces.current().ajax().update(Arrays.asList("form:msgs", "form:pessoa-table"));
	}

	public void atualizar() {
		PrimeFaces.current().ajax().update(Arrays.asList("form:pessoa-table"));
	}

	public List<TbTurma> listTurma() {
		return turmaService.listarTodos();
	}

	public List<TbEstado> listEstado() {
		return estadoService.lista();
	}

	public String gerarMatricula() {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		char[] carct = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder senha = new StringBuilder();
		senha.append(sdf.format(data));
		for (int x = 0; x < 5; x++) {
			int j = (int) (Math.random() * carct.length);
			senha.append(carct[j]);
		}
		return senha.toString();
	}

	public TbTurma getTbTurma() {
		return tbTurma;
	}

	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}

	public TbCidade getTbCidade() {
		return tbCidade;
	}

	public void setTbCidade(TbCidade tbCidade) {
		this.tbCidade = tbCidade;
	}

	public TbContato getTbContato() {
		return tbContato;
	}

	public void setTbContato(TbContato tbContato) {
		this.tbContato = tbContato;
	}

	public TbPessoa getTbPessoa() {
		return tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	public List<TbPessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<TbPessoa> pessoa) {
		this.pessoa = pessoa;
	}

	public TbUsuario getTbUsuario() {
		return tbUsuario;
	}

	public void setTbUsuario(TbUsuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public ConverterTurma getConverterTurma() {
		return converterTurma;
	}

	public void setConverterTurma(ConverterTurma converterTurma) {
		this.converterTurma = converterTurma;
	}

	public ConverterEstado getConverterEstado() {
		return converterEstado;
	}

	public void setConverterEstado(ConverterEstado converterEstado) {
		this.converterEstado = converterEstado;
	}

	public TbEndereco getTbEndereco() {
		return tbEndereco;
	}

	public void setTbEndereco(TbEndereco tbEndereco) {
		this.tbEndereco = tbEndereco;
	}

	public TbEstado getTbEstado() {
		return tbEstado;
	}

	public void setTbEstado(TbEstado tbEstado) {
		this.tbEstado = tbEstado;
	}

	public List<TbCidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<TbCidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public ConverterCidade getConverterCidade() {
		return converterCidade;
	}

	public void setConverterCidade(ConverterCidade converterCidade) {
		this.converterCidade = converterCidade;
	}

	public String getPesquisar() {
		return pesquisar;
	}

	public void setPesquisar(String pesquisar) {
		this.pesquisar = pesquisar;
	}

}

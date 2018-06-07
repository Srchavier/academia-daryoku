package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

import br.com.academiaDaryoku.converter.ConvertCalendar;
import br.com.academiaDaryoku.converter.ConverterCidade;
import br.com.academiaDaryoku.converter.ConverterEstado;
import br.com.academiaDaryoku.converter.ConverterTurma;
import br.com.academiaDaryoku.filters.SessionContext;
import br.com.academiaDaryoku.model.SexoEnum;
import br.com.academiaDaryoku.model.TbCidade;
import br.com.academiaDaryoku.model.TbContato;
import br.com.academiaDaryoku.model.TbEndereco;
import br.com.academiaDaryoku.model.TbEstado;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.service.CidadeService;
import br.com.academiaDaryoku.service.ContatoService;
import br.com.academiaDaryoku.service.EnderencoService;
import br.com.academiaDaryoku.service.EstadoService;
import br.com.academiaDaryoku.service.LoginService;
import br.com.academiaDaryoku.service.PessoaService;
import br.com.academiaDaryoku.service.TurmaService;
import br.com.academiaDaryoku.ultils.Sha256;
import br.com.academiaDaryoku.ultils.UploadArquivo;
import br.com.academiaDaryoku.ultils.UtilErros;
import br.com.academiaDaryoku.ultils.UtilMensagens;

@Named(value = "perfilControle")
@ViewScoped
public class PerfilControle implements Serializable {
	private static final long serialVersionUID = 1L;

	private TbEndereco tbEndereco;

	private TbCidade tbCidade;

	private TbEstado tbEstado;

	private TbTurma tbTurma;

	private TbPessoa tbPessoa;

	private TbContato tbContato;

	private TbUsuario tbUsuario;

	private List<TbPessoa> pessoa;

	private List<TbCidade> listaCidades;

	@Inject
	private UploadArquivo arquivo;

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

	@Inject
	private ConvertCalendar convertCaledar;

	@PostConstruct
	public void inicializar() {

		newModel();

	}

	private void newModel() {
		TbUsuario tbUsuarioSessao = (TbUsuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		tbUsuario = usuarioService.porId(tbUsuarioSessao.getIdUsuario());
		tbPessoa = pessoaService.findOne(tbUsuario.getTbPessoa());
		tbEndereco = enderencoService.porIdPessoa(tbPessoa.getIdPessoa());
		tbContato = contatoService.porIdPessoa(tbPessoa.getIdPessoa());
		tbUsuario.setMatSenha("");
		tbCidade = tbEndereco.getTbCidade();
		tbEstado = tbEndereco.getTbCidade().getTbEstado();
		listaCidades = (cidadeService.findPorIdEstado(tbEndereco.getTbCidade().getTbEstado().getIdEstado()));
	}

	public boolean alterarDadosPerfil() {
		if (this.tbUsuario.getMatSenha().length() >= 16 || this.tbUsuario.getMatSenha().length() <= 4) {
			UtilMensagens.mensagemInformacao("senha deve ser maior 5 e menor 16!");
			PrimeFaces.current().ajax().addCallbackParam("validacaoMat", false);
			PrimeFaces.current().ajax().update("form");
			return false;
		}
		try {

			TbPessoa pessoaSalvar = pessoaService.alterar(this.tbPessoa);

			tbContato.setTbPessoa(pessoaSalvar);
			contatoService.alterar(tbContato);

			tbEndereco.setTbCidade(tbCidade);
			tbEndereco.setTbPessoa(pessoaSalvar);
			enderencoService.alterar(tbEndereco);

			tbUsuario.setMatSenha(Sha256.shaSet(tbUsuario.getMatSenha()));
			tbUsuario.setTbPessoa(pessoaSalvar);
			usuarioService.alterar(tbUsuario);
			tbUsuario.setMatSenha("");
			this.tbContato = contatoService.porIdPessoa(tbPessoa.getIdPessoa());
			UtilMensagens.mensagemInformacao("Edição feita com sucesso!");
			PrimeFaces.current().ajax().addCallbackParam("validacaoMat", true);
			PrimeFaces.current().ajax().update("form:msgs");
			PrimeFaces.current().ajax().update("form");
			return true;

		} catch (Exception e) {
			UtilMensagens.mensagemErro("Erro ao alterar!");
			PrimeFaces.current().ajax().update("form");
			return false;
		}
	}

	public synchronized void uploadAction(FileUploadEvent event) {
		try {
			this.arquivo.fileUpload(event, ".jpg", "/image/", tbUsuario.getMatLogin());
			tbPessoa.setFoto(this.arquivo.getNome());
			pessoaService.alterar(tbPessoa);
			this.arquivo.gravar();
			UtilMensagens.mensagemInformacao("Foto alterada!");
			PrimeFaces.current().ajax().update(Arrays.asList("form:fotoUpload:msgFotoDialog, form:fotoUpload"));
			PrimeFaces.current().ajax().update("form");

		} catch (Exception e) {
			UtilMensagens.mensagemInformacao("Erro alterar foto!");
			PrimeFaces.current().ajax().update(Arrays.asList("form:fotoUpload:msgFotoDialog, form:fotoUpload, form"));
		}
	}

	public String carregarImagem() {
		if (tbPessoa.getFoto() != null) {
			return "//image/" + tbPessoa.getFoto();
		} else if (tbPessoa.getFlSexo().equals(SexoEnum.M)) {
			return "//resources/imagens/masculino.png";
		} else if (tbPessoa.getFlSexo().equals(SexoEnum.F)) {
			return "//resources/imagens/feminino.png";
		}
		UtilMensagens.mensagemInformacao("Erro ao carregar foto!");
		PrimeFaces.current().ajax().update(Arrays.asList("form:fotoUpload:msgFotoDialog, form:fotoUpload, form"));
		return null;
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

	public List<TbTurma> listTurma() {
		return turmaService.listarTodos();
	}

	public List<TbEstado> listEstado() {
		return estadoService.lista();

	}

	public TbEndereco getTbEndereco() {
		return tbEndereco;
	}

	public void setTbEndereco(TbEndereco tbEndereco) {
		this.tbEndereco = tbEndereco;
	}

	public TbCidade getTbCidade() {
		return tbCidade;
	}

	public void setTbCidade(TbCidade tbCidade) {
		this.tbCidade = tbCidade;
	}

	public TbEstado getTbEstado() {
		return tbEstado;
	}

	public void setTbEstado(TbEstado tbEstado) {
		this.tbEstado = tbEstado;
	}

	public TbPessoa getTbPessoa() {
		return tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	public TbContato getTbContato() {
		return tbContato;
	}

	public void setTbContato(TbContato tbContato) {
		this.tbContato = tbContato;
	}

	public TbUsuario getTbUsuario() {
		return tbUsuario;
	}

	public void setTbUsuario(TbUsuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public List<TbPessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<TbPessoa> pessoa) {
		this.pessoa = pessoa;
	}

	public List<TbCidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<TbCidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public ContatoService getContatoService() {
		return contatoService;
	}

	public void setContatoService(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	public EnderencoService getEnderencoService() {
		return enderencoService;
	}

	public void setEnderencoService(EnderencoService enderencoService) {
		this.enderencoService = enderencoService;
	}

	public LoginService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(LoginService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public TurmaService getTurmaService() {
		return turmaService;
	}

	public void setTurmaService(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	public CidadeService getCidadeService() {
		return cidadeService;
	}

	public void setCidadeService(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	public EstadoService getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(EstadoService estadoService) {
		this.estadoService = estadoService;
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

	public ConverterCidade getConverterCidade() {
		return converterCidade;
	}

	public void setConverterCidade(ConverterCidade converterCidade) {
		this.converterCidade = converterCidade;
	}

	public TbTurma getTbTurma() {
		return tbTurma;
	}

	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}

	public ConvertCalendar getConvertCaledar() {
		return convertCaledar;
	}

	public void setConvertCaledar(ConvertCalendar convertCaledar) {
		this.convertCaledar = convertCaledar;
	}

}

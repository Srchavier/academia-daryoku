package br.com.academiaDaryoku.ultils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;

public class UploadArquivo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String caminho;
	private byte[] arquivo;
	private String nome;

	public UploadArquivo() {
	}

	public String getNome() {
		return nome;
	}

	public String getRealPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		@SuppressWarnings("unused")
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		FacesContext aFacesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

		return context.getRealPath("/");
	}

	public void fileUpload(FileUploadEvent event, String type, String diretorio, String nome) {
		try {
			this.nome = nome + type;
			this.caminho = getRealPath() + diretorio + getNome();
			this.arquivo = event.getFile().getContents();

			File file = new File(getRealPath() + diretorio);
			File fileExiste = new File(caminho);

			if (fileExiste.exists()) {
				fileExiste.delete();
			}

			file.mkdirs();

		} catch (Exception ex) {
			System.out.println("Erro no upload do arquivo" + ex);
		}
	}

	public void gravar() {

		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.caminho);
			fos.write(this.arquivo);
			fos.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}

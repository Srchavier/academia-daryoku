package br.com.academiaDaryoku.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.academiaDaryoku.service.PresencaService;

@Named("presencaControle")
@ViewScoped
public class PresencaControle  implements Serializable{

	private static final long serialVersionUID = -336018689139447199L;

	
	@Inject
	PresencaService presencaService;
	
	@PostConstruct
	public void init() {
	}

}

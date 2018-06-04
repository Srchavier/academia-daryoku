package br.com.academiaDaryoku.respository;

import java.io.Serializable;
import java.util.List;

import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.model.TbAula_;
import br.com.academiaDaryoku.respository.filter.FilterAll;

public class AulaRepository extends RepositoryImpl<TbAula>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4816344070973313536L;
	
	
	public List<TbAula> buscaPorNomeData(FilterAll filter) {
		return buscaCriteriaData(filter, TbAula_.dtAula);

	}

}

package br.com.academiaDaryoku.respository;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import br.com.academiaDaryoku.respository.filter.FilterAll;

public interface Repository<T> {

	public T salva(T entity);

	public T alterar(T entity);

	public void remover(T entity);

	public T porId(int id);

	public List<T> todos();

	public long count();

	public List<T> find(String jpql, Object... params);

	public List<T> buscaCriteria(FilterAll filter, SingularAttribute<T, String> nmPessoa);
	
	public List<T> buscaCriteriaData(FilterAll filter, SingularAttribute<T, Date> nmPessoa);

}
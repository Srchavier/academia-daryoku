package br.com.academiaDaryoku.respository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import br.com.academiaDaryoku.respository.filter.FilterAll;

public class RepositoryImpl<T> implements Repository<T> {

	@Inject
	protected EntityManager manager;
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public RepositoryImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T salva(T entity) {
		entity = this.manager.merge(entity);
		return entity;
	}

	@Override
	public T alterar(T entity) {
		return this.manager.merge(entity);
	}

	@Override
	public void remover(T entity) {
		manager.remove(manager.merge(entity));
	}

	@Override
	public List<T> todos() {
		TypedQuery<T> query = manager.createQuery("from " + clazz.getName(), clazz);
		List<T> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public T porId(int id) {
		return manager.find(clazz, id);
	}

	@Override
	public long count() {
		Query query = manager.createQuery("select count(c) from " + clazz.getSimpleName() + " c");
		long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public List<T> find(String jpql, Object... params) {
		TypedQuery<T> query = manager.createQuery(jpql, this.clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		List<T> entities = query.getResultList();
		return entities;
	}
	
	@Override
	public T findOne(String jpql, Object... params) {
		TypedQuery<T> query = manager.createQuery(jpql, this.clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		T entities = query.getSingleResult();
		return entities;
	}

	@Override
	public List<T> buscaCriteria(FilterAll filter, SingularAttribute<T, String> nmPessoa) {

		try {

			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(clazz);
			Root<T> root = criteria.from(clazz);

			Predicate[] predicates = criarRestricoes(filter, builder, root, nmPessoa);
			criteria.where(predicates);

			TypedQuery<T> query = manager.createQuery(criteria);

			List<T> entities = query.getResultList();
			return entities;
		} catch (NoResultException e) {
			return null;
		}

	}

	private Predicate[] criarRestricoes(FilterAll filter, CriteriaBuilder builder, Root<T> root,
			SingularAttribute<T, String> nmPessoa) {

		List<Predicate> predicates = new ArrayList<>();

		if (!org.apache.commons.lang3.StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(nmPessoa)), "%" + filter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<T> buscaCriteriaData(FilterAll filter, SingularAttribute<T, Date> data) {
		try {

			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(clazz);
			Root<T> root = criteria.from(clazz);

			Predicate[] predicates = criarRestricoesData(filter, builder, root, data);
			criteria.where(predicates);

			TypedQuery<T> query = manager.createQuery(criteria);

			List<T> entities = query.getResultList();
			return entities;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	private Predicate[] criarRestricoesData(FilterAll filter, CriteriaBuilder builder, Root<T> root,
			SingularAttribute<T, Date> data) {

		List<Predicate> predicates = new ArrayList<>();

		if (filter.getDate() != null) {
			predicates.add(builder.equal(root.get(data), "%" + filter.getDate() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

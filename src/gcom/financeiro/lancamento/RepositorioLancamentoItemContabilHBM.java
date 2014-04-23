package gcom.financeiro.lancamento;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 
 * Title: GCOM
 * 
 * Description: Repositório de Item de Lançamento Contábil
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 23 de Janeiro de 2006
 * @version 1.0
 */
public class RepositorioLancamentoItemContabilHBM implements IRepositorioLancamentoItemContabil{

	//cria uma variável da interface do repositório de lançamento item contábil
	private static IRepositorioLancamentoItemContabil instancia;

	// construtor da classe
	private RepositorioLancamentoItemContabilHBM() {
	}

	// retorna uma instância do repositório
	public static IRepositorioLancamentoItemContabil getInstancia() {
		// se não existe ainda a instância
		if (instancia == null) {
			// cria a instância do repositório
			instancia = new RepositorioLancamentoItemContabilHBM();
		}
		// retorna a instância do repositório
		return instancia;
	}

	/**
	 * Pesquisa uma coleção de lançamento de item contábil
	 * 	 
	 * @return Coleção de Lançamentos de Item Contábil 
	 * @exception ErroRepositorioException  Erro no hibernate
	 */
	public Collection<LancamentoItemContabil> pesquisarLancamentoItemContabil() throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select lancamentoItemContabil "
					+ "from LancamentoItemContabil lancamentoItemContabil ";

			// pesquisa a coleção de acordo com o parâmetro informado
			retorno = session.createQuery(consulta).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção pesquisada
		return retorno;
	}
}

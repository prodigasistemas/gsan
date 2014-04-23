package gcom.cadastro.empresa;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Sávio Luiz
 */
public class RepositorioEmpresaHBM implements IRepositorioEmpresa {

	private static IRepositorioEmpresa instancia;

	/**
	 * Construtor da classe RepositorioFaturamentoHBM
	 */
	private RepositorioEmpresaHBM() {
	}

	/**
	 * Retorna o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static IRepositorioEmpresa getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioEmpresaHBM();
		}
		return instancia;
	}

	/**
	 * Pesquisa as empresas que serão processadas no emitir contas
	 * 
	 * @author Sávio Luiz
	 * @date 09/01/2007
	 * 
	 */
	public Collection pesquisarIdsEmpresa()
	throws ErroRepositorioException{

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select emp.id from Empresa emp order by emp.id";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

}

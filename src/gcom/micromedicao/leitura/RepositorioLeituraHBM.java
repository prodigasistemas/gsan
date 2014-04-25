package gcom.micromedicao.leitura;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * O repositório faz a comunicação com a base de dados através do hibernate. O
 * cliente usa o repositório como fonte de dados.
 * 
 * @author Sávio Luiz
 * @created 25 de Agosto de 2005
 */

public class RepositorioLeituraHBM implements IRepositorioLeitura {
	private static IRepositorioLeitura instancia;

	/**
	 * Construtor da classe RepositorioEnderecoHBM
	 */
	private RepositorioLeituraHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioLeitura getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioLeituraHBM();
		}

		return instancia;
	}

	public Integer verificarExistenciaLeituraAnormalidade(Integer idLeituraAnormalidade) throws ErroRepositorioException{
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select la.id " 
					+ "from LeituraAnormalidade la "
					+ "where la.id = :idLeituraAnormalidade";

			retorno = (Integer)session.createQuery(consulta).setInteger("idLeituraAnormalidade",
					idLeituraAnormalidade).setMaxResults(1).uniqueResult();

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

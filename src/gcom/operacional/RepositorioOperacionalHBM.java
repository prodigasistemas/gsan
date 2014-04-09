package gcom.operacional;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Repositorio para modulo operacional
 * 
 * @author Rafael Pinto
 * @since 15/10/2008
 */
public class RepositorioOperacionalHBM implements IRepositorioOperacional {

	private static IRepositorioOperacional instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioOperacionalHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioOperacional getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioOperacionalHBM();
		}

		return instancia;
	}

	/**
	 * [UC0596] - Inserir Qualidade de agua
	 * 
	 * Pesquisa as fonte de captacao apatir da tabela de SetorFonteCaptacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 * 
	 * @param Collection colecaoSetorComercial
	 * @throws ErroRepositorioException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		String consulta;
		Collection<FonteCaptacao> retornoConsulta = null;
		try {
			consulta = "SELECT distinct fonte " 
				+ "from SetorFonteCaptacao setorFonte "
				+ "inner join setorFonte.comp_id.fonteCaptacao fonte "
				+ "left join setorFonte.comp_id.setorComercial setor "
				+ "where setor.id in (:colecaoSetor)";
				

			retornoConsulta = (Collection<FonteCaptacao>) 
				session.createQuery(consulta).
				setParameterList("colecaoSetor", colecaoSetorComercial).list();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

}

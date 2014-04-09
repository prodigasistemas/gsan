package gcom.atendimentopublico.registroatendimento;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioRegistroAtendimentoPostgresHBM extends
		RepositorioRegistroAtendimentoHBM {

	/**
	 * Construtor da classe RepositorioAcessoHBM
	 */

	protected RepositorioRegistroAtendimentoPostgresHBM() {
	}

	  /**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
    @Override
    public Integer pesquisarSequencialProtocoloAtendimento() throws ErroRepositorioException {

        Session session = HibernateUtil.getSession();

        Integer retorno = null;
        String consulta = null;

        try {
        	consulta = "SELECT nextval('atendimentopublico.seq_ra_protocolo') as sequencial";
           
            retorno = (Integer) session.createSQLQuery(consulta).addScalar("sequencial", Hibernate.INTEGER)
            		.uniqueResult();

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

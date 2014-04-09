package gcom.seguranca.acesso;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 * Descrição da classe 
 *
 * @author Administrador
 * @date 13/11/2006
 */
public class RepositorioAcessoPostgresHBM extends RepositorioAcessoHBM {

	public void atualizarRegistrarAcessoUsuario(Usuario usuario) 
		throws ErroRepositorioException {
		
	
		Session session = HibernateUtil.getSession();
	
		String consulta = "update Usuario usu "
				+ "set usu.numeroAcessos =:acesso, usu.ultimoAcesso = :ultimo "
				+ "where usu.id = :idUsuario" ;

		try {
	
			session.createQuery(consulta).
				setInteger("acesso",usuario.getNumeroAcessos()).
				setTimestamp("ultimo",usuario.getUltimoAcesso()).
				setInteger("idUsuario",usuario.getId()).
				executeUpdate();
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
	
		}
	}
}

package gcom.portal;

import gcom.cadastro.localidade.Localidade;
import gcom.gui.portal.LojaAtendimentoHelper;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioLojaVirtualHBM implements IRepositorioLojaVirtualHBM {

	private static RepositorioLojaVirtualHBM instancia;

	protected RepositorioLojaVirtualHBM() {
	}

	public static RepositorioLojaVirtualHBM getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioLojaVirtualHBM();
		}
		return instancia;
	}

	@SuppressWarnings("unchecked")
	public List<Localidade> pesquisarLocalidades() throws ErroRepositorioException {
		Session sessao = HibernateUtil.getSession();
		List<Localidade> retorno = null;

		String sql = "SELECT new Localidade(loja.localidade) FROM LojaAtendimento loja GROUP BY loja.localidade ORDER BY loja.localidade";

		try {
			retorno = sessao.createQuery(sql).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}

		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<LojaAtendimentoHelper> pesquisarLojasAtendimento(String localidade) throws ErroRepositorioException {
		Session sessao = HibernateUtil.getSession();
		List<LojaAtendimentoHelper> retorno = null;
		
		String sql = "SELECT new gcom.gui.portal.LojaAtendimentoHelper("
				+ " nome, " 
				+ " localidade, "
				+ " endereco, "
				+ " ddd, "
				+ " telefone, "
				+ " horario, "
				+ " coordenadas "
				+ " ) "
				+ " FROM LojaAtendimento "
				+ " WHERE localidade = :localidade "
				+ " ORDER BY localidade";
		
		try{
			retorno = sessao.createQuery(sql).setString("localidade", localidade).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}
		
		return retorno;
	}
	
	public boolean isCpfCnpjCadastrado(String matricula, String cpfCnpj) throws ErroRepositorioException {
		Session sessao = HibernateUtil.getSession();
		boolean cpfCnpjCadastrado = false;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT imov_id AS idImovel ")
			   .append("FROM cadastro.cliente_imovel ci ")
			   .append("INNER JOIN cadastro.cliente c ON c.clie_id = ci.clie_id ")
			   .append("WHERE imov_id = :matricula ")
			   .append("AND clim_dtrelacaofim IS NULL ")
			   .append("AND c.clie_nncpf = :cpfCnpj OR clie_nncnpj = :cpfCnpj ");
			
			Integer idImovel = (Integer) sessao.createSQLQuery(sql.toString())
					.addScalar("idImovel", Hibernate.INTEGER)
					.setString("matricula", matricula)
					.setString("cpfCnpj", cpfCnpj)
					.uniqueResult();
			
			if (idImovel != null)
				cpfCnpjCadastrado = true;
			
		} catch (Exception e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}
		
		return cpfCnpjCadastrado;
	}
}

package gcom.cadastro.cliente;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * O repositório faz a comunicação com a base de dados através do hibernate. O
 * cliente usa o repositório como fonte de dados.
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */

public class RepositorioClienteTipoHBM implements IRepositorioClienteTipo {

    private static IRepositorioClienteTipo instancia;

    /**
     * Constructor for the RepositorioClienteTipoHBM object
     */
    public RepositorioClienteTipoHBM() {
    }

    /**
     * Retorna o valor de instancia
     * 
     * @return O valor de instancia
     */
    public static IRepositorioClienteTipo getInstancia() {

        if (instancia == null) {
            instancia = new RepositorioClienteTipoHBM();
        }

        return instancia;
    }

    /**
     * pesquisa uma coleção de cliente(s) de acordo com critérios existentes no
     * filtro
     * 
     * @param filtroClienteTipo
     *            Description of the Parameter
     * @return Description of the Return Value
     * @exception ErroRepositorioException
     *                Description of the Exception
     */
    public Collection pesquisarClienteTipo(FiltroClienteTipo filtroClienteTipo)
            throws ErroRepositorioException {

        Collection retorno = null;
        Session session = HibernateUtil.getSession();

        try {

            retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(
                    filtroClienteTipo, "clienteTipo",
                    "from gcom.cadastro.cliente.ClienteTipo as clienteTipo",
                    session).list()));

        } catch (HibernateException e) {

            throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }

}

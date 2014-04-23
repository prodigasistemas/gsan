package gcom.util.filtro;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Serve como retorno para o GeradorHQLCondicional
 * 
 * @author rodrigo
 */
public class CondicionalQuery {

    private StringBuffer query = new StringBuffer();

    private Collection parametrosValores = new ArrayList();

    private boolean consultaSemLimites;

    /**
     * Retorna o valor de parametrosValores
     * 
     * @return O valor de parametrosValores
     */
    public Collection getParametrosValores() {
        return parametrosValores;
    }

    /**
     * Seta o valor de parametrosValores
     * 
     * @param parametrosValores
     *            O novo valor de parametrosValores
     */
    public void setParametrosValores(Collection parametrosValores) {
        this.parametrosValores = parametrosValores;
    }

    /**
     * Retorna o valor de query
     * 
     * @return O valor de query
     */
    public StringBuffer getQuery() {
        return query;
    }

    /**
     * Seta o valor de query
     * 
     * @param query
     *            O novo valor de query
     */
    public void setQuery(StringBuffer query) {
        this.query = query;
    }

    /**
     * Retorna o valor de consultaSemLimites
     * 
     * @return O valor de consultaSemLimites
     */
    public boolean isConsultaSemLimites() {
        return consultaSemLimites;
    }

    /**
     * Seta o valor de consultaSemLimites
     * 
     * @param consultaSemLimites
     *            O novo valor de consultaSemLimites
     */
    public void setConsultaSemLimites(boolean consultaSemLimites) {
        this.consultaSemLimites = consultaSemLimites;
    }
}

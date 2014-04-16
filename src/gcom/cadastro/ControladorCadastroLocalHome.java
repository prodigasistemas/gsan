package gcom.cadastro;

import javax.ejb.CreateException;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public interface ControladorCadastroLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorCadastroLocal create() throws CreateException;
}

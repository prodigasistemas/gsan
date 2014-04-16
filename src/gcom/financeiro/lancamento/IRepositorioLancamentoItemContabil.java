package gcom.financeiro.lancamento;

import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * 
 * Title: GCOM
 * 
 * Description: Interface do Repositório de Item Lançamento Contábil
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 23 de Janeiro de 2006
 * @version 1.0
 */
public interface IRepositorioLancamentoItemContabil {

	/**
     * Pesquisa uma coleção de itens de lançamento contábil
     * @return Coleção de Item de Lançamento Contábil
     * @exception ErroRepositorioException Erro no hibernate
     */
    public Collection<LancamentoItemContabil> pesquisarLancamentoItemContabil() throws ErroRepositorioException;

}

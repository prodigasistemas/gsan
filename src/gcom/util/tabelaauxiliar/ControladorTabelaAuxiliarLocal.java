package gcom.util.tabelaauxiliar;

import gcom.util.ControladorException;
import gcom.util.filtro.Filtro;

import java.util.Collection;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorTabelaAuxiliarLocal extends
		javax.ejb.EJBLocalObject {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param tabelaAuxiliar
	 *            Descrição do parâmetro
	 */
	public void atualizarTabelaAuxiliar(TabelaAuxiliarAbstrata tabelaAuxiliar)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param nomePacoteObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection pesquisarTeste(Filtro filtro, String nomePacoteObjeto)
			throws ControladorException;
}

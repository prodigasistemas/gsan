package gcom.relatorio.faturamento;

import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoEconomiaHelper;
import gcom.faturamento.bean.EmitirHistogramaEsgotoHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoHelper;
import gcom.faturamento.conta.Fatura;
import gcom.util.ControladorException;

import java.util.Collection;


/**
 * Foi criador esse controlador para relatorio especificos para faturamento
 * 
 * @author Rafael Pinto
 * @created 16/06/2007
 */
public interface ControladorRelatorioFaturamentoLocal extends javax.ejb.EJBLocalObject {
	
	
	/**
	 * [UC0604] Emitir Histograma de Água por Economia
	 * 
	 * @author Rafael Pinto
	 * @date 04/06/2007
	 * 
	 * @param FiltrarEmitirHistogramaAguaEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaAguaEconomiaHelper>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaAguaEconomiaHelper> pesquisarEmitirHistogramaAguaEconomia(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro) throws ControladorException ;
	
	
	/**
	 * [UC0600] Emitir Histograma de Esgoto
	 * 
	 * @author Rafael Pinto
	 * @date 05/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoHelper>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaEsgotoHelper> pesquisarEmitirHistogramaEsgoto(
			FiltrarEmitirHistogramaEsgotoHelper filtro)
			throws ControladorException ;	
	
	/**
	 * [UC0606] Emitir Histograma de Água por Economia
	 * 
	 * @author Rafael Pinto
	 * @date 07/11/2007
	 * 
	 * @param FiltrarEmitirHistogramaEsgotoEconomiaHelper
	 * 
	 * @return Collection<EmitirHistogramaEsgotoEconomiaHelper>
	 * @throws ControladorException
	 */
	public Collection<EmitirHistogramaEsgotoEconomiaHelper> pesquisarEmitirHistogramaEsgotoEconomia(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro) throws ControladorException ;
	
	
	/**
	 * [UC0099] Emitir Relação Sintética de Faturas
	 * 
	 * @author Rafael Pinto
	 * @date 19/11/2007
	 * 
	 * @param colecaoFatura
	 * @throws ControladorException
	 */
	public void emitirRelacaoSinteticaFaturas(Collection<Fatura> colecaoFatura,Integer anoMesFaturamento)
			throws ControladorException ;	

}

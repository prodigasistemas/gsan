package gcom.atendimentopublico.ligacaoesgoto;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Interface que disponibiliza os serviços do Repositório de Ligação de Esgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public interface IRepositorioLigacaoEsgoto {

	/**
	 * [UC0464] Atualizar Volume Mínimo da Ligação de Esgoto
	 * 
	 * [SB0001] Atualizar Ligação de Esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public void atualizarVolumeMinimoLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto)
			throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal recuperarPercentualEsgoto(Integer idLigacaoEsgoto)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança - Ordem de Fiscalização
	 * 
	 * @author Sávio Luiz
	 * @date 21/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer recuperarConsumoMinimoEsgoto(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Imóvel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoEsgotoSituacao
	 * @param idConsumoTipo
	 * @return LigacaoEsgotoSituacaoConsumoTipo
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacaoConsumoTipo pesquisarLigacaoEsgotoSituacaoConsumoTipo(Integer idLigacaoEsgotoSituacao,
			Integer idConsumoTipo) throws ErroRepositorioException;
	
	/**
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todas as situações de ligações de esgoto ativas
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarLigacaoEsgotoSituacao() throws ErroRepositorioException;
}

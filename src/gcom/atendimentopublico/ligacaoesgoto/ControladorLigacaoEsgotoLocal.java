package gcom.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorLigacaoEsgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public interface ControladorLigacaoEsgotoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0367]Atualizar Liga��o de Esgoto no sistema.
	 * 
	 * [SB002] Atualiza liga��o de esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 17/07/2006
	 * 
	 * @param ligacaoEsgoto
	 *            a ser atualizado
	 * 
	 * @throws ControladorException
	 */
	public void atualizarLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto, Usuario usuario)
			throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * liga��o de esgoto do im�vel no momento da exibi��o.
	 * 
	 * [FS0001] Verificar exist�ncia da matr�cula do im�vel. [FS0002] Verificar
	 * Situa��o do Imovel. [FS0003] Validar Situa��o de Esgoto do im�vel.
	 * [FS0006] Validar Percentual de Coleta. [FS0007] Validar Volume M�nimo
	 * Fixado.
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoImovelExibir(OrdemServico ordem,boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * liga��o de esgoto do im�vel no momento da atualiza��o.
	 * 
	 * [FS0001] Verificar exist�ncia da matr�cula do im�vel. [FS0002] Verificar
	 * Situa��o do Imovel. [FS0003] Validar Situa��o de Esgoto do im�vel.
	 * [FS0006] Validar Percentual de Coleta. [FS0007] Validar Volume M�nimo
	 * Fixado.
	 * 
	 * @author Leonardo Regis
	 * @date 20/07/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoImovelAtualizar(Imovel imovel)
			throws ControladorException;
	
	/**
	 * [UC0464] Atualizar Volume M�nimo de Liga��o de Esgoto
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * atualiza��o do volume m�nimo da liga��o de esgoto
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param OrdemServico
	 * @param veioEncerrarOS
	 * @throws ControladorException
	 */
	public void validarExibirAtualizarVolumeMinimoLigacaoEsgoto(OrdemServico ordemServico, boolean veioEncerrarOS) throws ControladorException;
	
	/**
	 * [UC0464] Atualizar Volume M�nimo de Liga��o de Esgoto
	 * 
	 * [FS004] Validar Volume M�nimo
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarVolumeMinimoLigacaoEsgoto(Imovel imovel) throws ControladorException;
	
	/**
	 * [UC0464] Atualizar Volume M�nimo da Liga��o de Esgoto
	 * 
	 * [SB0001] Atualizar Liga��o de Esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ControladorException
	 */
	public void atualizarVolumeMinimoLigacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [SB0004] - Calcular Valor de �gua e/ou Esgoto
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal recuperarPercentualEsgoto(Integer idLigacaoEsgoto) throws ControladorException;
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a - Ordem de Fiscaliza��o
	 * 
	 * @author S�vio Luiz
	 * @date 21/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer recuperarConsumoMinimoEsgoto(Integer idImovel)
	throws ControladorException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoEsgotoSituacao
	 * @param idConsumoTipo
	 * @return LigacaoEsgotoSituacaoConsumoTipo
	 * @throws ControladorException
	 */
	public LigacaoEsgotoSituacaoConsumoTipo pesquisarLigacaoEsgotoSituacaoConsumoTipo(Integer idLigacaoEsgotoSituacao,
			Integer idConsumoTipo) throws ControladorException;
	
	/**
	 *
	 * 
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 * 
	 *        Pesquisar Situa��es de Liga��o de Esgoto ativas.
	 * 
	 *        Gera��o de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<LigacaoEsgotoSituacao> pesquisarLigacaoEsgotoSituacao()
			throws ControladorException;
}

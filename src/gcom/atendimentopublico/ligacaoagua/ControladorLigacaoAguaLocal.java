package gcom.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorLigacaoAgua
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public interface ControladorLigacaoAguaLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0463] Atualizar Consumo M�nimo de Liga��o �gua
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades
	 * da atualiza��o do consumo m�nimo da liga��o de agua
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param OrdemServico
	 * @param veioEncerrarOS 
	 */	
	public void validarExibirAtualizarConsumoMinimoLigacaoAgua(OrdemServico ordemServico, boolean veioEncerrarOS) 
		throws ControladorException;
	
	/**
	 * [UC0463] Atualizar Consumo M�nimo de Liga��o de �gua
	 * 
	 * [FS004] Validar Consumo M�nimo
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param imovel
	 */
	public void validarConsumoMinimoLigacaoAgua(Imovel imovel) throws ControladorException;
	
	/**
	 * [UC0463] Atualizar Consumo M�nimo da Liga��o de �gua
	 * 
	 * [SB0001] Atualizar Liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @throws ControladorException
	 */
	public void atualizarConsumoMinimoLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)	throws ControladorException;
	
	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades
	 * do corte liga��o de agua
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirCorteLigacaoAgua(OrdemServico ordemServico,boolean veioEncerrarOS) 
		throws ControladorException;
	
	/**
	 * [UC0355] Efetuar Corte de Liga��o de �gua.
	 * 
	 * @author Leonardo Regis.
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void efetuarCorteLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;
	

	/**
	 * [UC0365] Efetuar Corte Administrativo da Liga��o Agua
	 * 
	 * @author Thiago Ten�rio
	 * @date 30/06/2006
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void efetuarCorteAdministrativoLigacaoAgua(DadosEfetuacaoCorteLigacaoAguaHelper helper, Usuario usuario) throws ControladorException;
	
	
	/**
	 * [UC0463] Efetuar Restabelecimento da Liga��o de �gua
	 *
	 * [SB0001] Atualizar Im�vel/Liga��o �gua
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaRestabelecimento(LigacaoAgua ligacaoAgua)throws ControladorException;
	
	/**
	 * [UC0357] Efetuar Religa��o de �gua
	 *
	 * [SB0001] Atualizar Im�vel/Liga��o �gua
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaReligacao(LigacaoAgua ligacaoAgua)throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da Ligacao de �gua
	 * (id,dataCorte,dataSupressao)
	 * 
	 * @author S�vio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public LigacaoAgua recuperaParametrosLigacaoAgua(Integer idImovel)
			throws ControladorException;
	
	/**
	 * 
	 * Pesquisa o id do hidrometro
	 * 
	 * @author S�vio Luiz
	 * @date 19/02/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdHidrometroInstalacaoHistorico(Integer idImovel)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados da Tarifa Social 
	 * 
	 * Recupera o consumo m�nimo fixado do Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 04/0/2006
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoMinimoFixado(Integer idImovel) throws ControladorException;
	
	public Collection verificaExistenciaLigacaoAgua(Integer idImovel);
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel]
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoAguaSituacao
	 * @param idConsumoTipo
	 * @return LigacaoAguaSituacaoConsumoTipo
	 * @throws ControladorException
	 */
	public LigacaoAguaSituacaoConsumoTipo pesquisarLigacaoAguaSituacaoConsumoTipo(Integer idLigacaoAguaSituacao,
			Integer idConsumoTipo) throws ControladorException;
		
	/**
	 * 
	 * Atualiza o tipo de corte
	 * 
	 * Autor: Hugo Amorim
	 * 
	 * Data: 18/05/2009
	 */
	public void atualizarTipoCorte(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;
	
	/**
	 *
	 * 
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 * 
	 *        Pesquisar Situa��es de Liga��o de �gua ativas.
	 * 
	 *        Gera��o de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<LigacaoAguaSituacao> pesquisarLigacaoAguaSituacao()
			throws ControladorException;
}

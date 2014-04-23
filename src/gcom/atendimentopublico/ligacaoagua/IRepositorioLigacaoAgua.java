package gcom.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Interface do RepositorioLigacaoAgua 
 *
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public interface IRepositorioLigacaoAgua {
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarConsumoMinimoLigacaoAgua(LigacaoAgua ligacaoAgua) throws ErroRepositorioException;
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água/Histórico de Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @exception ErroRepositorioExceptions
	 */
	public void efetuarCorteLigacaoAgua(DadosEfetuacaoCorteLigacaoAguaHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água/Histórico de Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param helper
	 * @exception ErroRepositorioExceptions
	 */
	public void efetuarCorteAdministrativoLigacaoAgua(DadosEfetuacaoCorteLigacaoAguaHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0463] Efetuar Restabelecimento da Ligação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaRestabelecimento(LigacaoAgua ligacaoAgua)throws ErroRepositorioException;
	
	/**
	 * [UC0357] Efetuar Religação de Água
	 *
	 * [SB0001] Atualizar Imóvel/Ligação Água
	 * 
	 * @author Flávio Cordeiro
	 * @date 28/09/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarLigacaoAguaReligacao(LigacaoAgua ligacaoAgua)throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da Ligacao de água
	 * 
	 * @author Sávio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsLigacaoAgua(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados da Tarifa Social 
	 * 
	 * Recupera o consumo mínimo fixado do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 04/0/2006
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoFixado(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa o id do hidrometro
	 * 
	 * @author Sávio Luiz
	 * @date 19/02/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdHidrometroInstalacaoHistorico(Integer idImovel) throws ErroRepositorioException;
	
	public Collection verificaExistenciaLigacaoAgua(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Imóvel]
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoAguaSituacao
	 * @param idConsumoTipo
	 * @return LigacaoAguaSituacaoConsumoTipo
	 * @throws ErroRepositorioException
	 */
	public LigacaoAguaSituacaoConsumoTipo pesquisarLigacaoAguaSituacaoConsumoTipo(Integer idLigacaoAguaSituacao,
			Integer idConsumoTipo) throws ErroRepositorioException;
	/**
	 * 
	 * Atualiza o tipo de corte
	 * 
	 * Autor: Hugo Amorim
	 * 
	 * Data: 18/05/2009
	 */
	public void atualizarTipoCorte(IntegracaoComercialHelper integracaoComercialHelper) throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todas as situações de ligações de água ativas
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarLigacaoAguaSituacao() throws ErroRepositorioException;
}

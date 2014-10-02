package gcom.atendimentopublico;

import gcom.atendimentopublico.bean.DadosLigacoesBoletimCadastroHelper;
import gcom.atendimentopublico.bean.EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.bean.UnidadesFilhasHelper;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaClienteBean;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativaHelper;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServicoJuridicoBean;
import gcom.relatorio.atendimentopublico.RelatorioOSSituacaoHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorCliente
 * 
 * @author S�vio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorAtendimentoPublicoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC353]Efetuar Liga��o de Esgoto no sistema. [SB002] Atualizar dados do
	 * im�vel.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 14/06/2006
	 * 
	 * @param ligacaoEsgoto
	 *            a ser enserido
	 * @param imovel
	 *            a ser atualizado
	 * @throws ControladorException
	 */

	public void efetuarLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0367]Atualizar Liga��o de Agua no sistema.
	 * 
	 * [SB002] Atualiza liga��o de agua.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param ligacaoAgua
	 * @throws ControladorException
	 */
	public void atualizarLigacaoAgua(LigacaoAgua ligacaoAgua, Usuario usuario)
			throws ControladorException;

	/**
	 * [UC353]Efetuar Liga��o Esgoto no sistema.
	 * 
	 * [SB002] Atualizar dados doim�vel.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 20/06/2006
	 * 
	 * @param ligacaoEsgoto
	 *            a ser enserido
	 * @param imovel
	 *            a ser atualizado
	 * @throws ControladorException
	 */
	public void inserirLigacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * inser��o da especificacao situacao criterio imovel.
	 * 
	 * [FS0001] Validar especifica��o da situa��oo j� existente [FS0002] Validar
	 * exist�ncia de hidr�metro na liga��o �gua [FS0003] Validar exist�ncia de
	 * hidr�metro no po�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/08/2006
	 * 
	 * @param equipeComponentes
	 */
	public void validarExibirInsercaoEspecificacaoImovSitCriterio(
			Collection colecaoEspecificacaoImovSitCriterio,
			EspecificacaoImovSitCriterio especImovSitCriterio)
			throws ControladorException;

	/**
	 * [UC0365] Efetuar Remanejamento de Hidr�metro [SB0001] Atualizar Hit�rico
	 * de instala��o do hidr�metro
	 * 
	 * @author R�mulo Aur�lio
	 * @date 30/06/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */

	public void efetuarRemanejamentoHidrometro(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0365] Efetuar Retirada de Hidr�metro [SB0001] Atualizar Hit�rico de
	 * instala��o do hidr�metro
	 * 
	 * @author Thiago Ten�rio
	 * @date 30/06/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */
	public void efetuarRetiradaHidrometro(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0362] Efetuar Instala��o de Hidr�metro
	 * 
	 * [SB0001] Gerar Hit�rico de instala��o do hidr�metro [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua [SB0003] Atualizar situa��o de hidr�metro na
	 * tabela HIDROMETRO
	 * 
	 * @author Ana Maria
	 * @date 12/07/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @param materialImovel
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	public void efetuarInstalacaoHidrometro(IntegracaoComercialHelper integracaoComercialHelper) 
		throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * liga��o de agua do im�vel no momento da exibi��o.
	 * 
	 * [FS0001] Verificar exist�ncia da matr�cula do im�vel. [FS0002] Verificar
	 * Situa��o do Imovel. [FS0003] Validar Situa��o de Esgoto do im�vel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param Imovel
	 */
	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * liga��o de agua do im�vel no momento da exibi��o.
	 * 
	 * [FS0001] Verificar exist�ncia da matr�cula do im�vel. [FS0002] Verificar
	 * Situa��o do Imovel. [FS0003] Validar Situa��o de Esgoto do im�vel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param Imovel
	 */
	public void validarExibirLigacaoAguaImovel(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0356] Efetuar Mudan�a de Situa��o de Faturamento da Liga��o de Esgoto
	 * 
	 * Permite Efetuar Mudan�a de Situa��o de Faturamento da Liga��o de Esgoto .
	 * 
	 * [FS0001]- Validar Ordem de Servi�o [FS0002] Verificar Situa��o do Imovel
	 * [FS0002] Verificar Situa��o do Imovel [FS0003]- Validar Situa��o da
	 * Liga��o de Esgoto do im�vel * [FS0001]- Validar Ordem de Servi�o [FS0002]
	 * Verificar Situa��o do Imovel [FS0002] Verificar Situa��o do Imovel
	 * [FS0003]- Validar Situa��o da Liga��o de Esgoto do im�vel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServicoId
	 * @param imovel
	 * @param dataMudanca
	 * @param volumeMinimoFixado
	 * @param novaSituacaoEsgoto
	 * @throws ControladorException
	 */
	public void efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0356]- Efetuar mudan�a de Faturamento na Liga��o de �gua
	 * [FS0006]-Atualizar Liga��o de Esgoto
	 * 
	 * Permite atualizar a Tabele de Liga��o Esdoto . Update LIGACAO_ESGOTO
	 * LESG_NNCONSUMOMINIMOESGOTO (volume m�nimo fixado) LESG_TMULTIMAALTERADAO
	 * (data e hora correntes) Where LESG_ID=IMOV_ID da tabela IMOVEL
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * 
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public void atualizarLigacaoEsgoto(Imovel imovel, String volumeMinimoFixado)
			throws ControladorException;

	/**
	 * [UC0356]- Efetuar mudan�a de Faturamento na Liga��o de �gua
	 * 
	 * [FS0007]- Validar Situa��o da Liga��o de �gua do im�vel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public String validarSituacaoAguaImovel(Imovel imovel, Integer tipoServico)
			throws ControladorException;

	/**
	 * [UC0356] Efetuar Mudan�a de Situa��o de Faturamento da Liga��o de Esgoto
	 * 
	 * Permite Efetuar Mudan�a de Situa��o de Faturamento da Liga��o de Esgoto .
	 * 
	 * [FS0001]- Validar Ordem de Servi�o [FS0002] Verificar Situa��o do Imovel
	 * [FS0002] Verificar Situa��o do Imovel [FS0003]- Validar Situa��o da
	 * Liga��o de Esgoto do im�vel [FS0007]- Validar Situa��o da Liga��o de �gua
	 * do im�vel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServicoId
	 * @param imovel
	 * @param dataMudanca
	 * @param volumeMinimoFixado
	 * @param novaSituacaoEsgoto
	 * @throws ControladorException
	 */
	public String validarMudancaSituacaoFaturamentoLigacaoesgotoExibir(
			OrdemServico ordemServico, boolean veioEncerrarOS ) throws ControladorException;

	/**
	 * [UC0364] Efetuar Substitui��o de Hidr�metro
	 * 
	 * [SB0001] Atualiza o Hist�rico da instala��o com os dados do hidr�metro
	 * substituido [SB0002] Gerar Hit�rico de instala��o do hidr�metro [SB0003]
	 * Atualizar Im�vel/Liga��o de �gua [SB0004] Atualizar situa��o de
	 * hidr�metro na tabela HIDROMETRO [SB0005] Atualizar situa��o do hidr�metro
	 * substituido na tabela HIDROMETRO
	 * 
	 * @author Ana Maria
	 * @date 24/07/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @param materialImovel
	 * @param hidrometroSubstituicaoHistorico
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public void efetuarSubstituicaoHidrometro(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;

	/**
	 * [UC0360]- Efetuar Supress�o da Liga��o de �gua
	 * 
	 * [SB0001]- Atualizar Liga��o de �gua [SB0002]- Atualizar Im�vel [SB0004]-
	 * Atualizar Hist�tico de Instala��o de Hidr�metro
	 * 
	 * @author R�mulo Aur�lio
	 * @date 28/07/2006
	 * @param imovel
	 * 
	 * @throws ControladorException
	 */
	public void efetuarSupressaoLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0368] Atualizar Instala��o do Hidr�metro
	 * 
	 * [FS0001] - Verificar a exist�ncia da matr�cula do im�vel [FS0002] -
	 * Verificar a situa��o do im�vel [FS0003] - Validar exist�ncia do
	 * hidr�metro [FS0004] - Validar leitura instala��o hidr�metro [FS0005] -
	 * Validar leitura retirada hidr�metro [FS0006] - Validar leitura retirada
	 * corte [FS0007] - Validar Leitura Supress�o [FS0009] - Verificar sucesso
	 * da transa��o
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void atualizarInstalacaoHidrometro(Imovel imovel, Integer medicaoTipo,Usuario usuario)
			throws ControladorException;

	/**
	 * [UC0359] Efetuar Restabelecimento Liga��o de �gua
	 * 
	 * 
	 * [SB0001] Atualizar Im�vel/Liga��o de �gua/Liga��o de Esgoto
	 * 
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 12/07/2006
	 * 
	 * @param idImovel,idOrdemServico
	 * 
	 * @throws ControladorException
	 */

	public void efetuarRestabelecimentoLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
	 * 
	 * [FS0003] - Validar atendimento do motivo de encerramento.
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void validarAtendimentoMotivoEncerramento(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException;

	/**
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
	 * 
	 * [FS0002] - Solicitar o indicador de troca de servi�o, situa��o e motivo
	 * de encerramento [FS0003] - Validar atendimento do motivo de encerramento
	 * [FS0005] - Validar indicador de deferimento [FS0006] - Validar indicador
	 * de deferimento x indicador de troca de servi�o [FS0007] - Verificar
	 * sucesso da transa��o
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public Integer inserirOSReferidaRetornoTipo(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException;

	/**
	 * [UC0354] Efetuar Liga��o de �gua.
	 * 
	 * Permite validar liga��o de �gua Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a execu��o da ordem de ser�o.
	 * 
	 * [FS0008] Verificar Situa��o Rede de �gua na Quadra. [FS0007] Verificar
	 * Situa��o do Imovel. [FS0002] Validar Situa��o de �gua do Im�vel
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0353] Efetuar Liga��o de Esgoto.
	 * 
	 * Permite validar liga��o de esgoto Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a execu��o da ordem de ser�o.
	 * 
	 * [FS0008] Verificar Situa��o Rede de Esgoto na Quadra. [FS0007] Verificar
	 * Situa��o do Imovel. [FS0002] Validar Situa��o de Esgoto do Im�vel
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0354] Efetuar Liga��o de �gua.
	 * 
	 * Permite validar liga��o de �gua Efetuar ou pelo menu ou pela
	 * funcionalidade encerrar a execu��o da ordem de ser�o.
	 * 
	 * 
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaEfetuar(Imovel imovel, LigacaoAgua ligacaoAgua)
			throws ControladorException;

	/**
	 * [UC0381] Inserir Material com Unidade
	 * 
	 * Permite a inclusao de um novo material
	 * 
	 * 
	 * [SB0001] Gerar Material com Unidade
	 * 
	 * 1.1Inclui o material na tabela Material
	 * 
	 * 
	 * 
	 * @author R�mulo Aur�lio, Diogo Peixoto
	 * @date 31/07/2006, 18/08/2011
	 * 
	 * @param codigoMaterial
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param unidadeMaterial
	 * 
	 * @throws ControladorException
	 */
	public Integer inserirMaterial(String codigoMaterial, String descricao, String descricaoAbreviada,
			String unidadeMaterial, Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0385] Inserir Tipo Perfil Servi�o
	 * 
	 * @author Ana Maria
	 * @date 01/08/2006
	 * 
	 * @param servicoPerfilTipo
	 * @throws ControladorException
	 */
	public Integer inserirServicoTipoPerfil(ServicoPerfilTipo servicoPerfilTipo)
			throws ControladorException;

	/**
	 * [UC0436] Inserir Tipo de Servi�o de Refer�ncia.
	 * 
	 * Permite a inclus�o de um tipo de servi�o de refer�ncia.
	 * 
	 * [FS0003] Validar indicador de existencia x Situa��o da Os de referencia
	 * 
	 * @author R�mulo Aur�lio.
	 * @date 05/08/2006
	 * 
	 * 
	 * @param servicoTipoReferencia
	 * @throws ControladorException
	 */

	public Integer inserirTipoServicoReferencia(
			ServicoTipoReferencia servicoTipoReferencia, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * [FS0004] - Validar Perfil do Servi�o
	 * 
	 * @author lms
	 * @date 01/08/2006
	 */
	public ServicoPerfilTipo pesquisarServicoPerfilTipo(
			Integer idServicoPerfilTipo) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * [FS0005] - Validar Tipo de Servi�o de Refer�ncia
	 * 
	 * @author lms
	 * @date 02/08/2006
	 */
	public ServicoTipoReferencia pesquisarServicoTipoReferencia(
			Integer idServicoTipoReferencia) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * [FS0009] - Validar Atividade
	 * 
	 * @author lms
	 * @date 05/08/2006
	 */
	public Atividade pesquisarAtividade(Integer idAtividade, String atividadeUnica)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * [FS0006] - Validar Ordem de Execu��o
	 * 
	 * @author lms
	 * @date 05/08/2006
	 */
	public void validarOrdemExecucao(Collection colecaoServicoTipoAtividade,
			Short ordemExecucao) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public Integer inserirServicoTipo(ServicoTipo servicoTipo, Usuario usuarioLogado,ServicoTipoBoletim servicoTipoBoletim)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public ServicoTipoSubgrupo pesquisarServicoTipoSubgrupo(
			Integer idServicoTipoSubgrupo) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public ServicoTipoPrioridade pesquisarServicoTipoPrioridade(
			Integer idServicoTipoPrioridade) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * [FS0010] - Validar Material
	 * 
	 * @author lms
	 * @date 08/08/2006
	 */
	public Material pesquisarMaterial(Integer idMaterial)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public void validarAdicionarAtividade(
			Collection colecaoServicoTipoAtividade, Integer idAtividade)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Servi�o
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public void validarAdicionarMaterial(Collection colecaoServicoTipoMaterial,
			Integer idMaterial) throws ControladorException;

	/**
	 * [UC0449] Inserir Prioridade do Tipo de Servi�o
	 * 
	 * Permite a inclus�o de uma prioridade do tipo de servi�o.
	 * 
	 * [FS0001] Verificar existencia da descri��o [FS0003]- Verificar exist�ncia
	 * da descri��o abreviada [FS0002] Validar quantidade de horas in�cio e
	 * quantidade de horas fim
	 * 
	 * @author R�mulo Aur�lio.
	 * @date 11/08/2006
	 * 
	 * 
	 * @param servicoTipoPrioridade
	 * @throws ControladorException
	 */
	public Integer inserirPrioridadeTipoServico(
			ServicoTipoPrioridade servicoTipoPrioridade, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades do
	 * supressao liga��o de agua
	 * 
	 * @author Rafael Pinto
	 * @date 28/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirSupressaoLigacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades do
	 * restabelecimento liga��o de agua
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirRestabelecimentoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades de
	 * religa��o de �gua
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirReligacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades de
	 * corte adimistrativo de liga��o de �gua
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirCorteAdministrativoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades do
	 * substitui��o de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 31/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirSubstituicaoHidrometro(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * retirada de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirRetiradaHidrometroAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * remanejamento de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirRemanejmentoHidrometroAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * [UC0362] Efetuar Instalacao de Hidr�metro
	 * 
	 * Validar Instalacao de Hidr�metro
	 * 
	 * @author Ana Maria
	 * @date 13/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param tipoMedicao
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validarExibirInstalacaoHidrometro(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este m�todo se destina a validar todas as situa��es e particularidades da
	 * atualiza��o da instala��o de hidr�metro do im�vel no momento da exibi��o.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirAtualizarInstalacaoHidrometro(
			OrdemServico ordemServico, boolean menu)
			throws ControladorException;

	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param servicoTipoId
	 * @param imovelId
	 * @param tipoMedicao
	 * @return valor do d�bito
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal obterValorDebito(Integer servicoTipoId, Integer imovelId,
			Short tipoMedicao) throws ControladorException;

	/**
	 * M�todo que retorna o n�mero do hidr�metro da liga��o de �gua
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometroLigacaoAgua(Integer idLigacaoAgua)
			throws ControladorException;

	/**
	 * M�todo que retorna o tipo da liga��o de �gua e a data do corte da liga��o
	 * de �gua
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLigacaoAgua(Integer idLigacaoAgua)
			throws ControladorException;
	
	/**
	 * [UC0357] Efetuar Religa��o de �gua
	 * 
	 * Permite efetuar religa��o da liga��o de �gua ou pelo menu ou pela
	 * funcionalidade encerrar a execu��o da ordem de servi�o.
	 * 
	 * [SB0001] Atualizar Im�vel/Liga��o de �gua/Liga��o de Esgoto
	 * 
	 * @author R�mulo Aur�lio
	 * @date 07/07/2006
	 * 
	 * @param ordemServico
	 * 
	 * @throws ControladorException
	 */
	public void efetuarReligacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;
	
	/**
	 * Consulta os dados das ordens de servi�o para a gera��o do relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(Integer idEquipe, Date dataRoteiro)
			throws ControladorException;
	
	/**
	 * [UC0364] Efetuar Substitui��o de Hidr�metro
	 * 
	 * Validar Substitui��o de Hidr�metro
	 * 
	 * @author Ana Maria
	 * @date 25/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param situacaoHidrometroSubstituido
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validacaoSubstituicaoHidrometro(String matriculaImovel,
			String numeroHidrometro, String situacaoHidrometroSubstituido)
			throws ControladorException; 
	
	/**
	 * [UC0362] Efetuar Instalacao de Hidr�metro
	 * 
	 * Validar Instalacao de Hidr�metro
	 * 
	 * @author Ana Maria
	 * @date 13/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param tipoMedicao
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validacaoInstalacaoHidrometro(String numeroHidrometro)
			throws ControladorException;
	
	/**
	 * [UC0387] Manter Tipo Perfil Servi�o
	 * 
	 * [SB0001] Atualizar Tipo Perfil Servi�o
	 * 
	 * @author Kassia Albuquerque
	 * @date 01/11/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public void atualizarServicoTipoPerfil(ServicoPerfilTipo servicoPerfilTipo,Usuario usuarioLogado)
			throws ControladorException ;
	
	/**
	 * 
	 * Este m�todo valida os dados que s�o necessarios para a 
	 * inser��o do servi�o tipo referencia.
	 *
	 *
	 * @author Fl�vio Leonardo
	 * @date 31/10/2006
	 *
	 * @param servicoTipoReferencia
	 * @return
	 * @throws ControladorException 
	 */
	public void validarTipoServicoReferenciaParaInsercao(ServicoTipoReferencia servicoTipoReferencia) throws ControladorException;
	
	
	/**
	 * [UC0387] Manter Tipo Perfil Servi�o
	 * [SB0002] Remover Tipo Perfil Servi�o
	 * 
	 * @author Kassia Albuquerque
	 * @date 08/11/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public void removerServicoTipoPerfil(String[] ids,Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0404] Manter Especifica��o da Situa��o do Imovel
	 * 
	 * Este caso de uso remove a especifica��o e os crit�rio
	 * 
	 * [SB0002] Remover Especifica��o da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovel(String[] idsEspecificacaoSituacaoImovel,
		Usuario usuario,Date ultimaAlteracao) throws ControladorException;	
	

	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Verificar exist�ncia de hidr�metro na liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId)
			throws ControladorException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Verificar exist�ncia de hidr�metro na liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId)
			throws ControladorException;
	
	
	/**
	 * [UC0383] Manter Material
	 * [SB0003] Remover Material
	 * 
	 * @author Kassia Albuquerque
	 * @date 16/11/2006
	 * 
	 * @pparam material
	 * @throws ControladorException
	 */
	public void removerMaterial(String[] ids,Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0383] Manter Material [SB0001] Atualizar Material
	 * 
	 * @param Material  
	 * @param Usuario Logado
	 * 
	 * @author Diogo Peixoto
	 * @date 18/08/2011
	 */
	public void atualizarMaterial(Material material, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0498] Efetuar Liga��o de �gua com Instala��o de Hidr�metro.
	 * 
	 * Permite validar o efetuar liga��o de �gua com Instala��o de Hidr�metro Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a execu��o da ordem de ser�o.
	 * 
	 * [FS0008] Verificar Situa��o Rede de �gua na Quadra. [FS0007] Verificar
	 * Situa��o do Imovel. [FS0002] Validar Situa��o de �gua do Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 28/11/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaComInstalacaoHidrometroExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException;
	
	/**
	 * [UC0498] Efetuar Liga��o de �gua com Instala��o de Hidr�metro.
	 * 
	 * Permite efetuar liga��o de �gua com Instala��o de Hidr�metr ou pelo menu
	 * ou pela funcionalidade encerrar a execu��o da ordem de ser�o.
	 * 
	 * @author Rafael Corr�a
	 * @date 29/11/2006
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarLigacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException;
	
	/**
	 * [UC0294] Prioridade Tipo Servico [] Atualizar Prioridade Tipo Servico
	 * 
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 25/05/2006
	 * 
	 * @param Prioridade Tipo Servico
	 * @throws ControladorException
	 */

	public void atualizarPrioridadeTipoServico(ServicoTipoPrioridade servicoTipoPrioridade,
			Collection colecaoServicoTipoPrioridade)
			throws ControladorException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * @author Rafael Pinto
	 * @date 22/02/2007
	 * 
	 * @param servicoTipoId
	 * @param imovelId
	 * @param tipoMedicao
	 * @param idHidrometroCapacidade
	 *
	 * @return valor do D�bito
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal obterValorDebito(Integer servicoTipoId, Integer imovelId,
		HidrometroCapacidade hidrometroCapacidade) throws ControladorException ;
	
	/**
	 * [UC0555] Alterar Situacao da Ligacao
	 * 
	 * @author Romulo Aurelio
	 * @date 27/03/2007
	 * 
	 * @param imovel
	 *
	 * 
	 * @throws ControladorException
	 */
	public void validarOrdemServicoAlterarSituacaoLigacao(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;
	
	public Integer alterarSituacaoLigacao(Imovel imovel, String indicadorTipoLigacao, String idSituacaoLigacaoAguaNova, 
			String idSituacaoLigacaoEsgotoNova, String idOrdemServico, 
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0540] Efetuar Restabelecimento da Liga��o de �gua com Instala��o de hidr�metro.
	 * 
	 * Permite validar o Efetuar Restabelecimento Liga��o de �gua com Instala��o de hidr�metro
	 * Exibir ou pelo menu ou pela funcionalidade encerrar a Execu��o da ordem
	 * de servi�o.
	 * 
	 * [FS0008] Verificar Situa��o Rede de �gua na Quadra. [FS0007] Verificar
	 * Situa��o do Imovel. [FS0002] Validar Situa��o de �gua do Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 18/04/2007
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarRestabelecimentoLigacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * [UC0540] Efetuar Restabelecimento da Liga��o de �gua com Instala��o de hidr�metro.
	 * 
	 * Permite efetuar o Restabelecimento Liga��o de �gua com Instala��o de Hidr�metro ou pelo menu
	 * ou pela funcionalidade encerrar a Execu��o da ordem de servi�o.
	 * 
	 * @author Rafael Corr�a
	 * @date 19/04/2007
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException;

	/**
	 * Pesquisa todos os ids das situa��es de liga��o de �gua.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua() throws ControladorException ;
	
	/**
	 * Pesquisa todos os ids das situa��es de liga��o de esgoto.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoEsgoto() throws ControladorException ;
	
	/**
	 * 
	 * Este cso de uso permite efetuar a liga��o de �gua e eventualmente a
	 * instala��o de hidr�metro, sem informa��o de RA sendo chamado direto pelo
	 * menu.
	 * 
	 * [UC0579] - Efetuar Liga��o de �gua com Intala��o de Hidr�metro
	 * 
	 * @author Fl�vio Leonardo
	 * @date 25/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper pesquisarEfetuarLigacaoAguaHidrometroSemRA(
			Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0XXX] Gerar Contrato de Presta��o de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 03/05/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection obterDadosContratoPrestacaoServico(
			Integer idImovel, Integer idCliente) throws ControladorException;
	
	public void atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(Integer idImovel, Integer idHidrometro);
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Obt�m os dados necess�rio da liga��o de �gua, de esgoto e do hidr�metro
	 * instalado na liga��o de �gua
	 * 
	 * @author Rafael Corr�a
	 * @date 17/05/2007
	 * 
	 * @throws ControladorException
	 */
	public DadosLigacoesBoletimCadastroHelper obterDadosLigacaoAguaEsgoto(
			Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * [UC0587] Emitir Contrato de Prestacao de servico
	 * 
	 * @param idImovel
	 * @param idClienteEmpresa
	 * @return
	 * @throws ControladorException
	 */
	public RelatorioContratoPrestacaoServicoJuridicoBean gerarContratoJuridica(Integer idImovel, Integer idClienteEmpresa) throws ControladorException;

	
	/**
	 * [UC0608] Efetuar Liga��o de Esgoto sem RA.
	 * 
	 * [FS0001] Verificar exist�ncia da matr�cula do Imovel.
	 * 
	 * [FS0007] Verificar situa��o do im�vel.
	 * 
	 * [FS0008] Verificar Situa��o Rede de Esgoto da Quadra.
	 * 
	 * @author S�vio Luiz.
	 * @date 10/09/2007
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public String validarMatriculaImovel(Integer idImovel)
			throws ControladorException;

	
	/**
	 * [UC0482]Emitir 2� Via de Conta
	 *obter numero do hidr�metro na liga��o de �gua.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ControladorException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Capacidade de Hidr�metro pela Liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(
			Integer imovelId) throws ControladorException;
	
	/**
	 * [UC0726] Gerar Relat�rio de Im�veis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioCertidaoNegativaHelper> pesquisarRelatorioCertidaoNegativa(
		Imovel imo ) 
		throws ControladorException;
	
	/**
	 * [UC0747] Efetuar Religa��o de �gua com Instala��o de hidr�metro.
	 * 
	 * Permite validar o efetuar religa��o de �gua com Instala��o de hidr�metro
	 * Exibir ou pelo menu ou pela funcionalidade encerrar a Execu��o da ordem
	 * de servi�o.
	 * 
	 * [FS0002] Verificar Situa��o do Imovel. [FS0003] Validar Situa��o de �gua
	 * 
	 * @author S�vio Luiz
	 * @date 29/01/2008
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarReligacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * [UC0498] Efetuar Religa��o de �gua com Instala��o de hidr�metro.
	 * 
	 * Permite efetuar religa��o de �gua com Instala��o de Hidr�metro ou pelo
	 * menu ou pela funcionalidade encerrar a Execu��o da ordem de servi�o.
	 * 
	 * @author S�vio Luiz
	 * @date 29/01/2008
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarReligacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException;
	
	public Integer atualizarServicoTipo(ServicoTipo servicoTipo,ServicoTipoBoletim servicoTipoBoletim)
	throws ControladorException;
	
	/**
	 * [UC0778] Gerar Relat�rio Gest�o de Servi�os UPA<br>
	 * [UC0497] Gerar Relat�rio Resumo de Solicita��es de RA por Unidade
	 * <p>
	 * Retorna todas as unidades filhas (direta ou indiretamente) da Unidade Superior passada como par�metro.
	 * 
	 * @see gcom.atendimentopublico.ControladorAtendimentoPublicoSEJB#pesquisarUnidadesFilhas(int)
	 * 
	 * @author Victor Cisneiros
	 * @date 09/07/2008
	 * 
	 * @param idUnidadeSuperior
	 * @throws ControladorException
	 */
	public UnidadesFilhasHelper pesquisarUnidadesFilhas(int idUnidadeSuperior) throws ControladorException;
	
	/**
	 * Pesquisa os dados necess�rios para a gera��o do relat�rio
	 * 
	 * [UC0864] Gerar Certid�o Negativa por Cliente
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioCertidaoNegativaClienteBean> pesquisarRelatorioCertidaoNegativaCliente(
			Collection<Integer> idsClientes, Cliente clienteInformado)
			throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 12/08/2008
	 */
	public void efetuarLigacaoAguaComInstalacaoHidrometroSemRA(
			Integer idImovel, 
			LigacaoAgua ligacaoAgua,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) throws ControladorException;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0003] - Verificar se documento � v�lido
	 *
	 * [FS0004] - Cliente n�o associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cpf
	 * @param cnpj
	 * @throws ControladorException
	 */
	public void verificarDocumentoValidoEmissaoInternet(Integer idImovel, String cpf, String cnpj) 
	throws ControladorException ;
	
	/**
	 * [UC0482] Emitir 2a Via Conta
	 *
	 * [FS0002] - Cliente sem documento
	 *
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 *
	 * @param idImovel
	 * @param usuario
	 * @throws ControladorException
	 */
	public void verificarClienteSemDocumento(Integer idImovel, Usuario usuario) throws ControladorException ;

	/**
	 * Permite inserir um Perfil da Ligacao de Esgoto
	 * 
	 * [UC0861] Inserir Perfil da Ligacao de Esgoto
	 * 
	 * @author Arthur Carvalho
	 * @date 16/10/2006
	 * 
	 */

	public Integer inserirPerfilLigacaoEsgoto(LigacaoEsgotoPerfil ligacaoEsgotoPerfil, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * Permite atualizacao um Perfil da Ligacao de Esgoto
	 * 
	 * [UC0861]Manter Perfil da Ligacao de Esgoto
	 * 
	 * @author Arthur Carvalho
	 * @date 20/10/2008
	 * 
	 */

	public void atualizarPerfilLigacaoEsgoto(LigacaoEsgotoPerfil ligacaoEsgotoPerfil, Usuario usuarioLogado)
			throws ControladorException;

	 /**
     * [UC0150] Retificar Conta
     * @author Vivianne Sousa
     * @date 26/11/2008
     */
    public BigDecimal obterPercentualAguaConsumidaColetadaImovel(Integer idImovel)
    	throws ControladorException ;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * [SB0002] - Gerar D�bito a Cobrar
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
	public Short gerarDebitoACobrarAutoInfracao(AutosInfracao autosInfracao,SistemaParametro sistemaParametro)
			throws ControladorException;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
	public void atualizarAutosInfracaoComPrazoRecursoVencido(
			SistemaParametro sistemaParametro,Integer idFuncionalidadeIniciada)
			throws ControladorException; 
	
	/**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 *  Step que gera os dados.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void gerarDadosOrdemDeFiscalizacao(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,SetorComercial setorComercial,SistemaParametro sistemaParametro) throws ControladorException;
	
	/**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 *  Step que gera os arquivos.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 10/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void gerarArquivoOrdemDeFiscalizacao(Integer integer, Usuario usuario)throws ControladorException;
	
	/**
	 * [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e
	 * valor. Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna o
	 * total.
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal() throws ControladorException;
	
	/**
	 * [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e
	 * valor. Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna uma
	 * cole��o.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo	
	 * @date 04/02/2010 - 26/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(
			String[] selecionacos) throws ControladorException;
	
	/**
	 * [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e
	 * valor. Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna uma
	 * cole��o.
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(
			Integer numeroPagina) throws ControladorException;
	
	/**
	 * [UC0391] Inserir valor cobran�a Servi�o.
	 * 
	 * Verificar se existe vig�ncia j� cadastrada para o Servi�o Tipo.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idServicoTipo
	 * @param opcao
	 * @throws ControladorException
	 * @data 03/05/2010
	 * 
	 * @see Caso a opcao = 1 - verifica as situa��es de inserir e atualizar Servi�o Tipo.
	 * @see Caso a opcao = 2 - verifica a situa��o de retificar Servi�o Tipo.
	 */
	public void verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo, Integer opcao) 
			throws ControladorException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] � Verificar RA de urg�ncia
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urg�ncia
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   04/06/2010
	 * 
	 */
	public Integer verificarRegistroAtendimentoUrgencia(Integer IdRegistroAtendimento)
		throws ControladorException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento
	 *  [SB0004] � Verificar RA de urg�ncia
	 *  
	 * Atualizar os Usu�rios da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   03/06/2010
	 * 
	 */
	
	public void inserirUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento,Short indicadorReiteracao)
			throws ControladorException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento
	 *  [SB0004] � Verificar RA de urg�ncia
	 *  
	 * Atualizar os Usu�rios da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   03/06/2010
	 * 
	 */
	
	public void atualizarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
			throws ControladorException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] � Verificar RA de urg�ncia
	 * 
	 * Verifica se o Registro de Atendimento j� est� relacionado a uma Unidade informada.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   05/06/2010
	 * 
	 */
	public Integer verificarUsuariosRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, Integer idUnidade)
		throws ControladorException;
	
	/**	 
	 * [UC1028] Exibir Registro Atendimento Urg�ncia
	 *  
	 * Verifica se o Usuario possui algum Registro de Atendimento urgente.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   07/06/2010
	 * 	  
	 */
	public Collection verificarUsuarioRegistroAtendimentoUrgencia(Integer idUsuario)
		throws ControladorException;
	
    /**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ControladorException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRAAnalitico(FiltrarAcompanhamentoRegistroAtendimentoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC1056] pesquisar Total de RA's do Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 30/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Intenger
	 * 
	 * @throws ControladorException
	 */
	public Integer countPesquisarRelatorioAcompanhamentoRAAnalitico(FiltrarAcompanhamentoRegistroAtendimentoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento Sintetico Encerrado
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoEncerrado(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 	throws ControladorException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento Sintetico Aberto
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoAberto(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 	throws ControladorException;
	
	/**
	 * Remover todas as LocalidadeComEspecificacaoUnidade
	 * [UC1091] Informar Associa��o de Localidade com Especifica��o e Unidade
	 * 
	 * @author Hugo Leonardo
	 * @date 30/11/2010
	 * 
	 * @param idLocalidade
	 * @return void
	 */
	public void removerLocalidadeComEspecificacaoUnidade( Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 27/12/2010
 	 * 
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return boolean
	 */
	public boolean verificaRemoverCustoPavimentoPorRepavimentadora(Integer idRepavimentadora,
			Integer idPavimento, Integer indicadorPavimento)throws ControladorException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 28/12/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return void
	 */
	public void verificaAtualizarCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, Date dataFinal, 
			Integer indicadorPavimento, Integer tipo) throws ControladorException;
					
	/**
	 * [UC1102] - Inserir Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @data: 03/12/2010
	 * 
	 * @param descricao
	 * @param indicadorUso
	 * @param indicadorCorteAdministrativo
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirTipoCorte(String descricao, String indicadorUso, String indicadorCorteAdministrativo, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC1103] Manter Tipo de Corte
	 *  
	 * @author Ivan Sergio
	 * @date 06/12/2010
	 * 
	 * @pparam cortetipo
	 * @throws ControladorException
	 */
	public void atualizarCorteTipo(CorteTipo corteTipo, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC1103] - Manter Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @data: 07/12/2010
	 * 
	 * @param ids
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerCorteTipo(String[] ids, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
	 * 
	 * 		[FS0010] Verificar se existem dias sem valor
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 11/01/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return Integer
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, Date dataFinal, 
			Integer indicadorPavimento, Integer tipo) throws ControladorException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) throws ControladorException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer countRelatorioReligacaoClientesInadiplentes( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) throws ControladorException;

	
	/**
     * Obt�m a cole��o de perfis de tipo de servi�o para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */
	
	public Collection obterColecaoTipoOSgerada() throws ControladorException;

	
	
	/**
     * Obt�m a cole��o de OS a partir dos par�metros passados pela funcionalidade de Acompanhamento de Cobran�a por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ControladorException
     */
	
	public Collection obterColecaoImovelOSCobrancaResultado(String[] categoriaImovel, String[] perfilImovel, String[] gerenciaRegional, String[] unidadeNegocio, 
			String idLocalidadeInicial, String idLocalidadeFinal, String idSetorComercialInicial, String idSetorComercialFinal,
			String idQuadraInicial, String idQuadraFinal, String tipoServico,String comando) throws ControladorException;
	
	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * O segundo par�metro (boletimGerado) � um booleano que
	 * indica se para um dado grupo de cobran�a e um m�s referencia
	 * foi gerado um boletim de medi��o.
	 * 
	 * @author Diogo Peixoto
	 * @date 09/06/2011
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper

	 * @return FiltrarRelatorioOSSituacaoHelper
	 * @throws ControladorException
	 */
	public RelatorioOSSituacaoHelper filtrarRelatorioOSSituacao(FiltrarRelatorioOSSituacaoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 17/06/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return RelatorioAcompanhamentoBoletimMedicaoHelper
	 */
	public RelatorioAcompanhamentoBoletimMedicaoHelper filtrarRelatorioAcompanhamentoBoletimMedicao(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ControladorException;

	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar as Ordens de servi�os a partir de seu im�vel e tipo de servi�o
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ControladorException;
	
	/**
	 * 
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
     * Obt�m a quantida de OS a partir dos par�metros passados pela funcionalidade de Acompanhamento de Cobran�a por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ControladorException
     */
	
	public int obterTotalOSColecaoImovelTipoServico(Collection colecaoImovel,Integer tipoServico) throws ControladorException;
	
	 /**
	  * [UC1189] Inserir Registro de Atendimento Loja Virtual
	  * 
	  * @author Magno Gouveia
	  * @date 12/07/2011
	  * 
	  * @return
	  * @throws ErroRepositorioException
	  */
	public Collection<Object[]> pesquisarSolicitacaoTipoLojaVirtual() throws ControladorException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0001] Selecionar Munic�pios da Regi�o
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @return colecaoDeMunicipios
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMunicipiosLojaVirtualCompesa() throws ControladorException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0005] Exibir Dados da Loja
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @param id do bairro
	 * @return colecaoDeLojas
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarLojasDeAtendimentoLojaVirtualCompesa(Integer idMunicipio) throws ControladorException;
	
	public void ProcessarEncerramentoOSFiscalizacaoDecursoPrazo(Integer idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * [SB0003] � Pesquisar Fotos da OS
	 * 
	 * M�todo que vai retornar as fotos de uma determinada
	 * ordem de servi�o passada no par�metro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID (Ordem de Servi�o ou da Foto Ordem de Servi�o)
	 * @param Boolean - Indica se o id � da OS ou da Foto (true = OS, false = Foto) 
	 * 
	 * @return Collection<OrdemServicoFoto> - Cole��o das Fotos da OS
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoFoto> pesquisarFotosOrdemServico(Integer id, boolean idOS) throws ControladorException;
	
	/**
	 *
	 * 
	 * @autor: Wellington Rocha
	 * @date: 03/07/2012
	 * 
	 *        Pesquisar Locais de Insta��o de Ramal Ativos
	 * 
	 *        Gera��o de rotas para recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 * 
	 */
	public Collection<RamalLocalInstalacao> pesquisarRamalLocalInstalacao()
			throws ControladorException;
	
}

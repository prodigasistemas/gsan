package gcom.micromedicao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.RotaAcaoCriterioHelper;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper;
import gcom.gui.micromedicao.ColetaMedidorEnergiaHelper;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.gui.relatorio.micromedicao.FiltroRelatorioLeituraConsultarArquivosTextoHelper;
import gcom.gui.relatorio.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultaneaHelper;
import gcom.micromedicao.bean.AnaliseConsumoRelatorioOSHelper;
import gcom.micromedicao.bean.AssociarConjuntoRotasCriterioCobrancaHelper;
import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;
import gcom.micromedicao.bean.HidrometroRelatorioOSHelper;
import gcom.micromedicao.bean.ImovelFaltandoSituacaoLeituraHelper;
import gcom.micromedicao.bean.InformarSubdivisoesDeRotaHelper;
import gcom.micromedicao.bean.MonitorarLeituraMobilePopupHelper;
import gcom.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;
import gcom.micromedicao.bean.SituacaoLeituraHelper;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.cadastro.micromedicao.FiltrarRelatorioImoveisComLeiturasHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioImoveisComLeiturasQuantitativosBean;
import gcom.relatorio.cadastro.micromedicao.RelatorioImoveisComLeiturasRelacaoBean;
import gcom.relatorio.cadastro.micromedicao.RelatorioImoveisComLeiturasTipo7Bean;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometroBean;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometroHelper;
import gcom.relatorio.micromedicao.AvisoAnormalidadeRelatorioHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.relatorio.micromedicao.FiltrarRelatorioAnormalidadeLeituraPeriodoHelper;
import gcom.relatorio.micromedicao.FiltrarRelatorioBoletimMedicaoHelper;
import gcom.relatorio.micromedicao.GerarDadosLeituraHelper;
import gcom.relatorio.micromedicao.RelatorioAcompanhamentoLeituristaHelper;
import gcom.relatorio.micromedicao.RelatorioAnormalidadeLeituraPeriodoBean;
import gcom.relatorio.micromedicao.RelatorioGerarDadosLeituraBean;
import gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.mail.SendFailedException;

import org.apache.commons.fileupload.FileItem;

/**
 * Interface Controlador Faturamento PADRÃO
 * 
 * @author Raphael Rossiter
 * @date 20/12/2006
 */
public interface IControladorMicromedicao {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param sistemaParametro
	 *            Descrição do parâmetro
	 */
	public void consistirLeiturasCalcularConsumos(
			FaturamentoGrupo faturamentoGrupo,
			SistemaParametro sistemaParametro, Collection<Rota> colecaoRotas,
			int idFuncionalidadeIniciada) throws ControladorException;



	/**
	 * < <Descrição do método>>
	 * 
	 * @param hidrometro
	 *            Descrição do parâmetro
	 * @param fixo
	 *            Descrição do parâmetro
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 */
	public void inserirHidrometro(Hidrometro hidrometro, String fixo,
			Integer faixaInicial, Integer faixaFinal)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param fixo
	 *            Description of the Parameter
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection pesquisarNumeroHidrometroFaixa(String fixo,
			String faixaInicial, String faixaFinal) throws ControladorException;

	/**
	 * Pesquisa uma coleção de hidrômetros de acordo com fixo, faixa inicial e
	 * faixa final
	 * 
	 * @param fixo
	 *            Description of the Parameter
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection pesquisarNumeroHidrometroFaixaRelatorio(String fixo,
			String faixaInicial, String faixaFinal) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param fixo
	 *            Description of the Parameter
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection pesquisarNumeroHidrometroFaixaPaginacao(String fixo,
			String faixaInicial, String faixaFinal, Integer numeroPagina)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param fixo
	 *            Description of the Parameter
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection pesquisarNumeroHidrometroFaixaComLimite(String fixo,
			String faixaInicial, String faixaFinal) throws ControladorException;

	/**
	 * Description of the Method
	 * 
	 * @param hidrometro
	 *            Description of the Parameter
	 */
	public void atualizarHidrometro(Hidrometro hidrometro)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param colecaoHidrometro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public String verificarLocalArmazenagemSituacao(Collection colecaoHidrometro)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param colecaoHidrometro
	 *            Descrição do parâmetro
	 * @param data
	 *            Descrição do parâmetro
	 * @param hora
	 *            Descrição do parâmetro
	 * @param idLocalArmazenagemDestino
	 *            Descrição do parâmetro
	 * @param idMotivoMovimentacao
	 *            Descrição do parâmetro
	 * @param parecer
	 *            Descrição do parâmetro
	 */
	public void inserirAtualizarMovimentacaoHidrometroIds(
			Collection colecaoHidrometro, String data, String hora,
			String idLocalArmazenagemDestino, String idMotivoMovimentacao,
			String parecer, Usuario usuario) throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param colecaoHidrometro
	 *            Descrição do parâmetro
	 * @param ids
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection obterColecaoObjetosSelecionados(
			Collection colecaoHidrometro, String[] ids)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 */
	public void executarImovelTestesMedicaoConsumo(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ControladorException;

	public int obterConsumoMinimoLigacao(Imovel imovel,
			Collection colecaoCategorias) throws ControladorException;

	/**
	 * [UC0083] - Gerar Dados para Leitura
	 * 
	 * [SF0001] - Gerar Arquivo MicroColetor
	 * 
	 * Autor: Sávio Luiz Data: 21/12/2005
	 */
	public Collection gerarDadosPorLeituraMicroColetor(Collection colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,SistemaParametro sistemaParametro,
			int idFuncionalidadeIniciada) throws ControladorException;

	/*
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos Flávio Leonardo
	 * Cavalcanti Cordeiro
	 */
	public Collection filtrarExcecoesLeiturasConsumos(
			FaturamentoGrupo faturamentoGrupo,
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			Integer numeroPagina, boolean todosRegistros,
			String mesAno,String valorAguaEsgotoInicial, String valorAguaEsgotoFinal)
			throws ControladorException;

	/**
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos Flávio Leonardo
	 * Cavalcanti Cordeiro
	 */
	public Collection pesquisarLigacoesMedicaoIndividualizada(Integer idImovel,
			String anoMes) throws ControladorException;

	/*
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos Flávio Leonardo
	 * Cavalcanti Cordeiro
	 */
	public Integer filtrarExcecoesLeiturasConsumosCount(
			FaturamentoGrupo faturamentoGrupo,
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			String mesAno, String valorAguaEsgotoInicial, String valorAguaEsgotoFinal)
			throws ControladorException;

	/**
	 * [UC0087] - Calcular Faixa de Leitura Falsa Autor: Sávio Luiz Data:
	 * 29/12/2005
	 */
	public Object[] calcularFaixaLeituraFalsa(Imovel imovel, int media,
			Integer leituraAnterior, MedicaoHistorico medicaoHistorico,
			boolean hidrometroSelecionado, Hidrometro hidrometro)
			throws ControladorException;

	public Collection pesquisarLeituraAnteriorTipoLigacaoAgua(Integer idImovel,
			Integer anoMesAnterior) throws ControladorException;

	/**
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo Atualizar Tipo
	 * Rateio com Vinculo com o Imovel Author: Rafael Santos Data: 12/01/2006
	 * 
	 * @param imovel
	 * @param hidrometroInstalacaoHistoricoAgua
	 * @param hidrometroInstalacaoHistoricoPoco
	 * @throws ControladorException
	 * 
	 */
	public void atualizarTipoRateio(Imovel imovel,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco,
			Usuario usuarioLogado,
			boolean indicadorRateioPorAreaComum,
			Integer imovelRemover) throws ControladorException;

	/**
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo Atualizar Tipo
	 * Rateio com Vinculo com o Imovel Author: Rafael Santos Data: 16/01/2006
	 * Estabelecer Vinculo ao Imovel
	 * 
	 * @param Imovel
	 * @param imoveisASerVinculados
	 * @param imoveisASerDesvinculados
	 * @param hidrometroInstalacaoHistoricoAgua
	 * @param hidrometroInstalacaoHistoricoPoco
	 * @throws ControladorException
	 */
	public void estabelecerVinculo(Imovel imovel,
			Collection imoveisASerVinculados,
			Collection imoveisASerDesvinculados,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoPoco,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo Atualizar Tipo
	 * Rateio com Vinculo com o Imovel Author: Rafael Santos Data: 16/01/2006
	 * Desfazer Vinculo ao Imovel
	 * 
	 * @param Imovel
	 * @param ids
	 * @throws ControladorException
	 */
	public void desfazerVinculo(Imovel imovel, String[] ids,
			boolean desvincular, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */
	public Collection registrarLeiturasAnormalidades(
			Collection colecaoMedicaoHistorico, Integer idFaturamentoGrupo,
			Integer anoMesReferencia, Usuario usuario, String nomeArquivo)
			throws ControladorException;

	public Collection pesquisarHidrometroPorHidrometroMovimentacao(Filtro filtro)
			throws ControladorException;

	/**
	 * Consultar Dados do Cliente Imovel Vinculado Auhtor: Rafael Santos Data:
	 * 23/01/2006 [UC0179] Consultar Historico Medição Indiviualizada
	 * 
	 * @param inscricaoImovel
	 *            Inscrição do Imovel
	 * @return Dados do Imovel Vinculado
	 * @throws ControladorException
	 */
	public Cliente consultarDadosClienteImovelUsuario(Imovel imovel)
			throws ControladorException;

	/**
	 * Consultar Historico Medição Individualizada Auhtor: Rafael Santos Data:
	 * 23/01/2006 [UC0179] Consultar Historico Medição Indiviualizada
	 * 
	 * @param imovelCondominio
	 *            Imovel Condominio
	 * @param anoMesFaturamento
	 *            Ano Mês Fauramento
	 * @return Coleção deDados do Historico Medição Individualizada
	 * @throws ControladorException
	 */
	public Collection consultarHistoricoMedicaoIndividualizada(
			Imovel imovelCondominio, String anoMesFaturamento,LigacaoTipo ligacaoTipoInformado)
			throws ControladorException;

	/**
	 * Consultar Matriculas dos Imoveis Vinculados do Imovel condominio Auhtor:
	 * Rafael Santos Data: 23/01/2006 [UC0179] Consultar Historico Medição
	 * Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarConsumoHistoricoImoveisVinculados(
			ConsumoHistorico consumoHistorico) throws ControladorException;

	/**
	 * Consultar Dados Consumo Tipo Imovel Auhtor: Rafael Santos Data:
	 * 23/01/2006 [UC0179] Consultar Historico Medição Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public ConsumoTipo consultarDadosConsumoTipoConsumoHistorico(
			ConsumoHistorico consumoHistorico) throws ControladorException;

	/**
	 * Consultar Consumo Historico da Medicao Individualizada Auhtor: Rafael
	 * Santos Data: 23/01/2006 [UC0179] Consultar Historico Medição
	 * Indiviualizada
	 * 
	 * @param imovel
	 *            Imovel
	 * @param ligcaoTipo
	 *            Tipo de Ligacação
	 * @param anoMesFaturamento
	 *            Ano Mes Faturamento
	 * @return
	 * @throws ControladorException
	 */
	public ConsumoHistorico obterConsumoHistoricoMedicaoIndividualizada(
			Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ControladorException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento Author: Rafael Santos
	 * Data:03/02/2006 Consultar Medicao Historico do Tipo Agua
	 * 
	 * @param imovel
	 *            Id do Imovel Imovel
	 * @param anoMes
	 *            Ano Mes
	 * @return Medicao Historico
	 * @exception ControladorException
	 */
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoAgua(Integer imovel,
			Integer anoMes) throws ControladorException;

	/**
	 * [UC0113] Faturar Grupo de Faturamento Author: Rafael Santos
	 * Data:03/02/2006 Consultar Medicao Historico do Tipo Poço
	 * 
	 * @param imovel
	 *            Id do Imovel Imovel
	 * @param anoMes
	 *            Ano Mes
	 * @return Medicao Historico
	 * @exception ControladorException
	 */
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoPoco(Integer imovel,
			Integer anoMes) throws ControladorException;

	/**
	 * Consultar Consumo Historico da Medicao Individualizada [UC0113] Faturar
	 * Grupo Faturamento Auhtor: Rafael Santos Data: 20/02/2006
	 * 
	 * @param idImovel
	 * @param idAnormalidade
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ConsumoHistorico obterConsumoHistoricoAnormalidade(Integer idImovel,
			Integer idAnormalidade, int anoMes) throws ControladorException;

	/**
	 * Atualizar Medicação Historico [UC0000] Alterar Dados Para Faturamento
	 * Auhtor: Rafael Corrêa Data: 07/03/2006
	 * 
	 * @param medicaoHistorico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarMedicaoHistorico(MedicaoHistorico medicaoHistorico,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Consultar Imoveis com Medição Indiviualizada Auhtor: Sávio Luiz Data:
	 * 06/02/2006 [UC0180] Consultar Imoveis com Medição Indiviualizada
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoRateioTipoLigacaoAgua(Integer idImovel)
			throws ControladorException;

	/**
	 * Consultar Imoveis com Medição Indiviualizada Auhtor: Sávio Luiz Data:
	 * 06/02/2006 [UC0180] Consultar Imoveis com Medição Indiviualizada
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoRateioTipoLigacaoEsgoto(Integer idImovel)
			throws ControladorException;



	/**
	 * [UC0039] Manter Rota
	 * 
	 * Altera um objeto do tipo rota no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 06/04/2006
	 * @param rota
	 * 
	 * @param collectionRotaAcaoCriterio
	 * @return void
	 * @throws ControladorException
	 */
	public void atualizarRota(Rota rota, String idLocalidade,
			Collection collectionRotaAcaoCriterio, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0038] Inserir Rota
	 * 
	 * Insere um objeto do tipo rota no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 17/04/2006
	 * @param rota
	 * @param idLocalidade
	 * @param collectionRotaAcaoCriterio
	 * @return idRota
	 * @throws ControladorException
	 */
	public Integer inserirRota(Rota rota, String idLocalidade,
			Collection collectionRotaAcaoCriterio, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0039] Manter Rota
	 * 
	 * Remove um objeto do tipo rota no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 05/05/2006
	 * @param ids
	 * @return void
	 * @throws ControladorException
	 */
	public void removerRota(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Método que efetua o Rateio do consumo para todos os imóveis de uma rota
	 * que sejam imóveil condominio
	 * 
	 * [UC0103] - Efetuar Rateio de Consumo
	 * 
	 * @author Thiago Toscano
	 * @date 07/04/2006
	 * 
	 * @param colecaoRotas
	 * @param anoMesFaturamento
	 */
	public void efetuarRateioDeConsumo(Collection rotas,
			Integer anoMesFaturamento, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * 
	 * Filtrar Hidrometro
	 * 
	 * Faz a pesquisa da quantidade que o filtro vai retornar
	 * 
	 * @author Fernanda Paiva
	 * @date
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarNumeroHidrometroFaixaCount(String fixo,
			String faixaInicial, String faixaFinal) throws ControladorException;

	/**
	 * Método que retorna o maior código de Rota de um Setor Comercial
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ControladorException
	 */

	public Short pesquisarMaximoCodigoRota(Integer idSetorComercial)
			throws ControladorException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarImovelExcecoesApresentaDados(Integer idImovel,
			boolean ligacaoAgua) throws ControladorException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarMedicaoConsumoHistoricoExcecoesApresentaDados(
			FaturamentoGrupo faturamentoGrupo, Integer idImovel,
			boolean ligacaoAgua) throws ControladorException;

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosMedicao(Integer idImovel, boolean ligacaoAgua)
			throws ControladorException;

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosMedicaoResumo(Integer idImovel,
			boolean ligacaoAgua) throws ControladorException;

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosConsumo(Integer idImovel, int anoMes,
			boolean ligacaoAgua) throws ControladorException;

	/**
	 * 
	 * Método utilizado para pesquisar os consumo historicos a serem
	 * substituidos pelo caso de uso [UC0106] Substituir Consumos Anteriores
	 * 
	 */
	public Collection pesquisaConsumoHistoricoSubstituirConsumo(Integer idImovel)
			throws ControladorException;

	/**
	 * Atualiza o valor de cshi_nnconsumoFaturadomes consumo historico [UC0106] -
	 * Substituir Consumos Anteriores
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumosAnteriores(ConsumoHistorico consumoHistorico)
			throws ControladorException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Flávio Cordeiro
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public ImovelMicromedicao pesquiarImovelExcecoesApresentaDadosResumido(
			Integer idImovel, boolean ligacaoAgua) throws ControladorException;


	/**
	 * 
	 * Retorna um objeto com os dados das medicoes para apresentação
	 * 
	 * Flávio
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public ImovelMicromedicao carregarDadosMedicaoResumido(Integer idImovel,
			boolean ligacaoAgua, String anoMes) throws ControladorException;

	/**
	 * Atualizar Analise excecoes consumo resumido
	 * @param idMedicaoTipo 
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarLeituraConsumoResumido(Integer idImovel,
			String mesAno, String dataLeituraAnteriorFaturamento,
			String leituraAnteriorFaturamento,
			String dataLeituraAtualInformada, String leituraAtualInformada,
			String consumo, boolean ligacaoAgua, Integer idAnormalidade,
			Integer idleituraSituacaoAtual, FaturamentoGrupo faturamentoGrupo, Usuario usuarioLogado, boolean alterouAnormalidade,
			MotivoInterferenciaTipo motivoInterferenciaTipo, Integer idMedicaoTipo)
			throws ControladorException;
	
	/**
	 * Atualizar Analise excecoes consumo resumido
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAnalisadoMedicaoHistorico(Integer idMedicaoHistorico, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * consumo com tipo de medição poco
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0006] Obter Dados de consumo da conta
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idTipoLigacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosConsumoConta(Integer idImovel,
			int anoMesReferencia, Integer idTipoLigacao)
			throws ControladorException;

	/**
	 * Método que retorna o número da leitura de retirada do hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/09/2006
	 * 
	 * @param idLigacaoAgua
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroLeituraRetiradaLigacaoAgua(
			Integer idLigacaoAgua) throws ControladorException;

	/**
	 * Método que retorna o número da leitura de retirada do hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroLeituraRetiradaImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0000] - Efetuar Retirada de Hidrômetro
	 * 
	 * Pesquisa todos os campos do Hidrometro e seus relacionamentos
	 * obrigatórios.
	 * 
	 * @author Thiago Tenório
	 * @date 28/09/2006
	 * 
	 * @param idHidrometro
	 * @throws ControladorException
	 */
	public Hidrometro pesquisarHidrometroPeloId(Integer idHidrometro)
			throws ControladorException;

	/**
	 * Método que retorna o consumo médio do imóvel
	 * 
	 * @author Ana Maria
	 * @date 17/10/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarConsumoMedioImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Integer pesquisarConsumoMedio(Integer idImovel, Integer tipoMedicao)
			throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Integer pesquisarMaiorConsumoFaturadoQuantidadeMeses(
			Integer idImovel, Integer tipoMedicao, short qtdMeses)
			throws ControladorException;

	/**
	 * Atualizar Hidrômetro
	 * 
	 * Pesquisa o imóvel no qual o hidrômetro está instalado
	 * 
	 * @author Rafael Corrêa
	 * @date 23/11/2006
	 * 
	 * @param idHidrometro
	 * @return String
	 * @throws ControladorException
	 */
	public String pesquisarImovelHidrometroInstalado(Integer idHidrometro)
			throws ControladorException;

	/**
	 * [UC0498] - Efetuar Ligação de Água com Instalaação de Hidrômetro
	 * 
	 * Pesquisa o id do hidrômetro e a sua situação pelo número
	 * 
	 * @author Rafael Corrêa
	 * @date 29/11/2006
	 * 
	 * @param numeroHidrometro
	 * @return Hidrometro
	 * @throws ControladorException
	 */
	public Hidrometro pesquisarHidrometroPeloNumero(String numeroHidrometro)
			throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 06/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataInstalacaoHidrometroAgua(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 06/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataInstalacaoHidrometroPoco(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoFaturaMes(Integer amReferencia,
			Integer idLigacao, Integer idImovel) throws ControladorException;

	/**
	 * Obtém os ids de todas as rotas cadastradas
	 * 
	 * [UC0251] - Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0002] - Gerar Atividade de Ação de Cobrança para os Imóveis da Lista
	 * de Rotas
	 * 
	 * @author Leonardo Vieira
	 * @date 13/12/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotas() throws ControladorException;

	/**
	 * [] Ligacoes Medicao Individualizada
	 * 
	 * 
	 * @author Flávio Cordeiro
	 * @date 17/12/2006
	 * 
	 * @param colecaoLigacoesMedicao
	 * @throws ControladorException
	 */
	public void atualizarLigacoesMedicaoIndividualizada(
			Collection colecaoLigacoesMedicao, Usuario usuarioLogado,
			Integer anoMes) throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Raphael Rossiter
	 * @date 19/01/2007
	 * 
	 * @param Integer
	 *            idImovel, Integer tipoMedicao
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarUltimoAnoMesConsumoFaturado(Integer idImovel,
			Integer tipoMedicao) throws ControladorException;

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 19/01/2007
	 * 
	 * @param Rota
	 * @param ano
	 *            e es corrente
	 * @param id
	 *            grupo faturamento
	 * @param id
	 *            funcionalidade iniciada
	 * 
	 * @throws ControladorException
	 */
	public void gerarDadosPorLeituraConvencional(Collection<Rota> colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,SistemaParametro sistemaParametro,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * 
	 * Método que retorna o consumo de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado anoMes do faturamento. Método utilizado
	 * pra saber a ligacao de
	 * 
	 * 2.2.2.2 e 2.2.3.2 do [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 07/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterConsumoAnteriorAnormalidadeDoImovel(Integer idImovel,
			Integer anoMes, Integer idLigacaoTipo) throws ControladorException;

	/**
	 * Atualiza o valor de cshi_nnconsumomedio, cshi_nnconsumomediohidrometro
	 * consumo historico [UC0106] - Substituir Consumos Anteriores
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumosMedio(Integer idImovel,
			Integer anoMesConsumoHistorico, 
			int consumoMedioHidrometroAgua, int consumoMedioHidrometroEsgoto);

	/**
	 * Obtém os ids de todas as rotas cadastradas menos as rotas que tiverem o
	 * emp_cobranca = 1
	 * 
	 * [UC0251] - Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0002] - Gerar Atividade de Ação de Cobrança para os Imóveis da Lista
	 * de Rotas
	 * 
	 * @author Sávio Luiz
	 * @date 05/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotasEspecificas()
			throws ControladorException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquisarMedicaoConsumoHistoricoExcecoesApresentaDadosConsultarImovel(
			Integer anoMesReferencia, Integer idImovel, boolean ligacaoAgua)
			throws ControladorException;

	/**
	 * Pesquisa todas as rotas do sistema. Metódo usado no [UC0302] Gerar Débito
	 * a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 20/03/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarListaRotasCarregadas()
			throws ControladorException;

	/**
	 * Description of the Method
	 * 
	 * @param hidrometros
	 *            Description of the Parameter
	 * @param hidrometroAtualizado
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarConjuntoHidrometro(String fixo,String inicialFixo,String finalFixo,
			Hidrometro hidrometroAtualizado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0105] Obter Consumo Mínimo da Ligação por Subcategoria
	 * 
	 * @author Raphael Rossiter
	 * @date 11/04/2007
	 * 
	 * @return imovel, colecaoSubcategoria
	 * @throws ControladorException
	 */
	/*public int obterConsumoMinimoLigacaoPorSubcategoria(Imovel imovel,
			Collection colecaoSubcategoria) throws ControladorException;*/

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa as Anormalidades de Leitura e suas quantidades
	 * 
	 * @author Rafael Corrêa     - Hugo Leonardo
	 * @date 13/04/2007			 - 18/03/2010
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarAnormalidadesRelatorioComparativoLeiturasEAnormalidades(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal,
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista, 
			Integer idRotaInicial, Integer idRotaFinal, 
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras)
			throws ControladorException;

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa os dados do relatório do comparativo de leituras e anormalidades
	 * 
	 * @author Rafael Corrêa      - Hugo Leonardo      - Magno Gouveia	
	 * @date 13/04/2007			  - 18/03/2010		   - 21/03/2011
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosRelatorioComparativoLeiturasEAnormalidades(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal,
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista, 
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras)
			throws ControladorException;

	public Object[] pesquisarDadosHidrometroTipoLigacaoAgua(Imovel imovel)
			throws ControladorException;

	/**
	 * [UC0100] Informar Leitura de Fiscalização
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 19/05/2007
	 * 
	 * @return
	 * @throws ControladorException
	 */

	public void informarLeituraFiscalizacao(Usuario usuarioLogado,
			LeituraFiscalizacao leituraFiscalizacao)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Anormalidade de Leitura
	 * 
	 * [UC0000 - Inserir Anormalidade Leitura
	 * 
	 * @author Thiago Tenório
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param leituraAnormalidade
	 *            leituraAnormalidade
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirAnormalidadeLeitura(
			LeituraAnormalidade leituraAnormalidade, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Anormalidade de Leitura.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarAnormalidadeLeitura(
			LeituraAnormalidade leituraAnormalidade)
			throws ControladorException;

	// valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativel(String anoMesReferencia,
			String anoMesAtual);

	// valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativelInferior(
			String anoMesReferencia, String anoMesAnterior);

	/**
	 * relatório de regitro atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 07/06/2007
	 * 
	 * @param
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public AnaliseConsumoRelatorioOSHelper obterDadosAnaliseConsumo(
			Integer idImovel) throws ControladorException;

	/**
	 * [UC0613] - Registrar Leituras e Anormalidades
	 * 
	 * Pesquisa os imóveis com faixa falsa
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2007
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelFaixaFalsa(Integer anoMesReferencia)
			throws ControladorException;

	/**
	 * [UC0613] - Registrar Leituras e Anormalidades
	 * 
	 * Retorna a quantidade de imóveis com faixa falsa
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2007
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelFaixaFalsaCount(Integer anoMesReferencia)
			throws ControladorException;

	/**
	 * relatório de regitro atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 08/06/2007
	 * 
	 * @param
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public HidrometroRelatorioOSHelper obterDadosHidrometro(Integer idImovel)
			throws ControladorException;

	/**
	 * Insere a marca de um hidrômetro
	 * 
	 * @param hidrometroMarca
	 *            Marca de hidrometro a ser inserida
	 * @return código da marca que foi inserida
	 * @throws ControladorException
	 */
	public Integer inserirHidrometroMarca(HidrometroMarca hidrometroMarca,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Capacidade de hidrometro
	 * 
	 * [UC0000 - Inserir Capacidade Hidrometro
	 * 
	 * @author Thiago Tenório
	 * @date 26/062007
	 * 
	 * @param hidrometroCapacidade
	 */
	public Integer inserirCapacidadeHidrometro(
			HidrometroCapacidade hidrometroCapacidade, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 * 
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarCapacidadeHidrometro(
			HidrometroCapacidade hidrometroCapacidade)
			throws ControladorException;

	/**
	 * relatório de extrato de debito
	 * 
	 * @author Vivianne Sousa
	 * @date 17/07/2007
	 * 
	 * @param
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public String obterRotaESequencialRotaDoImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0595] Gerar Histórico de Medicao
	 * 
	 * @param medicaoTipo
	 *            Tipo de medição
	 * @param imovel
	 *            Imovel a ter a medicao gerada
	 * @param faturamentoGrupo
	 *            Grupo de faturamento
	 * @return
	 * @throws ControladorException
	 */
	public MedicaoHistorico gerarHistoricoMedicao(MedicaoTipo medicaoTipo,
			Imovel imovel, FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro)
			throws ControladorException;

	/**
	 * [UC0623] Gerar Resumo de Metas CAERN
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Integer pesquisarConsumoFaturado(Integer idImovel,
			Integer tipoLigacao, Integer idConsumoTipoMediaImovel,
			Integer idConsumoTipoMediaHidrometro, Integer amArrecadacao)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir um Leiturista
	 * 
	 * [UC0588 - Inserir Leiturista
	 * 
	 * @author Thiago Tenório
	 * @date 22/07/2007
	 * 
	 * @param leiturista
	 *            leiturista
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirLeiturista(Leiturista leiturista,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0083] Gerar Dados para Leitura [SB0004] Gerar Movimento do Roteiro
	 * Empresa
	 * 
	 * @author Rodrigo Silveira
	 * @date 25/07/2007
	 * 
	 * @param colecaoRota
	 * @param anoMesCorrente
	 * @param idGrupoFaturamento
	 * @param idUnidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarDadosPorLeituraCelularMobile(Collection colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamento,
			Integer idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Permite gerar o arquivo texto do roteiro empresa para o leiturista.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 * 
	 * @author Pedro Alexandre, Pedro Alexandre
	 * @date 02/08/2007, 15/10/2007
	 * 
	 * @param colecaoRoteirosEmpresa
	 * @param anoMesFaturamento
	 * @param faturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @param colecaoRotas
	 * @throws ControladorException
	 */
	public void gerarArquivoTextoParaLeiturista(
			Collection colecaoRoteirosEmpresa, Collection colecaoRotas,
			Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Pesquisa as roteiro empresas de acordo com os parâmetros informado pelo
	 * usuário
	 * 
	 * [UC0370] - Filtrar Roteiro Empresa
	 * 
	 * @author Thiago Tenório
	 * @date 29/08/2007
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @param numeroPagina
	 * @return Collection
	 */
	public Collection pesquisarRoteiroEmpresa(String idEmpresa,
			String idLocalidade, String codigoSetorComercial,
			String idLeiturista, String indicadorUso, Integer numeroPagina)
			throws ControladorException;

	/**
	 * Verifica a quantidade de registros retornados da pesquisa de roteiro
	 * empresa
	 * 
	 * [UC0370] - Filtrar Roteiro Empresa
	 * 
	 * @author Thiago Tenório
	 * @date 29/08/2007
	 * 
	 * @param empresa
	 * @param idLocalidade
	 * @param idLeiturista
	 * @param idSetorComercial
	 * @param indicadorUso
	 * @param numeroPagina
	 */
	public int pesquisarRoteiroEmpresaCount(String idEmpresa,
			String idLocalidade, String codigoSetorComercial,
			String idLeiturista, String indicadorUso)
			throws ControladorException;

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLocalProtecaoTipoLigacaoAgua(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLocalProtecaoTipoTipoPoco(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0583] Inserir Roteiro Empresa
	 * 
	 * Insere um objeto do tipo roteiro empresa no BD
	 * 
	 * @author Francisco Nascimento
	 * @param roteiroEmpresa
	 * @param idLocalidade
	 * @param collectionRoteiroEmpresaAcaoCriterio
	 * @return idRota
	 * @throws ControladorException
	 */
	public Integer inserirRoteiroEmpresa(RoteiroEmpresa roteiroEmpresa,
			String[] quadras, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC00082] Registrar Leituras e Anormalidades
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 29/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<MedicaoHistorico> recuperarMedicoesHistorico(
			Integer idGrupoFaturamento) throws ControladorException;

	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * [SB0001] Baixar Arquivo Texto para o Leiturista.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param imei
	 * @return
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoParaLeitura(long imei,Integer idServicoTipoCelular)
			throws ControladorException;

	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ControladorException
	 */
	@Deprecated
	public void atualizarArquivoTextoEnviado(long imei, int situacaoAnterior,
			int situacaoNova) throws ControladorException;
	
	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param idRota
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoEnviadoPorRota(Integer idRota, int situacaoAnterior,
			int situacaoNova) throws ControladorException;

	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * [SB0002] Atualizar o movimento roteiro empresa.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public void atualizarRoteiro(Collection<DadosMovimentacao> dados, boolean isCelular)
			throws ControladorException ;

	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 06/09/2007
	 * 
	 * @param idImovel
	 * @param anoMes
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoMedioEmConsumoHistorico(Integer idImovel,
			Integer idLigacaoTipo) throws ControladorException;

	/**
	 * Pesquisa os roteiros empresas pelo grupo de faturamento
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 * 
	 * @author Pedro Alexandre
	 * @date 13/09/2007
	 * 
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarRoteiroEmpresaPorGrupoFaturamento(
			Integer idFaturamentoGrupo) throws ControladorException;

	/**
	 * Atualizar um roteiro empresa e as quadras associadas
	 * 
	 * @author Francisco do Nascimento
	 * @date 20/09/07
	 * 
	 * @param roteiroEmpresa
	 * @param idQuadras
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void atualizarRoteiroEmpresa(RoteiroEmpresa roteiroEmpresa,
			String[] idQuadras, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Remover roteiros empresa
	 * 
	 * @author Francisco do Nascimento
	 * @date 20/09/07
	 * @param ids
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerRoteiroEmpresa(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Metódo responsável por Liberar um Arquivo Texto de Leitura
	 * 
	 * [UC0000 - Inserir Anormalidade Leitura
	 * 
	 * @author Thiago Tenório
	 * @date 18/09/2007
	 * 
	 * @param liberarArquivoTextoLeitura
	 *            liberarArquivoTextoLeitura
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer liberarArquivoTextoLeitura(
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Leiturista.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarLeiturista(Leiturista leiturista)
			throws ControladorException;

	/**
	 * Pesquisa as rotas pelo grupo de faturamento
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarRotaPorGrupoFaturamento(Integer idFaturamentoGrupo) throws ControladorException ;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloRotas(String codigoSetorComercial, 
			String rotaInicial, String rotaFinal, String idLocalidade, String idCobrancaAcao) 
		throws ControladorException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloSetor(String codigoSetorComercialInicial, 
			String codigoSetorComercialFinal, String idLocalidade, String idCobrancaAcao) throws ControladorException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloLocalidade(String idLocalidadeInicial, 
			String idLocalidadeFinal, String idCobrancaAcao) throws ControladorException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloGerencia(String idGerenciaRegional, String idCobrancaAcao) throws ControladorException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloGrupo(String idGrupoCobranca, String idCobrancaAcao) throws ControladorException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio, String idCobrancaAcao) throws ControladorException;

	/**
	 * pesquisa o consumo historico passando o imovel e o anomes referencia e o
	 * consumo anormalidade correspondente ao faturameto antecipado.
	 * 
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 08/11/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoHistoricoAntecipado(Integer idImovel,
			Integer anoMes) throws ControladorException;

	
	/**
	 * 
	 * Relatório Analise de Consumo
	 * Flávio Leonardo 
	 * 26/12/2007
	 * 
	 * @param idImovel
	 * @param anomes
	 * @return
	 * @throws ErroRepositorioException
	 */
	 
	public Collection pesquisarLeiturasImovel(String idImovel, String anoMes)
			throws ControladorException;
	
	/**
	 * Relatório Manter Hidrometro
	 * 
	 * Flávio Leonardo
	 * 
	 * pesquisa o id do imovel do hidrometro instalado
	 * @throws ErroRepositorioException 
	 */
	public Integer pesquisarImovelPeloHidrometro(Integer hidrometroId) throws ControladorException;
	
	/**
	 * Método que atualiza as leituras e Anormalidades do Celular caso não tenha sido iniciado
	 * pelo operador do Computador.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param dados
	 * @throws ControladorException
	 */
	public byte[] atualizarLeituraAnormalidadeCelularCasoOperador(
			Vector<DadosMovimentacao> dados, Usuario usuario, String nomeArquivo, Leiturista l) throws ControladorException ;
	
	/**
	 * 
	 * Enviar Email em Caso de Problemas no Registrar ou Consistir.
	 * 
	 * @param nomeArquivo
	 * @param arquivo
	 * @throws ControladorException
	 */
	public void enviarEmailProblemasRegistrarConsistir(String nomeArquivo, byte[] arquivo) throws ControladorException;
	

	/**
	 * 
	 * Buscar Empresa pela Matrícula do Imóvel.
	 * 
	 * @param imovel
	 * @return
	 * @throws ControladorException
	 */
	public Empresa buscarEmpresaPorMatriculaImovel(Integer imovel) throws ControladorException;
	
	/**
	 * 
	 * Método que atualiza as leituras e Anormalidades do Celular caso não tenha sido iniciado
	 * pelo Sistema.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param dados
	 * @throws ControladorException
	 */
	public void atualizarLeituraAnormalidadeCelularCasoSistema(Vector<DadosMovimentacao> dados) throws SendFailedException,ControladorException;
	

	/**
	 * [UC0543] Associar Conjunto de Rotas a Critério de Cobrança
	 * 
	 * [FS0003] - Verificar existência da localidade
	 * [FS0004] - Verificar existência do setor
	 * 
	 * Verifica se a localidade inicial é maior que a localidade final
	 * Verifica se o setor inicial é maior que o setor final
	 * 
	 * @author Raphael Rossiter
	 * @date 24/01/2008
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public void validarParametrosParaSelecaoDeRotas(AssociarConjuntoRotasCriterioCobrancaHelper parametros)
		throws ControladorException ;
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Critério de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @date 24/01/2008
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public String[] pesquisarQuantidadeRotas(AssociarConjuntoRotasCriterioCobrancaHelper parametros)
		throws ControladorException ;
	
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Critério de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @date 24/01/2008
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public Collection pesquisarRotasParaAssociacaoCriterioCobranca(AssociarConjuntoRotasCriterioCobrancaHelper parametros)
		throws ControladorException ;
	
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Critério de Cobrança
	 * 
	 * @author Raphael Rossiter, Anderson Italo
	 * @date 24/01/2008, 02/06/2009
	 * Adicionado registro de transacao CRC1946
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public void desassociarConjuntoRotasCriterioCobranca(AssociarConjuntoRotasCriterioCobrancaHelper parametros, 
			Usuario usuarioLogado, RotaAcaoCriterioHelper rotaAcaoCriterioHelper)
		throws ControladorException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 29/01/2008
	 * 
	 * @param id
	 * @param situacaoNova
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoEnviado(Integer id, Integer situacaoNova) throws ControladorException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 29/01/2008
	 * 
	 * @param ids
	 * @param situacaoNova
	 * @param motivoFinalizacao 
	 * @throws ControladorException
	 */
	public void atualizarListaArquivoTexto(Vector<Integer> ids, Integer situacaoNova,Integer idServicoTipoCelular,
			boolean temPermissaoFinalizarArquivo, String motivoFinalizacao) throws ControladorException;

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * sem informar o ano/mes para o caso em que o Imovel nao possui
	 * Hidrometro (Sem Medicao).
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosConsumo(Integer idImovel, boolean ligacaoAgua) throws ControladorException;
	
	/**
	 * 
	 * Busca uma Lista de Imoveis por Rota
	 * 
	 * @param idRota
	 * @return
	 * @throws ControladorException
	 */
	public Collection<DadosMovimentacao> buscarImoveisPorRota(Rota idRota,
			Integer anoMesReferencia, boolean manter) throws ControladorException;

	/**
	 * 
	 * Método que atualiza as leituras e Anormalidades sem utilizar o Celular.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param dados
	 * @throws ControladorException
	 */
	public void atualizarLeituraAnormalidadeSemCelular(Vector<DadosMovimentacao> dados) throws ControladorException;
	
	/**
	 * Atualiza a Relação de Rota X Leiturista
	 * 
	 * @author Thiago Nascimento
	 * @param leiturista
	 * @param rotas
	 * @throws ControladorException
	 */
	public void atualizarRelacaoRotaLeiturista(Integer leiturista, Integer[] rotas) throws ControladorException;

	
	/**
	 * [UC0762] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
	 * @author Tiago Moreno
	 * @date 10/04/2008
	 * 
	 * @param ArquivoTextoLigacoesHidrometroHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */
	
	public void gerarArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper arquivoTextoLigacoesHidrometroHelper) throws ControladorException;
	
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 *
	 * @param imovel
	 * @param anoMesReferenciaInicio
	 * @param anoMesReferenciaFim
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection obterUltimosConsumosImovel(Imovel imovel) throws ControladorException ;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 2 (DOIS)
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 22/01/2007
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroConsumoMedioImovel(Integer idImovel, Integer anoMesReferencia) 
		throws ControladorException ;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 *
	 * @param consumoHistorico
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer obterLeituraAnormalidadeFaturamentoMedicaoHistorico(ConsumoHistorico consumoHistorico) 
		throws ControladorException ;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * [SB0006] - Obter dados dos tipos de medição
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection obterDadosTiposMedicao(Imovel imovel, Integer anoMesReferencia) 
		throws ControladorException ;
	
	/**
	 * [UC0086] - Calcular Faixa de Leitura Esperada
	 */
	public int[] calcularFaixaLeituraEsperada(int media,
			MedicaoHistorico medicaoHistorico, Hidrometro hidrometro,
			Integer leituraAnteriorPesquisada) ;

	
	/**
	 * 
	 * O ultimo sequencia para o id do leiturista passado como parametro 
	 * 
	 * @author Thiago Nascimento
	 * @param leit
	 * @return
	 * @throws ControladorException
	 */
	public Integer numeroSequenciaUltimaRota(Integer leit) throws ControladorException;
	
	/**
	 * 
	 * Retorno o valor máximo do Id do Leiturista.
	 * 
	 * @author Thiago Nascimento
	 * @return
	 * @throws ControladorException
	 */
	public Integer maximoIdLeiturista() throws ControladorException;
	
	/**
	 * 
	 * Retorna a quantidade de Leitura feitas na Rota para o AnoMes de referencia.
	 * 
	 * @author Thiago Nascimento
	 * @return
	 * @throws ControladorException 
	 * @throws ErroRepositorioException
	 */
	public Integer quantidadeLeiturasRealizada(Integer rota, Integer anoMes,Integer idServicoTipoCelular) throws ControladorException;
	
	/**
	 * 
	 * [UC0781] - Informar Consumo por Área
	 * 
	 * Inserir/Atualizar consumoMinimoArea
	 * 
	 * @author Rômulo Aurélio
	 * @date 26/05/2008
	 * 
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer informarConsumoMinimoArea(
			Collection colecaoConsumoMinimoArea,Collection colecaoConsumoMinimoAreaBase,Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * Atualiza o FaturamentoAtividadeCronograma do grupo no anoMes especificado para o
	 * Registrar, Consistir e Efetuar Leitura. 
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 23/05/2008
	 * 
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ControladorException
	 */
	public void atualizarFaturamentoAtividadeCronogramaRegistrarConsistirEfetuarLeitura(Integer grupo,Date dataRealizacao) throws ControladorException;
	
	/**
	 * Adaptação do batch que efetua o Rateio do consumo para todos os imóveis de uma rota
	 * que sejam imóvel condominio
	 * 
	 * [UC0103] - Efetuar Rateio de Consumo
	 * 
	 * @author Thiago Nascimento
	 * @date 27/03/2008
	 * 
	 * @param rota
	 * @param anoMesFaturamento
	 * @throws ControladorException
	 */
//	public void efetuarRateioDeConsumoPorRota(Rota rota,
//			Integer anoMesFaturamento)throws ControladorException;

	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 *
	 * Pesquisa os dados necessérios para a geração do relatório
	 *
	 * @author Rafael Corrêa
	 * @date 03/06/2008
	 *
	 * @param colecaoImoveis
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AvisoAnormalidadeRelatorioHelper> pesquisarAvisoAnormalidadeRelatorio(
			Collection colecaoImoveis, Integer anoMes) throws ControladorException;
	
	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 *
	 * Pesquisa os dados necessérios para a geração do relatório
	 *
	 * @author Rafael Corrêa
	 * @date 03/06/2008
	 *
	 * @param colecaoImoveis
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<AvisoAnormalidadeRelatorioHelper> pesquisarAvisoAnormalidadeRelatorio(
			FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes) throws ControladorException;
	
	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa os dados necessérios para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 28/06/2008
	 * 
	 * @param gerarDadosLeituraHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioGerarDadosLeituraBean> pesquisarDadosParaLeituraRelatorio(
			GerarDadosLeituraHelper gerarDadosLeituraHelper)
			throws ControladorException;
	
	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa a quantidade de registros do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 28/06/2008
	 * 
	 * @param gerarDadosLeituraHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosParaLeituraRelatorioCount(GerarDadosLeituraHelper gerarDadosLeituraHelper) throws ControladorException;
	
	/**
	 * 
	 * [UC0781] - Informar Consumo por Área
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/05/2008
	 * 
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(
			int anoMesReferenciaInformado) throws ControladorException;
	
	/**
	 * Retorno a quantidade leituras que ainda não foram registradas
	 * 
	 * @data 03/06/2008
	 * @param grupo
	 * @return
	 * @throws ControladorException
	 */
	public Integer consultarQuantidadeLeiturasNaoResgistradas( FaturamentoGrupo grupo) throws ControladorException;
	
	/**
	 * Retorna as leituras que ainda não foram registradas
	 * 
	 * @data 03/06/2008
	 * @param anoMes
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void processarLeiturasNaoResgistradas(FaturamentoGrupo grupo)
		throws ControladorException;
	
	
	/**
	 * [UC0083]-Gerar Dados para Leitura
	 * 
	 * Author: Rômulo Aurélio
	 * Data: 17/06/2008
	 * 
	 * 
	 * 
	 * @param colecaoRota
	 * @param anoMesCorrente
	 * @param idGrupoFaturamentoRota
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	
	public void gerarDadosPorLeituraParaInserir(Rota rota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota, SistemaParametro sistemaParametro,
			Integer idFuncionalidadeIniciada) throws ControladorException;
	
	public void removerMovimentoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota) throws ControladorException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * @author Rômulo Aurélio
	 * @throws ControladorException 
	 * @date 07/07/2008
	 */
	
	public Collection pesquisarImoveisPorRota(Rota rota, 
			SistemaParametro sistemaParametro) throws ControladorException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @date 07/07/2008
	 * @author Rômulo Aurélio
	 * @param imoveisParaSerGerados
	 * @param anoMesCorrente
	 * @param sistemaParametro
	 * @param idLeituraTipo
	 * @throws ControladorException
	 */

	public void inserirDadosImoveisMovimentoRoteiroEmpresa(
			Collection imoveisParaSerGerados, int anoMesCorrente,
			SistemaParametro sistemaParametro, Integer idLeituraTipo)
			throws ControladorException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/07/2008
	 */
	public Collection retornaImoveisPorRota(Rota rota,
			SistemaParametro sistemaParametro) throws ControladorException;
	
	/**
	 * Author: Rômulo Aurélio [UC0083] - Gerar Dados para Leitura
	 * 
	 * @param objetosImoveis
	 * @return
	 */
	public Collection verificarImoveisParaSerGerados(Collection objetosImoveis)throws ControladorException;
	
	/**
	 * [UC0800] - Obter Consumo Não Medido
	 *
	 * Obter o consumo mínimo associado à faixa de área do imóvel e a categoria ou subcategoria informada
	 *
	 * @author Raphael Rossiter
	 * @date 22/05/2008
	 *
	 * @param areaConstruida
	 * @param anoMesReferencia
	 * @param subcategoria
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoMinimoArea(BigDecimal areaConstruida, Integer anoMesReferencia, 
			Subcategoria subcategoria, Categoria categoria) throws ControladorException;

	/**
	 * Retorno a quantidade leituras enviada.
	 * 
	 * @autor Yara T. Souza
	 * 
	 * @data 26/08/2008
	 * @param grupo
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisarLeiturasEnviadasRelatorioCount(Integer anoMesReferencia, Integer idEmpresa, Integer idLocalidade,Integer codigoSetorComercial,
			Integer idGrupoFaturamento, Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista)throws ControladorException;
	
	

	/**
	 * Gerar o vetor para registrar as leituras e anormalidades
	 * 
	 * @data 28/07/2008
	 * @return
	 * @throws ControladorException
	 */
	public Vector<DadosMovimentacao> gerarVetorDadosParaLeitura(BufferedReader buffer) throws ControladorException;
	
	/**
	 * 
	 * Método que atualiza as leituras da telemetria
	 * 
	 * 	 * 
	 * @author Thiago Nascimento
	 * @date 20/08/2008
	 * 
	 * @param dados
	 * @throws ControladorException
	 */
	public void atualizarLeituraTelemetria(Vector<DadosMovimentacao> dados) throws ControladorException;
	
	/**
	 * [UC0629] Retornar Arquivo Txt Leitura
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 06/10/2008
	 * 
	 */
	public Collection pesquisarArquivosTextoRoteiroEmpresaParaArquivoZip(
			String[] ids) throws ControladorException;

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Object[]
	 * @throws Controlador Exception
	 */

	public Object[] obterDadosHidrometroAtualizacaoCadastral(Integer idImovel)
		throws ControladorException;

    /**
     * Informar Subdivisões de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
	public void informarSubdivisoesDeRota(Integer idRotaOriginal, 
			InformarSubdivisoesDeRotaHelper subdivisaoRotaOriginal, 
			Collection<InformarSubdivisoesDeRotaHelper> subdivisoes) throws ControladorException;
	
	/**
	 * [UC0???] Gerar Relatorio Rotas Online por Empresa
	 * 
	 * @see ControladorMicromedicao#pesquisarRelatorioRotasOnlinePorEmpresa(PesquisarRelatorioRotasOnlinePorEmpresaHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 28/10/2008
	 */
	public Collection<RelatorioRotasOnlinePorEmpresaBean> pesquisarRelatorioRotasOnlinePorEmpresa(
			PesquisarRelatorioRotasOnlinePorEmpresaHelper helper) throws ControladorException;
	
	/**
	 * [UC0???] Gerar Relatorio Rotas Online por Empresa
	 * 
	 * @author Victor Cisneiros
	 * @date 28/10/2008
	 */
	public Integer pesquisarRelatorioRotasOnlinePorEmpresaCount(
			PesquisarRelatorioRotasOnlinePorEmpresaHelper helper) throws ControladorException;
	
	
	/**
	 * Emitir Contas Caema
	 * 
	 * @author Tiago Moreno
	 * @date 06/11/2008
	 * 
	 * @param
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public Object[] obterRotaESequencialRotaDoImovelSeparados(Integer idImovel)
		throws ControladorException;
    
    /**
     * Verifica se o processo de dados diários da arrecadação está sendo executado
     *
     * @author bruno
     * @date 12/12/2008
     *
     * @throws ControladorException
     */
    public void verificarBatchDadosDiariosArracadacaoRodando() 
        throws ControladorException;
    
	public Collection pesquisarDadosRelatorioAnaliseImovelCorporativoGrande(
			Integer idGerenciaRegional,Integer idUnidadeNegocio,  
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal, 
			Integer referencia, Integer idImovelPerfil, Integer selecionar)
		throws ControladorException;
    
    /**
     * 
     * [UC0889] - Alterar datas das leituras
     *
     * Pesquisamos todos os dados necessários para a alteração das datas
     *
     * @author bruno
     * @date 26/02/2009
     *
     * @param idGrupoFaturamento
     * @return
     */
    public Collection<AlterarDatasLeiturasHelper> 
        pesquisarDadosAlterarGruposFaturamento( Integer idGrupoFaturamento ) 
        throws ControladorException;
    
    /**
     * 
     * [UC0889] - Alterar datas das leituras
     *
     * Alteramos todos as datas informadas
     *
     * @author bruno
     * @date 26/02/2009
     *
     * @param idGrupoFaturamento
     * @return
     */
    public void alterarDatasLeituras( 
            Collection<AlterarDatasLeiturasHelper> colHelper,
            Integer idGrupo) throws ControladorException;   
    
   /** < <Descrição do método>>
	 * 
	 * @param fixo
	 *            Descrição do parâmetro
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Integer pesquisarNumeroHidrometroSituacaoInstaladoPaginacaoCount(FiltrarHidrometroHelper helper)
			throws ControladorException;
	
	  /**
	 * < <Descrição do método>>
	 * 
	 * @param fixo
	 *            Descrição do parâmetro
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarNumeroHidrometroSituacaoInstaladoPaginacao(FiltrarHidrometroHelper helper,
			Integer numeroPagina)
			throws ControladorException;
	
	  /**
	 * < <Descrição do método>>
	 * 
	 * @param fixo
	 *            Descrição do parâmetro
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarNumeroHidrometroSituacaoInstaladoRelatorio(FiltrarHidrometroHelper helper)
			throws ControladorException;
	
	
            
    
    /**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Collection pesquisarConsumoFaturadoQuantidadeMesesPorReferencia(
			Integer idImovel, Integer tipoMedicao, short qtdMeses)
			throws ControladorException;
	
	
	/**
	 * [UC00083] Gerar Dados para Leitura [FS0006]-Verificar imóveis processados
	 * na competência
	 * 
	 * @date 13/10/2008
	 * @author Rômulo Aurélio
	 * 
	 */

	public Collection verificarImoveisProcessadosEmMovimentoRoteiroEmpresa(
			Collection colecaoImoveisAGerar, Integer idFaturamentoGrupo,
			Integer anoMes) throws ControladorException ;
	
	
	/**
	 * Obter consumo mínimo da ligação de agua via conta
	 * 
	 * @author Bruno Barros
	 * @date 15/05/2009
	 * 
	 * @param idConta
	 * @return valorMinimoContaAgua
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoMininoAgua( Integer idConta ) throws ControladorException;
	
	/**
	 * Obter consumo mínimo da ligação de esgoto via conta
	 * 
	 * @author Bruno Barros
	 * @date 15/05/2009
	 * 
	 * @param idConta
	 * @return valorMinimoConsumoEsgoto
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoMininoEsgoto( Integer idConta ) throws ControladorException;  	
	
	/**
 	 * Inserir e Atualizar Movimentações de Hidrometros 
 	 * 
 	 * @author Arthur Carvalho
 	 * @date 17/06/2009
 	 * 
 	 */
	public void inserirAtualizarMovimentacaoHidrometroIdsBatch(
			Collection colecaoHidrometro, String data, String hora,
			String idLocalArmazenagemDestino, String idMotivoMovimentacao,
			String parecer, Usuario usuario, int idFuncionalidadeIniciada ) throws ControladorException;
	
	/**
	 * 
	 * Método que retorna os consumos de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado período do faturamento. 
	 * 
	 * @author Rafael Corrêa
	 * @date 27/07/2009
	 * 
	 * @param idImovel
	 * @param anoMesInicial
	 * @param anoMesFinal
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Object[]> obterConsumosAnterioresAnormalidadeDoImovel(Integer idImovel,
			Integer anoMesInicial, Integer anoMesFinal, Integer idLigacaoTipo) throws ControladorException;
	
	/**
	 * Description of the Method
	 * 
	 * @param hidrometros
	 *            Description of the Parameter
	 * @param hidrometroAtualizado
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarConjuntoHidrometroBatch(String fixo,String inicialFixo,String finalFixo,
			Hidrometro hidrometroAtualizado, Usuario usuarioLogado, int idFuncionalidadeIniciada,int numero)
			throws ControladorException;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Sávio Luiz
	 * @date 01/07/2009
	 *
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMaiorDataLeituraImoveis(Integer idImovel, Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * [UC0082] Registrar Leituras e Anormalidades 
	 *
	 * @author Raphael Rossiter
	 * @date 03/09/2009
	 *
	 * @param idFaturamentoGrupo
	 * @param fileItem
	 * @return Collection<MedicaoHistorico>
	 * @throws ControladorException
	 */
	public Collection<MedicaoHistorico> processarArquivoTextoParaRegistrarLeiturasAnormalidades(
			Integer idFaturamentoGrupo, FileItem fileItem) throws ControladorException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * para uma lista de Imóveis
	 */
	public void consistirLeiturasCalcularConsumosImoveis(
			FaturamentoGrupo faturamentoGrupo,
			 Collection<Integer> imoveis)
			throws ControladorException;	
	
	/**
	 * [UC0811] Requisições do Dispositivo Móvel da Conta Pré-faturada.
	 * 
	 * SB0001 ? Baixar Arquivo Texto para o Leiturista
	 * 
	 * @author Bruno Barros
	 * @date 24/09/2009
	 * 
	 * @param imei
	 * @return
	 * @throws ControladorException
	 */
	public Object[] baixarArquivoTextoParaLeituristaImpressaoSimulanea(long imei,Integer idServicoTipoCelular)
		throws ControladorException;	
	
	/**
	 * UC0957 - Relatorio de Acompanhamento de Leiturista
	 * 
	 * Pesquisa as rotas que possuem validos para geração do relatorio de
	 * acompanhamento de leiturista.
	 * 
	 * @author Hugo Amorim
	 * @data 16/10/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection selecionarRotasRelatorioAcompanhamentoLeiturista(
			Integer anoMesReferencia,
			Integer rotas[],
			String idEmpresa,
			String idLeiturista)
			throws ControladorException;
	
	/**
	 * UC0957 - Relatorio de Acompanhamento de Leiturista
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection<RelatorioAcompanhamentoLeituristaHelper> pesquisarRelatorioAcompanhamentoLeiturista(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ControladorException;
	
	/**
	 * UC0957 - Relatorio de Acompanhamento de Leiturista
	 *  
	 * @author Hugo Amorim
	 * @data 21/10/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarLeituristasDasRotas(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ControladorException;
	
	/**
	 *[UC0965] - Relatorio de Anormalidade de Leitura por Periodo
	 *
	 *@since 03/11/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioAnormalidadeLeituraPeriodoBean> pesquisarRelatorioAnormalidadeLeituraPeriodo(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro) throws ControladorException;

	/**
	 *[UC0965] - Relatorio de Anormalidade de Leitura por Periodo
	 *
	 *@since 03/11/2009
	 *@author Marlon Patrick
	 */
	public  Collection<Object[]> pesquisarTotalRegistrosRelatorioAnormalidadeLeituraPeriodo(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro)throws ControladorException;
	
	/**
	 * Método utlizado para receber uma coleção e retornar um objeto do tipo
	 * faturamento situação tipo
	 */

	public FaturamentoSituacaoTipo obterFaturamentoSituacaoTipo(
			Collection colecaoFaturamentoSituacaoTipo);
	
	/**
	 *[UC0745] - Gerar Arquivo Texto Faturamento
	 *
	 *@since 25/11/2009
	 *@author Sávio Luiz
	 */
	public  Collection pesquisarFaturamentoSituacaoTipo(FaturamentoSituacaoTipo faturamentoSituacaoTipo)throws ControladorException;

	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa a quantidade de dados do relatório do comparativo de leituras e anormalidades
	 * 
	 * @author Arthur Carvalho 	 - Hugo Leonardo 	 - Magno Gouveia	
	 * @date 16/11/2009			 - 22/03/2010		 - 21/06/2011
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Integer pesquisarDadosRelatorioComparativoLeiturasEAnormalidadesCount(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal,
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista, 
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras)
			throws ControladorException;
	
	/**
	 * 
	 * Verifica se houve todos os processamentos do um determinado grupo e
	 * ano-Mês de Referencia e inserir no cronograma de atividades.
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular 
	 * 
	 * @author Sávio Luiz
	 * @date 01/12/2009
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataRealizacaoGronogramaPreFaturamento(Integer grupo,
			Integer anoMesReferencia,Date dataRealizacao) throws ControladorException;
	
	/**
	 * Método que efetua o Rateio do consumo, recebendo apenas um
	 * imovel, sendo esse o imovel condominio. Esse metodo foi 
	 * criado para atender a impressão simultânea de conta
	 * 
	 * [UC0103] - Efetuar Rateio de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 29/12/2009
	 * 
	 * @param idImovelCondominio - Id do imovel condominio a ser processado
	 * @param anoMesFaturamento - Ano/Mes de faturamento para ser processado
	 */
	public void efetuarRateioDeConsumo( Integer idImovelCondominio , Integer anoMesFaturamento )
		throws ControladorException;	
	/**
	 * [UC0101] Consistir Leituras e Calcular Consumos
	 * 
	 *  3.1 do [SB0014] - Verificar Estouro de Consumo
	 *  2.1 do [SB0015] - Verificar Alto Consumo
	 *  2.1 do [SB0016] - Verificar Baixo Consumo
	 *  
	 * @author Vivianne Sousa
	 * @date 17/12/2009
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public ConsumoAnormalidadeAcao verificaAcaoASerTomada(Integer idConsumoAnormalidade, 
			Integer idCategoria, Integer idPerfilImovel) throws ControladorException;
	
	public void removerMovimentoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota,Rota rota) throws ControladorException;
	
	/**
	 * 
	 * Buscar Rota a partir da Matrícula de um Imóvel e anoMesFaturamentoGrupo.
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 03/02/2010
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public Rota buscarRotaPorMatriculaImovel(Integer imovel, Integer anoMesFaturamentoGrupo)throws ControladorException;
	/**
	 * Pesquisar ultima anormalidade de leitura do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 19/02/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarAnormalidadesImovel(
			Integer idImovel,String indicadorAguaEsgoto)
			throws ControladorException;
	
	/**
	 * Inseri um nova Rota a partir da Rota pai. Utilizado na alteracao de
	 * subdivisao de Rotas.
	 * 
	 * @author Ivan Sérgio
	 * @date 23/02/2010
	 * 
	 * @param idRotaOriginal
	 * @param codigoRota
	 * @param idLeiturista
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirNovaSubdivisoesDeRota(Integer idRotaOriginal, Short codigoRota,
			Integer idLeiturista) throws ControladorException;
	/**
	 * 
	 * Pesquisa numero de hidrometro da ligacao de agua do imovel
	 * 
	 * @date 04/03/2010
	 * @param Id Imovel
	 * @return Numero do Hidrometro
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometro(Integer idImovel) throws ControladorException;
	
	
	/**
	 * 
	 * Métido retorna o consumo tipo - descricao abreviada
	 * 
	 * @author Tiago Moreno
	 * @date 25/02/2010
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ControladorException
	 */
	public String obterConsumoTipoImovel(Integer idImovel,
			Integer anoMes, Integer idLigacaoTipo) throws ControladorException;

	
    /**
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 12/03/2010
	 * 
	 * @param ColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countColetaMedidorEnergia(ColetaMedidorEnergiaHelper helper) 
				throws ControladorException;
	
    /**
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * @author Hugo Leonardo
	 * @date 12/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Collection<ColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<ColetaMedidorEnergiaHelper> pesquisarColetaMedidorEnergia(
			ColetaMedidorEnergiaHelper helper)
		throws ControladorException;
	
    /**
	 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro.
	 * 
	 * @author Hugo Leonardo
	 * @date 30/03/2010
	 * 
	 * @param RelatorioResumoLigacoesCapacidadeHidrometroHelper
	 * 
	 * @return Collection<RelatorioResumoLigacoesCapacidadeHidrometroBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioResumoLigacoesCapacidadeHidrometroBean> pesquisarRelatorioResumoLigacoesCapacidadeHidrometro(
			RelatorioResumoLigacoesCapacidadeHidrometroHelper helper) throws ControladorException;
	
    /**
	 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro.
	 * 
	 * @author Hugo Leonardo
	 * @date 30/03/2010
	 * 
	 * @param RelatorioResumoLigacoesCapacidadeHidrometroHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countRelatorioResumoLigacoesCapacidadeHidrometro(
			RelatorioResumoLigacoesCapacidadeHidrometroHelper helper) throws ControladorException;
	
	/**
	 * 
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * 	[FS0015]  Verificar Imóvel Impressão Simultânea
	 * 
	 * @author Hugo Amorim
	 * @date 08/04/2010
	 */
	public boolean verificarExistenciaArquivoDeImpressao(Integer idImovel,
			Integer tipoMedicao)throws ControladorException;
	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 05/04/2010
	 * 
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoMenorSequencialLeitura(long imei, int situacaoAnterior,
			int situacaoNova,int idServicoTipoCelular) throws ControladorException;
	
	/**
	 * Pesquisa os dados de Medição e Consumo Historico da ligação de Esgoto do imóvel.
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 07/05/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection carregarDadosMedicaoConsumoHistoricoLigacaoEsgoto(Integer idImovel) 
		throws ControladorException;
	
	
	/**
	 * [UC1022] Relatório de Notificação de Débitos para Impressão Simultânea	 
	 * 
	 * @author Daniel Alves
	 * @date 19/05/2010
	 */
	public Collection pesquisarNotificacaoDebitosImpressaoSimultanea(
			RelatorioNotificacaoDebitosImpressaoSimultaneaHelper filtro)throws ControladorException;
	
	/**
	 * Pesquisar Capacidade do Hidrometro
	 * 
	 * @author Arthur Carvalho
	 * @date 06/11/2008
	 * 
	 * @param Numero do Hidrometro
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */							
	public HidrometroCapacidade pesquisarCapacidadeHidrometro( String numeroHidrometro )
		throws ControladorException;
	
	/**
     * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
     *  
     * @author Hugo Leonardo
     * @created 04/06/2010
	 * 
	 * @param idArquivoTextoRoteiroEmpresaDivisao
	 * @param situacaoNova
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoDivisaoEnviado(Integer idArquivoTextoRoteiroEmpresaDivisao,
			int situacaoNova) throws ControladorException;
	
	/**
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
     *  
     * @author Hugo Leonardo
     * @created 04/06/2010
	 * 
	 * @param ids
	 * @param situacaoNova
	 * @throws ControladorException
	 */
	public void atualizarListaArquivoTextoDivisao(Vector<Integer> ids, Integer situacaoNova) 
		throws ControladorException;
    
    public ConsumoHistorico obterConsumoHistorico(Imovel imovel,
            LigacaoTipo ligacaoTipo, int anoMesReferencia)
            throws ControladorException;
    
	/**
	 * 
	 * Obter rota do imóvel através do código da rota
	 * do sequencial e da localidade .
	 * 
	 * @author Breno Santos
	 * @date 13/08/2010
	 * 
	 * @param codRota, sequencial, localidade
	 * @throws ErroRepositorioException
	 * @throws ControladorException 
	 */
	public Integer obterIdRotaPorSetorComercialELocalidade(Integer codRota, Integer setorComercial, Integer localidade)
			throws ControladorException ;
	/**
	 * Pesquisa Dados do Contrato Empresa Servico
	 * [UC1055] - Informar Valor de Item de Serviço Por Contrato
	 * 
	 * @author Hugo Leonardo
	 * @date 30/07/2010
	 * 
	 * @param idEmpresa
	 * 
	 * @return List<InformarItensContratoServicoHelper>
	 */
	public List<InformarItensContratoServicoHelper> obterDadosItensContratoServico(Integer idEmpresa) 
		throws ControladorException;
	
	/**
	 * Remover todos os Itens de Servico do Contrato
	 * [UC1055] - Informar Valor de Item de Serviço Por Contrato
	 * 
	 * @author Hugo Leonardo
	 * @date 03/08/2010
	 * 
	 * @param idContratoEmpresaServico
	 * @return void
	 */
	public void removerItemServicoDoContrato( Integer idContratoEmpresaServico, 
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * Remover todos os Aditivos do Contrato
	 * [UC1055] - Informar Valor de Item de Serviço Por Contrato
	 * 
	 * @author Mariana Victor
	 * @date 25/11/2010
	 * 
	 * @param idContratoEmpresaServico
	 * @return void
	 */
	public void removerAditivosDoContrato(Integer idContratoEmpresaServico)
		throws ControladorException;
	
	/**
	 * [UC1054] - Gerar Relatório Boletim de Medição
	 * 
	 * @author Hugo Leonardo
	 * @date 05/08/2010
	 * 
	 * @param FiltrarRelatorioBoletimMedicaoHelper
	 * @return Collection<RelatorioBoletimMedicaoHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioBoletimMedicao(FiltrarRelatorioBoletimMedicaoHelper helper)
		throws ControladorException;
	
	
	/**
	 * [UC1054] - Gerar Relatório Boletim de Medição
	 * 
	 * @author Hugo Leonardo
	 * @date 06/08/2010
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarEmpresasContratoServico() throws ControladorException;

	
	/**
	 * 
 	 * [UC0629] Consultar Arquivo Texto para Leitura
 	 * 	
 	 * 	[FS0011 - Verificar Leituras];
 	 * 
	 * @author Hugo Amorim
	 * @date 20/08/2010
	 */
	public SituacaoLeituraHelper pesquisarSituacaoLeitura(Integer anoMes,Integer idGrupo,Integer idRota)
			throws ControladorException;

	/**
	 * 
	 * Verifica se uma rota em questão está com
	 * os arquivos de impressão simultanea divididos
	 * 
	 * @autor Bruno Barros.
	 * @date 26/08/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return boolean - A rota está dividida 
	 */
	public boolean isRotaDividida( Integer idRota, Integer anoMesFaturamento) throws ControladorException;
	
	/**
	 * 
	 * Verifica se uma rota em questão está com
	 * os arquivos de impressão simultanea divididos com a 
	 * situação diferente da situação enviada como parâmetro 
	 * 
	 * @autor Sávio Luiz.
	 * @date 30/09/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return boolean - A rota está dividida 
	 */
	public boolean verificarExistenciaArquivosDivididosSituacaoDiferente( Integer idRota, Integer anoMesFaturamento, Integer[] idsSituacaoTransmissao ) 
		throws ControladorException;
	
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura.
	 * 
	 * 	Verifica se todas leituras foram relizadas.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 25/08/2010
	 * 
	 * @param ids
	 * @throws ControladorException
	 */
	public boolean verificarLeiturasRealizadas(Vector<Integer> ids,Integer idServicoTipoCelular) throws ControladorException;
	
	/**
	 * Verifica de imovel é medido ou não-medido
	 * 
	 * 	retorna true se medido
	 * 			false se não-medido
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2010
	 */
	public boolean verificarSituacaoMedicao(Integer idImovel)throws ControladorException;
	
	/**
	 *
	 * [UC0153] Apresentar Dados Para Análise da Medição e Consumo
	 * 
	 * [FS0004] ? Solicitar releitura
	 * 		.Caso o usuário solicite a releitura de determinado imóvel, o sistema identifica o imóvel, 
	 * 		grava os dados (REMO_ID, IMOV_ID, REMO_ICRELEITURA, USUR_ID, REMO_AMREFERENCIA 
	 * 		preenchido com o valor FTGR_AMREFERENCIA) na tabela RELEITURA_MOBILE e envia a seguinte mensagem 
	 * 		para o celular do leiturista responsável pela rota (LEIT_ID que esta na tabela rota em que o imovel 
	 * 		pertence e identificar o celular deste (LEIT_NNIMEI) para envio da mensagem): 
	 * 		?Refazer leitura para o imóvel <IMOV_ID>. Ir para o imóvel??
	 * 		
	 * 		.Caso o imóvel ainda não tenha tido leitura para o ano/mês de referência (se não existir registro na 
	 * 		tabela MOVIMENTO_CONTAPREFATURADA no ano/mês de referencia), o sistema exibirá a seguinte 
	 * 		mensagem: Leitura não realizada para o imóvel em < FTGR_AMREFERENCIA >;
	 * 		
	 * 		.Caso o leiturista já tenha finalizado a 
	 * 		rota (verificar na tabela ARQUIVO_TEXTO_ROTEIRO_EMPRESA se existem 
	 * 		ocorrências para a rota mencionada no ano/mês de referencia e o campo STIL_ID correspondente a 
	 * 		descrição "FINALIZADO" na tabela SITUACAO_TRANSMISSAO_LEITURA) será enviada a seguinte 
	 * 		mensagem para o operador: ?Rota já foi finalizada pelo leiturista? e retorna ao passo que chamou este fluxo.
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2010
	 * 
	 * @param String matricula - Imovel cuja releitura será solicitada
	 * @param Usuario usuarioLogado - O usuário que está solicitando a releitura
	 */	
	public void solicitarReleitura( String matricula, Usuario usuarioLogado ) throws ControladorException;
	
	/**
	 * 
	 * Pesquisa os imóveis para a tela de Filtrar Imoveis para Releitura
	 * 
	 * @author Bruno Barros
	 * @date 14/09/2010
	 * 
	 * @param anoMesReferencia 
	 * @param idGrupoFaturamento
	 * @param idRota
	 * @param idQuadra
	 * @param idSituacaoTrasmissaoLeitura
	 * @return
	 */
	public Collection<AnalisarImoveisReleituraHelper> pesquisarImovelParaReleitura(
			String anoMesReferencia,
			String idGrupoFaturamento,
			String idRota,
			String idQuadra,
			String idSituacaoTrasmissaoLeitura,
			String idEmpresa) throws ControladorException;
	
    /**
    *
    * Verifica se a releitura solicitada já foi realizada
    *
    * @author Bruno Barros
    * @date 13/09/2010
    *
    * @param idImovel Imóvel a ser pesquisado
    * @param anoMesFaturamento Ano mes de faturamento a ser pesquisado
    * @return
    */
   public boolean releituraJaRealizada( String matricula )
       throws ControladorException;	
   
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * [SB0006] - Gerar Dados da Conta 
	 * 
	 * @author Vivianne Sousa
	 * @date 20/09/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public  Object[] obterLeituraAnteriorEAtualFaturamentoMedicaoHistorico(
			Integer idImovel, Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * 
	 * [UC0629] Consultar Arquivo Texto para Leitura
	 * 
	 * @author Bruno Barros
	 * @date 27/09/2010
	 *
	 */
	public Collection<ImovelFaltandoSituacaoLeituraHelper>  pesquisarImoveisFaltandoSituacaoLeitura(
			Integer idRota, Integer anoMesReferencia ) throws ControladorException;	
	
	/**
	 * [UC0932] Monitorar Leituras Transmitidas
	 * 
	 * Pesquisa as leituras que foram trasmitidas.
	 * 
	 * @author Bruno Barros
	 * @date 28/09/2010
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @param indicadorContaImpressa
	 * @param indicadorMedido
	 * @return Colecao com as informações necessarias para o prenchimento da tela.
	 * 
	 */
	public Collection<MonitorarLeituraMobilePopupHelper> pesquisarImoveisMonitorarLeiturasTransmitidas(
		Integer idRota, Integer anoMesReferencia, Short indicadorContaImpressa, Short indicadorMedido ) throws ControladorException;	

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * @author Tiago Nascimento, Rômulo Aurélio
	 * @Data ??/??/??,  27/10/2010
	 *
	 */
	public void alterarLeituristaArquivoLeitura (ArquivoTextoRoteiroEmpresa arq, Integer idLeiturista, Integer idGrupoFaturamento, 
			Integer anoMesReferencia)throws ControladorException;
	
	/**
	 * [UC1004] Processar Leitura via Telemetria 
	 *
	 * @author Raphael Rossiter
	 * @date 27/09/2010
	 *
	 * @param dadosTelemetria
	 * @return TelemetriaLog
	 */
	public TelemetriaLog processarLeituraViaTelemetria(String dadosTelemetria) throws ControladorException;
	
 	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Collection<TelemetriaMovReg> filtrarLeiturasTelemetria(FiltrarLeiturasTelemetriaHelper helper)
		throws ControladorException;
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Integer countFiltrarLeiturasTelemetria(FiltrarLeiturasTelemetriaHelper helper)
		throws ControladorException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean verificarLeiturasTelemetriaNaoProcessadas(
			FiltrarLeiturasTelemetriaHelper helper)throws ControladorException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean verificarGruposDiferentesLeiturasTelemetria(
			String[] ids)throws ControladorException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Collection<TelemetriaMovReg> perquisarLeiturasTelemetriaPorId(
			String[] ids)throws ControladorException;
	
	/**
 	 * [UC1068] Consistir Leitura Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean consistirLeituraTelemetria(TelemetriaMovReg telemetriaMovReg);
	
	/** [UC0091] Alterar Dados para Faturamento
	 * 
	 * 	[FS0015]  Verificar Imóvel Impressão Simultânea
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/11/2010
	 */
	public boolean verificarExistenciaArquivoDeImpressaoRotaAlternativa(Integer idImovel,
			Integer tipoMedicao)throws ControladorException;
		
	/**
	 * [UCXXXX] - Obter Volume Medio Agua ou Esgoto
	 * RM4548
	 * 
	 * @author Ivan Sergio
	 * @data 13/12/2010
	 * 
	 * @param idImovel
	 * @param faturamentoGrupo
	 * @param idLigacaoTipo
	 * @return houveIntslacaoHidrometro
	 * @throws ControladorException
	 */
	public int[] obterVolumeMedioAguaEsgoto(
			Integer idImovel, 
			Integer anoMesreferencia, 
			Integer idLigacaoTipo,
			boolean houveIntslacaoHidrometro) throws ControladorException;
	
	
	/**
	 * Este caso de uso permite a emissão de boletins de medição em formato TXT
	 * 
	 * [UC1054] Gerar Relatório Boletim de Medição
	 * 
	 * @author Mariana Victor
	 * @date 22/02/2011
	 * 
	 * @param
	 * @return byte[]
	 */
	public byte[] emitirBoletimMedicao(Collection colecao, String mesAno) throws ControladorException;
	
	/**
	 * [UCXXXX] - Obter Volume Medio Agua ou Esgoto
	 * RM4548
	 * Método para descobrir o tipo de ligação do imóvel para poder chamar o método de obterVolumeMedioAguaEsgoto
	 * 
	 * @author Sávio Luiz
	 * @data 23/02/2011
	 * 
	 * @param imovel (Precisa que a situação da Ligação de Água e Esgoto do imóvel esteja carregado.
	 *                HidrometroInstalaçãoHistórico da ligação de água do imóvel também deve estar carregado)
	 * @return
	 * @throws ControladorException
	 */
	public int verificarTipoLigacao (Imovel imovel);
	
	/**
	 * Pesquisa do relatorio consultar arquivo texto leitura
	 * 
	 * @author Rafael Pinto
	 * @date 11/03/2011
	 * 
	 * @param FiltroRelatorioLeituraConsultarArquivosTextoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection<ArquivoTextoRoteiroEmpresa> consultarRelatorioLeituraConsultarArquivosTexto(
			FiltroRelatorioLeituraConsultarArquivosTextoHelper helper)
		throws ControladorException;
	
	/**
	 * Pesquisa do relatorio consultar arquivo texto leitura
	 * 
	 * @author Rafael Pinto
	 * @date 11/03/2011
	 * 
	 * @param FiltroRelatorioLeituraConsultarArquivosTextoHelper
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCountRelatorioLeituraConsultarArquivosTexto(
			FiltroRelatorioLeituraConsultarArquivosTextoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC0800] - Obter Consumo Não Medido
	 *
	 * [SB0005] - Determinar consumo mínimo da subcategoria por pontos de utilização
	 * [FS0007] – Verificar existência do consumo mínimo por subcategoria (pontos)
	 * [SB0006] - Determinar consumo mínimo da categoria por pontos de utilização
	 * 
	 * Obter o consumo mínimo associado ao ponto de utilização e a subcategoria ou a categoria informada
	 *
	 * @author Mariana Victor
	 * @date 23/05/2011
	 *
	 * @param areaConstruida
	 * @param anoMesReferencia
	 * @param subcategoria
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoMinimoParametro(BigDecimal numeroParametro, Integer anoMesReferencia, 
			Subcategoria subcategoria, Categoria categoria) throws ControladorException;
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0001] Gerar Relatório do Tipo 1</b>: Quantitativo de imóveis com leituras através da WEB
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0002] Gerar Relatório do Tipo 2</b>: Quantitativo de imóveis sem leituras através da ISC e WEB
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0003] Gerar Relatório do Tipo 3</b>: Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC</p>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 06/06/2011
	 * 
	 * @param FiltrarRelatorioImoveisComLeiturasHelper 
	 * 
	 * @return Collection<RelatorioImoveisComLeiturasQuantitativosBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisComLeiturasQuantitativosBean> filtrarRelatorioImoveisComLeiturasQuantitativos(FiltrarRelatorioImoveisComLeiturasHelper helper, int parametroPersistirRelatorio) throws ControladorException;
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0004] Gerar Relatório do Tipo 4</b>: Relação de imóveis com leituras não recebidas através da ISC</b>
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0005] Gerar Relatório do Tipo 5</b>: Relação de imóveis não medidos que não estão na rota de ISC</b>
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0006] Gerar Relatório do Tipo 6</b>: Relação de imóveis medidos que não estão na rota de ISC</b>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 10/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<RelatorioImoveisComLeiturasRelacaoBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisComLeiturasRelacaoBean> filtrarRelatorioImoveisComLeiturasRelacao(FiltrarRelatorioImoveisComLeiturasHelper helper, int parametroPersistirRelatorio) throws ControladorException;
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0007] Gerar Relatório do Tipo 7</b>: Quantitativo de imóveis com leituras enviado e recebidos</b>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 13/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<RelatorioImoveisComLeiturasTipo7Bean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisComLeiturasTipo7Bean> filtrarRelatorioImoveisComLeiturasTipo7(FiltrarRelatorioImoveisComLeiturasHelper helper) throws ControladorException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarUltimoConsumoFaturadoImovel(Integer idImovel,
			Integer tipoLigacao)throws ControladorException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosHidrometro(Integer idImovel)
		throws ControladorException ;
	
	/**
	 * [MA2011061011]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idHidrometro
	 * 
	 * @return HidrometroMovimentado
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMaiorDataHidrometroMovimentado(Integer idHidrometro) throws ControladorException;
	
	/**[MA2011061010]
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ControladorException
	 *                Description of the Exception
	 */
	public Integer pesquisarNumeroHidrometroMovimentacaoPorFaixaCount(String Fixo,
			String faixaInicial, String faixaFinal) throws ControladorException;
	
	/** [MA2011061010]
	 * 
	 * pesquisa uma colecao de HidrometroMovimentacao
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroMovimentacaoPorFaixaPaginacao(
			String faixaInicial, String faixaFinal, Integer numeroPagina)
			throws ControladorException; 
	
	/** [MA2011061010]
	 * 
	 * pesquisa uma colecao de HidrometroMovimentacao
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroMovimentacaoPorFaixa(
			String faixaInicial, String faixaFinal)
			throws ControladorException;
	
	/**
	 * [UC0840] - Atualizar Faturamento do Movimento Celular 
	 * 
	 * @author Raphael Rossiter
	 * @date 27/09/2011
	 * 
	 * @return [0] = id do consumo historico, [1] = consumo faturado no mês, [2] = tipo de poço do imóvel
	 * @throws ControladorException
	 */
	public Object[] obterConsumoLigacaoAguaOuEsgotoDoImovel(Integer idImovel,
			Integer anoMesFaturamento, Integer idLigacaoTipo)throws ControladorException ;
	
	/**
	 * [UC0800] - Obter Consumo Não Medido
	 *
	 * @author Raphael Rossiter
	 * @date 22/05/2008
	 *
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer obterConsumoNaoMedidoSemTarifa(Integer idImovel, Integer anoMesReferencia) 
		throws ControladorException;
	
	/**TODO:COSANPA
	 * @author Pamela Gatinho
	 * 
	 * @param imovel
	 * @param medicaoHistorico
	 * @param sistemaParametro
	 * 
	 * @throws ControladorException
	 */
	public boolean verificarSubstituicaoHidrometro(Imovel imovel,
			MedicaoHistorico medicaoHistorico, SistemaParametro sistemaParametro)
			throws ControladorException;
	
	/*
	 * TODO - COSANPA
	 * Adição do médoto pesquisarImovelHidrometroInstaladoMaiorDataInstalacao()
	 * 
	 * Pesquisa o imóvel no qual o hidrômetro está instalado por maior data de instalação
	 */
	
	public String pesquisarImovelHidrometroInstaladoMaiorDataInstalacao(Integer idHidrometro)
			throws ControladorException;
	
	/**
	 * TODO : Cosanpa
	 * Alteracao feita para a rota dividida não finalizar os imoveis por IMEI, 
	 * e sim, pelo numero do arquivo dividido.
	 */
	/**
	 * Metodo para atualizar a rota dividida por partes
	 * 
	 * @author Pamela Gatinho
	 * @date 04/02/2011
	 * 
	 * @param idRota 
	 * @param anoMesFaturamento
	 * @param numeroSequenciaArquivo
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoDividido(Integer idRota, Integer anoMesFaturamento, Integer numeroSequenciaArquivo, 
			int situacaoAnterior, int situacaoNova) throws ControladorException;
	
	/*
	 * TODO : COSANPA
	 * Criação do método para obter uma rota. 
	 */
	/**
	 * Método para buscar uma rota pelo id
	 * 
	 * @author Pamela Gatinho
	 * @date 14/04/2011
	 * 
	 * @param idRota 
	 * @throws ControladorException
	 */
	public Rota pesquisarRota(Integer idRota) throws ControladorException;
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho - 06/06/2011
	 * Adicionando o método para verificar se houve
	 * substituicao de hidrometro no período da leitura
	 * anterior faturada até a data atual
	 */
	public boolean verificarInstalacaoSubstituicaoHidrometro(Integer idImovel, MedicaoTipo medicaoTipo) throws ControladorException;
	
	/**
	 * TODO : COSANPA
	 * 27/06/2011 - Pamela Gatinho
	 * Metodo para salvar no banco as informacoes que foram 
	 * processadas pelo IS, sem refazer os cálculos já feitos no IS.
	 * @throws ControladorException 
	 */
	public void processarImoveisImpressaoSimultanea(FaturamentoGrupo faturamentoGrupo, MovimentoContaPrefaturada movimentoContaPreFaturada) 
		throws ControladorException;

	/**
	 * TODO:COSANPA
	 * Método para atualizar a leitura atual faturada no medição histórico,
	 * na retificação da conta
	 * 
	 * Autor: Adriana Muniz
	 * Data: 22/07/2011
	 * 
	 * @param leituraAtual
	 * @param anoMesReferencia
	 * @param idImovel
	 * @throws ControladorException
	 */
	public void atualizarLeituraRetificarConta( Integer leituraAtual,
			int anoMesReferencia, Integer idImovel) throws ControladorException;
	
	/**
	 * TODO : COSANPA 
	 * relatório de extrato de debito
	 * 
	 * Pesquisa uma rota pelo id da localidade e do setor comercial
	 * 
	 * @author Pamela Gatinho
	 * @date 01/08/2011
	 * 
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @throws ControladorException
	 */
	public Rota obterRotaPorLocalidadeSetorComercial(Integer idLocalidade,
			Integer codigoSetorComercial)
			throws ControladorException;
	
	/**
	 * TODO : COSANPA
	 * Essa consulta se transformou em método, para ser acessado
	 * por outros controladores.
	 * 
	 * @param imovel
	 * @return
	 * @throws ControladorException
	 */
	public FaturamentoSituacaoHistorico obterSituacaoEspecialFaturamentoImovel(
			Imovel imovel) throws ControladorException;
	
	
	public MedicaoHistorico pesquisarMedicaoHistoricoAnterior(Integer idImovel,
			Integer anoMes, Integer idMedicaoTipo) throws ControladorException;
	
	/**
	 * TODO: COSANPA
	 * Retornar Arquivo Texto Roteiro Empresa completo
	 * 
	 * @author Felipe Santos
	 * @date 28/07/2011
	 * 
	 */
	public Collection pesquisarArquivosTextoRoteiroEmpresaCompletoParaArquivoZip(
			String[] ids) throws ControladorException;
	
	/**
	 * Retornar Arquivo Texto Roteiro Empresa por 
	 * Localidade, Id da Rota e Ano/Mês de Referência
	 * 
	 * @author Felipe Santos
	 * @date 05/08/2011
	 * 
	 */
	public ArquivoTextoRoteiroEmpresa pesquisarArquivosTextoRoteiroEmpresaTransmissaoOffline(
			Integer idLocalidade,Integer idRota, Integer anoMesReferencia) throws ControladorException;	
	
	/**
	 * Retornar Arquivo Texto Roteiro Empresa Divisão
	 * 
	 * @author Felipe Santos
	 * @date 05/08/2011
	 */
	public ArquivoTextoRoteiroEmpresaDivisao pesquisarArquivosTextoRoteiroEmpresaDivisao(Integer atreId, 
			Integer numeroSequenciaArquivo) throws ControladorException;
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho - 08/09/2011
	 * 
	 * Gerar dados para o relatorio de leituras realizadas
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param tipoRelatorio
	 * @param usuarioLogado
	 * 
	 * @return
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosRelatorioLeiturasRealizadas(
			int anoMesFaturamento, Integer idFaturamentoGrupo) throws ControladorException;

	/**TODO: COSANPA
	 * Autor: Adriana Muniz
	 * Data: 22/07/2011
	 * 
	 * Verifica se há diferença entre a leitura persistida e a passada como parametro
	 * 
	 * Utilizado na retificação de contas
	 * 
	 * @param leituraAtual
	 * @param anoMesReferencia
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarLeituraAtualFaturadaImovel(Integer leituraAtual, int anoMesReferencia, Integer idImovel) throws ControladorException;
	
	/**TODO: COSANPA
	 * Autor: Adriana Muniz
	 * Data: 22/07/2011
	 * 
	 * Consulta arquivo de rota por ano/mes referencia e grupo
	 * 
	 * Utilizado na retificação de contas
	 * 
	 * @param idGrupo
	 * @param referencia
	 * @return
	 * @throws ControladorException
	 */
	public boolean pesquisaArquivoRotaPorGrupoEReferencia(Integer idGrupo, int referencia)
			throws ControladorException;
			
	
		/**
	 * Método utilizado na impressao das contas via Impressora Térmica
	 * 
	 * @param imovel
	 * @param anoMes
	 * @return
	 * @author Wellington Vernech Rocha
	 * @date 22/08/2011
	 * @throws ControladorException
	 */
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(Integer imovel,
			Integer anoMes) throws ControladorException;
	
	/**
	 * Método utilizado na impressao das contas via Impressora Térmica
	 * 
	 * @param imovel
	 * @param anoMes
	 * @return
	 * @author Wellington Vernech Rocha
	 * @date 22/08/2011
	 * @throws ControladorException
	 */
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(Integer imovel,
			Integer anoMes) throws ControladorException;
	
	/**
	 * TODO : COSANPA 
	 * Pamela Gatinho - 31/05/2012
	 * 
	 * Metodo que soma a quantidade de economias de
	 * todos os imóveis micros do condomínio
	 * 
	 * @param idImovelCondominio
	 * @param anoMesFaturamento
	 * @return quantidadeEconomias
	 * @throws ErroRepositorioException 
	 */
	public int obterQuantidadeEconomiasCondominio (Integer idImovelCondominio, Integer anoMesFaturamento);
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho - 31/05/2012
	 * 
	 * Metodo que calcula o consumo do a ser rateado de agua e 
	 * esgoto do condominio.
	 *  
	 * @param idImovelCondominio
	 * @param anoMesFaturamento
	 * @param ligacaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public int obterConsumoASerRateado(Integer idImovelCondominio, Integer anoMesFaturamento, Integer ligacaoTipo);
	
	/**
	 * Método que obtem o consumo do imóvel não medido,
	 * através da verificação do parâmetro que define
	 * como será o cálculo de consumo para imóvel
	 * não medido.
	 * 
	 * @param imovel
	 * @return consumoNaoMedido
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public int obterConsumoNaoMedido(Imovel imovel) throws ControladorException;
	
	/**
	 * TODO - COSANPA
	 * 
	 * Método para obter todos os imóveis que faltam ser processados
	 * na transmissão offline do arquivo de retorno do IS 
	 * 
	 * @author Felipe Santos
	 * @date 26/01/2012
	 * 
	 * @param idRota
	 * @return List<Integer> retorno
	 * @throws ErroRepositorioException
	 */
	public List<Integer> obterImoveisNaoEnviadosMovimentoContaPreFaturada(Integer idRota, Integer anoMesFaturamento)
			throws ControladorException;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 15/02/2013
	 * 
	 * @param leituraAnterior
	 * @param leituraAtual
	 * @param leituraSituacao
	 * @param idImovel
	 * @param anoMes
	 * @param faturamentoGrupo
	 * @param sistemaParametro
	 * @param dataLeituraAtualInformada
	 * @param idLeituraAnormalidade
	 * @param alterouAnormalidade
	 * 
	 * @throws ControladorException
	 */
	public void calcularLeituraConfirmada(Integer leituraAnterior,
			Integer leituraAtual, LeituraSituacao leituraSituacao, Integer idImovel, Integer anoMes, 
			FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro, String dataLeituraAtualInformada, 
			Integer idLeituraAnormalidade, boolean alterouAnormalidade)  throws ControladorException;

	/**
	 * Buscar Rota a qual o imóvel pertence.
	 * 
	 * @param matricula
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Rota buscarRotaDoImovel(Integer matricula) throws ControladorException ;
	
	/**
	 * TODO : COSANPA
	 * Verificar se o imóvel está com o arquivo de leitura em campo.
	 * 
	 * @author Pamela Gatinho
	 * @date 01/10/2013
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return
	 * @throws ControladorException
	 */
	public void validarImovelEmCampo(Integer idImovel) throws ControladorException;
	
	/**
     * TODO: COSANPA
     * 
     * @autor: Wellington Rocha
     * @date: 21/03/2012
     * 
     * 
     * @return Collection
     * @throws ControladorException
     * 
     */
    public Collection<HidrometroProtecao> pesquisarHidrometroProtecao()
    		throws ControladorException;
    
    /**
     * TODO: COSANPA
     * 
     * @autor: Wellington Rocha
     * @date: 30/04/2012
     * 
     * 
     * 
     * @return Collection
     * @throws ControladorException
     * 
     */
    public Collection<HidrometroMarca> pesquisarHidrometroMarca()
    		throws ControladorException;

}

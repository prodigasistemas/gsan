package gcom.faturamento;

import gcom.api.GsanApi;
import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FichaCompensacao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.IClienteConta;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelCobrarDoacaoHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.AtualizarContaPreFaturadaHelper;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.ContaSegundaViaHelper;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.faturamento.bean.DeclaracaoQuitacaoAnualDebitosHelper;
import gcom.faturamento.bean.DeclaracaoQuitacaoAnualDebitosItemHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.bean.GerarCreditoRealizadoHelper;
import gcom.faturamento.bean.GerarDebitoCobradoHelper;
import gcom.faturamento.bean.GerarRelatorioAnormalidadePorAmostragemHelper;
import gcom.faturamento.bean.GerarResumoSimulacaoFaturamentoHelper;
import gcom.faturamento.bean.PagadorDTO;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.faturamento.bean.RemoverImovesJaProcessadorImpressaoSimultaneaHelper;
import gcom.faturamento.bean.RemoverImovesJaProcessadorImpressaoSimultaneaHelper.DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.faturamento.bo.ContaSegundaViaBO;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaCategoria;
import gcom.faturamento.conta.ComunicadoEmitirConta;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaImpressao;
import gcom.faturamento.conta.ContaImpressaoTermicaQtde;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaCategoria;
import gcom.faturamento.conta.FiltroContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.conta.FiltroContaImpostosDeduzidos;
import gcom.faturamento.conta.FiltroContaImpressao;
import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.conta.IConta;
import gcom.faturamento.conta.IContaCategoria;
import gcom.faturamento.conta.IContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.IContaImpostosDeduzidos;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoriaPK;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoARealizarCategoria;
import gcom.faturamento.credito.FiltroCreditoARealizarGeral;
import gcom.faturamento.credito.FiltroCreditoRealizado;
import gcom.faturamento.credito.FiltroCreditoRealizadoCategoria;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.credito.ICreditoRealizado;
import gcom.faturamento.credito.ICreditoRealizadoCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.faturamento.debito.DebitoCobradoHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoCobrado;
import gcom.faturamento.debito.FiltroDebitoCobradoHistorico;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.faturamento.debito.IDebitoCobrado;
import gcom.faturamento.debito.IDebitoCobradoCategoria;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.FaturamentoImediatoAjusteHelper;
import gcom.gui.faturamento.ImovelFaturamentoSeletivo;
import gcom.gui.faturamento.bean.Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.gui.micromedicao.ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction;
import gcom.gui.portal.ConsultarEstruturaTarifariaPortalHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresa;
import gcom.micromedicao.FiltroReleituraMobile;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.MovimentoArquivoTextoRetornoIS;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.FiltroComunicadoEmitirConta;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.faturamento.DataLeituraAnteriorHelper;
import gcom.relatorio.faturamento.DataLeituraPrevistaHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioContasRetidasHelper;
import gcom.relatorio.faturamento.RelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadas;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadasBean;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioMedicaoFaturamentoHelper;
import gcom.relatorio.faturamento.RelatorioMultasAutosInfracaoPendentesBean;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturarHelper;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturarPorCategoriaHelper;
import gcom.relatorio.faturamento.RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea;
import gcom.relatorio.faturamento.RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean;
import gcom.relatorio.faturamento.ValorAFaturarHelper;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.MergeProperties;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ModeloEmailVencimento;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;
import gcom.util.filtro.ParametroSimplesIn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;

import org.apache.commons.beanutils.BeanComparator;
import org.hibernate.LazyInitializationException;
import org.jboss.logging.Logger;

public class ControladorFaturamento extends ControladorFaturamentoFINAL {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ControladorFaturamento.class);
	
	private SistemaParametro sistemaParametro = null;
	
	/**
	 * Método responsável por verificar se existe no banco um determinado ID na
	 * tabela de faturamento_grupo - caso exista o id passado como parâmetro na
	 * tabela, retorna true, caso contrário retorna false
	 * 
	 * @param Integer
	 *            id - id de um FaturamentoGrupo
	 * @return boolean - true para existir o id na tabela, false para não
	 *         existir
	 * @exception ErroRepositorioException
	 */
	public boolean verificarExistenciaIdGrupoFaturamento(Integer id)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.verificarExistenciaIdGrupoFaturamento(id);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	public Collection pesquisarIdContasGrupoFaturamentoRegistrarBoletos(Integer anoMesFaturamento, Integer idGrupoFaturamento) throws ControladorException {
		try {
			return repositorioFaturamento.pesquisarIdContasGrupoFaturamentoRegistrarBoletos(anoMesFaturamento, idGrupoFaturamento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0184] - Manter débito a Cobrar
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean verificarAutosAssociadosAoDebito(String[] idsDebitosACobrar)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.verificarAutosAssociadosAoDebito(idsDebitosACobrar);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0184] - Manter débito a Cobrar
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public void cancelarAutosInfracao(String[] idsDebitosACobrar)
			throws ControladorException {
		try {

			repositorioFaturamento.cancelarAutosInfracao(idsDebitosACobrar);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0896] - Manter Autos de Infração
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean validarExistenciaDebitoAtivosAutoInfracao(
			Integer idAutoInfracao) throws ControladorException {
		try {

			return repositorioFaturamento
					.validarExistenciaDebitoAtivosAutoInfracao(idAutoInfracao);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0896] - Manter Autos de Infração
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean validarExistenciaDeDebitosAutoInfracao(Integer idAutoInfracao)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.validarExistenciaDeDebitosAutoInfracao(idAutoInfracao);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param colHelper
	 * @param atualizaSituacaoAtualConta
	 *            - Caso seja chamado via a funcionalidade de ISC, não atualiza
	 *            a situação atual da conta que não foi impressa. Caso seja
	 *            chamado via a funcionalidade de consistir, atualiza a situação
	 *            atual da conta.
	 */
	private void atualizarMovimentoCelular(Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada, boolean efetuarRateio) throws ControladorException {

		String matriculaImovel = "";
		try {
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			for (MovimentoContaPrefaturada helper : colMovimentoContaPrefaturada) {

				if (helper.getMovimentoContaPrefaturadaCategorias() != null && helper.getMovimentoContaPrefaturadaCategorias().size() > 0) {

					/**
		        	 * Alteracao para quando a conta não tiver sido emitida pelo IS, não altera nenhuma informação da conta, continua
		        	 * PRE FATURADA
		        	 */
	        		 if(helper.getIndicadorGeracaoConta().shortValue() == ConstantesSistema.SIM.shortValue()){        	
	        			 // Caso o imovel seja o imovel condominio, pulamos
	        			 if ( helper.getImovel().getIndicadorImovelCondominio().equals( ConstantesSistema.SIM ) ){
	        				 continue;
	        			 }
	        			 
	        			 Conta contaAtualizacao = helper.getConta();
	        			 
	        			 matriculaImovel = ""+helper.getImovel().getId();
	        			 
	        			 try {
	        				 
	        				 if (contaAtualizacao != null) {
	        					 contaAtualizacao.getConsumoAgua();
	        				 }
	        			 } catch (LazyInitializationException e) {
	        				 try {
	        					 contaAtualizacao = 
	        						 repositorioFaturamento.pesquisarContaPreFaturada(helper.getImovel().getId(),
	        								 helper.getAnoMesReferenciaPreFaturamento(),
	        								 DebitoCreditoSituacao.PRE_FATURADA);
	        					 
	        					 if (contaAtualizacao == null || contaAtualizacao.getId() == null){
	        						 continue;
	        					 }
	        				 } catch (ErroRepositorioException ex) {
	        					 throw new ControladorException("erro.sistema", ex);
	        				 }			
	        			 }
	        			 
	        			 // Para cada imóvel da tabela movimento conta pré-faturada, 
	        			 // realizar os seguintes procedimentos:
	        			 // O sistema calcula os valores de faturamento para o imóvel
	        			 // obtendo os valores faturados e de consumo de água e
	        			 // esgoto;
	        			 // [SB0001 - Determinar Valores para Faturamento de Água
	        			 // e/ou Esgoto].
	        			 
	        			 FiltroImovel filtro = new FiltroImovel();
	        			 filtro.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
	        			 filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
	        			 filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
	        			 filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helper.getImovel()));
	        			 Collection<Imovel> colImo = Fachada.getInstancia().pesquisar(filtro, Imovel.class.getName());
	        			 Imovel imo = (Imovel) Util.retonarObjetoDeColecao(colImo);
	        			 
	        			 FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
	        			 filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID, helper.getImovel().getId()));
	        			 filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.LIGACAO_TIPO_ID,LigacaoTipo.LIGACAO_AGUA));
	        			 filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.ANO_MES_FATURAMENTO,helper.getFaturamentoGrupo().getAnoMesReferencia()));
	        			 Collection<ConsumoHistorico> colConsumoHistorico = Fachada.getInstancia().pesquisar(filtroConsumoHistorico,ConsumoHistorico.class.getName());
	        			 ConsumoHistorico consumoHistoricoAgua = (ConsumoHistorico) Util.retonarObjetoDeColecao(colConsumoHistorico);
	        			 
	        			 
	        			 filtroConsumoHistorico.limparListaParametros();
	        			 filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID, helper.getImovel().getId()));
	        			 filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.LIGACAO_TIPO_ID,LigacaoTipo.LIGACAO_ESGOTO));
	        			 filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.ANO_MES_FATURAMENTO,helper.getFaturamentoGrupo().getAnoMesReferencia()));
	        			 colConsumoHistorico = Fachada.getInstancia().pesquisar(filtroConsumoHistorico,ConsumoHistorico.class.getName());
	        			 ConsumoHistorico consumoHistoricoEsgoto = (ConsumoHistorico) Util.retonarObjetoDeColecao(colConsumoHistorico);
	        			 
	        			 Collection colecaoCategoriaOUSubcategoria = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imo);
	        			 
	        			 if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
	        				 if (contaAtualizacao != null) {
	        					 colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria(contaAtualizacao);
	        				 } else {
	        					 colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imo);
	        				 }
	        			 } else {
	        				 if (contaAtualizacao != null) {
	        					 colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoriaPorSubcategoria(contaAtualizacao.getId());
	        				 } else {
	        					 colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasSubCategoria(imo.getId());
	        				 }
	        				 
	        			 }
	        			 
	        			 DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = this.determinarValoresFaturamentoAguaEsgoto(imo, 
	        					 helper.getFaturamentoGrupo().getAnoMesReferencia(),
	        					 colecaoCategoriaOUSubcategoria, 
	        					 imo.getQuadra().getRota().getFaturamentoGrupo(),
	        					 consumoHistoricoAgua,
	        					 consumoHistoricoEsgoto);
	        			 
	        			 Collection<CalcularValoresAguaEsgotoHelper> colecaoCalcularValoresAguaEsgotoHelper = helperValoresAguaEsgoto
	        			 .getColecaoCalcularValoresAguaEsgotoHelper();
	        			 
	        			 BigDecimal valorTotalAguaCalculado = this.calcularValorTotalAguaOuEsgotoPorCategoria(colecaoCalcularValoresAguaEsgotoHelper,ConstantesSistema.CALCULAR_AGUA);
	        			 BigDecimal valorTotalEsgotoCalculado = this.calcularValorTotalAguaOuEsgotoPorCategoria(colecaoCalcularValoresAguaEsgotoHelper,ConstantesSistema.CALCULAR_ESGOTO);
	        			 
	        			 Integer consumoAguaCalculado = null;
	        			 
	        			 consumoAguaCalculado = this.calcularConsumoTotalAguaOuEsgotoPorCategoria(colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_AGUA);
	        			 
	        			 Integer consumoEsgotoCalculado = null;
	        			 
	        			 consumoEsgotoCalculado = this.calcularConsumoTotalAguaOuEsgotoPorCategoria(colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_ESGOTO);
	        			 
	        			 Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = helper.getMovimentoContaPrefaturadaCategorias();
	        			 
	        			 BigDecimal valorAgua = new BigDecimal(0);
	        			 BigDecimal valorEsgoto = new BigDecimal(0);
	        			 
	        			 Integer consumoAgua = 0;
	        			 Integer consumoEsgoto = 0;
	        			 Integer consumoRateioAgua = helper.getConsumoRateioAgua();
	        			 Integer consumoRateioEsgoto = helper
	        			 .getConsumoRateioEsgoto();
	        			 BigDecimal valorImposto = new BigDecimal(0);
	        			 
	        			 for (MovimentoContaPrefaturadaCategoria helperCategoria : colMovimentoContaPrefaturadaCategoria) {
	        				 valorAgua = valorAgua.add(helperCategoria.getValorFaturadoAgua());
	        				 valorEsgoto = valorEsgoto.add(helperCategoria.getValorFaturadoEsgoto());
	        				 consumoAgua += helperCategoria.getConsumoFaturadoAgua();
	        				 consumoEsgoto += helperCategoria.getConsumoFaturadoEsgoto();
	        			 }
	        			 
	        			 BigDecimal diferencaValorAgua = valorAgua.subtract(valorTotalAguaCalculado);
	        			 BigDecimal diferencaValorEsgoto = valorEsgoto.subtract(valorTotalEsgotoCalculado);
	        			 
	        			 if (efetuarRateio
	        					 && ((diferencaValorAgua.doubleValue() > .01d || diferencaValorAgua.doubleValue() < -.01d)
	        							 || (diferencaValorEsgoto.doubleValue() > .01d || diferencaValorEsgoto.doubleValue() < -.01d)
    									 || consumoAguaCalculado.intValue() != consumoAgua.intValue() 
    									 || consumoEsgotoCalculado.intValue() != consumoEsgoto.intValue())) {
	        				 
	        				 FaturamentoImediatoAjuste faturamentoImediatoAjuste = new FaturamentoImediatoAjuste();
	        				 faturamentoImediatoAjuste.setConta(contaAtualizacao);
	        				 faturamentoImediatoAjuste.setNumeroConsumoAgua(consumoAgua - consumoAguaCalculado);
	        				 faturamentoImediatoAjuste.setNumeroConsumoEsgoto(consumoEsgoto - consumoEsgotoCalculado);
	        				 faturamentoImediatoAjuste.setValorCobradoAgua(valorAgua.subtract(valorTotalAguaCalculado));
	        				 faturamentoImediatoAjuste.setValorCobradoEsgoto(valorEsgoto.subtract(valorTotalEsgotoCalculado));
	        				 faturamentoImediatoAjuste.setUltimaAlteracao(new Date());
	        				 
	        				 this.getControladorBatch().inserirObjetoParaBatch(faturamentoImediatoAjuste);
	        				 this.atualizarConsumoMovimentoCelular(contaAtualizacao,consumoAgua, consumoAguaCalculado, consumoEsgoto, consumoEsgotoCalculado);
	        			 }
	        			 
	        			 if (contaAtualizacao != null) {
	        				 
	        				 helper.setConta(contaAtualizacao);
	        				 
	        				 /*
	        				  * 2. Para cada registro do tipo 2, alterar na tabela
	        				  * CONTA_CATEGORIA o seu correspondente (CNTA_ID = Conta
	        				  * do movimento em processamento e CATG_ID = código da
	        				  * categoria do movimento e SCAT_ID = código da
	        				  * subcategoria do movimento) , com os seguintes dados
	        				  */
							for (MovimentoContaPrefaturadaCategoria helperCategoria : colMovimentoContaPrefaturadaCategoria) {
								FiltroContaCategoria filtroContaCategoria = new FiltroContaCategoria();
								
								filtroContaCategoria.adicionarParametro(new ParametroSimples(FiltroContaCategoria.CATEGORIA_ID, helperCategoria.getComp_id().getCategoria().getId()));
								filtroContaCategoria.adicionarParametro(new ParametroSimples(FiltroContaCategoria.CONTA_ID, contaAtualizacao.getId()));

								Integer idSubcategoria = null;
								if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
									idSubcategoria = 0;
								} else {
									idSubcategoria = helperCategoria.getComp_id().getSubcategoria().getId();
								}
								filtroContaCategoria.adicionarParametro(new ParametroSimples(FiltroContaCategoria.SUBCATEGORIA_ID, idSubcategoria));
								Collection<ContaCategoria> colContaCategoria = this.getControladorUtil().pesquisar(filtroContaCategoria,ContaCategoria.class.getName());

								ContaCategoria contaCategoria = (ContaCategoria) Util.retonarObjetoDeColecao(colContaCategoria);

								if (contaCategoria != null && !contaCategoria.equals("")) {
									contaCategoria.setValorAgua(helperCategoria.getValorFaturadoAgua());
									contaCategoria.setConsumoAgua(helperCategoria.getConsumoFaturadoAgua());
									contaCategoria.setValorEsgoto(helperCategoria.getValorFaturadoEsgoto());
									contaCategoria.setConsumoEsgoto(helperCategoria.getConsumoFaturadoEsgoto());
									contaCategoria.setValorTarifaMinimaAgua(helperCategoria.getValorTarifaMinimaAgua());
									contaCategoria.setConsumoMinimoAgua(helperCategoria.getConsumoMinimoAgua());
									contaCategoria.setValorTarifaMinimaEsgoto(helperCategoria.getValorTarifaMinimaEsgoto());
									contaCategoria.setConsumoMinimoEsgoto(helperCategoria.getConsumoMinimoEsgoto());
									contaCategoria.setUltimaAlteracao(new Date());

									try {
										repositorioFaturamento.atualizarContaCategoriaProcessoMOBILE(contaCategoria);
									} catch (ErroRepositorioException e) {
										throw new ControladorException("erro.sistema", e);
									}

									/*
									 * 3. Para cada registro do tipo 3, alterar na tabela CONTA_CATEGORIA_CONSUMO_FAIXA o
									 * seu correspondente (CNTA_ID = Conta do movimento em processamento e CATG_ID =
									 * código da categoria do movimento e SCAT_ID = código da subcategoria do movimento)
									 */
									FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();

									filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
											FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID, helper.getId()));

									filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
											FiltroMovimentoContaCategoriaConsumoFaixa.CATEGORIA_ID, helperCategoria.getComp_id().getCategoria().getId()));

									filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
											FiltroMovimentoContaCategoriaConsumoFaixa.SUBCATEGORIA_ID, idSubcategoria));

									Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = this.getControladorUtil()
											.pesquisar(filtroMovimentoContaCategoriaConsumoFaixa, MovimentoContaCategoriaConsumoFaixa.class.getName());

									for (MovimentoContaCategoriaConsumoFaixa helperMovimentoContaCategoriaConsumoFaixa : colMovimentoContaCategoriaConsumoFaixa) {

										ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = new ContaCategoriaConsumoFaixa();
										contaCategoriaConsumoFaixa.setCategoria(helperMovimentoContaCategoriaConsumoFaixa
												.getMovimentoContaPrefaturadaCategoria().getComp_id().getCategoria());

										contaCategoriaConsumoFaixa.setSubcategoria(helperMovimentoContaCategoriaConsumoFaixa
												.getMovimentoContaPrefaturadaCategoria().getComp_id().getSubcategoria());

										contaCategoriaConsumoFaixa.setContaCategoria(contaCategoria);

										contaCategoriaConsumoFaixa.setValorAgua(helperMovimentoContaCategoriaConsumoFaixa.getValorFaturadoAguaNaFaixa());
										contaCategoriaConsumoFaixa.setConsumoAgua(helperMovimentoContaCategoriaConsumoFaixa.getConsumoFaturadoAguaNaFaixa());
										contaCategoriaConsumoFaixa.setValorEsgoto(helperMovimentoContaCategoriaConsumoFaixa.getValorFaturadoEsgotoNaFaixa());
										contaCategoriaConsumoFaixa
												.setConsumoEsgoto(helperMovimentoContaCategoriaConsumoFaixa.getConsumoFaturadoEsgotoNaFaixa());
										contaCategoriaConsumoFaixa.setConsumoFaixaInicio(helperMovimentoContaCategoriaConsumoFaixa
												.getLimiteInicialConsumoNaFaixa());
										contaCategoriaConsumoFaixa.setConsumoFaixaFim(helperMovimentoContaCategoriaConsumoFaixa.getLimiteFinalConsumoNaFaixa());
										contaCategoriaConsumoFaixa.setValorTarifaFaixa(helperMovimentoContaCategoriaConsumoFaixa.getValorTarifaNaFaixa());
										contaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());

										this.getControladorBatch().inserirObjetoParaBatch(contaCategoriaConsumoFaixa);
									}

								}
							}
	        				 
	        				 /*
	        				  * 
	        				  * Para cada registro do tipo 4, alterar na tabela
	        				  * CONTA_IMPOSTOS_DEDUZIDOS o seu correspondente
	        				  * (CNTA_ID = Conta do movimento em processamento e
	        				  * IMTP_ID = código do imposto do movimento) , com os
	        				  * seguintes dados
	        				  */
	        				 FiltroMovimentoContaImpostoDeduzido filtroMovimentoContaImpostoDeduzido = new FiltroMovimentoContaImpostoDeduzido();
	        				 
	        				 filtroMovimentoContaImpostoDeduzido
	        				 .adicionarParametro(new ParametroSimples(
	        						 FiltroMovimentoContaImpostoDeduzido.MOVIMENTO_CONTA_PREFATURADA_ID,
	        						 helper.getId()));
	        				 
	        				 Collection<MovimentoContaImpostoDeduzido> colMovimentoContaImpostoDeduzido = this
	        				 .getControladorUtil().pesquisar(
	        						 filtroMovimentoContaImpostoDeduzido,
	        						 MovimentoContaImpostoDeduzido.class
	        						 .getName());
	        				 
	        				 
	        				 BigDecimal valorBolsaAguaConcedido = retornaValorBolsaAgua(helper.getAnoMesReferenciaPreFaturamento(), helper.getImovel());
	        				 
	        				 if (valorBolsaAguaConcedido != null && valorBolsaAguaConcedido.doubleValue() > 0) {
	        				     BigDecimal valorBolsaAguaAtlz = new BigDecimal(0);
	        				     BigDecimal valorAguaEsgoto = new BigDecimal(0);
	        				     
	        				     if (valorAgua != null && valorAgua.doubleValue() > 0) {
	        				    	 valorAguaEsgoto = valorAguaEsgoto.add(valorAgua);
	        				      	}                                       
	        				     if (valorEsgoto != null && valorEsgoto.doubleValue() > 0) {
	        				    	 valorAguaEsgoto = valorAguaEsgoto.add(valorEsgoto);
	        				        }                   
	        				     if (valorAguaEsgoto.doubleValue() > valorBolsaAguaConcedido.doubleValue()) {
	        				            valorBolsaAguaAtlz = valorBolsaAguaConcedido;
	        				     } else {
	        				            valorBolsaAguaAtlz = valorAguaEsgoto;
	        				        }
	        				     atualizarValorCreditoBolsaAgua(helper.getAnoMesReferenciaPreFaturamento(), helper.getImovel(), valorBolsaAguaAtlz, contaAtualizacao);
	        				 }
	        				 
	        				 BigDecimal valorTotalMenosImposto = new BigDecimal(
	        						 valorAgua.doubleValue()
	        						 + valorEsgoto.doubleValue()
	        						 + contaAtualizacao.getDebitos()
	        						 .doubleValue()
	        						 - contaAtualizacao.getValorCreditos()
	        						 .doubleValue());
	        				 
							for (MovimentoContaImpostoDeduzido helperMovimentoContaImpostoDeduzido : colMovimentoContaImpostoDeduzido) {

								FiltroContaImpostosDeduzidos filtroContaImpostosDeduzidos = new FiltroContaImpostosDeduzidos();
								filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples(FiltroContaImpostosDeduzidos.CONTA_ID, helper.getConta().getId()));
								filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples(FiltroContaImpostosDeduzidos.IMPOSTO_TIPO,
										helperMovimentoContaImpostoDeduzido.getImpostoTipo().getId()));

								Collection<ContaImpostosDeduzidos> colContaImpostosDeduzidos = this.getControladorUtil().pesquisar(
										filtroContaImpostosDeduzidos, ContaImpostosDeduzidos.class.getName());

								ContaImpostosDeduzidos contaImpostosDeduzidos = (ContaImpostosDeduzidos) Util.retonarObjetoDeColecao(colContaImpostosDeduzidos);

								if (contaImpostosDeduzidos != null && !contaImpostosDeduzidos.equals("")) {
									contaImpostosDeduzidos.setValorImposto(helperMovimentoContaImpostoDeduzido.getValorImposto());
									
									valorImposto = valorImposto.add(helperMovimentoContaImpostoDeduzido.getValorImposto());

									contaImpostosDeduzidos.setPercentualAliquota(helperMovimentoContaImpostoDeduzido.getPercentualAliquota());
									contaImpostosDeduzidos.setValorBaseCalculo(valorTotalMenosImposto);
									contaImpostosDeduzidos.setUltimaAlteracao(new Date());

									try {
										repositorioFaturamento.atualizarContaImpostosDeduzidosProcessoMOBILE(contaImpostosDeduzidos);
									} catch (ErroRepositorioException e) {
										throw new ControladorException("erro.sistema", e);
									}
								}
							}
	        				 
	        				 contaAtualizacao.setConsumoAgua(consumoAgua);
	        				 contaAtualizacao.setConsumoEsgoto(consumoEsgoto);
	        				 contaAtualizacao.setConsumoRateioAgua(consumoRateioAgua);
	        				 contaAtualizacao.setConsumoRateioEsgoto(consumoRateioEsgoto);
	        				 contaAtualizacao.setValorAgua(valorAgua);
	        				 contaAtualizacao.setValorEsgoto(valorEsgoto);
	        				 contaAtualizacao.setValorImposto(valorImposto);
	        				 contaAtualizacao.setDataEmissao(helper.getDataHoraLeitura());
	        				 contaAtualizacao.setUltimaAlteracao(new Date());
	        				 contaAtualizacao.setNumeroLeituraAtual(helper.getLeituraFaturamento());
	        				 contaAtualizacao.setNumeroLeituraAnterior(helper.getLeituraHidrometroAnterior());
	        				 contaAtualizacao.setValorRateioAgua(helper.getValorRateioAgua());
					         contaAtualizacao.setValorRateioEsgoto(helper.getValorRateioEsgoto());
					            
	        				 DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
	        				 debitoCreditoSituacao.setId( DebitoCreditoSituacao.NORMAL );
	        				 contaAtualizacao.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
	        				 
	        				 // Só irá atualizar o nitrato na conta caso o mesmo ainda nao tenha sido atualizado
	        				 FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
	        				 filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.IMOVEL_ID,helper.getImovel().getId()));
	        				 filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO,helper.getAnoMesReferenciaPreFaturamento()));
	        				 filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.CREDITO_TIPO,CreditoTipo.CREDITO_NITRATO));
	        				 
	        				 Collection<CreditoARealizar> colCreditoARealizar = this.getControladorUtil().pesquisar(filtroCreditoARealizar,CreditoARealizar.class.getName());
	        				 
	        				 if (colCreditoARealizar != null && colCreditoARealizar.size() > 0) {
	        					 CreditoARealizar credito = (CreditoARealizar) Util.retonarObjetoDeColecao(colCreditoARealizar);
	        					 
	        					 if (credito.getValorCredito() == null || credito.getValorCredito().floatValue() == 0) {
	        						 BigDecimal valorCreditoNitrato = this.atualizarCreditoARealizarNitrato(helper.getImovel(),
	        								 helper.getAnoMesReferenciaPreFaturamento(), valorAgua, helper.getConta());
	        						 
	        						 if (valorCreditoNitrato != null) {
	        							 BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();
	        							 valorCreditos = valorCreditos.add(valorCreditoNitrato);
	        							 contaAtualizacao.setValorCreditos(valorCreditos);
	        						 }
	        					 }
	        				 }
	        				 
	        				 // verifica se o valor crédito é maior que o valor da conta caso seja chamar atualizar os creditos a realizar e os creditos realizados
	        				 BigDecimal valorTotalContaSemCreditos = valorAgua.add(valorEsgoto);
	        				 valorTotalContaSemCreditos = valorTotalContaSemCreditos.add(contaAtualizacao.getDebitos());
	        				 valorTotalContaSemCreditos = valorTotalContaSemCreditos.subtract(valorImposto);
	        				 
	        				 BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();
	        				 
	        				 logger.info(" 1 - Credito a Realizar: Imovel: " + (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId() : "NULL") 
										+ " | Valor creditos: " + contaAtualizacao.getValorCreditos());
	        				 
	        				 Collection indicadorRetransmissaoColecao = repositorioFaturamento.pesquisaIndicadorRetransmissaoMovimentoContaPF(contaAtualizacao.getImovel().getId(),
	        						 helper.getFaturamentoGrupo().getAnoMesReferencia());
	        				 Integer indicadorRetransmissao = null;
	        				 if(!indicadorRetransmissaoColecao.isEmpty()) {
	        					 Iterator indicadorIterator = indicadorRetransmissaoColecao.iterator();
	        					 indicadorRetransmissao = (Integer) indicadorIterator.next();
	        				 }
				           
	        				 if (valorCreditos.compareTo(valorTotalContaSemCreditos) == 1) {
	        					 Imovel imovel = contaAtualizacao.getImovel();
	        					 BigDecimal valorTotalCreditos = this.atualizarCreditoResidual(imovel,
	        							 contaAtualizacao.getId(), helper.getFaturamentoGrupo().getAnoMesReferencia(),
	        							 valorTotalContaSemCreditos, contaAtualizacao);
	        					 contaAtualizacao.setValorCreditos(valorTotalCreditos);
	        					 logger.info(" 2 - Credito a Realizar: Imovel: " + (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId() : "NULL") 
											+ " | Valor creditos: " + contaAtualizacao.getValorCreditos()
											+ " | Valor total creditos: " + valorTotalCreditos);
	        					 
	        					 /**
	        					  * Autor: Wellington Rocha
	        					  * Data: 30/08/2011
	        					  * 
	        					  * Caso a conta seja retransmitida o valor do crédito residual 
	        					  * não será atualizado novamente.
	        					  */
	        				 }else if( (indicadorRetransmissao != null 
	        						 && indicadorRetransmissao.equals(2)) 
	        						 && (valorCreditos.compareTo(valorTotalContaSemCreditos)==0 
	        						 || valorCreditos.compareTo(valorTotalContaSemCreditos)== -1 )){
	        					 
	        					 logger.info(" 3 - Credito a Realizar: Imovel: " + (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId() : "NULL") 
											+ " | Valor creditos: " + contaAtualizacao.getValorCreditos());
	        					 
					            	/**
					            	 * Autor: Adriana Muniz
					            	 * Data: 09/08/2011
					            	 * 
					            	 * Como os valores residuais dos créditos a realizar não estão mais sendo 
					            	 * zerados no momento de geração da rota, é necessário zerar, esse valor caso
					            	 * seja diferente de zero, no retorno do IS
					            	 * */
					            	//consulta todos os créditos com valor residual diferente de zero
					            	Collection colecaoCreditosARealizar = repositorioFaturamento
					            		.buscarCreditoARealizarPorImovelValorResidualDiferenteZero(contaAtualizacao.getImovel().getId());
					            	
					            	
					            	if(!colecaoCreditosARealizar.isEmpty() && colecaoCreditosARealizar != null) {

					            		logger.info(" 4 - Credito a Realizar: Imovel: " + (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId() : "NULL") 
												+ " | Qtde de créditos com valor residual diferente de zero: " + colecaoCreditosARealizar.size());
					            		
					            		Iterator creditoIterator = colecaoCreditosARealizar.iterator();

					            		while(creditoIterator.hasNext()) {
					            			CreditoARealizar credito = (CreditoARealizar)creditoIterator.next();

					            			credito.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);

					            			logger.info(" 5 - Credito a Realizar: Imovel: " + (credito.getImovel() != null ? credito.getImovel().getId() : "NULL") 
													+ " | Créditos: " + (credito.getValorCredito() != null ? credito.getValorCredito() : "NULL" )
													+ " | Residual Concedido no Mês: " + (credito.getValorResidualConcedidoMes() != null ? credito.getValorResidualConcedidoMes() : "NULL") 
													+ " | Residual do Mês Anterior: " + (credito.getValorResidualMesAnterior() != null ? credito.getValorResidualMesAnterior() : "NULL"));
					            			
					            			repositorioFaturamento.atualizarCreditoARealizar(credito);
					            		}
					            	}
	        				 }
	        				 
	        				 try {
	        					 repositorioFaturamento
	        					 .atualizarContaProcessoMOBILE(contaAtualizacao);
	        				 } catch (ErroRepositorioException e) {
	        					 throw new ControladorException("erro.sistema", e);
	        				 }
	        				 
	        				 // Verfificar se o imóvel é para ser faturado ou não,
	        				 // caso não seja então deletar a conta.
	        				 boolean faturar = true;
	        				 if (contaAtualizacao.getLigacaoAguaSituacao() != null
	        						 && contaAtualizacao.getLigacaoAguaSituacao()
	        						 .getIndicadorFaturamentoSituacao()
	        						 .equals(ConstantesSistema.NAO)
	        						 && contaAtualizacao.getLigacaoEsgotoSituacao() != null
	        						 && contaAtualizacao.getLigacaoEsgotoSituacao()
	        						 .getIndicadorFaturamentoSituacao()
	        						 .equals(ConstantesSistema.NAO)
	        						 && contaAtualizacao.getDebitos().compareTo(
	        								 BigDecimal.ZERO) == 0) {
	        					 faturar = false;
	        				 }
	        				 
	        				 BigDecimal valorMinimoEmissao = sistemaParametro
	        				 .getValorMinimoEmissaoConta();
	        				 // Caso o valor da conta seja menor que o valor mínimo
	        				 // permitido para a deleção da conta,
	        				 // então deleta os dados da conta
	        				 if (contaAtualizacao.getValorTotal().compareTo(
	        						 valorMinimoEmissao) < 0
	        						 || !faturar) {
	        					 if (contaAtualizacao.getValorCreditos().compareTo(
	        							 BigDecimal.ZERO) == 0) {
	        						 // Objeto que armazenará as informações para
	        						 // deleção das contas
	        						 ApagarDadosFaturamentoHelper helperApagarDadosFaturamento = new ApagarDadosFaturamentoHelper();
	        						 helperApagarDadosFaturamento
	        						 .setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);
	        						 helperApagarDadosFaturamento.setIdImovel(helper
	        								 .getImovel().getId());
	        						 helperApagarDadosFaturamento
	        						 .setAnoMesFaturamento(helper
	        								 .getAnoMesReferenciaPreFaturamento());
	        						 
	        						 this.apagarDadosGeradosFaturarGrupoFaturamento(
	        								 helperApagarDadosFaturamento,
	        								 FaturamentoAtividade.FATURAR_GRUPO
	        								 .intValue());
	        						 
	        						 // pula de imóvel
	        						 continue;
	        					 }
	        				 }
	        				 
	        				 boolean contaNaoImpressa = false;
	        				 // Caso o valor da conta seja zero e o imóvel não tenha
	        				 // crédito,
	        				 // então não coloca em conta_impressão
	        				 if (contaAtualizacao.getValorTotal().compareTo(
	        						 BigDecimal.ZERO) == 0) {
	        					 if (contaAtualizacao.getValorCreditos().compareTo(
	        							 BigDecimal.ZERO) == 0) {
	        						 contaNaoImpressa = true;
	        					 }
	        				 }
	        				 
	        				 // Caso o indicador de emissão de conta seja igual à não
	        				 // emitida
	        				 if (helper.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO
	        						 .shortValue() && !contaNaoImpressa) {
	        					 
	        					 ContaImpressao contaImpressao = new ContaImpressao();
	        					 contaImpressao.setId(helper.getConta().getId());
	        					 ContaGeral contaGeral = new ContaGeral();
	        					 contaGeral.setConta(contaAtualizacao);
	        					 contaImpressao.setContaGeral(contaGeral);
	        					 contaImpressao.setReferenciaConta(helper
	        							 .getFaturamentoGrupo()
	        							 .getAnoMesReferencia());
	        					 contaImpressao.setFaturamentoGrupo(helper
	        							 .getFaturamentoGrupo());
	        					 contaImpressao
	        					 .setIndicadorImpressao(ConstantesSistema.NAO);
	        					 contaImpressao.setUltimaAlteracao(new Date());
	        					 
	        					 /*
	        					  * 
	        					  * Caso esteja indicado no imóvel que a conta deve
	        					  * ser entregue ao responsável (ICTE_ID da tabela
	        					  * IMOVEL seja igual a 1 ou 3), e o imóvel não seja
	        					  * débito automático( IMOV_ICDEBITOCONTA da tabela
	        					  * IMOVEL seja igual a 2), atribuir CLIE_ID da
	        					  * tabela CLIENTE_IMOVEL para IMOV_ID=Id da
	        					  * matrícula do imóvel e CLIM_DTRELACAOFIM com o
	        					  * valor correspondente a nulo e CRTP_ID com o valor
	        					  * correspondente a responsável da tabela
	        					  * CLIENTE_RELACAO_TIPO, caso contrário atribuir o
	        					  * valor nulo.
	        					  */
	        					 // CAERN só vai imprimir quando for enviar para o
	        					 // cliente responsavel no final do grupo
	        					 // helper.getImovel().getImovelContaEnvio().getId()
	        					 // == ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL ||
	        					 // helper.getImovel().getImovelContaEnvio().getId()
	        					 // ==
	        					 // ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL
	        					 // ||
	        					 
	        					 boolean clienteResponsavel = false;
	        					 if (sistemaParametro
	        							 .getCodigoEmpresaFebraban()
	        							 .equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
	        						 if (helper.getImovel().getImovelContaEnvio() != null
	        								 && (helper.getImovel()
	        										 .getImovelContaEnvio().getId()
	        										 .equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))) {
	        							 clienteResponsavel = true;
	        						 }
	        					 } else {
	        						 if (helper.getImovel().getImovelContaEnvio() != null
	        								 && (helper
	        										 .getImovel()
	        										 .getImovelContaEnvio()
	        										 .getId()
	        										 .equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
	        										 || helper
	        										 .getImovel()
	        										 .getImovelContaEnvio()
	        										 .getId()
	        										 .equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL) || helper
	        										 .getImovel()
	        										 .getImovelContaEnvio()
	        										 .getId()
	        										 .equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))) {
	        							 clienteResponsavel = true;
	        						 }
	        					 }
	        					 
	        					 if (clienteResponsavel) {
	        						 try {
	        							 
	        							 Integer idClienteResponsavel = (Integer) repositorioFaturamento
	        							 .pesquisarClienteResponsavel(helper
	        									 .getImovel().getId());
	        							 
	        							 if (idClienteResponsavel != null
	        									 && !idClienteResponsavel.equals("")) {
	        								 Cliente cliente = new Cliente();
	        								 cliente.setId(idClienteResponsavel);
	        								 contaImpressao
	        								 .setClienteResponsavel(cliente);
	        							 }
	        						 } catch (ErroRepositorioException e) {
	        							 throw new ControladorException(
	        									 "erro.sistema", e);
	        						 }
	        						 
	        					 }
	        					 
	        					 /*
	        					  * 1.Caso id do cliente responsável esteja
	        					  * preenchido (CLIE_IDRESPONSAVEL) atribuir o valor
	        					  * correspondente a conta de cliente responsável da
	        					  * tabela CONTA_TIPO;
	        					  * 
	        					  * 2.Caso imóvel seja débito automático
	        					  * (IMOV_ICDEBITOCONTA da tabela IMOVEL seja igual a
	        					  * 1), atribuir o valor correspondente a conta
	        					  * débito automático da tabela CONTA_TIPO;
	        					  * 
	        					  * Caso nenhuma das condições acima tenha sido
	        					  * verdadeira atribuir o valor correspondente a
	        					  * conta normal da tabela CONTA_TIPO;
	        					  */
	        					 
	        					 ContaTipo contaTipo = new ContaTipo();
	        					 
	        					 if (contaImpressao.getClienteResponsavel() != null) {
	        						 if (helper.getImovel()
	        								 .getIndicadorDebitoConta()
	        								 .equals(ConstantesSistema.SIM)) {
	        							 contaTipo
	        							 .setId(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP);
	        						 } else {
	        							 contaTipo
	        							 .setId(ContaTipo.CONTA_CLIENTE_RESPONSAVEL);
	        						 }
	        						 
	        					 } else if (helper.getImovel()
	        							 .getIndicadorDebitoConta()
	        							 .equals(ConstantesSistema.SIM)) {
	        						 contaTipo
	        						 .setId(ContaTipo.CONTA_DEBITO_AUTOMATICO);
	        					 } else {
	        						 contaTipo.setId(ContaTipo.CONTA_NORMAL);
	        					 }
	        					 
	        					 contaImpressao.setContaTipo(contaTipo);
	        					 
	        					 /*
	        					  * Caso a empresa seja a COMPESA e Caso id do
	        					  * cliente responsável esteja preenchido
	        					  * (CLIE_IDRESPONSAVEL) atribuir o valor nulo, caso
	        					  * contrário atribuir à empresa associada à rota
	        					  * (EMPR_ID da tabela ROTA);
	        					  */
	        					 if (sistemaParametro
	        							 .getCodigoEmpresaFebraban()
	        							 .equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)) {
	        						 if (contaImpressao.getClienteResponsavel() == null) {
	        							 contaImpressao.setEmpresa(helper
	        									 .getImovel().getQuadra().getRota()
	        									 .getEmpresa());
	        						 }
	        					 } else {
	        						 /*
	        						  * Caso contrário, atribuir à empresa associada
	        						  * à rota (EMPR_ID da tabela ROTA);
	        						  */
	        						 if (!sistemaParametro
	        								 .getCodigoEmpresaFebraban()
	        								 .equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
	        							 contaImpressao.setEmpresa(helper
	        									 .getImovel().getQuadra().getRota()
	        									 .getEmpresa());
	        						 }
	        					 }
	        					 /*
	        					  * 
	        					  * Valor total da conta (Soma do valor da água +
	        					  * Soma do valor de esgoto + Valor de débitos da
	        					  * conta ? Valor de créditos da conta ? Soma do
	        					  * valor dos impostos)
	        					  */
	        					 BigDecimal valorTotalConta = BigDecimal.ZERO;
	        					 if (valorTotalMenosImposto != null) {
	        						 valorTotalConta = valorTotalMenosImposto
	        						 .subtract(valorImposto);
	        					 }
	        					 contaImpressao.setValorConta(valorTotalConta);
	        					 
	        					 this.getControladorBatch().inserirObjetoParaBatch(
	        							 contaImpressao);
	        					 
	        				 }
	        			 }
	        			 
	        			 // Atualiza a forma do documento de cobrança se a conta foi
	        			 // impressa
	        			 if (helper.getIndicadorEmissaoConta().equals(
	        					 ConstantesSistema.SIM)
	        					 && helper.getCobrancaDocumento() != null
	        					 && helper.getCobrancaDocumento().getId() != null) {
	        				 
	        				 repositorioCobranca
	        				 .atualizarFormaEmissaoCobrancaDocumento(helper
	        						 .getCobrancaDocumento().getId());
	        			 }
	        		 }
				}
	        		 }

		} catch (Exception e) {
			System.out.println("MATRICULA IMOVEL QUE DEU ERRO:"
					+ matriculaImovel);
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object[] incluirMovimentoContaPreFaturada( BufferedReader buffer, Integer idRota,
		ArquivoTextoRetornoIS arquivoTextoRetornoIS, BufferedReader bufferOriginal)
			throws ControladorException, MobileComunicationException {
		
		byte[] relatorioRetorno = null;
		
		Object[] retorno = new Object[3];
		
		try {
			AtualizarContaPreFaturadaHelper helper = new AtualizarContaPreFaturadaHelper();
			Collection<AtualizarContaPreFaturadaHelper> colecaoAtualizarContaPreFaturadaHelper = new ArrayList();
			AtualizarContaPreFaturadaHelper helperDadosCabecalho = null;
		
			Collection<AtualizarContaPreFaturadaHelper> colHelper = helper.parseHelper(buffer);
		
			Collection<String> errors = new ArrayList();
		
			errors.addAll(verificarSequenciaTiposRegistros(colHelper));
		
			Integer matriculaImovel = null;
			for (AtualizarContaPreFaturadaHelper helperLaco : colHelper) {
		
				if (helperLaco.getTipoRegistro() == 1 && (matriculaImovel == null || !matriculaImovel.equals(helperLaco.getMatriculaImovel()))) {
					matriculaImovel = helperLaco.getMatriculaImovel();
					colecaoAtualizarContaPreFaturadaHelper.add(helperLaco);
					helperDadosCabecalho = helperLaco;
				}
		
				errors.addAll(verificarValorTipoRegistro(helperLaco));
				errors.addAll(verificarExistenciaMatriculaImovel(helperLaco));
				errors.addAll(verificarTipoMedicao(helperLaco));
				errors.addAll(verificarExistenciaFaturamentoGrupo(helperLaco));
				errors.addAll(verificarExistenciaCodigoAnormalidadeLeitura(helperLaco));
				errors.addAll(verificarDataHoraLeitura(helperLaco));
				errors.addAll(validarIndicadorConfirmacaoLeitura(helperLaco));
				errors.addAll(verificarExistenciaCodigoAnormalidadeConsumo(helperLaco));
				errors.addAll(verificarExistenciaCategoria(helperLaco));
				errors.addAll(verificarExistenciaImpostoTipo(helperLaco));
			}
		
			if (errors != null && errors.size() > 0 && helperDadosCabecalho != null) {
				relatorioRetorno = geraResumoInconsistenciasLeiturasAnormalidades(errors, helperDadosCabecalho);
			} else {
				try {
		            incluiDadosMovimentosContaPreFaturada( colHelper, idRota);
		            incluiDadosArquivoRetorno(arquivoTextoRetornoIS, bufferOriginal, colHelper, idRota);
					} catch (ErroRepositorioException e) {
						logger.error("Erro ao incluir movimento conta pre faturada", e);
					}
			}
		
			retorno[0] = relatorioRetorno;
			retorno[1] = colecaoAtualizarContaPreFaturadaHelper;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro de io", e);
		}
		return retorno;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Verificar seqüência dos tipos de registros
	 * 
	 * Não poderá vir um registro do tipo 1 depois de outro tipo 1 para o mesmo
	 * imóvel, deverá retornar uma mensagem "Imóvel: <<número do imóvel>>, do
	 * arquivo, com seqüencial 1 depois de outro seqüencial 1";
	 * 
	 * Não poderá vir um registro do tipo 2 sem que tenha um do tipo 1 para o
	 * mesmo imóvel, deverá retornar uma mensagem "Imóvel: <<número do imóvel>>,
	 * do arquivo, com seqüencial 2 sem seqüencial 1.";
	 * 
	 * Não poderá vir um registro do tipo 3 sem que tenha um do tipo 2 para o
	 * mesmo imóvel, deverá retornar uma mensagem "Imóvel: <<número do imóvel>>
	 * , do arquivo, com seqüencial 3 sem seqüencial 2.";
	 * 
	 * Não poderá vir um registro do tipo 4 sem que tenha um do tipo 1 para o
	 * mesmo imóvel deverá retornar uma mensagem "Imóvel: <<número do imóvel>>,
	 * do arquivo, com seqüencial 4 sem seqüencial 1.";
	 * 
	 * [FS0008] - Verificar seqüência dos tipos de registros
	 * 
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 * @throws ControladorException
	 */
	private Collection<String> verificarSequenciaTiposRegistros(
			Collection<AtualizarContaPreFaturadaHelper> colHelper) {

		Integer registroAnterior = null;
		Integer matriculaImovelRegistroTipo1Selecionado = null;
		Integer matriculaImovelRegistroTipo2Selecionado = null;

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		for (AtualizarContaPreFaturadaHelper helperLaco : colHelper) {

			if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1) {
				// Não poderá vir um registro do tipo 1 depois de outro tipo 1
				if (registroAnterior != null
						&& registroAnterior
								.equals(helperLaco.getTipoRegistro())) {
					/*
					 * errors.add( ConstantesAplicacao.get(
					 * "atencao.imovel_movimento_dados_faturamento_registro_tipo_1"
					 * , helperLaco.getMatriculaImovel()+"" ) );
					 */
				}

				// Guardamos as informações necessarias ao registro tipo 1
				matriculaImovelRegistroTipo1Selecionado = helperLaco
						.getMatriculaImovel();
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2) {
				// Não poderá vir um registro do tipo 2 sem que tenha um do
				// tipo 1 para o mesmo imóvel
				if (matriculaImovelRegistroTipo1Selecionado == null
						|| !matriculaImovelRegistroTipo1Selecionado
								.equals(helperLaco.getMatriculaImovel())) {
					
					System.out.println("[IMOVEL: " + 
                    		helperLaco.getMatriculaImovel() + "] atencao.imovel_movimento_dados_faturamento_registro_tipo_2");
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_2",
									helperLaco.getMatriculaImovel() + ""));
				}

				// Guardamos as informações necessarias ao registro tipo 2
				matriculaImovelRegistroTipo2Selecionado = helperLaco
						.getMatriculaImovel();
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3) {
				// Não poderá vir um registro do tipo 3 sem que tenha um do
				// tipo 2 para o mesmo imóvel
				if (matriculaImovelRegistroTipo2Selecionado == null
						|| !matriculaImovelRegistroTipo2Selecionado
								.equals(helperLaco.getMatriculaImovel())) {
					System.out.println("[IMOVEL: " + 
                    		helperLaco.getMatriculaImovel() + "] atencao.imovel_movimento_dados_faturamento_registro_tipo_3");
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_3",
									helperLaco.getMatriculaImovel() + ""));
				}
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4) {
				// Não poderá vir um registro do tipo 4 sem que tenha um do
				// tipo 1 para o mesmo imóvel
				if (matriculaImovelRegistroTipo1Selecionado == null
						|| !matriculaImovelRegistroTipo1Selecionado
								.equals(helperLaco.getMatriculaImovel())) {
					System.out.println("[IMOVEL: " + 
                    		helperLaco.getMatriculaImovel() + "] atencao.imovel_movimento_dados_faturamento_registro_tipo_4");
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_4",
									helperLaco.getMatriculaImovel() + ""));
				}
			}

			registroAnterior = helperLaco.getTipoRegistro();
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o tipo de registro possua valor <> 1, 2, 3 ou 4, gerar no log de
	 * consistência a mensagem "Imóvel: <<número do imóvel>> com Movimento de
	 * pré-faturamento com tipo de registro inválido".
	 * 
	 * [FS0009] - Verificar valor do tipo de registro
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarValorTipoRegistro(
			AtualizarContaPreFaturadaHelper helperLaco) {

		Collection<String> errors = new ArrayList();

		if (!helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_5)) {
			errors.add(ConstantesAplicacao.get(
					"atencao.imovel_tipo_registros_invalidados",
					helperLaco.getMatriculaImovel() + ""));
			System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() + 
    		"] atencao.imovel_tipo_registros_invalidados");
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso a matrícula do imóvel não exista na tabela IMOVEL, gerar no log de
	 * consistência a mensagem "Matrícula do imóvel inexistente: <<número do
	 * imóvel>>"
	 * 
	 * [FS0002] - Verificar existência da matrícula do imóvel
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private Collection<String> verificarExistenciaMatriculaImovel(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
			// [FS0002] - Verificar existência da matrícula do imóvel
			if (this.getControladorImovel().verificarExistenciaImovel(
					helperLaco.getMatriculaImovel()) == 0) {
				errors.add(ConstantesAplicacao.get(
						"atencao.imovel_matricula_inexistente",
						helperLaco.getMatriculaImovel() + ""));
				System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() + 
    			"] atencao.imovel_matricula_inexistente");
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o tipo de medição seja diferente de zero e não exista na tabela
	 * MEDICAO_TIPO, gerar no log de consistência a mensagem "Imóvel: <<número
	 * do imóvel>> com Tipo de Medição inexistente <<tipo de medição>>".
	 * 
	 * Caso o tipo de medição corresponda à ligação de água e não exista
	 * hidrômetro instalado para a ligação (LAGU_ID=matrícula do imóvel, HIDI_ID
	 * não preenchido na tabela LIGAÇÃO_AGUA), gerar no log de consistência a
	 * mensagem "Imóvel: <<número do imóvel>> com Movimento para ligação de água
	 * sem hidrômetro".
	 * 
	 * Caso o tipo de medição corresponda a poço e não exista hidrômetro
	 * instalado para o poço (IMOV_NNMATRICULA=matrícula do imóvel, HIDI_ID não
	 * preenchido na tabela IMOVEL), gerar no log de consistência a mensagem
	 * "Imóvel: <<número do imóvel>> com Movimento para poço sem hidrômetro".
	 * 
	 * Caso o tipo de medição seja zero e a leitura seja informada e não exista
	 * hidrômetro instalado para o imóvel (LAGU_ID=matrícula do imóvel, HIDI_ID
	 * não preenchido na tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do imóvel,
	 * HIDI_ID não preenchido na tabela IMOVEL), gerar no log de consistência a
	 * mensagem "Imóvel: <<número do imóvel>> com Movimento para ligação sem
	 * hidrômetro" e retornar para o passo 3 do fluxo principal. Caso o tipo de
	 * medição seja zero e a anormalidade informada não seja compatível com
	 * ligação sem hidrômetro (LTAN_ICIMOVELSEMHIDROMETRO=2) e não exista
	 * hidrômetro instalado para o imóvel (LAGU_ID=matrícula do imóvel, HIDI_ID
	 * não preenchido na tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do imóvel,
	 * HIDI_ID não preenchido na tabela IMOVEL), gerar no log de consistência a
	 * mensagem "Imóvel: <<número do imóvel>> com Anormalidade não permitida
	 * para ligação sem hidrômetro".
	 * 
	 * [FS0003] - Verificar tipo de medição
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private Collection<String> verificarTipoMedicao(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		// Apenas testamos o tipo de medição para registros tipo 1
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			// Coletamos as informações necessárias para as validações
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAgua.ID, helperLaco.getMatriculaImovel()));
			filtroLigacaoAgua.adicionarParametro(new ParametroNulo(
					FiltroLigacaoAgua.ID_HIDROMETRO_INSTALACAO_HISTORICO));
			Collection<LigacaoAgua> colLigacaoAgua = this.getControladorUtil()
					.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, helperLaco.getMatriculaImovel()));
			filtroImovel.adicionarParametro(new ParametroNulo(
					FiltroImovel.HIDROMETRO_INSTALACAO_HISTORICO_ID));
			Collection<Imovel> colImovel = this.getControladorUtil().pesquisar(
					filtroImovel, Imovel.class.getName());

			/*
			 * Caso o tipo de medição seja diferente de zero e não exista na
			 * tabela MEDICAO_TIPO, gerar no log de consistência a mensagem
			 * "Imóvel: <<número do imóvel>> com Tipo de Medição inexistente
			 * <<tipo de medição>>".
			 */
			if (!helperLaco.getTipoMedicao().equals(ConstantesSistema.ZERO)) {

				FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
				filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
						FiltroMedicaoTipo.ID, helperLaco.getTipoMedicao()));
				Collection<MedicaoTipo> colMedicaoTipo = this
						.getControladorUtil().pesquisar(filtroMedicaoTipo,
								MedicaoTipo.class.getName());

				if (colMedicaoTipo.size() == 0) {
					errors.add(ConstantesAplicacao.get(
							"atencao.imovel_tipo_medicao_inexistente",
							helperLaco.getMatriculaImovel() + "",
							helperLaco.getTipoMedicao() + ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() 
                    		+ "] atencao.imovel_tipo_medicao_inexistente");
				} else {
					MedicaoTipo medicaoTipo = (MedicaoTipo) Util
							.retonarObjetoDeColecao(colMedicaoTipo);

					/*
					 * Caso o tipo de medição corresponda à ligação de água e
					 * não exista hidrômetro instalado para a ligação
					 * (LAGU_ID=matrícula do imóvel, HIDI_ID não preenchido na
					 * tabela LIGAÇÃO_AGUA), gerar no log de consistência a
					 * mensagem "Imóvel: <<número do imóvel>> com Movimento para
					 * ligação de água sem hidrômetro".
					 */
					if (medicaoTipo.getId() == MedicaoTipo.LIGACAO_AGUA) {
						if (colLigacaoAgua == null
								|| colLigacaoAgua.size() == 0) {
							errors.add(ConstantesAplicacao
									.get("atencao.imovel_movimento_ligacao_agua_sem_hidrometro",
											helperLaco.getMatriculaImovel()
													+ ""));
							System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() 
                            		+ "] atencao.imovel_movimento_ligacao_agua_sem_hidrometro");
						}
						/*
						 * Caso o tipo de medição corresponda a poço e não
						 * exista hidrômetro instalado para o poço
						 * (IMOV_NNMATRICULA=matrícula do imóvel, HIDI_ID não
						 * preenchido na tabela IMOVEL), gerar no log de
						 * consistência a mensagem "Imóvel: <<número do imóvel>>
						 * com Movimento para poço sem hidrômetro".
						 */
					} else if (medicaoTipo.getId() == MedicaoTipo.POCO) {
						if (colImovel == null || colImovel.size() == 0) {
							errors.add(ConstantesAplicacao
									.get("atencao.imovel_movimento_poco_sem_hidrometro",
											helperLaco.getMatriculaImovel()
													+ ""));
							System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() 
                            		+ "] atencao.imovel_movimento_ligacao_agua_sem_hidrometro");
						}
					}
				}
			} else {
				/*
				 * Caso o tipo de medição seja zero e a leitura seja informada e
				 * não exista hidrômetro instalado para o imóvel
				 * (LAGU_ID=matrícula do imóvel, HIDI_ID não preenchido na
				 * tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do imóvel, HIDI_ID
				 * não preenchido na tabela IMOVEL), gerar no log de
				 * consistência a mensagem "Imóvel: <<número do imóvel>> com
				 * Movimento para ligação sem hidrômetro".
				 */
				if (helperLaco.getLeituraHidrometro() != null) {
					if ((colLigacaoAgua == null || colLigacaoAgua.size() == 0)
							&& (colImovel == null || colImovel.size() == 0)) {
						errors.add(ConstantesAplicacao.get(
								"atencao.movimento_ligacao_sem_hidrometro",
								helperLaco.getMatriculaImovel() + ""));
						System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() 
                        		+ "] atencao.movimento_ligacao_sem_hidrometro");
					}
				}

				/*
				 * Caso o tipo de medição seja zero e a anormalidade informada
				 * não seja compatível com ligação sem hidrômetro
				 * (LTAN_ICIMOVELSEMHIDROMETRO=2) e não exista hidrômetro
				 * instalado para o imóvel (LAGU_ID=matrícula do imóvel, HIDI_ID
				 * não preenchido na tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do
				 * imóvel, HIDI_ID não preenchido na tabela IMOVEL), gerar no
				 * log de consistência a mensagem "Imóvel: <<número do imóvel>>
				 * com Anormalidade não permitida para ligação sem hidrômetro".
				 */
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroLeituraAnormalidade.ID, helperLaco
										.getAnormalidadeLeitura()));
				Collection<LeituraAnormalidade> colAnormalidade = this
						.getControladorUtil().pesquisar(
								filtroLeituraAnormalidade,
								LeituraAnormalidade.class.getName());

				if (colAnormalidade != null && colAnormalidade.size() > 0) {

					LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util
							.retonarObjetoDeColecao(colAnormalidade);

					if (leituraAnormalidade.getIndicadorImovelSemHidrometro() == ConstantesSistema.NAO
							&& (colLigacaoAgua == null || colLigacaoAgua.size() == 0)
							&& (colImovel == null || colImovel.size() == 0)) {
						errors.add(ConstantesAplicacao
								.get("atencao.imovel_anormalidade_nao_permitida_ligacao_sem_hidrometro",
										helperLaco.getMatriculaImovel() + ""));
						System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() 
                         		+ "] atencao.imovel_anormalidade_nao_permitida_ligacao_sem_hidrometro");
					}
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o grupo de faturamento não exista na tabela FATURAMENTO_GRUPO,
	 * exibir a mensagem "Grupo de faturamento inexistente" e cancelar a
	 * operação. Lembrar que só virá um grupo por arquivo.
	 * 
	 * [FS0001] - Verificar existência do grupo de faturamento
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaFaturamentoGrupo(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o grupo de faturamento não exista na tabela FATURAMENTO_GRUPO,
		 * exibir a mensagem "Grupo de faturamento inexistente" e cancelar a
		 * operação. Lembrar que só virá um grupo por arquivo.
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, helperLaco
							.getCodigoGrupoFaturamento()));
			Collection<FaturamentoGrupo> colGrupoFaturamento = this
					.getControladorUtil().pesquisar(filtroFaturamentoGrupo,
							FaturamentoGrupo.class.getName());

			if (colGrupoFaturamento == null || colGrupoFaturamento.size() == 0) {
				errors.add(ConstantesAplicacao
						.get("atencao.grupo_faturamento_inexistente"));
				System.out.println("atencao.grupo_faturamento_inexistente");
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o código da anormalidade seja informado (diferente de zero e de
	 * espaços em branco) e não exista na tabela LEITURA_ANORMALIDADE, gerar no
	 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com Código
	 * da Anormalidade de Leitura inexistente <<código da anormalidade>>".
	 * 
	 * FS0008 - Verificar existência do código da anormalidade de leitura]
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 */
	private Collection<String> verificarExistenciaCodigoAnormalidadeLeitura(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o código da anormalidade seja informado (diferente de zero e de
		 * espaços em branco) e não exista na tabela LEITURA_ANORMALIDADE, gerar
		 * no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Código da Anormalidade de Leitura inexistente <<código da
		 * anormalidade>>".
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			/*
        	 *
        	 * Alteração para verificar se o codigo de anormalida e igual a 0 (zero), ou seja,
        	 * se não existe anormalidade, pois caso seja 0, a consulta de anormalidades não
        	 * vai achar a anormalidade e vai indicar erro de anormalidade inexistente, só
        	 * que é o caso de não ter anormalidade no imóvel, e não de ser um código inexistente.
        	 */
			if ( helperLaco.getAnormalidadeLeitura() != null && !helperLaco.getAnormalidadeLeitura().equals(new Integer(0))  ){
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroLeituraAnormalidade.ID, helperLaco
										.getAnormalidadeLeitura()));
				Collection<LeituraAnormalidade> colAnormalidade = this
						.getControladorUtil().pesquisar(
								filtroLeituraAnormalidade,
								LeituraAnormalidade.class.getName());


				if (colAnormalidade == null || colAnormalidade.size() == 0) {
					errors.add(ConstantesAplicacao.get(
							"atencao.imovel_codigo_anormalidade_inexistente",
							helperLaco.getMatriculaImovel() + "",
							helperLaco.getAnormalidadeLeitura() + ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() 
	                 		+ "] atencao.imovel_codigo_anormalidade_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso a data e hora de leitura seja inválida ou maior que a data corrente,
	 * gerar no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
	 * Data e hora de leitura inválida <<data da leitura>>".
	 * 
	 * Caso o ano/mês da data de leitura não seja igual ao ano/mês de referência
	 * do faturamento do grupo (FTGR_AMREFERENCIA) e não seja igual ao ano/mês
	 * de referência do faturamento do grupo menos um mês e não seja igual ao
	 * ano/mês de referência do faturamento do grupo mais um mês, gerar no log
	 * de consistência a mensagem "Data de leitura incompatível com o mês/ano de
	 * faturamento".
	 * 
	 * [FS0004 - Verificar data e hora da leitura]
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 */
	private Collection<String> verificarDataHoraLeitura(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o código da anormalidade seja informado (diferente de zero e de
		 * espaços em branco) e não exista na tabela LEITURA_ANORMALIDADE, gerar
		 * no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Código da Anormalidade de Leitura inexistente <<código da
		 * anormalidade>>".
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getDataHoraLeituraHidrometro() != null) {
				Date dataLeituraHidrometro = helperLaco
						.getDataHoraLeituraHidrometro();

				dataLeituraHidrometro = Util.subtrairNumeroDiasDeUmaData(
						dataLeituraHidrometro, 1);

				if (Util.compararData(dataLeituraHidrometro, new Date()) == 1) {
					errors.add(ConstantesAplicacao
							.get("atencao.data_leitura_incompativel_mes_ano_faturamento"));
					System.out.println("atencao.data_leitura_incompativel_mes_ano_faturamento");
				}

				/*
				 * Caso o ano/mês da data de leitura não seja igual ao ano/mês
				 * de referência do faturamento do grupo (FTGR_AMREFERENCIA) e
				 * não seja igual ao ano/mês de referência do faturamento do
				 * grupo menos um mês e não seja igual ao ano/mês de referência
				 * do faturamento do grupo mais um mês, gerar no log de
				 * consistência a mensagem "Data de leitura incompatível com o
				 * mês/ano de faturamento" e retornar para o passo 3 do fluxo
				 * principal.
				 */

				// Pesquisamos o grupo de faturamento
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, helperLaco
								.getCodigoGrupoFaturamento()));
				Collection<FaturamentoGrupo> colGrupoFaturamento = this
						.getControladorUtil().pesquisar(filtroFaturamentoGrupo,
								FaturamentoGrupo.class.getName());

				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util
						.retonarObjetoDeColecao(colGrupoFaturamento);

				Integer anoMesFaturamentoGrupoMaisUmMes = Util
						.somaMesAnoMesReferencia(
								faturamentoGrupo.getAnoMesReferencia(), 1);
				Integer anoMesFaturamentoGrupoMenosUmMes = Util
						.subtrairMesDoAnoMes(
								faturamentoGrupo.getAnoMesReferencia(), 1);

				Integer anoMesDataLeituraHidrometro = Util
						.recuperaAnoMesDaData(dataLeituraHidrometro);

				if (faturamentoGrupo.getAnoMesReferencia().intValue() != anoMesDataLeituraHidrometro
						.intValue()
						&& anoMesFaturamentoGrupoMaisUmMes.intValue() != anoMesDataLeituraHidrometro
								.intValue()
						&& anoMesFaturamentoGrupoMenosUmMes.intValue() != anoMesDataLeituraHidrometro
								.intValue()) {

					errors.add(ConstantesAplicacao
							.get("atencao.data_leitura_incompativel_mes_ano_faturamento"));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o Indicador de confirmação de leitura não seja igual a 0 ou 1, gerar
	 * no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
	 * Indicador de Confirmação de Leitura inválido <<indicador de confirmação
	 * de leitura>>".
	 * 
	 * [FS0006] - Validar indicador de confirmação de leitura
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> validarIndicadorConfirmacaoLeitura(
			AtualizarContaPreFaturadaHelper helperLaco) {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o Indicador de confirmação de leitura não seja igual a 0 ou 1,
		 * gerar no log de consistência a mensagem "Imóvel: <<número do imóvel>>
		 * com Indicador de Confirmação de Leitura inválido <<indicador de
		 * confirmação de leitura>>" e retornar para o passo 3 do fluxo
		 * principal.
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
			if (helperLaco.getIndicadorConfirmacaoLeitura() != null) {
				if (helperLaco.getIndicadorConfirmacaoLeitura() != 0
						&& helperLaco.getIndicadorConfirmacaoLeitura() != 1) {
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_indicador_confirmacao_leitura_invalido",
									helperLaco.getMatriculaImovel() + "",
									helperLaco.getIndicadorConfirmacaoLeitura()
											+ ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() +
            		"] atencao.imovel_indicador_confirmacao_leitura_invalido");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o código da anormalidade seja informado (diferente de zero e de
	 * espaços em branco) e não exista na tabela CONSUMO_ANORMALIDADE, gerar no
	 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com Código
	 * da Anormalidade de consumo inexistente <<código da anormalidade>>".
	 * 
	 * [FS0012] - Verificar existência do código da anormalidade de consumo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaCodigoAnormalidadeConsumo(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o Indicador de confirmação de leitura não seja igual a 0 ou 1,
		 * gerar no log de consistência a mensagem "Imóvel: <<número do imóvel>>
		 * com Indicador de Confirmação de Leitura inválido <<indicador de
		 * confirmação de leitura>>" e retornar para o passo 3 do fluxo
		 * principal.
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getAnormalidadeConsumo() != null) {
				FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
				filtroConsumoAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroConsumoAnormalidade.ID, helperLaco
										.getAnormalidadeConsumo()));
				Collection<ConsumoAnormalidade> colConsumoAnormalidade = this
						.getControladorUtil().pesquisar(
								filtroConsumoAnormalidade,
								ConsumoAnormalidade.class.getName());

				if (colConsumoAnormalidade != null
						&& colConsumoAnormalidade.size() == 0) {
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_codigo_anormalidade_consumo_inexistente",
									helperLaco.getMatriculaImovel() + "",
									helperLaco.getIndicadorConfirmacaoLeitura()
											+ ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel() +
            		"] atencao.imovel_codigo_anormalidade_consumo_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o código da categoria não exista na tabela CATEGORIA, gerar no log
	 * de consistência a mensagem "Imóvel: <<número do imóvel>> com Categoria
	 * inexistente <<código da categoria>>".
	 * 
	 * [FS0012] - Verificar existência do código da anormalidade de consumo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaCategoria(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o código da categoria não exista na tabela CATEGORIA, gerar no
		 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Categoria inexistente <<código da categoria>>".
		 */

		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)) {

			if (helperLaco.getCodigoCategoria() != 0) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria
						.adicionarParametro(new ParametroSimples(
								FiltroCategoria.CODIGO, helperLaco
										.getCodigoCategoria()));
				Collection<Categoria> colCategoria = this.getControladorUtil()
						.pesquisar(filtroCategoria,
								ConsumoAnormalidade.class.getName());

				if (colCategoria != null && colCategoria.size() == 0) {
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_categoria_inexistente"));
					System.out.println("atencao.imovel_categoria_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o tipo do imposto não exista na tabela IMPOSTO_TIPO, gerar no log de
	 * consistência a mensagem "Imóvel: <<número do imóvel>> com Tipo do Imposto
	 * inexistente <<tipo do imposto>>".
	 * 
	 * [FS0010] - Verificar existência do tipo do imposto
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaImpostoTipo(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o tipo do imposto não exista na tabela IMPOSTO_TIPO, gerar no
		 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com Tipo
		 * do Imposto inexistente <<tipo do imposto>>".
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)) {

			if (helperLaco.getTipoImposto() != 0) {
				FiltroImpostoTipo filtroImpostoTipo = new FiltroImpostoTipo();
				filtroImpostoTipo.adicionarParametro(new ParametroSimples(
						FiltroImpostoTipo.ID, helperLaco.getTipoImposto()));
				Collection<ImpostoTipo> colImpostoTipo = this
						.getControladorUtil().pesquisar(filtroImpostoTipo,
								ImpostoTipo.class.getName());

				if (colImpostoTipo != null && colImpostoTipo.size() == 0) {
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_tipo_imposto_inexistente"));
					System.out.println("atencao.imovel_tipo_imposto_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * 
	 * Caso seja chamado por uma tela, o sistema gera uma tela de acordo com o
	 * movimento, Caso contrário, o sistema gera um relatório e envia, por
	 * e-mail para o operador, registrado com os seguintes campos:
	 * 
	 * No cabeçalho imprimir o grupo de faturamento informado (FTGR_ID), o
	 * código e descrição da empresa (EMPR_ID e EMPR_NMEMPRESA da tabela EMPRESA
	 * com EMPR_ID da tabela ROTA com ROTA_ID da tabela QUADRA com QDRA_ID da
	 * tabela IMOVEL com IMOV_ID=matrícula do imóvel do primeiro registro do
	 * arquivo que exista na tabela IMOVEL), o código da localidade e o título
	 * fixo "MOVIMENTO CELULAR - IMPRESSÃO SIMULTÂNEA" quando processado o
	 * arquivo de movimento;
	 * 
	 * Imprimir o erro correspondente encontrado no processamento do imóvel;
	 * 
	 * Caso seja chamado por uma tela, imprimir um texto "Arquivo processado com
	 * problema e enviado para operação para processar o movimento. Localidade
	 * <<Código da Localidade>>";
	 * 
	 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impressão
	 * simultânea com Problemas
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colErrors
	 */
	private byte[] geraResumoInconsistenciasLeiturasAnormalidades(
			Collection<String> colErrors, AtualizarContaPreFaturadaHelper helper)
			throws ControladorException {

		RelatorioErrosMovimentosContaPreFaturadas relatorioErrosMovimentosContaPreFaturadas = new RelatorioErrosMovimentosContaPreFaturadas(
				new Usuario());

		// Adicionamos os parametros
		Map parametros = new HashMap();

		parametros.put("imagem", this.getControladorUtil()
				.pesquisarParametrosDoSistema().getImagemRelatorio());

		// Grupo de faturamento
		parametros.put("grupoFaturamento", helper.getCodigoGrupoFaturamento()
				+ "");

		// Id da localidade
		FiltroRota filtro = new FiltroRota();
		filtro.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtro.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,
				helper.getCodigoRota()));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroRota.LOCALIDADE_ID, helper.getLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, helper
						.getCodigoSetorComercial()));
		Collection<Rota> colRota = Fachada.getInstancia().pesquisar(filtro,
				Rota.class.getName());
		Rota rota = (Rota) Util.retonarObjetoDeColecao(colRota);
		parametros.put("idLocalidade", helper.getLocalidade() + "");

		// Código do setor Comercial
		parametros.put("codigoSetorComercial", helper.getCodigoSetorComercial()
				+ "");

		// Id e descrição de empresa
		String descricaoRota = "";
		if (rota != null && !rota.equals("")) {
			descricaoRota = rota.getEmpresa().getId() + " - "
					+ rota.getEmpresa().getDescricao();
		}
		parametros.put("idDescricaoEmpresa", descricaoRota);

		// Código da localidade
		parametros.put("codigoLocalidade", helper.getLocalidade() + "");

		// Criamos agora os beans do relatorio
		List relatorioBeans = new ArrayList();

		for (String erro : colErrors) {

			RelatorioErrosMovimentosContaPreFaturadasBean bean = new RelatorioErrosMovimentosContaPreFaturadasBean();

			bean.setError(erro);

			relatorioBeans.add(bean);
		}

		// Criamos o source
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		return relatorioErrosMovimentosContaPreFaturadas
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_ERROS_MOVIMENTOS_CONTA_PRE_FATURADAS,
						parametros, ds, TarefaRelatorio.TIPO_PDF);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "rawtypes", "unused" })
	private void incluiDadosMovimentosContaPreFaturada(Collection<AtualizarContaPreFaturadaHelper> colHelper, Integer idRota ) 
    		throws ControladorException, MobileComunicationException{

		String matriculaImovel = "";
		Integer anoMesFaturamentoGrupoFaturamento = null;
		Rota rota = null;

		Collection<RotaAtualizacaoSeq> colAtuSeq = new ArrayList();
		Collection moviContaPF = new ArrayList();

		try {
			MovimentoContaPrefaturada movimentoContaPreFaturadaIncluido = null;
			MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoriaIncluido = null;

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			boolean jaSelecionouRegistroTipo1 = false;
			Object[] dadosArquivoTextoRoteiroEmpresa = null;

			for (AtualizarContaPreFaturadaHelper helper : colHelper) {
				
				if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
					
	        		if(moviContaPF.isEmpty()) {
	        			Imovel imovelMovimentoContaPF = this.getControladorImovel().pesquisarImovel(helper.getMatriculaImovel());
	        			Integer amReferenciaGrupo = repositorioFaturamento.retornaAnoMesFaturamentoGrupo(imovelMovimentoContaPF.getId());

	        			moviContaPF = repositorioFaturamento.pesquisaMovimentoContaPF(imovelMovimentoContaPF.getId(), amReferenciaGrupo);
	        		}
	        		
					if ((rota == null || rota.equals(""))) {
						if (idRota != null) {
							rota = this.getControladorMicromedicao().pesquisarRota(idRota);
						} else {
							rota = pesquisarRotaImpressaoSimultanea(helper);
						}
						
						if (rota != null && !rota.equals("")) {
							dadosArquivoTextoRoteiroEmpresa = repositorioFaturamento.pesquisarArquivoTextoRoteiroEmpresa(rota.getId(),helper.getAnoMesFaturamento());
							
							if (dadosArquivoTextoRoteiroEmpresa != null) {
								Integer idSituacaoTransmissaoLeitura = (Integer) dadosArquivoTextoRoteiroEmpresa[1];
							
								if (!idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.DISPONIVEL)
										&& !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.LIBERADO)
										&& !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.EM_CAMPO)
										&& !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO)) {
									
									matriculaImovel = "" + helper.getMatriculaImovel();
									throw new MobileComunicationException("atencao.arquivo_ja_finalizado", null);
								}
							}

							if (rota.getFaturamentoGrupo() != null 	&& !rota.getFaturamentoGrupo().equals("")) {
								Integer anoMesGrupo = rota.getFaturamentoGrupo().getAnoMesReferencia();
								
								if (helper.getAnoMesFaturamento() != null && helper.getAnoMesFaturamento() != 0) {
									
									if (!anoMesGrupo.equals(helper.getAnoMesFaturamento())) {
										matriculaImovel = "" + helper.getMatriculaImovel();
										throw new MobileComunicationException("atencao.grupo_ja_faturado",	null);
									}
								}
							}
						}
					}

					this.removerDadosMovimentosContaPreFaturada(helper);

				}
			}

			BigDecimal valorRateioAgua = new BigDecimal(0);
			BigDecimal valorRateioEsgoto = new BigDecimal(0);
			
			for (AtualizarContaPreFaturadaHelper helper : colHelper) {
				Imovel imovel = this.getControladorImovel().pesquisarImovel(helper.getMatriculaImovel());

				if ((rota.getIndicadorRotaAlternativa().equals(
						ConstantesSistema.NAO) && imovel.getRotaAlternativa() == null)
						|| (rota.getIndicadorRotaAlternativa().equals(
								ConstantesSistema.SIM) && imovel
								.getRotaAlternativa() != null)) {
					
					if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
						
						valorRateioAgua = helper.getValorRateioAgua();
						valorRateioEsgoto = helper.getValorRateioEsgoto();
						
						MovimentoContaPrefaturada movimentoContaPrefaturada = new MovimentoContaPrefaturada();
						movimentoContaPrefaturada.setAnoMesReferenciaPreFaturamento(helper.getAnoMesFaturamento());
						movimentoContaPrefaturada.setImovel(imovel);

						MedicaoTipo medicaoTipo = new MedicaoTipo(helper.getTipoMedicao());
						movimentoContaPrefaturada.setMedicaoTipo(medicaoTipo);
						
						if (helper.getNumeroConta() != 0) {
							Conta conta = new Conta(helper.getNumeroConta());
							movimentoContaPrefaturada.setConta(conta);
						}
						
						FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo(helper.getCodigoGrupoFaturamento());
						movimentoContaPrefaturada.setFaturamentoGrupo(faturamentoGrupo);
						
						if (rota == null || rota.equals("")) {
							rota = pesquisarRotaImpressaoSimultanea(helper);
						}
						movimentoContaPrefaturada.setRota(rota);
						
						movimentoContaPrefaturada.setLeituraHidrometro(helper.getLeituraHidrometro());
						
						if (helper.getAnormalidadeLeitura() != null && !helper.getAnormalidadeLeitura().equals(0)) {
							LeituraAnormalidade anormalidadeLeitura = new LeituraAnormalidade(helper.getAnormalidadeLeitura());
							movimentoContaPrefaturada.setLeituraAnormalidadeLeitura(anormalidadeLeitura);
						}
						
						movimentoContaPrefaturada.setDataHoraLeitura(helper.getDataHoraLeituraHidrometro());
						movimentoContaPrefaturada.setIndicadorSituacaoLeitura(helper.getIndicadorConfirmacaoLeitura());
						movimentoContaPrefaturada.setLeituraFaturamento(helper.getLeituraFaturamento());
						movimentoContaPrefaturada.setConsumoMedido(helper.getConsumoMedido());
						movimentoContaPrefaturada.setConsumoCobrado(helper.getConsumoASerCobradoMes());
						
						if (helper.getTipoConsumo() != null) {
							ConsumoTipo consumoTipo = new ConsumoTipo(helper.getTipoConsumo());
							movimentoContaPrefaturada.setConsumoTipo(consumoTipo);
						}
						
						if (helper.getAnormalidadeConsumo() != null) {
							ConsumoAnormalidade anormalidadeConsumo = new ConsumoAnormalidade(helper.getAnormalidadeConsumo());
							movimentoContaPrefaturada.setConsumoAnormalidade(anormalidadeConsumo);
						}
						
						movimentoContaPrefaturada.setDataHoraGeracaoMovimento(new Date());
						movimentoContaPrefaturada.setIndicadorAtualizacaoFaturamento(Short.parseShort("2"));
						movimentoContaPrefaturada.setUtlimaAlteracao(new Date());
						movimentoContaPrefaturada.setIndicadorEmissaoConta(helper.getIndicacaoEmissaoConta());
						movimentoContaPrefaturada.setConsumoRateioAgua(helper.getConsumoRateioAgua());
						movimentoContaPrefaturada.setConsumoRateioEsgoto(helper.getConsumoRateioEsgoto());
						movimentoContaPrefaturada.setIndicadorGeracaoConta(helper.getIndicadorGeracaoConta());
						movimentoContaPrefaturada.setValorRateioAgua(valorRateioAgua);
						movimentoContaPrefaturada.setValorRateioEsgoto(valorRateioEsgoto);
							
						if (helper.getAnormalidadeFaturamento() != null && !helper.getAnormalidadeFaturamento().equals(0)) {
							LeituraAnormalidade anormalidadeLeitura = new LeituraAnormalidade(helper.getAnormalidadeFaturamento());
							movimentoContaPrefaturada.setLeituraAnormalidadeFaturamento(anormalidadeLeitura);
						}
						
						if (helper.getLeituraHidrometroAnterior() != null && !helper.getLeituraHidrometroAnterior().equals("")) {
							movimentoContaPrefaturada.setLeituraHidrometroAnterior(helper.getLeituraHidrometroAnterior());
						}
						
						movimentoContaPrefaturada.setIndicadorAlteracao(ConstantesSistema.NAO);
						
						matriculaImovel = movimentoContaPrefaturada.getImovel().getId() + "";
						
						if(moviContaPF.isEmpty()){
							movimentoContaPrefaturada.setIndicadorRetransmissao(ConstantesSistema.NAO);
						}else {
							movimentoContaPrefaturada.setIndicadorRetransmissao(ConstantesSistema.SIM);
						}

						movimentoContaPrefaturada.setLatitude(helper.getLatitude());
						movimentoContaPrefaturada.setLongitude(helper.getLongitude());
						movimentoContaPrefaturada.setId((Integer) this.getControladorBatch().inserirObjetoParaBatch(movimentoContaPrefaturada));
						
						if (!jaSelecionouRegistroTipo1) {
							movimentoContaPreFaturadaIncluido = movimentoContaPrefaturada;
							jaSelecionouRegistroTipo1 = true;
						} else {
							jaSelecionouRegistroTipo1 = false;
						}

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)) {
						
						jaSelecionouRegistroTipo1 = false;
						
						Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = null;
						
						if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
							FiltroMovimentoContaPrefaturadaCategoria filtroMovimentoContaPrefaturadaCategoria = new FiltroMovimentoContaPrefaturadaCategoria();
							filtroMovimentoContaPrefaturadaCategoria.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturadaCategoria.MOVIMENTO_CONTA_PREFATURADA_ID,
									movimentoContaPreFaturadaIncluido.getId()));
							
							filtroMovimentoContaPrefaturadaCategoria.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturadaCategoria.ID_CATEGORIA, helper.getCodigoCategoria()));
							
							colMovimentoContaPrefaturadaCategoria = this.getControladorUtil().pesquisar(
									filtroMovimentoContaPrefaturadaCategoria,
									MovimentoContaPrefaturadaCategoria.class
									.getName());
						}
						
						MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoria = null;
						
						if (colMovimentoContaPrefaturadaCategoria != null && !colMovimentoContaPrefaturadaCategoria.isEmpty()) {
							
							for (MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoriaAtualizar : colMovimentoContaPrefaturadaCategoria) {
								BigDecimal valorFaturadoAgua = movimentoContaPrefaturadaCategoriaAtualizar.getValorFaturadoAgua().add(helper.getValorFaturadoAgua());
								Integer consumoFaturadoAgua = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoFaturadoAgua()+ helper.getConsumoFaturadoAgua();
								
								BigDecimal valorTarifaMinimaAgua = movimentoContaPrefaturadaCategoriaAtualizar.getValorTarifaMinimaAgua().add(helper.getValorTarifaMinimaAgua());
								Integer consumoMinimoAgua = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoMinimoAgua() + helper.getConsumoMinimoAgua();
								
								movimentoContaPrefaturadaCategoriaAtualizar.setValorFaturadoAgua(valorFaturadoAgua);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoFaturadoAgua(consumoFaturadoAgua);
								movimentoContaPrefaturadaCategoriaAtualizar.setValorTarifaMinimaAgua(valorTarifaMinimaAgua);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoMinimoAgua(consumoMinimoAgua);
								
								BigDecimal valorFaturadoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getValorFaturadoEsgoto().add(helper.getValorFaturadoEsgoto());
								Integer consumoFaturadoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoFaturadoEsgoto() + helper.getConsumoFaturadoEsgoto();
								
								BigDecimal valorTarifaMinimaEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getValorTarifaMinimaEsgoto().add(helper.getValorTarifaMinimaEsgoto());
								Integer consumoMinimoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoMinimoEsgoto() + helper.getConsumoMinimoEsgoto();
								
								movimentoContaPrefaturadaCategoriaAtualizar.setValorFaturadoEsgoto(valorFaturadoEsgoto);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoFaturadoEsgoto(consumoFaturadoEsgoto);
								movimentoContaPrefaturadaCategoriaAtualizar.setValorTarifaMinimaEsgoto(valorTarifaMinimaEsgoto);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoMinimoEsgoto(consumoMinimoEsgoto);
								
								movimentoContaPrefaturadaCategoriaAtualizar.setUltimaAlteracao(new Date());
								
								this.getControladorUtil().atualizar(movimentoContaPrefaturadaCategoriaAtualizar);
								
								movimentoContaPrefaturadaCategoria = movimentoContaPrefaturadaCategoriaAtualizar;
							}
							
							/**
							 * 
							 * Pamela gatinho - 28/05/2012
							 * Alteracao para salvar o valor do rateio junto com o 
							 * valor faturado de agua.
							 */
							BigDecimal valorFaturadoAgua = new BigDecimal(0);
							
							System.out.println(helper.getMatriculaImovel());
							if (valorRateioAgua != null && !valorRateioAgua.equals(new BigDecimal(0)) 
									&& helper.getIndicadorGeracaoConta().shortValue() == ConstantesSistema.SIM.shortValue()) {
								valorFaturadoAgua = movimentoContaPrefaturadaCategoria.getValorFaturadoAgua().add(
										helper.getValorRateioAgua());
							} else {
								valorFaturadoAgua = movimentoContaPrefaturadaCategoria.getValorFaturadoAgua();
							}
							movimentoContaPrefaturadaCategoria.setValorFaturadoAgua(valorFaturadoAgua);
							// Atualizar
							this.getControladorUtil().atualizar(movimentoContaPrefaturadaCategoria);

							
						} else {
							movimentoContaPrefaturadaCategoria = new MovimentoContaPrefaturadaCategoria();
							
							// Informamos o movimento atual
							MovimentoContaPrefaturadaCategoriaPK pk = new MovimentoContaPrefaturadaCategoriaPK();
							pk.setMovimentoContaPrefaturada(movimentoContaPreFaturadaIncluido);
							
							Categoria categoria = new Categoria(helper.getCodigoCategoria());
							pk.setCategoria(categoria);
							
							Subcategoria subcategoria = new Subcategoria(helper.getCodigoSubCategoria());
							pk.setSubcategoria(subcategoria);
							
							movimentoContaPrefaturadaCategoria.setComp_id(pk);
							
							BigDecimal valorFaturadoAgua = new BigDecimal(0);
							if (valorRateioAgua != null) {
								valorFaturadoAgua = helper.getValorFaturadoAgua().add(valorRateioAgua);
							} else {
								valorFaturadoAgua = helper.getValorFaturadoAgua();
							}
							
							movimentoContaPrefaturadaCategoria.setValorFaturadoAgua(valorFaturadoAgua);
							movimentoContaPrefaturadaCategoria.setConsumoFaturadoAgua(helper.getConsumoFaturadoAgua());
							movimentoContaPrefaturadaCategoria.setValorTarifaMinimaAgua(helper.getValorTarifaMinimaAgua());
							movimentoContaPrefaturadaCategoria.setConsumoMinimoAgua(helper.getConsumoMinimoAgua());
							

							BigDecimal valorFaturadoEsgoto = new BigDecimal(0);
							if (valorFaturadoEsgoto != null) {
								valorFaturadoEsgoto = helper.getValorFaturadoEsgoto().add(valorRateioEsgoto);
							} else {
								valorFaturadoEsgoto = movimentoContaPrefaturadaCategoria.getValorFaturadoEsgoto();
							}
							
							movimentoContaPrefaturadaCategoria.setValorFaturadoEsgoto(valorFaturadoEsgoto);
							movimentoContaPrefaturadaCategoria.setConsumoFaturadoEsgoto(helper.getConsumoFaturadoEsgoto());
							movimentoContaPrefaturadaCategoria.setValorTarifaMinimaEsgoto(helper.getValorTarifaMinimaEsgoto());
							movimentoContaPrefaturadaCategoria.setConsumoMinimoEsgoto(helper.getConsumoMinimoEsgoto());
							movimentoContaPrefaturadaCategoria.setUltimaAlteracao(new Date());
							
							this.getControladorBatch().inserirObjetoParaBatch(movimentoContaPrefaturadaCategoria);
						}
						
						movimentoContaPrefaturadaCategoriaIncluido = movimentoContaPrefaturadaCategoria;

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3)) {
						
						Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = null;
						
						if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
							
							FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();
							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID,
									movimentoContaPreFaturadaIncluido
									.getId()));
							
							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.CATEGORIA_ID,
									helper.getCodigoCategoria()));
							
							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.LIMITE_INICIAL_CONSUMO_FAIXA,
									helper.getLimiteInicialConsumoFaixa()));
							
							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.LIMITE_FINAL_CONSUMO_FAIXA,
									helper.getLimiteFinalConsumoFaixa()));
							
							colMovimentoContaCategoriaConsumoFaixa = this.getControladorUtil().pesquisar(
									filtroMovimentoContaCategoriaConsumoFaixa,
									MovimentoContaCategoriaConsumoFaixa.class
									.getName());
							
						}
						
						if (colMovimentoContaCategoriaConsumoFaixa != null && !colMovimentoContaCategoriaConsumoFaixa.isEmpty()) {
							
							for (MovimentoContaCategoriaConsumoFaixa movimentoContaCategoriaConsumoFaixa : colMovimentoContaCategoriaConsumoFaixa) {
								
								BigDecimal valorFaturadoAguaFaixa = movimentoContaCategoriaConsumoFaixa.getValorFaturadoAguaNaFaixa().add(helper.getValorFaturadoAguaFaixa());
								
								Integer consumoFaturadoAguaFaixa = movimentoContaCategoriaConsumoFaixa.getConsumoFaturadoAguaNaFaixa() + helper.getConsumoFaturadoAguaFaixa();
								
								movimentoContaCategoriaConsumoFaixa.setConsumoFaturadoAguaNaFaixa(consumoFaturadoAguaFaixa);
								movimentoContaCategoriaConsumoFaixa.setValorFaturadoAguaNaFaixa(valorFaturadoAguaFaixa);
								
								BigDecimal valorFaturadoEsgotoFaixa = movimentoContaCategoriaConsumoFaixa.getValorFaturadoEsgotoNaFaixa().add(helper.getValorFaturadoEsgotoFaixa());
								Integer consumoFaturadoEsgotoFaixa = movimentoContaCategoriaConsumoFaixa.getConsumoFaturadoEsgotoNaFaixa()+ helper.getConsumoFaturadoEsgotoFaixa();
								
								BigDecimal valorTarifaFaixa = new BigDecimal("0.00");
								if (helper.getValorTarifaAguaFaixa() != null) {
									valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaAguaFaixa());
								} else {
									valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaEsgotoFaixa());
								}
								
								movimentoContaCategoriaConsumoFaixa.setConsumoFaturadoEsgotoNaFaixa(consumoFaturadoEsgotoFaixa);
								movimentoContaCategoriaConsumoFaixa.setValorFaturadoEsgotoNaFaixa(valorFaturadoEsgotoFaixa);
								movimentoContaCategoriaConsumoFaixa.setValorTarifaNaFaixa(valorTarifaFaixa);
								movimentoContaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());
								
								this.getControladorUtil().atualizar(movimentoContaCategoriaConsumoFaixa);
								
							}
						} else {
							
							MovimentoContaCategoriaConsumoFaixa movimentoContaCategoriaConsumoFaixa = new MovimentoContaCategoriaConsumoFaixa();
							
							movimentoContaCategoriaConsumoFaixa.setMovimentoContaPrefaturadaCategoria(movimentoContaPrefaturadaCategoriaIncluido);
							movimentoContaCategoriaConsumoFaixa.setConsumoFaturadoAguaNaFaixa(helper.getConsumoFaturadoAguaFaixa());
							movimentoContaCategoriaConsumoFaixa.setValorFaturadoAguaNaFaixa(helper.getValorFaturadoAguaFaixa());
							movimentoContaCategoriaConsumoFaixa.setConsumoFaturadoEsgotoNaFaixa(helper.getConsumoFaturadoEsgotoFaixa());
							movimentoContaCategoriaConsumoFaixa.setValorFaturadoEsgotoNaFaixa(helper.getValorFaturadoEsgotoFaixa());
							movimentoContaCategoriaConsumoFaixa.setLimiteInicialConsumoNaFaixa(helper.getLimiteInicialConsumoFaixa());
							movimentoContaCategoriaConsumoFaixa.setLimiteFinalConsumoNaFaixa(helper.getLimiteFinalConsumoFaixa());
							
							BigDecimal valorTarifaFaixa = new BigDecimal("0.00");
							if (helper.getValorTarifaAguaFaixa() != null) {
								valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaAguaFaixa());
							} else {
								valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaEsgotoFaixa());
							}
							
							movimentoContaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());
							movimentoContaCategoriaConsumoFaixa.setValorTarifaNaFaixa(valorTarifaFaixa);
							
							this.getControladorBatch().inserirObjetoParaBatch(movimentoContaCategoriaConsumoFaixa);
						}
						
					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)) {
						MovimentoContaImpostoDeduzido movimentoContaImpostoDeduzido = new MovimentoContaImpostoDeduzido();
						
						movimentoContaImpostoDeduzido.setMovimentoContaPrefaturada(movimentoContaPreFaturadaIncluido);
						
						ImpostoTipo impostoTipo = new ImpostoTipo(helper.getTipoImposto());
						movimentoContaImpostoDeduzido.setImpostoTipo(impostoTipo);
						
						movimentoContaImpostoDeduzido.setDescricaoImposto(helper.getDescricaoImposto());
						movimentoContaImpostoDeduzido.setPercentualAliquota(helper.getPercentualAliquota());
						movimentoContaImpostoDeduzido.setValorImposto(helper.getValorImposto());
						movimentoContaImpostoDeduzido.setUltimaAlteracao(new Date());
						
						this.getControladorBatch().inserirObjetoParaBatch(movimentoContaImpostoDeduzido);

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_5)) {
						RotaAtualizacaoSeq atuSeq = new RotaAtualizacaoSeq();
						Imovel imo = new Imovel(helper.getMatriculaImovel());
						
						FiltroImovel filtro = new FiltroImovel();
						filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helper.getMatriculaImovel()));
						filtro.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
						
						Collection<Imovel> colImovel = repositorioUtil.pesquisar(filtro, Imovel.class.getName());
						Iterator itImovel = colImovel.iterator();
						
						imo = (Imovel) itImovel.next();
						
						colImovel = null;
						itImovel = null;
						filtro = null;
						
						rota = imo.getQuadra().getRota();
						
						anoMesFaturamentoGrupoFaturamento = repositorioFaturamento.retornaAnoMesFaturamentoGrupo(imo.getId());
						
						atuSeq.setImovel(imo);
						atuSeq.setRota(rota);
						atuSeq.setAmFaturamento(anoMesFaturamentoGrupoFaturamento);
						atuSeq.setSequencialRota(Integer.parseInt(helper.getSequencialRotaMarcacao()));
						atuSeq.setDtUltimaAlteracao(new Date());

						colAtuSeq.add(atuSeq);
					}
				}
			}

			if (colAtuSeq.size() > 0) {
				repositorioMicromedicao.deletarRotaAtualizacaoSequencial(anoMesFaturamentoGrupoFaturamento, rota.getId());

				this.getControladorBatch().inserirColecaoObjetoParaBatchSemTransacao(colAtuSeq);
			}

		} catch (MobileComunicationException mce) {
			throw mce;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("atencao.erro_inserindo_imovel", e, matriculaImovel);
		}
	}

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 30/07/2009, 14/01/2010
	 * 
	 * @param idArrecadacaoForma
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCartoes(Integer idArrecadacaoForma)
			throws ControladorException {
		Collection<Cliente> retorno = new ArrayList();

		try {

			Collection colecao = repositorioFaturamento
					.pesquisarCartoes(idArrecadacaoForma);

			Iterator colecaoIt = colecao.iterator();

			while (colecaoIt.hasNext()) {
				Object[] obj = (Object[]) colecaoIt.next();

				Cliente cliente = new Cliente();

				cliente.setId((Integer) obj[0]);
				cliente.setNome((String) obj[1]);

				retorno.add(cliente);

			}

			return retorno;

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Atualiza Dados do Parcelamento para Cartão de Crédito
	 * 
	 * @author Hugo Amorim
	 * @date 31/07/2009
	 * 
	 */
	public void confimarParcelamentoCartaoCredito(Parcelamento parcelamento,
			Collection parcelamentoPagamentoCartaoCreditoCollection,
			Collection debitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoAtualizar,
			Usuario usuario) throws ControladorException {
		try {

			Iterator iterator = debitoACobrar.iterator();

			while (iterator.hasNext()) {

				DebitoACobrar dAc = (DebitoACobrar) iterator.next();

				// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO,
						dAc.getId(), dAc.getId(), new UsuarioAcaoUsuarioHelper(
								usuario,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(dAc);
				// ------------ REGISTRAR TRANSAÇÃO ----------------

				getControladorUtil().atualizar(dAc);

			}

			Iterator iteratorCreditoAtualizar = colecaoCreditoAtualizar
					.iterator();

			while (iteratorCreditoAtualizar.hasNext()) {

				CreditoARealizar credito = (CreditoARealizar) iteratorCreditoAtualizar
						.next();

				// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO,
						credito.getId(), credito.getId(),
						new UsuarioAcaoUsuarioHelper(usuario,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(credito);
				// ------------ REGISTRAR TRANSAÇÃO ----------------

				getControladorUtil().atualizar(credito);

			}

			Iterator itera = parcelamentoPagamentoCartaoCreditoCollection
					.iterator();

			while (itera.hasNext()) {

				ParcelamentoPagamentoCartaoCredito parcPagamento = (ParcelamentoPagamentoCartaoCredito) itera
						.next();

				this.repositorioUtil.inserir(parcPagamento);

			}

			this.repositorioUtil.atualizar(parcelamento);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0819] Gerar Historico do Encerramento do Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 04/08/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento()
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0926] - Gerar Bônus de Tarifa Social
	 * 
	 * @author Hugo Amorim
	 * @date 25/08/2008
	 * 
	 * @param faturamentoGrupo
	 * @param sistemaParametro
	 * @param colecaoRotas
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public void gerarBonusTarifaSocial(FaturamentoGrupo faturamentoGrupo,
			SistemaParametro sistemaParametro, Collection<Rota> colecaoRotas,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.ROTA,
				((Rota) Util.retonarObjetoDeColecao(colecaoRotas)).getId());

		try {

			BigDecimal valorBonusSocial = calcularValorBonusSocial(faturamentoGrupo, sistemaParametro);

			Integer anoMes = Util.subtraiAteSeisMesesAnoMesReferencia(faturamentoGrupo.getAnoMesReferencia(), 1);

			CreditoTipo creditoTipo = obterCreditoTipo(CreditoTipo.DESCONTO_TARIFA_SOCIAL);

			if (colecaoRotas != null && !colecaoRotas.isEmpty()) {

				Iterator iteratorRotas = colecaoRotas.iterator();

				while (iteratorRotas.hasNext()) {

					Rota rota = (Rota) iteratorRotas.next();

					// Variáveis para a paginação da pesquisa de Imovel por Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection colecaoImovel = this.pesquisarImovelGrupoFaturamento(rota, numeroIndice, quantidadeRegistros, false, false);

						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							Iterator iteratorImoveis = colecaoImovel.iterator();

							while (iteratorImoveis.hasNext()) {

								Imovel imovel = (Imovel) iteratorImoveis.next();

								apagarDadosCreditoSocialInicioBatch(anoMes, creditoTipo, imovel);

								// Fluxo 4.1 - Verifica se o imovel tem perfil de Tarifa Social,caso não possua passa para o próximo imóvel.
								if (!imovel.getImovelPerfil().getId().equals(ImovelPerfil.TARIFA_SOCIAL)) 
									continue;

								 // Verifica se o imóvel pertence ao R1
								Integer subcategoriaImovel = repositorioFaturamento.pesquisarSubcategoriaImovel(imovel.getId());

								if (!subcategoriaImovel.equals(Subcategoria.SUBCATEGORIA_R1))
									continue;

								/*
								 * autor: Adriana Muniz data: 02/05/2011
								 * Verificar se o imóvel está com a situação da ligação de agua como Ligado, antes de gerar o credito. 
								 * Obs.: Atende ao IS já que mais abaixo verifica se o consumo de agua for nulo, o crédito não é gerado, mas como o
								 * consumo para imoveis do IS não é verificado no GSAN, imoveis suprimidos(e que atendam todas as outras condições 
								 * para concessão do credito), por exemplo, recebem o crédito.
								 */
								if (!imovel.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)) {
									continue;
								}

								Collection collectionConta = this.pesquisarVencimentoConta(imovel.getId(), anoMes);

								// Caso não exista conta com anoMês selecionado, segue para o próximo imóvel.
								if (collectionConta == null || collectionConta.isEmpty()) {
									continue;
								}

								// Gerenciamento dos dados retornados do método pesquisarVencimentoConta().
								Object[] conta = (Object[]) collectionConta.iterator().next();
								Integer idConta = new Integer(conta[0].toString());
								Date dataVencimento = (Date) conta[1];

								/*
								 * autor: Adriana Muniz data: 17/06/2011
								 * verificar se a conta está cancelada, se sim o bônus é concedido sem executar outras verificações
								 * */
								if (!repositorioFaturamento.verificaContaCancelada(idConta)) {

									/**
									 * Data: 22/01/2011
									 * 
									 * Primeira condição Verificação se o dia do vencimento da conta é maior que a data atual, se for procurar 
									 * o pagamento do mês anterior para liberar ou não a geração do crédito
									 * 
									 * Segunda condição Devido o processamento de algumas contas não acontecer no mesmo dia, foi estabelecido 
									 * um prazo de cinco dias posterior ao vencimento. Se a conta se encontrar nessa situação, o pagamento
									 * a ser considerado será do mês posterior.
									 * 
									 */

									SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
									String data = s.format(dataVencimento);
									Date dtVenc = s.parse(data);

									Integer anoMesConsumo = anoMes;
									
									if (dtVenc.after(new Date()) || dataVencimento.equals(new Date())) {
										
										anoMesConsumo = Util.subtraiAteSeisMesesAnoMesReferencia(anoMes, 1);
										
										collectionConta = this.pesquisarVencimentoConta(imovel.getId(), anoMesConsumo);

										// Caso não exista conta com anoMês selecionado, segue para o próximo imóvel.
										if (collectionConta == null || collectionConta.isEmpty()) {
											continue;
										}

										// Gerenciamento dos dados retornados do método pesquisarVencimentoConta().
										conta = (Object[]) collectionConta.iterator().next();
										idConta = new Integer(conta[0].toString());
										dataVencimento = (Date) conta[1];

									} else if (dtVenc.before(new Date())) {
										if (Util.diferencaEntreDatas(dtVenc, new Date()) <= 5) {
											
											anoMesConsumo = Util.subtraiAteSeisMesesAnoMesReferencia(anoMes, 1);
											
											collectionConta = this.pesquisarVencimentoConta(imovel.getId(), anoMesConsumo);

											// FS0002  Caso não exista conta com anoMês selecionado, segue para o próximo imóvel.
											if (collectionConta == null || collectionConta.isEmpty()) {
												continue;
											}

											// Gerenciamento dos dados retornados do método pesquisarVencimentoConta().
											conta = (Object[]) collectionConta.iterator().next();
											idConta = new Integer(conta[0].toString());
											dataVencimento = (Date) conta[1];
										}

									}

									Collection collectionDataPagamento = this.pesquisarDataPagamento(idConta);

									// Fluxo 4.3.2 - Caso não seja localizado o pagamento, segue para o próximo imóvel
									if (collectionDataPagamento == null || collectionDataPagamento.isEmpty()) {
										continue;
									}

									Object arrayDataPagamento = (Object) collectionDataPagamento.iterator().next();

									// Fluxo 4.3.3 - Caso exista pegamento, Verifica quantidade de dias uteis em relação a data de vencimento
									Date dataPagamento = (Date) arrayDataPagamento;

									Municipio municipioImovel = (Municipio) this.getControladorGeografico().pesquisarMunicipioDoImovel(imovel.getId())
											.iterator().next();

									Integer diasUteis = this.getControladorUtil().calcularDiferencaDiasUteisEntreDuasDatas(dataVencimento, dataPagamento,
											municipioImovel);

									// Fluxo 4.3.4 - Verifica se numero dias e maior que "numeroDiasCalculoAcrescimos"
									if (diasUteis.compareTo(new Integer(sistemaParametro.getNumeroDiasCalculoAcrescimos())) > 0) {
										continue;
									}
									
									/*
									 * Data: 22/01/2011 
									 * Verifica se o consumo dos imóveis, que não fazem parte do impressão simultânea, é maior que 10.
									 * Se for maior que 10, não gera o crédito
									 */
									Integer consumoAgua = repositorioFaturamento.pesquisarConsumoAguaImovel(imovel.getId(), anoMesConsumo);

									if (consumoAgua == null || consumoAgua > 10)
										continue;

									Integer qtdContasEmDebito = repositorioFaturamento.obterQuantidadeContasAnterioresVencidasENaoPagas(idConta, dataVencimento);
									if (qtdContasEmDebito > 0) 
										continue;
								}

								// ******************************
								// INICIO DA GERAÇÃO DO CREDITO
								// ******************************

								Date dataAtual = new Date(System.currentTimeMillis());

								// Inclusão do CreditoARealizarGeral
								CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
								creditoARealizarGeral.setIndicadorHistorico(new Short("2"));
								creditoARealizarGeral.setUltimaAlteracao(dataAtual);

								Object idCreditoARealizarGeral = repositorioUtil.inserir(creditoARealizarGeral);

								creditoARealizarGeral.setId(new Integer(idCreditoARealizarGeral.toString()));
								// *************Fim****************

								// Inclusão do CreditoARealizar
								CreditoARealizar creditoARealizar = new CreditoARealizar();

								creditoARealizar.setId(new Integer(idCreditoARealizarGeral.toString()));
								creditoARealizar.setImovel(imovel);
								creditoARealizar.setCreditoTipo(creditoTipo);
								creditoARealizar.setGeracaoCredito(dataAtual);
								creditoARealizar.setAnoMesReferenciaCredito(anoMes);
								creditoARealizar.setAnoMesCobrancaCredito(sistemaParametro.getAnoMesArrecadacao());

								// Seta AnoMesReferenciaContabil, com maior valor entre o ano/Mês da data corrente e o ano/Mês de referencia do faturamento.
								Integer mesDataAtual = Util.getMes(dataAtual);
								Integer mesArreacadao = new Integer(sistemaParametro.getAnoMesFaturamento().toString().substring(4));

								if (mesDataAtual.compareTo(mesArreacadao) > 0) {
									creditoARealizar.setAnoMesReferenciaContabil(Util.recuperaAnoMesDaData(dataAtual));
								} else {
									creditoARealizar.setAnoMesReferenciaContabil(sistemaParametro.getAnoMesFaturamento());
								}
								// ***************************************************************

								creditoARealizar.setValorCredito(valorBonusSocial);
								creditoARealizar.setValorResidualMesAnterior(new BigDecimal("0.0"));
								creditoARealizar.setNumeroPrestacaoCredito(new Short("1"));
								creditoARealizar.setNumeroPrestacaoRealizada(new Short("0"));
								creditoARealizar.setLocalidade(imovel.getLocalidade());
								creditoARealizar.setQuadra(imovel.getQuadra());
								creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
								creditoARealizar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
								creditoARealizar.setNumeroLote(imovel.getLote());
								creditoARealizar.setNumeroSubLote(imovel.getSubLote());
								creditoARealizar.setRegistroAtendimento(null);
								creditoARealizar.setOrdemServico(null);
								creditoARealizar.setLancamentoItemContabil(creditoTipo.getLancamentoItemContabil());
								creditoARealizar.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL));
								creditoARealizar.setDebitoCreditoSituacaoAnterior(null);
								creditoARealizar.setCreditoARealizarGeral(creditoARealizarGeral);
								creditoARealizar.setCreditoOrigem(new CreditoOrigem(CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO));
								creditoARealizar.setUltimaAlteracao(dataAtual);
								creditoARealizar.setUsuario(Usuario.USUARIO_BATCH);

								Object idCreditoARealizar = repositorioUtil.inserir(creditoARealizar);

								creditoARealizar.setId(new Integer(idCreditoARealizar.toString()));
								// *************Fim****************

								// Inclusão do CreditoARealizarCategoria
								// UC0108 - Obter Quantidade de Economias por
								// Categoria
								Collection colecaoCategoriasImovel = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);

								Iterator iteratorColecaoCategoriasImovel = colecaoCategoriasImovel.iterator();

								// UC0185 - Obter Valor por Categoria
								Iterator iteratorColecaoValorPorCategoria = getControladorImovel().obterValorPorCategoria(colecaoCategoriasImovel,
										valorBonusSocial).iterator();

								while (iteratorColecaoCategoriasImovel.hasNext() && iteratorColecaoValorPorCategoria.hasNext()) {

									Categoria categoria = (Categoria) iteratorColecaoCategoriasImovel.next();

									BigDecimal valor = (BigDecimal) iteratorColecaoValorPorCategoria.next();

									CreditoARealizarCategoria creditoARealizarCategoria = new CreditoARealizarCategoria();

									creditoARealizarCategoria.setComp_id(new CreditoARealizarCategoriaPK(creditoARealizar.getId(), categoria.getId()));
									creditoARealizarCategoria.setCreditoARealizar(creditoARealizar);
									creditoARealizarCategoria.setCategoria(categoria);
									creditoARealizarCategoria.setQuantidadeEconomia(categoria.getQuantidadeEconomiasCategoria());
									creditoARealizarCategoria.setValorCategoria(valor);
									creditoARealizarCategoria.setUltimaAlteracao(dataAtual);

									repositorioUtil.inserir(creditoARealizarCategoria);
								}
								// *************Fim****************

								// ******************************
								// FIM DA GERAÇÃO DO CREDITO
								// ******************************

							}// Fim da Iteração dos imoveis
						}

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null || colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}
					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA
				throw new ControladorException("atencao.pesquisa.grupo_rota_vazio");
			}
			// -----------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// -----------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	@SuppressWarnings("rawtypes")
	private void apagarDadosCreditoSocialInicioBatch(Integer anoMes, CreditoTipo creditoTipo, Imovel imovel) throws ControladorException {
		
		FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();

		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO, anoMes));
		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.IMOVEL_ID, imovel.getId()));
		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_CREDITO_TIPO, creditoTipo.getId()));

		FiltroCreditoRealizado filtroRealizado = null; 
		
		Collection colecaoCreditoARealizar = (Collection) this.getControladorUtil().pesquisar(filtroCreditoARealizar, CreditoARealizar.class.getName());

		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {

			CreditoARealizar credito = (CreditoARealizar) colecaoCreditoARealizar.iterator().next();
			
			if (credito.getNumeroPrestacaoRealizada().compareTo(new Short("0")) == 0) {

				FiltroCreditoARealizarCategoria filtroCreditoARealizarCategoria = new FiltroCreditoARealizarCategoria();

				filtroCreditoARealizarCategoria.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizarCategoria.ID_CREDITO_A_REALIZAR, credito.getId()));

				Iterator iteratorCreditoARealizarCategoria = this.getControladorUtil()
						.pesquisar(filtroCreditoARealizarCategoria, CreditoARealizarCategoria.class.getName()).iterator();

				while (iteratorCreditoARealizarCategoria.hasNext()) {
					this.getControladorUtil().remover(iteratorCreditoARealizarCategoria.next());
				}

				FiltroCreditoARealizarGeral filtro = new FiltroCreditoARealizarGeral();

				filtro.adicionarParametro(new ParametroSimples(FiltroCreditoARealizarGeral.ID, credito.getId()));

				CreditoARealizarGeral creditoARealizarGeral = (CreditoARealizarGeral) this.getControladorUtil()
						.pesquisar(filtro, CreditoARealizarGeral.class.getName()).iterator().next();

				if (creditoTipo.getId().equals(CreditoTipo.CREDITO_BOLSA_AGUA)) {
					filtroRealizado = new FiltroCreditoRealizado();
					filtroRealizado.adicionarParametro(new ParametroSimples(FiltroCreditoRealizado.ID_CREDITO_A_REALIZAR, credito.getId()));
					
					Collection colecaoCreditoRealizado = (Collection) this.getControladorUtil().pesquisar(filtroRealizado, CreditoRealizado.class.getName());
					if (colecaoCreditoRealizado == null 
							|| (colecaoCreditoRealizado != null && colecaoCreditoRealizado.isEmpty())) {
						this.getControladorUtil().remover(credito);
						this.getControladorUtil().remover(creditoARealizarGeral);
					}
				} else {
					this.getControladorUtil().remover(credito);
					this.getControladorUtil().remover(creditoARealizarGeral);
				}
				
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void atualizarValorCreditoBolsaAgua(Integer anoMes, Imovel imovel, BigDecimal valorBolsaAgua, Conta conta) throws ControladorException {
		
		FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();

		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO, anoMes));
		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.IMOVEL_ID, imovel.getId()));
		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_CREDITO_TIPO, CreditoTipo.CREDITO_BOLSA_AGUA));

		Collection colecaoCreditoARealizar = (Collection) this.getControladorUtil().pesquisar(filtroCreditoARealizar, CreditoARealizar.class.getName());

		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {

			CreditoARealizar credito = (CreditoARealizar) colecaoCreditoARealizar.iterator().next();

			if (credito.getNumeroPrestacaoRealizada().compareTo(new Short("1")) == 0) {

				BigDecimal novoValorCredito = valorBolsaAgua;
				
				credito.setValorCredito(novoValorCredito);
				credito.setValorResidualMesAnterior(BigDecimal.ZERO);
				
				FiltroCreditoARealizarCategoria filtroCreditoARealizarCategoria = new FiltroCreditoARealizarCategoria();

				filtroCreditoARealizarCategoria.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizarCategoria.ID_CREDITO_A_REALIZAR, credito.getId()));

				
				Iterator<CreditoARealizarCategoria> iteratorCreditoCategoria = this.getControladorUtil()
						.pesquisar(filtroCreditoARealizarCategoria, CreditoARealizarCategoria.class.getName()).iterator();

				CreditoARealizarCategoria creditoCategoria = (CreditoARealizarCategoria) iteratorCreditoCategoria.next();
				creditoCategoria.setValorCategoria(novoValorCredito);
				
								
				getControladorUtil().atualizar(credito);
				getControladorUtil().atualizar(creditoCategoria);
			}
			
			FiltroCreditoRealizado filtroRealizado = new FiltroCreditoRealizado();
			filtroRealizado.adicionarParametro(new ParametroSimples(FiltroCreditoRealizado.CONTA_ID, conta.getId()));
			filtroRealizado.adicionarParametro(new ParametroSimples(FiltroCreditoRealizado.ANO_MES_REFERENCIA_CREDITO, anoMes));
			filtroRealizado.adicionarParametro(new ParametroSimples(FiltroCreditoRealizado.CREDITO_TIPO_ID, CreditoTipo.CREDITO_BOLSA_AGUA));
			
			Collection colecaoRealizado = (Collection) this.getControladorUtil().pesquisar(filtroRealizado, CreditoRealizado.class.getName());
			
			if (colecaoRealizado != null && !colecaoRealizado.isEmpty()) {
				CreditoRealizado realizado = (CreditoRealizado) colecaoRealizado.iterator().next();
				BigDecimal valorAtualizado = valorBolsaAgua;
				realizado.setValorCredito(valorAtualizado);
				
				FiltroCreditoRealizadoCategoria filtroRealizadoCategoria = new FiltroCreditoRealizadoCategoria();

				filtroRealizadoCategoria.adicionarParametro(new ParametroSimples(
						FiltroCreditoRealizadoCategoria.CREDITO_REALIZADO_ID, realizado.getId()));

				Iterator<CreditoRealizadoCategoria> iteratorRealizadoCategoria = this.getControladorUtil()
						.pesquisar(filtroRealizadoCategoria, CreditoRealizadoCategoria.class.getName()).iterator();

				CreditoRealizadoCategoria realizadoCategoria = (CreditoRealizadoCategoria) iteratorRealizadoCategoria.next();
				realizadoCategoria.setValorCategoria(valorAtualizado);
				
				getControladorUtil().atualizar(realizado);
				getControladorUtil().atualizar(realizadoCategoria);
			}		
			
		}
	}

	private CreditoTipo obterCreditoTipo(Integer id) throws ControladorException {
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.ID, id));
		filtroCreditoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

		Collection colecaoTipoCredito = this.getControladorUtil().pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());

		CreditoTipo creditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colecaoTipoCredito);
		return creditoTipo;
	}

	private BigDecimal calcularValorBonusSocial(FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro) throws ControladorException {
		BigDecimal valorBonusSocial;
		Collection collectionCosumoTarifaVigente = this
				.obterConsumoTarifaVigenciaCalcularAguaEsgotoPorMesAno(
						ConsumoTarifa.CONSUMO_NORMAL, null, null,
						faturamentoGrupo.getAnoMesReferencia());

		ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) Util
				.retonarObjetoDeColecao(collectionCosumoTarifaVigente);

		FiltroConsumoTarifaCategoria filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();

		filtroConsumoTarifaCategoria
				.adicionarParametro(new ParametroSimples(
						FiltroConsumoTarifaCategoria.CATEGORIA_ID,
						Categoria.RESIDENCIAL));
		filtroConsumoTarifaCategoria
				.adicionarParametro(new ParametroSimples(
						FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_ID,
						consumoTarifaVigencia.getId()));

		ConsumoTarifaCategoria consumoTarifaCategoria = (ConsumoTarifaCategoria) this
				.getControladorUtil()
				.pesquisar(filtroConsumoTarifaCategoria,
						ConsumoTarifaCategoria.class.getName()).iterator()
				.next();

		valorBonusSocial = (consumoTarifaCategoria.getValorTarifaMinima()
				.multiply(sistemaParametro.getPercentualBonusSocial()))
				.divide(new BigDecimal("100.0"));
		return valorBonusSocial;
	}

	/**
	 * [UC0187] Inserir Guia de Pagamento
	 * 
	 * [FS0020] Imóvel não possui conta para pagamento parcial
	 * 
	 * @author Raphael Rossiter
	 * @date 12/08/2009
	 * 
	 * @param idImovel
	 * @param idDebitoTipo
	 * @throws ControladorException
	 */
	public Collection obterContasParaPagamentoParcial(Integer idImovel,
			Integer idDebitoTipo) throws ControladorException {

		Collection colecaoRetorno = null;

		// Caso o tipo de débito informado seja “Pagamento Antecipado de Conta”.
		if (idDebitoTipo != null
				&& idDebitoTipo.equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA)) {

			/*
			 * Não será permitido inserir guia de pagamento com tipo de débito
			 * igual a pagamento parcial de conta quando o imóvel não tiver sido
			 * informado.
			 */
			if (idImovel == null) {

				// [FS0023]
				throw new ControladorException(
						"atencao.imovel_obrigatorio_para_pagamento_parcial");
			}

			Imovel imovel = new Imovel();
			imovel.setId(idImovel);

			/*
			 * O sistema deverá exibir as contas do imóvel selecionado que
			 * estejam na situação de normal, incluída ou retificada e não
			 * esteja paga (CNTA_AMREFERENCIACONTA da tabela CONTA com IMOV_ID =
			 * IMOV_ID do imóvel e DCST_IDATUAL igual a 0, 1 ou 2 e CNTA_ID não
			 * existe na tabela PAGAMENTO).
			 */

			// 1º Passo: Selecionado as contas
			Collection colecaoContaParaPagamentoParcial = this
					.obterContasImovelManter(imovel,
							DebitoCreditoSituacao.NORMAL,
							DebitoCreditoSituacao.INCLUIDA,
							DebitoCreditoSituacao.RETIFICADA);

			if (colecaoContaParaPagamentoParcial == null
					|| colecaoContaParaPagamentoParcial.isEmpty()) {

				// [FS0020] Imóvel não possui conta para pagamento parcial
				throw new ControladorException(
						"atencao.imovel_sem_conta_pagamento_parcial");
			} else {

				Iterator it = colecaoContaParaPagamentoParcial.iterator();

				while (it.hasNext()) {

					Conta conta = (Conta) it.next();

					// Verificando a existência de pagamento para a conta
					// selecionada
					Pagamento pagamento = this.getControladorArrecadacao()
							.pesquisarPagamentoDeConta(conta.getId());

					if (pagamento == null) {

						if (colecaoRetorno == null) {

							colecaoRetorno = new ArrayList();
							colecaoRetorno.add(conta);
						} else {
							colecaoRetorno.add(conta);
						}
					}
				}

				if (colecaoContaParaPagamentoParcial == null
						|| colecaoContaParaPagamentoParcial.isEmpty()) {

					// [FS0020] Imóvel não possui conta para pagamento parcial
					throw new ControladorException(
							"atencao.imovel_sem_conta_pagamento_parcial");
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * [UC0187] Inserir Guia de Pagamento
	 * 
	 * [FS0021] Valor informado maior ou igual que valor da conta selecionada.
	 * 
	 * @author Raphael Rossiter
	 * @date 13/08/2009
	 * 
	 * @param idConta
	 * @param idDebitoTipo
	 * @param valorTotalServico
	 * @throws ControladorException
	 */
	public void validarValorTotalServicoParaPagamentoParcial(Integer idConta,
			Integer idDebitoTipo, BigDecimal valorTotalServico)
			throws ControladorException {

		if (idConta != null
				&& valorTotalServico != null
				&& (idDebitoTipo != null && idDebitoTipo
						.equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA))) {

			// Obtendo os dados da conta selecionada
			Collection colecaoConta = this.obterConta(idConta);

			Conta conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);

			/*
			 * Caso o valor da guia de pagamento informado pelo usuário for
			 * maior que valor da conta ((CNTA_VLAGUA + CNTA_VLESGOTO +
			 * CNTA_VLDEBITO - CNTA_VLCREDITO - CNTA_VLIMPOSTOS da conta
			 * selecionada) menor ou igual que o valor informado) , exibir a
			 * mensagem “O valor informado deve ser menor que o valor da conta
			 * <<((CNTA_VLAGUA + CNTA_VLESGOTO + CNTA_VLDEBITO - CNTA_VLCREDITO
			 * - CNTA_VLIMPOSTOS>>” e retornar para o passo correspondente no
			 * fluxo principal.
			 */
			if (valorTotalServico.compareTo(conta.getValorTotal()) >= 0) {

				// [FS0021] Valor informado maior ou igual que valor da conta
				// selecionada.
				throw new ControladorException(
						"atencao.valor_guia_maior_valor_conta", null,
						Util.formatarMoedaReal(conta.getValorTotal()));
			}
		}
	}

	/**
	 * Este caso de uso gera os débitos a cobrar referentes aos acréscimos por
	 * impontualidade (multa, juros de mora e atualização monetária)
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade Autor:
	 * 
	 * @author Fernanda Paiva, Pedro Alexandre, Pedro Aexandre
	 * @date 20/04/2006, 31/08/2006, 23/04/2008
	 * 
	 * @param rotas
	 * @param indicadorGeracaoMulta
	 * @param indicadorGeracaoJuros
	 * @param indicadorGeracaoAtualizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(
			Collection rotas, Short indicadorGeracaoMulta,
			Short indicadorGeracaoJuros, Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, boolean indicadorEncerrandoArrecadacao)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			Rota rota = (Rota) Util.retonarObjetoDeColecao(rotas);

			// -------------------------
			//
			// Registrar o início do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA, rota.getId());

			// cria uma coleção de imóvel por rota
			Collection imoveisPorRota = null;
			Collection colecaoDebitoACobrarInserir = new ArrayList();
			Collection colecaoDebitoACobrarCategoriaInserir = new ArrayList();

			// recupera todos os imóveis da coleção de rotas
			imoveisPorRota = this
					.pesquisarImovelGerarAcrescimosImpontualidade(rota);

			SistemaParametro sistemaParametros = getControladorUtil()
					.pesquisarParametrosDoSistema();

			Integer anoMesReferenciaArrecadacao = sistemaParametros
					.getAnoMesArrecadacao();

			Integer anoMesReferenciaFaturamento = sistemaParametros
					.getAnoMesFaturamento();

			Short codigoEmpresaFebraban = sistemaParametros
					.getCodigoEmpresaFebraban();

			Iterator imovelPorRotaIterator = imoveisPorRota.iterator();

			// Item 5.1 [UC0306] - Obter Principal Categoria do Imóvel
			Map<Integer, Categoria> mapImovelPrincipalCategoria = this
					.pesquisarPrincipalCategoriaImovelPorRota(
							codigoEmpresaFebraban, rota);

			/**
			 * Item 5.4 Caso o imóvel possua cliente responsável, recupera o
			 * indicador de cobrança de acrécimos do cliente responsável
			 * (CLIE_ICCOBRANCAACRESCIMOS)
			 */
			Map<Integer, Short> mapIndicadorAcrescimoCliente = this
					.obterIndicadorGeracaoAcrescimosClienteImovel(rota);

			while (imovelPorRotaIterator.hasNext()) {
				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayImoveisPorRota = (Object[]) imovelPorRotaIterator
						.next();

				// instancia um imóvel
				Imovel imovel = new Imovel();
				if (arrayImoveisPorRota[0] != null) {
					// seta o id no imovel
					imovel.setId((Integer) arrayImoveisPorRota[0]);
				}

				if (arrayImoveisPorRota[4] != null) {
					// seta o lote no imovel
					imovel.setLote((Short) arrayImoveisPorRota[4]);
				}

				if (arrayImoveisPorRota[5] != null) {
					// seta o sublote no imovel
					imovel.setSubLote((Short) arrayImoveisPorRota[5]);
				}

				if (arrayImoveisPorRota[1] != null) {
					imovel.setLocalidade(new Localidade((Integer) arrayImoveisPorRota[1]));
				}

				if (arrayImoveisPorRota[3] != null) {
					Quadra quadra = new Quadra((Integer) arrayImoveisPorRota[7]);
					quadra.setNumeroQuadra((Integer) arrayImoveisPorRota[3]);
					imovel.setQuadra(quadra);
				}

				if (arrayImoveisPorRota[2] != null) {
					imovel.setCodigoSetorComercial((Integer) arrayImoveisPorRota[2]);
				}

				/*
				 * Colocado por Raphael Rossiter em 31/05/2007
				 */
				if (arrayImoveisPorRota[8] != null) {
					imovel.setIndicadorDebitoConta((Short) arrayImoveisPorRota[8]);
				}

				// Item 5.1 [UC0306] - Obter Principal Categoria do Imóvel
				Categoria principalCategoria = mapImovelPrincipalCategoria
						.get(imovel.getId());

				boolean flagProximoImovel = false;

				/**
				 * Item 5.2 Caso a principal categoria do imóvel esteja
				 * indicando que somente deve ser gerado acréscimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=NAO) da principal categoria do
				 * imóvel, passa para o próximo imóvel.
				 */
				if (principalCategoria.getIndicadorCobrancaAcrescimos().equals(
						ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				/**
				 * Item 5.3 Caso a principal categoria do imóvel esteja
				 * indicando que não deve ser gerado acréscimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=ENCERRAMENTO_ARRECADACAO) da
				 * principal categoria do imóvel e esteja indicando que não está
				 * sendo encerrada a arrecadação , passa para o próximo imóvel.
				 */
				if ((principalCategoria != null && principalCategoria
						.getIndicadorCobrancaAcrescimos().equals(
								ConstantesSistema.ENCERRAMENTO_ARRECADACAO))
						&& !indicadorEncerrandoArrecadacao) {
					flagProximoImovel = true;
				}

				/**
				 * Item 5.4 Caso o imóvel possua cliente responsável, recupera o
				 * indicador de cobrança de acrécimos do cliente responsável
				 * (CLIE_ICCOBRANCAACRESCIMOS)
				 */
				Short indicadorCobrancaAcrescimos = mapIndicadorAcrescimoCliente
						.get(imovel.getId());

				/**
				 * Item 5.4.1 Caso esteja indicado que não de ve ser gerado
				 * acrécimos por impontualidade para o cliente
				 * (CLIE_ICCOBRANCAACRESCIMOS=NAO) , passar para o próximo
				 * imóvel
				 */
				if (indicadorCobrancaAcrescimos != null
						&& indicadorCobrancaAcrescimos
								.equals(ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				if (indicadorCobrancaAcrescimos != null
						&& (indicadorCobrancaAcrescimos
								.equals(ConstantesSistema.NAO) && !indicadorEncerrandoArrecadacao)) {
					flagProximoImovel = true;
				}

				if (!flagProximoImovel) {

					Date dataAnoMesReferenciaUltimoDia = Util
							.gerarDataApartirAnoMesRefencia(anoMesReferenciaArrecadacao);

					Collection<Integer> colecaoIdsContasAtualizarIndicadorMulta = new ArrayList();

					// cria uma coleção de contas do imovel
					Collection colecaoContaImovel = null;

					/*
					 * Item 5.5 Caso esteja indicado que NÃO esteja sendo
					 * encerrada a arrecadacão seleciona as contas do imóvel com
					 * ano/mês da data de vencimento menor ou igual ao ano/mês
					 * de referência da arrecadação corrente e com situação
					 * atual correspondente a normal, retificada ou incluída e
					 * que não estejam em revisão e que ainda não tiveram
					 * cobrança de multa.
					 */
					if (!indicadorEncerrandoArrecadacao) {
						// recupera todas as contas dos imóveis da coleção
						// de rotas
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovel(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia);
					} else {
						// recupera todas as contas dos imóveis da coleção
						// de rotas
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovelComPagamento(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia,
										anoMesReferenciaArrecadacao);
					}

					Map<Integer, Boolean> mapIndicadorExistePagamentoConta = this
							.pesquisarIndicadorPagamentoConta(
									colecaoContaImovel,
									anoMesReferenciaArrecadacao);

					Short numeroPrestacaoDebito = 1;
					Short numeroPrestacaoCobradas = 0;

					if (!Util.isVazioOrNulo(colecaoContaImovel)) {

						Iterator contasIterator = colecaoContaImovel.iterator();

						while (contasIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosConta = (Object[]) contasIterator
									.next();

							Integer anoMes = Util
									.recuperaAnoMesDaData((Date) dadosConta[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								Integer idConta = (Integer) dadosConta[0];
								Conta conta = new Conta();
								if (dadosConta[0] != null) {
									// seta o id da conta
									conta.setId((Integer) dadosConta[0]);
								}
								if (dadosConta[1] != null) {
									// seta o ano/mes referencia da
									// conta
									conta.setReferencia((Integer) dadosConta[1]);
								}
								if (dadosConta[2] != null) {
									// seta a data de vencimento da
									// conta
									conta.setDataVencimentoConta((Date) dadosConta[2]);
								}
								if (dadosConta[3] != null) {
									// seta o valor da água
									conta.setValorAgua((BigDecimal) dadosConta[3]);
								}
								if (dadosConta[4] != null) {
									// seta o valor do esgoto
									conta.setValorEsgoto((BigDecimal) dadosConta[4]);
								}
								if (dadosConta[5] != null) {
									// seta o valor dos debitos
									conta.setDebitos((BigDecimal) dadosConta[5]);
								}
								if (dadosConta[6] != null) {
									// seta o valor dos creditos
									conta.setValorCreditos((BigDecimal) dadosConta[6]);
								}
								if (dadosConta[7] != null) {
									// seta o indicador de cobranca da
									// multa
									conta.setIndicadorCobrancaMulta((Short) dadosConta[7]);
								}

								// cria uma coleção dos pagamentos da
								// conta com menor
								// data de pagamento
								Date pagamentoContasMenorData = null;
								Integer idArrecadacaoForma = null;

								// recupera todos os pagamentos da conta
								// com menor data de pagamento
								Object[] arrayPagamentoContasMenorData = repositorioFaturamento.obterArrecadacaoFormaPagamentoContasMenorData(conta);

								if (arrayPagamentoContasMenorData != null) {
									idArrecadacaoForma = (Integer) arrayPagamentoContasMenorData[0];
									pagamentoContasMenorData = (Date) arrayPagamentoContasMenorData[1];
								}

								/*
								 * Colocado por Raphael Rossiter em 19/05/2008
								 * Só irá calcular o acréscimo caso o imovel e o
								 * pagamento não sejam débito automático
								 */
								if (idArrecadacaoForma == null
										|| (idArrecadacaoForma != null && !idArrecadacaoForma
												.equals(ArrecadacaoForma.DEBITO_AUTOMATICO))) {

									boolean indicadorExistePagamentoClassificadoConta;
									// caso tenha o id da conta no map
									// então existe pagamento para a conta
									// atual.
									if (mapIndicadorExistePagamentoConta
											.containsKey(idConta)) {
										indicadorExistePagamentoClassificadoConta = true;
									} else {
										indicadorExistePagamentoClassificadoConta = false;
									}

									CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();

									BigDecimal valorConta = conta
											.getValorAgua()
											.add(conta.getValorEsgoto())
											.add(conta.getDebitos())
											.subtract(conta.getValorCreditos());

									// Calcula o valor das multas cobradas
									// para
									// a conta
									BigDecimal valorMultasCobradas = repositorioFaturamento
											.pesquisarValorMultasCobradas(idConta);

									// Item 5.6.2 Calcular os acrescimos por
									// impontualidade
									calcularAcrescimoPorImpontualidade = this
											.getControladorCobranca()
											.calcularAcrescimoPorImpontualidade(
													conta.getReferencia(),
													conta.getDataVencimentoConta(),
													pagamentoContasMenorData,
													valorConta,
													valorMultasCobradas,
													conta.getIndicadorCobrancaMulta(),
													""
															+ anoMesReferenciaArrecadacao,
													conta.getId(),
													ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

									DebitoTipo debitoTipo = null;

									/**
									 * Item 5.6.3 Caso o indicador de geração de
									 * multa corresponda a sim(1) e o valor da
									 * multa seja maior que que zero. Gera o
									 * débito a cobrar referente a multa.
									 */
									if (indicadorGeracaoMulta
											.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade
													.getValorMulta().compareTo(
															BigDecimal.ZERO) == 1) {

										debitoTipo = new DebitoTipo();
										debitoTipo
												.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

										// [SB0001 - Gerar Débito a Cobrar]
										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														imovel,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade
																.getValorMulta(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(this
														.inserirDebitoACobrarCategoriaBatch(
																debitoACobrar,
																debitoACobrar
																		.getImovel()));
									}// if indicador de geração de multa

									/**
									 * Item 5.6.4 Caso o indicador de geração
									 * dos juros de mora corresponda a sim(1) e
									 * o valor dos juros de mora seja maior que
									 * zero Gera o débito a cobrar referente a
									 * juros de mora e exista pagamento para a
									 * conta com data de pagamento diferente de
									 * nulo e ano/mês referência da arrecadação
									 * do pagamento seja menor ou igual ao
									 * ano/mês de arrecadação corente.
									 */
									if (indicadorGeracaoJuros
											.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade
													.getValorJurosMora()
													.compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.JUROS_MORA);

										// [SB0001 - Gerar Débito a Cobrar]
										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														imovel,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade
																.getValorJurosMora(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(this
														.inserirDebitoACobrarCategoriaBatch(
																debitoACobrar,
																debitoACobrar
																		.getImovel()));
									}

									/*
									 * 5.6.5 Caso o indicador de geração de
									 * atualização monetária corresponda a
									 * sim(1) e o valor da atualização monetária
									 * seja maior que zero Gera o débito a
									 * cobrar referente a atualização monetária
									 * e
									 */
									if (indicadorGeracaoAtualizacao
											.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade
													.getValorAtualizacaoMonetaria()
													.compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo
												.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

										// [SB0001 - Gerar Débito a Cobrar]
										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														imovel,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade
																.getValorAtualizacaoMonetaria(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(this
														.inserirDebitoACobrarCategoriaBatch(
																debitoACobrar,
																debitoACobrar
																		.getImovel()));
									}
								} // fim comparacao debito automatico
							} // fim if da comparação da data de pagamento
						} // fim while contas iterator
					} // fim if coleção conta

					/*
					 * Item 5.6.3.2 Atualiza o indicador de que já cobrou multa
					 * da conta com o valor igual a SIM (CNTA_ICCOBRANCAMULTA=1)
					 */
					if (colecaoIdsContasAtualizarIndicadorMulta != null
							&& !colecaoIdsContasAtualizarIndicadorMulta
									.isEmpty()) {
						repositorioFaturamento
								.atualizarIndicadorMultaDeConta(colecaoIdsContasAtualizarIndicadorMulta);
					}

					// cria uma coleção de guias do imóvel
					Collection colecaoGuiasPagamentoImovel = null;

					Collection<Integer> colecaoIdsGuiasPagamentosAtualizarIndicadorMulta = new ArrayList();

					// recupera todas as guias dos imóveis da coleção de rotas
					colecaoGuiasPagamentoImovel = repositorioFaturamento
							.obterGuiasPagamentoImovel(imovel.getId(),
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									anoMesReferenciaArrecadacao);

					if (!Util.isVazioOrNulo(colecaoGuiasPagamentoImovel)) {

						Iterator guiasPagamentoIterator = colecaoGuiasPagamentoImovel
								.iterator();

						while (guiasPagamentoIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosGuiasPagamento = (Object[]) guiasPagamentoIterator
									.next();

							Integer anoMes = Util
									.recuperaAnoMesDaData((Date) dadosGuiasPagamento[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								GuiaPagamento guiaPagamento = new GuiaPagamento();
								if (dadosGuiasPagamento[0] != null) {
									// seta o id da guia
									guiaPagamento
											.setId((Integer) dadosGuiasPagamento[0]);
								}
								if (dadosGuiasPagamento[1] != null) {
									// seta o ano/mes referencia da guia
									guiaPagamento
											.setAnoMesReferenciaContabil((Integer) dadosGuiasPagamento[1]);
								}
								if (dadosGuiasPagamento[2] != null) {
									// seta a data de vencimento da
									// conta
									guiaPagamento
											.setDataVencimento((Date) dadosGuiasPagamento[2]);
								}
								if (dadosGuiasPagamento[3] != null) {
									// seta o valor dos debitos
									guiaPagamento
											.setValorDebito((BigDecimal) dadosGuiasPagamento[3]);
								}
								if (dadosGuiasPagamento[4] != null) {
									// seta o indicador de cobranca da
									// multa
									guiaPagamento
											.setIndicadoCobrancaMulta((Short) dadosGuiasPagamento[4]);
								}

								DebitoTipo debitoTipoGuiaPagamento = new DebitoTipo();
								if (dadosGuiasPagamento[5] != null) {
									debitoTipoGuiaPagamento
											.setId((Integer) dadosGuiasPagamento[5]);
									guiaPagamento
											.setDebitoTipo(debitoTipoGuiaPagamento);
								}

								Date menorDataPagamento = repositorioCobranca
										.pesquisarMenorDataPagamentoGuiaPagamento(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId());

								boolean indicadorExistePagamentoClassificadoGuiaPagamento = repositorioFaturamento
										.obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId(),
												anoMesReferenciaArrecadacao);

								// [UC0216] Calcular Acréscimos por
								// Impontualidade
								CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();
								calcularAcrescimoPorImpontualidade = this
										.getControladorCobranca()
										.calcularAcrescimoPorImpontualidade(
												guiaPagamento
														.getAnoMesReferenciaContabil(),
												guiaPagamento
														.getDataVencimento(),
												menorDataPagamento,
												guiaPagamento.getValorDebito(),
												BigDecimal.ZERO,
												guiaPagamento
														.getIndicadoCobrancaMulta(),
												""
														+ anoMesReferenciaArrecadacao,
												null,
												ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

								DebitoTipo debitoTipo = null;

								/*
								 * Item 5.8.3 Caso o indicador de geração de
								 * multa corresponda a sim(1) e o valor da multa
								 * seja maior que que zero. Gera o débito a
								 * cobrar referente a multa.
								 */
								if (indicadorGeracaoMulta
										.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade
												.getValorMulta().compareTo(
														BigDecimal.ZERO) == 1) {

									debitoTipo = new DebitoTipo();
									debitoTipo
											.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

									// [SB0001 - Gerar Débito a Cobrar]
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel, 
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade
													.getValorMulta(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(this
													.inserirDebitoACobrarCategoriaBatch(
															debitoACobrar,
															debitoACobrar
																	.getImovel()));
								}

								/*
								 * Item 5.8.4 Caso o indicador de geração dos
								 * juros de mora corresponda a sim(1) e o valor
								 * dos juros de mora seja maior que zero e
								 * exista pagamento para a guia de pagamento com
								 * situação atual igual a classificado. Gera o
								 * débito a cobrar referente a juros de mora.
								 */
								if (indicadorGeracaoJuros
										.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade
												.getValorJurosMora().compareTo(
														BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.JUROS_MORA);

									// [SB0001 - Gerar Débito a Cobrar]
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel, 
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade
													.getValorJurosMora(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(this
													.inserirDebitoACobrarCategoriaBatch(
															debitoACobrar,
															debitoACobrar
																	.getImovel()));
								}

								/*
								 * Item 5.8.5 Caso o indicador de geração de
								 * atualização monetária corresponda a sim(1) e
								 * o valor da atualização monetária seja maior
								 * que zero e exista pagamento para a guia de
								 * pagamento com situação atual igual a
								 * classificado. Gera o débito a cobrar
								 * referente a atualização monetária.
								 */
								if (indicadorGeracaoAtualizacao
										.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade
												.getValorAtualizacaoMonetaria()
												.compareTo(BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo
											.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

									// [SB0001 - Gerar Débito a Cobrar]
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade
													.getValorAtualizacaoMonetaria(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(this
													.inserirDebitoACobrarCategoriaBatch(
															debitoACobrar,
															debitoACobrar
																	.getImovel()));
								}
							} // fim if da comparacao da data de
								// pagamento
						} // fim while contasiterator
					} // fim if colecaoguia

					if (colecaoIdsGuiasPagamentosAtualizarIndicadorMulta != null
							&& !colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
									.isEmpty()) {
						repositorioFaturamento
								.atualizarIndicadorMultaDeGuiaPagamento(colecaoIdsGuiasPagamentosAtualizarIndicadorMulta);
					}
				} // fim if flagProximoImovel
			}// fim while imovelporrotaiterator

			// Inserir os débitos a cobrar
			if (colecaoDebitoACobrarInserir != null
					&& !colecaoDebitoACobrarInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(
						colecaoDebitoACobrarInserir);
			}

			// Inseri os débitos a cobrar por categoria
			if (colecaoDebitoACobrarCategoriaInserir != null
					&& !colecaoDebitoACobrarCategoriaInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(
						colecaoDebitoACobrarCategoriaInserir);
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

			return null;

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}

	}

	/**
	 * Metodo que retorna os imóveis das quadras pertencentes às rotas
	 * 
	 * Utilizado pelo [UC0302] Gerar Débitos a Cobrar de Acréscimos por
	 * Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2009
	 * 
	 * @param idRota
	 * @return Collection
	 */
	public Collection pesquisarImovelGerarAcrescimosImpontualidade(Rota rota) throws ControladorException {

		Collection colecaoImoveis = null;

		try {
			if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {
				colecaoImoveis = repositorioFaturamento.pesquisarImoveisDasQuadrasPorRota(rota.getId());
			} else {
				colecaoImoveis = repositorioFaturamento.pesquisarImoveisDasQuadrasPorRotaAlternativa(rota.getId());
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		return colecaoImoveis;
	}

	/**
	 * Permite gerar os débitos de doações para os imóveis contidos na coleção
	 * [UC0394] Gerar Débitos a Cobrar de Doações
	 * 
	 * @author César Araújo, Raphael Rossiter
	 * @date 05/08/2006, 26/08/2009
	 * 
	 * @param Collection
	 *            <Rota> rotas
	 * @param int idFuncionalidadeIniciada
	 * 
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		/*
		 * Registrar o início do processamento da Unidade de Processamento do
		 * Batch
		 * 
		 * Colocado por Raphael Rossiter em 11/01/2007
		 */
		// -------------------------
		int idUnidadeIniciada = 0;

		Rota rota = (Rota) Util.retonarObjetoDeColecao(rotas);

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((Rota) Util.retonarObjetoDeColecao(rotas)).getId());

		try {

			/** * Declara variáveis locais ** */
			Imovel imovel = null;
			Quadra quadra = null;
			DebitoTipo debitoTipo = null;
			Localidade localidade = null;
			CobrancaForma cobrancaForma = null;
			DebitoACobrar debitoACobrar = null;
			Integer idDebitoACobrarGeral = null;
			FinanciamentoTipo financiamentoTipo = null;
			DebitoACobrarGeral debitoACobrarGeral = null;
			DebitoCreditoSituacao debitoCreditoSituacao = null;
			LancamentoItemContabil lancamentoItemContabil = null;
			Collection<ImovelCobrarDoacaoHelper> colecaoImovelCobrarDoacaoHelper = null;

			/**
			 * Pesquisa Imoveis que tem doação a faturar baseando-se numa
			 * coleção de rotas *
			 */

			// Parte Alterada por Sávio Luiz Data:09/05/2007
			// Parte que remove os débitos a cobrar, do ano mes de faturamento
			// para as rotas recebidas, caso já exista na base.
			/*
			 * SistemaParametro sistemaParametro = getControladorUtil()
			 * .pesquisarParametrosDoSistema();
			 */
			Integer anoMesFaturamentoGrupo = repositorioFaturamento
					.retornaAnoMesFaturamentoGrupoDaRota(rota.getId());

			Collection colecaoIdsDebitoACobrar = repositorioFaturamento
					.pesquisarDebitoACobrarParaRemocao(rota,
							anoMesFaturamentoGrupo);

			if (colecaoIdsDebitoACobrar != null
					&& !colecaoIdsDebitoACobrar.isEmpty()) {

				repositorioFaturamento
						.deletarDebitosACobrarCategoria(colecaoIdsDebitoACobrar);

				getControladorCobranca()
						.atualizarParcelamentoItensDebitoACobrar(
								colecaoIdsDebitoACobrar);

				getControladorCobranca().removerDocumentosItensDebitoACobrar(
						colecaoIdsDebitoACobrar);

				repositorioFaturamento
						.deletarDebitosACobrar(colecaoIdsDebitoACobrar);

			}

			colecaoImovelCobrarDoacaoHelper = this
					.pesquisarImovelDoacaoPorRota(rota);

			for (ImovelCobrarDoacaoHelper imovelCobrarDoacaoHelper : colecaoImovelCobrarDoacaoHelper) {

				/*
				 * Colocado por Hugo Amorim em 07/04/2010
				 * 
				 * OBJ: Verificar a existência de débito a cobrar de doação
				 * ativo para o imóvel.
				 * 
				 * Caso já exista débito a cobrar de doação ativo para o imóvel
				 * o sistema deverá passar para o próximo imóvel
				 */

				Collection colecaoDebitosACobrarDeDoacaoAtivos = repositorioFaturamento
						.pesquisarDebitoACobrarDeDoacaoAtivos(
								imovelCobrarDoacaoHelper.getIdImovel(),
								anoMesFaturamentoGrupo,
								imovelCobrarDoacaoHelper.getIdDebitoTipo());

				if (colecaoDebitosACobrarDeDoacaoAtivos == null
						|| colecaoDebitosACobrarDeDoacaoAtivos.isEmpty()) {

					if (imovelCobrarDoacaoHelper.getAnoMesReferenciaFinal() != null) {
						this.efetuarCancelamentoImovelParaDoacoes(
								imovelCobrarDoacaoHelper, rota);
					}

					/** * Instancia debitoACobrarGeral ** */
					debitoACobrarGeral = new DebitoACobrarGeral();

					/** * preenche os valores para debitoACobrarGeral ** */
					debitoACobrarGeral
							.setIndicadorHistorico(ImovelCobrarDoacaoHelper.INDICADOR_HISTORICO);
					debitoACobrarGeral.setUltimaAlteracao(new Date());

					/** * insere debitoACobrarGeral na base ** */
					idDebitoACobrarGeral = repositorioFaturamento
							.inserirDebitoACobrarGeral(debitoACobrarGeral);

					debitoACobrarGeral.setId(idDebitoACobrarGeral);

					/** * Instancia debitoACobrar ** */
					debitoACobrar = new DebitoACobrar();

					/** * preenche os valores para debitoACobrar ** */
					debitoACobrar.setId(idDebitoACobrarGeral);
					debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

					imovel = new Imovel();
					imovel.setId(imovelCobrarDoacaoHelper.getIdImovel());
					debitoACobrar.setImovel(imovel);

					debitoTipo = new DebitoTipo();
					debitoTipo
							.setId(imovelCobrarDoacaoHelper.getIdDebitoTipo());
					debitoACobrar.setDebitoTipo(debitoTipo);

					debitoACobrar.setGeracaoDebito(new Date());
					debitoACobrar.setAnoMesReferenciaDebito(null);
					debitoACobrar.setAnoMesCobrancaDebito(null);

					// alterado por: Rômulo Aurélio CRC 4662 Data:21/06/2010
					// analista responsavel: Eduardo Borges

					int anoMesReferenciaContabil = anoMesFaturamentoGrupo;

					int anoMesCorrente = Util.getAnoMesComoInt(new Date());

					// anoMesReferenciaContabil recebe o maior valor entre
					// ano/mes da data corrente
					// e o ano/mes de referencia do faturamento
					if (anoMesFaturamentoGrupo < anoMesCorrente) {
						anoMesReferenciaContabil = anoMesCorrente;
					}

					debitoACobrar
							.setAnoMesReferenciaContabil(anoMesReferenciaContabil);

					// Alterado por Rômulo Aurélio
					// Data: 05/10/2010
					// Analista: Eduardo Borges
					debitoACobrar
							.setAnoMesReferenciaDebito(anoMesFaturamentoGrupo);

					/*
					 * Caso exista mês/ano de referência inicial e final da
					 * doação (IMDO_AMREFERENCIAINICAL<> NULL e
					 * IMDO_AMREFERENCIAFINAL<>NULL), calcular a quantidade de
					 * meses entre as referências mais um
					 * (DBAC_NNPRESTACAODEBITO= IMDO_AMREFERENCIAFINAL -
					 * IMDO_AMREFERENCIAINICAL + 1) , caso contrário, mover um
					 * (DBAC_NNPRESTACAODEBITO=1)
					 */
					Short numeroPrestacaoDebito = ImovelCobrarDoacaoHelper.NUMERO_PRESTACAO_DEBITO;

					if (imovelCobrarDoacaoHelper.getAnoMesReferenciaInicial() != null
							&& imovelCobrarDoacaoHelper
									.getAnoMesReferenciaFinal() != null) {
						Integer qtdMeses = Util.retornaQuantidadeMeses(
								imovelCobrarDoacaoHelper
										.getAnoMesReferenciaFinal(),
								imovelCobrarDoacaoHelper
										.getAnoMesReferenciaInicial());
						qtdMeses = qtdMeses + 1;

						numeroPrestacaoDebito = Short.parseShort(qtdMeses
								.toString());
					}

					debitoACobrar
							.setNumeroPrestacaoDebito(numeroPrestacaoDebito);

					debitoACobrar.setValorDebito(imovelCobrarDoacaoHelper
							.getValorDebito().multiply(
									new BigDecimal(numeroPrestacaoDebito)));

					/*
					 * Caso exista mês/ano de referência inicial e final da
					 * doação (IMDO_AMREFERENCIAINICAL<> NULL e
					 * IMDO_AMREFERENCIAFINAL<>NULL), calcular a quantidade de
					 * meses entre a referência inicial e a referencia atual do
					 * grupo de faturamento da rota (DBAC_NNPRESTACAOCOBRADAS=
					 * FTGR_AMREFERENCIA - IMDO_AMREFERENCIAINICAL), caso
					 * contrário, mover zero (DBAC_NNPRESTACAOCOBRADAS=0)
					 */
					Short numeroPrestacaoCobradas = ImovelCobrarDoacaoHelper.NUMERO_PRESTACAO_COBRADA;

					if (imovelCobrarDoacaoHelper.getAnoMesReferenciaInicial() != null
							&& imovelCobrarDoacaoHelper
									.getAnoMesReferenciaFinal() != null) {

						FiltroRota filtroRota = new FiltroRota();

						filtroRota.adicionarParametro(new ParametroSimples(
								FiltroRota.ID_ROTA, rota.getId()));

						filtroRota
								.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);

						FaturamentoGrupo faturamentoGrupo = ((Rota) this
								.getControladorUtil()
								.pesquisar(filtroRota, Rota.class.getName())
								.iterator().next()).getFaturamentoGrupo();

						Integer qtdMeses = Util.retornaQuantidadeMeses(
								faturamentoGrupo.getAnoMesReferencia(),
								imovelCobrarDoacaoHelper
										.getAnoMesReferenciaInicial());

						numeroPrestacaoCobradas = Short.parseShort(qtdMeses
								.toString());
					}

					debitoACobrar
							.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);

					localidade = new Localidade();
					localidade
							.setId(imovelCobrarDoacaoHelper.getIdLocalidade());
					debitoACobrar.setLocalidade(localidade);

					quadra = new Quadra();
					quadra.setId(imovelCobrarDoacaoHelper.getIdQuadra());
					debitoACobrar.setQuadra(quadra);

					debitoACobrar
							.setCodigoSetorComercial(imovelCobrarDoacaoHelper
									.getCodigoSetorComercial());
					debitoACobrar.setNumeroQuadra(imovelCobrarDoacaoHelper
							.getNumeroQuadra());
					debitoACobrar.setNumeroLote(imovelCobrarDoacaoHelper
							.getNumeroLote());
					debitoACobrar.setNumeroSubLote(imovelCobrarDoacaoHelper
							.getNumeroSubLote());
					debitoACobrar
							.setPercentualTaxaJurosFinanciamento(ImovelCobrarDoacaoHelper.TAXA_JURO_FINANCIAMENTO);
					debitoACobrar.setRegistroAtendimento(null);
					debitoACobrar.setOrdemServico(null);

					financiamentoTipo = new FinanciamentoTipo();
					financiamentoTipo.setId(imovelCobrarDoacaoHelper
							.getFinanciamentoTipo());
					debitoACobrar.setFinanciamentoTipo(financiamentoTipo);

					lancamentoItemContabil = new LancamentoItemContabil();
					lancamentoItemContabil.setId(imovelCobrarDoacaoHelper
							.getLancamentoItemContabil());
					debitoACobrar
							.setLancamentoItemContabil(lancamentoItemContabil);

					debitoCreditoSituacao = new DebitoCreditoSituacao();
					debitoCreditoSituacao
							.setId(ImovelCobrarDoacaoHelper.DEBITO_CREDITO_SITUACAO_ATUAL);
					debitoACobrar
							.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
					debitoACobrar.setDebitoCreditoSituacaoAnterior(null);
					debitoACobrar.setParcelamentoGrupo(null);

					cobrancaForma = new CobrancaForma();
					cobrancaForma
							.setId(ImovelCobrarDoacaoHelper.COBRANCA_FORMA);
					debitoACobrar.setCobrancaForma(cobrancaForma);
					debitoACobrar.setUltimaAlteracao(new Date());

					/** * Insere debitoACobrar na base ** */
					repositorioFaturamento.inserirDebitoACobrar(debitoACobrar);

					/** * Insere debitoACobrarCategoria ** */
					this.inserirDebitoACobrarCategoria(debitoACobrar, imovel);
				}
			}

			// --------------------------------------------------------
			// Registrar o fim da execução da Unidade de Processamento
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}
	}

	/**
	 * Permite pesquisar imóvel doação baseando-se em rotas [UC0394] Gerar
	 * Débitos a Cobrar de Doações
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 * 
	 * @param idRota
	 * @return Collection<ImovelCobrarDoacaoHelper> - Coleção de
	 *         ImovelCobrarDoacaoHelper já com as informações necessárias para
	 *         registro da cobrança
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelDoacaoPorRota(Rota rota)
			throws ControladorException {

		Collection colecaoImoveis = null;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				colecaoImoveis = repositorioFaturamento
						.pesquisarImovelDoacaoPorRota(rota.getId());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				colecaoImoveis = repositorioFaturamento
						.pesquisarImovelDoacaoPorRotaAlternativa(rota.getId());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarVencimentoConta(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException {
		Collection retorno;

		try {

			retorno = this.repositorioFaturamento.pesquisarVencimentoConta(
					idImovel, anoMesReferencia);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * Retorna a qualidade de água associada ao imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 07/09/2009
	 * @param imovel
	 * @return QualidadeAgua
	 * @throws ControladorException
	 */
	public QualidadeAgua getQualidadeAgua(Imovel imovel, Integer anoMes)
			throws ControladorException {

		QualidadeAgua retorno = null;

		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
				FiltroQualidadeAgua.LOCALIDADE_ID, imovel.getLocalidade()
						.getId()));
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
				FiltroQualidadeAgua.SETOR_COMERCIAL_CODIGO, imovel
						.getSetorComercial().getCodigo()));
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
				FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMes));

		Collection colecaoQualidadeAgua = getControladorUtil().pesquisar(
				filtroQualidadeAgua, QualidadeAgua.class.getName());

		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
			retorno = (QualidadeAgua) Util
					.retonarObjetoDeColecao(colecaoQualidadeAgua);
		} else {
			filtroQualidadeAgua.limparListaParametros();
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID, imovel.getLocalidade()
							.getId()));
			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
					FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMes));

			colecaoQualidadeAgua = getControladorUtil().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());

			if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
				retorno = (QualidadeAgua) Util
						.retonarObjetoDeColecao(colecaoQualidadeAgua);
			} else {
				filtroQualidadeAgua.limparListaParametros();
				filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
						FiltroQualidadeAgua.LOCALIDADE_ID));
				filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
						FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMes));

				colecaoQualidadeAgua = getControladorUtil().pesquisar(
						filtroQualidadeAgua, QualidadeAgua.class.getName());

				if (colecaoQualidadeAgua != null
						&& !colecaoQualidadeAgua.isEmpty()) {
					retorno = (QualidadeAgua) Util
							.retonarObjetoDeColecao(colecaoQualidadeAgua);
				}
			}
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idConta
	 * @return dataPagamento
	 */
	public Collection pesquisarDataPagamento(Integer idContal)
			throws ControladorException {
		Collection retorno;
		try {

			retorno = this.repositorioFaturamento
					.pesquisarDataPagamento(idContal);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * Atualiza um conjunto de leituras e anormalidades bem como seu consumo e
	 * suas contas prefaturadas
	 */
	public RetornoAtualizarFaturamentoMovimentoCelularHelper atualizarFaturamentoMovimentoCelular( 
			BufferedReader buffer, String nomeArquivo, boolean offline, 
			boolean finalizarArquivo, Integer idRota,
			ArquivoTextoRetornoIS arquivoTextoRetornoIS, BufferedReader bufferOriginal)
			throws ControladorException {

		RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = new RetornoAtualizarFaturamentoMovimentoCelularHelper();

		Object[] retornoIncluirMovimento = null;
		Collection<AtualizarContaPreFaturadaHelper> colecaoAtualizarContaPreFaturadaHelper = null;
		byte[] relatorio = null;

		if (buffer == null) {
			throw new ControladorException("atencao.arquivo_sem_dados", null, nomeArquivo);
		}

		try {
			// Verificamos se algum erro de comunição previsto aconteceu. Caso positivo, incluimos a mensagem retornada no retorno da função.
			retornoIncluirMovimento = this.incluirMovimentoContaPreFaturada(buffer, idRota, arquivoTextoRetornoIS, bufferOriginal);
		} catch (MobileComunicationException mce) {
			if (offline) {
				throw new ControladorException(mce.getMessage(), mce);
			} else {
				// Setamos e retornamos
				mce.printStackTrace();
				retorno.setMensagemComunicacaoServidorCelular("mensagem="
						+ ConstantesAplicacao.get(mce.getMessage()));
				return retorno;
			}
		}

		try {
			relatorio = (byte[]) retornoIncluirMovimento[0];
			colecaoAtualizarContaPreFaturadaHelper = (Collection) retornoIncluirMovimento[1];
			Collection<MovimentoContaPrefaturada> colContaPreFaturada = new ArrayList();
			Collection<Integer> colIdsImoveisAtualizar = new ArrayList();

			if (relatorio != null) {
				retorno.setRelatorioConsistenciaProcessamento(relatorio);
				retorno.setIndicadorSucessoAtualizacao(false);
				return retorno;
			}

			// Pesquisamos a rota que utilizaremos na consistencia dos dados
			Rota rota = null;
			if (colecaoAtualizarContaPreFaturadaHelper != null && !colecaoAtualizarContaPreFaturadaHelper.equals("")) {

				for (AtualizarContaPreFaturadaHelper helperCabecalho : colecaoAtualizarContaPreFaturadaHelper) {
					if (rota == null || rota.equals("")) {
						rota = pesquisarRotaImpressaoSimultanea(helperCabecalho);
					}

					Collection<MovimentoContaPrefaturada> colMovimentoContaPreFaturada = verificarExistenciaListaMovimentoContaPrefaturada(helperCabecalho);
					if (colMovimentoContaPreFaturada != null && !colMovimentoContaPreFaturada.isEmpty()) {
						colContaPreFaturada.addAll(colMovimentoContaPreFaturada);

						for (MovimentoContaPrefaturada prefaturada : colMovimentoContaPreFaturada) {
							colIdsImoveisAtualizar.add(prefaturada.getImovel().getId());
						}
					}
				}
			}

			this.processarMovimentoContaPrefaturada(rota, colContaPreFaturada,true);
			this.atualizarInformacoesImpressaoExtratoQuitacao(colContaPreFaturada);
			
			if (offline) {
				relatorio = this.geraResumoLeiturasAnormalidadesImpressaoSimultanea(colContaPreFaturada);
				retorno.setRelatorioConsistenciaProcessamento(relatorio);
			}

			// Verificamos se já foi enviado algum tipo de mensagem nessa requisição
			if (rota != null) {
				// Caso não seja finalizar a rota, então verifica se tem releitura para a rota
				if (!finalizarArquivo) {
					if (retorno.getMensagemComunicacaoServidorCelular() == null) {
						String releituraImoveis = this.verificarSolicitacaoReleituraImovelImpressaoSimultanea(rota.getId());

						if (releituraImoveis != null) {
							retorno.setMensagemComunicacaoServidorCelular(releituraImoveis);
						}
					}
				}

				Integer anoMesFaturamentoGrupoRota = this.retornaAnoMesFaturamentoGrupoDaRota(rota.getId());
				
				FiltroReleituraMobile filtroReleituraMobile = new FiltroReleituraMobile();
				filtroReleituraMobile.adicionarParametro(new ParametroSimples(FiltroReleituraMobile.ANO_MES_FATURAMENTO,anoMesFaturamentoGrupoRota));
				filtroReleituraMobile.adicionarParametro(new ParametroSimplesIn(FiltroReleituraMobile.ID_IMOVEL, colIdsImoveisAtualizar));
				filtroReleituraMobile.adicionarParametro(new ParametroSimples(FiltroReleituraMobile.INDICADOR_RELEITURA,ConstantesSistema.NAO));
				
				Collection<ReleituraMobile> colReleituraMobile = this.getControladorUtil().pesquisar(filtroReleituraMobile,ReleituraMobile.class.getName());

				if (colReleituraMobile != null && colReleituraMobile.size() > 0) {
					for (ReleituraMobile mobile : colReleituraMobile) {

						FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
						filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.MATRICULA,mobile.getImovel().getId()));
						filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,anoMesFaturamentoGrupoRota));

						Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada = getControladorUtil()
								.pesquisar(filtroMovimentoContaPrefaturada,MovimentoContaPrefaturada.class.getName());

						Integer leituraAgua = null;
						Integer leituraPoco = null;

						LeituraAnormalidade leituraAnormalidadeAgua = null;
						LeituraAnormalidade leituraAnormalidadePoco = null;

						for (MovimentoContaPrefaturada prefaturada : colMovimentoContaPrefaturada) {
							if (prefaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
								leituraAgua = prefaturada.getLeituraHidrometro();
								leituraAnormalidadeAgua = prefaturada.getLeituraAnormalidadeLeitura();
							} else {
								leituraPoco = prefaturada.getLeituraHidrometro();
								leituraAnormalidadePoco = prefaturada.getLeituraAnormalidadeLeitura();
							}
						}

						mobile.setLeituraAnteriorAgua(mobile.getLeituraAtualAgua());
						mobile.setLeituraAnteriorPoco(mobile.getLeituraAtualPoco());
						mobile.setLeituraAnormalidadeAnteriorAgua(mobile.getLeituraAnormalidadeAtualAgua());
						mobile.setLeituraAnormalidadeAnteriorPoco(mobile.getLeituraAnormalidadeAtualPoco());
						mobile.setLeituraAtualAgua(leituraAgua);
						mobile.setLeituraAtualPoco(leituraPoco);
						mobile.setLeituraAnormalidadeAtualAgua(leituraAnormalidadeAgua);
						mobile.setLeituraAnormalidadeAtualPoco(leituraAnormalidadePoco);
						mobile.setIndicadorReleitura(new Integer(ConstantesSistema.SIM));
						mobile.setUltimaAlteracao(new Date());
					}

					this.getControladorBatch().atualizarColecaoObjetoParaBatch(colReleituraMobile);
				}
			}

			retorno.setIndicadorSucessoAtualizacao(true);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/*
	 * Atualiza um conjunto de leituras e anormalidades bem como seu consumo e
	 * suas contas prefaturadas
	 * 
	 * @author Sávio Luiz @date 24/02/2010 @param buffer - BufferedReader com o
	 * arquivo selecionado @return void @throws ControladorException
	 */
	private Rota pesquisarRotaImpressaoSimultanea(
			AtualizarContaPreFaturadaHelper helperCabecalho)
			throws ControladorException {
		Rota rota = null;

		try {
			if (helperCabecalho.getMatriculaImovel() != null
					&& helperCabecalho.getAnoMesFaturamento() != null) {

				FiltroMovimentoRoteiroEmpresa filtroMovimentoRoteiroEmpresa = new FiltroMovimentoRoteiroEmpresa();

				filtroMovimentoRoteiroEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");
				filtroMovimentoRoteiroEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("rota.leiturista");

				filtroMovimentoRoteiroEmpresa
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				filtroMovimentoRoteiroEmpresa
						.adicionarParametro(new ParametroSimples(
								FiltroMovimentoRoteiroEmpresa.IMOVEL_ID,
								helperCabecalho.getMatriculaImovel()));

				filtroMovimentoRoteiroEmpresa
						.adicionarParametro(new ParametroSimples(
								FiltroMovimentoRoteiroEmpresa.ANO_MES_MOVIMENTO,
								helperCabecalho.getAnoMesFaturamento()));

				Collection<Rota> colMovimmentoRoteiroEmpresa = (Collection<Rota>) repositorioUtil
						.pesquisar(filtroMovimentoRoteiroEmpresa,
								MovimentoRoteiroEmpresa.class.getName());
				MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) Util
						.retonarObjetoDeColecao(colMovimmentoRoteiroEmpresa);
				if (movimentoRoteiroEmpresa != null
						&& !movimentoRoteiroEmpresa.equals("")) {
					rota = movimentoRoteiroEmpresa.getRota();
				}
			}
			// caso não tenha a rota em movimento_roteiro_empresa, pesquisar
			// pelo imóvel
			if (rota == null || rota.equals("")) {
				
				/*
				 * 
				 * Alteracao para, quando for rota alternativa, bucar a rota 
				 * pela rota alternativa do imóvel, e não pelo setor comercial 
				 */
				Imovel imovel = this.getControladorImovel().pesquisarImovel(helperCabecalho
						.getMatriculaImovel());
				
				if (imovel.getRotaAlternativa() != null && imovel.getRotaAlternativa().getId() != null) {
					rota = this.getControladorMicromedicao().pesquisarRota(imovel.getRotaAlternativa().getId());
				} else {
					FiltroRota filtroRota = new FiltroRota();
					filtroRota
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
					filtroRota
					.adicionarCaminhoParaCarregamentoEntidade("leiturista");
					filtroRota
					.adicionarParametro(new ParametroSimples(
							FiltroRota.CODIGO_ROTA, helperCabecalho
							.getCodigoRota()));
					
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.SETOR_COMERCIAL_CODIGO, helperCabecalho
							.getCodigoSetorComercial()));
					
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.LOCALIDADE_ID, helperCabecalho
							.getLocalidade()));
					
					Collection<Rota> colRota = (Collection<Rota>) repositorioUtil
					.pesquisar(filtroRota, Rota.class.getName());
					rota = (Rota) Util.retonarObjetoDeColecao(colRota);
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return rota;
	}

	/**
	 * PesquisarDataPrevistaFaturamentoAtividadeCronograma
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 11/09/2009
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Date pesquisarDataPrevistaFaturamentoAtividadeCronograma(
			Integer idImovel, int quantidadeMeses) throws ControladorException {

		Date dataRealizacao = null;
		try {
			Object[] parmsFaturamentoGrupo = repositorioFaturamento
					.pesquisarParmsFaturamentoGrupo(idImovel);
			Integer idFaturamentoGrupo = null;
			Integer anoMesFaturamentoGrupo = null;
			if (parmsFaturamentoGrupo != null) {
				if (parmsFaturamentoGrupo[0] != null) {
					idFaturamentoGrupo = (Integer) parmsFaturamentoGrupo[0];
				}
				if (parmsFaturamentoGrupo[1] != null) {
					anoMesFaturamentoGrupo = (Integer) parmsFaturamentoGrupo[1];
				}
			}
			if (idFaturamentoGrupo != null && anoMesFaturamentoGrupo != null) {
				// caso a quantidades de meses que quer subitrair seja diferente
				// de 0
				if (quantidadeMeses > 0) {
					anoMesFaturamentoGrupo = Util.subtrairMesDoAnoMes(
							anoMesFaturamentoGrupo, quantidadeMeses);
				}
				Integer idFaturamentoAtividade = FaturamentoAtividade.EFETUAR_LEITURA;
				// pesquisa a data de realização
				dataRealizacao = repositorioFaturamento
						.pesquisarDataPrevistaFaturamentoAtividadeCronograma(
								idFaturamentoGrupo, idFaturamentoAtividade,
								anoMesFaturamentoGrupo);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return dataRealizacao;

	}

	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */
	public Integer pesquisarQuantidadeContasCanceladasOuRetificadas(
			RelatorioContasCanceladasRetificadasHelper helper, int tipoPesquisa)
			throws ControladorException {

		Integer quantidade = null;

		SistemaParametro sistemaParametro;
		sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		int anoMes = Util.formatarMesAnoComBarraParaAnoMes(helper.getMesAno());

		// tipo conta = 1 - cancelada / 2 - retificada
		if (anoMes < sistemaParametro.getAnoMesFaturamento()) {

			// cancelada
			if (tipoPesquisa == 1) {
				// faturamento fechado( anoMes < anoMesParametroSistema)
				// pesquisar em conta historico
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasCanceladasFaturamentoFechado(helper);

				// retificada
			} else {
				// faturamento Aberto pesquisar em conta
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasRetificadasFaturamentoFechado(helper);
			}
		} else {
			// cancelada
			if (tipoPesquisa == 1) {

				// faturamento Aberto pesquisar em conta
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasCanceladasFaturamentoAberto(helper);

				// retificada
			} else {
				// faturamento Aberto pesquisar em conta
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasRetificadasFaturamentoAberto(helper);

			}

		}

		return quantidade;
	}

	/**
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 14/09/2009
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarDadosRelatorioContasRevisaoCount(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarDadosRelatorioContasRevisaoCount(
							idGerenciaRegional, idUnidadeNegocio,
							idLocalidadeInicial, idLocalidadeFinal,
							codigoSetorComercialInicial,
							codigoSetorComercialFinal, colecaoIdsMotivoRevisao,
							idImovelPerfil, referenciaInicial, referenciaFinal,
							idCategoria, idEsferaPoder);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * [UC0820] Atualizar Faturamento do Movimento Celular [SB002] Incluir
	 * Medicao
	 * 
	 * @param movimentoContaPreFaturada
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	private void incluirMedicaoHistorico(MovimentoContaPrefaturada movimentoContaPreFaturada) throws ErroRepositorioException, ControladorException {

		MedicaoHistorico medicaoHistorico = this.verificarExistenciaHistoricoMedicao(movimentoContaPreFaturada);

		if (medicaoHistorico == null) {

			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, movimentoContaPreFaturada.getImovel().getId()));

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(this.getControladorUtil().pesquisar(filtroImovel,Imovel.class.getName()));

			if (movimentoContaPreFaturada.getFaturamentoGrupo() != null) {
				sistemaParametro.setAnoMesFaturamento(movimentoContaPreFaturada.getFaturamentoGrupo().getAnoMesReferencia());
			}

			medicaoHistorico = this.getControladorMicromedicao().gerarHistoricoMedicao(movimentoContaPreFaturada.getMedicaoTipo(), imovel,
							movimentoContaPreFaturada.getFaturamentoGrupo(),sistemaParametro);

			medicaoHistorico.setImovel(
					(movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.POCO) ? movimentoContaPreFaturada.getImovel() : null);

			if (movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				ligacaoAgua.setId(movimentoContaPreFaturada.getImovel().getId());
				medicaoHistorico.setLigacaoAgua(ligacaoAgua);
			} else {
				medicaoHistorico.setLigacaoAgua(null);
			}

			medicaoHistorico.setMedicaoTipo(movimentoContaPreFaturada.getMedicaoTipo());
			medicaoHistorico.setAnoMesReferencia(movimentoContaPreFaturada.getFaturamentoGrupo().getAnoMesReferencia());
			medicaoHistorico.setNumeroVezesConsecutivasOcorrenciaAnormalidade(null);
			medicaoHistorico.setDataLeituraAtualInformada(movimentoContaPreFaturada.getDataHoraLeitura());
			medicaoHistorico.setDataLeituraCampo(movimentoContaPreFaturada.getDataHoraLeitura());
			medicaoHistorico.setDataLeituraAtualFaturamento(movimentoContaPreFaturada.getDataHoraLeitura());

			if (movimentoContaPreFaturada.getLeituraFaturamento() != null) {
				medicaoHistorico.setLeituraAtualFaturamento(movimentoContaPreFaturada.getLeituraFaturamento());
			} else {
				medicaoHistorico.setLeituraAtualFaturamento(0);
			}

			if (movimentoContaPreFaturada.getLeituraHidrometro() != null) {
				medicaoHistorico.setLeituraAtualInformada(movimentoContaPreFaturada.getLeituraHidrometro());
				medicaoHistorico.setLeituraCampo(movimentoContaPreFaturada.getLeituraHidrometro());
			} else {
				medicaoHistorico.setLeituraAtualInformada(null);
				medicaoHistorico.setLeituraCampo(null);
			}

			if (movimentoContaPreFaturada.getConsumoMedido() != null && movimentoContaPreFaturada.getConsumoMedido() > ConstantesSistema.ZERO) {
				medicaoHistorico.setNumeroConsumoMes(movimentoContaPreFaturada.getConsumoMedido());
			} else {
				medicaoHistorico.setNumeroConsumoMes(null);
			}

			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, movimentoContaPreFaturada.getRota().getId()));
			
			Collection<Rota> colRota = this.getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
			Rota rota = (Rota) colRota.iterator().next();
			medicaoHistorico.setLeiturista(rota.getLeiturista());

			medicaoHistorico.setLeituraProcessamentoMovimento(new Date());
			medicaoHistorico.setFuncionario(null);

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null && !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico.setLeituraAnormalidadeInformada(movimentoContaPreFaturada.getLeituraAnormalidadeLeitura());
			} else {
				medicaoHistorico.setLeituraAnormalidadeInformada(null);

			}

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null && !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(movimentoContaPreFaturada.getLeituraAnormalidadeFaturamento());
			} else {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(null);

			}

			LeituraSituacao leituraSituacao = new LeituraSituacao();

			if (movimentoContaPreFaturada.getLeituraHidrometro() == null|| movimentoContaPreFaturada.getLeituraHidrometro() == 0) {
				leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
			
			} else if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() != null 
					&& movimentoContaPreFaturada.getIndicadorSituacaoLeitura().equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {

				leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
			} else {
				leituraSituacao.setId(LeituraSituacao.REALIZADA);
			}

			medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();

			if (movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
				hidrometroInstalacaoHistorico.setId(movimentoContaPreFaturada.getImovel().getLigacaoAgua().getHidrometroInstalacaoHistorico().getId());
				medicaoHistorico.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			} else {
				hidrometroInstalacaoHistorico.setId(movimentoContaPreFaturada.getImovel().getHidrometroInstalacaoHistorico().getId());
				medicaoHistorico.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			}

			medicaoHistorico.setConsumoMedioHidrometro(null);
			medicaoHistorico.setIndicadorAnalisado(ConstantesSistema.NAO);
			medicaoHistorico.setUltimaAlteracao(new Date());

			if (medicaoHistorico.getId() != null) {
				RepositorioMicromedicaoHBM.getInstancia().atualizarMedicaoHistoricoProcessoMOBILE(medicaoHistorico);
			} else {
				getControladorBatch().inserirObjetoParaBatchSemTransacao(medicaoHistorico);
			}
		}
	}

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * FS0005 - Verificar Existência do histórico de Medição
	 * 
	 * 
	 * @param matricula
	 *            matricula do imovel selecionado
	 * @param anoMes
	 *            ano mês do historico a ser consultado
	 */
	private MedicaoHistorico verificarExistenciaHistoricoMedicao(
			MovimentoContaPrefaturada movimentoContaPreFaturada)
			throws ErroRepositorioException, ControladorException {
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

		// Caso o tipo de medição seja água
		if (movimentoContaPreFaturada.getMedicaoTipo().getId()
				.equals(MedicaoTipo.LIGACAO_AGUA)) {
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
					movimentoContaPreFaturada.getImovel().getId()));
		} else {
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.IMOVEL_ID, movimentoContaPreFaturada
							.getImovel().getId()));
		}

		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
				movimentoContaPreFaturada.getFaturamentoGrupo()
						.getAnoMesReferencia()));
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.MEDICAO_TIPO_ID,
				movimentoContaPreFaturada.getMedicaoTipo().getId()));
		Collection<MedicaoHistorico> colMedicaoHistorico = this.repositorioUtil
				.pesquisar(filtroMedicaoHistorico,
						MedicaoHistorico.class.getName());

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) Util
				.retonarObjetoDeColecao(colMedicaoHistorico);

		if (medicaoHistorico != null) {

			medicaoHistorico.setFuncionario(null);

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada
							.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeInformada(movimentoContaPreFaturada
								.getLeituraAnormalidadeLeitura());
			} else {
				medicaoHistorico.setLeituraAnormalidadeInformada(null);

			}

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada
							.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeFaturamento(movimentoContaPreFaturada
								.getLeituraAnormalidadeFaturamento());
			} else {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(null);

			}

			if (movimentoContaPreFaturada.getDataHoraLeitura() != null
					&& !movimentoContaPreFaturada.getDataHoraLeitura().equals(
							"")) {
				medicaoHistorico
						.setDataLeituraAtualInformada(movimentoContaPreFaturada
								.getDataHoraLeitura());
				medicaoHistorico
						.setDataLeituraAtualFaturamento(movimentoContaPreFaturada
								.getDataHoraLeitura());
				medicaoHistorico.setDataLeituraCampo(movimentoContaPreFaturada
						.getDataHoraLeitura());
			}

			// 1.10, 1.12
			if (movimentoContaPreFaturada.getLeituraFaturamento() != null) {
				medicaoHistorico
						.setLeituraAtualFaturamento(movimentoContaPreFaturada
								.getLeituraFaturamento());
			} else {
				medicaoHistorico.setLeituraAtualFaturamento(0);
			}

			if (movimentoContaPreFaturada.getLeituraHidrometro() != null) {
				medicaoHistorico
						.setLeituraAtualInformada(movimentoContaPreFaturada
								.getLeituraHidrometro());
				medicaoHistorico.setLeituraCampo(movimentoContaPreFaturada
						.getLeituraHidrometro());
			} else {
				medicaoHistorico.setLeituraAtualInformada(null);
				medicaoHistorico.setLeituraCampo(null);
			}

			LeituraSituacao leituraSituacao = new LeituraSituacao();

			if (movimentoContaPreFaturada.getLeituraFaturamento() == null
					|| movimentoContaPreFaturada.getLeituraFaturamento() == 0) {
				leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
			} else {
				if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
				} else {
					leituraSituacao.setId(LeituraSituacao.REALIZADA);
				}
			}

			medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

			// 1.13
			if (movimentoContaPreFaturada.getConsumoMedido() != null
					&& movimentoContaPreFaturada.getConsumoMedido() > ConstantesSistema.ZERO) {
				medicaoHistorico.setNumeroConsumoMes(movimentoContaPreFaturada
						.getConsumoMedido());
			} else {
				medicaoHistorico.setNumeroConsumoMes(null);
			}

			// Adicionamos o leiturista da rota na medicao historico
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.ID_ROTA, movimentoContaPreFaturada.getRota()
							.getId()));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista");
			Collection<Rota> colRota = this.getControladorUtil().pesquisar(
					filtroRota, Rota.class.getName());
			Rota rota = (Rota) colRota.iterator().next();
			medicaoHistorico.setLeiturista(rota.getLeiturista());

			repositorioUtil.atualizar(medicaoHistorico);
		}

		return medicaoHistorico;
	}

	/**
	 * 
	 * Selecionamos os movimento para serem processados
	 * 
	 * @param helper
	 *            Helper para pesquisa
	 * @return Coleção com os dados solicitados
	 * 
	 * @throws ControladorException
	 */
	private Collection<MovimentoContaPrefaturada> verificarExistenciaListaMovimentoContaPrefaturada(
			AtualizarContaPreFaturadaHelper helperCabecalho)
			throws ControladorException {
		FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("conta");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("movimentoContaPrefaturadaCategorias");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("conta.ligacaoAguaSituacao");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("conta.ligacaoEsgotoSituacao");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelCondominio");

		filtroMovimentoContaPrefaturada
				.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturada.INDICADOR_ATUALIZAR_FATURAMENTO,
						ConstantesSistema.NAO));

		filtroMovimentoContaPrefaturada
				.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturada.MATRICULA,
						helperCabecalho.getMatriculaImovel()));

		filtroMovimentoContaPrefaturada
				.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,
						helperCabecalho.getAnoMesFaturamento()));

		Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada = null;

		try {
			colMovimentoContaPrefaturada = (Collection<MovimentoContaPrefaturada>) repositorioUtil
					.pesquisar(filtroMovimentoContaPrefaturada,
							MovimentoContaPrefaturada.class.getName());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colMovimentoContaPrefaturada;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impressão
	 * simultânea registradas
	 * 
	 * @author bruno
	 * @date 21/09/2009
	 */
	private byte[] geraResumoLeiturasAnormalidadesImpressaoSimultanea(
			Collection<MovimentoContaPrefaturada> colRelatorio)
			throws ControladorException {

		Integer qtdRegistrosRecebidos = 0;
		Integer qtdRegistrosLeitura = 0;
		Integer qtdRegistrosAnormalidade = 0;
		Integer qtdRegistrosLeituraAnormalidade = 0;
		Integer qtdRegistrosInvalidos = 0;
		Integer qtdRegistrosContaNaoEmitida = 0;

		MovimentoContaPrefaturada helperCabecalho = null;

		Collection<Integer> imoveisJaLidos = new ArrayList();

		// Criamos agora os beans do relatorio
		List relatorioBeans = new ArrayList();

		for (MovimentoContaPrefaturada helper : colRelatorio) {

			if (!imoveisJaLidos.contains(helper.getImovel().getId())) {
				imoveisJaLidos.add(helper.getImovel().getId());

				qtdRegistrosRecebidos++;

				if (helperCabecalho == null) {
					helperCabecalho = helper;
				}

				if (helper.getLeituraFaturamento() != null
						&& helper.getLeituraFaturamento() != 0) {
					qtdRegistrosLeitura++;
				} else if (helper.getLeituraAnormalidadeFaturamento() != null) {
					qtdRegistrosAnormalidade++;
				} else if (helper.getLeituraFaturamento() != null
						&& helper.getLeituraFaturamento() != 0
						&& helper.getLeituraAnormalidadeFaturamento() != null) {
					qtdRegistrosLeituraAnormalidade++;
				} else {
					qtdRegistrosInvalidos++;
				}

				if (helper.getIndicadorEmissaoConta() == ConstantesSistema.NAO) {
					qtdRegistrosContaNaoEmitida++;
				}

				RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean bean = new RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean();

				if (helper.getLeituraAnormalidadeFaturamento() != null) {
					bean.setCodigoAnormalidade(helper
							.getLeituraAnormalidadeFaturamento().getId() + "");

					if (!relatorioBeans.contains(bean)) {

						if (helper.getLeituraAnormalidadeFaturamento() != null) {
							bean.setCodigoAnormalidade(helper
									.getLeituraAnormalidadeFaturamento()
									.getId()
									+ "");
							bean.setDescricaoAnormalidade(helper
									.getLeituraAnormalidadeFaturamento()
									.getDescricao());
						}

						bean.setQtdAnormalidade(1);

						relatorioBeans.add(bean);
					} else {
						int index = relatorioBeans.indexOf(bean);
						bean = (RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean) relatorioBeans
								.get(index);
						bean.setQtdAnormalidade(bean.getQtdAnormalidade() + 1);
					}
				}
			}
		}

		if (relatorioBeans.size() == 0) {
			relatorioBeans
					.add(new RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean());
		}

		RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea relatorioResumoLeiturasAnormalidadesImpressaoSimultanea = new RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea(
				new Usuario());

		// Adicionamos os parametros
		Map parametros = new HashMap();

		parametros.put("imagem", this.getControladorUtil()
				.pesquisarParametrosDoSistema().getImagemRelatorio());

		// Id da localidade
		FiltroImovel filtro = new FiltroImovel();
		filtro.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.empresa");
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				helperCabecalho.getImovel().getId()));
		Collection<Imovel> colImo = Fachada.getInstancia().pesquisar(filtro,
				Imovel.class.getName());
		Imovel imo = (Imovel) Util.retonarObjetoDeColecao(colImo);
		parametros.put("grupo", helperCabecalho.getFaturamentoGrupo().getId()
				+ "");
		parametros.put("localidade", imo.getLocalidade().getId() + "");
		parametros.put("codigoEmpresa", imo.getQuadra().getRota().getEmpresa()
				.getId());
		parametros.put("empresa", imo.getQuadra().getRota().getEmpresa()
				.getDescricao());

		parametros.put("qtdRegistrosRecebidos", qtdRegistrosRecebidos);
		parametros.put("qtdRegistrosLeitura", qtdRegistrosLeitura);
		parametros.put("qtdRegistrosAnormalidade", qtdRegistrosAnormalidade);
		parametros.put("qtdRegistrosInvalidos", qtdRegistrosInvalidos);
		parametros.put("qtdRegistrosLeituraAnormalidade",
				qtdRegistrosLeituraAnormalidade);
		parametros.put("qtdRegistrosSemContaEmitida",
				qtdRegistrosContaNaoEmitida);

		// Criamos o source
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		return relatorioResumoLeiturasAnormalidadesImpressaoSimultanea
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA,
						parametros, ds, TarefaRelatorio.TIPO_PDF);
	}

	/**
	 * Gerar quantidade de imoveis
	 * 
	 * @author Arthur Carvalho
	 * @date 23/09/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer gerarRelacaoAcompanhamentoFaturamentoCount(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			int anoMesReferencia) throws ControladorException {

		try {
			return repositorioFaturamento
					.gerarRelacaoAcompanhamentoFaturamentoCount(
							idImovelCondominio, idImovelPrincipal, idNomeConta,
							idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
							consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,

							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,

							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	private void removerDadosMovimentosContaPreFaturada(AtualizarContaPreFaturadaHelper helper) throws ControladorException {
		try {

			Integer idConta = helper.getNumeroConta();
			
			FiltroMovimentoContaPrefaturada filtroMovContaPF = new FiltroMovimentoContaPrefaturada();
			filtroMovContaPF.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.MATRICULA, helper.getMatriculaImovel()));
			filtroMovContaPF.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,	helper.getAnoMesFaturamento()));

			Collection<MovimentoContaPrefaturada> colMovimento = getControladorUtil().pesquisar(filtroMovContaPF,	MovimentoContaPrefaturada.class.getName());

			for (MovimentoContaPrefaturada movimento : colMovimento) {

				FiltroMovimentoContaPrefaturadaCategoria filtroMovContaPFCategoria = new FiltroMovimentoContaPrefaturadaCategoria();
				filtroMovContaPFCategoria.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturadaCategoria.MOVIMENTO_CONTA_PREFATURADA_ID, movimento.getId()));

				Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPFCategoria = getControladorUtil()
						.pesquisar(filtroMovContaPFCategoria, MovimentoContaPrefaturadaCategoria.class.getName());

				for (MovimentoContaPrefaturadaCategoria movimentoCategoria : colMovimentoContaPFCategoria) {

					FiltroMovimentoContaCategoriaConsumoFaixa filtroMovContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();
					filtroMovContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID, movimento.getId()));

					Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = getControladorUtil()
							.pesquisar(filtroMovContaCategoriaConsumoFaixa, MovimentoContaCategoriaConsumoFaixa.class.getName());

					getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colMovimentoContaCategoriaConsumoFaixa);
					getControladorBatch().removerObjetoParaBatchSemTransacao(movimentoCategoria);
				}

				FiltroMovimentoContaImpostoDeduzido filtroMovContaImpostoDeduzido = new FiltroMovimentoContaImpostoDeduzido();
				filtroMovContaImpostoDeduzido.adicionarParametro(new ParametroSimples(FiltroMovimentoContaImpostoDeduzido.MOVIMENTO_CONTA_PREFATURADA_ID, movimento.getId()));

				Collection<MovimentoContaImpostoDeduzido> colMovimentoContaImpostoDeduzido = getControladorUtil()
						.pesquisar(filtroMovContaImpostoDeduzido, MovimentoContaImpostoDeduzido.class.getName());

				if (colMovimentoContaImpostoDeduzido != null && !colMovimentoContaImpostoDeduzido.isEmpty()) {
					getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colMovimentoContaImpostoDeduzido);
				}

				getControladorBatch().removerObjetoParaBatchSemTransacao(movimento);
			}

			FiltroConta filtroConta = new FiltroConta();
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));

			Collection<Conta> colConta = getControladorUtil().pesquisar(filtroConta, Conta.class.getName());

			if (colConta == null || colConta.isEmpty()) {

				Conta contaAtualizacao = repositorioFaturamento.pesquisarContaPreFaturada(helper.getMatriculaImovel(), helper.getAnoMesFaturamento(), DebitoCreditoSituacao.NORMAL);

				colConta = new ArrayList();

				if (contaAtualizacao != null) {

					idConta = contaAtualizacao.getId();
					colConta.add(contaAtualizacao);

				} else {
					contaAtualizacao = repositorioFaturamento.pesquisarContaPreFaturada(helper.getMatriculaImovel(), helper.getAnoMesFaturamento(), DebitoCreditoSituacao.PRE_FATURADA);
					if (contaAtualizacao != null) {
						idConta = contaAtualizacao.getId();
						colConta.add(contaAtualizacao);
					}
				}

			}

			BigDecimal valorCreditoNitrato = null;
			CreditoRealizado creditoRealizado = null;

			Conta contaCreditos = new Conta(idConta);

			Collection<CreditoRealizado> colCreditos = Fachada.getInstancia().obterCreditosRealizadosConta(contaCreditos);

			for (CreditoRealizado objeto : (Collection<CreditoRealizado>) colCreditos) {
				if (objeto.getCreditoTipo().getId().equals(CreditoTipo.CREDITO_NITRATO)) {
					creditoRealizado = objeto;
				}
			}

			Object[] dadosCreditoARealizarNitrato = null;

			if (creditoRealizado != null) {
				dadosCreditoARealizarNitrato = repositorioFaturamento.pesquisarCreditoARealizar(creditoRealizado.getCreditoARealizarGeral().getId(), helper.getAnoMesFaturamento());
			}

			if (dadosCreditoARealizarNitrato != null && !dadosCreditoARealizarNitrato.equals("")) {
				BigDecimal valorCredito = new BigDecimal("0.00");
				valorCreditoNitrato = new BigDecimal("0.00");
				Integer idCreditoARealizarNitrato = (Integer) dadosCreditoARealizarNitrato[0];
				valorCreditoNitrato = (BigDecimal) dadosCreditoARealizarNitrato[1];
				repositorioFaturamento.atualizarValorCreditoARealizar(idCreditoARealizarNitrato, valorCredito, DebitoCreditoSituacao.PRE_FATURADA);
			}

			FiltroContaCategoriaConsumoFaixa filtroContaCategoriaConsumoFaixa = new FiltroContaCategoriaConsumoFaixa();
			filtroContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(FiltroContaCategoriaConsumoFaixa.CONTA_ID, idConta));
			Collection<Object> colContaCategoriaConsumoFaixa = getControladorUtil().pesquisar(filtroContaCategoriaConsumoFaixa, ContaCategoriaConsumoFaixa.class.getName());
			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colContaCategoriaConsumoFaixa);

			FiltroFaturamentoImediatoAjuste filtroFaturamentoImediatoAjuste = new FiltroFaturamentoImediatoAjuste();
			filtroFaturamentoImediatoAjuste.adicionarParametro(new ParametroSimples(FiltroFaturamentoImediatoAjuste.ID_CONTA, idConta));

			Collection<Object> colFaturamentoImediatoAjuste = getControladorUtil().pesquisar(filtroFaturamentoImediatoAjuste, FaturamentoImediatoAjuste.class.getName());
			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colFaturamentoImediatoAjuste);

			FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
			filtroContaImpressao.adicionarParametro(new ParametroSimples(FiltroContaImpressao.ID, idConta));

			Collection<Object> colContaImpressao = getControladorUtil().pesquisar(filtroContaImpressao,	ContaImpressao.class.getName());
			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colContaImpressao);

			for (Conta conta : colConta) {

				if (conta.getDebitoCreditoSituacaoAtual().getId() != DebitoCreditoSituacao.PRE_FATURADA) {

					DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao(DebitoCreditoSituacao.PRE_FATURADA);

					conta.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
					conta.setValorAgua(BigDecimal.ZERO);
					conta.setValorEsgoto(BigDecimal.ZERO);
					conta.setValorImposto(BigDecimal.ZERO);

					if (valorCreditoNitrato != null) {
						BigDecimal valorCreditos = conta.getValorCreditos();
						valorCreditos = valorCreditos.subtract(valorCreditoNitrato);
						conta.setValorCreditos(valorCreditos);
					}

					try {
						repositorioFaturamento.zerarValoresContaPassarDebitoCreditoSituacaoAtualPreFaturadaMOBILE(conta);
					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}
				}
			}
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0958] - Gerar Relatório de juros, Multas e Débitos Cancelados
	 * 
	 * @since 13/10/2009
	 * @author Marlon Patrick
	 */
	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		try {
			Collection<Object[]> dadosRelatorio = this.repositorioFaturamento
					.pesquisarRelatorioJurosMultasDebitosCancelados(filtro);

			if (Util.isVazioOrNulo(dadosRelatorio)) {
				throw new ControladorException(
						"atencao.relatorio_juros_multas_debitos_cancelados.nenhuma_conta_retificada",
						null);
			}

			List<RelatorioJurosMultasDebitosCanceladosHelper> colecaoRelatoriosHelper = null;

			colecaoRelatoriosHelper = criarColecaoRelatorioJurosMultasDebitosCanceladosAgrupados(dadosRelatorio);

			for (Iterator<RelatorioJurosMultasDebitosCanceladosHelper> iteratorRelatorioHelper = colecaoRelatoriosHelper
					.iterator(); iteratorRelatorioHelper.hasNext();) {

				RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper = iteratorRelatorioHelper
						.next();

				ContaHistorico contaHistoricoOriginal = obterContaOriginalContaHistorico(relatorioHelper);

				if (contaHistoricoOriginal == null) {

					Conta contaOriginal = obterContaOriginalConta(relatorioHelper);

					if (contaOriginal != null) {

						if (!selecionarApenasDebitosCancelados(relatorioHelper,
								contaOriginal, filtro)) {
							iteratorRelatorioHelper.remove();
						}

					}

				} else {

					if (!selecionarApenasDebitosCancelados(relatorioHelper,
							contaHistoricoOriginal, filtro)) {
						iteratorRelatorioHelper.remove();
					}
				}

			}

			return colecaoRelatoriosHelper;

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0958] - Relatorio de Juros, Multas e Débitos Cancelados<br/>
	 * [SB0003] - Pesquisar juros,multas e débitos cancelados.<br/>
	 * 
	 * Esse método define quais débitos foram cancelados comparando a conta
	 * original e a conta retificada.<br/>
	 * Caso seja encontrado algum débito que NÃO foi cancelado o mesmo é
	 * removido da coleção de débitos.<br/>
	 * Caso a conta retificada não possua nenhum débito cancelado o método
	 * retorna FALSE.<br/>
	 * 
	 * Regras:<br/>
	 * 0 - Se uma conta possui vários débitos do mesmo tipo, eles devem ser
	 * somados(a retificada já vem assim da consulta, a original é feito neste
	 * método)<br/>
	 * 1 - Se não havia nenhum débito na conta original, então, nenhum débito
	 * foi cancelado.<br/>
	 * 2 - Se a conta original tinha débitos e a retificada não, então, todos
	 * foram cancelados.<br/>
	 * 3 - Se o débito existe apenas na conta retificada, então, ele não foi
	 * cancelado e sim adicionado.<br/>
	 * 4 - Se o débito é maior ou igual na retificada, então, na verdade houve
	 * um aumento do débito ou continuou o mesmo.<br/>
	 * 5 - Se o débito na original é maior que na retificada, então, o valor
	 * cancelado é: valor da original - valor da retificada.<br/>
	 * 6 - Se o débito existe apenas na original, então, ele foi cancelado.<br/>
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private boolean selecionarApenasDebitosCancelados(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper,
			ContaHistorico contaOriginalHistorico,
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		FiltroDebitoCobradoHistorico filtroDebitoCobradoHistorico = new FiltroDebitoCobradoHistorico();
		filtroDebitoCobradoHistorico
				.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoCobradoHistorico.DEBITO_TIPO);
		filtroDebitoCobradoHistorico.adicionarParametro(new ParametroSimples(
				FiltroDebitoCobradoHistorico.CONTA_HISTORICO_ID,
				contaOriginalHistorico.getId()));
		filtroDebitoCobradoHistorico
				.setCampoOrderBy(FiltroDebitoCobradoHistorico.DEBITO_TIPO_DESCRICAO);// ordem
																						// influencia
																						// na
																						// lógica

		Collection<DebitoCobradoHistorico> colecaoDebitosContaOriginal = getControladorUtil()
				.pesquisar(filtroDebitoCobradoHistorico,
						DebitoCobradoHistorico.class.getName());

		if (Util.isVazioOrNulo(colecaoDebitosContaOriginal)) {
			return false;
		}

		DebitoCobradoHistorico debitoCobrado = colecaoDebitosContaOriginal
				.iterator().next();
		Collection<DebitoCobradoHistorico> colecaoDebitosAuxiliar = new ArrayList<DebitoCobradoHistorico>();

		while (!colecaoDebitosAuxiliar.contains(debitoCobrado)) {
			for (Iterator<DebitoCobradoHistorico> iterator = colecaoDebitosContaOriginal
					.iterator(); iterator.hasNext();) {

				DebitoCobradoHistorico debitoCobradoAtual = iterator.next();

				if (colecaoDebitosAuxiliar.contains(debitoCobradoAtual)) {
					continue;
				}

				if (!debitoCobrado.getId().equals(debitoCobradoAtual.getId())
						&& debitoCobrado
								.getDebitoTipo()
								.getDescricao()
								.equals(debitoCobradoAtual.getDebitoTipo()
										.getDescricao())) {

					debitoCobrado.setValorPrestacao(debitoCobrado
							.getValorPrestacao().add(
									debitoCobradoAtual.getValorPrestacao()));

					iterator.remove();
					continue;
				}

				if (!debitoCobrado.getId().toString()
						.equals(debitoCobradoAtual.getId().toString())) {
					colecaoDebitosAuxiliar.add(debitoCobrado);
					debitoCobrado = debitoCobradoAtual;
					break;
				}

				if (!iterator.hasNext()) {
					colecaoDebitosAuxiliar.add(debitoCobrado);
				}
			}
		}

		if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {

			relatorioHelper
					.setColecaoDebitosCobrados(new ArrayList<DebitoCobradoAgrupadoHelper>());

			for (DebitoCobradoHistorico debitoContaOriginal : colecaoDebitosContaOriginal) {
				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getDebitoTipo().getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			return true;
		}

		for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
				.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {

			DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator.next();

			boolean isDebitoApenasContaRetificada = true;

			for (DebitoCobradoHistorico debitoContaOriginal : colecaoDebitosContaOriginal) {
				if (debitoContaRetificada
						.getDescricaoDebitoTipo()
						.trim()
						.equalsIgnoreCase(
								debitoContaOriginal.getDebitoTipo()
										.getDescricaoAbreviada().trim())) {
					isDebitoApenasContaRetificada = false;
					break;
				}
			}

			if (isDebitoApenasContaRetificada) {
				iterator.remove();
			}
		}

		for (DebitoCobradoHistorico debitoContaOriginal : colecaoDebitosContaOriginal) {

			boolean isDebitoApenasContaOriginal = true;

			for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
					.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {
				DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator
						.next();

				if (debitoContaRetificada
						.getDescricaoDebitoTipo()
						.trim()
						.equalsIgnoreCase(
								debitoContaOriginal.getDebitoTipo()
										.getDescricaoAbreviada().trim())) {

					isDebitoApenasContaOriginal = false;

					if (debitoContaRetificada.getValorDebito().compareTo(
							debitoContaOriginal.getValorPrestacao()) >= 0) {
						iterator.remove();
						continue;
					}

					debitoContaRetificada.setValorDebito(debitoContaOriginal
							.getValorPrestacao().subtract(
									debitoContaRetificada.getValorDebito()));
					break;
				}
			}

			if (isDebitoApenasContaOriginal) {
				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * [UC0958] - Relatorio de Juros, Multas e Débitos Cancelados<br/>
	 * [SB0003] - Pesquisar juros,multas e débitos cancelados.<br/>
	 * 
	 * Esse método define quais débitos foram cancelados comparando a conta
	 * original e a conta retificada.<br/>
	 * Caso seja encontrado algum débito que NÃO foi cancelado o mesmo é
	 * removido da coleção de débitos.<br/>
	 * Caso a conta retificada não possua nenhum débito cancelado o método
	 * retorna FALSE.<br/>
	 * 
	 * Regras:<br/>
	 * 0 - Se uma conta possui vários débitos do mesmo tipo, eles devem ser
	 * somados(a retificada já vem assim da consulta, a original é feito neste
	 * método)<br/>
	 * 1 - Se não havia nenhum débito na conta original, então, nenhum débito
	 * foi cancelado.<br/>
	 * 2 - Se a conta original tinha débitos e a retificada não, então, todos
	 * foram cancelados.<br/>
	 * 3 - Se o débito existe apenas na conta retificada, então, ele não foi
	 * cancelado e sim adicionado.<br/>
	 * 4 - Se o débito é maior ou igual na retificada, então, na verdade houve
	 * um aumento do débito ou continuou o mesmo.<br/>
	 * 5 - Se o débito na original é maior que na retificada, então, o valor
	 * cancelado é: valor da original - valor da retificada.<br/>
	 * 6 - Se o débito existe apenas na original, então, ele foi cancelado.<br/>
	 * 
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private boolean selecionarApenasDebitosCancelados(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper,
			Conta contaOriginal,
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		FiltroDebitoCobrado filtroDebitoCobrado = new FiltroDebitoCobrado();
		filtroDebitoCobrado
				.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoCobrado.DEBITO_TIPO);
		filtroDebitoCobrado.adicionarParametro(new ParametroSimples(
				FiltroDebitoCobrado.CONTA_ID, contaOriginal.getId()));

		Collection<DebitoCobrado> colecaoDebitosContaOriginal = getControladorUtil()
				.pesquisar(filtroDebitoCobrado, DebitoCobrado.class.getName());

		if (Util.isVazioOrNulo(colecaoDebitosContaOriginal)) {
			return false;
		}

		DebitoCobrado debitoCobrado = colecaoDebitosContaOriginal.iterator()
				.next();

		while (debitoCobrado != null) {
			for (Iterator<DebitoCobrado> iterator = colecaoDebitosContaOriginal
					.iterator(); iterator.hasNext();) {
				DebitoCobrado debitoCobradoAtual = iterator.next();

				if (!debitoCobrado.getId().equals(debitoCobradoAtual.getId())
						&& debitoCobrado
								.getDebitoTipo()
								.getDescricao()
								.equals(debitoCobradoAtual.getDebitoTipo()
										.getDescricao())) {

					debitoCobrado.setValorPrestacao(debitoCobrado
							.getValorPrestacao().add(
									debitoCobradoAtual.getValorPrestacao()));

					iterator.remove();
					continue;
				}

				debitoCobrado = debitoCobradoAtual;
				break;
			}
		}

		if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {

			relatorioHelper
					.setColecaoDebitosCobrados(new ArrayList<DebitoCobradoAgrupadoHelper>());

			for (DebitoCobrado debitoContaOriginal : colecaoDebitosContaOriginal) {

				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getDebitoTipo().getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			return true;
		}

		for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
				.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {

			DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator.next();

			boolean isDebitoApenasContaRetificada = true;

			for (DebitoCobrado debitoContaOriginal : colecaoDebitosContaOriginal) {
				if (debitoContaRetificada.getDescricaoDebitoTipo().equals(
						debitoContaOriginal.getDebitoTipo().getDescricao())) {
					isDebitoApenasContaRetificada = false;
					break;
				}
			}

			if (isDebitoApenasContaRetificada) {
				iterator.remove();
			}
		}

		for (DebitoCobrado debitoContaOriginal : colecaoDebitosContaOriginal) {

			boolean isDebitoApenasContaOriginal = true;

			for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
					.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {
				DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator
						.next();

				if (debitoContaRetificada.getDescricaoDebitoTipo().equals(
						debitoContaOriginal.getDebitoTipo().getDescricao())) {

					isDebitoApenasContaOriginal = false;

					if (debitoContaRetificada.getValorDebito().compareTo(
							debitoContaOriginal.getValorPrestacao()) >= 0) {

						iterator.remove();
						break;
					}

					debitoContaRetificada.setValorDebito(debitoContaOriginal
							.getValorPrestacao().subtract(
									debitoContaRetificada.getValorDebito()));

					break;
				}

			}

			if (isDebitoApenasContaOriginal) {

				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * [UC0958] - Relatorio de juros,Multas e Débitos Cancelados<br/>
	 * [SB0002] - Obter conta original<br/>
	 * 
	 * Este método tenta obter a conta original de uma determinada conta
	 * retificada a partir da tabela de conta. O parametro relatorioHelper tem
	 * os dados da conta retificada.
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private Conta obterContaOriginalConta(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper)
			throws ControladorException {

		FiltroConta filtroConta = new FiltroConta();

		filtroConta.adicionarParametro(new ParametroSimples(
				FiltroConta.REFERENCIA, relatorioHelper.getAnoMesReferencia()));
		filtroConta.adicionarParametro(new ParametroSimples(
				FiltroConta.IMOVEL_ID, relatorioHelper.getMatricula()));
		filtroConta.adicionarParametro(new ParametroSimples(
				FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
				DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));
		filtroConta.adicionarParametro(new ParametroNulo(
				FiltroContaHistorico.DATA_RETIFICACAO));

		Collection<Conta> colecaoConta = getControladorUtil().pesquisar(
				filtroConta, Conta.class.getName());

		if (!Util.isVazioOrNulo(colecaoConta)) {
			return colecaoConta.iterator().next();
		}

		return null;
	}

	/**
	 * [UC0958] - Relatorio de juros,Multas e Débitos Cancelados<br/>
	 * [SB0002] - Obter conta original<br/>
	 * 
	 * Este método tenta obter a conta original de uma determinada conta
	 * retificada a partir da tabela de conta histórico. O parametro
	 * relatorioHelper tem os dados da conta retificada.
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private ContaHistorico obterContaOriginalContaHistorico(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper)
			throws ControladorException {

		FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();

		filtroContaHistorico.adicionarParametro(new ParametroSimples(
				FiltroContaHistorico.ANO_MES_REFERENCIA, relatorioHelper
						.getAnoMesReferencia()));
		filtroContaHistorico
				.adicionarParametro(new ParametroSimples(
						FiltroContaHistorico.IMOVEL_ID, relatorioHelper
								.getMatricula()));
		filtroContaHistorico.adicionarParametro(new ParametroSimples(
				FiltroContaHistorico.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
				DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));
		filtroContaHistorico.adicionarParametro(new ParametroNulo(
				FiltroContaHistorico.DATA_RETIFICACAO));

		Collection<ContaHistorico> colecaoContaHistorico = getControladorUtil()
				.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());

		if (!Util.isVazioOrNulo(colecaoContaHistorico)) {
			return colecaoContaHistorico.iterator().next();
		}

		return null;
	}

	/**
	 * [UC0958] - Relatorio de Juros, Multas e Débitos Cancelados.<br/>
	 * 
	 * Este método cria uma coleção de
	 * RelatorioJurosMultasDebitosCanceladosHelper com base no retorno da
	 * consulta realizada anteriormente. Além disso, ele agrupa as contas pelo
	 * seus débitos (uma conta passa a ter uma coleção de débitos).
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private List<RelatorioJurosMultasDebitosCanceladosHelper> criarColecaoRelatorioJurosMultasDebitosCanceladosAgrupados(
			Collection<Object[]> dadosRelatorio) throws ControladorException {

		List<RelatorioJurosMultasDebitosCanceladosHelper> colecaoRelatoriosHelper = new ArrayList<RelatorioJurosMultasDebitosCanceladosHelper>();

		for (Object[] dadosAtuais : dadosRelatorio) {
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper = new RelatorioJurosMultasDebitosCanceladosHelper();

			relatorioHelper.setDataCancelamento((Date) dadosAtuais[0]);

			relatorioHelper.setResponsavel((String) dadosAtuais[1]);

			Imovel imovel = new Imovel();
			imovel.setLocalidade(new Localidade((Integer) dadosAtuais[2]));
			imovel.setSetorComercial(new SetorComercial());
			imovel.getSetorComercial().setCodigo((Integer) dadosAtuais[3]);
			imovel.setQuadra(new Quadra());
			imovel.getQuadra().setNumeroQuadra((Integer) dadosAtuais[4]);
			imovel.setLote((Short) dadosAtuais[5]);
			imovel.setSubLote((Short) dadosAtuais[6]);
			imovel.setId((Integer) dadosAtuais[7]);

			relatorioHelper.setInscricao(imovel.getInscricaoFormatada());
			relatorioHelper.setMatricula(imovel.getId().toString());

			relatorioHelper.setEndereco(this.getControladorEndereco()
					.obterEnderecoAbreviadoImovel(imovel.getId()));

			relatorioHelper.setAnoMesReferencia((Integer) dadosAtuais[8]);

			relatorioHelper
					.setColecaoDebitosCobrados(new ArrayList<DebitoCobradoAgrupadoHelper>());

			if (dadosAtuais[9] != null && dadosAtuais[10] != null) {
				DebitoCobradoAgrupadoHelper debitoCobrado = new DebitoCobradoAgrupadoHelper();
				debitoCobrado.setDescricaoDebitoTipo((String) dadosAtuais[9]);
				debitoCobrado.setValorDebito((BigDecimal) dadosAtuais[10]);
				relatorioHelper.getColecaoDebitosCobrados().add(debitoCobrado);
			}

			relatorioHelper.setTabelaOrigem((String) dadosAtuais[12]);

			if (colecaoRelatoriosHelper.contains(relatorioHelper)) {
				RelatorioJurosMultasDebitosCanceladosHelper helperJaExistente = colecaoRelatoriosHelper
						.get(colecaoRelatoriosHelper.indexOf(relatorioHelper));

				helperJaExistente.getColecaoDebitosCobrados().addAll(
						relatorioHelper.getColecaoDebitosCobrados());

				continue;
			}

			colecaoRelatoriosHelper.add(relatorioHelper);
		}

		return colecaoRelatoriosHelper;
	}

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * [SB0005] - Gerar os Créditos Realizados
	 * 
	 * @author Sávio Luiz
	 * @date 27/10/2009
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param helperValoresAguaEsgoto
	 * @param valorTotalDebitos
	 * @param gerarAtividadeGrupoFaturamento
	 * @return GerarCreditoRealizadoHelper
	 * @throws ControladorException
	 */
	private BigDecimal atualizarCreditoResidual(Imovel imovel, Integer idConta,
			Integer anoMesFaturamento, BigDecimal valorTotalContaSemCredito, Conta contaAtualizacao)
			throws ControladorException {
		BigDecimal valorTotalCreditos = BigDecimal.ZERO;
		
		try {
			Collection colecaoCreditoRealizado = repositorioFaturamento.pesquisarCreditosRealizados(idConta);
		//	System.out.println("Atualizar valor residual. Id imóvel: " + imovel != null ? imovel.getId():"");
			
			
			/**
			 * Autor: Adriana Muniz
			 * Data: 20/07/2011
			 * 
			 * Alteração para atender créditos com uma prestação
			 * */
			List<Integer> idCreditosARealizarVerificados = new ArrayList<Integer>();
			
			if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()) {
				Iterator iteratorColecaoCreditosRealizados = colecaoCreditoRealizado.iterator();
				boolean deletaCreditoRealizado = false;
				CreditoRealizado creditoRealizado = null;
				BigDecimal valorTotalACobrar = valorTotalContaSemCredito;
				boolean verificouBolsaAgua = false;
				
				
						
				while (iteratorColecaoCreditosRealizados.hasNext()){
					creditoRealizado = (CreditoRealizado) iteratorColecaoCreditosRealizados.next();
					
					Collection colecaoCreditosARealizar = this
							.obterCreditoARealizarDadosCreditoRealizadoAntigo(imovel.getId(),
							DebitoCreditoSituacao.NORMAL, anoMesFaturamento, 
							creditoRealizado);	
					CreditoRealizado creditoBolsaAgua = obterCreditoBolsaAgua(colecaoCreditoRealizado);
					
					BigDecimal valorCorrespondenteParcelaMes = ConstantesSistema.VALOR_ZERO;
					BigDecimal valorCredito = ConstantesSistema.VALOR_ZERO;
					BigDecimal valorConta = ConstantesSistema.VALOR_ZERO;
					
					if (creditoBolsaAgua != null && !verificouBolsaAgua) {

								valorTotalACobrar = valorTotalACobrar.subtract(creditoBolsaAgua.getValorCredito());														
													
									logger.info(" 0 - Credito a Realizar BOLSA ÁGUA: Imovel (atualizarCreditoResidual): " + (creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getImovel() != null ? creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getImovel().getId() : "NULL") 
											+ " | Créditos: " + (creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getValorCredito() != null ? creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getValorCredito() : "NULL" )
											+ " | Residual Concedido no Mês: " + (creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getValorResidualConcedidoMes() != null ? creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getValorResidualConcedidoMes() : "NULL") 
											+ " | Residual Concedido no Mês Anterior: " + (creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getValorResidualMesAnterior() != null ? creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar().getValorResidualMesAnterior() : "NULL"));
									
									creditoBolsaAgua.setUltimaAlteracao(new Date());
									getControladorUtil().atualizar(creditoRealizado);
									
									//atualiza o credito realizado categoria
									this.atualizarCreditoRealizadoCategoria(creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar(), creditoBolsaAgua);	
								
									getControladorUtil().atualizar(creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar());	
									
									valorTotalCreditos = valorTotalCreditos.add(creditoBolsaAgua.getValorCredito());
									
									verificouBolsaAgua = true;

									if (valorTotalACobrar
											.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
										deletaCreditoRealizado = true;
									}
																
					}
	
					if (colecaoCreditosARealizar != null 
							&& !colecaoCreditosARealizar.isEmpty() 
							&& valorTotalACobrar.compareTo(BigDecimal.ZERO) == 1) {
						
						Iterator iteratorColecaoCreditosARealizar = colecaoCreditosARealizar.iterator();
						
						CreditoARealizar creditoARealizar = null;
						
							while (iteratorColecaoCreditosARealizar.hasNext()) {
								creditoARealizar = (CreditoARealizar) iteratorColecaoCreditosARealizar.next();
								
								if (creditoARealizar.isCreditoBolsaAgua()) {
									continue;
								}
	
								if (!idCreditosARealizarVerificados.contains(creditoARealizar.getId())) {
									idCreditosARealizarVerificados.add(creditoARealizar.getId());
	
									if (!deletaCreditoRealizado || idCreditosARealizarVerificados.contains(creditoARealizar.getId())) {
										valorCorrespondenteParcelaMes = ConstantesSistema.VALOR_ZERO;
										valorCredito = ConstantesSistema.VALOR_ZERO;
										valorConta = ConstantesSistema.VALOR_ZERO;
	
										/**
										 * 
										 * Data: 01/07/2011
										 * autor: Adriana Muniz
										 * 
										 * Alteração para atender casos de créditos com apenas uma prestação e
										 * que são consumidos conforme o valor da conta a ate ser concedido totalmente
										 * */
										if (creditoARealizar.getNumeroPrestacaoCredito() == 1) {
											BigDecimal valorResidual = ConstantesSistema.VALOR_ZERO;
											
											if (valorTotalCreditos.compareTo(valorTotalContaSemCredito) == -1) {
												
												if (creditoARealizar.getValorResidualMesAnterior().compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
													valorConta = valorTotalACobrar;
													valorTotalACobrar = valorTotalACobrar.subtract(creditoARealizar.getValorCredito());
													valorResidual = creditoARealizar.getValorCredito();
												} else {
													valorConta = valorTotalACobrar;
													valorTotalACobrar = valorTotalACobrar.subtract(creditoARealizar.getValorResidualMesAnterior());
													valorResidual = creditoARealizar.getValorResidualMesAnterior();
												}
												BigDecimal valorCreditoConcedido = ConstantesSistema.VALOR_ZERO;
												// valorCredito = creditoARealizar.getValorCredito();
	
												if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {
	
													creditoARealizar.setValorResidualMesAnterior(valorTotalACobrar
																	.multiply(new BigDecimal(
																			"-1")));
	
													valorCreditoConcedido = valorResidual.subtract(creditoARealizar
																	.getValorResidualMesAnterior());
	
													/**
													 * @author Adriana Muniz e Wellington Rocha
													 * @date 30/08/2012
													 * Atualização da data da ultima alteração do credito realizado
													 * e atualização do credito realizado categoria
													 */
													// atualiza o credito realizado
													creditoRealizado.setValorCredito(valorCreditoConcedido);
													creditoRealizado.setUltimaAlteracao(new Date());
													getControladorUtil().atualizar(creditoRealizado);
													
													//atualiza o credito realizado categoria
													this.atualizarCreditoRealizadoCategoria(creditoARealizar,creditoRealizado);
	
													// atualiza o credito a realizar
													repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
	
													// Acumula o valor do crédito
													valorTotalCreditos = valorTotalCreditos.add(valorCreditoConcedido);
	
													if (valorTotalCreditos.compareTo(valorTotalContaSemCredito) == 0) {
														valorTotalACobrar = ConstantesSistema.VALOR_ZERO;
											
													}
												} else {
													BigDecimal valorConcedido = valorConta.subtract(valorTotalACobrar);
													
													if(valorConcedido.compareTo(ConstantesSistema.VALOR_ZERO) == -1)
														valorConcedido = valorConcedido.multiply(new BigDecimal("-1"));
	
													if(valorConcedido.compareTo(valorConta) == -1)
														creditoARealizar.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);
													else {
														if(valorResidual.compareTo(ConstantesSistema.VALOR_ZERO) == 0)
															creditoARealizar.setValorResidualMesAnterior(
																	creditoARealizar.getValorCredito().subtract(valorConcedido));
														else
															creditoARealizar.setValorResidualMesAnterior(
																	valorResidual.subtract(valorConcedido));
																
													}
													
													logger.info(" 1 - Credito a Realizar: Imovel (atualizarCreditoResidual): " + (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId() : "NULL") 
															+ " | Créditos: " + (creditoARealizar.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL" )
															+ " | Residual Concedido no Mês: " + (creditoARealizar.getValorResidualConcedidoMes() != null ? creditoARealizar.getValorResidualConcedidoMes() : "NULL") 
															+ " | Residual Concedido no Mês Anterior: " + (creditoARealizar.getValorResidualMesAnterior() != null ? creditoARealizar.getValorResidualMesAnterior() : "NULL"));
													
													repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
													// Acumula o valor do crédito
													valorTotalCreditos = valorTotalCreditos.add(valorConcedido);
													
													/**
													 * @author Adriana Muniz e Wellington Rocha
													 * @date 30/08/2012
													 * Atualização da data da ultima alteração do credito realizado
													 * e atualização do credito realizado categoria
													 */
													// atualiza o credito realizado
													creditoRealizado.setValorCredito(valorConcedido);
													creditoRealizado.setUltimaAlteracao(new Date());
													getControladorUtil().atualizar(creditoRealizado);
													
													//atualiza o credito realizado categoria
													this.atualizarCreditoRealizadoCategoria(creditoARealizar,creditoRealizado);
													
													//Acrescentado no dia 26/08/2011
													if (valorTotalCreditos.compareTo(valorTotalContaSemCredito) == 0) {
														valorTotalACobrar = ConstantesSistema.VALOR_ZERO;
														
													}
												}
	
											} else {
												creditoARealizar.setValorResidualMesAnterior(creditoARealizar.getValorCredito());
												logger.info(" 2 - Credito a Realizar: Imovel (atualizarCreditoResidual): " + (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId() : "NULL") 
														+ " | Créditos: " + (creditoARealizar.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL" )
														+ " | Residual Concedido no Mês: " + (creditoARealizar.getValorResidualConcedidoMes() != null ? creditoARealizar.getValorResidualConcedidoMes() : "NULL") 
														+ " | Residual Concedido no Mês Anterior: " + (creditoARealizar.getValorResidualMesAnterior() != null ? creditoARealizar.getValorResidualMesAnterior() : "NULL"));
												
												// atualiza o credito a realizar
												repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
											}
										} else {
	
											int numeroPrestacoesRealizadas = creditoARealizar.getNumeroPrestacaoRealizada()
													.intValue() - 1;
	
											Short numeroParcelaBonus = 0;
											if (creditoARealizar.getNumeroParcelaBonus() != null) {
												numeroParcelaBonus = creditoARealizar.getNumeroParcelaBonus();
											}
	
											if (numeroPrestacoesRealizadas < (creditoARealizar.getNumeroPrestacaoCredito()
													.intValue() - numeroParcelaBonus.intValue())) {
	
												valorCorrespondenteParcelaMes = creditoARealizar.getValorCredito()
														.divide(new BigDecimal(creditoARealizar.getNumeroPrestacaoCredito()),
																2,BigDecimal.ROUND_HALF_UP);
	
												if (numeroPrestacoesRealizadas == ((creditoARealizar
														.getNumeroPrestacaoCredito()
														.intValue() - numeroParcelaBonus
														.intValue()) - 1)) {
	
	
													BigDecimal valorMesVezesPrestacaoCredito = valorCorrespondenteParcelaMes
															.multiply(
																	new BigDecimal(
																			creditoARealizar
																					.getNumeroPrestacaoCredito()))
															.setScale(2);
	
													BigDecimal parte11 = valorCorrespondenteParcelaMes
															.add(creditoARealizar
																	.getValorCredito());
	
													BigDecimal parte22 = parte11
															.subtract(valorMesVezesPrestacaoCredito);
	
													valorCorrespondenteParcelaMes = parte22;
												}
	
											}
	
											valorCredito = valorCorrespondenteParcelaMes
													.add(creditoARealizar
															.getValorResidualMesAnterior());
	
											valorTotalACobrar = valorTotalACobrar
													.subtract(valorCredito);
	
											if (valorTotalACobrar
													.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {
	
												creditoARealizar
														.setValorResidualMesAnterior(valorTotalACobrar
																.multiply(new BigDecimal(
																		"-1")));
	
												valorCredito = valorCredito
														.subtract(creditoARealizar
																.getValorResidualMesAnterior());
	
												valorTotalACobrar = ConstantesSistema.VALOR_ZERO;
											
												logger.info(" 3 - Credito a Realizar: Imovel (atualizarCreditoResidual): " + (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId() : "SEM IMÓVEL...") 
														+ " | Créditos: " + (creditoARealizar.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL" )
														+ " | Residual Concedido no Mês: " + (creditoARealizar.getValorResidualConcedidoMes() != null ? creditoARealizar.getValorResidualConcedidoMes() : "NULL") 
														+ " | Residual Concedido no Mês Anterior: " + (creditoARealizar.getValorResidualMesAnterior() != null ? creditoARealizar.getValorResidualMesAnterior() : "NULL"));
												
												// atualiza o credito a realizar
												repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
												
												/**
												 * @author Adriana Muniz e Wellington Rocha
												 * @date 30/08/2012
												 * Atualização da data da ultima alteração do credito realizado
												 * e atualização do credito realizado categoria
												 */
												// atualiza o credito realizado
												creditoRealizado.setValorCredito(valorCredito);
												creditoRealizado.setUltimaAlteracao(new Date());
												getControladorUtil().atualizar(creditoRealizado);
												
												//atualiza o credito realizado categoria
												this.atualizarCreditoRealizadoCategoria(creditoARealizar,creditoRealizado);
												
											} else {
												creditoARealizar
														.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);
												
												logger.info(" 4 - Credito a Realizar: Imovel (atualizarCreditoResidual): " + (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId() : "NULL") 
														+ " | Créditos: " + (creditoARealizar.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL" )
														+ " | Residual Concedido no Mês: " + (creditoARealizar.getValorResidualConcedidoMes() != null ? creditoARealizar.getValorResidualConcedidoMes() : "NULL") 
														+ " | Residual Concedido no Mês Anterior: " + (creditoARealizar.getValorResidualMesAnterior() != null ? creditoARealizar.getValorResidualMesAnterior() : "NULL"));
												
												repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
											}
											valorTotalCreditos = valorTotalCreditos.add(valorCredito);
										}
	
									} else {
										// atualiza o credito a realizar
	
										/**
										 * 
										 * Para não subtrair a prestação do crédito, caso o credito seja apenas de uma parcela
										 * */
										if (creditoARealizar.getNumeroPrestacaoCredito() != 1) {
										// Atualiza o nº de prestações realizadas
										creditoARealizar
												.setNumeroPrestacaoRealizada(new Short(
														(creditoARealizar
																.getNumeroPrestacaoRealizada()
																.intValue() - 1)
																+ ""));
										}
										// anoMes da prestação será o anaMes de
										// referência da conta
										creditoARealizar
												.setAnoMesReferenciaPrestacao(null);
										
										logger.info(" 5 - Credito a Realizar: Imovel (atualizarCreditoResidual): " + (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId() : "NULL") 
												+ " | Créditos: " + (creditoARealizar.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL" )
												+ " | Residual Concedido no Mês: " + (creditoARealizar.getValorResidualConcedidoMes() != null ? creditoARealizar.getValorResidualConcedidoMes() : "NULL") 
												+ " | Residual Concedido no Mês Anterior: " + (creditoARealizar.getValorResidualMesAnterior() != null ? creditoARealizar.getValorResidualMesAnterior() : "NULL"));
										
										repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
									}
									
								}/* fim laço que verifica se o credito a realizar já foi analisado
								
								if (creditoARealizar.getCreditoTipo().getId().equals(CreditoTipo.CREDITO_BOLSA_AGUA)) {
									Filtro filtro = new FiltroCreditoARealizar();
									filtro.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID, creditoARealizar.getId()));
									
									CreditoARealizar creditoParaAtualizar = (CreditoARealizar) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, CreditoARealizar.class.getName()));
									
									BigDecimal novoValorCredito = creditoARealizar.getValorCredito()
											.subtract(creditoARealizar.getValorResidualMesAnterior());
									
									if (contaAtualizacao.getValorDebitos().compareTo(BigDecimal.ZERO) == 1) {
										novoValorCredito = novoValorCredito.subtract(contaAtualizacao.getValorDebitos());
										contaAtualizacao.setValorCreditos(novoValorCredito);
									}

									creditoParaAtualizar.setValorCredito(novoValorCredito);
									creditoParaAtualizar.setValorResidualMesAnterior(BigDecimal.ZERO);
									getControladorUtil().atualizar(creditoParaAtualizar);
									
									logger.info(" 6 - Credito a Realizar: Imovel (atualizarCreditoResidual): " + (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId() : "NULL") 
											+ " | Créditos: " + (creditoARealizar.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL" )
											+ " | Residual Concedido no Mês: " + (creditoARealizar.getValorResidualConcedidoMes() != null ? creditoARealizar.getValorResidualConcedidoMes() : "NULL") 
											+ " | Residual Concedido no Mês Anterior: " + (creditoARealizar.getValorResidualMesAnterior() != null ? creditoARealizar.getValorResidualMesAnterior() : "NULL"));
								}*/
							}// fim laço de credito a realizar
					}
							
					if (deletaCreditoRealizado && !creditoRealizado.isCreditoBolsaAgua()) {
					 // deleta o credito realizado categoria
					 repositorioFaturamento.deletarCreditoRealizadoCategoria(creditoRealizado.getId());
	
					 // deleta o credito realizado
					 getControladorBatch().removerObjetoParaBatchSemTransacao(creditoRealizado);
					}
	
					if (valorTotalACobrar
							.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
						deletaCreditoRealizado = true;
					}
				}
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return valorTotalCreditos;
	}
	
	public Collection obterCreditoARealizarDadosCreditoRealizadoAntigo(Integer imovelId, Integer debitoCreditoSituacaoAtualId, Integer anoMesFaturamento,
			CreditoRealizado creditoRealizado) throws ControladorException {

        Collection creditosARealizar = null;
        Collection colecaoCreditosARealizar = null;
        try {
			BigDecimal valorCredito = creditoRealizado.getValorCredito().multiply(new BigDecimal(String.valueOf(creditoRealizado.getNumeroPrestacao())));
        	Integer idCreditoARealizar = creditoRealizado.obterIdCreditoARealizar();
            colecaoCreditosARealizar = repositorioFaturamento
                    .pesquisarCreditoARealizarPeloCreditoRealizadoAntigo(imovelId,
                            creditoRealizado.getCreditoTipo().getId(),valorCredito,debitoCreditoSituacaoAtualId,
                            anoMesFaturamento, idCreditoARealizar);

        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", ex);
        }

        if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {
            creditosARealizar = new ArrayList();
            Iterator iteratorColecaoCreditosARealizar = colecaoCreditosARealizar.iterator();
            CreditoARealizar creditoARealizar = null;
            
            while (iteratorColecaoCreditosARealizar.hasNext()) {

                Object[] arrayCreditosACobrar = (Object[]) iteratorColecaoCreditosARealizar.next();
                
                /**
                 * Autor: Adriana Muniz
                 * Data: 01/09/2011
                 * 
                 * Adição de verificação da referencia do credito e da cobrança, visando com isso
                 * trazer o credito a realizar referente ao credito realizado
                 * */
                //selecionar o crédito a partir da referência do credito e da cobrança
                Integer referenciaCredito = (Integer)arrayCreditosACobrar[13];
                Integer cobrancacredito = (Integer)arrayCreditosACobrar[14];
                if(referenciaCredito.equals(creditoRealizado.getAnoMesReferenciaCredito()) &&
                		cobrancacredito.equals(creditoRealizado.getAnoMesCobrancaCredito())) {

                	creditoARealizar = new CreditoARealizar();
                	if (arrayCreditosACobrar[0] != null) {
                		creditoARealizar.setId((Integer) arrayCreditosACobrar[0]);
                	}

                	if (arrayCreditosACobrar[1] != null) {
                		creditoARealizar.setNumeroPrestacaoRealizada((Short) arrayCreditosACobrar[1]);
                	}

                	if (arrayCreditosACobrar[2] != null) {
                		creditoARealizar.setNumeroPrestacaoCredito((Short) arrayCreditosACobrar[2]);
                	}

                	if (arrayCreditosACobrar[3] != null) {
                		creditoARealizar.setValorCredito((BigDecimal) arrayCreditosACobrar[3]);
                	}

                	if (arrayCreditosACobrar[4] != null) {
                		creditoARealizar.setValorResidualMesAnterior((BigDecimal) arrayCreditosACobrar[4]);
                	}

                	if (arrayCreditosACobrar[5] != null) {
                		CreditoTipo creditoTipo = new CreditoTipo();
                		creditoTipo.setId((Integer) arrayCreditosACobrar[5]);
                		creditoARealizar.setCreditoTipo(creditoTipo);
                	}

                	if (arrayCreditosACobrar[6] != null) {
                		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
                		lancamentoItemContabil.setId((Integer) arrayCreditosACobrar[6]);
                		creditoARealizar.setLancamentoItemContabil(lancamentoItemContabil);
                	}

                	if (arrayCreditosACobrar[7] != null) {
                		Localidade localidade = new Localidade();
                		localidade.setId((Integer) arrayCreditosACobrar[7]);
                		creditoARealizar.setLocalidade(localidade);
                	}

                	if (arrayCreditosACobrar[8] != null) {
                		Quadra quadra = new Quadra();
                		quadra.setId((Integer) arrayCreditosACobrar[8]);
                		creditoARealizar.setQuadra(quadra);
                	}

                	if (arrayCreditosACobrar[9] != null) {
                		creditoARealizar.setCodigoSetorComercial((Integer) arrayCreditosACobrar[9]);
                	}

                	if (arrayCreditosACobrar[10] != null) {
                		creditoARealizar.setNumeroQuadra((Integer) arrayCreditosACobrar[10]);
                	}

                	if (arrayCreditosACobrar[11] != null) {
                		creditoARealizar.setNumeroLote((Short) arrayCreditosACobrar[11]);
                	}

                	if (arrayCreditosACobrar[12] != null) {
                		creditoARealizar.setNumeroSubLote((Short) arrayCreditosACobrar[12]);
                	}

                	if (arrayCreditosACobrar[13] != null) {
                		creditoARealizar.setAnoMesReferenciaCredito((Integer) arrayCreditosACobrar[13]);
                	}

                	if (arrayCreditosACobrar[14] != null) {
                		creditoARealizar.setAnoMesCobrancaCredito((Integer) arrayCreditosACobrar[14]);
                	}

                	if (arrayCreditosACobrar[15] != null) {
                		CreditoOrigem creditoOrigem = new CreditoOrigem();
                		creditoOrigem.setId((Integer) arrayCreditosACobrar[15]);
                		creditoARealizar.setCreditoOrigem(creditoOrigem);
                	}

                	/*
                	 * Alterado por Vivianne Sousa em 20/12/2007 - Analista: Adriano
                	 * criação do bonus para parcelamento com RD especial
                	 */
                	//numero de parcelas bonus - item 16
                	if (arrayCreditosACobrar[16] != null) {
                		creditoARealizar.setNumeroParcelaBonus((Short) arrayCreditosACobrar[16]);
                	}
                	creditosARealizar.add(creditoARealizar);
                }
            }
        }
        return creditosARealizar;
    }

	/**
	 * Obtem os Credito A Realizar do Imovel
	 * 
	 * @param imovelID
	 *            Id do Imovel
	 * @param debitoCreditoSituacaoAtualID
	 *            ID do Debito Credito Situação
	 * @return Coleção de Creditos a Realizar
	 */
	private Collection obterCreditoARealizarDadosCreditoRealizado(
			Integer IdCreditoARealizar, Integer anoMesFaturamento)
			throws ControladorException {

		// lista de credito a realizar
		Collection creditosARealizar = null;
		Collection colecaoCreditosARealizar = null;
		// Pesquisa créditos a cobrar
		try {
			colecaoCreditosARealizar = repositorioFaturamento
					.pesquisarCreditoARealizarPeloCreditoRealizado(
							IdCreditoARealizar, anoMesFaturamento);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		// Verifica se existe débitos a realizar
		if (colecaoCreditosARealizar != null
				&& !colecaoCreditosARealizar.isEmpty()) {

			creditosARealizar = new ArrayList();

			Iterator iteratorColecaoCreditosARealizar = colecaoCreditosARealizar
					.iterator();
			CreditoARealizar creditoARealizar = null;
			while (iteratorColecaoCreditosARealizar.hasNext()) {

				Object[] arrayCreditosACobrar = (Object[]) iteratorColecaoCreditosARealizar
						.next();

				creditoARealizar = new CreditoARealizar();
				// id do Credito a Realizar - Item 0
				if (arrayCreditosACobrar[0] != null) {
					creditoARealizar.setId((Integer) arrayCreditosACobrar[0]);
				}

				// numero de prestacoes realizadas - item 1
				if (arrayCreditosACobrar[1] != null) {
					creditoARealizar
							.setNumeroPrestacaoRealizada((Short) arrayCreditosACobrar[1]);
				}

				// numero de prestacoes credito - item 2
				if (arrayCreditosACobrar[2] != null) {
					creditoARealizar
							.setNumeroPrestacaoCredito((Short) arrayCreditosACobrar[2]);

				}

				// valor de credito - item 3
				if (arrayCreditosACobrar[3] != null) {
					creditoARealizar
							.setValorCredito((BigDecimal) arrayCreditosACobrar[3]);

				}

				// valor residual mes anterior - item 4
				if (arrayCreditosACobrar[4] != null) {
					creditoARealizar
							.setValorResidualMesAnterior((BigDecimal) arrayCreditosACobrar[4]);
				}

				// credito tipo - item 5
				if (arrayCreditosACobrar[5] != null) {
					CreditoTipo creditoTipo = new CreditoTipo();
					creditoTipo.setId((Integer) arrayCreditosACobrar[5]);
					creditoARealizar.setCreditoTipo(creditoTipo);

				}

				// lancamento item contabil - item 6
				if (arrayCreditosACobrar[6] != null) {
					LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
					lancamentoItemContabil
							.setId((Integer) arrayCreditosACobrar[6]);
					creditoARealizar
							.setLancamentoItemContabil(lancamentoItemContabil);
				}

				// lancamento - item 7
				if (arrayCreditosACobrar[7] != null) {
					Localidade localidade = new Localidade();
					localidade.setId((Integer) arrayCreditosACobrar[7]);
					creditoARealizar.setLocalidade(localidade);
				}

				// quadra - item 8
				if (arrayCreditosACobrar[8] != null) {
					Quadra quadra = new Quadra();
					quadra.setId((Integer) arrayCreditosACobrar[8]);
					creditoARealizar.setQuadra(quadra);
				}

				// codigo setor comercial - item 9
				if (arrayCreditosACobrar[9] != null) {
					creditoARealizar
							.setCodigoSetorComercial((Integer) arrayCreditosACobrar[9]);
				}

				// numero quadra - item 10
				if (arrayCreditosACobrar[10] != null) {
					creditoARealizar
							.setNumeroQuadra((Integer) arrayCreditosACobrar[10]);
				}

				// numero lote - item 11
				if (arrayCreditosACobrar[11] != null) {
					creditoARealizar
							.setNumeroLote((Short) arrayCreditosACobrar[11]);
				}

				// numero sublote - item 12
				if (arrayCreditosACobrar[12] != null) {
					creditoARealizar
							.setNumeroSubLote((Short) arrayCreditosACobrar[12]);
				}

				// ano mes referencia credito - item 13
				if (arrayCreditosACobrar[13] != null) {
					creditoARealizar
							.setAnoMesReferenciaCredito((Integer) arrayCreditosACobrar[13]);
				}

				// ano mes cobranca credito - item 14
				if (arrayCreditosACobrar[14] != null) {
					creditoARealizar
							.setAnoMesCobrancaCredito((Integer) arrayCreditosACobrar[14]);
				}

				// CreditoOrigem - item 15
				if (arrayCreditosACobrar[15] != null) {

					CreditoOrigem creditoOrigem = new CreditoOrigem();
					creditoOrigem.setId((Integer) arrayCreditosACobrar[15]);

					creditoARealizar.setCreditoOrigem(creditoOrigem);
				}

				/*
				 * Alterado por Vivianne Sousa em 20/12/2007 - Analista: Adriano
				 * criação do bonus para parcelamento com RD especial
				 */
				// numero de parcelas bonus - item 16
				if (arrayCreditosACobrar[16] != null) {
					creditoARealizar
							.setNumeroParcelaBonus((Short) arrayCreditosACobrar[16]);
				}

				// valor residual concedido no mês - item 17
				if (arrayCreditosACobrar[17] != null) {
					creditoARealizar
							.setValorResidualConcedidoMes((BigDecimal) arrayCreditosACobrar[17]);
				}

				creditosARealizar.add(creditoARealizar);
			}
		}

		return creditosARealizar;

	}

	/**
	 * [UC0958] - Count Relatório de juros, Multas e Débitos Cancelados
	 * 
	 * @since 10/12/2009
	 * @author Hugo Amorim
	 */
	public int countRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		int retorno = 0;
		Collection<Object[]> dadosRelatorio;
		try {

			dadosRelatorio = this.repositorioFaturamento
					.pesquisarRelatorioJurosMultasDebitosCancelados(filtro);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		retorno = dadosRelatorio.size();

		return retorno;
	}

	/**
	 * 
	 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @since 14/12/2009
	 * 
	 */
	public void gerarTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente, Integer idFuncionalidadeIniciada)
			throws ControladorException {
		int idUnidadeIniciada = 0;
		ZipOutputStream zos = null;
		BufferedWriter out = null;

		try {
			// -------------------------
			// Registrar o início do processamento da Unidade de
			// Processamento do Batch
			// -------------------------

			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.FUNCIONALIDADE, 0);

			// Variáveis para a paginação da pesquisa
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeRegistros = 5000;
			int numeroIndice = 0;
			// ========================================================================

			Collection colecaoDadosTxt = null;

			String nomeArquivo = "contas_projetos_especiais_" + anoMes;

			// criar o arquivo zip
			File compactado = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeArquivo + ".zip"); // nomeZip
			zos = new ZipOutputStream(new FileOutputStream(compactado));

			File leitura = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeArquivo + ".txt");

			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));

			while (!flagTerminou) {

				colecaoDadosTxt = repositorioFaturamento
						.pesquisarDadosTxtContasProjetosEspeciais(anoMes,
								idCliente, quantidadeRegistros, numeroIndice);

				if (colecaoDadosTxt != null && !colecaoDadosTxt.isEmpty()) {

					Iterator colecaoDadosTxtIterator = colecaoDadosTxt
							.iterator();

					while (colecaoDadosTxtIterator.hasNext()) {

						GerarArquivoTextoContasProjetosEspeciaisHelper helper = new GerarArquivoTextoContasProjetosEspeciaisHelper();

						StringBuilder arquivoTxt = new StringBuilder();

						// cria um array de objetos para pegar os parametros
						// de retorno da pesquisa
						Object[] arraydadosTxt = (Object[]) colecaoDadosTxtIterator
								.next();

						this.montarDadosArquivoTextoContasProjetosEspeciais(
								arraydadosTxt, helper);

						this.montarArquivoTextoContasProjetosEspeciais(
								arquivoTxt, helper);

						arquivoTxt.append(System.getProperty("line.separator"));

						if (arquivoTxt != null && arquivoTxt.length() != 0) {

							out.write(arquivoTxt.toString());
							out.flush();

						}

						helper = null;
					}
				}

				// Incrementa o nº do indice da páginação
				numeroIndice = numeroIndice + quantidadeRegistros;

				/**
				 * Caso a coleção de dados retornados for menor que a quantidade
				 * de registros seta a flag indicando que a paginação terminou.
				 */
				if (colecaoDadosTxt == null
						|| colecaoDadosTxt.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (colecaoDadosTxt != null) {
					colecaoDadosTxt.clear();
					colecaoDadosTxt = null;
				}
			}

			ZipUtil.adicionarArquivo(zos, leitura);
			out.close();
			leitura.delete();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);

		} catch (IOException e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		} finally {
			IoUtil.fecharStream(out);
			IoUtil.fecharStream(zos);
		}

	}

	/**
	 * Montar Helper para facilitar na criação do txt das contas do projeto
	 * especial
	 * 
	 * @author: Hugo Amorim
	 * @date: 14/12/2009
	 */
	private void montarDadosArquivoTextoContasProjetosEspeciais(
			Object[] arraydadosTxt,
			GerarArquivoTextoContasProjetosEspeciaisHelper helper) {
		// Id Localidade
		if (arraydadosTxt[0] != null) {
			helper.setIdLocalidade((arraydadosTxt[0]).toString());
		}
		// Nome Localidade
		if (arraydadosTxt[1] != null) {
			helper.setNomeLocalidade((arraydadosTxt[1]).toString());
		}
		// Matricula
		if (arraydadosTxt[2] != null) {
			helper.setMatriculaImovel((arraydadosTxt[2]).toString());
		}
		// Nome Usuario
		if (arraydadosTxt[3] != null) {
			helper.setNomeUsuario((arraydadosTxt[3]).toString());
		}
		// Endereco
		if (helper.getMatriculaImovel() != null) {
			String endereco = "";

			try {
				endereco = this.getControladorEndereco().pesquisarEndereco(
						new Integer(helper.getMatriculaImovel()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ControladorException e) {
				e.printStackTrace();
			}

			helper.setEndereco(endereco);
		}
		// Numero Hidrometro
		if (arraydadosTxt[4] != null) {
			helper.setNumeroHidrometro((arraydadosTxt[4]).toString());
		}
		// Ano Mes Referencia da Conta
		if (arraydadosTxt[5] != null) {
			helper.setAnoMesReferenciaConta((arraydadosTxt[5]).toString());
		}
		// Consumo
		if (arraydadosTxt[6] != null) {
			helper.setConsumoAgua((arraydadosTxt[6]).toString());
		}
		// Valor da Conta
		if (arraydadosTxt[7] != null) {

			BigDecimal valorConta = new BigDecimal(arraydadosTxt[7].toString());

			if (valorConta.intValue() < 0) {
				helper.setValorConta("0.0");
			} else {
				helper.setValorConta((arraydadosTxt[7]).toString());
			}

		}
		// CPF
		if (arraydadosTxt[8] != null) {
			helper.setCpf((arraydadosTxt[8]).toString());
		}
		// Setor Comercial
		if (arraydadosTxt[9] != null) {
			helper.setSetorComercial((arraydadosTxt[9]).toString());
		}
		// Grupo Faturamento
		if (arraydadosTxt[10] != null) {
			helper.setGrupoFaturamento((arraydadosTxt[10]).toString());
		}
	}

	/**
	 * Montar Arquivo txt das contas do projeto especial
	 * 
	 * @author: Hugo Amorim
	 * @date: 14/12/2009
	 */
	private void montarArquivoTextoContasProjetosEspeciais(
			StringBuilder arquivoTxt,
			GerarArquivoTextoContasProjetosEspeciaisHelper helper) {
		// id Localidade tam 03
		if (helper.getIdLocalidade() != null) {
			arquivoTxt.append(Util.truncarString(helper.getIdLocalidade()
					.toString(), 3)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Nome Localidade tam 25
		if (helper.getNomeLocalidade() != null) {
			arquivoTxt.append(Util.truncarString(helper.getNomeLocalidade()
					.toString(), 25)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Matricula tam 9
		if (helper.getMatriculaImovel() != null) {
			arquivoTxt
					.append(Util.truncarString(helper.getMatriculaImovel(), 9)
							+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Nome Usuario tam 30
		if (helper.getNomeUsuario() != null) {
			arquivoTxt.append(Util.truncarString(helper.getNomeUsuario(), 30)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Endereco tam 100
		if (helper.getEndereco() != null) {
			arquivoTxt.append(Util.truncarString(helper.getEndereco(), 100)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Numero do Hidrometro tam 11
		if (helper.getNumeroHidrometro() != null) {
			arquivoTxt.append(Util.truncarString(helper.getNumeroHidrometro(),
					11) + ";");
		} else {
			arquivoTxt.append("S/ HIDROM;");
		}
		// Ano Mes referencia da Conta tam 6
		if (helper.getAnoMesReferenciaConta() != null) {
			arquivoTxt.append(Util.truncarString(
					helper.getAnoMesReferenciaConta(), 6)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Consumo Agua tam 6
		if (helper.getConsumoAgua() != null) {
			arquivoTxt.append(Util.truncarString(helper.getConsumoAgua(), 6)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Valor Conta tam 15
		if (helper.getValorConta() != null) {
			arquivoTxt.append(Util.truncarString(helper.getValorConta(), 6)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}

		// CPF
		if (helper.getCpf() != null) {
			arquivoTxt.append(Util.truncarString(helper.getCpf(), 11) + ";");
		} else {
			arquivoTxt.append(";");
		}
		// Setor Comercial
		if (helper.getSetorComercial() != null) {
			arquivoTxt.append(Util.truncarString(helper.getSetorComercial(), 4)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Grupo Faturamento
		if (helper.getGrupoFaturamento() != null) {
			arquivoTxt.append(Util.truncarString(helper.getGrupoFaturamento(),
					4) + ";");
		} else {
			arquivoTxt.append(";");
		}
	}

	/**
	 * 
	 * [UC0972] count TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @since 15/12/2009
	 * 
	 */
	public Integer countTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente) throws ControladorException {

		int retorno = 0;

		try {

			retorno = this.repositorioFaturamento
					.countTxtContasProjetosEspeciais(anoMes, idCliente);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * Este caso de uso permite gerar o resumo de simulação em um conjunto de
	 * rotas de um grupo de faturamento.
	 * 
	 * [UC0980] - Gerar Resumo Simulação do Faturamento
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 19/01/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param anoMesReferenciaFaturamento
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoSimulacaoFaturamento(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronogramaRota))
								.getRota().getId());

		try {

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			/*
			 * Caso a coleção de atividade de faturamento de cronograma para
			 * rota não esteja nula para cada rota informada seleciona as
			 * quadras da rota e para cada quadra os imóveis
			 */
			if (colecaoFaturamentoAtividadeCronogramaRota != null
					&& !colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {

				Iterator iteratorColecaoFaturamentoAtividadeCronogramaRota = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				// Objeto que armazenará as informações para deleção das contas
				ApagarDadosFaturamentoHelper helper = new ApagarDadosFaturamentoHelper();

				// LAÇO PARA FATURAR TODAS AS ROTAS
				while (iteratorColecaoFaturamentoAtividadeCronogramaRota
						.hasNext()) {

					FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) iteratorColecaoFaturamentoAtividadeCronogramaRota
							.next();

					helper.setRota(faturamentoAtivCronRota.getRota());
					helper.setAnoMesFaturamento(faturamentoGrupo
							.getAnoMesReferencia());
					helper.setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);

					// APAGAR DADOS GERADOS DO RESUMO DA SIMULAÇÃO PARA A ROTA
					// NO ANO/MES DE REFERENCIA DO FATURAMENTO
					// =================================================================================================
					this.apagarDadosGeradosResumoFaturamentoSimulacaoDetalhe(
							faturamentoGrupo.getId(), helper);
					this.apagarDadosGeradosResumoFaturamentoSimulacao(
							faturamentoGrupo.getId(), helper);

					/*
					 * Caso o mês de faturamento corresponda ao mês de novembro,
					 * o sistema exclui também os dados do resumo da simulação
					 * do faturamento do mês de dezembro.
					 */
					if (Util.obterMes(faturamentoGrupo.getAnoMesReferencia()) == ConstantesSistema.NOVEMBRO) {

						helper.setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);

						// Cria o ano/mês de referência para dezembro do ano
						// informado
						helper.setAnoMesFaturamento(Util
								.somaUmMesAnoMesReferencia(faturamentoGrupo
										.getAnoMesReferencia()));

						// APAGAR DADOS GERADOS DO RESUMO DA SIMULAÇÃO PARA A
						// ROTA NO ANO/MES DE REFERENCIA DO FATURAMENTO
						// =================================================================================================
						this.apagarDadosGeradosResumoFaturamentoSimulacaoDetalhe(
								faturamentoGrupo.getId(), helper);
						this.apagarDadosGeradosResumoFaturamentoSimulacao(
								faturamentoGrupo.getId(), helper);

					}

					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection colecaoImovel = this
								.pesquisarImovelGrupoFaturamento(
										faturamentoAtivCronRota.getRota(),
										numeroIndice, quantidadeRegistros,
										false, true);

						// Resumos de faturamento para simulação.
						Collection colecaoResumoFaturamento = new ArrayList();

						/*
						 * Caso exista ids de imóveis para a rota atual
						 * determina o faturamento para cada imóvel retornado.
						 */
						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							Iterator iteratorColecaoImoveis = colecaoImovel
									.iterator();

							// LAÇO PARA DETERMINAR O FATURAMENTO DE TODOS OS
							// IMOVEIS DA ROTA ATUAL

							int count = 1;
							Imovel imovel = null;
							while (iteratorColecaoImoveis.hasNext()) {

								imovel = (Imovel) iteratorColecaoImoveis.next();

								// System.out.println("CONTADOR :"+count);

								// Resumo Simulacao Faturamento
								this.pesquisarContasGerarResumoSimulacaoFaturamento(
										faturamentoGrupo.getAnoMesReferencia(),
										sistemaParametro,
										faturamentoAtivCronRota,
										colecaoResumoFaturamento, imovel,
										faturamentoGrupo, false);

								// Resumo Simulacao Faturamento Antecipado
								this.pesquisarContasGerarResumoSimulacaoFaturamentoAntecipado(
										faturamentoGrupo.getAnoMesReferencia(),
										sistemaParametro,
										faturamentoAtivCronRota,
										colecaoResumoFaturamento, imovel,
										faturamentoGrupo);

								count++;

							}// FIM DO LOOP DE IMOVEIS

						}// FIM DO LOOP DE IMOVEIS

						/*
						 * Caso a coleção de resumo de faturamento não esteja
						 * vazia ou nula inseri os resumos na base de dados.
						 */
						if (colecaoResumoFaturamento != null
								&& !colecaoResumoFaturamento.isEmpty()) {

							this.inserirResumoSimulacaoFaturamento(colecaoResumoFaturamento);

							if (colecaoResumoFaturamento != null) {
								colecaoResumoFaturamento.clear();
								colecaoResumoFaturamento = null;
							}
						}

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null
								|| colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}

					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA

				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	/**
	 * Recupera as contas dos imóveis selecionados que tenham o mês ano de
	 * referência e que estejam com a situação atual igual a normal ou situação
	 * anterior igual a normal. E gera o resumo simulação do faturamento.
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 20/01/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public void pesquisarContasGerarResumoSimulacaoFaturamento(
			Integer anoMesReferencia, SistemaParametro sistemaParametro,
			FaturamentoAtivCronRota faturamentoAtivCronRota,
			Collection colecaoResumoFaturamento, Imovel imovel,
			FaturamentoGrupo faturamentoGrupo, boolean faturamentoAntecipado)
			throws ControladorException {

		GerarResumoSimulacaoFaturamentoHelper gerarResumoSimulacaoFaturamentoHelper = null;

		try {

			Object[] retornoPesquisa = repositorioFaturamento
					.pesquisarContasResumoSimulacaoFaturamento(imovel.getId(),
							anoMesReferencia);

			if (retornoPesquisa != null && retornoPesquisa.length > 0) {

				gerarResumoSimulacaoFaturamentoHelper = new GerarResumoSimulacaoFaturamentoHelper(
						(Integer) retornoPesquisa[0],
						(Integer) retornoPesquisa[1],
						(Integer) retornoPesquisa[2],
						(Integer) retornoPesquisa[3],
						(Integer) retornoPesquisa[4],
						(Integer) retornoPesquisa[5],
						(Integer) retornoPesquisa[6],
						(Integer) retornoPesquisa[7],
						(Integer) retornoPesquisa[8],
						(Integer) retornoPesquisa[9],
						(Integer) retornoPesquisa[10],
						(Integer) retornoPesquisa[11],
						(Integer) retornoPesquisa[12],
						(Integer) retornoPesquisa[13],
						(Short) retornoPesquisa[14],
						(Integer) retornoPesquisa[15],
						(BigDecimal) retornoPesquisa[16],
						(Integer) retornoPesquisa[17],
						(BigDecimal) retornoPesquisa[18],
						(Integer) retornoPesquisa[19],
						(BigDecimal) retornoPesquisa[20],
						(BigDecimal) retornoPesquisa[21],
						(BigDecimal) retornoPesquisa[22]);

				// Valor dos Debitos
				FiltroDebitoCobrado filtroDebitoCobrado = new FiltroDebitoCobrado();

				filtroDebitoCobrado.adicionarParametro(new ParametroSimples(
						FiltroDebitoCobrado.CONTA_ID,
						gerarResumoSimulacaoFaturamentoHelper.getIdConta()));

				filtroDebitoCobrado
						.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoCobrado.DEBITO_TIPO);

				Collection<DebitoCobrado> colecaoDebitos = this
						.getControladorUtil().pesquisar(filtroDebitoCobrado,
								DebitoCobrado.class.getName());

				Map<DebitoTipo, BigDecimal> mapValoresPorTipoDebito = null;

				if (!Util.isVazioOrNulo(colecaoDebitos)) {

					mapValoresPorTipoDebito = new HashMap<DebitoTipo, BigDecimal>();

					for (DebitoCobrado debitoCobrado : colecaoDebitos) {
						if (mapValoresPorTipoDebito.containsKey(debitoCobrado
								.getDebitoTipo())) {
							BigDecimal valor = mapValoresPorTipoDebito
									.get(debitoCobrado.getDebitoTipo());
							mapValoresPorTipoDebito.put(
									debitoCobrado.getDebitoTipo(),
									Util.somaBigDecimal(valor,
											debitoCobrado.getValorPrestacao()));
						}
						// Caso contrario inseri na coleção
						// primeiro registro do tipo.
						else {
							mapValoresPorTipoDebito.put(
									debitoCobrado.getDebitoTipo(),
									debitoCobrado.getValorPrestacao());
						}
					}
				}

				GerarDebitoCobradoHelper gerarDebitoCobradoHelper = new GerarDebitoCobradoHelper();

				gerarDebitoCobradoHelper
						.setValorTotalDebito(gerarResumoSimulacaoFaturamentoHelper
								.getValorDebitos());

				gerarDebitoCobradoHelper
						.setMapValoresPorTipoDebito(mapValoresPorTipoDebito);

				GerarImpostosDeduzidosContaHelper helperImpostos = new GerarImpostosDeduzidosContaHelper();

				helperImpostos
						.setValorTotalImposto(gerarResumoSimulacaoFaturamentoHelper
								.getValorImpostos());

				gerarDebitoCobradoHelper
						.setGerarImpostosDeduzidosContaHelper(helperImpostos);

				// Valor dos Creditos
				FiltroCreditoRealizado filtroCreditoRealizado = new FiltroCreditoRealizado();

				filtroCreditoRealizado.adicionarParametro(new ParametroSimples(
						FiltroCreditoRealizado.CONTA_ID,
						gerarResumoSimulacaoFaturamentoHelper.getIdConta()));

				filtroCreditoRealizado
						.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoRealizado.CREDITO_TIPO);

				Collection<CreditoRealizado> colecaoCreditos = this
						.getControladorUtil().pesquisar(filtroCreditoRealizado,
								CreditoRealizado.class.getName());

				Map<CreditoTipo, BigDecimal> mapValoresPorTipoCredito = null;

				if (!Util.isVazioOrNulo(colecaoCreditos)) {

					mapValoresPorTipoCredito = new HashMap<CreditoTipo, BigDecimal>();

					for (CreditoRealizado creditoRealizado : colecaoCreditos) {
						if (mapValoresPorTipoCredito
								.containsKey(creditoRealizado.getCreditoTipo())) {
							BigDecimal valor = mapValoresPorTipoCredito
									.get(creditoRealizado.getCreditoTipo());
							mapValoresPorTipoCredito
									.put(creditoRealizado.getCreditoTipo(),
											Util.somaBigDecimal(valor,
													creditoRealizado
															.getValorCredito()));
						}
						// Caso contrario inseri na coleção
						// primeiro registro do tipo.
						else {
							mapValoresPorTipoCredito.put(
									creditoRealizado.getCreditoTipo(),
									creditoRealizado.getValorCredito());
						}
					}
				}

				GerarCreditoRealizadoHelper gerarCreditoRealizadoHelper = new GerarCreditoRealizadoHelper();

				gerarCreditoRealizadoHelper
						.setValorTotalCredito(gerarResumoSimulacaoFaturamentoHelper
								.getValorCreditos());

				gerarCreditoRealizadoHelper
						.setMapValoresPorTipoCredito(mapValoresPorTipoCredito);

				// Pesquisa as categorias da conta
				Collection colecaoContaCategoria = repositorioFaturamento
						.pesquisarContaCategoria(gerarResumoSimulacaoFaturamentoHelper
								.getIdConta());

				Conta conta = new Conta();
				conta.setId(gerarResumoSimulacaoFaturamentoHelper.getIdConta());

				Collection colecaoCategoriasConta = this.getControladorImovel()
						.obterQuantidadeEconomiasContaCategoria(conta);

				Collection colecaoCategoriasOrdenado = new ArrayList();

				DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = new DeterminarValoresFaturamentoAguaEsgotoHelper();

				Collection colecaoCalcularValoresAguaEsgotoHelper = new ArrayList();

				if (colecaoContaCategoria != null
						&& !colecaoContaCategoria.isEmpty()) {

					Iterator colecaoContaCategoriaIterator = colecaoContaCategoria
							.iterator();

					while (colecaoContaCategoriaIterator.hasNext()) {

						ContaCategoria contaCategoria = (ContaCategoria) colecaoContaCategoriaIterator
								.next();

						CalcularValoresAguaEsgotoHelper calcularValoresAguaEsgotoHelper = new CalcularValoresAguaEsgotoHelper();

						calcularValoresAguaEsgotoHelper
								.setIdCategoria(contaCategoria.getComp_id()
										.getCategoria().getId());

						calcularValoresAguaEsgotoHelper
								.setConsumoFaturadoAguaCategoria(contaCategoria
										.getConsumoAgua());

						calcularValoresAguaEsgotoHelper
								.setConsumoFaturadoEsgotoCategoria(contaCategoria
										.getConsumoEsgoto());

						calcularValoresAguaEsgotoHelper
								.setValorFaturadoAguaCategoria(contaCategoria
										.getValorAgua());

						calcularValoresAguaEsgotoHelper
								.setValorFaturadoEsgotoCategoria(contaCategoria
										.getValorEsgoto());

						colecaoCalcularValoresAguaEsgotoHelper
								.add(calcularValoresAguaEsgotoHelper);

						// [UC0108] - Obter Quantidade de Economias por
						// Categoria
						if (colecaoCategoriasConta != null
								&& !colecaoCategoriasConta.isEmpty()) {

							Iterator colecaoCategoriaContaIterator = colecaoCategoriasConta
									.iterator();

							while (colecaoCategoriaContaIterator.hasNext()) {
								Categoria categoria = (Categoria) colecaoCategoriaContaIterator
										.next();

								if (categoria.getId().intValue() == contaCategoria
										.getComp_id().getCategoria().getId()
										.intValue()) {

									colecaoCategoriasOrdenado.add(categoria);

									break;
								}
							}
						}
					}

				}

				helperValoresAguaEsgoto
						.setColecaoCalcularValoresAguaEsgotoHelper(colecaoCalcularValoresAguaEsgotoHelper);

				Integer anoMesReferenciaResumoFaturamento = null;
				if (faturamentoAntecipado) {
					anoMesReferenciaResumoFaturamento = anoMesReferencia;
				}

				// [SB0009] - Gerar Resumo da Simulação do Faturamento
				this.gerarResumoFaturamentoSimulacao(colecaoCategoriasOrdenado,
						helperValoresAguaEsgoto
								.getColecaoCalcularValoresAguaEsgotoHelper(),
						gerarDebitoCobradoHelper, gerarCreditoRealizadoHelper,
						colecaoResumoFaturamento, imovel, true,
						faturamentoAtivCronRota, faturamentoGrupo,
						anoMesReferenciaResumoFaturamento, false);

			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Este caso de uso permite gerar resumo simulacao faturamento de um imóvel
	 * de um grupo de faturamento de forma antecipada.
	 * 
	 * 
	 * @author Fernando Fontelles
	 * @date 26/01/2010
	 * 
	 * @param anoMesFaturamento
	 * @param atividade
	 * @param sistemaParametro
	 * @param faturamentoAtivCronRota
	 * @param colecaoResumoFaturamento
	 * @param imovel
	 * @throws ControladorException
	 */
	public void pesquisarContasGerarResumoSimulacaoFaturamentoAntecipado(
			Integer anoMesReferencia, SistemaParametro sistemaParametro,
			FaturamentoAtivCronRota faturamentoAtivCronRota,
			Collection colecaoResumoFaturamento, Imovel imovel,
			FaturamentoGrupo faturamentoGrupo) throws ControladorException {

		/*
		 * Caso o mês de faturamento corresponda ao mês de novembro, o sistema
		 * verifica se haverá faturamento antecipado
		 */
		if (Util.obterMes(anoMesReferencia) == ConstantesSistema.NOVEMBRO
				&& sistemaParametro.getIndicadorFaturamentoAntecipado().equals(
						ConstantesSistema.SIM)) {

			Integer anoMesFaturamentoAntecipado = Util
					.somaUmMesAnoMesReferencia(anoMesReferencia);

			Integer idConsumoHistorico = getControladorMicromedicao()
					.pesquisarConsumoHistoricoAntecipado(imovel.getId(),
							anoMesFaturamentoAntecipado);

			if (idConsumoHistorico != null) {

				this.pesquisarContasGerarResumoSimulacaoFaturamento(
						anoMesFaturamentoAntecipado, sistemaParametro,
						faturamentoAtivCronRota, colecaoResumoFaturamento,
						imovel, faturamentoGrupo, true);
			}
		}
	}

	/**
	 * 
	 * [UC0394] Gerar Débitos a Cobrar de Doações
	 * 
	 * Efetua cancelamento do imovel caso ano/mês final de adesão seja menor ou
	 * igual ano/mês do grupo de faturamento da rota
	 * 
	 * @author Hugo Amorim
	 * @date 02/02/2010
	 * 
	 */
	private void efetuarCancelamentoImovelParaDoacoes(
			ImovelCobrarDoacaoHelper imovelCobrarDoacaoHelper, Rota rota)
			throws ControladorException {

		FiltroImovelDoacao filtroImovelDoacao = new FiltroImovelDoacao();

		filtroImovelDoacao.adicionarParametro(new ParametroSimples(
				FiltroImovelDoacao.ID, imovelCobrarDoacaoHelper
						.getIdImovelDoacao()));

		Collection colecaoImovelDoacao = this.getControladorUtil().pesquisar(
				filtroImovelDoacao, ImovelDoacao.class.getName());

		ImovelDoacao imovelDoacao = (ImovelDoacao) Util
				.retonarObjetoDeColecao(colecaoImovelDoacao);

		if (imovelDoacao != null
				&& imovelDoacao.getAnoMesReferenciaFinal() != null
				&& !imovelDoacao.getAnoMesReferenciaFinal().toString()
						.equals("")) {

			FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();

			filtro.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, rota.getFaturamentoGrupo()
							.getId()));

			Collection colecaoFaturamentoGrupo = this.getControladorUtil()
					.pesquisar(filtro, FaturamentoGrupo.class.getName());

			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util
					.retonarObjetoDeColecao(colecaoFaturamentoGrupo);

			// Ano/mês final da adesão do imóvel seja diferente de nulo
			// e que seja o menor ou igual o ano/mês de referência do grupo de
			// faturamento da rota.
			if (imovelDoacao.getAnoMesReferenciaFinal().compareTo(
					faturamentoGrupo.getAnoMesReferencia()) == 0
					|| imovelDoacao.getAnoMesReferenciaFinal().compareTo(
							faturamentoGrupo.getAnoMesReferencia()) < 0) {

				imovelDoacao.setDataCancelamento(new Date());
				imovelDoacao.setUsuarioCancelamento(Usuario.USUARIO_BATCH);

				this.getControladorUtil().atualizar(imovelDoacao);
			}

		}

	}

	/**
	 * Este caso de uso permite enviar email para cliente informando que sua
	 * conta já foi gerada. Retorno do celular
	 * 
	 * @author Raphael Rossiter
	 * @date 03/03/2010
	 * 
	 * @param rota
	 * @param colContaPreFaturada
	 * @param efetuarRateio
	 * @param atualizaSituacaoAtualConta
	 *            - Caso seja chamado via a funcionalidade de ISC, não atualiza
	 *            a situação atual da conta que não foi impressa. Caso seja
	 *            chamado via a funcionalidade de consistir, atualiza a situação
	 *            atual da conta.
	 * @throws ControladorException
	 */
	public void processarMovimentoContaPrefaturada(Rota rota, Collection<MovimentoContaPrefaturada> colContaPreFaturada, boolean efetuarRateio) throws ControladorException {

		try {
			if (colContaPreFaturada != null && !colContaPreFaturada.isEmpty()) {

				Collection<Imovel> colImoveisCondominio = new ArrayList();
				Collection<Imovel> colImoveis = new ArrayList();

				Collection<DadosMovimentacao> colecaoDadosMovimentacao = new ArrayList();

				Long imei = null;
				if (rota != null && rota.getLeiturista() != null && !rota.getLeiturista().equals("")) {
					imei = rota.getLeiturista().getNumeroImei();
				}

				Collection<Integer> colIdImoveis = new ArrayList();
				BigDecimal valorRateio = new BigDecimal(0);
				
				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {

					if (!colIdImoveis.contains(movimentoContaPreFaturada.getImovel().getId())) {
						colIdImoveis.add(movimentoContaPreFaturada.getImovel().getId());
						colImoveis.add(movimentoContaPreFaturada.getImovel());
					}

					FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
					filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, movimentoContaPreFaturada.getImovel().getId()));
					filtroLigacaoAgua.adicionarParametro(new ParametroNaoNulo(FiltroLigacaoAgua.HIDROMETRO_INSTALACAO_HISTORICO));
					
					Collection<LigacaoAgua> colLigacaoAgua = this.repositorioUtil.pesquisar(filtroLigacaoAgua,LigacaoAgua.class.getName());

					if ((colLigacaoAgua != null && colLigacaoAgua.size() > 0) || movimentoContaPreFaturada.getImovel().getHidrometroInstalacaoHistorico() != null) {

						if ((movimentoContaPreFaturada.getMedicaoTipo().getId() == MedicaoTipo.LIGACAO_AGUA.intValue() 
								&& colLigacaoAgua != null && colLigacaoAgua.size() > 0)
								|| (movimentoContaPreFaturada.getMedicaoTipo().getId() == MedicaoTipo.POCO.intValue() 
								&& movimentoContaPreFaturada.getImovel().getHidrometroInstalacaoHistorico() != null)) {
							incluirMedicaoHistorico(movimentoContaPreFaturada);
						}
					} else {

						if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null 
								&& movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId() != null
								&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId().equals("")) {
							
							Imovel imovel = movimentoContaPreFaturada.getImovel();
							imovel.setLeituraAnormalidade(movimentoContaPreFaturada.getLeituraAnormalidadeLeitura());
							imovel.setUltimaAlteracao(new Date());

							RepositorioImovelHBM.getInstancia().atualizarImovelLeituraAnormalidadeProcessoMOBILE(imovel);
						}
					}

					if (movimentoContaPreFaturada.getImovel().getIndicadorImovelCondominio().equals(ConstantesSistema.SIM)) {

						boolean achou = false;

						for (Imovel imovel : colImoveisCondominio) {
							if (imovel.getId() == movimentoContaPreFaturada.getImovel().getId()) {
								achou = true;
								break;
							}
						}

						if (!achou) {
							colImoveisCondominio.add(movimentoContaPreFaturada.getImovel());
						}
					}

					Integer idAnormalidade = null;
					if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null) {
						idAnormalidade = movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId();
					}

					Byte indicadorConfirmacao = new Byte("0");
					if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() != null && !movimentoContaPreFaturada.getIndicadorSituacaoLeitura().equals("")) {
						indicadorConfirmacao = new Byte(""+ movimentoContaPreFaturada.getIndicadorSituacaoLeitura());
					}

					Integer idMedicaoTipo = null;
					if ((movimentoContaPreFaturada.getLeituraHidrometro() != null && !movimentoContaPreFaturada.getLeituraHidrometro().equals(""))
							|| (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null 
							&& movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId() != null 
							&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId().equals(""))) {
						idMedicaoTipo = movimentoContaPreFaturada.getMedicaoTipo().getId();
					}

					DadosMovimentacao dadosMovimentacao = new DadosMovimentacao(
							movimentoContaPreFaturada.getImovel().getId(),
							movimentoContaPreFaturada.getLeituraHidrometro(),
							idAnormalidade,
							movimentoContaPreFaturada.getDataHoraLeitura(),
							imei, indicadorConfirmacao, idMedicaoTipo);

					colecaoDadosMovimentacao.add(dadosMovimentacao);

				}

				// ataliza movimento roteiro empresa
				if (colecaoDadosMovimentacao != null && !colecaoDadosMovimentacao.isEmpty()) {
					getControladorMicromedicao().atualizarRoteiro(colecaoDadosMovimentacao, true);
				}

				if (colIdImoveis != null && !colIdImoveis.isEmpty()) {
					this.getControladorMicromedicao().consistirLeiturasCalcularConsumosImoveis(rota.getFaturamentoGrupo(), colIdImoveis);
					this.atualizarFaturamentoImoveisCortados(colImoveis, rota.getFaturamentoGrupo().getAnoMesReferencia().intValue());
					colImoveis = null;
				}

				// Verificamos se devemos efetuar o rateio
				if (colImoveisCondominio != null && colImoveisCondominio.size() > 0 && efetuarRateio) {
					for (Imovel imovelCondominio : colImoveisCondominio) {
						this.getControladorMicromedicao().efetuarRateioDeConsumo(imovelCondominio.getId(),rota.getFaturamentoGrupo().getAnoMesReferencia());
					}
				}

				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {
					Date dataLeituraAtual = movimentoContaPreFaturada.getDataHoraLeitura();
					
					if (dataLeituraAtual == null || dataLeituraAtual.equals("")) {
						dataLeituraAtual = new Date();
					}
					getControladorMicromedicao().atualizarDataRealizacaoGronogramaPreFaturamento(
							rota.getFaturamentoGrupo().getId(),
							rota.getFaturamentoGrupo().getAnoMesReferencia(),
							dataLeituraAtual);
					break;
				}

				this.atualizarMovimentoCelular(colContaPreFaturada, efetuarRateio);

				// não atualizar o indicador de atualizacao de faturamento caso indicador de emissao de conta seja igual a 2 e o imóvel não
				// esteja vinculado com nenhuma outra matrícula (Imóvel micro) ou venha pela funcionalidade de consistir
				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {

					if (movimentoContaPreFaturada.getMovimentoContaPrefaturadaCategorias() != null
							&& movimentoContaPreFaturada.getMovimentoContaPrefaturadaCategorias().size() > 0) {

						if ((movimentoContaPreFaturada.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.SIM.shortValue())
								|| (movimentoContaPreFaturada.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue() 
								&& movimentoContaPreFaturada.getImovel().getImovelCondominio() != null)) {

							movimentoContaPreFaturada.setUtlimaAlteracao(new Date());
							movimentoContaPreFaturada.setIndicadorAtualizacaoFaturamento(Short.parseShort("1"));

							repositorioFaturamento.atualizarMovimentoContaPrefaturadaProcessoMOBILE(movimentoContaPreFaturada);
						}
					}
				}
			}

		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0994] - Envio de Email da Conta para o Cliente
	 * 
	 * @author Fernando Fontelles
	 * @date 02/03/2010
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param localidade
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void envioEmailContaParaCliente(Localidade localidade,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, localidade.getId());

		try {

			SistemaParametro sistemaParametro = this.getControladorUtil()
					.pesquisarParametrosDoSistema();

			Collection colecaoImoveisEmails = repositorioFaturamento
					.pesquisarContasImpressasParaEnvioEmail(localidade.getId(),
							sistemaParametro);

			/*
			 * Collection colecaoImoveisEmails = repositorioFaturamento
			 * .pesquisarContasImpressasParaEnvioEmail(idRota,
			 * faturamentoGrupo.getAnoMesReferencia());
			 */

			Collection colecaoImoveisEmailsPreFaturados = repositorioFaturamento
					.pesquisarContasPrefaturadasParaEnvioEmail(
							sistemaParametro, localidade.getId());

			// Adiciona os imoveis PreFaturados a colecao de imoveis para envio
			// de email
			if (colecaoImoveisEmailsPreFaturados != null
					&& !colecaoImoveisEmailsPreFaturados.isEmpty()) {

				colecaoImoveisEmails.addAll(colecaoImoveisEmailsPreFaturados);

			}

			if (colecaoImoveisEmails != null && !colecaoImoveisEmails.isEmpty()) {

				Iterator colecaoImoveisEmailsIterator = colecaoImoveisEmails
						.iterator();

				while (colecaoImoveisEmailsIterator.hasNext()) {

					Object[] imoveisEmails = (Object[]) colecaoImoveisEmailsIterator
							.next();

					Integer idImovel = (Integer) imoveisEmails[0];
					String emailReceptor = (String) imoveisEmails[1];

					String mesAnoFormatado = Util
							.formatarAnoMesParaMesAno(sistemaParametro
									.getAnoMesArrecadacao());

					Conta conta = this.pesquisarContaDigitada(
							idImovel.toString(), mesAnoFormatado);

					try {

						// Envia de Arquivo por email
						EnvioEmail envioEmail = this
								.getControladorCadastro()
								.pesquisarEnvioEmail(
										EnvioEmail.ENVIO_EMAIL_CONTA_PARA_CLIENTE);

						String emailRemetente = envioEmail.getEmailRemetente();
						String tituloMensagem = envioEmail.getTituloMensagem();
						String corpoMensagem = "A "
								+ sistemaParametro.getNomeEmpresa()
								+ " informa que a conta do imóvel de matrícula "
								+ idImovel
								+ " está disponível para acessá-la na página da internet "
								+ sistemaParametro.getUrlAcessoInternet()
								+
								// +sistemaParametro.getUrl2ViaConta()+"&idImovel="+idImovel
								"/gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta="
								+ conta.getId();

						ServicosEmail.enviarMensagem(emailRemetente,
								emailReceptor, tituloMensagem, corpoMensagem);

						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro(new ParametroSimples(
								FiltroConta.ID, conta.getId()));

						Conta c = (Conta) Util.retonarObjetoDeColecao(Fachada
								.getInstancia().pesquisar(filtroConta,
										Conta.class.getName()));
						c.setDataEnvioEmailConta(new Date());
						c.setUltimaAlteracao(new Date());

						this.getControladorUtil().atualizar(c);

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Erro ao enviar email.");
					}

				}

			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 * 
	 * @author Hugo Leonardo
	 * @throws ControladorException
	 * @throws ControladorException
	 * @data 26/02/2010
	 * 
	 */
	public Collection pesquisarFaturamentoImediatoAjuste(
			FaturamentoImediatoAjusteHelper helper, int qtd)
			throws ControladorException {

		Collection colecaoRetorno = new ArrayList();

		// Pesquisa
		try {
			Collection faturamentoImediatoAjuste = repositorioFaturamento
					.pesquisarFaturamentoImediatoAjuste(helper
							.getMesAnoReferencia().toString(), helper
							.getFaturamentoGrupo() != null ? helper
							.getFaturamentoGrupo().toString() : null, helper
							.getImovelId() != null ? helper.getImovelId()
							.toString() : null,
							helper.getRotaId() != null ? helper.getRotaId()
									.toString() : null, qtd);

			Iterator iterator = faturamentoImediatoAjuste.iterator();

			while (iterator.hasNext()) {

				FaturamentoImediatoAjusteHelper relatorioHelper = new FaturamentoImediatoAjusteHelper();

				Object[] objeto = (Object[]) iterator.next();

				// Matricula do imóvel
				if (objeto[0] != null) {
					Integer imovelMatricula = (Integer) objeto[0];

					relatorioHelper.setImovelId(Util
							.retornaMatriculaImovelFormatada(imovelMatricula));
					relatorioHelper.setInscricao(this.getControladorImovel()
							.pesquisarInscricaoImovelExcluidoOuNao(
									imovelMatricula));
				}

				// Grupo Faturamento
				if (objeto[1] != null) {
					Integer faturamentoGrupo = (Integer) objeto[1];

					relatorioHelper.setFaturamentoGrupo(faturamentoGrupo
							.toString());
				}

				// Rota
				if (objeto[2] != null) {
					Short rota = (Short) objeto[2];

					relatorioHelper.setRota(rota.toString());
				}

				// Dif. Valor da Água
				if (objeto[3] != null) {
					BigDecimal valorAgua = (BigDecimal) objeto[3];

					relatorioHelper.setDifValorAgua(Util
							.formatarMoedaReal(valorAgua));
				} else {
					relatorioHelper.setDifValorAgua("0,00");
				}

				// Dif. Consumo de Água
				if (objeto[4] != null) {
					Integer consumoAgua = (Integer) objeto[4];

					relatorioHelper.setDifConsumoAgua(consumoAgua.toString());
				} else {
					relatorioHelper.setDifConsumoAgua("0");
				}

				// Dif. Valor do Esgoto
				if (objeto[5] != null) {
					BigDecimal valorEsgoto = (BigDecimal) objeto[5];

					relatorioHelper.setDifValorEsgoto(Util
							.formatarMoedaReal(valorEsgoto));
				} else {
					relatorioHelper.setDifValorEsgoto("0,00");
				}

				// Dif. Consumo de Esgoto
				if (objeto[6] != null) {
					Integer consumoEsgoto = (Integer) objeto[6];

					relatorioHelper.setDifConsumoEsgoto(consumoEsgoto
							.toString());
				} else {
					relatorioHelper.setDifConsumoEsgoto("0");
				}

				// anoMes referência
				if (objeto[7] != null) {
					Integer anoMes = (Integer) objeto[7];

					relatorioHelper.setMesAnoReferencia(Util
							.formatarAnoMesParaMesAno(anoMes));
				}

				colecaoRetorno.add(relatorioHelper);
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoRetorno;
	}

	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 * 
	 * @author Hugo Leonardo
	 * @param form
	 * @throws ControladorException
	 * @throws ControladorException
	 * @data 01/03/2010
	 * 
	 */
	public Integer contarFaturamentoImediatoAjuste(
			FaturamentoImediatoAjusteHelper helper) throws ControladorException {

		try {
			Integer qtdSetores = this.repositorioFaturamento
					.contarFaturamentoImediatoAjuste(helper
							.getMesAnoReferencia().toString(), helper
							.getFaturamentoGrupo() != null ? helper
							.getFaturamentoGrupo().toString() : null, helper
							.getImovelId() != null ? helper.getImovelId()
							.toString() : null,
							helper.getRotaId() != null ? helper.getRotaId()
									.toString() : null);

			return qtdSetores;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração de declaração de quitação de débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 17/03/2010
	 */
	public void gerarDadosDeclaracaoQuitacaoAnualDebitos(
			int idFuncionalidadeIniciada, Collection<Integer> anos, Rota rota,
			Short indicadorContaParcelada, Short indicadorCobrancaJudical,
			Date dataVerificacaoPagamentos) throws ControladorException {

		SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA, rota.getId());
		try {

			for (Integer ano : anos) {

				// Variáveis para a paginação da pesquisa
				// ========================================================================
				boolean flagTerminou = false;
				final int quantidadeMaxima = 500;
				int quantidadeInicio = 0;
				// ========================================================================

				while (!flagTerminou) {

					Collection<Integer> colecaoIdsImoveis = this.repositorioFaturamento
							.pesquisarImoveisParaGeracaoDaDeclaracaodeQuitacaoDebitos(
									rota.getId(), quantidadeInicio,
									quantidadeMaxima);

					if (colecaoIdsImoveis != null
							&& !colecaoIdsImoveis.isEmpty()) {

						for (Integer idImovel : colecaoIdsImoveis) {

							// [FS0001] Declaração já gerada para o imovel no
							// ano de referencia
							if (declaracaoJaGeradaParaAnoReferencia(idImovel,
									ano)) {
								continue;
							}

							// O sistema deverá verificar para cada imóvel, se
							// todas as faturas foram pagas
							// no ano de referência nos meses de janeiro a
							// dezembro.
							DeclaracaoQuitacaoAnualDebitosHelper helper = this
									.pesquisarDadosParaGeracaoDaDeclaracaodeQuitacaoDebitos(
											idImovel, ano,
											dataVerificacaoPagamentos,
											indicadorContaParcelada,
											indicadorCobrancaJudical);

							if (helper.getExtratoQuitacaoItens().size() >= 12) {

								ExtratoQuitacao extratoQuitacao = new ExtratoQuitacao();

								extratoQuitacao.setImovel(new Imovel(helper.getIdImovel()));
								extratoQuitacao.setValorTotalDasContas(helper.getValorTotalContas());
								extratoQuitacao.setIndicadorImpressao(ConstantesSistema.NAO.intValue());
								extratoQuitacao.setIndicadorImpressaoNaConta(ConstantesSistema.NAO.intValue()); 
								extratoQuitacao.setAnoReferencia(ano);
								extratoQuitacao.setUltimaAlteracao(new Date());
								extratoQuitacao.setAnoMesMensagemConta(sistemaParametro.getAnoMesFaturamento());

								Integer idextratoQuitacao = (Integer) this.getControladorUtil().inserir(extratoQuitacao);

								extratoQuitacao.setId(idextratoQuitacao);

								for (Iterator iterator = helper
										.getExtratoQuitacaoItens().iterator(); iterator
										.hasNext();) {
									DeclaracaoQuitacaoAnualDebitosItemHelper helperItem = (DeclaracaoQuitacaoAnualDebitosItemHelper) iterator
											.next();

									ExtratoQuitacaoItem item = new ExtratoQuitacaoItem();

									item.setExtratoQuitacao(extratoQuitacao);

									ContaGeral contaGeral = new ContaGeral();
									contaGeral.setId(helperItem.getIdConta());

									item.setContaGeral(contaGeral);
									item.setValorConta(helperItem
											.getValorTotalConta());
									item.setDescricaoSituacao(helperItem
											.getDescricaoSituacao());
									item.setDataSituacao(helperItem
											.getDataSituacao());
									item.setUltimaAlteracao(new Date());
									item.setAnoMesReferenciaConta(helperItem
											.getAnoMesReferencia());

									this.getControladorUtil().inserir(item);

								}

							}

						}

					}

					// Incrementa o nº do indice da páginação
					quantidadeInicio = quantidadeInicio + quantidadeMaxima;

					/**
					 * Caso a coleção de dados retornados for menor que a
					 * quantidade de registros seta a flag indicando que a
					 * paginação terminou.
					 */
					if (colecaoIdsImoveis == null
							|| colecaoIdsImoveis.size() < quantidadeMaxima) {

						flagTerminou = true;
					}

					if (colecaoIdsImoveis != null) {
						colecaoIdsImoveis.clear();
						colecaoIdsImoveis = null;
					}

				}

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			e.printStackTrace();

			// Este catch serve para interceptar
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		}
	}

	private boolean declaracaoJaGeradaParaAnoReferencia(Integer idImovel,
			Integer ano) throws ControladorException {
		boolean retorno = false;

		FiltroExtratoQuitacao filtro = new FiltroExtratoQuitacao();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ID_IMOVEL, idImovel));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ANO_REFERENCIA, ano));

		Collection colecaoExtratoQuitacao = this.getControladorUtil()
				.pesquisar(filtro, ExtratoQuitacao.class.getName());

		if (colecaoExtratoQuitacao != null && !colecaoExtratoQuitacao.isEmpty()) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pequisa as contas do imovel e verifica se o mesmo esta de acordo com os
	 * parametros do caso de uso, se sim retorno uma coleção de dados para
	 * inserção.
	 * 
	 * @author Hugo Amorim
	 * @param indicadorCobrancaJudical
	 * @param indicadorContaParcelada
	 * @throws ErroRepositorioException
	 * @date 17/03/2010
	 */
	public DeclaracaoQuitacaoAnualDebitosHelper pesquisarDadosParaGeracaoDaDeclaracaodeQuitacaoDebitos(
			Integer idImovel, Integer ano, Date dataVerificacaoPagamentos,
			Short indicadorContaParcelada, Short indicadorCobrancaJudical)
			throws ControladorException {

		DeclaracaoQuitacaoAnualDebitosHelper retorno = new DeclaracaoQuitacaoAnualDebitosHelper(
				idImovel, ano);

		Collection colecaoDadosContasPagas = null;
		Collection colecaoDadosContasCanceladas = null;
		Collection colecaoDadosContasParceladas = null;
		Collection colecaoDadosContasEmCobrancaJudicial = null;

		Collection<DeclaracaoQuitacaoAnualDebitosItemHelper> colecaoItemHelper = new ArrayList<DeclaracaoQuitacaoAnualDebitosItemHelper>();

		DeclaracaoQuitacaoAnualDebitosItemHelper itemHelper = null;

		BigDecimal valorTotalDasContas = new BigDecimal("0.0");

		try {

			// Verifica Contas Pagas
			colecaoDadosContasPagas = this.repositorioFaturamento
					.pesquisarContasPagasGeracaoDeclaracaoQuitacao(idImovel,
							ano + "%", dataVerificacaoPagamentos);

			for (Iterator iteradadosContasPagas = colecaoDadosContasPagas
					.iterator(); iteradadosContasPagas.hasNext();) {
				Object[] dadosContasPagas = (Object[]) iteradadosContasPagas
						.next();

				itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
						(Integer) dadosContasPagas[1],// Id da Conta
						(Integer) dadosContasPagas[8],// AnoMês Referencia
						(BigDecimal) dadosContasPagas[2],// Valor Agua
						(BigDecimal) dadosContasPagas[3],// Valor Esgoto
						(BigDecimal) dadosContasPagas[4],// Valor Debitos
						(BigDecimal) dadosContasPagas[5],// Valor Creditos
						(BigDecimal) dadosContasPagas[6],// Valor Impostos
						(Date) dadosContasPagas[7],// Data
						(String) dadosContasPagas[9]);// Situacao Debito

				itemHelper.setValorTotalConta(itemHelper.getValorTotal());
				valorTotalDasContas = valorTotalDasContas.add(itemHelper
						.getValorTotal());

				colecaoItemHelper.add(itemHelper);

			}

			// Verifica Contas Canceladas
			colecaoDadosContasCanceladas = this.repositorioFaturamento
					.pesquisarContasCanceladasGeracaoDeclaracaoQuitacao(
							idImovel, ano + "%", dataVerificacaoPagamentos);

			labelCanceladas: for (Iterator iterator = colecaoDadosContasCanceladas
					.iterator(); iterator.hasNext();) {
				Object[] dadosContasCanceladas = (Object[]) iterator.next();

				itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
						(Integer) dadosContasCanceladas[1],// Id da Conta
						(Integer) dadosContasCanceladas[8],// AnoMês Referencia
						(BigDecimal) dadosContasCanceladas[2],// Valor Agua
						(BigDecimal) dadosContasCanceladas[3],// Valor Esgoto
						(BigDecimal) dadosContasCanceladas[4],// Valor Debitos
						(BigDecimal) dadosContasCanceladas[5],// Valor
																// Creditos
						(BigDecimal) dadosContasCanceladas[6],// Valor
																// Impostos
						(Date) dadosContasCanceladas[7],// Data
						(String) dadosContasCanceladas[9]);// Situacao Debito

				itemHelper.setValorTotalConta(itemHelper.getValorTotal());
				valorTotalDasContas = valorTotalDasContas.add(itemHelper
						.getValorTotal());

				for (DeclaracaoQuitacaoAnualDebitosItemHelper helper : colecaoItemHelper) {

					if (helper.equalsAnoMesConta(itemHelper)) {
						if (helper.getIdConta().compareTo(
								itemHelper.getIdConta()) < 0) {
							colecaoItemHelper.remove(helper);
							colecaoItemHelper.add(itemHelper);
							continue labelCanceladas;
						} else {
							continue labelCanceladas;
						}
					}
				}
				colecaoItemHelper.add(itemHelper);
			}

			// Verifica Contas Parceladas caso indicadorContaParcelada seja
			// igual a sim para verificar contas parceladas.
			if (indicadorContaParcelada.compareTo(ConstantesSistema.SIM) == 0) {
				colecaoDadosContasParceladas = this.repositorioFaturamento
						.pesquisarContasParceladasGeracaoDeclaracaoQuitacao(
								idImovel, ano + "%", dataVerificacaoPagamentos);

				labelParceladas: for (Iterator iterator = colecaoDadosContasParceladas
						.iterator(); iterator.hasNext();) {
					Object[] dadosContasParceladas = (Object[]) iterator.next();

					itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
							(Integer) dadosContasParceladas[1],// Id da Conta
							(Integer) dadosContasParceladas[8],// AnoMês
																// Referencia
							(BigDecimal) dadosContasParceladas[2],// Valor
																	// Agua
							(BigDecimal) dadosContasParceladas[3],// Valor
																	// Esgoto
							(BigDecimal) dadosContasParceladas[4],// Valor
																	// Debitos
							(BigDecimal) dadosContasParceladas[5],// Valor
																	// Creditos
							(BigDecimal) dadosContasParceladas[6],// Valor
																	// Impostos
							(Date) dadosContasParceladas[7],// Data
							(String) dadosContasParceladas[9]);// Situacao
																// Debito

					itemHelper.setValorTotalConta(itemHelper.getValorTotal());
					valorTotalDasContas = valorTotalDasContas.add(itemHelper
							.getValorTotal());

					for (DeclaracaoQuitacaoAnualDebitosItemHelper helper : colecaoItemHelper) {

						if (helper.equalsAnoMesConta(itemHelper)) {
							if (helper.getIdConta().compareTo(
									itemHelper.getIdConta()) < 0) {
								colecaoItemHelper.remove(helper);
								colecaoItemHelper.add(itemHelper);

								continue labelParceladas;
							} else {
								continue labelParceladas;
							}
						}
					}
					colecaoItemHelper.add(itemHelper);
				}
			}

			// Verifica Contas Em Cobrança Judicial caso indicadorContaParcelada
			// seja
			// igual a sim para verificar Contas Em Cobrança Judicia.
			if (indicadorCobrancaJudical.compareTo(ConstantesSistema.SIM) == 0) {
				colecaoDadosContasEmCobrancaJudicial = this.repositorioFaturamento
						.pesquisarContasEmCobrancaJudicialGeracaoDeclaracaoQuitacao(
								idImovel, ano + "%", dataVerificacaoPagamentos);

				labelJudicial: for (Iterator iterator = colecaoDadosContasEmCobrancaJudicial
						.iterator(); iterator.hasNext();) {
					Object[] dadosContasEmCobrancaJudicial = (Object[]) iterator
							.next();

					itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
							(Integer) dadosContasEmCobrancaJudicial[1],// Id da
																		// Conta
							(Integer) dadosContasEmCobrancaJudicial[7],// AnoMês
																		// Referencia
							(BigDecimal) dadosContasEmCobrancaJudicial[2],// Valor
																			// Agua
							(BigDecimal) dadosContasEmCobrancaJudicial[3],// Valor
																			// Esgoto
							(BigDecimal) dadosContasEmCobrancaJudicial[4],// Valor
																			// Debitos
							(BigDecimal) dadosContasEmCobrancaJudicial[5],// Valor
																			// Creditos
							(BigDecimal) dadosContasEmCobrancaJudicial[6],// Valor
																			// Impostos
							(Date) dadosContasEmCobrancaJudicial[9],// Data
																	// Revisão
							(String) dadosContasEmCobrancaJudicial[8]);// Situacao
																		// Debito

					itemHelper.setValorTotalConta(itemHelper.getValorTotal());
					valorTotalDasContas = valorTotalDasContas.add(itemHelper
							.getValorTotal());

					for (DeclaracaoQuitacaoAnualDebitosItemHelper helper : colecaoItemHelper) {

						if (helper.equalsAnoMesConta(itemHelper)) {
							if (helper.getIdConta().compareTo(
									itemHelper.getIdConta()) < 0) {
								colecaoItemHelper.remove(helper);
								colecaoItemHelper.add(itemHelper);
								continue labelJudicial;
							} else {
								continue labelJudicial;
							}
						}
					}
					colecaoItemHelper.add(itemHelper);
				}
			}

			List colecaoItemHelperParaOrdenar = (List) colecaoItemHelper;

			// ORDENA COLEÇÃO POR ANO MES DE REFERENCIA DA CONTA
			Collections.sort(colecaoItemHelperParaOrdenar, new Comparator() {
				public int compare(Object left, Object right) {
					DeclaracaoQuitacaoAnualDebitosItemHelper leftKey = (DeclaracaoQuitacaoAnualDebitosItemHelper) left;
					DeclaracaoQuitacaoAnualDebitosItemHelper rightKey = (DeclaracaoQuitacaoAnualDebitosItemHelper) right;
					return leftKey.getAnoMesReferencia().compareTo(
							rightKey.getAnoMesReferencia());
				}
			});

			retorno.setValorTotalContas(valorTotalDasContas);
			retorno.setExtratoQuitacaoItens(colecaoItemHelperParaOrdenar);

		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Integer> pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos()
			throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos();

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public void gerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
			Integer idFuncionalidadeIniciada, Integer idGrupoFaturamento,
			Empresa empresa) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.EMPRESA, empresa.getId());

		try {

			Collection<Integer> anosParaGeracaoArquivoTexto = this
					.pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos();

			for (Integer ano : anosParaGeracaoArquivoTexto) {

				// Variáveis para controle das partes dos arquivos de 3000 em
				// 3000 registros
				// ========================================================================
				int parte = 1;
				boolean flagTerminouParte = false;
				int contadorDosTresMil = 0;
				// ========================================================================

				// Variáveis para a paginação da pesquisa
				// ========================================================================
				boolean flagTerminou = false;
				final int quantidadeMaxima = 500;
				// ========================================================================

				Integer sequencial = 0;

				StringBuilder linha = null;

				while (!flagTerminou) {

					// Criação do Arquivo
					// ========================================================================
					Date dataAtual = new Date();
					String nomeZip = null;
					nomeZip = "DECLARACAO_DE_QUITACAO_ANUAL_DEBITOS_G"
							+ idGrupoFaturamento + "_" + ano + "_Emp"
							+ empresa.getId() + "_PARTE_" + parte + "_"
							+ Util.formatarData(dataAtual) + "_"
							+ Util.formatarHoraSemDataSemDoisPontos(dataAtual);
					nomeZip = nomeZip.replace("/", "_");
					File compactado = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeZip + ".zip");
					File leitura = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeZip + ".txt");
					
					ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compactado));
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
					// ========================================================================

					flagTerminouParte = false;
					parte++;

					while (!flagTerminouParte) {

						// [SB0001 Verificação para geração do TXT];
						Collection colecaoExtratos = this.repositorioFaturamento
								.pesquisarExtratoQuitacaoParaGeracaoArquivoTexto(
										ano, empresa.getId(), quantidadeMaxima,
										idGrupoFaturamento);

						if (colecaoExtratos != null
								&& !colecaoExtratos.isEmpty()) {

							Iterator<Object[]> itera = colecaoExtratos
									.iterator();

							while (itera.hasNext()) {

								Object[] dados = itera.next();

								DeclaracaoQuitacaoAnualDebitosHelper helper = new DeclaracaoQuitacaoAnualDebitosHelper();

								ExtratoQuitacao extratoQuitacao = new ExtratoQuitacao();
								extratoQuitacao.setId(new Integer(dados[0].toString()));
								extratoQuitacao.setImovel(new Imovel((Integer) dados[1]));
								extratoQuitacao.setAnoReferencia(new Integer(dados[2].toString()));
								extratoQuitacao.setIndicadorImpressao(new Integer(dados[5].toString()));
								extratoQuitacao.setUltimaAlteracao((Date) dados[6]);
								extratoQuitacao.setValorTotalDasContas(new BigDecimal(dados[3].toString()));
								extratoQuitacao.setAnoMesMensagemConta(new Integer(
												dados[7].toString()));
								extratoQuitacao.setIndicadorImpressaoNaConta(new Integer(dados[8].toString()));
								
								Integer idImovel = new Integer(dados[1].toString());
								Integer anoReferencia = new Integer(dados[2].toString());
								String matriculaFormatada = Util.retornaMatriculaImovelFormatada(idImovel);
								String inscricaoImovel = this.getControladorImovel().pesquisarInscricaoImovel(idImovel);
								String nomeCliente = this.getControladorImovel().consultarNomeClienteUsuarioImovel(idImovel);
								String endereco = this.getControladorEndereco().pesquisarEndereco(idImovel);
								FaturamentoGrupo faturamentoGrupo = this.getControladorImovel().pesquisarGrupoImovel(idImovel);
								String[] enderecoDividido = this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(idImovel);
								
								boolean enderecoAlternativo = false;
								short endAlternativo = 0;
								
								/*Incluída verificação de endereço de entrega alternativa
								 * e em caso afirmativo preenchimento do campo endereço de entrega pelo
								 * endereço do cliente responsável*/
								try {
									Integer idQuadraImovel = repositorioImovel.pesquisaIdQuadraImovel(idImovel);
									Integer idRotaQuadra = repositorioCadastro.pesquisarIdRotaQuadra(idQuadraImovel);
									Rota rota = repositorioMicromedicao.pesquisarRota(idRotaQuadra);
									enderecoAlternativo = repositorioFaturamento.verificaImovelPorRotasComContaEntregaEmOutroEndereco(rota, idImovel);
									
									if(enderecoAlternativo == true){
										Collection colecaoClienteImovel = repositorioClienteImovel.
										pesquisarClienteImovelResponsavelConta(idImovel);
										endAlternativo = 1;
								
																			
									if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
										ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel.iterator().next();
										
										if (clienteImovelRespConta != null){
											Cliente cliente = clienteImovelRespConta.getCliente();
										
											if (cliente != null){
												enderecoDividido = getControladorEndereco().pesquisarEnderecoClienteAbreviadoDivididoCosanpa(cliente.getId());
												if (enderecoDividido[0].equals(null) ||
														enderecoDividido[0].trim().equalsIgnoreCase("")){
													enderecoDividido = this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(idImovel);
												}
											}
										}
									}
										
									}else{
										enderecoDividido = this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(idImovel);
									}
								} catch (ErroRepositorioException e) {
									e.printStackTrace();
								}
								
								Object[] rotaESequencialRotaDoImovel = this
										.getControladorMicromedicao()
										.obterRotaESequencialRotaDoImovelSeparados(
												idImovel);

								Short codigoRota = (Short) rotaESequencialRotaDoImovel[0];
								Integer sequencialRota = (Integer) rotaESequencialRotaDoImovel[1];

								helper.setAnoMesArrecadacao(anoReferencia);
								helper.setMatriculaFormatada(matriculaFormatada);
								helper.setInscricaoImovel(inscricaoImovel);
								helper.setNomeClienteUsuario(nomeCliente);
								helper.setEndereco(endereco);
								helper.setFirma(dados[4].toString());
								helper.setIdGrupo(faturamentoGrupo.getId().toString());
								helper.setEnderecoDestinatario(enderecoDividido[0]);
								helper.setBairro(enderecoDividido[3]);
								helper.setMunicipio(enderecoDividido[1]);
								helper.setUf(enderecoDividido[2]);
								helper.setCep(enderecoDividido[4]);
								helper.setExtratoQuitacaoParaAtualizacao(extratoQuitacao);
								helper.setCodigoRota(codigoRota.toString());
								helper.setSeguencialRota(sequencialRota
										.toString());
								/* Setando valor ao atributo 
								 * que informa se o imóvel tem ou não
								 * endereço de entrega alternativo*/
								helper.setEnderecoAlternativo(endAlternativo);
								
								Collection<ExtratoQuitacaoItem> colecaoExtratosItens = this.repositorioFaturamento
										.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(extratoQuitacao.getId());

								helper.setFaturas(colecaoExtratosItens);

								sequencial++;

								linha = this.gerarlinhaArquivoExtratoQuitacao(helper, sequencial);

								out.write(linha.toString());
								out.flush();

								// O sistema atualiza o campo
								// EXTRATO_QUITACAO.EXQT_ICIMPRESSAO,
								// para os registros em que foram gerados o TXT,
								// para o valor 1;
								ExtratoQuitacao extratoQuitacaoParaAtualizacao = helper.getExtratoQuitacaoParaAtualizacao();

								extratoQuitacaoParaAtualizacao.setIndicadorImpressao(ConstantesSistema.SIM.intValue());

								getControladorBatch().atualizarObjetoParaBatch(extratoQuitacaoParaAtualizacao);

								linha = null;

							}

						}

						contadorDosTresMil++;

						/**
						 * Caso a coleção de dados retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoExtratos == null || colecaoExtratos.size() < quantidadeMaxima) {
							flagTerminou = true;
							flagTerminouParte = true;
						}

						if (colecaoExtratos != null) {
							colecaoExtratos.clear();
							colecaoExtratos = null;
						}

						if (contadorDosTresMil == 4 || flagTerminou) {

							try {
								out.close();
								ZipUtil.adicionarArquivo(zos, leitura);

								// close the stream
								zos.close();
								leitura.delete();
							} catch (IOException e) {
								getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
								throw new EJBException(e);
							}
							contadorDosTresMil = 0;
							flagTerminouParte = true;
						}

					}// Terminou Parte

				}// Terminou
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception e) {

			e.printStackTrace();

			// Este catch serve para interceptar
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
		}

	}

	private StringBuilder gerarlinhaArquivoExtratoQuitacao(
			DeclaracaoQuitacaoAnualDebitosHelper helper, Integer sequencial) {

		StringBuilder linha = new StringBuilder();

		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getAnoMesArrecadacao().toString(), 4));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getNomeClienteUsuario(), 50));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getEndereco(), 120));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getMatriculaFormatada(), 9));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(sequencial.toString(), 50));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getInscricaoImovel(), 20));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getFirma(), 10));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getIdGrupo().toString(), 3));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getNomeClienteUsuario(), 50));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getEnderecoDestinatario(), 70));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getBairro(), 30));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getMunicipio(), 30));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getUf(), 2));

		if (helper.getCep().length() == 8) {
			linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(Util.formatarCEP(helper.getCep()), 10));
		} else {
			linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getCep(), 10));
		}

		Collection<Integer> anomesreferencia = new ArrayList<Integer>();
		BigDecimal valorDuplicado = new BigDecimal(0);

		for (ExtratoQuitacaoItem item : helper.getFaturas()) {

			if(!anomesreferencia.contains(item.getAnoMesReferenciaConta())) {
				anomesreferencia.add(item.getAnoMesReferenciaConta());
				linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(Util.formatarAnoMesParaMesAno(item.getAnoMesReferenciaConta()), 7));
				linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(item.getDescricaoSituacao(), 30));
				linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(Util.formatarData(item.getDataSituacao()), 10));
				linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(Util.formatarMoedaReal(item.getValorConta()), 14));
			} else {
				valorDuplicado = valorDuplicado.add(item.getValorConta());
			}

		}

		/**
		 * autor: Adriana Muniz
		 * Data: 30/03/2011
		 * Se houve conta com pagamentos duplicados, o valor duplicado será subtraído,
		 * contas duplicadas não serão impressas, então, para que o valor total não seja indiferente 
		 * ao valor da soma das contas, a variavel valor duplicado guarda o valor da referencia que não entrará
		 * na declaração, para subtrair do valor total
		   */
		BigDecimal valorTotal = helper.getExtratoQuitacaoParaAtualizacao().getValorTotalDasContas().subtract(valorDuplicado);
		
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(Util.formatarMoedaReal(valorTotal),14));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getAnoMesArrecadacao().toString(), 4));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getCodigoRota().toString(), 5));
		linha.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(helper.getSeguencialRota().toString(), 5));

		/**
		 * autor: Adriana Muniz
		 * Data: 12/05/2011
		 * Verifica se o imovel possui outro endereço de entrega(endereço alternativo)
		 * Se sim, adiciona-se 1, senão adiciona-se 0 
		 */
		linha.append(helper.getEnderecoAlternativo()+"");
		linha.append(System.getProperty("line.separator"));

		return linha;

	}

	/**
	 * [UC1010] Emitir 2ª via de declaração anual de quitação de débitos
	 * 
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @date 23/03/2010
	 */
	public Collection<ExtratoQuitacaoItem> pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(
			Integer idExtratoQuitacao) throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(idExtratoQuitacao);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * @author Raphael Rossiter
	 * @date 27/04/2010
	 * 
	 * @param idDebitoACobrar
	 * @throws ControladorException
	 */
	public void atualizarSituacaoAtualDebitoACobrar(Integer idDebitoACobrar)
			throws ControladorException {

		try {

			repositorioFaturamento
					.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [SB0002] - Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada débito tipo, e retorna uma coleção com
	 * limite de 10 registros.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 14/04/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigencia(
			Integer numeroPagina) throws ControladorException {

		Collection<DebitoTipoVigencia> retorno = new ArrayList<DebitoTipoVigencia>();

		try {
			Collection<DebitoTipoVigencia> colecao = this.repositorioFaturamento
					.pesquisarDebitoTipoVigenciaUltimaVigencia(numeroPagina);

			if (colecao != null && !colecao.isEmpty()) {

				Iterator iterator = colecao.iterator();
				while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();

					DebitoTipoVigencia debitoTipoVigencia = new DebitoTipoVigencia();
					debitoTipoVigencia.setId((Integer) object[0]);

					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, object[1].toString()));
					Collection<DebitoTipo> collDebitoTipo = this
							.getControladorUtil().pesquisar(filtroDebitoTipo,
									DebitoTipo.class.getName());

					DebitoTipo debitoTipo = collDebitoTipo.iterator().next();

					debitoTipoVigencia.setDebitoTipo(debitoTipo);
					debitoTipoVigencia.setValorDebito((BigDecimal) object[2]);
					debitoTipoVigencia.setDataVigenciaInicial((Date) object[4]);
					debitoTipoVigencia.setDataVigenciaFinal((Date) object[5]);

					retorno.add(debitoTipoVigencia);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

		return retorno;

	}

	/**
	 * [SB0002] - Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo débito, e retorna o total.
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Integer pesquisarDebitoTipoVigenciaUltimaVigenciaTotal()
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarDebitoTipoVigenciaUltimaVigenciaTotal();
		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

	}

	/**
	 * [SB0002] - Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo débito, e retorna uma coleção.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 14/04/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(
			String[] selecionados) throws ControladorException {

		Collection<DebitoTipoVigencia> retorno = new ArrayList<DebitoTipoVigencia>();

		try {
			Collection<DebitoTipoVigencia> colecao = this.repositorioFaturamento
					.pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(selecionados);

			if (colecao != null && !colecao.isEmpty()) {

				Iterator iterator = colecao.iterator();
				while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();

					DebitoTipoVigencia debitoTipoVigencia = new DebitoTipoVigencia();
					debitoTipoVigencia.setId((Integer) object[0]);

					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, object[1].toString()));
					Collection<DebitoTipo> collDebitoTipo = this
							.getControladorUtil().pesquisar(filtroDebitoTipo,
									DebitoTipo.class.getName());

					DebitoTipo debitoTipo = collDebitoTipo.iterator().next();

					debitoTipoVigencia.setDebitoTipo(debitoTipo);
					debitoTipoVigencia.setValorDebito((BigDecimal) object[2]);
					debitoTipoVigencia.setDataVigenciaInicial((Date) object[4]);
					debitoTipoVigencia.setDataVigenciaFinal((Date) object[5]);

					retorno.add(debitoTipoVigencia);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

		return retorno;
	}

	/**
	 * [UC0982] Inserir tipo de Débito com Vigência.
	 * 
	 * Verificar se existe vigência já cadastrada para o tipo de débito.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idDebitoTipo
	 * @param opcao
	 * @throws ControladorException
	 * @data 30/04/2010
	 * 
	 * @see Caso a opcao = 1 - verifica as situações de inserir e atualizar
	 *      Débito Tipo Vigência
	 * @see Caso a opcao = 2 - verifica a situação de retificar Débito Tipo
	 *      Vigncia
	 */
	public void verificarExistenciaVigenciaDebito(String dataVigenciaInicial,
			String dataVigenciaFinal, Integer idDebitoTipo, Integer opcao)
			throws ControladorException {

		String retorno = "";

		try {
			retorno = repositorioFaturamento.verificarExistenciaVigenciaDebito(
					dataVigenciaInicial, dataVigenciaFinal, idDebitoTipo);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		// Caso a opcao seja iqual a 1 - verifica as situações de inserir e
		// atualizar Debito Tipo Vigencia
		//
		// Caso a opcao seja iqual a 2 - verifica a situação de retificar Debito
		// Tipo Vigencia
		//
		if (opcao == 1) {
			if (retorno != null && !retorno.equals("")) {
				throw new ControladorException(
						"atencao.data_vigencia_final_ja_cadastrada", null,
						retorno);
			}
		} else if (opcao == 2) {
			if (retorno != null && !retorno.equals("")) {
				throw new ControladorException(
						"atencao.data_vigencia_final_ja_cadastrada_retificar",
						null, retorno);
			}
		}
	}

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Empresa> pesquisarEmpresasParaGeraracaoExtrato(
			Integer idGrupoFaturamento) throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisarEmpresasParaGeraracaoExtrato(idGrupoFaturamento);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0391] Inserir valor de cobrança de serviço.
	 * 
	 * Verificar se existe valor de cobrança de serviço já cadastrada.
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 07/06/2010
	 * 
	 */
	public Boolean validarVigenciaValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.validarVigenciaValorCobrancaServico(servicoCobrancaValor);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0840] Atualizar Conta Pré-faturada
	 * 
	 * [SB0003] Atualizar Crédito realizado e crédito a realizar
	 * 
	 * @author Sávio Luiz
	 * @date 27/10/2009
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param helperValoresAguaEsgoto
	 * @param valorTotalDebitos
	 * @param gerarAtividadeGrupoFaturamento
	 * @return GerarCreditoRealizadoHelper
	 * @throws ControladorException
	 */
	private BigDecimal atualizarCreditoARealizarNitrato(Imovel imovel,
			Integer anoMesFaturamento, BigDecimal valorAgua, Conta conta)
			throws ControladorException {

		BigDecimal valorCredito = null;
		// Caso a situação de faturamento seja igual a Nitrato,
		// e o valor de água é maior que zero
		// Atualiza os créditos a realizar e realizados do nitrato
		if (imovel.getFaturamentoSituacaoTipo() != null
				&& !imovel.getFaturamentoSituacaoTipo().equals("")) {
			if (imovel.getFaturamentoSituacaoTipo().getId()
					.equals(FaturamentoSituacaoTipo.NITRATO)
					&& valorAgua.compareTo(BigDecimal.ZERO) == 1) {

				FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
				filtroFaturamentoSituacaoHistorico
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
								imovel.getId()));
				filtroFaturamentoSituacaoHistorico
						.adicionarParametro(new ParametroNulo(
								FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
				Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this
						.getControladorUtil().pesquisar(
								filtroFaturamentoSituacaoHistorico,
								FaturamentoSituacaoHistorico.class.getName());

				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util
						.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

				if ((faturamentoSituacaoHistorico != null
						&& anoMesFaturamento >= faturamentoSituacaoHistorico
								.getAnoMesFaturamentoSituacaoInicio() && anoMesFaturamento <= faturamentoSituacaoHistorico
						.getAnoMesFaturamentoSituacaoFim())) {

					// calcula o valor do crédito que será 50% do valor total da
					// água
					valorCredito = valorAgua.divide(new BigDecimal(2), 2,
							BigDecimal.ROUND_DOWN);

					try {
						// Pesamos o tipo de credito realizado
						// com credito tipo = Credito de Nitrato
						CreditoRealizado creditoRealizado = null;
						Collection<CreditoRealizado> colCreditos = Fachada
								.getInstancia().obterCreditosRealizadosConta(
										conta);

						for (CreditoRealizado objeto : (Collection<CreditoRealizado>) colCreditos) {
							if (objeto.getCreditoTipo().getId()
									.equals(CreditoTipo.CREDITO_NITRATO)) {
								creditoRealizado = objeto;
							}
						}

						Object[] dadosCreditoARealizar = null;

						if (creditoRealizado != null) {
							dadosCreditoARealizar = repositorioFaturamento
									.pesquisarCreditoARealizar(
											creditoRealizado
													.getCreditoARealizarGeral()
													.getId(), anoMesFaturamento);
						}

						// pesquisa o crédito a realizar
						/*
						 * Object[] dadosCreditoARealizar =
						 * repositorioFaturamento
						 * .pesquisarCreditoARealizar(imovel.getId(),
						 * CreditoTipo.CREDITO_NITRATO,
						 * DebitoCreditoSituacao.PRE_FATURADA,
						 * anoMesFaturamento);
						 */
						// Caso exista o crédito a realizar com a situação
						// pre-fdaturada,
						// então atualiza o valor do crédito e a situação do
						// crédito a realizar,
						// do crédito a realizar categoria, do credito realizado
						// e do credito realizado categoria
						if (dadosCreditoARealizar != null
								&& !dadosCreditoARealizar.equals("")) {

							Integer idCreditoARealizar = (Integer) dadosCreditoARealizar[0];

							// atualiza o crédito a realizar com o valor do
							// crédito calculado
							repositorioFaturamento
									.atualizarValorCreditoARealizar(
											idCreditoARealizar, valorCredito,
											DebitoCreditoSituacao.NORMAL);

							// Pesquisa os créditos a realizar categoria
							Collection colecaoCreditoARealizarCategoria = this
									.obterCreditoRealizarCategoria(idCreditoARealizar);

							Iterator colecaoCreditoARealizarCategoriaIterator = colecaoCreditoARealizarCategoria
									.iterator();

							// Crédito a realizar categoria
							CreditoARealizarCategoria creditoARealizarCategoria = null;

							Collection colecaoCategoriasObterValor = new ArrayList();

							// Laço para recuperar as categorias do crédito a
							// realizar
							while (colecaoCreditoARealizarCategoriaIterator
									.hasNext()) {
								creditoARealizarCategoria = (CreditoARealizarCategoria) colecaoCreditoARealizarCategoriaIterator
										.next();
								Categoria categoria = new Categoria();
								categoria.setId(creditoARealizarCategoria
										.getCategoria().getId());
								categoria
										.setQuantidadeEconomiasCategoria(creditoARealizarCategoria
												.getQuantidadeEconomia());
								colecaoCategoriasObterValor.add(categoria);
							}

							// Obter os valores das categorias por categoria do
							// credito a realizar categoria
							Collection colecaoCategoriasCalculadasValor = getControladorImovel()
									.obterValorPorCategoria(
											colecaoCategoriasObterValor,
											valorCredito);

							// atualiza o crédito a realizar por categoria com o
							// valor do crédito calculado por categoria
							repositorioFaturamento
									.atualizarValorCreditoARealizarCategoria(
											idCreditoARealizar,
											colecaoCategoriasObterValor,
											colecaoCategoriasCalculadasValor);

							// pesquisa o crédito realizado
							Integer idCreditoRealizadoNitrato = repositorioFaturamento
									.pesquisarIdCreditoRealizadoNitrato(conta);

							// caso exista crédito realizado,
							// então atualiza os dados de credito realizado e
							// credito realizado categoria
							if (idCreditoRealizadoNitrato != null
									&& !idCreditoRealizadoNitrato.equals("")) {
								repositorioFaturamento
										.atualizarValorCreditoRealizado(
												idCreditoRealizadoNitrato,
												valorCredito);
								repositorioFaturamento
										.atualizarValorCreditoRealizadoCategoria(
												idCreditoRealizadoNitrato,
												colecaoCategoriasObterValor,
												colecaoCategoriasCalculadasValor);
							}

						}

					} catch (ErroRepositorioException ex) {
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}
		}

		return valorCredito;

	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 09/07/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarTaxaPercentualTarifaMinimaCortado(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// PROCESSO BATCH
		// ------------------------------------------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronogramaRota))
								.getRota().getId());
		// ---------------------------------------------------------------------------------------------------

		try {

			// PARÂMETROS DO SISTEMA
			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			// CARREGANDO O TIPO DO DÉBITO PARA TARIFA DE CORTADO
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo
					.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
			filtroDebitoTipo
					.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, DebitoTipo.TARIFA_CORTADO));

			Collection colecaoDebitoTipo = this.getControladorUtil().pesquisar(
					filtroDebitoTipo, DebitoTipo.class.getName());

			DebitoTipo debitoTipo = (DebitoTipo) Util
					.retonarObjetoDeColecao(colecaoDebitoTipo);

			if (colecaoFaturamentoAtividadeCronogramaRota != null
					&& !colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {

				Iterator iteratorColecaoFaturamentoAtividadeCronogramaRota = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				// LAÇO PARA GERAR DÉBITO PARA TODAS AS ROTAS
				while (iteratorColecaoFaturamentoAtividadeCronogramaRota
						.hasNext()) {

					FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) iteratorColecaoFaturamentoAtividadeCronogramaRota
							.next();

					// =================================================================================================
					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection colecaoImovel = this
								.pesquisarImovelCortadoSemTarifaSocial(
										faturamentoAtivCronRota.getRota(),
										numeroIndice, quantidadeRegistros);

						/*
						 * Caso exista ids de imóveis para a rota atual
						 * determina a geração do crédito para cada imóvel
						 * retornado.
						 */
						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							Iterator iteratorColecaoImoveis = colecaoImovel
									.iterator();

							// LAÇO PARA GERAR O DÉBITO DE TODOS OS IMOVEIS DA
							// ROTA ATUAL
							Imovel imovel = null;
							while (iteratorColecaoImoveis.hasNext()) {

								imovel = (Imovel) iteratorColecaoImoveis.next();

								// GERAÇÃO DÉBITO
								// --------------------------------------------------------------------------------
								this.gerarTaxaPercentualTarifaMinimaCortadoPorImovel(
										imovel, faturamentoGrupo, debitoTipo,
										sistemaParametro);
								// --------------------------------------------------------------------------------

							}// FIM DO LOOP DE IMOVEIS

						}// FIM DO LOOP DE IMOVEIS

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null
								|| colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}

					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA

				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 09/07/2010
	 * 
	 * @param rota
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @return Collection
	 * @throws ControladorException
	 */
	protected Collection pesquisarImovelCortadoSemTarifaSocial(Rota rota,
			int numeroIndice, int quantidadeRegistros)
			throws ControladorException {

		Collection colecaoImoveis = null;
		Collection imoveis;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelCortadoSemTarifaSocialPorRota(
								rota.getId(), numeroIndice, quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelCortadoSemTarifaSocialPorRotaAlternativa(
								rota.getId(), numeroIndice, quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		// Carregando os dados dos imóveis selecionados
		if (imoveis != null && !imoveis.isEmpty()) {

			Iterator iteratorImoveis = imoveis.iterator();

			colecaoImoveis = new ArrayList();

			Imovel imovel = null;

			while (iteratorImoveis.hasNext()) {

				Object[] arrayImovel = (Object[]) iteratorImoveis.next();

				imovel = new Imovel();

				// ligacaoAguaSituacao.id
				if (arrayImovel[0] != null) {
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId((Integer) arrayImovel[0]);
					ligacaoAguaSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImovel[11]);
					ligacaoAguaSituacao
							.setConsumoMinimoFaturamento((Integer) arrayImovel[13]);
					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}

				// ligacaoEsgotoSituacao.id
				if (arrayImovel[1] != null) {
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId((Integer) arrayImovel[1]);
					ligacaoEsgotoSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImovel[12]);
					ligacaoEsgotoSituacao
							.setVolumeMinimoFaturamento((Integer) arrayImovel[14]);
					imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				}

				// consumoTarifa.id
				if (arrayImovel[2] != null) {
					ConsumoTarifa consumoTarifa = new ConsumoTarifa();
					consumoTarifa.setId((Integer) arrayImovel[2]);
					imovel.setConsumoTarifa(consumoTarifa);
				}

				// localidade.id
				Localidade localidade = null;
				if (arrayImovel[3] != null) {
					localidade = new Localidade();
					localidade.setId((Integer) arrayImovel[3]);
					imovel.setLocalidade(localidade);

				}

				Quadra quadra = null;

				// quadra.id
				if (arrayImovel[4] != null) {
					quadra = new Quadra();
					quadra.setId((Integer) arrayImovel[4]);
					imovel.setQuadra(quadra);
				}

				// quadra.numeroQuadra
				if (arrayImovel[5] != null) {
					quadra.setNumeroQuadra(((Integer) arrayImovel[5])
							.intValue());
					imovel.setQuadra(quadra);
				}

				// imovel.lote
				if (arrayImovel[6] != null) {
					imovel.setLote(((Short) arrayImovel[6]).shortValue());
				}

				// setorComercial.codigo
				SetorComercial setorComercial = null;
				if (arrayImovel[7] != null) {
					setorComercial = new SetorComercial();
					setorComercial.setCodigo(((Integer) arrayImovel[7])
							.intValue());
					imovel.setSetorComercial(setorComercial);

				}

				// imovel.subLote
				if (arrayImovel[8] != null) {
					imovel.setSubLote(((Short) arrayImovel[8]).shortValue());
				}

				// imovel.setorComercial
				if (arrayImovel[9] != null) {
					if (setorComercial == null) {
						setorComercial = new SetorComercial();
					}
					setorComercial.setId((Integer) arrayImovel[9]);
					imovel.setSetorComercial(setorComercial);
				}

				// id do imovel
				if (arrayImovel[10] != null) {
					imovel.setId((Integer) arrayImovel[10]);
				}

				// adiciona o imovel
				colecaoImoveis.add(imovel);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 12/07/2010
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param debitoTipo
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	public void gerarTaxaPercentualTarifaMinimaCortadoPorImovel(Imovel imovel,
			FaturamentoGrupo faturamentoGrupo, DebitoTipo debitoTipo,
			SistemaParametro sistemaParametro) throws ControladorException {

		/*
		 * 2. Para cada imóvel selecionado, o sistema verifica se o imóvel está
		 * cortado há mais de trinta dias:
		 * 
		 * 2.1. Caso a diferença entre a data prevista de leitura no cronograma
		 * (FTAC_DTPREVISTA da tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA com
		 * FTAT_ID com o valor correspondente a efetuar leitura, FTCM_ID=FTCM_ID
		 * da tabela FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com
		 * FTCM_AMREFERENCIA=(Ano e mês de referência) e FTGR_ID=FTGR_ID da
		 * tabela FATURAMENTO_GRUPO com FTGR_ID=FTGR_ID da tabela ROTA) e a data
		 * do corte (LAGU_DTCORTE da tabela LIGACAO_AGUA com LAGU_ID=IMOV_ID da
		 * tabela IMOVEL) seja menor ou igual a 30 dias o sistema deve passar
		 * para o próximo imóvel selecionado, caso contrário a será gerado o
		 * débito a cobrar, passar para o próximo passo;
		 */
		Date dataPrevistaLeituraCronograma = null;

		try {

			dataPrevistaLeituraCronograma = repositorioFaturamento
					.pesquisarDataPrevistaFaturamentoAtividadeCronograma(
							faturamentoGrupo.getId(),
							FaturamentoAtividade.EFETUAR_LEITURA,
							faturamentoGrupo.getAnoMesReferencia());

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		/*
		 * 0 - Tipo do corte 1 - Data do corte administrativo 2 - Data da
		 * religação 3 - Data do corte 4 - Data da supressão
		 */
		Object[] dadosLigacaoAgua = this.getControladorAtendimentoPublico()
				.pesquisarDadosLigacaoAgua(imovel.getId());
		Date dataCorte = (Date) dadosLigacaoAgua[3];

		if (Util.obterQuantidadeDiasEntreDuasDatas(dataCorte,
				dataPrevistaLeituraCronograma) > 30) {

			/*
			 * 3. O sistema calcula os valores de água e/ou esgoto <<Inclui>>
			 * [UC0120 – Calcular Valores de Água e/ou Esgoto], passando os
			 * seguintes parâmetros:
			 */

			// Inicializando o objeto que armazenará as informações que serão
			// utilizadas no cálculo da conta
			DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = new DeterminarValoresFaturamentoAguaEsgotoHelper();

			/*
			 * 3.1. Ano e mês de referência.
			 * 
			 * 3.2. Situação da ligação de água (LAST_ID da tabela imóvel).
			 * 
			 * 3.3. Situação da ligação de esgoto (LEST_ID da tabela imóvel).
			 * 
			 * 3.4. Indicador de faturamento de água (LAST_ICFATURAMENTO da
			 * tabela LIGACAO_AGUA_SITUACAO com LAST_ID=LAST_ID da tabela
			 * IMOVEL).
			 * 
			 * 3.5. Indicador de faturamento de esgoto (LEST_ICFATURAMENTO da
			 * tabela LIGACAO_ESGOTO_SITUACAO com LEST_ID=LEST_ID da tabela
			 * IMOVEL).
			 * 
			 * 3.6. Caso o indicador de tarifa categoria seja igual a 2
			 * (PARM_ICTARIFACATEGORIA = 2 da tabela de SISTEMA_PARAMETROS) o
			 * sistema passa as subcategorias e as respectivas quantidades de
			 * economias do imóvel (SCAT_ID e IMSB_QTECONOMIA da tabela
			 * IMOVEL_SUBCATEGORIA com IMOV_ID da tabela IMOVEL);
			 * 
			 * 3.7. Caso contrário, categoria(s) do imóvel e sua(s)
			 * respectiva(s) quantidade(s) de economia retornada pelo [UC0108 -
			 * Obter Quantidade de Economias por Categoria];
			 * 
			 * 3.8. Consumo faturado de água do mês (passar o valor zero).
			 * 
			 * 3.9. Consumo faturado de esgoto do mês (passar o valor zero).
			 * 
			 * 3.10. Consumo mínimo da ligação <<Inclui>> [UC0105 – Obter
			 * Consumo Mínimo da Ligação];
			 * 
			 * 3.11. Data de Leitura Anterior (caso DATE (FTAC_TMREALIZACAO) da
			 * tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA com FTAT_ID com o valor
			 * correspondente a efetuar leitura, FTCM_ID=FTCM_ID da tabela
			 * FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTCM_AMREFERENCIA=(Ano e
			 * mês de referência – 1 MÊS) e FTGR_ID=FTGR_ID da tabela
			 * FATURAMENTO_GRUPO com FTGR_ID=FTGR_ID da tabela ROTA seja
			 * diferente de nulo, caso contrário (FTAC_DTPREVISTA) da tabela
			 * FATURAMENTO_ATIVIDADE_CRONOGRAMA com FTAT_ID com o valor
			 * correspondente a efetuar leitura, FTCM_ID=FTCM_ID da tabela
			 * FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTCM_AMREFERENCIA=(Ano e
			 * mês de referência – 1 MÊS) e FTGR_ID=FTGR_ID da tabela
			 * FATURAMENTO_GRUPO com FTGR_ID=FTGR_ID da tabela ROTA seja
			 * diferente de nulo, caso contrário caso contrário data leitura
			 * atual menos 30 dias.
			 * 
			 * 3.12. Data de Leitura Atual (data prevista de leitura no
			 * cronograma (FTAC_DTPREVISTA da tabela
			 * FATURAMENTO_ATIVIDADE_CRONOGRAMA com FTAT_ID com o valor
			 * correspondente a efetuar leitura, FTCM_ID=FTCM_ID da tabela
			 * FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTCM_AMREFERENCIA=(Ano e
			 * mês de referência) e FTGR_ID=FTGR_ID da tabela FATURAMENTO_GRUPO
			 * com FTGR_ID=FTGR_ID da tabela ROTA)).
			 * 
			 * 3.13. Percentual de esgoto (LESG_PCESGOTO da tabela
			 * LIGACAO_ESGOTO com LESG_ID=IMOV_ID da tabela IMOVEL, caso o
			 * imóvel seja ligado de esgoto, caso o imóvel seja ligado ou
			 * cortado de água (LAST_ID = 3 OR 5) caso o percentual de esgoto
			 * alternativo seja diferente de nulo (LESG_PCALTERNATIVO),
			 * verificar se o consumo por economia (Consumo faturado de esgoto
			 * do mês/quantidade de economia) é menor ou igual ao consumo do
			 * percentual alternativo (LESG_NNCONSUMOPCALTERNATIVO), caso seja
			 * verdade, enviar como Percentual de esgoto o menor valor entre o
			 * LESG_PCESGOTO e o LESG_PCALTERNATIVO, caso contrário enviar o
			 * LESG_PCESGOTO; ou zero caso contrário).
			 * 
			 * 3.14. Tarifa para o imóvel (CSTF_ID da tabela IMOVEL).
			 */

			// 3.4 - INDICADOR FATURAMENTO DE ÁGUA
			helperValoresAguaEsgoto
					.setIndicadorFaturamentoAgua(imovel
							.getLigacaoAguaSituacao()
							.getIndicadorFaturamentoSituacao());

			// 3.5 - INDICADOR FATURAMENTO ESGOTO
			helperValoresAguaEsgoto.setIndicadorFaturamentoEsgoto(imovel
					.getLigacaoEsgotoSituacao()
					.getIndicadorFaturamentoSituacao());

			// 3.6 e 3.7 - CATEGORIAS E SUBCATEGORIAS
			Collection colecaoCategoriaOUSubcategoria = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imovel);
			
			// OBS: O restante dos parâmetros serão preparados através do
			// [SB0002] do caso de uso [UC0113] - Faturar Grupo de Faturamento

			/*
			 * [UC0113] - Faturar Grupo de Faturamento [SB0002] - Determinar
			 * Valores para Faturamento de Água e/ou Esgoto
			 */
			helperValoresAguaEsgoto = this
					.determinarValoresFaturamentoAguaEsgoto(imovel,
							faturamentoGrupo.getAnoMesReferencia(),
							colecaoCategoriaOUSubcategoria, faturamentoGrupo,
							null, null);

			// GERANDO O DÉBITO
			this.gerarDebitoACobrarDeTaxaPercentualTarifaMinimaCortado(imovel,
					debitoTipo, faturamentoGrupo.getAnoMesReferencia(),
					helperValoresAguaEsgoto.getValorTotalAgua(),
					sistemaParametro);
		}
	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 12/07/2010
	 * 
	 * @param imovel
	 * @param debitoTipo
	 * @param anoMesFaturamento
	 * @param valorTotalAgua
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarDeTaxaPercentualTarifaMinimaCortado(
			Imovel imovel, DebitoTipo debitoTipo, Integer anoMesFaturamento,
			BigDecimal valorTotalAgua, SistemaParametro sistemaParametro)
			throws ControladorException {

		/*
		 * 5. Para cada imóvel selecionado o sistema inclui: [FS0002 – Verifica
		 * a existência de débito a cobrar de Tarifa de Cortado ativo para o
		 * imóvel]
		 */
		Collection colecaoDebitoACobrarTarifaCortado = null;

		try {

			colecaoDebitoACobrarTarifaCortado = repositorioFaturamento
					.pesquisarDebitoACobrarTarifaCortado(imovel.getId(),
							anoMesFaturamento);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDebitoACobrarTarifaCortado == null
				|| colecaoDebitoACobrarTarifaCortado.isEmpty()) {

			// 5.1. O débito a cobrar na tabela DEBITO_A_COBRAR_GERAL
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();

			debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
			debitoACobrarGeral.setUltimaAlteracao(new Date());

			// INSERINDO NO BANCO
			Integer idDebitoGerado = (Integer) getControladorUtil().inserir(
					debitoACobrarGeral);
			debitoACobrarGeral.setId(idDebitoGerado);

			// 5.2. Inclui o débito a cobrar na tabela DEBITO_A_COBRAR
			DebitoACobrar debitoACobrar = new DebitoACobrar();

			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);
			debitoACobrar.setId(idDebitoGerado);

			debitoACobrar.setImovel(imovel);
			debitoACobrar.setDebitoTipo(debitoTipo);
			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(anoMesFaturamento);

			/*
			 * Maior valor entre o ano/mes da data corrente e o ano/mes de
			 * referencia do faturamento (PARM_AMREFERENCIAFATURAMENTO da tabela
			 * SISTEMA_PARAMETROS)
			 */
			int anoMesReferenciaContabil = sistemaParametro
					.getAnoMesFaturamento();
			int anoMesCorrente = Util.getAnoMesComoInt(new Date());

			if (sistemaParametro.getAnoMesFaturamento() < anoMesCorrente) {
				anoMesReferenciaContabil = anoMesCorrente;
			}

			debitoACobrar.setAnoMesReferenciaContabil(anoMesReferenciaContabil);

			// Calcula 30% do valor total de água determinado no passo 4
			BigDecimal valorDebito = (valorTotalAgua.multiply(new BigDecimal(
					"0.30"))).setScale(2, BigDecimal.ROUND_HALF_UP);
			debitoACobrar.setValorDebito(valorDebito);

			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));

			debitoACobrar.setLocalidade(imovel.getLocalidade());
			debitoACobrar.setQuadra(imovel.getQuadra());
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
					.getCodigo());
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(imovel.getLote());
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());

			debitoACobrar.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);

			debitoACobrar.setFinanciamentoTipo(debitoTipo
					.getFinanciamentoTipo());
			debitoACobrar.setLancamentoItemContabil(debitoTipo
					.getLancamentoItemContabil());

			DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);

			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
			debitoACobrar.setCobrancaForma(cobrancaForma);

			debitoACobrar.setUltimaAlteracao(new Date());
			debitoACobrar.setUsuario(Usuario.USUARIO_BATCH);

			// INSERINDO NA BASE
			getControladorUtil().inserir(debitoACobrar);

			/*
			 * 5.3. <<Inclui>> [UC0108 – Obter Quantidade de Economias por
			 * Categoria]. 5.4. <<Inclui>> [UC0185 – Obter Valor por Categoria].
			 * 5.5. Inclui, na tabela DEBITO_A_COBRAR_CATEGORIA, a(s)
			 * categoria(s) e sua(s) respectiva(s) quantidade(s) de economia da
			 * lista retornada pelo [UC0108] e os valores retornados pelo
			 * [UC0185] para cada categoria:
			 */
			inserirDebitoACobrarCategoria(debitoACobrar, imovel);
		}
	}

	public void excluirDebitosImoveisCortados(Integer idImovel,
			Integer anoMesFaturamento) throws ControladorException {

		try {

			// Atualizando o valor do débito da conta
			Collection<Integer> colIdDebitoACobrar = this.repositorioFaturamento
					.atualizarValorDebitoDaConta(idImovel, anoMesFaturamento);

			if (colIdDebitoACobrar != null && !colIdDebitoACobrar.isEmpty()) {

				// Apagamos o débito cobrado categoria
				this.repositorioFaturamento
						.deletarDebitosCobradosCategoriaImoveisCortados(
								idImovel, anoMesFaturamento);

				// Apagamos o débito cobrado
				this.repositorioFaturamento
						.deletarDebitosCobradosImoveisCortados(idImovel,
								anoMesFaturamento);

				// Apagamos o debito a cobrar categoria
				this.repositorioFaturamento
						.deletarDebitosACobrarCategoria(colIdDebitoACobrar);

				// Apagamos o debito a cobrar/debito a cobrar geral
				this.repositorioFaturamento
						.deletarDebitosACobrar(colIdDebitoACobrar);

			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2010
	 */
	public Conta pesquisarUltimaContaDoImovel(Integer idImovel)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarUltimaContaDoImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1035] Efetivar Alterar Inscrição de Imóvel
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 08/07/201
	 */
	public void alterarInscricoesImoveis(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {

			// Variáveis para a paginação da pesquisa de Imovel por Grupo
			// Faturamento
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;
			// ========================================================================

			while (!flagTerminou) {

				Collection colecaoDados = this.repositorioFaturamento.pesquisarImoveisComInscricaoPedenteParaAtualizacao(idLocalidade, numeroIndice, quantidadeRegistros);

				if (colecaoDados != null && !colecaoDados.isEmpty()) {

					Iterator dadosIterator = colecaoDados.iterator();

					while (dadosIterator.hasNext()) {

						boolean existeImovelComMesmaIncricao = false;

						ImovelInscricaoAlterada imovelInscricaoAlterada = (ImovelInscricaoAlterada) dadosIterator.next();

						Imovel imovelAtualizar = imovelInscricaoAlterada.getImovel();
						Localidade localidade = imovelInscricaoAlterada.getLocalidadeAtual();
						SetorComercial setorComercial = imovelInscricaoAlterada.getSetorComercialAtual();
						Quadra quadra = imovelInscricaoAlterada.getQuadraAtual();
						Short lote = imovelInscricaoAlterada.getLoteAtual();
						Short subLote = imovelInscricaoAlterada.getSubLoteAtual();

						QuadraFace quadraFace = null;
						if (imovelInscricaoAlterada.getQuadraFaceAtual() != null) {
							quadraFace = imovelInscricaoAlterada.getQuadraFaceAtual();
						}

						// Inicio [FS0002] Verificar duplicidade de inscrição
						FiltroImovel filtroImovel = new FiltroImovel();
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID, localidade.getId()));
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_ID, setorComercial.getId()));
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_ID, quadra.getId()));
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOTE, lote));
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SUBLOTE, subLote));

						if (quadraFace != null) {
							filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_FACE_ID, quadraFace.getId()));
						}

						Collection colecaoImoveis = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());

						if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

							Imovel imovelMesmaInscricao = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);

							if (imovelMesmaInscricao != null) {
								existeImovelComMesmaIncricao = true;
							}
						}

						// Caso exista não efetua a alteração na tabela imovel.
						if (existeImovelComMesmaIncricao) {
							imovelInscricaoAlterada.setIndicadorErroAlteracao(ConstantesSistema.SIM);
							imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.NAO);
						}
						else {
							imovelAtualizar.setLocalidade(localidade);
							imovelAtualizar.setSetorComercial(setorComercial);
							imovelAtualizar.setQuadra(quadra);
							imovelAtualizar.setLote(lote);
							imovelAtualizar.setSubLote(subLote);
							imovelAtualizar.setUltimaAlteracao(new Date());
							if (quadraFace != null) {
								imovelAtualizar.setQuadraFace(quadraFace);
							}

							if (imovelInscricaoAlterada.getUsuarioAlteracao() != null && !imovelInscricaoAlterada.getUsuarioAlteracao().equals("")) {
								RegistradorOperacao registradorOperacao = new RegistradorOperacao(
										Operacao.OPERACAO_IMOVEL_ATUALIZAR, imovelAtualizar.getId(),
										imovelAtualizar.getId(),
										new UsuarioAcaoUsuarioHelper(imovelInscricaoAlterada.getUsuarioAlteracao(),
												UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

								registradorOperacao.registrarOperacao(imovelAtualizar);

								getControladorTransacao().registrarTransacao(imovelAtualizar);
							}

							imovelAtualizar.setUsuarioParaHistorico(getControladorBatch().obterUsuarioQueDisparouProcesso(idFuncionalidadeIniciada));
							
							getControladorAtualizacaoCadastro().atualizar(imovelAtualizar);

							imovelInscricaoAlterada.setIndicadorErroAlteracao(ConstantesSistema.NAO);
							imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.SIM);
						}

						// Atualiza a tabela IMOVEL_INSCR_ALTERADA

						Date dataAtual = new Date();

						imovelInscricaoAlterada.setDataAlteracaoBatch(dataAtual);
						imovelInscricaoAlterada.setUltimaAlteracao(dataAtual);
						getControladorUtil().atualizar(imovelInscricaoAlterada);
					}
				}

				/**
				 * Incrementa o nº do indice da páginação
				 */
				numeroIndice = numeroIndice + quantidadeRegistros;

				/**
				 * Caso a coleção de imoveis retornados for menor que a
				 * quantidade de registros seta a flag indicando que a paginação
				 * terminou.
				 */
				if (colecaoDados == null || colecaoDados.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (colecaoDados != null) {
					colecaoDados.clear();
					colecaoDados = null;
				}

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	/**
	 * 
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * Item 2
	 * 
	 * @autor Bruno Barros
	 * @date 13/07/2010
	 * 
	 * @param idRota
	 *            - Id da rota a ser processada
	 * @param anoMesFaturamento
	 *            - Ano mês do a ser faturado
	 * @throws ControladorException
	 */
	private void atualizarFaturamentoImoveisCortados(
			Collection<Imovel> colImoveis, int anoMesFaturamento)
			throws ControladorException {
		try {
			for (Imovel imovel : colImoveis) {

				LigacaoTipo lt = new LigacaoTipo();
				lt.setId(LigacaoTipo.LIGACAO_AGUA);

				// Pesquisamos o consumo historico do imóvel selecionado
				ConsumoHistorico consumoHistorico = this
						.getControladorMicromedicao().obterConsumoHistorico(
								imovel, lt, anoMesFaturamento);

				if (consumoHistorico != null) {

					// 2.1
					if ((imovel.getLigacaoAguaSituacao() != null && !imovel
							.getLigacaoAguaSituacao().getId()
							.equals(LigacaoAguaSituacao.CORTADO))
							|| (consumoHistorico.getConsumoTipo() != null
									&& consumoHistorico.getConsumoTipo()
											.getId().equals(ConsumoTipo.REAL)
									&& consumoHistorico
											.getNumeroConsumoFaturadoMes() != null && consumoHistorico
									.getNumeroConsumoFaturadoMes() > 0)) {

						this.excluirDebitosImoveisCortados(imovel.getId(),
								anoMesFaturamento);
						// 2.2
					} else {
						// Verificamos se o imóvel possui o débito
						Object[] debitoACobrar = this.repositorioFaturamento
								.pesquisarDebitoACobrar(imovel.getId(),
										DebitoTipo.TARIFA_CORTADO,
										anoMesFaturamento);

						if (debitoACobrar != null) {

							this.repositorioFaturamento
									.atualizarIndicadorFaturamentoConsumoHistorico(
											consumoHistorico.getId(),
											ConstantesSistema.NAO.shortValue());
						}
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * @author Raphael Rossiter
	 * @date 13/07/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void verificarFaturamentoImoveisCortados(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// PROCESSO BATCH
		// ------------------------------------------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronogramaRota))
								.getRota().getId());
		// ---------------------------------------------------------------------------------------------------

		try {

			if (colecaoFaturamentoAtividadeCronogramaRota != null
					&& !colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {

				Iterator iteratorColecaoFaturamentoAtividadeCronogramaRota = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				// LAÇO PARA GERAR DÉBITO PARA TODAS AS ROTAS
				while (iteratorColecaoFaturamentoAtividadeCronogramaRota
						.hasNext()) {

					FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) iteratorColecaoFaturamentoAtividadeCronogramaRota
							.next();

					// =================================================================================================
					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection<Imovel> colecaoImovel = this
								.pesquisarImovelComDebitoTarifaCortado(
										faturamentoAtivCronRota.getRota(),
										faturamentoGrupo.getAnoMesReferencia(),
										numeroIndice, quantidadeRegistros);

						/*
						 * Caso exista ids de imóveis para a rota atual
						 * determina a geração do crédito para cada imóvel
						 * retornado.
						 */
						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							// Verificar Farturamento dos Imóveis Cortados
							// --------------------------------------------------------------------------------
							this.atualizarFaturamentoImoveisCortados(
									colecaoImovel,
									faturamentoGrupo.getAnoMesReferencia());
							// --------------------------------------------------------------------------------
						}

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null
								|| colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}

					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA

				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * @author Raphael Rossiter
	 * @date 13/07/2010
	 * 
	 * @param rota
	 * @param anoMesfaturamento
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */
	protected Collection<Imovel> pesquisarImovelComDebitoTarifaCortado(
			Rota rota, Integer anoMesfaturamento, int numeroIndice,
			int quantidadeRegistros) throws ControladorException {

		Collection<Imovel> colecaoImoveis = null;
		Collection imoveis;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelComDebitoTarifaCortadoPorRota(
								rota.getId(), anoMesfaturamento, numeroIndice,
								quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelComDebitoTarifaCortadoPorRotaAlternativa(
								rota.getId(), anoMesfaturamento, numeroIndice,
								quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		// Carregando os dados dos imóveis selecionados
		if (imoveis != null && !imoveis.isEmpty()) {

			Iterator iteratorImoveis = imoveis.iterator();

			colecaoImoveis = new ArrayList();

			Imovel imovel = null;

			while (iteratorImoveis.hasNext()) {

				Object[] arrayImovel = (Object[]) iteratorImoveis.next();

				imovel = new Imovel();

				// id do imovel
				if (arrayImovel[0] != null) {
					imovel.setId((Integer) arrayImovel[0]);
				}

				// ligacaoAguaSituacao.id
				if (arrayImovel[1] != null) {
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId((Integer) arrayImovel[1]);

					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}

				// adiciona o imovel
				colecaoImoveis.add(imovel);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * [UC0820] - Atualizar Faturamento do Movimento Celular
	 * 
	 * Verifica se a quantidade de imóveis que chegaram é a esperada.
	 * 
	 * @author bruno
	 * @date 16/08/2010
	 * 
	 * @param idRota
	 *            - Id da rota ser verificada
	 * @param anoMesFaturamento
	 *            - Ano mes de faturamento a ser pesquisado
	 * 
	 * @return Integer
	 * 
	 */
	public Integer pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
			Integer idRota, Integer anoMesFaturamento)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = this.repositorioFaturamento
					.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
							idRota, anoMesFaturamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * @author Rômulo Aurélio
	 * @throws ControladorException
	 * @data 22/06/2010
	 */
	public Integer retornaAnoMesFaturamentoGrupoDaRota(Integer idRota)
			throws ControladorException {

		Integer retorno = null;

		try {
			return this.repositorioFaturamento
					.retornaAnoMesFaturamentoGrupoDaRota(idRota);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * Pesquisar Conta Historico
	 * 
	 * @author Fernando Fontelles
	 * @date 06/08/2010
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return
	 * @throws ControladorException
	 */
	public ContaHistorico pesquisarContaHistoricoDigitada(String idImovel,
			String referenciaConta) throws ControladorException {

		// Variável que vai armazenar a conta pesquisada
		ContaHistorico contaDigitada = null;
		Object[] dadosConta = null;

		// Formata a referência da conta informada para o formato (AAAAMM) sem a
		// barra
		String anoMesConta = Util
				.formatarMesAnoParaAnoMesSemBarra(referenciaConta);

		// Cria o filtro de conta e seta todos os parâmetros para pesquisar a
		// conta do imóvel
		// Pesquisa imovel
		try {
			dadosConta = repositorioFaturamento
					.pesquisarContaHistoricoDigitada(idImovel, anoMesConta);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (dadosConta != null) {

			contaDigitada = new ContaHistorico();

			// Id da Conta
			if (dadosConta[0] != null) {
				contaDigitada.setId((Integer) dadosConta[0]);
			}

			// Referência
			if (dadosConta[1] != null) {
				contaDigitada.setAnoMesReferenciaConta((Integer) dadosConta[1]);
			}

			// Valor da Água
			if (dadosConta[2] != null) {
				contaDigitada.setValorAgua((BigDecimal) dadosConta[2]);
			}

			// Valor de Esgoto
			if (dadosConta[3] != null) {
				contaDigitada.setValorEsgoto((BigDecimal) dadosConta[3]);
			}

			// Débitos
			if (dadosConta[4] != null) {
				contaDigitada.setValorDebitos((BigDecimal) dadosConta[4]);
			}

			// Valor Créditos
			if (dadosConta[5] != null) {
				contaDigitada.setValorCreditos((BigDecimal) dadosConta[5]);
			}

			// Valor Imposto
			if (dadosConta[6] != null) {
				contaDigitada.setValorImposto((BigDecimal) dadosConta[6]);
			}

			// Data Vencimento
			if (dadosConta[7] != null) {
				contaDigitada.setDataVencimentoConta((Date) dadosConta[7]);
			}

			// Débito Crédito Situação
			if (dadosConta[8] != null && dadosConta[9] != null) {
				DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
				debitoCreditoSituacao.setId((Integer) dadosConta[8]);
				debitoCreditoSituacao
						.setDescricaoAbreviada((String) dadosConta[9]);
				contaDigitada
						.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
			}

			// Localidade
			if (dadosConta[10] != null) {
				Localidade localidade = new Localidade();
				localidade.setId((Integer) dadosConta[10]);
				contaDigitada.setLocalidade(localidade);
			}

		}

		// Retorna a conta encontrada ou nulo se não existir a conta
		return contaDigitada;
	}

	/**
	 * Verifica a Quantidade de Alteracoes no Vencimento da Conta
	 * 
	 * @author Hugo Leonardo
	 * @date 10/08/2010
	 * 
	 * @param idsConta
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarQuantidadeAlteracoesVencimentoConta(Collection idsConta)
			throws ControladorException {

		try {
			Iterator iteratorContas = idsConta.iterator();

			// PARÂMETROS DO SISTEMA
			SistemaParametro sistemaParametro = this.getControladorUtil()
					.pesquisarParametrosDoSistema();

			if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento()
					.equals(ConstantesSistema.SIM)) {

				Integer quantidadeMaximaRetificacoes = sistemaParametro
						.getNumeroLimiteAlteracaoVencimento();

				while (iteratorContas.hasNext()) {

					Integer idConta = (Integer) iteratorContas.next();

					Integer quantidadeRetificacoes = this.repositorioFaturamento
							.obterQuantidadeAlteracoesVencimentoConta(idConta);

					if (quantidadeRetificacoes != null
							&& quantidadeMaximaRetificacoes != null
							&& quantidadeMaximaRetificacoes <= quantidadeRetificacoes) {
						throw new ControladorException(
								"atencao.retificacao_conta_nao_permitida",
								null, quantidadeMaximaRetificacoes.toString());
					}
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC1051] Gerar Relatório de Amostragem das Anormalidades Informadas
	 * 
	 * @author Hugo Leonardo
	 * @date 09/08/2010
	 * 
	 * @throws ControladorException
	 */
	public Collection<GerarRelatorioAnormalidadePorAmostragemHelper> pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria, Integer amostragem)
			throws ControladorException {

		Collection retorno = new ArrayList();

		// Criação das coleções
		Collection colecaoDadosAnormalidadesConsumoPorAmostragem = null;

		Integer totalRelatorio = 0;
		double porcentagem = 0.0;

		try {
			totalRelatorio = this.repositorioFaturamento
					.pesquisarTotalDadosRelatorioAnormalidadeConsumoPorAmostragem(
							idGrupoFaturamento, codigoRota, idGerenciaRegional,
							idUnidadeNegocio, idLocalidadeInicial,
							idLocalidadeFinal, idSetorComercialInicial,
							idSetorComercialFinal, referencia, idImovelPerfil,
							numOcorConsecutivas, indicadorOcorrenciasIguais,
							mediaConsumoInicial, mediaConsumoFinal,
							colecaoIdsAnormalidadeConsumo,
							colecaoIdsAnormalidadeLeitura,
							colecaoIdsAnormalidadeLeituraInformada,
							tipoMedicao, colecaoIdsEmpresa,
							numeroQuadraInicial, numeroQuadraFinal, idCategoria);

			porcentagem = (totalRelatorio * amostragem) / 100;

			totalRelatorio = new Integer((int) Math.round(porcentagem));

			colecaoDadosAnormalidadesConsumoPorAmostragem = this.repositorioFaturamento
					.pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
							idGrupoFaturamento, codigoRota, idGerenciaRegional,
							idUnidadeNegocio, idLocalidadeInicial,
							idLocalidadeFinal, idSetorComercialInicial,
							idSetorComercialFinal, referencia, idImovelPerfil,
							numOcorConsecutivas, indicadorOcorrenciasIguais,
							mediaConsumoInicial, mediaConsumoFinal,
							colecaoIdsAnormalidadeConsumo,
							colecaoIdsAnormalidadeLeitura,
							colecaoIdsAnormalidadeLeituraInformada,
							tipoMedicao, colecaoIdsEmpresa,
							numeroQuadraInicial, numeroQuadraFinal,
							idCategoria, totalRelatorio);

		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDadosAnormalidadesConsumoPorAmostragem != null
				&& !colecaoDadosAnormalidadesConsumoPorAmostragem.isEmpty()) {

			Iterator colecaoDadosAnormalidadesConsumoIterator = colecaoDadosAnormalidadesConsumoPorAmostragem
					.iterator();

			while (colecaoDadosAnormalidadesConsumoIterator.hasNext()) {

				// Obtém os dados do débito cobrado
				Object[] dadosAnormalidadesConsumo = (Object[]) colecaoDadosAnormalidadesConsumoIterator
						.next();

				GerarRelatorioAnormalidadePorAmostragemHelper relatorioHelper = new GerarRelatorioAnormalidadePorAmostragemHelper();

				// Id do Grupo de Faturamento
				if (dadosAnormalidadesConsumo[0] != null) {
					relatorioHelper
							.setIdGrupo((Integer) dadosAnormalidadesConsumo[0]);
				}

				// Nome do Grupo de Faturamento
				if (dadosAnormalidadesConsumo[1] != null) {
					relatorioHelper
							.setNomeGrupo((String) dadosAnormalidadesConsumo[1]);
				}

				// Id da Gerência Regional
				if (dadosAnormalidadesConsumo[2] != null) {
					relatorioHelper
							.setIdGerenciaRegional((Integer) dadosAnormalidadesConsumo[2]);
				}

				// Nome da Gerência Regional
				if (dadosAnormalidadesConsumo[3] != null) {
					relatorioHelper
							.setNomeGerenciaRegional((String) dadosAnormalidadesConsumo[3]);
				}

				// Id da Unidade de Negócio
				if (dadosAnormalidadesConsumo[4] != null) {
					relatorioHelper
							.setIdUnidadeNegocio((Integer) dadosAnormalidadesConsumo[4]);
				}

				// Nome da Unidade de Negócio
				if (dadosAnormalidadesConsumo[5] != null) {
					relatorioHelper
							.setNomeUnidadeNegocio((String) dadosAnormalidadesConsumo[5]);
				}

				// Id do Elo
				if (dadosAnormalidadesConsumo[6] != null) {
					relatorioHelper
							.setIdElo((Integer) dadosAnormalidadesConsumo[6]);
				}

				// Nome do Elo
				if (dadosAnormalidadesConsumo[7] != null) {
					relatorioHelper
							.setNomeElo((String) dadosAnormalidadesConsumo[7]);
				}

				// Id da Localidade
				if (dadosAnormalidadesConsumo[8] != null) {
					relatorioHelper
							.setIdLocalidade((Integer) dadosAnormalidadesConsumo[8]);
				}

				// Nome da Localidade
				if (dadosAnormalidadesConsumo[9] != null) {
					relatorioHelper
							.setNomeLocalidade((String) dadosAnormalidadesConsumo[9]);
				}

				// Id do Imóvel
				if (dadosAnormalidadesConsumo[10] != null) {
					relatorioHelper
							.setIdImovel((Integer) dadosAnormalidadesConsumo[10]);
				}

				// Nome do Usuário
				if (dadosAnormalidadesConsumo[11] != null) {
					relatorioHelper
							.setNomeUsuario((String) dadosAnormalidadesConsumo[11]);
				}

				// Id da Situação de Água
				if (dadosAnormalidadesConsumo[12] != null) {
					relatorioHelper
							.setSituacaoLigacaoAgua((Integer) dadosAnormalidadesConsumo[12]);
				}

				// Id da Situação de Esgoto
				if (dadosAnormalidadesConsumo[13] != null) {
					relatorioHelper
							.setSituacaoLigacaoEsgoto((Integer) dadosAnormalidadesConsumo[13]);
				}

				// Indicador de Débito Automático
				if (dadosAnormalidadesConsumo[14] != null) {
					relatorioHelper
							.setIndicadorDebito((Short) dadosAnormalidadesConsumo[14]);
				}

				// Consumo Médio
				if (dadosAnormalidadesConsumo[15] != null) {
					relatorioHelper
							.setConsumoMedio((Integer) dadosAnormalidadesConsumo[15]);
				}

				// Consumo do Mês
				if (dadosAnormalidadesConsumo[16] != null) {
					relatorioHelper
							.setConsumoMes((Integer) dadosAnormalidadesConsumo[16]);
				}

				// Descrição Abreviada da Anormalidade de Consumo
				if (dadosAnormalidadesConsumo[17] != null) {
					relatorioHelper
							.setDescricaoAbrevConsumoAnormalidade((String) dadosAnormalidadesConsumo[17]);
				}

				// Id da Anormalidade de Leitura
				if (dadosAnormalidadesConsumo[18] != null) {
					relatorioHelper
							.setIdLeituraAnormalidade((Integer) dadosAnormalidadesConsumo[18]);
				}

				// Quantidade de Economias
				if (dadosAnormalidadesConsumo[19] != null) {
					relatorioHelper
							.setQuantidadeEconomias((Short) dadosAnormalidadesConsumo[19]);
				}

				// Tipo de Medição
				if (dadosAnormalidadesConsumo[20] != null) {

					if (dadosAnormalidadesConsumo[20].equals(MedicaoTipo.POCO)) {
						relatorioHelper.setTipoMedicao("PC");
					} else {
						relatorioHelper.setTipoMedicao("LA");
					}

				} else {
					relatorioHelper.setTipoMedicao("");
				}

				// Descrição Abreviada da Capacidade do Hidrômetro da
				// Ligação de Água
				if (dadosAnormalidadesConsumo[21] != null) {
					relatorioHelper
							.setCapacidadeHidrometro((String) dadosAnormalidadesConsumo[21]);
				}

				// Descrição Abreviada do Local de Instalação do Hidrômetro
				// da Ligação de Água
				if (dadosAnormalidadesConsumo[22] != null) {
					relatorioHelper
							.setLocalInstalacaoHidrometro((String) dadosAnormalidadesConsumo[22]);
				}

				// Id do Setor Comercial
				if (dadosAnormalidadesConsumo[23] != null) {
					relatorioHelper
							.setIdSetorComercial((Integer) dadosAnormalidadesConsumo[23]);
				}

				// Código do Setor Comercial
				if (dadosAnormalidadesConsumo[24] != null) {
					relatorioHelper
							.setCodigoSetorComercial((Integer) dadosAnormalidadesConsumo[24]);
				}

				// Número leitura atual informada
				if (dadosAnormalidadesConsumo[25] != null) {
					relatorioHelper
							.setNnLeituraAtualInformada((Integer) dadosAnormalidadesConsumo[25]);
				}

				// Id Empresa
				if (dadosAnormalidadesConsumo[26] != null) {
					relatorioHelper
							.setIdEmpresa((Integer) dadosAnormalidadesConsumo[26]);
				}

				// Nome Empresa
				if (dadosAnormalidadesConsumo[27] != null) {
					relatorioHelper
							.setNomeEmpresa((String) dadosAnormalidadesConsumo[27]);
				}

				// inscrição do Imóvel
				if (dadosAnormalidadesConsumo[28] != null) {
					relatorioHelper
							.setInscricaoImovel((String) dadosAnormalidadesConsumo[28]);
				}
				if (dadosAnormalidadesConsumo[29] != null) {
					relatorioHelper
							.setEnderecoImovel((String) dadosAnormalidadesConsumo[29]);
				}

				retorno.add(relatorioHelper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @param idOrdemServico
	 */
	public boolean verificarExistenciaAutosInfracaoPorOS(Integer idOrdemServico)
			throws ControladorException {
		try {
			boolean existeAutosInfracaoPorOs = false;

			Collection autosInfracao = repositorioFaturamento
					.verificarExistenciaAutosInfracaoPorOS(idOrdemServico);

			if (autosInfracao != null && !autosInfracao.isEmpty()) {
				existeAutosInfracaoPorOs = true;
			}

			return existeAutosInfracaoPorOs;

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public AutosInfracao pesquisarAutosInfracaoPorOS(Integer idOrdemServico)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarAutosInfracaoPorOS(idOrdemServico);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 24/08/2010
	 * 
	 * @param idAutoInfracao
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaAutosInfracaoDebitoACobrar(Integer idAutoInfracao)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisaAutosInfracaoDebitoACobrar(idAutoInfracao);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * Remove os imóveis que ja foram enviados para uma determinada rota em
	 * impressão simultanea
	 * 
	 * @autor Bruno Barros.
	 * @date 24/08/2010
	 * 
	 * @param idRota
	 *            - Id da rota a ser pesquisada
	 * @param BufferedReader
	 *            - Buffer com TODOS os imóveis da rota
	 * 
	 * @return BufferedReader Novo buffer apenas com as matriculas que ainda
	 *         precisam ser processadas
	 */

	public BufferedReader removerImoveisJaProcessadosBufferImpressaoSimultanea(
			Integer idRota, BufferedReader reader) throws ControladorException {

		StringBuffer arquivo = new StringBuffer();

		BufferedReader retorno = null;

		try {

			RemoverImovesJaProcessadorImpressaoSimultaneaHelper helper = new RemoverImovesJaProcessadorImpressaoSimultaneaHelper(reader);

			System.out.println(helper);

			Integer anoMesFaturamento = retornaAnoMesFaturamentoGrupoDaRota(idRota);

			for (DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel : helper.getColDadosFormatados()) {

				boolean alterouAgua = false;

				// Selecionamos os dados dos medidos de agua e dos não medidos

				if (dadosImovel.isMedidoAgua() || (!dadosImovel.isMedidoAgua() && !dadosImovel.isMedidoPoco())) {
					alterouAgua = this.reprocessarImovelImpressaoSimultanea(
						anoMesFaturamento,
						dadosImovel.getIdImovel(),
						MedicaoTipo.LIGACAO_AGUA.shortValue(),
						dadosImovel.getLeituraAgua(),
						dadosImovel.getAnormalidadeAgua(),
						dadosImovel.getIndicadorEmissaoConta());
				}

				boolean alterouPoco = false;

				if (dadosImovel.isMedidoPoco()) {
					alterouPoco = this.reprocessarImovelImpressaoSimultanea(
						anoMesFaturamento,
						dadosImovel.getIdImovel(),
						MedicaoTipo.POCO.shortValue(),
						dadosImovel.getLeituraPoco(),
						dadosImovel.getAnormalidadePoco(),
						dadosImovel.getIndicadorEmissaoConta());
				}

				logger.info("Alteração leitura do imóvel " + dadosImovel.getIdImovel() + ": agua: " + alterouAgua + ", esgoto: " + alterouPoco);

				if (alterouAgua || alterouPoco) {
					String[] linhas = dadosImovel.getLinhas();

					for (String string : linhas) {
						arquivo.append(string + "\n");
					}
				}
			}

			// Adiciona as linhas referentes a rota de marcação
			arquivo.append(helper.getRegistrosRotaMarcacao());

			if (arquivo.length() > 0) {
				InputStream is = new ByteArrayInputStream(arquivo.toString().getBytes());
				InputStreamReader readerRetorno = new InputStreamReader(is);
				retorno = new BufferedReader(readerRetorno);
			}
		} catch (IOException ex1) {
			throw new ControladorException("erro.sistema", ex1);
		}

		return retorno;
	}

	/**
	 * 
	 * Verifica se algum imóvel teve uma solicitação de releitura para uma rota
	 * e anomes
	 * 
	 * @author Bruno Barros
	 * @date 01/09/2010
	 * 
	 * @param idRota
	 *            - Id da rota a ser pesquisada
	 * 
	 * @return String com a mensagem formatada para o celular - Formato da
	 *         mensagem:
	 *         XXXXXXX...&YYYYY=123456,654321,567890&ZZZZZ=123123,123123
	 *         ,123123...
	 * 
	 *         Sendo XXXX -> Mensagem a ser apresentada no celular. & ->
	 *         Separador que indica que a mensagem acabou. YYYYY - > Nome do
	 *         parametro = -> Indicador que o nome do parametro acabou 123456 ->
	 *         Valor retornado , -> Separador de valor
	 * 
	 *         Caso não haja imóveis para releitura, retorna nulo;
	 * 
	 * @throws ErroRepositorioException
	 */
	private String verificarSolicitacaoReleituraImovelImpressaoSimultanea(
			Integer idRota) throws ControladorException {

		// Verificamos se alguma solicitação de releitura foi feita para essa
		// rota
		Collection<ReleituraMobile> colReleituraMobile;
		StringBuffer retorno = new StringBuffer();

		try {
			Integer anoMesFaturamentoGrupoRota = this
					.retornaAnoMesFaturamentoGrupoDaRota(idRota);

			colReleituraMobile = this.repositorioMicromedicao
					.pesquisarImoveisReleituraMobileSolicitada(idRota,
							anoMesFaturamentoGrupoRota);

			if (colReleituraMobile != null && colReleituraMobile.size() > 0) {

				StringBuffer matriculas = new StringBuffer();
				StringBuffer matriculasFormatadas = new StringBuffer();

				int i = 1;

				for (ReleituraMobile releituraMobile : colReleituraMobile) {

					if (i == colReleituraMobile.size()) {
						matriculas.append(releituraMobile.getImovel().getId());
						matriculasFormatadas.append(releituraMobile.getImovel()
								.getMatriculaFormatada());
					} else {
						matriculas.append(releituraMobile.getImovel().getId()
								+ ",");
						matriculasFormatadas.append(releituraMobile.getImovel()
								.getMatriculaFormatada() + ", ");
					}

					releituraMobile.setIndicadorMensagemRecebida(new Integer(
							ConstantesSistema.SIM));
					releituraMobile.setUltimaAlteracao(new Date());

					++i;
				}

				retorno.append("imoveis=" + matriculas);

				if (colReleituraMobile.size() == 1) {
					// retorno.append( "mensagem=Refazer leitura para o imovel "
					// + matriculasFormatadas.toString() + " . Ir para o imovel
					// ?&imoveis=" + matriculas );
				} else {
					// retorno.append( "mensagem=Refazer leitura para os imoveis
					// " + matriculasFormatadas.toString() + " . Ir para o
					// primeiro imovel ?&imoveis=" + matriculas );
				}

				// Colocamos como enviados
				this.getControladorBatch().atualizarColecaoObjetoParaBatch(
						colReleituraMobile);
				return retorno.toString();
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return null;
	}

	/**
	 * [UC1010] Emitir 2ª via de declaração anual de quitação de débitos
	 * 
	 * @Author Daniel Alves
	 * @Date 14/09/2010
	 * 
	 */
	public Collection pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(
			String idImovel) throws ControladorException {

		Collection colecaoRetorno = new ArrayList();

		// Pesquisa
		try {
			Collection colecaoEmitir2ViaDeclaracaoAnualQuitacaoDebitos = repositorioFaturamento
					.pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(idImovel);

			Iterator iterator = colecaoEmitir2ViaDeclaracaoAnualQuitacaoDebitos
					.iterator();

			while (iterator.hasNext()) {

				Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper helper = new Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper();

				Object[] objeto = (Object[]) iterator.next();

				// id
				if (objeto[0] != null) {
					helper.setId((Integer) objeto[0]);
				}

				// descricao
				if (objeto[1] != null) {
					helper.setDescricao((Integer) objeto[1]);
				}

				colecaoRetorno.add(helper);
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoRetorno;

	}

	/**
	 * [UC1073] – Religar Imóveis Cortados com Consumo Real
	 * 
	 * @author Vivianne Sousa
	 * @date 13/09/2010
	 * 
	 */
	public void religarImovelCortadoComConsumoReal(
			Integer anoMesReferenciaFaturamento, Integer idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, (idLocalidade));

		try {

			Collection colecaoMatriculasImoveis = null;

			int mesfaturamento = Util.obterMes(anoMesReferenciaFaturamento);
			int anoFaturamento = Util.obterAno(anoMesReferenciaFaturamento);
			int ultimoDiaMes = new Integer(Util.obterUltimoDiaMes(
					mesfaturamento, anoFaturamento));
			Date data = Util.criarData(ultimoDiaMes, mesfaturamento,
					anoFaturamento);
			Date dataMenos30Dias = Util.subtrairNumeroDiasDeUmaData(data, 30);

			colecaoMatriculasImoveis = repositorioFaturamento
					.pesquisarImoveisCortados(LigacaoAguaSituacao.CORTADO,
							dataMenos30Dias, idLocalidade);

			if (colecaoMatriculasImoveis != null
					&& !colecaoMatriculasImoveis.isEmpty()) {

				Iterator icolecaoMatriculasImoveis = colecaoMatriculasImoveis
						.iterator();

				String idConsumoHistorico = null;
				while (icolecaoMatriculasImoveis.hasNext()) {

					Integer idImovel = (Integer) icolecaoMatriculasImoveis
							.next();

					idConsumoHistorico = repositorioFaturamento
							.pesquisarImoveisConsumoFaturadoReal(idImovel,
									anoMesReferenciaFaturamento,
									ConsumoTipo.REAL, LigacaoTipo.LIGACAO_AGUA);

					if (idConsumoHistorico != null) {

						selecionarAtualizaSituacaoLigacaoAguaImovelREGISTRATRANSACAO(
								idImovel, LigacaoAguaSituacao.LIGADO,
								Usuario.USUARIO_BATCH);

						selecionarAtualizaDataReligacaoAguaREGISTRATRANSACAO(
								idImovel, new Date(), Usuario.USUARIO_BATCH);

						System.out.println("--- IMOVEL --- " + idImovel);

						repositorioFaturamento.religarImovelCortado(idImovel,
								LigacaoAguaSituacao.LIGADO, new Date());

					}

				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	/**
	 * Registra transacao do imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	protected void selecionarAtualizaSituacaoLigacaoAguaImovelREGISTRATRANSACAO(
			Integer idImovel, Integer idLigacaoAguaSituacao,
			Usuario usuarioLogado) throws ControladorException {

		Imovel imovel = new Imovel();
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				idImovel));
		Collection colecaoImovel = this.getControladorUtil().pesquisar(
				filtroImovel, Imovel.class.getName());
		imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(idLigacaoAguaSituacao);

		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);

		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, idImovel));
		Collection colecaoLigacaoAgua = this.getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());
		ligacaoAgua = (LigacaoAgua) Util
				.retonarObjetoDeColecao(colecaoLigacaoAgua);

		ligacaoAgua.setDataReligacao(new Date());

		imovel.setLigacaoAgua(ligacaoAgua);

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL,
				idImovel, idImovel, new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(imovel);

		getControladorTransacao().registrarTransacao(imovel);

	}

	/**
	 * Registra transacao do imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	protected void selecionarAtualizaDataReligacaoAguaREGISTRATRANSACAO(
			Integer idImovel, Date dataReligacao, Usuario usuarioLogado)
			throws ControladorException {

		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, idImovel));
		Collection colecaoLigacaoAgua = this.getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());
		ligacaoAgua = (LigacaoAgua) Util
				.retonarObjetoDeColecao(colecaoLigacaoAgua);

		ligacaoAgua.setDataReligacao(dataReligacao);

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL,
				ligacaoAgua.getId(), ligacaoAgua.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(ligacaoAgua);

		getControladorTransacao().registrarTransacao(ligacaoAgua);

	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método criado para evitar o if "compesa" ou if "caern". Para todas as
	 * empresas, o próximo arquivo do leiturista é disponibilizado assim que o
	 * arquivo anterior é finalizado. Apenas na compesa, não permite.
	 * 
	 * @author Bruno Barros
	 * @date 05/10/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean liberaProximoArquivoImpressaoSimultaneaOnLine()
			throws ControladorException {
		return true;
	}

	/**
	 * [UC1083] Prescrever Débitos de Imóveis Públicos
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 * @param PrescreverDebitosImovelHelper
	 * @throws ControladorException
	 */
	public Integer prescreverDebitosImoveisPublicos(
			PrescreverDebitosImovelHelper helper) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		// Se Batch Manual - Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL
		if (Util.verificarNaoVazio(helper.getFormaPrescricao())
				&& helper.getFormaPrescricao().equals("0")) {

			String dataFinal = helper.getDataFim();
			String dataInicial = helper.getDataInicio();

			// verifica se existe alguma esfera de poder selecionada.

			if (helper.getIdImovel() == null && helper.getIdCliente() == null) {

				if (helper.getEsferaPoder().equals("")) {

					throw new ActionServletException(
							"atencao.campo_texto.obrigatorio", null,
							"Esfera do Poder");
				}
			}

			// Validar se a Data tem pelo menos 5 anos a menos que a data atual
			Date data = Util.converteStringParaDate(dataFinal);
			Date dataAnoMes = Util.gerarDataApartirAnoMesRefencia(new Integer(
					helper.getAnoMesReferencia()));

			if (Util.anosEntreDatas(data, dataAnoMes) < 5) {
				throw new ControladorException(
						"atencao.anomesreferencia.invalida.prescricao", null,
						Util.formatarData(data));
			}

			String anoMesInicial = Util.recuperaAnoMesDataString(dataInicial)
					.toString();
			helper.setDataInicio(anoMesInicial);

			String anoMesFinal = Util.recuperaAnoMesDataString(dataFinal)
					.toString();
			helper.setDataFim(anoMesFinal);

			Map parametros = new HashMap();
			parametros.put("helper", helper);

			codigoProcessoIniciadoGerado = getControladorBatch()
					.inserirProcessoIniciadoParametrosLivres(parametros,
							helper.getIdProcesso(), helper.getUsuarioLogado());
		} else {
			// Se Batch Automatico -
			// Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO
			Prescricao prescricao = new Prescricao();

			Integer idEsferaPoder = null;

			prescricao.setAnoMesReferencia(new Integer(helper
					.getAnoMesReferencia()));

			EsferaPoder esferaPoder1 = new EsferaPoder();
			EsferaPoder esferaPoder2 = new EsferaPoder();

			Collection colecaoEsferaPoder = Util.separarCamposString(",",
					helper.getEsferaPoder());

			Iterator iEsferaPoder = colecaoEsferaPoder.iterator();

			idEsferaPoder = new Integer(iEsferaPoder.next().toString());
			esferaPoder1.setId(idEsferaPoder);

			if (iEsferaPoder.hasNext()) {
				idEsferaPoder = new Integer(iEsferaPoder.next().toString());
				esferaPoder2.setId(idEsferaPoder);
			}

			prescricao.setEsferaPoder1(esferaPoder1);
			prescricao.setEsferaPoder2(esferaPoder2);
			prescricao.setUltimaAlteracao(new Date());

			getControladorUtil().inserir(prescricao);

		}

		return codigoProcessoIniciadoGerado;
	}

	/**
	 * 
	 * [UC1038] Prescrever Débitos de Imóveis Públicos
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 */
	public void prescreverDebitosImoveisPublicosManual(
			Integer idFuncionalidadeIniciada, Map parametros)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		PrescreverDebitosImovelHelper helper = (PrescreverDebitosImovelHelper) parametros
				.get("helper");

		// ---------------------------------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// ---------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {

			this.repositorioFaturamento
					.prescreverDebitosImoveisPublicosManual(helper);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	public Collection obterDadosPrescricaoDebitosAutomaticos()throws ControladorException {

		try {
			return repositorioFaturamento.obterDadosPrescricaoDebitosAutomaticos();

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public void prescreverDebitosImoveisPublicosAutomatico(
			Integer idFuncionalidadeIniciada, Integer anoMesReferencia,
			Date dataPrescricao, Integer usuario, String idsEsferaPoder)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// ---------------------------------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// ---------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {

			this.repositorioFaturamento
					.prescreverDebitosImoveisPublicosAutomatico(
							anoMesReferencia, dataPrescricao, usuario,
							idsEsferaPoder);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 22/11/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarDebitoCreditoSituacaoAtualDaConta(idImovel,
							anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2010
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarArquivoTextoRoteiroEmpresa(Integer idRota,
			Integer anoMesReferencia) throws ControladorException {
		try {
			return repositorioFaturamento.pesquisarArquivoTextoRoteiroEmpresa(
					idRota, anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * Retificação de um conjunto de contas que foram pagas e que o pagamento
	 * não estava o débito e/ou crédito (Conta paga via Impressão Simultânea)
	 * 
	 * @author Sávio Luiz
	 * @date 27/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasPagasSemDebitoCreditoPago(
			Integer amreferencia, Integer idGrupo) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarContasPagasSemDebitoCreditoPago(amreferencia,
							idGrupo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Sávio Luiz, Ivan Sergio
	 * @date 24/05/2006, 01/12/2010
	 * @alteracoes: 01/12/2010 - RM247
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta(EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro, int tipoConta,
			Collection<NacionalFeriado> colecaoNacionalFeriado)
			throws ControladorException {

		String[] mensagem = new String[7];

		// mensagem da conta para a anormalidade de consumo (Baixo Consumo,Auto
		// Consumo e Estouro de Consumo)

		mensagem = obterMensagemAnormalidadeConsumo(emitirContaHelper);

		if (mensagem == null || mensagem.equals("")) {

			mensagem = new String[7];

			Integer anoMesReferenciaFinal = sistemaParametro
					.getAnoMesFaturamento();
			int anoMesSubtraido = Util.subtrairMesDoAnoMes(
					anoMesReferenciaFinal, 1);
			Integer dataVencimentoFinalInteger = sistemaParametro
					.getAnoMesArrecadacao();
			String anoMesSubtraidoString = ""
					+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
			int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
			int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

			// recupera o ultimo dia do anomes e passa a data como parametro
			Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
			dataVencimentoFinal.set(Calendar.YEAR, ano);
			dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
			dataVencimentoFinal
					.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
							.getActualMaximum(Calendar.DAY_OF_MONTH));

			Date dataFinalDate = dataVencimentoFinal.getTime();

			// converte String em data
			Date dataVencimento = Util.converteStringParaDate("01/01/1900");

			ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
					.obterDebitoImovelOuCliente(1,
							"" + emitirContaHelper.getIdImovel(), null, null,
							"190001", "" + anoMesSubtraido, dataVencimento,
							dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null);

			// se o imovel possua débito(debitoImovelCobrança for diferente de
			// nulo)
			if (debitoImovelClienteHelper != null
					&& ((debitoImovelClienteHelper
							.getColecaoGuiasPagamentoValores() != null && !debitoImovelClienteHelper
							.getColecaoGuiasPagamentoValores().isEmpty()) || (debitoImovelClienteHelper
							.getColecaoContasValores() != null && !debitoImovelClienteHelper
							.getColecaoContasValores().isEmpty()))) {

				String dataVencimentoFinalString = Util
						.formatarData(dataFinalDate);

				mensagem[0] = "AVISO:EM " + dataVencimentoFinalString
						+ " CONSTA DÉBITO SUJ.CORT. IGNORE CASO PAGO";

			} else {
				mensagem[0] = "";
			}

			if (tipoConta == 4) {

				StringBuilder msg = new StringBuilder();

				Object[] parmsDebitoAutomatico = null;
				try {
					parmsDebitoAutomatico = repositorioArrecadacao
							.pesquisarParmsDebitoAutomatico(emitirContaHelper
									.getIdImovel());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				String codigoAgencia = "";
				String idBanco = "";
				String indentificacaoBanco = "";
				if (parmsDebitoAutomatico != null) {

					// codigo Agencia
					if (parmsDebitoAutomatico[1] != null) {
						codigoAgencia = ((String) parmsDebitoAutomatico[1]);
					}

					// id do banco
					if (parmsDebitoAutomatico[2] != null) {
						idBanco = ((Integer) parmsDebitoAutomatico[2])
								.toString();
					}

					// indentificacao do banco
					if (parmsDebitoAutomatico[3] != null) {
						indentificacaoBanco = ((String) parmsDebitoAutomatico[3]);
					}

					msg.append("DEBITAR NO BANCO ");
					msg.append(idBanco);
					msg.append("/");
					msg.append(codigoAgencia);
					msg.append("/");
					msg.append(indentificacaoBanco);

					// Mensagem 2
					mensagem[1] = msg.toString();

					// Mensagem 3
					mensagem[2] = "";
				}
			}

			if (tipoConta == 3) {

				if (emitirContaHelper.getIdClienteResponsavel() != null
						&& !emitirContaHelper.getIdClienteResponsavel().equals(
								"")) {

					StringBuilder msg = new StringBuilder();

					String enderecoClienteResponsavel = null;

					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(
									new Integer(emitirContaHelper
											.getIdClienteResponsavel()));

					if (enderecoClienteResponsavel != null) {
						msg.append("ENTREGAR EM ");
						msg.append(Util.completaString(
								enderecoClienteResponsavel, 50));

						// Mensagem 2
						mensagem[1] = msg.toString();

						// Mensagem 3
						mensagem[2] = "";

					}
				}
			}

			if (tipoConta == 6) {

				StringBuilder msg2 = new StringBuilder();

				Object[] parmsDebitoAutomatico = null;
				try {
					parmsDebitoAutomatico = repositorioArrecadacao
							.pesquisarParmsDebitoAutomatico(emitirContaHelper
									.getIdImovel());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				String codigoAgencia = "";
				String idBanco = "";
				String indentificacaoBanco = "";
				if (parmsDebitoAutomatico != null) {

					// codigo Agencia
					if (parmsDebitoAutomatico[1] != null) {
						codigoAgencia = ((String) parmsDebitoAutomatico[1]);
					}

					// id do banco
					if (parmsDebitoAutomatico[2] != null) {
						idBanco = ((Integer) parmsDebitoAutomatico[2])
								.toString();
					}

					// indentificacao do banco
					if (parmsDebitoAutomatico[3] != null) {
						indentificacaoBanco = ((String) parmsDebitoAutomatico[3]);
					}

					msg2.append("DEBITAR NO BANCO ");
					msg2.append(idBanco);
					msg2.append("/");
					msg2.append(codigoAgencia);
					msg2.append("/");
					msg2.append(indentificacaoBanco);

					if (emitirContaHelper.getIdClienteResponsavel() != null
							&& !emitirContaHelper.getIdClienteResponsavel()
									.equals("")) {

						StringBuilder msg = new StringBuilder();

						String enderecoClienteResponsavel = null;

						// [UC0085]Obter Endereco
						enderecoClienteResponsavel = getControladorEndereco()
								.pesquisarEnderecoClienteAbreviado(
										new Integer(emitirContaHelper
												.getIdClienteResponsavel()));

						if (enderecoClienteResponsavel != null) {
							msg.append("ENTREGAR EM ");
							msg.append(Util.completaString(
									enderecoClienteResponsavel, 50));

							// Mensagem 1
							mensagem[0] = msg2.toString();

							// Mensagem 2
							mensagem[1] = msg.toString();

						}
					} else {
						// Mensagem 2
						mensagem[1] = msg2.toString();

						// Mensagem 3
						mensagem[2] = "";

					}
				}
			}

			// Mensagem 4
			// Date dataVencimentoConta =
			// emitirContaHelper.getDataVencimentoConta();
			// Date dataSubtraida = null;
			//
			// mensagem[3] = Util.formatarData(dataVencimentoConta);
			//
			// for (int i = 0; i < 5; i++) {
			//
			// boolean naoEhValida = true;
			//
			// while(naoEhValida){
			//
			// if(dataSubtraida == null){
			// dataSubtraida =
			// Util.adicionarNumeroDiasDeUmaData(dataVencimentoConta,-2);
			// }else{
			// dataSubtraida =
			// Util.adicionarNumeroDiasDeUmaData(dataSubtraida,-2);
			// }
			//
			// // Não considera os feriados municipais
			// if(Util.ehDiaUtil(dataSubtraida,colecaoNacionalFeriado,null)){
			// mensagem[3] = Util.formatarData(dataSubtraida)+" "+mensagem[3];
			// naoEhValida = false;
			// }else{
			// dataSubtraida =
			// Util.adicionarNumeroDiasDeUmaData(dataSubtraida,+1);
			// }
			// }
			// }

			Object[] mensagensConta = null;

			// recupera o id do grupo de faturamento da conta
			Integer idFaturamentoGrupo = emitirContaHelper
					.getIdFaturamentoGrupo();
			// recupera o id da gerencia regional da conta
			Integer idGerenciaRegional = emitirContaHelper
					.getIdGerenciaRegional();
			// recupera o id da localidade da conta
			Integer idLocalidade = emitirContaHelper.getIdLocalidade();
			// recupera o id do setor comercial da conta
			Integer idSetorComercial = emitirContaHelper.getIdSetorComercial();

			// caso entre em alguma condição então não entra mais nas outras
			boolean achou = false;

			try {
				// o sistema obtem a mensagem para a conta
				// Caso seja a condição 1
				// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
				// Localidade=parmConta, SetorComercial=parmConta)
				mensagensConta = repositorioFaturamento
						.pesquisarParmsContaMensagem(emitirContaHelper, null,
								idGerenciaRegional, idLocalidade,
								idSetorComercial);

				if (mensagensConta != null) {

					// Mensagem 3
					if (mensagensConta[0] != null) {
						mensagem[2] = (String) mensagensConta[0];
					} else {
						mensagem[2] = "";
					}

					// Mensagem 4
					if (mensagensConta[1] != null) {
						mensagem[3] = (String) mensagensConta[1];
					} else {
						mensagem[3] = "";
					}

					// Mensagem 5
					if (mensagensConta[2] != null) {
						mensagem[4] = (String) mensagensConta[2];
					} else {
						mensagem[4] = "";
					}
					achou = true;
				}

				if (!achou) {

					// Caso seja a condição 2
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, idGerenciaRegional, idLocalidade,
									null);

					if (mensagensConta != null) {

						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 5
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condição 3
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, idGerenciaRegional, null, null);

					if (mensagensConta != null) {

						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 5
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condição 4
					// (FaturamentoGrupo =parmConta, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									idFaturamentoGrupo, null, null, null);

					if (mensagensConta != null) {

						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 5
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condição 5
					// (FaturamentoGrupo =null, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, null, null, null);
					if (mensagensConta != null) {
						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 4
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				// caso não tenha entrado em nenhuma das opções acima
				// então completa a string com espaçõs em branco
				if (!achou) {
					mensagem[2] = "";
					mensagem[3] = "";
					mensagem[4] = "";
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}

		return mensagem;
	}

	

	/**
	 * [UC0651] Inserir Comando de Negativação [FS0031] – Verificar existência
	 * de conta em nome do cliente
	 * 
	 * Pesquisa os relacionamentos entre cliente e conta.
	 * 
	 * @author Vivianne Sousa
	 * @date 29/12/2010
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarSeExisteClienteConta(Integer idCliente,
			Collection colecaoContasIds) throws ControladorException {
		try {

			boolean existeClienteConta = false;

			Collection colecaoClienteConta = repositorioFaturamento
					.verificarSeExisteClienteConta(idCliente, colecaoContasIds);

			if (colecaoClienteConta != null && !colecaoClienteConta.isEmpty()) {
				existeClienteConta = true;
			}

			return existeClienteConta;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Inserir Débitos para as contas impressas via Impressão Simultânea de
	 * Contas que sairam com o valor da conta errada (Alguns grupos com tarifa
	 * proporcional que não estava levando em consideração a quantidade de
	 * economias)
	 * 
	 * @author Sávio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasComValorFaixasErradas(Integer amreferencia)
			throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarContasComValorFaixasErradas(amreferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Inserir Débitos para as contas impressas via Impressão Simultânea de
	 * Contas que sairam com o valor da conta errada (Alguns grupos com tarifa
	 * proporcional que não estava levando em consideração a quantidade de
	 * economias)
	 * 
	 * @author Sávio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirDebitosContasComValorFaixasErradas(Integer amreferencia,
			Usuario usuarioLogado) throws ControladorException {

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		DocumentoTipo documentoTipo = new DocumentoTipo();
		documentoTipo.setId(6);

		FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
		financiamentoTipo.setId(1);

		DebitoTipo debitoTipo = new DebitoTipo();
		debitoTipo.setId(22);

		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
		lancamentoItemContabil.setId(6);

		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(1);

		DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
		debitoCreditoSituacaoAtual.setId(0);

		Collection colecaoContasValorFaixas = null;

		try {

			colecaoContasValorFaixas = repositorioFaturamento
					.pesquisarContasComValorFaixasErradas(amreferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoContasValorFaixas != null
				&& !colecaoContasValorFaixas.isEmpty()) {

			Iterator ite = colecaoContasValorFaixas.iterator();
			while (ite.hasNext()) {
				Object[] dadosConta = (Object[]) ite.next();
				Integer idImovel = null;
				Integer referenciaConta = null;
				BigDecimal valorDiferenca = null;
				if (dadosConta != null) {
					if (dadosConta[0] != null) {
						idImovel = (Integer) dadosConta[0];
					}
					if (dadosConta[1] != null) {
						referenciaConta = (Integer) dadosConta[1];
					}

					if (dadosConta[2] != null) {
						valorDiferenca = (BigDecimal) dadosConta[2];
					}

					System.out
							.println("Matricula do imóvel Faixas:" + idImovel);

					// Pesquisa o imovel na base
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, idImovel));

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("localidade");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("quadra");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

					Collection<Imovel> imovelPesquisado = getControladorUtil()
							.pesquisar(filtroImovel, Imovel.class.getName());

					Imovel imovel = (Imovel) Util
							.retonarObjetoDeColecao(imovelPesquisado);

					if (imovel != null && !imovel.equals("")) {

						DebitoACobrar debitoACobrarInserir = new DebitoACobrar(
								new Date(), referenciaConta,
								sistemaParametro.getAnoMesFaturamento(),
								valorDiferenca, new Short("1"), new Short("0"),
								imovel.getSetorComercial().getCodigo(), imovel
										.getQuadra().getNumeroQuadra(),
								imovel.getLote(), imovel.getSubLote(),
								new Date(), Util.formataAnoMes(new Date()),
								BigDecimal.ZERO, imovel, documentoTipo, null,
								financiamentoTipo, null, imovel.getQuadra(),
								imovel.getLocalidade(), debitoTipo, null,
								lancamentoItemContabil, null,
								debitoCreditoSituacaoAtual, null,
								cobrancaForma, usuarioLogado, null);

						this.inserirDebitoACobrar(1, debitoACobrarInserir,
								valorDiferenca, imovel, null, null,
								usuarioLogado, true);

						// limpa os campos
						imovelPesquisado = null;
						imovel = null;
						debitoACobrarInserir = null;
						filtroImovel = null;

					}
				}

				// Limpa os campos
				dadosConta = null;
			}
		}

		System.out
				.println("FIM DO PROCESSAMENTO DAS CONTAS COM VALOR DAS FAIXAS ERRADAS.");

	}

	/**
	 * [RM-4643 (COMPESA)] Verificamos se o imóvel sofreu alterações depois de
	 * ter sido mandado para o GSAN a primeira vez
	 * 
	 * @author Bruno Barros
	 * @date 14/12/2010
	 * 
	 * @param anoMes
	 * @param idImovel
	 * @param tipoMedicao
	 * @param leitura
	 * @param idAnormalidade
	 * @return
	 * @throws ControladorException
	 */
	public boolean reprocessarImovelImpressaoSimultanea(Integer anoMes,
			Integer idImovel, Short tipoMedicao, Integer leitura,
			Integer idAnormalidade, Short icImpresso)
			throws ControladorException {
		try {
			return repositorioFaturamento.reprocessarImovelImpressaoSimultanea(
					anoMes, idImovel, tipoMedicao, leitura, idAnormalidade,
					icImpresso);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0048] – Verificar existência da conta.
	 * 
	 * @author Mariana Victor
	 * @date 27/01/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaAnoMesImovel(Integer idImovel,
			int anoMesReferencia) throws ControladorException {
		try {

			return repositorioFaturamento.pesquisarContaAnoMesImovel(idImovel,
					anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0146] Manter Conta [FS0037]-Verificar ocorrências mesmo motivo no ano
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisaQtdeContaEContaHistoricoRetificadaMotivo(
			Integer idMotivo, Integer idImovel) throws ControladorException {

		try {
			Integer retorno = 0;
			// data corrente menos 1 ano
			Date dataLimite = new Date();
			dataLimite = Util.subtrairNumeroAnosDeUmaData(dataLimite, 1);

			Integer qtdeConta = repositorioFaturamento
					.pesquisaQtdeContaRetificadaMotivo(idMotivo, idImovel,
							dataLimite);

			Integer qtdeContaHistorico = repositorioFaturamento
					.pesquisaQtdeContaHistoricoRetificadaMotivo(idMotivo,
							idImovel, dataLimite);

			retorno = qtdeConta + qtdeContaHistorico;

			return retorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaTabelaColunaContaMotivoRetificacaoColuna(
			Integer idMotivo) throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisaTabelaColunaContaMotivoRetificacaoColuna(idMotivo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
	 * 
	 * @author Hugo Leonardo
	 * @date 10/03/2011
	 * 
	 * @param FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper
	 * 
	 * @return Collection<RelatorioDevolucaoPagamentosDuplicidadeHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioDevolucaoPagamentosDuplicidade(
			FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper)
			throws ControladorException {

		Collection colecaoRetorno = new ArrayList();
		try {

			Collection colecaoSolicitacaoAcesso = this.repositorioFaturamento
					.pesquisarRelatorioDevolucaoPagamentosDuplicidade(helper);

			Iterator iterator = colecaoSolicitacaoAcesso.iterator();

			String idRaAnterior = "";
			HashMap mapValorCreditoARealizar = null;

			while (iterator.hasNext()) {

				RelatorioDevolucaoPagamentosDuplicidadeHelper relatorioHelper = new RelatorioDevolucaoPagamentosDuplicidadeHelper();

				Object[] objeto = (Object[]) iterator.next();

				// ID Gerencia
				String idGerencia = "";
				if (objeto[9] != null) {
					idGerencia = objeto[9].toString();
				}

				relatorioHelper.setIdGerencia(idGerencia);

				// Nome Gerencia
				String nomeGerencia = "";
				if (objeto[10] != null) {
					nomeGerencia = objeto[10].toString();
				}

				relatorioHelper.setNomeGerencia(nomeGerencia);

				// ID Unidade
				String idUnidade = "";
				if (objeto[11] != null) {
					idUnidade = objeto[11].toString();
				}

				relatorioHelper.setIdUnidade(idUnidade);

				// Nome Unidade
				String nomeUnidade = "";
				if (objeto[12] != null) {
					nomeUnidade = objeto[12].toString();
				}

				relatorioHelper.setNomeUnidade(nomeUnidade);

				// ID Localidade
				String idLocalidade = "";
				if (objeto[0] != null) {
					idLocalidade = objeto[0].toString();
				}

				relatorioHelper.setIdLocalidade(idLocalidade);

				// Nome Localidade
				String nomeLocalidade = "";
				if (objeto[8] != null) {
					nomeLocalidade += " " + objeto[8].toString();
				}

				relatorioHelper.setNomeLocalidade(nomeLocalidade);

				// Registro Atendimento
				String RA = "";
				if (objeto[1] != null) {
					RA = objeto[1].toString();
				}

				relatorioHelper.setNumeroRA(RA);

				// Imóvel
				String imovel = "";
				if (objeto[2] != null) {
					imovel = Util
							.retornaMatriculaImovelFormatada((Integer) objeto[2]);
				}

				relatorioHelper.setMatricula(imovel);

				// AnoMes Referência Pagamento
				String mesAnoReferenciaPagamento = "";
				if (objeto[3] != null) {
					mesAnoReferenciaPagamento = Util
							.formatarAnoMesParaMesAno(objeto[3].toString());
				}

				relatorioHelper
						.setMesAnoPagamentoDuplicidade(mesAnoReferenciaPagamento);

				// Valor Pagamento
				BigDecimal valorPagamento = new BigDecimal(0.0);
				if (objeto[4] != null) {
					valorPagamento = valorPagamento.add((BigDecimal) objeto[4]);
				}

				relatorioHelper.setValorPagamentoDuplicidade(valorPagamento);

				// AnoMes Referência Conta
				String mesAnoReferenciaConta = "";
				if (objeto[5] != null) {
					mesAnoReferenciaConta = Util
							.formatarAnoMesParaMesAno(objeto[5].toString());
				}

				relatorioHelper.setMesAnoConta(mesAnoReferenciaConta);

				// Valor Conta Original
				BigDecimal valorContaOriginal = new BigDecimal(0.0);
				if (objeto[6] != null) {
					valorContaOriginal = valorContaOriginal
							.add((BigDecimal) objeto[6]);
				}

				relatorioHelper.setValorConta(valorContaOriginal);

				// Valor Credito Realizado
				BigDecimal valorCreditoRealizado = new BigDecimal(0.0);

				if (objeto[2] != null && objeto[3] != null) {

					Integer anoMes = (Integer) objeto[3];

					Collection<CreditoRealizado> colecaoCreditoRealizado = this.repositorioFaturamento
							.pesquisarCreditosRealizado((Integer) objeto[2],
									anoMes);

					if (colecaoCreditoRealizado != null
							&& !colecaoCreditoRealizado.isEmpty()) {
						Iterator itera = colecaoCreditoRealizado.iterator();
						while (itera.hasNext()) {
							CreditoRealizado creditoRealizado = (CreditoRealizado) itera
									.next();

							valorCreditoRealizado = valorCreditoRealizado
									.add(creditoRealizado.getValorCredito());
						}
					}

					Collection<CreditoRealizadoHistorico> colecaoCreditoRealizadoHistorico = this.repositorioFaturamento
							.pesquisarCreditosRealizadoHistorico(
									(Integer) objeto[2], anoMes);

					if (colecaoCreditoRealizadoHistorico != null
							&& !colecaoCreditoRealizadoHistorico.isEmpty()) {
						Iterator itera = colecaoCreditoRealizadoHistorico
								.iterator();
						while (itera.hasNext()) {
							CreditoRealizadoHistorico creditoRealizado = (CreditoRealizadoHistorico) itera
									.next();

							valorCreditoRealizado = valorCreditoRealizado
									.add(creditoRealizado.getValorCredito());
						}
					}

					if (!idRaAnterior.equals(relatorioHelper.getNumeroRA())) {
						mapValorCreditoARealizar = new HashMap();
					}

					BigDecimal valorCredito = this.repositorioFaturamento
							.pesquisarValorCreditosARealizar(
									(Integer) objeto[2], anoMes);

					BigDecimal valorCreditoHistorico = this.repositorioFaturamento
							.pesquisarValorCreditosARealizarHistorico(
									(Integer) objeto[2], anoMes);

					BigDecimal valorCreditoARealizar = BigDecimal.ZERO;

					if (valorCredito != null && valorCreditoHistorico != null) {
						valorCreditoARealizar = valorCreditoARealizar
								.add(valorCredito);
						valorCreditoARealizar = valorCreditoARealizar
								.add(valorCreditoHistorico);
					} else if (valorCredito != null) {
						valorCreditoARealizar = valorCreditoARealizar
								.add(valorCredito);
					} else if (valorCreditoHistorico != null) {
						valorCreditoARealizar = valorCreditoARealizar
								.add(valorCreditoHistorico);
					}

					if (!mapValorCreditoARealizar.containsKey(anoMes)) {
						mapValorCreditoARealizar.put(anoMes,
								valorCreditoARealizar);
					}

				}

				idRaAnterior = relatorioHelper.getNumeroRA();

				// Credito Realizado
				relatorioHelper.setCreditoRealizado(valorCreditoRealizado);

				// Credito a Realizar
				BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
				if (mapValorCreditoARealizar != null
						&& !mapValorCreditoARealizar.isEmpty()) {
					Iterator itera = mapValorCreditoARealizar.values()
							.iterator();
					while (itera.hasNext()) {
						BigDecimal valor = (BigDecimal) itera.next();
						valorCreditoARealizar = valorCreditoARealizar
								.add(valor);
					}

				}
				relatorioHelper.setCreditoARealizar(valorCreditoARealizar);

				// Data Atualizacao
				String dataAtualizacao = "";
				if (objeto[7] != null) {
					dataAtualizacao = Util.formatarData((Date) objeto[7]);
				}
				relatorioHelper.setDataAtualizacao(dataAtualizacao);

				colecaoRetorno.add(relatorioHelper);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colecaoRetorno;
	}

	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social [SB0003]
	 * Excluir Comando Selecionado
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @exception ErroRepositorioException
	 */
	public Integer pesquisarQtdeContaNaoPaga(Collection idContas)
			throws ControladorException {

		try {
			return repositorioFaturamento.pesquisarQtdeContaNaoPaga(idContas);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param identificacaoCodigoBarras
	 * @param valorPagamento
	 * @return Conta
	 * @throws ControladorException
	 */
	public Conta pesquisarContaTipoBoleto(Integer identificacaoCodigoBarras,
			BigDecimal valorPagamento) throws ControladorException {

		Conta conta = null;

		try {

			conta = this.repositorioFaturamento
					.pesquisarExistenciaContaPorNumeroBoleto(identificacaoCodigoBarras);

			if (conta == null) {

				conta = this.repositorioFaturamento
						.pesquisarExistenciaContaPorIdentificadorEValor(
								identificacaoCodigoBarras, valorPagamento);

				if (conta == null) {

					conta = this.repositorioFaturamento
							.pesquisarExistenciaContaPorIdentificadorTruncadoEValor(
									identificacaoCodigoBarras, valorPagamento);
				}
			}

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}

		return conta;
	}

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param identificacaoCodigoBarras
	 * @param valorPagamento
	 * @return Conta
	 * @throws ControladorException
	 */
	public ContaHistorico pesquisarContaHistoricoTipoBoleto(
			Integer identificacaoCodigoBarras, BigDecimal valorPagamento)
			throws ControladorException {

		ContaHistorico conta = null;

		try {

			conta = this.repositorioFaturamento
					.pesquisarExistenciaContaHistoricoPorNumeroBoleto(identificacaoCodigoBarras);

			if (conta == null) {

				conta = this.repositorioFaturamento
						.pesquisarExistenciaContaHistoricoPorIdentificadorEValor(
								identificacaoCodigoBarras, valorPagamento);

				if (conta == null) {

					conta = this.repositorioFaturamento
							.pesquisarExistenciaContaHistoricoPorIdentificadorTruncadoEValor(
									identificacaoCodigoBarras, valorPagamento);
				}
			}

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}

		return conta;
	}

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 * 
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa(Integer idRota,
			Integer anoMes, Integer idLeituristaNovo)
			throws ControladorException {
		try {
			this.repositorioFaturamento
					.alterarLeituristaMovimentoRoteiroEmpresa(idRota, anoMes,
							idLeituristaNovo);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 * 
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa(
			Collection<Integer> idsImovel, Integer anoMes,
			Integer idLeituristaNovo) throws ControladorException {
		try {
			this.repositorioFaturamento
					.alterarLeituristaMovimentoRoteiroEmpresa(idsImovel,
							anoMes, idLeituristaNovo);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarTxtImpressaoContasBraille(int idFuncionalidadeIniciada)
			throws ControladorException {

		BufferedWriter out = null;
		ZipOutputStream zos = null;
		File leitura = null;
		Date dataAtual = new Date();
		String nomeZip = null;
		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.LOCALIDADE, 0);

			SistemaParametro sistemaParametro = repositorioUtil
					.pesquisarParametrosDoSistema();
			Collection colecaoContasBraille = pesquisarContaBraille(sistemaParametro
					.getAnoMesFaturamento());

			if (colecaoContasBraille != null && !colecaoContasBraille.isEmpty()) {

				for (int tipoConta = 6; tipoConta < 10; tipoConta++) {
					// Gerar txt do emitir contas
					emitirContas(sistemaParametro.getAnoMesFaturamento(), null,
							0, tipoConta, null, ConstantesSistema.NAO);
				}

				nomeZip = "CONTAS_BRAILLE_" + Util.formatarData(dataAtual)
						+ Util.formatarHoraSemDataSemDoisPontos(dataAtual);

				// Definindo arquivo para escrita
				nomeZip = nomeZip.replace("/", "_");
				File compactado = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeZip + ".zip");
				leitura = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeZip + ".txt");

				zos = new ZipOutputStream(new FileOutputStream(compactado));
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));

				// pegar o arquivo, zipar pasta e arquivo e escrever no stream
				System.out.println("***************************************");
				System.out.println("INICO DA CRIACAO DO ARQUIVO");
				System.out.println("QTD DE CONTAS:"
						+ colecaoContasBraille.size());
				System.out.println("***************************************");

				Iterator iterContas = colecaoContasBraille.iterator();
				while (iterContas.hasNext()) {

					Object[] dadosConta = (Object[]) iterContas.next();

					Conta conta = (Conta) dadosConta[0];
					EmitirContaHelper emitirContaHelper = (EmitirContaHelper) dadosConta[1];

					Integer idImovel = conta.getImovel().getId();

					StringBuilder contaTxt = new StringBuilder();
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"COMPESA", 10));

					String nomeClienteUsuario = null;
					if (emitirContaHelper.getNomeImovel() != null
							&& !emitirContaHelper.getNomeImovel().equals("")) {
						nomeClienteUsuario = emitirContaHelper.getNomeImovel();
					} else {
						nomeClienteUsuario = this
								.obterNomeCliente(emitirContaHelper
										.getIdConta());
					}
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Nome do cliente = " + nomeClienteUsuario,
									70));

					// UC0085 - Obter Endereço
					String enderecoImovel = getControladorEndereco()
							.pesquisarEnderecoFormatado(idImovel);
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Endereço = " + enderecoImovel, 130));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Matrícula do imóvel = " + idImovel, 30));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Mês/Ano da conta = "
											+ conta.getReferenciaFormatada(),
									26));

					String leituraAnterior = "";
					if (conta.getNumeroLeituraAnterior() != null) {
						leituraAnterior = conta.getNumeroLeituraAnterior()
								.toString();
					}
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Leitura anterior = " + leituraAnterior, 25));
					String leituraAtual = "";
					if (conta.getNumeroLeituraAtual() != null) {
						leituraAtual = conta.getNumeroLeituraAtual().toString();
					}
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Leitura atual = " + leituraAtual, 25));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Volume faturado de água (m3) = "
											+ conta.getConsumoAgua(), 35));

					// Dias de Consumo
					Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
					Integer tipoMedicao = parmSituacao[1];

					Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
							emitirContaHelper, tipoMedicao);
					String dataLeituraAnterior = "";
					String dataLeituraAtual = "";
					if (parmsMedicaoHistorico != null) {
						if (parmsMedicaoHistorico[3] != null) {
							dataLeituraAnterior = Util
									.formatarData((Date) parmsMedicaoHistorico[3]);
						}

						if (parmsMedicaoHistorico[2] != null) {
							dataLeituraAtual = Util
									.formatarData((Date) parmsMedicaoHistorico[2]);
						}
					}
					String diasConsumo = "";
					if (!dataLeituraAnterior.equals("")
							&& !dataLeituraAtual.equals("")) {
						// calcula a quantidade de dias de consumo que é a
						// quantidade de dias entre a data de leitura
						// anterior e a data de leitura atual
						diasConsumo = ""
								+ Util.obterQuantidadeDiasEntreDuasDatas(
										(Date) parmsMedicaoHistorico[3],
										(Date) parmsMedicaoHistorico[2]);
					}
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Dias de consumo = " + diasConsumo, 20));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Data de vencimento = "
											+ Util.formatarData(conta
													.getDataVencimentoConta()),
									31));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Valor a pagar = "
											+ Util.formatarMoedaReal(conta
													.getValorTotalContaBigDecimal()),
									30));

					Collection colecaoCategorias = repositorioFaturamento
							.obterQuantidadeEconomiasContaCategoria(conta
									.getId());
					String residencial = "Categoria Residencial = ";
					String comercial = "Categoria Comercial = ";
					String industrial = "Categoria Industrial  = ";
					String publica = "Categoria Pública  = ";

					Iterator iterCategorias = colecaoCategorias.iterator();
					while (iterCategorias.hasNext()) {
						Object[] economiaCategoria = (Object[]) iterCategorias
								.next();

						Short qtdeEconomia = (Short) economiaCategoria[0];
						Integer idCategoria = (Integer) economiaCategoria[1];

						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							residencial = residencial + qtdeEconomia;
						}
						if (idCategoria.equals(Categoria.COMERCIAL)) {
							comercial = comercial + qtdeEconomia;
						}
						if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							industrial = industrial + qtdeEconomia;
						}
						if (idCategoria.equals(Categoria.PUBLICO)) {
							publica = publica + qtdeEconomia;
						}
					}
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									residencial, 30));
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									comercial, 30));
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									industrial, 30));
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									publica, 30));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Valor Água = "
											+ Util.formatarMoedaReal(conta
													.getValorAgua()), 30));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Valor Esgoto = "
											+ Util.formatarMoedaReal(conta
													.getValorEsgoto()), 30));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Valor Débitos = "
											+ Util.formatarMoedaReal(conta
													.getDebitos()), 30));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Valor Créditos  = "
											+ Util.formatarMoedaReal(conta
													.getValorCreditos()), 30));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Valor Impostos =  "
											+ Util.formatarMoedaReal(conta
													.getValorImposto()), 30));

					// SB0005-Obter Mensagem da Conta em 3 Partes
					String msg = "";
					Object[] mensagemConta = obterMensagemConta3Partes(
							emitirContaHelper, sistemaParametro);
					if (mensagemConta != null && !mensagemConta.equals("")) {
						msg = msg + (String) mensagemConta[0];
						msg = msg + (String) mensagemConta[1];
						msg = msg + (String) mensagemConta[2];
					}
					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									msg, 310));

					contaTxt.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									"Contato = Compesa: 08000810195 - www.compesa.com.br - ARPE: 08002813844",
									70));

					contaTxt.append(System.getProperty("line.separator"));
					out.write(contaTxt.toString());

				}

				out.flush();

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		} finally {
			try {
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			} catch (IOException e) {
				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
						idUnidadeIniciada, true);
				throw new EJBException(e);
			}
		}

	}

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 */
	public Collection pesquisarContaBraille(Integer anoMesFaturamento)
			throws ControladorException {

		try {
			Collection colecaoRetorno = null;

			Collection colecaoContasBraille = repositorioFaturamento
					.pesquisarContaBraille(anoMesFaturamento);

			if (colecaoContasBraille != null && !colecaoContasBraille.isEmpty()) {
				colecaoRetorno = new ArrayList();
				Iterator iterContas = colecaoContasBraille.iterator();

				while (iterContas.hasNext()) {
					Object[] retorno = new Object[2];
					Object[] objeto = (Object[]) iterContas.next();

					// criado helper para pesquisa de msg da conta, nome
					// Cliente,Dias de Consumo
					EmitirContaHelper emitirContaHelper = new EmitirContaHelper();

					Integer idConta = (Integer) objeto[0];
					Conta conta = repositorioFaturamento
							.obterObjetoConta(idConta);

					if (objeto[1] != null) {
						emitirContaHelper.setNomeImovel((String) objeto[1]);
					}
					if (objeto[2] != null) {
						emitirContaHelper
								.setIdGerenciaRegional((Integer) objeto[2]);
					}
					if (objeto[3] != null) {
						emitirContaHelper
								.setIdSetorComercial((Integer) objeto[3]);
					}

					emitirContaHelper.setIdImovel(conta.getImovel().getId());
					emitirContaHelper.setAmReferencia(conta.getReferencia());
					emitirContaHelper.setIdConta(conta.getId());
					emitirContaHelper.setIdLigacaoAguaSituacao(conta
							.getLigacaoAguaSituacao().getId());
					emitirContaHelper.setIdLigacaoEsgotoSituacao(conta
							.getLigacaoEsgotoSituacao().getId());
					emitirContaHelper.setIdImovelPerfil(conta.getImovelPerfil()
							.getId());
					emitirContaHelper.setIdFaturamentoGrupo(conta
							.getFaturamentoGrupo().getId());
					emitirContaHelper.setIdLocalidade(conta.getLocalidade()
							.getId());

					retorno[0] = conta;
					retorno[1] = emitirContaHelper;
					colecaoRetorno.add(retorno);

				}
			}

			return colecaoRetorno;
		} catch (Exception e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille [FS0001] –
	 * Verificar Grupos Faturados
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoFaturamentoGrupoNaoFaturados(
			Integer anoMesReferenciaFaturamento) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarGrupoFaturamentoGrupoNaoFaturados(anoMesReferenciaFaturamento);

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}

	}

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 22/11/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarContaDoImovelDiferentePreFaturada(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarContaDoImovelDiferentePreFaturada(idImovel,
							anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Integer pesquisarFaturamentoGrupoImovel(Integer idImovel)
			throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarFaturamentoGrupoImovel(idImovel);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}

	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param conta
	 * @param consumoAguaMovimentoCelular
	 * @param consumoAguaGSAN
	 * @param consumoEsgotoMovimentoCelular
	 * @param consumoEsgotoGSAN
	 * @throws ControladorException
	 */
	public void atualizarConsumoMovimentoCelular(Conta conta,
			Integer consumoAguaMovimentoCelular, Integer consumoAguaGSAN,
			Integer consumoEsgotoMovimentoCelular, Integer consumoEsgotoGSAN)
					throws ControladorException {

		if (conta != null && conta.getId() != null) {

			// Verifica se o imóvel está associado a um imóvel condomínio
			Integer idImovelCondominio = this.getControladorImovel()
					.pesquisarImovelCondominio(conta.getImovel().getId());

			// ÁGUA
			if (consumoAguaMovimentoCelular.intValue() != consumoAguaGSAN.intValue()) {

				MovimentoContaPrefaturada movimentoContaPrefaturadaAgua = null;

				try {
					movimentoContaPrefaturadaAgua = repositorioFaturamento.pesquisarMovimentoContaPrefaturadaPorIdConta(conta.getId(), MedicaoTipo.LIGACAO_AGUA);
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}

				if (movimentoContaPrefaturadaAgua != null) {

					try {

						Integer idConsumoHistoricoAguaMacro = null;
						Integer consumoImovelVinculadosCondominioAgua = null;

						if (movimentoContaPrefaturadaAgua.getConsumoRateioAgua() != null) {

							if (idImovelCondominio != null) {

								Object[] dadosConsumoHistoricoAguaCondominio = this.getControladorMicromedicao()
										.obterConsumoLigacaoAguaOuEsgotoDoImovel(idImovelCondominio, conta.getReferencia(), LigacaoTipo.LIGACAO_AGUA);

								if (dadosConsumoHistoricoAguaCondominio != null) {

									// id do consumo historico do imóvel macro
									idConsumoHistoricoAguaMacro = (Integer) dadosConsumoHistoricoAguaCondominio[0];

									consumoImovelVinculadosCondominioAgua = movimentoContaPrefaturadaAgua.getConsumoMedido();

									if (consumoImovelVinculadosCondominioAgua == null && movimentoContaPrefaturadaAgua.getConsumoCobrado() != null) {

										consumoImovelVinculadosCondominioAgua = movimentoContaPrefaturadaAgua.getConsumoCobrado() 
												- movimentoContaPrefaturadaAgua.getConsumoRateioAgua();
									}
								}
							}
						}

						repositorioFaturamento.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaAgua);

						repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(movimentoContaPrefaturadaAgua,
								consumoAguaMovimentoCelular, idConsumoHistoricoAguaMacro,
								consumoImovelVinculadosCondominioAgua);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}

			// ESGOTO
			if (consumoEsgotoMovimentoCelular.intValue() != consumoEsgotoGSAN.intValue()) {

				MovimentoContaPrefaturada movimentoContaPrefaturadaEsgoto = null;

				try {

					movimentoContaPrefaturadaEsgoto = repositorioFaturamento
							.pesquisarMovimentoContaPrefaturadaPorIdConta(conta.getId(), MedicaoTipo.POCO);

				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}

				if (movimentoContaPrefaturadaEsgoto != null) {

					try {

						Integer idConsumoHistoricoEsgotoMacro = null;
						Integer consumoImovelVinculadosCondominioEsgoto = null;

						if (movimentoContaPrefaturadaEsgoto.getConsumoRateioEsgoto() != null) {

							if (idImovelCondominio != null) {

								Object[] dadosConsumoHistoricoEsgotoCondominio = this.getControladorMicromedicao()
										.obterConsumoLigacaoAguaOuEsgotoDoImovel(idImovelCondominio,
												conta.getReferencia(), LigacaoTipo.LIGACAO_ESGOTO);

								if (dadosConsumoHistoricoEsgotoCondominio != null) {

									// id do consumo historico do imóvel macro
									idConsumoHistoricoEsgotoMacro = (Integer) dadosConsumoHistoricoEsgotoCondominio[0];

									consumoImovelVinculadosCondominioEsgoto = movimentoContaPrefaturadaEsgoto
											.getConsumoMedido();

									if (consumoImovelVinculadosCondominioEsgoto == null
											&& movimentoContaPrefaturadaEsgoto.getConsumoCobrado() != null) {

										consumoImovelVinculadosCondominioEsgoto = movimentoContaPrefaturadaEsgoto
												.getConsumoCobrado()
												- movimentoContaPrefaturadaEsgoto.getConsumoRateioEsgoto();
									}
								}
							}
						}

						repositorioFaturamento
								.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaEsgoto);

						repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(
								movimentoContaPrefaturadaEsgoto, consumoEsgotoMovimentoCelular,
								idConsumoHistoricoEsgotoMacro, consumoImovelVinculadosCondominioEsgoto);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}
		}
	}

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual [SB0001] Pesquisar
	 * Tarifa Social ou Tarifa Mínima
	 * 
	 * Método que vai retornar um Helper que possui o consumo da tarifa mínima e
	 * da tarifa social e seus respectivos valores.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/07/2011
	 * 
	 * @param idCategoria
	 * 
	 * @return Collection<ConsultarEstruturaTarifariaPortalHelper>
	 */
	public ArrayList<ConsultarEstruturaTarifariaPortalHelper> pesquisarEstruturaTarifaria(
			Integer idCategoria) throws ControladorException {
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> estruturaTarifaria = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();

		try {
			Collection<Object[]> dados;
			// A tarifa social só é válida para imóveis da categoria
			// residencial.
			// ------------------- Pesquisa a Tarifa Social
			// -------------------//
			if (Categoria.RESIDENCIAL == idCategoria) {
				dados = this.repositorioFaturamento
						.pesquisarTarifaSocialOuTarifaMinima(
								ConsumoTarifa.CONSUMO_SOCIAL, idCategoria);
				estruturaTarifaria = this.adicionarEstruturaTarifaria(
						estruturaTarifaria, dados, false, true);
			}

			// ------------------- Pesquisa a Tarifa Normal (Mínima)
			// -------------------//
			dados = this.repositorioFaturamento
					.pesquisarTarifaSocialOuTarifaMinima(
							ConsumoTarifa.CONSUMO_NORMAL, idCategoria);
			estruturaTarifaria = this.adicionarEstruturaTarifaria(
					estruturaTarifaria, dados, false, false);

			// ------------------- Pesquisa a Tarifa Normal
			// -------------------//
			dados = this.repositorioFaturamento.pesquisarTarifaNormal(
					ConsumoTarifa.CONSUMO_NORMAL, idCategoria);
			estruturaTarifaria = this.adicionarEstruturaTarifaria(
					estruturaTarifaria, dados, true, false);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return (ArrayList<ConsultarEstruturaTarifariaPortalHelper>) estruturaTarifaria;
	}

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual Método auxiliar para
	 * montar a estrutura tarifária da loja virtual.
	 * 
	 * Método que vai retornar um Helper que possui o consumo não medido de
	 * chafariz público.
	 * 
	 * @author Diogo Peixoto
	 * @since 06/09/2011
	 * 
	 * @return ConsultarEstruturaTarifariaPortalHelper
	 */
	public ConsultarEstruturaTarifariaPortalHelper pesquisarEstruturaTarifariaChafarizPublico()
			throws ControladorException {
		ConsultarEstruturaTarifariaPortalHelper estruturaTarifaria = null;
		Collection<Object[]> dados;
		try {
			dados = this.repositorioFaturamento
					.pesquisarEstruturaTarifariaChafarizPublico();
			if (!Util.isVazioOrNulo(dados)) {
				Object[] tarifaChafariz = dados.iterator().next();

				String consumo = "";
				if (tarifaChafariz[0] != null) {
					consumo = (String) tarifaChafariz[0];
				}

				String valor = "";
				if (tarifaChafariz[1] != null) {
					valor = Util
							.formatarMoedaReal((BigDecimal) tarifaChafariz[1]);
				}

				String categoria = "";
				if (tarifaChafariz[2] != null) {
					categoria = (String) tarifaChafariz[2];
				}
				estruturaTarifaria = new ConsultarEstruturaTarifariaPortalHelper(
						categoria, consumo, valor, 2);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return estruturaTarifaria;
	}

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual. Método auxiliar para
	 * montar a estrutura tarifária da loja virtual.
	 * 
	 * @param ConsultarEstruturaTarifariaPortalHelper
	 *            - Helper que vai ser acrescentado novas estruturas
	 * @param Collection
	 *            <Object[]> - Coleção de objetos que retornou da busca no
	 *            repositório
	 * @param tarifaNormal
	 *            - Booleano que vai indicar se a tarifa é normal ou tarifa
	 *            mínima
	 * @param tarifaSocial
	 *            - Booleano que vai indicar se a tarifa é social (Apenas para a
	 *            categoria de imóvel residencial)
	 * 
	 */
	private ArrayList<ConsultarEstruturaTarifariaPortalHelper> adicionarEstruturaTarifaria(
			ArrayList<ConsultarEstruturaTarifariaPortalHelper> estrutura,
			Collection<Object[]> dados, boolean tarifaNormal,
			boolean tarifaSocial) {
		StringBuilder sb = new StringBuilder();
		String valor = null;
		Object[] object;
		// Verifica se a consulta retornou algum registro (Tarifa Social)
		if (!Util.isVazioOrNulo(dados)) {
			Iterator<Object[]> iterator = dados.iterator();

			if (tarifaNormal) {
				/*
				 * Adiciona todos os registros encontrados na coleção do helper.
				 * A primeira coluna que retorna da consulta é o valor incial do
				 * consumo a segunda coluna é o valor final do consumo e a
				 * terceira coluna é o valor do tarifa. E a terceira a categoria
				 * da tarifa.
				 */
				while (iterator.hasNext()) {
					object = iterator.next();

					/*
					 * A String é concatenada com o '.000', pois a consulta
					 * retorna o valor em 1000L Ex: Consulta retornou 10 no
					 * valor incial do consumo 10 significa que são 10.000L.
					 */
					sb.append((String) object[0]);
					sb.append(".000 a ");
					sb.append((String) object[1]);
					sb.append(".000 litros");

					if (object[2] != null) {
						valor = Util.formatarMoedaReal((BigDecimal) object[2]);
					}
					/*
					 * O último parâmetro é um integer (1), pois esse número irá
					 * auxiliar na montagem do relatório da estrutura tarifária.
					 * Esse índice indica que são consumidores medidos. No
					 * ExibirConsultarEstruturaTarifariaPortalAction serão
					 * inicializados os consumidores não medidos cuja costante é
					 * igual à 2.
					 */
					estrutura.add(new ConsultarEstruturaTarifariaPortalHelper(
							(String) object[3], sb.toString(), valor, 1));
					sb = new StringBuilder();
				}
			} else {
				/*
				 * Adiciona todos os registros encontrados na coleção do helper.
				 * A primeira coluna que retorna da consulta é o consumo e a
				 * segunda coluna é o valor do tarifa. E a terceira a categoria
				 * da tarifa.
				 */
				while (iterator.hasNext()) {
					object = iterator.next();

					/*
					 * A String é concatenada com o '.000', pois a consulta
					 * retorna o valor em 1000L Ex: Consulta retornou 10 no
					 * valor incial do consumo 10 significa que são 10.000L.
					 */
					if (tarifaSocial) {
						sb.append("Tarifa Social ");
					}
					sb.append("Até ");
					sb.append((String) object[0]);
					sb.append(".000 litros/mês");
					if (object[1] != null) {
						valor = Util.formatarMoedaReal((BigDecimal) object[1]);
					}

					/*
					 * O último parâmetro é um integer (1), pois esse número irá
					 * auxiliar na montagem do relatório da estrutura tarifária.
					 * Esse índice indica que são consumidores medidos. No
					 * ExibirConsultarEstruturaTarifariaPortalAction serão
					 * inicializados os consumidores não medidos cuja costante é
					 * igual à 2.
					 */
					estrutura.add(new ConsultarEstruturaTarifariaPortalHelper(
							(String) object[2], sb.toString(), valor, 1));
				}
			}
		} else {
			estrutura.add(new ConsultarEstruturaTarifariaPortalHelper("", "",
					"", null));
		}
		return estrutura;
	}

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual Método auxiliar para
	 * montar a estrutura tarifária da loja virtual.
	 * 
	 * @param ConsultarEstruturaTarifariaPortalHelper
	 *            - Helper que vai ser acrescentado novas estruturas
	 * @param Collection
	 *            <Object[]> - Coleção de objetos que retornou da busca no
	 *            repositório de imóvel residencial)
	 * 
	 */
	private ArrayList<ConsultarEstruturaTarifariaPortalHelper> adicionarEstruturaTarifariaAguaBruta(
			ArrayList<ConsultarEstruturaTarifariaPortalHelper> estrutura,
			Collection<Object[]> dados) {
		StringBuilder sb = new StringBuilder();
		String valor = null;
		Object[] object;
		/*
		 * Booleano responsável por verificar se a estrutura tarifaria é a
		 * primeira da categoria Caso seja a primeira, Adicionar a descrição da
		 * categoria object[3], caso contrário adicionar a string vazia para a
		 * descrição.
		 */
		boolean primeiroDaCategoria = true;

		// Verifica se a consulta retornou algum registro
		if (!Util.isVazioOrNulo(dados)) {
			Iterator<Object[]> iterator = dados.iterator();

			/*
			 * Adiciona todos os registros encontrados na coleção do helper. A
			 * primeira coluna que retorna da consulta é o consumo e a segunda
			 * coluna é o valor do tarifa. E a terceira a categoria da tarifa.
			 */
			while (iterator.hasNext()) {
				object = iterator.next();

				sb.append("entre ");
				sb.append((String) object[0]);
				sb.append(".000");
				sb.append(" e ");
				sb.append((String) object[1]);
				sb.append(".000 litros");

				if (object[2] != null) {
					valor = Util.formatarMoedaReal((BigDecimal) object[2]);
				}

				if (primeiroDaCategoria) {
					/*
					 * O último parâmetro é um integer (3), pois esse número irá
					 * auxiliar na montagem do relatório da estrutura tarifária.
					 * Esse índice indica que são tarifas do tipo água bruta.
					 */
					estrutura.add(new ConsultarEstruturaTarifariaPortalHelper(
							(String) object[3], sb.toString(), valor, 3));
					primeiroDaCategoria = false;
				} else {
					estrutura.add(new ConsultarEstruturaTarifariaPortalHelper(
							"", sb.toString(), valor, 3));
				}
				sb = new StringBuilder();
			}
		} else {
			estrutura.add(new ConsultarEstruturaTarifariaPortalHelper("", "",
					"", null));
		}
		return estrutura;
	}

	/**
	 * UC 8xx - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */

	public Collection obterColecaoGrupoFaturamento()
			throws ControladorException {
		Collection colecaoQuery = new ArrayList();
		try {
			colecaoQuery = repositorioFaturamento
					.obterColecaoGrupoFaturamento();
		}

		catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		// Monta os objetos da coleção retornada.
		Collection retorno = new ArrayList();
		Iterator it = colecaoQuery.iterator();
		FaturamentoGrupo fr = null;
		Object[] objGroup = null;
		while (it.hasNext()) {

			fr = new FaturamentoGrupo();
			objGroup = (Object[]) it.next();
			fr.setId((Integer) objGroup[0]);
			fr.setDescricao((String) objGroup[1]);

			// Adiciona o objeto ao retorno
			retorno.add(fr);

		}

		return retorno;
	}

	/**
	 * UC1198 - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ControladorException
	 */

	public Collection pesquisarDadosRelatorioAutoInfracaoPendentes(
			Integer grupo, Integer funcionario) throws ControladorException {
		Collection retornoQuery = new ArrayList();

		try {
			retornoQuery = repositorioFaturamento
					.pesquisarDadosRelatorioAutoInfracaoPendentes(grupo,
							funcionario);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		Collection<RelatorioMultasAutosInfracaoPendentesBean> colecaoBeans = new ArrayList<RelatorioMultasAutosInfracaoPendentesBean>();
		Iterator it = retornoQuery.iterator();

		ArrayList debitosACobrar = new ArrayList();

		while (it.hasNext()) {
			RelatorioMultasAutosInfracaoPendentesBean bean = new RelatorioMultasAutosInfracaoPendentesBean();
			Object[] obj = (Object[]) it.next();

			bean.setIdGrupoFaturamento((String) obj[0]);
			bean.setGrupoFaturamento((String) obj[1]);
			bean.setIdFuncionario((String) obj[2]);
			bean.setNomeFuncionario((String) obj[2] + " - " + (String) obj[3]);
			bean.setIdLocalidade((String) obj[4]);
			bean.setNomeLocalidade((String) obj[5]);
			bean.setRota((String) obj[6]);
			bean.setMatriculaImovel((String) obj[7]);
			bean.setNomeCliente((String) obj[9]);
			bean.setAutoInfracao((String) obj[10]);
			bean.setDescricaoServico((String) obj[11]);
			bean.setDataAutuacao(Util.formatarData((Date) obj[12]));
			String enderecoClienteResponsavel = "";
			enderecoClienteResponsavel = this.getControladorEndereco()
					.pesquisarEnderecoClienteAbreviado(
							new Integer((String) obj[8]));
			bean.setEndereco(enderecoClienteResponsavel);

			colecaoBeans.add(bean);

			Integer idDebACobrar = (Integer) obj[13];
			boolean verificarIPDC = verificarItensParcelamentos(idDebACobrar);
			if (verificarIPDC)
				debitosACobrar.add(bean);

		}

		return colecaoBeans;

	}

	/**
	 * UC1198 - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ControladorException
	 */
	private boolean verificarItensParcelamentos(Integer idDebitoACobrar)
			throws ControladorException {
		boolean retorno = false;
		try {

			// Verificar para cada item de parcelamento
			Collection collItensParcelamentosN1 = repositorioFaturamento
					.pesquisarItensParcelamentosNivel1(idDebitoACobrar);

			// Foi parcelado
			if (collItensParcelamentosN1 != null
					&& !collItensParcelamentosN1.isEmpty()) {
				Iterator it = collItensParcelamentosN1.iterator();
				while (it.hasNext()) {
					Integer dadosItensParcelamento = (Integer) it.next();

					// Parcelamento não pago
					if (dadosItensParcelamento != null) {
						Integer idConta = (Integer) dadosItensParcelamento;

						Collection<Object[]> collItensParcelamentosN2 = repositorioFaturamento
								.pesquisarItensParcelamentosNivel2(idConta);

						// Foi re-parcelado
						if (collItensParcelamentosN2 != null
								&& !collItensParcelamentosN2.isEmpty()) {
							Iterator it2 = collItensParcelamentosN2.iterator();
							while (it2.hasNext()) {
								Integer dadosItensParcelamentoN2 = (Integer) it2
										.next();

								// Re-parcelamento não pago
								if (dadosItensParcelamentoN2 != null) {
									return true;
								}
							}
						}

						// Não é re-parcelamento
						else
							return true;

					}
				}
			}

			// Não é parcelamento
			else
				return true;

			return retorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado
	 * 
	 * @author Vivianne Sousa
	 * @date 23/08/2011
	 */
	public void suspenderLeituraParaImovelComHidrometroRetirado(
			Integer idFuncionalidadeIniciada, Integer referenciaFaturamento,
			Integer grupofaturamento, Integer idRota)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA, idRota);

			Integer idAnormalidade = LeituraAnormalidade.HIDROMETRO_RETIRADO;

			// ANO_MES_FINAL = referenciaFaturamento + +
			// ltan_nnmesesleiturasuspensa
			// (da tabela leitura_anormalidade com ltan_id = 2)
			LeituraAnormalidade ltan = this.repositorioFaturamento
					.obterNumeroMesesLeituraSuspensaLeituraAnormalidade(idAnormalidade);
			Integer anoMesFaturamentoSituacaoFim = Util
					.somaMesAnoMesReferencia(referenciaFaturamento,
							ltan.getNumeroMesesLeituraSuspensa());
			Integer qtdAnormalidades = ltan.getNumeroVezesSuspendeLeitura();

			// FATURAMENTO_SITUACAO_MOTIVO
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoSituacaoMotivo
					.setId(FaturamentoSituacaoMotivo.IMOVEL_MEDIDO_COM_HIDROMETRO_RETIRADO);

			// FATURAMENTO_SITUACAO_TIPO
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			faturamentoSituacaoTipo
					.setId(FaturamentoSituacaoTipo.PARALISAR_LEITURA_FATURAR_MEDIA);

			// OBSERVAÇÃO
			String observacao = "INCLUIDO ATRAVES DE ROTINA BATCH POR ANORMALIDADE DE LEITURA = HIDROMETRO RETIRADO";

			// [SB0001] – Selecionar Imóveis com Hidrômetro Retirado
			Collection idsImovel = this.repositorioFaturamento
					.pesquisarImovelNumeroDeOcorrenciasConsecultivasAnormalidades(
							idAnormalidade, qtdAnormalidades,
							referenciaFaturamento, grupofaturamento, idRota);

			Iterator iterimoveis = idsImovel.iterator();
			while (iterimoveis.hasNext()) {
				// Para cada imóvel selecionado o sistema inclui o imóvel na
				// situação especial de faturamento
				// [SB0002 – Incluir Imóvel na Situação Especial de Faturamento]

				Integer idImovel = (Integer) iterimoveis.next();
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();
				faturamentoSituacaoHistorico.setImovel(imovel);
				faturamentoSituacaoHistorico
						.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				faturamentoSituacaoHistorico
						.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);
				faturamentoSituacaoHistorico.setUsuario(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico
						.setUsuarioInforma(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico.setObservacaoInforma(observacao);
				faturamentoSituacaoHistorico
						.setAnoMesFaturamentoSituacaoInicio(referenciaFaturamento);
				faturamentoSituacaoHistorico
						.setAnoMesFaturamentoSituacaoFim(anoMesFaturamentoSituacaoFim);
				faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());

				this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
				this.repositorioImovel
						.atualizarSituacaoEspecialFaturamentoImovel(idImovel,
								faturamentoSituacaoTipo.getId(),
								faturamentoSituacaoMotivo.getId());
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}

	/**
	 * [UC1218] Suspender Leitura para Imóvel com Consumo Real não Superior a
	 * 10m3
	 * 
	 * @author Vivianne Sousa
	 * @date 26/08/2011
	 */
	public void suspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(
			Integer idFuncionalidadeIniciada, Integer referenciaFaturamento,
			Integer grupofaturamento, Integer idRota)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA, idRota);

			SistemaParametro sistemaParametro = this.getControladorUtil()
					.pesquisarParametrosDoSistema();
			Integer qtdConsumoRealNaoSuperiorA10 = sistemaParametro
					.getNumeroVezesSuspendeLeitura();
			Integer numeroMesesReinicioSitEspFaturamento = sistemaParametro
					.getNumeroMesesReinicioSitEspFaturamento();

			// ANO_MES_FINAL = referenciaFaturamento + parm_mesesleiturasuspensa
			// (da tabela sistema_parametros)
			Integer anoMesFaturamentoSituacaoFim = Util
					.somaMesAnoMesReferencia(referenciaFaturamento,
							sistemaParametro.getNumeroMesesLeituraSuspensa());

			// FATURAMENTO_SITUACAO_MOTIVO
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoSituacaoMotivo
					.setId(FaturamentoSituacaoMotivo.IMOVEL_COM_CONSUMO_MENOR_IGUAL_10M3_VARIOS_MESES);

			// FATURAMENTO_SITUACAO_TIPO
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			faturamentoSituacaoTipo
					.setId(FaturamentoSituacaoTipo.PARALISAR_LEITURA_FATURAR_TAXA_MINIMA);

			// OBSERVAÇÃO
			String observacao = "INCLUIDO ATRAVES DE ROTINA BATCH POR APRESENTAR CONSUMO NÃO SUPERIOR A 10M3";

			// [SB0001] – Selecionar Imóveis com Hidrômetro Retirado
			Collection idsImovel = this.repositorioFaturamento
					.pesquisarImovelComConsumoRealNaoSuperiorA10(
							qtdConsumoRealNaoSuperiorA10,
							referenciaFaturamento, grupofaturamento, idRota,
							numeroMesesReinicioSitEspFaturamento);

			Iterator iterimoveis = idsImovel.iterator();
			while (iterimoveis.hasNext()) {
				// Para cada imóvel selecionado o sistema inclui o imóvel na
				// situação especial de faturamento
				// [SB0004] – Incluir Imóvel na Situação Especial de Faturamento

				Integer idImovel = (Integer) iterimoveis.next();
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();
				faturamentoSituacaoHistorico.setImovel(imovel);
				faturamentoSituacaoHistorico
						.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				faturamentoSituacaoHistorico
						.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);
				faturamentoSituacaoHistorico.setUsuario(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico
						.setUsuarioInforma(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico.setObservacaoInforma(observacao);
				faturamentoSituacaoHistorico
						.setAnoMesFaturamentoSituacaoInicio(referenciaFaturamento);
				faturamentoSituacaoHistorico
						.setAnoMesFaturamentoSituacaoFim(anoMesFaturamentoSituacaoFim);
				faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());

				this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
				this.repositorioImovel
						.atualizarSituacaoEspecialFaturamentoImovel(idImovel,
								faturamentoSituacaoTipo.getId(),
								faturamentoSituacaoMotivo.getId());
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}

	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual [SB0001] Pesquisar
	 * Tarifa Social ou Tarifa Mínima
	 * 
	 * Método que vai retornar um Helper que possui o consumo da tarifa mínima e
	 * da tarifa social e seus respectivos valores.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/07/2011
	 * 
	 * @param idCategoria
	 * 
	 * @return Collection<ConsultarEstruturaTarifariaPortalHelper>
	 */
	public ArrayList<ConsultarEstruturaTarifariaPortalHelper> pesquisarEstruturaTarifariaAguaBruta(
			Integer idCategoria) throws ControladorException {
		ArrayList<ConsultarEstruturaTarifariaPortalHelper> estruturaTarifaria = new ArrayList<ConsultarEstruturaTarifariaPortalHelper>();

		try {
			Collection<Object[]> dados;
			dados = this.repositorioFaturamento.pesquisarTarifaNormal(
					ConsumoTarifa.AGUA_BRUTA_ADUTORA, idCategoria);
			estruturaTarifaria = this.adicionarEstruturaTarifariaAguaBruta(
					estruturaTarifaria, dados);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return (ArrayList<ConsultarEstruturaTarifariaPortalHelper>) estruturaTarifaria;
	}



	
	/**
	 * 
	 * [UC1083] Prescrever Débitos de Imóveis Públicos Automático
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 */
	public Collection obterImoveisComContaPF(Integer anoMesReferencia,
			Rota rota) throws ControladorException{
		
		try {		
			return repositorioFaturamento.obterImoveisComContaPF(anoMesReferencia, rota);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/*
	 * 
	 * Método criado para obter os imóveis de uma rota que possuem conta
	 */
	
	 /** @author Pamela Gatinho  
	 * @date 21/06/2011
	 * 
	 * @return Collection Dados dos imóveis com conta
	 * @param anoMesReferencia
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Collection obterImoveisComConta(Integer anoMesReferencia,
			Rota rota) throws ControladorException {
		
		try {		
			return repositorioFaturamento.obterImoveisComConta(anoMesReferencia, rota);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * 
	 * Pamela Gatinho - 15/09/2011
	 * 
	 * Gerar dados para o relatorio de contas retidas
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
	public Collection<RelatorioContasRetidasHelper> pesquisarDadosRelatorioContasRetidas(
			int anoMesReferencia, Integer idFaturamentoGrupo) throws ControladorException {
		
		Collection<RelatorioContasRetidasHelper> retorno = new ArrayList<RelatorioContasRetidasHelper>();
		
		try {
			Collection colecaoObjeto = repositorioFaturamento.pesquisarDadosRelatorioContasRetidas(anoMesReferencia, idFaturamentoGrupo);
			
			if(colecaoObjeto != null && !colecaoObjeto.isEmpty()){
				RelatorioContasRetidasHelper relatorioLeiturasContasRetidas = null;
				Iterator iterator = colecaoObjeto.iterator();
				
				while(iterator.hasNext()){
					
					relatorioLeiturasContasRetidas = new RelatorioContasRetidasHelper();
					Object[] obj = (Object[]) iterator.next();
					
					if(obj[0] != null){
						relatorioLeiturasContasRetidas.setAnoMesReferencia((String) obj[0]);
					}
					
					if(obj[1] != null){
						relatorioLeiturasContasRetidas.setUnidadeDeNegocio((String) obj[1]);
					}
					
					if(obj[2] != null){
						relatorioLeiturasContasRetidas.setGrupo((String) obj[2]);
					}
					
					if(obj[3] != null){
						relatorioLeiturasContasRetidas.setQtdContasRetidas((Integer) obj[3]);
					}
					
					relatorioLeiturasContasRetidas.setAnoMesReferencia(Util.formatarAnoMesParaMesAnoSemBarra(anoMesReferencia));
					retorno.add(relatorioLeiturasContasRetidas);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * 
	 * Pamela Gatinho - 16/09/2011
	 * 
	 * Gerar dados para o relatorio de medicao do faturamento
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param idEmpresa
	 * @param tipoRelatorio
	 * @param usuarioLogado
	 * 
	 * @return
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosRelatorioMedicaoFaturamento(
			int anoMesReferencia, Integer idFaturamentoGrupo, Integer idEmpresa) throws ControladorException {
		
		Collection retorno = new ArrayList();
		try {
			Collection colecaoObjeto = repositorioFaturamento
					.pesquisarDadosRelatorioMedicaoFaturamento(anoMesReferencia, idFaturamentoGrupo, idEmpresa);
			
			if (colecaoObjeto != null && !colecaoObjeto.isEmpty()) {
				Iterator iterator = colecaoObjeto.iterator();

				while (iterator.hasNext()) {
					RelatorioMedicaoFaturamentoHelper relatorioMedicaoFaturamento = new RelatorioMedicaoFaturamentoHelper();
					Object[] objeto = (Object[]) iterator.next();
					
					// referencia
					if (objeto[0] != null) {
						relatorioMedicaoFaturamento.setAnoMesReferencia((String) objeto[0]);
					}

					// grupo
					if (objeto[1] != null) {
						relatorioMedicaoFaturamento.setGrupo((String) objeto[1]);
					}
					
					// unidade de negocio
					if (objeto[3] != null) {
						relatorioMedicaoFaturamento.setUnidadeDeNegocio((String) objeto[3]);
					}
					
					// empresa
					if (objeto[4] != null) {
						relatorioMedicaoFaturamento.setEmpresa((String) objeto[4]);
					}
					
					// lidas e impressas
					if (objeto[5] != null) {
						relatorioMedicaoFaturamento.setQtdContasLidasEImpressas((Integer) objeto[5]);
					}
					
					// lidas
					if (objeto[6] != null) {
						relatorioMedicaoFaturamento.setQtdContasSoLidas((Integer) objeto[6]);
					}
					
					// impressas
					if (objeto[7] != null) {
						relatorioMedicaoFaturamento.setQtdContasSoImpressas((Integer) objeto[7]);
					}
					
					relatorioMedicaoFaturamento
							.setAnoMesReferencia(Util
									.formatarAnoMesParaMesAnoSemBarra(anoMesReferencia));

					retorno.add(relatorioMedicaoFaturamento);

				}

			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
	
	/**
	 * @author Adriana Muniz e Wellington Rocha
	 * @date 30/08/2012
	 * 
	 * @param creditoARealizar
	 * @param creditoRealizado
	 */
	public void atualizarCreditoRealizadoCategoria(CreditoARealizar creditoARealizar, 
			CreditoRealizado creditoRealizado) {
		try {
			// Pesquisa os créditos a realizar categoria
			Collection colecaoCreditoARealizarCategoria = this
					.obterCreditoRealizarCategoria(creditoARealizar
							.getId());
			
			Iterator colecaoCreditoARealizarCategoriaIterator = colecaoCreditoARealizarCategoria
					.iterator();

			Collection colecaoCategorias = new ArrayList();

			// Laço para recuperar as categorias do crédito a realizar
			while (colecaoCreditoARealizarCategoriaIterator.hasNext()) {
				CreditoARealizarCategoria creditoARealizarCategoria = (CreditoARealizarCategoria) colecaoCreditoARealizarCategoriaIterator.next();
				Categoria categoria = new Categoria();
				categoria.setId(creditoARealizarCategoria.getCategoria().getId());
				categoria.setQuantidadeEconomiasCategoria(creditoARealizarCategoria.getQuantidadeEconomia());
				colecaoCategorias.add(categoria);
			}
			
			//obter o valor do credito pra cada categoria
			Collection colecaoCategoriasEValores = 
					this.obterValorPorCategoria(colecaoCategorias, creditoRealizado.getValorCredito());
			
			Collection creditosRealizadosCategoria = 
					repositorioFaturamento.pesquisarCreditoRealizadoCategoria(creditoRealizado.getId());
			
			Iterator creditosRealizadosCategoriaIterator = creditosRealizadosCategoria.iterator();
			
			while(creditosRealizadosCategoriaIterator.hasNext()) {
				
				CreditoRealizadoCategoria creditoRealizadoCategoria = (CreditoRealizadoCategoria)creditosRealizadosCategoriaIterator.next();
				
				Iterator colecaoCategoriasEValoresIterator = colecaoCategoriasEValores.iterator();
				while(colecaoCategoriasEValoresIterator.hasNext()) {
					Object[] categoriaEValor = (Object[])colecaoCategoriasEValoresIterator.next();
					
					if(categoriaEValor[0].equals(creditoRealizadoCategoria.getCategoria().getId())) {
						creditoRealizadoCategoria.setValorCategoria((BigDecimal)categoriaEValor[1]);
						creditoRealizadoCategoria.setUltimaAlteracao(new Date());
					}
				}
				//atualiza o credito realizado categoria
				getControladorUtil().atualizar(creditoRealizadoCategoria);
			}
			
		} catch (ControladorException e) {
			e.printStackTrace();
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Adriana Muniz e Wellington Rocha
	 * @date 30/08/2012
	 * 
	 * Rateia um determinado valore entre as categorias do imóvel
	 *
	 * @param colecaoCategorias 
	 * @param valor 
	 * @return Coleção de vetores com a posição 
	 * [0] = id da Categoria
	 * [1] = valor por categoria
	 * 
	 */
	public Collection obterValorPorCategoria(
	Collection<Categoria> colecaoCategorias, BigDecimal valor) {
		Collection colecaoValoresPorCategoria = new ArrayList();
	
		//acuama a quantidae de ecnomias das acategorias
		int somatorioQuantidadeEconomiasCadaCategoria = 0;
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
		
			while (iteratorColecaoCategorias.hasNext()) {
				Categoria categoria = (Categoria) iteratorColecaoCategorias.next();
				somatorioQuantidadeEconomiasCadaCategoria = somatorioQuantidadeEconomiasCadaCategoria
				+ categoria.getQuantidadeEconomiasCategoria().intValue();
			}
	
		}
	
	//	 calcula o fator de multiplicação
		BigDecimal fatorMultiplicacao = valor.divide(
		new BigDecimal(somatorioQuantidadeEconomiasCadaCategoria),2,BigDecimal.ROUND_DOWN);
	
		BigDecimal valorPorCategoriaAcumulado = new BigDecimal(0);
	
	//	 para cada categoria, calcula o Valor por Cageoria
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
		
			while (iteratorColecaoCategorias.hasNext()) {
				Categoria categoria = (Categoria) iteratorColecaoCategorias.next();
				
				Object[] valorPorCategoriaVetor = new Object[2];
			
				BigDecimal valorPorCategoria = new BigDecimal(0);
			
				valorPorCategoria = fatorMultiplicacao.multiply(new BigDecimal(
				categoria.getQuantidadeEconomiasCategoria()));
			
				BigDecimal valorTruncado = valorPorCategoria.setScale(2, BigDecimal.ROUND_DOWN);
			
				valorPorCategoriaAcumulado = valorPorCategoriaAcumulado.add(valorTruncado);
				
				valorPorCategoriaVetor[0] = categoria.getId();
				valorPorCategoriaVetor[1] = valorTruncado;
			
				colecaoValoresPorCategoria.add(valorPorCategoriaVetor);
			}
		}
		
		valorPorCategoriaAcumulado = valorPorCategoriaAcumulado.setScale(7);
	
		// caso o valor por categoria acumulado seja menor que o valor
		// acumula a diferença no valor da primeira categoria
		if (valorPorCategoriaAcumulado.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(
				valor.setScale(2, BigDecimal.ROUND_HALF_UP)) == -1) {
	
			BigDecimal diferenca = valor.subtract(valorPorCategoriaAcumulado);
		
			diferenca = diferenca.setScale(2, BigDecimal.ROUND_HALF_UP);
		
			Object[] categoriaPrimeira = (Object[]) colecaoValoresPorCategoria.iterator().next();
			
			BigDecimal valorDaPrimeiraCategoria = (BigDecimal) categoriaPrimeira[1];
			categoriaPrimeira[1] = valorDaPrimeiraCategoria.add(diferenca);
			
			((ArrayList)colecaoValoresPorCategoria).set(0, categoriaPrimeira);
		}
		return colecaoValoresPorCategoria;
	}
	
	private StringBuilder converterArquivoRetorno(BufferedReader buffer) {
		String linha = "";
		StringBuilder arquivoCompleto = new StringBuilder();
		do {    
        	
        	try {
				linha = buffer.readLine();
				
				arquivoCompleto.append(linha);
				arquivoCompleto.append(System.getProperty("line.separator"));
        
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        } while ( linha != null && linha.length() > 0 );
		return arquivoCompleto; 
	}
	
	private byte[] compactarArquivoRetorno(String nomeArquivo, StringBuilder arquivoTexto) throws IOException {
		byte[] retorno;
		
		File compactado = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeArquivo + ".tar.gz");
		ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();
		
		GZIPOutputStream zos = new GZIPOutputStream(new FileOutputStream(compactado));
		File leitura = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeArquivo + ".txt");

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
		
		out.write(arquivoTexto.toString());
		out.flush();
		ZipUtil.adicionarArquivo(zos, leitura);

		zos.close();

		FileInputStream inputStream = new FileInputStream(compactado);

		// Escrevemos aos poucos
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baosArquivoZip.write(temp, 0, numBytesRead);
		}

		retorno = baosArquivoZip.toByteArray();

		// Fechamos o inputStream
		inputStream.close();
		baosArquivoZip.close();

		inputStream = null;
		compactado.delete();
		leitura.delete();
		
		return retorno;
	}
	
	/*
     * 
     * 02/05/2011 - Pamela Gatinho
     * Adicionando o ID da rota como informacao para finalizar o arquivo de rota.
     */
    private void incluiDadosArquivoRetorno(ArquivoTextoRetornoIS arquivoRetornoIS, BufferedReader bufferOriginal,
    		Collection<AtualizarContaPreFaturadaHelper> colHelper, Integer idRota) throws ControladorException, 
    		ErroRepositorioException, IOException {
    	
    	if (!colHelper.isEmpty()) {
    		
    		AtualizarContaPreFaturadaHelper helper = colHelper.iterator().next();
    		Rota rota;
    		if (idRota != null) {
    			rota = this.getControladorMicromedicao()
    			.pesquisarRota(idRota);
    		} else {
    			rota = pesquisarRotaImpressaoSimultanea(helper);
    		}
    		
    		StringBuilder arquivoRetorno = this.converterArquivoRetorno(bufferOriginal);
    		
    		Localidade localidade = new Localidade();
    		localidade.setId(helper.getLocalidade());
    		
    		if (arquivoRetornoIS != null) {
    			arquivoRetornoIS.setFaturamentoGrupo(rota.getFaturamentoGrupo());
    			arquivoRetornoIS.setLocalidade(localidade);
    			arquivoRetornoIS.setArquivoTexto(arquivoRetorno.toString());

    			logger.info("Salvando arquivo retorno " + arquivoRetornoIS.getNomeArquivo() + ", conteudo vazio? " + arquivoRetorno.equals(null));
    			
    			Integer idArquivoTextoRetornoIS = (Integer) repositorioUtil.inserir(arquivoRetornoIS);
    			arquivoRetornoIS.setId(idArquivoTextoRetornoIS);
    			
    		} else {
    			
    			logger.info("Arquivo de retorno NULO...");
    			arquivoRetornoIS = new ArquivoTextoRetornoIS();
    			
    			arquivoRetornoIS.setLocalidade(localidade);
    			arquivoRetornoIS.setCodigoSetorComercial(helper.getCodigoSetorComercial());
    			arquivoRetornoIS.setCodigoRota(helper.getCodigoRota());
    			arquivoRetornoIS.setAnoMesReferencia(helper.getAnoMesFaturamento());
    		}
    		
    		this.inserirMovimentoArquivoRetornoIS(colHelper, arquivoRetornoIS, rota);
    	}
    }
    
    private Collection<MovimentoArquivoTextoRetornoIS> inserirMovimentoArquivoRetornoIS(Collection<AtualizarContaPreFaturadaHelper> colHelper, 
    		ArquivoTextoRetornoIS arquivoTextoRetornoIS, Rota rota) throws IOException, ErroRepositorioException {
    	
    	Collection<MovimentoArquivoTextoRetornoIS> colecaoMovimentos = null;
    	
    	for (AtualizarContaPreFaturadaHelper helper : colHelper) {
    		
    		if (helper.getTipoRegistro().equals(new Integer(1))) {
    			
    			MovimentoArquivoTextoRetornoIS movimento = new MovimentoArquivoTextoRetornoIS();
    			movimento.setAnoMesMovimento(helper.getAnoMesFaturamento());
    			movimento.setCodigoRota(helper.getCodigoRota());
    			movimento.setCodigoSetorComercial(helper.getCodigoSetorComercial());
    			movimento.setFaturamentoGrupo(rota.getFaturamentoGrupo());
    			movimento.setImovel(new Imovel(helper.getMatriculaImovel()));
    			movimento.setLocalidade(new Localidade(helper.getLocalidade()));
    			movimento.setMedicaoTipo(new MedicaoTipo(helper.getTipoMedicao()));
    			movimento.setTempoRetornoArquivo(new Date());
    			movimento.setUltimaAlteracao(new Date());
    			
    			
    			if (arquivoTextoRetornoIS != null && arquivoTextoRetornoIS.getId() != null) {
    				movimento.setArquivoTextoRetornoIS(arquivoTextoRetornoIS);
    			}
    			
    			if (helper.getArquivoImovel().toString() != null) {
    				movimento.setNomeArquivo(this.obterNomeArquivoRetorno(arquivoTextoRetornoIS).toString());
    				movimento.setArquivoTexto(helper.getArquivoImovel().toString());
    			}

    			if (helper.getAnormalidadeConsumo() != null) {
    				movimento.setConsumoAnormalidade(new ConsumoAnormalidade(helper.getAnormalidadeConsumo()));
    			}
    			
    			if (helper.getAnormalidadeLeitura() != null) {
    				movimento.setLeituraAnormalidade(new LeituraAnormalidade(helper.getAnormalidadeLeitura()));
    			}
    			
    			if (helper.getLeituraHidrometro() != null) {
    				movimento.setLeituraHidrometro(helper.getLeituraHidrometro());
    			}
    			
    			repositorioUtil.inserir(movimento);
    		}
    	}

    	return colecaoMovimentos;
    	
    }
    
    public StringBuilder obterNomeArquivoRetorno(ArquivoTextoRetornoIS arquivoRetorno) {
    	StringBuilder nomeArquivo = new StringBuilder();
    	
    		
    	if (arquivoRetorno.getTipoFinalizacao() != null && arquivoRetorno.getTipoFinalizacao().intValue() == 
    		ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO) {
    		nomeArquivo.append("GCOMPLETO");
    	} else {
    		nomeArquivo.append("G");
    	}
    	
    	nomeArquivo.append(Util.completaStringComZeroAEsquerda(arquivoRetorno.getLocalidade().getId().toString(), 3));
    	nomeArquivo.append(Util.completaStringComZeroAEsquerda(arquivoRetorno.getCodigoSetorComercial().toString(), 3));
    	nomeArquivo.append(Util.completaStringComZeroAEsquerda(arquivoRetorno.getCodigoRota().toString(), 3));
    	nomeArquivo.append(Util.completaString(arquivoRetorno.getAnoMesReferencia().toString(), 6));
    	nomeArquivo.append(".txt");
    			
    	return nomeArquivo;
    	
    }
    
	/**
	* 
	* @author Pamela Gatinho
	* @date 24/02/2012
	*
	* Metodo que obtem o movimento do imóvel
	* lido pelo IS
	*
	* @return MovimentoContaPreFaturada
	* @param anoMesReferencia
	* @param idImovel
	* @throws ErroRepositorioException
	*/
	public MovimentoContaPrefaturada obterMovimentoImovel(Integer idImovel, Integer anoMesReferencia) throws ControladorException{
		try {
			return repositorioFaturamento.obterMovimentoImovel(idImovel, anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * @author Pamela Gatinho
	 * @date 15/03/2013
	 * 
	 *  Método que atualiza se o Extrato de Quitação gerado para o ano
	 *  anterior foi impresso pelo Impressão Simultânea.
	 *  
	 *  @param colMovimentoContaPreFaturada
	 * @throws ControladorException 
	 */
	private void atualizarInformacoesImpressaoExtratoQuitacao(Collection<MovimentoContaPrefaturada> colMovimentoContaPreFaturada) throws ControladorException {
		System.out.println("Atualizando informações de extrato de quitação...");
		if (colMovimentoContaPreFaturada != null && !colMovimentoContaPreFaturada.isEmpty()){
			for ( MovimentoContaPrefaturada movimento : colMovimentoContaPreFaturada ){
				
				Integer anoMesAnterior = Util.subtrairAnoAnoMesReferencia(movimento.getAnoMesReferenciaPreFaturamento(), 1);
				
				Integer anoAnterior = Util.obterAno(anoMesAnterior);
				
				ExtratoQuitacao extrato = this.obterExtratoQuitacaoImovel(movimento.getImovel().getId(), anoAnterior);
				
				if (extrato != null && !extrato.getIndicadorImpressaoNaConta().equals(new Integer(movimento.getIndicadorEmissaoConta()))) {
						extrato.setIndicadorImpressaoNaConta(new Integer(movimento.getIndicadorEmissaoConta()));
						
						this.getControladorUtil().atualizar(extrato);
				}
			}
		}
		System.out.println("Fim da atualização das informações de extrato de quitação...");
	}
	
	/**
	 * Pamela Gatinho - 12/03/2013
	 * 
	 * Método que obtem a mensagem de quitação anual de
	 * débitos. Caso o imóvel não esteja quitado, retorna
	 * uma mensagem em branco.
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @param arquivoTextoRegistroTipo01
	 * @throws ControladorException
	 */
	public String obterMsgQuitacaoDebitos(Imovel imovel, Integer anoMesReferencia)
			throws ControladorException {
		
		String mensagem = "";
		
		Integer anoMesAnterior = Util.subtrairAnoAnoMesReferencia(anoMesReferencia, 1);
		
		Integer anoAnterior = Util.obterAno(anoMesAnterior);
		
		ExtratoQuitacao extratoQuitacao = this.obterExtratoQuitacaoImovel(imovel.getId(), anoAnterior);
		
		if (extratoQuitacao != null && extratoQuitacao.getIndicadorImpressaoNaConta() != null && extratoQuitacao.getIndicadorImpressaoNaConta().equals(new Integer(ConstantesSistema.NAO))) {
			mensagem = "Em cumprimento a lei 12.007/2009, declaramos quitados os débitos de consumo de água e/ou esgoto do ano de " + anoAnterior +  ".";
		} 
		return mensagem;
	}
	
	public long obterDiferencaDiasCronogramas(Integer anoMesAtual, Rota rota, Integer idFaturamentoAtividade) throws ControladorException {

		long diferencaDatas = 0;
		
		anoMesAtual = rota.getFaturamentoGrupo().getAnoMesReferencia();
		try {
			Date dataCronogramaMesAtual = this.pesquisarFaturamentoAtividadeCronogramaDataPrevista(rota.getFaturamentoGrupo().getId(),idFaturamentoAtividade, anoMesAtual);
			
			Integer anoMesAnterior = Util.subtrairMesDoAnoMes(anoMesAtual, 1);
			
			Date dataCronogramaMesAnterior = this.pesquisarFaturamentoAtividadeCronogramaDataPrevista(
					rota.getFaturamentoGrupo().getId(),idFaturamentoAtividade, anoMesAnterior);
			
			if (dataCronogramaMesAnterior != null) {
				diferencaDatas =  Util.diferencaEntreDatas(dataCronogramaMesAnterior, dataCronogramaMesAtual);
			} else {
				diferencaDatas = rota.getNumeroDiasConsumoAjuste();
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return diferencaDatas;
	
	}
	
	public void atualizarVecimentoFaturaClienteResponsavel(Date dataVencimento, String anoMesReferencia) throws ControladorException {
		try {
			repositorioFaturamento.atualizarVecimentoFaturaClienteResponsavel(dataVencimento, anoMesReferencia);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}

	public Integer countFaturasClienteResponsaveis(String anoMesReferencia) throws ControladorException {
		try {
			return repositorioFaturamento.countFaturasClienteResponsaveis(anoMesReferencia);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public Map<Integer, Conta> incluirContasParaRefaturarPagamentos(Collection<Pagamento> pagamentos, Usuario usuarioLogado) throws ControladorException, ErroRepositorioException {
		
		Map<Integer, Conta> mapNovasContas = new HashMap<Integer, Conta>();
		
		Collection<IConta> listaContaHistoricoOrigem = this.pesquisarContaOuContaHistorico(pagamentos);
		
		for (IConta contaHistorico : listaContaHistoricoOrigem) {
			try {
				Conta novaConta = this.refaturarContaParaClassificar(contaHistorico);
				mapNovasContas.put(contaHistorico.getId(), novaConta);
			} catch (Exception e) {
				throw new ControladorException("Erro ao incluir contas para pagamentos", e);
			}
		}
		
		return mapNovasContas;
	}

	
	private Conta refaturarContaParaClassificar(IConta contaOrigem) throws Exception  {
		ContaMotivoInclusao motivoInclusao = new ContaMotivoInclusao(ContaMotivoInclusao.RECUPERACAO_DE_CREDITO);
		Integer referenciaContabil = getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao();
		DebitoCreditoSituacao situacao = new DebitoCreditoSituacao(DebitoCreditoSituacao.INCLUIDA);
		
		Conta novaConta = this.copiarContaCompleta(contaOrigem, motivoInclusao, referenciaContabil, situacao);
		
		return novaConta;
	}
	
	private void copiarConsumoFaixaCategoria(IConta contaAntiga, Conta contaNova) throws Exception {
		Collection<IContaCategoriaConsumoFaixa> listaContaCategoriaConsumoFaixaOrigem = repositorioFaturamento.pesquisarContaCategoriaConsumoFaixa(contaAntiga.getId());
		listaContaCategoriaConsumoFaixaOrigem.addAll(repositorioFaturamento.pesquisarContaCategoriaConsumoFaixaHistorico(contaAntiga.getId()));
				
		for (IContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa : listaContaCategoriaConsumoFaixaOrigem) {
			IContaCategoriaConsumoFaixa novaContaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) MergeProperties.mergeInterfaceProperties(new ContaCategoriaConsumoFaixa(), contaCategoriaConsumoFaixa);
			
			IContaCategoria contaCategoria = novaContaCategoriaConsumoFaixa.getContaCategoria();
			contaCategoria.setConta(contaNova);
			novaContaCategoriaConsumoFaixa.setContaCategoria(contaCategoria);
			novaContaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novaContaCategoriaConsumoFaixa);
		}
	}
	
	private void copiarContaCategoria(IConta contaAntiga, Conta contaNova) throws Exception {
		Collection<IContaCategoria> listaContaCategoriaOrigem = repositorioFaturamento.pesquisarContaCategoria(contaAntiga.getId());
		listaContaCategoriaOrigem.addAll(repositorioFaturamento.pesquisarContaCategoriaHistorico(contaAntiga.getId()));
				
		for (IContaCategoria contaCategoria : listaContaCategoriaOrigem) {
			IContaCategoria novaContaCategoria = (ContaCategoria) MergeProperties.mergeInterfaceProperties(new ContaCategoria(), contaCategoria);
			novaContaCategoria.setConta(contaNova);
			novaContaCategoria.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novaContaCategoria);
		}
	}
	
	private void copiarDebitoCobrado(IConta contaAntiga, Conta contaNova) throws Exception {
		Collection<IDebitoCobrado> listaDebitoCobradoOrigem = repositorioFaturamento.pesquisarDebitosCobrados(contaAntiga.getId());
		listaDebitoCobradoOrigem.addAll(repositorioFaturamento.pesquisarDebitosCobradosHistorico(contaAntiga.getId()));
				
		for (IDebitoCobrado debitoCobradoAntivo : listaDebitoCobradoOrigem) {
			IDebitoCobrado novoDebitoCobrado = (DebitoCobrado) MergeProperties.mergeInterfaceProperties(new DebitoCobrado(), debitoCobradoAntivo);
			novoDebitoCobrado.setConta(contaNova);
			novoDebitoCobrado.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novoDebitoCobrado);

			this.criarDebitoCobradoCategoriaParaRecuperacaoCredito(debitoCobradoAntivo, novoDebitoCobrado);
		}
	}
	
	private void criarDebitoCobradoCategoriaParaRecuperacaoCredito(IDebitoCobrado debitoCobradoAntigo, IDebitoCobrado debitoCobradoNovo) throws Exception {
		Collection<IDebitoCobradoCategoria> listaDebitosCobradosCategoriaOrigem = repositorioFaturamento.pesquisarDebitoCobradoCategoria(debitoCobradoAntigo.getId());
		listaDebitosCobradosCategoriaOrigem.addAll(repositorioFaturamento.pesquisarDebitosCobradosCategoriaHistorico(debitoCobradoAntigo.getId()));
				
		for (IDebitoCobradoCategoria debitoCobradoCategoria : listaDebitosCobradosCategoriaOrigem) {
			IDebitoCobradoCategoria novoDebitoCobradoCategoria = (DebitoCobradoCategoria) MergeProperties.mergeInterfaceProperties(new DebitoCobradoCategoria(), debitoCobradoCategoria);
			
			novoDebitoCobradoCategoria.setDebitoCobrado((DebitoCobrado)debitoCobradoNovo);
			novoDebitoCobradoCategoria.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novoDebitoCobradoCategoria);
		}
	}
	
	private void copiarCreditoRealizado(IConta contaAntiga, Conta contaNova) throws Exception {
		Collection<ICreditoRealizado> listaCreditosOrigem = repositorioFaturamento.pesquisarCreditosRealizados(((Conta)contaAntiga).getId());
		listaCreditosOrigem.addAll(repositorioFaturamento.pesquisarCreditosRealizadosHistorico(contaAntiga.getId()));
				
		for (ICreditoRealizado creditoRealizadoAntigo : listaCreditosOrigem) {
			ICreditoRealizado novoCreditoRealizado = (CreditoRealizado) MergeProperties.mergeInterfaceProperties(new CreditoRealizado(), creditoRealizadoAntigo);
			novoCreditoRealizado.setConta(contaNova);
			novoCreditoRealizado.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novoCreditoRealizado);

			this.criarCreditoRealizadoCategoriaParaRecuperacaoCredito(creditoRealizadoAntigo, novoCreditoRealizado);
		}
	}
	
	private void criarCreditoRealizadoCategoriaParaRecuperacaoCredito(ICreditoRealizado creditoRealizadoAntigo, ICreditoRealizado creditoRealizadoNovo) throws Exception {
		Collection<ICreditoRealizadoCategoria> listaCreditosReaizadosCategoriaOrigem = repositorioFaturamento.pesquisarCreditoRealizadoCategoria(creditoRealizadoAntigo.getId());
		listaCreditosReaizadosCategoriaOrigem.addAll(repositorioFaturamento.pesquisarCreditoRealizadoCategoriaHistorico(creditoRealizadoAntigo.getId()));
				
		for (ICreditoRealizadoCategoria creditoRealizadoCategoria : listaCreditosReaizadosCategoriaOrigem) {
			ICreditoRealizadoCategoria novoCreditoRealizadoCategoria = (CreditoRealizadoCategoria) MergeProperties.mergeInterfaceProperties(new CreditoRealizadoCategoria(), creditoRealizadoCategoria);
			
			novoCreditoRealizadoCategoria.setCreditoRealizado((CreditoRealizado) creditoRealizadoNovo);
			novoCreditoRealizadoCategoria.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novoCreditoRealizadoCategoria);
		}
	}
	
	private void copiarContaImpostosDeduzidos(IConta contaAntiga, Conta contaNova) throws Exception {
		Collection<IContaImpostosDeduzidos> listaContaImpostoOrigem = repositorioFaturamento.pesquisarContaImpostosDeduzidos(contaAntiga.getId());
		listaContaImpostoOrigem.addAll(repositorioFaturamento.pesquisarContaImpostosDeduzidosHistorico(contaAntiga.getId()));
				
		for (IContaImpostosDeduzidos contaImpostoDeduzidoAntigo : listaContaImpostoOrigem) {
			IContaImpostosDeduzidos novaContaImpostosDeduzido = (ContaImpostosDeduzidos) MergeProperties.mergeInterfaceProperties(new ContaImpostosDeduzidos(), contaImpostoDeduzidoAntigo);
			novaContaImpostosDeduzido.setConta(contaNova);
			novaContaImpostosDeduzido.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novaContaImpostosDeduzido);
		}
	}
	
	private void copiarClienteConta(IConta contaAntiga, Conta contaNova) throws Exception {
		Collection<IClienteConta> listaClienteContaOrigem = repositorioFaturamento.pesquisarClienteConta(contaAntiga.getId());
		listaClienteContaOrigem.addAll(repositorioFaturamento.pesquisarClienteContaHistorico(contaAntiga.getId()));
				
		for (IClienteConta clienteContaAntigo : listaClienteContaOrigem) {
			IClienteConta novoClienteConta = (ClienteConta) MergeProperties.mergeInterfaceProperties(new ClienteConta(), clienteContaAntigo);
			novoClienteConta.setConta(contaNova);
			novoClienteConta.setUltimaAlteracao(new Date());
			
			repositorioUtil.inserir(novoClienteConta);
		}
	}
	
	private Conta copiarContaCompleta(IConta contaOrigem, ContaMotivoInclusao motivoInclusao, Integer referenciaContabil, DebitoCreditoSituacao situacao) throws Exception {
		Conta novaConta = this.copiarConta(contaOrigem);
		
		novaConta.setDataVencimentoConta(new Date());
		novaConta.setContaMotivoInclusao(motivoInclusao);
		novaConta.setReferenciaContabil(referenciaContabil);
		novaConta.setDebitoCreditoSituacaoAtual(situacao);
		novaConta.setUltimaAlteracao(new Date());
		
		repositorioUtil.inserir(novaConta);
		
		this.copiarContaCategoria(contaOrigem, novaConta);
		this.copiarDebitoCobrado(contaOrigem, novaConta);
		this.copiarCreditoRealizado(contaOrigem, novaConta);
		this.copiarClienteConta(contaOrigem, novaConta);
		this.copiarContaImpostosDeduzidos(contaOrigem, novaConta);
		this.copiarConsumoFaixaCategoria(contaOrigem, novaConta);
		
		return novaConta;
	}
	
	private Conta copiarConta(IConta contaOrigem) throws ControladorException {
		Rota rota = getControladorMicromedicao().buscarRotaDoImovel(contaOrigem.getImovel().getId());

		ContaGeral contaGeral = new ContaGeral();
		contaGeral.setIndicadorHistorico((short) 2);
		contaGeral.setUltimaAlteracao(new Date());
		contaGeral.setId((Integer) this.getControladorUtil().inserir(contaGeral));
		
		Conta novaConta = new Conta();
		MergeProperties.mergeInterfaceProperties(novaConta, contaOrigem);
		contaOrigem.buildConta(novaConta);
		
		novaConta.setId(contaGeral.getId());
		novaConta.setContaGeral(contaGeral);
		novaConta.setUltimaAlteracao(new Date());
		novaConta.setRota(rota);
		
		return novaConta;
	}
	
	public Collection<IConta> pesquisarContaOuContaHistorico(Collection<Pagamento> pagamentos) throws ControladorException{
		
		try {
			Collection<Integer> idsContas = getListaIdContas(pagamentos);
		
			Collection<IConta> listaContaOrigem = repositorioFaturamento.pesquisarContaOuContaHistorico(idsContas, ContaHistorico.class.getName());
			
			if (listaContaOrigem.size() != pagamentos.size()) {
				listaContaOrigem.addAll(repositorioFaturamento.pesquisarContaOuContaHistorico(idsContas, Conta.class.getName()));
			}
			
			return listaContaOrigem;
			
		} catch (ErroRepositorioException ex) {
			logger.error("Erro ao pesquisar conta historico para recuperacao de credito." , ex);
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Integer> getListaIdContas(Collection<Pagamento> pagamentos) {
		Collection<Integer> ids = null;
		
		if (pagamentos != null && pagamentos.size() > 0) {
			ids = new ArrayList<Integer>();
			
			for (Pagamento pagamento: pagamentos) {
				ids.add(pagamento.getContaGeral().getId());
			}
		}
		return ids;
	}
	
	public void faturarImovelSeletivo(ImovelFaturamentoSeletivo imovelFaturamentoSeletivo) throws ControladorException, ParseException {
		
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		
		LigacaoAgua ligacaoAgua = getControladorMicromedicao().obterLigacaoAgua(imovelFaturamentoSeletivo.getIdImovel());
		Imovel imovel = getControladorImovel().pesquisarImovel(imovelFaturamentoSeletivo.getIdImovel());
		
		Rota rota = getControladorMicromedicao().buscarRotaDoImovel(imovel.getId());
		
		Collection<Integer> idsImoveis = new ArrayList<Integer>();
		idsImoveis.add(imovelFaturamentoSeletivo.getIdImovel());
		
		if (ligacaoAgua != null) {
			
			this.prepararFaturamentoImovel(FaturamentoAtividade.FATURAR_GRUPO, rota, imovel.getId());
			
			MedicaoTipo medicaoTipo = new MedicaoTipo(MedicaoTipo.LIGACAO_AGUA);
			Collection colecaoResumoFaturamento = new ArrayList();
			
			FaturamentoGrupo grupo = getControladorImovel().pesquisarGrupoImovel(ligacaoAgua.getId());
			
			FaturamentoAtivCronRota cronogramaFaturamentoRota = this.pesquisarFaturamentoAtivCronRotaPara(
					rota.getId(), FaturamentoAtividade.FATURAR_GRUPO, grupo.getId(), grupo.getAnoMesReferencia()); 
			
			this.getControladorMicromedicao().consistirLeiturasCalcularConsumosImoveis(rota.getFaturamentoGrupo(), idsImoveis);
			
			this.faturarImovel(grupo.getAnoMesReferencia(), FaturamentoAtividade.FATURAR_GRUPO.intValue(), 
					sistemaParametro, cronogramaFaturamentoRota, colecaoResumoFaturamento, imovel, false, grupo);
			
			if (colecaoResumoFaturamento != null && !colecaoResumoFaturamento.isEmpty()) {
				this.inserirResumoSimulacaoFaturamento(colecaoResumoFaturamento);

				if (colecaoResumoFaturamento != null) {
					colecaoResumoFaturamento.clear();
					colecaoResumoFaturamento = null;
				}
			}

		}
	}
	
	public Conta incluirDebitoContaRetificadaPagamentosDiferenca2Reais(Integer idConta, DebitoACobrar debito) throws Exception {
		Conta novaConta = null;
		
		if (idConta != null) {
			novaConta = this.retificarContaPagamentosDiferenca2Reais(idConta);
			incluirDebitoCobradoContaRetificadaDiferenca2Reais(novaConta, debito);
			registrarTransacaoRetificacaoContaDiferenca2Reais(novaConta);
		}
		
		return novaConta;
	}
	
	public Conta incluirCreditoContaRetificadaPagamentosDiferenca2Reais(Integer idConta, CreditoARealizar credito) throws Exception {
		Conta novaConta = null;
		
		if (idConta != null) {
			novaConta = this.retificarContaPagamentosDiferenca2Reais(idConta);
			incluirCreditoRealizadoContaRetificadaDiferenca2Reais(novaConta, credito);
			registrarTransacaoRetificacaoContaDiferenca2Reais(novaConta);
		}
		
		return novaConta;
	}
	
	private Conta retificarContaPagamentosDiferenca2Reais(Integer idConta) throws Exception {
		Conta novaConta = null;
		
		if (idConta != null) {
			Conta contaOriginal  = (Conta)(this.obterConta(idConta)).iterator().next();
			novaConta = inserirContaRetificadaDiferenca2Reais(contaOriginal);
			alterarContaDiferenca2Reais(contaOriginal);
		}
		
		return novaConta;
	}
	
	private void incluirCreditoRealizadoContaRetificadaDiferenca2Reais(Conta conta, CreditoARealizar creditoARealizar) throws Exception {
		Collection<Categoria> categoriasImovel = getControladorImovel().obterQuantidadeEconomiasCategoria(conta.getImovel());
		
		Collection<CreditoRealizado> colecaoCreditoRealizado = new ArrayList<CreditoRealizado>();
		
		CreditoRealizado creditoRealizado = new CreditoRealizado();
		conta.setImovel(getControladorImovel().pesquisarImovel(conta.getImovel().getId()));
		
		creditoRealizado.setCreditoTipo(creditoARealizar.getCreditoTipo());
		creditoRealizado.setCreditoRealizado(creditoARealizar.getGeracaoCredito());
		creditoRealizado.setLancamentoItemContabil(creditoARealizar.getLancamentoItemContabil());
		creditoRealizado.setLocalidade(creditoARealizar.getLocalidade());
		creditoRealizado.setQuadra(creditoARealizar.getQuadra());
		creditoRealizado.setCodigoSetorComercial(creditoARealizar.getCodigoSetorComercial());
		creditoRealizado.setNumeroQuadra(creditoARealizar.getNumeroQuadra());
		creditoRealizado.setNumeroLote(creditoARealizar.getNumeroLote());
		creditoRealizado.setNumeroSubLote(creditoARealizar.getNumeroSubLote());
		creditoRealizado.setAnoMesReferenciaCredito(creditoARealizar.getAnoMesReferenciaCredito());
		creditoRealizado.setAnoMesCobrancaCredito(creditoARealizar.getAnoMesCobrancaCredito());
		creditoRealizado.setValorCredito(creditoARealizar.getValorCredito());
		creditoRealizado.setCreditoOrigem(creditoARealizar.getCreditoOrigem());
		creditoRealizado.setNumeroPrestacao(creditoARealizar.getNumeroPrestacaoCredito());
		creditoRealizado.setNumeroParcelaBonus(creditoARealizar.getNumeroParcelaBonus());
		creditoRealizado.setNumeroPrestacaoCredito(creditoARealizar.getNumeroPrestacaoRealizada());
		creditoRealizado.setCreditoARealizarGeral(creditoARealizar.getCreditoARealizarGeral());

		FiltroCreditoTipo filtro = new FiltroCreditoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.ID, creditoARealizar.getCreditoTipo().getId()));
		Collection<CreditoTipo> colecaoCreditoTipo = getControladorUtil().pesquisar(filtro, CreditoTipo.class.getName());
		CreditoTipo creditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colecaoCreditoTipo);
		
		
		FiltroLancamentoItemContabil filtroLancamentoItem = new FiltroLancamentoItemContabil();
		filtroLancamentoItem.adicionarParametro(new ParametroSimples(FiltroLancamentoItemContabil.ID, creditoTipo.getLancamentoItemContabil().getId()));
		Collection<LancamentoItemContabil> colecaoLancamentoItem = getControladorUtil().pesquisar(filtroLancamentoItem, LancamentoItemContabil.class.getName());
		LancamentoItemContabil lancamentoItem = (LancamentoItemContabil) Util.retonarObjetoDeColecao(colecaoLancamentoItem);
		
		creditoTipo.setLancamentoItemContabil(lancamentoItem);
		creditoRealizado.setCreditoTipo(creditoTipo);
		creditoRealizado.setLancamentoItemContabil(lancamentoItem);

		colecaoCreditoRealizado.add(creditoRealizado);
		this.inserirCreditoRealizado(conta,colecaoCreditoRealizado, conta.getImovel(), categoriasImovel);
		
		conta.setValorCreditos(conta.getValorCreditos().add(creditoRealizado.getValorCredito()));
		getControladorUtil().atualizar(conta);
		
		creditoARealizar.setNumeroPrestacaoRealizada(new Short("1"));
		getControladorUtil().atualizar(creditoARealizar);
	}
	
	private void incluirDebitoCobradoContaRetificadaDiferenca2Reais(Conta conta, DebitoACobrar debitoACobrar) throws Exception {
		Collection<Categoria> categoriasImovel = getControladorImovel().obterQuantidadeEconomiasCategoria(conta.getImovel());
		
		Collection<DebitoCobrado> colecaoDebitoCobrado = new ArrayList<DebitoCobrado>();
		
		DebitoCobrado debitoCobrado = new DebitoCobrado();

		conta.setImovel(getControladorImovel().pesquisarImovel(conta.getImovel().getId()));
		
		debitoCobrado.setDebitoTipo(debitoACobrar.getDebitoTipo());
		debitoCobrado.setDebitoCobrado(new Date());
		debitoCobrado.setConta(conta);
		debitoCobrado.setLocalidade(conta.getImovel().getLocalidade());
		debitoCobrado.setQuadra(conta.getImovel().getQuadra());
		debitoCobrado.setCodigoSetorComercial(new Integer(conta.getCodigoSetorComercial()));
		debitoCobrado.setNumeroQuadra(new Integer(conta.getImovel().getQuadra().getNumeroQuadra()));
		debitoCobrado.setNumeroLote(new Short(conta.getImovel().getLote()));
		debitoCobrado.setNumeroSubLote(new Short(conta.getImovel().getSubLote()));
		
		debitoCobrado.setDebitoACobrarGeral(debitoACobrar.getDebitoACobrarGeral());
		
		if (debitoACobrar.getAnoMesReferenciaDebito() != null) {
			debitoCobrado.setAnoMesReferenciaDebito(debitoACobrar.getAnoMesReferenciaDebito());
		}

		if (debitoACobrar.getAnoMesCobrancaDebito() != null) {
			debitoCobrado.setAnoMesCobrancaDebito(debitoACobrar.getAnoMesCobrancaDebito());
		}

		debitoCobrado.setValorPrestacao(debitoACobrar.getValorPrestacao());
		debitoCobrado.setNumeroPrestacao(new Short("1").shortValue());
		debitoCobrado.setNumeroPrestacaoDebito(new Short("1").shortValue());
		debitoCobrado.setNumeroParcelaBonus(debitoACobrar.getNumeroParcelaBonus());
		debitoCobrado.setUltimaAlteracao(new Date());
		debitoCobrado.setOperacaoEfetuada(null);
		debitoCobrado.setUsuarioAcaoUsuarioHelp(null);

		FiltroDebitoTipo filtro = new FiltroDebitoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, debitoACobrar.getDebitoTipo().getId()));
		Collection<DebitoTipo> colecaoDebitoTipo = getControladorUtil().pesquisar(filtro, DebitoTipo.class.getName());
		DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
		
		
		FiltroLancamentoItemContabil filtroLancamentoItem = new FiltroLancamentoItemContabil();
		filtroLancamentoItem.adicionarParametro(new ParametroSimples(FiltroLancamentoItemContabil.ID, debitoTipo.getLancamentoItemContabil().getId()));
		Collection<LancamentoItemContabil> colecaoLancamentoItem = getControladorUtil().pesquisar(filtroLancamentoItem, LancamentoItemContabil.class.getName());
		LancamentoItemContabil lancamentoItem = (LancamentoItemContabil) Util.retonarObjetoDeColecao(colecaoLancamentoItem);
		
		debitoTipo.setLancamentoItemContabil(lancamentoItem);
		debitoCobrado.setDebitoTipo(debitoTipo);
		debitoCobrado.setLancamentoItemContabil(lancamentoItem);

		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID, debitoACobrar.getFinanciamentoTipo().getId()));
		Collection<FinanciamentoTipo> colecaoFinanciamentoTipo = getControladorUtil().pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
		FinanciamentoTipo financiamentoTipo = (FinanciamentoTipo) Util.retonarObjetoDeColecao(colecaoFinanciamentoTipo);

		debitoCobrado.setFinanciamentoTipo(financiamentoTipo);
		
		colecaoDebitoCobrado.add(debitoCobrado);
		this.inserirDebitoCobrado(conta, colecaoDebitoCobrado, conta.getImovel(), categoriasImovel);
		
		conta.setValorDebitos(conta.getValorDebitos().add(debitoCobrado.getValorPrestacao()));
		getControladorUtil().atualizar(conta);
	}
	
	private Conta inserirContaRetificadaDiferenca2Reais(Conta contaOriginal) throws Exception{
		Conta novaConta  = null;
		DebitoCreditoSituacao situacaoOriginal = contaOriginal.getDebitoCreditoSituacaoAtual();
		DebitoCreditoSituacao novaSituacao = new DebitoCreditoSituacao(DebitoCreditoSituacao.RETIFICADA);
		ContaMotivoInclusao motivoInclusao = new ContaMotivoInclusao(ContaMotivoInclusao.NAO_INFORMADO);
		
		Integer referenciaContabil = getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao();
		
		if (!situacaoOriginal.getId().equals(DebitoCreditoSituacao.RETIFICADA) && !situacaoOriginal.getId().equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)) {
			try {
				novaConta = this.copiarContaCompleta(contaOriginal, motivoInclusao, referenciaContabil, novaSituacao);
				novaConta.setDataRetificacao((new GregorianCalendar()).getTime());
				getControladorUtil().atualizar(novaConta);
				return novaConta;
			} catch (Exception e) {
				logger.error("Erro ao retificar conta para pagamentos om diferença de R$2,00", e);
				return null;
			}
			
		} else {
			novaConta = contaOriginal;
		}
		return novaConta;
	}
	
	private Conta alterarContaDiferenca2Reais(Conta contaOriginal) throws ControladorException{
		DebitoCreditoSituacao situacaoOriginal = contaOriginal.getDebitoCreditoSituacaoAtual();
		
		if (situacaoOriginal.getId().equals(DebitoCreditoSituacao.RETIFICADA)) {
			contaOriginal.setUltimaAlteracao(new Date());
		} else if (!situacaoOriginal.getId().equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)) {
			contaOriginal.setDebitoCreditoSituacaoAnterior(situacaoOriginal);
			contaOriginal.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));
			contaOriginal.setUltimaAlteracao(new Date());
		}
		this.atualizarConta(contaOriginal);
		return contaOriginal;
	}
	
	private void registrarTransacaoRetificacaoContaDiferenca2Reais(Conta novaConta) {
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_CONTA_RETIFICAR, novaConta.getImovel()
				.getId(), novaConta.getId(), new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_BATCH, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		registradorOperacao.registrarOperacao(novaConta);
		
	}
	
	public Collection pesquisarClienteContaECliente(Integer idConta, String cnpjEmpresa) throws ControladorException {
		try{
		  return repositorioFaturamento.pesquisarClienteContaECliente(idConta, cnpjEmpresa);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	public Collection<RelatorioReceitasAFaturarHelper> pesquisarDadosRelatorioReceitasAFaturarAnalitico(Integer idGrupo, Integer anoMes) throws ControladorException {
		try {
		
			return gerarDadosRelatorioReceitasAFaturarAnalitico(idGrupo, anoMes);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	public Collection<RelatorioReceitasAFaturarPorCategoriaHelper> pesquisarDadosRelatorioReceitasAFaturarSintetico(Integer anoMes, Short indicadorCategoria) throws ControladorException {
		
		if (indicadorCategoria.shortValue() == ConstantesSistema.SIM.shortValue()) {
			return pesquisarDadosRelatorioReceitasAFaturarSinteticoPorCategoria(anoMes);
		} else {
			return pesquisarDadosRelatorioReceitasAFaturarSintetico(anoMes);
		}
	}
	
	private Collection<RelatorioReceitasAFaturarPorCategoriaHelper> pesquisarDadosRelatorioReceitasAFaturarSinteticoPorCategoria(Integer anoMes) throws ControladorException {
		Collection<RelatorioReceitasAFaturarPorCategoriaHelper> retorno = new ArrayList<RelatorioReceitasAFaturarPorCategoriaHelper>();
		
		try{
			FiltroCategoria filtro = new FiltroCategoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<Categoria> colecao = getControladorUtil().pesquisar(filtro, Categoria.class.getName());
			
			for (Categoria categoria : colecao) {
				RelatorioReceitasAFaturarPorCategoriaHelper receitaCategoria = new RelatorioReceitasAFaturarPorCategoriaHelper();
				
				receitaCategoria.setDescricaoCategoria(categoria.getDescricao());
				
				Collection<ReceitasAFaturarResumo> receitas = repositorioFaturamento.obterDadosRelatorioSinteticoReceitasAFaturarPorCategoria(anoMes, categoria.getId()); 
				receitaCategoria.setRelatorioReceitasAFaturarHelpers(getRelatorioReceitasAFaturarHelpers(receitas));
				
				retorno.add(receitaCategoria);
			}
		return retorno;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	private Collection<RelatorioReceitasAFaturarPorCategoriaHelper> pesquisarDadosRelatorioReceitasAFaturarSintetico(Integer anoMes) throws ControladorException {
		Collection<RelatorioReceitasAFaturarPorCategoriaHelper> retorno = new ArrayList<RelatorioReceitasAFaturarPorCategoriaHelper>();
		
		try{
			RelatorioReceitasAFaturarPorCategoriaHelper receitaCategoria = new RelatorioReceitasAFaturarPorCategoriaHelper();
			
			Collection<ReceitasAFaturarResumo> receitas = repositorioFaturamento.obterDadosRelatorioSinteticoReceitasAFaturar(anoMes); 
			receitaCategoria.setRelatorioReceitasAFaturarHelpers(getRelatorioReceitasAFaturarHelpers(receitas));
			
			retorno.add(receitaCategoria);
		return retorno;
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	private Collection<RelatorioReceitasAFaturarHelper> getRelatorioReceitasAFaturarHelpers(Collection<ReceitasAFaturarResumo> receitas) {

		Collection<RelatorioReceitasAFaturarHelper> retorno = new ArrayList<RelatorioReceitasAFaturarHelper>();
		
		for (ReceitasAFaturarResumo receita : receitas) {
			retorno.add(new RelatorioReceitasAFaturarHelper(receita));
		}
		
		return retorno;
	}
	

	public void gerarDadosReceitasAFaturarResumo(Integer anoMes, Integer idGrupo, Integer idFuncionalidadeIniciada) 
			throws ControladorException, ErroRepositorioException {
		
		int idUnidadeIniciada = 0;
		
		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
					idFuncionalidadeIniciada, UnidadeProcessamento.FATURAMENTO_GRUPO, idGrupo);
			
			this.apagarReceitasAFaturarResumoPorReferenciaEGrupo(idGrupo, anoMes);

			DataLeituraPrevistaHelper dataLeituraPrevistaHelper = DataLeituraPrevistaHelper.getListaDatasLeituraPrevistaHelperPorGrupo(
					repositorioFaturamento.pesquisarDadosRelatorioReceitasAFaturarDataLeituraPrevista(idGrupo, anoMes));
			
			DataLeituraAnteriorHelper dataLeituraAnteriorHelper = DataLeituraAnteriorHelper.getListaDatasLeituraAnteriorHelperPorGrupo(
					repositorioFaturamento.pesquisarDadosRelatorioReceitasAFaturarDataLeituraAnterior(idGrupo, anoMes));
			
			 Collection<ValorAFaturarHelper> valorAFaturarHelpers = ValorAFaturarHelper.getListaValoresAFaturarHelper(
					repositorioFaturamento.pesquisarDadosRelatorioReceitasAFaturarValorAFaturar(idGrupo, anoMes));
			
			for (ValorAFaturarHelper valorAFaturarHelper : valorAFaturarHelpers) {
				
				RelatorioReceitasAFaturarHelper helper = new RelatorioReceitasAFaturarHelper();
				helper.setIdGrupo(idGrupo);
				helper.setDataLeituraAtual(dataLeituraPrevistaHelper.getDataPrevista());
				helper.setDataLeituraAnterior(dataLeituraAnteriorHelper.getDataAnterior());
				helper.setValorAgua(valorAFaturarHelper.getValorAgua());
				helper.setValorEsgoto(valorAFaturarHelper.getValorEsgoto());
				helper.setCategoria(valorAFaturarHelper.getCategoria());

				if (helper.gerar()) {
					Integer diferencaDias = Util.obterQuantidadeDiasEntreDuasDatasPositivo(helper.getDataLeituraAtual(), helper.getDataLeituraAnterior());
					helper.setDiferencaDias(diferencaDias);			
					
					int mes = Integer.parseInt(anoMes.toString().substring(4));
					int ano = Integer.parseInt(anoMes.toString().substring(0, 4));
					Date ultimoDiaMes = Util.obterUltimaDataMes(mes, ano);
					int diasNaoFaturados = Util.obterQuantidadeDiasEntreDuasDatasPositivo(ultimoDiaMes, helper.getDataLeituraAtual());
					helper.setDiasNaoFaturados(diasNaoFaturados);
					
					BigDecimal bdDiferencaDias = new BigDecimal(diferencaDias);
					BigDecimal valorDiarioAgua = helper.getValorAgua().divide(bdDiferencaDias, 7, RoundingMode.HALF_UP);
					BigDecimal valorDiarioEsgoto = helper.getValorEsgoto().divide(bdDiferencaDias, 7, RoundingMode.HALF_UP);
					helper.setValorAguaDiario(valorDiarioAgua.setScale(2, RoundingMode.HALF_UP));
					helper.setValorEsgotoDiario(valorDiarioEsgoto.setScale(2, RoundingMode.HALF_UP));
					
					BigDecimal bdDiasNaoFaturados = new BigDecimal(diasNaoFaturados);
					helper.setValorAguaAFaturar(bdDiasNaoFaturados.multiply(valorDiarioAgua));
					helper.setValorEsgotoAFaturar(bdDiasNaoFaturados.multiply(valorDiarioEsgoto));
					
					ReceitasAFaturarResumo receitasAFaturarResumo = new ReceitasAFaturarResumo(helper);
					receitasAFaturarResumo.setAnoMesReferencia(anoMes);
					receitasAFaturarResumo.setUltimaAlteracao(new Date());
					getControladorUtil().inserir(receitasAFaturarResumo);
				}
				
				
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			System.out.println("Erro no processamento do GRUPO: " + idGrupo);
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	private void apagarReceitasAFaturarResumoPorReferenciaEGrupo(Integer idGrupo, Integer anoMes)
			throws ControladorException {
		
		FiltroReceitasAFaturarResumo filtro = new FiltroReceitasAFaturarResumo();
		filtro.adicionarParametro(new ParametroSimples(FiltroReceitasAFaturarResumo.GRUPO_ID, idGrupo));
		filtro.adicionarParametro(new ParametroSimples(FiltroReceitasAFaturarResumo.ANO_MES_REFERENCIA, anoMes));
		Collection<ReceitasAFaturarResumo> colecao = getControladorUtil().pesquisar(filtro, ReceitasAFaturarResumo.class.getName());
		
		for (ReceitasAFaturarResumo receitasAFaturarResumo : colecao) {
			getControladorUtil().remover(receitasAFaturarResumo);
		}
	}
	
	private Collection<RelatorioReceitasAFaturarHelper> gerarDadosRelatorioReceitasAFaturarAnalitico(Integer idGrupo, Integer anoMes)
			throws ErroRepositorioException {
		Collection<RelatorioReceitasAFaturarHelper> retorno = new ArrayList();

		ArrayList<DataLeituraPrevistaHelper> datasLeituraPrevista = DataLeituraPrevistaHelper
				.getListaDatasLeituraPrevistaHelper(repositorioFaturamento.pesquisarDadosRelatorioReceitasAFaturarDataLeituraPrevista(idGrupo, anoMes));
		
		ArrayList<DataLeituraAnteriorHelper> datasLeituraAnterior = DataLeituraAnteriorHelper
				.getListaDatasLeituraAnteriorHelper(repositorioFaturamento.pesquisarDadosRelatorioReceitasAFaturarDataLeituraAnterior(idGrupo, anoMes));
		
		ArrayList<ValorAFaturarHelper> valoresAFaturar = ValorAFaturarHelper.getListaValoresAFaturarHelperPorGrupo(idGrupo, repositorioFaturamento
				.pesquisarDadosRelatorioReceitasAFaturarValorAFaturarPorGrupo(idGrupo, anoMes));
		
		for (ValorAFaturarHelper valorAFaturarHelper : valoresAFaturar) {
			
			RelatorioReceitasAFaturarHelper helper = new RelatorioReceitasAFaturarHelper();
			helper.setIdGrupo(valorAFaturarHelper.getIdGrupo());
			helper.setImovel(valorAFaturarHelper.getImovel());
			helper.setNomeCliente(valorAFaturarHelper.getNomeCliente());
			helper.setValorAgua(valorAFaturarHelper.getValorAgua());
			helper.setValorEsgoto(valorAFaturarHelper.getValorEsgoto());
			helper.setDataLeituraAnterior(datasLeituraAnterior.get(0).getDataAnterior());
			helper.setDataLeituraAtual(datasLeituraPrevista.get(0).getDataPrevista());
			
			retorno.add(helper);
		}

		for (RelatorioReceitasAFaturarHelper helper : retorno) {
			Integer diferencaDias = Util.obterQuantidadeDiasEntreDuasDatasPositivo(helper.getDataLeituraAtual(), helper.getDataLeituraAnterior());
			helper.setDiferencaDias(diferencaDias);			
			
			int mes = Integer.parseInt(anoMes.toString().substring(4));
			int ano = Integer.parseInt(anoMes.toString().substring(0, 4));
			Date ultimoDiaMes = Util.obterUltimaDataMes(mes, ano);
			int diasNaoFaturados = Util.obterQuantidadeDiasEntreDuasDatasPositivo(ultimoDiaMes, helper.getDataLeituraAtual());
			helper.setDiasNaoFaturados(diasNaoFaturados);
			
			BigDecimal bdDiferencaDias = new BigDecimal(diferencaDias);
			BigDecimal valorDiarioAgua = helper.getValorAgua().divide(bdDiferencaDias, 7, RoundingMode.HALF_UP);
			BigDecimal valorDiarioEsgoto = helper.getValorEsgoto().divide(bdDiferencaDias, 7, RoundingMode.HALF_UP);
			helper.setValorAguaDiario(valorDiarioAgua.setScale(2, RoundingMode.HALF_UP));
			helper.setValorEsgotoDiario(valorDiarioEsgoto.setScale(2, RoundingMode.HALF_UP));
			
			BigDecimal bdDiasNaoFaturados = new BigDecimal(diasNaoFaturados);
			helper.setValorAguaAFaturar(bdDiasNaoFaturados.multiply(valorDiarioAgua));
			helper.setValorEsgotoAFaturar(bdDiasNaoFaturados.multiply(valorDiarioEsgoto));
		}
		
		BeanComparator fieldComparator = new BeanComparator("nomeCliente");
		Collections.sort((ArrayList)retorno, fieldComparator);
		
		return retorno;
	}
	
	public int pesquisarMaiorAnoMesReferenciaCronogramaGrupoFaturamentoMensal(Integer idGrupo) throws ControladorException {
		try{
		  return repositorioFaturamento.pesquisarMaiorAnoMesReferenciaCronogramaGrupoFaturamentoMensal(idGrupo);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	public boolean verificarAnoMesReferenciaCronogramaGrupoFaturamentoMensal(Integer idGrupo, Integer referencia) throws ControladorException {
		try{
		  return repositorioFaturamento.verificarAnoMesReferenciaCronogramaGrupoFaturamentoMensal(idGrupo, referencia);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	public Collection<ContaImpressaoTermicaQtde> pesquisarQtdeContaImpressaoTermica(Integer idGrupoFaturamento, Integer referencia) {
		Collection<ContaImpressaoTermicaQtde> colecaoQtdeContas = null;
		try {
			colecaoQtdeContas = repositorioFaturamento.pesquisarQuantidadeContasImpressaoTermica(referencia, idGrupoFaturamento);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return colecaoQtdeContas;
	}
	
	public Fatura pesquisarFaturaDeConta(Integer idConta) throws ControladorException {
		try {
			return repositorioFaturamento.pesquisarFaturaDeConta(idConta);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public BigDecimal[] obterValorCreditoReparcelamentoDeCurtoELongoPrazo(short numeroPrestacoes, BigDecimal valorCredito) throws ControladorException {

		final int indiceCurtoPrazo = 0;
		final int indiceLongoPrazo = 1;

		BigDecimal valorPrestacao = null;
		BigDecimal[] valoresCurtoPrazoLongoPrazo = new BigDecimal[2];

		if (numeroPrestacoes > 0) {
			if (numeroPrestacoes < 13) {
				valoresCurtoPrazoLongoPrazo[indiceCurtoPrazo] = valorCredito;
				valoresCurtoPrazoLongoPrazo[indiceLongoPrazo] = new BigDecimal(0.0);
			} else {
				valorPrestacao = valorCredito.divide(new BigDecimal(numeroPrestacoes), 2, BigDecimal.ROUND_DOWN);
	
				valoresCurtoPrazoLongoPrazo[indiceCurtoPrazo] = valorPrestacao.multiply(new BigDecimal("12"));
				valoresCurtoPrazoLongoPrazo[indiceLongoPrazo] = valorCredito.subtract(valoresCurtoPrazoLongoPrazo[indiceCurtoPrazo]);
			}
	
			if (valoresCurtoPrazoLongoPrazo[0] == null) {
				valoresCurtoPrazoLongoPrazo[0] = BigDecimal.ZERO;
			}
	
			if (valoresCurtoPrazoLongoPrazo[1] == null) {
				valoresCurtoPrazoLongoPrazo[1] = BigDecimal.ZERO;
			}
		}

		return valoresCurtoPrazoLongoPrazo;
	}
	
	public BigDecimal[] obterValorCurtoELongoPrazoParaParcelamento(short numeroPrestacoes, short numeroPrestacoesCobradas, BigDecimal valorTotal, BigDecimal valorRestante) throws ControladorException {

		final int curtoPrazo = 0;
		final int longoPrazo = 1;

		BigDecimal valorPrestacao = null;

		BigDecimal[] valores = new BigDecimal[2];

		if (numeroPrestacoes < numeroPrestacoesCobradas) {
			return null;
		} else {
			if (numeroPrestacoes == 0) {
				return null;
			} else {
				if ((numeroPrestacoes - numeroPrestacoesCobradas) < 13) {
					valores[curtoPrazo] = valorRestante;
					valores[longoPrazo] = new BigDecimal(0.0);
				} else {
					valorPrestacao = valorTotal.divide(new BigDecimal(numeroPrestacoes), 2, BigDecimal.ROUND_DOWN);

					valores[curtoPrazo] = valorPrestacao.multiply(new BigDecimal("12"));

					valores[longoPrazo] = valorRestante.subtract(valores[curtoPrazo]);
				}

				if (valores[0] == null) {
					valores[0] = BigDecimal.ZERO;
				}

				if (valores[1] == null) {
					valores[1] = BigDecimal.ZERO;
				}

				return valores;
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection<Object[]> pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(Integer idConta, Collection idsFinanciamentoTipo) throws ControladorException {
		try {
			Collection<Object[]> debitosCobrados =  repositorioFaturamento.pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(idConta, idsFinanciamentoTipo);
			
//			if (debitosCobradosHistorico != null && !debitosCobradosHistorico.isEmpty())
//				debitosCobrados.addAll(debitosCobradosHistorico);
				
			return debitosCobrados;
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Object[] gerarDadosAliquotasImpostos(EmitirContaHelper helper, boolean isContaHistorico) {
		Object[] retorno = new Object[8];
		try {
			
			BigDecimal valorPrestacao = new BigDecimal(0.00);
			
			Collection<Integer> financiamentosTipo = new ArrayList<Integer>();
			financiamentosTipo.add(FinanciamentoTipo.PARCELAMENTO_AGUA);
			financiamentosTipo.add(FinanciamentoTipo.PARCELAMENTO_ESGOTO);
			financiamentosTipo.add(FinanciamentoTipo.PARCELAMENTO_SERVICO);
			financiamentosTipo.add(FinanciamentoTipo.JUROS_PARCELAMENTO);
			
			Filtro filtro = null;
			if (isContaHistorico) {
				filtro = new FiltroDebitoCobradoHistorico();
				filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCobradoHistorico.CONTA_HISTORICO_ID, helper.getIdConta()));
				filtro.adicionarParametro(new ParametroSimplesIn(FiltroDebitoCobradoHistorico.FINANCIAMENTO_TIPO_ID, financiamentosTipo));
				
				Collection<DebitoCobradoHistorico> debitosParcelamento = getControladorUtil().pesquisar(filtro, DebitoCobradoHistorico.class.getName());
				
				for (Iterator<DebitoCobradoHistorico> iterator = debitosParcelamento.iterator(); iterator.hasNext();) {
					DebitoCobradoHistorico debito = (DebitoCobradoHistorico) iterator.next();
					valorPrestacao = valorPrestacao.add(debito.getValorPrestacao());
				}

			} else {
				filtro = new FiltroDebitoCobrado();
				filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCobrado.CONTA_ID, helper.getIdConta()));
				filtro.adicionarParametro(new ParametroSimplesIn(FiltroDebitoCobrado.FINANCIAMENTO_TIPO_ID, financiamentosTipo));
				
				Collection<DebitoCobrado> debitosParcelamento = getControladorUtil().pesquisar(filtro, DebitoCobrado.class.getName());
				
				for (Iterator<DebitoCobrado> iterator = debitosParcelamento.iterator(); iterator.hasNext();) {
					DebitoCobrado debito = (DebitoCobrado) iterator.next();
					valorPrestacao = valorPrestacao.add(debito.getValorPrestacao());
				}

			}
			
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			Collection colecao = getControladorUtil().pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
			
			String descricaoAgenciaReguladora = getFaturamentoParametro(FaturamentoParametro.NOME_PARAMETRO_FATURAMENTO.AGENCIA_REGULADORA_NOME.toString());
			BigDecimal alicotaAgenciaReguladora = new BigDecimal(getFaturamentoParametro(FaturamentoParametro.NOME_PARAMETRO_FATURAMENTO.AGENCIA_REGULADORA_ALIQUOTA.toString()));
			
			String descricaoAliquotaImposto = "";
			BigDecimal aliquota = null;
				
			if(colecao != null && !colecao.isEmpty()){
				SistemaParametro sistemaParametro = (SistemaParametro) colecao.iterator().next();
				descricaoAliquotaImposto = sistemaParametro.getDescricaoAliquotaImposto();
				aliquota = sistemaParametro.getValorAliquotaImposto();
			}
			
			BigDecimal valorBaseCalculo = helper.getValorAgua().add(helper.getValorEsgoto()).add(helper.getDebitos()).subtract(valorPrestacao);
			
			BigDecimal percentualAliquota = aliquota.divide(new BigDecimal(100));
			BigDecimal valorImposto = valorBaseCalculo.multiply(percentualAliquota);
			
			BigDecimal percentualAliquotaAgenciaReguladora = alicotaAgenciaReguladora.divide(new BigDecimal(100));
			BigDecimal valorImpostoAgenciaReguladora = valorBaseCalculo.multiply(percentualAliquotaAgenciaReguladora);
	    	
			retorno[0] = descricaoAliquotaImposto; 
			retorno[1] = aliquota; 
			retorno[2] = valorBaseCalculo.setScale(2, BigDecimal.ROUND_HALF_UP);
			retorno[3] = valorImposto.setScale(2, BigDecimal.ROUND_HALF_UP);
			retorno[4] = descricaoAgenciaReguladora;
			retorno[5] = alicotaAgenciaReguladora;
			retorno[6] = valorImpostoAgenciaReguladora.setScale(2, BigDecimal.ROUND_HALF_UP);
			
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return retorno;
    }
	
	public Object[] obterDadosAgenciaReguladora() {
		String nome = Fachada.getInstancia().getFaturamentoParametro(FaturamentoParametro.NOME_PARAMETRO_FATURAMENTO.AGENCIA_REGULADORA_NOME.toString());
		String aliquota = Fachada.getInstancia().getFaturamentoParametro(FaturamentoParametro.NOME_PARAMETRO_FATURAMENTO.AGENCIA_REGULADORA_ALIQUOTA.toString());
		String municipio = Fachada.getInstancia().getFaturamentoParametro(FaturamentoParametro.NOME_PARAMETRO_FATURAMENTO.AGENCIA_REGULADORA_MUNICIPIO.toString());
		
		Object[] retorno = new Object[3];
		retorno[0] = nome;
		retorno[1] = new BigDecimal(aliquota);
		retorno[2] = new Integer(municipio);
		
		return retorno;
	}
	
	public Short obterDiaVencimentoConta(Integer idImovel) throws ControladorException {
		Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

		if (imovel.getDiaVencimento() != null) {
			return imovel.getDiaVencimento();
		} else {
			FaturamentoGrupo grupo = getControladorImovel().pesquisarGrupoImovel(idImovel);
			
			return grupo.getDiaVencimento();
		}
	}
	
	public Object[] pesquisarParmsContaMensagem(EmitirContaHelper helper, Integer idGrupo, Integer idGerencia, Integer idLocalidade, Integer idSetor) throws ControladorException {
		try {
			return repositorioFaturamento.pesquisarParmsContaMensagem(helper, idGrupo, idGerencia, idLocalidade, idSetor);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public boolean isAlgumaContaEmProcessoJudicial (Integer idImovel, Date data) throws ControladorException {
		try {
			FiltroConta filtro = new FiltroConta();
			filtro.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, idImovel));
			filtro.adicionarParametro(new ParametroNaoNulo(FiltroConta.DATA_REVISAO));
			filtro.adicionarParametro(new ParametroSimples(FiltroConta.CONTA_MOTIVO_REVISAO_ID, ContaMotivoRevisao.EM_COBRANCA_JUDICIAL));
			
	
			Collection<Conta> contas = getControladorUtil().pesquisar(filtro, Conta.class.getName());
			
			if (contas != null && !contas.isEmpty()) {
				for (Conta conta : contas) {
					if (conta.getDataEmissao().after(data))
						return true;
				}
			}
		} catch (ControladorException e) {
			throw new ControladorException("erro.pesquisar.conta.cobranca.judicial", e);
		}
		
		return false;
	}
	
	public List<IClienteConta> pesquisarClienteContaDeContasEmitidasAPartirDeUmaData(Integer idImovel, Date dataEmissao) throws ControladorException {
		try {
			return repositorioFaturamento.pesquisarClienteContaDeContasEmitidasAPartirDeUmaData(idImovel, dataEmissao);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public IConta obterContaOuContaHistorico(Integer idImovel, Integer referencia) {
        
        IConta conta = null; 
        try {

            FiltroConta filtroConta = new FiltroConta();
            filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, idImovel));
            filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.REFERENCIA, referencia));
            filtroConta.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));

            Collection colecaoConta = getControladorUtil().pesquisar(filtroConta, Conta.class.getName());
            
            if (colecaoConta != null && !colecaoConta.isEmpty()) {
                conta = (IConta) colecaoConta.iterator().next();
            } else {
                FiltroContaHistorico filtroHistorico = new FiltroContaHistorico();
                filtroHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.IMOVEL_ID, idImovel));
                filtroHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ANO_MES_REFERENCIA, referencia));
                filtroHistorico.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaHistorico.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));

                Collection colecaoContaHistorico = getControladorUtil().pesquisar(filtroConta, Conta.class.getName());
                
                if (colecaoConta != null && !colecaoConta.isEmpty()) {
                    conta = (IConta) colecaoConta.iterator().next();
                }
            }
        } catch (ControladorException e) {
            e.printStackTrace();
        }
        return conta;
    }
	
	public boolean isImovelEmsituacaoEspecialFaturamento(Integer idImovel, Integer anoMesReferencia) throws ControladorException {
        Collection<FaturamentoSituacaoHistorico> situacoes = pesquisarSituacaoEspecialFaturamentoVigente(idImovel, anoMesReferencia);
        
        return situacoes != null && situacoes.size() > 0; 
    }
	
	public ComunicadoEmitirConta pesquisarComunicadoNaoEmitido(Integer idImovel, Integer tipoComunicado) throws ControladorException {
		FiltroComunicadoEmitirConta filtro = new FiltroComunicadoEmitirConta(FiltroComunicadoEmitirConta.REFERENCIA);
		
		filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.IMOVEL_ID, idImovel));
		filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.TIPO_COMUNICADO, tipoComunicado));	
		filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.INDICADOR_EMISSAO, ConstantesSistema.NAO));
		
		Collection<ComunicadoEmitirConta> comunicados = getControladorUtil().pesquisar(filtro, ComunicadoEmitirConta.class.getName());
		
		if (comunicados != null && !comunicados.isEmpty())
			return  comunicados.iterator().next();
		else
			return null;
	}
	
	public ComunicadoEmitirConta pesquisarUltimoComunicadoGerado(Integer idImovel, Integer tipoComunicado) throws ControladorException {
		try {
			return repositorioFaturamento.pesquisarUltimoComunicadoGerado(idImovel, tipoComunicado);
		} catch (Exception e) {
			throw new ControladorException("Erro ao pesquisar ultimo comunicado gerado", e);
		}
	}
	
	public Collection pesquisarComunicadosNaoEmitidos(Integer tipoComunicado) throws ControladorException {
		
		try {
			FiltroComunicadoEmitirConta filtro = new FiltroComunicadoEmitirConta();
			
			filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.TIPO_COMUNICADO, tipoComunicado));
			filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.INDICADOR_EMISSAO, ConstantesSistema.NAO));

			return  (Collection) getControladorUtil().pesquisar(filtro, ComunicadoEmitirConta.class.getName());
		} catch (ControladorException e) {
			throw new ControladorException("Erro ao pesquisar comunicados não emitidos", e);
		}
		
	}
	
	public ComunicadoEmitirConta pesquisarComunicado(Integer idImovel, Integer referencia, Integer tipoComunicado) throws ControladorException {
		try {
			FiltroComunicadoEmitirConta filtro = new FiltroComunicadoEmitirConta();
			
			filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.IMOVEL, idImovel));
			filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.REFERENCIA, referencia));
			filtro.adicionarParametro(new ParametroSimples(FiltroComunicadoEmitirConta.TIPO_COMUNICADO, tipoComunicado));

			Collection<ComunicadoEmitirConta> comunicados = getControladorUtil().pesquisar(filtro, ComunicadoEmitirConta.class.getName());
			
			if (comunicados != null && !comunicados.isEmpty())
				return  comunicados.iterator().next();
			else
				return null;
		} catch (ControladorException e) {
			throw new ControladorException("Erro ao pesquisar comunicados não emitidos", e);
		}
	}
	
	public String getFaturamentoParametro(String parametro) throws ControladorException {
		Filtro filtro = new FiltroFaturamentoParametro();
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoParametro.NOME, parametro));

		Collection parametros = Fachada.getInstancia().pesquisar(filtro, FaturamentoParametro.class.getName());

		return ((FaturamentoParametro) parametros.iterator().next()).getValor();
	}
	
	public Localidade pesquisarLocalidadeConta(Integer parametro) throws ControladorException {
		Filtro filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, parametro));

		Collection<Localidade> colecao = getControladorUtil().pesquisar(filtro, Localidade.class.getName());
		
		if (colecao != null && !colecao.isEmpty()) {
			return (Localidade) Util.retonarObjetoDeColecao(colecao);
		} else {
			return null;
		}
	}
	
	public Object[] pesquisarContatosAgenciaReguladora(EmitirContaHelper emitirContaHelper) throws ErroRepositorioException, ControladorException {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, emitirContaHelper.getIdSetorComercial()));
		
		SetorComercial setorComercial = (SetorComercial) getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName()).iterator().next();
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, setorComercial.getMunicipio().getId()));
		
		Municipio municipio = (Municipio) getControladorUtil().pesquisar(filtroMunicipio, Municipio.class.getName()).iterator().next();
		
		return repositorioFaturamento.pesquisarContatosAgenciaReguladora(municipio.getId());
	}
	
	public File faturaEnvioEmailVencimentoFatura(Conta conta, Imovel imovel)
			throws ControladorException {
	
		try {
			Collection<Integer> idsContas = new ArrayList<Integer>();
			idsContas.add(conta.getId());
			ContaSegundaViaBO bo = new ContaSegundaViaBO(null, idsContas, false, new Short("1"));
			ContaSegundaViaHelper helper = bo.criar(imovel, null, conta.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao());

			if (helper != null) {
				String url = Fachada.getInstancia().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_SEGUNDA_VIA.toString());

				GsanApi api = new GsanApi(url);
				api.invoke(helper);
				return api.salvar(helper.getNomeArquivo());
				
			} else {
				throw new ActionServletException("atencao.conta_segunda_via_sem_dados");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_conta_segunda_via");
		}		
	}
	
	public void envioNotificacaoVencimentoFatura(Integer idFuncionalidadeIniciada, Collection<Integer> colecaoIdsLocalidades)
			throws ControladorException {

		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
				UnidadeProcessamento.LOCALIDADE, ((Integer) Util.retonarObjetoDeColecao(colecaoIdsLocalidades)));

		Integer qtdDiasVencimento = Integer.valueOf(getFaturamentoParametro(
				FaturamentoParametro.NOME_PARAMETRO_FATURAMENTO.QUANTIDADE_DIAS_FATURA_VENCIDA
				.toString()));

		try {
			if (colecaoIdsLocalidades != null && !colecaoIdsLocalidades.isEmpty()) {
				
				for (Integer idLocalidade : colecaoIdsLocalidades) {

					Collection<Integer> idsRotas = this.getControladorMicromedicao()
							.obterIdsRotasPelaLocalidade(idLocalidade);
					System.out.println("\nNÚMERO ROTAS: " + idsRotas.size());
					if (idsRotas != null && !idsRotas.isEmpty()) {

						for (Integer idRota : idsRotas) {

							Date dataVencimento = Util.adicionarNumeroDiasDeUmaData(new Date(), qtdDiasVencimento);
							Collection contas = repositorioFaturamento.pesquisarContasVencimentoParaEnvioEmail(idRota, dataVencimento);

							if (contas != null && !contas.isEmpty()) {

								Iterator iteratosContas = contas.iterator();

								while (iteratosContas.hasNext()) {
									try {

										Object[] contasEmail = (Object[]) iteratosContas.next();

										envioEmailVencimentoFatura(contasEmail, qtdDiasVencimento);
										//envioSMSVencimentoFatura(contasEmail);
									} catch (Exception e) {
										System.out.println("erro.notificacao.vencimento.fatura");
									}
								}

							}

						}
					}
				}

			}
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}
	
	private void envioEmailVencimentoFatura(Object[] contasEmail, Integer qtdDiasVencimento)
			throws ControladorException {

		try {
			Conta conta = new Conta((Integer) contasEmail[0]);
			conta.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao((Integer) contasEmail[1]));
			String emailReceptor = (String) contasEmail[2];;
			String nomeCliente = (String) contasEmail[3];
			Imovel imovel = getControladorImovel().pesquisarImovel((Integer) contasEmail[4]);
			
			File contaSegundaVia = faturaEnvioEmailVencimentoFatura(conta, imovel);
		
			EnvioEmail envioEmail = this.getControladorCadastro()
					.pesquisarEnvioEmail(EnvioEmail.ENVIO_EMAIL_VENCIMENTO);
		
			Collection<String> emails = new ArrayList<String>();
			emails.add(emailReceptor);
			
			ServicosEmail.enviarMensagemHTML(emails, 
						 envioEmail.getEmailRemetente(), 
						 "COSANPA", 
						 envioEmail.getTituloMensagem(), 
						 ModeloEmailVencimento.getMensagem(nomeCliente, qtdDiasVencimento));
		} catch (ErroEmailException e) {
			throw new ActionServletException("erro.email.vencimento.fatura");
		}
	}
	
	private void envioSMSVencimentoFatura(Object[] contasEmail)
			throws ControladorException {

		try {
			Integer idImovel = (Integer) contasEmail[4];
			String ddd = (String) contasEmail[5];
			String telefone = (String) contasEmail[6];
			Integer idLocalidade = (Integer) contasEmail[7];
			Short digitoVerificador = (Short) contasEmail[8];
			Integer referencia = (Integer) contasEmail[9];
			BigDecimal valorConta = (BigDecimal) contasEmail[10];
			
					if (ddd != null && telefone != null) {
				String celular = ddd.concat(telefone);
				celular.trim();
				
				String codigoBarras = this
				.getControladorArrecadacao()
				.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
						idLocalidade,
						idImovel, referencia.toString(),
						digitoVerificador.intValue(), null, null, null, null,
						null, null, null);
				
				//ServicoSMS.enviarSMS(celular, ServicoSMS.MSG_VENCIMENTO);
				System.out.println("ENVIANDO SMS PARA " + celular + " ---> cod de barras:" + codigoBarras);
			}
		} catch (Exception e) {
			throw new ActionServletException("erro.sms.vencimento.fatura");
		}
	}
	
	public void gerarCreditosBolsaAgua(Rota rota, int idFuncionalidadeIniciada, FaturamentoGrupo grupo) throws ControladorException {

		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.ROTA, rota.getId());

		Integer idImovelAtual = 0;
		try {
			CreditoTipo creditoTipo = obterCreditoTipo(CreditoTipo.CREDITO_BOLSA_AGUA);
			
			Collection<Imovel> colecaoImovel = getControladorImovel().pesquisarImoveisBolsaAgua(rota);

			for (Imovel imovel: colecaoImovel) {
				idImovelAtual = imovel.getId();
				System.out.println("INSERINDO CREDITO BOLSA AGUA PARA IMOVEL " + imovel.getId());
				apagarDadosCreditoSocialInicioBatch(grupo.getAnoMesReferencia(), creditoTipo, imovel);
				DeterminarValoresFaturamentoAguaEsgotoHelper helper = obterValoresCreditosBolsaAgua(imovel, grupo);
				
				BigDecimal valorCredito = BigDecimal.ZERO;
				
				if (imovel.isLigadoAgua() ) {
					valorCredito = valorCredito.add(helper.getValorTotalAgua());  
				}
				
				if (imovel.isLigadoEsgoto()) {
					if (helper.getPercentualEsgoto() != null && !helper.getPercentualEsgoto().equals("")) {
						BigDecimal valor = helper.getValorTotalAgua();
						BigDecimal percentualEsgoto = new BigDecimal("100");
						helper.setValorTotalEsgoto(valor.multiply(helper.getPercentualEsgoto().divide(percentualEsgoto))
								.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					valorCredito = valorCredito.add(helper.getValorTotalEsgoto());
				}
				
				inserirCreditoBolsaAgua(imovel, valorCredito, grupo.getAnoMesReferencia(), creditoTipo);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			System.out.println("ERRO CREDITO BOLSA AGUA PARA IMOVEL " + idImovelAtual);
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new ActionServletException("erro.credito.bolsa.agua");
		}
	}
	
	public DeterminarValoresFaturamentoAguaEsgotoHelper obterValoresCreditosBolsaAgua(Imovel imovel, FaturamentoGrupo grupo ) {
		LigacaoTipo ligacaoTipo = new LigacaoTipo();
		DeterminarValoresFaturamentoAguaEsgotoHelper valoresAguaEsgoto = null;
		
		try {
			Categoria categoria = getControladorImovel().obterCategoria(Categoria.RESIDENCIAL);
			categoria.setQuantidadeEconomiasCategoria(1);
			Collection colecaoCategorias = new ArrayList();
			colecaoCategorias.add(categoria);
			
			ConsumoHistorico consumoHistoricoAgua = null;
			if (imovel.getLigacaoAgua() != null) {
				ligacaoTipo.setId(LigacaoTipo.LIGACAO_AGUA);
	
				consumoHistoricoAgua = this.getControladorMicromedicao().obterUltimoConsumoImovel(imovel, LigacaoTipo.LIGACAO_AGUA);
				
				if (consumoHistoricoAgua == null) {
					consumoHistoricoAgua = new ConsumoHistorico();
					consumoHistoricoAgua.setIndicadorFaturamento(ConstantesSistema.SIM);
					consumoHistoricoAgua.setConsumoTipo(new ConsumoTipo(ConsumoTipo.SEM_CONSUMO));
				}
				
				consumoHistoricoAgua.setNumeroConsumoFaturadoMes(20);
			}
	
			ConsumoHistorico consumoHistoricoEsgoto = null;
	
			if (imovel.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao().equals(LigacaoEsgotoSituacao.FATURAMENTO_ATIVO)) {
				ligacaoTipo.setId(LigacaoTipo.LIGACAO_ESGOTO);

				consumoHistoricoEsgoto = this.getControladorMicromedicao().obterUltimoConsumoImovel(imovel, LigacaoTipo.LIGACAO_ESGOTO);
				
				if (consumoHistoricoEsgoto == null) {
					consumoHistoricoEsgoto = new ConsumoHistorico();
					consumoHistoricoEsgoto.setIndicadorFaturamento(ConstantesSistema.SIM);
					consumoHistoricoEsgoto.setConsumoTipo(new ConsumoTipo(ConsumoTipo.SEM_CONSUMO));
				}
				
				consumoHistoricoEsgoto.setNumeroConsumoFaturadoMes(14);

			}
		
			valoresAguaEsgoto = this.determinarValoresFaturamentoAguaEsgoto(imovel, grupo.getAnoMesReferencia(),
					colecaoCategorias, grupo, consumoHistoricoAgua, consumoHistoricoEsgoto);
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}

		return valoresAguaEsgoto;
	}

	private void inserirCreditoBolsaAgua(Imovel imovel, BigDecimal valorCredito, Integer referencia, CreditoTipo creditoTipo) throws ControladorException {
		CreditoOrigem creditoOrigem = new CreditoOrigem(CreditoOrigem.BOLSA_AGUA);
		Short qtdPrestacoes = new Short("1");
		
		this.inserirCreditoARealizar(imovel, referencia, valorCredito, creditoTipo, creditoOrigem, qtdPrestacoes, Usuario.USUARIO_BATCH);
	}
	
	private void inserirCreditoARealizar(Imovel imovel, Integer referencia, BigDecimal valorCredito, 
			CreditoTipo creditoTipo, CreditoOrigem creditoOrigem, Short qtdPrestacoes, Usuario usuario) throws ControladorException {
		try {
			
			Date dataAtual = new Date(System.currentTimeMillis());
	
			CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
			creditoARealizarGeral.setIndicadorHistorico(new Short("2"));
			creditoARealizarGeral.setUltimaAlteracao(dataAtual);
	
			Object idCreditoARealizarGeral = repositorioUtil.inserir(creditoARealizarGeral);
			creditoARealizarGeral.setId(new Integer(idCreditoARealizarGeral.toString()));
	
			CreditoARealizar creditoARealizar = new CreditoARealizar();
	
			creditoARealizar.setId(new Integer(idCreditoARealizarGeral.toString()));
			creditoARealizar.setImovel(imovel);
			creditoARealizar.setCreditoTipo(creditoTipo);
			creditoARealizar.setGeracaoCredito(dataAtual);
			creditoARealizar.setAnoMesReferenciaCredito(referencia);
			creditoARealizar.setAnoMesCobrancaCredito(getSistemaParametro().getAnoMesArrecadacao());
	
			// Seta AnoMesReferenciaContabil, com maior valor entre o ano/Mês da data corrente e o ano/Mês de referencia do faturamento.
			Integer mesDataAtual = Util.getMes(dataAtual);
			Integer mesArreacadao = new Integer(getSistemaParametro().getAnoMesFaturamento().toString().substring(4));
	
			if (mesDataAtual.compareTo(mesArreacadao) > 0) {
				creditoARealizar.setAnoMesReferenciaContabil(Util.recuperaAnoMesDaData(dataAtual));
			} else {
				creditoARealizar.setAnoMesReferenciaContabil(sistemaParametro.getAnoMesFaturamento());
			}
	
			creditoARealizar.setValorCredito(valorCredito);
			creditoARealizar.setValorResidualMesAnterior(new BigDecimal("0.0"));
			creditoARealizar.setNumeroPrestacaoCredito(qtdPrestacoes); 
			creditoARealizar.setNumeroPrestacaoRealizada(new Short("0"));
			creditoARealizar.setLocalidade(imovel.getLocalidade());
			creditoARealizar.setQuadra(imovel.getQuadra());
			creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			creditoARealizar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			creditoARealizar.setNumeroLote(imovel.getLote());
			creditoARealizar.setNumeroSubLote(imovel.getSubLote());
			creditoARealizar.setRegistroAtendimento(null);
			creditoARealizar.setOrdemServico(null);
			creditoARealizar.setLancamentoItemContabil(creditoTipo.getLancamentoItemContabil());
			creditoARealizar.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL));
			creditoARealizar.setDebitoCreditoSituacaoAnterior(null);
			creditoARealizar.setCreditoARealizarGeral(creditoARealizarGeral);
			creditoARealizar.setCreditoOrigem(creditoOrigem);
			creditoARealizar.setUltimaAlteracao(dataAtual);
			creditoARealizar.setUsuario(usuario); 
	
			Object idCreditoARealizar = repositorioUtil.inserir(creditoARealizar);
	
			creditoARealizar.setId(new Integer(idCreditoARealizar.toString()));
			
			this.inserirCreditoARealizarCategoria(creditoARealizar);
				
			
		} catch (ControladorException e) {
			System.out.println("ERRO CREDITO BOLSA AGUA PARA IMOVEL " + imovel.getId());
			e.printStackTrace();
			throw new ControladorException("erro.credito.realizar.bolsa.agua");
		} catch (ErroRepositorioException e) {
			System.out.println("ERRO CREDITO BOLSA AGUA PARA IMOVEL " + imovel.getId());
			e.printStackTrace();
			throw new ControladorException("erro.credito.realizar.bolsa.agua");
		}
	}
	
	private void inserirCreditoARealizarCategoria(CreditoARealizar creditoARealizar) throws ControladorException {
		try {
			Collection colecaoCategoriasImovel;
			
			if(creditoARealizar.isCreditoBolsaAgua()) {
				colecaoCategoriasImovel = this.getControladorImovel().obterQuantidadeEconomiasCategoria(creditoARealizar.getImovel(), Categoria.RESIDENCIAL);
			}else {
				colecaoCategoriasImovel = this.getControladorImovel().obterQuantidadeEconomiasCategoria(creditoARealizar.getImovel());
					}
				
			Iterator iteratorColecaoCategoriasImovel = colecaoCategoriasImovel.iterator();
	
			Iterator iteratorColecaoValorPorCategoria = getControladorImovel().obterValorPorCategoria(colecaoCategoriasImovel,
					creditoARealizar.getValorCredito()).iterator();
	
			while (iteratorColecaoCategoriasImovel.hasNext() && iteratorColecaoValorPorCategoria.hasNext()) {
	
				Categoria categoria = (Categoria) iteratorColecaoCategoriasImovel.next();
	
				BigDecimal valor = (BigDecimal) iteratorColecaoValorPorCategoria.next();
	
				CreditoARealizarCategoria creditoARealizarCategoria = new CreditoARealizarCategoria();
	
				creditoARealizarCategoria.setComp_id(new CreditoARealizarCategoriaPK(creditoARealizar.getId(), categoria.getId()));
				creditoARealizarCategoria.setCreditoARealizar(creditoARealizar);
				creditoARealizarCategoria.setCategoria(categoria);
				creditoARealizarCategoria.setQuantidadeEconomia(categoria.getQuantidadeEconomiasCategoria());
				creditoARealizarCategoria.setValorCategoria(valor);
				creditoARealizarCategoria.setUltimaAlteracao(new Date(System.currentTimeMillis()));

				repositorioUtil.inserir(creditoARealizarCategoria);
			}
		} catch (ErroRepositorioException e) {
			System.out.println("ERRO CREDITO CATEGORIA BOLSA AGUA PARA IMOVEL " + creditoARealizar.getImovel().getId());
			e.printStackTrace();
			throw new ControladorException("erro.credito.categoria");
		} catch (ControladorException ex) {
			System.out.println("ERRO CREDITO CATEGORIA BOLSA AGUA PARA IMOVEL " + creditoARealizar.getImovel().getId());
			ex.printStackTrace();
			throw new ControladorException("erro.credito.categoria");
		}

	}
	
	public BigDecimal retornaValorBolsaAgua (Integer anoMesReferencia, Imovel imovel) throws ControladorException {

		FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();

		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO, anoMesReferencia));
		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.IMOVEL_ID, imovel.getId()));
		filtroCreditoARealizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_CREDITO_TIPO, CreditoTipo.CREDITO_BOLSA_AGUA));

		Collection colecaoCreditoARealizar = (Collection) this.getControladorUtil().pesquisar(filtroCreditoARealizar, CreditoARealizar.class.getName());

		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {

			CreditoARealizar credito = (CreditoARealizar) colecaoCreditoARealizar.iterator().next();
			return credito.getValorCredito();
		}

		return BigDecimal.ZERO;
	}
	
	private CreditoRealizado obterCreditoBolsaAgua(Collection<CreditoRealizado> collectionCreditos) {
    	Iterator<CreditoRealizado> iteratorColecaoCreditosARealizar = collectionCreditos.iterator();

        while (iteratorColecaoCreditosARealizar.hasNext()) {
        	CreditoRealizado credito = iteratorColecaoCreditosARealizar.next();
        	
        	if (credito.isCreditoBolsaAgua()) {
        		return credito;
        	}
        }
        
        return null;
    }
	
	private SistemaParametro getSistemaParametro() throws ControladorException {
		try {
			sistemaParametro = repositorioUtil.pesquisarParametrosDoSistema();
		} catch (ErroRepositorioException e) {
		    throw new ControladorException("erro.sistema", e);
		}
		return sistemaParametro;
	}
	
	public void registroFichaCompensacao(Integer idGrupoFaturamento, Integer anoMesReferencia)
			throws ControladorException, ErroRepositorioException {
		Collection<Integer> colecaoContas = repositorioFaturamento.idContasEmitidasFichaCompensacao(idGrupoFaturamento, anoMesReferencia);
		try {
			for(Integer conta : colecaoContas){
			FichaCompensacao fichaCompensacao = null;			

			Integer idConta = conta;
			fichaCompensacao = registrarBoleto(idConta);
				
			String url = Fachada.getInstancia().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_API_REGISTRAR_BOLETO_BB.toString());
		
				GsanApi api = new GsanApi(url);
				api.invoke(fichaCompensacao);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.nao_foi_possivel_registrar_conta");
		}		
		
	}
	
	public FichaCompensacao registrarBoleto(Integer idConta) throws ControladorException, ErroRepositorioException {
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		
		Conta conta = new Conta();		
		Cliente cliente = new Cliente();
		conta = repositorioFaturamento.contaFichaCompensacao(idConta);		
		Imovel imovel = conta.getImovel();
		String municipio = imovel.getNomeMunicipio();
		Integer idImovel = imovel.getId();
        cliente = repositorioFaturamento.clienteFichaCompensacao(idImovel);
        Integer idCliente = cliente.getId();
		
		Integer idConv = 2860143; // Em produção, informar o número do convênio de cobrança, com 7 dígitos.
		Integer numeroCarteira = 17; // Em produção, informar o número da carteira de cobrança.
		Integer numeroVariacaoCarteira = 35; // Em produção, informar o número da variação da carteira de cobrança.
		Short codigoModalidade = 1; // Código que identifica a característica dos boletos dentro das modalidades de
									// cobrança existentes no BB. Domínio: 1 - Simples; 4 - Vinculada.
		String dataEmissao =  Util.formatarDataComTracoDDMMAAAA(conta.getDataEmissao()).toString(); //Pegar da conta
		String dataVencimento = Util.formatarDataComTracoDDMMAAAA(conta.getDataVencimentoConta()).toString(); //pegar da conta
		String valorOriginal = conta.getValorTotalConta().replace(".", "").replace(",", "");
		String codigoAceite = "A"; // Domínio: A - Aceito; N - Não aceito
		Short codigoTipoTitulo = 2; // Código para identificar o tipo de boleto de cobrança. Verifique os domínios
									// possíveis no swagger.
		String indicadorPermissaoRecebimentoParcial = "N"; // Código para identificação da autorização de pagamento
															// parcial do boleto. "S" ou "N"
		StringBuilder nossoNumero = obterNossoNumeroFichaCompensacao("1", conta.getId().toString());
		String nossoNumeroSemDV = nossoNumero.toString().substring(0, 17);
		String numeroTituloCliente = nossoNumeroSemDV; // pegar da conta (nosso numero)
		PagadorDTO pagadorDTO = new PagadorDTO(); // Identifica o pagador do boleto.
		
		if(cliente.getCpf() != null) { 
		pagadorDTO.setTipoInscricao((short) 1);
		pagadorDTO.setNumeroInscricao(cliente.getCpf());
		}else {
		pagadorDTO.setTipoInscricao((short) 2);	
		pagadorDTO.setNumeroInscricao(cliente.getCnpj());
		}
		
		pagadorDTO.setNome(cliente.getNome());
		pagadorDTO.setEndereco(imovel.getEnderecoFormatado()); 
		pagadorDTO.setCidade(municipio);
		pagadorDTO.setBairro(imovel.getNomeBairro());
		pagadorDTO.setUf("PA");			
		
		pagadorDTO.setCep(imovel.getCodigoCep());
		
		FichaCompensacao fichaCompensacaoApi = new FichaCompensacao(idConta, idConv, numeroCarteira,
				numeroVariacaoCarteira, codigoModalidade, dataEmissao, dataVencimento, valorOriginal, codigoAceite,
				codigoTipoTitulo, indicadorPermissaoRecebimentoParcial, numeroTituloCliente, pagadorDTO);
		
		FichaCompensacao fichaCompensacaoBanco = new FichaCompensacao(idConta, idConv, numeroCarteira,
				numeroVariacaoCarteira, codigoModalidade, dataEmissao, dataVencimento, valorOriginal, codigoAceite,
				codigoTipoTitulo, indicadorPermissaoRecebimentoParcial, numeroTituloCliente, idImovel, idCliente);

		Fachada.getInstancia().inserir(fichaCompensacaoBanco);

		return fichaCompensacaoApi;
	}

}

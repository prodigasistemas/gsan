package gcom.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.msg.FiltroMensagemParcelamentoBoleto;
import gcom.cobranca.parcelamento.msg.MensagemParcelamentoBoleto;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioEmitirGuiaPagamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioEmitirGuiaPagamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EMITIR);
	}

	@Deprecated
	public RelatorioEmitirGuiaPagamento() {
		super(null, "");
	}

	@SuppressWarnings("rawtypes")
	private Collection<RelatorioEmitirGuiaPagamentoBean> inicializarBeanRelatorio(Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioEmitirGuiaPagamentoDetailBean> colecaoDetail = new ArrayList<RelatorioEmitirGuiaPagamentoDetailBean>();
		Collection<RelatorioEmitirGuiaPagamentoBean> retorno = new ArrayList<RelatorioEmitirGuiaPagamentoBean>();

		Iterator iterator = dadosRelatorio.iterator();

		colecaoDetail.clear();

		while (iterator.hasNext()) {
			boolean ehParcelamento = false;
			
			GuiaPagamentoRelatorioHelper helper = (GuiaPagamentoRelatorioHelper) iterator.next();

			String descricaoServicosTarifas = "";
			String valor = "";
			RelatorioEmitirGuiaPagamentoDetailBean relatorioEmitirGuiaPagamentoDetailBean = null;

			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, helper.getIdGuiaPagamento()));

			filtroGuiaPagamentoItem.setCampoOrderBy(new String[] { "guiaPagamentoGeral", "debitoTipo" });

			Collection colecaoGuiaPagamentoItem = Fachada.getInstancia().pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			Iterator iteratorGuiaPagamentoitem = colecaoGuiaPagamentoItem.iterator();

			if (!colecaoGuiaPagamentoItem.isEmpty()) {
				while (iteratorGuiaPagamentoitem.hasNext()) {
					GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem) iteratorGuiaPagamentoitem.next();
					descricaoServicosTarifas = guiaPagamentoItem.getDebitoTipo().getDescricao() + "     " + helper.getPrestacaoFormatada();

					valor = Util.formatarMoedaReal(guiaPagamentoItem.getValorDebito());

					relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(descricaoServicosTarifas, valor, ehParcelamento);

					colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
				}
			} else {
				ehParcelamento = true;
				
				descricaoServicosTarifas = helper.getDescTipoDebito() + "     " + helper.getPrestacaoFormatada();

				valor = Util.formatarMoedaReal(helper.getValorDebito());

				relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(descricaoServicosTarifas, valor, ehParcelamento);

				preencherInformacoesParcelamento(relatorioEmitirGuiaPagamentoDetailBean, helper.getIdGuiaPagamento());
				colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
			}

			String valorTotal = Util.formatarMoedaReal(helper.getValorDebito());

			String matricula = helper.getMatricula();
			String nomeCliente = "";
			if (helper.getNomeCliente() != null) {
				nomeCliente = helper.getNomeCliente();
			}
			String dataVencimento = Util.formatarData(helper.getDataVencimento());
			String inscricao = helper.getInscricao();
			String enderecoImovel = helper.getEnderecoImovel();
			String enderecoClienteResponsavel = helper.getEnderecoClienteResponsavel();
			String representacaoNumericaCodBarraFormatada = helper.getRepresentacaoNumericaCodBarraFormatada();
			String representacaoNumericaCodBarraSemDigito = helper.getRepresentacaoNumericaCodBarraSemDigito();
			String dataValidade = helper.getDataValidade();
			String idGuiaPagamento = helper.getIdGuiaPagamento();

			String observacao = "";

			Short indicadorEmitirObservacao = helper.getIndicadorEmitirObservacao();
			if (indicadorEmitirObservacao != null && indicadorEmitirObservacao.equals(ConstantesSistema.SIM)) {
				observacao = helper.getObservacao();
			}

			String cpfCnpjCliente = "";

			if (helper.getCpfCliente() != null && !helper.getCpfCliente().equals("")) {
				cpfCnpjCliente = Util.formatarCpf(helper.getCpfCliente());
			} else if (helper.getCnpjCliente() != null && !helper.getCnpjCliente().equals("")) {
				cpfCnpjCliente = Util.formatarCnpj(helper.getCnpjCliente());
			}

			String idImovel = "";
			if (helper.getIdImovel() != null && !helper.getIdImovel().equals("")) {
				idImovel = helper.getIdImovel().toString();
			} else if (helper.getIdCliente() != null) {
				idImovel = helper.getIdCliente().toString();
			}

			String nossoNumero = helper.getNossoNumero();
			String sacadoParte01 = helper.getSacadoParte01();
			String sacadoParte02 = helper.getSacadoParte02();

			String subRelatorio = helper.getSubRelatorio();

			RelatorioEmitirGuiaPagamentoBean bean = new RelatorioEmitirGuiaPagamentoBean(colecaoDetail, matricula, nomeCliente, dataVencimento, inscricao, enderecoImovel, enderecoClienteResponsavel,
					representacaoNumericaCodBarraFormatada, representacaoNumericaCodBarraSemDigito, dataValidade, valorTotal, idGuiaPagamento, observacao, cpfCnpjCliente, idImovel, nossoNumero,
					sacadoParte01, sacadoParte02, subRelatorio, colecaoDetail, obterMensagemParcelamento(ehParcelamento), ehParcelamento);

			colecaoDetail.clear();

			retorno.add(bean);

		}

		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	private Parcelamento obterParcelamentoGuia(String idGuiaPagamento) {
		FiltroGuiaPagamento filtro = new FiltroGuiaPagamento();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, idGuiaPagamento));

		Collection<GuiaPagamento> guias = Fachada.getInstancia().pesquisar(filtro, GuiaPagamento.class.getName());
		GuiaPagamento guia = guias.iterator().next();
		
		if (guia.getParcelamento() != null)
			return guia.getParcelamento();
		else
			return null;
	}
	
	private void preencherInformacoesParcelamento(RelatorioEmitirGuiaPagamentoDetailBean relatorio, String idGuiaPagamento) {
		Parcelamento parcelamento = obterParcelamentoGuia(idGuiaPagamento);
		
		Integer[] periodoDebitos = Fachada.getInstancia().obterPeriodoContasParceladas(parcelamento.getId());
		
		Short diaVencimento = Fachada.getInstancia().obterDiaVencimentoConta(parcelamento.getImovel().getId());
		if (parcelamento != null)
			relatorio.preencherDadosParcelamento(parcelamento, periodoDebitos, diaVencimento);
	}

	@SuppressWarnings("rawtypes")
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String[] ids = (String[]) getParametro("ids");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Map<String, Object> parametros = new HashMap<String, Object>();

		Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio = fachada.pesquisarGuiaPagamentoRelatorio(ids);

		Collection<RelatorioEmitirGuiaPagamentoBean> colecaoBean = this.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}

		String idUsuario = "";

		Usuario usuario = this.getUsuario();

		if (usuario != null) {
			idUsuario = usuario.getId().toString();
		} else {
			idUsuario = "INTERNET";
		}
		String nomeUsuario = "";
		if (usuario != null) {
			nomeUsuario = usuario.getNomeUsuario();
		}

		parametros.put("imagemConta", sistemaParametro.getImagemConta());

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());

		parametros.put("dataVencimento", colecaoBean.iterator().next().getDataVencimento());

		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("nomeUsuario", nomeUsuario);
		Integer anoAtual = Util.getAno(new Date());
		parametros.put("anoGuiaPagamento", "" + anoAtual);

		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());

		List listArrayJRDetail = new ArrayList();
		RelatorioDataSource arrayJRDetailSub = new RelatorioDataSource(listArrayJRDetail);
		parametros.put("arrayJRDetailSub", arrayJRDetailSub);

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioEmitirGuiaPagamentoBean>) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EMITIR, parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.EMITIR_GUIA_PAGAMENTO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirGuiaPagamento", this);
	}
	
	@SuppressWarnings("rawtypes")
	private String obterMensagemParcelamento(boolean ehParcelamento) {
		if (ehParcelamento) {
			
			FiltroMensagemParcelamentoBoleto filtro = new FiltroMensagemParcelamentoBoleto();
			filtro.adicionarParametro(new ParametroNulo(FiltroMensagemParcelamentoBoleto.FIM_VIGENCIA));
	
			Collection mensagens = Fachada.getInstancia().pesquisar(filtro, MensagemParcelamentoBoleto.class.getName());
			Iterator itMensagem = mensagens.iterator();
			
			MensagemParcelamentoBoleto mensagem = (MensagemParcelamentoBoleto) itMensagem.next();
		
			return mensagem.getMensagem();
		}else
			return "";
	}
}

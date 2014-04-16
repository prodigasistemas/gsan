package gcom.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0379] Emitir Guia de Pagamento
 * 
 * @author Vivianne Sousa, Mariana Victor
 * @date 22/09/2006, 02/03/2011
 */
public class RelatorioEmitirGuiaPagamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioEmitirGuiaPagamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EMITIR);
	}
	
	@Deprecated
	public RelatorioEmitirGuiaPagamento() {
		super(null, "");
	}

	private Collection<RelatorioEmitirGuiaPagamentoBean> inicializarBeanRelatorio(
			Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioEmitirGuiaPagamentoDetailBean> colecaoDetail = new ArrayList<RelatorioEmitirGuiaPagamentoDetailBean>();
		Collection<RelatorioEmitirGuiaPagamentoBean> retorno = new ArrayList<RelatorioEmitirGuiaPagamentoBean>();

		Iterator iterator = dadosRelatorio.iterator();
		
		colecaoDetail.clear();
		
		while (iterator.hasNext()) {

			GuiaPagamentoRelatorioHelper guiaPagamentoRelatorioHelper = (GuiaPagamentoRelatorioHelper) iterator
					.next();
			
			//nov parte de guia pagamento item
			//Flávio Leonardo
			//07/11/2008
			String descricaoServicosTarifas = "";
			String valor = "";
			RelatorioEmitirGuiaPagamentoDetailBean relatorioEmitirGuiaPagamentoDetailBean = null;
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, 
					guiaPagamentoRelatorioHelper.getIdGuiaPagamento()));
			
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			
			Collection colecaoGuiaPagamentoItem = Fachada.getInstancia().pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			Iterator iteratorGuiaPagamentoitem = colecaoGuiaPagamentoItem.iterator();
			
			if(!colecaoGuiaPagamentoItem.isEmpty()){
				while(iteratorGuiaPagamentoitem.hasNext()){
					GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem)iteratorGuiaPagamentoitem.next();
					descricaoServicosTarifas = guiaPagamentoItem
					.getDebitoTipo().getDescricao() + "     " +  guiaPagamentoRelatorioHelper.getPrestacaoFormatada();
					
					valor = Util.formatarMoedaReal(guiaPagamentoItem
								.getValorDebito());
					
					relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(
							descricaoServicosTarifas, valor);
					
					colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
				}
			}else{
				descricaoServicosTarifas = guiaPagamentoRelatorioHelper
				.getDescTipoDebito() + "     " +  guiaPagamentoRelatorioHelper.getPrestacaoFormatada();
				
				valor = Util.formatarMoedaReal(guiaPagamentoRelatorioHelper
							.getValorDebito());
				
				relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(
						descricaoServicosTarifas, valor);
				
				colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
			}
			

			String valorTotal = Util.formatarMoedaReal(guiaPagamentoRelatorioHelper.getValorDebito());

			String matricula = guiaPagamentoRelatorioHelper
					.getMatriculaFormatada();
			String nomeCliente = "";
			if (guiaPagamentoRelatorioHelper.getNomeCliente() != null){
				nomeCliente = guiaPagamentoRelatorioHelper.getNomeCliente();
			}
			String dataVencimento = Util
					.formatarData(guiaPagamentoRelatorioHelper
							.getDataVencimento());
			String inscricao = guiaPagamentoRelatorioHelper.getInscricao();
			String enderecoImovel = guiaPagamentoRelatorioHelper
					.getEnderecoImovel();
			String enderecoClienteResponsavel = guiaPagamentoRelatorioHelper
					.getEnderecoClienteResponsavel();
			String representacaoNumericaCodBarraFormatada = guiaPagamentoRelatorioHelper
					.getRepresentacaoNumericaCodBarraFormatada();
			String representacaoNumericaCodBarraSemDigito = guiaPagamentoRelatorioHelper
					.getRepresentacaoNumericaCodBarraSemDigito();
			String dataValidade = guiaPagamentoRelatorioHelper
					.getDataValidade();
			String idGuiaPagamento = guiaPagamentoRelatorioHelper
					.getIdGuiaPagamento();
			
			String observacao = "";
			
			Short indicadorEmitirObservacao = guiaPagamentoRelatorioHelper.getIndicadorEmitirObservacao();
			if (indicadorEmitirObservacao != null && indicadorEmitirObservacao.equals(ConstantesSistema.SIM)) {
				observacao = guiaPagamentoRelatorioHelper.getObservacao();
			}
			
			String cpfCnpjCliente = "";
			
			if ( guiaPagamentoRelatorioHelper.getCpfCliente() != null 
					&& !guiaPagamentoRelatorioHelper.getCpfCliente().equals("") ) {
						cpfCnpjCliente = Util.formatarCpf(guiaPagamentoRelatorioHelper.getCpfCliente());
			} else if ( guiaPagamentoRelatorioHelper.getCnpjCliente() != null 
					&& !guiaPagamentoRelatorioHelper.getCnpjCliente().equals("") ) {
						cpfCnpjCliente = Util.formatarCnpj(guiaPagamentoRelatorioHelper.getCnpjCliente());
			}

			String idImovel = "";
			if (guiaPagamentoRelatorioHelper.getIdImovel() != null
					&& !guiaPagamentoRelatorioHelper.getIdImovel().equals("")) {
				idImovel = guiaPagamentoRelatorioHelper.getIdImovel().toString();
			} else if (guiaPagamentoRelatorioHelper.getIdCliente() != null) {
				idImovel = guiaPagamentoRelatorioHelper.getIdCliente().toString();
			}
			
			String nossoNumero = guiaPagamentoRelatorioHelper.getNossoNumero();
			String sacadoParte01 = guiaPagamentoRelatorioHelper.getSacadoParte01();
			String sacadoParte02 = guiaPagamentoRelatorioHelper.getSacadoParte02();

			String subRelatorio = guiaPagamentoRelatorioHelper.getSubRelatorio();
			
			RelatorioEmitirGuiaPagamentoBean bean = new RelatorioEmitirGuiaPagamentoBean(
					colecaoDetail, matricula, nomeCliente, dataVencimento,
					inscricao, enderecoImovel, enderecoClienteResponsavel,
					representacaoNumericaCodBarraFormatada,
					representacaoNumericaCodBarraSemDigito, dataValidade,
					valorTotal, idGuiaPagamento, observacao, cpfCnpjCliente, idImovel,
					nossoNumero, sacadoParte01, sacadoParte02, subRelatorio, colecaoDetail);
			
			colecaoDetail.clear();

			retorno.add(bean);

		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String[] ids = (String[]) getParametro("ids");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio = fachada
				.pesquisarGuiaPagamentoRelatorio(ids);

		Collection<RelatorioEmitirGuiaPagamentoBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
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
		if ( usuario != null ) {
			nomeUsuario = usuario.getNomeUsuario();
		}
		
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		
		parametros.put("dataVencimento", colecaoBean.iterator().next().getDataVencimento());
		
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("nomeUsuario", nomeUsuario);
		Integer anoAtual = Util.getAno(new Date());
		parametros.put("anoGuiaPagamento", ""+anoAtual);
		
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		
		List listArrayJRDetail = new ArrayList();
		RelatorioDataSource arrayJRDetailSub = new RelatorioDataSource(listArrayJRDetail);
		parametros.put("arrayJRDetailSub", arrayJRDetailSub);
		
		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioEmitirGuiaPagamentoBean>) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EMITIR,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.EMITIR_GUIA_PAGAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirGuiaPagamento", this);
	}
}

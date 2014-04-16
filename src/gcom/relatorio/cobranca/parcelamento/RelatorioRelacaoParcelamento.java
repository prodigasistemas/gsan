package gcom.relatorio.cobranca.parcelamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de 
 * [UC0580]Emitir Protocolo de Documento de Cobrança do Cronogrma
 * 
 * @author Ana Maria
 * @date 05/10/06
 * 
 */
public class RelatorioRelacaoParcelamento extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioRelacaoParcelamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA);
	}
	
	@Deprecated
	public RelatorioRelacaoParcelamento() {
		super(null, "");
	}

	private Collection<RelatorioRelacaoParcelamentoBean> inicializarBeanRelatorio(
			Collection<RelacaoParcelamentoRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioRelacaoParcelamentoBean> retorno = new ArrayList();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			RelacaoParcelamentoRelatorioHelper helper = (RelacaoParcelamentoRelatorioHelper)iterator.next();
			
			String situacao = "";
			if (helper.getSituacao() != null) {
				situacao = helper.getSituacao();
			}
			
			String localidade = "";
			if (helper.getLocalidade() != null) {
				localidade = helper.getIdGerencia() + "-" +helper.getGerencia()+"/"+ helper.getLocalidade();
			}
			
			String cliente = "";
			String telefone = "";
			if (helper.getCliente() != null) {
				cliente = helper.getCliente();
				if (helper.getTelefone() != null) {
					telefone = helper.getDdd()+" "+ helper.getTelefone();
				}
			}

			String matricula = "";
			if (helper.getMatricula() != null) {
				matricula = helper.getMatricula().toString();
			}
			
			String idParcelamento = "";
			if (helper.getParcelamento() != null) {
				idParcelamento = helper.getParcelamento().toString();
			}
			
			BigDecimal valorDebito = new BigDecimal("0.00");
			if (helper.getDebitoTotal() != null) {
				valorDebito = helper.getDebitoTotal();
			}
			
			BigDecimal valorEntrada = new BigDecimal("0.00");
			if (helper.getValorEntrada() != null) {
				valorEntrada = helper.getValorEntrada();
			}
			
			BigDecimal valorParcelas = new BigDecimal("0.00");
			if (helper.getValorParcelamento() != null) {
				valorParcelas = helper.getValorParcelamento();
			}
			
			String dataParcelamento = "";
			if (helper.getDataParcelamento() != null) {
				dataParcelamento = Util.formatarData(helper.getDataParcelamento());
			}
			
			String  vencimento = "";
			if (helper.getVencimento() != null) {
				vencimento = helper.getVencimento().toString();
				//vencimento = vencimento.substring(0, 2);
			}
			
			String numeroParcelas = "";
			if (helper.getNumeroParcelamento() != null) {
				numeroParcelas = helper.getNumeroParcelamento().toString();
			}
			
			String idLocalidade = "";
			if (helper.getIdLocalidade() != null) {
				idLocalidade = helper.getIdLocalidade().toString();
			}
			
			String idGerencia = "";
			if (helper.getIdGerencia() != null) {
				idGerencia = helper.getIdGerencia().toString();
			}			
			
			String gerencia = "";
			if (helper.getGerencia() != null) {
				gerencia = helper.getIdGerencia() + "-" +helper.getGerencia();
			}	
			
			String unidade = "";
			if (helper.getUnidade() != null) {
				unidade = helper.getUnidade();
			}
			
			String municipio = "";
			if(helper.getMunicipio() != null){
				municipio = helper.getMunicipio();
			}
			
			String idMunicipio = "";
			if(helper.getIdMunicipio() != null){
				idMunicipio = helper.getIdMunicipio().toString();
			}
			RelatorioRelacaoParcelamentoBean bean = new 
			RelatorioRelacaoParcelamentoBean(situacao, localidade, municipio,
					cliente, telefone, matricula, idParcelamento, valorDebito, valorEntrada,
					valorParcelas, dataParcelamento, vencimento,numeroParcelas, idLocalidade, 
					idMunicipio, idGerencia, gerencia, unidade);
			
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
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoRelacaoParcelamento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String cabecalho = (String)getParametro("cabecalho");
		String faixaValores = (String)getParametro("faixaValores");
		String periodo = (String)getParametro("periodo");
		
		String gerencia = (String)getParametro("parametrosGerencia");
		String unidadeOrganizacional = (String)getParametro("parametroUnidadeOrganizacional");
		String unidadeNegocio = (String)getParametro("parametroUnidadeNegocio");
		String elo = (String)getParametro("parametroElo");
		String periodoParcelamento = (String)getParametro("parametroPeriodo");
		String usuarioParcelamento = (String)getParametro("parametroUsuario");
		String perfilImovel = (String)getParametro("parametroPerfilImovel");
		String valorParcelamento = (String)getParametro("parametroValor");
		String municipio = (String)getParametro("municipio");
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("cabecalho", cabecalho);
		
		parametros.put("faixaValores" , faixaValores);
		
		parametros.put("periodo", periodo);
		
		//String parametrosFiltro = (String)getParametro("parametros");
		
		//parametros.put("parametros", parametrosFiltro );
		
		parametros.put("parametrosGerencia", gerencia );
		parametros.put("parametroUnidadeOrganizacional", unidadeOrganizacional );
		parametros.put("parametroUnidadeNegocio", unidadeNegocio );
		parametros.put("parametroElo", elo );
		parametros.put("parametroPeriodo", periodoParcelamento );
		parametros.put("parametroUsuario", usuarioParcelamento );
		parametros.put("parametroPerfilImovel", perfilImovel );
		parametros.put("parametroValor", valorParcelamento );
		parametros.put("parametroMunicipio", municipio);
		
		parametros.put("numeroRelatorio", "R0594");
		
		Collection<RelatorioRelacaoParcelamentoBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELACAO_PARCELAMENTO,
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
		int retorno = 0;

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoParcelamento", this);
	}
}

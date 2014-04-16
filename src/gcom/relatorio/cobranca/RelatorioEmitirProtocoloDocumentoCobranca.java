package gcom.relatorio.cobranca;

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
public class RelatorioEmitirProtocoloDocumentoCobranca extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioEmitirProtocoloDocumentoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA);
	}
	
	@Deprecated
	public RelatorioEmitirProtocoloDocumentoCobranca() {
		super(null, "");
	}

	private Collection<RelatorioEmitirProtocoloDocumentoCobrancaBean> inicializarBeanRelatorio(
			Collection<ProtocoloDocumentoCobrancaRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioEmitirProtocoloDocumentoCobrancaBean> retorno = new ArrayList();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			ProtocoloDocumentoCobrancaRelatorioHelper helper = 
				(ProtocoloDocumentoCobrancaRelatorioHelper)iterator.next();
			
			String empresa = "";
			
			if (helper.getEmpresa() != null) {
				empresa = helper.getEmpresa();
			}
			
			String idLocalidade = "";
			if(helper.getIdLocalidade() != null){
				idLocalidade = helper.getIdLocalidade().toString();				
			}
			
			String localidade = "";
			if(helper.getLocalidade() != null){
			  localidade = helper.getIdLocalidade()+" - "+helper.getLocalidade();
			}
			
			String setor = "";
			if(helper.getIdSetorComercial() != null){
			  setor = helper.getIdSetorComercial().toString();	
			}			
			
			String quantidadeDocumento = "";
			if(helper.getQuantidade() != null){
				quantidadeDocumento = helper.getQuantidade().toString();	
			}
			
			BigDecimal valorDocumento = new BigDecimal("0.00");
			if(helper.getValorDocumentos() != null){
				valorDocumento = helper.getValorDocumentos();	
			}
			
			Integer seqInicial = null;
			if(helper.getSeqInicial() != null){
				seqInicial = helper.getSeqInicial();	
			}
			
			Integer seqFinal = null;
			if(helper.getSeqFinal() != null){
				seqFinal = helper.getSeqFinal();	
			}
			
			String idGerencia = "";
			if(helper.getIdGerencia() != null){
				idGerencia = helper.getIdGerencia().toString();				
			}
			
			String gerencia = "";
			if(helper.getGerencia() != null){
				gerencia = helper.getIdGerencia()+" - "+helper.getGerencia()+" - "+helper.getNomeGerencia();
			}
			
			String idUnidadeNegocio = "";
			if(helper.getIdUnidadeNegocio() != null){
				idUnidadeNegocio = helper.getIdUnidadeNegocio().toString();
			}
			
			String nomeUnidadeNegocio = "";
			if(helper.getNomeUnidadeNegocio() != null){
				nomeUnidadeNegocio = helper.getNomeUnidadeNegocio();
			}
			
			RelatorioEmitirProtocoloDocumentoCobrancaBean bean = new 
					RelatorioEmitirProtocoloDocumentoCobrancaBean(empresa, idLocalidade, localidade, 
							setor, quantidadeDocumento, valorDocumento, seqInicial, seqFinal, 
							idGerencia, gerencia, idUnidadeNegocio, nomeUnidadeNegocio);
			
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
		
		Collection dadosRelatorio = (Collection)getParametro("protocoloDocumentoCobranca");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String R0000 = (String) getParametro("R0000");
		String grupo = (String)getParametro("grupo");
		String primeiroTitulo = (String)getParametro("primeiroTitulo");
		String acaoCobranca = (String)getParametro("acaoCobranca");
		//String mesAnoCobranca = (String)getParametro("anoMesCobranca");
		//String dataRealizacao = (String)getParametro("dataRealizacao");
		//String horaRealizacao = (String)getParametro("horaRealizacao");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("R0000", R0000);
		
		parametros.put("grupo", grupo);
		
		parametros.put("primeiroTitulo" , primeiroTitulo);
		
		parametros.put("acaoCobranca", acaoCobranca);
		
		//parametros.put("mesAnoCobranca", mesAnoCobranca);
		
		//parametros.put("dataRealizacao", dataRealizacao);
		
		//parametros.put("horaRealizacao", horaRealizacao);

		Collection<RelatorioEmitirProtocoloDocumentoCobrancaBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA,
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
		AgendadorTarefas.agendarTarefa("RelatorioEmitirProtocoloDocumentoCobranca", this);
	}
}

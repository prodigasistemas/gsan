package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de Relatorio de Acompanhamento de Registro de Atendimento
 * 
 * [UC1056] - Gerar Relatório de Acompanhamento dos Registros de Atendimento
 * 
 * @author Hugo Leonardo
 *
 * @date 28/09/2010
 */
public class RelatorioAcompanhamentoRegistroAtendimentoAnalitico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioAcompanhamentoRegistroAtendimentoAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_ANALITICO);
	}

	@Deprecated
	public RelatorioAcompanhamentoRegistroAtendimentoAnalitico() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		FiltrarAcompanhamentoRegistroAtendimentoHelper relatorioHelper = 
			(FiltrarAcompanhamentoRegistroAtendimentoHelper) getParametro("filtroHelper");
		// ------------------------------------
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String situacao = (String)  getParametro("situacao");
		
		String periodoAbertura = (String)  getParametro("periodoAbertura");
		
		String periodoEncerramento = (String)  getParametro("periodoEncerramento");
		
		/*
		 * O objeto que vai ser agrupado pode ser o município ou a unidade de atendimento
		 * Depende do filtro que o usuário escolher (Se for escolhido pelo menos um
		 * município associado então o resultado vai ser agrupado por município. 
		 */
		String objetoGroupAtual = "";
		String objetoGroupAnterior = "";
		boolean estadoMunicipio;
		if(!Util.isVazioOrNulo(relatorioHelper.getMunicipiosAssociados())){
			estadoMunicipio = true;
		}else{
			estadoMunicipio = false;
		}
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean = null;
		RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean1 = null;
		RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean2 = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentos = fachada.pesquisarRelatorioAcompanhamentoRAAnalitico(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRegistroAtendimentos != null && !colecaoRegistroAtendimentos.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecaoRegistroAtendimentos.iterator();
			
			Map<String, RelatorioMapHelper> countMotivoEncerramentoMap = new HashMap<String, RelatorioMapHelper>();
			
			Map<String, RelatorioMapHelper> countMotivoEncerramentoUnidadeMap = new HashMap<String, RelatorioMapHelper>();
			
			RelatorioMapHelper relatorioMapHelper;
			
			RelatorioMapHelper relatorioMapUnidadeHelper;
			
			RelatorioAcompanhamentoRegistroAtendimentoBean beanFooter;
			
			boolean flag = false;
			
			RelatorioMapHelper helperMap;
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
				RelatorioAcompanhamentoRAHelper helper = (RelatorioAcompanhamentoRAHelper) colecaoIterator.next();

				if(estadoMunicipio){
					objetoGroupAtual = helper.getIdMunicipio();
					relatorioBean = new RelatorioAcompanhamentoRegistroAtendimentoBean(
							helper.getIdRA() != null ? helper.getIdRA().toString() : "",
							helper.getTipoSolicitacao() != null ? helper.getTipoSolicitacao().toString() : "",		
							helper.getDataAbertura() != null ? helper.getDataAbertura() : "",	
							helper.getDataEncerramento() != null ? helper.getDataEncerramento().toString().toString() : "",	
							helper.getMotivoEncerramento() != null ? helper.getMotivoEncerramento().toString() : "",
							null, null,
							helper.getIdMunicipio() != null ? helper.getIdMunicipio().toString() : "",
							helper.getDescricaoMunicipio() != null ? helper.getDescricaoMunicipio().toString() : "");
				}else{
					objetoGroupAtual = helper.getIdUnidadeAtendimento();
					relatorioBean = new RelatorioAcompanhamentoRegistroAtendimentoBean(
							
							helper.getIdRA() != null ? helper.getIdRA().toString() : "",
							helper.getTipoSolicitacao() != null ? helper.getTipoSolicitacao().toString() : "",		
							helper.getDataAbertura() != null ? helper.getDataAbertura() : "",	
							helper.getDataEncerramento() != null ? helper.getDataEncerramento().toString().toString() : "",	
							helper.getMotivoEncerramento() != null ? helper.getMotivoEncerramento().toString() : "",
							helper.getIdUnidadeAtendimento() != null ? helper.getIdUnidadeAtendimento().toString() : "",
							helper.getDescricaoUnidadeAtendimento() != null ? helper.getDescricaoUnidadeAtendimento().toString() : "",
							null, null);
				}
				
				if(!flag){
					
					if(estadoMunicipio){
						objetoGroupAtual = helper.getIdMunicipio();
						objetoGroupAnterior = helper.getIdMunicipio();
					}else{
						objetoGroupAtual = helper.getIdUnidadeAtendimento();
						objetoGroupAnterior = helper.getIdUnidadeAtendimento();
					}
					flag = true;
				}
				
				// Contabiliza a quantidade de motivos de encerramento por Unidade de Atendimento
				// Armazenando no Map a quantidade por motivo.
				if(objetoGroupAtual.equals(objetoGroupAnterior)){
					
					if(!countMotivoEncerramentoUnidadeMap.containsKey(helper.getMotivoEncerramento())){
						
						relatorioMapUnidadeHelper = new RelatorioMapHelper(
								new Integer(objetoGroupAtual), new Integer(1) );
						
						countMotivoEncerramentoUnidadeMap.put(helper.getMotivoEncerramento(), relatorioMapUnidadeHelper);
					}else{
						
						relatorioMapUnidadeHelper = countMotivoEncerramentoUnidadeMap.get(helper.getMotivoEncerramento());
						relatorioMapUnidadeHelper.setQuantidade( relatorioMapUnidadeHelper.getQuantidade() + 1 );
						
						countMotivoEncerramentoUnidadeMap.put(helper.getMotivoEncerramento(), relatorioMapUnidadeHelper);
					}
				}else{
					
					relatorioBean1 = new RelatorioAcompanhamentoRegistroAtendimentoBean();
					relatorioBean2 = new RelatorioAcompanhamentoRegistroAtendimentoBean();
					if(estadoMunicipio){
						relatorioBean1.setIdMunicipio(objetoGroupAnterior);
						relatorioBean2.setIdMunicipio(objetoGroupAnterior);
					}else{
						relatorioBean1.setIdUnidadeAtendimento(objetoGroupAnterior);
						relatorioBean2.setIdUnidadeAtendimento(objetoGroupAnterior);
					}
					relatorioBeans.add(relatorioBean1);
					relatorioBean2.setQuantidade(" ");
					relatorioBean2.setSituacaoRA(situacao);
					relatorioBeans.add(relatorioBean2);
					
					// Se mudar o objeto percorre o map e adiciona na 
					// coleção de Bens os totalizadores do objeto. Depois, limpa o Map para o próximo objeto e 
					// armazena os totalizadores do novo objeto no Map. 
					for (Map.Entry<String, RelatorioMapHelper> entry : countMotivoEncerramentoUnidadeMap.entrySet()) {
						
						String motivo = entry.getKey();
						helperMap = entry.getValue();

						if(estadoMunicipio){
							beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean(
									null, motivo, helperMap.getQuantidade().toString(), helperMap.getId().toString());
						}else{
							beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean(
									helperMap.getId().toString(), motivo, helperMap.getQuantidade().toString(), null);
						}
						relatorioBeans.add(beanFooter);
					}
					
					countMotivoEncerramentoUnidadeMap.clear();
					
					if(!countMotivoEncerramentoUnidadeMap.containsKey(helper.getMotivoEncerramento())){
						
						relatorioMapUnidadeHelper = new RelatorioMapHelper(
								new Integer(objetoGroupAtual), new Integer(1) );
						
						countMotivoEncerramentoUnidadeMap.put(helper.getMotivoEncerramento(), relatorioMapUnidadeHelper);
					}
					
				}
				
				if(!countMotivoEncerramentoMap.containsKey(helper.getMotivoEncerramento())){

					relatorioMapHelper = new RelatorioMapHelper(
							new Integer(objetoGroupAtual), new Integer(1) );
					
					countMotivoEncerramentoMap.put(helper.getMotivoEncerramento(), relatorioMapHelper);
					
				}else{

					relatorioMapHelper = countMotivoEncerramentoMap.get(helper.getMotivoEncerramento());
					relatorioMapHelper.setQuantidade( relatorioMapHelper.getQuantidade() + 1 );
					
					countMotivoEncerramentoMap.put(helper.getMotivoEncerramento(), relatorioMapHelper);
				}
				
				if(estadoMunicipio){
					objetoGroupAnterior = helper.getIdMunicipio();
				}else{
					objetoGroupAnterior = helper.getIdUnidadeAtendimento();
				}
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
			
			if(!countMotivoEncerramentoUnidadeMap.isEmpty()){
				
				relatorioBean1 = new RelatorioAcompanhamentoRegistroAtendimentoBean();
				relatorioBean2 = new RelatorioAcompanhamentoRegistroAtendimentoBean();
				if(estadoMunicipio){
					relatorioBean1.setIdMunicipio(objetoGroupAnterior);
					relatorioBean2.setIdMunicipio(objetoGroupAnterior);
				}else{
					relatorioBean1.setIdUnidadeAtendimento(objetoGroupAnterior);
					relatorioBean2.setIdUnidadeAtendimento(objetoGroupAnterior);
				}
				relatorioBeans.add(relatorioBean1);
				relatorioBean2.setQuantidade(" ");
				relatorioBean2.setSituacaoRA(situacao);
				relatorioBeans.add(relatorioBean2);
				
				// Se mudar o objeto percorre o map e adiciona na 
				// coleção de Bens os totalizadores do objeto. Depois, limpa o Map para o próximo objeto e 
				// armazena os totalizadores do novo objeto no Map. 
				for (Map.Entry<String, RelatorioMapHelper> entry : countMotivoEncerramentoUnidadeMap.entrySet()) {
					
					String motivo = entry.getKey();
					helperMap = entry.getValue();

					if(estadoMunicipio){
						beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean(
								null, motivo, helperMap.getQuantidade().toString(), helperMap.getId().toString());
					}else{
						beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean(
								helperMap.getId().toString(), motivo, helperMap.getQuantidade().toString(),null);
					}
					relatorioBeans.add(beanFooter);
				}
			}
			
			relatorioBean1 = new RelatorioAcompanhamentoRegistroAtendimentoBean();
			relatorioBean2 = new RelatorioAcompanhamentoRegistroAtendimentoBean();
			if(estadoMunicipio){
				relatorioBean1.setIdMunicipio(objetoGroupAnterior);
				relatorioBean2.setIdMunicipio(objetoGroupAnterior);
			}else{
				relatorioBean1.setIdUnidadeAtendimento(objetoGroupAnterior);
				relatorioBean2.setIdUnidadeAtendimento(objetoGroupAnterior);
			}
			relatorioBeans.add(relatorioBean1);
			relatorioBeans.add(relatorioBean1);
			
			relatorioBean2.setQuantidade(" ");
			relatorioBean2.setSituacaoRA(situacao);
			relatorioBean2.setTotalGeral("*");
			relatorioBeans.add(relatorioBean2);
			
			for (Map.Entry<String, RelatorioMapHelper> entry : countMotivoEncerramentoMap.entrySet()) {
				
				String motivo = entry.getKey();
				helperMap = entry.getValue();

				if(estadoMunicipio){
					beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean(
							null, motivo, helperMap.getQuantidade().toString(), objetoGroupAnterior );
				}else{
					beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean(
							objetoGroupAnterior, motivo, helperMap.getQuantidade().toString(), null );
				}
				relatorioBeans.add(beanFooter);
			}

		}else{
			
			relatorioBean = new RelatorioAcompanhamentoRegistroAtendimentoBean();
			relatorioBeans.add(relatorioBean);
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada	.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("periodoAbertura", periodoAbertura);
		parametros.put("periodoEncerramento", periodoEncerramento);
		
		Integer idsituacao = new Integer(situacao);

		switch (idsituacao) {
			// Aberta
			case 0:
				parametros.put("situacao", "Aberta");
				break;
				
			// Encerrados
			case 1:
				parametros.put("situacao", "Encerrado");
				break;
				
			default:
				break;
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(colecaoRegistroAtendimentos != null && colecaoRegistroAtendimentos.size() > 0){
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_ANALITICO,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
			
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ACOMPANHAMENTO_RA,
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
		
		retorno = Fachada.getInstancia().pesquisarTotalRelatorioAcompanhamentoRAAnalitico(
				(FiltrarAcompanhamentoRegistroAtendimentoHelper) getParametro("filtroHelper"));
		   
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {

		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoRegistroAtendimentoAnalitico", this);
	}

}

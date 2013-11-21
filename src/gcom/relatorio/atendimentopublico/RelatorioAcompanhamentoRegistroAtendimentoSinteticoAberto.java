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
 * @author Hugo Leonardo, Diogo Peixoto
 *
 * @date 28/09/2010, 29/04/2011
 */
public class RelatorioAcompanhamentoRegistroAtendimentoSinteticoAberto extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioAcompanhamentoRegistroAtendimentoSinteticoAberto(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ABERTO);
	}

	@Deprecated
	public RelatorioAcompanhamentoRegistroAtendimentoSinteticoAberto() {
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
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentos = fachada.pesquisarRelatorioAcompanhamentoRASinteticoAberto(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoRegistroAtendimentos != null && !colecaoRegistroAtendimentos.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecaoRegistroAtendimentos.iterator();
			
			Integer contador = 0;
			RelatorioAcompanhamentoRAHelper helper = null;
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
				helper = (RelatorioAcompanhamentoRAHelper) colecaoIterator.next();

				relatorioBean = new RelatorioAcompanhamentoRegistroAtendimentoBean();
				
				relatorioBean.setDescricaoUnidadeAtendimento(helper.getDescricaoUnidadeAtendimento());
				relatorioBean.setDescricaoMunicipio(helper.getDescricaoMunicipio());
				relatorioBean.setQuantidadeTotal(helper.getQuantidade());
				
				contador += helper.getQuantidade();
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
			
			RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean1 = 
				new RelatorioAcompanhamentoRegistroAtendimentoBean();
			relatorioBean1.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			
			RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean2 = 
				new RelatorioAcompanhamentoRegistroAtendimentoBean();
			relatorioBean2.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			
			RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean3 = 
				new RelatorioAcompanhamentoRegistroAtendimentoBean();
			
			relatorioBean3.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			relatorioBean3.setQuantidadeTotal(contador);
			relatorioBean3.setQuantidade("");
			relatorioBeans.add(relatorioBean1);
			relatorioBeans.add(relatorioBean2);
			relatorioBeans.add(relatorioBean3);
			
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
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ABERTO,
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

		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoRegistroAtendimentoSinteticoAberto", this);
	}

}

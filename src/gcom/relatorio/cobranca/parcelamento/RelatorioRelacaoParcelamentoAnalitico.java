package gcom.relatorio.cobranca.parcelamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0878] Gerar Relação de Parcelamento - Visão Analitica
 * 
 * Classe que cria o pdf do relatorio
 * 
 * @author Bruno Barros
 * @date 04/02/2009
 *
 */
public class RelatorioRelacaoParcelamentoAnalitico extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioRelacaoParcelamentoAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO);
	}
	
	@Deprecated
	public RelatorioRelacaoParcelamentoAnalitico() {
		super(null, "");
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoRelacaoParcelamentoAnalitico");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String cabecalho = (String)getParametro("cabecalho");
		//String parametrosFiltro = (String)getParametro("parametros");
		
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
		//parametros.put("parametros", parametrosFiltro );
		parametros.put("numeroRelatorio", "R0878");
		
		parametros.put("parametrosGerencia", gerencia );
		parametros.put("parametroUnidadeOrganizacional", unidadeOrganizacional );
		parametros.put("parametroUnidadeNegocio", unidadeNegocio );
		parametros.put("parametroElo", elo );
		parametros.put("parametroPeriodo", periodoParcelamento );
		parametros.put("parametroUsuario", usuarioParcelamento );
		parametros.put("parametroPerfilImovel", perfilImovel );
		parametros.put("parametroValor", valorParcelamento );
		parametros.put("parametroMunicipio", municipio);
		
		if (dadosRelatorio == null || dadosRelatorio.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) dadosRelatorio);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO_ANALITICO,
				parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoParcelamento", this);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return -1;
	}
}

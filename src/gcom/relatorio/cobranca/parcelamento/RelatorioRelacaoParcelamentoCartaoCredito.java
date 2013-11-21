package gcom.relatorio.cobranca.parcelamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0998] Gerar Relação de Parcelamento - Visão Cartão de Crédito
 * 
 * Bean que preencherá o relatorio
 * 
 * @author Hugo Amorim
 * @date 11/06/2010
 *
 */
public class RelatorioRelacaoParcelamentoCartaoCredito extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioRelacaoParcelamentoCartaoCredito(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO_CARTAO_CREDITO);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoParcelamentoCartaoCredito", this);

	}

	@Override
	public Object executar() throws TarefaException {
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoRelacaoParcelamentoCartaoCredito");
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

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
		parametros.put("cabecalho", cabecalho);
		//parametros.put("parametros", parametrosFiltro );
		parametros.put("numeroRelatorio", "R0998");
		
		parametros.put("parametrosGerencia", gerencia );
		parametros.put("parametroUnidadeOrganizacional", unidadeOrganizacional );
		parametros.put("parametroUnidadeNegocio", unidadeNegocio );
		parametros.put("parametroElo", elo );
		parametros.put("parametroPeriodo", periodoParcelamento );
		parametros.put("parametroUsuario", usuarioParcelamento );
		parametros.put("parametroPerfilImovel", perfilImovel );
		parametros.put("parametroValor", valorParcelamento );
		

		if (dadosRelatorio == null || dadosRelatorio.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) dadosRelatorio);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO_CARTAO_CREDITO,
				parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

}

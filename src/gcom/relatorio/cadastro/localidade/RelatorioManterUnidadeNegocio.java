package gcom.relatorio.cadastro.localidade;

import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * @author Arthur Carvalho
 * @date 01/07/09
 */

public class RelatorioManterUnidadeNegocio extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterUnidadeNegocio(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_UNIDADE_NEGOCIO_MANTER );
	}
	
	@Deprecated
	public RelatorioManterUnidadeNegocio() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroUnidadeNegocio filtroUnidadeNegocio = (FiltroUnidadeNegocio) getParametro("filtroUnidadeNegocio");
		UnidadeNegocio unidadeNegocioParametros = (UnidadeNegocio) getParametro("unidadeNegocioParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocio.GERENCIA);
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterUnidadeNegocioBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
				UnidadeNegocio.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator unidadeNegocioIterator = colecaoUnidadeNegocio.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (unidadeNegocioIterator.hasNext()) {

				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) unidadeNegocioIterator.next();

				//Gerencia Regional
				String gerenciaRegional = "";
			    if ( unidadeNegocio.getGerenciaRegional() != null && !unidadeNegocio.getGerenciaRegional().equals("") ) {
			    	gerenciaRegional = "" + unidadeNegocio.getGerenciaRegional().getNome();
			    }
			
				
				relatorioBean = new RelatorioManterUnidadeNegocioBean(
				
						//ID
						"" + unidadeNegocio.getId(),
						
						//Nome
						unidadeNegocio.getNome(),
						
						//Nome Abreviado
						unidadeNegocio.getNomeAbreviado(),
						
						//Gerencia Regional
						gerenciaRegional
						
				);
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		//ID
		String codigo = "";
		if ( unidadeNegocioParametros.getId() != null && !unidadeNegocioParametros.getId().equals("") ) {
			codigo = "" + unidadeNegocioParametros.getId();
		}
		parametros.put("codigo", codigo ) ;

		//Nome
		String nome = "";
		if (unidadeNegocioParametros.getNome() != null && !unidadeNegocioParametros.getNome().equals("") ) {
			nome = unidadeNegocioParametros.getNome();
		}
		parametros.put( "nome", nome );
		
		//Nome Abreviado
		String nomeAbreviado = "";
		if ( unidadeNegocioParametros.getNomeAbreviado() != null && !unidadeNegocioParametros.getNomeAbreviado().equals("") ) {
			nomeAbreviado = unidadeNegocioParametros.getNomeAbreviado();
		}
		parametros.put( "nomeAbreviado", nomeAbreviado ) ;
		
		//Cnpj
		String cnpj = "";
		if ( unidadeNegocioParametros.getCnpj() != null && !unidadeNegocioParametros.getCnpj().equals("") ) {
			cnpj = unidadeNegocioParametros.getCnpjFormatado();
		}
		parametros.put( "cnpj",  cnpj );
		
		//Gerente da Unidade de Negocio
		String cliente = "";
		if ( unidadeNegocioParametros.getCliente() != null && !unidadeNegocioParametros.getCliente().equals("") ) {
			cliente = unidadeNegocioParametros.getCliente().getDescricao();
		}
		parametros.put("cliente" , cliente) ;
		
		//Gerencia Regional
		String gerenciaRegional = "";
		if ( unidadeNegocioParametros.getGerenciaRegional() != null && 
				!unidadeNegocioParametros.getGerenciaRegional().equals("") ) {
			gerenciaRegional = unidadeNegocioParametros.getGerenciaRegional().getNome();
		}		
		parametros.put("gerenciaRegional", gerenciaRegional);
		
		
		//Indicador de Uso
		String indicadorUso = "";

		if (unidadeNegocioParametros.getIndicadorUso() != null
				&& !unidadeNegocioParametros.getIndicadorUso().equals("")) {
			if (unidadeNegocioParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (unidadeNegocioParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}
		parametros.put("indicadorUso", indicadorUso);
	
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_UNIDADE_NEGOCIO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterUnidadeNegocio", this);
	}

}

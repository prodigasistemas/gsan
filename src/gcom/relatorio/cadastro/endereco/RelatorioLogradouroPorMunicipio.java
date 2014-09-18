package gcom.relatorio.cadastro.endereco;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
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
 * [UC00728] Gerar Relatório Logradouros por Municipio
 * 
 * @author Wallace Thierre
 * @date 02/09/2010
 */

public class RelatorioLogradouroPorMunicipio extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;

	public RelatorioLogradouroPorMunicipio(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LOGRADOURO_POR_MUNICIPIO);
		
	}

	@Deprecated
	public RelatorioLogradouroPorMunicipio() {
		super(null, "");
	}


	@Override
	public Object executar() throws TarefaException {
		// ------------------------------------
		//Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection colecaoLogradouroBairro = (Collection) getParametro("colecaoLogradourosBairro");
		String nomeMunicipio = (String) getParametro("nomeMunicipio");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioLogradouroPorMunicipioBean relatorioBean = null;	

		// se a coleção de parâmetros da analise não for vazia				
		if (colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator logradouroBairroIterator = colecaoLogradouroBairro.iterator();

			// laço para criar a coleção de parâmetros da analise					
			while (logradouroBairroIterator.hasNext()) {						

				LogradouroBairro logradouroBairro = (LogradouroBairro) logradouroBairroIterator.next();

				relatorioBean = new RelatorioLogradouroPorMunicipioBean(

						logradouroBairro.getLogradouro().getLogradouroTipo().getDescricao(),

						logradouroBairro.getBairro().getNome(),
						
						logradouroBairro.getLogradouro().getLogradouroTitulo()!=null?
								
						" "+logradouroBairro.getLogradouro().getLogradouroTitulo().getDescricao():"",
						
						logradouroBairro.getLogradouro().getNome());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("nomeMunicipio", nomeMunicipio);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1063");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_LOGRADOURO_POR_MUNICIPIO,
				parametros, ds, tipoFormatoRelatorio);


		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		if (getParametro("logradouros") != null
				&& getParametro("logradouros") instanceof Collection) {
			retorno = ((Collection) getParametro("logradouros")).size();
		}
		if(retorno == 0){

		}else{

		}

		return retorno;
	}


	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioLogradouroPorMunicipio", this);

	}

}

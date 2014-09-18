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
 * [UC00728] Gerar Relat�rio Logradouros por Municipio
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

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioLogradouroPorMunicipioBean relatorioBean = null;	

		// se a cole��o de par�metros da analise n�o for vazia				
		if (colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator logradouroBairroIterator = colecaoLogradouroBairro.iterator();

			// la�o para criar a cole��o de par�metros da analise					
			while (logradouroBairroIterator.hasNext()) {						

				LogradouroBairro logradouroBairro = (LogradouroBairro) logradouroBairroIterator.next();

				relatorioBean = new RelatorioLogradouroPorMunicipioBean(

						logradouroBairro.getLogradouro().getLogradouroTipo().getDescricao(),

						logradouroBairro.getBairro().getNome(),
						
						logradouroBairro.getLogradouro().getLogradouroTitulo()!=null?
								
						" "+logradouroBairro.getLogradouro().getLogradouroTitulo().getDescricao():"",
						
						logradouroBairro.getLogradouro().getNome());

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("nomeMunicipio", nomeMunicipio);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1063");

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_LOGRADOURO_POR_MUNICIPIO,
				parametros, ds, tipoFormatoRelatorio);


		// retorna o relat�rio gerado
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

package gcom.relatorio.cadastro.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
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
 * [UC0999] Gerar Relat�rio de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 *
 * @date 08/03/2010
 */
public class RelatorioColetaMedidorEnergia extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioColetaMedidorEnergia(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COLETA_MEDIDOR_ENERGIA);
	}

	@Deprecated
	public RelatorioColetaMedidorEnergia() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioColetaMedidorEnergiaHelper filtro = 
			(FiltrarRelatorioColetaMedidorEnergiaHelper) getParametro("filtrarRelatorioColetaMedidorEnergiaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioColetaMedidorEnergiaBean relatorioColetaMedidorEnergiaBean = null;

		Collection<RelatorioColetaMedidorEnergiaHelper> colecao =   
			fachada.pesquisarRelatorioColetaMedidorEnergia(filtro);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioColetaMedidorEnergiaHelper helper = 
					(RelatorioColetaMedidorEnergiaHelper) colecaoIterator.next();
				
				relatorioColetaMedidorEnergiaBean = 
					new RelatorioColetaMedidorEnergiaBean(helper);
				
				
				relatorioColetaMedidorEnergiaBean.setInscricaoImovel(
						fachada.pesquisarInscricaoImovel(Integer.parseInt(helper.getMatriculaImovel())));
				
				try {
					relatorioColetaMedidorEnergiaBean.setEndereco(
							fachada.pesquisarEnderecoFormatado(new Integer(helper.getMatriculaImovel())));
				} catch (NumberFormatException e) {
					
					e.printStackTrace();
				} catch (ControladorException e) {
					
					e.printStackTrace();
				}
			
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioColetaMedidorEnergiaBean);				
				
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R0999");

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COLETA_MEDIDOR_ENERGIA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_COLETA_MEDIDOR_ENERGIA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().countRelatorioColetaMedidorEnergia(
				(FiltrarRelatorioColetaMedidorEnergiaHelper) 
					getParametro("filtrarRelatorioColetaMedidorEnergiaHelper"));
		
		if (retorno == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao
			// usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");

		}

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioColetaMedidorEnergia", this);

	}

}

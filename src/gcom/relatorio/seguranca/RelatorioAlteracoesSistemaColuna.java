package gcom.relatorio.seguranca;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
 * 
 * @author Hugo Amorim
 * @date 08/09/2010
 */
public class RelatorioAlteracoesSistemaColuna extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioAlteracoesSistemaColuna(Usuario usuario,String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public RelatorioAlteracoesSistemaColuna() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		// helper pesquisa
		GerarRelatorioAlteracoesSistemaColunaHelper helper = 
			(GerarRelatorioAlteracoesSistemaColunaHelper) getParametro("filtroHelper");
		
		//  tipo relatorio
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("coluna", helper.getDescColuna());
		parametros.put("periodo", helper.getPeriodoInicial() + " A " + helper.getPeriodoFinal());
		parametros.put("imagem", fachada.pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("relatorio", "R1047");

		Collection<Object[]> colecaoDados = fachada.pesquisarDadosRelatorioAlteracoesSistemaColuna(helper);
		
		for (Object[] objetos : colecaoDados) {
			
			RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = null;
			
			switch (Integer.parseInt(helper.getTipoRelatorio())) {
			//TIPO POR USUARIO
			case 1:
				
				relatorioAlteracoesSistemaColunaBean = new RelatorioAlteracoesSistemaColunaBean(
						//unidadeSuperior
						objetos[0]!=null?(String)objetos[0]:null,
						//unidadeOrganizacional
						objetos[1]!=null?(String)objetos[1]:null,
						//usuario	
						objetos[2]!=null?(String)objetos[2]:null,
						//meio
						objetos[3]!=null?(String)objetos[3]:null,
						//quantidade		
						objetos[4]!=null?(Integer)objetos[4]:null);
				break;
			//TIPO POR LOCALIDADE
			case 2:
	
				relatorioAlteracoesSistemaColunaBean = new RelatorioAlteracoesSistemaColunaBean(
						//meio
						objetos[3]!=null?(String)objetos[3]:null,
						//quantidade		
						objetos[4]!=null?(Integer)objetos[4]:null,
						//gerenciaRegional
						objetos[0]!=null?(String)objetos[0]:null,
						//unidadeNegocio
						objetos[1]!=null?(String)objetos[1]:null,
						//localidade	
						objetos[2]!=null?(String)objetos[2]:null);
				break;	

			default:
				break;
			}
			
			relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
		}
		
		//Caso não possua dados aprensetar mensagem de relatorio vazio ao usuario.
		if(Util.isVazioOrNulo(relatorioBeans)){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = 
				new RelatorioAlteracoesSistemaColunaBean();
			
			relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
		}else if(helper.getTipoRelatorio().equals("1")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_SISTEMA_COLUNA_USUARIO;
		}else if(helper.getTipoRelatorio().equals("2")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_SISTEMA_COLUNA_LOCALIDADE;
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(this.nomeRelatorio,parametros, ds, tipoFormatoRelatorio);
			
		// ------------------------------------
		// Grava o relatório no sistema
		try {

			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			
			persistirRelatorioConcluido(retorno,
					Relatorio.REL_ALTERACOES_NO_SISTEMA_COLUNA,
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
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAlteracoesSistemaColuna", this);
	}


}

package gcom.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
 * 
 * @autor Mariana Victor
 * @date 16/02/2011
 */
public class RelatorioAlteracoesCpfCnpj extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioAlteracoesCpfCnpj(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public RelatorioAlteracoesCpfCnpj() {
		super(null, "");
	}
	
	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		// helper pesquisa
		GerarRelatorioAlteracoesCpfCnpjHelper helper = 
			(GerarRelatorioAlteracoesCpfCnpjHelper) getParametro("filtroHelper");
		
		//  tipo relatorio
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		String meio = "";
		
		if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())){
			String[] colecaoMeio = helper.getColecaoMeio(); 
			Collection<Integer> idsMeio = new ArrayList<Integer>();
			for (int i = 0; i < colecaoMeio.length; i++) {
				idsMeio.add(Integer.parseInt(colecaoMeio[i]));
			}
			if (!idsMeio.isEmpty()) {
				FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
				
				Collection colecaoMeioSolicitacao = fachada.pesquisar(idsMeio, filtroMeioSolicitacao, MeioSolicitacao.class.getName());
				
				Iterator iterator = colecaoMeioSolicitacao.iterator();
				while (iterator.hasNext()) {
					MeioSolicitacao meioSolicitacao = (MeioSolicitacao) iterator.next();
					meio += meioSolicitacao.getDescricao();
					meio += ", ";
				}
			}
		}

		if (!meio.equals("")) {
			meio = meio.substring(0, meio.length() - 2);
			parametros.put("meio", meio);		
		}
		
		parametros.put("periodo", helper.getPeriodoInicial() + " A " + helper.getPeriodoFinal());
		parametros.put("imagem", fachada.pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("relatorio", "R1124");
		
		if (Integer.parseInt(helper.getTipoRelatorio()) ==  2){
			if (helper.getOpcaoTotalizacao() != null
					&& !helper.getOpcaoTotalizacao().equals("")) {
				
				parametros.put("codigoOpcaoTotalizacao", helper.getOpcaoTotalizacao());

				if (helper.getOpcaoTotalizacao().equals("estado")) {

					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - ESTADO");
					parametros.put("gerenciaRegionalHeader", false);
					parametros.put("unidadeHeader", false);
					parametros.put("localidadeHeader", false);
					parametros.put("gerenciaRegionalFooter", false);
					
				} else if (helper.getOpcaoTotalizacao().equals("estadoGerencia")) {
					
					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - ESTADO POR GERENCIA REGIONAL");
					parametros.put("nomeGerencia", "Gerência Regional");
					parametros.put("gerenciaRegionalHeader", true);
					parametros.put("unidadeHeader", false);
					parametros.put("localidadeHeader", false);
					parametros.put("gerenciaRegionalFooter", false);
					
				}  else if (helper.getOpcaoTotalizacao().equals("estadoUnidadeNegocio")) {

					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - ESTADO POR UNIDADE DE NEGÓCIO");
					parametros.put("nomeUnidade", "Unidade de Negócio");
					parametros.put("gerenciaRegionalHeader", false);
					parametros.put("unidadeHeader", true);
					parametros.put("localidadeHeader", false);
					parametros.put("gerenciaRegionalFooter", false);
					
				}   else if (helper.getOpcaoTotalizacao().equals("estadoLocalidade")) {

					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - ESTADO POR LOCALIDADE");
					parametros.put("nomeLocalidade", "Localidade");
					parametros.put("gerenciaRegionalHeader", false);
					parametros.put("unidadeHeader", false);
					parametros.put("localidadeHeader", true);
					parametros.put("gerenciaRegionalFooter", false);
					
				} else if (helper.getOpcaoTotalizacao().equals("gerenciaRegional")) {

					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - GERENCIA REGIONAL");
					parametros.put("nomeGerencia", "Gerência Regional");
					parametros.put("gerenciaRegionalHeader", true);
					parametros.put("unidadeHeader", false);
					parametros.put("localidadeHeader", false);
					parametros.put("gerenciaRegionalFooter", false);
					
					parametros.put("nomeOpcaoTotalizacao", "GERÊNCIA REGIONAL");
					FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
					filtroGerenciaRegional.adicionarParametro(
							new ParametroSimples(FiltroGerenciaRegional.ID, helper.getIdGerenciaRegional()));
					
					Collection colecao = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
					
					if (colecao != null && !colecao.isEmpty()) {
						GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecao);
					
						parametros.put("opcaoTotalizacao", gerenciaRegional.getNome());
					}
					
				} else if (helper.getOpcaoTotalizacao().equals("unidadeNegocio")) {
					
					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - UNIDADE DE NEGÓCIO");
					parametros.put("nomeUnidade", "Unidade de Negócio");
					parametros.put("gerenciaRegionalHeader", false);
					parametros.put("unidadeHeader", true);
					parametros.put("localidadeHeader", false);
					parametros.put("gerenciaRegionalFooter", false);
					
					parametros.put("nomeOpcaoTotalizacao", "UNIDADE DE NEGÓCIO");
					FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
					filtroUnidadeNegocio.adicionarParametro(
							new ParametroSimples(FiltroUnidadeNegocio.ID, helper.getIdUnidadeNegocio()));
					
					Collection colecao = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
					
					if (colecao != null && !colecao.isEmpty()) {
						UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecao);
					
						parametros.put("opcaoTotalizacao", unidadeNegocio.getNome());
					}
					
				} else if (helper.getOpcaoTotalizacao().equals("gerenciaRegionalLocalidade")) {
					
					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - GERENCIA REGIONAL POR LOCALIDADE");
					parametros.put("nomeGerencia", "Gerência Regional");
					parametros.put("nomeLocalidade", "Localidade");
					parametros.put("gerenciaRegionalHeader", true);
					parametros.put("unidadeHeader", false);
					parametros.put("localidadeHeader", true);
					parametros.put("gerenciaRegionalFooter", true);
					
				} else if (helper.getOpcaoTotalizacao().equals("localidade")) {
					
					parametros.put("titulo", "RELATÓRIO QUANTIDADES DE OPERAÇÕES - LOCALIDADE");
					parametros.put("nomeLocalidade", "Localidade");
					parametros.put("gerenciaRegionalHeader", false);
					parametros.put("unidadeHeader", false);
					parametros.put("localidadeHeader", true);
					parametros.put("gerenciaRegionalFooter", false);
					
					parametros.put("nomeOpcaoTotalizacao", "LOCALIDADE");
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(
							new ParametroSimples(FiltroLocalidade.ID, helper.getIdLocalidade()));
					
					Collection colecao = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
					
					if (colecao != null && !colecao.isEmpty()) {
						Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecao);
					
						parametros.put("opcaoTotalizacao", localidade.getDescricao());
					}
	
				}
			}
		}
		
		Collection<Object[]> colecaoDados = fachada.pesquisarDadosRelatorioAlteracoesCpfCnpj(helper);
		
		for (Object[] objetos : colecaoDados) {
			
			RelatorioAlteracoesCpfCnpjBean relatorioAlteracoesCpfCnpjBean = null;
			
			switch (Integer.parseInt(helper.getTipoRelatorio())) {
			//TIPO POR USUARIO
			case 1:
				
				relatorioAlteracoesCpfCnpjBean = new RelatorioAlteracoesCpfCnpjBean(
						//nome
						objetos[0]!=null?(String)objetos[0]:null,
						//login
						objetos[1]!=null?(String)objetos[1]:null,
						//lotacao	
						objetos[2]!=null?(String)objetos[2]:null,
						//meio
						objetos[3]!=null?(String)objetos[3]:null,
						//cpf
						objetos[4]!=null?((Double)objetos[4]):0,
						//cnpj
						objetos[5]!=null?((Double)objetos[5]):0,
						//total		
						objetos[6]!=null?((Double)objetos[6]):0);
				break;
			//TIPO POR LOCALIDADE
			case 2:
	
				relatorioAlteracoesCpfCnpjBean = new RelatorioAlteracoesCpfCnpjBean(
						//gerenciaRegional
						objetos[0]!=null?(String)objetos[0]:null,
						//unidadeNegocio		
						objetos[1]!=null?(String)objetos[1]:null,
						//localidade
						objetos[1]!=null?(String)objetos[2]:null,
						//cpf
						objetos[2]!=null?(Double)objetos[3]:0,
						//cnpj
						objetos[3]!=null?(Double)objetos[4]:0,
						//total	
						objetos[4]!=null?(Double)objetos[5]:0);
				break;	
			//TIPO POR MEIO
			case 3:
	
				relatorioAlteracoesCpfCnpjBean = new RelatorioAlteracoesCpfCnpjBean(
						//meio
						objetos[0]!=null?(String)objetos[0]:null,
						//cpf
						objetos[1]!=null?(Double)objetos[1]:0,
						//cnpj
						objetos[2]!=null?(Double)objetos[2]:0,
						//total	
						objetos[3]!=null?(Double)objetos[3]:0);
				break;	

			default:
				break;
			}
			
			relatorioBeans.add(relatorioAlteracoesCpfCnpjBean);
		}

		
		//Caso não possua dados aprensetar mensagem de relatorio vazio ao usuario.
		if(Util.isVazioOrNulo(relatorioBeans)){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioAlteracoesCpfCnpjBean relatorioAlteracoesCpfCnpjBean = 
				new RelatorioAlteracoesCpfCnpjBean();
			
			relatorioBeans.add(relatorioAlteracoesCpfCnpjBean);
		}else if(helper.getTipoRelatorio().equals("1")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_CPF_CNPJ_USUARIO;
		}else if(helper.getTipoRelatorio().equals("2")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_CPF_CNPJ_LOCALIDADE;
		}else if(helper.getTipoRelatorio().equals("3")){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_CPF_CNPJ_MEIO;
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

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAlteracoesCpfCnpj", this);
	}
}

package gcom.relatorio.cadastro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterUnidadeOrganizacional extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterUnidadeOrganizacional(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_UNIDADE_ORGANIZACIONAL_MANTER);
	}
	
	@Deprecated
	public RelatorioManterUnidadeOrganizacional() {
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

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = (FiltroUnidadeOrganizacional) getParametro("filtroUnidadeOrganizacional");
		
		UnidadeOrganizacional unidadeOrganizacionalParametros = (UnidadeOrganizacional) getParametro("unidadeOrganizacionalParametros");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String pesquisouNivel = (String) getParametro ("pesquisouNivel");

		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("meioSolicitacao");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeCentralizadora");
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterUnidadeOrganizacionalBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator unidadeOrganizacionalIterator = colecaoUnidadeOrganizacional.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (unidadeOrganizacionalIterator.hasNext()) {

				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) unidadeOrganizacionalIterator.next();

				String indicadorAberturaRa = "";
				
				if(unidadeOrganizacional.getIndicadorAberturaRa().equals(ConstantesSistema.SIM)){
					indicadorAberturaRa = "SIM";
				} else {
					indicadorAberturaRa = "NÃO";
				}
				
				String indicadorTramite = "";
				
				if(unidadeOrganizacional.getIndicadorTramite()==(ConstantesSistema.SIM)){
					indicadorTramite = "SIM";
				} else {
					indicadorTramite = "NÃO";
				}
				
				String indicadorUso ="";
				
				if(unidadeOrganizacional.getIndicadorUso() == (ConstantesSistema.SIM)){
					indicadorUso = "SIM";
				} else {
					indicadorUso = "NÃO";
				}
				
				String empresa = null;
				
				if(unidadeOrganizacional.getEmpresa() != null ){
					empresa = unidadeOrganizacional.getEmpresa().getDescricao();
				} 
				
				String sigla = null;
				
				if(unidadeOrganizacional.getSigla() != null){
					sigla = unidadeOrganizacional.getSigla();
				}
				
				String indicadorEsgoto = "";
				
				if(unidadeOrganizacional.getIndicadorEsgoto() == (ConstantesSistema.SIM)){
					indicadorEsgoto = "SIM";
				} else {
					indicadorEsgoto = "NÃO";
				}
				
				String gerenciaRegional = null;
				
				if(unidadeOrganizacional.getGerenciaRegional()!= null){
					gerenciaRegional = unidadeOrganizacional.getGerenciaRegional().getNome();
				}
				
				String localidade = null;

				if(unidadeOrganizacional.getLocalidade() != null ){
					localidade = unidadeOrganizacional.getLocalidade().getDescricao();
				}
				
				String unidadeSuperior = null;
				
				if(unidadeOrganizacional.getUnidadeSuperior() != null){
					unidadeSuperior = unidadeOrganizacional.getUnidadeSuperior().getDescricao();
				}
				
				String unidadeCentralizadora = null;
				
				if(unidadeOrganizacional.getUnidadeCentralizadora() != null ){
					unidadeCentralizadora = unidadeOrganizacional.getUnidadeCentralizadora().getDescricao();
				}
				
				String unidadeRepavimentadora = null;
				
				if(unidadeOrganizacional.getUnidadeRepavimentadora() != null) {
					unidadeRepavimentadora = unidadeOrganizacional.getUnidadeRepavimentadora().getDescricao();
				}
				
				String meioSolicitacao = null;
				
				if(unidadeOrganizacional.getMeioSolicitacao() != null) {
					meioSolicitacao = unidadeOrganizacional.getMeioSolicitacao().getDescricao();
				}
				
				
				relatorioBean = new RelatorioManterUnidadeOrganizacionalBean(
						
						// ID
						unidadeOrganizacional.getId().toString(), 
						
						// Descrição
						unidadeOrganizacional.getDescricao(), 
						
						//Unidade tipo Nivel
						
						unidadeOrganizacional.getUnidadeTipo().getNivel().toString(),
						
						//Unidade tipo descricao
						unidadeOrganizacional.getUnidadeTipo().getDescricao(),
						
						// Indicador Abertura Registro Atendimento
						indicadorAberturaRa,
						
						//Indicador Tramite
						indicadorTramite,
						
						//Indicador Uso
						indicadorUso,
						
						//Empresa
						empresa,
						
						//Indicador Esgoto
						indicadorEsgoto,
						
						//Sigla
						sigla,
						
						//Gerencia Regional
						gerenciaRegional,
						
						//Localidade
						localidade,
						
						//Unidade Superior 
						unidadeSuperior,
						
						//Unidade Centralizadora
						unidadeCentralizadora,
						
						//Unidade Repavimentadora
						unidadeRepavimentadora,
						
						//Meio Solicitacao
						meioSolicitacao);
						
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

		parametros.put("id",
				unidadeOrganizacionalParametros.getId() == null ? "" : ""
						+ unidadeOrganizacionalParametros.getId());

		parametros.put("descricao", unidadeOrganizacionalParametros.getDescricao());	
		
		if (unidadeOrganizacionalParametros.getUnidadeTipo() != null
				&& unidadeOrganizacionalParametros.getUnidadeTipo()
						.getDescricao() != null) {
			parametros.put("descricaoUnidadeTipo", unidadeOrganizacionalParametros.getUnidadeTipo().getDescricao() );
		} else {
			parametros.put("descricaoUnidadeTipo", "");
		}
		
		
		if (unidadeOrganizacionalParametros.getUnidadeTipo() != null
				&& unidadeOrganizacionalParametros.getUnidadeTipo().getNivel() != null 
				&& pesquisouNivel.equalsIgnoreCase("sim")) {
			parametros.put("nivel", unidadeOrganizacionalParametros.getUnidadeTipo().getNivel() );
		} else {
			parametros.put("nivel", null);
		}
		
		
		String indicadorAberturaRa= "";

		if (unidadeOrganizacionalParametros.getIndicadorAberturaRa() != null
				&& !unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals("")) {
			if (unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals(new Short("1"))) {
				indicadorAberturaRa = "Sim";
			} else if (unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals(
					new Short("2"))) {
				indicadorAberturaRa = "Não";
			} else if (unidadeOrganizacionalParametros.getIndicadorAberturaRa().equals(
					new Short ("0"))){
				indicadorAberturaRa = "Todos";
			}
		}
		parametros.put("indicadorAberturaRa", indicadorAberturaRa);

		
		String indicadorTramite= "";

			if (unidadeOrganizacionalParametros.getIndicadorTramite()==(
					new Short("1"))) {
				indicadorTramite = "Sim";
			} else if (unidadeOrganizacionalParametros.getIndicadorTramite()==(
					new Short("2"))) {
				indicadorTramite = "Não";
			} else if (unidadeOrganizacionalParametros.getIndicadorTramite() == (
					new Short("0"))) {
				indicadorTramite = "Todos";
			}
		parametros.put("indicadorTramite", indicadorTramite);
		
		String indicadorUso= "";
		if (unidadeOrganizacionalParametros.getIndicadorUso()==(new Short("1"))) {
			indicadorUso = "Ativo";
		} else if (unidadeOrganizacionalParametros.getIndicadorUso()==(
				new Short("2"))) {
			indicadorUso = "Inativo";
		} else if (unidadeOrganizacionalParametros.getIndicadorUso() == (
				new Short("0"))) {
			indicadorUso = "Todos";
		}
		parametros.put("indicadorUso", indicadorUso);
	
		String indicadorEsgoto= "";
		if (unidadeOrganizacionalParametros.getIndicadorEsgoto()==(new Short("1"))) {
			indicadorEsgoto = "Sim";
		} else if (unidadeOrganizacionalParametros.getIndicadorEsgoto()==(
				new Short("2"))) {
			indicadorEsgoto = "Não";
		} else if (unidadeOrganizacionalParametros.getIndicadorEsgoto() == (
				new Short("0"))) {
			indicadorEsgoto = "Todos";
		}
		parametros.put("indicadorEsgoto", indicadorEsgoto);
	
	
		if(unidadeOrganizacionalParametros.getEmpresa() != null){
			parametros.put("empresa", unidadeOrganizacionalParametros.getEmpresa().getDescricao());
		}else{
			parametros.put("empresa", "");
		}
		
		
		if(unidadeOrganizacionalParametros.getSigla() != null){
			parametros.put("sigla", unidadeOrganizacionalParametros.getSigla());
		} else {
			parametros.put("sigla", null);
		}
		
		
		if(unidadeOrganizacionalParametros.getGerenciaRegional() != null){
			parametros.put("gerenciaRegional", unidadeOrganizacionalParametros.getGerenciaRegional());
		} else {
			parametros.put("gerenciaRegional", "");
		}
		
		if(unidadeOrganizacionalParametros.getLocalidade()!=null){
			parametros.put("localidade", unidadeOrganizacionalParametros.getLocalidade().getDescricao());
		} else {
			parametros.put("localidade", "");
		}
		
		if (unidadeOrganizacionalParametros.getUnidadeSuperior()!= null){
			parametros.put("unidadeSuperior", unidadeOrganizacionalParametros.getUnidadeSuperior().getDescricao());
		} else {
			parametros.put("unidadeSuperior", "");
		}
	
		if(unidadeOrganizacionalParametros.getUnidadeCentralizadora()!= null){
			parametros.put("unidadeCentralizadora", unidadeOrganizacionalParametros.getUnidadeCentralizadora().getDescricao());
		} else {
			parametros.put("unidadeCentralizadora", "");
		}
		
		if(unidadeOrganizacionalParametros.getUnidadeRepavimentadora()!= null){
			parametros.put("unidadeRepavimentadora", unidadeOrganizacionalParametros.getUnidadeRepavimentadora().getDescricao());
		} else {
			parametros.put("unidadeRepavimentadora", "");
		}
		
		if(unidadeOrganizacionalParametros.getMeioSolicitacao()!= null){
			parametros.put("meioSolicitacao", unidadeOrganizacionalParametros.getMeioSolicitacao().getDescricao());
		} else {
			parametros.put("meioSolicitacao", "");
		}
		
		
		
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_UNIDADE_ORGANIZACIONAL_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterUnidadeOrganizacional", this);
	}

}

package gcom.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

/**
 * Descrição da classe
 * Classe responsável pelo processamento dos
 * parametros informados e consequente 
 * montagem dos registros exibidos posteriormente
 * pelo relatório
 * 
 * @author Anderson Italo
 * @date 01/08/2009
 */
public class RelatorioSupressoesReligacoesRestabelecimentos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioSupressoesReligacoesRestabelecimentos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SUPRESSOES_RELIGACOES_REESTABELECIMENTOS);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "R0906");
		
		FiltroSupressoesReligacoesReestabelecimentoHelper filtro = (FiltroSupressoesReligacoesReestabelecimentoHelper) getParametro("filtroSupressoesReligacoesReestabelecimentos");
		List objetosEncontrados = (List)getParametro("objetosEncontrados");
		filtro.setNumeroPagina(-1);
		
		//TRECHO PARA RECUPERAÇÃO DOS PARAMETROS
		//periodo
		//data inicial
		String dataInicial = "";
		if (filtro.getDataEmissaoInicio() !=null && !filtro.getDataEmissaoInicio().equals("")){
			dataInicial = filtro.getDataEmissaoInicio(); 
		}
		
		//data final
		String dataFinal = "";
		if (filtro.getDataEmissaoFim() !=null && !filtro.getDataEmissaoFim().equals("")){
			dataFinal = filtro.getDataEmissaoFim(); 
		}
		
		String periodo = "";
		if (!dataInicial.equals("") && !dataFinal.equals("")){
			periodo = dataInicial.substring(0,11) +  " a " + dataFinal.substring(0,11);
		}
		
		//limite religação após corte
		String limiteReligacaoAposCorte = "";
		if (filtro.getLimititeReligacaoPosCorte() != null && !filtro.getLimititeReligacaoPosCorte().equals("")) {
			limiteReligacaoAposCorte = filtro.getLimititeReligacaoPosCorte();
		}else{
			limiteReligacaoAposCorte = "0";
		}
		
		//empresa
		String empresa = "";
		if (filtro.getIdEmpresa() != null 
				&& !filtro.getIdEmpresa().equals("")){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(
					new ParametroSimples(FiltroEmpresa.ID, filtro.getIdEmpresa()));
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresaEntidade = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			empresa = empresaEntidade.getDescricao();
		}
		
		//define o tipo da combinação escolhida
		String tipoCombinacaoIdentificador = "";
		String tipoCombinacaoDescricao = "";
		String estadoHeader = "";
		String gerenciaHeader = "";
		String unidadeNegocioHeader = "";
		String localidadeHeader = "";
		Integer indicadorTipoRelatorio = filtro.getIndicadorTipoRelatorio();
		
		switch (indicadorTipoRelatorio) {
		case 1:
			tipoCombinacaoIdentificador="TIPO 1";
			tipoCombinacaoDescricao="Resumo por Estado";
			estadoHeader = "Estado";
			break;
		case 2:
			tipoCombinacaoIdentificador="TIPO 2";
			tipoCombinacaoDescricao="Resumo do Estado por Gerência";
			estadoHeader = "Estado";
			gerenciaHeader = "Gerência";
			break;
		case 3:
			tipoCombinacaoIdentificador="TIPO 3";
			tipoCombinacaoDescricao="Resumo por Gerência Específica";
			gerenciaHeader = "Gerência";
			break;
		case 4:
			tipoCombinacaoIdentificador="TIPO 4";
			tipoCombinacaoDescricao="Resumo do Estado por Gerência por Unidade de Negócio";
			estadoHeader = "Estado";
			gerenciaHeader = "Gerência";
			unidadeNegocioHeader = "Unidade de Negócio";
			break;
		case 5:
			tipoCombinacaoIdentificador="TIPO 5";
			tipoCombinacaoDescricao="Resumo por Unidade de Negócio Específica";
			unidadeNegocioHeader = "Unidade de Negócio";
			break;
		case 6:
			tipoCombinacaoIdentificador="TIPO 6";
			tipoCombinacaoDescricao="Resumo do Estado por Gerência por Unidade de Negócio por Localidade";
			estadoHeader = "Estado";
			gerenciaHeader = "Gerência";
			unidadeNegocioHeader = "Unidade de Negócio";
			localidadeHeader = "Localidade";
			break;
		case 7:
			tipoCombinacaoIdentificador="TIPO 7";
			tipoCombinacaoDescricao="Resumo por Localidade Específica";
			localidadeHeader = "Localidade";
			break;
		case 8:
			tipoCombinacaoIdentificador="TIPO 8";
			tipoCombinacaoDescricao="Gerência Especifica por Unidade de Negócio";
			gerenciaHeader = "Gerência";
			unidadeNegocioHeader = "Unidade de Negócio";
			break;
		case 9:
			tipoCombinacaoIdentificador="TIPO 9";
			tipoCombinacaoDescricao="Gerências Especificas por Unidade de Negócio por Localidade";
			gerenciaHeader = "Gerência";
			unidadeNegocioHeader = "Unidade de Negócio";
			localidadeHeader = "Localidade";
			break;
		case 10:
			tipoCombinacaoIdentificador="TIPO 10";
			tipoCombinacaoDescricao="Unidade de Negócio Específica por Localidade";
			unidadeNegocioHeader = "Unidade de Negócio";
			localidadeHeader = "Localidade";
			break;
		default:
			break;
		}
		
		//seta os parametros
		parametros.put("periodo", periodo);
		parametros.put("limiteReligacaoAposCorte", limiteReligacaoAposCorte);
		parametros.put("empresa", empresa);
		parametros.put("tipoCombinacaoIdentificador", tipoCombinacaoIdentificador);
		parametros.put("tipoCombinacaoDescricao", tipoCombinacaoDescricao);
		parametros.put("estadoHeader", estadoHeader);
		parametros.put("gerenciaHeader", gerenciaHeader);
		parametros.put("unidadeNegocioHeader", unidadeNegocioHeader);
		parametros.put("localidadeHeader", localidadeHeader);
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		List<RelatorioSupressoesReligacoesReestabelecimentosBean> beans = new ArrayList<RelatorioSupressoesReligacoesReestabelecimentosBean>();
		Object obj = null;
		Object[] dados = null;
		Object[] dadosAnterior = null;
		Integer totalizadorReligacoesAntesGerencia = 0;
		Integer totalizadorReligacoesAposGerencia = 0;
		Integer totalizadorSupressoesGerencia = 0;
		Integer totalizadorReestabelecimentosGerencia = 0;
		Integer totalizadorCortadosGerencia = 0;
		Integer totalizadorReligacoesAntesEstado = 0;
		Integer totalizadorReligacoesAposEstado = 0;
		Integer totalizadorSupressoesEstado = 0;
		Integer totalizadorReestabelecimentosEstado = 0;
		Integer totalizadorCortadosEstado = 0;
		Integer totalizadorReligacoesAntesUnidade = 0;
		Integer totalizadorReligacoesAposUnidade = 0;
		Integer totalizadorSupressoesUnidade = 0;
		Integer totalizadorReestabelecimentosUnidade = 0;
		Integer totalizadorCortadosUnidade = 0;
		Integer totalizadorReligacoesAntesLocalidade = 0;
		Integer totalizadorReligacoesAposLocalidade = 0;
		Integer totalizadorSupressoesLocalidade = 0;
		Integer totalizadorReestabelecimentosLocalidade = 0;
		Integer totalizadorCortadosLocalidade = 0;
		int totalBeansPorGerencia = 0;
		int totalBeansPorUnidade = 0;
		
		//seta os dados dos beans
		if (objetosEncontrados != null){
			for (int i = 0; i < objetosEncontrados.size(); i++) {
				obj = objetosEncontrados.get(i);
				
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					
					RelatorioSupressoesReligacoesReestabelecimentosBean bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
					
					switch (indicadorTipoRelatorio) {
					case 1:
						//TIPO 1 - Resumo por Estado
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(bean);
						}
						break;
					case 2:
						//TIPO 2 - Resumo do Estado por Gerência
						
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(0,bean);
						}
						//para preencher as gerencias
						if (dadosAnterior == null || dados[1].toString().equals(dadosAnterior[1].toString())){
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}else{
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dadosAnterior[0].toString());
							bean.setGerenciaRegionalNome("-" + dadosAnterior[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesGerencia = 0;
							totalizadorReligacoesAposGerencia = 0;
							totalizadorSupressoesGerencia = 0;
							totalizadorReestabelecimentosGerencia = 0;
							totalizadorCortadosGerencia = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(bean);
						}
						
						break;
					case 3:
						//TIPO 3 - Resumo por Gerência Específica
						//para preencher as gerencias
						totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
						totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
						totalizadorSupressoesGerencia += new Integer(dados[6].toString());
						totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
						totalizadorCortadosGerencia += new Integer(dados[8].toString());
						
						if (i == (objetosEncontrados.size() - 1)){
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 4:
						//TIPO 4 - Resumo do Estado por Gerência por Unidade de Negócio
						//para preencher as unidades de negócio
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else{
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
							//atualiza o indexe dos beans
							totalBeansPorGerencia++;
						}
						
						//para totalizar as gerencias
						if (dadosAnterior == null || dados[1].toString().equals(dadosAnterior[1].toString())){
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[1].toString().equals(dadosAnterior[1].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a gerencia
							bean.setGerenciaRegionalNomeAbreviado(dadosAnterior[0].toString());
							bean.setGerenciaRegionalNome("-" + dadosAnterior[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
							//zera os totalizadores
							totalBeansPorGerencia = 0;
							totalizadorReligacoesAntesGerencia = 0;
							totalizadorReligacoesAposGerencia = 0;
							totalizadorSupressoesGerencia = 0;
							totalizadorReestabelecimentosGerencia = 0;
							totalizadorCortadosGerencia = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}
						
						//para preencher o estado
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(0,bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 5:
						//TIPO 5 - Resumo por Unidade de Negócio Específica
						//para totaliza as Unidades 
						totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
						totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
						totalizadorSupressoesUnidade += new Integer(dados[6].toString());
						totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
						totalizadorCortadosUnidade += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
						}
						break;
					case 6:
						//TIPO 6 - Resumo do Estado por Gerência por Unidade de Negócio por Localidade
						//para preencher as localidades
						if (dadosAnterior == null || dados[3].toString().equals(dadosAnterior[3].toString())){
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}else{
							bean.setLocalidade(dadosAnterior[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesLocalidade = 0;
							totalizadorReligacoesAposLocalidade = 0;
							totalizadorSupressoesLocalidade = 0;
							totalizadorReestabelecimentosLocalidade = 0;
							totalizadorCortadosLocalidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
							//atualiza o indexe dos beans
							totalBeansPorUnidade++;
							totalBeansPorGerencia++;
						}
						
						//para totalizar as unidades
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[2].toString().equals(dadosAnterior[2].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a Unidade
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
							totalBeansPorGerencia ++;
							//zera os totalizadores
							totalBeansPorUnidade = 0;
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}
						
						//para totalizar as gerencias
						if (dadosAnterior == null || dados[1].toString().equals(dadosAnterior[1].toString())){
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[1].toString().equals(dadosAnterior[1].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a gerencia
							bean.setGerenciaRegionalNomeAbreviado(dadosAnterior[0].toString());
							bean.setGerenciaRegionalNome("-" + dadosAnterior[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
							//zera os totalizadores
							totalBeansPorGerencia = 0;
							totalizadorReligacoesAntesGerencia = 0;
							totalizadorReligacoesAposGerencia = 0;
							totalizadorSupressoesGerencia = 0;
							totalizadorReestabelecimentosGerencia = 0;
							totalizadorCortadosGerencia = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
							totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
							totalizadorSupressoesGerencia += new Integer(dados[6].toString());
							totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
							totalizadorCortadosGerencia += new Integer(dados[8].toString());
						}
						
						//para preencher o estado
						totalizadorReligacoesAntesEstado += new Integer(dados[4].toString());
						totalizadorReligacoesAposEstado += new Integer(dados[5].toString());
						totalizadorSupressoesEstado += new Integer(dados[6].toString());
						totalizadorReestabelecimentosEstado += new Integer(dados[7].toString());
						totalizadorCortadosEstado += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setEstado("ESTADO");
							bean.setReligacoesAntesDiasEstado(totalizadorReligacoesAntesEstado);
							bean.setReligacoesAposDiasEstado(totalizadorReligacoesAposEstado);
							bean.setSupressoesEstado(totalizadorSupressoesEstado);
							bean.setReestabelecimentosEstado(totalizadorReestabelecimentosEstado);
							bean.setCortadosEstado(totalizadorCortadosEstado);
							beans.add(0,bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorGerencia, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						
						break;
					case 7:
						//TIPO 7 - Resumo por Localidade Específica
						//para totalizar a localidade
						totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
						totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
						totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
						totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
						totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						
						if (i == (objetosEncontrados.size() - 1)){
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 8:
						//TIPO 8 - Gerência Especifica por Unidade de Negócio
						totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
						totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
						totalizadorSupressoesGerencia += new Integer(dados[6].toString());
						totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
						totalizadorCortadosGerencia += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							beans.add(0,bean);
						}
						
						//para preencher as unidades de negocio
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else{
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					case 9:
						//TIPO 9 - Gerências Especificas por Unidade de Negócio por Localidade
						//para preencher as localidades
						if (dadosAnterior == null || dados[3].toString().equals(dadosAnterior[3].toString())){
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}else{
							bean.setLocalidade(dadosAnterior[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesLocalidade = 0;
							totalizadorReligacoesAposLocalidade = 0;
							totalizadorSupressoesLocalidade = 0;
							totalizadorReestabelecimentosLocalidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());							
							//atualiza o indexe dos beans
							totalBeansPorUnidade++;
							totalBeansPorGerencia++;
						}
						
						//para totalizar as unidades
						if (dadosAnterior == null || dados[2].toString().equals(dadosAnterior[2].toString())){
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}else if (dadosAnterior != null && !dados[2].toString().equals(dadosAnterior[2].toString())){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							// preenche a Unidade
							bean.setUnidadeNegocio(dadosAnterior[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
							//zera os totalizadores
							totalBeansPorUnidade = 0;
							totalizadorReligacoesAntesUnidade = 0;
							totalizadorReligacoesAposUnidade = 0;
							totalizadorSupressoesUnidade = 0;
							totalizadorReestabelecimentosUnidade = 0;
							totalizadorCortadosUnidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
							totalizadorSupressoesUnidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
							totalizadorCortadosUnidade += new Integer(dados[8].toString());
						}
						
						//para totalizar a gerencia
						totalizadorReligacoesAntesGerencia += new Integer(dados[4].toString());
						totalizadorReligacoesAposGerencia += new Integer(dados[5].toString());
						totalizadorSupressoesGerencia += new Integer(dados[6].toString());
						totalizadorReestabelecimentosGerencia += new Integer(dados[7].toString());
						totalizadorCortadosGerencia += new Integer(dados[8].toString());						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setGerenciaRegionalNomeAbreviado(dados[0].toString());
							bean.setGerenciaRegionalNome("-" + dados[1].toString());
							bean.setReligacoesAntesDiasGerencia(totalizadorReligacoesAntesGerencia);
							bean.setReligacoesAposDiasGerencia(totalizadorReligacoesAposGerencia);
							bean.setSupressoesGerencia(totalizadorSupressoesGerencia);
							bean.setReestabelecimentosGerencia(totalizadorReestabelecimentosGerencia);
							bean.setCortadosGerencia(totalizadorCortadosGerencia);
							//adiciona bean
							beans.add(0,bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							//adiciona bean
							beans.add(beans.size() - totalBeansPorUnidade, bean);
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						
						break;
					case 10:
						//TIPO 10 - Unidade de Negócio Específica por Localidade
						totalizadorReligacoesAntesUnidade += new Integer(dados[4].toString());
						totalizadorReligacoesAposUnidade += new Integer(dados[5].toString());
						totalizadorSupressoesUnidade += new Integer(dados[6].toString());
						totalizadorReestabelecimentosUnidade += new Integer(dados[7].toString());
						totalizadorCortadosUnidade += new Integer(dados[8].toString());
						if (i == (objetosEncontrados.size() - 1)){
							bean.setUnidadeNegocio(dados[2].toString());
							bean.setReligacoesAntesDiasUnidade(totalizadorReligacoesAntesUnidade);
							bean.setReligacoesAposDiasUnidade(totalizadorReligacoesAposUnidade);
							bean.setSupressoesUnidade(totalizadorSupressoesUnidade);
							bean.setReestabelecimentosUnidade(totalizadorReestabelecimentosUnidade);
							bean.setCortadosUnidade(totalizadorCortadosUnidade);
							beans.add(0,bean);
						}
						
						//para preencher as Localidades
						if (dadosAnterior == null || dados[3].toString().equals(dadosAnterior[3].toString())){
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}else{
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dadosAnterior[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
							//zera os totalizadores
							totalizadorReligacoesAntesLocalidade = 0;
							totalizadorReligacoesAposLocalidade = 0;
							totalizadorSupressoesLocalidade = 0;
							totalizadorReestabelecimentosLocalidade = 0;
							totalizadorCortadosLocalidade = 0;
							//totaliza os da próxima/atual
							totalizadorReligacoesAntesLocalidade += new Integer(dados[4].toString());
							totalizadorReligacoesAposLocalidade += new Integer(dados[5].toString());
							totalizadorSupressoesLocalidade += new Integer(dados[6].toString());
							totalizadorReestabelecimentosLocalidade += new Integer(dados[7].toString());
							totalizadorCortadosLocalidade += new Integer(dados[8].toString());
						}
						
						if (i == (objetosEncontrados.size() - 1)){
							bean = new RelatorioSupressoesReligacoesReestabelecimentosBean();
							bean.setLocalidade(dados[3].toString());
							bean.setReligacoesAntesDiasLocalidade(totalizadorReligacoesAntesLocalidade);
							bean.setReligacoesAposDiasLocalidade(totalizadorReligacoesAposLocalidade);
							bean.setSupressoesLocalidade(totalizadorSupressoesLocalidade);
							bean.setReestabelecimentosLocalidade(totalizadorReestabelecimentosLocalidade);
							bean.setCortadosLocalidade(totalizadorCortadosLocalidade);
							//adiciona bean
							beans.add(bean);
						}
						break;
					default:
						break;
					}
					dadosAnterior = dados;

				}
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_SUPRESSOES_RELIGACOES_REESTABELECIMENTOS,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioSupressoesReligacoesRestabelecimentos", this);
	}

}

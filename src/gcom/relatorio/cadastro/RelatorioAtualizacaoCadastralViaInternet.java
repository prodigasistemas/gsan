package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
 * 
 * @author Daniel Alves,Hugo Amorim
 * @date 28/09/2010,04/10/2010
 */

public class RelatorioAtualizacaoCadastralViaInternet extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioAtualizacaoCadastralViaInternet(Usuario usuario) {		
		super(usuario, ConstantesRelatorios.RELATORIO_ATUALIZACAO_CADASTRAL_VIA_INTERNET);		
	}
	
	@Deprecated
	public RelatorioAtualizacaoCadastralViaInternet() {
		super(null, "");
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String filtroPeriodoInicial = (String) getParametro("filtroPeriodoInicial");
		String filtroPeriodoFinal = (String) getParametro("filtroPeriodoFinal");
		String filtroGerenciaRegional = (String) getParametro("filtroGerenciaRegional");
		String filtroUnidadeNegocio = (String) getParametro("filtroUnidadeNegocio");
		String filtroLocalidadeInicial = (String) getParametro("filtroLocalidadeInicial");
		String filtroLocalidadeFinal = (String) getParametro("filtroLocalidadeFinal");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		//Parametros
		String gerencia = "";
		String unidade = "";
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();			
		
		GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro = new GerarRelatorioAtualizacaoCadastralViaInternetHelper();
		
		filtro.setPeriodoReferenciaInicial(filtroPeriodoInicial);
		filtro.setPeriodoReferenciaFinal(filtroPeriodoFinal);
		
		if(filtroGerenciaRegional != null && !filtroGerenciaRegional.equals("-1")){
			filtro.setIdGerenciaRegional(Integer.parseInt(filtroGerenciaRegional));
			
			FiltroGerenciaRegional pesquisaGerenciaRegional = new FiltroGerenciaRegional();
			
			pesquisaGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, filtroGerenciaRegional));
			
			Collection<GerenciaRegional> colecaoGerencias = fachada.pesquisar(pesquisaGerenciaRegional, GerenciaRegional.class.getName());
			
			for (GerenciaRegional gerenciaRegional : colecaoGerencias) {
				gerencia = gerenciaRegional.getNome();
			}
		}
		
		if(filtroUnidadeNegocio != null && !filtroUnidadeNegocio.equals("-1")){
			filtro.setIdUnidadeNegocio(Integer.parseInt(filtroUnidadeNegocio));
			
			FiltroUnidadeNegocio pesquisaUnidadeNegocio = new FiltroUnidadeNegocio();
			
			pesquisaUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, filtroUnidadeNegocio));
			
			Collection<UnidadeNegocio> colecaoUnidade = fachada.pesquisar(pesquisaUnidadeNegocio, UnidadeNegocio.class.getName());
			
			for (UnidadeNegocio unidadeNegocio : colecaoUnidade) {
				unidade = unidadeNegocio.getNome();
			}
		}
		
		if(filtroLocalidadeInicial != null && !filtroLocalidadeInicial.equals("")
				&& filtroLocalidadeFinal != null && !filtroLocalidadeFinal.equals("")){
			
			filtro.setIdLocalidadeInicial(Integer.parseInt(filtroLocalidadeInicial));
			filtro.setIdLocalidadeFinal(Integer.parseInt(filtroLocalidadeFinal));
		}
			
		Collection colecaAtualizacaoCadastralViaInternet = null;
		
		
		try {
			colecaAtualizacaoCadastralViaInternet = fachada.pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(filtro);
		} catch (ControladorException e1) {
			//throw new ActionServletException("atencao.naocadastrado", null,	"Unidade de Negócio");		
		}	
		
		Iterator iterator = colecaAtualizacaoCadastralViaInternet.iterator();
		
		RelatorioAtualizacaoCadastralViaInternetBean relatorioBean = null;
		
		while(iterator.hasNext()){
			relatorioBean = new RelatorioAtualizacaoCadastralViaInternetBean();
			
			Object[] obj = (Object[]) iterator.next();
			
			relatorioBean.setMatricula((Integer)obj[0]+"");				
			relatorioBean.setLocalidade((Integer)obj[1]+"");
			relatorioBean.setUnidade(obj[14]+"");	
			relatorioBean.setGerencia(obj[15]+"");
			relatorioBean.setDataConfirmacao(Util.formatarData((Date) obj[16]));
			relatorioBean.setIdCliente((Integer)obj[17]+"");
			
			
			//Dados Alterados
			String nomeClienteAnterior = ((String)obj[2]);
			String nomeClienteAtual = ((String)obj[3]);
			String cpfAnterior = ((String)obj[4]);
			String cpfAtual = ((String)obj[5]);
			String cnpjAnterior = ((String)obj[6]);
			String cnpjAtual = ((String)obj[7]);
			String emailAnterior = ((String)obj[8]);
			String emailAtual = ((String)obj[9]);
			
			//criar uma colecao  de "Dados Alterados" e formatar conforme abaixo.
			
			String alteracao = "";
			
			nomeClienteAnterior = nomeClienteAnterior != null ?nomeClienteAnterior:"Não Informado";
			nomeClienteAtual = nomeClienteAtual != null ?nomeClienteAtual:"Não Informado";
			
			cpfAnterior = cpfAnterior != null ?cpfAnterior:"Não Informado";
			cpfAtual = cpfAtual != null ?cpfAtual:"Não Informado";
			
			cnpjAnterior = cnpjAnterior != null ?cnpjAnterior:"Não Informado";
			cnpjAtual = cnpjAtual != null ?cnpjAtual:"Não Informado";
			
			emailAnterior = emailAnterior != null ?emailAnterior:"Não Informado";
			emailAtual = emailAtual != null ?emailAtual:"Não Informado";
			
			
			if(!nomeClienteAnterior.equals(nomeClienteAtual)){
				alteracao += "Nome Anterior: " + nomeClienteAnterior +" Nome Atual: " +nomeClienteAtual+"\n";
			}
			
			if(!cpfAnterior.equals(cpfAtual)){
				alteracao += "Cpf Anterior: " + cpfAnterior +" Cpf Atual: " +cpfAtual+"\n";
			}
			
			if(!cnpjAnterior.equals(cnpjAtual)){
				alteracao += "Cnpj Anterior: " + cnpjAnterior +" Cnpj Atual: " +cnpjAtual+"\n";
			}
			
			if(!emailAnterior.equalsIgnoreCase(emailAtual)){
				alteracao += "Email Anterior: " + emailAnterior +" Email Atual: " +emailAtual+"\n";
			}
			
			relatorioBean.setAlteracao(alteracao);	
			
			
			//Solicitante
			String confirmacaoOnline = (Util.formatarDataComHora((Date)obj[10]));
			String nomeSolicitante = ((String)obj[11]);
			String cpfSolicitante = ((String)obj[12]);
			Integer telefoneContato = (Integer)obj[13];
			
			String solicitante = "";
			
			solicitante = "Alterado em "+ confirmacaoOnline 
				+ " por "+nomeSolicitante+" CPF:" 
				+cpfSolicitante;
			
			if(telefoneContato!=null && !telefoneContato.equals("")){
				solicitante += " Fone: " +telefoneContato;
			}
			
			relatorioBean.setSolicitante(solicitante);				
	
			relatorioBeans.add(relatorioBean);
		}
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("filtroGerenciaRegional", gerencia);
		parametros.put("filtroUnidadeNegocio", unidade);
		parametros.put("filtroLocalidadeInicial", filtroLocalidadeInicial);
		parametros.put("filtroLocalidadeFinal", filtroLocalidadeFinal);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("filtroPeriodoInicial", filtroPeriodoInicial);
		parametros.put("filtroPeriodoFinal", filtroPeriodoFinal);
		parametros.put("tipoRelatorio", "R1076");
		
		Collection colecaResumoAtualizacaoCadastralViaInternet = null;
		
		try {
			colecaResumoAtualizacaoCadastralViaInternet = fachada.pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(filtro);
		} catch (ControladorException e1) {
			e1.printStackTrace();
			//throw new ActionServletException("atencao.naocadastrado", null,	"Unidade de Negócio");		
		}	
		
		Iterator iteratorResumo = colecaResumoAtualizacaoCadastralViaInternet.iterator();
		
		while(iteratorResumo.hasNext()){
		
			Object[] obj = (Object[]) iteratorResumo.next();
			
			parametros.put("QuantidadeNome", (obj[0]!=null?((Integer)obj[0]).toString():"0"));
			parametros.put("QuantidadeCPF", (obj[1]!=null?((Integer)obj[1]).toString():"0"));
			parametros.put("QuantidadeCNPJ", (obj[2]!=null?((Integer)obj[2]).toString():"0"));
			parametros.put("QuantidadeEmail", (obj[3]!=null?((Integer)obj[3]).toString():"0"));
			parametros.put("QuantidadeClientesAlterados", (obj[4]!=null?((Integer)obj[4]).toString():"0"));
			
			break;
		}
		
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) relatorioBeans );

		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ATUALIZACAO_CADASTRAL_VIA_INTERNET, parametros, ds,
					tipoFormatoRelatorio);
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ATUALIZACAO_CADASTRAL_VIA_INTERNET,
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
		
		String filtroPeriodoInicial = (String) getParametro("filtroPeriodoInicial");
		String filtroPeriodoFinal = (String) getParametro("filtroPeriodoFinal");
		String filtroGerenciaRegional = (String) getParametro("filtroGerenciaRegional");
		String filtroUnidadeNegocio = (String) getParametro("filtroUnidadeNegocio");
		String filtroLocalidadeInicial = (String) getParametro("filtroLocalidadeInicial");
		String filtroLocalidadeFinal = (String) getParametro("filtroLocalidadeFinal");
		
		GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro = new GerarRelatorioAtualizacaoCadastralViaInternetHelper();
		
		filtro.setPeriodoReferenciaInicial(filtroPeriodoInicial);
		filtro.setPeriodoReferenciaFinal(filtroPeriodoFinal);
		
		if(filtroGerenciaRegional != null && !filtroGerenciaRegional.equals("-1")){
			filtro.setIdGerenciaRegional(Integer.parseInt(filtroGerenciaRegional));
		}
		
		if(filtroUnidadeNegocio != null && !filtroUnidadeNegocio.equals("-1")){
			filtro.setIdUnidadeNegocio(Integer.parseInt(filtroUnidadeNegocio));
		}
		
		if(filtroLocalidadeInicial != null && !filtroLocalidadeInicial.equals("")
				&& filtroLocalidadeFinal != null && !filtroLocalidadeFinal.equals("")){
			
			filtro.setIdLocalidadeInicial(Integer.parseInt(filtroLocalidadeInicial));
			filtro.setIdLocalidadeFinal(Integer.parseInt(filtroLocalidadeFinal));
		}
			
		Integer retorno = new Integer(0);
		
		retorno = Fachada.getInstancia().countRelatorioAtualizacaoCadastralViaInternet(filtro);
		
		if(retorno.intValue() == 0 ){
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAtualizacaoCadastralViaInternet", this);
	}
	
}

package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemServicoFiscalizacaoAnaliticoBean;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemServicoFiscalizacaoSinteticoBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/**
 * 
 * 
 * [UC1213] Emitir Relatorio de Ordem de Sercico de Fiscalizacao
 * 
 * @author Paulo Diniz
 * @date 02/08/2011
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */


public class RelatorioOrdensServicoFiscalizacaoAction extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	private int relatorioTipoFiscalizacao;
	
	public RelatorioOrdensServicoFiscalizacaoAction(Usuario usuario, String tipoRelatorio) {
		super(usuario, tipoRelatorio);
	}
	
	@Deprecated
	public RelatorioOrdensServicoFiscalizacaoAction() {
		super(null, "");
	}
	
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		
		//Parâmetros vindos do gerar relatório
		Collection colecaoOrdemServico = (Collection)getParametro("colecaoOrdemServico");
		int tipoFormatoRelatorio = (Integer)getParametro("tipoFormatoRelatorio");
		relatorioTipoFiscalizacao = (Integer)getParametro("relatorioTipoFiscalizacao");
		String idLocalidadeFinal = (String)getParametro("idLocalidadeFinal");
		String idLocalidadeInicial = (String)getParametro("idLocalidadeInicial");
		String periodoFinal = (String)getParametro("periodoFinal");
		String periodoInicial =(String)getParametro("periodoInicial");
		String descLocalidadeInicial = (String)getParametro("descLocalidadeInicial");
		String descLocalidadeFinal = (String)getParametro("descLocalidadeFinal");
		String idGerenciaRegional = (String)getParametro("idGerenciaRegional");
		String idUnidadeNegocios = (String)getParametro("idUnidadeNegocios");
		String situacaoOS = (String)getParametro("situacaoOS");
		String idOSReferidaRetornoTipo = (String)getParametro("idOSReferidaRetornoTipo");
		String aceitacaoDaOS = (String)getParametro("aceitacaoDaOS");
		
		//Variáveis
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Map parametros = new HashMap();
		
		//valor de retorno
		byte[] retorno = null;
		
		//Relatorio Analitico
		if(relatorioTipoFiscalizacao == 1){
			
			//Define a variálvel de nome usada para construir o relatorio
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO;
			
			//Montar Cabeçalho
			parametros = montarCabecalhoAnalitico(idLocalidadeInicial, idLocalidadeFinal, periodoInicial, periodoFinal, descLocalidadeInicial, descLocalidadeFinal, idGerenciaRegional, idUnidadeNegocios, situacaoOS, idOSReferidaRetornoTipo, aceitacaoDaOS, sistemaParametro, fachada);
			
			//Montar Beans
			RelatorioDataSource ds = montarBeansAnalitico(colecaoOrdemServico,fachada);
			
			//Montar relatório
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO,
					parametros, ds, tipoFormatoRelatorio);
			
			
			// ------------------------------------
			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------
		}
		//Relatorio Sintetico
		else if(relatorioTipoFiscalizacao == 2){
			
			//Define a variálvel de nome usada para construir o relatorio
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO;
			
			//Montar Cabeçalho
			parametros = montarCabecalhoSintetico(idLocalidadeInicial, idLocalidadeFinal, periodoInicial, periodoFinal, descLocalidadeInicial, descLocalidadeFinal, idGerenciaRegional, idUnidadeNegocios, sistemaParametro, fachada);
			
			//Montar Beans
			RelatorioDataSource ds = montarBeansSintetico(colecaoOrdemServico,fachada);
			
			//Montar relatório
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO,
					parametros, ds, tipoFormatoRelatorio);
			
			
			// ------------------------------------
			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------
		}
	
		
		// retorna o relatório gerado
		return retorno;
	}
	
	
	
	/**
	 * 
	 * Método auxiliar para montar o cabeçalho do relatório analitico
	 * 
	 * @author Paulo Diniz
	 * @data 12/08/2011
	 */
	private Map montarCabecalhoAnalitico(String idLocalidadeInicial, String idLocalidadeFinal, String periodoInicial, 
			String periodoFinal, String descLocalidadeInicial, String descLocalidadeFinal, 
			String idGerenciaRegional, String idUnidadeNegocios, String situacaoOS, String idOSReferidaRetornoTipo, 
			String aceitacaoDaOS, SistemaParametro sistemaParametro, Fachada fachada) {
		
		HashMap parametros = new HashMap();
		
		//Imagem do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Insere os parâmetros na hash de retorno
		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
			parametros.put("localidadeInicial",descLocalidadeInicial);
		}
		if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
			parametros.put("localidadeFinal",descLocalidadeFinal);
		}
		
		parametros.put("periodoInicial",Util.formatarMesAnoParaData(periodoInicial,"01","00:00:00"));
		String[] mesAnoArray = periodoFinal.split("/");
		String ultimoDiaMEs = Util.obterUltimoDiaMes(Integer.parseInt(mesAnoArray[0]),Integer.parseInt(mesAnoArray[1]));
		parametros.put("periodoFinal",Util.formatarMesAnoParaData(periodoFinal,ultimoDiaMEs,"23:59:59"));
		
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
			filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
			Collection collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) collGerenciaRegional.iterator().next();
			parametros.put("gerenciaRegional",gerencia.getNome());
		}else{
			parametros.put("gerenciaRegional","");
		}
		
		if(idUnidadeNegocios != null && !idUnidadeNegocios.equals("")){
			FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocios));
			Collection collUnidadeNegocio = fachada.pesquisar(filtroUnidade, UnidadeNegocio.class.getName());
			UnidadeNegocio unidade = (UnidadeNegocio) collUnidadeNegocio.iterator().next();
			parametros.put("unidadeNegocios",unidade.getNome());
		}else{
			parametros.put("unidadeNegocios","");
		}
		
		if(idOSReferidaRetornoTipo != null && !idOSReferidaRetornoTipo.equals("")){
			FiltroOSReferidaRetornoTipo filtroRetornoTipo = new FiltroOSReferidaRetornoTipo();
			filtroRetornoTipo.adicionarParametro(new ParametroSimples(FiltroOSReferidaRetornoTipo.ID, idOSReferidaRetornoTipo));
			Collection collTipoRetorno = fachada.pesquisar(filtroRetornoTipo, OsReferidaRetornoTipo.class.getName());
			OsReferidaRetornoTipo retornoTipo = (OsReferidaRetornoTipo) collTipoRetorno.iterator().next();
			parametros.put("tipoRetorno",retornoTipo.getDescricao());
		}else{
			parametros.put("tipoRetorno","");
		}
		
		if(situacaoOS != null && !situacaoOS.equals("")){
			if(situacaoOS.equals("-1")){
				parametros.put("situacao","TODOS");
			}else if(situacaoOS.equals("1")){
				parametros.put("situacao","PENDENTES");
			}else if(situacaoOS.equals("2")){
				parametros.put("situacao","ENCERRADOS");
			}
		}else{
			parametros.put("situacao","");
		}
		
		if(aceitacaoDaOS != null && !aceitacaoDaOS.equals("") && aceitacaoDaOS.equals("1")){
			parametros.put("aceitacao","SIM");
		}else if(aceitacaoDaOS != null && !aceitacaoDaOS.equals("") && aceitacaoDaOS.equals("2")){
			parametros.put("aceitacao","NÃO");
		}else if(aceitacaoDaOS != null && !aceitacaoDaOS.equals("") && aceitacaoDaOS.equals("3")){
			parametros.put("aceitacao","TODOS");
		}
		
		//retorna ao método principal
		return parametros;
		
	}
	
	/**
	 * 
	 * Método auxiliar para montar os dados do relatório analitico
	 * 
	 * @author Paulo Diniz
	 * @data 02/08/2011
	 */
	private RelatorioDataSource montarBeansAnalitico(Collection colecaoOrdemServico, Fachada fachada) {
	
		//lista de retorno
		ArrayList<RelatorioOrdemServicoFiscalizacaoAnaliticoBean> beans = new ArrayList<RelatorioOrdemServicoFiscalizacaoAnaliticoBean>();
		
		//Iteração com cada imóvel retornado pelo filtro
		Iterator it = colecaoOrdemServico.iterator();
		while(it.hasNext()){
			OrdemServico ordemServico = (OrdemServico) it.next();
			OrdemServico ordemFiscalidada = fachada.pesquisarOrdemServicoFiscalizada(ordemServico.getOsReferencia().getId());
			
			RelatorioOrdemServicoFiscalizacaoAnaliticoBean bean = new RelatorioOrdemServicoFiscalizacaoAnaliticoBean();
			
			if(ordemFiscalidada.getImovel() != null){
				bean.setGerenciaRegional(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
				bean.setUnidadeNegocio(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getNome());
				bean.setLocalidade(ordemFiscalidada.getImovel().getLocalidade().getDescricao());
			}else{
				bean.setGerenciaRegional(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
				bean.setUnidadeNegocio(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getNome());
				bean.setLocalidade(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getDescricao());
			}
			
			String mes = Util.getMes(ordemServico.getDataGeracao())+"";
			if(Util.getMes(ordemServico.getDataGeracao()) < 10){
				mes = "0"+mes;
			}
			bean.setMesAnoReferencia(mes +"/" +Util.getAno(ordemServico.getDataGeracao()));
			bean.setQuantidade(new BigDecimal("1"));
		
			if(ordemServico.getOsReferidaRetornoTipo() != null && ordemServico.getOsReferidaRetornoTipo().getIndicadorDeferimento() == 1){
				bean.setAceitacaoFiscalizada("Sim");
			}else if(ordemServico.getOsReferidaRetornoTipo() != null && ordemServico.getOsReferidaRetornoTipo().getIndicadorDeferimento() == 2){
				bean.setAceitacaoFiscalizada("Não");
			}else{
				bean.setAceitacaoFiscalizada("");
			}
			
			if(ordemServico.getDataGeracao() != null){
				bean.setDataGeracaoFiscalizacao(Util.formatarData(ordemServico.getDataGeracao()));
			}else{
				bean.setDataGeracaoFiscalizacao("");
			}
			
			if(ordemServico.getAtendimentoMotivoEncerramento() != null){
				bean.setMotivoEncerFiscalizacao(ordemServico.getAtendimentoMotivoEncerramento().getDescricao());
			}else{
				bean.setMotivoEncerFiscalizacao("");
			}
			
			if(ordemServico.getDataEncerramento() != null){
				bean.setDataEncerramentoFiscalizacao(Util.formatarData(ordemServico.getDataEncerramento()));
			}else{
				bean.setDataEncerramentoFiscalizacao("");
			}
			
			if(ordemServico.getOsReferidaRetornoTipo() != null){
				bean.setTipoRetornoFiscalizacao(ordemServico.getOsReferidaRetornoTipo().getDescricao());
			}else{
				bean.setTipoRetornoFiscalizacao("");
			}
			bean.setIdFiscalizacao(ordemServico.getId().intValue()+"");
			
			bean.setIdFiscalizada(ordemFiscalidada.getId().intValue()+"");
			if(ordemFiscalidada.getImovel() != null){
				
				ClienteImovel clienteImovel = fachada.pesquisarClienteImovelOSFiscalizada(ordemFiscalidada.getImovel().getId());
				if(clienteImovel != null && clienteImovel.getCliente() != null){
					bean.setClienteFiscalizada(clienteImovel.getCliente().getNome());
				}else{
					bean.setClienteFiscalizada("");
				}
				
				bean.setImovelFiscalizacao(ordemFiscalidada.getImovel().getMatriculaFormatada());
				
			}else{
				bean.setClienteFiscalizada("");
				bean.setImovelFiscalizacao("");
			}
			
			if(ordemFiscalidada.getAtendimentoMotivoEncerramento() != null){
				bean.setMotivoEncerFiscalizada(ordemFiscalidada.getAtendimentoMotivoEncerramento().getDescricao());
			}else{
				bean.setMotivoEncerFiscalizada("");
			}
			

			if(ordemFiscalidada.getOsReferidaRetornoTipo() != null){
				bean.setTipoRetornoFiscalizada(ordemFiscalidada.getOsReferidaRetornoTipo().getDescricao());
			}else{
				bean.setTipoRetornoFiscalizada("");
			}
			
			if(ordemFiscalidada.getServicoTipo() != null){
				bean.setTipoServicoFiscalizada(ordemFiscalidada.getServicoTipo().getDescricao());
			}else{
				bean.setTipoServicoFiscalizada("");
			}
			
			if(ordemFiscalidada.getDataEncerramento() != null){
				bean.setDataExecucaoFiscalizada(Util.formatarData(ordemFiscalidada.getDataEncerramento()));
			}else{
				bean.setDataExecucaoFiscalizada("");
			}
			
			beans.add(bean);
		}
		
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		return ds;
	}
	
	/**
	 * 
	 * Método auxiliar para montar o cabeçalho do relatório sintetico
	 * 
	 * @author Paulo Diniz
	 * @data 12/08/2011
	 */
	private Map montarCabecalhoSintetico(String idLocalidadeInicial, String idLocalidadeFinal, String periodoInicial, 
			String periodoFinal, String descLocalidadeInicial, String descLocalidadeFinal, 
			String idGerenciaRegional, String idUnidadeNegocios, SistemaParametro sistemaParametro, Fachada fachada) {
		
		HashMap parametros = new HashMap();
		
		//Imagem do relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Insere os parâmetros na hash de retorno
		if(idLocalidadeInicial != null && !idLocalidadeInicial.equals("")){
			parametros.put("localidadeInicial",descLocalidadeInicial);
		}
		if(idLocalidadeFinal != null && !idLocalidadeFinal.equals("")){
			parametros.put("localidadeFinal",descLocalidadeFinal);
		}
		
		parametros.put("periodoInicial",Util.formatarMesAnoParaData(periodoInicial,"01","00:00:00"));
		String[] mesAnoArray = periodoFinal.split("/");
		String ultimoDiaMEs = Util.obterUltimoDiaMes(Integer.parseInt(mesAnoArray[0]),Integer.parseInt(mesAnoArray[1]));
		parametros.put("periodoFinal",Util.formatarMesAnoParaData(periodoFinal,ultimoDiaMEs,"23:59:59"));
		
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
			filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
			Collection collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) collGerenciaRegional.iterator().next();
			parametros.put("gerenciaRegional",gerencia.getNome());
		}else{
			parametros.put("gerenciaRegional","");
		}
		
		if(idUnidadeNegocios != null && !idUnidadeNegocios.equals("")){
			FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocios));
			Collection collUnidadeNegocio = fachada.pesquisar(filtroUnidade, UnidadeNegocio.class.getName());
			UnidadeNegocio unidade = (UnidadeNegocio) collUnidadeNegocio.iterator().next();
			parametros.put("unidadeNegocios",unidade.getNome());
		}else{
			parametros.put("unidadeNegocios","");
		}
		
		//retorna ao método principal
		return parametros;
		
	}
	
	/**
	 * 
	 * Método auxiliar para montar os dados do relatório analitico
	 * 
	 * @author Paulo Diniz
	 * @data 02/08/2011
	 */
	private RelatorioDataSource montarBeansSintetico(Collection colecaoOrdemServico, Fachada fachada) {
	
		//lista de retorno
		ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean> beans = new ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean>();
		ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean> beansMotivoEncerramento = new ArrayList<RelatorioOrdemServicoFiscalizacaoSinteticoBean>();
		
		List<OrdemServico> listaOS = new ArrayList<OrdemServico>(colecaoOrdemServico);
		int quatidadeSomadaTipoRetorno = 1;
		int quatidadeSomadaMotivo = 1;
		for (int i = 0; i < listaOS.size(); i++) {
			OrdemServico ordemServico = listaOS.get(i);
			OrdemServico ordemFiscalidada = fachada.pesquisarOrdemServicoFiscalizada(ordemServico.getOsReferencia().getId());
			
			OrdemServico ordemProx = null;
			OrdemServico ordemFiscalidadaProx = null;
			Localidade localidadeProx = null;
			Localidade localidade = null;
			
			if(i < listaOS.size()-1){
				ordemProx = listaOS.get(i+1);
				ordemFiscalidadaProx = fachada.pesquisarOrdemServicoFiscalizada(ordemProx.getOsReferencia().getId());
				
				if(ordemFiscalidadaProx.getImovel() != null){
					localidadeProx = ordemFiscalidadaProx.getImovel().getLocalidade();
				}else{
					localidadeProx = ordemFiscalidadaProx.getRegistroAtendimento().getLocalidade();
				}
				
				if(ordemFiscalidada.getImovel() != null){
					localidade = ordemFiscalidada.getImovel().getLocalidade();
				}else{
					localidade = ordemFiscalidada.getRegistroAtendimento().getLocalidade();
				}
				
			}
			
			if(ordemServico.getOsReferidaRetornoTipo() != null){
				
				boolean criaNovoBean = false;
				if(i < listaOS.size()-1){
					
					if(ordemProx.getOsReferidaRetornoTipo() == null || ordemProx.getOsReferidaRetornoTipo().getId().intValue() != ordemServico.getOsReferidaRetornoTipo().getId().intValue()
							|| localidadeProx == null || localidade.getId().intValue() != localidadeProx.getId().intValue()){
						criaNovoBean = true;
					}else{
						quatidadeSomadaTipoRetorno++;
					}
				}else{
					criaNovoBean = true;
				}
				
				if(criaNovoBean){
					RelatorioOrdemServicoFiscalizacaoSinteticoBean beanTipoRetorno = new RelatorioOrdemServicoFiscalizacaoSinteticoBean();
					if(ordemFiscalidada.getImovel() != null){
						beanTipoRetorno.setUnidadeNegocio(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getNome());
						beanTipoRetorno.setGerenciaRegional(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
						beanTipoRetorno.setLocalidade(ordemFiscalidada.getImovel().getLocalidade().getDescricao());
					}else{
						beanTipoRetorno.setUnidadeNegocio(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getNome());
						beanTipoRetorno.setGerenciaRegional(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
						beanTipoRetorno.setLocalidade(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getDescricao());
					}
					
					String mes = Util.getMes(ordemServico.getDataGeracao())+"";
					if(Util.getMes(ordemServico.getDataGeracao()) < 10){
						mes = "0"+mes;
					}
					beanTipoRetorno.setMesAnoReferencia(mes +"/" +Util.getAno(ordemServico.getDataGeracao()));
					beanTipoRetorno.setQuantidadeTipoRetorno(new BigDecimal(quatidadeSomadaTipoRetorno));
					
					beanTipoRetorno.setNomeTotal("OS de Fiscalização para os Tipos de Retorno:");
					
					beanTipoRetorno.setNomeColuna("Tipo de Retorno");
					
					beanTipoRetorno.setItemDescricao(ordemServico.getOsReferidaRetornoTipo().getDescricao());
					
					beans.add(beanTipoRetorno);
					
					quatidadeSomadaTipoRetorno = 1;
				}
			}
			
			if(ordemServico.getAtendimentoMotivoEncerramento() != null){
				
				boolean criaNovoBean = false;
				if(i < listaOS.size()-1){
					
					if(ordemProx.getAtendimentoMotivoEncerramento() == null || ordemProx.getAtendimentoMotivoEncerramento().getId().intValue() != ordemServico.getAtendimentoMotivoEncerramento().getId().intValue()
							|| localidadeProx == null || localidade.getId().intValue() != localidadeProx.getId().intValue()){
						criaNovoBean = true;
					}else{
						quatidadeSomadaMotivo++;
					}
				}else{
					criaNovoBean = true;
				}
				
				if(criaNovoBean){
					RelatorioOrdemServicoFiscalizacaoSinteticoBean beanMotivoEncerramento = new RelatorioOrdemServicoFiscalizacaoSinteticoBean();
					
					if(ordemServico.getImovel() != null){
						beanMotivoEncerramento.setUnidadeNegocio(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getNome());
						beanMotivoEncerramento.setGerenciaRegional(ordemFiscalidada.getImovel().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
						beanMotivoEncerramento.setLocalidade(ordemFiscalidada.getImovel().getLocalidade().getDescricao());
					}else{
						beanMotivoEncerramento.setUnidadeNegocio(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getNome());
						beanMotivoEncerramento.setGerenciaRegional(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getUnidadeNegocio().getGerenciaRegional().getNome());
						beanMotivoEncerramento.setLocalidade(ordemFiscalidada.getRegistroAtendimento().getLocalidade().getDescricao());
					}
					
					String mes = Util.getMes(ordemServico.getDataGeracao())+"";
					if(Util.getMes(ordemServico.getDataGeracao()) < 10){
						mes = "0"+mes;
					}
					beanMotivoEncerramento.setMesAnoReferencia(mes +"/" +Util.getAno(ordemServico.getDataGeracao()));
					beanMotivoEncerramento.setQuantidadeMotivo(new BigDecimal(quatidadeSomadaMotivo));
					
					beanMotivoEncerramento.setNomeTotal("OS de Fiscalização para os Motivos de Encerramento:");
					beanMotivoEncerramento.setNomeColuna("Motivo de Encerramento");
					
					
					beanMotivoEncerramento.setItemDescricao(ordemServico.getAtendimentoMotivoEncerramento().getDescricao());
					
					beansMotivoEncerramento.add(beanMotivoEncerramento);
					
					quatidadeSomadaMotivo = 1;
				}
				
			}
			
			if(i == 16){
				System.out.println("a");
			}
			
			if(localidadeProx != null){
				if(localidade.getId().intValue() != localidadeProx.getId().intValue()){
					beans.addAll(beansMotivoEncerramento);
					beansMotivoEncerramento.clear();
				}
			}else{
				beans.addAll(beansMotivoEncerramento);
			}
		}
		
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		return ds;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		Collection colecaoOrdemServico = (Collection)getParametro("colecaoOrdemServico");
		if(colecaoOrdemServico == null || colecaoOrdemServico.isEmpty()){
			return 0;
		}
		
		return colecaoOrdemServico.size();
	}

	public void agendarTarefaBatch() {
		
		
			AgendadorTarefas.agendarTarefa("RelatorioOrdensServicoFiscalizacaoAction", this);
		
		//	AgendadorTarefas.agendarTarefa("RelatorioOrdensServicoFiscalizacaoSintetico", this);
		
		
	}
	
	
}

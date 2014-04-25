package gcom.relatorio.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.bean.ClienteImovelEconomiaHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ConsultarClienteRelacaoClienteImovelHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarRelacaoClienteImovelActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioClientesRelacionadosImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioClientesRelacionadosImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CLIENTES_RELACIONADOS_IMOVEL);
	}
	
	public Object executar() throws TarefaException {
		
		List<RelatorioClientesRelacionadosImovelBean> relatorioBeans = new ArrayList<RelatorioClientesRelacionadosImovelBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CLIENTES_RELACIONADOS_IMOVEL, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse método cria o RelatorioBean através dos parametros
	 * enviado a este objeto.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioClientesRelacionadosImovelBean criarRelatorioBean() {
		
		Imovel imovel = (Imovel) getParametro("imovel");
		
		Collection<ClienteImovel> colecaoClienteImovel = 
			(Collection<ClienteImovel>) getParametro("colecaoClienteImovel");

		Collection<ImovelSubcategoriaHelper> colecaoImovelSubCategoriaHelper = 
			(Collection<ImovelSubcategoriaHelper>) getParametro("colecaoImovelSubCategoriaHelper");

		RelatorioClientesRelacionadosImovelBean relatorioBean = new RelatorioClientesRelacionadosImovelBean();
		
		relatorioBean.setInscricaoImovel(imovel.getInscricaoFormatada());
		relatorioBean.setMatriculaImovel(imovel.getMatriculaFormatada());
		relatorioBean.setEnderecoImovel(imovel.getEnderecoFormatado());

		if(imovel.getLigacaoAguaSituacao()!=null){
			relatorioBean.setSituacaoAguaImovel(imovel.getLigacaoAguaSituacao().getDescricao());
			
		}

		if(imovel.getLigacaoEsgotoSituacao()!=null){
			relatorioBean.setSituacaoEsgotoImovel(imovel.getLigacaoEsgotoSituacao().getDescricao());
			
		}

		if( !Util.isVazioOrNulo(colecaoClienteImovel)){
			Collection<ConsultarClienteRelacaoClienteImovelHelper> colecaoHelpers = new ArrayList<ConsultarClienteRelacaoClienteImovelHelper>();

			for(ClienteImovel clienteImovel : colecaoClienteImovel){
				ConsultarClienteRelacaoClienteImovelHelper clienteImovelHelper= new ConsultarClienteRelacaoClienteImovelHelper();
				clienteImovelHelper.setClienteImovel(clienteImovel);
				colecaoHelpers.add(clienteImovelHelper);
			}
			

			relatorioBean.setColecaoClienteImovel(
					new JRBeanCollectionDataSource(colecaoHelpers));
		}

		if( !Util.isVazioOrNulo(colecaoImovelSubCategoriaHelper)){

			for(ImovelSubcategoriaHelper subCatHelper : colecaoImovelSubCategoriaHelper){				
				subCatHelper.setColecaoClienteImovelEconomiaHelper(
						new JRBeanCollectionDataSource(criarColecaoClienteImovelEconomiaHelper(subCatHelper.getColecaoImovelEconomia())) 
				);
				
			}
			relatorioBean.setColecaoImovelSubcategoriaHelper(
					new JRBeanCollectionDataSource(colecaoImovelSubCategoriaHelper));			
		}

		return relatorioBean;
	}

	
	/**
	 * Esse método cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) getParametro("consultarRelacaoClienteImovelActionForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(form.getIdImovel()) ){
			parametros.put("idImovelFiltro",form.getIdImovel());
		}

		if(Util.verificarNaoVazio(form.getPeriodoInicialDataInicioRelacao())){
			parametros.put("periodoInicioRelacaoFiltro",
					form.getPeriodoInicialDataInicioRelacao() + " - " + form.getPeriodoFinalDataInicioRelacao());
			
		}
		
		if(Util.verificarNaoVazio(form.getPeriodoInicialDataFimRelacao())){
			parametros.put("periodoFimRelacaoFiltro",
					form.getPeriodoInicialDataFimRelacao() + " - " + form.getPeriodoFinalDataFimRelacao());
			
		}

		if(Util.verificarNaoVazio(form.getSituacaoRelacao())){
			parametros.put("situacaoRelacaoFiltro",form.getSituacaoRelacao());			
		}

		if(Util.verificarNaoVazio(form.getIdClienteImovelFimRelacaoMotivo())){
	
	        FiltroClienteImovelFimRelacaoMotivo filtroClienteImovelFimRelacaoMotivo = new FiltroClienteImovelFimRelacaoMotivo();
	        filtroClienteImovelFimRelacaoMotivo.adicionarParametro(
	        		new ParametroSimples(FiltroClienteImovelFimRelacaoMotivo.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO_ID,
	        				form.getIdClienteImovelFimRelacaoMotivo()));
	        
	        Collection<ClienteImovelFimRelacaoMotivo> colecaoTemp = Fachada.getInstancia().pesquisar(filtroClienteImovelFimRelacaoMotivo, ClienteImovelFimRelacaoMotivo.class.getSimpleName());
			parametros.put("motivoFimRelacaoFiltro",colecaoTemp.iterator().next().getDescricao());
		}
		
		return parametros;
	}

	/**
	 * Cria uma coleção de ClienteImovelEconomiaHelper a partir da coleção
	 * de ClienteImovelEconomia passada como parametro.
	 * É necessário pelo fato do Jasper NÃO descer níveis na hierarquia do bean
	 * (clienteImovel.imovel.id).
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private Collection<ClienteImovelEconomiaHelper> criarColecaoClienteImovelEconomiaHelper(
			Collection<ClienteImovelEconomia> colecaoClienteImovelEconomia) {
		
		Collection<ClienteImovelEconomiaHelper> colecaoHelpers = new ArrayList<ClienteImovelEconomiaHelper>();
		
		for(ClienteImovelEconomia cliImovEconAtual : colecaoClienteImovelEconomia){
			ClienteImovelEconomiaHelper helper = new ClienteImovelEconomiaHelper();

			if(cliImovEconAtual.getImovelEconomia()!=null){
				helper.setComplementoEndereco(cliImovEconAtual.getImovelEconomia().getComplementoEndereco());
			}

			if(cliImovEconAtual.getImovelEconomia()!=null){
				helper.setNumeroMoradores(cliImovEconAtual.getImovelEconomia().getNumeroMorador());
			}

			if(cliImovEconAtual.getImovelEconomia()!=null){
				helper.setNumeroPontosUtilizacao(cliImovEconAtual.getImovelEconomia().getNumeroPontosUtilizacao());
			}
			
			if(cliImovEconAtual.getImovelEconomia()!=null){
				helper.setNumeroIptu(cliImovEconAtual.getImovelEconomia().getNumeroIptu());
			}

			if(cliImovEconAtual.getImovelEconomia()!=null
					&& cliImovEconAtual.getImovelEconomia().getAreaConstruidaFaixa()!=null){
				
				helper.setAreaConstruida(cliImovEconAtual.getImovelEconomia().getAreaConstruidaFaixa().getFaixaCompleta());
			}

			if(cliImovEconAtual.getCliente() != null){				
				helper.setClienteNome(cliImovEconAtual.getCliente().getNome());
			}

			colecaoHelpers.add(helper);

		}
		return colecaoHelpers;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterDebitoAutomatico", this);
	}

}

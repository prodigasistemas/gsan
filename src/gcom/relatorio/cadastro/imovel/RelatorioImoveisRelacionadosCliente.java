package gcom.relatorio.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.bean.ClienteImovelEconomiaHelper;
import gcom.cadastro.imovel.bean.ConsultarClienteRelacaoClienteImovelHelper;
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

public class RelatorioImoveisRelacionadosCliente extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioImoveisRelacionadosCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_RELACIONADOS_CLIENTE);
	}
	
	public Object executar() throws TarefaException {

		List<RelatorioImoveisRelacionadosClienteBean> relatorioBeans = new ArrayList<RelatorioImoveisRelacionadosClienteBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_RELACIONADOS_CLIENTE, parametros,ds, tipoFormatoRelatorio);
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

		if( Util.verificarNaoVazio(form.getIdCliente()) ){
			parametros.put("idClienteFiltro",form.getIdCliente());
		}

		if( Util.verificarNaoVazio(form.getIdClienteRelacaoTipo())){
			parametros.put("relacaoTipoFiltro","");
			
			int idRelacaoTipo = Integer.parseInt(form.getIdClienteRelacaoTipo().trim());
			
			if(ClienteRelacaoTipo.PROPRIETARIO.equals(idRelacaoTipo)){
				parametros.put("relacaoTipoFiltro","PROPRIETARIO");
				
			}else if(ClienteRelacaoTipo.RESPONSAVEL.equals(idRelacaoTipo)){
				parametros.put("relacaoTipoFiltro","RESPONSAVEL");
				
			}else if(ClienteRelacaoTipo.USUARIO.equals(idRelacaoTipo)){
				parametros.put("relacaoTipoFiltro","USUARIO");
				
			}
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
	 * Esse método cria o RelatorioBean através dos parametros
	 * enviado a este objeto.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioImoveisRelacionadosClienteBean criarRelatorioBean() {
		
		Cliente cliente = (Cliente) getParametro("cliente");
		ClienteEndereco clienteEndereco = (ClienteEndereco) getParametro("clienteEndereco");
		
		Long qtdImoveis = (Long) getParametro("quantidadeImoveis");
		
		Collection<ConsultarClienteRelacaoClienteImovelHelper> colecaoClienteImovelHelper = 
			(Collection<ConsultarClienteRelacaoClienteImovelHelper>) getParametro("collClienteImovel");
		
		Collection<ClienteImovelEconomia> colecaoClienteImovelEconomia= 
			(Collection<ClienteImovelEconomia>) getParametro("collClienteImovelEconomia");

		RelatorioImoveisRelacionadosClienteBean relatorioBean = new RelatorioImoveisRelacionadosClienteBean();
		
		relatorioBean.setCliente(cliente.getId() + " - " + cliente.getNome());
		relatorioBean.setEnderecoCorrespondenciaCliente(clienteEndereco.getEnderecoFormatado());		
		if( Util.verificarNaoVazio(cliente.getCpf())){
			relatorioBean.setCpfCnpjCliente(cliente.getCpfFormatado());
			
		}else{
			relatorioBean.setCpfCnpjCliente(cliente.getCnpjFormatado());
			
		}
		
		if(cliente.getRamoAtividade()!=null){
			relatorioBean.setRamoAtividadeCliente(cliente.getRamoAtividade().getDescricao());
		}
		
		if(cliente.getProfissao()!=null){
			relatorioBean.setProfissaoCliente(cliente.getProfissao().getDescricao());			
		}

		if( !Util.isVazioOrNulo(colecaoClienteImovelEconomia)){
			
			Collection<ClienteImovelEconomiaHelper> colecaoHelpers = criarColecaoClienteImovelEconomiaHelper(colecaoClienteImovelEconomia);
			
			relatorioBean.setColecaoClienteImovelEconomia(
					new JRBeanCollectionDataSource(colecaoHelpers));
		}

		if( !Util.isVazioOrNulo(colecaoClienteImovelHelper)){

			relatorioBean.setColecaoClienteImovelHelper(
					new JRBeanCollectionDataSource(colecaoClienteImovelHelper));
			
			relatorioBean.setQuantidadeImoveis(Integer.valueOf(qtdImoveis.toString()));			

		}
		
		return relatorioBean;
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

			if(cliImovEconAtual.getImovelEconomia()!=null
					&& cliImovEconAtual.getImovelEconomia().getImovelSubcategoria()!=null
					&& cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().getComp_id()!=null
					&& cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().getComp_id().getImovel()!=null){

				helper.setMatriculaFormatadaImovel(cliImovEconAtual.getImovelEconomia().
						getImovelSubcategoria().getComp_id().getImovel().getMatriculaFormatada());

			}

			if(cliImovEconAtual.getImovelEconomia()!=null
					&& cliImovEconAtual.getImovelEconomia().getImovelSubcategoria()!=null
					&& cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().getComp_id()!=null
					&& cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().getComp_id().getSubcategoria()!=null){


				helper.setSubCategoria(cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().
						getComp_id().getSubcategoria().getDescricao());

				if(cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().
						getComp_id().getSubcategoria().getCategoria()!=null){
					
					helper.setCategoria(cliImovEconAtual.getImovelEconomia().getImovelSubcategoria().
							getComp_id().getSubcategoria().getCategoria().getDescricao());

				}
			}
			
			if(cliImovEconAtual.getImovelEconomia()!=null){
				helper.setComplementoEndereco(cliImovEconAtual.getImovelEconomia().getComplementoEndereco());
			}

			if(cliImovEconAtual.getClienteRelacaoTipo()!=null){
				helper.setRelacaoTipo(cliImovEconAtual.getClienteRelacaoTipo().getDescricao());
			}

			if(cliImovEconAtual.getClienteImovelFimRelacaoMotivo()!=null){
				helper.setRelacaoTipo(cliImovEconAtual.getClienteImovelFimRelacaoMotivo().getDescricao());
			}

			helper.setRelacaoDataInicio(cliImovEconAtual.getDataInicioRelacao());
			helper.setRelacaoDataFim(cliImovEconAtual.getDataFimRelacao());
			
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
		AgendadorTarefas.agendarTarefa("RelatorioImoveisRelacionadosCliente", this);
	}

}

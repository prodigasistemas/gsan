package gcom.relatorio.cadastro.imovel;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.bean.ConsultarClienteRelacaoClienteImovelHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDadosCadastraisImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioDadosCadastraisImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_CADASTRAIS_IMOVEL);
	}
	
	public Object executar() throws TarefaException {
		
		List<RelatorioDadosCadastraisImovelBean> relatorioBeans = new ArrayList<RelatorioDadosCadastraisImovelBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DADOS_CADASTRAIS_IMOVEL, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse método cria o RelatorioBean através dos parametros
	 * enviado a este objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioDadosCadastraisImovelBean criarRelatorioBean() {
		
		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");
		
		Collection<ClienteImovel> colecaoClienteImovel = 
			(Collection<ClienteImovel>) getParametro("colecaoClienteImovel");

		Collection<ImovelSubcategoria> colecaoImovelSubCategoria = 
			(Collection<ImovelSubcategoria>) getParametro("colecaoImovelSubcategoria");

		RelatorioDadosCadastraisImovelBean relatorioBean = new RelatorioDadosCadastraisImovelBean();
		
		relatorioBean.setInscricaoImovel(consultarImovelForm.getMatriculaImovelDadosCadastrais());
		relatorioBean.setMatriculaImovel(consultarImovelForm.getIdImovelDadosCadastrais());
		relatorioBean.setEnderecoImovel(consultarImovelForm.getEnderecoImovelDadosCadastrais());
		relatorioBean.setSituacaoAguaImovel(consultarImovelForm.getSituacaoAguaDadosCadastrais());
		relatorioBean.setSituacaoEsgotoImovel(consultarImovelForm.getSituacaoEsgotoDadosCadastrais());
		relatorioBean.setPerfilImovel(consultarImovelForm.getImovelPerfilDadosCadastrais());
		relatorioBean.setTipoDespejo(consultarImovelForm.getDespejoDadosCadastrais());
		relatorioBean.setAreaConstruida(consultarImovelForm.getAreaConstruidaDadosDadosCadastrais());		
		relatorioBean.setTestadaLote(consultarImovelForm.getTestadaLoteDadosCadastrais());
		relatorioBean.setVolumeInferiorReservatorio(consultarImovelForm.getVolumeReservatorioInferiorDadosCadastrais());
		relatorioBean.setVolumeSuperiorReservatorio(consultarImovelForm.getVolumeReservatorioSuperiorDadosCadastrais());
		relatorioBean.setVolumePiscina(consultarImovelForm.getVolumePiscinaDadosCadastrais());
		relatorioBean.setFonteAbastecimento(consultarImovelForm.getFonteAbastecimentoDadosCadastrais());
		relatorioBean.setTipoPoco(consultarImovelForm.getPocoTipoDadosCadastrais());
		relatorioBean.setDivisaoEsgoto(consultarImovelForm.getDivisaoEsgotoDadosCadastrais());
		relatorioBean.setPavimentoRua(consultarImovelForm.getPavimentoRuaDadosCadastrais());
		relatorioBean.setPavimentoCalcada(consultarImovelForm.getPavimentoCalcadaDadosCadastrais());
		relatorioBean.setNumeroIptu(consultarImovelForm.getNumeroIptuDadosCadastrais());
		relatorioBean.setNumeroCompanhiaEletrica(consultarImovelForm.getNumeroCelpeDadosCadastrais());
		relatorioBean.setCoordenadaUTMX(consultarImovelForm.getCoordenadaXDadosCadastrais());
		relatorioBean.setCoordenadaUTMY(consultarImovelForm.getCoordenadaYDadosCadastrais());
		relatorioBean.setOcorrenciaCadastro(consultarImovelForm.getCadastroOcorrenciaDadosCadastrais());
		relatorioBean.setEloAnormalidade(consultarImovelForm.getEloAnormalidadeDadosCadastrais());
		relatorioBean.setIndicadorImovelCondominio(consultarImovelForm.getIndicadorImovelCondominioDadosCadastrais());
		relatorioBean.setMatriculaCondominio(consultarImovelForm.getImovelCondominioDadosCadastrais());
		relatorioBean.setMatriculaImovelPrincipal(consultarImovelForm.getImovelPrincipalDadosCadastrais());
		relatorioBean.setNumeroPontosUtilizacao(consultarImovelForm.getNumeroPontosUtilizacaoDadosCadastrais());
		relatorioBean.setNumeroMoradores(consultarImovelForm.getNumeroMoradoresDadosCadastrais());
		relatorioBean.setIndicadorJardim(consultarImovelForm.getJardimDadosCadastrais());		
		relatorioBean.setTipoHabitacao(consultarImovelForm.getTipoHabitacaoDadosCadastrais());
		relatorioBean.setTipoPropriedade(consultarImovelForm.getTipoPropriedadeDadosCadastrais());
		relatorioBean.setTipoConstrucao(consultarImovelForm.getTipoConstrucaoDadosCadastrais());
		relatorioBean.setTipoCobertura(consultarImovelForm.getTipoCoberturaDadosCadastrais());
		relatorioBean.setDistritoAbastecimento(consultarImovelForm.getDistritoOperacionalDadosCadastrais());

		if( !Util.isVazioOrNulo(colecaoClienteImovel)){			
			relatorioBean.setColecaoClienteImovel(
					new JRBeanCollectionDataSource( criarColecaoClienteImovelHelper(colecaoClienteImovel) ));
		}

		if( !Util.isVazioOrNulo(colecaoImovelSubCategoria)){

			relatorioBean.setColecaoImovelSubcategoriaHelper(
					new JRBeanCollectionDataSource( criarColecaoImovelSubcategoriaHelper(colecaoImovelSubCategoria) ));
		}

		return relatorioBean;
	}

	/**
	 * Esse método cria uma coleção de ImovelSubcategoriaHelper
	 * a partir da coleção de ImovelSubcategoria.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<ImovelSubcategoriaHelper> criarColecaoImovelSubcategoriaHelper(
			Collection<ImovelSubcategoria> colecaoImovelSubCategoria) {
		
		ArrayList<ImovelSubcategoriaHelper> colecaoImovelSubCategoriaHelper = new ArrayList<ImovelSubcategoriaHelper>();

		for(ImovelSubcategoria subCategoria : colecaoImovelSubCategoria){
			ImovelSubcategoriaHelper helperTemp = new ImovelSubcategoriaHelper();
			helperTemp.setImovelSubcategoria(subCategoria);
			colecaoImovelSubCategoriaHelper.add(helperTemp);
		}
		return colecaoImovelSubCategoriaHelper;
	}

	/**
	 * Esse método cria os ClienteImovelHelper
	 * a partir da coleção de ClienteImovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Collection<ConsultarClienteRelacaoClienteImovelHelper> criarColecaoClienteImovelHelper(
			Collection<ClienteImovel> colecaoClienteImovel) {
		
		Collection<ConsultarClienteRelacaoClienteImovelHelper> colecaoHelpers = new ArrayList<ConsultarClienteRelacaoClienteImovelHelper>();

		for(ClienteImovel clienteImovel : colecaoClienteImovel){
			if(clienteImovel.getCliente()!=null 
					&& !Util.isVazioOrNulo(clienteImovel.getCliente().getClienteFones())){
				
				ClienteFone fonePadrao = (ClienteFone)clienteImovel.getCliente().getClienteFones().iterator().next();
				fonePadrao.setIndicadorTelefonePadrao((short)1);
			}
			
			ConsultarClienteRelacaoClienteImovelHelper clienteImovelHelper= new ConsultarClienteRelacaoClienteImovelHelper();
			clienteImovelHelper.setClienteImovel(clienteImovel);
			colecaoHelpers.add(clienteImovelHelper);
		}
		return colecaoHelpers;
	}

	
	/**
	 * Esse método cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(consultarImovelForm.getIdImovelDadosCadastrais()) ){
			parametros.put("idImovelFiltro",consultarImovelForm.getIdImovelDadosCadastrais());
		}
		
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosCadastraisImovel", this);
	}

}

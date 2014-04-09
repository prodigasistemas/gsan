package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ControladorException;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *[UC0958] Gerar Relatório Juras, Multas e Débitos Cancelados
 *
 * @author Marlon Patrick
 * @since 22/10/2009
 */
public class RelatorioJurosMultasDebitosCancelados extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioJurosMultasDebitosCancelados(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS);
	}

	@Deprecated
	public RelatorioJurosMultasDebitosCancelados() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		Map<String, Object> parametros = criarParametros();

		List<RelatorioJurosMultasDebitosCanceladosBean> relatorioBeans = executarConsultaECriarRelatoriosBean(parametros);


//		if(Util.isVazioOrNulo(relatorioBeans)){
//			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_NENHUM_DEBITO_CANCELADO);
//		}
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS,
				 parametros, ds, tipoFormatoRelatorio);
		
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS,
					this.getIdFuncionalidadeIniciada());
			
			return retorno;
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(ConstantesInterfaceGSAN.ERRO_GSAN_ERRO_GRAVAR_RELATORIO_SISTEMA, e);
		}
	}
	

	/**
	 * O método cria os parametros necessários a geração do relatorio.
	 *
	 *@since 13/10/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros(){

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().pesquisarParametrosDoSistema().getImagemRelatorio());

		parametros.put("filtroMesAnoFaturamento",getParametro("filtroMesAnoFaturamento"));
		parametros.put("filtroDataCancelamento",getParametro("filtroDataCancelamento"));
		parametros.put("filtroUnidadeNegocio",getParametro("filtroUnidadeNegocio"));
		parametros.put("filtroLocalidade",getParametro("filtroLocalidade"));
		parametros.put("filtroTipoDebito",getParametro("filtroTipoDebito"));
		parametros.put("filtroCategoria",getParametro("filtroCategoria"));
		parametros.put("filtroPerfilImovel",getParametro("filtroPerfilImovel"));
		parametros.put("filtroEsferaPoder",getParametro("filtroEsferaPoder"));
		parametros.put("filtroUsuarioCancelamento",getParametro("filtroUsuarioCancelamento"));
		
		
		
		return parametros;
	}


	/**
	 * Esse método tem a lógica para realizar a consulta referente ao relatorio
	 * e a partir do resultado obtido criar os beans.
	 *
	 *@since 06/10/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioJurosMultasDebitosCanceladosBean> executarConsultaECriarRelatoriosBean(Map<String, Object> parametros) {

		FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro = (FiltrarRelatorioJurosMultasDebitosCanceladosHelper) getParametro("filtrarRelatorioJurosMultasDebitosCanceladosHelper");

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioJurosMultasDebitosCanceladosHelper> 
				colecaoRelatorioHelper = fachada.pesquisarRelatorioJurosMultasDebitosCancelados(filtro);
		
		if(colecaoRelatorioHelper.isEmpty()){
			throw new FachadaException("atencao.relatorio_juros_multas_debitos_cancelados.nenhum_debito_cancelado");
		}
		
		List<RelatorioJurosMultasDebitosCanceladosBean> relatorioBeans = new ArrayList<RelatorioJurosMultasDebitosCanceladosBean>();
		List<RelatorioJurosMultasDebitosCanceladosBeanHelper> colecaoTotalizadores = new ArrayList<RelatorioJurosMultasDebitosCanceladosBeanHelper>();

		if ( !Util.isVazioOrNulo(colecaoRelatorioHelper)) {
			
			for (RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper : colecaoRelatorioHelper) {
				
				if(!Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())){
					
					for(DebitoCobradoAgrupadoHelper debitoHelper : relatorioHelper.getColecaoDebitosCobrados()){

						RelatorioJurosMultasDebitosCanceladosBean relatorioBean = new RelatorioJurosMultasDebitosCanceladosBean();
						
						relatorioBean.setMesAnoReferencia( Util.formatarAnoMesParaMesAno(relatorioHelper.getAnoMesReferencia()));
						relatorioBean.setDataCancelamento(relatorioHelper.getDataCancelamento());
						relatorioBean.setEndereco(relatorioHelper.getEndereco());
						relatorioBean.setInscricao(relatorioHelper.getInscricao());
						relatorioBean.setMatricula(relatorioHelper.getMatricula());
						
						
						FiltroUsuario filtroUsuario = new FiltroUsuario();
						filtroUsuario.adicionarParametro(new ParametroSimples
								(FiltroUsuario.ID, new Integer(relatorioHelper.getResponsavel())));
						
						Collection<Usuario> colecaoUsuario =  fachada.pesquisar(filtroUsuario, Usuario.class.getName());
						
						Usuario usuarioResponsavel = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
						
						if(usuarioResponsavel!=null){
							relatorioBean.setResponsavel(
									relatorioHelper.getResponsavel()
									+" - "
									+ usuarioResponsavel.getNomeUsuario().split(" ")[0]);
						}else{
							relatorioBean.setResponsavel(
									relatorioHelper.getResponsavel());
						}	
						relatorioBean.setTipoDebito(debitoHelper.getDescricaoDebitoTipo());
						relatorioBean.setValorDebito(debitoHelper.getValorDebito());
						
						relatorioBeans.add(relatorioBean);
						
						criarTotalizador(colecaoTotalizadores, relatorioBean);
					}
				}
			}
		}

		Collections.sort(colecaoTotalizadores);
		parametros.put("colecaoTotalizadores", new JRBeanCollectionDataSource(colecaoTotalizadores));

		Collections.sort(relatorioBeans);
		parametros.put("quantidadeRegistros", relatorioBeans.size());
		
		return relatorioBeans;
	}

	/**
	 * Este método cria o objeto RelatorioJurosMultasDebitosCanceladosBeanHelper que será
	 * usado para exibir a totalização dos dados no relatório.
	 * 
	 * Essa coleção será enviada ao relatório atravésde um parametro.Isso foi necessário
	 * devido o relatório pedir para ordenar por algumas colunas e totalizar por outras,
	 * dessa forma, não é possível fazer isso via iReport.
	 *
	 *@since 21/10/2009
	 *@author Marlon Patrick
	 */
	private void criarTotalizador(
			List<RelatorioJurosMultasDebitosCanceladosBeanHelper> colecaoTotalizadores,
			RelatorioJurosMultasDebitosCanceladosBean relatorioBean) {

		RelatorioJurosMultasDebitosCanceladosBeanHelper helperTotalizador = new RelatorioJurosMultasDebitosCanceladosBeanHelper();
		helperTotalizador.setTipoDebito(relatorioBean.getTipoDebito());
		helperTotalizador.setTotalCancelado(relatorioBean.getValorDebito());
		helperTotalizador.setQuantidadeRegistros(1);
		
		if(colecaoTotalizadores.contains(helperTotalizador)){
			RelatorioJurosMultasDebitosCanceladosBeanHelper helperTotalizadorExistente = 
				colecaoTotalizadores.get(colecaoTotalizadores.indexOf(helperTotalizador));
			
			helperTotalizadorExistente.setQuantidadeRegistros(helperTotalizadorExistente.getQuantidadeRegistros() + 1);
			helperTotalizadorExistente.setTotalCancelado(helperTotalizadorExistente.getTotalCancelado().add(helperTotalizador.getTotalCancelado()));
			
			return;
		}
		
		colecaoTotalizadores.add(helperTotalizador);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro = (FiltrarRelatorioJurosMultasDebitosCanceladosHelper) getParametro("filtrarRelatorioJurosMultasDebitosCanceladosHelper");
			
		retorno = fachada.countRelatorioJurosMultasDebitosCancelados(filtro);
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioJurosMultasDebitosCancelados", this);

	}

}

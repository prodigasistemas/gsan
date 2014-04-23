package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovelSimplificado;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.ImovelDoacao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
 * 
 * @author Daniel Alves,Hugo Amorim
 * @date 28/09/2010,04/10/2010
 */

public class RelatorioImoveisDoacoesImovel extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioImoveisDoacoesImovel(Usuario usuario) {		
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_IMOVEL);
	}
	
	@Deprecated
	public RelatorioImoveisDoacoesImovel() {
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

		String filtroImovel = (String) getParametro("filtroImovel");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
			
		Collection<ImovelDoacao> colecaoImoveisDoacoes = null;
	
		colecaoImoveisDoacoes = pesquisarDoacoes();		
		
		RelatorioImoveisDoacoesImovelBean relatorioBean = null;
		
		for(ImovelDoacao imovel : colecaoImoveisDoacoes){
			relatorioBean = new RelatorioImoveisDoacoesImovelBean();
			
			relatorioBean.setEntidade(imovel.getEntidadeBeneficente().getCliente().getNome());
			
			FiltroClienteImovel filtro = new FiltroClienteImovel();			
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getImovel().getId()));
			Collection col = fachada.pesquisarClienteImovel(filtro);			
			ClienteImovelSimplificado client = (ClienteImovelSimplificado)Util.retonarObjetoDeColecao(col);
			
			relatorioBean.setCliente(client.getCliente().getNome());
			
			relatorioBean.setDataAdesao(imovel.getDataAdesaoAsString());
			
			if(imovel.getDataCancelamentoAsString() != null && !imovel.getDataCancelamentoAsString().equals("")){
				relatorioBean.setDataCancelamento(imovel.getDataCancelamentoAsString());
			}else{
				relatorioBean.setDataCancelamento(" ");
			}
			
			if(imovel.getAnoMesReferenciaInicial() != null && !imovel.getAnoMesReferenciaInicial().equals("")){
				relatorioBean.setMesAnoInicio(Util.formatarAnoMesParaMesAno(imovel.getAnoMesReferenciaInicial()));
			}else{
				relatorioBean.setMesAnoInicio(" ");
			}
			
			if(imovel.getAnoMesReferenciaFinal() != null && !imovel.getAnoMesReferenciaFinal().equals("")){
				relatorioBean.setMesAnoFinal(Util.formatarAnoMesParaMesAno(imovel.getAnoMesReferenciaFinal()));
			}else{
				relatorioBean.setMesAnoFinal(" ");
			}
			
			relatorioBean.setUsuarioAdesao(imovel.getUsuarioAdesao().getNomeUsuario());
			try{
			relatorioBean.setUsuarioCancelamento(imovel.getUsuarioCancelamento().getNomeUsuario().toUpperCase());
			}catch(NullPointerException e){
				relatorioBean.setUsuarioCancelamento(" ");
			}
			relatorioBean.setValor(imovel.getValorDoacao());
			
			relatorioBeans.add(relatorioBean);
		}
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("filtroImovel", Util.retornaMatriculaImovelFormatada(new Integer(filtroImovel)));
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "R1174");
		
		
		
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) relatorioBeans );

		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_IMOVEL, parametros, ds,
					tipoFormatoRelatorio);
		
 
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_DOACOES_IMOVEL,
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
			
		Integer retorno = new Integer(0);
		
		retorno = pesquisarDoacoes().size();
		
		if(retorno.intValue() == 0 ){
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
	}
	
	private Collection pesquisarDoacoes(){
		
		String filtroImovel = (String) getParametro("filtroImovel");
		
		Collection colecaoImoveisDoacoes = null;
		
		FiltroImovelDoacao filtro = new FiltroImovelDoacao();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID_IMOVEL, filtroImovel));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("entidadeBeneficente.cliente");
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioAdesao");
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioCancelamento");
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
		filtro.setCampoOrderBy("entidadeBeneficente.cliente.nome");
		
		filtro.setCampoOrderBy("dataAdesao");
		
		colecaoImoveisDoacoes = Fachada.getInstancia().pesquisar(filtro, ImovelDoacao.class.getName());
		
		return colecaoImoveisDoacoes;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisDoacoesImovel", this);
	}
	
}

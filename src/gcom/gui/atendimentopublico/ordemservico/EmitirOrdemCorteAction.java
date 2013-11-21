package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoSeletiva;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioOrdemCorteOnline;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0983] Emitir Ordem de Fiscalização
 * 
 * Este Caso Uso permite realizar a emissão de Documentos de Ordem de Fiscalização
 * de forma individual para um determinado imóvel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class EmitirOrdemCorteAction extends
		ExibidorProcessamentoTarefaRelatorio {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		EmitirOrdemCorteForm form =
			(EmitirOrdemCorteForm) actionForm;
		
		this.pesquisarEconomias(form,fachada);
		this.pesquisarDadosImovel(form,fachada);
		
		String idOrdemServico = null;
		String dataGeracaoOs = null;

		ServicoTipo servicoTipo = 
			fachada.recuperaServicoTipoPorConstante(
					ServicoTipo.TIPO_CORTE_LIGACAO_AGUA);
		
		if(servicoTipo==null){
			
			servicoTipo = 
				fachada.recuperaServicoTipoPorConstanteServicoTipoSeletivo(
						ServicoTipoSeletiva.CORTE_SELETIVO);	
			
		}
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}
		
		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer tipoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(2);
		Integer indicadorCredito = new Integer(2);
		Integer indicadorNotas = new Integer(2);
		Integer indicadorGuias = new Integer(2);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						form.getMatriculaImovel().toString().trim(), null, null,
						anoMesInicial, anoMesFinal,
						dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento
								.intValue(), indicadorConta
								.intValue(), indicadorDebito
								.intValue(), indicadorCredito
								.intValue(), indicadorNotas
								.intValue(), indicadorGuias
								.intValue(), indicadorAtualizar
								.intValue(), null);
		
		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();
		
		if(colecaoContaValores == null || colecaoContaValores .isEmpty()){
			throw new ActionServletException("atencao.imovel.sem.debito",form.getMatriculaImovel().toString().trim());
		}
		
		if(servicoTipo==null){
			throw new ActionServletException("atencao.servico_tipo_nao_existe");
		}
		
		FiltroOrdemServico filtro = new FiltroOrdemServico();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroOrdemServico.ID_IMOVEL, form.getMatriculaImovel()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroOrdemServico.SITUACAO, OrdemServico.SITUACAO_PENDENTE));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroOrdemServico.ID_SERVICO_TIPO,servicoTipo.getId()));
		
		Collection colecaoOrdensServido 
			= fachada.pesquisar(filtro, OrdemServico.class.getName());
		
		OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdensServido);
		
		if(ordemServico == null){

			servicoTipo = 
				fachada.recuperaServicoTipoPorConstanteServicoTipoSeletivo(
						ServicoTipoSeletiva.CORTE_SELETIVO);
			
			FiltroOrdemServico filtroSeletiva = new FiltroOrdemServico();
			
			filtroSeletiva.adicionarParametro(
					new ParametroSimples(FiltroOrdemServico.ID_IMOVEL, form.getMatriculaImovel()));
			filtroSeletiva.adicionarParametro(
					new ParametroSimples(FiltroOrdemServico.SITUACAO, OrdemServico.SITUACAO_PENDENTE));
			filtroSeletiva.adicionarParametro(
					new ParametroSimples(FiltroOrdemServico.ID_SERVICO_TIPO,servicoTipo.getId()));
			
			Collection colecaoOrdensServicoSeletiva 
				= fachada.pesquisar(filtroSeletiva, OrdemServico.class.getName());
			
			ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdensServicoSeletiva);
		}
		
		if(ordemServico!=null){
			idOrdemServico  = ordemServico.getId().toString();
		}
		
		
		if(idOrdemServico==null){
			throw new ActionServletException("atencao.ordem_servico_nao_encontrada");
		}
		
		Date dataAtual = new Date();	
		dataGeracaoOs = Util.formatarData(dataAtual);
		
		ordemServico.setDataEmissao(dataAtual);
		
		fachada.atualizar(ordemServico);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioOrdemCorteOnline relatorioOrdemCorteOnline = 
			new RelatorioOrdemCorteOnline((Usuario)
					(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioOrdemCorteOnline.addParametro("idsOrdemServico", idOrdemServico);
		relatorioOrdemCorteOnline.addParametro("endereco", form.getEnderecoImovel());
		relatorioOrdemCorteOnline.addParametro("matricula", new Integer(form.getMatriculaImovel()));
		relatorioOrdemCorteOnline.addParametro("situacaoAgua", form.getSituacaoLigacaoAgua());
		relatorioOrdemCorteOnline.addParametro("sistuacaoEsgoto",form.getSituacaoLigacaoEsgoto());
		relatorioOrdemCorteOnline.addParametro("inscricao", form.getInscricaoImovel());
		relatorioOrdemCorteOnline.addParametro("numeroHidrometro", form.getNumeroHidrometro());
		relatorioOrdemCorteOnline.addParametro("grupo", form.getFaturamentoGrupo());
		relatorioOrdemCorteOnline.addParametro("dataGeracaoOs", dataGeracaoOs);
		relatorioOrdemCorteOnline.addParametro("nomeCliente", form.getNomeCliente());
		relatorioOrdemCorteOnline.addParametro("anoMesfaturamentoGrupo",Util.formatarAnoMesParaMesAno(form.getAnoMesfaturamentoGrupo()));
		relatorioOrdemCorteOnline.addParametro("perfilImovel", form.getDescricaoPerfilImovel());
		relatorioOrdemCorteOnline.addParametro("unidadeNegocio", form.getUnidadeNegocio());
		
		
		relatorioOrdemCorteOnline.addParametro("qtdeEconResidencial",
				form.getQtdeEconResidencial()!=null?
					form.getQtdeEconResidencial().toString():null);
		relatorioOrdemCorteOnline.addParametro("qtdeEconComercial",
				form.getQtdeEconComercial()!=null?
						form.getQtdeEconComercial().toString():null);
		relatorioOrdemCorteOnline.addParametro("qtdeEconIndustrial",
				form.getQtdeEconIndustrial()!=null?
						form.getQtdeEconIndustrial().toString():null);
		relatorioOrdemCorteOnline.addParametro("qtdeEconPublica",
				form.getQtdeEconPublica()!=null?
						form.getQtdeEconPublica().toString():null);
		relatorioOrdemCorteOnline.addParametro("qtdeEconTotal",
				form.getQtdeEconTotal()!=null?
					form.getQtdeEconTotal().toString():null);
		
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioOrdemCorteOnline.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioOrdemCorteOnline,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
	
	private void pesquisarEconomias(EmitirOrdemCorteForm form,
			Fachada fachada) {
		
		Collection imovelSubcategorias = 
			fachada.pesquisarCategoriasImovel(
					new Integer(form.getMatriculaImovel().trim()));
		
		form.setQtdeEconResidencial(new Integer(0));
		form.setQtdeEconComercial(new Integer(0));
		form.setQtdeEconIndustrial(new Integer(0));
		form.setQtdeEconPublica(new Integer(0));
		form.setQtdeEconTotal(new Integer(0));
	
		for (Iterator iterator = imovelSubcategorias.iterator(); iterator
				.hasNext();) {
			
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
			
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.RESIDENCIAL)==0){
				form.setQtdeEconResidencial(form.getQtdeEconResidencial()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.COMERCIAL)==0){
				form.setQtdeEconComercial(form.getQtdeEconComercial()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.INDUSTRIAL)==0){
				form.setQtdeEconIndustrial(form.getQtdeEconIndustrial()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
			if(imovelSubcategoria
					.getComp_id().getSubcategoria()
						.getCategoria().getId().compareTo(Categoria.PUBLICO)==0){
				form.setQtdeEconPublica(form.getQtdeEconPublica()+
						imovelSubcategoria.getQuantidadeEconomias());
				form.setQtdeEconTotal(form.getQtdeEconTotal()+
						imovelSubcategoria.getQuantidadeEconomias());
			}
		}
	}
	
	private void pesquisarDadosImovel(EmitirOrdemCorteForm form,
			Fachada fachada) {

		FiltroImovel filtro = new FiltroImovel();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatriculaImovel()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.FATURAMENTO_GRUPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CLIENTES_IMOVEIS);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE_UNIDADE_NEGOCIO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_HIDROMETRO);
		
		
		Collection<Imovel> imoveis = fachada.pesquisar(filtro, Imovel.class.getName());
		
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(imoveis);
		
		form.setFaturamentoGrupo(imovel.getQuadra().getRota().getFaturamentoGrupo().getDescricaoAbreviada().toString());
		
		form.setUnidadeNegocio(imovel.getLocalidade().getUnidadeNegocio().getNome());
		
		form.setAnoMesfaturamentoGrupo(imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia().toString());
		
		form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
		
		form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
		
		form.setDescricaoPerfilImovel(imovel.getImovelPerfil().getDescricao());
		
		Cliente clienteUsuario = imovel.getClienteUsuario();
		
		if(clienteUsuario!=null){
			
			form.setNomeCliente(clienteUsuario.getNome());
			
		}
		
		if(imovel!=null 
			&& imovel.getLigacaoAgua()!=null 
			&& imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico()!=null
			&& imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro()!=null){
			form.setNumeroHidrometro(imovel
					.getLigacaoAgua()
					.getHidrometroInstalacaoHistorico()
					.getHidrometro().getNumero());
		}
		
	}
}

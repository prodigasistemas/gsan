package gcom.gui.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ValidarSituacaoEspecialFaturamentoInserirAtualizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = (SituacaoEspecialFaturamentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);				
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		Fachada fachada = Fachada.getInstancia();

		String mesAnoReferenciaFaturamentoInicial = situacaoEspecialFaturamentoActionForm.getMesAnoReferenciaFaturamentoInicial();
		boolean mesAnoReferenciaInicialValido = Util.validarMesAno(mesAnoReferenciaFaturamentoInicial);

		String mesAnoReferenciaFaturamentoFinal = situacaoEspecialFaturamentoActionForm.getMesAnoReferenciaFaturamentoFinal();
		boolean mesAnoReferenciaFinalValido = Util.validarMesAno(mesAnoReferenciaFaturamentoFinal);

		Integer anoMesReferenciaInicial = null;
		Integer anoMesReferenciaFinal = null;

		if ((mesAnoReferenciaFaturamentoInicial != null && mesAnoReferenciaFaturamentoFinal != null)
				&& (!mesAnoReferenciaFaturamentoInicial.equals("") && !mesAnoReferenciaFaturamentoFinal.equals(""))) {
			
			if (!mesAnoReferenciaInicialValido) {
				throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido", null, "inicial");
			}
			
			if (!mesAnoReferenciaFinalValido) {
				throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido", null, "final");
			}
			
			anoMesReferenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferenciaFaturamentoInicial);
			anoMesReferenciaFinal = Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferenciaFaturamentoFinal);

			boolean dataInicialSuperiorMenor = Util.compararAnoMesReferencia(new Integer(anoMesReferenciaInicial), new Integer(anoMesReferenciaFinal), "<");
			boolean dataInicialSuperiorIgual = Util.compararAnoMesReferencia(new Integer(anoMesReferenciaInicial), new Integer(anoMesReferenciaFinal), "=");

			if (dataInicialSuperiorMenor || dataInicialSuperiorIgual) {

				Integer anoMesInicial = fachada.validarMesAnoReferencia(transferirActionFormParaHelper(situacaoEspecialFaturamentoActionForm,usuarioLogado));
				
				if (anoMesInicial > (anoMesReferenciaInicial)) {
					throw new ActionServletException("atencao.mes.ano.anterior.mes.ano.corrente.imovel");
				}
			} else {
				throw new ActionServletException("atencao.mes.ano.inicial.maior.mes.ano.final");
			}
		} else {
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Mês e Ano de Referência do Faturamento Inicial e Final");
		}

		SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = transferirActionFormParaHelper(situacaoEspecialFaturamentoActionForm,usuarioLogado);

		Collection pesquisarImoveisParaSerInseridos = (Collection) sessao.getAttribute("SEMSituacaoEspecialFaturamento"); 
			
		boolean retirar = false;
		
		fachada.inserirSituacaoEspecialFaturamento(situacaoEspecialFaturamentoHelper, retirar,pesquisarImoveisParaSerInseridos,
													new Integer(situacaoEspecialFaturamentoActionForm.getIdFaturamentoSituacaoTipo()), 
													anoMesReferenciaInicial,anoMesReferenciaFinal);
		
		
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

		filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoTipo.ID, 
																				situacaoEspecialFaturamentoActionForm.getIdFaturamentoSituacaoTipo()));
		
		Collection collectionFaturamentoSituacaoTipo = fachada.pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
		String descricaoFaturamentoSituacaoTipo = ((FaturamentoSituacaoTipo) Util.retonarObjetoDeColecao(collectionFaturamentoSituacaoTipo)).getDescricao();
		
		montarPaginaSucesso(httpServletRequest, situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento()
						+ " imóvel(is) inserido(s) na situação especial de faturamento "
						+ descricaoFaturamentoSituacaoTipo.toLowerCase() + " com sucesso.",
						"Realizar outra Manutenção de Situação Especial de Faturamento",
						"exibirSituacaoEspecialFaturamentoInformarAction.do?menu=sim");
		
		return retorno;
	}

	private SituacaoEspecialFaturamentoHelper transferirActionFormParaHelper(
			SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm,
			Usuario usuarioLogado) {

		SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = new SituacaoEspecialFaturamentoHelper();

		situacaoEspecialFaturamentoHelper
				.setIdImovel(situacaoEspecialFaturamentoActionForm
						.getIdImovel() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getIdImovel());

		situacaoEspecialFaturamentoHelper
				.setInscricaoTipo(situacaoEspecialFaturamentoActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getInscricaoTipo());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoNM(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoNM());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemNM(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemNM());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemOrigem(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeDestino(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemID(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeDestinoID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeOrigemID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialDestino(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemDestino(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoID(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoID());

		situacaoEspecialFaturamentoHelper
				.setTipoSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getTipoSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getTipoSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setSubloteOrigem(situacaoEspecialFaturamentoActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteOrigem());

		situacaoEspecialFaturamentoHelper
				.setSubloteDestino(situacaoEspecialFaturamentoActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteDestino());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoMotivo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoMotivo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoMotivo());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoTipo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoTipo());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoInicial(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoInicial() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoInicial());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoFinal(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoFinal() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoFinal());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisCOMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisSEMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialFaturamentoHelper
		.setCodigoRotaInicial(situacaoEspecialFaturamentoActionForm
				.getCdRotaInicial() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getCdRotaInicial());
		
		situacaoEspecialFaturamentoHelper
		.setCodigoRotaFinal(situacaoEspecialFaturamentoActionForm
				.getCdRotaFinal() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getCdRotaFinal());
		
		situacaoEspecialFaturamentoHelper
		.setSequencialRotaInicial(situacaoEspecialFaturamentoActionForm
				.getSequencialRotaInicial() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getSequencialRotaInicial());
		
		situacaoEspecialFaturamentoHelper
		.setSequencialRotaFinal(situacaoEspecialFaturamentoActionForm
				.getSequencialRotaFinal() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getSequencialRotaFinal());
		
		//Colocado por Raphael Rossiter em 11/08/2008 - Analista:Rosana Carvalho
		if (situacaoEspecialFaturamentoActionForm.getConsumoFixoMedido() != null &&
			!situacaoEspecialFaturamentoActionForm.getConsumoFixoMedido().equals("")){
			
			situacaoEspecialFaturamentoHelper.setNumeroConsumoAguaMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getConsumoFixoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getConsumoFixoNaoMedido() != null &&
				!situacaoEspecialFaturamentoActionForm.getConsumoFixoNaoMedido().equals("")){
				
			situacaoEspecialFaturamentoHelper.setNumeroConsumoAguaNaoMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getConsumoFixoNaoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getVolumeFixoMedido() != null &&
			!situacaoEspecialFaturamentoActionForm.getVolumeFixoMedido().equals("")){
				
			situacaoEspecialFaturamentoHelper.setNumeroVolumeEsgotoMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getVolumeFixoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getVolumeFixoNaoMedido() != null &&
			!situacaoEspecialFaturamentoActionForm.getVolumeFixoNaoMedido().equals("")){
				
			situacaoEspecialFaturamentoHelper.setNumeroVolumeEsgotoNaoMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getVolumeFixoNaoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getObservacaoInforma() != null &&
			!situacaoEspecialFaturamentoActionForm.getObservacaoInforma().equals("")){
			
			situacaoEspecialFaturamentoHelper.setObservacaoInforma(
					situacaoEspecialFaturamentoActionForm.getObservacaoInforma());
					
			situacaoEspecialFaturamentoHelper.setObservacao(
			situacaoEspecialFaturamentoActionForm.getObservacaoInforma());
		}
		
		//situacaoEspecialFaturamentoHelper.setIdUsuarioInforma(usuarioLogado.getId().toString());
		
		//situacaoEspecialFaturamentoHelper.setIdUsuario(usuarioLogado.getId().toString());
		situacaoEspecialFaturamentoHelper.setUsuarioLogado(usuarioLogado);

		Integer quantidadeImoveisSem = 0;
		
		
		if (situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento()!=null 
				&& !situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento().equals("")){
			
			quantidadeImoveisSem =new Integer(situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento());
		}
		
		Integer quantidadeTotal =  quantidadeImoveisSem;
		
		situacaoEspecialFaturamentoHelper.setQuantidadeDeImoveis(quantidadeTotal.toString());
		
		situacaoEspecialFaturamentoHelper.setIdsCategoria(situacaoEspecialFaturamentoActionForm.getIdsCategoria());
		
		if (situacaoEspecialFaturamentoActionForm.getIdsCategoria() != null) {
			
			String [] idsCategoria = situacaoEspecialFaturamentoActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		}
		
		situacaoEspecialFaturamentoHelper.setIndicadorConsumoImovel(
				situacaoEspecialFaturamentoActionForm.getIndicadorConsumoImovel());
		
		
		return situacaoEspecialFaturamentoHelper;
	}
	
}

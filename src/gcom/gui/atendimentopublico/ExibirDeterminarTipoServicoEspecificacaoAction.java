package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
import gcom.atendimentopublico.FiltroEspecificacaoPavimentacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroLocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirDeterminarTipoServicoEspecificacaoAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("determinarTipoServicoEspecificacao");	
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		DeterminarTipoServicoEspecificacaoActionForm determinarTipoServicoEspecificacaoActionForm = (DeterminarTipoServicoEspecificacaoActionForm) actionForm;
		
		String pesquisarTipoServico = httpServletRequest.getParameter("pesquisarTipoServico");
		
		sessao.removeAttribute("corTipoServico");
		
		if(pesquisarTipoServico != null && pesquisarTipoServico.equals("OK")){
			ServicoTipo servicoTipo = fachada.pesquisarSevicoTipo(
					new Integer(determinarTipoServicoEspecificacaoActionForm.getTipoServico()));
			
			if(servicoTipo != null){
				determinarTipoServicoEspecificacaoActionForm.setTipoServico(servicoTipo.getId().toString());
				determinarTipoServicoEspecificacaoActionForm.setDescricaoTipoServico(servicoTipo.getDescricao());
			}else{
				determinarTipoServicoEspecificacaoActionForm.setTipoServico("");
				determinarTipoServicoEspecificacaoActionForm.setDescricaoTipoServico("Tipo de Serviço Inexistente");
				sessao.setAttribute("corTipoServico", "exception");
			}
		}
		//Solicitação Tipo		
		FiltroSolicitacaoTipo filtroTipoSolicitacao = new FiltroSolicitacaoTipo();
		filtroTipoSolicitacao.adicionarParametro( new ParametroSimples
				(FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		
		filtroTipoSolicitacao.adicionarParametro( new ParametroSimples
				(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, ConstantesSistema.INDICADOR_USO_DESATIVO));
		Collection colecaoTipoSolicitacao = fachada.pesquisar(
				filtroTipoSolicitacao, SolicitacaoTipo.class.getName());
		if ((colecaoTipoSolicitacao == null || colecaoTipoSolicitacao.isEmpty())) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Solicitação Tipo");
		}
		sessao.setAttribute("colecaoTipoSolicitacao", colecaoTipoSolicitacao);
		
		String tipoSolicitacao = determinarTipoServicoEspecificacaoActionForm.getTiposSolicitacao();
		Collection colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		
		if (tipoSolicitacao != null && !tipoSolicitacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			// Solicitação Tipo Especificação		
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro( new ParametroSimples
					(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, tipoSolicitacao));		
			colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			if ((colecaoSolicitacaoTipoEspecificacao == null || colecaoSolicitacaoTipoEspecificacao.isEmpty()) &&
					!determinarTipoServicoEspecificacaoActionForm.getTiposSolicitacao().equals("-1")) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Solicitação Tipo Especificação");
			}
		}
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
		
		
		// Local de Ocorrência		
		FiltroLocalOcorrencia filtroLocalOcorrencia = new FiltroLocalOcorrencia();
		filtroLocalOcorrencia.adicionarParametro( new ParametroSimples
				(FiltroLocalOcorrencia.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		
		Collection colecaoLocalOcorrencia = fachada.pesquisar(
				filtroLocalOcorrencia, LocalOcorrencia.class.getName());
		if (colecaoLocalOcorrencia == null || colecaoLocalOcorrencia.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Local de Ocorrência");
		}
		sessao.setAttribute("colecaoLocalOcorrencia", colecaoLocalOcorrencia);
		
		// Pavimento Rua	
		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro( new ParametroSimples
				(FiltroPavimentoRua.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		
		Collection colecaoPavimentoRua = fachada.pesquisar(
				filtroPavimentoRua, PavimentoRua.class.getName());
		if (colecaoPavimentoRua == null || colecaoPavimentoRua.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Pavimento Rua");
		}
		sessao.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		
		// Pavimento Calçada	
		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		filtroPavimentoCalcada.adicionarParametro( new ParametroSimples
				(FiltroPavimentoCalcada.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		
		Collection colecaoPavimentoCalcada = fachada.pesquisar(
				filtroPavimentoCalcada, PavimentoCalcada.class.getName());
		if (colecaoPavimentoCalcada == null || colecaoPavimentoCalcada.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Pavimento Calçada");
		}
		sessao.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		
		sessao.setAttribute("tiposSolicitacao",determinarTipoServicoEspecificacaoActionForm.getTiposSolicitacao());
		
		//Colecão Tipo de Serviço da Ordem de Serviço por Especificação para mostrar na Grid
    	
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")){
		
    	FiltroEspecificacaoPavimentacaoServicoTipo filtro = new FiltroEspecificacaoPavimentacaoServicoTipo();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.LOCALOCORRENCIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTOCALCADA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTORUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.SOLICITACAOTIPOESPECIFICACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.SERVICOTIPO);
		
		filtro.setCampoOrderBy(FiltroEspecificacaoPavimentacaoServicoTipo.SOLICITACAOTIPOESPECIFICACAO);
		filtro.setCampoOrderBy(FiltroEspecificacaoPavimentacaoServicoTipo.LOCALOCORRENCIA);
		filtro.setCampoOrderBy(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTORUA);
		filtro.setCampoOrderBy(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTOCALCADA);
		filtro.setCampoOrderBy(FiltroEspecificacaoPavimentacaoServicoTipo.SERVICOTIPO);
		
		Collection result = 
			fachada.pesquisar(filtro, 
				EspecificacaoPavimentacaoServicoTipo.class.getName());
		
		sessao.setAttribute("colecaoEspServTipo", result);
		
		}
		// atualiza coleção
		if (httpServletRequest.getParameter("adicionarTipoServicoEspecificacao") != null
                && httpServletRequest.getParameter("adicionarTipoServicoEspecificacao").equalsIgnoreCase("S")){
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest, actionForm);
        	
        	httpServletRequest.setAttribute("adicionarTipoServicoEspecificacao","N");
        } 
		
		return retorno;
	}
	
	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest, ActionForm actionForm){
		
		DeterminarTipoServicoEspecificacaoActionForm detToServEspActionForm = 
			(DeterminarTipoServicoEspecificacaoActionForm) actionForm;

		// Instancia o objeto
		EspecificacaoPavimentacaoServicoTipo espPavServTp = this.instanciarObjeto(detToServEspActionForm);
		
//		 ------------- Verificar preenchimento dos campos ------------- //
		if(espPavServTp.getSolicitacaoTipoEspecificacao() == null
				|| espPavServTp.getLocalOcorrencia() == null
				|| espPavServTp.getPavimentoCalcada() == null
				|| espPavServTp.getPavimentoRua() == null
				|| espPavServTp.getServicoTipo() == null){
			throw new ActionServletException("atencao.inserir_especificacao_pavimentacao_servico_tipo",
					null, "");
		}

		Collection<EspecificacaoPavimentacaoServicoTipo> colecaoEspServTipo = null;
		
		if (sessao.getAttribute("colecaoEspServTipo") != null) {
			colecaoEspServTipo = (Collection<EspecificacaoPavimentacaoServicoTipo>) sessao
                    .getAttribute("colecaoEspServTipo");
        } else {
        	colecaoEspServTipo = new ArrayList();
        }
    	
		if (colecaoEspServTipo.contains(espPavServTp)) {
			throw new ActionServletException("atencao.especificacao_pavimentacao_servico_tipo_existente");
			
		} else {
		colecaoEspServTipo.add(espPavServTp);
		}
		
		//Ordena a coleção
		Collections.sort((List) colecaoEspServTipo,
				new Comparator() {
					public int compare(Object a, Object b) {
						
						String posicao1 = ((EspecificacaoPavimentacaoServicoTipo) a)
							.getSolicitacaoTipoEspecificacao().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) a)
							.getLocalOcorrencia().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) a)
							.getPavimentoRua().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) a)
							.getPavimentoCalcada().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) a)
							.getServicoTipo().getDescricao();
						
						String posicao2 = ((EspecificacaoPavimentacaoServicoTipo) b)
							.getSolicitacaoTipoEspecificacao().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) b)
							.getLocalOcorrencia().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) b)
							.getPavimentoRua().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) b)
							.getPavimentoCalcada().getDescricao()
							+ ((EspecificacaoPavimentacaoServicoTipo) b)
							.getServicoTipo().getDescricao();
						
							return posicao1.compareTo(posicao2);
					}
				});
		
		 //manda para a sessão a coleção
        sessao.setAttribute("colecaoEspServTipo", colecaoEspServTipo);

    }
	
	private EspecificacaoPavimentacaoServicoTipo instanciarObjeto(DeterminarTipoServicoEspecificacaoActionForm form){
		EspecificacaoPavimentacaoServicoTipo espPavServTp = new EspecificacaoPavimentacaoServicoTipo();
		String idServicoTipo = form.getTipoServico();
		Fachada fachada = Fachada.getInstancia();
		
		if(!(form.getTiposSolicitacao() == null || form.getTiposEspecificacaoSolicitacao() == null
				|| form.getPavimentoRua() == null || form.getPavimentoCalcada() == null
				|| form.getLocaisOcorrencia() == null || idServicoTipo == null)){
			
			SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
			solicitacaoTipo.setId(Integer.valueOf(form.getTiposSolicitacao()));
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, form.getTiposEspecificacaoSolicitacao()));
			
			Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
					filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacao.iterator().next();
			espPavServTp.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);

			FiltroLocalOcorrencia filtroLocalOcorrencia = new FiltroLocalOcorrencia();
			filtroLocalOcorrencia.adicionarParametro(
					new ParametroSimples(FiltroLocalOcorrencia.ID, form.getLocaisOcorrencia()));
			
			Collection colecaoLocalOcorrencia = fachada.pesquisar(filtroLocalOcorrencia, LocalOcorrencia.class.getName());
			
			LocalOcorrencia localOcorrencia = (LocalOcorrencia) colecaoLocalOcorrencia.iterator().next();
			espPavServTp.setLocalOcorrencia(localOcorrencia);
			
			FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
			filtroPavimentoRua.adicionarParametro(
					new ParametroSimples(FiltroPavimentoRua.ID, form.getPavimentoRua()));
			
			Collection colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
			PavimentoRua pavimentoRua = (PavimentoRua) colecaoPavimentoRua.iterator().next();
			espPavServTp.setPavimentoRua(pavimentoRua);
			
			FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
			filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
					FiltroPavimentoCalcada.ID, form.getPavimentoCalcada()));
			
			Collection colecaoPavimentoCalcada = fachada.pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
			PavimentoCalcada pavimentoCalcada = (PavimentoCalcada) colecaoPavimentoCalcada.iterator().next();
			espPavServTp.setPavimentoCalcada(pavimentoCalcada);
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));
			
			Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo,
                    ServicoTipo.class.getName());
            
            if(colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()){
            	ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next(); 
            	espPavServTp.setServicoTipo(servicoTipo);
            }else{
            	throw new ActionServletException("atencao.tipo_servico_inexistente");
            }
			
            espPavServTp.setUltimaAlteracao(new Date());
		}		
		return espPavServTp;
	}
}

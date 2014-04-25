package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSAtividadePeriodoExecucaoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSExecucaoEquipeHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização dos dados das atividades de uma OS
 * (Horas)
 * 
 * @author Raphael Rossiter
 * @date 18/09/2006
 */
public class ExibirManterHorasExecucaoEquipeOSAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterHorasExecucaoEquipeOS");

		ManterHorasExecucaoEquipeOSActionForm form = (ManterHorasExecucaoEquipeOSActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//Carregamento inicial
		String numeroOS = httpServletRequest.getParameter("ordemServico");
		
		if (numeroOS != null && !numeroOS.equalsIgnoreCase("")){
			
			//Identifica se a chamada foi feita por uma tela principal ou por um popup
			String caminhoRetorno = httpServletRequest.getParameter("caminhoRetorno");
			
			if (caminhoRetorno != null && !caminhoRetorno.equalsIgnoreCase("")){
				sessao.setAttribute("caminhoRetornoManterHoras", caminhoRetorno);
			}
			
			String idAtividade = httpServletRequest.getParameter("idAtividade");
			String descricaoAtividade = httpServletRequest.getParameter("descricaoAtividade");
			
			form.setNumeroOS(numeroOS);
			form.setIdAtividade(idAtividade);
			form.setDescricaoAtividade(descricaoAtividade);
			
			/*
			 * Apresentar na data de execução a data do roteiro caso tenha sido informada, caso contrário informar 
			 * a data do encerramento
			 */
			String dataRoteiro = httpServletRequest.getParameter("dataRoteiro");
			String dataEncerramento = httpServletRequest.getParameter("dataEncerramento");
			
			if (dataRoteiro != null && !dataRoteiro.equalsIgnoreCase("")){
				form.setDataExecucao(dataRoteiro);
				form.setDataEncerramentoOS("");
				httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
				sessao.setAttribute("desabilitarDataExecucao", "OK");
			}
			else{
				form.setDataExecucao(dataEncerramento);
				form.setDataEncerramentoOS(dataEncerramento);
				httpServletRequest.setAttribute("nomeCampo", "dataExecucao");
				sessao.removeAttribute("desabilitarDataExecucao");
			}
			
			//Equipes Programadas
			Collection colecaoEquipes = fachada.obterEquipesProgramadas(Util
					.converterStringParaInteger(numeroOS));
			if (colecaoEquipes != null && !colecaoEquipes.isEmpty()) {
				sessao.setAttribute("colecaoEquipe", colecaoEquipes);
			} 
			
			
			//Caso a OS esteja associada a um documento de cobrança
			sessao.removeAttribute("documentoCobranca");
			sessao.removeAttribute("registroAtendimento");
			sessao.removeAttribute("colecaoEquipesPorUnidade");
			
			if (fachada.verificarOSAssociadaDocumentoCobranca(Util.converterStringParaInteger(numeroOS))){
				sessao.setAttribute("documentoCobranca", "OK");
			}
			//Caso a OS esteja associada a um RA
			else if (fachada.verificarOSAssociadaRA(Util.converterStringParaInteger(numeroOS))){
				
				//Pesquisa o último tramite do RA para obter a unidade de destino
				Integer idUnidadeDestino = fachada.obterUnidadeDestinoUltimoTramite(Util.converterStringParaInteger(numeroOS));
				if (idUnidadeDestino != null){
					
					//Obtém todas as equipes pertencentens a unidade de destino
					Collection colecaoEquipesPorUnidade = fachada.obterEquipesPorUnidade(idUnidadeDestino);
					
					if (colecaoEquipesPorUnidade != null && !colecaoEquipesPorUnidade.isEmpty()){
						sessao.setAttribute("registroAtendimento", "OK");
						sessao.setAttribute("colecaoEquipesPorUnidade", colecaoEquipesPorUnidade);
					}else{
						throw new ActionServletException("atencao.unidade_sem_equipe");
					}
				}
			}
			
			
			
			
			//Inicializando o formulário
			form.setHoraInicioExecucao("");
			form.setHoraFimExecucao("");
			form.setIdEquipeProgramada("");
			form.setIdEquipeNaoProgramada("");
			form.setDescricaoEquipeNaoProgramada("");
			
		}
		
		
		//Pesquisar Equipe ENTER
		if ((form.getIdEquipeNaoProgramada() != null && !form.getIdEquipeNaoProgramada().equals("")) &&
			(form.getDescricaoEquipeNaoProgramada() == null || form.getDescricaoEquipeNaoProgramada().equals(""))){

			FiltroEquipe filtroEquipe = new FiltroEquipe();
		
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID,
			form.getIdEquipeNaoProgramada()));
			
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.INDICADOR_USO,
			ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoEquipe = fachada.pesquisar(filtroEquipe,
			Equipe.class.getName());
	
			if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
	
				form.setIdEquipeNaoProgramada("");
				form.setDescricaoEquipeNaoProgramada("EQUIPE INEXISTENTE");
	
				httpServletRequest.setAttribute("corEquipe", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idEquipeNaoProgramada");
	
			} else {
				
				 Equipe equipe = (Equipe) Util
				.retonarObjetoDeColecao(colecaoEquipe);
	
				form.setIdEquipeNaoProgramada(equipe.getId().toString());
				form.setDescricaoEquipeNaoProgramada(equipe.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
			
			}
		}
		
		
		//Pesquisar Equipe POPUP
		String pesquisarEquipe = httpServletRequest.getParameter("pesquisarEquipe");
		
		if (pesquisarEquipe != null && !pesquisarEquipe.equalsIgnoreCase("")){
			retorno = actionMapping.findForward("pesquisarEquipe");
		}
		
		//Recebendo dados Equipe POPUP
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
			form.setIdEquipeNaoProgramada(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setDescricaoEquipeNaoProgramada(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
		
			httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
		}
		
		
		//Adicionar
		String adicionarPeriodoEquipe = httpServletRequest.getParameter("adicionarPeriodoEquipe");
		
		if (adicionarPeriodoEquipe != null && !adicionarPeriodoEquipe.equalsIgnoreCase("")){
			
			//Verificando as informações colhidas
			//===================================================================================
			Integer idEquipe = null;
			if (form.getIdEquipeProgramada() != null && !form.getIdEquipeProgramada().equalsIgnoreCase("")){
				idEquipe = Util.converterStringParaInteger(form.getIdEquipeProgramada());
			}
			else{
				idEquipe = Util.converterStringParaInteger(form.getIdEquipeNaoProgramada());
			}
			
			fachada.verificaDadosAdicionarPeriodoEquipe(form.getDataExecucao(), form.getHoraInicioExecucao(),
			form.getHoraFimExecucao(), idEquipe, form.getDataEncerramentoOS(), Util.converterStringParaInteger(form.getNumeroOS()));
			//===================================================================================
			
			
			//Informando OrdemServicoAtividade
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				this.informarOrdemServicoAtividade(colecaoManterDadosAtividadesOrdemServicoHelper, 
				Util.converterStringParaInteger(form.getNumeroOS()), 
			    Util.converterStringParaInteger(form.getIdAtividade()), form.getDataExecucao(), form.getHoraInicioExecucao(), form.getHoraFimExecucao(), 
			    idEquipe, sessao, fachada);
			}
			else{
				
				this.informarOrdemServicoAtividade(null, 
				Util.converterStringParaInteger(form.getNumeroOS()), 
				Util.converterStringParaInteger(form.getIdAtividade()), form.getDataExecucao(), form.getHoraInicioExecucao(), form.getHoraFimExecucao(), 
				idEquipe, sessao, fachada);
				
			}
			
			//Inicializando o formulário
			form.setHoraInicioExecucao("");
			form.setHoraFimExecucao("");
			form.setIdEquipeProgramada("");
			form.setIdEquipeNaoProgramada("");
			form.setDescricaoEquipeNaoProgramada("");
			
			httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
		}
		
		
		//Remover
		String removerPeriodoEquipe = httpServletRequest.getParameter("removerPeriodoEquipe");
		
		if (removerPeriodoEquipe != null && !removerPeriodoEquipe.equalsIgnoreCase("")){
			
			long identificadorEquipe = Long.valueOf(removerPeriodoEquipe);
			Collection colecaoSessao = (Collection) sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
			
			this.removerPeriodoExecucaoEquipe(identificadorEquipe, 
			Util.converterStringParaInteger(form.getIdAtividade()), colecaoSessao);
			
			//Inicializando o formulário
			form.setHoraInicioExecucao("");
			form.setHoraFimExecucao("");
			form.setIdEquipeProgramada("");
			form.setIdEquipeNaoProgramada("");
			form.setDescricaoEquipeNaoProgramada("");
			
			httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
		}
		
		
		//Objetos utilizados apenas para facilitar a quebra na exibição
		httpServletRequest.setAttribute("numeroOS", form.getNumeroOS());
		httpServletRequest.setAttribute("idAtividade", form.getIdAtividade());
		
		return retorno;
	}
	
	
	
	private void informarOrdemServicoAtividade(Collection colecaoSessao, Integer numeroOS, 
			Integer idAtividade, String dataExecucao, String horaInicio, String horaFim, 
			Integer idEquipe ,HttpSession sessao, Fachada fachada){
		
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		
		if (colecaoSessao != null && !colecaoSessao.isEmpty()){
			
			boolean ordemServicoAtividadeInformada = false;
			Iterator iteratorColecaoSessao = colecaoSessao.iterator();
			
			while(iteratorColecaoSessao.hasNext()){
			
				manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
				iteratorColecaoSessao.next();
			
				/*
				 * Verifica se já existe na coleção uma OrdemServicoAtividade com o mesmo número de OS e 
				 * mesma atividade informada
				 */
				if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getOrdemServico().getId()
					.equals(numeroOS) && manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade()
					.getAtividade().getId().equals(idAtividade)){
					
					//Informando OsAtividadePeriodoExecucao
					Collection colecaoOsAtividadePeriodoExecucaoHelper = this.informarOsAtividadePeriodoExecucao(manterDadosAtividadesOrdemServicoHelper
					.getColecaoOSAtividadePeriodoExecucaoHelper(), dataExecucao,horaInicio, horaFim, idEquipe, fachada);
					
					manterDadosAtividadesOrdemServicoHelper.setColecaoOSAtividadePeriodoExecucaoHelper(
					colecaoOsAtividadePeriodoExecucaoHelper);
					
					ordemServicoAtividadeInformada = true;
					break;
				}
			}
		
			/*
			 * Caso já exista OrdemServicoAtividade informada, porém ainda não foi cadastrada nenhuma com o mesmo
			 * número de OS e atividade informados
			 */
			if (!ordemServicoAtividadeInformada){
				
				//1º Passo - Gerando o objeto
				manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividade(numeroOS, 
				idAtividade, dataExecucao, horaInicio, horaFim, idEquipe, fachada);
				
				//2º Passo - Adicionando o objeto na coleção que foi recebida (Já foi criada e colocada na sessão)
				colecaoSessao.add(manterDadosAtividadesOrdemServicoHelper);
			}
		}
		else{
			
			//Primeira OrdemServicoAtividade informada
			
			//1º Passo - Gerando o objeto
			manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividade(numeroOS, 
			idAtividade, dataExecucao, horaInicio, horaFim, idEquipe, fachada);
			
			//2º Passo - Adicionando o objeto em uma coleção vazia
			Collection colecaoManterDadosAtividadesOrdemServicoHelper = new ArrayList();
			colecaoManterDadosAtividadesOrdemServicoHelper.add(manterDadosAtividadesOrdemServicoHelper);
			
			//3º Passo - Colocando a coleção gerada na sessão
			sessao.setAttribute("colecaoManterDadosAtividadesOrdemServicoHelper", 
			colecaoManterDadosAtividadesOrdemServicoHelper);
			
		}
	}
	
	private ManterDadosAtividadesOrdemServicoHelper gerarOrdemServicoAtividade(Integer numeroOS, Integer idAtividade, String 
		dataExecucao, String horaInicio, String horaFim, Integer idEquipe, Fachada fachada){
		
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = 
		new ManterDadosAtividadesOrdemServicoHelper();
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(numeroOS);
		
		Atividade atividade = new Atividade();
		atividade.setId(idAtividade);
		
		OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
		ordemServicoAtividade.setAtividade(atividade);
		ordemServicoAtividade.setOrdemServico(ordemServico);
		
		manterDadosAtividadesOrdemServicoHelper.setOrdemServicoAtividade(ordemServicoAtividade);
		
		//Informando OsAtividadePeriodoExecucao
		Collection colecaoOsAtividadePeriodoExecucaoHelper = this.informarOsAtividadePeriodoExecucao(null, 
		dataExecucao,horaInicio, horaFim, idEquipe, fachada);
		
		manterDadosAtividadesOrdemServicoHelper.setColecaoOSAtividadePeriodoExecucaoHelper(
		colecaoOsAtividadePeriodoExecucaoHelper);
		
		return manterDadosAtividadesOrdemServicoHelper;
		
	}
	
	
	
	private Collection informarOsAtividadePeriodoExecucao(Collection colecaoOsAtividadePeriodoExecucaoHelper, 
			String dataExecucao, String horaInicio, String horaFim, Integer idEquipe, Fachada fachada){
		
		Collection colecaoRetorno = null;
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
		
		if (colecaoOsAtividadePeriodoExecucaoHelper != null &&
			!colecaoOsAtividadePeriodoExecucaoHelper.isEmpty()){
			
			boolean osAtividadePeriodoExecucaoInformada = false;
			Iterator iteratorcolecaoOsAtividadePeriodoExecucaoHelper = colecaoOsAtividadePeriodoExecucaoHelper.iterator();
			
			while(iteratorcolecaoOsAtividadePeriodoExecucaoHelper.hasNext()){
				
				osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper)
				iteratorcolecaoOsAtividadePeriodoExecucaoHelper.next();
				
				/*
				 * Verifica se já existe na coleção uma OSAtividadePeriodoExecucaoHelper com a mesma data de execução 
				 * e mesmo período de execução
				 */
				if (osAtividadePeriodoExecucaoHelper.getDataExecucaoParaQuebra()
					.equals(Util.converteStringParaDate(dataExecucao)) &&
					osAtividadePeriodoExecucaoHelper.getHoraMinutoInicioParaQuebra().equals(horaInicio) &&
					osAtividadePeriodoExecucaoHelper.getHoraMinutoFimParaQuebra().equals(horaFim)){
					
					
					//Informando OsAtividadePeriodoExecucao
					Collection colecaoOsExecucaoEquipeHelper = this.informarOsExecucaoEquipe(
					osAtividadePeriodoExecucaoHelper.getColecaoOSExecucaoEquipeHelper(), idEquipe, fachada);
					
					osAtividadePeriodoExecucaoHelper.setColecaoOSExecucaoEquipeHelper(colecaoOsExecucaoEquipeHelper);
					
					osAtividadePeriodoExecucaoInformada = true;
					break;
				}
			}
			
			/*
			 * Caso já exista OSAtividadePeriodoExecucaoHelper informada, porém ainda não foi cadastrada nenhuma 
			 * com a mesma data de execução e mesmo período de execução
			 */
			if (!osAtividadePeriodoExecucaoInformada){
				
				//1º Passo - Gerando o objeto
				osAtividadePeriodoExecucaoHelper = this.gerarOsAtividadePeriodoExecucao(dataExecucao, 
				horaInicio, horaFim, idEquipe, fachada);
				
				//2º Passo - Adicionando o objeto na coleção que foi recebida (Já foi criada e colocada na sessão)
				colecaoOsAtividadePeriodoExecucaoHelper.add(osAtividadePeriodoExecucaoHelper);
			}
			
			return colecaoOsAtividadePeriodoExecucaoHelper;
		}
		else{
			
			//Primeira OsAtividadePeriodoExecucao informada
			
			//1º Passo - Gerando o objeto
			osAtividadePeriodoExecucaoHelper = this.gerarOsAtividadePeriodoExecucao(dataExecucao, 
			horaInicio, horaFim, idEquipe, fachada);
			
			//2º Passo - Adicionando o objeto em uma coleção vazia
			colecaoRetorno = new ArrayList();
			colecaoRetorno.add(osAtividadePeriodoExecucaoHelper);
			
			return colecaoRetorno;
		}
	} 
	
	
	private OSAtividadePeriodoExecucaoHelper gerarOsAtividadePeriodoExecucao(String
			dataExecucao, String horaInicio, String horaFim, Integer idEquipe, Fachada fachada){
			
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = 
		new OSAtividadePeriodoExecucaoHelper();
		
		OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();
		
		//Gerando uma String no formato dd/MM/yyyy HH:mm:ss
		String dataHoraExecucaoInicio = dataExecucao + " " + horaInicio + ":00";
		String dataHoraExecucaoFim = dataExecucao + " " + horaFim + ":00";
		
		osAtividadePeriodoExecucao.setDataInicio(Util.converteStringParaDateHora(dataHoraExecucaoInicio));
		osAtividadePeriodoExecucao.setDataFim(Util.converteStringParaDateHora(dataHoraExecucaoFim));
		
		//Objetos utilizados apenas para facilitar a quebra na exibição
		osAtividadePeriodoExecucaoHelper.setDataExecucaoParaQuebra(Util.converteStringParaDate(dataExecucao));
		osAtividadePeriodoExecucaoHelper.setHoraMinutoInicioParaQuebra(horaInicio);
		osAtividadePeriodoExecucaoHelper.setHoraMinutoFimParaQuebra(horaFim);
		
		osAtividadePeriodoExecucaoHelper.setOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao);
		
		//Informando OsExecucaoEquipe
		Collection colecaoOSExecucaoEquipeHelper = this.informarOsExecucaoEquipe(null, idEquipe, fachada);
			
		osAtividadePeriodoExecucaoHelper.setColecaoOSExecucaoEquipeHelper(colecaoOSExecucaoEquipeHelper);
		
		return osAtividadePeriodoExecucaoHelper;
			
	}
	
	
	
	private Collection informarOsExecucaoEquipe(Collection colecaoOSExecucaoEquipeHelper, 
			Integer idEquipe, Fachada fachada){
		
		Collection colecaoRetorno = null;
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
		
		if (colecaoOSExecucaoEquipeHelper != null &&
			!colecaoOSExecucaoEquipeHelper.isEmpty()){
			
			Iterator iteratorColecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper.iterator();
			
			while(iteratorColecaoOSExecucaoEquipeHelper.hasNext()){
				
				osExecucaoEquipeHelper = (OSExecucaoEquipeHelper)
				iteratorColecaoOSExecucaoEquipeHelper.next();
			
				/*
				 * Verifica se já existe na coleção uma OSExecucaoEquipeHelper com a mesma equipe
				 * 
				 *[FS002] - Verificar Existência de Dados
				 */
				if (osExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe().getId().equals(idEquipe)){
					
					throw new ActionServletException(
					"atencao.data_periodo_equipe_ja_informado");
				}
			}
			
			/*
			 * Caso já exista OSExecucaoEquipeHelper informada, porém ainda não foi cadastrada nenhuma 
			 * com a mesma equipe
			 */
			
			//1º Passo - Gerando o objeto
			osExecucaoEquipeHelper = this.gerarOsExecucaoEquipe(idEquipe, fachada);
				
			//2º Passo - Adicionando o objeto na coleção que foi recebida (Já foi criada e colocada na sessão)
			colecaoOSExecucaoEquipeHelper.add(osExecucaoEquipeHelper);
			
			return colecaoOSExecucaoEquipeHelper;
		}
		else{
			
			//Primeira OsExecucaoEquipe informada
			
			//1º Passo - Gerando o objeto
			osExecucaoEquipeHelper = this.gerarOsExecucaoEquipe(idEquipe, fachada);
			
			//2º Passo - Adicionando o objeto em uma coleção vazia
			colecaoRetorno = new ArrayList();
			colecaoRetorno.add(osExecucaoEquipeHelper);
			
			return colecaoRetorno;
		}
	}
	
	
	private OSExecucaoEquipeHelper gerarOsExecucaoEquipe(Integer idEquipe, Fachada fachada){
			
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = 
		new OSExecucaoEquipeHelper();
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, 
		idEquipe));
		
		Collection colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		
		Equipe equipe = (Equipe)Util.retonarObjetoDeColecao(colecaoEquipe);
		
		//Alterado para facilitar a identificação de uma equipe na coleção
		equipe.setUltimaAlteracao(new Date());
		
		OsExecucaoEquipe osExecucaoEquipe = new OsExecucaoEquipe();
		osExecucaoEquipe.setEquipe(equipe);
		
		osExecucaoEquipeHelper.setOsExecucaoEquipe(osExecucaoEquipe);
		
		return osExecucaoEquipeHelper;
			
	}
	
	
	
	private void removerPeriodoExecucaoEquipe(long identificacaoEquipe, Integer idAtividade,Collection colecaoSessao){
		
		Iterator iteratorColecaoSessao = colecaoSessao.iterator();
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
		
		boolean removerSuperior = false;
		
		//Atividade
		while (iteratorColecaoSessao.hasNext()){
			
			manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
			iteratorColecaoSessao.next();
			
			if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getAtividade().getId()
				.equals(idAtividade)){
				
				//Período
				Collection colecaoOSAtividadePeriodoExecucaoHelper = 
				manterDadosAtividadesOrdemServicoHelper.getColecaoOSAtividadePeriodoExecucaoHelper();
				
				Iterator iteratorColecaoOSAtividadePeriodoExecucaoHelper = 
				colecaoOSAtividadePeriodoExecucaoHelper.iterator();
				
				while (iteratorColecaoOSAtividadePeriodoExecucaoHelper.hasNext()){
					
					osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper)
					iteratorColecaoOSAtividadePeriodoExecucaoHelper.next();
					
					//Equipe
					Collection colecaoOSExecucaoEquipeHelper = osAtividadePeriodoExecucaoHelper
					.getColecaoOSExecucaoEquipeHelper();
					
					Iterator iteratorColecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper.iterator();
					
					while (iteratorColecaoOSExecucaoEquipeHelper.hasNext()){
						
						osExecucaoEquipeHelper = (OSExecucaoEquipeHelper) iteratorColecaoOSExecucaoEquipeHelper.next();
					
						if ((GcomAction.obterTimestampIdObjeto(osExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe()))
							== identificacaoEquipe){
							
							colecaoOSExecucaoEquipeHelper.remove(osExecucaoEquipeHelper);
							
							if (colecaoOSExecucaoEquipeHelper.size() < 1){
								removerSuperior = true;
							}
							
							break;
						}
					}
					
					if (removerSuperior){
						colecaoOSAtividadePeriodoExecucaoHelper.remove(osAtividadePeriodoExecucaoHelper);
						
						if (colecaoOSAtividadePeriodoExecucaoHelper.size() < 1){
							removerSuperior = true;
						}
						else{
							removerSuperior = false;
						}
						
						break;
					}
				}
				
				if (removerSuperior){
					break;
				}
			}
		}
		
		if (removerSuperior){
			colecaoSessao.remove(manterDadosAtividadesOrdemServicoHelper);
		}
	}
}

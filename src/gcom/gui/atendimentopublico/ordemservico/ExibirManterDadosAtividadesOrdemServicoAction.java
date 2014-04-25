package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização do dados das atividades de uma OS
 * 
 * @author Raphael Rossiter
 * @date 15/09/2006
 */
public class ExibirManterDadosAtividadesOrdemServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterDadosAtividadesOrdemServico");

		ManterDadosAtividadesOrdemServicoActionForm form = (ManterDadosAtividadesOrdemServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		/*
		 * O caso de uso recebe o número sa OS, data de roteiro e a data de encerramento (deverá vir informado
		 * um ou outro e ambos)
		 */
		String numeroOSParametro = httpServletRequest.getParameter("numeroOS");
		
		if (numeroOSParametro != null && !numeroOSParametro.equals("")){
			
			String dataRoteiroParametro = httpServletRequest.getParameter("dataRoteiro");
			String dataEncerramentoParametro = httpServletRequest.getParameter("dataEncerramento");
			
			String caminhoRetorno = httpServletRequest.getParameter("caminhoRetorno");
			
			if (caminhoRetorno != null && !caminhoRetorno.equalsIgnoreCase("")){
				form.setCaminhoRetorno(caminhoRetorno);
			}
			
			//Conversação dos objetos
			Integer numeroOS = Util.converterStringParaInteger(numeroOSParametro);
			form.setNumeroOSForm(numeroOSParametro);
			
			//Date dataRoteiro = null;
			if (dataRoteiroParametro != null && !dataRoteiroParametro.equals("")){
				//dataRoteiro = Util.converteStringParaDate(dataRoteiroParametro);
				form.setDataRoteiroForm(dataRoteiroParametro);
			}
			
			//Date dataEncerramento = null;
			if (dataEncerramentoParametro != null && !dataEncerramentoParametro.equals("")){
				//dataEncerramento = Util.converteStringParaDate(dataEncerramentoParametro);
				form.setDataEncerramentoForm(dataEncerramentoParametro);
			}
			

			/*
			 * O sistema apresenta uma lista de atividades associadas ao serviço tipo que pertencem à ordem de serviço
			 */
			Collection<Atividade> colecaoAtividadeOS = fachada.obterAtividadesOrdemServico(numeroOS);
			sessao.setAttribute("colecaoAtividade", colecaoAtividadeOS);
			
		}
		
		
		//Pesquisar Atividade
		if ((form.getIdAtividade() != null && !form.getIdAtividade().equals("")) &&
			(form.getDescricaoAtividade() == null || form.getDescricaoAtividade().equals(""))){
			
			this.pesquisarAtividade(form, fachada, httpServletRequest);
		}
		
		
		//Remover Atividade
		String removerAtividade = httpServletRequest.getParameter("removerAtividade");
		
		if (removerAtividade != null && !removerAtividade.equals("")){
			
			Atividade remover = new Atividade();
			remover.setId(new Integer(removerAtividade));
			
			Collection<Atividade> colecaoAtividade = (Collection) sessao.getAttribute("colecaoAtividade");
			colecaoAtividade.remove(remover);
			
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				Iterator iteratorColecaoManterDadosAtividadesOrdemServicoHelper = 
				colecaoManterDadosAtividadesOrdemServicoHelper.iterator();
				
				ManterDadosAtividadesOrdemServicoHelper helper = null;
				
				while (iteratorColecaoManterDadosAtividadesOrdemServicoHelper.hasNext()){
					
					helper = (ManterDadosAtividadesOrdemServicoHelper)
					iteratorColecaoManterDadosAtividadesOrdemServicoHelper.next();
					
					if (helper.getOrdemServicoAtividade().getAtividade().getId()
						.equals(remover.getId())){
						
						colecaoManterDadosAtividadesOrdemServicoHelper.remove(helper);
						break;
					}
				}
			}
		}
		
		
		//Adicionar Atividade
		String adicionarAtividade = httpServletRequest.getParameter("adicionarAtividade");
		
		if (adicionarAtividade != null && !adicionarAtividade.equals("")){
			
			Atividade adicionar = this.pesquisarAtividade(form, fachada, httpServletRequest);
			
			Collection<Atividade> colecaoAtividade = (Collection) sessao.getAttribute("colecaoAtividade");
		
			//[FS0003 - Verificar existência da atividade]
			if (colecaoAtividade.contains(adicionar)){
				throw new ActionServletException("atencao.atividade_ja_existente");
			}
			
			colecaoAtividade.add(adicionar);
			
			form.setIdAtividade("");
			form.setDescricaoAtividade("");
			
			httpServletRequest.setAttribute("nomeCampo", "idAtividade");
		}
		
		
		//Pesquisar Atividade POPUP
		String pesquisarAtividade = httpServletRequest.getParameter("pesquisarAtividade");
		
		if (pesquisarAtividade != null && !pesquisarAtividade.equalsIgnoreCase("")){
			retorno = actionMapping.findForward("pesquisarAtividade");
		}
		
		
		//Recebendo dados Atividade POPUP
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
			form.setIdAtividade(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setDescricaoAtividade(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
		
			httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
		}
		
		
		//Concluir Manutenção
		String concluirManutencao = httpServletRequest.getParameter("inserir");
		
		if (concluirManutencao != null && !concluirManutencao.equalsIgnoreCase("")){
			
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				if (colecaoManterDadosAtividadesOrdemServicoHelper != null &&
					!colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()){
					
					if (form.getCaminhoRetorno() != null && !form.getCaminhoRetorno().equalsIgnoreCase("")){
						
						fachada.manterDadosAtividadesOrdemServico(colecaoManterDadosAtividadesOrdemServicoHelper);
						
						sessao.removeAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
					
						httpServletRequest.setAttribute("caminhoRetorno", form.getCaminhoRetorno());
					}
					else{
						sessao.setAttribute("colecaoManutencao", colecaoManterDadosAtividadesOrdemServicoHelper);
					}
				}
				
				httpServletRequest.setAttribute("fecharPopup", "OK");
			}
		}
		
		if(httpServletRequest.getParameter("caminhoRetornoDadosAtividadesOS")!= null && 
				!httpServletRequest.getParameter("caminhoRetornoDadosAtividadesOS").equals("")){
			sessao.setAttribute("caminhoRetornoDadosAtividadesOS", httpServletRequest.getParameter("caminhoRetornoDadosAtividadesOS"));
		}

		return retorno;
	}
	
	
	
	private Atividade pesquisarAtividade(ManterDadosAtividadesOrdemServicoActionForm form, 
			Fachada fachada, HttpServletRequest httpServletRequest){
		
		Atividade retorno = null;
		
		FiltroAtividade filtroAtividade = new FiltroAtividade();
		
		filtroAtividade.adicionarParametro(new ParametroSimples(FiltroAtividade.ID,
		form.getIdAtividade()));
		
		filtroAtividade.adicionarParametro(new ParametroSimples(FiltroAtividade.INDICADORUSO,
		ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoAtividade = fachada.pesquisar(filtroAtividade,
		Atividade.class.getName());

		if (colecaoAtividade == null || colecaoAtividade.isEmpty()) {

			form.setIdAtividade("");
			form.setDescricaoAtividade("Atividade inexistente");

			httpServletRequest.setAttribute("corAtividade", "exception");
			httpServletRequest.setAttribute("nomeCampo", "idAtividade");

		} else {
			
			 Atividade atividade = (Atividade) Util
			.retonarObjetoDeColecao(colecaoAtividade);

			form.setIdAtividade(atividade.getId().toString());
			form.setDescricaoAtividade(atividade.getDescricao());

			httpServletRequest.setAttribute("nomeCampo", "idAtividade");
		
			retorno = atividade;
		}
		
		
		return retorno;
	}

}

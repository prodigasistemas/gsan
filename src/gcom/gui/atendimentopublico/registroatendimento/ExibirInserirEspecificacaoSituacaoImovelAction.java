package gcom.gui.atendimentopublico.registroatendimento;


import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Action responsável pela pré-exibição da página de Inserir Especificaçao Situação
 * 
 * @author Rafael Pinto
 * @created 03/08/2006
 */
public class ExibirInserirEspecificacaoSituacaoImovelAction extends GcomAction {
	
	/**
	 * Exibição de inserção de equipe.
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Exibição da Tela de Inserção)
		ActionForward retorno = actionMapping.findForward("inserirEspecificacaoSituacaoImovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		// Form
		InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm = 
			(InserirEspecificacaoSituacaoImovelActionForm) actionForm;
		
		// Testa se é pra remover especificação situacao criterio imovel
		if (inserirEspecificacaoSituacaoImovelActionForm.getDeleteSituacaoCriterioImovel() != null && 
			!inserirEspecificacaoSituacaoImovelActionForm.getDeleteSituacaoCriterioImovel().equals("")) {
			
			removeSituacaoCriterioImovel(inserirEspecificacaoSituacaoImovelActionForm);
			
		} else if(!inserirEspecificacaoSituacaoImovelActionForm.getMethod().equals("")){

			retorno = actionMapping.findForward("inserirEspecificacaoSituacaoCriterioImovel");			
			
			this.consultaSelectObrigatorio(sessao);
			
			if ((inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoAgua() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) 
					|| (inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoEsgoto() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))
					|| (inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroLigacaoAgua() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))					
					|| (inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroPoco() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroPoco().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) ){


				// Cria objeto EspecificacaoImovSitCriterio
				EspecificacaoImovSitCriterio especificacaoImovSitCriterio = null;

				// Recupera especificao imovel sit criterio vindo do popup
				especificacaoImovSitCriterio = 
					getEspecificacaoImovSitCriterio(inserirEspecificacaoSituacaoImovelActionForm,sessao);
				
				// Reseta informações vindas do popup
				resetPopup(inserirEspecificacaoSituacaoImovelActionForm);
				
				// Faz as validações de inserção de especificao situacao imovel
				fachada.validarExibirInsercaoEspecificacaoImovSitCriterio(
					inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio(), 
						especificacaoImovSitCriterio);
				
				// Seta componente na coleção
				setColecaoEspecificacaoImovSitCriterio(inserirEspecificacaoSituacaoImovelActionForm, 
					especificacaoImovSitCriterio);
				
				// Seta retorno
				retorno = actionMapping.findForward("inserirEspecificacaoSituacaoImovel");
			}
			
			
			inserirEspecificacaoSituacaoImovelActionForm.setMethod("");
		}

		return retorno;
	}

	/**
	 * Seta nova EspecificacaoImovSitCriterio na Coleção 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param especificacaoImovSitCriterio
	 */
	private void setColecaoEspecificacaoImovSitCriterio(InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm, 
			EspecificacaoImovSitCriterio especificacaoImovSitCriterio) {
		
		
		inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().add(especificacaoImovSitCriterio);
		inserirEspecificacaoSituacaoImovelActionForm.setTamanhoColecao(
			inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().size()+"");
	}

	/**
	 * Recupera objeto Especificacao Imovel Situacao Criterio com informações vindas da tela 
	 *
	 * @author Rafael Pinto
	 * @date 04/08/2006
	 *
	 * @param InserirEspecificacaoSituacaoImovelActionForm
	 */
	private EspecificacaoImovSitCriterio getEspecificacaoImovSitCriterio(
		InserirEspecificacaoSituacaoImovelActionForm inserirActionForm,HttpSession sessao) {
		
		EspecificacaoImovSitCriterio especificacaoImovSitCriterio = new EspecificacaoImovSitCriterio();
		
		if(inserirActionForm.getIndicadorHidrometroLigacaoAgua() != null &&
				!inserirActionForm.getIndicadorHidrometroLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			especificacaoImovSitCriterio.setIndicadorHidrometroLigacaoAgua(
				new Short(inserirActionForm.getIndicadorHidrometroLigacaoAgua()));	
		}
		
		if(inserirActionForm.getIndicadorHidrometroPoco() != null &&
				!inserirActionForm.getIndicadorHidrometroPoco().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
		
			especificacaoImovSitCriterio.setIndicadorHidrometroPoco(
				new Short(inserirActionForm.getIndicadorHidrometroPoco()));
		}		
		
		especificacaoImovSitCriterio.setUltimaAlteracao(new Date());

		if(inserirActionForm.getSituacaoLigacaoAgua() != null &&
				!inserirActionForm.getSituacaoLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			Integer idSituacaoLigacaoAgua = new Integer(inserirActionForm.getSituacaoLigacaoAgua());
			
			LigacaoAguaSituacao ligacaoAguaSituacao = this.retornaLigacaoAguaSituacao(sessao,idSituacaoLigacaoAgua);
			
			especificacaoImovSitCriterio.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		}		
		
		if(inserirActionForm.getSituacaoLigacaoEsgoto() != null &&
				!inserirActionForm.getSituacaoLigacaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

			Integer idSituacaoLigacaoEsgoto = new Integer(inserirActionForm.getSituacaoLigacaoEsgoto());
			
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = 
				this.retornaLigacaoEsgotoSituacao(sessao,idSituacaoLigacaoEsgoto);
			
			especificacaoImovSitCriterio.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			
		}		
		
		return especificacaoImovSitCriterio;
	}

	//retorna LigacaoAguaSituacao pelo id na colecao de ligacaoAguaSituacao	
	private LigacaoAguaSituacao retornaLigacaoAguaSituacao(HttpSession sessao,Integer id){

		LigacaoAguaSituacao retorno = null;

		Collection colecao = (Collection) sessao.getAttribute("colecaoSituacaoLigacaoAgua");
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) itera.next();
				
				if(ligacaoAguaSituacao.getId().intValue() == id.intValue()){
					retorno = ligacaoAguaSituacao;
					break;
				}
			}
		}

		return retorno;
	}

	//retorna LigacaoEsgotoSituacao pelo id na colecao de ligacaoEsgotoSituacao	
	private LigacaoEsgotoSituacao retornaLigacaoEsgotoSituacao(HttpSession sessao,Integer id){

		LigacaoEsgotoSituacao retorno = null;

		Collection colecao = (Collection) sessao.getAttribute("colecaoSituacaoLigacaoEsgoto");
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) itera.next();
				
				if(ligacaoEsgotoSituacao.getId().intValue() == id.intValue()){
					retorno = ligacaoEsgotoSituacao;
					break;
				}
			}
		}

		return retorno;
	}

	/**
	 * Reseta informações vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param inserirEspecificacaoSituacaoImovelActionForm
	 */
	private void resetPopup(InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm) {

		inserirEspecificacaoSituacaoImovelActionForm.setIndicadorHidrometroLigacaoAgua(null);
		inserirEspecificacaoSituacaoImovelActionForm.setIndicadorHidrometroPoco(null);
		inserirEspecificacaoSituacaoImovelActionForm.setSituacaoLigacaoAgua(null);
		inserirEspecificacaoSituacaoImovelActionForm.setSituacaoLigacaoEsgoto(null);
		
	}

	/**
	 * Remove Situacao Criterio Imovel da Coleção 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param InserirEspecificacaoSituacaoImovelActionForm
	 */
	private void removeSituacaoCriterioImovel(InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm) {
		
		Collection newSituacaoCriterioImovelCollection = new ArrayList();
		
		int index = 0;
		
		for (Iterator iter = inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().iterator(); 
			iter.hasNext();) {
			
			index++;
			
			EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter.next();
			
			if (index != new Integer(inserirEspecificacaoSituacaoImovelActionForm.getDeleteSituacaoCriterioImovel()).intValue()) {
				newSituacaoCriterioImovelCollection.add(element);
			}
		}
		inserirEspecificacaoSituacaoImovelActionForm.setEspecificacaoImovelSituacaoCriterio(newSituacaoCriterioImovelCollection);
		inserirEspecificacaoSituacaoImovelActionForm.setTamanhoColecao(inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().size()+"");
		inserirEspecificacaoSituacaoImovelActionForm.setDeleteSituacaoCriterioImovel("");
		
		resetPopup(inserirEspecificacaoSituacaoImovelActionForm);
	}
	
	private void consultaSelectObrigatorio(HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		// Filtro para o campo Situação Ligação Água
		Collection colecaoSituacaoLigacaoAgua = (Collection) 
			sessao.getAttribute("colecaoSituacaoLigacaoAgua");

		if(colecaoSituacaoLigacaoAgua == null){

			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoSituacaoLigacaoAgua = 
				fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoSituacaoLigacaoAgua != null && !colecaoSituacaoLigacaoAgua.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoLigacaoAgua",colecaoSituacaoLigacaoAgua);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Situação Ligação de Agua");
			}
		}	

		// Filtro para o campo Situacao da Ligação Esgoto
		Collection colecaoSituacaoLigacaoEsgoto = (Collection) 
			sessao.getAttribute("colecaoSituacaoLigacaoEsgoto");

		if(colecaoSituacaoLigacaoEsgoto == null){

			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoSituacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroMaterialLigacao.DESCRICAO);

			colecaoSituacaoLigacaoEsgoto = 
				fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoSituacaoLigacaoEsgoto != null && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",colecaoSituacaoLigacaoEsgoto);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Situação Ligação de Esgoto");
			}
		}
	}
	
}

package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC0459] Adicionar Ra Dados Agencia Reguladora Fone Popup 
 * 
 * @author Kassia Albuquerque
 * @created 13/04/2006
 */


public class AdicionarRaDadosAgenciaReguladoraFonePopupAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("adicionarRaDadosAgenciaReguladoraFonePopup");
	
			AdicionarRaDadosAgenciaReguladoraFonePopupActionForm form = 
								(AdicionarRaDadosAgenciaReguladoraFonePopupActionForm) actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
	
			Fachada fachada = Fachada.getInstancia();
			
			RaDadosAgenciaReguladoraFone raDadosAgenciaReguladoraFone = new RaDadosAgenciaReguladoraFone();
			
			form.setFormValues(raDadosAgenciaReguladoraFone);
			
			Collection collectionRaDadosAgenciaReguladoraFone = null;
			
			
			//	 CARREGANDO NO OBJETO A DESCRICAO DO TIPO DE TELEFONE 
			if(sessao.getAttribute("colecaoFoneTipo")!=null){
				
				Collection colecaoFoneTipo = (Collection)sessao.getAttribute("colecaoFoneTipo");
				
				Iterator iteratorColecaoFoneTipo = colecaoFoneTipo.iterator();
				
				while(iteratorColecaoFoneTipo.hasNext()){

					FoneTipo foneTipo = (FoneTipo) iteratorColecaoFoneTipo.next();
					
					if(foneTipo.getId().intValue() == raDadosAgenciaReguladoraFone.getFoneTipo().getId().intValue()){
					
						raDadosAgenciaReguladoraFone.getFoneTipo().setDescricao(foneTipo.getDescricao());
					}
				}
			}
			
			
			//	VERIFICANDO EXISTENCIA DE DDD NA TABELA MUNICIPIO
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.DDD,raDadosAgenciaReguladoraFone.getDdd() ));
			
			Collection colecaoFone = fachada.pesquisar(filtroMunicipio,	Municipio.class.getName());
			
			if (colecaoFone == null || colecaoFone.isEmpty()) {
				
				throw new ActionServletException("atencao.ddd_inexistente");
			}
			
			
			//	VERIFICANDO A REPETICAO DO TELEFONE NA COLECAO
			if(sessao.getAttribute("collectionRaDadosAgenciaReguladoraFone")!=null){
				
				Collection colecaoNumeroFone = (Collection)sessao.getAttribute("collectionRaDadosAgenciaReguladoraFone");
				
				Iterator iteratorColecaoNumeroFone = colecaoNumeroFone.iterator();
				
				while(iteratorColecaoNumeroFone.hasNext()){

					RaDadosAgenciaReguladoraFone foneNaColecao = (RaDadosAgenciaReguladoraFone) iteratorColecaoNumeroFone.next();
					
 					if(foneNaColecao.getFone().equalsIgnoreCase(raDadosAgenciaReguladoraFone.getFone())){
					
						throw new ActionServletException("atencao.fone_existente");
						
					}
				}
			}
			
			
			// SE A COLECAO NA SESSAO FOR NULA
			if (sessao.getAttribute("collectionRaDadosAgenciaReguladoraFone")== null){
				
				collectionRaDadosAgenciaReguladoraFone = new ArrayList();
			}else{
			
				collectionRaDadosAgenciaReguladoraFone = (Collection) sessao.getAttribute("collectionRaDadosAgenciaReguladoraFone");
			}
			
			
			raDadosAgenciaReguladoraFone.setUltimaAlteracao(new Date());
			
			collectionRaDadosAgenciaReguladoraFone.add(raDadosAgenciaReguladoraFone);	
				
			sessao.setAttribute("collectionRaDadosAgenciaReguladoraFone",collectionRaDadosAgenciaReguladoraFone);
			
			httpServletRequest.setAttribute("reload", true);
			httpServletRequest.setAttribute("limparForm", true);
			
			
			return retorno;
	}

}

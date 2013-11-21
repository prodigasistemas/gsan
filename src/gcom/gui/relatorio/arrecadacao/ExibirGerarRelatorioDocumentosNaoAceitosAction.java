package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Raimundo Martins
 * @date 17/08/2011
 */
public class ExibirGerarRelatorioDocumentosNaoAceitosAction extends GcomAction {

	/**
	 * Action que exibe os filtros do relatório de documentos não aceitos
	 * 
	 * [UC 1215] Gerar Relatório de Documentos não Aceitos
	 * 
	 * @author Raimundo Martins
	 * @date 18/08/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	private Fachada fachada = Fachada.getInstancia();
	private HttpSession sessao = null;
	private GerarRelatorioDocumentosNaoAceitosActionForm form = null;	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		sessao = httpServletRequest.getSession(false);
		form = (GerarRelatorioDocumentosNaoAceitosActionForm) actionForm;
		
		String pesquisarArrecadador = httpServletRequest.getParameter("pesquisarArrecadador");
		String pesquisarAvisoBancario = httpServletRequest.getParameter("pesquisarAvisoBancario");

				
		//verifica se existe o arrecadador
		if (pesquisarArrecadador != null && pesquisarArrecadador.equals("true")) {
			boolean existeArrecadador = existeArrecadador();  
			if(!existeArrecadador){
				httpServletRequest.setAttribute("idArrecadadorNaoEncontrado", true);				
			}			
			retorno = actionMapping.findForward("exibirGerarRelatorioDocumentosNaoAceitos");
			
		}		
		//Verifica se existe o aviso bancario
		 if(pesquisarAvisoBancario !=null && pesquisarAvisoBancario.equals("true")){
			boolean existeAvisoBancario = existeAvisoBancario();
			if(!existeAvisoBancario){
				httpServletRequest.setAttribute("avisoBancarioNaoEncontrado", true);				
			}			
			retorno = actionMapping.findForward("exibirGerarRelatorioDocumentosNaoAceitos");
		}
		
		// Caso o usuário não queira realizar nenhuma das opções acima, chama a
		// pagina que filtra os dados
		else {
			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma(FiltroArrecadacaoForma.DESCRICAO);
			Collection colecaoArrecadacaoForma = fachada.pesquisar(	filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
			sessao.setAttribute("colecaoArrecadacaoForma",colecaoArrecadacaoForma);

			retorno = actionMapping.findForward("exibirGerarRelatorioDocumentosNaoAceitos");
		}

		return retorno;
	}

	//[FS0001] Verificar a existencia do Arrecadador
	private Boolean existeArrecadador() {
		String idArrecadador = form.getArrecadadorCodigo();

		if (idArrecadador != null && !idArrecadador.trim().equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection colecaoArrecadadores = fachada.pesquisar(filtroArrecadador, Arrecadador.class.getName());

			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadadores);

				form.setArrecadadorCodigo(arrecadador.getId().toString());
				form.setArrecadadorDescricao(arrecadador.getCliente().getNome());
				return true;

			} else {				
				form.setArrecadadorCodigo(idArrecadador);
				form.setArrecadadorDescricao("Arrecadador Inexistente");
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	private Boolean existeAvisoBancario() {
		
		String idAvisoBancario = form.getIdAvisoBancario();        
		        
		if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
			FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
			filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
			
			Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
			
			if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
			    AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
			    
			    form.setCodigoAgenteArrecadador( avisoBancario.getArrecadador().getCodigoAgente().toString());
			    form.setDataLancamentoAviso( Util.formatarData(avisoBancario.getDataLancamento()) );
			    form.setNumeroSequencialAviso( avisoBancario.getNumeroSequencial().toString() );
			    
			    return true;			    
			} 
			else {
				form.setCodigoAgenteArrecadador( "");
				form.setDataLancamentoAviso( "AVISO INEXISTENTE" );
				form.setNumeroSequencialAviso( "" );
				form.setIdAvisoBancario( "" );
				
				return false;				
		    }
		}
		return false;
	}

}

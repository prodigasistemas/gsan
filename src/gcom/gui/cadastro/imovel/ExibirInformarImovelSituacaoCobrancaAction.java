package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInformarImovelSituacaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarImovelSituacaoCobranca");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		// HttpSession sessao = httpServletRequest.getSession(false);
		
		InformarImovelSituacaoCobrancaActionForm informarImovelSituacaoCobrancaActionForm = (InformarImovelSituacaoCobrancaActionForm) actionForm;
		
		
		if (httpServletRequest.getParameter("objetoConsulta") != null ){
			Integer idImovel = new Integer(informarImovelSituacaoCobrancaActionForm.getIdImovel()); 
			if (idImovel != null){
				String matriculaImovel = fachada.pesquisarInscricaoImovel(idImovel);
				Imovel imovel = fachada.pesquisarImovelComSituacaoAguaEsgoto(idImovel);
				httpServletRequest.setAttribute("imovel", imovel);
				if (matriculaImovel != null && !matriculaImovel.equalsIgnoreCase("")){
					String enderecoImovel = fachada.pesquisarEndereco(idImovel);
					httpServletRequest.setAttribute("endereco", enderecoImovel);
					httpServletRequest.setAttribute("matriculaImovel", matriculaImovel);
					
					FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();
					filtroImovelCobrancaSituacao.adicionarParametro(new ParametroSimples(
							FiltroImovelCobrancaSituacao.IMOVEL_ID, idImovel.toString()));
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cliente");
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
					filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
					Collection colecaoImovelCobrancaSituacao = fachada.pesquisar(
							filtroImovelCobrancaSituacao, ImovelCobrancaSituacao.class.getName());
					
					boolean escondeRetirar = true;
					boolean existeNaoRealizado = false;
					
					if (colecaoImovelCobrancaSituacao != null && !colecaoImovelCobrancaSituacao.isEmpty()){
						httpServletRequest.setAttribute("situacoes", colecaoImovelCobrancaSituacao);
						
						for (Iterator iter = colecaoImovelCobrancaSituacao.iterator(); iter.hasNext();) {
							ImovelCobrancaSituacao imovelCobrancaSituacao = (ImovelCobrancaSituacao) iter.next();
							if (imovelCobrancaSituacao.getDataRetiradaCobranca() == null) {
								existeNaoRealizado = true;
//								escondeInserir = true;
								Short indicadorRetirada = imovelCobrancaSituacao.getCobrancaSituacao().getIndicadorBloqueioRetirada();
								if (indicadorRetirada == null || indicadorRetirada.equals(new Short("2"))) {
									escondeRetirar = false;
								}
							}
						}
						
						if (existeNaoRealizado == false) { 
							escondeRetirar = true; 
						} 
					} else {
						escondeRetirar = true;
					}
					
//					if (escondeInserir) httpServletRequest.setAttribute("escondeInserir", "sim");
					if (escondeRetirar) httpServletRequest.setAttribute("escondeRetirar", "sim");
					
				} else {
					httpServletRequest.setAttribute("inexistente", 1);
					httpServletRequest.setAttribute("escondeInserir", "sim");
					httpServletRequest.setAttribute("escondeRetirar", "sim");
				}
			}
		}else{
			//o sistema desabilita a primeira vez q entra na tela
			httpServletRequest.setAttribute("escondeInserir", "sim");
			httpServletRequest.setAttribute("escondeRetirar", "sim");
		}

		
		return retorno;

	}

}

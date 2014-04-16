package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelEmissaoOrdensSeletivasHidrometro extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasHidrometro");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		 
		Collection<HidrometroCapacidade> collectionHidrometroCapacidade = null;
		Collection<HidrometroMarca> collectionHidrometroMarca = null;
		Collection<LeituraAnormalidade> collectionHidrometroAnormalidade = null;
		Collection<HidrometroLocalInstalacao> collectionHidrometroLocalInstalacao = null;
		
		if(imovelEmissaoOrdensSeletivas.getIdImovel() != null
				&& !imovelEmissaoOrdensSeletivas.getIdImovel().equals("")){
			
			httpServletRequest.setAttribute("desabilitarCampos", "ok");
		}else{
			if (httpServletRequest.getAttribute("desabilitarCampos") != null){
				httpServletRequest.removeAttribute("desabilitarCampos");
			}
		}
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem().
				equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
			
			FiltrarImovelEmissaoOrdensSeletivasWizardAction filtrar = new FiltrarImovelEmissaoOrdensSeletivasWizardAction();
			
			if (sessao.getAttribute("abaAtual") == "PARAMETROS") {
				retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasCaracteristicas");
				filtrar.exibirFiltrarImovelEmissaoOrdensSeletivasCaracteristicas(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}else if (sessao.getAttribute("abaAtual") == "CARACTERISTICAS") {
				retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasParametros");
				filtrar.exibirFiltrarImovelEmissaoOrdensSeletivasParametros(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}
			
		}else {
		
			// Lista Capacidade
			if (imovelEmissaoOrdensSeletivas.getCapacidade() == null) {
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.INDICADOR_USO, 1));
				filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);
				collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				if(collectionHidrometroCapacidade == null || collectionHidrometroCapacidade.isEmpty()) {
					throw new ActionServletException("atencao.naocadastrado", null, "Capacidade Hidrômetro");			
				}
				
				sessao.setAttribute("collectionHidrometroCapacidade", collectionHidrometroCapacidade);
			}
			
			// Lista Marca
			if (imovelEmissaoOrdensSeletivas.getMarca() == null) {
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMarca.INDICADOR_USO, 1));
				filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);
				
				collectionHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca,
						HidrometroMarca.class.getName());
				
				if(collectionHidrometroMarca == null || collectionHidrometroMarca.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Marca do Hidrômetro");			
				}
				
				sessao.setAttribute("collectionHidrometroMarca", collectionHidrometroMarca);
			}
			
			//Lista Local de Instalacao
			if(imovelEmissaoOrdensSeletivas.getLocalInstalacao() == null){
				
				FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = 
					new FiltroHidrometroLocalInstalacao();
				// Retirado por Romulo Aurelio, Analista: Ana Cristina
				// CRC 
				// Data: 30/08/2010  
				/*filtroHidrometroLocalInstalacao.adicionarParametro( new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO, 1));*/
				filtroHidrometroLocalInstalacao.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
				
				collectionHidrometroLocalInstalacao = fachada.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());
				
				if(collectionHidrometroLocalInstalacao == null || collectionHidrometroLocalInstalacao.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Local Instalação Hidrômetro");
				}
				
				sessao.setAttribute("collectionHidrometroLocalInstalacao", collectionHidrometroLocalInstalacao);
			}
			
			// Lista Hidrometro Anormalidade
			if (imovelEmissaoOrdensSeletivas.getAnormalidadeHidrometro() == null) {
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				if(imovelEmissaoOrdensSeletivas.getTipoOrdem() != null && 
				(imovelEmissaoOrdensSeletivas.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO) ||						
				imovelEmissaoOrdensSeletivas.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO))){
					//Caso o tipo de ordem selecionado corresponda a ‘SUBSTITUIÇÃO HIDROMETRO’ 
					//ou ‘REMOÇÃO HIDROMETRO’, selecionar apenas  anormalidades com LTAN_ICRELATIVOHIDROMETRO=1 
					filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
							FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO, 1));
				}
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.INDICADOR_USO, 1));
				
				filtroLeituraAnormalidade.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
				
				collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				if(collectionHidrometroAnormalidade == null || collectionHidrometroAnormalidade.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Anormalidade de Hidrômetro");			
				}
				
				sessao.setAttribute("collectionHidrometroAnormalidade", collectionHidrometroAnormalidade);
			}
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		//sessao.setAttribute("abaAtual", "HIDROMETRO");
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem() != null) {
			httpServletRequest.setAttribute("tipoOrdem", imovelEmissaoOrdensSeletivas.getTipoOrdem());
		}
		
		return retorno;
	}
}

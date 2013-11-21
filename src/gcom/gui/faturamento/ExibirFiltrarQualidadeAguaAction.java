package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
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


/**
 * 
 * @author Flávio
 * @date 26/09/2007
 */



public class ExibirFiltrarQualidadeAguaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarQualidadeAgua");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarQualidadeAguaActionForm form = (FiltrarQualidadeAguaActionForm) actionForm;
		
		//	Código para checar ou não o ATUALIZAR
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}		

		String idLocalidade = form.getIdLocalidade();
		String codigoSetor =  form.getCodigoSetor();
		
		// Verificar se o número da Localidade não está cadastrado
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {

			// Filtro para descobrir id da Localidade
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
						FiltroLocalidade.ID, 
						idLocalidade));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				form.setIdLocalidade("");
				form.setNomeLocalidade( "LOCALIDADE INEXISTENTE" );
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrado","exception");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			}else{
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo","codigoSetor");
			}
		}

		// Verifica se o Setor Comercial não está cadastrado 
		if (codigoSetor != null && !codigoSetor.trim().equals("")) {

			// Filtro para descobrir o Setor Comercial relacionado à localidade.
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
						codigoSetor));
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE, 
							idLocalidade));

			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
			if (colecaoSetor == null || colecaoSetor.isEmpty()) {
				form.setCodigoSetor(""); 
				form.setNomeSetor( "SETOR COMERCIAL INEXISTENTE" );
				httpServletRequest.setAttribute("codigoSetorNaoEncontrado","exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetor");
			}else{
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
				form.setCodigoSetor(setorComercial.getCodigo() + "");
				form.setNomeSetor(setorComercial.getDescricao());
				httpServletRequest.setAttribute("nomeCampo","fonteCaptacao");
			}
		}
		
		//Sistema Abastecimento
		
		FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento,
				SistemaAbastecimento.class.getName());

		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Sistema Abastecimento");
		}
		
		//Manda valores do Sistema de Abastecimento para Sessão
		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		//pesquisa os dados do enter
		pesquisarEnter(form, httpServletRequest,fachada);
		
		return retorno;
	}
	
	private void pesquisarEnter(
			FiltrarQualidadeAguaActionForm form,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// pesquisa enter de FONTE DE CAPTACAO
		if (form.getFonteCaptacao() != null
				&& !form.getFonteCaptacao().equals("")) {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			try {
				filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
						FiltroFonteCaptacao.ID, new Integer(
								form.getFonteCaptacao())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Fonte de Captação");
			}
			
			filtroFonteCaptacao
					.setCampoOrderBy(FiltroFonteCaptacao.DESCRICAO);
			Collection colecaoFonteCaptacao = fachada.pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());

			if (colecaoFonteCaptacao != null
					&& !colecaoFonteCaptacao.isEmpty()) {
				FonteCaptacao fonteCaptacao = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setDescricaoFonteCaptacao(fonteCaptacao.getDescricao());
				
				httpServletRequest.setAttribute("fonteCaptacaoEncontrada", true);
			} else {
				form.setFonteCaptacao("");
				form.setDescricaoFonteCaptacao("FONTE DE CAPTACÃO INEXISTENTE");
			}

		}
	}
	
}

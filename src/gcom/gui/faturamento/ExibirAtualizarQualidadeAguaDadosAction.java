package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroQualidadeAgua;
import gcom.faturamento.FiltroQualidadeAguaPadrao;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FonteCaptacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 16/09/2008
 */

public class ExibirAtualizarQualidadeAguaDadosAction extends GcomAction {

	/**
	 * Description of the Method
	 */
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarQualidadeAguaDadosAction");

		AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		if ( httpServletRequest.getParameter("manter") != null && 
				httpServletRequest.getParameter("manter").equals("sim") ) {
			
			sessao.setAttribute("PrimeiraVez",1);
		
			String idQualidadeAgua = null;
	
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idQualidadeAgua = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				
				sessao.setAttribute("idRegistroAtualizacao", idQualidadeAgua);
			}
	
			if (idQualidadeAgua == null) {
				if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
					idQualidadeAgua = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				} else {
					idQualidadeAgua = (String) httpServletRequest.getAttribute(
							"idRegistroAtualizacao").toString();
				}
	
			} else {
				sessao.setAttribute("i", true);
			}
	
			QualidadeAgua qualidadeAgua = null;
	
			String idLocalidade = null;
	
			String codigoSetor = null;
	
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
	
			if (objetoConsulta != null && !objetoConsulta.equals("")) {
	
				// Validamos o cliente
				if (form.getIdLocalidade() != null
						&& !form.getIdLocalidade().trim().equals("")) {
					FiltroLocalidade filtro = new FiltroLocalidade();
	
					filtro.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, form.getIdLocalidade()));
	
					Collection colLocalidade = fachada.pesquisar(filtro,
							Localidade.class.getName());
	
					if (colLocalidade == null
							|| !colLocalidade.iterator().hasNext()) {
						// O cliente não existe
						form.setIdLocalidade("");
						form.setLocalidadeDescricao("Localidade inexistente");
						httpServletRequest.setAttribute("localidadeEncontrada",
								"exception");
	
					} else {
						Localidade localidade = (Localidade) Util
								.retonarObjetoDeColecao(colLocalidade);
						form.setIdLocalidade(localidade.getId().toString());
	
						form.setLocalidadeDescricao(localidade.getDescricao());
					}
				}else{
					form.setIdLocalidade("");
					form.setLocalidadeDescricao("");
				}
	
				if (form.getIdSetorComercial() != null
						&& !form.getIdSetorComercial().trim().equals("")) {
					// Validamos o imovel
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form
									.getIdSetorComercial()));
	
					Collection colSetorComercial = fachada.pesquisar(
							filtroSetorComercial, SetorComercial.class.getName());
	
					if (colSetorComercial == null || colSetorComercial.isEmpty()) {
						// O Imovel não existe
						form.setIdSetorComercial("");
						form
								.setSetorComercialDescricao("Setor Comercial inexistente");
	
						httpServletRequest.setAttribute(
								"codigoSetorComercialEncontrado", "exception");
	
					} else {
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colSetorComercial);
	
						form.setIdSetorComercial(setorComercial.getCodigo() + "");
	
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());
					}
				}else{
					form.setIdSetorComercial("");
					form.setSetorComercialDescricao("");
				}
				
				if (form.getFonteCaptacao() != null && !form.getFonteCaptacao().equals("-1")){
					FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
					
					filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
							FiltroFonteCaptacao.DESCRICAO, form
									.getFonteCaptacao()));
	
					Collection colFonteCaptacao = fachada.pesquisar(
							filtroFonteCaptacao, FonteCaptacao.class.getName());
	
					if (colFonteCaptacao != null || !colFonteCaptacao.isEmpty()) {
						
						FonteCaptacao fonteCaptacao = (FonteCaptacao) Util
								.retonarObjetoDeColecao(colFonteCaptacao);
	
						form.setFonteCaptacao(fonteCaptacao.getDescricao());
	
					}
				}else{
					form.setFonteCaptacao("");
				}
				
			} else {
	
				if (idQualidadeAgua != null && !idQualidadeAgua.trim().equals("")
						&& Integer.parseInt(idQualidadeAgua) > 0) {
	
					FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
	
					filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
							FiltroQualidadeAgua.ID, idQualidadeAgua));
					Collection colecaoQualidadeAgua = fachada.pesquisar(
							filtroQualidadeAgua, QualidadeAgua.class.getName());
					if (colecaoQualidadeAgua != null
							&& !colecaoQualidadeAgua.isEmpty()) {
						qualidadeAgua = (QualidadeAgua) Util
								.retonarObjetoDeColecao(colecaoQualidadeAgua);
	
						// sessao.setAttribute("qualidadeAguaBase", qualidadeAgua);
					}
	
					if (qualidadeAgua.getLocalidade() != null
							&& (idLocalidade == null || idLocalidade.trim().equals(
									""))) {
	
						if (qualidadeAgua.getLocalidade() != null
								&& !qualidadeAgua.getLocalidade().getId()
										.toString().trim().equals("")) {
	
							idLocalidade = qualidadeAgua.getLocalidade().getId()
									.toString();
	
							FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
							filtroLocalidade
									.adicionarParametro(new ParametroSimples(
											FiltroLocalidade.ID, idLocalidade));
	
							Collection colecaoLocalidade = fachada.pesquisar(
									filtroLocalidade, Localidade.class.getName());
	
							Localidade localidade = (Localidade) Util
									.retonarObjetoDeColecao(colecaoLocalidade);
	
							form.setIdLocalidade(localidade.getId().toString());
	
							form.setLocalidadeDescricao(localidade.getDescricao());
						}
					}else{
						form.setIdLocalidade("");
						form.setLocalidadeDescricao("");
					}
	
					if (qualidadeAgua.getSetorComercial() != null
							&& (codigoSetor == null || codigoSetor.trim()
									.equals(""))) {
	
						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	
						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.ID, qualidadeAgua
												.getSetorComercial().getId()
												.toString()));
	
						Collection colSetor = fachada.pesquisar(
								filtroSetorComercial, SetorComercial.class
										.getName());
	
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colSetor);
						form.setIdSetorComercial(setorComercial.getCodigo() + "");
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());
	
					}else{
						form.setIdSetorComercial("");
						form.setSetorComercialDescricao("");
					}
					form.setIdQualidadeAgua(idQualidadeAgua);
					
					if(qualidadeAgua.getFonteCaptacao() != null){
						form.setFonteCaptacao(qualidadeAgua.getFonteCaptacao().getId()+"");	
					}
					
					if (qualidadeAgua.getSistemaAbastecimento() != null){
						
						form.setSistemaAbastecimento(qualidadeAgua.getSistemaAbastecimento().getDescricao());
						
					}else{
						
						form.setSistemaAbastecimento("");
						
					}
					
					form.setReferencia(
						Util.formatarAnoMesParaMesAno(qualidadeAgua.getAnoMesReferencia()));
	
					SistemaParametro sistemaParametro = fachada
							.pesquisarParametrosDoSistema();
	
					if (qualidadeAgua.getAnoMesReferencia().intValue() >= sistemaParametro
							.getAnoMesFaturamento().intValue()) {
						sessao.removeAttribute("desabilitaCampos");
					} else {
						sessao.setAttribute("desabilitaCampos", "S");
					}
					// dados do mes
					form.setIndiceMensalCloroResidual(qualidadeAgua
							.getNumeroCloroResidual() != null ? qualidadeAgua
							.getNumeroCloroResidual().toString() : "0");
					form
							.setIndiceMensalColiformesFecais(qualidadeAgua
									.getNumeroIndiceColiformesFecais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesFecais().toString()
									: "0");
					form
							.setIndiceMensalColiformesTotais(qualidadeAgua
									.getNumeroIndiceColiformesTotais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTotais().toString()
									: "0");
					form
							.setIndiceMensalCor(qualidadeAgua.getNumeroIndiceCor() != null ? qualidadeAgua
									.getNumeroIndiceCor().toString()
									: "0");
					form
							.setIndiceMensalFerro(qualidadeAgua
									.getNumeroIndiceFerro() != null ? qualidadeAgua
									.getNumeroIndiceFerro().toString() : "0");
					form
							.setIndiceMensalFluor(qualidadeAgua
									.getNumeroIndiceFluor() != null ? qualidadeAgua
									.getNumeroIndiceFluor().toString() : "0");
					form
							.setIndiceMensalNitrato(qualidadeAgua
									.getNumeroNitrato() != null ? qualidadeAgua
									.getNumeroNitrato().toString() : "0");
					form
							.setIndiceMensalPH(qualidadeAgua.getNumeroIndicePh() != null ? qualidadeAgua
									.getNumeroIndicePh().toString()
									: "0");
					form.setIndiceMensalTurbidez(qualidadeAgua
							.getNumeroIndiceTurbidez() != null ? qualidadeAgua
							.getNumeroIndiceTurbidez().toString() : "0");
	
					form
							.setIndiceMensalColiformesTermotolerantes(qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes()
									.toString()
									: "0");
					
					form
							.setIndiceMensalAlcalinidade(qualidadeAgua
									.getNumeroIndiceAlcalinidade() != null ? qualidadeAgua
									.getNumeroIndiceAlcalinidade()
									.toString()
									: "0");
	
					// dados padrao
					FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
					Collection colPadrao = fachada.pesquisar(
							filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class
									.getName());
					if (!colPadrao.isEmpty()) {
						QualidadeAguaPadrao qualidadeAguaPadrao = (QualidadeAguaPadrao) Util
								.retonarObjetoDeColecao(colPadrao);
	
						form.setPadraoCloroResidual(qualidadeAguaPadrao
								.getDescricaoPadraoCloro());
						form.setPadraoColiformesFecais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesFecais());
						form.setPadraoColiformesTotais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTotais());
						form.setPadraoCor(qualidadeAguaPadrao
								.getDescricaoPadraoCor());
						form.setPadraoFerro(qualidadeAguaPadrao
								.getDescricaoPadraoFerro());
						form.setPadraoFluor(qualidadeAguaPadrao
								.getDescricaoPadraoFluor());
						form.setPadraoNitrato(qualidadeAguaPadrao
								.getDescricaoNitrato());
						form
								.setPadraoPH(qualidadeAguaPadrao
										.getDescricaoPadraoPh());
						form.setPadraoTurbidez(qualidadeAguaPadrao
								.getDescricaoPadraoTurbidez());
	
						form.setPadraoColiformesTermotolerantes(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTermotolerantes());
						
						form.setPadraoAlcalinidade(qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade());
	
						sessao.setAttribute("qualidadeAguaPadraoId",
								qualidadeAguaPadrao.getId());
	
						sessao.setAttribute("qualidadeAguaPadrao",
								qualidadeAguaPadrao);
					}
				}
	
				if (qualidadeAgua == null
						&& sessao.getAttribute("qualidadeAgua") != null) {
					qualidadeAgua = (QualidadeAgua) sessao
							.getAttribute("qualidadeAgua");
	
					if (qualidadeAgua.getLocalidade() != null
							&& (idLocalidade == null || idLocalidade.trim().equals(
									""))) {
	
						if (qualidadeAgua.getLocalidade() != null
								&& !qualidadeAgua.getLocalidade().getId()
										.toString().trim().equals("")) {
	
							idLocalidade = qualidadeAgua.getLocalidade().getId()
									.toString();
	
							FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
							filtroLocalidade
									.adicionarParametro(new ParametroSimples(
											FiltroLocalidade.ID, idLocalidade));
	
							Collection colecaoLocalidade = fachada.pesquisar(
									filtroLocalidade, Localidade.class.getName());
	
							Localidade localidade = (Localidade) Util
									.retonarObjetoDeColecao(colecaoLocalidade);
	
							form.setIdLocalidade(localidade.getId().toString());
	
							form.setLocalidadeDescricao(localidade.getDescricao());
						}
					}
	
					if (qualidadeAgua.getSetorComercial() != null
							&& (codigoSetor == null || codigoSetor.trim()
									.equals(""))) {
	
						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	
						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.ID, qualidadeAgua
												.getSetorComercial().getId()
												.toString()));
	
						Collection colSetor = fachada.pesquisar(
								filtroSetorComercial, SetorComercial.class
										.getName());
	
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colSetor);
						form.setIdSetorComercial(setorComercial.getCodigo() + "");
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());
	
					}
					form.setIdQualidadeAgua(idQualidadeAgua);
	
					if(qualidadeAgua.getFonteCaptacao() != null){
						form.setFonteCaptacao(qualidadeAgua.getFonteCaptacao().getId()+"");	
					}else{
						form.setFonteCaptacao("");
					}
					
					if(qualidadeAgua.getSistemaAbastecimento() != null){
						form.setSistemaAbastecimento(qualidadeAgua.getSistemaAbastecimento().getDescricao());
					}else{
						form.setSistemaAbastecimento("");
					}
	
					form.setReferencia(Util.formatarAnoMesParaMesAno(qualidadeAgua
							.getAnoMesReferencia()));
	
					SistemaParametro sistemaParametro = fachada
							.pesquisarParametrosDoSistema();
	
					if (qualidadeAgua.getAnoMesReferencia().intValue() >= sistemaParametro
							.getAnoMesFaturamento().intValue()) {
						sessao.removeAttribute("desabilitaCampos");
					} else {
						sessao.setAttribute("desabilitaCampos", "S");
					}
					// dados do mes
					form.setIndiceMensalCloroResidual(qualidadeAgua
							.getNumeroCloroResidual() != null ? qualidadeAgua
							.getNumeroCloroResidual().toString() : "0");
					form
							.setIndiceMensalColiformesFecais(qualidadeAgua
									.getNumeroIndiceColiformesFecais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesFecais().toString()
									: "0");
					form
							.setIndiceMensalColiformesTotais(qualidadeAgua
									.getNumeroIndiceColiformesTotais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTotais().toString()
									: "0");
					form
							.setIndiceMensalCor(qualidadeAgua.getNumeroIndiceCor() != null ? qualidadeAgua
									.getNumeroIndiceCor().toString()
									: "0");
					form
							.setIndiceMensalFerro(qualidadeAgua
									.getNumeroIndiceFerro() != null ? qualidadeAgua
									.getNumeroIndiceFerro().toString() : "0");
					form
							.setIndiceMensalFluor(qualidadeAgua
									.getNumeroIndiceFluor() != null ? qualidadeAgua
									.getNumeroIndiceFluor().toString() : "0");
					form
							.setIndiceMensalNitrato(qualidadeAgua
									.getNumeroNitrato() != null ? qualidadeAgua
									.getNumeroNitrato().toString() : "0");
					form
							.setIndiceMensalPH(qualidadeAgua.getNumeroIndicePh() != null ? qualidadeAgua
									.getNumeroIndicePh().toString()
									: "0");
					form.setIndiceMensalTurbidez(qualidadeAgua
							.getNumeroIndiceTurbidez() != null ? qualidadeAgua
							.getNumeroIndiceTurbidez().toString() : "0");
	
					form
							.setIndiceMensalColiformesTermotolerantes(qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes()
									.toString()
									: "0");
					
					form
							.setIndiceMensalAlcalinidade(qualidadeAgua
									.getNumeroIndiceAlcalinidade() != null ? qualidadeAgua
									.getNumeroIndiceAlcalinidade()
									.toString()
									: "0");
	
					if (sessao.getAttribute("qualidadeAguaPadrao") != null) {
						QualidadeAguaPadrao qualidadeAguaPadrao = (QualidadeAguaPadrao) sessao
								.getAttribute("qualidadeAguaPadrao");
	
						form.setPadraoCloroResidual(qualidadeAguaPadrao
								.getDescricaoPadraoCloro());
						form.setPadraoColiformesFecais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesFecais());
						form.setPadraoColiformesTotais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTotais());
						form.setPadraoCor(qualidadeAguaPadrao
								.getDescricaoPadraoCor());
						form.setPadraoFerro(qualidadeAguaPadrao
								.getDescricaoPadraoFerro());
						form.setPadraoFluor(qualidadeAguaPadrao
								.getDescricaoPadraoFluor());
						form.setPadraoNitrato(qualidadeAguaPadrao
								.getDescricaoNitrato());
						form
								.setPadraoPH(qualidadeAguaPadrao
										.getDescricaoPadraoPh());
						form.setPadraoTurbidez(qualidadeAguaPadrao
								.getDescricaoPadraoTurbidez());
	
						form.setPadraoColiformesTermotolerantes(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTermotolerantes());
						
						form.setPadraoAlcalinidade(qualidadeAguaPadrao
								.getDescricaoPadraoAlcalinidade());
					}
				}
	
				if (qualidadeAgua != null) {
					sessao.setAttribute("qualidadeAgua", qualidadeAgua);
	
				}
				if (sessao.getAttribute("colecaoQualidadeAgua") != null) {
					sessao.setAttribute("caminhoRetornoVoltar",
							"/gsan/filtrarQualidadeAguaAction.do");
				} else {
					sessao.setAttribute("caminhoRetornoVoltar",
							"/gsan/exibirFiltrarQualidadeAguaAction.do");
				}
	
			}
			
			this.montaColecaoFonteCaptacao(form,httpServletRequest);
		}else{
			sessao.setAttribute("PrimeiraVez",2);
		}
		
		return retorno;
	}
	
	/**
	 * Monta a colecao de Fontes de Captacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */	
	private void montaColecaoFonteCaptacao(
			AtualizarQualidadeAguaActionForm form,HttpServletRequest httpServletRequest){
		
		String localidade = form.getIdLocalidade();
		String setorComercial = form.getIdSetorComercial();
		Collection<FonteCaptacao> colecaoFonteCaptacao = new ArrayList<FonteCaptacao>();
		
		//Pesquisa fontes de captacao por setor comercial
		if(localidade != null && !localidade.equals("") && 
			setorComercial != null && !setorComercial.equals("")){

			Collection colecaoSetoresComerciais = 
				this.pesquisarSetorComercial(
					new Integer(localidade),
					new Integer(setorComercial));
						
			if (colecaoSetoresComerciais != null && !colecaoSetoresComerciais.isEmpty()) {
				
				SetorComercial setor = 
					(SetorComercial) colecaoSetoresComerciais.iterator().next();
				
				Collection colecaoSetor = new ArrayList();
				colecaoSetor.add(setor);
				
				colecaoFonteCaptacao = this.getFachada().pesquisarFonteCaptacao(colecaoSetor);
			}
			
			
		}else if(localidade != null && !localidade.equals("")){

			Collection colecaoSetoresComerciais = 
				this.pesquisarSetorComercial(
					new Integer(localidade),
					null);
						
			colecaoFonteCaptacao = this.getFachada().pesquisarFonteCaptacao(colecaoSetoresComerciais);			
		}else{
			
			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			filtroFonteCaptacao.limparListaParametros();
			filtroFonteCaptacao.adicionarParametro(
				new ParametroSimples( 
						FiltroFonteCaptacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoFonteCaptacao = 
				this.getFachada().pesquisar(filtroFonteCaptacao, 
					FonteCaptacao.class.getName());
		}
		
		if (colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()) {
			httpServletRequest.setAttribute("colecaoFonteCaptacao",colecaoFonteCaptacao);
		} 

	}
	
	/**
	 * Pesquisa o SetorComercial
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */		
	private Collection<SetorComercial> pesquisarSetorComercial(Integer localidade,Integer setorComercial){
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		filtroSetorComercial.limparListaParametros();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples( 
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples( 
				FiltroSetorComercial.ID_LOCALIDADE, 
				localidade));
		
		if(setorComercial != null){

			filtroSetorComercial.adicionarParametro(
				new ParametroSimples( 
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercial));
		}
		
		return 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	}
}

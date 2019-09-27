package gcom.gui.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroImovelNaoGerado;
import gcom.cobranca.ImovelNaoGerado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * @author Anderson Italo
 * @date 30/11/2009
 * Classe responsável pela exibição do filtro de UC9999 Consultar Motivo da não Geração de Documento de Cobrança
 */
public class ExibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("motivoNaoGeracaoDocumentoTipoComandoEventual");
		
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		//4.	Caso a opção por imóvel tenha sido escolhida 
		if (httpServletRequest.getParameter("filtroPorImovel") != null 
				&& httpServletRequest.getParameter("filtroPorImovel").equals("true")){
			form.setIndicadorTipoPesquisa("2");
			
			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
			filtroCobrancaAcaoAtividadeComando.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComando.ID, new Integer(form.getIdCobrancaAcaoAtividadeComando())));
			
			Collection colecaoCobrancaAcaoAtividadeComando = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando)Util.retonarObjetoDeColecao(colecaoCobrancaAcaoAtividadeComando); 
			
			Imovel imovel = fachada.pesquisarImovel(new Integer(form.getMatriculaImovel()));
			
			if (imovel != null){
				FiltroImovelNaoGerado filtroImovelNaoGerado = new FiltroImovelNaoGerado();
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_IMOVEL, imovel.getId()));
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_COMANDO, cobrancaAcaoAtividadeComando.getId()));
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
				Collection colecaoImovelNaoGerado = fachada.pesquisar(filtroImovelNaoGerado, ImovelNaoGerado.class.getName());
				
				/*[FS0004] - Motivo não encontrado
				 * . Caso a matrícula do imóvel informada não exista na tabela 
				 * IMOVEL_NAO_GERADO para o CAAC_ID ou CACM_ID em questão, exibir 
				 * a mensagem “Imóvel não pertence ao universo do comando ou teve 
				 * documento gerado” e retornar para o passo correspondente 
				 * no fluxo principal.
				*/
				if (colecaoImovelNaoGerado == null || colecaoImovelNaoGerado.isEmpty()){
					throw new ActionServletException("atencao.imovel_nao_pertence_comando_documento_gerado");
				}else{
					/*4.2.1.	O sistema exibe o motivo de não Geração 
					 * (MNGD_DSMOTIVO com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO)*/
					ImovelNaoGerado imovelNaogerado = (ImovelNaoGerado)Util.retonarObjetoDeColecao(colecaoImovelNaoGerado);
					form.setDescricaoMotivo(imovelNaogerado.getMotivoNaoGeracaoDocCobranca().getDescricao());
				}
			}else{
				throw new ActionServletException("atencao.imovel.inexistente");
			}
		}else{
			form.setIndicadorTipoPesquisa("1");
			form.setIndicadorTipoRelatorio("1");
		}
		
		
		//CobrancaAcaoAtividadeComando
		Collection colecaoCobrancaAcaoAtividadeComando = fachada.obterListaAtividadesEventuaisAcaoCobrancaComandadasRecentes();
			
		//[SB0002] - Comandos de Ação de Cobrança Eventual
		//6.2. Caso o comando seja por cliente não disponibilizar as opções geográficas.
		if(form.getIdCobrancaAcaoAtividadeComando() != null && !form.getIdCobrancaAcaoAtividadeComando().equals("")){
			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE);
			filtroCobrancaAcaoAtividadeComando.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComando.ID, new Integer(form.getIdCobrancaAcaoAtividadeComando())));
			
			Collection colecaoCobrancaAC = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando)Util.retonarObjetoDeColecao(colecaoCobrancaAC);
			
			if (cobrancaAcaoAtividadeComando.getCliente() != null){
				sessao.setAttribute("coordenadas","true");
				form.setDescricaoLocalidade("");
				form.setIdLocalidade("");
				form.setIdSetorComercial("");
				form.setDescricaoSetorComercial("");
				form.setIdQuadra("");
				form.setDescricaoQuadra("");
				
			}
			else{
				sessao.setAttribute("coordenadas","false");
			}
		}
		else{
			sessao.setAttribute("coordenadas","true");
			form.setDescricaoLocalidade("");
			form.setIdLocalidade("");
			form.setIdSetorComercial("");
			form.setDescricaoSetorComercial("");
			form.setIdQuadra("");
			form.setDescricaoQuadra("");
		}
		
		
		
		
		//[FS002] - Verificar existência de dados
		if (colecaoCobrancaAcaoAtividadeComando == null || colecaoCobrancaAcaoAtividadeComando.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"CobrancaAtividadeComando");
		}
		
		
		//Gerência regional
		Collection colecaoGerenciaRegional = fachada.obterColecaoGerenciaRegional();
		sessao.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		
		//Unidade negócio
		Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocio();
		sessao.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		
		//Tratamento das buscas através do enter
		//=================================================
				
		//Localidade
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){
			Integer idLocalidade = new Integer(form.getIdLocalidade());
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(idLocalidade);
			
			if(localidade != null){
				form.setDescricaoLocalidade(localidade.getDescricao());
			}
			else{
				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
				form.setIdLocalidade("");
				form.setIdSetorComercial("");
				form.setDescricaoSetorComercial("");
				form.setIdQuadra("");
				form.setDescricaoQuadra("");
				httpServletRequest.setAttribute("localidadeException","ok");
			}
			
		}
		
		//Setor Comercial
		String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
		if(pesquisarSetorComercial != null && !"".equals(pesquisarSetorComercial)){
			
			String idSetorComercial = form.getIdSetorComercial();
			//Localidade localidadeInicial = (Localidade) sessao.getAttribute("localidadeInicial");
			String idLocalidadeInicial = form.getIdLocalidade();
				
			SetorComercial setorComercial = fachada.obterSetorComercialLocalidade(idLocalidadeInicial,idSetorComercial);
			
			if(setorComercial != null){
				form.setDescricaoSetorComercial(setorComercial.getDescricao());
				sessao.setAttribute("setorComercialMotivoNaoGeracao", setorComercial);
			}
			else{
				form.setDescricaoSetorComercial("SETOR COMERCIAL INEXISTENTE");
				form.setIdSetorComercial("");
				form.setIdQuadra("");
				form.setDescricaoQuadra("");
				httpServletRequest.setAttribute("setorComercialException","ok");
				sessao.removeAttribute("setorComercialMotivoNaoGeracao");
			}
			
		}
		
		//Quadra
		String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");
		if(pesquisarQuadra != null && !"".equals(pesquisarQuadra)){
			
			SetorComercial setorComercial = (SetorComercial)sessao.getAttribute("setorComercialMotivoNaoGeracao");
			
			int idQuadra = Integer.parseInt(form.getIdQuadra());
			
			Quadra quadra = null;
			if(setorComercial != null)
				quadra = fachada.obterQuadraSetorComercial(setorComercial.getId(),idQuadra);
			
			
			if(quadra != null){
				form.setDescricaoQuadra(quadra.getDescricao());
				sessao.setAttribute("quadra",quadra);
			}
			else{
				form.setDescricaoQuadra("QUADRA INEXISTENTE");
				form.setIdQuadra("");
				sessao.removeAttribute("quadra");
			}
			
		}
		
		httpServletRequest.setAttribute("colecaoCobrancaAcaoAtividadeComando", colecaoCobrancaAcaoAtividadeComando);
		
		return retorno;

	}

}

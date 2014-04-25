package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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
 * Descrição da classe
 * 
 * @author Ana Maria
 * @date 30/06/2006
 */

public class EfetuarInstalacaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar instalação de hidrômetro
	 * 
	 * [UC0362] Efetuar Instalação de Hidrômetro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		EfetuarInstalacaoHidrometroActionForm efetuarInstalacaoHidrometroActionForm = 
			(EfetuarInstalacaoHidrometroActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		
		
		String numeroHidrometro = efetuarInstalacaoHidrometroActionForm.getNumeroHidrometro();
		
		String idServicoMotivoNaoCobranca = efetuarInstalacaoHidrometroActionForm.getMotivoNaoCobranca();
		String valorPercentual = efetuarInstalacaoHidrometroActionForm.getPercentualCobranca();
		String qtdParcelas = efetuarInstalacaoHidrometroActionForm.getQuantidadeParcelas();
		//String nmLacre = efetuarInstalacaoHidrometroActionForm.getNumeroLacre();
		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");	
        Imovel imovel = null;
        
        if(ordemServico.getImovel() != null){
            imovel = ordemServico.getImovel();
        }else{
            imovel = ordemServico.getRegistroAtendimento().getImovel();
        }
			
		if (numeroHidrometro != null) {
            //Constrói o filtro para pesquisa do Hidrômetro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
	        //Realiza a pesquisa do Hidrômetro
			Collection colecaoHidrometro = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
			
			//verificar se o número do hidrômetro não está cadastrado
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				throw new ActionServletException("atencao.hidrometro_inexistente");
			}
			Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			Imovel imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			
			if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null && 
				hidrometro.getHidrometroLocalArmazenagem() != null &&
				!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
					throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
			}
			
            hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}
		
		//Atualiza a entidade com os valores do formulário
		efetuarInstalacaoHidrometroActionForm.setFormValues(hidrometroInstalacaoHistorico);
		
		// Informa que o usuário que fez a instalação é o usuário logado
		hidrometroInstalacaoHistorico.setUsuarioInstalacao( usuario );
		
		
		if(ordemServico != null 
				 && efetuarInstalacaoHidrometroActionForm.getIdTipoDebito() != null){
					
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
					
			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
					
			BigDecimal valorAtual = new BigDecimal(0);
			
			if (efetuarInstalacaoHidrometroActionForm.getValorDebito() != null) {
			    String valorDebito = efetuarInstalacaoHidrometroActionForm
			     	.getValorDebito().toString().replace(".", "");
			    
			    valorDebito = valorDebito.replace(",", ".");
			    
			    valorAtual = new BigDecimal(valorDebito);

			    ordemServico.setValorAtual(valorAtual);
			}
					
			if(idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
			   servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
			   servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
					
			if(valorPercentual != null){
			   ordemServico.setPercentualCobranca(new BigDecimal(efetuarInstalacaoHidrometroActionForm.getPercentualCobranca()));
			}
					
			ordemServico.setUltimaAlteracao(new Date());				
		}
		
		if (efetuarInstalacaoHidrometroActionForm.getTipoPoco() != null && 
				!efetuarInstalacaoHidrometroActionForm.getTipoPoco().equals("-1")){
			
			PocoTipo pocoTipo = new PocoTipo();
			
			pocoTipo.setId(new Integer(efetuarInstalacaoHidrometroActionForm.getTipoPoco()));
			
			imovel.setPocoTipo(pocoTipo);
		}
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		integracaoComercialHelper.setImovel(imovel);
		
		if(efetuarInstalacaoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarInstalacaoHidrometro(integracaoComercialHelper);
		}else{
			fachada.validacaoInstalacaoHidrometro(numeroHidrometro);
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			
			sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
			
			if(sessao.getAttribute("semMenu") == null){
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			}else{
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Instalação de Hidrômetro para o imóvel "+imovel.getId()
					+ " efetuada com sucesso.", "Efetuar outra Instalação de Hidrômetro",
					"exibirEfetuarInstalacaoHidrometroAction.do");
		}
		
		sessao.removeAttribute("EfetuarInstalacaoHidrometroActionForm");

		return retorno;
	}
	
}

package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Atualiza o tipo de corte
 * 
 * Autor: Hugo Amorim
 * 
 * Data: 18/05/2009
 */
public class AlterarTipoCorteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		retorno.getName();
		
		IntegracaoComercialHelper helper = new IntegracaoComercialHelper();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AlterarTipoCorteActionForm form = (AlterarTipoCorteActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		if(form.getIdOrdemServico()!=null && !form.getIdOrdemServico().equals("")){
		
			if (sessao.getAttribute("ordemServico") != null) {
				
				//pesquisa Ligacao agua
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAgua.ID, form.getMatriculaImovel()));
				
				filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroLigacaoAgua.CORTE_TIPO);

				Collection colecaoLigacao = getFachada().pesquisar(
						filtroLigacaoAgua, LigacaoAgua.class.getName());


				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util
						.retonarObjetoDeColecao(colecaoLigacao);
				
				//Pesquisa Imovel
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,form.getMatriculaImovel()));

				Collection colecaoImovel = getFachada().pesquisar(filtroImovel,
						Imovel.class.getName());

				

				Imovel imovel = (Imovel) colecaoImovel.iterator().next();
				
				//pesquisa OS
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID,form.getIdOrdemServico()));
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SERVICO_TIPO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.debitoTipo");
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoNaoCobrancaMotivo");
  
				Collection colecaoOrdemServico = fachada.pesquisar(filtroOrdemServico,OrdemServico.class.getName());
	
				OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico.iterator().next();
				
				helper.setOrdemServico(ordemServico);	
				helper.setLigacaoAgua(ligacaoAgua);	
				helper.getLigacaoAgua().getCorteTipo().setId(new Integer(form.getTipoCorte()));
				helper.getLigacaoAgua().setUltimaAlteracao(form.getDataConcorrencia());
				helper.setMatriculaImovel(form.getMatriculaImovel());
				helper.setImovel(imovel);

				String debitoEncontrado = "false";
				
				debitoEncontrado = (String) sessao.getAttribute("debitoEncontrado");
				
				if(debitoEncontrado!=null && debitoEncontrado.equals("true") &&  !form.getMotivoNaoCobranca().equals("-1")){
					
					ServicoNaoCobrancaMotivo sncm = new ServicoNaoCobrancaMotivo();
					
					sncm.setId(new Integer(form.getMotivoNaoCobranca()));
					
					helper.getOrdemServico().setServicoNaoCobrancaMotivo(sncm);
					
				}else if(debitoEncontrado!=null && debitoEncontrado.equals("true")){
					
					BigDecimal valorAtual = new BigDecimal(0);
					if (form.getValorDebito() != null) {
					    String valorDebito = form
					     	.getValorDebito().toString().replace(".", "");
					    
					    valorDebito = valorDebito.replace(",", ".");
					    
					    valorAtual = new BigDecimal(valorDebito);

					}
					
					
					
					helper.getOrdemServico().setId(new Integer(form.getIdOrdemServico()));
					helper.getOrdemServico().getServicoTipo().getDebitoTipo().setId(new Integer(form.getIdTipoDebito()));
					helper.getOrdemServico().setValorAtual(valorAtual);
					helper.setQtdParcelas(form.getQuantidadeParcelas());
					helper.getOrdemServico().setPercentualCobranca(new BigDecimal(form.getPercentualCobranca()));
					helper.setUsuarioLogado(usuarioLogado);
					
				}
					
					
			 }
			if(form.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
				helper.setVeioEncerrarOS(Boolean.FALSE);
				fachada.atualizarTipoCorte(helper);
			}else{
				helper.setVeioEncerrarOS(Boolean.TRUE);
				sessao.setAttribute("integracaoComercialHelper", helper);
				
				if(sessao.getAttribute("menu") == null){
					retorno = actionMapping.findForward("encerrarOrdemServicoAction");
				}else{
					retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
				}
				sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
			}
					
		}
		retorno.getName();
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){

			montarPaginaSucesso(httpServletRequest,"Tipo do Corte Ligação de Água do imóvel "+form.getMatriculaImovel()+" efetuado com sucesso",
					"Atualizar outro Corte de Ligação de Água",
					"exibirAlterarTipoCorteAction.do?menu=sim");
		
		}
	  return retorno;
	}
}

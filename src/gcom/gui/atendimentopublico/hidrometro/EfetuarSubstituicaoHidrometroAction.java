package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
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
 * Efetua a substituição do hidrômetro de acordo com os parâmetros informados
 * 
 * @author Ana Maria
 * @date 19/07/2006
 */

public class EfetuarSubstituicaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar substituição de hidrômetro
	 * 
	 * [UC0364] Efetuar Substituição de Hidrômetro
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
		
		EfetuarSubstituicaoHidrometroActionForm efetuarSubstituicaoHidrometroActionForm = 
			(EfetuarSubstituicaoHidrometroActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		
		
		String matriculaImovel = efetuarSubstituicaoHidrometroActionForm.getMatriculaImovel();
		String numeroHidrometro = efetuarSubstituicaoHidrometroActionForm.getNumeroHidrometro();
		String tipoMedicaoAtual = efetuarSubstituicaoHidrometroActionForm.getTipoMedicaoAtual();		
        String situacaoHidrometroSubstituido = efetuarSubstituicaoHidrometroActionForm.getSituacaoHidrometro();
        
        String localArmazenagemHidrometro = null;
        String hidrometroExtraviado = (String) sessao.getAttribute("hidrometroExtravido");
        sessao.removeAttribute("hidrometroExtravido");
        
        // caso o hidrometro esteja extraviado, nao pega o local de armazenagem
        if(hidrometroExtraviado == null || !hidrometroExtraviado.equals("sim")){
        localArmazenagemHidrometro = efetuarSubstituicaoHidrometroActionForm.getLocalArmazenagemHidrometro();        	
        }
        
        String numeroLeituraRetiradaHidrometro = efetuarSubstituicaoHidrometroActionForm.getNumeroLeitura();
		String idServicoMotivoNaoCobranca = efetuarSubstituicaoHidrometroActionForm.getMotivoNaoCobranca();
		String valorPercentual = efetuarSubstituicaoHidrometroActionForm.getPercentualCobranca();

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		
        //Constrói o filtro para pesquisa da Ordem de Serviço		
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
        
        if(ordemServico.getImovel() != null){
            matriculaImovel =  ordemServico.getImovel().getId().toString();
        }else{
            matriculaImovel =  ordemServico.getRegistroAtendimento().getImovel().getId().toString();
        }
			
		if (numeroHidrometro != null) {

			//Constrói o filtro para pesquisa do Hidrômetro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(
					FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
	        
			//Realiza a pesquisa do Hidrômetro
			Collection colecaoHidrometro = null;
			colecaoHidrometro = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
			
			//verifica se o número do hidrômetro não está cadastrado
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				throw new ActionServletException("atencao.numero_hidrometro_inexistente", null, 
						efetuarSubstituicaoHidrometroActionForm.getNumeroHidrometro());
			}
			
			Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, matriculaImovel));
			
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
		efetuarSubstituicaoHidrometroActionForm.setFormValues(hidrometroInstalacaoHistorico);
		
		HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico = 
			(HidrometroInstalacaoHistorico)sessao.getAttribute("hidrometroSubstituicaoHistorico");
		
		Date dataRetirada = Util.converteStringParaDate(efetuarSubstituicaoHidrometroActionForm.getDataRetirada());
		
		hidrometroSubstituicaoHistorico.setDataRetirada(dataRetirada);
		
		if (numeroLeituraRetiradaHidrometro != null && 
			!numeroLeituraRetiradaHidrometro.equalsIgnoreCase("")){
			hidrometroSubstituicaoHistorico.setNumeroLeituraRetirada(new Integer(numeroLeituraRetiradaHidrometro));
		}
		
		hidrometroSubstituicaoHistorico.setUltimaAlteracao(new Date());
				
		BigDecimal valorAtual = new BigDecimal(0);
		
		if(ordemServico != null 
			 && efetuarSubstituicaoHidrometroActionForm.getIdTipoDebito() != null){
				
			 ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
				
			 ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
			
				if (efetuarSubstituicaoHidrometroActionForm.getValorDebito() != null
						&& !efetuarSubstituicaoHidrometroActionForm.getValorDebito().equals("")  ) {
				    String valorDebito = efetuarSubstituicaoHidrometroActionForm
				     	.getValorDebito().toString().replace(".", "");
				    
				    valorDebito = valorDebito.replace(",", ".");
				    
				    valorAtual = new BigDecimal(valorDebito);

				    ordemServico.setValorAtual(valorAtual);
				}
				
			 if(idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
			 }
			 ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
				
			 if(valorPercentual != null){
				ordemServico.setPercentualCobranca(new BigDecimal(efetuarSubstituicaoHidrometroActionForm.getPercentualCobranca()));
			 }
				
			 ordemServico.setUltimaAlteracao(new Date());				
		}
		
		String qtdParcelas = efetuarSubstituicaoHidrometroActionForm.getQuantidadeParcelas();
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setHidrometroSubstituicaoHistorico(hidrometroSubstituicaoHistorico);
		integracaoComercialHelper.setSituacaoHidrometroSubstituido(situacaoHidrometroSubstituido);
		if(localArmazenagemHidrometro != null){
		integracaoComercialHelper.setLocalArmazenagemHidrometro(new Integer(localArmazenagemHidrometro));
		}
		integracaoComercialHelper.setMatriculaImovel(matriculaImovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if(efetuarSubstituicaoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarSubstituicaoHidrometro(integracaoComercialHelper);
		}else{
//			fachada.validacaoSubstituicaoHidrometro(matriculaImovel,hidrometroInstalacaoHistorico.getHidrometro().getNumero(),hidrometroInstalacaoHistorico.getHidrometro().getHidrometroSituacao().getId().toString());
			fachada.validacaoSubstituicaoHidrometro(matriculaImovel,hidrometroInstalacaoHistorico.getHidrometro().getNumero(), situacaoHidrometroSubstituido);
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			
			sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
			
			if(sessao.getAttribute("semMenu") == null){
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			}else{
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
			
	   //Inserir na base de dados a instalação de hidrômetro e a atualização da substituição do hidrômetro
/*		fachada.efetuarSubstituicaoHidrometro(hidrometroInstalacaoHistorico,matriculaImovel, hidrometroSubstituicaoHistorico, 
					situacaoHidrometroSubstituido, new Integer(localArmazenagemHidrometro), ordemServico, efetuarSubstituicaoHidrometroActionForm.getVeioEncerrarOS().toString());	
*/		
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Substituição de Hidrômetro para "+ tipoMedicaoAtual +
				" no imóvel "+matriculaImovel+ " efetuada com sucesso.", 
				"Efetuar outra Substituição de Hidrômetro", "exibirEfetuarSubstituicaoHidrometroAction.do");
		}
        
		sessao.removeAttribute("EfetuarSubstituicaoHidrometroActionForm");
		sessao.removeAttribute("hidrometroSubstituicaoHistorico");

		return retorno;
	}
	
}

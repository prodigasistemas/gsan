package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoAguaAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoAguaActionForm ligacaoAguaActionForm = (EfetuarLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
				
		String ordemServicoId = ligacaoAguaActionForm.getIdOrdemServico();
		String diametroLigacao = ligacaoAguaActionForm.getDiametroLigacao();
		String materialLigacao = ligacaoAguaActionForm.getMaterialLigacao();
		String idPerfilLigacao = ligacaoAguaActionForm.getPerfilLigacao();
		
		//[FS0015] - verificar tarifa de consumo associada. 
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (
				FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,
				idPerfilLigacao));
		
		Collection pesquisa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
			Imovel imovelConsulta = null;
			
			if(ligacaoAguaActionForm.getMatriculaImovel() != null && ligacaoAguaActionForm.getMatriculaImovel() != ""){
				imovelConsulta= fachada.pesquisarImovel(new Integer(ligacaoAguaActionForm.getMatriculaImovel()));
			}else{
			    //caso apenas para usuario com permissao especial para efetuarLigacaoAguaSemRA
				if(ligacaoAguaActionForm.getIdImovel() != null && ligacaoAguaActionForm.getIdImovel() != ""){
					imovelConsulta= fachada.pesquisarImovel(new Integer(ligacaoAguaActionForm.getIdImovel()));
				}
			}
			
			while(iteratorColecaoConsumoTarifa.hasNext()){
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
				if (consumoTarifa.getLigacaoAguaPerfil() != null){
					if(imovelConsulta != null){
						if (imovelConsulta.getConsumoTarifa().getId().intValue() ==  consumoTarifa.getId().intValue()){
							existeTarifaIgual = true;
						}
					}
					
				}
			}
			
			if (!existeTarifaIgual){
				throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Ligação");
			}
		}
		
		String ramalLocalInstalacao = ligacaoAguaActionForm.getRamalLocalInstalacao();
		String idServicoMotivoNaoCobranca = ligacaoAguaActionForm.getMotivoNaoCobranca();
		String valorPercentual = ligacaoAguaActionForm.getPercentualCobranca();
		String numeroLacre = ligacaoAguaActionForm.getNumeroLacre();
		
		String idLigacaoOrigem = ligacaoAguaActionForm.getIdLigacaoOrigem();
		
		String idImovel = ligacaoAguaActionForm.getIdImovel();
		
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		Imovel imovel = null;
		
		
		
		
		if(idImovel!=null && !idImovel.equalsIgnoreCase("")){
			
				
			imovel = new Imovel();
			imovel.setId(new Integer(idImovel));
			imovel.setUltimaAlteracao(new Date());
			ligacaoAgua.setImovel(imovel);
			
			//[FS0005] Verificar preenchimento dos Campos
			//validar Data Corte
			if (ligacaoAguaActionForm.getDataLigacao() != null && 
				!ligacaoAguaActionForm.getDataLigacao().equals("")) {
				
				Date data = Util
						.converteStringParaDate(ligacaoAguaActionForm
								.getDataLigacao());
				ligacaoAgua.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo",null, " Data da Ligação");
			}
			
			if (diametroLigacao != null && 
				!diametroLigacao.equals("") && 
				!diametroLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
				ligacaoAguaDiametro.setId(new Integer(diametroLigacao));
				ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
				
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
					"Diametro da Ligação");
			}

			if (materialLigacao != null && 
				!materialLigacao.equals("") && 
				!materialLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial();
				ligacaoAguaMaterialMaterial.setId(new Integer(materialLigacao));
				ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
					"Material da Ligação");
			}

			if (idPerfilLigacao != null && 
				!idPerfilLigacao.equals("") && 
				!idPerfilLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
				ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
				ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
				
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
					"Perfil da Ligação");
			}
			
			if (ramalLocalInstalacao != null && 
				!ramalLocalInstalacao.equals("") && 
				!ramalLocalInstalacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
				ramalLocal.setId(new Integer(ramalLocalInstalacao));
				
				ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
			}
			// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
			// Ligacao Origem
			if(idLigacaoOrigem !=null && !idLigacaoOrigem.equals("") && 
				!idLigacaoOrigem.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));
				
				ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
				
			}
			
			if(numeroLacre !=null && !numeroLacre.equals("")){
				ligacaoAgua.setNumeroLacre(numeroLacre);
			}
						
			String qtdParcelas = ligacaoAguaActionForm.getQuantidadeParcelas();
			
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
			
			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(null);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			fachada.efetuarLigacaoAgua(integracaoComercialHelper);
			
		}
		
		

		if (ordemServicoId != null && !ordemServicoId.equals("") && (idImovel == null || idImovel.equals(""))) {

				OrdemServico ordemServico = (OrdemServico)sessao.getAttribute("ordemServico");

				if(ordemServico == null){
					throw new ActionServletException("atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");
				}

				if (sessao.getAttribute("imovel") != null) {
					
					imovel = (Imovel) sessao.getAttribute("imovel");
					imovel.setUltimaAlteracao(new Date());
					ligacaoAgua.setImovel(imovel);
				}
				
				//[FS0005] Verificar preenchimento dos Campos
				//validar Data Corte
				if (ligacaoAguaActionForm.getDataLigacao() != null && 
					!ligacaoAguaActionForm.getDataLigacao().equals("")) {
					
					Date data = Util
							.converteStringParaDate(ligacaoAguaActionForm
									.getDataLigacao());
					ligacaoAgua.setDataLigacao(data);
				} else {
					throw new ActionServletException("atencao.informe_campo",null, " Data da Ligação");
				}
				
				if (diametroLigacao != null && 
					!diametroLigacao.equals("") && 
					!diametroLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
					ligacaoAguaDiametro.setId(new Integer(diametroLigacao));
					ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
					
				} else {
					throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
						"Diametro da Ligação");
				}

				if (materialLigacao != null && 
					!materialLigacao.equals("") && 
					!materialLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial();
					ligacaoAguaMaterialMaterial.setId(new Integer(materialLigacao));
					ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
				} else {
					throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
						"Material da Ligação");
				}

				if (idPerfilLigacao != null && 
					!idPerfilLigacao.equals("") && 
					!idPerfilLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
					ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
					
				} else {
					throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
						"Perfil da Ligação");
				}
				
				if (ramalLocalInstalacao != null && 
					!ramalLocalInstalacao.equals("") && 
					!ramalLocalInstalacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					
					RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
					ramalLocal.setId(new Integer(ramalLocalInstalacao));
					
					ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
				}
				// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
				// Ligacao Origem
				if(idLigacaoOrigem !=null && !idLigacaoOrigem.equals("") && 
					!idLigacaoOrigem.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
					ligacaoOrigem.setId(new Integer(idLigacaoOrigem));
					
					ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
					
				}
				if(numeroLacre !=null && !numeroLacre.equals("")){
					ligacaoAgua.setNumeroLacre(numeroLacre);
				}
				
				if(ordemServico != null 
						&& ligacaoAguaActionForm.getIdTipoDebito() != null){
					
					ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
					
					ordemServico.setIndicadorComercialAtualizado(new Short("1"));
					
					if(idServicoMotivoNaoCobranca != null && 
						!idServicoMotivoNaoCobranca.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
						
						servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
						servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
					}
					
					ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
					
					if(valorPercentual != null){
						ordemServico.setPercentualCobranca(new BigDecimal(ligacaoAguaActionForm.getPercentualCobranca()));
					}
					
					ordemServico.setUltimaAlteracao(new Date());
					
				}

				BigDecimal valorAtual = new BigDecimal(0);
				if (ligacaoAguaActionForm.getValorDebito() != null) {
				    
					String valorDebito = 
						ligacaoAguaActionForm.getValorDebito().toString().replace(".", "");
				    
				    valorDebito = valorDebito.replace(",", ".");
				    valorAtual = new BigDecimal(valorDebito);

				    ordemServico.setValorAtual(valorAtual);
				}
				
			
				
				String qtdParcelas = ligacaoAguaActionForm.getQuantidadeParcelas();
				
				IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
				
				integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
				integracaoComercialHelper.setImovel(imovel);
				integracaoComercialHelper.setOrdemServico(ordemServico);
				integracaoComercialHelper.setQtdParcelas(qtdParcelas);
				integracaoComercialHelper.setUsuarioLogado(usuario);
				
				if(ligacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
					integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
					fachada.efetuarLigacaoAgua(integracaoComercialHelper);
				}else{
					integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
					sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
					
					if(sessao.getAttribute("semMenu") == null){
						retorno = actionMapping.findForward("encerrarOrdemServicoAction");
					}else{
						retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
					}
					sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
				}
		} 
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest,"Ligação de Água efetuada com Sucesso",
					"Efetuar outra Ligação de Água",
					"exibirEfetuarLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarLigacaoAguaAction.do?menu=sim&matriculaImovel="+imovel.getId(),
					"Atualização Ligação de Água efetuada");
		}
		
		return retorno;
	}
}

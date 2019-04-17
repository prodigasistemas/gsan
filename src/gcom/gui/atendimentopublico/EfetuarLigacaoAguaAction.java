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
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
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

public class EfetuarLigacaoAguaAction extends GcomAction {

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoAguaActionForm form = (EfetuarLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
				
		String ordemServicoId = form.getIdOrdemServico();
		String diametroLigacao = form.getDiametroLigacao();
		String materialLigacao = form.getMaterialLigacao();
		String idPerfilLigacao = form.getPerfilLigacao();
		String ramalLocalInstalacao = form.getRamalLocalInstalacao();
		String idServicoMotivoNaoCobranca = form.getMotivoNaoCobranca();
		String valorPercentual = form.getPercentualCobranca();
		String numeroLacre = form.getNumeroLacre();
		String idLigacaoOrigem = form.getIdLigacaoOrigem();
		String idImovel = form.getIdImovel();
		String idPavimentoRua = form.getIdPavimentoRua();
		String idPavimentoCalcada = form.getIdPavimentoCalcada();
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,idPerfilLigacao));
		
		Collection pesquisa = fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
			Imovel imovelConsulta = null;
			
			if(form.getMatriculaImovel() != null && form.getMatriculaImovel() != ""){
				imovelConsulta= fachada.pesquisarImovel(new Integer(form.getMatriculaImovel()));
			}else{
				if(form.getIdImovel() != null && form.getIdImovel() != ""){
					imovelConsulta= fachada.pesquisarImovel(new Integer(form.getIdImovel()));
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
		
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		Imovel imovel = null;
		
		if(idImovel!=null && !idImovel.equalsIgnoreCase("")){
			
			imovel = new Imovel(new Integer(idImovel));
			imovel.setUltimaAlteracao(new Date());
			ligacaoAgua.setImovel(imovel);
			
			if (form.getDataLigacao() != null && !form.getDataLigacao().equals("")) {
				
				Date data = Util.converteStringParaDate(form.getDataLigacao());
				ligacaoAgua.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo",null, " Data da Ligação");
			}
			
			if (isIdValido(diametroLigacao)) {
				LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro(new Integer(diametroLigacao));
				ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
				
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null, "Diametro da Ligação");
			}

			if (isIdValido(materialLigacao)){
				
				LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial(new Integer(materialLigacao));
				ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null, "Material da Ligação");
			}

			if (isIdValido(idPerfilLigacao)) {
				LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil(new Integer(idPerfilLigacao));
				ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
				
			} else {
				throw new ActionServletException("atencao.informe_campo_obrigatorio", null, "Perfil da Ligação");
			}
			
			if (isIdValido(ramalLocalInstalacao)) {
				RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao(new Integer(ramalLocalInstalacao));
				ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
			}

			if(isIdValido(idLigacaoOrigem)){
				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem(new Integer(idLigacaoOrigem));
				ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
			}
			
			if(numeroLacre !=null && !numeroLacre.equals("")){
				ligacaoAgua.setNumeroLacre(numeroLacre);
			}
			
			String qtdParcelas = form.getQuantidadeParcelas();
			
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
				
				if (form.getDataLigacao() != null && !form.getDataLigacao().equals("")) {
					
					Date data = Util.converteStringParaDate(form.getDataLigacao());
					ligacaoAgua.setDataLigacao(data);
				} else {
					throw new ActionServletException("atencao.informe_campo",null, " Data da Ligação");
				}
				
				if (isIdValido(diametroLigacao)) {
					
					LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro(new Integer(diametroLigacao));
					ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
					
				} else {
					throw new ActionServletException("atencao.informe_campo_obrigatorio", null, "Diametro da Ligação");
				}

				if (isIdValido(materialLigacao)){
					LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial(new Integer(materialLigacao));
					ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
				} else {
					throw new ActionServletException("atencao.informe_campo_obrigatorio", null, "Material da Ligação");
				}

				if (isIdValido(idPerfilLigacao)) {
					LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil(new Integer(idPerfilLigacao));
					ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
					
				} else {
					throw new ActionServletException("atencao.informe_campo_obrigatorio", null, "Perfil da Ligação");
				}
				
				if (isIdValido(ramalLocalInstalacao)) {
					RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao(new Integer(ramalLocalInstalacao));
					ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
				}

				if(isIdValido(idLigacaoOrigem)){
					LigacaoOrigem ligacaoOrigem = new LigacaoOrigem(new Integer(idLigacaoOrigem));
					ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
				}
				
				if(isIdValido(idPavimentoRua)){
					PavimentoRua pavimentoRua = new PavimentoRua(new Integer(idPavimentoRua));
					ligacaoAgua.setPavimentoRua(pavimentoRua);
				}
				
				if(isIdValido(idPavimentoCalcada)){
					PavimentoCalcada pavimentoCalcada = new PavimentoCalcada(new Integer(idPavimentoCalcada));
					ligacaoAgua.setPavimentoCalcada(pavimentoCalcada);
				}
				
				
				if(isIdValido(idLigacaoOrigem)){
					LigacaoOrigem ligacaoOrigem = new LigacaoOrigem(new Integer(idLigacaoOrigem));
					ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
				}
				
				if (form.getProfundidadeRamal() != null && form.getProfundidadeRamal() != "" )
					ligacaoAgua.setProfundidadeRamal(new BigDecimal(form.getProfundidadeRamal()));
				
				if (form.getDistanciaInstalacaoRamal() != null && form.getDistanciaInstalacaoRamal() != "")
					ligacaoAgua.setDistanciaInstalacaoRamal(new BigDecimal(form.getDistanciaInstalacaoRamal()));

				if(numeroLacre !=null && !numeroLacre.equals("")){
					ligacaoAgua.setNumeroLacre(numeroLacre);
				}
				
				if(ordemServico != null && form.getIdTipoDebito() != null){
					
					ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
					
					ordemServico.setIndicadorComercialAtualizado(new Short("1"));
					
					if(isIdValido(idServicoMotivoNaoCobranca)){
						servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
						servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
					}
					
					ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
					
					if(valorPercentual != null){
						ordemServico.setPercentualCobranca(new BigDecimal(form.getPercentualCobranca()));
					}
					
					ordemServico.setUltimaAlteracao(new Date());
				}

				BigDecimal valorAtual = new BigDecimal(0);
				if (form.getValorDebito() != null) {
				    
					String valorDebito = form.getValorDebito().toString().replace(".", "");
				    
				    valorDebito = valorDebito.replace(",", ".");
				    valorAtual = new BigDecimal(valorDebito);

				    ordemServico.setValorAtual(valorAtual);
				}
				
				String qtdParcelas = form.getQuantidadeParcelas();
				
				IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
				
				integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
				integracaoComercialHelper.setImovel(imovel);
				integracaoComercialHelper.setOrdemServico(ordemServico);
				integracaoComercialHelper.setQtdParcelas(qtdParcelas);
				integracaoComercialHelper.setUsuarioLogado(usuario);
				
				if(form.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
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
	
	private boolean isIdValido(String idCampo) {
		return idCampo != null && !idCampo.equals("") &&!idCampo.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO); 
	}
}

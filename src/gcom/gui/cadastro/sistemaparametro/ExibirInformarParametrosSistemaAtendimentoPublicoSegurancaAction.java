package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

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
 * @date 10/01/2007
 */
public class ExibirInformarParametrosSistemaAtendimentoPublicoSegurancaAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = 
			actionMapping.findForward("exibirInformarParametrosSistemaAtendimentoPublicoSeguranca");

		HttpSession sessao = this.getSessao(httpServletRequest);
		
		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		SistemaParametro sistemaParametro = 
			(SistemaParametro) sessao.getAttribute("sistemaParametro");

		if (sistemaParametro.getIndicadorSugestaoTramite() != null) {
			form.setIndicadorSugestaoTramite(sistemaParametro.getIndicadorSugestaoTramite().toString());
		}

		if (sistemaParametro.getDiasReativacao() != null) {
			form.setDiasMaximoReativarRA(sistemaParametro.getDiasReativacao().toString());
		}

		if (sistemaParametro.getDiasMaximoAlterarOS() != null) {
			form.setDiasMaximoAlterarOS(sistemaParametro.getDiasMaximoAlterarOS().toString());
		}
		
		if (sistemaParametro.getNumeroDiasEncerramentoOrdemServico() != null) {
			form.setNumeroDiasEncerramentoOrdemServico(sistemaParametro.getNumeroDiasEncerramentoOrdemServico().toString());
		}
		
		if (sistemaParametro.getNumeroDiasEncerramentoOSSeletiva() != null) {
			form.setNumeroDiasEncerramentoOSSeletiva(sistemaParametro.getNumeroDiasEncerramentoOSSeletiva().toString());
		}

		if (sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null) {
			form.setNumeroDiasAlteracaoVencimentoPosterior(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().toString());
		}

		if (sistemaParametro.getUltimoRAManual() != null) {
			form.setUltimoIDGeracaoRA(sistemaParametro.getUltimoRAManual().toString());
		}
		
		if (sistemaParametro.getNumeroDiasExpiracaoAcesso() != null) {
			form.setDiasMaximoExpirarAcesso(sistemaParametro.getNumeroDiasExpiracaoAcesso().toString());
		}

		if (sistemaParametro.getNumeroDiasMensagemExpiracao() != null) {
			form.setDiasMensagemExpiracaoSenha(sistemaParametro.getNumeroDiasMensagemExpiracao().toString());
		}

		if (sistemaParametro.getNumeroMaximoLoginFalho() != null) {
			form.setNumeroMaximoTentativasAcesso(sistemaParametro.getNumeroMaximoLoginFalho().toString());
		}

		if (sistemaParametro.getNumeroMaximoFavorito() != null) {
			form.setNumeroMaximoFavoritosMenu(sistemaParametro.getNumeroMaximoFavorito().toString());
		}

		if (sistemaParametro.getIpServidorSmtp() != null) {
			form.setIpServidorSmtp(sistemaParametro.getIpServidorSmtp());
		}

		if (sistemaParametro.getIpServidorModuloGerencial() != null) {
			form.setIpServidorGerencial(sistemaParametro.getIpServidorModuloGerencial());
		}
		
		if (sistemaParametro.getUrlhelp() != null) {
			form.setUrlHelp(sistemaParametro.getUrlhelp());
		}

		if (sistemaParametro.getDsEmailResponsavel() != null) {
			form.setEmailResponsavel(sistemaParametro.getDsEmailResponsavel());
		}
		
		
		if (sistemaParametro.getMensagemSistema() != null) {
			form.setMensagemSistema(sistemaParametro.getMensagemSistema());
		}
		
		if(sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos() != null){
			form.setDiasVencimentoCertidaoNegativa(""+ sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
		}
		
		if(sistemaParametro.getIndicadorControleTramitacaoRA() != null){
			form.setIndicadorControleTramitacaoRA(""+ sistemaParametro.getIndicadorControleTramitacaoRA());
		}
		
		if(sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis() != null){
			form.setIndicadorCalculoPrevisaoRADiasUteis(""+ sistemaParametro.getIndicadorCalculoPrevisaoRADiasUteis());
		}
		
		if(sistemaParametro.getIndicadorDocumentoValido() != null){
			form.setIndicadorDocumentoValido(""+ sistemaParametro.getIndicadorDocumentoValido());
		}
		
		if (sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS() != null ) {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS("" + sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS() );
		} else {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS( "" + ConstantesSistema.NAO ) ;
		}
		
		form.setIndicadorCertidaoNegativaEfeitoPositivo("" + sistemaParametro.getIndicadorCertidaoNegativaEfeitoPositivo());

		form.setIndicadorDebitoACobrarValidoCertidaoNegativa("" + sistemaParametro.getIndicadorDebitoACobrarValidoCertidaoNegativa());
		
		form.setIndicadorLoginUnico("" + sistemaParametro.getIndicadorLoginUnico());
		
		if(sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo()!=null){
			form.setIndicarControleExpiracaoSenhaPorGrupo(sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo().toString());
		}
		if(sistemaParametro.getIndicadorControleBloqueioSenhaAnterior()!=null){
			form.setIndicarControleBloqueioSenha(sistemaParametro.getIndicadorControleBloqueioSenhaAnterior().toString());
		}
		if(sistemaParametro.getIndicadorSenhaForte()!=null){
			form.setIndicadorSenhaForte(sistemaParametro.getIndicadorSenhaForte().toString());
		}
		
		
		if(sistemaParametro.getUnidadeOrganizacionalTramiteGrandeConsumidor()!=null){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = 
				new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID,
							sistemaParametro.getUnidadeOrganizacionalTramiteGrandeConsumidor().getId()));
			
			Collection<UnidadeOrganizacional> colecao = 
				this.getFachada().pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());
		
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecao);	
			
			form.setIdUnidadeDestinoGrandeConsumidor(unidade.getId().toString());
			form.setNomeUnidadeDestinoGrandeConsumidor(unidade.getDescricao());
			
			httpServletRequest.setAttribute("idUnidadeDestinoGrandeConsumidor", unidade.getId().toString());
			httpServletRequest.setAttribute("idUnidadeEncontrada","true");
		}
		
		
		String pesquisaUnidade = httpServletRequest.getParameter("pesquisaUnidade");
		String id = httpServletRequest.getParameter("id");
		
		if(pesquisaUnidade!=null && pesquisaUnidade.toString().equals("sim")
				&& id!=null && !id.equals("")){
			this.pesquisarUnidadeOrganizacional(new Integer(id), form, httpServletRequest);
		}
	
		if(sistemaParametro.getNumeroDiasRevisaoComPermEspecial() != null){
			form.setNumeroDiasRevisaoConta(sistemaParametro.getNumeroDiasRevisaoComPermEspecial().toString());
		}

		if(sistemaParametro.getUltimoDiaVencimentoAlternativo() != null){
			form.setUltimoDiaVencimentoAlternativo(sistemaParametro.getUltimoDiaVencimentoAlternativo().toString());
		}
		
		if(sistemaParametro.getQtdeDiasValidadeOSFiscalizacao() != null){
			form.setQtdeDiasValidadeOSFiscalizacao(sistemaParametro.getQtdeDiasValidadeOSFiscalizacao().toString());
		}
		
		if(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null){
			form.setQtdeDiasEncerraOSFiscalizacao(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao().toString());
		}
		
		if(sistemaParametro.getQtdeDiasEnvioEmailConta() != null){
			form.setQtdeDiasEnvioEmailConta(sistemaParametro.getQtdeDiasEnvioEmailConta().toString());
		}
		
		if(sistemaParametro.getDescricaoDecreto() != null){
			form.setDescricaoDecreto(sistemaParametro.getDescricaoDecreto().toString());
		}
		
		if(sistemaParametro.getDescricaoLeiEstTarif() != null){
			form.setDescricaoLeiEstTarif(sistemaParametro.getDescricaoLeiEstTarif().toString());
		}
		
		if(sistemaParametro.getDescricaoLeiIndividualizacao() != null){
			form.setDescricaoLeiIndividualizacao(sistemaParametro.getDescricaoLeiIndividualizacao().toString());
		}
		
		if(sistemaParametro.getDescricaoNormaCM() != null){
			form.setDescricaoNormaCM(sistemaParametro.getDescricaoNormaCM().toString());
		}
		
		if(sistemaParametro.getDescricaoNormaCO() != null){
			form.setDescricaoNormaCO(sistemaParametro.getDescricaoNormaCO().toString());
		}
		
		return retorno;

	}
	
	/**
	 * Hugo Amorim
	 * 
	 * Pesquisa Unidade Organizacional
	 */
	public void pesquisarUnidadeOrganizacional(Integer id,
			InformarSistemaParametrosActionForm form,
			HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

		filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, id));

		Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = fachada
				.pesquisar(filtroUnidadeEmpresa, UnidadeOrganizacional.class
						.getName());

		if (unidadeEmpresaEncontrada != null
				&& !unidadeEmpresaEncontrada.isEmpty()) {
			form.setIdUnidadeDestinoGrandeConsumidor(""
							+ ((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada)
									.get(0)).getId());
			form.setNomeUnidadeDestinoGrandeConsumidor(((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada)
							.get(0)).getDescricao());
			httpServletRequest.setAttribute("idUnidadeDestinoGrandeConsumidor", id);
			httpServletRequest.setAttribute("idUnidadeEncontrada","true");
			httpServletRequest.setAttribute("nomeCampo", "idUnidade");

		} else {
			form.setIdUnidadeDestinoGrandeConsumidor("");
			httpServletRequest.removeAttribute("idUnidadeEncontrada");
			httpServletRequest.removeAttribute("idUnidadeDestinoGrandeConsumidor");
			form.setNomeUnidadeDestinoGrandeConsumidor("Unidade  Organizacional Inexistente");
			httpServletRequest.setAttribute("nomeCampo", "idUnidade");
		}
	}
}

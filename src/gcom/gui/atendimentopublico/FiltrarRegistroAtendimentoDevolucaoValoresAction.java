package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRegistroAtendimentoDevolucaoValoresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterRegistroAtendimentoDevolucaoValores");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRegistroAtendimentoDevolucaoValoresActionForm filtrarGuiaDevolucaoActionForm = (FiltrarRegistroAtendimentoDevolucaoValoresActionForm) actionForm;

		// Recupera os parâmetros do form
		String[] idPerfilImovel = filtrarGuiaDevolucaoActionForm.getPerfilImovel();
		String dataAtendimentoInicio = filtrarGuiaDevolucaoActionForm.getDataAtendimentoInicio();
		String dataAtendimentoFim = filtrarGuiaDevolucaoActionForm.getDataAtendimentoFim();

		boolean peloMenosUmParametroInformado = false;
		Date dataAtendimentoInicioFormatada = null;
		Date dataAtendimentoFimFormatada = null;
		// Período Data Emissão
		if ((dataAtendimentoInicio != null && !dataAtendimentoInicio.equals(""))
			|| (dataAtendimentoFim != null && !dataAtendimentoFim.equals(""))) {
			
			peloMenosUmParametroInformado = true;

			if (dataAtendimentoInicio != null && !dataAtendimentoInicio.trim().equals("")) {
				dataAtendimentoInicioFormatada = Util.converteStringParaDate(dataAtendimentoInicio);
			}

			if (dataAtendimentoFim != null && !dataAtendimentoFim.trim().equals("")) {
				dataAtendimentoFimFormatada = Util.converteStringParaDate(dataAtendimentoFim);
			}

			if(dataAtendimentoInicioFormatada != null && dataAtendimentoFimFormatada != null &&
					dataAtendimentoInicioFormatada.compareTo(dataAtendimentoFimFormatada) == 1){
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}	

		}

		Integer numeroRA = null;
		if(filtrarGuiaDevolucaoActionForm.getNumeroRA() != null && !filtrarGuiaDevolucaoActionForm.getNumeroRA().equals("")){
			numeroRA = new Integer(filtrarGuiaDevolucaoActionForm.getNumeroRA());
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, numeroRA));
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFIC);
			
			Collection colecaoRegistroAtendimento = fachada
			.pesquisar(filtroRegistroAtendimento,RegistroAtendimento.class.getName());
			
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);

			if(registroAtendimento == null){
				//[FS0003] - Verificar existência do Registro de atendimento
				//Caso o registro de atendimento informado não exista na tabela REGISTRO_ATENDIMENTO, 
				//exibir a mensagem “Registro de atendimento inexistente”
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Registro de Atendimento");
			}
		
			if(registroAtendimento.getCodigoSituacao() != RegistroAtendimento.SITUACAO_PENDENTE.shortValue()){
				//[FS0004] - Verificar situação do ra informado
				//Caso a situação do ra informado diferente de ‘PENDENTE’ na tabela REGISTRO_ATENDIMENTO, 
				//exibir a mensagem “Situação do registro de atendimento deve ser pendente para devolução de pagamentos”
				throw new ActionServletException("atencao.situacao.ra.deve.ser.pendente");
			}
			
			if(registroAtendimento.getSolicitacaoTipoEspecificacao() != null && 
				registroAtendimento.getSolicitacaoTipoEspecificacao().getIndicadorInformarPagamentoDuplicidade().equals(ConstantesSistema.NAO)){
				//[FS0005] - Verificar tipo do ra informado
				//Caso o indicador  (IC_INFORMAPAGTODUPLICIDADE) = 2 da tabela SOLICITACAO_TIPO_ESPEC 
				//com STEP_ID = STEP_ID da tabela REGISTRO_ATENDIMENTO com o RGAT_ID do ra informado) 
				//exibir a mensagem “Registro de Atendimento não corresponde a uma solicitação de devolução de pagamento”
				
				throw new ActionServletException("atencao.ra.nao.corresponde.devolucao.pagamento");
			}
			peloMenosUmParametroInformado = true;	
		}
		
		Integer idImovel = null;
		if (filtrarGuiaDevolucaoActionForm.getIdImovelHidden() != null && !filtrarGuiaDevolucaoActionForm.getIdImovelHidden().equals("")) {
			idImovel = new Integer(filtrarGuiaDevolucaoActionForm.getIdImovelHidden());
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if(imoveis == null || imoveis.isEmpty()){
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Imóvel");
			}
			peloMenosUmParametroInformado = true;
		}	
		
		if (idPerfilImovel != null) {
			peloMenosUmParametroInformado = true;
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		RegistroAtendimentoDevolucaoValoresHelper helper = new RegistroAtendimentoDevolucaoValoresHelper();
		helper.setIdImovel(idImovel);
		helper.setIdPerfilImovel(idPerfilImovel);
		helper.setDataAtendimentoInicioFormatada(dataAtendimentoInicioFormatada);
		helper.setDataAtendimentoFimFormatada(dataAtendimentoFimFormatada);
		helper.setNumeroRA(numeroRA);
		
		sessao.setAttribute("registroAtendimentoDevolucaoValoresHelper",helper);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}

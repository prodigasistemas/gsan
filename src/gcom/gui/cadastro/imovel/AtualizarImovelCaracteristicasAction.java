package gcom.gui.cadastro.imovel;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarImovelCaracteristicasAction extends GcomAction {
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
    @SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
        
        //instanciando o ActionForm de InserirImovelLocalidadeActionForm
        DynaValidatorForm inserirImovelCaracteristicasActionForm = (DynaValidatorForm) actionForm;
        Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizacao");
        
        String areaConstruida = (String) inserirImovelCaracteristicasActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelCaracteristicasActionForm.get("faixaAreaConstruida");
        String reservatorioInferior = (String) inserirImovelCaracteristicasActionForm.get("reservatorioInferior");
        String reservatorioSuperior = (String) inserirImovelCaracteristicasActionForm.get("reservatorioSuperior");
        String piscina = (String) inserirImovelCaracteristicasActionForm.get("piscina");
		String pavimentoCalcada = (String) inserirImovelCaracteristicasActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelCaracteristicasActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelCaracteristicasActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelCaracteristicasActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelCaracteristicasActionForm.get("situacaoLigacaoEsgoto");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelCaracteristicasActionForm.get("idLigacaoEsgotoEsgotamento");
		String perfilImovel = (String) inserirImovelCaracteristicasActionForm.get("perfilImovel");
		
		String idSetorComercial = (String) inserirImovelCaracteristicasActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelCaracteristicasActionForm.get("idQuadra");
		
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelCaracteristicasActionForm.get("indicadorNivelInstalacaoEsgoto");

        Fachada fachada = Fachada.getInstancia();
        
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
		helperCaracteristica.setImovelAtualizar(imovelAtualizar);
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setVolumeReservatorioInferior(reservatorioInferior);
		helperCaracteristica.setVolumeReservatorioSuperior(reservatorioSuperior);
		helperCaracteristica.setVolumePiscinaMovel(piscina);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
		
		

		//*************************************************
		// Autor: Ivan Sergio
		// Data: 23/04/2009
		// CRC1657
		//*************************************************
		// [FS0023] - Verificar Setor e Quadra
		//*************************************************
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		//*************************************************
	
		// Autor: Nathalia Santos 
		// Data: 12/07/2011
		// RR201106690 - Verifica se Existe nível para instalação de esgoto caso a empresa seja CAER.
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		//*****************************************************
		
		
		fachada.validarAtualizarImovelAbaCaracteristicas(imovelAtualizar.getId(), getClienteResponsavel(clientes), new Integer(perfilImovel));
		
		ImovelAbaCaracteristicasRetornoHelper resultado = fachada.validarImovelAbaCaracteristicas(helperCaracteristica);

		if (resultado.getAreaConstruidaFaixa() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaAreaConstruida", resultado.getAreaConstruidaFaixa().getId() + "");
		}
		if (resultado.getReservatorioVolumeFaixaInferior() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaReservatorioInferior", resultado.getReservatorioVolumeFaixaInferior().getId() + "");
		}
		if (resultado.getReservatorioVolumeFaixaSuperior() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaResevatorioSuperior", resultado.getReservatorioVolumeFaixaSuperior().getId() + "");
		}
		if (resultado.getPiscinaVolumeFaixa() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaPiscina", resultado.getPiscinaVolumeFaixa().getId() + "");
		} 


        return retorno;
    }
    
    private ClienteImovel getClienteResponsavel(Collection<ClienteImovel> clientes) {
    	
    	for (ClienteImovel clienteImovel : clientes) {
    		if (clienteImovel.isClienteResponsavel()) {
    			return clienteImovel;
    		}
    	}
    	
    	return null;
    }

}

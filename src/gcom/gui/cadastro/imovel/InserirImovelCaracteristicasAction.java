package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirImovelCaracteristicasAction extends GcomAction {
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
                .findForward("gerenciadorProcesso");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //instanciando o ActionForm de InserirImovelLocalidadeActionForm
        DynaValidatorForm inserirImovelCaracteristicasActionForm = (DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");

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
        
        sessao.removeAttribute("gis");
        
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
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
		
		// ****************************************************
		// Autor: Nathalia Santos 
		// Data: 12/07/2011
		// RR201106690 - Verifica se Existe nível para instalação de esgoto caso a empresa seja CAER.
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		//*****************************************************
		
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
		
//        FiltroAreaConstruidaFaixa filtroAreaConstruida = new FiltroAreaConstruidaFaixa();
//        FiltroReservatorioVolumeFaixa filtroReservatorioVolumeFaixa = new FiltroReservatorioVolumeFaixa();
//        FiltroPiscinaVolumeFaixa filtroPiscina = new FiltroPiscinaVolumeFaixa();
//
//        if (areaConstruida != null
//                && !areaConstruida.trim().equalsIgnoreCase("")) {
//            filtroAreaConstruida.adicionarParametro(new MenorQue(
//                    FiltroAreaConstruidaFaixa.MENOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(areaConstruida)));
//            filtroAreaConstruida.adicionarParametro(new MaiorQue(
//                    FiltroAreaConstruidaFaixa.MAIOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(areaConstruida)));
//
//            Collection areaConstruidas = fachada.pesquisar(
//                    filtroAreaConstruida, AreaConstruidaFaixa.class.getName());
//
//            if (areaConstruidas != null && !areaConstruidas.isEmpty()) {
//            	AreaConstruidaFaixa areaConstruidaFaixa = (AreaConstruidaFaixa) ((List) areaConstruidas).get(0);
//                inserirImovelCaracteristicasActionForm.set(
//                        "faixaAreaConstruida", areaConstruidaFaixa.getId().toString());
//            }
//        }
//
//        if (reservatorioInferior != null
//                && !reservatorioInferior.trim().equalsIgnoreCase("")) {
//        	filtroReservatorioVolumeFaixa = new FiltroReservatorioVolumeFaixa();
//            filtroReservatorioVolumeFaixa.adicionarParametro(new MenorQue(
//                    FiltroReservatorioVolumeFaixa.VOLUME_MENOR_FAIXA,
//                    Util.formatarMoedaRealparaBigDecimal(reservatorioInferior)));
//            filtroReservatorioVolumeFaixa.adicionarParametro(new MaiorQue(
//                    FiltroReservatorioVolumeFaixa.VOLUME_MAIOR_FAIXA,
//                    Util.formatarMoedaRealparaBigDecimal(reservatorioInferior)));
//
//            Collection reservatorioInferiores = fachada.pesquisar(
//                    filtroReservatorioVolumeFaixa,
//                    ReservatorioVolumeFaixa.class.getName());
//
//            if (reservatorioInferiores != null
//                    && !reservatorioInferiores.isEmpty()) {
//                inserirImovelCaracteristicasActionForm
//                        .set(
//                                "faixaReservatorioInferior",
//                                ((ReservatorioVolumeFaixa) ((List) reservatorioInferiores)
//                                        .get(0)).getId().toString());
//            }
//        }
//
//        if (reservatorioSuperior != null
//                && !reservatorioSuperior.trim().equalsIgnoreCase("")) {
//        	filtroReservatorioVolumeFaixa = new FiltroReservatorioVolumeFaixa();
//            filtroReservatorioVolumeFaixa.adicionarParametro(new MenorQue(
//                    FiltroReservatorioVolumeFaixa.VOLUME_MENOR_FAIXA,
//                    Util.formatarMoedaRealparaBigDecimal(reservatorioSuperior)));
//            filtroReservatorioVolumeFaixa.adicionarParametro(new MaiorQue(
//                    FiltroReservatorioVolumeFaixa.VOLUME_MAIOR_FAIXA,
//                    Util.formatarMoedaRealparaBigDecimal(reservatorioSuperior)));
//
//            Collection reservatorioSuperiores = fachada.pesquisar(
//                    filtroReservatorioVolumeFaixa,
//                    ReservatorioVolumeFaixa.class.getName());
//
//            if (reservatorioSuperiores != null
//                    && !reservatorioSuperiores.isEmpty()) {
//                inserirImovelCaracteristicasActionForm
//                        .set(
//                                "faixaResevatorioSuperior",
//                                ((ReservatorioVolumeFaixa) ((List) reservatorioSuperiores)
//                                        .get(0)).getId().toString());
//            }
//        }
//
//        if (piscina != null && !piscina.trim().equalsIgnoreCase("")) {
//            filtroPiscina.adicionarParametro(new MenorQue(
//                    FiltroPiscinaVolumeFaixa.VOLUME_MENOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(piscina)));
//            filtroPiscina.adicionarParametro(new MaiorQue(
//                    FiltroPiscinaVolumeFaixa.VOLUME_MAIOR_FAIXA, Util.formatarMoedaRealparaBigDecimal(piscina)));
//
//            Collection piscinas = fachada.pesquisar(filtroPiscina,
//                    PiscinaVolumeFaixa.class.getName());
//
//            if (piscinas != null && !piscinas.isEmpty()) {
//                inserirImovelCaracteristicasActionForm.set("faixaPiscina",
//                        ((PiscinaVolumeFaixa) ((List) piscinas).get(0))
//                                .getId().toString());
//            }
//        }

        //HttpServletRequest request = httpServletRequest;

        return retorno;
    }

}

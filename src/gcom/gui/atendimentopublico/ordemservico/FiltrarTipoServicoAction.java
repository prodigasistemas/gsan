package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarTipoServicoAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroTipoServico");

		FiltrarTipoServicoActionForm form = (FiltrarTipoServicoActionForm) actionForm;
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Variaveis
		String indicadorAtualizar = httpServletRequest
		.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar == null) {
			form.setIndicadorAtualizar("2");
		} else {
			form.setIndicadorAtualizar(indicadorAtualizar);
		}
		
		//recupera dados do form
		String idTipoServico = form.getIdTipoServico();
        String descricao = form.getDescricaoTipoServico();
        String abreviada = form.getAbreviada();
        String subgrupo = form.getSubgrupo();
        String valorInicial = form.getValorInicial();
        String valorFinal = form.getValorFinal();
        //substituido por indicador de rua e calcada
        //String pavimento = form.getPavimento();
        String atualizacaoComercial = form.getAtualizacaoComercial();
        String servicoTerceirizado = form.getServicoTerceirizado();
        String indicadorFiscalizacaoInfracao = form.getIndicadorFiscalizacaoInfracao();
        String indicadorVistoria = form.getIndicadorVistoria();
        String codigoServico = form.getCodigoServico();
        String tempoMedioIncial = form.getTempoMedioIncial();
        String tempoMedioFinal = form.getTempoMedioFinal();
        String idTipoDebito = form.getIdTipoDebito();
        String idTipoCredito = form.getIdTipoCredito();
        String idPrioridadeServico = form.getIdPrioridadeServico();
        String perfilServico = form.getPerfilServico();
        //String idTipoServicoReferencia = form.getIdTipoServicoReferencia();
        //String indicadorAtividadeUnica = form.getIndicadorAtividadeUnica();
        String indicadorUso = form.getIndicadorUso();
        /*String idAtividadeTipoServico = form.getIdAtividadeTipoServico();
        String idMaterialTipoServico = form.getIdMaterialTipoServico();*/
        
        String indicadorPavimentoRua = form.getIndicadorPavimentoRua();
        String indicadorPavimentoCalcada = form.getIndicadorPavimentoCalcada();
        
        String indicadorEmpresaCobranca = form.getIndicadorEmpresaCobranca();
        
        String indicadorInspecaoAnormalidade = form.getIndicadorInspecaoAnormalidade();
        
        String indicadorProgramacaoAutomatica = form.getIndicadorProgramacaoAutomatica();
        
        String indicativoTipoSevicoEconomias = form.getIndicativoTipoSevicoEconomias();
        
        String indicadorEncAutomaticoRAQndEncOS = form.getIndicadorEncAutomaticoRAQndEncOS();
        
        
        FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

        boolean peloMenosUmParametroInformado = false;
        
        //descricao
        if(idTipoServico != null && !idTipoServico.equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, idTipoServico));
        }
        //descricao
        if(descricao != null && !descricao.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ComparacaoTexto(FiltroTipoServico.DESCRICAO, descricao));
        }
        //descricao abreviada
        if(abreviada != null && !abreviada.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ComparacaoTexto(FiltroTipoServico.ABREVIADA, abreviada));
        }
        //subgrupo
        if(subgrupo != null && !subgrupo.trim().equals("") && !subgrupo.trim().equals("-1")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.SUBGRUPO, subgrupo));
        }
        //valor
        if(valorInicial != null && !valorInicial.trim().equals("")
        		&& valorFinal != null && !valorFinal.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new Intervalo(FiltroTipoServico.VALOR, valorInicial, valorFinal));
        }else if(valorInicial != null && !valorInicial.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new MaiorQue(FiltroTipoServico.VALOR, valorInicial));
        }else if(valorFinal != null && !valorFinal.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new MenorQue(FiltroTipoServico.VALOR, valorFinal));
        }
        
        //pavimento
/*        if(pavimento != null && !pavimento.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_PAVIMENTO, pavimento));
        }
*/        
        
        //indicador de pavimento de rua
        if(indicadorPavimentoRua != null && !indicadorPavimentoRua.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_PAVIMENTO_RUA, indicadorPavimentoRua));
        }
        
        //indicador de pavimento de calçada
        if(indicadorPavimentoCalcada != null && !indicadorPavimentoCalcada.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_PAVIMENTO_CALCADA, indicadorPavimentoCalcada));
        }
        
        // indicador Empresa Cobranca
        if(indicadorEmpresaCobranca != null && !indicadorEmpresaCobranca.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_EMPRESA_COBRANCA, indicadorEmpresaCobranca));
        }
        
        //indicador Inspeção Anormalidade
        if(indicadorInspecaoAnormalidade != null && !indicadorInspecaoAnormalidade.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_INSPECAO_ANORMALIDADE, indicadorInspecaoAnormalidade));
        }
        
        //indicador Programação Automática
        if(indicadorProgramacaoAutomatica != null && !indicadorProgramacaoAutomatica.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_PROGRAMACAO_AUTOMATICA, indicadorProgramacaoAutomatica));
        }
        
        //indicador do Tipo de Serviço por Economias
        if(indicativoTipoSevicoEconomias != null && !indicativoTipoSevicoEconomias.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_CONSIDERA_ECONOMIAS, indicativoTipoSevicoEconomias));
        }

        //Atualizacao comercial
        if(atualizacaoComercial != null && !atualizacaoComercial.trim().equals("0")){
        	peloMenosUmParametroInformado = true;
        	if(atualizacaoComercial.equals("1")){
        		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_ATUALIZACAO_COMERCIAL, "1", ParametroSimples.CONECTOR_OR, 2));
        		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_ATUALIZACAO_COMERCIAL, "3"));
        	}else if (atualizacaoComercial.equals("2")){
        		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_ATUALIZACAO_COMERCIAL, "1"));        		
        	}else if (atualizacaoComercial.equals("3")){
        		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_ATUALIZACAO_COMERCIAL, "3"));        		
        	}else if (atualizacaoComercial.equals("5")){
        		filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_ATUALIZACAO_COMERCIAL, "2"));        		
        	}
        }
        //servico Terceirizado
        if(servicoTerceirizado != null && !servicoTerceirizado.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_TERCEIRIZADO, servicoTerceirizado));
        }
        //indicadorFiscalizacaoInfracao
        if(indicadorFiscalizacaoInfracao != null && !indicadorFiscalizacaoInfracao.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_FISCALIZACAO_INFRACAO, indicadorFiscalizacaoInfracao));
        }
        //indicadorVistoria
        if(indicadorVistoria != null && !indicadorVistoria.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_VISTORIA, indicadorVistoria));
        }
        //codigoServico
        if(codigoServico != null && !codigoServico.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.CODIGO_TIPO_SERVICO, codigoServico));
        }
        //Tempo Medio
        if(tempoMedioIncial != null && !tempoMedioIncial.trim().equals("")
        		&& tempoMedioFinal != null && !tempoMedioFinal.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new Intervalo(FiltroTipoServico.TEMPO_MEDIO_ESECUCAO, tempoMedioIncial, tempoMedioFinal));
        }else if(tempoMedioIncial != null && !tempoMedioIncial.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new MaiorQue(FiltroTipoServico.TEMPO_MEDIO_ESECUCAO, tempoMedioIncial));
        }else if(valorFinal != null && !valorFinal.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new MenorQue(FiltroTipoServico.TEMPO_MEDIO_ESECUCAO, tempoMedioFinal));
        }
        //idTipoDebito
        if(idTipoDebito != null && !idTipoDebito.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.DEBITO_TIPO_ID, idTipoDebito));
        }
        //idTipoCredito
        if(idTipoCredito != null && !idTipoCredito.trim().equals("") && !idTipoCredito.trim().equals("-1")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.CREDITO_TIPO_ID, idTipoCredito));
        }
        //idPrioridadeServico
        if(idPrioridadeServico != null && !idPrioridadeServico.trim().equals("") && !idPrioridadeServico.trim().equals("-1")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.PRIORIDADE_SERVICO, idPrioridadeServico));
        }
        //perfilServico
        if(perfilServico != null && !perfilServico.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.PERFIL_TIPO, perfilServico));
        }
        //indicador de Encerramento Automatico RA quando Encerrar OS
        if(indicadorEncAutomaticoRAQndEncOS != null && !indicadorEncAutomaticoRAQndEncOS.equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(
        			new ParametroSimples(FiltroTipoServico.INDICADOR_ENCERRAMENTO_AUTOMATICO_RA_QUANDO_ENCERRAR_ULTIMA_OS, 
        					indicadorEncAutomaticoRAQndEncOS));
        }
        //indicadorUso
        if(indicadorUso != null && !indicadorUso.trim().equals("")){
        	peloMenosUmParametroInformado = true;
        	filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.INDICADOR_USO, indicadorUso));
        }
        
        //grauImportancia
        FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem(
				FiltroOSProgramaCalibragem.NUMERO_GRAU_IMPORTANCIA);
		filtroOSProgramaCalibragem.setConsultaSemLimites(true);
		filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(
				FiltroOSProgramaCalibragem.PRIORIZACAO_TIPO_ID,
				new Integer(6)));
		filtroOSProgramaCalibragem.adicionarCaminhoParaCarregamentoEntidade("priorizacaoTipo");
		Collection osProgramacaoCalibragem = Fachada.getInstancia().pesquisar(
				filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());

		if (osProgramacaoCalibragem != null && !osProgramacaoCalibragem.isEmpty()) {
			sessao.setAttribute("osProgramacaoCalibragem", osProgramacaoCalibragem);
		}
		
		/* if(form.getArrayAtividade() != null 
        		&& !form.getArrayAtividade().isEmpty()){
        	peloMenosUmParametroInformado = true;
        }
        
        if(form.getArrayMateriais() != null 
        		&& !form.getArrayMateriais().isEmpty()){
        	peloMenosUmParametroInformado = true;
        }*/

        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }
        sessao.setAttribute("filtroTipoServico", filtroTipoServico);

		return retorno;
	}

}

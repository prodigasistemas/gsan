package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Inserir Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class InserirResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1133] Inserir Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String method = httpServletRequest.getParameter("method");
		ActionForward retorno = actionMapping.findForward("exibirInserir");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		InserirResolucaoDiretoriaContratoParcelamentoActionForm form = (InserirResolucaoDiretoriaContratoParcelamentoActionForm) actionForm;
			
        //ContratoParcelamentoRD que vai ser cadastrado no sistema
		ContratoParcelamentoRD contratoCadastrar = (ContratoParcelamentoRD) sessao
				.getAttribute("contratoCadastrar");
		if (contratoCadastrar == null) {
			contratoCadastrar = new ContratoParcelamentoRD();
		}

		
		if(method != null && method.equals("concluirInserirRD")){
			
			List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
			
			if (form.getIndicadorParcelamentoJuros() != null
					&& !form.getIndicadorParcelamentoJuros().toString().trim().equals("")
					&& form.getIndicadorParcelamentoJuros().compareTo(ConstantesSistema.SIM) == 0){
				form.setIndicadorInformarParcela(ConstantesSistema.NAO);
			}
			
			String validacao = validarFormulario(form);

			//Verifica a existência de algum ContratoParcelamentoRD com o número informado pelo usuário
			if (form != null && form.getNumero() != null && !"".equals(form.getNumero())){
				ContratoParcelamentoRD contrato = fachada.pesquisarContratoParcelamentoRDPorNumero(form.getNumero());
				if(contrato != null ){
					validacao = "Já existe RD com este número. Informe outro número";
				}
			}
			
			if(validacao.equals("") && validarNumeroMaxParc(parcelas, form) == false){
				validacao = "Parcela informada excede o número máximo de parcelas da RD";
			}
			
			if(!validacao.equals("")){
				carregarFormContratoCadastrar(contratoCadastrar, form);
				Collections.sort(parcelas, new ComparatorParcela());
				sessao.setAttribute("parcelas", parcelas);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
				sessao.setAttribute("atencao", validacao);
				sessao.setAttribute("voltar","document.forms[0].submit();");
				retorno = actionMapping.findForward("exibirError");
			}else{
				
				carregarFormContratoCadastrar(contratoCadastrar, form);
				Collections.sort(parcelas, new ComparatorParcela());
				
				Integer contratoId = (Integer) fachada.inserirContratoParcelamentoResolucaoDiretoria(contratoCadastrar,parcelas,usuarioLogado);
				
				if(contratoId != null && contratoId != 0){
					retorno = actionMapping.findForward("telaSucesso");
					
					montarPaginaSucesso(
							httpServletRequest,
							"Resolução de diretoria para contratos de parcelamento por cliente - "
							+ contratoCadastrar.getNumero() +" - inserida com sucesso",
							"Inserir outra Resolução de diretoria para contratos de parcelamento por cliente",
							"exibirInserirResolucaoDiretoriaContratoParcelamentoAction.do?menu=sim",
							"exibirAtualizarResolucaoDiretoriaContratoParcelamentoAction.do?numeroContratoParcelamentoRD="+ contratoCadastrar.getNumero(),
					"Atualizar Resolução de diretoria para contratos de parcelamento por cliente inserida");
					
				}else{
					throw new ActionServletException("erro.sistema");
				}
				
			}
			
			
		}else if(method != null && method.equals("inserirParcela")){
			
			retorno = actionMapping.findForward("exibirInserir");

			List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
			
			if(parcelas == null){
				parcelas = new ArrayList<QuantidadePrestacoes>();
			}
			
			String parcela = httpServletRequest.getParameter("parcela");
			String taxaJuros = httpServletRequest.getParameter("taxaJuros");
			
			boolean parcelaExistente = false;
			boolean campoInvalido = false;
			if(parcela != null && !parcela.equals("") 
					&& taxaJuros != null && !taxaJuros.equals("")
					&& Util.formatarMoedaRealparaBigDecimal(taxaJuros).compareTo(BigDecimal.ZERO) != 0){
				for (QuantidadePrestacoes prestacoes : parcelas) {
					if(prestacoes.getQtdFaturasParceladas() == Integer.parseInt(parcela)){
						parcelaExistente = true;
					}
				}
			}else{
				campoInvalido = true;
			}
			
			if(parcelaExistente == false && campoInvalido == false){
				QuantidadePrestacoes prestacao = new QuantidadePrestacoes();
				prestacao.setQtdFaturasParceladas(Integer.parseInt(parcela));
				prestacao.setUltimaAlteracao(new Date());
				taxaJuros = taxaJuros.replace(',','.');
				if (taxaJuros.trim().equals("")) {
					taxaJuros = "0";
				}
				BigDecimal taxa = new BigDecimal(taxaJuros);
				
				prestacao.setTaxaJuros(taxa);
				parcelas.add(prestacao);
				
				carregarFormContratoCadastrar(contratoCadastrar, form);
				Collections.sort(parcelas, new ComparatorParcela());
				sessao.setAttribute("parcelas", parcelas);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
			}else{
				carregarFormContratoCadastrar(contratoCadastrar, form);
				
				sessao.setAttribute("parcelas", parcelas);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
				if(parcelaExistente){
					throw new ActionServletException(
							"atencao.parcela_informada_ja_existente");
				}else {
					throw new ActionServletException(
							"atencao.erro_invalido", "Campo Parcelas ou Taxa de Juros");
				}
			}
			
		}else if(method != null && method.equals("removerParcela")){
			
			retorno = actionMapping.findForward("exibirInserir");

			List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
			
			String parcela = httpServletRequest.getParameter("parcela");
			
			if(parcela != null && !parcela.equals("")){
				for (int i = 0; i < parcelas.size(); i++) {
					if(parcelas.get(i).getQtdFaturasParceladas() == Integer.parseInt(parcela)){
						parcelas.remove(i);
						i = parcelas.size();
					}
				}
			}else{
				throw new ActionServletException("erro.sistema");
			}
			
			carregarFormContratoCadastrar(contratoCadastrar, form);
			Collections.sort(parcelas, new ComparatorParcela());
			sessao.setAttribute("parcelas", parcelas);
			sessao.setAttribute("contratoCadastrar", contratoCadastrar);
			
		} else if(method != null && method.equals("limparColecaoParcelas")) {
			contratoCadastrar.setIndicadorParcelamentoJuros(ConstantesSistema.NAO);
			form.setIndicadorParcelamentoJuros(ConstantesSistema.NAO);
			sessao.removeAttribute("parcelas");
			sessao.setAttribute("contratoCadastrar", contratoCadastrar);
		}

		return retorno;
	}
	
	
	/**Inicio dos metodos Privados**/
	
	private void carregarFormContratoCadastrar(ContratoParcelamentoRD contratoCadastrar, InserirResolucaoDiretoriaContratoParcelamentoActionForm form){
		if (form != null && !"".equals(form.getNumero())){
			contratoCadastrar.setNumero(form.getNumero());
		}
		if (form != null && !"".equals(form.getAssunto())){
			contratoCadastrar.setAssunto(form.getAssunto());
		}
		if (form != null && !"".equals(form.getDataVigenciaInicial())){
			contratoCadastrar.setDataVigenciaInicio(Util.converteStringParaDate(form.getDataVigenciaInicial()));
		}
		if (form != null && !"".equals(form.getDataVigenciaFinal())){
			contratoCadastrar.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
		}
		if (form != null && !"".equals(form.getIndicadorDebitoAcrescimo())){
			contratoCadastrar.setIndicadorDebitoAcrescimo(form.getIndicadorDebitoAcrescimo());
		}
		if (form != null && !"".equals(form.getIndicadorInformarParcela())){
			contratoCadastrar.setIndicadorInformarParcela(form.getIndicadorInformarParcela());
		}
		if (form != null && !"".equals(form.getIndicadorParcelamentoJuros())){
			contratoCadastrar.setIndicadorParcelamentoJuros(form.getIndicadorParcelamentoJuros());
		}
		if (form != null && !"".equals(form.getQtdFaturasParceladas())){
			contratoCadastrar.setQtdFaturasParceladas(Integer.parseInt(form.getQtdFaturasParceladas()));
		}
		if (form != null && !"".equals(form.getFormaPgto())){
			Collection<CobrancaForma> formasPagto = fachada.pesquisar(new FiltroCobrancaForma(), CobrancaForma.class.getName());
			for (CobrancaForma formaPgto : formasPagto) {
				if(formaPgto.getId() == Integer.parseInt(form.getFormaPgto())){
					contratoCadastrar.setCobrancaForma(formaPgto);
				}
			}
		}
	}
	
	private String validarFormulario(InserirResolucaoDiretoriaContratoParcelamentoActionForm form){
		String retorno = "";
		
		if (form.getNumero() == null || "".equals(form.getNumero())){
			retorno = "Número ";	
		}
		if (form.getAssunto() == null || "".equals(form.getAssunto())){
			retorno = "Assunto ";	
		}
		if (form.getDataVigenciaInicial() == null || "".equals(form.getDataVigenciaInicial())){
			retorno = "Data Vigência Inicial ";	
		}
		if (form.getDataVigenciaFinal() == null || "".equals(form.getDataVigenciaFinal())){
			retorno = "Data Vigência Final ";	
		}
		if (form.getIndicadorDebitoAcrescimo() == null || "".equals(form.getIndicadorDebitoAcrescimo())){
			retorno = "Débito com Acréscimo ";	
		}
		if (form.getIndicadorParcelamentoJuros() == null || "".equals(form.getIndicadorParcelamentoJuros())){
			retorno = "Parcelamento com Juros ";	
		}
		if (form.getIndicadorInformarParcela() == null || "".equals(form.getIndicadorInformarParcela())){
			retorno = "Indicador Informar o Valor da Parcela ";	
		}
		if (form.getQtdFaturasParceladas() == null || "".equals(form.getQtdFaturasParceladas())){
			retorno = "Número Máximo de Parcelas ";	
		}
		if (form.getFormaPgto() == null ||  "".equals(form.getFormaPgto())){
			retorno = "Forma de Pagamento ";	
		}
		
		if(!retorno.equals("")){
			retorno = "Informe: " + retorno;
		}
		
		Date dataInicial = Util.converteStringParaDate(form.getDataVigenciaInicial());
		Date dataFinal = Util.converteStringParaDate(form.getDataVigenciaFinal());
		if(dataInicial == null || dataFinal == null){
			retorno = "Data inválida";
		}else{
			if(dataFinal.before(dataInicial)){
				retorno = "Data Início da Vigência deve ser anterior ou igual à Data Fim da Vigência";
			}
		}
		
		
		return retorno;
	}
	
	
	private boolean validarNumeroMaxParc(List<QuantidadePrestacoes> parcelas, InserirResolucaoDiretoriaContratoParcelamentoActionForm form){
		boolean retorno = true;
		if (form.getQtdFaturasParceladas() != null && !"".equals(form.getQtdFaturasParceladas())){
			for (QuantidadePrestacoes parcela : parcelas) {
				if(parcela.getQtdFaturasParceladas().intValue() > Integer.parseInt(form.getQtdFaturasParceladas())){
					retorno = false;
				}
			}
		}
		
		return retorno;
	}
	
}

package gcom.gui.cobranca.contratoparcelamento;



import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoPrestacao;
import gcom.cobranca.contratoparcelamento.FiltroPrestacaoItemContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirEmitirComprovantePagamentoContratoParcelamentoClienteAction extends GcomAction {
	
	private HttpSession sessao;
	
	
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

		ActionForward retorno = actionMapping.findForward("emitirComprovante");
		sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		String numeroContrato = httpServletRequest.getParameter("numeroContrato");
		ContratoParcelamentoCliente contratoParcelamentoCliente = null;
		
		EmitirComprovantePagamentoContratoParcelamentoClienteActionForm emitirComprovantePagamento = (EmitirComprovantePagamentoContratoParcelamentoClienteActionForm) actionForm;
		
		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = new FiltroContratoParcelamentoCliente();
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.cobrancaForma");
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.motivoDesfazer");
		
		
		if(numeroContrato != null && !numeroContrato.equals("")){
			
			filtroContratoParcelamentoCliente.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamentoCliente.NUMERO_CONTRATO,numeroContrato));
			ArrayList<ContratoParcelamentoCliente> collectionContratoParcelamentoCliente = new ArrayList<ContratoParcelamentoCliente>(fachada.pesquisar(filtroContratoParcelamentoCliente,ContratoParcelamentoCliente.class.getName()));
			
			//Verificar a existência do contrato
			if(collectionContratoParcelamentoCliente.size() == 0){
				throw new ActionServletException("atencao.numero.contrato.nao.existe",numeroContrato);
			}
			
			//Rotina para buscar o cliente superior se a ocorrência não for única
			else if(collectionContratoParcelamentoCliente.size() > 1){
				Iterator it = collectionContratoParcelamentoCliente.iterator();
				while(it.hasNext()){
					contratoParcelamentoCliente = (ContratoParcelamentoCliente) it.next();
					if(contratoParcelamentoCliente.getIndicadorClienteSuperior().toString().equals("1"))
						break;
				}
			}
			else{
				contratoParcelamentoCliente = collectionContratoParcelamentoCliente.get(0);
				if(contratoParcelamentoCliente.getIndicadorClienteSuperior().toString().equals("2") && contratoParcelamentoCliente.getClienteSuperior() == null){ 
					if (contratoParcelamentoCliente.getContrato().getRelacaoCliente() != null){
							emitirComprovantePagamento.setTipoRelacao(contratoParcelamentoCliente.getContrato().getRelacaoCliente().getDescricao());
					}
				}
			}
			
			
			
			FiltroContratoParcelamentoPrestacao filtroContratoParcelamentoPrestacao = new FiltroContratoParcelamentoPrestacao();
			filtroContratoParcelamentoPrestacao.adicionarCaminhoParaCarregamentoEntidade("contratoParcelamento");
			filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroNaoNulo(FiltroContratoParcelamentoPrestacao.VALOR_PAGAMENTO,ConectorAnd.CONECTOR_AND,2));
			filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoPrestacao.CONTRATO_PARCEL_ID,contratoParcelamentoCliente.getContrato().getId()));
			filtroContratoParcelamentoPrestacao.setCampoOrderBy(FiltroContratoParcelamentoPrestacao.NUMERO);
			
			ArrayList<PrestacaoContratoParcelamento> collectionPrestacaoContratoParcelamento = new ArrayList<PrestacaoContratoParcelamento>(fachada.pesquisar(filtroContratoParcelamentoPrestacao,PrestacaoContratoParcelamento.class.getName()));
			
			
			//Verificando possibilidade de emissão de comprovante
			if(collectionPrestacaoContratoParcelamento.size() != 0){
				
				//Dados do Contrato que irão para a tela
				emitirComprovantePagamento.setIdNumeroContrato(contratoParcelamentoCliente.getContrato().getNumero().toString());
				emitirComprovantePagamento.setIdCliente(contratoParcelamentoCliente.getCliente().getId().toString());
				emitirComprovantePagamento.setNome(contratoParcelamentoCliente.getCliente().getNome());
				emitirComprovantePagamento.setCnpj(contratoParcelamentoCliente.getCliente().getCnpj());
				emitirComprovantePagamento.setNomeCNPJ(contratoParcelamentoCliente.getCliente().getNome() + " " + contratoParcelamentoCliente.getCliente().getCnpjFormatado());
				emitirComprovantePagamento.setDataImplantacaoContrato(Util.formatarData(contratoParcelamentoCliente.getContrato().getDataImplantacao()));
				
				//Quantidades de prestações
				Iterator it = collectionPrestacaoContratoParcelamento.iterator();
				int marcador = 0;
				//filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item");
				
				
				while(it.hasNext()){
					FiltroPrestacaoItemContratoParcelamento filtroPrestacaoItem = new FiltroPrestacaoItemContratoParcelamento();
					filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("prestacao");
					filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item");
					PrestacaoContratoParcelamento prestacao = (PrestacaoContratoParcelamento) it.next();
					filtroPrestacaoItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_ID,prestacao.getId()));
					filtroPrestacaoItem.setCampoDistinct("objeto."+FiltroPrestacaoItemContratoParcelamento.ITEM_ID);
					Collection collectionPrestacaoItem = fachada.pesquisar(filtroPrestacaoItem,PrestacaoItemContratoParcelamento.class.getName());
					prestacao.setItensPagos(collectionPrestacaoItem.size());
					//prestacao.setDataPagamento(prestacao.getDataPagamento());
					
					//Salva com o valor da quantidade de itens pagos
					collectionPrestacaoContratoParcelamento.set(marcador,prestacao);
					
					marcador++;
				}
				
				sessao.setAttribute("contratoParcelamentoCliente",contratoParcelamentoCliente);
				sessao.setAttribute("parcelas",collectionPrestacaoContratoParcelamento);
				sessao.setAttribute("quantidadeParcelas",collectionPrestacaoContratoParcelamento.size());
					
				}
				else{
					throw new ActionServletException("atencao.contrato.pagamento.inexistente",numeroContrato);
				}
			}
			else{
				throw new ActionServletException("atencao.numero.contrato.nao.existe",numeroContrato);
			}			
			
		
		
		
		return retorno;
	}

}

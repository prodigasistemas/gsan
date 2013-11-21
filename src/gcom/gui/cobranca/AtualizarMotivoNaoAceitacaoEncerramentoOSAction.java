package gcom.gui.cobranca;

import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1172] Manter Motivos de Não Aceitação de Encerramento de OS
 * 
 * Classe responsável por atualizar na base de dados o motivo de não
 * aceitação do encerramento da OS.
 * 
 * @author Diogo Peixoto
 * @since 25/05/2011
 *
 */
public class AtualizarMotivoNaoAceitacaoEncerramentoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		MotivoNaoAceitacaoEncerramentoOSAtualizarActionForm form = (MotivoNaoAceitacaoEncerramentoOSAtualizarActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		//Recuperando os dados do formulário
		String descricao = form.getDescricao();
		String servicoDescontarCorteSupressao = form.getMultiplicadorValorServicoDescontarCorteSupressao();
		String servicoDescontarNaoExecutados = form.getMultiplicadorValorServicoDescontarNaoExecutados();
		String percentualMultaAplicar = form.getPercentualMultaAplicar();
		String ativoInativo = form.getIndicadorUso();
		String id = form.getId();
		
		//Configurando os valores do objeto para ser inserido na base de dados
		MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacao = new MotivoNaoAceitacaoEncerramentoOS();

		if(descricao  != null && descricao.length() <= 40){
			motivoNaoAceitacao.setDescricaoMotivoNaoAceitacaoEncerramentoOS(descricao);
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,"Descrição " );	
		}

		if(Util.verificarNaoVazio(id)){
			try{
				motivoNaoAceitacao.setId(Integer.valueOf(id));
			}catch (NumberFormatException e) {
				throw new ActionServletException("erro.sistema", null ,"");	
			}
		}

		Short multiplicadorServicoDescontarCorteSupressao = null;
		if(servicoDescontarCorteSupressao != null && !servicoDescontarCorteSupressao.equals("")){
			try{
				multiplicadorServicoDescontarCorteSupressao = Short.valueOf(servicoDescontarCorteSupressao);
				motivoNaoAceitacao.setMultiplicadorValorServicoDescontarCorteSupressao(multiplicadorServicoDescontarCorteSupressao);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null ,
				"Multiplicador do valor de serviço a ser descontado por serviços não efetivamente executados" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,
			"Multiplicador do valor de serviço a ser descontado por corte ou supressão" );	
		}

		Short multiplicadorServicoDescontarNaoExecutados;
		if(servicoDescontarNaoExecutados != null && !servicoDescontarNaoExecutados.equals("")){
			try{
				multiplicadorServicoDescontarNaoExecutados = Short.valueOf(servicoDescontarNaoExecutados);
				motivoNaoAceitacao.setMultiplicadorValorServicoDescontarNaoExecutados(multiplicadorServicoDescontarNaoExecutados);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null ,
				"Multiplicador do valor de serviço a ser descontado por serviços não efetivamente executados" );		
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,
			"Multiplicador do valor de serviço a ser descontado por serviços não efetivamente executados" );	
		}

		BigDecimal percentualMultaAplicarServicos = null;
		if(percentualMultaAplicar != null && !percentualMultaAplicar.equals("")){
			try{
				percentualMultaAplicarServicos = Util.formatarMoedaRealparaBigDecimal(percentualMultaAplicar);
				motivoNaoAceitacao.setPercentualMultaAplicar(percentualMultaAplicarServicos);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null ,
				"Percentual da multa a ser aplicada por serviços não efetivamente executados" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null , 
			"Percentual da multa a ser aplicada por serviços não efetivamente executados");
		}
		Short indicadorUso = null;
		if(ativoInativo != null){
			try{
				indicadorUso = Short.valueOf(ativoInativo);
				motivoNaoAceitacao.setIndicadorUso(indicadorUso);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null , "Indicador Uso" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null , "Indicador Uso" );	
		}
		motivoNaoAceitacao.setUltimaAlteracao(new Date());
		fachada.atualizar(motivoNaoAceitacao);
		
		montarPaginaSucesso(httpServletRequest, "Motivo de não aceitação do encerramento de OS "+ 
				motivoNaoAceitacao.getDescricaoMotivoNaoAceitacaoEncerramentoOS() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Motivo de Não Aceitação",
				"exibirManterMotivoNaoAceitacaoEncerramentoOSAction.do?menu=sim");
		return retorno;
	}
}
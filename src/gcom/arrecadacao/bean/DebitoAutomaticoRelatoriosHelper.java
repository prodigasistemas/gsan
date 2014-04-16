package gcom.arrecadacao.bean;

import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;

import java.util.Date;



/**
 * [CRC:1710] - Botões de imprimir nas abas de Consultar Imovel.<br/><br/>
 * 
 * Classe que servirá para exibir os dados dos Debitos Automáticos
 * no RelatorioDadosComplementaresImovel.<br/><br/>
 * 
 * OBS: Pode ser utilizada por qualquer outro relatorio tambem de modo
 * que não mude o que já existe.
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class DebitoAutomaticoRelatoriosHelper {
	
	private DebitoAutomatico debitoAutomatico;

	public DebitoAutomatico getDebitoAutomatico() {
		return debitoAutomatico;
	}

	public void setDebitoAutomatico(DebitoAutomatico debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}

	public String getDescricaoAbreviadaBanco() {
		if(debitoAutomatico!=null && debitoAutomatico.getAgencia()!=null
				&& debitoAutomatico.getAgencia().getBanco()!=null){
		
			return debitoAutomatico.getAgencia().getBanco().getDescricaoAbreviada();
		}
		
		return "";
	}

	public String getCodigoAgencia() {
		if(debitoAutomatico!=null && debitoAutomatico.getAgencia()!=null){
		
			return debitoAutomatico.getAgencia().getCodigoAgencia();
		}
		
		return "";
	}

	public String getIdentificacaoClienteBanco() {
		if(debitoAutomatico!=null){
		
			return debitoAutomatico.getIdentificacaoClienteBanco();
		}
		
		return "";
	}

	public Date getDataOpcaoDebitoContaCorrente() {
		if(debitoAutomatico!=null){
		
			return debitoAutomatico.getDataOpcaoDebitoContaCorrente();
		}
		
		return null;
	}
	
	public Date getDataInclusaoNovoDebitoAutomatico() {
		if(debitoAutomatico!=null){
		
			return debitoAutomatico.getDataInclusaoNovoDebitoAutomatico();
		}
		
		return null;
	}
	
	public Date getDataExclusao() {
		if(debitoAutomatico!=null){
		
			return debitoAutomatico.getDataExclusao();
		}
		
		return null;
	}
	
}

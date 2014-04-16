package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;

import java.io.Serializable;
import java.util.Collection;

/**
 * classe responsável por criar o relatório de Boletim de Custo de Pavimento.
 * 
 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
 * 
 * @author Hugo Leonardo
 *
 * @date 03/01/2011
 */
public class RelatorioBoletimCustoPavimentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OrdemServicoPavimento ordemServicoPavimento; 	
    private OrdemServico ordemServico; 	
	private String endereco;	
	private String motivo;
	private String tipo;
	private String tipoPavimento;
	
	private Collection collOSTipoPavimentoRuaHelper;
	

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public OrdemServicoPavimento getOrdemServicoPavimento() {
		return ordemServicoPavimento;
	}
	
	public void setOrdemServicoPavimento(OrdemServicoPavimento ordemServicoPavimento) {
		this.ordemServicoPavimento = ordemServicoPavimento;
	}

	public Collection getCollOSTipoPavimentoRuaHelper() {
		return collOSTipoPavimentoRuaHelper;
	}

	public void setCollOSTipoPavimentoRuaHelper(
			Collection collOSTipoPavimentoRuaHelper) {
		this.collOSTipoPavimentoRuaHelper = collOSTipoPavimentoRuaHelper;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoPavimento() {
		return tipoPavimento;
	}

	public void setTipoPavimento(String tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}
	
}

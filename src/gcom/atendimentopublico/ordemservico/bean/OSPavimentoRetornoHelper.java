package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC0639] Filtrar Ordens de Processo de Repavimentação
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Yara Taciane
 * @date 02/06/2008
 */
public class OSPavimentoRetornoHelper  implements Serializable{
	
	private static final long serialVersionUID = 1L;

   
	private OrdemServicoPavimento ordemServicoPavimento; 	
    private OrdemServico ordemServico; 	
	private String endereco;	
	private String motivo;
	
	private Collection collOSTipoPavimentoHelper_Rua;
	private Collection collOSTipoPavimentoHelper_RuaRet;
	
	private String hint1;
	
    /**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo ordemServico.
	 */
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	/**
	 * @param ordemServico O ordemServico a ser setado.
	 */
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	/**
	 * @return Retorna o campo ordemServicoPavimento.
	 */
	public OrdemServicoPavimento getOrdemServicoPavimento() {
		return ordemServicoPavimento;
	}

	/**
	 * @param ordemServicoPavimento O ordemServicoPavimento a ser setado.
	 */
	public void setOrdemServicoPavimento(OrdemServicoPavimento ordemServicoPavimento) {
		this.ordemServicoPavimento = ordemServicoPavimento;
	}

	/**
	 * @return Retorna o campo collOSTipoPavimentoHelper_Rua.
	 */
	public Collection getCollOSTipoPavimentoHelper_Rua() {
		return collOSTipoPavimentoHelper_Rua;
	}

	/**
	 * @param collOSTipoPavimentoHelper_Rua O collOSTipoPavimentoHelper_Rua a ser setado.
	 */
	public void setCollOSTipoPavimentoHelper_Rua(
			Collection collOSTipoPavimentoHelper_Rua) {
		this.collOSTipoPavimentoHelper_Rua = collOSTipoPavimentoHelper_Rua;
	}

	/**
	 * @return Retorna o campo collOSTipoPavimentoHelper_RuaRet.
	 */
	public Collection getCollOSTipoPavimentoHelper_RuaRet() {
		return collOSTipoPavimentoHelper_RuaRet;
	}

	/**
	 * @param collOSTipoPavimentoHelper_RuaRet O collOSTipoPavimentoHelper_RuaRet a ser setado.
	 */
	public void setCollOSTipoPavimentoHelper_RuaRet(
			Collection collOSTipoPavimentoHelper_RuaRet) {
		this.collOSTipoPavimentoHelper_RuaRet = collOSTipoPavimentoHelper_RuaRet;
	}

	/**
	 * @return Returns the motivo.
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo The motivo to set.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getHint1() {
		return hint1;
	}

	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}

}

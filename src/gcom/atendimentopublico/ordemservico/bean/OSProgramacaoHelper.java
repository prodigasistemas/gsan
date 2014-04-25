package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;

import java.util.Collection;
import java.util.Set;


/**
 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class OSProgramacaoHelper {

	private String situacao;
	private int diasAtrasoCliente;
	private int diasAtrasoAgencia;
	private String endereco;
	private boolean podeRemover;
	private boolean temAlerta;
	private OrdemServicoProgramacao ordemServicoProgramacao;
	private String alertaEquipeDeServicoPerfilTipo;
	private Collection colecaoAlertaEquipeDeLogradouro;
	private Set<Equipe> colecaoEquipeAssociadaOS;
	
	public boolean isPodeRemover() {
		return podeRemover;
	}

	public void setPodeRemover(boolean podeRemover) {
		this.podeRemover = podeRemover;
	}

	public OSProgramacaoHelper(){}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getDiasAtrasoAgencia() {
		return diasAtrasoAgencia;
	}

	public void setDiasAtrasoAgencia(int diasAtrasoAgencia) {
		this.diasAtrasoAgencia = diasAtrasoAgencia;
	}

	public int getDiasAtrasoCliente() {
		return diasAtrasoCliente;
	}

	public void setDiasAtrasoCliente(int diasAtrasoCliente) {
		this.diasAtrasoCliente = diasAtrasoCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public OrdemServicoProgramacao getOrdemServicoProgramacao() {
		return ordemServicoProgramacao;
	}

	public void setOrdemServicoProgramacao(OrdemServicoProgramacao ordemServicoProgramacao) {
		this.ordemServicoProgramacao = ordemServicoProgramacao;
	}

	public boolean isTemAlerta() {
		return temAlerta;
	}

	public void setTemAlerta(boolean temAlerta) {
		this.temAlerta = temAlerta;
	}

	public String getAlertaEquipeDeServicoPerfilTipo() {
		return alertaEquipeDeServicoPerfilTipo;
	}

	public void setAlertaEquipeDeServicoPerfilTipo(
			String alertaEquipeDeServicoPerfilTipo) {
		this.alertaEquipeDeServicoPerfilTipo = alertaEquipeDeServicoPerfilTipo;
	}

	public Collection getColecaoAlertaEquipeDeLogradouro() {
		return colecaoAlertaEquipeDeLogradouro;
	}

	public void setColecaoAlertaEquipeDeLogradouro(
			Collection colecaoAlertaEquipeDeLogradouro) {
		this.colecaoAlertaEquipeDeLogradouro = colecaoAlertaEquipeDeLogradouro;
	}

	public Set<Equipe> getColecaoEquipeAssociadaOS() {
		return colecaoEquipeAssociadaOS;
	}

	public void setColecaoEquipeAssociadaOS(Set<Equipe> colecaoEquipeAssociadaOS) {
		this.colecaoEquipeAssociadaOS = colecaoEquipeAssociadaOS;
	}


}

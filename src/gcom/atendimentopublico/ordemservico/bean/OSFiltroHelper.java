package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.unidade.UnidadeOrganizacional;


/**
 * [UC0450] Filtrar Registro de Atendimento
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class OSFiltroHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico;
	private String situacao;
	private UnidadeOrganizacional unidadeAtual;
	private Imovel imovel;
	
	
	//Parte usada no caso de uso [UC0456]
	private int diasAtrasoCliente;
	private int diasAtrasoAgencia;
	private String dataPrevisaoAtual;
	private String endereco;
	private ImovelPerfil perfilImovel;

	//Adicionado para atender o UC0466 - Manter Ordem de Servico
	private int indicadorUrgencia;
	private String hint1; 
	
	public String getHint1() {
		return hint1;
	}

	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}

	public OSFiltroHelper(){}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
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

	public String getDataPrevisaoAtual() {
		return dataPrevisaoAtual;
	}

	public void setDataPrevisaoAtual(String dataPrevisaoAtual) {
		this.dataPrevisaoAtual = dataPrevisaoAtual;
	}

	public int getIndicadorUrgencia() {
		return indicadorUrgencia;
	}

	public void setIndicadorUrgencia(int indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}

	public ImovelPerfil getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(ImovelPerfil perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	
	
}

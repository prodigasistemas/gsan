package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Date;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o
 * resultado do [UC0408] Atualizar Registro de Atendimento [SB0019] - Exibe
 * Registros de Atendimento de Falta de Água no Imóvel da Área do Bairro
 * 
 * @author Sávio Luiz
 * @date 16/08/2006
 * 
 */
public class ExibirRAFaltaAguaImovelHelper {

	private Integer idRA;

	private Integer idImovel;

	private Date dataAtendimento;

	private String horaAtendimento;
	
	private String enderecoOcorrencia;

	public ExibirRAFaltaAguaImovelHelper(Integer idRA,Integer idImovel, Date dataAtendimento) {
		this.idRA = idRA;
		this.idImovel = idImovel;
		this.dataAtendimento = dataAtendimento;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdRA() {
		return idRA;
	}

	public void setIdRA(Integer idRA) {
		this.idRA = idRA;
	}

	public String getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}
	
	

}

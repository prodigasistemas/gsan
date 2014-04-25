package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;

import java.util.Collection;


/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [SB0009] - Exibe Registros de Atendimento Pendentes do Local da Ocorrência
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 16/08/2006
 */
public class RegistroAtendimentoPendenteLocalOcorrenciaHelper {

	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
	private String enderecoOcorrencia;
	
	private Collection colecaoRegistroAtendimento;
	
	public RegistroAtendimentoPendenteLocalOcorrenciaHelper(){}

	
	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public Collection getColecaoRegistroAtendimento() {
		return colecaoRegistroAtendimento;
	}

	public void setColecaoRegistroAtendimento(Collection colecaoRegistroAtendimento) {
		this.colecaoRegistroAtendimento = colecaoRegistroAtendimento;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}
	
	
}

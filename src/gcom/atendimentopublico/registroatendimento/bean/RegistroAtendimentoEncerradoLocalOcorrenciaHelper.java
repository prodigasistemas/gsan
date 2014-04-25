package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Imovel;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [SB0015] - Verifica Existência de Registro de Atendimento Encerrado para o 
 * Local da Ocorrência
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 16/08/2006
 */
public class RegistroAtendimentoEncerradoLocalOcorrenciaHelper {
	
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
	private Imovel imovel;
	
	private String enderecoLocalOcorrencia;
	
	private Collection colecaoRegistroAtendimento;
	
	public RegistroAtendimentoEncerradoLocalOcorrenciaHelper(){}

	
	
	public String getEnderecoLocalOcorrencia() {
		return enderecoLocalOcorrencia;
	}



	public void setEnderecoLocalOcorrencia(String enderecoLocalOcorrencia) {
		this.enderecoLocalOcorrencia = enderecoLocalOcorrencia;
	}



	public Collection getColecaoRegistroAtendimento() {
		return colecaoRegistroAtendimento;
	}

	public void setColecaoRegistroAtendimento(Collection colecaoRegistroAtendimento) {
		this.colecaoRegistroAtendimento = colecaoRegistroAtendimento;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

}

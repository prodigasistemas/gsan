package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.geografico.BairroArea;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [SB0025] - Verifica Registro de Atendimento de Falta de Água Generalizada
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 16/08/2006
 */
public class RegistroAtendimentoFaltaAguaGeneralizadaHelper {
	
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
	private BairroArea bairroArea;
	
	private Collection colecaoRegistroAtendimento;
	
	
	public RegistroAtendimentoFaltaAguaGeneralizadaHelper(){}


	public BairroArea getBairroArea() {
		return bairroArea;
	}


	public void setBairroArea(BairroArea bairroArea) {
		this.bairroArea = bairroArea;
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

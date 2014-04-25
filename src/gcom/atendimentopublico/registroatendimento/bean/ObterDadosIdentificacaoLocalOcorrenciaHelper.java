package gcom.atendimentopublico.registroatendimento.bean;

import gcom.cadastro.imovel.Imovel;


/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [SB0004] - Obtém e Habilita/Desabilita Dados da Identificação do Local da Ocorrência
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 28/07/2006
 */
public class ObterDadosIdentificacaoLocalOcorrenciaHelper {

	private Imovel imovel;
	
	private String enderecoDescritivo;
	
	private boolean habilitarAlteracaoEndereco;
	
	private boolean informarEndereco;
	
	private boolean solicitacaoTipoRelativoFaltaAgua;
	
	private boolean solicitacaoTipoRelativoAreaEsgoto;
	
	private boolean imovelObrigatorio;
	
	public ObterDadosIdentificacaoLocalOcorrenciaHelper(){}

	
	
	public boolean isSolicitacaoTipoRelativoAreaEsgoto() {
		return solicitacaoTipoRelativoAreaEsgoto;
	}

	
	

	public boolean isImovelObrigatorio() {
		return imovelObrigatorio;
	}

	public void setImovelObrigatorio(boolean imovelObrigatorio) {
		this.imovelObrigatorio = imovelObrigatorio;
	}

	public void setSolicitacaoTipoRelativoAreaEsgoto(
			boolean solicitacaoTipoRelativoAreaEsgoto) {
		this.solicitacaoTipoRelativoAreaEsgoto = solicitacaoTipoRelativoAreaEsgoto;
	}



	public boolean isSolicitacaoTipoRelativoFaltaAgua() {
		return solicitacaoTipoRelativoFaltaAgua;
	}



	public void setSolicitacaoTipoRelativoFaltaAgua(
			boolean solicitacaoTipoRelativoFaltaAgua) {
		this.solicitacaoTipoRelativoFaltaAgua = solicitacaoTipoRelativoFaltaAgua;
	}



	public String getEnderecoDescritivo() {
		return enderecoDescritivo;
	}

	public void setEnderecoDescritivo(String enderecoDescritivo) {
		this.enderecoDescritivo = enderecoDescritivo;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public boolean isHabilitarAlteracaoEndereco() {
		return habilitarAlteracaoEndereco;
	}

	public void setHabilitarAlteracaoEndereco(boolean habilitarAlteracaoEndereco) {
		this.habilitarAlteracaoEndereco = habilitarAlteracaoEndereco;
	}

	public boolean isInformarEndereco() {
		return informarEndereco;
	}

	public void setInformarEndereco(boolean informarEndereco) {
		this.informarEndereco = informarEndereco;
	}
	
	
}

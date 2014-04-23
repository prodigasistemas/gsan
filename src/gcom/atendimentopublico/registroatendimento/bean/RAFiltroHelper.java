package gcom.atendimentopublico.registroatendimento.bean;

import java.io.Serializable;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.unidade.UnidadeOrganizacional;


/**
 * [UC0406] Filtrar Registro de Atendimento
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Leonardo Regis
 * @date 09/08/2006
 */
public class RAFiltroHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RegistroAtendimento registroAtendimento;
	
	private String situacao;
	
	private UnidadeOrganizacional unidadeAtual;
	
	private UnidadeOrganizacional unidadeDestino;
	
	private ImovelPerfil perfilImovel;
	
	private String hint1; 
	
	
	//Criado para atende o UC0405 - Manter Registro Atendimento
	private int indicadorUrgencia;
	
	/**
	 * @return Retorna o campo unidadeDestino.
	 */
	public UnidadeOrganizacional getUnidadeDestino() {
		return unidadeDestino;
	}

	/**
	 * @param unidadeDestino O unidadeDestino a ser setado.
	 */
	public void setUnidadeDestino(UnidadeOrganizacional unidadeDestino) {
		this.unidadeDestino = unidadeDestino;
	}

	public RAFiltroHelper(){}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
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

	public String getHint1() {
		return hint1;
	}

	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}
		
}

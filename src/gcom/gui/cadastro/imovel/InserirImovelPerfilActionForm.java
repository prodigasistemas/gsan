package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirImovelPerfilActionForm extends ValidatorActionForm{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;
	
	private Short indicadorUso;
	
	private Short indicadorGeracaoAutomatica;
	
	private Short indicadorInserirManterPerfil;
	
	private Short indicadorGerarDadosLeitura;
	
	private Short indicadorBloquearRetificacao;
	
	private Short indicadorGrandeConsumidor;
	
	private Short indicadorBloqueaDadosSocial;
	
	private Short indicadorGeraDebitoSegundaViaConta;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorGeracaoAutomatica() {
		return indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAuto) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAuto;
	}

	public Short getIndicadorInserirManterPerfil() {
		return indicadorInserirManterPerfil;
	}

	public void setIndicadorInserirManterPerfil(Short indicadorInserirManterPerfil) {
		this.indicadorInserirManterPerfil = indicadorInserirManterPerfil;
	}

	public Short getIndicadorGerarDadosLeitura() {
		return indicadorGerarDadosLeitura;
	}

	public void setIndicadorGerarDadosLeitura(Short indicadorGerarDadosLeitura) {
		this.indicadorGerarDadosLeitura = indicadorGerarDadosLeitura;
	}

	public Short getIndicadorBloquearRetificacao() {
		return indicadorBloquearRetificacao;
	}

	public void setIndicadorBloquearRetificacao(Short indicadorBloquearRetificacao) {
		this.indicadorBloquearRetificacao = indicadorBloquearRetificacao;
	}

	public Short getIndicadorGrandeConsumidor() {
		return indicadorGrandeConsumidor;
	}

	public void setIndicadorGrandeConsumidor(Short indicadorGrandeConsumidor) {
		this.indicadorGrandeConsumidor = indicadorGrandeConsumidor;
	}

	public Short getIndicadorBloqueaDadosSocial() {
		return indicadorBloqueaDadosSocial;
	}

	public void setIndicadorBloqueaDadosSocial(Short indicadorBloquearDadosSocial) {
		this.indicadorBloqueaDadosSocial = indicadorBloquearDadosSocial;
	}

	public Short getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(Short indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}
	

	
	

}

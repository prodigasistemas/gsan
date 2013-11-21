package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1080] ATUALIZAR PERFIL IMOVEL
 * 
 * @author Wallace Thierre
 * @date 04/10/2010
 */


public class AtualizarImovelPerfilActionForm extends ValidatorActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String descricao;

	private String indicadorUso;

	private String indicadorGeracaoAutomatica;

	private String indicadorInserirManterPerfil;

	private String indicadorGerarDadosLeitura;

	private String indicadorBloquearRetificacao;

	private String indicadorGrandeConsumidor;

	private String indicadorBloquearDadosSocial;

	private String indicadorGeraDebitoSegundaViaConta;
	
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIndicadorGeracaoAutomatica() {
		return indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(String indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}

	public String getIndicadorInserirManterPerfil() {
		return indicadorInserirManterPerfil;
	}

	public void setIndicadorInserirManterPerfil(String indicadorInserirManterPerfil) {
		this.indicadorInserirManterPerfil = indicadorInserirManterPerfil;
	}

	public String getIndicadorGerarDadosLeitura() {
		return indicadorGerarDadosLeitura;
	}

	public void setIndicadorGerarDadosLeitura(String indicadorGerarDadosLeitura) {
		this.indicadorGerarDadosLeitura = indicadorGerarDadosLeitura;
	}

	public String getIndicadorBloquearRetificacao() {
		return indicadorBloquearRetificacao;
	}

	public void setIndicadorBloquearReatificacao(String indicadorBloquearRetificacao) {
		this.indicadorBloquearRetificacao = indicadorBloquearRetificacao;
	}

	public String getIndicadorGrandeConsumidor() {
		return indicadorGrandeConsumidor;
	}

	public void setIndicadorGrandeConsumidor(String indicadorGrandeConsumidor) {
		this.indicadorGrandeConsumidor = indicadorGrandeConsumidor;
	}

	public String getIndicadorBloquearDadosSocial() {
		return indicadorBloquearDadosSocial;
	}

	public void setIndicadorBloquearDadosSocial(String indicadorBloquearDadosSocial) {
		this.indicadorBloquearDadosSocial = indicadorBloquearDadosSocial;
	}

	public String getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(String indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}






}

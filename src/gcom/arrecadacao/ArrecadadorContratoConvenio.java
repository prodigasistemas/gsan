package gcom.arrecadacao;

import gcom.interceptor.ControleAlteracao;

@ControleAlteracao()
public class ArrecadadorContratoConvenio {
	
	public static final Integer REGISTRO_CONVENIO_TESTE = 1;
	public static final Integer REGISTRO_CONVENIO = 2;
	public static final Integer PARCELAMENTO = 3;
	
	private Integer id;
	private Integer convenio;
	private Integer numeroCarteira;
	private Integer numeroVariacaoCarteira;
	private Short codigoTipoTitulo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getConvenio() {
		return convenio;
	}
	public void setConvenio(Integer convenio) {
		this.convenio = convenio;
	}
	public Integer getNumeroCarteira() {
		return numeroCarteira;
	}
	public void setNumeroCarteira(Integer numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}
	public Integer getNumeroVariacaoCarteira() {
		return numeroVariacaoCarteira;
	}
	public void setNumeroVariacaoCarteira(Integer numeroVariacaoCarteira) {
		this.numeroVariacaoCarteira = numeroVariacaoCarteira;
	}
	public Short getCodigoTipoTitulo() {
		return codigoTipoTitulo;
	}
	public void setCodigoTipoTitulo(Short codigoTipoTitulo) {
		this.codigoTipoTitulo = codigoTipoTitulo;
	}
	

}

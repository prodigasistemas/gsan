package gcom.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
 *
 * @author Vivianne Sousa
 * @date 25/06/2009
 */
public class OperacoesEfetuadasHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer argumento;
	private Integer id2TabelaLinhaAlteracao;
//	private Integer idAtributo;
	private BigDecimal valorAtualizacaoAtributo;
	private AtributosBoletimChaveHelper atributosBoletimChaveHelper;
	
	public AtributosBoletimChaveHelper getAtributosBoletimChaveHelper() {
		return atributosBoletimChaveHelper;
	}
	public void setAtributosBoletimChaveHelper(
			AtributosBoletimChaveHelper atributosBoletimChaveHelper) {
		this.atributosBoletimChaveHelper = atributosBoletimChaveHelper;
	}
	public Integer getArgumento() {
		return argumento;
	}
	public void setArgumento(Integer argumento) {
		this.argumento = argumento;
	}
	public Integer getId2TabelaLinhaAlteracao() {
		return id2TabelaLinhaAlteracao;
	}
	public void setId2TabelaLinhaAlteracao(Integer id2TabelaLinhaAlteracao) {
		this.id2TabelaLinhaAlteracao = id2TabelaLinhaAlteracao;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public BigDecimal getValorAtualizacaoAtributo() {
		return valorAtualizacaoAtributo;
	}
	public void setValorAtualizacaoAtributo(BigDecimal valorAtualizacaoAtributo) {
		this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
	}
	
	
}

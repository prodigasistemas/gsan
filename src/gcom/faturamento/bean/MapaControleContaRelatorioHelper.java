package gcom.faturamento.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class MapaControleContaRelatorioHelper implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idEmpresa;
	private String nomeEmpresa;
	private Integer idTipoConta;
	private String descricaoTipoConta;
	private Integer idLocalidade;
	private Integer codigoSetor;
	private Integer idFaturamentoGrupo;
	private Integer sequencialInicial;
	private Integer sequencialFinal;
	private BigDecimal somaValorAgua;
	private BigDecimal somaValorEsgoto;
	private BigDecimal somaValordebito;
	private BigDecimal somaValorCredito;
	private Integer idEsferaPoder;
	private Integer qtdeContas;
	private Integer qtdTotalMacro;
		
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public MapaControleContaRelatorioHelper(){}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getDescricaoTipoConta() {
		return descricaoTipoConta;
	}

	public void setDescricaoTipoConta(String descricaoTipoConta) {
		this.descricaoTipoConta = descricaoTipoConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(Integer idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Integer getSequencialFinal() {
		return sequencialFinal;
	}

	public void setSequencialFinal(Integer sequencialFinal) {
		this.sequencialFinal = sequencialFinal;
	}

	public Integer getSequencialInicial() {
		return sequencialInicial;
	}

	public void setSequencialInicial(Integer sequencialInicial) {
		this.sequencialInicial = sequencialInicial;
	}

	public BigDecimal getSomaValorAgua() {
		return somaValorAgua;
	}

	public void setSomaValorAgua(BigDecimal somaValorAgua) {
		this.somaValorAgua = somaValorAgua;
	}

	public BigDecimal getSomaValorCredito() {
		return somaValorCredito;
	}

	public void setSomaValorCredito(BigDecimal somaValorCredito) {
		this.somaValorCredito = somaValorCredito;
	}

	public BigDecimal getSomaValordebito() {
		return somaValordebito;
	}

	public void setSomaValordebito(BigDecimal somaValordebito) {
		this.somaValordebito = somaValordebito;
	}

	public BigDecimal getSomaValorEsgoto() {
		return somaValorEsgoto;
	}

	public void setSomaValorEsgoto(BigDecimal somaValorEsgoto) {
		this.somaValorEsgoto = somaValorEsgoto;
	}
	
	public BigDecimal getValor(){
		BigDecimal valor = BigDecimal.ZERO;
		
		valor = valor.add(this.somaValorAgua);
		valor = valor.add(this.somaValordebito);
		valor = valor.add(this.somaValorEsgoto);
		valor = valor.subtract(this.somaValorCredito);
		
		return valor;
	}
	
	public Integer getQtdContas(){
		Integer qtd = new Integer(0);
		
		if(this.sequencialFinal != null && this.sequencialInicial != null){
			qtd = (this.sequencialFinal.intValue() - this.sequencialInicial) + 1;
		}else{
			qtd = 0;
		}
		
		return qtd;
	}

	/**
	 * @return Retorna o campo qtdeContas.
	 */
	public Integer getQtdeContas() {
		return qtdeContas;
	}

	/**
	 * @param qtdeContas O qtdeContas a ser setado.
	 */
	public void setQtdeContas(Integer qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	
	public Integer getQtdTotalMacro() {
		return qtdTotalMacro;
	}

	public void setQtdTotalMacro(Integer qtdTotalMacro) {
		this.qtdTotalMacro = qtdTotalMacro;
	}
	
	
	
	
}

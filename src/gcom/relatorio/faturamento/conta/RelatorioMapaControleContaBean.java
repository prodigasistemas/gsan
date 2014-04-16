package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */
public class RelatorioMapaControleContaBean implements RelatorioBean {
	
	private String idEmpresa;
	private String nomeEmpresa;
	private String idLocalidade;
	private String codigoSetor; 
	private String descTipoConta;
	private String seqInicial;
	private String seqFinal;
	private String qtdeContas;
	private String valor;
	private String valorTotal;
	private String esferaPoder;
	private String qtdContasFirma;
	private String qtdContasCorreio;
	private String qtdContasPublico;
	private String qtdContasMacro;
	private String qtdTotalContas;
	private String qtdTotalGeral;
	private String qtdTotalGeralFirma;
	private String qtdTotalGeralCorreios;
	private String qtdTotalGeralPublico;

	
	public String getQtdTotalContas() {
		return qtdTotalContas;
	}

	public void setQtdTotalContas(String qtdTotalContas) {
		this.qtdTotalContas = qtdTotalContas;
	}

	public RelatorioMapaControleContaBean(
			 String idEmpresa,
			 String nomeEmpresa,
			 String idLocalidade,
			 String codigoSetor,
			 String descTipoConta,
			 String seqInicial,
			 String seqFinal,
			 String qtdeContas,
			 String valor,
			 String valorTotal) {
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.idLocalidade = idLocalidade;
		this.codigoSetor = codigoSetor;
		this.descTipoConta = descTipoConta;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.qtdeContas = qtdeContas;
		this.valor = valor;
		this.valorTotal = valorTotal;
		
	}
	
	public RelatorioMapaControleContaBean(
			 String idEmpresa,
			 String nomeEmpresa,
			 String idLocalidade,
			 String seqInicial,
			 String seqFinal,
			 String esferaPoder,
			 String qtdContasFirma,
			 String qtdContasCorreio,
			 String qtdContasPublico,
			 String qtdContasMacro,
			 String qtdTotalContas,
			 String qtdTotalGeralFirma,
			 String qtdTotalGeralCorreios,
			 String qtdTotalGeralPublico,			
			 String qtdTotalGeral) {
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.idLocalidade = idLocalidade;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.esferaPoder = esferaPoder;
		this.qtdContasFirma = qtdContasFirma; 
		this.qtdContasCorreio = qtdContasCorreio;
		this.qtdContasPublico = qtdContasPublico;
		this.qtdContasMacro = qtdContasMacro;
		this.qtdTotalContas = qtdTotalContas;
		this.qtdTotalGeralFirma = qtdTotalGeralFirma;
		this.qtdTotalGeralPublico = qtdTotalGeralPublico;	
		this.qtdTotalGeralCorreios = qtdTotalGeralCorreios;
		this.qtdTotalGeral = qtdTotalGeral;
	}

	
	
	public String getQtdContasMacro() {
		return qtdContasMacro;
	}

	public void setQtdContasMacro(String qtdContasMacro) {
		this.qtdContasMacro = qtdContasMacro;
	}	
	

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getDescTipoConta() {
		return descTipoConta;
	}

	public void setDescTipoConta(String descTipoConta) {
		this.descTipoConta = descTipoConta;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getQtdeContas() {
		return qtdeContas;
	}

	public void setQtdeContas(String qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	public String getSeqFinal() {
		return seqFinal;
	}

	public void setSeqFinal(String seqFinal) {
		this.seqFinal = seqFinal;
	}

	public String getSeqInicial() {
		return seqInicial;
	}

	public void setSeqInicial(String seqInicial) {
		this.seqInicial = seqInicial;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getQtdContasCorreio() {
		return qtdContasCorreio;
	}

	public void setQtdContasCorreio(String qtdContasCorreio) {
		this.qtdContasCorreio = qtdContasCorreio;
	}

	public String getQtdContasFirma() {
		return qtdContasFirma;
	}

	public void setQtdContasFirma(String qtdContasFirma) {
		this.qtdContasFirma = qtdContasFirma;
	}

	public String getQtdContasPublico() {
		return qtdContasPublico;
	}

	public void setQtdContasPublico(String qtdContasPublico) {
		this.qtdContasPublico = qtdContasPublico;
	}

	public String getQtdTotalGeral() {
		return qtdTotalGeral;
	}

	public void setQtdTotalGeral(String qtdTotalGeral) {
		this.qtdTotalGeral = qtdTotalGeral;
	}

	public String getQtdTotalGeralCorreios() {
		return qtdTotalGeralCorreios;
	}

	public void setQtdTotalGeralCorreios(String qtdTotalGeralCorreios) {
		this.qtdTotalGeralCorreios = qtdTotalGeralCorreios;
	}

	public String getQtdTotalGeralFirma() {
		return qtdTotalGeralFirma;
	}

	public void setQtdTotalGeralFirma(String qtdTotalGeralFirma) {
		this.qtdTotalGeralFirma = qtdTotalGeralFirma;
	}

	public String getQtdTotalGeralPublico() {
		return qtdTotalGeralPublico;
	}

	public void setQtdTotalGeralPublico(String qtdTotalGeralPublico) {
		this.qtdTotalGeralPublico = qtdTotalGeralPublico;
	}

	

}

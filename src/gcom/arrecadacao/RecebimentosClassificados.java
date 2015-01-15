package gcom.arrecadacao;

public class RecebimentosClassificados {

	private ResumoArrecadacao contasAgua;
	private ResumoArrecadacao contasEsgoto;
	private ResumoArrecadacao contasFinanciamentos;
	
	private ResumoArrecadacao parcelamentosAgua;
	private ResumoArrecadacao parcelamentosEsgoto;
	private ResumoArrecadacao parcelamentosJuros;
	private ResumoArrecadacao parcelamentosFinanciamentos;
	
	private ResumoArrecadacao creditosDocumentosExcesso;
	private ResumoArrecadacao creditosContasExcesso;
	private ResumoArrecadacao creditosDescontosConcedidos;
	private ResumoArrecadacao creditosDescontosCondicionais;
	private ResumoArrecadacao creditosDescontosIncondicionais;
	private ResumoArrecadacao creditosAjustes;
	private ResumoArrecadacao creditosValoresCobradosIndevidamente;
	
	private ResumoArrecadacao impostosRetidosIR;
	private ResumoArrecadacao impostosRetidosCSLL;
	private ResumoArrecadacao impostosRetidosCOFINS;
	private ResumoArrecadacao impostosRetidosPISPASEP;
	
	private ResumoArrecadacao guiasEntradaParcelamentos;
	private ResumoArrecadacao guiasGrupoContabil;
	
	private ResumoArrecadacao doacoesGrupoContabil;
	
	private ResumoArrecadacao debitos;
	private ResumoArrecadacao debitosGrupoContabil;
	
	private ResumoArrecadacao totalCreditos;
	private ResumoArrecadacao totalRecebimentos;
	private ResumoArrecadacao totalRecebimentosContas;
	
	public ResumoArrecadacao getContasAgua() {
		return contasAgua;
	}
	
	public void setContasAgua(ResumoArrecadacao contasAgua) {
		this.contasAgua = contasAgua;
	}
	
	public ResumoArrecadacao getContasEsgoto() {
		return contasEsgoto;
	}
	
	public void setContasEsgoto(ResumoArrecadacao contasEsgoto) {
		this.contasEsgoto = contasEsgoto;
	}
	
	public ResumoArrecadacao getContasFinanciamentos() {
		return contasFinanciamentos;
	}
	
	public void setContasFinanciamentos(ResumoArrecadacao contasFinanciamentos) {
		this.contasFinanciamentos = contasFinanciamentos;
	}
	
	public ResumoArrecadacao getParcelamentosAgua() {
		return parcelamentosAgua;
	}
	
	public void setParcelamentosAgua(ResumoArrecadacao parcelamentosAgua) {
		this.parcelamentosAgua = parcelamentosAgua;
	}
	
	public ResumoArrecadacao getParcelamentosEsgoto() {
		return parcelamentosEsgoto;
	}
	
	public void setParcelamentosEsgoto(ResumoArrecadacao parcelamentosEsgoto) {
		this.parcelamentosEsgoto = parcelamentosEsgoto;
	}
	
	public ResumoArrecadacao getParcelamentosJuros() {
		return parcelamentosJuros;
	}
	
	public void setParcelamentosJuros(ResumoArrecadacao parcelamentosJuros) {
		this.parcelamentosJuros = parcelamentosJuros;
	}
	
	public ResumoArrecadacao getParcelamentosFinanciamentos() {
		return parcelamentosFinanciamentos;
	}
	
	public void setParcelamentosFinanciamentos(ResumoArrecadacao parcelamentosFinanciamentos) {
		this.parcelamentosFinanciamentos = parcelamentosFinanciamentos;
	}
	
	public ResumoArrecadacao getCreditosDocumentosExcesso() {
		return creditosDocumentosExcesso;
	}
	
	public void setCreditosDocumentosExcesso(ResumoArrecadacao creditosDocumentosExcesso) {
		this.creditosDocumentosExcesso = creditosDocumentosExcesso;
	}
	
	public ResumoArrecadacao getCreditosContasExcesso() {
		return creditosContasExcesso;
	}
	
	public void setCreditosContasExcesso(ResumoArrecadacao creditosContasExcesso) {
		this.creditosContasExcesso = creditosContasExcesso;
	}
	
	public ResumoArrecadacao getCreditosDescontosConcedidos() {
		return creditosDescontosConcedidos;
	}
	
	public void setCreditosDescontosConcedidos(ResumoArrecadacao creditosDescontosConcedidos) {
		this.creditosDescontosConcedidos = creditosDescontosConcedidos;
	}
	
	public ResumoArrecadacao getCreditosDescontosCondicionais() {
		return creditosDescontosCondicionais;
	}
	
	public void setCreditosDescontosCondicionais(ResumoArrecadacao creditosDescontosCondicionais) {
		this.creditosDescontosCondicionais = creditosDescontosCondicionais;
	}
	
	public ResumoArrecadacao getCreditosDescontosIncondicionais() {
		return creditosDescontosIncondicionais;
	}
	
	public void setCreditosDescontosIncondicionais(ResumoArrecadacao creditosDescontosIncondicionais) {
		this.creditosDescontosIncondicionais = creditosDescontosIncondicionais;
	}
	
	public ResumoArrecadacao getCreditosAjustes() {
		return creditosAjustes;
	}
	
	public void setCreditosAjustes(ResumoArrecadacao creditosAjustes) {
		this.creditosAjustes = creditosAjustes;
	}
	
	public ResumoArrecadacao getCreditosValoresCobradosIndevidamente() {
		return creditosValoresCobradosIndevidamente;
	}
	
	public void setCreditosValoresCobradosIndevidamente(ResumoArrecadacao creditosValoresCobradosIndevidamente) {
		this.creditosValoresCobradosIndevidamente = creditosValoresCobradosIndevidamente;
	}
	
	public ResumoArrecadacao getImpostosRetidosIR() {
		return impostosRetidosIR;
	}
	
	public void setImpostosRetidosIR(ResumoArrecadacao impostosRetidosIR) {
		this.impostosRetidosIR = impostosRetidosIR;
	}
	
	public ResumoArrecadacao getImpostosRetidosCSLL() {
		return impostosRetidosCSLL;
	}
	
	public void setImpostosRetidosCSLL(ResumoArrecadacao impostosRetidosCSLL) {
		this.impostosRetidosCSLL = impostosRetidosCSLL;
	}
	
	public ResumoArrecadacao getImpostosRetidosCOFINS() {
		return impostosRetidosCOFINS;
	}
	
	public void setImpostosRetidosCOFINS(ResumoArrecadacao impostosRetidosCOFINS) {
		this.impostosRetidosCOFINS = impostosRetidosCOFINS;
	}
	
	public ResumoArrecadacao getImpostosRetidosPISPASEP() {
		return impostosRetidosPISPASEP;
	}
	
	public void setImpostosRetidosPISPASEP(ResumoArrecadacao impostosRetidosPISPASEP) {
		this.impostosRetidosPISPASEP = impostosRetidosPISPASEP;
	}
	
	public ResumoArrecadacao getGuiasEntradaParcelamentos() {
		return guiasEntradaParcelamentos;
	}
	
	public void setGuiasEntradaParcelamentos(ResumoArrecadacao guiasEntradaParcelamentos) {
		this.guiasEntradaParcelamentos = guiasEntradaParcelamentos;
	}
	
	public ResumoArrecadacao getGuiasGrupoContabil() {
		return guiasGrupoContabil;
	}
	
	public void setGuiasGrupoContabil(ResumoArrecadacao guiasGrupoContabil) {
		this.guiasGrupoContabil = guiasGrupoContabil;
	}
	
	public ResumoArrecadacao getDoacoesGrupoContabil() {
		return doacoesGrupoContabil;
	}
	
	public void setDoacoesGrupoContabil(ResumoArrecadacao doacoesGrupoContabil) {
		this.doacoesGrupoContabil = doacoesGrupoContabil;
	}
	
	public ResumoArrecadacao getDebitos() {
		return debitos;
	}
	
	public void setDebitos(ResumoArrecadacao debitos) {
		this.debitos = debitos;
	}
	
	public ResumoArrecadacao getDebitosGrupoContabil() {
		return debitosGrupoContabil;
	}
	
	public void setDebitosGrupoContabil(ResumoArrecadacao debitosGrupoContabil) {
		this.debitosGrupoContabil = debitosGrupoContabil;
	}
	
	public ResumoArrecadacao getTotalCreditos() {
		return totalCreditos;
	}
	
	public void setTotalCreditos(ResumoArrecadacao totalCreditos) {
		this.totalCreditos = totalCreditos;
	}
	
	public ResumoArrecadacao getTotalRecebimentos() {
		return totalRecebimentos;
	}
	
	public void setTotalRecebimentos(ResumoArrecadacao totalRecebimentos) {
		this.totalRecebimentos = totalRecebimentos;
	}
	
	public ResumoArrecadacao getTotalRecebimentosContas() {
		return totalRecebimentosContas;
	}

	public void setTotalRecebimentosContas(ResumoArrecadacao totalRecebimentosContas) {
		this.totalRecebimentosContas = totalRecebimentosContas;
	}
	
}

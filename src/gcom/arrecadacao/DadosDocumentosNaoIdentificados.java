package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

public class DadosDocumentosNaoIdentificados {

	private Integer id;
	private Integer referenciaArrecadacao;
	private BigDecimal valorDocumento;
	private Date dataDocumento;
	private Date ultimaAlteracao;
	
	private AvisoBancario avisoBancario;
	private Arrecadador arrecadador;
	private ArrecadadorMovimentoItem item;
	
	public DadosDocumentosNaoIdentificados(){
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReferenciaArrecadacao() {
		return referenciaArrecadacao;
	}
	public void setReferenciaArrecadacao(Integer referenciaArrecadacao) {
		this.referenciaArrecadacao = referenciaArrecadacao;
	}
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	public Date getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento= dataDocumento;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}
	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}
	public Arrecadador getArrecadador() {
		return arrecadador;
	}
	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}
	
	public ArrecadadorMovimentoItem getItem() {
		return item;
	}

	public void setItem(ArrecadadorMovimentoItem item) {
		this.item = item;
	}

	public void setDadosCodigoBarras(RegistroHelperCodigoG codigoBarras){
		this.referenciaArrecadacao = Util.recuperaAnoMesDaData(Util.formatarYYYYMMDDParaData(codigoBarras.getDataPagamento()));
		this.valorDocumento = Util.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(codigoBarras.getValorRecebido());
		this.dataDocumento = Util.formatarYYYYMMDDParaData(codigoBarras.getDataPagamento());
	}
}

package gcom.gerencial.faturamento;

import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoFaturamentoOutro implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    /** full constructor */

    /** default constructor */
    public UnResumoFaturamentoOutro() {
    }

    /** minimal constructor */
    public UnResumoFaturamentoOutro(gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK comp_id, BigDecimal valorDocumentosFaturados, short quantidadeDocumentosFaturados, Date ultimaAlteracao) {

    public gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK getComp_id() {
    public void setComp_id(gcom.gerencial.faturamento.UnResumoFaturamentoOutroPK comp_id) {
    public void setQuantidadeDocumentosFaturados(short quantidadeDocumentosFaturados) {
    public Date getUltimaAlteracao() {
    public void setUltimaAlteracao(Date ultimaAlteracao) {
    public gcom.gerencial.faturamento.UnResumoFaturamento getUnResumoFaturamento() {
    public void setUnResumoFaturamento(gcom.gerencial.faturamento.UnResumoFaturamento unResumoFaturamento) {
    public GDocumentoTipo getGerDocumentoTipo() {
	public void setGerDocumentoTipo(GDocumentoTipo gerDocumentoTipo) {
	public GFinanciamentoTipo getGerFinanciamentoTipo() {
	public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
	public GLancamentoItemContabil getGerLancamentoItemContabil() {
	public void setGerLancamentoItemContabil(
	public String toString() {
    public boolean equals(Object other) {
    public int hashCode() {
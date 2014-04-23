package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaGrupoCronogramaMes;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC0312] Inserir Cromograma de Cobrança  
 *
 * @author Flávio Cordeiro
 * @date 25/04/2006
 */
public class CobrancaCronogramaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes;
	
	private CobrancaAcaoCronograma cobrancaAcaoCronograma;
	
	private Collection cobrancasAtividadesParaInsercao;
	
	private String comandar;
	
	private boolean Inserir;
	
	private boolean critica1;
	
	private boolean critica2;
	
	private boolean critica3;

	public boolean getCritica1() {
		return critica1;
	}

	public void setCritica1(boolean critica1) {
		this.critica1 = critica1;
	}

	public boolean getCritica2() {
		return critica2;
	}

	public void setCritica2(boolean critica2) {
		this.critica2 = critica2;
	}

	public String getComandar() {
		return comandar;
	}

	public void setComandar(String comandar) {
		this.comandar = comandar;
	}

	/**
	 * Construtor de OpcoesParcelamentoHelper 
	 */
	public CobrancaCronogramaHelper() {
	}

	public CobrancaAcaoCronograma getCobrancaAcaoCronograma() {
		return cobrancaAcaoCronograma;
	}

	public void setCobrancaAcaoCronograma(
			CobrancaAcaoCronograma cobrancaAcaoCronograma) {
		this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
	}

	public CobrancaGrupoCronogramaMes getCobrancaGrupoCronogramaMes() {
		return cobrancaGrupoCronogramaMes;
	}

	public void setCobrancaGrupoCronogramaMes(
			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes) {
		this.cobrancaGrupoCronogramaMes = cobrancaGrupoCronogramaMes;
	}

	public Collection getCobrancasAtividadesParaInsercao() {
		return cobrancasAtividadesParaInsercao;
	}

	public void setCobrancasAtividadesParaInsercao(
			Collection cobrancasAtividadesParaInsercao) {
		this.cobrancasAtividadesParaInsercao = cobrancasAtividadesParaInsercao;
	}

	public boolean isInserir() {
		return Inserir;
	}

	public void setInserir(boolean inserir) {
		Inserir = inserir;
	}

	public boolean getCritica3() {
		return critica3;
	}

	public void setCritica3(boolean critica3) {
		this.critica3 = critica3;
	}
	
}

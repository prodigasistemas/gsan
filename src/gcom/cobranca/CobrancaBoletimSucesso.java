package gcom.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class CobrancaBoletimSucesso extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private CobrancaBoletimSucessoPK comp_id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal valorTaxaSucesso;
    
    private CobrancaBoletimMedicao cobrancaBoletimMedicao;
    
    private Imovel imovel;
    
    private Localidade localidade;
    
    private BigDecimal valorRecuperado;


	public CobrancaBoletimSucesso() {
		super();
		
	}

	public CobrancaBoletimSucesso(CobrancaBoletimSucessoPK comp_id, Date ultimaAlteracao, BigDecimal valorTaxaSucesso, CobrancaBoletimMedicao cobrancaBoletimMedicao, Imovel imovel) {
		super();
		
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorTaxaSucesso = valorTaxaSucesso;
		this.cobrancaBoletimMedicao = cobrancaBoletimMedicao;
		this.imovel = imovel;
	}

	public CobrancaBoletimMedicao getCobrancaBoletimMedicao() {
		return cobrancaBoletimMedicao;
	}

	public void setCobrancaBoletimMedicao(
			CobrancaBoletimMedicao cobrancaBoletimMedicao) {
		this.cobrancaBoletimMedicao = cobrancaBoletimMedicao;
	}

	public CobrancaBoletimSucessoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(CobrancaBoletimSucessoPK comp_id) {
		this.comp_id = comp_id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorTaxaSucesso() {
		return valorTaxaSucesso;
	}

	public void setValorTaxaSucesso(BigDecimal valorTaxaSucesso) {
		this.valorTaxaSucesso = valorTaxaSucesso;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	

	public BigDecimal getValorRecuperado() {
		return valorRecuperado;
	}

	public void setValorRecuperado(BigDecimal valorRecuperado) {
		this.valorRecuperado = valorRecuperado;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroCobrancaBoletimSucesso filtroCobrancaBoletimSucesso = new FiltroCobrancaBoletimSucesso();
		
		filtroCobrancaBoletimSucesso.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroCobrancaBoletimSucesso.adicionarCaminhoParaCarregamentoEntidade("cobrancaBoletimMedicao");
		filtroCobrancaBoletimSucesso.adicionarCaminhoParaCarregamentoEntidade("imovel");

		
		filtroCobrancaBoletimSucesso.adicionarParametro(
						new ParametroSimples(FiltroCobrancaBoletimSucesso.COMP_ID_COBRANCA_BOLETIM_MEDICAO_ID, cobrancaBoletimMedicao.getId()));
		
		filtroCobrancaBoletimSucesso.adicionarParametro(
				new ParametroSimples(FiltroCobrancaBoletimSucesso.COMP_ID_IMOVEL_ID, imovel.getId()));
		
		return filtroCobrancaBoletimSucesso; 
	}
    
}

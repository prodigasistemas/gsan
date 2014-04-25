package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * classe responsável por criar o relatório de imoveis por situação da ligação de agua
 * 
 * @author Bruno Barros
 * @created 03/12/2007
 */
public class RelatorioImoveisFaturasAtrasoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidade;
	private String setorComercial;
	
	private String rota;
	
	private String nomeClienteUsuario;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private String quantidadeFaturasAtraso;
	
	private String referenciaFaturasAtrasoInicial;
	private String referenciaFaturasAtrasoFinal;
	
	private String valorFaturasAtrasoSemEncargos;
	private String valorFaturasAtrasoComEncargos;
	
	private JRBeanCollectionDataSource arrayJrContas;

	private ArrayList<RelatorioImoveisFaturasAtrasoContasBean> arrayRelatorioImoveisFaturasAtrasoContasBean;
	
	private BigDecimal valorTotalFaturaAtrasoSemEncargo;
	private BigDecimal valorTotalFaturaAtrasoComEncargo;

	private String cpfOuCnpjClienteUsuario;

	private String idCliente;
	private String nomeCliente;

	public RelatorioImoveisFaturasAtrasoBean(RelatorioImoveisFaturasAtrasoHelper helper, Collection<RelatorioImoveisFaturasAtrasoContasBean> colecaoRelatorioImoveisFaturasAtrasoContasBean) {
		
		this(helper);
		
		if ( !Util.isVazioOrNulo(colecaoRelatorioImoveisFaturasAtrasoContasBean)) {

			this.arrayRelatorioImoveisFaturasAtrasoContasBean = new ArrayList<RelatorioImoveisFaturasAtrasoContasBean>();
			this.arrayRelatorioImoveisFaturasAtrasoContasBean
					.addAll(colecaoRelatorioImoveisFaturasAtrasoContasBean);
			this.arrayJrContas = new JRBeanCollectionDataSource(
					this.arrayRelatorioImoveisFaturasAtrasoContasBean);
			
		} else {
			this.arrayJrContas = null;
		}
	}
	
	public RelatorioImoveisFaturasAtrasoBean(RelatorioImoveisFaturasAtrasoHelper helper) {

		this.idCliente = helper.getIdCliente()!=null?helper.getIdCliente().toString() : null;
		this.nomeCliente = helper.getNomeCliente();
		this.nomeClienteUsuario = helper.getNomeClienteUsuario();
		this.cpfOuCnpjClienteUsuario = helper.getCpfOuCnpjClienteUsuario() ;

		this.gerenciaRegional = helper.getGerenciaRegional()+"-"+helper.getNomeGerenciaRegional();
		this.localidade = helper.getLocalidade()+"-"+helper.getDescricaoLocalidade();
		this.setorComercial = helper.getSetorComercial()+"-"+helper.getDescricaoSetorComercial();
		this.unidadeNegocio = helper.getUnidadeNegocio()+"-"+helper.getNomeUnidadeNegocio();
		
		if(helper.getSequencialRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else if(helper.getRota() != null){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString());
		}
		
		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();

		this.referenciaFaturasAtrasoInicial = ( helper.getReferenciaFaturasAtrasoInicial() != null ? Util.formatarMesAnoReferencia( helper.getReferenciaFaturasAtrasoInicial() ) : "" );
		this.referenciaFaturasAtrasoFinal = ( helper.getReferenciaFaturasAtrasoFinal() != null ? Util.formatarMesAnoReferencia( helper.getReferenciaFaturasAtrasoFinal() ) : "" );

		this.quantidadeFaturasAtraso = Util.agruparNumeroEmMilhares( helper.getQuantidadeFaturasAtraso()!=null ? helper.getQuantidadeFaturasAtraso():0 );
		this.valorFaturasAtrasoSemEncargos = Util.formatarMoedaReal( helper.getValorFaturasAtrasoSemEncargos()!=null? helper.getValorFaturasAtrasoSemEncargos():BigDecimal.ZERO);
		this.valorFaturasAtrasoComEncargos = Util.formatarMoedaReal( helper.getValorFaturasAtrasoComEncargos()!=null?helper.getValorFaturasAtrasoComEncargos():BigDecimal.ZERO );

		this.matriculaImovel = helper.getMatriculaImovel();
		this.inscricaoImovel = helper.getInscricaoImovel();
		this.endereco = helper.getEndereco();
		
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public String getRota() {
		return rota;
	}

	public String getSetorComercial() {
		return setorComercial;
	}
	
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}

	
	public String getQuantidadeFaturasAtraso() {
	
		return quantidadeFaturasAtraso;
	}

	
	public String getReferenciaFaturasAtrasoFinal() {
	
		return referenciaFaturasAtrasoFinal;
	}

	
	public String getReferenciaFaturasAtrasoInicial() {
	
		return referenciaFaturasAtrasoInicial;
	}

	
	public String getValorFaturasAtrasoSemEncargos() {
	
		return valorFaturasAtrasoSemEncargos;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}

	
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
	
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public JRBeanCollectionDataSource getArrayJrContas() {
		return arrayJrContas;
	}

	public void setArrayJrContas(JRBeanCollectionDataSource arrayJrContas) {
		this.arrayJrContas = arrayJrContas;
	}

	public ArrayList<RelatorioImoveisFaturasAtrasoContasBean> getArrayRelatorioImoveisFaturasAtrasoContasBean() {
		return arrayRelatorioImoveisFaturasAtrasoContasBean;
	}

	public void setArrayRelatorioImoveisFaturasAtrasoContasBean(
			ArrayList<RelatorioImoveisFaturasAtrasoContasBean> arrayRelatorioImoveisFaturasAtrasoContasBean) {
		this.arrayRelatorioImoveisFaturasAtrasoContasBean = arrayRelatorioImoveisFaturasAtrasoContasBean;
	}

	public BigDecimal getValorTotalFaturaAtrasoSemEncargo() {
		return valorTotalFaturaAtrasoSemEncargo;
	}

	public void setValorTotalFaturaAtrasoSemEncargo(BigDecimal totalImovel) {
		this.valorTotalFaturaAtrasoSemEncargo = totalImovel;
	}

	public String getCpfOuCnpjClienteUsuario() {
		return cpfOuCnpjClienteUsuario;
	}

	public void setCpfOuCnpjClienteUsuario(String cpfOuCnpjClienteUsuario) {
		this.cpfOuCnpjClienteUsuario = cpfOuCnpjClienteUsuario;
	}

	public String getValorFaturasAtrasoComEncargos() {
		return valorFaturasAtrasoComEncargos;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public BigDecimal getValorTotalFaturaAtrasoComEncargo() {
		return valorTotalFaturaAtrasoComEncargo;
	}

	public void setValorTotalFaturaAtrasoComEncargo(
			BigDecimal valorTotalFaturaAtrasoComEncargo) {
		this.valorTotalFaturaAtrasoComEncargo = valorTotalFaturaAtrasoComEncargo;
	}
}

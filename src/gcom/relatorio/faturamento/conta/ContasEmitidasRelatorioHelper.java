package gcom.relatorio.faturamento.conta;

import java.math.BigDecimal;

public class ContasEmitidasRelatorioHelper {
		
	private String idContaImpressao;
	private String idClienteResponsavel;
	private String nomeClienteResponsavel;
	private String idLocalidade;
	private String descLocalidade;
	private String dataVencimentoConta;
	private String idImovel;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String numeroLote;
	private String numeroSubLote;
	private String nomeUsuario;  
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal valorDebitos;
	private BigDecimal valorCreditos;
	private String endereco;
	private String idEsferaPoder;
	private String descEsferaPoder;
	private String idGrupoFaturamento;
	private String mesAnoReferencia;

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	public void setDataVencimentoConta(String dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	public String getDescLocalidade() {
		return descLocalidade;
	}

	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getIdContaImpressao() {
		return idContaImpressao;
	}

	public void setIdContaImpressao(String idContaImpressao) {
		this.idContaImpressao = idContaImpressao;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(String numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}
	
	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = this.idLocalidade;
		setorComercial = this.codigoSetorComercial;
		quadra = this.numeroQuadra;
		lote = this.numeroLote;
		subLote = this.numeroSubLote;

		if (this.idLocalidade != null ){
			if (this.idLocalidade.length() < 3 && this.idLocalidade.length() > 1) {
				localidade = zeroUm + this.idLocalidade;
			} else if (String.valueOf(this.idLocalidade)
					.length() < 3) {
				localidade = zeroDois + this.idLocalidade;
			}	
		}

		if (this.codigoSetorComercial != null ){
			if (this.codigoSetorComercial.length() < 3 && this.codigoSetorComercial.length() > 1) {
				setorComercial = zeroUm + this.codigoSetorComercial;
			} else if (this.codigoSetorComercial.length() < 3) {
				setorComercial = zeroDois + this.codigoSetorComercial;
			}
		}
		
		if (this.numeroQuadra != null ){
			if (this.numeroQuadra.length() < 3 && this.numeroQuadra.length() > 1) {
				quadra = zeroUm + this.numeroQuadra;
			} else if (this.numeroQuadra.length() < 3) {
				quadra = zeroDois + this.numeroQuadra;
			}
		}
		
		if (this.numeroLote != null ){
			if (this.numeroLote.length() < 4 && this.numeroLote.length() > 2) {
				lote = zeroUm + this.numeroLote;
			} else if (this.numeroLote.length() < 3	&& this.numeroLote.length() > 1) {
				lote = zeroDois + this.numeroLote;
			} else if (this.numeroLote.length() < 2) {
				lote = zeroTres + this.numeroLote;
			}
		}
		
		if (this.numeroSubLote != null ){
			if (this.numeroSubLote.length() < 3 && this.numeroSubLote.length() > 1) {
				subLote = zeroUm + this.numeroSubLote;
			} else if (this.numeroSubLote.length() < 3) {
				subLote = zeroDois + this.numeroSubLote;
			}
		}
		
		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	
	/**
	 * Este método retorna o valor total da conta 
	 * (VALOR_AGUA + VALOR_ESGOTO + VALOR_DEBITOS) - VALOR_CREDITOS
	 *
	 * OBS - Este método foi alterado por Raphael pois não estava refletindo corretamente o valor da conta
	 *
	 * @author Raphael Rossiter
	 * @date 14/03/2006
	 *
	 */
	public BigDecimal getValorTotal() {

		BigDecimal valorTotalConta = new BigDecimal("0.00");

		// Valor de água
		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		// Valor de esgoto
		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		// Valor dos débitos
		if (this.getValorDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorDebitos());
		}

		// Valor dos créditos
		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}
		
		valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return valorTotalConta;	
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescEsferaPoder() {
		return descEsferaPoder;
	}

	public void setDescEsferaPoder(String descEsferaPoder) {
		this.descEsferaPoder = descEsferaPoder;
	}

	public String getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(String idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	
}

package gcom.relatorio.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ResumoReceitaHelper {
	
	private Integer unidadeNegocioId;
	private Integer gerenciaRegionalId;
	private Integer localidadeId;
	private String localidadeNome;
	private Integer categoriaId;
	private String categoriaNome;
	private Integer imovelId;
	private Timestamp dataRealizacao;
	private Integer contaBancariaId;
	private Integer arrecadadorId;
	private Integer bancoId;
	private Integer contaContabil;
	
	private BigDecimal somaAgua;
	private BigDecimal somaEsgoto;
	private BigDecimal somaCategoria;
	private BigDecimal somaServico;
	private BigDecimal somaImposto;
	private BigDecimal somaPagamento;
	private BigDecimal somaCredito;
	private BigDecimal somaDividaAtiva;
	private BigDecimal somaPagamentoGuia;
	private BigDecimal somaOutrasReceitas;
	private BigDecimal somaPagamentoNaoClassificado;
	private BigDecimal somaPagamentoDebCobrar;
	private BigDecimal somaPagamentoHistoricoSemCorrespondente;
	
	private String anoMes;
	private String localidadeInicial;
	private String localidadeFinal;
	
	private BigDecimal valorTotal;
	
	public ResumoReceitaHelper(){}
	
	public ResumoReceitaHelper(ResumoReceitaHelper helper){
		this.unidadeNegocioId = helper.getUnidadeNegocioId();
		this.gerenciaRegionalId = helper.getGerenciaRegionalId();
		this.localidadeId = helper.getLocalidadeId();
		this.localidadeNome = helper.getLocalidadeNome();
		this.categoriaId = helper.getCategoriaId();
		this.categoriaNome = helper.getCategoriaNome();
		this.imovelId = helper.getImovelId();
		this.dataRealizacao = helper.getDataRealizacao();
		this.contaBancariaId = helper.getContaBancariaId();
		this.arrecadadorId = helper.getArrecadadorId();
		
		this.somaAgua = helper.getSomaAgua();
		this.somaEsgoto = helper.getSomaEsgoto();
		this.somaCategoria = helper.getSomaCategoria();
		this.somaServico = helper.getSomaServico();
		this.somaImposto = helper.getSomaImposto();
		this.somaPagamento = helper.getSomaPagamento();
		this.somaCredito  = helper.getSomaCredito();
		this.bancoId = helper.getBancoId();
	}
	
	public BigDecimal getSomaAgua() {
		return somaAgua;
	}
	public void setSomaAgua(BigDecimal somaAgua) {
		this.somaAgua = somaAgua;
	}
	public BigDecimal getSomaCategoria() {
		return somaCategoria;
	}
	public void setSomaCategoria(BigDecimal somaCategoria) {
		this.somaCategoria = somaCategoria;
	}
	public BigDecimal getSomaEsgoto() {
		return somaEsgoto;
	}
	public void setSomaEsgoto(BigDecimal somaEsgoto) {
		this.somaEsgoto = somaEsgoto;
	}
	public BigDecimal getSomaServico() {
		return somaServico;
	}
	public void setSomaServico(BigDecimal somaServico) {
		this.somaServico = somaServico;
	}
	public Integer getUnidadeNegocioId() {
		return unidadeNegocioId;
	}
	public void setUnidadeNegocioId(Integer unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}
	public Integer getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}
	public void setGerenciaRegionalId(Integer gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}
	public Integer getLocalidadeId() {
		return localidadeId;
	}
	public void setLocalidadeId(Integer localidadeId) {
		this.localidadeId = localidadeId;
	}
	public String getLocalidadeNome() {
		return localidadeNome;
	}
	public void setLocalidadeNome(String localidadeNome) {
		this.localidadeNome = localidadeNome;
	}
	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getCategoriaNome() {
		return categoriaNome;
	}
	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
	public BigDecimal getSomaImposto() {
		return somaImposto;
	}
	public void setSomaImposto(BigDecimal somaImposto) {
		this.somaImposto = somaImposto;
	}
	public BigDecimal getSomaPagamento() {
		return somaPagamento;
	}
	public void setSomaPagamento(BigDecimal somaPagamento) {
		this.somaPagamento = somaPagamento;
	}
	public Integer getImovelId() {
		return imovelId;
	}
	public void setImovelId(Integer imovelId) {
		this.imovelId = imovelId;
	}
	public Timestamp getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Timestamp dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public BigDecimal getSomaCredito() {
		return somaCredito;
	}
	public void setSomaCredito(BigDecimal somaCredito) {
		this.somaCredito = somaCredito;
	}

	public Integer getContaBancariaId() {
		return contaBancariaId;
	}

	public void setContaBancariaId(Integer contaBancariaId) {
		this.contaBancariaId = contaBancariaId;
	}

	public Integer getArrecadadorId() {
		return arrecadadorId;
	}

	public void setArrecadadorId(Integer arrecadadorId) {
		this.arrecadadorId = arrecadadorId;
	}

	public Integer getBancoId() {
		return bancoId;
	}

	public void setBancoId(Integer bancoId) {
		this.bancoId = bancoId;
	}

	public Integer getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(Integer contaContabil) {
		this.contaContabil = contaContabil;
	}

	public BigDecimal getSomaDividaAtiva() {
		return somaDividaAtiva;
	}

	public void setSomaDividaAtiva(BigDecimal somaDividaAtiva) {
		this.somaDividaAtiva = somaDividaAtiva;
	}

	public BigDecimal getSomaOutrasReceitas() {
		return somaOutrasReceitas;
	}

	public void setSomaOutrasReceitas(BigDecimal somaOutrasReceitas) {
		this.somaOutrasReceitas = somaOutrasReceitas;
	}

	public BigDecimal getSomaPagamentoGuia() {
		return somaPagamentoGuia;
	}

	public void setSomaPagamentoGuia(BigDecimal somaPagamentoGuia) {
		this.somaPagamentoGuia = somaPagamentoGuia;
	}

	public BigDecimal getSomaPagamentoNaoClassificado() {
		return somaPagamentoNaoClassificado;
	}

	public void setSomaPagamentoNaoClassificado(
			BigDecimal somaPagamentoNaoClassificado) {
		this.somaPagamentoNaoClassificado = somaPagamentoNaoClassificado;
	}

	public BigDecimal getSomaPagamentoDebCobrar() {
		return somaPagamentoDebCobrar;
	}

	public void setSomaPagamentoDebCobrar(BigDecimal somaPagamentoDebCobrar) {
		this.somaPagamentoDebCobrar = somaPagamentoDebCobrar;
	}

	public BigDecimal getSomaPagamentoHistoricoSemCorrespondente() {
		return somaPagamentoHistoricoSemCorrespondente;
	}

	public void setSomaPagamentoHistoricoSemCorrespondente(
			BigDecimal somaPagamentoHistoricoSemCorrespondente) {
		this.somaPagamentoHistoricoSemCorrespondente = somaPagamentoHistoricoSemCorrespondente;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal retornaValorTotal(){
		BigDecimal retorno = BigDecimal.ZERO;
		
		if(this.somaAgua != null){
			retorno = retorno.add(this.somaAgua);
		}
		if(this.somaCategoria != null){
			retorno = retorno.add(this.somaCategoria);
		}
		if(this.somaCredito != null){
			retorno = retorno.add(this.somaCredito);
		}
		if(this.somaDividaAtiva != null){
			retorno = retorno.add(this.somaDividaAtiva);
		}
		if(this.somaEsgoto != null){
			retorno = retorno.add(this.somaEsgoto);
		}
		if(this.somaImposto != null){
			retorno = retorno.add(this.somaImposto);
		}
		if(this.somaOutrasReceitas != null){
			retorno = retorno.add(this.somaOutrasReceitas);
		}
		if(this.somaPagamento != null){
			retorno = retorno.add(this.somaPagamento);
		}
		if(this.somaPagamentoDebCobrar != null){
			retorno = retorno.add(this.somaPagamentoDebCobrar);
		}
		if(this.somaPagamentoGuia != null){
			retorno = retorno.add(this.somaPagamentoGuia);
		}
		if(this.somaPagamentoHistoricoSemCorrespondente != null){
			retorno = retorno.add(this.somaPagamentoHistoricoSemCorrespondente);
		}
		if(this.somaServico != null){
			retorno = retorno.add(this.somaServico);
		}
		if(this.somaPagamentoNaoClassificado != null){
			retorno = retorno.add(this.somaPagamentoNaoClassificado);
		}
				
		return retorno;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoMes == null) ? 0 : anoMes.hashCode());
		result = prime * result
				+ ((arrecadadorId == null) ? 0 : arrecadadorId.hashCode());
		result = prime * result + ((bancoId == null) ? 0 : bancoId.hashCode());
		result = prime * result
				+ ((dataRealizacao == null) ? 0 : dataRealizacao.hashCode());
		result = prime
				* result
				+ ((gerenciaRegionalId == null) ? 0 : gerenciaRegionalId
						.hashCode());
		result = prime * result
				+ ((localidadeId == null) ? 0 : localidadeId.hashCode());
		result = prime
				* result
				+ ((unidadeNegocioId == null) ? 0 : unidadeNegocioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResumoReceitaHelper other = (ResumoReceitaHelper) obj;
		if (anoMes == null) {
			if (other.anoMes != null)
				return false;
		} else if (!anoMes.equals(other.anoMes))
			return false;
		if (arrecadadorId == null) {
			if (other.arrecadadorId != null)
				return false;
		} else if (!arrecadadorId.equals(other.arrecadadorId))
			return false;
		if (bancoId == null) {
			if (other.bancoId != null)
				return false;
		} else if (!bancoId.equals(other.bancoId))
			return false;
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		} else if (!dataRealizacao.equals(other.dataRealizacao))
			return false;
		if (gerenciaRegionalId == null) {
			if (other.gerenciaRegionalId != null)
				return false;
		} else if (!gerenciaRegionalId.equals(other.gerenciaRegionalId))
			return false;
		if (localidadeId == null) {
			if (other.localidadeId != null)
				return false;
		} else if (!localidadeId.equals(other.localidadeId))
			return false;
		if (unidadeNegocioId == null) {
			if (other.unidadeNegocioId != null)
				return false;
		} else if (!unidadeNegocioId.equals(other.unidadeNegocioId))
			return false;
		
		if (contaContabil == null) {
			if (other.contaContabil != null)
				return false;
		} else if (!contaContabil.equals(other.contaContabil))
			return false;
		
		return true;
	}
	
}

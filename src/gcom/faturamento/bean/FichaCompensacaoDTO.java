package gcom.faturamento.bean;


public class FichaCompensacaoDTO {
			
		private Integer numeroConvenio;
		private Integer numeroCarteira;
		private Integer numeroVariacaoCarteira;
		private Short codigoModalidade;
		private String dataEmissao;
		private String dataVencimento; 
		private Double valorOriginal;
		private String codigoAceite;
		private Short codigoTipoTitulo;
		private String indicadorPermissaoRecebimentoParcial;
		private String numeroTituloCliente; //nosso numero
		
		private PagadorDTO pagador;
		
		public FichaCompensacaoDTO(Integer numeroConvenio, Integer numeroCarteira, Integer numeroVariacaoCarteira,
			Short codigoModalidade, String dataEmissao, String dataVencimento, Double valorOriginal,
			String codigoAceite, Short codigoTipoTitulo, String indicadorPermissaoRecebimentoParcial,
			String numeroTituloCliente, PagadorDTO pagador) {
			
			super();
			
			this.numeroConvenio = numeroConvenio;
			this.numeroCarteira = numeroCarteira;
			this.numeroVariacaoCarteira = numeroVariacaoCarteira;
			this.codigoModalidade = codigoModalidade;
			this.dataEmissao = dataEmissao;
			this.dataVencimento = dataVencimento;
			this.valorOriginal = valorOriginal;
			this.codigoAceite = codigoAceite;
			this.codigoTipoTitulo = codigoTipoTitulo;
			this.indicadorPermissaoRecebimentoParcial = indicadorPermissaoRecebimentoParcial;
			this.numeroTituloCliente = numeroTituloCliente;
			this.pagador = pagador;
		}

		public Integer getNumeroConvenio() {
			return numeroConvenio;
		}

		public Integer getNumeroCarteira() {
			return numeroCarteira;
		}

		public Integer getNumeroVariacaoCarteira() {
			return numeroVariacaoCarteira;
		}

		public Short getCodigoModalidade() {
			return codigoModalidade;
		}

		public String getDataEmissao() {
			return dataEmissao;
		}

		public String getDataVencimento() {
			return dataVencimento;
		}

		public Double getValorOriginal() {
			return valorOriginal;
		}

		public String getCodigoAceite() {
			return codigoAceite;
		}

		public Short getCodigoTipoTitulo() {
			return codigoTipoTitulo;
		}

		public String getIndicadorPermissaoRecebimentoParcial() {
			return indicadorPermissaoRecebimentoParcial;
		}

		public String getNumeroTituloCliente() {
			return numeroTituloCliente;
		}

		public PagadorDTO getPagador() {
			return pagador;
		}
		
		
}

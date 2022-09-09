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
		
//		private String indicadorPix;
		
		public FichaCompensacaoDTO(Integer numeroConvenio, Integer numeroCarteira, Integer numeroVariacaoCarteira,
			Short codigoModalidade, String dataEmissao, String dataVencimento, Double valorOriginal,
			String codigoAceite, Short codigoTipoTitulo, String indicadorPermissaoRecebimentoParcial,
			String numeroTituloCliente, PagadorDTO pagador) {//, String indicadorPix) {
			
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
//			this.indicadorPix = indicadorPix;
		}

		public FichaCompensacaoDTO() {
		}

		public Integer getNumeroConvenio() {
			return numeroConvenio;
		}

		public void setNumeroConvenio(Integer numeroConvenio) {
			this.numeroConvenio = numeroConvenio;
		}

		public Integer getNumeroCarteira() {
			return numeroCarteira;
		}

		public void setNumeroCarteira(Integer numeroCarteira) {
			this.numeroCarteira = numeroCarteira;
		}

		public Integer getNumeroVariacaoCarteira() {
			return numeroVariacaoCarteira;
		}

		public void setNumeroVariacaoCarteira(Integer numeroVariacaoCarteira) {
			this.numeroVariacaoCarteira = numeroVariacaoCarteira;
		}

		public Short getCodigoModalidade() {
			return codigoModalidade;
		}

		public void setCodigoModalidade(Short codigoModalidade) {
			this.codigoModalidade = codigoModalidade;
		}

		public String getDataEmissao() {
			return dataEmissao;
		}

		public void setDataEmissao(String dataEmissao) {
			this.dataEmissao = dataEmissao;
		}

		public String getDataVencimento() {
			return dataVencimento;
		}

		public void setDataVencimento(String dataVencimento) {
			this.dataVencimento = dataVencimento;
		}

		public Double getValorOriginal() {
			return valorOriginal;
		}

		public void setValorOriginal(Double valorOriginal) {
			this.valorOriginal = valorOriginal;
		}

		public String getCodigoAceite() {
			return codigoAceite;
		}

		public void setCodigoAceite(String codigoAceite) {
			this.codigoAceite = codigoAceite;
		}

		public Short getCodigoTipoTitulo() {
			return codigoTipoTitulo;
		}

		public void setCodigoTipoTitulo(Short codigoTipoTitulo) {
			this.codigoTipoTitulo = codigoTipoTitulo;
		}

		public String getIndicadorPermissaoRecebimentoParcial() {
			return indicadorPermissaoRecebimentoParcial;
		}

		public void setIndicadorPermissaoRecebimentoParcial(String indicadorPermissaoRecebimentoParcial) {
			this.indicadorPermissaoRecebimentoParcial = indicadorPermissaoRecebimentoParcial;
		}

		public String getNumeroTituloCliente() {
			return numeroTituloCliente;
		}

		public void setNumeroTituloCliente(String numeroTituloCliente) {
			this.numeroTituloCliente = numeroTituloCliente;
		}

		public PagadorDTO getPagador() {
			return pagador;
		}

		public void setPagador(PagadorDTO pagador) {
			this.pagador = pagador;
		}

		
		
}

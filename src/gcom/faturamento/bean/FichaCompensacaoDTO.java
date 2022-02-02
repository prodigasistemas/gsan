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
}

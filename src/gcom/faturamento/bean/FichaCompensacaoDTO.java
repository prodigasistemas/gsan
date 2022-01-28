package gcom.faturamento.bean;


public class FichaCompensacaoDTO {
			
		private Integer idConv;
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
		
		private PagadorDTO pagadorDTO;
		
		public FichaCompensacaoDTO(Integer idConv, Integer numeroCarteira, Integer numeroVariacaoCarteira,
			Short codigoModalidade, String dataEmissao, String dataVencimento, Double valorOriginal,
			String codigoAceite, Short codigoTipoTitulo, String indicadorPermissaoRecebimentoParcial,
			String numeroTituloCliente, PagadorDTO pagadorDTO) {
			
			super();
			
			this.idConv = idConv;
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
			this.pagadorDTO = pagadorDTO;
		}
}

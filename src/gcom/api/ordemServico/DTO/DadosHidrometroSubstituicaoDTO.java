package gcom.api.ordemServico.DTO;

import com.google.gson.annotations.SerializedName;

public class DadosHidrometroSubstituicaoDTO {
	
	//dados_hidrometro_substituicao
		@SerializedName("numeroSubstituicao")
		private Integer  idSubstituicao;
		
		@SerializedName("dataSubstituicao") 
		private String  dataSubstituicao;
		
		@SerializedName("leituraSubstituicao")
		private Integer  leituraSubstituicao;
		
		@SerializedName("situacaoHidrometro") 
		private Integer  situacaoHidrometro;
		
		@SerializedName("localArmazenagem") 
		private Integer localArmazenagem;

		public Integer getIdSubstituicao() {
			return idSubstituicao;
		}

		public void setIdSubstituicao(Integer idSubstituicao) {
			this.idSubstituicao = idSubstituicao;
		}

		public String getDataSubstituicao() {
			return dataSubstituicao;
		}

		public void setDataSubstituicao(String dataSubstituicao) {
			this.dataSubstituicao = dataSubstituicao;
		}

		public Integer getLeituraSubstituicao() {
			return leituraSubstituicao;
		}

		public void setLeituraSubstituicao(Integer leituraSubstituicao) {
			this.leituraSubstituicao = leituraSubstituicao;
		}

		public Integer getSituacaoHidrometro() {
			return situacaoHidrometro;
		}

		public void setSituacaoHidrometro(Integer situacaoHidrometro) {
			this.situacaoHidrometro = situacaoHidrometro;
		}

		public Integer getLocalArmazenagem() {
			return localArmazenagem;
		}

		public void setLocalArmazenagem(Integer localArmazenagem) {
			this.localArmazenagem = localArmazenagem;
		}
		
		
	

}

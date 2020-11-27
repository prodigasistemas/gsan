package gcom.api.ordemServico.helper;

import java.io.Serializable;

import gcom.cadastro.imovel.Imovel;

public class ArquivoRetornoAppOSHelper implements Serializable{

	private static final long serialVersionUID = 1448570895806137609L;

	private Integer indcFinalizacao;
	private Integer codRota;
	private Integer setorComercial;
	private Integer localidade;
	private Integer numeroSequenciaArquivo;
	private Integer idRota;
	
	public ArquivoRetornoAppOSHelper(){}
	
	public ArquivoRetornoAppOSHelper(String registro0, Integer idRota){
		this.indcFinalizacao = Integer.parseInt(registro0.substring(1,2));
		this.codRota = Integer.parseInt(registro0.substring(8,15));
		this.setorComercial = Integer.parseInt(registro0.substring(5,8));
		this.localidade = Integer.parseInt(registro0.substring(2,5));
		this.idRota = idRota;
		
		if (registro0.length() == 17) {
			this.numeroSequenciaArquivo = Integer.parseInt(registro0.substring(15, 17));
		} else {
			this.numeroSequenciaArquivo = Integer.parseInt(registro0.substring(19, 21));
		}
	}
	
	public Integer getIndcFinalizacao() {
		return indcFinalizacao;
	}
	public void setIndcFinalizacao(Integer indcFinalizacao) {
		this.indcFinalizacao = indcFinalizacao;
	}
	public Integer getCodRota() {
		return codRota;
	}
	public void setCodRota(Integer codRota) {
		this.codRota = codRota;
	}
	public Integer getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}
	public Integer getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Integer getNumeroSequenciaArquivo() {
		return numeroSequenciaArquivo;
	}

	public void setNumeroSequenciaArquivo(Integer numeroSequenciaArquivo) {
		this.numeroSequenciaArquivo = numeroSequenciaArquivo;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}
	
	public void atualizarDados(Imovel imovel, Integer idRota) {
		this.localidade = imovel.getLocalidade().getId();
		this.setorComercial = imovel.getRotaAlternativa().getSetorComercial().getCodigo();
		this.codRota = imovel.getRotaAlternativa().getCodigo().intValue();
		this.idRota = idRota;
	}
	
}

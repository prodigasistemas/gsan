package gcom.cadastro.imovel;

import java.util.Date;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

@ControleAlteracao()
public class ImagemAtualizacaoCadastral extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;
	
	private Integer id;
	
	private Integer idImovel;
	
	private byte[] imagem;
	
	/** persistent field */
    private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}

	public Integer getIdImovel() {
		return idImovel;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	

	

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

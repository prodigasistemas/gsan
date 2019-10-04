package gcom.cadastro.atualizacaocadastral.command;

import java.util.ArrayList;
import java.util.List;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;

public class AtualizacaoCadastral {

	private ArquivoTextoAtualizacaoCadastral arquivoTexto = null;

	private boolean validacaoLiberada = false;

	private List<AtualizacaoCadastralImovel> imoveisComInconsistencia = new ArrayList<AtualizacaoCadastralImovel>();

	private AtualizacaoCadastralImovel imovelAtual = new AtualizacaoCadastralImovel();

	private List<String> imagens = new ArrayList<String>();

	private Integer idRota = new Integer(0);

	private int totalImoveis = 0;

	private boolean comErro = false;

	public ArquivoTextoAtualizacaoCadastral getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(ArquivoTextoAtualizacaoCadastral arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public void liberarValidacao() {
		validacaoLiberada = true;
	}

	public boolean validacaoLiberada() {
		return validacaoLiberada;
	}

	public AtualizacaoCadastralImovel novaAtualizacaoImovel() {
		validacaoLiberada = false;
		AtualizacaoCadastralImovel imovel = new AtualizacaoCadastralImovel(this);
		imovelAtual = imovel;
		imoveisComInconsistencia.add(imovel);
		totalImoveis++;
		return imovel;
	}

	public AtualizacaoCadastralImovel getImovelAtual() {
		return imovelAtual;
	}

	public List<AtualizacaoCadastralImovel> getImoveisComInconsistencia() {
		return imoveisComInconsistencia;
	}

	public void excluirImovelSemInconsistencia() {
		imoveisComInconsistencia.remove(imovelAtual);
	}

	public boolean existeInconsistenciaNoArquivo() {
		return imoveisComInconsistencia.size() > 0;
	}

	public int getTotalImoveisComInconsistencia() {
		return imoveisComInconsistencia.size();
	}

	public int getTotalImoveis() {
		return totalImoveis;
	}

	public void setTotalImoveis(int totalImoveis) {
		this.totalImoveis = totalImoveis;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public boolean comErro() {
		return comErro;
	}

	public void setComErro(boolean comErro) {
		this.comErro = comErro;
	}
}

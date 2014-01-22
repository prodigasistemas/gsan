package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;

import java.util.ArrayList;
import java.util.List;

public class AtualizacaoCadastral {

	private ArquivoTextoAtualizacaoCadastral arquivoTexto = null;

	private boolean validacaoLiberada = false;
	
	private List<AtualizacaoCadastralImovel> imoveis = new ArrayList<AtualizacaoCadastralImovel>();
	
	private AtualizacaoCadastralImovel imovelAtual = new AtualizacaoCadastralImovel();
	
	public ArquivoTextoAtualizacaoCadastral getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(ArquivoTextoAtualizacaoCadastral arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public void liberarValidacao() {
		validacaoLiberada = true;
	}
	
	public boolean validacaoLiberada(){
		return validacaoLiberada;
	}
	
	public AtualizacaoCadastralImovel novaAtualizacaoImovel(){
		AtualizacaoCadastralImovel imovel = new AtualizacaoCadastralImovel();
		imovelAtual = imovel;
		imoveis.add(imovel);
		return imovel;
	}
	
	public AtualizacaoCadastralImovel getImovelAtual(){
		return imovelAtual;
	}
}

package gcom.cadastro.atualizacaocadastralsimplificado;


public class AtualizacaoCadastralSimplificadoBinario {
	
	private Integer id;
	private byte[] binario;
	private AtualizacaoCadastralSimplificado arquivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getBinario() {
		return binario;
	}

	public void setBinario(byte[] binario) {
		this.binario = binario;
	}

	public AtualizacaoCadastralSimplificado getArquivo() {
		return arquivo;
	}

	public void setArquivo(AtualizacaoCadastralSimplificado arquivo) {
		this.arquivo = arquivo;
	}

}

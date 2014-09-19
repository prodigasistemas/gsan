package gcom.cadastro.imovel.bean;


/**
 * Retorna a matricula do imovel formatado, o endereco formatado, situacao de agua e situacao de esgoto
 * 
 * 
 * @author Tiago Moreno
 * @date 20/11/2006
 */
public class ImovelDadosGeraisHelper {

	private Integer idImovel;

	private String matriculaImovel;
	
	private String enderecoImovel;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;

	public ImovelDadosGeraisHelper(Integer idImovel, String matriculaImovel, String enderecoImovel, String situacaoAgua, String situacaoEsgoto) {
		super();
		
		this.idImovel = idImovel;
		this.matriculaImovel = matriculaImovel;
		this.enderecoImovel = enderecoImovel;
		this.situacaoAgua = situacaoAgua;
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	


	
}

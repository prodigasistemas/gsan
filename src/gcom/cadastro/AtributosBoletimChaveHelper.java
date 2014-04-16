package gcom.cadastro;

import gcom.seguranca.Atributo;
import gcom.seguranca.AtributoGrupo;

/**
 * @author Vivianne Sousa
 * @since 26/06/2009
 */
public class AtributosBoletimChaveHelper implements Comparable{

	private Integer idAtributo;
	private Integer idGrupo; 
	private Short ordemEmissao;
	
	public final static AtributosBoletimChaveHelper NOTIFICACAO_VISITA = new AtributosBoletimChaveHelper(
			Atributo.NOTIFICACAO_VISITA,AtributoGrupo.VISITA_AO_IMOVEL,new Short("1"));
	
	
    /** full constructor */
    public AtributosBoletimChaveHelper(Integer idAtributo, Integer idGrupo, Short ordemEmissao) {
        this.idAtributo = idAtributo;
        this.idGrupo = idGrupo;
        this.ordemEmissao = ordemEmissao;
    }

    /** default constructor */
    public AtributosBoletimChaveHelper() {
    }
	
    public AtributosBoletimChaveHelper(Integer idAtributo) {
        this.idAtributo = idAtributo;
    }
	
	public Integer getIdAtributo() {
		return idAtributo;
	}
	public void setIdAtributo(Integer idAtributo) {
		this.idAtributo = idAtributo;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Short getOrdemEmissao() {
		return ordemEmissao;
	}
	public void setOrdemEmissao(Short ordemEmissao) {
		this.ordemEmissao = ordemEmissao;
	}

	public int compareTo(Object arg1) {
		AtributosBoletimChaveHelper helper = (AtributosBoletimChaveHelper)arg1;
		int retorno = 0;
		
		if(this.idGrupo.compareTo(helper.getIdGrupo()) == 0){
			
			retorno = this.ordemEmissao.compareTo(helper.getOrdemEmissao());
			
		}else{
			retorno = this.idGrupo.compareTo(helper.getIdGrupo());
		}
		return retorno;
	}

	public boolean equals(Object arg0) {
		AtributosBoletimChaveHelper helper = (AtributosBoletimChaveHelper)arg0;
		return helper.getIdAtributo().equals(this.idAtributo);
	}
}

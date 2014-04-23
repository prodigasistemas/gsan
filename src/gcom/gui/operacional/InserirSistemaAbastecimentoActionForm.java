package gcom.gui.operacional;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirSistemaAbastecimentoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String indicadorUso;

    private String descricao;
    private String descricaoAbreviada;
    
    private String fonteCaptacao;
    private String descricaoFonteCaptacao;
    
    private String tipoCaptacao;
    private String descricaoTipoCaptacao;

    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    
    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

	public String getDescricaoFonteCaptacao() {
		return descricaoFonteCaptacao;
	}

	public void setDescricaoFonteCaptacao(String descricaoFonteCaptacao) {
		this.descricaoFonteCaptacao = descricaoFonteCaptacao;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getDescricaoTipoCaptacao() {
		return descricaoTipoCaptacao;
	}

	public void setDescricaoTipoCaptacao(String descricaoTipoCaptacao) {
		this.descricaoTipoCaptacao = descricaoTipoCaptacao;
	}

	public String getTipoCaptacao() {
		return tipoCaptacao;
	}

	public void setTipoCaptacao(String tipoCaptacao) {
		this.tipoCaptacao = tipoCaptacao;
	}
	
}

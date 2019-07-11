package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Date;

import gcom.util.Util;

public class FiltrarGerarLoteAtualizacaoCadastralActionHelper {

	public static final int TODOS = -1;

	private int idLeiturista;

	private Date periodoInicial;

	private Date periodoFinal;

	private int idLocalidadeInicial;

	private int cdSetorComercialInicial;

	private int imoveisNovos;

	private int grandesConsumidores;

	private int ocorrenciaCadastro;

	private int ocorrenciaCadastroSelecionada;

	public FiltrarGerarLoteAtualizacaoCadastralActionHelper() {
	}

	public FiltrarGerarLoteAtualizacaoCadastralActionHelper(GerarLoteAtualizacaoCadastralForm form) {
		this.idLocalidadeInicial = Integer.valueOf(form.getIdLocalidadeInicial());
		this.cdSetorComercialInicial = Integer.valueOf(form.getCdSetorComercialInicial());
		this.periodoInicial = Util.formatarDataInicial(Util.converteStringParaDate(form.getPeriodoInicial()));
		this.periodoFinal = Util.formatarDataFinal(Util.converteStringParaDate(form.getPeriodoFinal()));
		this.idLeiturista = Integer.valueOf(form.getIdLeiturista());
		this.imoveisNovos = Integer.valueOf(form.getImoveisNovos());
		this.grandesConsumidores = Integer.valueOf(form.getGrandesConsumidores());
		this.ocorrenciaCadastro = Integer.valueOf(form.getOcorrenciaCadastro());
		this.ocorrenciaCadastroSelecionada = Integer.valueOf(form.getOcorrenciaCadastroSelecionada());
	}

	public int getIdLeiturista() {
		return idLeiturista;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public int getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public int getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public int getImoveisNovos() {
		return imoveisNovos;
	}

	public int getGrandesConsumidores() {
		return grandesConsumidores;
	}

	public int getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public int getOcorrenciaCadastroSelecionada() {
		return ocorrenciaCadastroSelecionada;
	}
}
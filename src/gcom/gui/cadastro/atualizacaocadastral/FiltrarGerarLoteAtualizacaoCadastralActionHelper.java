package gcom.gui.cadastro.atualizacaocadastral;

public class FiltrarGerarLoteAtualizacaoCadastralActionHelper {

	public static final int TODOS = -1;
	
	private int idLeiturista;

	private String periodoInicial;

	private String periodoFinal;

	private int idLocalidadeInicial;

	private int cdSetorComercialInicial;

	private int imoveisNovos;

	private int grandesConsumidores;

	private int ocorrenciaCadastro;

	private int ocorrenciaCadastroSelecionada;

	public FiltrarGerarLoteAtualizacaoCadastralActionHelper() {}

	public FiltrarGerarLoteAtualizacaoCadastralActionHelper(GerarLoteAtualizacaoCadastralForm form) {
		this.idLocalidadeInicial = Integer.valueOf(form.getIdLocalidadeInicial());
		this.cdSetorComercialInicial = Integer.valueOf(form.getCdSetorComercialInicial());
		this.periodoInicial = form.getPeriodoInicial();
		this.periodoFinal = form.getPeriodoFinal();
		this.idLeiturista = Integer.valueOf(form.getIdLeiturista());
		this.imoveisNovos = Integer.valueOf(form.getImoveisNovos());
		this.grandesConsumidores = Integer.valueOf(form.getGrandesConsumidores());
		this.ocorrenciaCadastro = Integer.valueOf(form.getOcorrenciaCadastro());
		this.ocorrenciaCadastroSelecionada = Integer.valueOf(form.getOcorrenciaCadastroSelecionada());
	}

	public int getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(int idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public int getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(int idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public int getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(int cdSetorComercialInicial) {
		this.cdSetorComercialInicial = cdSetorComercialInicial;
	}

	public int getImoveisNovos() {
		return imoveisNovos;
	}

	public void setImoveisNovos(int imoveisNovos) {
		this.imoveisNovos = imoveisNovos;
	}

	public int getGrandesConsumidores() {
		return grandesConsumidores;
	}

	public void setGrandesConsumidores(int grandesConsumidores) {
		this.grandesConsumidores = grandesConsumidores;
	}

	public int getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(int ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public int getOcorrenciaCadastroSelecionada() {
		return ocorrenciaCadastroSelecionada;
	}

	public void setOcorrenciaCadastroSelecionada(int ocorrenciaCadastroSelecionada) {
		this.ocorrenciaCadastroSelecionada = ocorrenciaCadastroSelecionada;
	}
}
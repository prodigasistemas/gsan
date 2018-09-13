package gcom.atualizacaocadastral;

import java.util.Date;

import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;

public class Visita extends ObjetoTransacao {
    
    private static final long serialVersionUID = 3619726654733392236L;
    public static final Integer QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO = 3;
    private Integer id;
    private ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral;
    private CadastroOcorrencia cadastroOcorrencia;
    private Date ultimaAlteracao;
    private String coordenadaX;
    private String coordenadaY;

    public Visita() {}

    public Visita(ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral) {
        this.imovelControleAtualizacaoCadastral = imovelControleAtualizacaoCadastral;
        this.cadastroOcorrencia = imovelControleAtualizacaoCadastral.getCadastroOcorrencia();
        setUltimaAlteracao(new Date());
    }

    public Visita(ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral, String coordenadaX, String coordenadaY) {
        this.imovelControleAtualizacaoCadastral = imovelControleAtualizacaoCadastral;
        this.cadastroOcorrencia = imovelControleAtualizacaoCadastral.getCadastroOcorrencia();
        setCoordenadaX(coordenadaX);
        setCoordenadaY(coordenadaY);
        setUltimaAlteracao(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImovelControleAtualizacaoCadastral getImovelControleAtualizacaoCadastral() {
        return imovelControleAtualizacaoCadastral;
    }

    public void setImovelControleAtualizacaoCadastral(
            ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral) {
        this.imovelControleAtualizacaoCadastral = imovelControleAtualizacaoCadastral;
    }

    public CadastroOcorrencia getCadastroOcorrencia() {
        return cadastroOcorrencia;
    }

    public void setCadastroOcorrencia(CadastroOcorrencia cadastroOcorrencia) {
        this.cadastroOcorrencia = cadastroOcorrencia;
    }

    @Override
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    @Override
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * @return the coordenadaX
     */
    public String getCoordenadaX() {
        return coordenadaX;
    }

    /**
     * @param coordenadaX the coordenadaX to set
     */
    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    /**
     * @return the coordeandaY
     */
    public String getCoordenadaY() {
        return coordenadaY;
    }

    /**
     * @param coordenadaY the coordenadaY to set
     */
    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    @Override
    public Filtro retornaFiltro() {
        FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
        filtroTabelaAuxiliar.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliar.ID, this.getId()));
        
        return filtroTabelaAuxiliar;
    }

    @Override
    public String[] retornaCamposChavePrimaria(){
        String[] retorno = new String[1];
        retorno[0] = "id";
        return retorno;
    }
}

package gcom.cadastro.imovel;

public interface IImovelTipoOcupanteQuantidade {

    public Integer getQuantidade();

    public void setQuantidade(Integer quantidade);

    public Imovel getImovel();

    public void setImovel(Imovel imovel);

    public ImovelTipoOcupante getTipoOcupante();

    public void setTipoOcupante(ImovelTipoOcupante tipoOcupante);
}

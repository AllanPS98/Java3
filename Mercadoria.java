package Model;
/**
 * 
 * @author allan
 */
public class Mercadoria implements Comparable{
    private String codigoDeLote, bloco, numero, fornecedor, localizacao, endereco;
    private String data;
    private String hora;
    /**
     * 
     * @param codigo
     * @param bloco
     * @param numero
     * @param fornecedor
     * @param data
     * @param hora
     * @param endereco 
     */
    public Mercadoria(String codigo, String bloco, String numero, String fornecedor, String data, String hora, String endereco) {
        this.codigoDeLote = codigo;
        this.bloco = bloco;
        this.numero = numero;
        this.fornecedor = fornecedor;
        this.data = data;
        this.hora = hora;
        this.endereco = endereco;
    }
    /**
     * Construtor vazio
     */
    public Mercadoria() {
    }
    /**
     * 
     * @return String
     */
    public String getEndereco() {
        return endereco;
    }
    /**
     * 
     * @param endereco 
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    /**
     * 
     * @return String
     */
    public String getCodigoDeLote() {
        return codigoDeLote;
    }
    /**
     * 
     * @return String
     */
    public String getFornecedor() {
        return fornecedor;
    }
    /**
     * 
     * @param codigo 
     */
    public void setCodigoDeLote(String codigo) {
        this.codigoDeLote = codigo;
    }
    /**
     * 
     * @param fornecedor 
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    /**
     * 
     * @return String
     */
    public String getBloco() {
        return bloco;
    }
    /**
     * 
     * @param bloco 
     */
    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
    /**
     * 
     * @return String
     */
    public String getNumero() {
        return numero;
    }
    /**
     * 
     * @param numero 
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * 
     * @return String
     */
    public String getLocalizacao(){
        this.localizacao = this.codigoDeLote+this.endereco+this.bloco + this.numero + this.fornecedor;
        return localizacao;
    }
    /**
     * 
     * @return String
     */
    public String getData() {
        return data;
    }
    /**
     * 
     * @param data 
     */
    public void setData(String data) {
        this.data = data;
    }
    /**
     * 
     * @return String
     */
    public String getHora() {
        return hora;
    }
    /**
     * 
     * @param hora 
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
    /**
     * 
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        Mercadoria m = (Mercadoria) o;
        if(this.getLocalizacao().compareTo(m.getLocalizacao())>0){
            return 1;
        }else if(this.getLocalizacao().compareTo(m.getLocalizacao())==0){
            return 0;
        }else{
            return -1;
        }
    }
    /**
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Mercadoria{ " + "localização = " + localizacao + " }";
    }
    
}

package Util;
/**
 * 
 * @author allan
 */
public class Fila implements IFila{
    private Celula primeiro;
    private Celula ultimo;
    private int total;
    private class Celula { 
        private Object dados;
        private Celula prox;
        /**
         * 
         * @param o 
         */
        public Celula(Object o) {
            this.dados = o;
        }
        /**
         * 
         * @return Object
         */
        public Object getDados() {
            return dados;
        }
        /**
         * 
         * @param o 
         */
        public void setDados(Object o) {
            this.dados = o;
        }
        /**
         * 
         * @return Object
         */
        public Celula getProx() {
            return prox;
        }
        /**
         * 
         * @param prox 
         */
        public void setProx(Celula prox) {
            this.prox = prox;
        }

    }
    /**
     * 
     * @return boolean
     */
    public boolean estaVazia() {
        return this.total==0;
    }
    /**
     * 
     * @return int
     */
    public int obterTamanho() {
        return this.total;
    }
    /**
     * 
     * @param o 
     */
    public void inserirFinal(Object o) {
        Celula novo = new Celula(o);
        if(o==null){
            return;
        }
        if(this.total==0){
            this.primeiro = this.ultimo = novo;
        }else{
            this.ultimo.setProx(novo);
            this.ultimo = novo;
        }
        this.total++;
    }
    /**
     * 
     * @return Object
     */
    public Object removerInicio() {
        Celula aux = this.primeiro;
        if(this.total==0){
            return null;
        }else if(this.total==1){
            this.primeiro = this.ultimo = null;
        }
        else{
            this.primeiro = this.primeiro.getProx();    
        }
        this.total--;
        return aux.getDados();
    }
    /**
     * 
     * @return Object 
     */
    public Object recuperarInicio() {
        return primeiro.getDados();
    }
    
}

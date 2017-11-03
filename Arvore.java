package Util;
/**
 * 
 * @author allan
 */
public class Arvore {
    private No raiz;
    private int tamanho;
    private class No{
        private Comparable data;
        private No pai, esquerda, direita;
        private int balanceamento;
        /**
         * 
         * @param pai
         * @param data 
         */
        public No(No pai, Comparable data){
            this.pai = pai;
            this.data = data;
            this.balanceamento = 0;
        }
        /**
         * 
         * @param data 
         */
        public No(Comparable data) {
            this.data = data;
        }
        /**
         * 
         * @return Comparable
         */
        public Comparable getData() {
            return this.data;
        }
        /**
         * 
         * @param data 
         */
        public void setData(Comparable data){
            this.data = data;
        }
        /**
         * 
         * @return No
         */
        public No getPai() {
            return pai;
        }
        /**
         * 
         * @param pai 
         */
        public void setPai(No pai) {
            this.pai = pai;
        }
        /**
         * 
         * @return No
         */
        public No getEsquerda() {
            return esquerda;
        }
        /**
         * 
         * @param esquerda 
         */
        public void setEsquerda(No esquerda) {
            this.esquerda = esquerda;
        }
        /**
         * 
         * @return No
         */
        public No getDireita() {
            return direita;
        }
        /**
         * 
         * @param direita 
         */
        public void setDireita(No direita) {
            this.direita = direita;
        }
        /**
         * 
         * @return int
         */
        public int getBalanceamento() {
            return balanceamento;
        }
        /**
         * 
         * @param balanceamento 
         */
        public void setBalanceamento(int balanceamento) {
            this.balanceamento = balanceamento;
        }
        
    }
    /**
     * 
     * @return No
     */
    public No getRaiz(){
        return this.raiz;
    }
    /**
     * 
     * @param o 
     */
    public void inserir(Comparable o){
        No n = new No(o);
        inserir(raiz,n);
        this.tamanho++;
    }
    /**
     * 
     * @param node
     * @param o 
     */
    private void inserir(No node, No o){
        if(this.raiz == null){
            this.raiz = o;
        }else{
            if(o.getData().compareTo(node.getData())<0){
                if(node.getEsquerda()==null){
                    node.setEsquerda(o);
                    o.setPai(node);
                    verificarBalanceamento(o);
                }else{
                    inserir(node.getEsquerda(),o);
                }
            }else if(o.getData().compareTo(node.getData())>0){
                if(node.getDireita()==null){
                    node.setDireita(o);
                    o.setPai(node);
                    verificarBalanceamento(o);
                }else{
                    inserir(node.getDireita(),o);
                }
            }else{
                
            }
        }
        
    }
    /**
     * 
     * @return Iterador
     */
    public Iterator iterador(){
        return new Iterator();
    }
    /**
     * Iterador que retorna Comparable's
     */
    private class Iterator implements Iterador{
        private Fila fila = new Fila();
        /**
         * Construtor 
         */
        public Iterator() {
            fila.inserirFinal(raiz);
        }
        /**
         * 
         * @return boolean
         */
        @Override
        public boolean temProximo() {
            return !fila.estaVazia();
        }
        /**
         * 
         * @return boolean
         */
        @Override
        public Comparable obterProximo() {
            No n = (No) fila.removerInicio();
            if(n.getEsquerda()!=null){
                fila.inserirFinal(n.getEsquerda());
            }
            if(n.getDireita()!=null){
                fila.inserirFinal(n.getDireita());
            }
            return n.getData();
        }
        
    }
    /**
     * 
     * @return MyIterador
     */
    private MyIterator iteradorNo(){
        return new MyIterator();
    }
    /**
     * Iterador que retorna nós 
     */
    private class MyIterator implements Iterador{
        private Fila fila = new Fila();
        /**
         * Construtor
         */
        public MyIterator() {
            fila.inserirFinal(raiz);
        }
        /**
         * 
         * @return boolean
         */
        @Override
        public boolean temProximo() {
            return !fila.estaVazia();
        }
        /**
         * 
         * @return No
         */
        @Override
        public No obterProximo() {
            No n = (No) fila.removerInicio();
            if(n.getEsquerda()!=null){
                fila.inserirFinal(n.getEsquerda());
            }
            if(n.getDireita()!=null){
                fila.inserirFinal(n.getDireita());
            }
            return n;
        }
        
    }
    /**
     * 
     * @param o
     * @return Comparable
     */
    public Comparable remove(Comparable o){
        MyIterator itr = this.iteradorNo();
        No n1 = new No(o);
        No n = null;
        while(itr.temProximo()){
            n = itr.obterProximo();
            if(n.getData().compareTo(n1.getData())==0){
                break;
            }
        }
        if(n == null){
            return null;
        }else{
            if(n.getEsquerda()!=null && n.getDireita()!=null){
                n.setData(n.getEsquerda().getData());
                remove(n.getEsquerda());
            }else if(n.getEsquerda()!=null || n.getDireita()!=null){
                No aux = n.getEsquerda()!=null? n.getEsquerda() : n.getDireita();
                replace(n,aux);
                verificarBalanceamento(n.getPai());
            }else{
                replace(n,null);
            }  
        }
        
        this.tamanho--;
        return n.getData();
    }
    /**
     * 
     * @param n 
     */
    private void remove(No n){
        if(n.getEsquerda()!=null && n.getDireita()!=null){
            n.setData(n.getEsquerda().getData());
            remove(n.getEsquerda());
        }else if(n.getEsquerda()!=null || n.getDireita()!=null){
            No aux = n.getEsquerda()!=null? n.getEsquerda() : n.getDireita();
            replace(n,aux);
        }else{
            replace(n,null);
        }  
    }
    /**
     * 
     * @param no1
     * @param no2 
     */
    private void replace(No no1, No no2){
        if(no1 == this.getRaiz()){
            this.raiz = no2;
        }else{
            if(no1 == no1.getPai().getEsquerda()){
                no1.getPai().setEsquerda(no2);
            }else{
                no1.getPai().setDireita(no2);
            }
        }
    }
    /**
     * 
     * @return int 
     */
    public int size(){
        return this.tamanho;
    }
    /**
     * 
     * @return Fila
     */
    public Fila inOrdem(){
        Fila fila = new Fila();
        if(raiz==null){
            return null;
        }
        inOrdem(raiz, fila);
        if(fila.estaVazia()){
            return null;
        }
        return fila;
    }
    /**
     * 
     * @param no
     * @param queue 
     */
    private void inOrdem(No no, Fila queue){
        if(no.getEsquerda()!=null){
            inOrdem(no.getEsquerda(), queue);
        }
        queue.inserirFinal(no.data);
        if(no.getDireita()!=null){
            inOrdem(no.getDireita(), queue);
        }
    }
    /**
     * 
     * @param chave
     * @return Comparable
     */
    public Comparable buscaBin(Comparable chave){
        Comparable o = buscaBin(chave, raiz);
        return o;
    }
    private Comparable buscaBin(Comparable chave, No no){
        if(no==null){
            return chave;
        }
        if(chave.compareTo(no.getData())<0){
            buscaBin(chave, no.getEsquerda());
        }else if(chave.compareTo(no.getData())>0){
            buscaBin(chave, no.getDireita());
        }
        return no.getData();
    }
    /**
     * 
     * @param n
     * @return int
     */
    public int altura(No n){
        if (n == null) {
            return -1;
        }

        if (n.getEsquerda() == null && n.getDireita() == null) {
            return 0;

        } else if (n.getEsquerda() == null) {
            return 1 + altura(n.getDireita());

        } else if (n.getDireita() == null) {
            return 1 + altura(n.getEsquerda());

        } else {
            return 1 + Math.max(altura(n.getEsquerda()), altura(n.getDireita()));
        }
    }
    /**
     * 
     * @param inicial
     * @return No
     */
    private No rotacaoEsquerda(No inicial) {

        No direita = inicial.getDireita();
        direita.setPai(inicial.getPai());

        inicial.setDireita(direita.getEsquerda());

        if (inicial.getDireita() != null) {
            inicial.getDireita().setPai(inicial);
        }

        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {
            if (direita.getPai().getDireita() == inicial) {
                direita.getPai().setDireita(direita);

            } else if (direita.getPai().getEsquerda() == inicial) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }
    /**
     * 
     * @param inicial
     * @return No
     */
    private No rotacaoDireita(No inicial) {
        No esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());

        inicial.setEsquerda(esquerda.getDireita());

        if (inicial.getEsquerda() != null) {
            inicial.getEsquerda().setPai(inicial);
        }

        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {
            if (esquerda.getPai().getDireita() == inicial) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == inicial) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }
    /**
     * 
     * @param inicial
     * @return No
     */
    private No duplaRotacaoEsquerdaDireita(No inicial) {
        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
        return rotacaoDireita(inicial);
    }
    /**
     * 
     * @param inicial
     * @return No
     */
    private No duplaRotacaoDireitaEsquerda(No inicial) {
        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
        return rotacaoEsquerda(inicial);
    }
    /**
     * 
     * @param no 
     */
    private void setBalanceamento(No no){
        no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }
    /**
     * 
     * @param atual 
     */
    private void verificarBalanceamento(No atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2) {
            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                atual = rotacaoDireita(atual);
            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }
        } else if (balanceamento == 2) {
            if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                atual = rotacaoEsquerda(atual);
            } else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }
        if (atual.getPai() != null) {
            verificarBalanceamento(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }   
}

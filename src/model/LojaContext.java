package model;

public class LojaContext {
        private Loja lojaAtual;

        public Loja getLojaAtual() {
            return lojaAtual;
        }

        public void setLojaAtual(Loja loja) {
            this.lojaAtual = loja;
        }

        public boolean isLojaSelecionada() {
            return lojaAtual != null;
        }

}


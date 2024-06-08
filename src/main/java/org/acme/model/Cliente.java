    package org.acme.model;

    import io.quarkus.hibernate.orm.panache.PanacheEntity;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;

    @Entity
    public class Cliente extends PanacheEntity {
        @Column(nullable = false, unique = true)
        public int codigo;
        @Column(nullable = false)
        public String nome;

        public Cliente() {
        }

        public Cliente(int codigo, String nome) {
            this.codigo = codigo;
            this.nome = nome;
        }

        public Cliente(String nome) {
            this.nome = nome;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                '}';
        }
    }

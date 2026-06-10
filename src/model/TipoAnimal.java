package model;

public enum TipoAnimal {
    GATO("gato"),
    CACHORRO("cachorro");
    private String tipo;

    TipoAnimal(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

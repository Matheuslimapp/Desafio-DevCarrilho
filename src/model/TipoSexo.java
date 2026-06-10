package model;

public enum TipoSexo {
    MACHO("macho"),
    FEMEA("femea");

    private String sexo;

    TipoSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}

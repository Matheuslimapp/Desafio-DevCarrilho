package model;

public class Pet {
    public String nome;
    public TipoAnimal tipoAnimal;
    public TipoSexo tipoSexo;
    public Endereco endereco;
    public String idade;
    public String peso;
    public String raca;

    public Pet(String nome, TipoAnimal tipoAnimal, TipoSexo tipoSexo, Endereco endereco, String idade, String peso, String raca) {
        this.nome = nome;
        this.tipoAnimal = tipoAnimal;
        this.tipoSexo = tipoSexo;
        this.tipoSexo = tipoSexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }
}

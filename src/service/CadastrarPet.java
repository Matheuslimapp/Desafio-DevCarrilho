package service;

import model.Endereco;
import model.Pet;
import model.TipoAnimal;
import model.TipoSexo;
import repository.FileRepository;
import validator.PetValidator;

import java.util.Scanner;

public class CadastrarPet {
    public static Pet cadastrarPet(){
        Scanner scanner = new Scanner(System.in);
        TipoAnimal tipoAnimal = null;
        TipoSexo tipoSexo;
        Endereco endereco;
        final String NAOINFORMADO = "NÃO INFORMADO";
        String idade;
        String peso;
        FileRepository.formularioWrite();

        //Nome
        FileRepository.formularioRead(1);
        String nomeCompleto = scanner.nextLine();
        PetValidator.nomeValidator(nomeCompleto);

        //Tipo do Animal
        FileRepository.formularioRead(2);
        String tipoAnimalS = scanner.nextLine();
        if(tipoAnimalS.equalsIgnoreCase("cachorro")){
            tipoAnimal = TipoAnimal.CACHORRO;
        }if(tipoAnimalS.equalsIgnoreCase("gato")){
            tipoAnimal = TipoAnimal.GATO;
        }else{
            tipoAnimal = null;
        }

        //Sexo
        FileRepository.formularioRead(3);
        System.out.println("Macho ou Femea.");
        String sexo = scanner.nextLine();
        if (sexo.equalsIgnoreCase("femea")){
            tipoSexo = TipoSexo.FEMEA;
        }if (sexo.equalsIgnoreCase("macho")){
            tipoSexo = TipoSexo.MACHO;
        }else{
            tipoSexo = null;
        }

        //model.Endereco
        FileRepository.formularioRead(4);
        System.out.println("Número da casa.");
        String numeroCasa = scanner.nextLine();
        System.out.println("Cidade.");
        String cidade = scanner.nextLine();
        System.out.println("Rua.");
        String rua = scanner.nextLine();
        if(numeroCasa.isBlank()){
            numeroCasa = NAOINFORMADO;
        }
        endereco = new Endereco(numeroCasa,cidade,rua);

        //Idade
        FileRepository.formularioRead(5);
        idade = scanner.nextLine();
        PetValidator.idadeValidator(idade);

        //Peso
        FileRepository.formularioRead(6);
        peso = scanner.nextLine();
        PetValidator.pesoValidator(peso);

        //Raca
        FileRepository.formularioRead(7);
        String raca = scanner.nextLine();
        PetValidator.racaValidator(raca);

        Pet pet = new Pet(nomeCompleto, tipoAnimal,tipoSexo, endereco, idade, peso, raca);
        FileRepository.salvarPet(pet);
        return pet;
    }
}

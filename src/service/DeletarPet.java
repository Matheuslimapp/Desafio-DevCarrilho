package service;

import model.Endereco;
import model.Pet;
import repository.FileRepository;
import validator.PetValidator;

import java.util.List;
import java.util.Scanner;

public class DeletarPet {
    public static void deletarPet(){
        Scanner scanner = new Scanner(System.in);
        Endereco endereco;
        BuscarPet buscarPet = new BuscarPet();
        List<Pet> listaFiltrada = buscarPet.menuBuscar();
        System.out.println("Escolha o número do pet:");
        int opcao = PetValidator.lerNValido(scanner);
        if(opcao < 1 || opcao > listaFiltrada.size()){
            System.out.println("Número inválido");
            deletarPet();
            return;
        }
        Pet petSelecionado = listaFiltrada.get(opcao - 1);
        String nome = petSelecionado.nome;
        System.out.println("VOCÊ TEM CERTEZA QUE DESEJA EXECLUIR??, NAO OU SIM");
        String deletar = scanner.nextLine();
        if (deletar.equalsIgnoreCase("SIM")){
            FileRepository.deletarArquivoAntigo(nome);
            System.out.println("model.Pet deletado com sucesso!!");
        }else{
            System.out.println("O model.Pet nao foi deletado");
        }
    }

    public static void main(String[] args) {
        deletarPet();
    }
}

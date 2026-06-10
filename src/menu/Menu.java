package menu;

import service.*;
import validator.PetValidator;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BuscarPet buscarPet = new BuscarPet();
        int opcao = 0;
        do {
                System.out.println("1-Cadastrar um novo pet\n" + "2-Alterar os dados do pet cadastrado\n" + "3-Deletar um pet cadastrado\n" + "4-Listar todos os pets cadastrados\n" + "5-Listar pets por algum critério (idade, nome, raça)\n" + "6-Sair");
                opcao = PetValidator.lerNValido(scanner);
                if (opcao == 1){
                    CadastrarPet.cadastrarPet();
                } else if (opcao == 2) {
                    AlterarPet.alterarPet();
                } else if (opcao == 3) {
                    DeletarPet deletarPet;
                } else if (opcao == 4) {
                    ListarPet listarPet = new ListarPet();
                    listarPet.imprimirLista();
                } else if (opcao == 5){
                    buscarPet.menuBuscar();
                }
        } while (opcao != 6);
    }
}

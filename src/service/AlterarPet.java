package service;

import model.Endereco;
import model.Pet;
import repository.FileRepository;
import validator.PetValidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AlterarPet {
    public static void alterarPet(){
        Scanner scanner = new Scanner(System.in);
        Endereco endereco;

        BuscarPet buscarPet = new BuscarPet();
        List<Pet> listaFiltrada = buscarPet.menuBuscar();
        System.out.println("Escolha o número do pet:");
        int opcao = PetValidator.lerNValido(scanner);
        if(opcao < 1 || opcao > listaFiltrada.size()){
            System.out.println("Número inválido");
            alterarPet();
            return;
        }
        Pet petSelecionado = listaFiltrada.get(opcao - 1);

        String nomeAntigo = petSelecionado.nome;
        //Nome
        System.out.println("Você quer mudar o nome?sim digite o novo valor, não aperte enter");
        String nomeCompleto = scanner.nextLine();
        PetValidator.nomeValidator(nomeCompleto);
        if (!nomeCompleto.isBlank()){
            petSelecionado.nome = nomeCompleto;
        }

        //Endereco
        System.out.println("Você quer mudar o endereco?sim digite  1, não aperte enter");
        String mudarEndereco = scanner.nextLine();
        if (!mudarEndereco.isBlank()){
        int enderecoOpcao = Integer.parseInt(mudarEndereco);
        if (enderecoOpcao == 1) {
            System.out.println("Número da casa.");
            String numeroCasa = scanner.nextLine();
            System.out.println("Cidade.");
            String cidade = scanner.nextLine();
            System.out.println("Rua.");
            String rua = scanner.nextLine();
            if (numeroCasa.isBlank()) {
                numeroCasa = "NÃO INFORMADO";
            }
            endereco = new Endereco(numeroCasa, cidade, rua);
            petSelecionado.endereco = endereco;
            }
        }

        //Idade
        System.out.println("Você quer mudar a idade?sim digite o novo valor, não aperte enter");
        String idade = scanner.nextLine();
        PetValidator.idadeValidator(idade);
        if (!idade.isBlank()) {
            petSelecionado.idade = idade;
        }

        //Peso
        System.out.println("Você quer mudar o peso?sim digite o novo valor, não aperte enter");
        String peso = scanner.nextLine();
        PetValidator.pesoValidator(peso);
        if (!peso.isBlank()) {
            petSelecionado.peso = peso;
        }

        //Raca
        System.out.println("Você quer mudar a raça?sim digite o novo valor, não aperte enter");
        String raca = scanner.nextLine();
        PetValidator.racaValidator(raca);
        if (!raca.isBlank()) {
            petSelecionado.raca = raca;
        }

        FileRepository.deletarArquivoAntigo(nomeAntigo);
        String nomeFormatado = petSelecionado.nome.replace(" ", "").toUpperCase();

        // Pegando a data e hora atual
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHoraFormatada = agora.format(formatter);

        String nomeArquivo = dataHoraFormatada + "-" + nomeFormatado + ".txt";
        File diretorio = new File("petsCadastrados");
        File arquivo = new File(diretorio, nomeArquivo);
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(arquivo))) {

            bw.write("1 - " + petSelecionado.nome);
            bw.newLine();

            bw.write("2 - " + petSelecionado.tipoAnimal);
            bw.newLine();

            bw.write("3 - " + petSelecionado.tipoSexo);
            bw.newLine();

            bw.write("4 - Rua:" + petSelecionado.endereco.rua + ", "
                    + petSelecionado.endereco.numeroCasa + ", "
                    + petSelecionado.endereco.cidade);
            bw.newLine();

            bw.write("5 - " + petSelecionado.idade + " anos");
            bw.newLine();

            bw.write("6 - " + petSelecionado.peso + " kg");
            bw.newLine();

            bw.write("7 - " + petSelecionado.raca);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        alterarPet();
    }
}

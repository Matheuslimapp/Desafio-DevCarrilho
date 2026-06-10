package repository;

import model.Pet;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileRepository {
    //Escrever arquivo
    public static void formularioWrite() {
        File dir = new File("Formulario");
        File file = new File("Formulario//formulario.txt");

        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("1 - Qual o nome e sobrenome do pet?\n" + "2 - Qual o tipo do pet (Cachorro/Gato)?\n" + "3 - Qual o sexo do animal?\n" + "4 - Qual endereço e bairro que ele foi encontrado?\n" + "5 - Qual a idade aproximada do pet?\n" + "6 - Qual o peso aproximado do pet?\n" + "7 - Qual a raça do pet?");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //ler formulario
    public static void formularioRead(int numeroLinha) {
        File file = new File("Formulario/formulario.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 1; i < numeroLinha; i++) {
                br.readLine();
            }
            System.out.println(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Salvar pet
    public static void salvarPet(Pet pet) {
        File dir = new File("petsCadastrados");
        if (!dir.exists()) {
            dir.mkdir();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String data = localDateTime.format(formatter);
        String formatacaoArquivo = data + "-" + pet.nome.replace(" ", "").toUpperCase() + ".txt";
        File file = new File(dir, formatacaoArquivo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("1 - " + pet.nome);
            bw.newLine();

            bw.write("2 - " + pet.tipoAnimal);
            bw.newLine();

            bw.write("3 - " + pet.tipoSexo);
            bw.newLine();

            bw.write("4 - Rua: " + pet.endereco.rua + ", " +
                    pet.endereco.numeroCasa + ", " +
                    pet.endereco.cidade);
            bw.newLine();
            double idadeI = Double.parseDouble(pet.idade);

            bw.write("5 - " + pet.idade + " anos");
            bw.newLine();

            bw.write("6 - " + pet.peso + " kg");
            bw.newLine();

            bw.write("7 - " + pet.raca);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deletarArquivoAntigo(String nomeAntigoPet) {
        Path pathCadastrados = Paths.get("petsCadastrados");
        File file = new File(String.valueOf(pathCadastrados.toAbsolutePath()));
        File[] arquivos = file.listFiles();
        if (arquivos == null) {
            return;
        }


        String nomeFormatado = nomeAntigoPet.replaceAll("\\s+", "").toUpperCase();

        for (File arquivo : arquivos) {
            if (arquivo.getName().toUpperCase().contains("-" + nomeFormatado + ".TXT")) {
                System.out.println("Arquivo encontrado: " + arquivo.getName()); // Debug
                if (arquivo.delete()) {
                    System.out.println("Arquivo deletado com sucesso: " + arquivo.getName());
                } else {
                    System.out.println("Erro ao deletar: " + arquivo.getName());
                }
                return;
            }
        }
    }
}


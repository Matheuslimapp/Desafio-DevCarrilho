package service;

import model.Endereco;
import model.Pet;
import model.TipoAnimal;
import model.TipoSexo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListarPet {
    public List<Pet> ListarPet() {
        Scanner scanner = new Scanner(System.in);
        File pasta = new File("petsCadastrados");
        File[] arquivos = pasta.listFiles();
        List<Pet> pets = new ArrayList<>();
        for (File arquivo : arquivos) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {
                String nome = bufferedReader.readLine().substring(4);

                TipoAnimal tipoAnimal =
                        TipoAnimal.valueOf(bufferedReader.readLine().substring(4));

                TipoSexo tipoSexo =
                        TipoSexo.valueOf(bufferedReader.readLine().substring(4));

                String enderecoLinha = bufferedReader.readLine().substring(4);

                String idade = bufferedReader.readLine()
                        .substring(4)
                        .replace(" anos", "")
                        .replace(" meses", "");

                String peso = bufferedReader.readLine()
                        .substring(4)
                        .replace(" kg", "");

                String raca = bufferedReader.readLine().substring(4);

                String enderecoTexto = enderecoLinha.replace("Rua: ", "");

                String[] dadosEndereco = enderecoTexto.split(",");

                String rua = dadosEndereco[0].trim();
                String numeroCasa = dadosEndereco[1].trim();
                String cidade = dadosEndereco[2].trim();

                Endereco endereco = new Endereco(
                        numeroCasa,
                        cidade,
                        rua
                );

                Pet pet = new Pet(
                        nome,
                        tipoAnimal,
                        tipoSexo,
                        endereco,
                        idade,
                        peso,
                        raca
                );
                pets.add(pet);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pets;
    }
        public void formatarListaPets(List<Pet> listaPets) {
            if (listaPets == null) {
                return;
            }

            if (listaPets.isEmpty()) {
                System.out.println("Nenhum pet encontrado.");
                return;
            }

            int contador = 1;
            for (Pet pet : listaPets) {
                System.out.println(String.format(
                        "%d. %s - %s - %s - %s, %s - %s - %s anos - %s kg - %s",
                        contador++,
                        pet.nome,
                        pet.tipoSexo,
                        pet.tipoAnimal,
                        pet.endereco.rua,
                        pet.endereco.numeroCasa,
                        pet.endereco.cidade,
                        pet.idade,
                        pet.peso,
                        pet.raca
                ));
        }
    }
    public void imprimirLista(){
        formatarListaPets(ListarPet());
    }
}
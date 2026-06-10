package service;

import model.Endereco;
import model.Pet;
import model.TipoAnimal;
import model.TipoSexo;
import validator.PetValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarPet {
    public static List<Pet> buscarPet() {
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
    public ArrayList<Pet> menuBuscar(){
        ArrayList<Pet> listaPetsConvertidos;
        List<Pet> listaFiltrada;
        TipoAnimal tipoAnimal;
        listaPetsConvertidos = (ArrayList<Pet>) buscarPet();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual e o tipo de animal que você quer filtra\n1- cachorro\n2 - gato");
        int tipoDoAnimal = scanner.nextInt();
        if (tipoDoAnimal == 1) {
            tipoAnimal = TipoAnimal.CACHORRO;
        } else if (tipoDoAnimal == 2) {
            tipoAnimal = TipoAnimal.GATO;
        } else {
            System.out.println("Opção inválida! Digite apenas 1 ou 2.");
            return listaPetsConvertidos;
        }

        TipoAnimal finalTipoEscolhido = tipoAnimal;
        listaFiltrada = listaPetsConvertidos.stream()
                .filter(pet -> pet.tipoAnimal == finalTipoEscolhido)
                .toList();

        String perguntaBuscar = "1- Nome ou sobrenome\n" + "2 - Sexo\n" + "3 - Idade\n" + "4 - Peso\n" + "5 - Raça\n" + "6 - Endereço";
        System.out.println("Escolha um critério para filtrar os pets:");
        System.out.println(perguntaBuscar);
        int escolha1 = PetValidator.lerNValido(scanner);

        System.out.println("Escolha um segundo critério para filtrar os pets:");
        System.out.println(perguntaBuscar);
        int escolha2 = PetValidator.lerNValido(scanner);

        if (escolha1 == 1 || escolha2 == 1) {
            System.out.println("Digite o nome ou sobrenome do pet que deseja buscar:");
            String termoBusca = scanner.next().toLowerCase().trim();

            if (termoBusca.isBlank()) {
                System.out.println("Nenhum nome digitado! Tente novamente.");
            } else {
                listaFiltrada = filtraPorNome(termoBusca, listaFiltrada);
            }
        }

        if (escolha1 == 2 || escolha2 == 2) {
            System.out.println("Você deseja procurar por: 1 - Macho ou 2 - Femea?");
            int escolhaGenero = PetValidator.lerNValido(scanner);

            TipoSexo generoEscolhido = null;
            if (escolhaGenero == 1) {
                generoEscolhido = TipoSexo.MACHO;
            } else if (escolhaGenero == 2) {
                generoEscolhido = TipoSexo.FEMEA;
            } else {
                System.out.println("Opção inválida! Digite apenas 1 ou 2.");
                return listaPetsConvertidos;
            }

            Enum finalGenEscolha = generoEscolhido;
            listaFiltrada = listaFiltrada.stream()
                    .filter(pet -> pet.tipoSexo == finalGenEscolha)
                    .toList();

        }

        if (escolha1 == 3 || escolha2 == 3) {
            System.out.print("Digite a idade para buscar: ");
            int idadeBusca = PetValidator.lerNValido(scanner);
            listaFiltrada = filtraPorIdade(idadeBusca, listaFiltrada);
        }

        if (escolha1 == 4 || escolha2 == 4) {
            System.out.print("Digite o peso para buscar: ");
            float pesoBusca = scanner.nextFloat();
            listaFiltrada = filtraPorPeso(pesoBusca, listaFiltrada);
        }

        if (escolha1 == 5 || escolha2 == 5) {
            System.out.println("Digite a raça que deseja buscar:");
            String buscarRaca;
            buscarRaca = scanner.nextLine().trim().toLowerCase();


            if (buscarRaca.isBlank()) {
                System.out.println("Nenhuma raça digitada! Tente novamente.");
            } else {
                listaFiltrada = filtraPorRaca(buscarRaca, listaFiltrada);
            }
        }

        if (escolha1 == 6 || escolha2 == 6) {
            System.out.println("Digite o endereço que deseja buscar:");
            String buscarAddress = scanner.next().trim().toLowerCase();

            if (buscarAddress.isBlank()) {
                System.out.println("Nenhum endereço digitado! Tente novamente.");
            } else {
                listaFiltrada = filtraPorEndereco(buscarAddress, listaFiltrada);
            }
        }
        formatarListaPets(listaFiltrada);

        return listaPetsConvertidos;
    }

    private List<Pet> filtraPorNome(String nomeBuscado, List<Pet> petArrayList) {
        List<Pet> newList = petArrayList.stream()
                .filter(pet -> pet.nome.toLowerCase().contains(nomeBuscado)).toList();
        return newList;
    }


    private List<Pet> filtraPorIdade(int idadeBuscada, List<Pet> petArrayList) {
        return petArrayList.stream()
                .filter(pet -> {
                    try {
                        return Integer.parseInt(pet.idade) == idadeBuscada;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
    }

    private List<Pet> filtraPorPeso(double pesoBuscado, List<Pet> petArrayList) {
        return petArrayList.stream()
                .filter(pet -> {
                    try {
                        return Double.parseDouble(pet.peso) == pesoBuscado;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
    }

    private List<Pet> filtraPorRaca(String nomeBuscado, List<Pet> petArrayList) {
        return petArrayList.stream()
                .filter(pet -> pet.raca.toLowerCase().contains(nomeBuscado.toLowerCase())).toList();
    }

    private List<Pet> filtraPorEndereco(String enderecoBuscado, List<Pet> petArrayList) {
        return petArrayList.stream()
                .filter(pet ->
                        pet.endereco.rua.toLowerCase().contains(enderecoBuscado.toLowerCase())
                                ||
                                pet.endereco.cidade.toLowerCase().contains(enderecoBuscado.toLowerCase())
                )
                .toList();
    }

    public void formatarListaPets(List<Pet> listaPets) {
        if (listaPets == null) {
            listaPets = buscarPet();
        }

        if (listaPets.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios selecionados.");
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

    public static void main(String[] args) {
        BuscarPet buscarPet = new BuscarPet();
        buscarPet.menuBuscar();
    }
}


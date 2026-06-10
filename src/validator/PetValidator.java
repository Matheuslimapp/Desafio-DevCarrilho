package validator;

import exception.PetException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetValidator {
    public static void nomeValidator(String nome){
        final String NAOINFORMADO = "NÃO INFORMADO";
        Pattern patternNome = Pattern.compile("[A-Za-zÀ-ÿ]+\\s+[A-Za-zÀ-ÿ]+");
        Matcher matcherNome = patternNome.matcher(nome);
        if (nome.isBlank()){
            nome = NAOINFORMADO;
        }
        else {
            if (!matcherNome.matches()) {
                System.out.println("Digite nome e sobrenome usando apenas letras.");
            }
            try {
                if (nome.split("\\s+").length < 2) {
                    throw new PetException(
                            "O pet deve possuir nome e sobrenome."
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
    public static void idadeValidator(String idade){
        final String NAOINFORMADO = "NÃO INFORMADO";
        if (idade.isBlank()){
            idade = NAOINFORMADO;
        }
        else {
            try {
                double idadeValor = Double.parseDouble(idade);
                if (idadeValor > 20) {
                    throw new PetException(
                            "Idade Invalida!"
                    );
                }
            } catch (PetException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Não digite letras.");
            }
        }
    }

    public static void pesoValidator(String peso){
        final String NAOINFORMADO = "NÃO INFORMADO";
        if (peso.isBlank()){
            peso = NAOINFORMADO;
        }
        else {
            try {
                double pesoValor = Double.parseDouble(peso);
                if (pesoValor > 60 || pesoValor < 0.5) {
                    throw new PetException(
                            "Peso Invalido!"
                    );
                }
            } catch (PetException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Não digite letras.");
            }
        }
    }
    public static void racaValidator(String raca){
        final String NAOINFORMADO = "NÃO INFORMADO";
        Pattern patternRaca = Pattern.compile("^[A-Za-zÀ-ÿ]+(?:\\s+[A-Za-zÀ-ÿ]+)*$");
        Matcher matcherRaca = patternRaca.matcher(raca);
        if (raca.isBlank()){
            raca = NAOINFORMADO;
        }
        else {
            if (!matcherRaca.matches()) {
                System.out.println("Você não pode usar números nem caracteres especiais.");
            }
        }
    }
    public static int lerNValido(Scanner input) {
        try {
            System.out.print("Digite um número: ");
            int numValido = input.nextInt();
            input.nextLine();
            return numValido;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número valido.");
            input.nextLine();
            return lerNValido(input);
        }
    }
}

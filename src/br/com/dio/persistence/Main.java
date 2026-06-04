package br.com.dio.persistence;

import java.io.IOException;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("=======================================================");
        FilePersistent filePersistent = new IOFilePersistence("user.csv");
        System.out.println("=======================================================");
        System.out.println(filePersistent.write("Miguel;Miguel@macedo.com;01/12/2004"));
        System.out.println("=======================================================");
        System.out.println(filePersistent.write("Maria;Maria@Machado.com;10/09/1990"));
        System.out.println("=======================================================");
        System.out.println(filePersistent.write("Joao;Joao@Farias.com;20/07/2010"));
        System.out.println("=======================================================");
        System.out.println(filePersistent.findAll());
        System.out.println("=======================================================");
        System.out.println(filePersistent.remove("Miguel"));
        System.out.println("=======================================================");
        System.out.println(filePersistent.findBy("Miguel@macedo.com"));
        System.out.println("=======================================================");
        System.out.println(filePersistent.remove("2010"));
        System.out.println("=======================================================");
        System.out.println(filePersistent.findAll());



    }
}

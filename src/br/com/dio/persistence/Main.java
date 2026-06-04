package br.com.dio.persistence;

import java.io.IOException;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        FilePersistent filePersistent = new NIOFilePersistence("user.csv");
        System.out.println(filePersistent.write("Bianca;Bia@.gmailcom;10/12/2004"));
        System.out.println("========================");
        System.out.println(filePersistent.write("Ricardo;Ricardo@gmail.com;10/05/2000"));
        System.out.println("========================");
        System.out.println(filePersistent.write("Marisa;Mariasa@gmail.com;/5/09/1890"));
        System.out.println("========================");
        System.out.println(filePersistent.findAll());


    }
}

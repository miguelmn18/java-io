package br.com.dio.persistence;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        FilePersistent filePersistent = new IOFilePersistence("user.csv");
        System.out.println(filePersistent.write("Miguel ; Miguel@macedo.com ; 01/12/2004"));

    }
}

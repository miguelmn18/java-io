package br.com.dio.persistence;

import java.io.*;

public class IOFilePersistence implements  FilePersistent{

    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = "/manager/Files/IO/";
    private final String fileName;

    public IOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);
        if(!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();
    }

    @Override
    public String write(String dado) {

        try{
        var fileWriter = new FileWriter(currentDir + storeDir + fileName, true);
        var buffereWriter = new BufferedWriter(fileWriter);
        var printWrite = new PrintWriter(buffereWriter);
        printWrite.println(dado);

        } catch (IOException ex){
            ex.printStackTrace();
        }
        return dado;
    }

    @Override
    public boolean remove(String sentence) {
        return false;
    }

    @Override
    public String replace(String oldSentence, String newSentecen) {
        return null;
    }

    @Override
    public String findAll() {
        return null;
    }

    @Override
    public String findBy(String sentence) {
        return null;
    }

    private void clearFile(){
        try {
            OutputStream outputStream = new FileOutputStream(new File(currentDir + storeDir + fileName));
            System.out.printf("Inicializando recursos (%s) \n" , currentDir + storeDir + fileName);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void createFile(){}
}

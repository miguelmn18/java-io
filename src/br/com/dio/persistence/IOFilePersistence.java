package br.com.dio.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class IOFilePersistence implements  FilePersistent{

    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = "/manager/Files/NIO/";
    private final String fileName;

    public IOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);
        if(!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();
    }

    @Override
    public String write(String dado) {

        try (var fileWriter = new FileWriter(currentDir + storeDir + fileName, true);
                 var buffereWriter = new BufferedWriter(fileWriter);
                 var printWrite = new PrintWriter(buffereWriter)) {

            printWrite.println(dado);

        } catch (IOException ex){
            ex.printStackTrace();
        }
        return dado;
    }

    @Override
    public boolean remove(String sentence) {
        var content = findAll();
        var contentList = new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());

        if(contentList.stream().noneMatch(c -> c.contains(sentence))) return false;

        clearFile();
        contentList.stream().filter(c -> c.contains(sentence))
                .forEach(this::write);
        return true;
    }

    @Override
    public String replace(String oldSentence, String newSentece) {

        var content = findAll();
        var contentList = new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
        if(contentList.stream().noneMatch(c -> c.contains(oldSentence))) return "";
        clearFile();
        contentList.stream()
                .map(c -> c.contains(oldSentence) ? newSentece : c)
                .forEach(this::write);
        return newSentece;
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        try (var reader = new BufferedReader( new FileReader(currentDir + storeDir + fileName))){
            String line;
            do {
                line = reader.readLine();
                if(line != null) content.append(line)
                        .append(System.lineSeparator());
            } while (line != null);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        var  found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storeDir + fileName))){
            String line = reader.readLine();
            while (line != null) {
                if((line.contains(sentence))){
                    found = line;
                    break;
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return found;
    }

    public void clearFile(){
        try {
            OutputStream outputStream = new FileOutputStream(new File(currentDir + storeDir + fileName));
            System.out.printf("Inicializando recursos (%s) \n" , currentDir + storeDir + fileName);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void createFile(){}
}

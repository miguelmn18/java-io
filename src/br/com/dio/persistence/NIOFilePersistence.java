package br.com.dio.persistence;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.stream.Stream;

public class NIOFilePersistence implements  FilePersistent{

    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = "/manager/Files/IO/";
    private final String fileName;


    public NIOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);
        if(!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");
        clearFile();

    }


    @Override
    public String write(String dado) {
        try (var file = new RandomAccessFile(new File(currentDir + storeDir + fileName),"rw");)
        { file.seek(file.length());
            file.writeBytes(dado);
            file.writeBytes(System.lineSeparator());

        } catch (IOException ex){
            ex.printStackTrace();
        }
        return dado;
    }

    @Override
    public boolean remove(String sentence) {
        var content = findAll();
        var contentList = Stream.of(content.split(System.lineSeparator())).toList();
        if(contentList.stream().noneMatch(c -> c.contains(sentence)))return false;
        clearFile();
        contentList.stream().filter(c -> !c.contains(sentence))
                .forEach(this::write);
        return true;
    }

    @Override
    public String replace(String oldSentence, String newSentecen) {
        return "";
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        try(
                var file = new RandomAccessFile(new File(currentDir + storeDir + fileName), "r");
                var channel = file.getChannel();
        ){
            var buffer = ByteBuffer.allocate(256);
            var bytesReader = channel.read(buffer);
            while(bytesReader != -1){
                buffer.flip();
                while(buffer.hasRemaining()){
                    content.append((char) buffer.get());
                }
                buffer.clear();
                bytesReader = channel.read(buffer);
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        var content = new StringBuilder();
        try(
                var file = new RandomAccessFile(new File(currentDir + storeDir + fileName), "r");
                var channel = file.getChannel();
        ){
            var buffer = ByteBuffer.allocate(256);
            var bytesReader = channel.read(buffer);
            while(bytesReader != -1){
                buffer.flip();
                while(buffer.hasRemaining()) {

                    while (!content.toString().endsWith(System.lineSeparator())) {
                        content.append((char) buffer.get());
                    }
                    if (content.toString().contains(sentence)) {
                        break;
                    } else {
                        content.setLength(0);
                    }
                    if(!content.isEmpty()) break;
                }


                buffer.clear();
                bytesReader = channel.read(buffer);
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();

    }
    private void clearFile(){
        try {
            OutputStream outputStream = new FileOutputStream(new File(currentDir + storeDir + fileName));
            System.out.printf("Inicializando recursos (%s) \n" , currentDir + storeDir + fileName);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

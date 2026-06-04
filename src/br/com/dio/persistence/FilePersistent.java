package br.com.dio.persistence;

public interface FilePersistent {

    String write(final String dado);

    boolean remove(final String sentence);

    String replace(final String oldSentence, final String newSentecen);

    String findAll();

    String findBy(final String sentence);

}

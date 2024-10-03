package com.capgemini.demos.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.opensearch.OpenSearchEmbeddingStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.List;

@Slf4j
//@SpringBootApplication
public class DocumentsIngestor implements CommandLineRunner {
    public static void main(String[] args) {
        System.setProperty("spring.main.web-application-type", "NONE");
        SpringApplication.run(DocumentsIngestor.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively(
            "/home/illia/projects/ecb_demo/docs3"
        );

//        Document document = UrlDocumentLoader.load("https://learn.microsoft.com/en-us/azure/virtual-machines/windows/quick-create-portal", new ApacheTikaDocumentParser());

        EmbeddingStore<TextSegment> embeddingStore = OpenSearchEmbeddingStore.builder()
            .serverUrl("http://localhost:9200")
            .indexName("ecb_rag")
            .build();

        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
//        EmbeddingStoreIngestor.ingest(document, embeddingStore);
    }
}

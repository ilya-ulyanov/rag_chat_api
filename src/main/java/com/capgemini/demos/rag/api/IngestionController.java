package com.capgemini.demos.rag.api;

import com.capgemini.demos.rag.configuration.IngestionProperties;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class IngestionController {
    private final IngestionProperties properties;
    private final EmbeddingStore<TextSegment> embeddingStore;

    @PostMapping("sync_docs")
    public ResponseEntity<Boolean> syncDocuments() {
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively(properties.getDocumentsPath());
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        return ResponseEntity.ok(true);
    }
}

package com.capgemini.demos.rag.api;

import com.capgemini.demos.rag.configuration.IngestionProperties;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
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
    private final EmbeddingModel embeddingModel;

    @PostMapping("sync_docs")
    public ResponseEntity<Boolean> syncDocuments() {
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively(properties.getDocumentsPath());
        var ingestor = EmbeddingStoreIngestor
            .builder()
            .embeddingStore(embeddingStore)
            .embeddingModel(embeddingModel)
            .documentSplitter(DocumentSplitters.recursive(500, 50, new HuggingFaceTokenizer()))
            .build();

        ingestor.ingest(documents);
        return ResponseEntity.ok(true);
    }
}

### Opensearch setup

```shell
docker volume create opensearch_rag
docker run -d --name opensearch_rag \
  -p 9200:9200 \
  -p 9600:9600 \
  -e "discovery.type=single-node" \
  -e "plugins.security.disabled=true" \
  -e "OPENSEARCH_INITIAL_ADMIN_PASSWORD=Very_Diff_Pass^123*" \
  -v opensearch_rag:/usr/share/opensearch/data \
  opensearchproject/opensearch:latest
```

### Build docker image

```shell
docker build . -t cap_rag_demo_api:latest
```

### Run service

```shell
docker run -d --name rag_api \
  -p 12001:8080 \
  -e OPEN_AI_API_KEY=xxx \
  -e OPENSEARCH_URL=http://x.x.x.x:9200 \
  cap_rag_demo_api:latest
```
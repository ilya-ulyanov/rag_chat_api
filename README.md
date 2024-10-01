### Opensearch setup

```shell
docker volume create opensearch_rag
docker run -d --name opensearch_rag \
  -p 9200:9200 \
  -p 9600:9600 \
  -e "discovery.type=single-node" \
  -e "plugins.security.disabled=true" \
  -v opensearch_rag:/usr/share/opensearch/data \
  opensearchproject/opensearch:latest
```

### Build docker image

```shell
docker build . --build-arg JAR_FILE=target/*.jar -t cap_rag_demo_service:latest
```
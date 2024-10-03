docker stop rag_api
docker rm rag_api
docker rmi cap_rag_demo_api:latest
docker build . -t cap_rag_demo_api:latest
docker run -d --name rag_api \
  -p 12001:8080 \
  -e OPEN_AI_API_KEY=xxx \
  -e OPENSEARCH_URL=http://192.168.1.68:9200 \
  cap_rag_demo_api:latest
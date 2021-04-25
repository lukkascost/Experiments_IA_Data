docker build -t lukkascost/data-analyze-commons:latest-arm commons.module/
docker push lukkascost/data-analyze-commons:latest-arm
docker manifest rm lukkascost/data-analyze-commons:latest || true
docker manifest create lukkascost/data-analyze-commons:latest lukkascost/data-analyze-commons:latest-arm lukkascost/data-analyze-commons:latest-amd64
docker manifest push lukkascost/data-analyze-commons:latest


docker build -t lukkascost/api-gateway:latest-arm api-gateway/
docker push lukkascost/api-gateway:latest-arm
docker manifest rm lukkascost/api-gateway:latest || true
docker manifest create lukkascost/api-gateway:latest lukkascost/api-gateway:latest-arm lukkascost/api-gateway:latest-amd64
docker manifest push lukkascost/api-gateway:latest


docker build -t lukkascost/cloud-discovery:latest-arm cloud-service/service-discovery/
docker push lukkascost/cloud-discovery:latest-arm
docker manifest rm lukkascost/cloud-discovery:latest || true
docker manifest create lukkascost/cloud-discovery:latest lukkascost/cloud-discovery:latest-arm lukkascost/cloud-discovery:latest-amd64
docker manifest push lukkascost/cloud-discovery:latest



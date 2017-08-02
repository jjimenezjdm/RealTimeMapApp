#Authors
Este proyecto se ha hecho de forma conjunta por:

- Jose María Muñoz Rey
- Judit Jiménez Jiménez

# Compile code:

Change the "ribw_red" filed and the tag numbers. To run this commands, you must cd in the "web" folder.

```bash
docker build -t gcr.io/ribw_red/hello-node:1.1 .
```
```bash
gcloud docker -- push gcr.io/ribw_red/hello-node:1.1
```
# Node:

To run this commands, you must cd in the "web folder"

```bash
kubectl create -f hello-node-deployment.yaml
```
```bash
kubectl create -f hello-node-service.yaml
```

# Redis:

 To run this commands, you must cd in the "redis_ribw" folder.

```bash
kubectl create -f redis-master-deployment.yaml
```
```bash
kubectl create -f redis-master-service.yaml
```
```bash
kubectl expose deployment redis-master --name=redis-masterex --type=LoadBalancer --port=6379 --target-port=6379
```

# Server (CSV reader)

Remember to change the IP and put the Load Balancer's IP.

#  Other

See the logs from a Pod:

```bash
kubectl logs -f POD-NAME
```

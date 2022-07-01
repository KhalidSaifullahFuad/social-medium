# DOCKER

> **Stop and delete all container**
```dockerfile
docker system prune -af
```

> **Delete specific image**
```dockerfile
docker rmi 'image_id'
```

> **Delete All images**
```dockerfile
docker image rm -f $(docker images ls -q)
```

> **Create the Docker image `social_medium_image`.**
```dockerfile
docker build -t social_medium_image:latest .
```

> **Docker compose**

```dockerfile
docker-compose up
```

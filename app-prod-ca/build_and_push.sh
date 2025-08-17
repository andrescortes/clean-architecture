#!/bin/bash

# Configuration
IMAGE_NAME="devcoderjava/app-prod-ca"
TAG="latest"
DOCKERFILE_PATH="."  # Path to your Dockerfile
DOCKER_HUB_USERNAME="devcoderjava"  # Your Docker Hub username

# Step 1: Build the Docker image
echo "Building Docker image..."
docker build -t $IMAGE_NAME:$TAG $DOCKERFILE_PATH

if [ $? -ne 0 ]; then
    echo "Error: Docker build failed"
    exit 1
fi

# Step 3: Push the image
echo "Pushing image to Docker Hub..."
docker push $IMAGE_NAME:$TAG

if [ $? -ne 0 ]; then
    echo "Error: Docker push failed"
    exit 1
fi

echo "Successfully built and pushed $IMAGE_NAME:$TAG to Docker Hub"

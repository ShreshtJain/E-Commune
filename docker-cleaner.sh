#!/bin/bash

echo "Nettoyage des conteneurs, images, volumes et réseaux Docker..."

# Arrêter tous les conteneurs en cours d'exécution
sudo docker stop $(sudo docker ps -q) 2>/dev/null

# Supprimer tous les conteneurs
sudo docker rm $(sudo docker ps -a -q) 2>/dev/null

# Supprimer toutes les images
sudo docker rmi $(sudo docker images -q) --force 2>/dev/null

# Supprimer tous les volumes
sudo docker volume rm $(sudo docker volume ls -q) 2>/dev/null

# Supprimer tous les réseaux non utilisés
sudo docker network prune -f

echo "Nettoyage Docker terminé."

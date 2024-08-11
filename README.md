docker login \
docker volume create postgres_data \
docker pull fabriciofsousa/kfcrazy:latest \
docker build -t fabriciofsousa/kfcrazy:latest . \
docker compose up

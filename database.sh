#! /bin/sh
sudo mkdir -p /opt/dados/sodicas
docker run -d -v /opt/dados/sodicas:/var/lib/postgresql/data --network local --name sodicasdb -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=sodicas postgres:9.5

docker rm -f sodicasapi
docker rmi gilmariokpslow/sodicasapi
mvn clean package
./image.sh
./container.sh
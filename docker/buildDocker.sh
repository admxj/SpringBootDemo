#!/bin/bash

cd ../
mvn clean package -Pdev

cp ./target/springboot.jar ./

cd docker/
docker build -t harbor.repo.admxj.cn/admxj/springboot:v1.0 .

docker run -it harbor.repo.admxj.cn/admxj/springboot:v1.0

#docker push harbor.repo.admxj.cn/admxj/springboot:v1.0
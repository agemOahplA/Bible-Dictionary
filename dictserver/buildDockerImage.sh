#!/bin/bash
# maven清理
echo "Maven编译"
mvn clean package
# 编译镜像
echo "Docker编译镜像"
# shellcheck disable=SC2046
docker build . --tag  dict.cruciata.org/dictserver:1.0.0

echo "镜像列表"

docker images



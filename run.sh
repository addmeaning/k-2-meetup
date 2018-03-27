#!/usr/bin/env bash
if [[ -z "${DOCKER_REPO}" ]]; then
  echo environment variable DOCKER_REPO is undefined
elif [[ -z "${DOCKER_IMAGE_LABEL}" ]]; then
 echo environment variable DOCKER_IMAGE_LABEL is undefined
elif [[ -z "${K8S_MASTER_URL}" ]]; then
 echo environment variable K8S_MASTER_URL is undefined
elif [[ -z "${SPARK_HOME}" ]]; then
 echo environment variable SPARK_HOME is undefined
else
 ${SPARK_HOME}/bin/spark-submit \
    --master k8s://$K8S_MASTER_URL \
    --deploy-mode cluster \
    --name meetup-app \
    --class com.addmeaning.meetup.SparkApp \
    --conf spark.executor.instances=2 \
    --conf spark.kubernetes.container.image.pullPolicy=Always \
    --conf spark.kubernetes.authenticate.driver.serviceAccountName=spark \
    --conf spark.kubernetes.container.image=$DOCKER_REPO/$DOCKER_IMAGE_LABEL:latest  \
    local:///app/app.jar
fi
#!/usr/bin/env bash
if [[ -z "${DOCKER_REPO}" ]]; then
  echo environment variable DOCKER_REPO is undefined
elif [[ -z "${DOCKER_IMAGE_LABEL}" ]]; then
 echo environment variable DOCKER_IMAGE_LABEL is undefined
else
  sbt clean assembly && docker build . -t $DOCKER_REPO/$DOCKER_IMAGE_LABEL:latest && docker push $DOCKER_REPO/$DOCKER_IMAGE_LABEL:latest
fi
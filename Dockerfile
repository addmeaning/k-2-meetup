FROM addmeaning/spark:blank
RUN mkdir app
COPY target/scala-2.11/sparkathon-kubernetes-meetup-assembly-1.0.jar app/app.jar

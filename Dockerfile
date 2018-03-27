FROM addmeaning/spark:blank
RUN mkdir app
COPY target/scala-2.11/k-2-meetup-assembly-1.0.jar /app/app.jar

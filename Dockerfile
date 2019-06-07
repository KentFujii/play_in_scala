FROM hseeberger/scala-sbt
ADD . /root
EXPOSE 9000
CMD ["sbt", "run"]

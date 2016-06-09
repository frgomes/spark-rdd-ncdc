name := "spark-rdd-ncdc"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.1" % "provided"
/*,
  "org.apache.spark" %% "spark-sql" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-hive" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-streaming-flume" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-mllib" % "1.6.1" % "provided",
  "com.hadoop.gplcompression" % "hadoop-lzo" % "0.4.17",
  "mysql" % "mysql-connector-java" % "5.1.31",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.0.0-rc5",
  "com.datastax.spark" %% "spark-cassandra-connector-java" % "1.0.0-rc5",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.holdenkarau" %% "spark-testing-base" % "0.0.1" % "test"
*/
)


mainClass in assembly := some("io.peartree.NcdcRddJob")
assemblyJarName := "spark-rdd-ncdc-1.0.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

/*
resolvers ++= Seq(
  "JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  Resolver.sonatypeRepo("public")
)
*/

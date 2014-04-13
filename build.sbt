name := "tweetie"

version := "0.1"

scalaVersion := "2.10.3"

autoCompilerPlugins := true

scalaVersion := Version.scala

resolvers += "spray-releases" at "http://repo.spray.io"

libraryDependencies ++= Dependencies.tweetie

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)



//resolvers ++= Seq(
//  "sonatype" at "https://oss.sonatype.org/content/groups/public",
//  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
//  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
//  "central.maven" at "http://central.maven.org/maven2",
//  "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/",
//  "twitter" at "http://maven.twttr.com/"
//)

//libraryDependencies ++= Seq(
//  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT"
//)

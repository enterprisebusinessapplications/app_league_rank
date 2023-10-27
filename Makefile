build:
	sbt clean update compile
	sbt test
	sbt assembly
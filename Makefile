.PHONY: deploy
deploy: package cert
	java -jar target/idlerpg-1.0-SNAPSHOT.jar server idlerpg.yaml

.PHONY: package
package:
	mvn package

.PHONY: deploy
deploy: package cert
	java -jar target/idlerpg-1.0-SNAPSHOT.jar server cfg/idlerpg.yaml

.PHONY: package
package:
	mvn package

.PHONY: cert
cert:
	make -C cert

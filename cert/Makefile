PASSWORD=foobar123

localhost.jks: localhost.p12
	keytool -v -importkeystore -srckeystore $< -srcstoretype PKCS12 -srcstorepass $(PASSWORD) -destkeystore $@ -deststoretype JKS -deststorepass $(PASSWORD)

localhost.p12: localhost.key localhost.pem
	openssl pkcs12 -export -inkey localhost.key -in localhost.pem -out $@ -passout pass:$(PASSWORD)

localhost.key localhost.pem:
	openssl req -newkey rsa:2048 -sha256 -nodes -keyout localhost.key -x509 -days 365 -out localhost.pem -subj "/CN=localhost/"

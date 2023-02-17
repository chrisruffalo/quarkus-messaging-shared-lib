# Quarkus Messaging Shared Library Example

## Purpose
To attempt to show the different ways that a shared library can be added to a multi-module or
shared module Quarkus project.

## Conclusion
There are some benefits to creating a simple java module that does not include Quarkus components but if you are in an 
all-quarkus ecosystem it is probably easier to just build a quarkus version of the library.

## Structure
- bom : a module for collection/management dependencies without using parent inheritence
- vanilla-shared : a module that _does not_ need quarkus dependencies and can be used in any smallrye messaging application
- quarkus-shared : a module based on quarkus-smallrye-reactive-messaging that _does_ cause dependencies on quarkus
- quarkus-add : simple app that uses both shared libraries

## How do I know the decorators are being loaded
When running the tests the decorators (vanilla and quarkus) will add metadata to the payload. The consumers can check
for metadata on the payload and will print it out.

```bash
[]$ mvn clean verify
# ... snip ...
INFO: payload: some payload (interception points: vanilla publish, quarkus subscribe, quarkus publish, vanilla subscribe)
```

Or you can build it
```bash
[]$ mvn clean install
# or natively...
[]$ mvn clean install -Pnative
```

And run it
```bash
[]$ java -jar quarkus-app/target/quarkus-app/quarkus-run.jar
# or natively...
[]$ ./quarkus-app/target/quarkus-messaging-shared-lib-app-1.0.0-runner
```

And then, in another terminal, make a post to it
```bash
[]$ curl -v -d "hello" localhost:8080/ -H "Content-type: text/plain" -H "Accept: text/plain"
```

And in the logs of your application you will see:
```text
2023-02-17 15:12:34,428 INFO  [org.exa.Consumer] (executor-thread-1) payload: hello (interception points: vanilla publish, quarkus subscribe, quarkus publish, vanilla subscribe)
```
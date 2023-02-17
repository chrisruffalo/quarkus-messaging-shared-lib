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

## Building/Use

To build, `mvn clean install` is sufficient. Once built you can use the `quarkus-app` module to enter in to quarkus
dev mode:
```bash
[]$ mvn clean install
[]$ cd quarkus-app
[]$ mvn quarkus:dev
```

There is one resource at `/` that accepts a POST and a string body. You can also use dev mode's swagger ui to invoke it.
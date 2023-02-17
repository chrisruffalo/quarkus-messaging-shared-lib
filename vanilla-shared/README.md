# Vanilla Shared Library

## Bottom Line
This is only really useful in environments that re pretty decoupled and actual benefit from the amount of work
that it takes to decouple here.

### Pros:
- Does not cause quarkus to be required by projects that depend on this library
  - Can work with slightly different versions of smallrye without conflict or complex dependency management
- Standard-compliant (only operates within the standard) and does not rely on Quarkus

### Cons:
- Still need to bring in some sort of CDI to test, which in this case is Quarkus which begs the question "why?"
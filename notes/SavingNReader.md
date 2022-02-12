# Saving N Reading

The basis of this conversion factor and the previous ((Loading N Caching)[/LoadingNCaching.md]) are tied together in order to process said files within the program.

This method first does said operation beforehand and then process the file, however this difference with any other functionalities, is that we are now allowed to use a thread:
```java
java.lang.Thread t = new java.lang.Thread();
```
This in turn can mess up other futher processes, which can invoke things like a `NullPointerException` or a `IllegalArgumentException`

Threading this process could enable it to move faster but only at certain SPECIFIC processes, ending this process very quickly can destroy further processes.

<hr>

This process the following said steps:

<ul>
1. Process the file or said object beforehand<br>
2. Save the file or object to a <code>native-dir</code><br>
3. Then we await for the previous step to finish then we can continue with our further processes <br>
</ul>
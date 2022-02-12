# Loading N Caching

The basis of Loading N Caching is the concept of first identifying the source location of said file, then proceeding
to copy that file to a native directory (particularly closer to that of the program), after this operation, the file's
properties will be cached or literally stored to a file on the local hard drive.

## Model

```java
// First identify the file source and dest
// where native_dir = preferred program dir
// where parent_dir = original file dir
dest = file(native_dir)
src = file(parent_dir)

cacher = cacher(some_dir_to_save)

if(not native_dir.contains src) {
  UTIL.copyContents(
    from: src
    to: dest
  )
}
// a lambda "->" signifies we load thus file
cacher -> properties_file(native_dir + "/properties.file")

// the cacher would mostly use a hashmap in order to achieve
// said values
// for example we can then thus use this hashamp to keep track
// of where we are and also if certain values exists in the map
// this is not like a need for any other form of data structure
// as mostly we do not care about the order
if(not cacher.contains(src)) {
  cacher.add(
    key: src,
    value; dest
  )
}

// do other stuffs with the code generated from the contaienr
```

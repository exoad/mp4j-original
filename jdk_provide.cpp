#include <iostream>
#include <libmymath.h>

int main()
{
  graal_isolate_t *isolate = NULL;
  graal_isolatethread_t *thread = NULL;

  if (graal_create_isolate(NULL, &isolate, &thread) != 0)
  {
    fprintf(stderr, "initialization error\n");
    return 1;
  }

  printf("Result> %d\n", ceilingPowerOfTwo(thread, 14));
  return 0;
}
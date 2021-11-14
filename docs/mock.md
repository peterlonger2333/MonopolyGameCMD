# About Mocking

Our current APIs may seem hard to test since most of them have logics for interactions with users embedded in their calls. However, the impact is a lot less when we know the "communication channels" used are `System.in` and `System.out`. With Mockito, a test strategy naturally surfaced:

1. Before each test begins, replace `System.in` and `System.out` with mocked `InputStream` and `PrintStream` respectively.
2. For mocked `InputStream`, setup user input.
3. For mocked `PrintStream`, checks for expected output.

Note that subclasses of the above streams can be used instead of the exact interfaces. This method is still not flexible enough since it requires *exact match*, for example output strings must exactly match between the of the APIs and that in the tests.
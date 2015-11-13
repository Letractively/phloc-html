# Introduction #

phloc-html is a Java 1.6 library for that lets you easily create DOM trees of HTML elements with an easy to use API that hides the underlying HTML names from you and can perform consistency checks on the created tree. Also offers a consistent way to create JavaScript? code from Java, using structured objects (JSDOM).

Prerequisites: as **phloc-html depends** on [phloc-commons](http://code.google.com/p/phloc-commons), [phloc-css](http://code.google.com/p/phloc-css) and [phloc-json](http://code.google.com/p/phloc-json), the following examples also do. Additionally [SLF4J](http://www.slf4j.org/) is required.

# Usage with Maven #

To build phloc-html from source, Maven 3.0.4 is required. Any version below does **NOT** work!

To use phloc-html put the following to your pom.xml:
```
...
  <repositories>
...
    <repository>
      <id>phloc.com</id>
      <url>http://repo.phloc.com/maven2</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
...
  <dependencies>
    ...
    <dependency>
      <groupId>com.phloc</groupId>
      <artifactId>phloc-html</artifactId>
      <version>3.7.3</version>
    </dependency>
  </dependencies>
...
```

# Creating HTML programtically #

```
...
```
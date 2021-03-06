# Pinto

![Pinto](https://github.com/Complexible/pinto/raw/master/pinto.png)

Pinto is a Java framework for transforming Java beans into RDF (and back).

The current version is 2.0 as of 2016-06-14.  We're using [git flow](http://nvie.com/posts/a-successful-git-branching-model/)
for development.

Inspired by [Jackson](https://github.com/FasterXML/jackson) and [Empire](http://github.com/mhgrove/Empire), it aims to
be simple and easy to use.  No annotations or configuration are required.  If you have a compliant Java bean Pinto will
turn it into RDF.

The [rdf4j](http://rdf4j.com) framework is used to represent RDF primitives such as Graph and Statement.

## Building

To create the artifacts:

```bash
$ gradle jar
```

And to run the tests:

```bash
$ gradle test
```

## Example Usage

Given this simple Java Bean:

```java
public static final class Person {
    private String mName;

    public Person() {
    }

    public Person(final String theName) {
        mName = theName;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String theName) {
        mName = theName;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mName);
    }

    @Override
    public boolean equals(final Object theObj) {
        if (theObj == this) {
            return true;
        }
        else if (theObj instanceof Person) {
            return Objects.equal(mName, ((Person) theObj).mName);
        }
        else {
            return false;
        }
    }
}
```

You can easily serialize it as RDF:

```java
Graph aGraph = RDFMapper.create().writeValue(new Person("Michael Grove"));
```

And `aGraph` serialized as NTriples:

```
<tag:complexible:pinto:f97658c7048377a026111c7806bd7280> <tag:complexible:pinto:name> "Michael Grove"^^<http://www.w3.org/2001/XMLSchema#string> .
```

And if you have that RDF, you can turn it back into a `Person`:

```java
final Person aPerson RDFMapper.create().readValue(aGraph, Person.class)
```

This is the quick and dirty example, but for more detailed examples, check out the tests.

## Annotations

Pinto does not require annotations to serialize Beans as RDF, but does support a few basic annotations so you can
control how the object is serialized.

### `@RdfId`

An annotation which specifies the properties to be used for generating the URI of the object.  By default, a hash of
the object itself is used to generate a URI.  But when present on a getter or setter of one or more properties on the
bean, the values of those properties will be used as the seed for the URI.

Note: There is a secondary mechanism for controlling the URI of an object.  If the object implements `Identifiable` the
mapper will use the URI returned by `#id` ignoring any `@RdfId` annotated properties.

### `@RdfProperty`

An annotation which can be applied to a property on a bean, either the getter or the setter, which specifies the
URI of the property when generating RDF for the bean.  Normally, a URI for the property is auto-generated, but when
this annotation is present, the URI specified in the annotation is used instead.  The value of the annotation can also
be a QName.

### `@RdfsClass`

An annotation which can be applied to a class to specify the `rdf:type` of the class when generating the RDF.  Can be
a QName or a URI.  When not present, no `rdf:type` assertion is generated for the object.

### `@Iri`

Annotation which can be used to control the URI assigned to an `Enum`.  Normally the URI's are generated by Pinto, but
if you want to map them to specific constants in your ontology, you can use `Iri` to explicitly identify the objects.

```java
public enum TestEnum {
    @Iri("urn:my_ontology:test:Foo")
    Foo,

    @Iri("urn:my_ontology:test:Bar")
    Bar,
}
```

## Configuration

By default, `RDFMapper` does not require any configuration, it's meant to generate reasonable RDF out of the box.  There
are a couple (de)serialization options which are specified by `MappingOptions`:

* `REQUIRE_IDS` - By default, Pinto will auto-generate URIs for objects when `@RdfId` is not specified.  By setting this property to `true` the mapper will not auto-generate URIs, they must be specified explicitly. (default: `false`)
* `SERIALIZE_COLLECTIONS_AS_LISTS` - When true, collections are serialized as RDF lists.  Otherwise, they're serialized using `Collection#size` separate property assertions. (default: `false`)
* `IGNORE_INVALID_ANNOTATIONS` - Whether or not to ignore an annotation which is invalid, such as `@RdfProperty` which defines a property with an invalid URI.  Properties with invalid/ignored annotations are simply not used when generating a Bean or RDF. (default: `true`)

Beyond these configuration options, `RDFMapper` has a few other configuration mechanisms that can be specified on its
`Builder` when creating the mapper:

* `#map(URI, Class)` - Specify the provided type corresponds to instances of the given Java class.  Functions like the `@RdfsClass` annotation.
* `#namespace(...)` - Methods to specify namespace mappings which are used to expand any QNames used in the annotations
* `#valueFactory(ValueFactory)` - Provide the `ValueFactory` to be used when creating RDF from a bean
* `#collectionFactory(CollectionFactory)` - The factory to be used for creating instances of `java.util.Collection`.  Defaults to `DefaultCollectionFactory`
* `#mapFactory(MapFactory)` - The factory to be used for creating instances of `java.util.Map`.  Defaults to `DefaultMapFactory`

## Custom serialization

In some cases, an object won't ahere to the Java Bean specification, or it's a third-party class that you don't control
so you cannot add annotations, but you need a specific serialization.  For these cases `RDFCodec` can be used.  It's
a small plugin to `RDFMapper` which will handle transforming a Java object to/from RDF.  Pinto includes an example
implementation of a codec for `java.util.UUID` called `UUIDCodec`.

Codecs are registered when the `RDFMapper` is created via its builder: `Builder.codec(Class<T>, RDFCodec<T>)`

## Why Pinto?

Why create Pinto when there are similar frameworks available?  Well, the other frameworks, like
[Empire](http://github.com/mhgrove/Empire) or [Alibaba](https://bitbucket.org/openrdf/alibaba) are focused on more than
just transforming Beans into RDF and back. Neither are a good fit for _just_ round-tripping between beans and RDF.

A good example is if you're building a JAX-RS based web service and you have some bean in your domain that you'd like
to serialize as RDF, or accept as RDF, that's normally done with a custom implementation of
`MessageBodyReader`/`MessageBodyWriter`.  But that implementation is not straight-forward with the heavier-weight
frameworks.  With Pinto, it's a single line of code.

